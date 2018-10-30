#!/usr/bin/env bash

current=`pwd`
docker run --rm -d --name hotel-availability --hostname hotel-availability -p 8890:8080 -v ${current}/targetDeploy:/var/lib/jetty/webapps jetty