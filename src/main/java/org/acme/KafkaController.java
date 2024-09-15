package org.acme;

import org.apache.camel.ConsumerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private ConsumerTemplate consumerTemplate;

    @GetMapping(path = "/messages")
    public String getMessages() {
        return consumerTemplate.receiveBody("seda:kafka-messages", 10000, String.class);
    }
}
