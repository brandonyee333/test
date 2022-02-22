CREATE TABLE IF NOT EXISTS AsahTask (
	id BIGSERIAL PRIMARY KEY,
	className TEXT,
	context JSON,
	cronExpression TEXT,
	projectId TEXT
);

CREATE TABLE IF NOT EXISTS Job (
	id BIGSERIAL PRIMARY KEY,
	asahTaskId BIGINT NULL,
	createDate TIMESTAMPTZ,
	modifiedDate TIMESTAMPTZ,
	name TEXT,
	runDataPeriod TEXT,
	runFrequency TEXT,
	type TEXT
);