#!/usr/bin/env bash
echo 'START - Running application'
sudo nohup java -jar target/bets-api.jar > /dev/null 2>&1 &