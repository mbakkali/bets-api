#!/usr/bin/env bash
echo 'START - Setting exe rights to bets-api.jar'
sudo chmod 777 /home/ubuntu/bets-api/target/bets-api.jar
sudo chmod 777 /home/ubuntu/bets-api/deploy/start.sh
sudo chmod 777 /home/ubuntu/bets-api/deploy/stop.sh
sudo chmod 777 /home/ubuntu/bets-api/deploy/before-install.sh
sudo chmod 777 /home/ubuntu/bets-api/deploy/validate.sh
echo 'START - Running application'
sudo nohup java -jar /home/ubuntu/bets-api/target/bets-api.jar > /dev/null 2>&1 &