FROM adoptopenjdk:11-jre-hotspot
COPY target/testTask-0.0.1-SNAPSHOT.jar testTask-1.0.0.jar
ENTRYPOINT ["java","-jar","/testTask-1.0.0.jar"]