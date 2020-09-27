FROM adoptopenjdk:14-jre-hotspot

EXPOSE 8080

ADD ./target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
