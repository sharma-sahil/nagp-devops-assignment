# Pull base image
FROM tomcat:alpine

# Maintainer
MAINTAINER "sahil <sahil.sharma@nagarro.com">

WORKDIR $JENKINS_HOME/workspace/devops/

# Copy to images tomcat path
ADD SpringMvcMaven.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD /usr/local/tomcat/bin/catalina.sh run