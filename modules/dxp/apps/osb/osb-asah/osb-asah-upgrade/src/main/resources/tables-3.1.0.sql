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
	modifiedByUserId BIGINT,
	modifiedByUserName TEXT,
	modifiedDate TIMESTAMPTZ,
	name VARCHAR(255),
	rangeEnd TIMESTAMPTZ,
	rangeKey TEXT,
	rangeStart TIMESTAMPTZ
);