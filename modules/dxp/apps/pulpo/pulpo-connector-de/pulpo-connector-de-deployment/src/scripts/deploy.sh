#!/usr/bin/env bash

while getopts ":c:p:v:i:" opt; do
  case ${opt} in
    c) componentName="${OPTARG}"
    ;;
    i) dockerImageName="${OPTARG}"
    ;;
    p) pathToComponentDeployDir="${OPTARG}"
    ;;
    v) dockerImageVersion="${OPTARG}"
    ;;
    \?) echo "Invalid option -${OPTARG}" >&2
    exit 1
    ;;
  esac
done

printf "Parameters: \n"
printf "componentName: %s\n" "${componentName}"
printf "dockerImageName: %s\n" "${dockerImageName}"
printf "dockerImageVersion: %s\n" "${dockerImageVersion}"
printf "pathToComponentDeployDir: %s\n" "${pathToComponentDeployDir}"
printf "\n"

printf "Env variables: \n"
printf "COMPONENT_NAME: %s\n" "${COMPONENT_NAME}"
printf "DOCKER_IMAGE_NAME: %s\n" "${DOCKER_IMAGE_NAME}"
printf "DOCKER_IMAGE_VERSION: %s\n" "${DOCKER_IMAGE_VERSION}"
printf "PATH_TO_COMPONENT_DEPLOY_DIR: %s\n" "${PATH_TO_COMPONENT_DEPLOY_DIR}"
printf "\n"

GROUP_NAME="pulpo"

# Set COMPONENT_NAME variable if not already set and parameter is present
if [ -z "${COMPONENT_NAME}" ]; then
    if [[ ! -z "${componentName}" ]]; then
        COMPONENT_NAME=${componentName}
    fi
fi

# Set DOCKER_IMAGE_NAME variable if not already set and parameter is present
if [ -z "${DOCKER_IMAGE_NAME}" ]; then
    if [ -z "${dockerImageName}" ]; then
        DOCKER_IMAGE_NAME=com-liferay-osb-${GROUP_NAME}-${COMPONENT_NAME}-private
    else
        DOCKER_IMAGE_NAME=${dockerImageName}
    fi
fi

# Get explicit tag for docker image from images built locally if not already set
if [ -z "${DOCKER_IMAGE_VERSION}" ]; then
    if [ -z "${dockerImageVersion}" ]; then
        DOCKER_IMAGE_VERSION=$(docker images liferay/${DOCKER_IMAGE_NAME} --format "{{.Tag}}" | grep ".*T.*Z")
    else
        DOCKER_IMAGE_VERSION=${dockerImageVersion}
    fi
fi

# Set PATH_TO_COMPONENT_DEPLOY_DIR variable if not already set and parameter is present
if [ -z "${PATH_TO_COMPONENT_DEPLOY_DIR}" ]; then
    if [[ ! -z "${pathToComponentDeployDir}" ]]; then
        PATH_TO_COMPONENT_DEPLOY_DIR=${pathToComponentDeployDir}
    fi
fi

# Set EB_DIR explictly, as this name differs from the component name and the image name
EB_DIR=${TRAVIS_BUILD_DIR}/pulpo-connector-de-deployment/aws/eb

printf "Values used: \n"
printf "COMPONENT_NAME: %s\n" "${COMPONENT_NAME}"
printf "DOCKER_IMAGE_NAME: %s\n" "${DOCKER_IMAGE_NAME}"
printf "DOCKER_IMAGE_VERSION: %s\n" "${DOCKER_IMAGE_VERSION}"
printf "PATH_TO_COMPONENT_DEPLOY_DIR: %s\n" "${PATH_TO_COMPONENT_DEPLOY_DIR}"
printf "EB_DIR: %s\n" "${EB_DIR}"
printf "\n"

# Echo commands with expanded variables
set -x

cd ${EB_DIR}

# Copy template Dockerrun.aws.json and replace template vars

rm Dockerrun.aws.json || true

cp Dockerrun.aws.json.template Dockerrun.aws.json

if [ -f "${EB_DIR}/config.properties" ]; then
    printf "Config: \n"
    while IFS="=" read -r key value; do
      case "$key" in
        '#'*) ;;
        *)
          printf "%s %s %s\n" ${key}=${value}
          sed -i -e "s/<${key}>/${value}/" Dockerrun.aws.json
      esac
    done < "${EB_DIR}/config.properties"
    printf "\n"
fi

sed -i -e "s/<DOCKER_IMAGE_NAME>/$DOCKER_IMAGE_NAME/" Dockerrun.aws.json
sed -i -e "s/<DOCKER_IMAGE_VERSION>/$DOCKER_IMAGE_VERSION/" Dockerrun.aws.json

rm Dockerrun.aws.json-e || true

# Deploy

eb deploy -l "${GROUP_NAME}-${COMPONENT_NAME}_${DOCKER_IMAGE_VERSION}" -m "GROUP_NAME=${GROUP_NAME}. COMPONENT_NAME=${COMPONENT_NAME}. DOCKER_IMAGE_NAME=${DOCKER_IMAGE_NAME}. DOCKER_IMAGE_VERSION=${DOCKER_IMAGE_VERSION}. TRAVIS_BRANCH=${TRAVIS_BRANCH}. TRAVIS_JOB_ID=${TRAVIS_JOB_ID}. TRAVIS_BUILD_ID=${TRAVIS_BUILD_ID}. TRAVIS_COMMIT=${TRAVIS_COMMIT}." -vvv --debug

cd ${TRAVIS_BUILD_DIR}