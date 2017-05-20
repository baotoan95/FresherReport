package com.fresher.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fresher.report.entities.Trainee;
import com.fresher.report.services.ITraineeService;

@RestController
@RequestMapping(value = "/rest-trainee")
public class RestTraineeController {
	@Autowired
	private ITraineeService traineeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Trainee>> listTrainee() {
		List<Trainee> trainees = traineeService.findAll();
		if (trainees.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(trainees, HttpStatus.OK);
	}

	@RequestMapping(value = "/trainee/{empId}", method = RequestMethod.GET)
	public ResponseEntity<Trainee> getTrainee(@PathVariable("empId") String empId) {
		Trainee trainee = traineeService.findOne(empId);
		if (null == trainee) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(trainee, HttpStatus.OK);
	}

	@RequestMapping(value = "/trainee-filter-status/{status}", method = RequestMethod.GET)
	public ResponseEntity<List<Trainee>> getTraineesByStatus(@PathVariable("status") String status) {
		List<Trainee> result = null;
		result = traineeService.findByStatus(status);
		if (result.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		System.out.println(status);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
