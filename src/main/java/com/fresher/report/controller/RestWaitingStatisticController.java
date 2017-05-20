package com.fresher.report.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fresher.report.entities.WaitingStatistic;
import com.fresher.report.services.IWaitingStatisticService;

@RestController
@RequestMapping("/waiting-statistic")
public class RestWaitingStatisticController {
	@Autowired
	private IWaitingStatisticService waitingStatisticService;

	@RequestMapping(value = "/add-statistic", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addWaitingStatistic(@RequestBody WaitingStatistic waitingStatistic) {
		try {
			waitingStatistic.setDate(new Date());
			waitingStatisticService.save(waitingStatistic);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
