package com.decathlon.devalert.service;

import com.decathlon.devalert.Exception.DBException;
import com.decathlon.devalert.Exception.DeveloperNotPresentException;
import com.decathlon.devalert.Exception.TeamNotFoundException;
import com.decathlon.devalert.entity.Developer;
import com.decathlon.devalert.entity.Team;
import com.decathlon.devalert.entity.TeamDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamDetailsService {

    private final Logger logger = LoggerFactory.getLogger(TeamDetailsService.class);
    @Autowired
    private TeamService teamService;
    @Autowired
    private DeveloperService developerService;

    public TeamDetails saveTeamDetails(TeamDetails teamDetails) throws DeveloperNotPresentException {

        logger.info("Inside saveTeamDetails");
        if(teamDetails.getDevelopers() == null || teamDetails.getDevelopers().isEmpty()){
            String erroeMsg = "No developer found for this team";
            logger.error(erroeMsg);
            throw new DeveloperNotPresentException(erroeMsg);
        }

        Team team1 = new Team();
        team1.setTeamName(teamDetails.getTeamName());
        team1.setAddress(teamDetails.getAddress());
        Team team = null;
        try {
            team = teamService.saveTeam(team1);
        }catch(DataAccessException exp){
            String erroeMsg = "Unable to save team details";
            logger.error(erroeMsg);
            throw new DBException(erroeMsg);
        }
        teamDetails.setTeamId(team.getTeamId());
        teamDetails.setTeamName(team.getTeamName());
        teamDetails.setAddress(team.getAddress());
        List<Developer> devs = new ArrayList<Developer>();
        for(Developer developer : teamDetails.getDevelopers()){
            developer.setTeam(team);
            developer = developerService.saveDeveloper(developer);
            devs.add(developer);
        }
        teamDetails.setDevelopers(devs);
        return teamDetails;
    }

    public TeamDetails findTeamByName(String teamName) throws TeamNotFoundException {

        TeamDetails teamDetails = new TeamDetails();
        Team team = teamService.findByTeamName(teamName);
        teamDetails.setTeamId(team.getTeamId());
        teamDetails.setTeamName(team.getTeamName());
        teamDetails.setAddress(team.getAddress());
        teamDetails.setDevelopers(developerService.findByTeam(team));
        return teamDetails;
    }
}
