CREATE TABLE IF NOT EXISTS Event (
	applicationId TEXT,
	browserName TEXT,
	canonicalUrl TEXT,
	channelId BIGINT,
	city TEXT,
	contentLanguageId TEXT,
	country TEXT,
	createDate TIMESTAMP,
	dataSourceId BIGINT,
	description TEXT,
	deviceType TEXT,
	eventDate TIMESTAMP,
	eventId TEXT,
	experienceId TEXT,
	id TEXT UNIQUE ,
	languageId TEXT,
	platformName TEXT,
	projectId TEXT,
	projectTimeZoneId TEXT,
	referrer TEXT,
	region TEXT,
	sessionId TEXT,
	timezoneOffset TEXT,
	title TEXT,
	url TEXT,
	userId TEXT,
	variantId TEXT
);

CREATE TABLE IF NOT EXISTS EventProperty (
	channelId BIGINT,
	eventDate TIMESTAMP,
	id TEXT,
	name TEXT,
	projectId TEXT,
	value TEXT
);

CREATE TABLE IF NOT EXISTS Session (
	channelId BIGINT,
	id TEXT UNIQUE,
	projectId TEXT,
	sessionEnd TIMESTAMP,
	sessionStart TIMESTAMP
);