#!/bin/bash
#Delete Travis User

userName="travis"
 
AWS_IAM="aws iam"
TravisCIS3UploadARN="arn:aws:iam::786850513911:policy/Travis-Upload-To-S3"
TravisCICodeDeployARN="arn:aws:iam::786850513911:policy/Travis-CodeDeploy"

$AWS_IAM detach-user-policy --user-name $userName --policy-arn $TravisCIS3UploadARN
$AWS_IAM detach-user-policy --user-name $userName --policy-arn $TravisCICodeDeployARN
echo "Travis User Policies Detached"

key=$(aws iam list-access-keys --user-name travis | jq -r '.AccessKeyMetadata[0].AccessKeyId')
$AWS_IAM delete-access-key --access-key $key --user-name $userName
$AWS_IAM delete-user --user-name $userName
echo "Travis User Deleted"
