CREATE TABLE IF NOT EXISTS Event (
	id BIGSERIAL PRIMARY KEY,
	analyticsEventId TEXT UNIQUE,
	applicationId TEXT,
	channelId BIGINT NOT NULL,
	createDate TIMESTAMPTZ,
	dataSourceId BIGINT,
	eventDate TIMESTAMPTZ,
	eventDefinitionId BIGINT,
	individualId BIGINT,
	sessionId TEXT NOT NULL,
	userId TEXT
);

CREATE TABLE IF NOT EXISTS EventAnalysis (
	id BIGSERIAL PRIMARY KEY,
	breakdowns JSON,
	channelId BIGINT NOT NULL,
	compareToPrevious BOOLEAN DEFAULT false,
	createDate TIMESTAMPTZ,
	createdByUserId BIGINT,
	createdByUserName TEXT,
	eventAnalysisType TEXT,
	eventDefinitionId BIGINT,
	filters JSON,
	modifiedDate TIMESTAMPTZ,
	modifiedByUserId BIGINT,
	modifiedByUserName TEXT,
	name VARCHAR(255) UNIQUE,
	rangeEnd TIMESTAMPTZ,
	rangeKey TEXT,
	rangeStart TIMESTAMPTZ
);

CREATE TABLE IF NOT EXISTS EventAttribute (
	id BIGSERIAL PRIMARY KEY,
	eventAttributeDefinitionId BIGINT,
	eventDate TIMESTAMPTZ,
	eventId BIGINT REFERENCES Event,
	value TEXT
);

CREATE TABLE IF NOT EXISTS EventAttributeDefinition (
	id BIGSERIAL PRIMARY KEY,
	dataType TEXT NOT NULL,
	description TEXT,
	displayName TEXT UNIQUE,
	name VARCHAR(255) UNIQUE,
	type TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS EventDefinition (
	id BIGSERIAL PRIMARY KEY,
	blocked BOOLEAN DEFAULT false,
	blockedLastSeenDate TIMESTAMPTZ,
	blockedLastSeenURL TEXT,
	blockedReasonType TEXT,
	description TEXT,
	displayName TEXT UNIQUE,
	hidden BOOLEAN DEFAULT false,
	name VARCHAR(255) UNIQUE,
	type TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS EventDefinitionEventAttributeDefinition (
	eventAttributeDefinitionId BIGINT,
	eventDefinitionId BIGINT,
	sampleValue TEXT,
	PRIMARY KEY (eventAttributeDefinitionId, eventDefinitionId)
);