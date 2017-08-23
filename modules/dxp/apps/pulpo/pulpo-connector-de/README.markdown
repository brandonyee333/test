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
http://pulpo-connector-de-dev.eu-west-1.elasticbeanstalk.com/

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

| Environment Variable Name              | Value                                |
|----------------------------------------|--------------------------------------|
| OSB_LCS_GATEWAY_WEB_HOST_NAME          | lcs-gateway-pulpo                    |
| OSB_LCS_GATEWAY_WEB_HOST_PORT          | 8080                                 |
| OSB_LCS_GATEWAY_WEB_PROTOCOL           | http                                 |
| OSB_LCS_PORTLET_EMAIL                  | test@liferay.com                     |
| OSB_LCS_PORTLET_HOST_NAME              | lcs-portal-pulpo                     |
| OSB_LCS_PORTLET_HOST_PORT              | 8080                                 |
| OSB_LCS_PORTLET_OAUTH_CONSUMER_KEY     | 9b1dea2f-ca69-4d49-8aaf-ac7e8275f68a |
| OSB_LCS_PORTLET_OAUTH_CONSUMER_SECRET  | fe8691a9fbc1ff95c2b65f0f4faa1e61     |
| OSB_LCS_PORTLET_PROTOCOL               | http                                 |
| OSB_LCS_PORTLET_PWD                    | r3m3mb3r                             |

Then, running this will create the docker image:

```shell
./gradlew build buildAppDockerImage
```

It will generate an image named `com-liferay-pulpo-connector-de-private`,
with `latest` as tag.

You can then run this image and link a port in your docker host to the
8080 port in the container this way:

```shell
docker run -p 8081:8080 com-liferay-pulpo-connector-de-private
```

This will expose Liferay DE in localhost:8081. Feel free to change this
port to use any other.

The connectors use the following environment variables (in addition to
the previous ones) that should be set too:

| Environment Variable Name                      | Value                 |
|------------------------------------------------|-----------------------|
| OSB_LCS_GATEWAY_WEB_IP                         | 54.235.215.13         |
| OSB_LCS_PORTLET_IP                             | 54.243.198.186        |
| PULPO_ASSET_CONNECTOR_ENVIRONMENT_UNIQUENAME   | dev                   |
| PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME | dev                   |

## Testing the connectors
In order to run the tests, the connectors should be deployed into a
Liferay DE installation with the latest version of LCS deployed and
configured. If you have the docker image running, then you just need to execute:

```shell
./gradlew testIntegration
```

Running the test will use the default value of these variables. They can
be changed as an environment variable in your system to run them against
a different environment.

| Environment Variable Name                               | System Setting                          | Default Value                                                                                |
|---------------------------------------------------------|-----------------------------------------|----------------------------------------------------------------------------------------------|
| PULPO_TEST_CONNECTOR_DE_URL                             | Connector URL                           | http://localhost:8080                                                                        |
| PULPO_TEST_ASSET_CONNECTOR_ENVIRONMENT_UNIQUENAME       | Asset Connector Environment UniqueName  | dev                                                                                          |
| PULPO_TEST_ASSET_ENGINE_URL                             | Asset Engine URL                        | http://pulpo-engine-assets-dev.eu-west-1.elasticbeanstalk.com                                |
| PULPO_TEST_ASSET_ELASTIC_SEARCH_URL                     | Asset Engine Elastic Search URL         | https://search-pulpo-elasticsearch-dev-fowirqyosr5ljgr24rfuuqwmqm.eu-west-1.es.amazonaws.com |                                               |
| PULPO_TEST_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME     | Contact Connector Environment UniqueName| dev                                                                                          |
| PULPO_TEST_CONTACT_ENGINE_URL                           | Contact Engine URL                      | http://pulpo-engine-contacts-dev.eu-west-1.elasticbeanstalk.com                              |
| PULPO_TEST_CONTACT_ELASTIC_SEARCH_URL                   | Contact Engine Elastic Search URL       | https://search-pulpo-elasticsearch-dev-fowirqyosr5ljgr24rfuuqwmqm.eu-west-1.es.amazonaws.com |                                               |