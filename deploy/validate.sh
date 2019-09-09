#!/usr/bin/env bash
while [ true ]
do
    if [ "$(curl -s -k  https://localhost:8081/actuator/health)" = '{"status":"UP","details":{"db":{"status":"UP","details":{"database":"PostgreSQL","hello":1}}}}' ]
    then
        exit 0
    else
        echo "check server is running?"
        sleep 5s
    fi
done