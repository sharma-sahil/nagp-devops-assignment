FROM openjdk:8-jdk-alpine
WORKDIR $JENKINS_HOME/workspace/sharma-sahil-test-pipeline/
COPY target/devops-demo-0.0.1-SNAPSHOT.jar /usr/local/app.jar
ENTRYPOINT ["java","-jar","usr/local/app.jar"]