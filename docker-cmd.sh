#!/bin/bash

APPLICATION_NAME=retail-industry.jar
echo "CONTAINER_ENV : ${CONTAINER_ENV}"
if [ "x$XMSLimit" == "x" ]; then
    # default allocation memory minimum
    XMSLimit="128m"
fi

if [ "x$XMXLimit" == "x" ]; then
    # default allocation memory maximum
    XMXLimit="256m"
fi

eval "java -jar -Xms$XMSLimit -Xmx$XMXLimit -Dspring.profiles.active=${CONTAINER_ENV,,} $APPLICATION_NAME"
