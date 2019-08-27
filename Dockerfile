# Pull base image
FROM tomcat:alpine

# Maintainer
MAINTAINER "sahil <sahil.sharma@nagarro.com">

# Copy to images tomcat path
ADD /root/.m2/repository/com/nagarro/nagp/SpringMvcMaven/0.1/SpringMvcMaven-0.1.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD /usr/local/tomcat/bin/catalina.sh run