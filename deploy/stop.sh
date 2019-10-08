#!/usr/bin/env bash
# Script to stop the application
PID_PATH="/home/ubuntu/bets-api/application.pid"
if [ ! -f "$PID_PATH" ]; then
   echo "STOP - Process Id FilePath($PID_PATH) Not found with application.pid : using pkill -9 <service-name>"
   sudo pkill -9 -f bets-api 2> /dev/null
else
pid=`cat $PID_PATH`
       echo "STOP - Stopping app wih pid $pid"
       sudo kill $pid;
       echo "STOP - Stopped app with PID:$pid..."
       sleep 10
fi
