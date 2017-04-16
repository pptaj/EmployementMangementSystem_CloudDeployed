#!/bin/bash

# Shell script includes the following components:
# Create Travis User with Programmatic access only .
# Attach following policies to the newly created user
# 1.Travis Upload to S3
# 2.Travis Code-Deploy
TravisCIS3UploadARN="arn:aws:iam::786850513911:policy/Travis-Upload-To-S3"
TravisCICodeDeployARN="arn:aws:iam::786850513911:policy/Travis-CodeDeploy"
AWS_IAM="aws iam"

echo "Create User Travis"
$AWS_IAM create-user --user-name travis
echo "Programmatic acess key for Travis User"
$AWS_IAM create-access-key --user-name travis
echo "Attach Policy Upload to S3"
$AWS_IAM attach-user-policy --policy-arn $TravisCIS3UploadARN --user-name travis
echo "Policy attached"
echo "Attach Policy Code Deploy "
$AWS_IAM attach-user-policy --policy-arn $TravisCICodeDeployARN --user-name travis
echo "Policy attached"



