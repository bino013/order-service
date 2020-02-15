#!/bin/bash

docker network create order-service-mysql

docker container run --name docker-mysql --network order-service-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=order_service -d mysql

./mvnw clean package

docker image build -t order-service .

docker container run --network order-service-mysql --name order-service-container -e DISTANCE_SERVICE_KEY=AIzaSyB1kvAJG6g3PHdlgjk7fl8tLl1YZL1p7Ww -p 8080:8080 -d order-service

sleep 5