#!/bin/bash

# Shell script includes the following components:

# CREATING SECURITY GROUP AND AUTHORIZING PORTS 
# CREATING KEY PAIR AND DOWNLOADING IT TO LOCAL M/C
# CREATING UBUNTU EC2 INSTANCE USING AMI-ID
# INSTALLING PACKAGES ON EC2 INSTANCE
# ALLOCATING ELASTIC IP ADDRESS
# ASSIGNING ELASTIC IP ADDRESS TO EC2 INSTANCE 


set -e
#------------
# VARIABLES #
#------------

AMI_ID="ami-6edd3078"
INSTANCE_TYPE="t2.micro"
EC2_BIN="aws --region us-east-1 ec2"

#Cleaning Directory. To avoid conflicts if we run script more than once
rm -f /tmp/Auth_key.pem
rm -f /tmp/Output.txt


#---------------------------------------
# CREATING KEY PAIR AND DOWNLOADING IT #
#---------------------------------------

echo "Creating Key pair"
$EC2_BIN create-key-pair \
--key-name Auth_key \
--query 'KeyMaterial' \
--output text > /tmp/Auth_key.pem

chmod 400 /tmp/Auth_key.pem


#-------------------------------
# CREATING UBUNTU EC2 INSTANCE #
#-------------------------------

echo "Launching instance"
$EC2_BIN run-instances \
--image-id $AMI_ID \
--count 1 \
--instance-type ${INSTANCE_TYPE} \
--placement '{"AvailabilityZone":"us-east-1e"}' \
--iam-instance-profile Name="EMSEC2Instance" \
--disable-api-termination \
--user-data file://userData.txt \
--instance-initiated-shutdown-behavior 'terminate' \
--block-device-mappings '[{"DeviceName":"/dev/sda1","Ebs":{"VolumeSize":10,"VolumeType":"standard"}}]' \
--key-name Auth_key \
--security-groups web > /tmp/Output.txt

INSTANCE_ID=$(cat /tmp/Output.txt | grep InstanceId|awk -F '"' '{print $4}')

#--------------------------------
# ALLOCATING ELASTIC IP ADDRESS #
#--------------------------------

$EC2_BIN allocate-address > /tmp/Output.txt

PUBLIC_IP=$(cat /tmp/Output.txt | grep PublicIp|awk -F '"' '{print $4}')


#-----------------------------------------------
# ASSIGNING ELASTIC IP ADDRESS TO EC2 INSTANCE #
#-----------------------------------------------
sleep 90
$EC2_BIN associate-address --instance-id $INSTANCE_ID --public-ip $PUBLIC_IP
sleep 90
echo "Display instance"
$EC2_BIN describe-instances | jq -r '.Reservations[0].Instances[0].PublicIpAddress'
