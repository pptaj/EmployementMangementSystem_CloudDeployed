#!/bin/bash

# Shell script includes the following components:

# DELETE dbSecurity GROUP IF IT EXISTS
# FETCH WEB SECURITY GROUP ID
# CREATING DATABASE SECURITY GROUP AND AUTHORIZING PORTS 


EC2_BIN="aws --region us-east-1 ec2"
securityName="web"
dbSecurityName="db"
groupID=""


#----------------------------------------------
# DELETE DATABASE SECURITY GROUP IF IT EXISTS #
#----------------------------------------------

for i in {0..3..1}
do
	groupName=$($EC2_BIN describe-security-groups| jq -r '.SecurityGroups['$i'].GroupName')

	if [ "$groupName" == "$securityName" ];then 
		echo "Fetching web Security Group ID"
		groupID=$($EC2_BIN describe-security-groups| jq -r '.SecurityGroups['$i'].GroupId')
	fi

	if [ "$groupName" == "$dbSecurityName" ];then 
		echo "Deleting Database Security Group Since it already exists"
		$EC2_BIN delete-security-group \
		--group-name db
	fi
done


#------------------------------------------------
# CREATING SECURITY GROUP AND AUTHORIZING PORTS #
#------------------------------------------------

echo "Creating Database Security Group"
$EC2_BIN create-security-group \
--group-name db \
--description "Cloud Team 8 Database Security Group" > dbSecurity.txt  2>&1
for port in 3306
do
	$EC2_BIN authorize-security-group-ingress --group-name db --protocol tcp --port $port --source-group $groupID
done
