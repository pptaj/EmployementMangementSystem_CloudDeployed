#!/bin/bash

sudo service tomcat8 stop
cd /var/lib/tomcat8/webapps
sudo rm -rf trial-1.0.0-BUILD-SNAPSHOT
sudo service tomcat8 start