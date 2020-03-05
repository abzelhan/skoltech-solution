#!/bin/bash

#Первым делом соберем докер образ приложения

docker build -f src/main/docker/Dockerfile . -t skoltech-app

docker-compose -f src/main/docker/docker-compose.yml up -d