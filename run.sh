#!/bin/bash

SERVICE="ch-create-client"

if [ "$1" == "" ]; then
    docker run --rm -p 8080:8080 -dit --name $SERVICE soprasteria/$SERVICE
else
    docker stop $SERVICE
fi