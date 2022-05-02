package com.example.springgateway.controller;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("observablity")
public class ObservabilityController {

    Logger logger = LoggerFactory.getLogger(ObservabilityController.class);

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/observability")
    public ResponseEntity serviceDiscoveryInstanceObservability(){
        //InstanceInfo instance = eurekaClient.getNextServerFromEureka("GATEWAY", false);
        Applications applications = eurekaClient.getApplications();

        applications.getRegisteredApplications().forEach(application -> {
            logger.info(application.getName());
            application.getInstances().forEach(instanceInfo -> {
                logger.info(instanceInfo.getId());
                logger.info(instanceInfo.getAppName());
                logger.info(instanceInfo.getVIPAddress());
                logger.info(instanceInfo.getHomePageUrl());
            });
            logger.info("==========================");
        });

        return ResponseEntity.ok(applications);
    }

}
