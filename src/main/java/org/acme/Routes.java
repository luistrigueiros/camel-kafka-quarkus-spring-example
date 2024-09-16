package org.acme;

import io.quarkus.logging.Log;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.time.LocalTime;

@Singleton
public class Routes extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        // produces messages to kafka
        from("timer:foo?period={{timer.period}}&delay={{timer.delay}}")
                .routeId("FromTimer2Kafka")
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
        Log.debug("Processing timer event");
        String camelTimerFiredTime = LocalTime.now().toString();
        String body = "Current time is " + camelTimerFiredTime;
        exchange.getIn().setBody(body);
    }

    @PostConstruct
    private void postInit() {
        Log.info("Started routes!");
    }
}
