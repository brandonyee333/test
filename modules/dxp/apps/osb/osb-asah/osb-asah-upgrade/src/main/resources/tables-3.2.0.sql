CREATE TABLE IF NOT EXISTS AsahMarker (
	id VARCHAR(50) PRIMARY KEY,
	context JSON
);


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

CREATE TABLE IF NOT EXISTS JobParameter (
	jobId BIGINT,
	name TEXT,
	value TEXT,
	PRIMARY KEY (jobId, name)
);

CREATE TABLE IF NOT EXISTS JobRun (
	id BIGSERIAL PRIMARY KEY,
	completedDate TIMESTAMPTZ,
	context JSON,
	createDate TIMESTAMPTZ,
	jobId BIGINT,
	jobType TEXT,
	modifiedDate TIMESTAMPTZ,
	status TEXT,
	step TEXT,
	trigger TEXT
);