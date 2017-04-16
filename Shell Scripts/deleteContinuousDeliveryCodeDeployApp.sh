#!/bin/bash

# Shell script includes the following components:

# CREATES AN AWS CODE DEPLOY APPLICATION
# CREATES AN AWS CODE DEPLOY DEPLOYMENT



#------------
# VARIABLES #
#------------

AWS_DEPLOY="aws deploy"

echo "AWS CodeDeploy Application to be deleted"
$AWS_DEPLOY delete-application --application-name EMS

echo "AWS CodeDeploy Deployment Group deleted"

