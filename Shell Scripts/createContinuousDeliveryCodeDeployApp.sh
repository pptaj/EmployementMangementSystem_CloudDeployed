#!/bin/bash

# Shell script includes the following components:

# CREATES AN AWS CODE DEPLOY APPLICATION
# CREATES AN AWS CODE DEPLOY DEPLOYMENT



#------------
# VARIABLES #
#------------

AWS_DEPLOY="aws deploy"


$AWS_DEPLOY create-application --application-name EMS
echo "AWS CodeDeploy Application created"


$AWS_DEPLOY create-deployment-group \
--application-name EMS \
--deployment-config-name CodeDeployDefault.OneAtATime \
--deployment-group-name EMS \
--ec2-tag-filters Key=Auth,Value=Key,Type=KEY_AND_VALUE \
--service-role-arn arn:aws:iam::786850513911:role/CodeDeployServiceRole
echo "AWS CodeDeploy Deployment Group completed"
