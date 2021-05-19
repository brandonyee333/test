CREATE SCHEMA IF NOT EXISTS hive.default;

USE hive.default;

CREATE TABLE IF NOT EXISTS CustomAsset (
	abandonments BIGINT,
    assetId VARCHAR,
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