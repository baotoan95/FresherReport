package com.fresher.report.repositories;

import java.util.List;

import com.fresher.report.entities.Group;

public interface IGroupRepository {
    public Group findByName(String name);
    
    public List<Group> findAll();
    
    public void save(Group group);
    
    public Group findOne(String id);
    
}
