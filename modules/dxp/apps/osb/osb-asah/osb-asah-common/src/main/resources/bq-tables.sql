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
	dataType TEXT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT
);

CREATE TABLE IF NOT EXISTS BQExpandoValue (
	classPK BIGINT,
	classType TEXT,
	columnId BIGINT,
	entityId BIGINT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	value TEXT
);

CREATE TABLE IF NOT EXISTS BQGroup (
	groupId BIGINT,
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT
);


CREATE TABLE IF NOT EXISTS BQIdentity (
	channelId BIGINT,
	createDate TIMESTAMP,
	dataSourceId BIGINT,
	emailAddressHashed TEXT,
	id TEXT PRIMARY KEY,
	userId TEXT
);

CREATE TABLE IF NOT EXISTS BQMembership (
    id BIGSERIAL PRIMARY KEY,
    createDate TIMESTAMPTZ,
    individualId BIGINT,
    individualSegmentId BIGINT,
    modifiedDate TIMESTAMPTZ,
    removedDate TIMESTAMPTZ,
    status TEXT
);

CREATE TABLE IF NOT EXISTS BQMembershipChange (
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

CREATE TABLE IF NOT EXISTS BQOrganization (
	expandoColumnIds BIGINT[],
	expandoValueIds TEXT[],
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT,
	organizationId BIGINT,
	organizationType TEXT,
	parentOrganizationId BIGINT,
	treePath TEXT
);

CREATE TABLE IF NOT EXISTS BQRole (
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
	userId BIGINT,
	userName TEXT,
	uuid TEXT
);

CREATE TABLE IF NOT EXISTS BQUserGroup (
	id TEXT PRIMARY KEY,
	modifiedDate TIMESTAMP,
	name TEXT,
	userGroupId BIGINT
);