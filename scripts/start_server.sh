#!/bin/bash

# Spring Boot 애플리케이션을 백그라운드로 실행
nohup java -Dserver.port=80 -jar /var/www/jar/app.jar > /var/www/logs/app.log 2>&1 &

# 백그라운드 실행 확인 메시지
echo "Spring Boot 애플리케이션이 백그라운드에서 실행 중입니다."