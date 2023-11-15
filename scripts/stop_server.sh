#!/bin/bash

# Spring Boot 애플리케이션의 프로세스 ID를 찾기
PID=$(ps -ef | grep "java -Dserver.port=80 -jar app.jar" | grep -v grep | awk '{print $2}')

# 프로세스가 실행 중인지 확인
if [ -z "$PID" ]; then
  echo "Spring Boot 애플리케이션은 실행 중이지 않습니다."
else
  # 프로세스를 종료
  echo "Spring Boot 애플리케이션을 종료합니다. (PID: $PID)"
  kill -9 "$PID"
fi
