#!/bin/bash

# Shell script includes the following components:

# ATTACH THE ROLE TO EC2 SERVER POLICY
# ATTACH THE ROLE TO TRAVISCI TO UPLOAD TO AWS S3 POLICY
# ATTACH THE ROLE TO TRAVISCI TO CALL CODEDEPLOY POLICY


#------------
# VARIABLES #
#------------

AWS_IAM="aws iam"
EC2RoleName="CodeDeployEC2ServiceRole"
CodeDeployRoleName="CodeDeployServiceRole"

echo "Creating CodeDeployEC2ServiceRole Role"
$AWS_IAM create-role --role-name $EC2RoleName --assume-role-policy-document file:///tmp/Policies/EC2TrustPolicy.json
echo "Creating a role for EC2 Instance and specifying the trust policy"
#$AWS_IAM attach-role-policy --policy-arn arn:aws:iam::aws:policy/AmazonEC2FullAccess --role-name $EC2RoleName
$AWS_IAM attach-role-policy --policy-arn arn:aws:iam::786850513911:policy/CodeDeploy-EC2-S3 --role-name $EC2RoleName
echo "Successfully Attached EC2 S3 Policy"
$AWS_IAM create-instance-profile --instance-profile-name EMSEC2Instance
$AWS_IAM add-role-to-instance-profile --instance-profile-name EMSEC2Instance --role-name $EC2RoleName
echo "Role and Instance successfully created"

echo "Creating CodeDeployServiceRole Role"
$AWS_IAM create-role --role-name $CodeDeployRoleName --assume-role-policy-document file:///tmp/Policies/CodeDeployTrustPolicy.json
echo "Role CodeDeployServiceRole Successfully created"
$AWS_IAM attach-role-policy --policy-arn arn:aws:iam::aws:policy/service-role/AWSCodeDeployRole --role-name $CodeDeployRoleName
echo "Successfully Attached CodeDeploy Policy"
echo "Role successfully created"
