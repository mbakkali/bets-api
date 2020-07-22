#!/usr/bin/env bash
echo 'START - Setting exe rights to bets-api.jar'
sudo mv /home/ubuntu/bets-api/target/bets-api.jar /home/ubuntu/bets-api/bets-api.jar
sudo rm -rf /home/ubuntu/bets-api/target/
sudo chmod u+x /home/ubuntu/bets-api/bets-api.jar
echo 'START - Running application in background '
sudo nohup sudo java -jar -Dspring.profiles.active=prod /home/ubuntu/bets-api/bets-api.jar  > /dev/null 2>&1 &
