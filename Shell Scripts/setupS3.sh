#!/bin/bash

# Shell script includes the following components:

# CREATING A BUCKET USING DNS-COMPLAINT BUCKET NAME
# CREATING BUCKET IN us-east-1 REGION
# ENABLING VERSIONING ON BUCKET
# INSTALLING PACKAGES ON EC2 INSTANCE
# TAGGING THE BUCKET WITH KEY-VALUE
# GRANT READ PERMISSIONS



set -e
#------------
# VARIABLES #
#------------

Bucket_Name="s3.neu-csye6225-spring2017-team-8.site"
REGION="us-east-1"
S3_BUCKET="aws s3api"

#---------------------------------------------------
# CREATING  BUCKET USING DNS-COMPLAINT BUCKET NAME #
#---------------------------------------------------

echo "Creating S3 Bucket"
$S3_BUCKET create-bucket --bucket s3.neu-csye6225-spring2017-team-8.site \
--region eu-west-1 \
--create-bucket-configuration LocationConstraint=eu-west-1

#--------------------------
# GRANT READ PERMISSIONS #
#--------------------------

echo "Granting Read Permissions to Everyone"
$S3_BUCKET put-bucket-acl \
--bucket s3.neu-csye6225-spring2017-team-8.site \
--grant-read uri=http://acs.amazonaws.com/groups/global/AllUsers

#---------------------------------
# ENABLING VERSIONING ON BUCKET #
#---------------------------------

echo "Enabling Versioning on Bucket"
$S3_BUCKET put-bucket-versioning \
--bucket s3.neu-csye6225-spring2017-team-8.site \
--versioning-configuration Status=Enabled

#------------------------------------
# TAGGING THE BUCKET WITH KEY-VALUE #
#------------------------------------

echo "Tagging the bucket with key value"
$S3_BUCKET put-bucket-tagging \
--bucket s3.neu-csye6225-spring2017-team-8.site \
--tagging 'TagSet=[{Key=TEAM,Value=8}]'

echo "Bucket Creation Completed"