package org.acme;

import jakarta.annotation.PostConstruct;
//import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

@Singleton
public class Routes extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(Routes.class);

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
        String camelTimerFiredTime = LocalTime.now().toString();
        exchange.getIn().setBody(("Current time is " + camelTimerFiredTime));
    }

    @PostConstruct
    private void postInit() {
        logger.info("Started routes!");
    }
}
