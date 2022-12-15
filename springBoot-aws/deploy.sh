#!/bin/bash

REPOSITORY=/home/ubuntu/SpringBoot_Aws
PROJECT_NAME=springBoot-aws

cd $REPOSITORY/$PROJECT_NAME/

echo "> git pull"

git pull

echo "project build start"

./gradlew build

echo ">move to /SpringBoot_Aws"

cd $REPOSITORY

echo "> Build 파일 복사"

cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl springBoot-aws | grep jar | awk '{print $1}')

echo "현재 구동중인 어플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
            echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
    else
                echo "> kill -15 $CURRENT_PID"
                    kill -15 $CURRENT_PID
                        sleep 5
fi

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 실행"

nohup java -jar \
            -Dspring.config.location=classpath:/application.properties,/home/ubuntu/SpringBoot_Aws/application-oauth.properties\
            $REPOSITORY/$JAR_NAME 2>&1 &