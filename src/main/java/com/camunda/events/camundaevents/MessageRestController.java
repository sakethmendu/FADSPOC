package com.camunda.events.camundaevents;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@RestController
@CrossOrigin("*")
@RequestMapping("/camunda")
public class MessageRestController {

    @Autowired
    ZeebeClient client;

    @PostMapping("startEvent")
    public void startEvent() {
        Map<String, String> map = new HashMap<>();
        map.put("dkey", "1");
        client.newPublishMessageCommand().messageName("receivemsg").correlationKey("receivemsg1").variables(map).send().join();
    }

    @PostMapping("startProcess")
    public String startProcess(@RequestBody Case caseObj) {
    	System.out.println(caseObj);
        final ProcessInstanceEvent wfInstance = client.newCreateInstanceCommand()
                .bpmnProcessId("FADS_1")
                .latestVersion().variables(caseObj)
                .send()
                .join();
        final String workflowInstanceKey = String.valueOf(wfInstance.getProcessInstanceKey());
        return workflowInstanceKey;
    }

    @JobWorker(type = "send_email")
    public void sendEmail(final JobClient jobClient, final ActivatedJob job) {
        Map<String, String> map = new HashMap<>();
        map.put("messageCode", (String) job.getVariablesAsMap().get("messageCode"));
        map.put("caseCreatorMail", (String) job.getVariablesAsMap().get("caseCreatorMail"));
        map.put("caseId", (String) job.getVariablesAsMap().get("caseId"));
        map.put("caseStatus", (String) job.getVariablesAsMap().get("caseStatus"));
        client.newPublishMessageCommand().messageName("receive_email").correlationKey((String) job.getVariablesAsMap().get("caseId")).variables(map).send().join();
        jobClient.newCompleteCommand(job.getKey()).variables("").send().join();
    }

    @JobWorker(type = "prepare_text")
    public void prepareEmail(final JobClient jobClient, final ActivatedJob job) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        ArrayList<String> mailList = new ArrayList<>();
        map.put("messageCode", (String) job.getVariablesAsMap().get("messageCode"));
        ResponseEntity<Map> requestEntity = restTemplate.getForEntity("http://localhost:8088/alert/template/{messageCode}", Map.class, map);
        Map<String, Object> opMap = new HashMap<>();
        String htmlTemplate = (String) requestEntity.getBody().get("htmlTemplate");
        System.out.println("htmlTemplate "+(String) requestEntity.getBody().get("htmlTemplate"));
        htmlTemplate = htmlTemplate.replace("#{case_id}","Case Id: "+(String) job.getVariablesAsMap().get("caseId"));
        htmlTemplate = htmlTemplate.replace("#{case_status}","Case Status: "+(String) job.getVariablesAsMap().get("caseStatus"));
        opMap.put("htmlTemplate", htmlTemplate);
        if (((String) requestEntity.getBody().get("recipient")).equalsIgnoreCase("Case_Creator")) {
            mailList.add((String) job.getVariablesAsMap().get("caseCreatorMail"));
        } else {
            mailList.add("mendusakethreddy@gmail.com");
        }
        opMap.put("recipient", mailList);
        jobClient.newCompleteCommand(job.getKey()).variables(opMap).send().join();
    }

    @JobWorker(type = "send_alert")
    public void sendEmailAlert(final JobClient jobClient, final ActivatedJob job) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> map = new HashMap<>();
        map.put("messageBody", (String) job.getVariablesAsMap().get("htmlTemplate"));
        map.put("recipient", (ArrayList<ArrayList<String>>) job.getVariablesAsMap().get("recipient"));
        ResponseEntity<Boolean> requestEntity = restTemplate.postForEntity("http://localhost:8088/alert/email", map, Boolean.class);
        jobClient.newCompleteCommand(job.getKey()).send().join();
    }
}
