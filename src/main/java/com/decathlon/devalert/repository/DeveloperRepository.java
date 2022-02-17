package com.decathlon.devalert.repository;

import com.decathlon.devalert.entity.Developer;
import com.decathlon.devalert.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Developer findByDeveloperId(Long developerId);

    List<Developer> findByTeam(Team team);
}


