#!/usr/bin/env bash
while [ true ]
do
    if [ "$(curl -s -k  http://localhost:8080/bets-api/actuator/health)" = '{"status":"UP"}' ]
    then
        echo "VALIDATE - Server is up and running ! "
        exit 0
    else
        echo "VALIDATE - Checking https://localhost:8081/actuator/health endpoint"
        sleep 3s
    fi
done