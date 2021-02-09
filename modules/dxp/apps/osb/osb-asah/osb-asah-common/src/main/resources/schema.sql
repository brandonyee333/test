CREATE TABLE IF NOT EXISTS Channel (
	id BIGSERIAL PRIMARY KEY,
	createDate TIMESTAMPTZ,
	name TEXT
);

CREATE TABLE IF NOT EXISTS ChannelDataSource (
	channelId BIGINT REFERENCES Channel ON DELETE CASCADE,
	dataSourceId BIGINT,
	groupIds BIGINT [],
	PRIMARY KEY (channelId, dataSourceId)
);

CREATE TABLE IF NOT EXISTS DataSource (
	id BIGSERIAL PRIMARY KEY,
	analyticslastSyncDate TIMESTAMPTZ,
	authorId BIGINT,
	authorName TEXT,
	channelId BIGINT REFERENCES Channel ON DELETE CASCADE,
	contactsLastSuccessfulAuditEventDate TIMESTAMPTZ,
	contactsLastSyncDate TIMESTAMPTZ,
	contactsSelected BOOLEAN,
	createDate TIMESTAMPTZ,
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
	type TEXT,
	url TEXT,
	workspaceURL TEXT
);

CREATE TABLE IF NOT EXISTS DataSourceOrganization (
	dataSourceId BIGINT REFERENCES DataSource ON DELETE CASCADE,
	enableAllChildren BOOLEAN,
	organizationId BIGINT,
	organizationIds BIGINT []
);

CREATE TABLE IF NOT EXISTS DataSourceSite (
	dataSourceId BIGINT REFERENCES DataSource ON DELETE CASCADE,
	enableAllChildren BOOLEAN,
	siteId BIGINT
);

CREATE TABLE IF NOT EXISTS DataSourceUserGroup (
	dataSourceId BIGINT REFERENCES DataSource ON DELETE CASCADE,
	enableAllChildren BOOLEAN,
	userGroupId BIGINT,
	userGroupIds BIGINT []
);

CREATE TABLE IF NOT EXISTS Preference (
	id BIGSERIAL PRIMARY KEY,
	key TEXT UNIQUE,
	value TEXT
);