create table OSB_AccountAttachment (
	accountAttachmentId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	accountEntryId LONG,
	accountProjectId LONG,
	fileName VARCHAR(75) null,
	fileSize LONG,
	type_ INTEGER
);

create table OSB_AccountEntries_SupportRegions (
	companyId LONG not null,
	accountEntryId LONG not null,
	supportRegionId LONG not null,
	primary key (accountEntryId, supportRegionId)
);

create table OSB_AccountEntry (
	accountEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedUserId LONG,
	modifiedUserName VARCHAR(75) null,
	modifiedDate DATE null,
	koroneikiAccountKey VARCHAR(75) null,
	dossieraAccountKey VARCHAR(75) null,
	name VARCHAR(500) null,
	code_ VARCHAR(75) null,
	instructions STRING null,
	activeSupport BOOLEAN,
	activeTicketSupport BOOLEAN,
	lastZendeskAuditDate DATE null,
	status INTEGER
);

create table OSB_AccountEntryLanguage (
	accountEntryLanguageId LONG not null primary key,
	accountEntryId LONG,
	languageId VARCHAR(75) null
);

create table OSB_AccountEnvironment (
	accountEnvironmentId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accountEntryId LONG,
	productEntryId LONG,
	name VARCHAR(75) null,
	envOS INTEGER,
	envOSCustom VARCHAR(150) null,
	envDB INTEGER,
	envJVM INTEGER,
	envAS INTEGER,
	envLFR INTEGER,
	envCommerce INTEGER,
	envBrowser INTEGER,
	envCS INTEGER,
	envSearch VARCHAR(75) null
);

create table OSB_AccountEnvironmentAttachment (
	accountEnvironmentAttachmentId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accountEnvironmentId LONG,
	fileName VARCHAR(75) null,
	fileSize LONG,
	type_ INTEGER
);

create table OSB_AuditEntry (
	auditEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	classNameId LONG,
	classPK LONG,
	previousAuditEntryId LONG,
	auditSetId LONG,
	fieldClassNameId LONG,
	fieldClassPK LONG,
	action INTEGER,
	field INTEGER,
	visibility INTEGER,
	oldLabel VARCHAR(255) null,
	oldValue STRING null,
	newLabel VARCHAR(255) null,
	newValue STRING null,
	description STRING null,
	i18n BOOLEAN
);

create table OSB_ExternalIdMapper (
	externalIdMapperId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	type_ INTEGER,
	externalId VARCHAR(75) null
);

create table OSB_LicenseEntry (
	licenseEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	productEntryId LONG,
	name VARCHAR(75) null,
	type_ VARCHAR(75) null,
	portalVersionMin INTEGER,
	portalVersionMax INTEGER
);

create table OSB_ProductEntry (
	productEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	koroneikiProductKey VARCHAR(75) null,
	name VARCHAR(75) null,
	environment INTEGER,
	licenses BOOLEAN,
	versionsListType VARCHAR(75) null
);

create table OSB_SupportRegion (
	supportRegionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	timeZoneId VARCHAR(75) null,
	managerUserId LONG
);