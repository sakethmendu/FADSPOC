package com.camunda.events.camundaevents;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
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
    public void startProcess(@RequestBody Case caseObj) {
        final ProcessInstanceEvent wfInstance = client.newCreateInstanceCommand()
                .bpmnProcessId("FADS_1")
                .latestVersion().variables(caseObj)
                .send()
                .join();
        final long workflowInstanceKey = wfInstance.getProcessInstanceKey();
        System.out.println("Workflow instance created. Key: " + workflowInstanceKey);
    }

    @JobWorker(type = "send_email")
    public void sendEmail(final JobClient jobClient, final ActivatedJob job) {
        System.out.println("Sending Email Event......");
        client.newPublishMessageCommand().messageName("receive_email").correlationKey((String) job.getVariablesAsMap().get("caseId")).send().join();
        jobClient.newCompleteCommand(job.getKey()).send().join();
    }

    @JobWorker(type = "prepare_text")
    public void prepareEmail(final JobClient jobClient, final ActivatedJob job) {
        System.out.println("Preparing Email Text......");
        jobClient.newCompleteCommand(job.getKey()).send().join();
    }

    @JobWorker(type = "send_alert")
    public void sendEmailAlert(final JobClient jobClient, final ActivatedJob job) {
        System.out.println("Sending Email Alert......");
        jobClient.newCompleteCommand(job.getKey()).send().join();
    }
}
