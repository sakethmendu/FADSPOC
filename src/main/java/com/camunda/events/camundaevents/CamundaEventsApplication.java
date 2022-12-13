package com.camunda.events.camundaevents;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.model.bpmn.instance.CorrelationKey;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.camunda.zeebe.spring.client.EnableZeebeClient;

@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = {"classpath:fads.bpmn", "classpath:fads2.bpmn", "classpath:fads-decisiontable.dmn"})
public class CamundaEventsApplication {


    public static void main(String[] args) {
        SpringApplication.run(CamundaEventsApplication.class, args);
    }

}
