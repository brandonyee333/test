CREATE TABLE IF NOT EXISTS BQAccountEntry (
	accountEntryId BIGINT,
	createDate TIMESTAMPTZ,
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
	defaultAccountGroup BOOLEAN,
	description TEXT,
	id TEXT UNIQUE,
	modifiedDate TIMESTAMPTZ,
	name TEXT,
	type TEXT
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
	columnId BIGINT,
	dataSourceId BIGINT,
	dataType TEXT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT
);

CREATE TABLE IF NOT EXISTS BQExpandoValue (
	classPK BIGINT,
	classType TEXT,
	columnId BIGINT,
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

CREATE TABLE IF NOT EXISTS BQMembership (
    createDate TIMESTAMPTZ,
    id BIGSERIAL PRIMARY KEY,
    individualId BIGINT,
    individualSegmentId BIGINT,
    modifiedDate TIMESTAMPTZ,
    removedDate TIMESTAMPTZ,
    status TEXT
);

CREATE TABLE IF NOT EXISTS BQMembershipChange (
    createDate TIMESTAMPTZ,
    id BIGSERIAL PRIMARY KEY,
    individualsCount BIGINT,
    individualSegmentId BIGINT,
    knownIndividualsCount BIGINT
);

CREATE TABLE IF NOT EXISTS BQOrganization (
	dataSourceId BIGINT,
	expandoColumnIds BIGINT[],
	expandoValueIds TEXT[],
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
	expandoColumnIds BIGINT[],
	expandoValueIds TEXT[],
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