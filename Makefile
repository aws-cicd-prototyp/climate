SHELL = /bin/bash

.PHONY = startContainer _buildNativeImage _runNativeImage _localDevMode _mvnCleanInstall

help:
	@echo "Usage: "
	@echo -e "\n  Start a native-executable container:"
	@echo "    make sc"
	@echo -e "\n  Start on local dev-mode"
	@echo "    make sdm"

sdm	: _localDevMode

deployNativeImageToEcr: _buildApplication _buildNativeImage _tagNativeImage _loginIntoEcr _pushImgToEcr
deployJvmImageToEcr: _buildApplication _buildJvmImage _tagJvmImage _loginIntoEcr _pushImgToEcr


_buildJvmImage:
	@echo -e "\n Build the image in jvm-mode:"
	@docker build -f src/main/docker/Dockerfile.jvm -t climate-jvm .

_buildNativeImage:
	@echo -e "\n Build native image..."
	@docker build -f Dockerfile.native.multistage -t climate .

_runNativeImage:
	@echo "Run native image..."
	@docker run -i --rm -p 8080:8080 climate

_localDevMode:
	@echo "Start in local dev-mode"
	@mvn compile quarkus:dev

_buildApplication:
	@echo "Build Application"
	@mvn clean install

_loginIntoEcr:
	@echo -e "\n LogIn into Ecr..."
	@aws ecr get-login-password --region eu-central-1 --profile michi.prod | docker login --username AWS --password-stdin 496106771575.dkr.ecr.eu-central-1.amazonaws.com/climate-ecr-dev

_tagNativeImage:
	@echo -e "\n Tag native image..."
	@docker tag climate-ecr-dev:latest 496106771575.dkr.ecr.eu-central-1.amazonaws.com/climate-ecr-dev:latest

_tagJvmImage:
	@echo -e "\n Tag jvm image..."
	@docker tag climate-jvm:latest 496106771575.dkr.ecr.eu-central-1.amazonaws.com/climate-ecr-dev:latest
	@echo -e "\n successful tagged!"

_pushImgToEcr:
	@echo -e "\n Push to AWS-ECR..."
	@docker push 496106771575.dkr.ecr.eu-central-1.amazonaws.com/climate-ecr-dev:latest
