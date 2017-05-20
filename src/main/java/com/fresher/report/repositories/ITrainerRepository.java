package com.fresher.report.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fresher.report.entities.Trainer;

@Repository("trainerRepository")
public interface ITrainerRepository extends MongoRepository<Trainer, String> {

}
