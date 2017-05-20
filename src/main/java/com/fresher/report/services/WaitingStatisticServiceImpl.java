package com.fresher.report.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresher.report.entities.WaitingStatistic;
import com.fresher.report.repositories.IWaitingStatisticRepository;

@Service("waitingStatisticService")
public class WaitingStatisticServiceImpl implements IWaitingStatisticService {
	@Autowired
	private IWaitingStatisticRepository waitingStatisticRepository;

	@Override
	public void save(WaitingStatistic waitingStatistic) {
		waitingStatisticRepository.save(waitingStatistic);
	}

	@Override
	public long countWaitingByGroupNameAndTypeAndDate(String groupName, String type, Date fromDate, Date toDate) {
		return waitingStatisticRepository.countWaitingByGroupNameAndTypeAndDate(groupName, type, fromDate, toDate);
	}

}
