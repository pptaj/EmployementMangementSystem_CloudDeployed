#!/bin/bash

# Shell script includes the following components:

# DEATACH THE ROLE TO EC2 SERVER POLICY
# DEATACH THE ROLE TO TRAVISCI TO UPLOAD TO AWS S3 POLICY
# DEATACH THE ROLE TO TRAVISCI TO CALL CODEDEPLOY POLICY
# DELETE ROLE


#------------
# VARIABLES #
#------------

AWS_IAM="aws iam"
EC2RoleName="CodeDeployEC2ServiceRole"
CodeDeployRoleName="CodeDeployServiceRole"


echo "Detaching Policies"
$AWS_IAM detach-role-policy --role-name $EC2RoleName --policy-arn arn:aws:iam::786850513911:policy/CodeDeploy-EC2-S3
$AWS_IAM detach-role-policy --role-name $CodeDeployRoleName --policy-arn arn:aws:iam::aws:policy/service-role/AWSCodeDeployRole
#$AWS_IAM detach-role-policy --role-name $CodeDeployRoleName --policy-arn arn:aws:iam::786850513911:policy/Travis-Upload-To-S3
#$AWS_IAM detach-role-policy --role-name $RoleName --policy-arn arn:aws:iam::786850513911:policy/CodeDeploy-EC2-S3
echo "Policies successfully dettached"


$AWS_IAM delete-role --role-name $EC2RoleName
$AWS_IAM delete-role --role-name $CodeDeployRoleName
echo "Role successfully deleted"
