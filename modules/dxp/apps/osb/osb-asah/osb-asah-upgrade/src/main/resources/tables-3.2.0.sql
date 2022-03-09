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

CREATE TABLE IF NOT EXISTS BlockedKeyword (
	id BIGSERIAL PRIMARY KEY,
	createDate TIMESTAMPTZ,
	keyword TEXT
);

CREATE TABLE IF NOT EXISTS CSVIndividual (
	id BIGSERIAL PRIMARY KEY,
	dataSourceId BIGINT,
	dataSourceIndividualPK TEXT,
	fields JSON
);

CREATE TABLE IF NOT EXISTS DataControlTask (
	id BIGSERIAL PRIMARY KEY,
	batchId BIGINT,
	completeDate TIMESTAMPTZ,
	createDate TIMESTAMPTZ,
	emailAddress TEXT,
	ownerId TEXT,
	startDate TIMESTAMPTZ,
	status TEXT,
	type TEXT
);

CREATE TABLE IF NOT EXISTS DataExportTask (
	id BIGSERIAL PRIMARY KEY,
	completedDate TIMESTAMPTZ,
	createDate TIMESTAMPTZ,
	startedDate TIMESTAMPTZ,
	status TEXT,
	type TEXT
);

CREATE TABLE IF NOT EXISTS Experiment (
	id BIGSERIAL PRIMARY KEY,
	channelId BIGINT NOT NULL,
	confidenceLevel DOUBLE PRECISION,
	createDate TIMESTAMPTZ,
	dataSourceId TEXT,
	description TEXT,
	dxpExperienceId TEXT,
	dxpExperienceName TEXT,
	dxpGroupId TEXT,
	dxpLayoutId TEXT,
	dxpSegmentId TEXT,
	dxpSegmentName TEXT,
	finishedDate TIMESTAMPTZ,
	metric TEXT,
	modifiedDate TIMESTAMPTZ,
	name TEXT,
	pageRelativePath TEXT,
	pageTitle TEXT,
	pageURL TEXT,
	processedDate TIMESTAMPTZ,
	publishedDXPVariantId TEXT,
	startedDate TIMESTAMPTZ,
	status TEXT,
	target TEXT,
	type TEXT,
	winnerDXPVariantId TEXT
);

CREATE TABLE IF NOT EXISTS ExperimentMetric (
	id BIGSERIAL PRIMARY KEY,
	experimentId BIGINT,
	completion DOUBLE PRECISION,
	confidenceLevel DOUBLE PRECISION,
	elapsedDays BIGINT,
	estimatedDaysLeft BIGINT,
	processedDate TIMESTAMPTZ
);

CREATE TABLE IF NOT EXISTS ExperimentVariant (
	id BIGSERIAL PRIMARY KEY,
	changes INTEGER,
	control BOOLEAN,
	dxpVariantId TEXT,
	dxpVariantName TEXT,
	experimentId BIGINT,
	trafficSplit DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS ExperimentVariantMetric (
	id BIGSERIAL PRIMARY KEY,
	confidenceInterval REAL[],
	control BOOLEAN,
	dxpVariantId TEXT,
	experimentMetricId BIGINT,
	improvement REAL,
	median REAL,
	probabilityToWin REAL
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