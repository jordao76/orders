#!/bin/sh

mvn compile
mvn exec:java -Dexec.mainClass="codecrafter.orders.Program"
