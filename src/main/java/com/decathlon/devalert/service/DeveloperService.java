package com.decathlon.devalert.service;

import com.decathlon.devalert.entity.Developer;
import com.decathlon.devalert.entity.Team;
import com.decathlon.devalert.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    public Developer findDeveloperById(Long id) {
        return developerRepository.findByDeveloperId(id);
    }

    public Developer saveDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    public List<Developer> findByTeam(Team team){
        return developerRepository.findByTeam(team);
    }
}
