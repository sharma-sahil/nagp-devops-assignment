# Pull base image
FROM tomcat:alpine

# Maintainer
MAINTAINER "sahil <sahil.sharma@nagarro.com">

WORKDIR $JENKINS_HOME/workspace/devops/

# Copy to images tomcat path
COPY target/SpringMvcMaven-0.1.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD /usr/local/tomcat/bin/catalina.sh run