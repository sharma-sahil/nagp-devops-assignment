# Pull base image
From tomcat:8-jre8

# Maintainer
MAINTAINER "sahil <sahil.sharma@nagarro.com">

# Copy to images tomcat path
ADD SpringMvcMaven.war /usr/local/tomcat/webapps/