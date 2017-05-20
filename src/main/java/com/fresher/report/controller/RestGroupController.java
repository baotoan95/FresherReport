package com.fresher.report.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fresher.report.dto.GroupDTO;
import com.fresher.report.entities.ClassStatistic;
import com.fresher.report.entities.Group;
import com.fresher.report.entities.PersonStatistic;
import com.fresher.report.services.IGroupService;
import com.fresher.report.services.ISiteClassService;
import com.fresher.report.services.IWaitingStatisticService;

@RestController
@RequestMapping(value = "/dashboard")
public class RestGroupController {
	@Autowired
	private ISiteClassService siteClassService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IWaitingStatisticService waitingStatisticService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<GroupDTO>> getStatistic(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {
		List<GroupDTO> listGroupDTO = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date fDate = formatter.parse(fromDate);
			Date tDate = formatter.parse(toDate);
			// Increment one day to match with statistic
			Calendar c = Calendar.getInstance();
			c.setTime(formatter.parse(toDate));
			c.add(Calendar.DATE, 1);
			tDate = c.getTime();

			List<Group> listGroup = groupService.findAll();
			
			for (Group group : listGroup) {
				String groupName = group.getName();
				// Update class statistic
				long totalClass = siteClassService.countByGroupAndDate(groupName, fDate, tDate);
				long waitingClass = waitingStatisticService.countWaitingByGroupNameAndTypeAndDate(groupName, "class", fDate, tDate);
				long inProgressClass = siteClassService.countByGroupAndDateAndStatus(groupName, fDate, tDate, "In progress");
				long doneClass = siteClassService.countByGroupAndDateAndStatus(groupName, fDate, tDate, "Done");
				totalClass += waitingClass;

				// Update person statistic
				long inProgressPerson = siteClassService.countplanCountStudentByGroupAndDateAndStatus(groupName, fDate, tDate, "In progress");
				long donePerson = (long) siteClassService.countplanCountStudentByGroupAndDateAndStatus(groupName, fDate, tDate, "Done");
				long waitingPerson = waitingStatisticService.countWaitingByGroupNameAndTypeAndDate(groupName, "person", fDate, tDate);
				long totalPerson = inProgressPerson + donePerson + waitingPerson;
				
				ClassStatistic classStatistic = new ClassStatistic("ClassStatistic", totalClass, doneClass, inProgressClass);
				PersonStatistic personStatistic = new PersonStatistic("PersonStatistic", totalPerson, donePerson, inProgressPerson);

				group.setClassStatistic(classStatistic);
				group.setPersonStatistic(personStatistic);
				
				// Add to result
				listGroupDTO.add(new GroupDTO(group, siteClassService.getIssuesByGroupAndDate(group.getId(), fDate, tDate)));
				
				// Update group info
				groupService.save(group);
			}

			return new ResponseEntity<>(listGroupDTO, HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
