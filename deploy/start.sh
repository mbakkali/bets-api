#!/usr/bin/env bash
echo 'Building application'
cd '/home/ubuntu/bets-api'
sudo mvn clean install -DskipTests
echo 'Running application'
sudo chmod 400 bets-api.jar
sudo nohup java -jar target/bets-api.jar &