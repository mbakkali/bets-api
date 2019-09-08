#!/usr/bin/env bash
# Script to stop the application
PID_PATH="application.pid"

if [ ! -f "$PID_PATH" ]; then
   echo "Process Id FilePath($PID_PATH) Not found"
else
pid=`cat $PID_PATH`
    echo "Stopping app wih pid $pid"
    if ! kill $pid > /dev/null 2>&1; then
        echo "App was not running.";
    else
       kill $pid;
       echo "Stopped app with PID:$pid..."
    fi
fi