#!/bin/bash
java -Xms${JVM_XMS} -Xmx${JVM_XMX} -XX:+USeG1GC -noverify -jar app.jar --server.port=${PORT}