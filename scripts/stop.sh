#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
JAR_FILE="$PROJECDT_ROOT/issue-tracker.jar"

DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOE=$(date +%c)

CURRENT_PID=$(pgrep -f $JAR_FILE)

if [ -z $CURRENT_PID ]; then
	echo "$TIME_NOW > 현재 실행주인 애플리케이션이 없습니다" >> $DEPLOY_LOG
else
	ehco "$TIME_NOW > 실행중인 $CURRENT_PID 애플리케이션 종료" >> $DEPLOY_LOG
	kill -15 $CURRENT_PID
fi
