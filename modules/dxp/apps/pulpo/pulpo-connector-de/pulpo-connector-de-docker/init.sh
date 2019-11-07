#!/bin/bash

python /TokenDownloader.py

ret=$?
if [ $ret -ne 0 ]; then
	echo 'Exception while downloading LCS token. Aborting'
	exit
fi

# Delete lcs-portlet-7.0.10.19.war since we are copying
# lcs-portlet-7.0.10.20.war into /liferay/deploy and having two versions
# causes problems
# We need to avoid that lcs-portlet-7.0.10.19.war is copied
rm /liferay/deploy/lcs-portlet-7.0.10.19.war

/bin/bash /run.sh