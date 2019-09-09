#!/usr/bin/env bash
cd /home/ubuntu/bets-api
echo "BEFORE-INSTALL - Deleting bets-api repository ..."
sudo rm -rf bets-api
echo "BEFORE-INSTALL - Deleted bets-api repository with success"
echo "BEFORE-INSTALL - Deleting codedeploy *-cleanup file ..."
sudo rm -rf /opt/codedeploy-agent/deployment-root/deployment-instructions/*-cleanup
echo "BEFORE-INSTALL - Deleted codedeploy *-cleanup file with success"