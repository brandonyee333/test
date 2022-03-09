CREATE TABLE IF NOT EXISTS BQEvent (
	applicationId TEXT,
	browserName TEXT,
	canonicalUrl TEXT,
	channelId BIGINT,
	city TEXT,
	contentLanguageId TEXT,
	context TEXT,
	country TEXT,
	createDate TIMESTAMPTZ,
	dataSourceId BIGINT,
	description TEXT,
	deviceType TEXT,
	eventDate TIMESTAMPTZ,
	eventId TEXT,
	eventProperties TEXT,
	experienceId TEXT,
	id TEXT UNIQUE,
	keywords TEXT,
	languageId TEXT,
	platformName TEXT,
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

CREATE TABLE IF NOT EXISTS BQEventProperty (
	channelId BIGINT,
	eventDate TIMESTAMPTZ,
	id TEXT,
	name TEXT,
	projectId TEXT,
	value TEXT
);

CREATE TABLE IF NOT EXISTS BQSession (
	channelId BIGINT,
	id TEXT UNIQUE,
	sessionEnd TIMESTAMPTZ,
	sessionStart TIMESTAMPTZ
);