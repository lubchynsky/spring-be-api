#!/usr/bin/env bash

./gradlew clean
./gradlew build -x test
docker build -t my-be-api:latest .
sleep 3
docker image prune -f
docker-compose up