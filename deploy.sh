#!/usr/bin/env bash
echo 'Building application'
cd '/home/ubuntu/bets-api'
sudo mvn clean install
echo 'Running application'
sudo chmod 400 bets-api.jar
sudo java -jar target/bets-api.jar