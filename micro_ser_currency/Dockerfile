#FROM adoptopenjdk/openjdk11:jdk-11.0.1.13-alpine
#ADD target/*.jar app.jar
#
#ENV EUREKA_HOST=localhost
#
#ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"
#
#EXPOSE 8885 8787
#
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Duser.country=DE -Duser.language=ru \
#       -jar /app.jar \
#       --cloud.eureka-host=$EUREKA_HOST \
#       "]

#       ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Duser.country=DE -Duser.language=ru -Dspring.profiles.active=cloud \
#              -jar /app.jar \
#              --cloud.eureka-host=$EUREKA_HOST \
#              "]
FROM docker.io/eclipse-temurin:17.0.7_7-jre
#
ADD target/*.jar currency-0.0.1-SNAPSHOT.jar
#
#EXPOSE 8885
#
#ENV EUREKA_HOST=localhost:8761
#ENTRYPOINT ["java","-XX:+UseG1GC", "-jar", "currency-0.0.1-SNAPSHOT.jar"]

# Устанавливаем переменные окружения
ENV EUREKA_HOST=localhost
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"

# Открываем порты для приложения и отладки
EXPOSE 8885 8787

# Указываем точку входа
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Duser.country=RU -Duser.language=ru -Dspring.profiles.active=cloud -jar /currency-0.0.1-SNAPSHOT.jar --cloud.eureka.host=$EUREKA_HOST"]