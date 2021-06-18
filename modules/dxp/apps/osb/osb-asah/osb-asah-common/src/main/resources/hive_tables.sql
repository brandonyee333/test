CREATE SCHEMA IF NOT EXISTS hive.default;

USE hive.default;

CREATE TABLE IF NOT EXISTS CustomAsset (
	abandonments BIGINT,
	assetId VARCHAR,
	assetPrimaryKey VARCHAR,
	category VARCHAR,
	channelId BIGINT,
	clicks BIGINT,
	downloads BIGINT,
	eventDate TIMESTAMP,
	projectId VARCHAR,
	readTime BIGINT,
	submissionTime BIGINT,
	submissions BIGINT,
	userId VARCHAR,
	variantId VARCHAR,
	views BIGINT
);

CREATE TABLE IF NOT EXISTS Journal (
	assetId VARCHAR,
	assetPrimaryKey VARCHAR,
	browserName VARCHAR,
	canonicalUrl VARCHAR,
	channelId BIGINT,
	city VARCHAR,
	clicks BIGINT,
	contentLanguageId VARCHAR,
	country VARCHAR,
	deviceType VARCHAR,
	eventDate TIMESTAMP,
	knownIndividual BOOLEAN,
	platformName VARCHAR,
	projectId VARCHAR,
	region VARCHAR,
	userId VARCHAR,
	variantId VARCHAR,
	views BIGINT
);