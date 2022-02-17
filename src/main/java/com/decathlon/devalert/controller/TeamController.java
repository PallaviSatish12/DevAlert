package com.decathlon.devalert.controller;

import com.decathlon.devalert.Exception.DBException;
import com.decathlon.devalert.Exception.DeveloperNotPresentException;
import com.decathlon.devalert.Exception.TeamNotFoundException;
import com.decathlon.devalert.entity.TeamDetails;
import com.decathlon.devalert.service.TeamDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamDetailsService teamDetailsService;

    @PostMapping("/")
    @ExceptionHandler(value = DeveloperNotPresentException.class)
    public String saveTeam(@RequestBody TeamDetails teamDetails){
        logger.info("Inside saveTeam");
        try {
            teamDetailsService.saveTeamDetails(teamDetails);
            return "Teams details saved successfully";
        } catch(DeveloperNotPresentException | DBException exp){
            logger.error(exp.getMessage());
            return exp.getMessage();
        }
    }

    @GetMapping("/{name}")
    @ExceptionHandler(value = TeamNotFoundException.class)
    public Object findTeamById(@PathVariable("name") String name){
        logger.info("Inside findTeamById");
        try {
            return teamDetailsService.findTeamByName(name);
        }catch (TeamNotFoundException exp){
            logger.error(exp.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", exp.getMessage());
            return new ResponseEntity<Map<String, String>>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

}
