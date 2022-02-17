package com.decathlon.devalert.service;

import com.decathlon.devalert.Exception.AlertFailureException;
import com.decathlon.devalert.Exception.DeveloperNotPresentException;
import com.decathlon.devalert.Exception.TeamNotFoundException;
import com.decathlon.devalert.entity.Developer;
import com.decathlon.devalert.entity.TeamDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

@Service
public class AlertService {

    private final Logger logger = LoggerFactory.getLogger(AlertService.class);

    @Autowired
    private TeamDetailsService teamDetailsService;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${sms.url}")
    private String smsApiUrl;

    public void sendAlertToTeam(String teamName) {
        TeamDetails teamDetails = null;
        try {
            teamDetails = teamDetailsService.findTeamByName(teamName);
        }catch(TeamNotFoundException exp){
            String erroeMsg = "No valid team is found for teamName : " + teamName;
            logger.error(erroeMsg);
            throw new AlertFailureException(erroeMsg);
        }

        List<Developer> developers = teamDetails.getDevelopers();
        Random rand = new Random();
        Developer developer = developers.get(rand.nextInt(developers.size()));

        URI uri = null;
        try {
            uri = new URI(smsApiUrl);
        } catch (URISyntaxException e) {
            String erroeMsg = "Unable to connect to SMS API";
            logger.error(erroeMsg);
            throw new AlertFailureException(erroeMsg);
        }


        String phoneNumber = developer.getPhoneNumber();
        if(phoneNumber==null || phoneNumber.equals("")){
            String erroeMsg = "developer does not have phone number";
            logger.error(erroeMsg);
            throw new AlertFailureException(erroeMsg);
        }

        ResponseEntity<String> result = restTemplate().postForEntity(uri, phoneNumber, String.class);

    }
}
