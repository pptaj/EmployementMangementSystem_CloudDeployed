#!/bin/bash

# Shell script includes the following components:

# DELETE A POLICY FOR EC2 SERVER
# DELETE A POLICY FOR TRAVISCI TO UPLOAD TO AWS S3
# DELETE A POLICY FOR TRAVISCI TO CALL CODEDEPLOY


#------------
# VARIABLES #
#------------

AWS_IAM="aws iam"
EC2ServerPolicyARN="arn:aws:iam::786850513911:policy/CodeDeploy-EC2-S3"
TravisCIS3UploadARN="arn:aws:iam::786850513911:policy/Travis-Upload-To-S3"
TravisCICodeDeployARN="arn:aws:iam::786850513911:policy/Travis-CodeDeploy"


$AWS_IAM delete-policy --policy-arn $EC2ServerPolicyARN
echo "EC2 Server Policy Successfully Deleted"


$AWS_IAM delete-policy --policy-arn $TravisCIS3UploadARN
echo "Travis upload to S3 Policy Successfully Deleted"


$AWS_IAM delete-policy --policy-arn $TravisCICodeDeployARN
echo "Travis CodeDeploy Policy Successfully Deleted"
