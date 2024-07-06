FROM adoptopenjdk/openjdk11:jdk-11.0.1.13-alpine
ADD target/*.jar app.jar

ENV EUREKA_HOST=localhost

ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"

EXPOSE 8885 8787

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Duser.country=DE -Duser.language=ru -Dspring.profiles.active=cloud \
       -jar /app.jar \
       --cloud.eureka-host=$EUREKA_HOST \
       "]