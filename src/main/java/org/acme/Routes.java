package org.acme;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;

import java.time.LocalTime;

@Slf4j
@Singleton
public class Routes extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        // produces messages to kafka
        from("timer:foo?period={{timer.period}}&delay={{timer.delay}}")
                .routeId("FromTimer2Kafka")
                .transform( constant("Hello World "))
                .process(this::processTimerEvent)
                .to("kafka:{{kafka.topic.name}}")
                .log("Message correctly sent to the topic! : \"${body}\" ");

        // kafka consumer
        from("kafka:{{kafka.topic.name}}")
                .routeId("FromKafka2Seda")
                .log("Received : \"${body}\"")
                .to("seda:kafka-messages");
    }

    private void processTimerEvent(Exchange exchange) {
        log.debug("Processing timer event");
        String camelTimerFiredTime = LocalTime.now().toString();
        Message in = exchange.getIn();
        String body = in.getBody(String.class) +  " at " + camelTimerFiredTime;
        in.setBody(body);
    }

    @PostConstruct
    private void postInit() {
        log.info("Started routes!");
    }
}
