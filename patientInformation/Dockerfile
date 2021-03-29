FROM openjdk:8-jdk-alpine
#EXPOSE 8081
#ARG JAR_FILE=target/patientInformation-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]
COPY target/patientInformation-0.0.1-SNAPSHOT.jar patientInformation-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/patientInformation-0.0.1-SNAPSHOT.jar"]