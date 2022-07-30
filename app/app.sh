#!/bin/bash
java -Xms${JVM_XMS} -Xmx${JVM_XMX} -noverify -jar app.jar --server.port=${PORT}