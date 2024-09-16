package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class KafkaControllerTest {
    @Test
    void testKafkaMessagesEndpoint() {
        given()
          .when().get("/kafka/messages")
          .then()
             .statusCode(200)
             .body(containsString("Current time is"));
    }

}