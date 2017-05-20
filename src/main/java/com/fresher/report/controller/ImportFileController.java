package com.fresher.report.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fresher.report.constants.PathContant;
import com.fresher.report.entities.AccessHistory;
import com.fresher.report.entities.Comment;
import com.fresher.report.entities.Group;
import com.fresher.report.entities.InterviewResult;
import com.fresher.report.entities.Point;
import com.fresher.report.entities.SiteClass;
import com.fresher.report.entities.Skill;
import com.fresher.report.entities.Trainee;
import com.fresher.report.entities.Trainer;
import com.fresher.report.services.IGroupService;
import com.fresher.report.services.ISiteClassService;
import com.fresher.report.services.ITraineeService;
import com.fresher.report.services.ITrainerService;
import com.fresher.report.services.ReadFileService;
import com.fresher.report.services.UploadFileService;
import com.fresher.report.utils.TraineeUtils;

@RestController
public class ImportFileController {
	@Autowired
	private ISiteClassService siteClassService;
	@Autowired
	private ITraineeService traineeService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private ITrainerService trainerService;
	@Autowired
	private ServletContext context;

	@RequestMapping(value = "/import-class-info", method = RequestMethod.POST)
	public ResponseEntity<Boolean> importClassInfo(MultipartHttpServletRequest request, @RequestParam(value = "classId", required = false) String classId) {
		// Upload file
		List<String> fileNames = UploadFileService.uploadMultipleFile(request);

		if (fileNames.size() > 0) {
			for (String fileName : fileNames) {
				// Read trainees
				List<Trainee> trainees = null;
				Map<String, List<Point>> points = null;
				Map<String, String> notes = null;
				try {
					trainees = ReadFileService.readTraineeFile(PathContant.UPLOAD_PATH + File.separator + fileName);
					points = ReadFileService.readPointFile(PathContant.UPLOAD_PATH + File.separator + fileName);
					notes = ReadFileService.readNoteOfTrainer(PathContant.UPLOAD_PATH + File.separator + fileName);
				} catch (Exception e) {
					return new ResponseEntity<>(false, HttpStatus.OK);
				}
				
				// Get class
				if(classId == null) {
					classId = fileName.substring(0, fileName.indexOf("_"));
				}
				SiteClass siteClass = siteClassService.findOne(classId);

				// Insert trainees to database
				for (Trainee trainee : trainees) {
					traineeService.save(trainee, siteClass);
				}

				// Update point for trainees
				Set<String> keySet = points.keySet();
				Iterator<String> traineeIds = keySet.iterator();
				while (traineeIds.hasNext()) {
					String traineeId = traineeIds.next();
					Trainee trainee = traineeService.findOne(traineeId);

					if (null != trainee) {
						List<Point> scoreBoard = points.get(traineeId);
						trainee.setListPoint(scoreBoard);
						trainee.setFinalGrade(TraineeUtils.computeAverage(scoreBoard));
						trainee.setStars(TraineeUtils.countStars(trainee.getFinalGrade()));
						trainee.setRank(TraineeUtils.computeRank(trainee.getFinalGrade()));
						traineeService.save(trainee, trainee.getSiteClass());
					}
				}

				// Update note for trainee
				keySet = notes.keySet();
				traineeIds = keySet.iterator();
				List<Comment> listComment = null;
				while (traineeIds.hasNext()) {
					String traineeId = traineeIds.next();
					Trainee trainee = traineeService.findOne(traineeId);

					if (null != trainee) {
						listComment = new ArrayList<Comment>();
						listComment.add(
								new Comment(new Date(), trainee.getSiteClass().getHeadTeacher(), notes.get(traineeId)));
						trainee.setListComment(listComment);
						traineeService.save(trainee, trainee.getSiteClass());
					}

				}
			}
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/import-internships", method = RequestMethod.POST)
	public ResponseEntity<Boolean> importInternships(MultipartHttpServletRequest request) {
		// Upload file
		List<String> fileNames = UploadFileService.uploadMultipleFile(request);

		if (fileNames.size() > 0) {

			for (String fileName : fileNames) {
				Map<String, Object> result = new HashMap<>();
				try {
					result = ReadFileService.readFileIntern(PathContant.UPLOAD_PATH + File.separator + fileName,
							context.getRealPath("/WEB-INF/resources/avatars"));
				} catch (IOException e) {
					e.printStackTrace();
					return new ResponseEntity<>(false, HttpStatus.OK);
				}

				String className = (String) result.get("className");
				@SuppressWarnings("unchecked")
				List<Trainee> trainees = (List<Trainee>) result.get("trainees");

				// Check group Internship if it does not exist then create a new one
				Group group = groupService.findByName("Internship");
				if (group == null) {
					group = new Group();
					group.setName("Internship");
					group.setId("Internship");
					groupService.save(group);
				}

				// Get class
				SiteClass siteClass = siteClassService.findOne(className);

				if (siteClass == null) {
					siteClass = new SiteClass();
					siteClass.setId(className);
					siteClass.setName(className);
					siteClass.setPlanCountStudent(trainees.size());
					siteClass.setCurrentCountStudent(trainees.size());
					siteClass.setStartDate(new Date());
					siteClass.setPlannedStartDate(new Date());
					siteClass.setStatus("In progress");
					siteClass.setGroup(group);
					siteClass.setAttendeeType(group.getName());

					siteClassService.save(siteClass);
				}

				// Insert trainees to database
				for (Trainee trainee : trainees) {
					Trainee oldTrainee = traineeService.findOne(trainee.getId());
					if (oldTrainee != null) {
						trainee.setListPoint(oldTrainee.getListPoint());
						trainee.setListComment(oldTrainee.getListComment());
						trainee.setListPoint(oldTrainee.getListPoint());
						trainee.setSiteClass(oldTrainee.getSiteClass());
					}
					trainee.setSiteClass(siteClass);
					traineeService.save(trainee, siteClass);
				}
			}

		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/import-classes", method = RequestMethod.POST)
	public ResponseEntity<Boolean> importClass(@RequestParam("file") MultipartFile file) throws ParseException {
		// File name
		String fileName = file.getOriginalFilename();

		// Upload file
		boolean uploadSuccess = UploadFileService.uploadSingleFile(file, fileName);

		if (uploadSuccess) {
			// Read file
			List<SiteClass> siteClasses = new ArrayList<>();
			try {
				siteClasses = ReadFileService.readClassFile(PathContant.UPLOAD_PATH + File.separator + fileName);
			} catch (Exception e) {
				return new ResponseEntity<>(false, HttpStatus.OK);
			}

			// Insert classes to database
			for (SiteClass siteClass : siteClasses) {
				String siteClassName = siteClass.getCourseCode().substring(0, siteClass.getCourseCode().indexOf("_"));
				siteClass.setId(siteClassName);
				siteClass.setName(siteClassName);
				SiteClass oldSiteClass = siteClassService.findOne(siteClassName);
				// Update site class if it's exist
				if (oldSiteClass != null) {
					siteClass.setHeadTeacher(oldSiteClass.getHeadTeacher());
					siteClass.setListIssue(oldSiteClass.getListIssue());
				}

				siteClassService.save(siteClass);
			}

			// Update for group
			groupService.updateStatistic();
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/import-headteacher", method = RequestMethod.POST)
	public ResponseEntity<Boolean> importHeadTeacher(@RequestParam("file") MultipartFile file, @RequestParam("classId") String classId) {
		// File name
		String fileName = file.getOriginalFilename();

		// Upload file
		boolean uploadSuccess = UploadFileService.uploadSingleFile(file, fileName);

		if (uploadSuccess) {
			// Read file
			Map<String, Object> result = new HashMap<>();
			try {
				result = ReadFileService.readTrainerFile(PathContant.UPLOAD_PATH + File.separator + fileName);
			} catch (Exception e) {
				return new ResponseEntity<>(false, HttpStatus.OK);
			}

			// Save or update head teacher to database
			Trainer trainer = (Trainer) result.get("trainer");
			trainerService.save(trainer);

			// Update info for class
			SiteClass siteClass = siteClassService.findOne(classId);

			if (null != siteClass) {
				siteClass.setHeadTeacher(trainer);
				siteClassService.save(siteClass);
				
				// Update comment trainer for trainees
				List<Trainee> trainees = traineeService.findBySiteClass(classId);
				for(Trainee trainee : trainees) {
					List<Comment> comments = trainee.getListComment();
					for(Comment comment : comments) {
						if(comment.getTrainer() == null) {
							comment.setTrainer(trainer);
							traineeService.save(trainee, siteClass);
							break;
						}
					}
				}
			}
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/import-interview-result", method = RequestMethod.POST)
	public ResponseEntity<Boolean> importInterviewResult(@RequestParam("file") MultipartFile file, @RequestParam("classId") String classId) {
		// File name
		String fileName = file.getOriginalFilename();

		// Upload file
		boolean uploadSuccess = UploadFileService.uploadSingleFile(file, fileName);

		if (uploadSuccess) {
			// Read file
			List<InterviewResult> interviewResults = new ArrayList<>();
			try {
				interviewResults = ReadFileService.readInterviewResult(PathContant.UPLOAD_PATH + File.separator + fileName);
			} catch (Exception e) {
				return new ResponseEntity<>(false, HttpStatus.OK);
			}

			// Update interview result for trainee if email match with email in interviewResults
			List<Trainee> listTrainee = traineeService.findBySiteClass(classId);
			for(InterviewResult interviewResult : interviewResults) {
				System.out.println(interviewResult);
				for(Trainee trainee : listTrainee) {
					if(trainee.getEmail().trim().equalsIgnoreCase(interviewResult.getEmail())) {
						String note = interviewResult.getNote();
						String strSkills = interviewResult.getInterviewResult();
						String[] listStrSkill = strSkills.trim().split(",");
						List<Skill> listSkill = new ArrayList<>();
						for(String skill : listStrSkill) {
							String[] skillAndLevel = skill.trim().split(":");
							System.out.println(skillAndLevel[0] + " - " + Double.parseDouble(skillAndLevel[1]));
							listSkill.add(new Skill(skillAndLevel[0], Double.parseDouble(skillAndLevel[1]), ""));
						}
						
						// Update trainee
						if(trainee.getListComment().size() <= 2) {
							trainee.getListComment().add(new Comment(new Date(), new Trainer("Interviewer", "Interviewer"), note));
						}
						trainee.setListSkill(listSkill);
						traineeService.save(trainee, trainee.getSiteClass());
					}
				}
			}
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/import-attendance", method = RequestMethod.POST)
	public ResponseEntity<Boolean> importAttendance(@RequestParam("file") MultipartFile file, @RequestParam("classId") String classId) {
		// File name
		String fileName = file.getOriginalFilename();

		// Upload file
		boolean uploadSuccess = UploadFileService.uploadSingleFile(file, fileName);

		if (uploadSuccess) {
			// Read file
			Map<String, List<AccessHistory>> result = new HashMap<>();
			try {
				result = ReadFileService.readAttendance2(PathContant.UPLOAD_PATH + File.separator + fileName);
			} catch (IOException | java.text.ParseException e) {
				return new ResponseEntity<>(false, HttpStatus.OK);
			}

			// Update attendance for trainees
			Set<String> keySet = result.keySet();
			Iterator<String> traineeIds = keySet.iterator();

			while (traineeIds.hasNext()) {
				String traineeId = traineeIds.next();
				Trainee trainee = traineeService.findOne(traineeId);
				if (trainee != null) {
					trainee.setListAccessHistory(result.get(traineeId));
					traineeService.save(trainee, trainee.getSiteClass());
				}
			}
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
