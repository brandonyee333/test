CREATE TABLE IF NOT EXISTS ActivityGroup (
	id BIGSERIAL PRIMARY KEY,
	activityType TEXT,
	channelId BIGINT,
	dataSourceId BIGINT,
	dayDate TIMESTAMPTZ,
	endDate TIMESTAMPTZ,
	endLocalDate TIMESTAMPTZ,
	ownerId BIGINT,
	startDate TIMESTAMPTZ,
	startLocalDate TIMESTAMPTZ,
	userId TEXT
);

CREATE TABLE IF NOT EXISTS AsahTask (
	id BIGSERIAL PRIMARY KEY,
	className TEXT,
	context TEXT,
	cronExpression TEXT,
	projectId TEXT
);

CREATE TABLE IF NOT EXISTS BlockedKeyword (
	id BIGSERIAL PRIMARY KEY,
	createDate TIMESTAMPTZ,
	keyword TEXT
);

CREATE TABLE IF NOT EXISTS Channel (
	id BIGSERIAL PRIMARY KEY,
	createDate TIMESTAMPTZ,
	name TEXT
);

CREATE TABLE IF NOT EXISTS ChannelDataSource (
	channelId BIGINT,
	dataSourceId BIGINT,
	groupIds BIGINT [],
	PRIMARY KEY (channelId, dataSourceId)
);

CREATE TABLE IF NOT EXISTS DataExportTask (
	id BIGSERIAL PRIMARY KEY,
	completedDate TIMESTAMPTZ,
	createDate TIMESTAMPTZ,
	startedDate TIMESTAMPTZ,
	status TEXT,
	type TEXT
);

CREATE TABLE IF NOT EXISTS DataSource (
	id BIGSERIAL PRIMARY KEY,
	analyticslastSyncDate TIMESTAMPTZ,
	authorId BIGINT,
	authorName TEXT,
	channelId BIGINT,
	contactsLastSuccessfulAuditEventDate TIMESTAMPTZ,
	contactsLastSyncDate TIMESTAMPTZ,
	contactsSelected BOOLEAN,
	createDate TIMESTAMPTZ,
	credentialType TEXT,
	deletionDate TIMESTAMPTZ,
	enableAllAccounts BOOLEAN,
	enableAllContacts BOOLEAN,
	enableAllLeads BOOLEAN,
	enableAllSites BOOLEAN,
	faroBackendSecuritySignature TEXT,
	login TEXT,
	modifiedDate TIMESTAMPTZ,
	name TEXT,
	oAuthAccessSecret TEXT,
	oAuthAccessToken TEXT,
	oAuthClientId TEXT,
	oAuthClientSecret TEXT,
	oAuthConsumerKey TEXT,
	oAuthConsumerSecret TEXT,
	oAuthOwnerEmailAddress TEXT,
	oAuthOwnerName TEXT,
	oAuthRefreshToken TEXT,
	password TEXT,
	privateKey TEXT,
	providerType TEXT,
	publicKey TEXT,
	sitesSelected BOOLEAN,
	state TEXT,
	status TEXT,
	url TEXT,
	workspaceURL TEXT
);

CREATE TABLE IF NOT EXISTS DataSourceOrganization (
	dataSourceId BIGINT,
	enableAllChildren BOOLEAN,
	organizationId BIGINT,
	organizationIds BIGINT []
);

CREATE TABLE IF NOT EXISTS DataSourceSite (
	dataSourceId BIGINT,
	enableAllChildren BOOLEAN,
	siteId BIGINT
);

CREATE TABLE IF NOT EXISTS DataSourceUserGroup (
	dataSourceId BIGINT,
	enableAllChildren BOOLEAN,
	userGroupId BIGINT,
	userGroupIds BIGINT []
);

CREATE TABLE IF NOT EXISTS Event (
	id BIGSERIAL PRIMARY KEY,
	analyticsEventId TEXT UNIQUE,
	applicationId TEXT,
	channelId BIGINT NOT NULL,
	createDate TIMESTAMPTZ,
	dataSourceId TEXT,
	eventDate TIMESTAMPTZ,
	eventDefinitionId BIGINT,
	userId TEXT
);

CREATE TABLE IF NOT EXISTS EventAttribute (
	id BIGSERIAL PRIMARY KEY,
	attributeValue TEXT,
	eventAttributeDefinitionId BIGINT,
	eventId BIGINT REFERENCES Event
);

CREATE TABLE IF NOT EXISTS EventAttributeDefinition (
	id BIGSERIAL PRIMARY KEY,
	dataType TEXT NOT NULL,
	description TEXT,
	displayName TEXT UNIQUE,
	name TEXT UNIQUE
);

CREATE TABLE IF NOT EXISTS EventDefinition (
	id BIGSERIAL PRIMARY KEY,
	description TEXT,
	displayName TEXT UNIQUE,
	name TEXT UNIQUE,
	type TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS EventDefinitionEventAttributeDefinition (
	eventAttributeDefinitionId BIGINT,
	eventDefinitionId BIGINT,
	sampleValue TEXT,
	PRIMARY KEY (eventAttributeDefinitionId, eventDefinitionId)
);

CREATE TABLE IF NOT EXISTS Field (
	id BIGSERIAL PRIMARY KEY,
	context TEXT,
	dataSourceId BIGINT,
	dataSourceName TEXT,
	fieldType TEXT,
	modifiedDate TIMESTAMPTZ,
	name TEXT,
	ownerId BIGINT,
	ownerType TEXT,
	sourceName TEXT,
	value TEXT
);

CREATE TABLE IF NOT EXISTS Membership (
	id BIGSERIAL PRIMARY KEY,
	createDate TIMESTAMPTZ,
	individualId BIGINT,
	individualSegmentId BIGINT,
	modifiedDate TIMESTAMPTZ,
	removedDate TIMESTAMPTZ,
	status TEXT
);

CREATE TABLE IF NOT EXISTS Preference (
	id BIGSERIAL PRIMARY KEY,
	key TEXT UNIQUE,
	value TEXT
);