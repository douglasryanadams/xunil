#!/bin/bash

# Run on server

sudo systemctl stop tomcat8
sudo rm -rf /var/lib/tomcat8/webapps/*
sudo mv ROOT.war /var/lib/tomcat8/webapps/
sudo systemctl start tomcat8

sudo salt-call state.highstate
