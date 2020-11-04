#!/bin/bash

set -e

umask 0002

function run_as_other_user_if_needed {
	if [ "$(id -u)" == "0" ]
	then
		exec chroot --userspec=1000 / "${@}"
	else
		exec "${@}"
	fi
}

if [ "$1" != "eswrapper" ]
then
	if [[ "$(id -u)" == "0" && $(basename "$1") == "elasticsearch" ]]
	then
		set -- "elasticsearch" "${@:2}"

		exec chroot --userspec=1000 / "$@"
	else
		exec "$@"
	fi
fi

[ -f /usr/share/elasticsearch/config/elasticsearch.keystore ] || (run_as_other_user_if_needed elasticsearch-keystore create)

source /usr/share/elasticsearch/bin/elasticsearch-env-from-file

if [ -f bin/elasticsearch-users ]
then
	if [ -n "$ELASTIC_PASSWORD" ]
	then
		if ! (run_as_other_user_if_needed elasticsearch-keystore has-passwd --silent)
		then
			if ! (run_as_other_user_if_needed elasticsearch-keystore list | grep -q '^bootstrap.password$')
			then
				(run_as_other_user_if_needed echo "$ELASTIC_PASSWORD" | elasticsearch-keystore add -x 'bootstrap.password')
			fi
		elif ! (run_as_other_user_if_needed echo "$KEYSTORE_PASSWORD" | elasticsearch-keystore list | grep -q '^bootstrap.password$')
		then
			COMMANDS="$(printf "%s\n%s" "$KEYSTORE_PASSWORD" "$ELASTIC_PASSWORD")"

			(run_as_other_user_if_needed echo "$COMMANDS" | elasticsearch-keystore add -x 'bootstrap.password')
		fi
	fi
fi

if [ -n "$S3_REPOSITORY_ACCESS_KEY" ]
then
	if ! (run_as_other_user_if_needed elasticsearch-keystore list | grep -q '^s3.client.asah.access_key$')
	then
		(run_as_other_user_if_needed echo $S3_REPOSITORY_ACCESS_KEY | /usr/share/elasticsearch/bin/elasticsearch-keystore add -x s3.client.asah.access_key)
	fi
fi

if [ -n "$S3_REPOSITORY_SECRET_KEY" ]
then
	if ! (run_as_other_user_if_needed elasticsearch-keystore list | grep -q '^s3.client.asah.secret_key$')
	then
		(run_as_other_user_if_needed echo $S3_REPOSITORY_SECRET_KEY | /usr/share/elasticsearch/bin/elasticsearch-keystore add -x s3.client.asah.secret_key)
	fi
fi

if [ "$(id -u)" == "0" ]
then
	if [ -n "$TAKE_FILE_OWNERSHIP" ]
	then
		chown -R 1000:0 /usr/share/elasticsearch/{data,logs}
	fi
fi

run_as_other_user_if_needed /usr/share/elasticsearch/bin/elasticsearch <<< "$KEYSTORE_PASSWORD"