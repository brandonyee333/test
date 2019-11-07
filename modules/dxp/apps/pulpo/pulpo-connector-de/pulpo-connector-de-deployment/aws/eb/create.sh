#!/usr/bin/env bash

# Echo commands with expanded variables
set -x

eb create --instance_type t2.large --region eu-west-1 pulpo-client-de-dev