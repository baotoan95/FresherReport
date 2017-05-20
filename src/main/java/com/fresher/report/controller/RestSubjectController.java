package com.fresher.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fresher.report.entities.Subject;
import com.fresher.report.services.ISubjectService;

@RestController
@RequestMapping(value = "/rest-subject")
public class RestSubjectController {
	@Autowired
	private ISubjectService subjectService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Subject>> listSubject() {

		List<Subject> subjects = subjectService.findAll();

		if (subjects.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(subjects, HttpStatus.OK);

	}
}
