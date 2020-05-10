#!/bin/bash

set -e

CREATE_FOR_DELIVERY=false
DEVOPS_ACCOUNT_ID="147376585776"
ECR_NAME="microservice-images"
REPOSITORY="climate"
SUFFIX="feature-cf-test-123"
CODEBUILD_RESOLVED_SOURCE_VERSION="8c57d2db35f05f6ec142eefbe83a9037e6777493"

echo "build application:"
mvn clean install

echo "login into ecr:"
aws ecr get-login-password --region eu-central-1 | docker login --username AWS --password-stdin ${DEVOPS_ACCOUNT_ID}.dkr.ecr.eu-central-1.amazonaws.com/

if [ $CREATE_FOR_DELIVERY = "true" ]
then
  echo "start build and push process for DELIVERY stages:"
  docker build -f src/main/docker/Dockerfile.jvm -t ${REPOSITORY}-${SUFFIX}-delivery-image:$CODEBUILD_RESOLVED_SOURCE_VERSION .

  docker tag ${REPOSITORY}-${SUFFIX}-delivery-image:$CODEBUILD_RESOLVED_SOURCE_VERSION \
    ${DEVOPS_ACCOUNT_ID}.dkr.ecr.eu-central-1.amazonaws.com/${ECR_NAME}:$CODEBUILD_RESOLVED_SOURCE_VERSION

  docker push ${DEVOPS_ACCOUNT_ID}.dkr.ecr.eu-central-1.amazonaws.com/${ECR_NAME}:$CODEBUILD_RESOLVED_SOURCE_VERSION

elif [ $CREATE_FOR_DELIVERY = "false" ]
then
  echo "start build and push process for PREVIEW stage:"
  docker build -f src/main/docker/Dockerfile.jvm -t ${REPOSITORY}-${SUFFIX}-preview-image:${SUFFIX} .

  docker tag ${REPOSITORY}-${SUFFIX}-preview-image:${SUFFIX} \
    ${DEVOPS_ACCOUNT_ID}.dkr.ecr.eu-central-1.amazonaws.com/${ECR_NAME}:${SUFFIX}

  docker push ${DEVOPS_ACCOUNT_ID}.dkr.ecr.eu-central-1.amazonaws.com/${ECR_NAME}:${SUFFIX}
else
  echo "ERROR, invalid boolean for CREATE_FOR_DELIVERY'"
fi
echo "PROCESS FINISHED!"
