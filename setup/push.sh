#!/bin/bash

mvn clean package
scp target/ROOT.war xunil.io:
