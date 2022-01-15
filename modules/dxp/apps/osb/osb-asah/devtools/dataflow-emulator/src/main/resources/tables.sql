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
	projectId TEXT,
	name TEXT,
	value TEXT
);

CREATE TABLE IF NOT EXISTS Session (
	sessionStart TIMESTAMP,
	projectId TEXT,
	channelId BIGINT,
	id TEXT UNIQUE,
	sessionEnd TIMESTAMP
);
