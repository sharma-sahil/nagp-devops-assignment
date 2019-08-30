# Pull base image
FROM tomcat:alpine

# Maintainer
MAINTAINER "sahil"

WORKDIR $JENKINS_HOME/workspace/test-pipe/

# Copy to images tomcat path
COPY /target/SpringMvcMaven.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD /usr/local/tomcat/bin/catalina.sh run