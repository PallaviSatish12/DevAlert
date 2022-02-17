package com.decathlon.devalert.controller;

import com.decathlon.devalert.Exception.AlertFailureException;
import com.decathlon.devalert.Exception.DeveloperNotPresentException;
import com.decathlon.devalert.entity.TeamDetails;
import com.decathlon.devalert.service.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alertTeam")
public class AlertController {

    private final Logger logger = LoggerFactory.getLogger(AlertController.class);
    @Autowired
    private AlertService alertService;

    @RequestMapping(value="/{teamName}", method=RequestMethod.POST)
    public String alertTeam(@PathVariable String teamName){
        logger.info("Inside alertTeam : " + teamName);
        try{
            alertService.sendAlertToTeam(teamName);
            return "Successfully alerted : " + teamName;
        }catch (AlertFailureException exp){
            logger.error(exp.getMessage());
            return exp.getMessage();
        }
    }

}
