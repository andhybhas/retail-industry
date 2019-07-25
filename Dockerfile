FROM docker.doku.com/doku-jdk8-centos7
 
# don't forget to set final name project on pom.xml
ARG project_name=retail-industry
ARG application_name=$project_name.jar

USER root
#-------------------------------------------------------------
# Prepare file and application
#-------------------------------------------------------------
RUN mkdir -p /var/log/$project_name/

ADD docker-cmd.sh /apps/
ADD target/$application_name /apps/
 
#-------------------------------------------------------------
# Set Permission in OS
#-------------------------------------------------------------
RUN cd /apps && chmod +rx $application_name && chmod +rx docker-cmd.sh
RUN chown -R 3000:3000 /apps
USER 3000
 
#-------------------------------------------------------------
# Set default working dir
#-------------------------------------------------------------
WORKDIR  /apps/
EXPOSE 8080
 
CMD ./docker-cmd.sh