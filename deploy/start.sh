#!/usr/bin/env bash
echo 'START - Setting exe rights to bets-api.jar'
sudo chmod 400 target/bets-api.jar
sudo chmod u+x deploy/start.sh
echo 'START - Running application'
sudo nohup java -jar target/bets-api.jar > /dev/null 2>&1 &