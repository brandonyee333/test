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