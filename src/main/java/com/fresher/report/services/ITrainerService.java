package com.fresher.report.services;

import java.util.List;

import com.fresher.report.entities.Trainer;

public interface ITrainerService {

	public void save(Trainer headTeacher);

	public List<Trainer> findAll();
	
	public Trainer findOne(String id);
	
}
