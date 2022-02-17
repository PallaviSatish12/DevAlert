package com.decathlon.devalert.service;

import com.decathlon.devalert.Exception.DeveloperNotPresentException;
import com.decathlon.devalert.Exception.TeamNotFoundException;
import com.decathlon.devalert.entity.Developer;
import com.decathlon.devalert.entity.Team;
import com.decathlon.devalert.entity.TeamDetails;
import com.decathlon.devalert.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class TeamService {
    private final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DeveloperService developerService;

    public Team saveTeam(Team team) throws DataAccessException {
        logger.info("Inside saveTeam");
        return  teamRepository.save(team);

    }
    public Team findTeamById(Long teamId) {
        logger.info("Inside findTeamById");
        return teamRepository.findByTeamId(teamId);
    }

    public Team findByTeamName(String teamName) throws TeamNotFoundException {
        logger.info("Inside findByTeamName : " + teamName);

        Team team =  teamRepository.findByTeamName(teamName);
        if(team==null){
            throw new TeamNotFoundException("No team is found for team name : " + teamName);
        }
        else{
            return team;
        }
    }


}
