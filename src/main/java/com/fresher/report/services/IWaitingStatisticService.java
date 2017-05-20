package com.fresher.report.services;

import java.util.Date;

import com.fresher.report.entities.WaitingStatistic;

public interface IWaitingStatisticService {
	public void save(WaitingStatistic waitingStatistic);
	public long countWaitingByGroupNameAndTypeAndDate(String groupName, String type, Date fromDate, Date toDate);
}
