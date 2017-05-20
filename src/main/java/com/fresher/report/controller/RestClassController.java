package com.fresher.report.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fresher.report.dto.SiteClassDTO;
import com.fresher.report.entities.SiteClass;
import com.fresher.report.entities.Trainee;
import com.fresher.report.services.ISiteClassService;
import com.fresher.report.services.ITraineeService;

@RestController
@RequestMapping(value = "/rest-class")
public class RestClassController {
	@Autowired
	private ISiteClassService siteClassService;
	@Autowired
	private ITraineeService traineeService;

	@RequestMapping(value = "/create-solution", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<SiteClass> createSolution(@RequestBody SiteClass siteClass) {
		siteClassService.save(siteClass);
		return new ResponseEntity<>(siteClass, HttpStatus.OK);
	}

	@RequestMapping(value = "/create-issue", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<SiteClass> createIssue(@RequestBody SiteClass siteClass) {
		siteClassService.save(siteClass);
		return new ResponseEntity<>(siteClass, HttpStatus.OK);
	}

	@RequestMapping(value = "/update-issue", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<SiteClass> updateIssue(@RequestBody SiteClass siteClass) {
		siteClassService.save(siteClass);
		return new ResponseEntity<>(siteClass, HttpStatus.OK);
	}

	@RequestMapping(value = "/create-class", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> createClass(@RequestBody SiteClass newClass) {
		newClass.setStatus("Planning");
		newClass.setId(newClass.getCourseCode());
		siteClassService.save(newClass);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/update-planning", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> updatePlanning(@RequestBody SiteClass newClass) {
		siteClassService.save(newClass);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "/classes/{group}/{status}/{fromDate}/{toDate}", method = RequestMethod.GET)
	public ResponseEntity<List<SiteClass>> listSiteClass(@PathVariable("group") String groupName,
			@PathVariable("status") String status, @PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate) {
		
		List<SiteClass> siteClasses = null;
		if ("total".equals(status)) {
			siteClasses = siteClassService.findAll();
		} else {
			 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			 try {
			 Date fDate = formatter.parse(fromDate);
			 Date tDate = formatter.parse(toDate);
			 siteClasses = siteClassService.findByGroupAndDateAndStatus(groupName, fDate, tDate, status);
			 }catch(Exception ex){
			
			 }
		}
		if (siteClasses == null || siteClasses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(siteClasses, HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/class/{id:.+}", method = RequestMethod.GET)
	public ResponseEntity<SiteClassDTO> findById(@PathVariable("id") String id) {
		SiteClass siteClass = siteClassService.findOne(id);
		if (siteClass != null) {
			// Get number of trainees by status
			long total = traineeService.countBySiteClass(siteClass.getId());
			long inProgress = traineeService.countBySiteClassAndStatus(siteClass.getId(), "In progress");
			long failed = traineeService.countBySiteClassAndStatus(siteClass.getId(), "Failed");
			long passed = traineeService.countBySiteClassAndStatus(siteClass.getId(), "Passed");
			long dropOut = traineeService.countBySiteClassAndStatus(siteClass.getId(), "Drop-out");
			List<Trainee> listTrainee = traineeService.findBySiteClass(siteClass.getId());
			SiteClassDTO siteClassDTO = new SiteClassDTO(siteClass, failed, passed, dropOut, listTrainee);
			return new ResponseEntity<>(siteClassDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
