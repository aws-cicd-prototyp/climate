SHELL = /bin/bash

AWS_DEVOPS_PROFILE=default
AWS_PROD_PROFILE=michi.prod
AWS_REGION ?= eu-central-1

DEVOPS_ACCOUNT_ID=147376585776
WORKLOAD_ACCOUNT_ID=496106771575

BUCKET_DEV=ci-cd-bootstrap-devopsbasedev-10kar-artifactstore-vxieai8splhl
BUCKET_INTPROD=ci-cd-bootstrap-devopsbasedelivery-artifactstore-1lttxt4rg1a4w

KMS_DEV=arn:aws:kms:eu-central-1:147376585776:key/1d9b5b61-ac77-4602-9828-9e69d3075c4c
KMS_INTPROD=arn:aws:kms:eu-central-1:147376585776:key/76da54a3-6d7e-4b18-ab60-47bdb1ea5dea

PIPELINE_SERVICE_ROLE_DEV=arn:aws:iam::147376585776:role/CI-CD-Bootstrap-PipelineRolesD-PipelineServiceRole-1XPL06Y277SFN
PIPELINE_SERVICE_ROLE_INTPROD=arn:aws:iam::147376585776:role/CI-CD-Bootstrap-PipelineRolesD-PipelineServiceRole-7PEE7V5KVHUI

deployPipeline:
	@echo "Creating the Pipeline Stack..."
	@aws cloudformation create-stack \
		--stack-name climateMsPipelineStack \
		--template-body file://pipeline.yaml \
		--parameters \
        			ParameterKey="Project",ParameterValue="aws-cicd-prototyp" \
        			ParameterKey="Repository",ParameterValue="climate" \
	    			ParameterKey="Suffix",ParameterValue="feature-pr123" \
        			ParameterKey="Branch",ParameterValue="feature-pr123" \
        			ParameterKey="Stage",ParameterValue="dev" \
	      			ParameterKey="RemotePreviewAccount",ParameterValue="420052376030" \
        			ParameterKey="RemoteDeliveryAccount",ParameterValue="496106771575" \
        			ParameterKey="ArtifactBucket",ParameterValue=${BUCKET_DEV} \
        			ParameterKey="PipelineKmsKeyArn",ParameterValue=${KMS_DEV} \
        			ParameterKey="PipelineServiceRoleArn",ParameterValue=${PIPELINE_SERVICE_ROLE_DEV} \
        			ParameterKey="DynamicPipelineCleanupLambdaArn",ParameterValue="arn:aws:lambda:eu-central-1:147376585776:function:CI-CD-Bootstrap-DevOpsLam-DynamicPipelineCleanupLa-B1TEUINGML5I" \
        			ParameterKey="CreateForDelivery",ParameterValue="false" \
		--profile ${AWS_DEVOPS_PROFILE} \
		--capabilities CAPABILITY_NAMED_IAM \
		--region ${AWS_REGION}

	@echo "Waiting till all resources have been created... this can take some minutes"
	@aws cloudformation wait stack-create-complete \
		--stack-name climateMsPipelineStack \
		--profile ${AWS_DEVOPS_PROFILE} \
		--region ${AWS_REGION}
	@echo "successful created!"


#ONLY FOR TESTs:

