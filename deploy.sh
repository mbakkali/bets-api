#!/usr/bin/env bash
echo 'Building application'
cd '/home/ubuntu/bets-api'
mvn clean install
echo 'Running application'
sudo chmod 400 bets-api.jar
java -jar bets-api.jar