#!/usr/bin/env bash

# Echo commands with expanded variables
set -x

# Download and unzip installation bundle
curl "https://s3.amazonaws.com/aws-cli/awscli-bundle.zip" -o "awscli-bundle.zip"

unzip awscli-bundle.zip

# Install aws cli
./awscli-bundle/install -b ~/bin/aws

# Install eb cli
pip3 install awsebcli --upgrade