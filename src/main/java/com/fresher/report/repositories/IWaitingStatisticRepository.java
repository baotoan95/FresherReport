package com.fresher.report.repositories;

import java.util.Date;

import com.fresher.report.entities.WaitingStatistic;

public interface IWaitingStatisticRepository {
	public void save(WaitingStatistic waitingStatistic);
	public long countWaitingByGroupNameAndTypeAndDate(String groupName, String type, Date fromDate, Date toDate);
}
