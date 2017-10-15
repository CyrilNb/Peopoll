#!/bin/sh
export USER_HOME_DIR=/root
docker run \
       --rm \
       -e SONAR_TOKEN=${SONAR_TOKEN} \
       -e BINTRAY_API_KEY=${BINTRAY_API_KEY} \
       -e UTLN_EMAIL=${UTLN_EMAIL} \
       -e UTLN_PASSWORD=${UTLN_PASSWORD} \
       -v $HOME/.sonar/cache:${USER_HOME_DIR}/.sonar/cache \
       -v $HOME/.m2:${USER_HOME_DIR}/.m2 \
       -v $(pwd):/usr/src/mysrc \
       -w /usr/src/mysrc \
       maven:3.5.0-jdk-8-alpine \
       mvn \
       -Duser.home=${USER_HOME_DIR} \
       --settings ${USER_HOME_DIR}/.m2/settings.xml \
       $*
