CREATE TABLE IF NOT EXISTS Account (
	id BIGSERIAL PRIMARY KEY,
	accountPK TEXT,
	createDate TIMESTAMPTZ,
	dataSourceId BIGINT,
	modifiedDate TIMESTAMPTZ
);

CREATE TABLE IF NOT EXISTS ActivityGroup (
	id BIGSERIAL PRIMARY KEY,
	activityType TEXT,
	channelId BIGINT,
	dataSourceId BIGINT,
	dayDate TIMESTAMPTZ,
	endDate TIMESTAMPTZ,
	endLocalDateTime TIMESTAMP,
	ownerId BIGINT,
	startDate TIMESTAMPTZ,
	startLocalDateTime TIMESTAMP,
	userId TEXT
);

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

CREATE TABLE IF NOT EXISTS Asset (
	id BIGSERIAL PRIMARY KEY,
	assetType TEXT,
	canonicalURL TEXT,
	channelIds BIGINT[],
	dataSourceAssetPK TEXT,
	dataSourceId BIGINT,
	description TEXT,
	title TEXT,
	url TEXT
);

CREATE TABLE IF NOT EXISTS AssetKeyword (
	assetId BIGINT,
	keyword TEXT,
	type TEXT
);

CREATE TABLE IF NOT EXISTS BlockedEventDefinition (
	eventDefinitionId BIGINT PRIMARY KEY,
	lastSeenDate TIMESTAMPTZ,
	lastSeenURL TEXT
);

CREATE TABLE IF NOT EXISTS BlockedKeyword (
	id BIGSERIAL PRIMARY KEY,
	createDate TIMESTAMPTZ,
	keyword TEXT
);

CREATE TABLE IF NOT EXISTS Channel (
	id BIGSERIAL PRIMARY KEY,
	createDate TIMESTAMPTZ,
	defaultChannel BOOLEAN DEFAULT false,
	name TEXT
);

CREATE TABLE IF NOT EXISTS ChannelDataSource (
	channelId BIGINT,
	dataSourceId BIGINT,
	groupIds BIGINT[],
	PRIMARY KEY (channelId, dataSourceId)
);

CREATE TABLE IF NOT EXISTS CSVIndividual (
	id BIGSERIAL PRIMARY KEY,
	dataSourceId BIGINT,
	dataSourceIndividualPK TEXT,
	fields JSON
);

CREATE TABLE IF NOT EXISTS CustomAssetDashboard (
	id TEXT PRIMARY KEY,
	assetId TEXT,
	assetTitle TEXT,
	category TEXT,
	channelId BIGINT,
	createDate TIMESTAMPTZ,
	dataSourceId BIGINT,
	definition TEXT,
	modifiedByUserId TEXT,
	modifiedByUserName TEXT,
	modifiedDate TIMESTAMPTZ
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

CREATE TABLE IF NOT EXISTS DataSource (
	id BIGSERIAL PRIMARY KEY,
	authorId BIGINT,
	authorName TEXT,
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

CREATE TABLE IF NOT EXISTS DataSourceFieldMapping (
	dataSourceId BIGINT,
	fieldMappingId BIGINT,
	fieldName TEXT,
	PRIMARY KEY (dataSourceId, fieldMappingId)
);

CREATE TABLE IF NOT EXISTS DataSourceOrganization (
	dataSourceId BIGINT,
	enableAllChildren BOOLEAN,
	organizationId BIGINT,
	organizationIds BIGINT[]
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
	userGroupIds BIGINT[]
);

CREATE TABLE IF NOT EXISTS DXPEntity (
	id BIGSERIAL PRIMARY KEY,
	dataSourceId BIGINT,
	fields JSON,
	type TEXT
);

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
	userId TEXT
);

CREATE TABLE IF NOT EXISTS EventAttribute (
	id BIGSERIAL PRIMARY KEY,
	attributeValue VARCHAR(1024),
	eventAttributeDefinitionId BIGINT,
	eventId BIGINT REFERENCES Event
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
	description TEXT,
	displayName TEXT UNIQUE,
	name VARCHAR(255) UNIQUE,
	type TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS EventDefinitionEventAttributeDefinition (
	eventAttributeDefinitionId BIGINT,
	eventDefinitionId BIGINT,
	sampleValue TEXT,
	PRIMARY KEY (eventAttributeDefinitionId, eventDefinitionId)
);

CREATE TABLE IF NOT EXISTS Experiment (
	id BIGSERIAL PRIMARY KEY,
	channelId BIGINT NOT NULL,
	confidenceLevel BIGINT,
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
	completion BIGINT,
	confidenceLevel BIGINT,
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
	trafficSplit BIGINT
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

CREATE TABLE IF NOT EXISTS FieldMapping (
	id BIGSERIAL PRIMARY KEY,
	authorId TEXT,
	authorName TEXT,
	context TEXT,
	createDate TIMESTAMPTZ,
	displayName TEXT,
	displayType TEXT,
	fieldName TEXT,
	fieldType TEXT,
	modifiedDate TIMESTAMPTZ,
	ownerType TEXT,
	strategyConfiguration JSON,
	strategyKey TEXT
);

CREATE TABLE IF NOT EXISTS InterestTopic (
	id BIGSERIAL PRIMARY KEY,
	term TEXT,
	termType TEXT,
	termWeight REAL,
	topic INTEGER,
	topicWeight REAL
);

CREATE TABLE IF NOT EXISTS ItemRecommendation (
	id TEXT PRIMARY KEY,
	itemId TEXT,
	jobId BIGINT,
	recommendedItemIds TEXT[]
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

CREATE TABLE IF NOT EXISTS Membership (
	id BIGSERIAL PRIMARY KEY,
	createDate TIMESTAMPTZ,
	individualId BIGINT,
	individualSegmentId BIGINT,
	modifiedDate TIMESTAMPTZ,
	removedDate TIMESTAMPTZ,
	status TEXT
);

CREATE TABLE IF NOT EXISTS MembershipChange (
	id BIGSERIAL PRIMARY KEY,
	individualDeleted BOOLEAN,
	individualId BIGINT,
	individualEmail TEXT,
	individualName TEXT,
	individualsCount BIGINT,
	individualSegmentId BIGINT,
	joinedDate TIMESTAMPTZ,
	knownIndividualsCount BIGINT,
	modifiedDate TIMESTAMPTZ,
	operation TEXT
);

CREATE TABLE IF NOT EXISTS Organization (
	id BIGSERIAL PRIMARY KEY,
	createDate TIMESTAMPTZ,
	dataSourceId BIGINT,
	modifiedDate TIMESTAMPTZ,
	name TEXT,
	nameTreePath TEXT,
	organizationPK BIGINT,
	parentName TEXT,
	parentOrganizationPK BIGINT,
	treePath TEXT,
	type TEXT
);

CREATE TABLE IF NOT EXISTS Preference (
	id BIGSERIAL PRIMARY KEY,
	key TEXT UNIQUE,
	value TEXT
);

CREATE TABLE IF NOT EXISTS RunLog (
	id BIGSERIAL PRIMARY KEY,
	context JSON,
	dataSourceId BIGINT,
	dateLogged TIMESTAMPTZ,
	naniteClassName TEXT,
	status TEXT
);

CREATE TABLE IF NOT EXISTS SalesforceAuditEvent (
	id BIGSERIAL PRIMARY KEY,
	additionalInfo JSON,
	createDate TIMESTAMPTZ,
	dataSourceId BIGINT,
	entityTypeName TEXT,
	recordId TEXT,
	type TEXT,
	userId TEXT
);

CREATE TABLE IF NOT EXISTS SalesforceEntity (
	id TEXT,
	dataSourceId BIGINT,
	fields JSON,
	type TEXT,
	PRIMARY KEY (dataSourceId, id, type)
);

CREATE TABLE IF NOT EXISTS Segment (
	id BIGSERIAL PRIMARY KEY,
	activeIndividualCount BIGINT,
	activitiesCount BIGINT,
	anonymousIndividualCount BIGINT,
	authorId BIGINT,
	authorName TEXT,
	channelId BIGINT,
	createDate TIMESTAMPTZ,
	filter TEXT,
	filterMetadata TEXT,
	includeAnonymousUsers BOOLEAN,
	individualCount BIGINT,
	knownIndividualCount BIGINT,
	lastActivityDate TIMESTAMPTZ,
	modifiedDate TIMESTAMPTZ,
	name TEXT,
	referencedAssetDataSourceIds BIGINT[],
	referencedAssetIds BIGINT[],
	referencedFieldMappingIds BIGINT[],
	referencedGroupIds BIGINT[],
	referencedOrganizationIds BIGINT[],
	referencedRoleIds BIGINT[],
	referencedTeamIds BIGINT[],
	referencedUserGroupIds BIGINT[],
	referencedUserIds BIGINT[],
	scope TEXT,
	state TEXT,
	status TEXT,
	type TEXT
);

CREATE TABLE IF NOT EXISTS Suppression (
	id BIGSERIAL PRIMARY KEY,
	createDate TIMESTAMPTZ,
	dataControlTaskBatchId BIGINT,
	dataControlTaskCreateDate TIMESTAMPTZ,
	dataControlTaskStatus TEXT,
	emailAddress TEXT,
	emailAddressHashed TEXT
);