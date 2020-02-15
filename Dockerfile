FROM openjdk:13-alpine
COPY ./target/sample-order-service-0.0.1-SNAPSHOT.jar sample-order-service.jar
ENTRYPOINT ["java","-jar","sample-order-service.jar"]