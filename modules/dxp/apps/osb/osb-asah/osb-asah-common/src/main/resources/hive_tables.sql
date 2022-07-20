CREATE SCHEMA IF NOT EXISTS hive.default;

USE hive.default;

CREATE TABLE IF NOT EXISTS Journal (
	assetId VARCHAR,
	assetPrimaryKey VARCHAR,
	browserName VARCHAR,
	canonicalUrl VARCHAR,
	channelId BIGINT,
	city VARCHAR,
	clicks BIGINT,
	country VARCHAR,
	dataSourceId BIGINT,
	deviceType VARCHAR,
	eventDate TIMESTAMP,
	individualId BIGINT,
	knownIndividual BOOLEAN,
	platformName VARCHAR,
	projectId VARCHAR,
	region VARCHAR,
	segmentNames ARRAY<VARCHAR>,
	sessionId VARCHAR,
	title VARCHAR,
	userId VARCHAR,
	variantId VARCHAR,
	views BIGINT
);