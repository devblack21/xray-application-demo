#!/bin/bash
java -Xms${JVM_XMS} -Xmx${JVM_XMX} -d64 -XX:+USeG1GC -noverify -jar app.jar --server.port=${PORT}