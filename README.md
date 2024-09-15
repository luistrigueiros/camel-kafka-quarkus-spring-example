# camel-kafka-quarkus-spring-example

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/camel-kafka-quarkus-spring-example-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/gradle-tooling>.

## Related Guides

- Camel Kafka ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/kafka.html)): Sent and receive messages to/from an Apache Kafka broker
- Quarkus Extension for Spring Data REST ([guide](https://quarkus.io/guides/spring-data-rest)): Generate Jakarta REST resources for a Spring Data application
- Quarkus Extension for Spring Scheduled ([guide](https://quarkus.io/guides/spring-scheduled)): Schedule tasks with Spring Scheduling
- Quarkus Extension for Spring DI API ([guide](https://quarkus.io/guides/spring-di)): Define your dependency injection with Spring DI
- Quarkus Extension for Spring Web API ([guide](https://quarkus.io/guides/spring-web)): Use Spring Web annotations to create your REST services
- Quarkus Extension for Spring Cloud Config Client ([guide](https://quarkus.io/guides/spring-cloud-config-client)): Use properties from Spring Cloud Config as bootstrap properties sources
- Quarkus Extension for Spring Boot properties ([guide](https://quarkus.io/guides/spring-boot-properties)): Use Spring Boot properties annotations to configure your application

## Provided Code

### Spring Web

Spring, the Quarkus way! Start your REST Web Services with a Spring Controller.

[Related guide section...](https://quarkus.io/guides/spring-web#greetingcontroller)
