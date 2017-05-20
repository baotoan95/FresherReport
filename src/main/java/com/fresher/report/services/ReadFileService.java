package com.fresher.report.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.el.parser.ParseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fresher.report.entities.AccessHistory;
import com.fresher.report.entities.InterviewResult;
import com.fresher.report.entities.Point;
import com.fresher.report.entities.SiteClass;
import com.fresher.report.entities.Trainee;
import com.fresher.report.entities.Trainer;

public class ReadFileService {
	public static List<Trainee> readTraineeFile(String excelFilePath) throws ParseException, IOException {
		List<Trainee> listTrainee = new ArrayList<Trainee>();
		FileInputStream inputStream;
		inputStream = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet firstSheet = workbook.getSheet("Trainee general info");
		Iterator<Row> iterator = firstSheet.iterator();

		String account = "";
		String status = "";

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();

			Trainee trainee = new Trainee();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			int rowNum = nextRow.getRowNum();
			if (rowNum > 2) {
				// To filter column headings
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						trainee.setEmployeeId((String) nextCell.getStringCellValue());
						break;
					case 1:
						trainee.setNationalId((String) nextCell.getStringCellValue());
						break;

					case 2:
						account = nextCell.getStringCellValue();
						trainee.setAccount(account);
						trainee.setId(account);
						break;

					case 3:
						trainee.setFullName((String) nextCell.getStringCellValue());
						break;

					case 4:
						trainee.setBranch((String) nextCell.getStringCellValue());
						break;

					case 5:
						trainee.setParentDepartment((String) nextCell.getStringCellValue());
						break;

					case 6:
						trainee.setUniversity((String) nextCell.getStringCellValue());
						break;

					case 7:
						trainee.setFaculty((String) nextCell.getStringCellValue());
						break;

					case 8:
						trainee.setEmail((String) nextCell.getStringCellValue());
						break;

					case 9:
						String phone = null;
						try {
							phone = nextCell.getStringCellValue();
						} catch (Exception e) {
							phone = Double.toString(nextCell.getNumericCellValue());
						}
						trainee.setPhone(phone);
						break;

					case 10:
						trainee.setFacebook((String) nextCell.getStringCellValue());
						break;

					case 11:
						String universityGraduation = "";
						try {
							Date date = nextCell.getDateCellValue();
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
							universityGraduation = formatter.format(date);
							trainee.setUniversityGraduation(universityGraduation);
						} catch (Exception e) {
							universityGraduation = nextCell.getStringCellValue();
						}
						break;

					case 12:
						Date fullTimeWorkingDate = nextCell.getDateCellValue();
						trainee.setFullTimeWorkingDate(fullTimeWorkingDate);
						break;

					case 13:
						status = (String) nextCell.getStringCellValue();
						if ("".equals(status)) {
							status = "In progress";
						}
						trainee.setStatus(status);
						break;

					case 14:
						Date startDate = nextCell.getDateCellValue();
						trainee.setStarDate(startDate);
						break;

					case 15:
						Date endDate = nextCell.getDateCellValue();
						trainee.setEndDate(endDate);
						break;

					case 16:
						int learningTime = (int) nextCell.getNumericCellValue();
						trainee.setLearningTime(learningTime);
						break;

					case 17:
						float finalGrade = (float) nextCell.getNumericCellValue();
						trainee.setFinalGrade(finalGrade);
						break;

					case 18:
						String completionLevel = (String) nextCell.getStringCellValue();
						trainee.setCompletionLevel(completionLevel);
						break;

					case 19:
						trainee.setCertificateProvider((String) nextCell.getStringCellValue());
						break;

					case 20:
						trainee.setCertificateGroup((String) nextCell.getStringCellValue());
						break;

					case 21:
						trainee.setCertificateSubGroup((String) nextCell.getStringCellValue());
						break;

					case 22:
						trainee.setCertificateName((String) nextCell.getStringCellValue());
						break;

					case 23:
						trainee.setCertificateCode((String) nextCell.getStringCellValue());
						break;

					case 24:
						trainee.setrECInterviewDate((String) nextCell.getStringCellValue());
						break;

					case 25:
						trainee.setrECInterviewStatus((String) nextCell.getStringCellValue());
						break;

					case 26:
						trainee.setNote((String) nextCell.getStringCellValue());
						break;

					}
				}
			}
			if (trainee != null && null != trainee.getFullName() && !trainee.getFullName().equals("")) {
				listTrainee.add(trainee);
			}
		}

		workbook.close();
		inputStream.close();

		return listTrainee;

	}

	public static List<SiteClass> readClassFile(String excelFilePath) throws Exception {
		List<SiteClass> listClass = new ArrayList<SiteClass>();
		FileInputStream inputStream;
		inputStream = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheet("Courses, seminars, workshops");
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();

			SiteClass siteClass = new SiteClass();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			int rowNum = nextRow.getRowNum();
			if (rowNum > 3) {
				// To filter column headings
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {

					case 0:
						siteClass.setSite(nextCell.getStringCellValue());
						break;
					case 1:
						siteClass.setCourseCode(nextCell.getStringCellValue());
						break;
					case 2:
						siteClass.setName(nextCell.getStringCellValue());
						break;
					case 3:
						siteClass.setAttendeeType(nextCell.getStringCellValue());
						break;
					case 4:
						siteClass.setSubjectType(nextCell.getStringCellValue());
						break;
					case 5:
						siteClass.setSubSubjectType(nextCell.getStringCellValue());
						break;
					case 6:
						siteClass.setFormatType(nextCell.getStringCellValue());
						break;
					case 7:
						siteClass.setScope(nextCell.getStringCellValue());
						break;
					case 8:
						siteClass.setDeliveryType(nextCell.getStringCellValue());
						break;
					case 9:
						siteClass.setSupplien(nextCell.getStringCellValue());
						break;
					case 10:
						Date plannedStartDate = nextCell.getDateCellValue();
						siteClass.setPlannedStartDate(plannedStartDate);
						break;
					case 11:
						Date plannedEndDate = nextCell.getDateCellValue();
						siteClass.setPlannedEndDate(plannedEndDate);
						break;
					case 12:
						int plannedLearningTime = (int) nextCell.getNumericCellValue();
						siteClass.setPlannedLearningTime(plannedLearningTime);
						break;
					case 13:
						int planCountStudent = (int) nextCell.getNumericCellValue();
						siteClass.setPlanCountStudent(planCountStudent);
						break;
					case 14:
						try {
							float plannedExpense = (float) nextCell.getNumericCellValue();
							siteClass.setPlannedExpense(plannedExpense);
						} catch (Exception e) {
							// TODO: handle exception
						}
						break;
					case 15:
						siteClass.setProjectCode(nextCell.getStringCellValue());
						break;
					case 16:
						Date actualStartDate = nextCell.getDateCellValue();
						siteClass.setActualStartDate(actualStartDate);
						break;
					case 17:
						Date actualEndDate = nextCell.getDateCellValue();
						siteClass.setActualStartDate(actualEndDate);
						break;
					case 18:
						int actualLearningTime = (int) nextCell.getNumericCellValue();
						siteClass.setActualLearningTime(actualLearningTime);
						break;
					case 19:
						int currentCountStudent = (int) nextCell.getNumericCellValue();
						siteClass.setCurrentCountStudent(currentCountStudent);
						break;
					case 20:
						float actualExpense = 0f;
						try {
							actualExpense = (float) nextCell.getNumericCellValue();
							siteClass.setActualExpense(actualExpense);
						} catch (Exception e) {
							// TODO: handle exception
						}
						break;
					case 21:
						int numberofEnrolledTrainee = (int) nextCell.getNumericCellValue();
						siteClass.setNumberofEnrolledTrainee(numberofEnrolledTrainee);
						break;
					case 22:
						int numberofGraduates = (int) nextCell.getNumericCellValue();
						siteClass.setNumberofGraduates(numberofGraduates);
						break;
					case 23:
						int trainingFeedback = (int) nextCell.getNumericCellValue();
						siteClass.setTrainingFeedback(trainingFeedback);
						break;
					case 24:
						int trainingFeedbackContent = (int) nextCell.getNumericCellValue();
						siteClass.setTrainingFeedbackContent(trainingFeedbackContent);
						break;
					case 25:
						int trainingFeedbackTeacher = (int) nextCell.getNumericCellValue();
						siteClass.setTrainingFeedbackTeacher(trainingFeedbackTeacher);
						break;
					case 26:
						int trainingFeedbackOrganization = (int) nextCell.getNumericCellValue();
						siteClass.setTrainingFeedbackOrganization(trainingFeedbackOrganization);
						break;
					case 27:
						siteClass.setUpdateBy(nextCell.getStringCellValue());
						break;
					case 28:
						Date updateDate = nextCell.getDateCellValue();
						siteClass.setUpdateDate(updateDate);
						break;
					case 29:
						siteClass.setNote(nextCell.getStringCellValue());
						break;
					case 30:
						Date startDate = nextCell.getDateCellValue();
						siteClass.setStartDate(startDate);
						break;
					case 31:
						Date endDate = nextCell.getDateCellValue();
						siteClass.setEndDate(endDate);
						break;
					case 32:
						int learningTime = (int) nextCell.getNumericCellValue();
						siteClass.setLearningTime(learningTime);
						break;
					case 33:
						int numberOfTrainee = (int) nextCell.getNumericCellValue();
						siteClass.setNumberOfTrainee(numberOfTrainee);
						break;
					case 34:
						int expense = 0;
						try {
							expense = (int) nextCell.getNumericCellValue();
						} catch (Exception e) {
							// TODO: handle exception
						}
						siteClass.setExpense(expense);
						break;
					case 35:
						String courseStatus = nextCell.getStringCellValue();
						siteClass.setStatus(courseStatus);
						break;
					case 36:
						int startYear = (int) nextCell.getNumericCellValue();
						siteClass.setStartYear(startYear);
						break;
					case 37:
						int startMonth = (int) nextCell.getNumericCellValue();
						siteClass.setStartMonth(startMonth);
						break;
					case 38:
						int endYear = (int) nextCell.getNumericCellValue();
						siteClass.setEndYear(endYear);
					case 40:
						break;
					}
				}
				if (!"".equals(siteClass.getCourseCode().trim())) {
					listClass.add(siteClass);
				}

			}

		}

		workbook.close();
		inputStream.close();

		return listClass;

	}

	/*
	 * Return Map 1. classId: Name of class 2. trainer: Trainer object
	 */
	public static Map<String, Object> readTrainerFile(String excelFilePath) throws Exception {
		Map<String, Object> result = new HashMap<>();
		FileInputStream inputStream = null;
		Trainer trainer = null;
		XSSFWorkbook workbook = null;

		inputStream = new FileInputStream(new File(excelFilePath));
		workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet("Overall plan");
		Row rowTrainerInfo = sheet.getRow(21);
		Row rowClassId = sheet.getRow(4);

		// Read for class code
		String classId = rowClassId.getCell(2).getStringCellValue();
		if (classId != null) {
			classId = classId.substring(0, classId.indexOf("_"));
		} else {
			classId = "";
		}

		// Read for trainer
		trainer = new Trainer();
		trainer.setId(rowTrainerInfo.getCell(3).getStringCellValue());
		trainer.setAccount(rowTrainerInfo.getCell(3).getStringCellValue());
		trainer.setFullName(rowTrainerInfo.getCell(2).getStringCellValue());
		trainer.setPhone(rowTrainerInfo.getCell(4).getStringCellValue());
		trainer.setEmail(rowTrainerInfo.getCell(5).getStringCellValue());

		result.put("classId", classId);
		result.put("trainer", trainer);
		inputStream.close();
		workbook.close();
		return result;
	}

	/*
	 * Return class name and list trainee of it 1. className: value (String) 2.
	 * trainees: value (List<Trainee>)
	 */
	public static Map<String, Object> readFileIntern(String excelFilePath, String imageStore) throws IOException {
		Map<String, Object> result = new HashMap<>();

		// Check folder to store image
		File folder = new File(imageStore);
		if (!folder.exists()) {
			folder.mkdir();
		}

		String ext, fileName, imagePath, className;
		List<Trainee> listTrainee = new ArrayList<Trainee>();
		FileInputStream inputStream = null;
		String imageDirectoryPath = imageStore;
		XSSFWorkbook workbook = null;
		Sheet firstSheet;
		XSSFPictureData pictureData = null;
		XSSFPicture picture;
		byte[] picData;
		FileOutputStream out;

		inputStream = new FileInputStream(new File(excelFilePath));
		workbook = new XSSFWorkbook(inputStream);
		firstSheet = workbook.getSheetAt(0);

		className = firstSheet.getSheetName();

		Iterator<Row> iterator = firstSheet.iterator();
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFDrawing drawing = sheet.createDrawingPatriarch();
		List<XSSFPicture> listPicture = new ArrayList<XSSFPicture>();
		for (XSSFShape shape : drawing.getShapes()) {
			if (shape instanceof XSSFPicture) {
				picture = (XSSFPicture) shape;
				listPicture.add(picture);
			}
		}

		Row nextRow;
		int rowNum;

		while (iterator.hasNext()) {
			nextRow = iterator.next();
			Trainee trainee = new Trainee();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			rowNum = nextRow.getRowNum();

			String recordId = "";
			Cell recordIdCell = nextRow.getCell(2);
			if (recordIdCell != null) {
				recordId = recordIdCell.getStringCellValue();
			}
			trainee.setId(recordId);

			while (cellIterator.hasNext() && (rowNum > 2)) {
				XSSFCell nextCell = (XSSFCell) cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				nextCell.getRowIndex();
				switch (columnIndex) {
				case 1:
					pictureData = findPictureByRow(rowNum, listPicture);
					if (pictureData == null) {
						break;
					}

					picData = pictureData.getData();
					ext = pictureData.suggestFileExtension();
					fileName = recordId + "." + ext;
					imagePath = imageDirectoryPath + File.separator + fileName;
					trainee.setImagePath(!recordId.equals("") ? fileName : "avatar.jpg");
					out = new FileOutputStream(imagePath);
					out.write(picData);
					out.close();
					break;
				case 4:
					trainee.setFullName(nextCell.getStringCellValue());
					break;
				case 26:
					try {
						trainee.setPhone((String) nextCell.getStringCellValue());
					} catch (Exception e) {
						trainee.setPhone((String) Double.toString(nextCell.getNumericCellValue()));
					}
					break;
				case 27:
					trainee.setEmail(nextCell.getStringCellValue());
					break;

				}
			}
			if (trainee.getFullName() != null && trainee.getFullName() != "") {
				listTrainee.add(trainee);
			}
		}

		result.put("className", className);
		result.put("trainees", listTrainee);
		workbook.close();
		inputStream.close();

		return result;
	}

	private static XSSFPictureData findPictureByRow(int row, List<XSSFPicture> pictureList) {
		XSSFPictureData pictureData = null;
		for (XSSFPicture p : pictureList) {
			XSSFClientAnchor anchor = (XSSFClientAnchor) p.getAnchor();
			if (anchor.getRow1() == row) {
				pictureData = p.getPictureData();
				break;
			}
		}
		if (pictureData == null) {
			for (XSSFPicture p : pictureList) {
				XSSFClientAnchor anchor = (XSSFClientAnchor) p.getAnchor();
				if (anchor.getRow2() == (row + 1)) {
					pictureData = p.getPictureData();
					break;
				}
			}
		}

		return pictureData;
	}

	public static Map<String, List<Point>> readPointFile(String pathFile) throws IOException {
		Map<String, List<Point>> result = new HashMap<>();
		FileInputStream inputStream = null;
		XSSFWorkbook workbook = null;

		inputStream = new FileInputStream(new File(pathFile));
		workbook = new XSSFWorkbook(inputStream);
		// Get sheet 3
		Sheet sheet = workbook.getSheet("Topic Mark");
		// Get all rows of sheet
		Iterator<Row> rows = sheet.iterator();

		String traineeId = "";
		List<Point> points = null;
		Point point = null;
		int cellStartPoints = 6;
		int cellContainsAccount = 3;
		int rowStartContainsPoints = 5;
		Row nameOfSubjectRow = sheet.getRow(2);

		// Iterate rows
		while (rows.hasNext()) {
			Row nextRow = rows.next();

			if (nextRow.getRowNum() > rowStartContainsPoints) {
				if (nextRow.getCell(cellContainsAccount) != null) {
					// Get empId
					traineeId = nextRow.getCell(cellContainsAccount).getStringCellValue();
					// Get point of empId
					int numOfCells = nextRow.getPhysicalNumberOfCells();
					if (numOfCells > cellStartPoints) {
						points = new ArrayList<Point>();
						for (int indexOfCell = cellStartPoints; indexOfCell < numOfCells; indexOfCell++) {
							point = new Point();
							point.setName(nameOfSubjectRow.getCell(indexOfCell).getStringCellValue());
							point.setValue(nextRow.getCell(indexOfCell).getNumericCellValue());
							points.add(point);
						}
					}
					// Save list points for each empId to map collection
					if (!"".equals(traineeId)) {
						result.put(traineeId, points);
					}
				}
			}

		}
		workbook.close();
		inputStream.close();

		return result;
	}

	public static Map<String, List<AccessHistory>> readAttendance(String path) throws IOException {
		Map<String, List<AccessHistory>> result = new HashMap<>();
		List<AccessHistory> accessHistories = null;
		AccessHistory accessHistory = null;
		XSSFWorkbook workbook = null;
		FileInputStream fileInputStream = null;

		fileInputStream = new FileInputStream(new File(path));
		workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheet("Attendance status");
		Iterator<Row> iteratorrow = sheet.iterator();
		Row row13 = sheet.getRow(12);
		String empId = "";

		int indexRow = 0;
		while (iteratorrow.hasNext()) {
			Row row = iteratorrow.next();
			if (indexRow > 13) {
				Cell cell1 = row.getCell(2);
				if (cell1.getStringCellValue().isEmpty()) {
					break;
				}

				accessHistories = new ArrayList<>();
				accessHistory = new AccessHistory();

				if (row.getCell(0) != null) {
					empId = row.getCell(0).getStringCellValue();
				}

				for (int i = 4; i < row.getPhysicalNumberOfCells() - 46; i++) {
					Cell cell = row.getCell(i);
					if (cell != null && !cell.getStringCellValue().isEmpty()) {
						accessHistory.setDate(row13.getCell(i).getDateCellValue());
						accessHistory.setStatus(row.getCell(i).getStringCellValue());
						accessHistories.add(accessHistory);
					}
				}
				result.put(empId, accessHistories);
			}
			indexRow++;
		}
		workbook.close();
		fileInputStream.close();
		return result;
	}

	// Read tab GPA in trainee management
	public static Map<String, String> readNoteOfTrainer(String pathFile) throws IOException {
		Map<String, String> result = new HashMap<>();
		FileInputStream inputStream = null;
		XSSFWorkbook workbook = null;

		inputStream = new FileInputStream(new File(pathFile));
		workbook = new XSSFWorkbook(inputStream);
		// Get sheet 5
		Sheet sheet = workbook.getSheet("GPA");
		// Get all rows of sheet
		Iterator<Row> rows = sheet.iterator();

		String traineeId = "";
		String note = "";
		int cellContainsAccount = 2;
		int cellContainsNote = 23;
		int rowStartContainsData = 3;
		// Iterate rows
		while (rows.hasNext()) {
			Row nextRow = rows.next();
			if (nextRow.getRowNum() > rowStartContainsData) {
				if (nextRow.getCell(cellContainsAccount) != null) {
					// Get empId
					traineeId = nextRow.getCell(cellContainsAccount).getStringCellValue();
					// Get note of trainer
					note = nextRow.getCell(cellContainsNote).getStringCellValue();
					// Save list points for each empId to map collection
					if (!"".equals(traineeId)) {
						result.put(traineeId, note);
					}
				}
			}

		}
		workbook.close();
		inputStream.close();
		return result;
	}

	public static List<InterviewResult> readInterviewResult(String excelFilePath) throws ParseException, IOException {
		List<InterviewResult> listInterview = new ArrayList<InterviewResult>();
		FileInputStream fileInputStream;
		fileInputStream = new FileInputStream(new File(excelFilePath));
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = firstSheet.iterator();

		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			InterviewResult interviewResult = new InterviewResult();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			int rowNum = nextRow.getRowNum();
			if (rowNum > 2) {
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {

					case 1:
						interviewResult.setEmail((String) nextCell.getStringCellValue());
						break;
					case 2:
						try {
							interviewResult.setPhone((String) nextCell.getStringCellValue());
						} catch (Exception e) {
							interviewResult.setPhone((String) Double.toString(nextCell.getNumericCellValue()));
						}
						break;
					case 3:
						interviewResult.setInterviewResult((String) nextCell.getStringCellValue());
						break;
					case 4:
						interviewResult.setNote((String) nextCell.getStringCellValue());
						break;
					}

				}
				if (interviewResult != null && interviewResult.getPhone() != null
						&& !interviewResult.getPhone().equals("")
						|| interviewResult != null && interviewResult.getEmail() != null
								&& !interviewResult.getEmail().equals("")) {
					listInterview.add(interviewResult);
				}
			}
		}

		workbook.close();
		fileInputStream.close();
		return listInterview;
	}

	@SuppressWarnings("resource")
	public static Map<String, List<AccessHistory>> readAttendance2(String path)
			throws IOException, java.text.ParseException {
		Map<String, List<AccessHistory>> result = new HashMap<>();
		XSSFWorkbook workbook = null;
		FileInputStream fileInputStream = null;
		Map<String, List<AccessHistory>> tempMap = new HashMap<>();
		fileInputStream = new FileInputStream(new File(path));

		workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheet("Attendance status");
		Iterator<Row> iterator = sheet.iterator();
		Row nextRow;
		List<Date> listDate = new ArrayList<Date>();
		boolean readNote = false; // kiem tra nextRow da chay vo table Note

		while (iterator.hasNext()) {
			nextRow = iterator.next();
			int rowNum = nextRow.getRowNum();
			int lastCell = nextRow.getLastCellNum() - 16;
			List<String> listStatus = new ArrayList<String>();
			int i = 0;
			if (rowNum == 12) {

				Date date = new Date();
				// doc DateTime khi lop dang in progress tra ve 1 list
				for (i = 4; i <= lastCell; i++) {
					date = nextRow.getCell(i).getDateCellValue();
					listDate.add(date);
				}
			}

			if (rowNum > 12) {

				String stringCellFirst = "";
				String stringCel = nextRow.getCell(1).getStringCellValue().trim();
				try {
					stringCellFirst = nextRow.getCell(0).getStringCellValue();
				} catch (Exception e) {
					// Exception khi stringCellFrest la int
				}
				// kiem tra nextRow khi vao Note
				if ("".equals(stringCellFirst) && "Account".equals(stringCel.trim())) {
					readNote = true;
					nextRow = iterator.next();
				}
				// kiem tra nextRow khi read table attendence
				if (readNote == false) {
					String account = nextRow.getCell(2).getStringCellValue().trim();
					int j;
					// doc diem danh cua fresher
					for (j = 4; j <= lastCell; j++) {
						try {
							String status = nextRow.getCell(j).getStringCellValue();
							listStatus.add(status);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					List<AccessHistory> listAccessHistory = getListAccessHistory(listDate, listStatus);
					if (!"".equals(account)) {
						tempMap.put(account, listAccessHistory);
					}
				}

				if (readNote == true) {
					// set Note vao accessHistory
					Map<String, List<AccessHistory>> temp = getNoteAccessHistory(tempMap, nextRow);
					result.putAll(temp);
				}
			}
		}
		return result;
	}

	// read comlum Ngay nghi
	public static List<Date> getListDateTableNote(String stringDate) throws java.text.ParseException {
		List<Date> listDate = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String[] words = stringDate.split("-");
		int lenghtWords = words.length;
		String lastarr = words[lenghtWords - 1];
		Date dateLast = formatter.parse(lastarr);
		listDate.add(dateLast);
		cal.setTime(dateLast);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int i;
		int last = lenghtWords - 1;

		for (i = 0; i < last; i++) {
			int day = Integer.parseInt(words[i]);
			cal.clear();
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);
			Date date = cal.getTime();
			listDate.add(date);
		}
		return listDate;
	}

	// read table note and add set Note AccessHistory
	public static Map<String, List<AccessHistory>> getNoteAccessHistory(Map<String, List<AccessHistory>> listMap,
			Row row) throws java.text.ParseException {
		Set<String> keySet = listMap.keySet();
		Iterator<String> accounts = keySet.iterator();
		List<AccessHistory> listAccessHistory = new ArrayList<AccessHistory>();
		Map<String, List<AccessHistory>> resultMap = new HashMap<>();
		String accountCell = row.getCell(1).getStringCellValue();

		String note = row.getCell(3).getStringCellValue();
		List<Date> listDate = new ArrayList<>();

		try {
			String stringDate = row.getCell(2).getStringCellValue();
			listDate = getListDateTableNote(stringDate);
		} catch (Exception e) {
			System.out.println(row.getRowNum());
			Date datetmp = row.getCell(2).getDateCellValue();
			listDate.add(datetmp);
		}

		while (accounts.hasNext()) {
			String account = accounts.next().trim();
			if (accountCell.equals(account)) {
				listAccessHistory = listMap.get(account);
				for (Date date : listDate) {
					for (AccessHistory accessHistory : listAccessHistory) {
						if (date.equals(accessHistory.getDate())) {
							accessHistory.setNote(note);
						}
					}
				}
				resultMap.put(account, listAccessHistory);
				listMap.putAll(resultMap);
			}
		}
		return listMap;
	}

	// read list AccessHistory table attendance
	public static List<AccessHistory> getListAccessHistory(List<Date> listDate, List<String> listStatus) {
		List<AccessHistory> listAccessHistory = new ArrayList<>();
		AccessHistory accessHistory;
		int i;
		int j;

		for (i = 0; i < listDate.size(); i++) {
			accessHistory = new AccessHistory();
			for (j = 0; j < listStatus.size(); j++) {
				if (i == j) {
					accessHistory.setDate(listDate.get(i));
					accessHistory.setStatus(listStatus.get(j));
				}
			}
			listAccessHistory.add(accessHistory);
		}
		return listAccessHistory;
	}

}
