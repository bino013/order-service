#!/bin/bash

docker container stop order-service-container

docker container stop docker-mysql

docker container rm order-service-container

docker container rm docker-mysql

docker network rm order-service-mysql