[![Build Status](https://travis-ci.com/liferay/com-liferay-osb-asah-private.svg?token=qheGTkbg7SYfhBrP7xPX&branch=7.0.x-private)](https://travis-ci.com/liferay/com-liferay-osb-asah-private)
[![Coverage Status](https://coveralls.io/repos/github/liferay/com-liferay-osb-asah-private/badge.svg?branch=7.0.x-private&t=NFTa2k)](https://coveralls.io/github/liferay/com-liferay-osb-asah-private?branch=7.0.x-private)

# Asah

Asah is made up of small services that power Liferay Analytics Cloud. Each subproject in this repository represents one service.

Each customer is given unique instances of Asah's services. Those services are grouped under a workspace that is hosted by Liferay DXP Cloud.

For example, these services serve the data of an Analytics Cloud customer named ACME:

- `osbasahbackend-acme.liferay.cloud`
- `osbasahbatchcurator-acme.liferay.cloud`
- `osbasahextractor-acme.liferay.cloud`
- And so on...

As another example, these services serve the data of an Analytics Cloud customer named BOOBOO:

- `osbasahbackend-booboo.liferay.cloud`
- `osbasahbatchcurator-booboo.liferay.cloud`
- `osbasahextractor-booboo.liferay.cloud`
- And so on...

## Services

### osb-asah-backend

This service exposes endpoints to the [Faro frontend](https://github.com/liferay/com-liferay-osb-faro-private) and Liferay Portal.

### osb-asah-batch-curator

This service processes raw data into intelligent information using scheduled routines (e.g., once a day).

### osb-asah-demo

This service generates raw demo data.

### osb-asah-dxp-extractor

This service extracts and saves raw data from a customer's Liferay DXP instance.

### osb-asah-extractor

This service extracts and saves events from [osb-asah-queue](#osb-asah-queue).

### osb-asah-publisher

This service receives events from HTTP requests made by users' browsers, and publishes them to [osb-asah-queue](#osb-asah-queue).

### osb-asah-queue

This is a Message Queue service that contains messages published by [osb-asah-publisher](#osb-asah-publisher).

### osb-asah-redis

This is a Redis instance used as cache storage and message bus.

### osb-asah-salesforce-extractor

This service extracts and saves raw data from a customer's Salesforce account.

### osb-asah-stream-curator

This service processes raw data into intelligent information using routines with a higher frequency than [osb-asah-batch-curator](#osb-asah-batch-curator) (e.g., every 5 minutes).

### osb-asah-upgrade

This service defines upgrade steps that are executed on the deployment of new releases.

## Development Environment Instructions

Requirements:

- JDK 8+
- IntelliJ (recommended, CE is enough) or Eclipse
- Docker (on macOS or Windows increase memory to at least 4GB)
- Docker Compose

This repository should be imported by IntelliJ as a Gradle project. Once this is done, you can spin up a Docker container for Elasticsearch:

`docker-compose -f osb-asah-common/src/testIntegration/resources/elasticsearch-docker-compose.yml up -d`

To start a service, for example `osb-asah-backend`, open its `*SpringBootApplication` class, right-click it and choose `Run 'Backend'`.

### Microservice Approach

This is the default approach that starts a separate container for each service. This lets you modify and restart services individually without rebuilding everything.

This is also used for all paid customers to ensure there are sufficient resources to process all the data in a timely manner.

The downside to this approach is that it requires significantly more resources. At least 16 GB of memory is recommended.

To start up all services via docker, run `docker-compose up`.

### Monolith Approach

Alternatively, the Monolith can also be used for development. It is used for trial purposes, as it simplifies the development setup.

The main difference is that all services are bundled as a single Spring Boot application and some components have a different implementation (see `com.liferay.osb.asah.monolith.common`).

You can start in IntelliJ by running `OSBAsahMonolithSpringBootApplication`. If you make any changes, you must rerun the application and try it out.

The monolith can be started via docker: `docker-compose -f docker-compose.monolith.yml up`

Note that `docker-compose` YAML includes an Elasticsearch instance, which will conflict with any existing Elasticsearch instance.

### Environment Variables

The following is a non-exhaustive list of environment variables relevant for development:

- `LCP_ENGINE_ELASTICSEARCH_SERVER_IP`: Used to point to another Elasticsearch instance (default: `127.0.0.1`).