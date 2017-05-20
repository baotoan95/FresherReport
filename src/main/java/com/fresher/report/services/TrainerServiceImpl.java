package com.fresher.report.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresher.report.entities.Trainer;
import com.fresher.report.repositories.ITrainerRepository;

@Service("trainerService")
public class TrainerServiceImpl implements ITrainerService {
	@Autowired
	private ITrainerRepository trainerRepository;
	
	public void save(Trainer trainer) {
		trainerRepository.save(trainer);
	}

	public List<Trainer> findAll() {
		return trainerRepository.findAll();
	}

	@Override
	public Trainer findOne(String id) {
		return trainerRepository.findOne(id);
	}
}
