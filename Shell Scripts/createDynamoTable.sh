#!/bin/bash

# Shell script includes the following components:
#Creating a DynamoDB Table using AWS CLI with the below parameters:
#Table Name: csye6225
#Primary Key: id
#Primary Key Datatype: Number (N)
#Read Capacity Units: 1
#Write Capacity Units: 1

#-------------------------------------------------
# CREATING DYNAMODB WITH THE SPECIFIES PARAMETERS#
#-------------------------------------------------

AWS_DYNAMODB="aws dynamodb"

echo "Checking for duplicate DynamoDB with same name"
tableName=$($AWS_DYNAMODB list-tables| jq -r '.TableNames[0]')
if [ "$tableName" == "csye6225" ];then 
	echo "WARNING! db exists,exiting shell"
	exit

else
	echo "Creating DynamoDB Table"
	$AWS_DYNAMODB create-table --table-name csye6225 --attribute-definitions AttributeName=id,AttributeType=N --key-schema AttributeName=id,KeyType=HASH --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1
	echo "New DynamoDB Table named csye6225 created"
fi
