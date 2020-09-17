#!/bin/bash

CURRENT_DATE=$(date)
GIT_HASH=$(git rev-parse --short=7 HEAD)

function build_and_push_docker_images {
	if [[ $(docker images -q liferaycloud/com-liferay-osb-asah-private 2> /dev/null) ]]
	then
		echo ""
		echo "Removing local images."

		docker rmi -f $(docker images -q liferaycloud/com-liferay-osb-asah-private) >/dev/null
	fi

	for file_name in `ls`
	do
		if [ -z "$(ls -A ${file_name}/.wedeploy-profile-* 2> /dev/null)" ] ||
		   [ ! -e ${file_name}/Dockerfile ]
		then
			continue
		fi

		local docker_image_tag=$(get_docker_image_tag ${file_name})

		build_docker_image ${docker_image_tag} ${file_name}

		echo ""
		echo "Pushing ${docker_image_tag}."
		echo ""

		docker push ${docker_image_tag}
	done
}

function build_docker_image {
	local docker_image_tag=${1}
	local file_name=${2}

	echo ""
	echo "Building ${docker_image_tag}."
	echo ""

	if [[ ${file_name} == osb-asah-backend ]] ||
	   [[ ${file_name} == osb-asah-batch-curator ]] ||
	   [[ ${file_name} == osb-asah-dxp-extractor ]] ||
	   [[ ${file_name} == osb-asah-extractor ]] ||
	   [[ ${file_name} == osb-asah-publisher ]] ||
	   [[ ${file_name} == osb-asah-salesforce-extractor ]] ||
	   [[ ${file_name} == osb-asah-stream-curator ]] ||
	   [[ ${file_name} == osb-asah-upgrade ]]
	then
		cp ~/.asah/gcp_credentials.json ${file_name}/build/gcp_credentials.json

		echo "" >> ${file_name}/Dockerfile
		echo "COPY ./build/gcp_credentials.json gcp_credentials.json" >> ${file_name}/Dockerfile
		echo "ENV GOOGLE_APPLICATION_CREDENTIALS=/root/gcp_credentials.json" >> ${file_name}/Dockerfile
	fi

	docker build \
		--build-arg LABEL_BUILD_DATE=$(date "${CURRENT_DATE}" +'%Y-%m-%dT%H:%M:%SZ') \
		--build-arg LABEL_VCS_REF=$(git rev-parse HEAD) \
		--build-arg LABEL_VCS_URL=$(git config --get remote.origin.url) \
		--tag ${docker_image_tag} \
		${file_name}

	git checkout --quiet ${file_name}/Dockerfile
}

function check_repository {
	if [[ ! -f ~/.asah/gcp_credentials.json ]]
	then
		echo "${HOME}/.asah/gcp_credentials.json does not exist.";

		exit
	fi

	gradlew formatSource

	if [ -n "$(git status --porcelain -uno)" ]
	then
		echo "There are source formatter changes. Please fix them and try again.";

		exit
	fi
}

function compile_repository {
	gradlew clean assemble
}

function date {
	export LC_ALL=en_US.UTF-8

	if [ -z ${1+x} ] || [ -z ${2+x} ]
	then
		if [ "$(uname)" == "Darwin" ]
		then
			echo $(/bin/date)
		elif [ -e /bin/date ]
		then
			echo $(/bin/date)
		else
			echo $(/usr/bin/date)
		fi
	else
		if [ "$(uname)" == "Darwin" ]
		then
			echo $(/bin/date -jf "%a %b %e %H:%M:%S %Z %Y" "${1}" "${2}")
		elif [ -e /bin/date ]
		then
			echo $(/bin/date -d "${1}" "${2}")
		else
			echo $(/usr/bin/date -d "${1}" "${2}")
		fi
	fi
}

function generate_wedeploy_profile {
	local file_name=${1}
	local dir_name=${2}

	mkdir -p .wedeploy_profiles/${dir_name}/${file_name}

	cp ${file_name}/LCP.json .wedeploy_profiles/${dir_name}/${file_name}

	local file_content=$(<${file_name}/LCP.json)

	if [[ ${file_content} != *\"image\"* ]]
	then
		sed "s@\"id\"@\"image\": \"$(get_docker_image_tag ${file_name})\", \"id\"@" .wedeploy_profiles/${dir_name}/${file_name}/LCP.json

		python -m json.tool .wedeploy_profiles/${dir_name}/${file_name}/LCP.json > .wedeploy_profiles/${dir_name}/${file_name}/LCP.json.formatted

		mv .wedeploy_profiles/${dir_name}/${file_name}/LCP.json.formatted .wedeploy_profiles/${dir_name}/${file_name}/LCP.json

		sed $'s/    /\t/g' .wedeploy_profiles/${dir_name}/${file_name}/LCP.json

		perl -e 'chomp if eof' -pi .wedeploy_profiles/${dir_name}/${file_name}/LCP.json

		rm -f .wedeploy_profiles/${dir_name}/${file_name}/LCP.json.bak
	fi
}

function generate_wedeploy_profiles {
	local previous_git_hash=$(git log --pretty=tformat:"%h" -n1 ".wedeploy_profiles")

	rm -fr .wedeploy_profiles

	for file_name in `ls`
	do
		local pattern="${file_name}/.wedeploy-*"

		local markers=(${pattern})

		local marker=${markers[0]}

		if [[ ! -f ${marker} ]]
		then
			continue
		fi

		generate_wedeploy_profile ${file_name} ${marker##*.wedeploy-profile-}

		if [ $(git diff ${previous_git_hash} osb-asah-common | wc -l) -gt 0 ] ||
			 [ $(git diff ${previous_git_hash} ${file_name} | wc -l) -gt 0 ]; then

			generate_wedeploy_profile ${file_name} customer-upgrade
		fi
	done

	git add .wedeploy_profiles

	git commit -m "Generate WeDeploy profiles at $(date "${CURRENT_DATE}" +'%Y%m%d')-${GIT_HASH}" .wedeploy_profiles
}

function generate_tag {
	git tag $(date "${CURRENT_DATE}" +'%Y%m%d')-${GIT_HASH} HEAD
}

function get_docker_image_tag {
	echo "liferaycloud/com-liferay-osb-asah-private:${1}-$(date "${CURRENT_DATE}" +'%Y%m%d')-${GIT_HASH}"
}

function gradlew {
	./gradlew "$@"

	if [[ $? -ne 0 ]]
	then
		exit 1
	fi
}

function main {
	check_repository

	compile_repository

	build_and_push_docker_images

	generate_wedeploy_profiles

	generate_tag

	push_to_github
}

function push_to_github {
	git push origin
	git push upstream
	git push upstream $(date "${CURRENT_DATE}" +'%Y%m%d')-${GIT_HASH}
}

function sed {
	if [ "$(uname)" == "Darwin" ]
	then
		/usr/bin/sed -i .bak "${1}" "${2}"
	else
		/usr/bin/sed -i "${1}" "${2}"
	fi
}

main