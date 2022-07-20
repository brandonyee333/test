CREATE TABLE IF NOT EXISTS BQAccountEntry (
	accountEntryId BIGINT,
	createDate TIMESTAMPTZ,
	dataSourceId BIGINT,
	defaultCPaymentMethodKey TEXT,
	description TEXT,
	domains TEXT,
	emailAddress TEXT,
	id TEXT UNIQUE,
	logoId BIGINT,
	modifiedDate TIMESTAMPTZ,
	name TEXT,
	parentAccountEntryId BIGINT,
	status INTEGER,
	taxExemptionCode TEXT,
	taxIdNumber TEXT,
	type TEXT
);

CREATE TABLE IF NOT EXISTS BQAccountGroup (
	accountGroupId BIGINT,
	createDate TIMESTAMPTZ,
	dataSourceId BIGINT,
	defaultAccountGroup BOOLEAN,
	description TEXT,
	id TEXT UNIQUE,
	modifiedDate TIMESTAMPTZ,
	name TEXT,
	type TEXT
);

CREATE TABLE IF NOT EXISTS Blog (
	assetId VARCHAR,
	assetPrimaryKey VARCHAR,
	browserName VARCHAR,
	canonicalUrl VARCHAR,
	channelId BIGINT,
	city VARCHAR,
	clicks BIGINT,
	comments BIGINT,
	country VARCHAR,
	dataSourceId BIGINT,
	deviceType VARCHAR,
	eventDate TIMESTAMP,
	individualId BIGINT,
	knownIndividual BOOLEAN,
	platformName VARCHAR,
	projectId VARCHAR,
	ratings BIGINT,
	ratingsScore REAL,
	readPercentile BIGINT,
	readTime BIGINT,
	region VARCHAR,
	segmentNames ARRAY<VARCHAR>,
	sessionId VARCHAR,
	title VARCHAR,
	userId VARCHAR,
	variantId VARCHAR,
	views BIGINT
);

CREATE TABLE IF NOT EXISTS BQCSVUser (
    id BIGSERIAL PRIMARY KEY,
    dataSourceId BIGINT,
    dataSourceUserPK TEXT,
    fields JSON
);

CREATE TABLE IF NOT EXISTS CustomAsset (
	abandonments BIGINT,
	assetId VARCHAR,
	assetPrimaryKey VARCHAR,
	category VARCHAR,
	channelId BIGINT,
	clicks BIGINT,
	downloads BIGINT,
	eventDate TIMESTAMP,
	projectId VARCHAR,
	readTime BIGINT,
	submissionTime BIGINT,
	submissions BIGINT,
	userId VARCHAR,
	variantId VARCHAR,
	views BIGINT
);

CREATE TABLE IF NOT EXISTS BQDataSourceUser (
    accountPKs TEXT[],
    dataSourceId BIGINT,
    userId BIGINT,
    userPKs TEXT[],
    PRIMARY KEY (dataSourceId, userId)
);

CREATE TABLE IF NOT EXISTS DocumentLibrary (
	assetId VARCHAR,
	assetPrimaryKey VARCHAR,
	browserName VARCHAR,
	canonicalUrl VARCHAR,
	channelId BIGINT,
	city VARCHAR,
	comments BIGINT,
	country VARCHAR,
	dataSourceId BIGINT,
	deviceType VARCHAR,
	downloads BIGINT,
	eventDate TIMESTAMP,
	individualId BIGINT,
	knownIndividual BOOLEAN,
	platformName VARCHAR,
	previews BIGINT,
	projectId VARCHAR,
	ratings BIGINT,
	ratingsScore REAL,
	region VARCHAR,
	segmentNames ARRAY<VARCHAR>,
	sessionId VARCHAR,
	title VARCHAR,
	userId VARCHAR,
	variantId VARCHAR
);

CREATE TABLE IF NOT EXISTS BQEvent (
	applicationId TEXT,
	browserName TEXT,
	canonicalUrl TEXT,
	channelId BIGINT,
	city TEXT,
	contentLanguageId TEXT,
	context TEXT,
	country TEXT,
	createDate TIMESTAMPTZ,
	dataSourceId BIGINT,
	description TEXT,
	deviceType TEXT,
	eventDate TIMESTAMPTZ,
	eventId TEXT,
	eventProperties TEXT,
	experienceId TEXT,
	id TEXT UNIQUE,
	keywords TEXT,
	languageId TEXT,
	platformName TEXT,
	projectTimeZoneId TEXT,
	referrer TEXT,
	region TEXT,
	sessionId TEXT,
	timezoneOffset TEXT,
	title TEXT,
	url TEXT,
	userId TEXT,
	variantId TEXT
);

CREATE TABLE IF NOT EXISTS BQEventProperty (
	channelId BIGINT,
	eventDate TIMESTAMPTZ,
	id TEXT,
	name TEXT,
	projectId TEXT,
	value TEXT
);

CREATE TABLE IF NOT EXISTS BQExpandoColumn (
	columnId TEXT,
	dataSourceId BIGINT,
	dataType TEXT,
	displayType TEXT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT
);

CREATE TABLE IF NOT EXISTS BQExpandoValue (
	classPK BIGINT,
	classType TEXT,
	columnId TEXT,
	dataSourceId BIGINT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	value TEXT
);

CREATE TABLE IF NOT EXISTS Form (
	abandonments BIGINT,
	assetId VARCHAR,
	assetPrimaryKey VARCHAR,
	browserName VARCHAR,
	canonicalUrl VARCHAR,
	channelId BIGINT,
	city VARCHAR,
	country VARCHAR,
	dataSourceId BIGINT,
	deviceType VARCHAR,
	eventDate TIMESTAMP,
	individualId BIGINT,
	knownIndividual BOOLEAN,
	platformName VARCHAR,
	projectId VARCHAR,
	region VARCHAR,
	segmentNames ARRAY<VARCHAR>,
	sessionId VARCHAR,
	submissions BIGINT,
	submissionsTime BIGINT,
	title VARCHAR,
	userId VARCHAR,
	variantId VARCHAR,
	views BIGINT
);

CREATE TABLE IF NOT EXISTS BQGroup (
	dataSourceId BIGINT,
	groupId BIGINT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT
);

CREATE TABLE IF NOT EXISTS BQIdentity (
	createDate TIMESTAMP,
	emailAddressHashed TEXT,
	id TEXT PRIMARY KEY,
	userId TEXT
);

CREATE TABLE IF NOT EXISTS BQIdentityActivity (
	channelId BIGINT,
	createDate TIMESTAMP,
	dataSourceId BIGINT,
	id TEXT PRIMARY KEY,
	identityId TEXT
);

CREATE TABLE IF NOT EXISTS BQIdentityChannel (
    activitiesCount INTEGER,
    channelId BIGINT,
    identityId BIGINT,
    lastActivityDate TIMESTAMPTZ,
    previousActivityDate TIMESTAMPTZ,
    PRIMARY KEY (channelId, identityId)
);

CREATE TABLE IF NOT EXISTS BQMembership (
	createDate TIMESTAMPTZ,
	id BIGSERIAL PRIMARY KEY,
	identityId TEXT,
	modifiedDate TIMESTAMPTZ,
	removedDate TIMESTAMPTZ,
	segmentId BIGINT,
	status TEXT
);

CREATE TABLE IF NOT EXISTS BQMembershipChange (
	createDate TIMESTAMPTZ,
	id BIGSERIAL PRIMARY KEY,
	identitiesCount BIGINT,
	knownIdentitiesCount BIGINT,
	segmentId BIGINT
);

CREATE TABLE IF NOT EXISTS BQOrder(
	accountId BIGINT,
	channelId BIGINT,
	createDate TIMESTAMP,
	currencyCode TEXT,
	dataSourceId BIGINT,
	externalReferenceCode TEXT,
	id BIGINT,
	modifiedDate TIMESTAMP,
	orderDate TIMESTAMP,
	orderItems TEXT[],
	orderStatus BIGINT,
	orderTypeExternalReferenceCode TEXT,
	orderTypeId BIGINT,
	paymentMethod TEXT,
	paymentStatus BIGINT,
	projectId TEXT,
	status BIGINT,
	total TEXT,
	uploadDate TIMESTAMP,
	uploadType TEXT,
	userId BIGINT
);

CREATE TABLE IF NOT EXISTS BQOrganization (
	dataSourceId BIGINT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT,
	organizationId BIGINT,
	organizationType TEXT,
	parentOrganizationId BIGINT,
	parentOrganizationName TEXT,
	treePath TEXT
);

CREATE TABLE IF NOT EXISTS BQRole (
	dataSourceId BIGINT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT,
	roleId BIGINT
);

CREATE TABLE IF NOT EXISTS BQSalesforceAuditEvent (
    id BIGSERIAL PRIMARY KEY,
    additionalInfo JSON,
    createDate TIMESTAMPTZ,
    dataSourceId BIGINT,
    entityTypeName TEXT,
    recordId TEXT,
    type TEXT,
    userId TEXT
);

CREATE TABLE IF NOT EXISTS BQSalesforceEntity (
    id TEXT,
    dataSourceId BIGINT,
    fields JSON,
    type TEXT,
    PRIMARY KEY (dataSourceId, id, type)
);

CREATE TABLE IF NOT EXISTS BQSession (
	channelId BIGINT,
	id TEXT UNIQUE,
	sessionEnd TIMESTAMPTZ,
	sessionStart TIMESTAMPTZ
);

CREATE TABLE IF NOT EXISTS BQTeam (
	dataSourceId BIGINT,
	groupId BIGINT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT,
	teamId BIGINT
);

CREATE TABLE IF NOT EXISTS BQUser (
	accountId BIGINT,
	birthday TIMESTAMP,
	classNameId BIGINT,
	classPK BIGINT,
	dataSourceId BIGINT,
	dxpUserId BIGINT,
	contactId BIGINT,
	createDate TIMESTAMP,
	emailAddress TEXT,
	firstName TEXT,
	groupIds BIGINT[],
	id TEXT PRIMARY KEY,
	jobTitle TEXT,
	languageId TEXT,
	lastName TEXT,
	male BOOLEAN,
	middleName TEXT,
	modifiedDate TIMESTAMP,
	organizationIds BIGINT[],
	parentContactId BIGINT,
	prefixId TEXT,
	roleIds BIGINT[],
	screenName TEXT,
	suffixId TEXT,
	teamIds BIGINT[],
	timeZoneId TEXT,
	userGroupIds BIGINT[],
	userName TEXT,
	uuid TEXT
);

CREATE TABLE IF NOT EXISTS BQUserGroup (
	dataSourceId BIGINT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT,
	userGroupId BIGINT
);
