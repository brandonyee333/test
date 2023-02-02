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

CREATE TABLE IF NOT EXISTS BQCSVUser (
	id BIGSERIAL PRIMARY KEY,
	dataSourceId BIGINT,
	dataSourceUserPK TEXT,
	emailAddress TEXT,
	fields JSON,
	modifiedDate TIMESTAMPTZ
);

CREATE TABLE IF NOT EXISTS BQDataSourceUser (
	accountPKs TEXT[],
	dataSourceId BIGINT,
	userId BIGINT,
	userPKs TEXT[],
	PRIMARY KEY (dataSourceId, userId)
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
	className TEXT,
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

CREATE TABLE IF NOT EXISTS BQGroup (
	dataSourceId BIGINT,
	groupId BIGINT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT
);

CREATE TABLE IF NOT EXISTS BQIdentity (
	createDate TIMESTAMP,
	id TEXT PRIMARY KEY,
	individualId TEXT
);

CREATE TABLE IF NOT EXISTS BQIdentityInterestScore (
	id BIGSERIAL PRIMARY KEY,
	identityId TEXT,
	interested BOOLEAN,
	interestScore DOUBLE PRECISION,
	keyword TEXT,
	recordedDate TIMESTAMPTZ
);

CREATE TABLE IF NOT EXISTS BQIndividual (
	createDate TIMESTAMP,
	emailAddress TEXT,
	fields JSON,
	firstName TEXT,
	id TEXT PRIMARY KEY,
	jobTitle TEXT,
	lastName TEXT,
	middleName TEXT,
	modifiedDate TIMESTAMPTZ,
	screenName TEXT
);

CREATE TABLE IF NOT EXISTS BQMembership (
	createDate TIMESTAMPTZ,
	id BIGSERIAL PRIMARY KEY,
	individualId TEXT,
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
	createDate TIMESTAMPTZ,
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

CREATE TABLE IF NOT EXISTS BQSession (
	acquisitionCampaign TEXT,
	acquisitionChannel TEXT,
	acquisitionContent TEXT,
	acquisitionMedium TEXT,
	acquisitionSource TEXT,
	acquisitionTerm TEXT,
	bounce INTEGER,
	browserName TEXT,
	channelId BIGINT,
	city TEXT,
	country TEXT,
	deviceType TEXT,
	duration BIGINT,
	id TEXT UNIQUE,
	platformName TEXT,
	referrers TEXT[],
	region TEXT,
	sessionEnd TIMESTAMPTZ,
	sessionStart TIMESTAMPTZ,
	urls TEXT[],
	userId TEXT
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
	dataSourceId BIGINT,
	dxpUserId BIGINT,
	emailAddress TEXT,
    emailAddressHashed TEXT,
	fields json,
	firstName TEXT,
	id TEXT PRIMARY KEY,
    jobTitle TEXT,
	lastName TEXT,
	middleName TEXT,
	modifiedDate TIMESTAMP,
	screenName TEXT,
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