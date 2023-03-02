#!/bin/bash

set -e

docker pull liferay/com-liferay-osb-faro:master

./gradlew clean assemble

LABEL_BUILD_DATE=$(git log -1 --format=%cd) \
LABEL_VCS_REF=$(git rev-parse --short=7 HEAD) \
docker-compose $( [[ -n "$1" ]] && printf %s "-f docker-compose.${1}.yml" ) up --build -d

docker image prune -f