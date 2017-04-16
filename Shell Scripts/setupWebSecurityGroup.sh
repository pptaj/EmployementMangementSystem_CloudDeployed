#!/bin/bash

# Shell script includes the following components:

# DELETE webSecurity GROUP IF IT EXISTS
# CREATING SECURITY GROUP AND AUTHORIZING PORTS 


EC2_BIN="aws --region us-east-1 ec2"
securityName="web"


#------------------------------------
# DELETE SECURITY GROUP IF IT EXISTS#
#------------------------------------

for i in {0..3..1}
do
	groupName=$($EC2_BIN describe-security-groups| jq -r '.SecurityGroups['$i'].GroupName')
	if [ "$groupName" == "$securityName" ];then 
		echo "Deleting Security Group Since it already exists"
		$EC2_BIN delete-security-group \
		--group-name web
	fi
done


#-----------------------------------------------
# CREATING SECURITY GROUP AND AUTHORIZING PORTS#
#-----------------------------------------------

echo "Creating Security Group"
$EC2_BIN create-security-group \
--group-name web \
--description "Cloud Team 8 Security Group" > /webSecurity.txt  2>&1
for port in 22 80 443
do
	$EC2_BIN authorize-security-group-ingress --group-name web --protocol tcp --port $port --cidr 0.0.0.0/0
done
