#!/bin/bash

# Shell script includes the following components:

# CREATES A POLICY FOR EC2 SERVER
# CREATES A POLICY FOR TRAVISCI TO UPLOAD TO AWS S3
# CREATES A POLICY FOR TRAVISCI TO CALL CODEDEPLOY


#------------
# VARIABLES #
#------------

AWS_IAM="aws iam"
EC2ServerPolicy="CodeDeploy-EC2-S3"
TravisCIS3Upload="Travis-Upload-To-S3"
TravisCICodeDeploy="Travis-CodeDeploy"


$AWS_IAM create-policy --policy-name $EC2ServerPolicy --policy-document file:///tmp/Policies/CodeDeploy-EC2-S3.json
echo "Finished creating CodeDeploy-EC2-S3 Policy"


$AWS_IAM create-policy --policy-name $TravisCIS3Upload --policy-document file:///tmp/Policies/Travis-Upload-To-S3.json
echo "Finished creating Travis upload to S3 Policy"


$AWS_IAM create-policy --policy-name $TravisCICodeDeploy --policy-document file:///tmp/Policies/Travis-Code-Deploy.json
echo "Finished creating Travis CodeDeploy Policy"
