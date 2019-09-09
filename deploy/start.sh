#!/usr/bin/env bash
echo 'START - Building application with maven'
cd '/home/ubuntu/bets-api'
sudo mvn clean install -DskipTests
echo 'START - Setting exe rights to bets-api.jar'
sudo chmod 400 bets-api.jar
echo 'START - Running application'
sudo nohup java -jar target/bets-api.jar > /dev/null 2>&1 &