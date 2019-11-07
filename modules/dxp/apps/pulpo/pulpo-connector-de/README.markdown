# Liferay Pulpo Connector DE

[![Build Status](https://travis-ci.com/liferay/com-liferay-pulpo-connector-de-private.svg?token=mHyfMk6tfD1qBSuqnvbh&branch=master)](https://travis-ci.com/liferay/com-liferay-pulpo-connector-de-private)
[![Coverage Status](https://coveralls.io/repos/github/liferay/com-liferay-pulpo-connector-de-private/badge.svg?branch=7.0.x-private&t=eGW1nM)](https://coveralls.io/github/liferay/com-liferay-pulpo-connector-de-private?branch=7.0.x-private)

This project creates a connector for Liferay DE that will aggregate and
send data to LCS that will then be consumed by the Pulpo Engines (see
https://github.com/liferay/com-liferay-osb-pulpo-engine-assets-private
and https://github.com/liferay/com-liferay-osb-pulpo-engine-contacts-private).
This data will initially consist of contacts, assets and analytics.

In the future, there will be connectors for other systems, such as
Salesforce, Hubspot, Drupal... etc.

## Sample Liferay DE running in the cloud
The latest version of the connectors is deployed on an instance of
Liferay DE available in this url:
http://pulpo-client-de-dev.eu-west-1.elasticbeanstalk.com

## Building the Connector
If you want to create the connector from the source, you can execute:

```shell
./gradlew deploy
```

It will generate several jar files in the folder pulpo-connector-de-docker`.
These jar files should be deployed to a Liferay DE installation as any
other module (deploy folder of Liferay DE).

## Running the connectors locally
In order to run the connectors, you can use your own Liferay DE with the
latest version of LCS portlet installed and configured.
Since this can be tedious, as an alternative, the docker image generated
by the project already takes care of installing LCS and configuring it.
In order to build this docker image, you need to set the following
environment variables:

| Environment Variable Name                  | Value                   |
|--------------------------------------------|-------------------------|
| LCS_PORTAL_EMAIL                           | test@liferay.com        |
| LCS_PORTAL_HOST                            | lcs-portal-pulpo        |
| LCS_PORTAL_PORT                            | 8080                    |
| LCS_PORTAL_PWD                             |r3m3mb3r                 |

Then, running this will create the docker image:

```shell
./gradlew build buildAppDockerImage
```

It will generate an image named `liferay-pulpo-client-de`,
with `latest` as tag.

You can then run this image and link a port in your docker host to the
8080 port in the container this way:

```shell
docker run -p 8081:8080 liferay-pulpo-client-de
```

This will expose Liferay DE in localhost:8081. Feel free to change this
port to use any other.

The connectors use the following environment variables (in addition to
the previous ones) that should be set too:

| Environment Variable Name                      | Value                 |
|------------------------------------------------|-----------------------|
| LCS_GATEWAY_IP                                 | 54.235.215.13         |
| LCS_PORTAL_IP                                  | 54.243.198.186        |
| PULPO_ASSET_CONNECTOR_ENVIRONMENT_UNIQUENAME   | dev                   |
| PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME | dev                   |

## Testing the connectors
In order to run the tests, the connectors should be deployed into a
Liferay DE installation with the latest version of LCS deployed and
configured. If you have the docker image running, then you just need to execute:

```shell
./gradlew testIntegration
```