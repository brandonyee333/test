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

CREATE TABLE IF NOT EXISTS EventDefinition (
	id BIGSERIAL PRIMARY KEY,
	description TEXT,
	displayName TEXT UNIQUE,
	name TEXT UNIQUE,
	type TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Event (
	id BIGSERIAL PRIMARY KEY,
	analyticsEventId TEXT UNIQUE,
	applicationId TEXT,
	channelId BIGINT NOT NULL,
	createDate TIMESTAMPTZ,
	dataSourceId TEXT,
	eventDate TiMESTAMPTZ,
	eventDefinitionId BIGINT,
	userId TEXT
);

CREATE TABLE IF NOT EXISTS EventAttributeDefinition (
	id BIGSERIAL PRIMARY KEY,
	dataType TEXT NOT NULL,
	description TEXT,
	displayName TEXT UNIQUE,
	name TEXT UNIQUE
);

CREATE TABLE IF NOT EXISTS EventDefinitionEventAttributeDefinition (
	eventAttributeDefinitionId BIGINT,
	eventDefinitionId BIGINT,
	PRIMARY KEY (eventAttributeDefinitionId, eventDefinitionId)
);

CREATE TABLE IF NOT EXISTS EventAttribute (
	id BIGSERIAL PRIMARY KEY,
	attributeValue TEXT,
	eventAttributeDefinitionId BIGINT,
	eventId BIGINT REFERENCES Event
);

CREATE TABLE IF NOT EXISTS Preference (
	id BIGSERIAL PRIMARY KEY,
	key TEXT UNIQUE,
	value TEXT
);