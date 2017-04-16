#!/bin/bash

# Shell script includes the following components:

# Launches MySQL RDS Instance using AWS CLI

set -e
#------------
# VARIABLES #
#------------
RDS_BIN="aws --region us-east-1 rds"
DB_ENGINE="mysql"
DB_USER="csye6225"
DB_PASSWORD="csye6225"
DB_IDENTIFIER="csye6225"
groupID=""
dbSecurityName="db"
EC2_BIN="aws --region us-east-1 ec2"

#Cleaning Directory. To avoid conflicts if we run script more than once
rm -f /tmp/Auth_key.pem
rm -f /tmp/Output.txt

#------------------------
# CREATING RDS INSTANCE #
#------------------------

for i in {0..3..1}
do
	groupName=$($EC2_BIN describe-security-groups| jq -r '.SecurityGroups['$i'].GroupName')

	if [ "$groupName" == "$dbSecurityName" ];then 
		echo "Fetching web Security Group ID"
		groupID=$($EC2_BIN describe-security-groups| jq -r '.SecurityGroups[1].GroupId')
	fi
done


dbInstanceName=$(aws rds describe-db-instances |jq -r '.DBInstances[0].MasterUsername')
echo "Checking if DB Instance Exists"
if [ "$dbInstanceName" == "csye6225" ];then
	echo "DB Already exists"
	exit
else
	$RDS_BIN create-db-instance \
--db-instance-identifier ${DB_IDENTIFIER} \
--no-multi-az \
--no-publicly-accessible \
--vpc-security-group-ids $groupID \
--allocated-storage 8 \
--db-instance-class db.t2.micro \
--engine ${DB_ENGINE} \
--master-username  ${DB_USER} --master-user-password ${DB_PASSWORD} 

	aws rds describe-db-instances --db-instance-identifier ${DB_IDENTIFIER} > /tmp/Output.txt
	DB_HOSTNAME=$(cat /tmp/Output.txt|grep Address|awk -F '"' '{print $4}')
fi
