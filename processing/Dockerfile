
FROM docker.io/eclipse-temurin:17.0.7_7-jre

ADD target/*.jar processing-0.0.1-SNAPSHOT.jar

ENV EUREKA_HOST=localhost:8761
#ENV CURRENCY_URL=http://localhost:8080

EXPOSE 8090

ENTRYPOINT ["sh", "-c", "java -jar /processing-0.0.1-SNAPSHOT.jar --cloud.eureka.host=$EUREKA_HOST"]