#!/usr/bin/env bash
cd /home/ubuntu
echo "AFTER-INSTALL - copy properties to base"
sudo cp bets-api/src/main/resources/*  bets-api
sudo mkdir bets-api/logs
sudo chmod a+rwx -R  bets-api
