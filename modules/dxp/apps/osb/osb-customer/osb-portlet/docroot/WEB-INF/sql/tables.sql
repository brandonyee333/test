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

create table OSB_AccountCustomer (
	accountCustomerId LONG not null primary key,
	userId LONG,
	accountEntryId LONG,
	role INTEGER,
	notifications INTEGER
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
	corpProjectUuid VARCHAR(75) null,
	corpProjectId LONG,
	dossieraAccountKey VARCHAR(75) null,
	corpEntryName VARCHAR(255) null,
	name VARCHAR(500) null,
	code_ VARCHAR(75) null,
	redirectAccountEntryId LONG,
	type_ INTEGER,
	industry INTEGER,
	countryId LONG,
	partnerEntryId LONG,
	partnerManagedSupport BOOLEAN,
	tier INTEGER,
	maxCustomers INTEGER,
	instructions STRING null,
	notes STRING null,
	highestSupportResponseId LONG,
	lastAuditDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	statusMessage STRING null
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

create table OSB_AccountInformation (
	accountInformationId LONG not null primary key,
	modifiedUserId LONG,
	modifiedUserName VARCHAR(75) null,
	modifiedDate DATE null,
	accountEntryId LONG,
	accountProjectId LONG,
	fieldId INTEGER,
	data_ STRING null
);

create table OSB_AccountProject (
	accountProjectId LONG not null primary key,
	modifiedUserId LONG,
	modifiedUserName VARCHAR(75) null,
	modifiedDate DATE null,
	accountEntryId LONG,
	name VARCHAR(75) null
);

create table OSB_AccountWorker (
	accountWorkerId LONG not null primary key,
	userId LONG,
	accountEntryId LONG,
	role INTEGER,
	notifications INTEGER
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
	i18n BOOLEAN
);

create table OSB_CorpProject (
	uuid_ VARCHAR(75) null,
	corpProjectId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossieraProjectKey VARCHAR(75) null,
	salesforceProjectKey VARCHAR(75) null,
	name VARCHAR(75) null,
	organizationId LONG
);

create table OSB_CorpProjectMessage (
	uuid_ VARCHAR(75) null,
	corpProjectMessageId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	corpProjectId LONG,
	type_ INTEGER,
	severityLevel INTEGER,
	title VARCHAR(75) null,
	content VARCHAR(75) null,
	displayCP BOOLEAN,
	displayLCS BOOLEAN
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

create table OSB_LCSSubscriptionEntry (
	lcsSubscriptionEntryId LONG not null primary key,
	lcsProjectId LONG,
	product VARCHAR(75) null,
	productVersion INTEGER,
	type_ VARCHAR(75) null,
	platform VARCHAR(75) null,
	platformVersion VARCHAR(75) null,
	serversAllowed INTEGER,
	serversUsed INTEGER,
	quantity INTEGER,
	instanceSize INTEGER,
	startDate DATE null,
	endDate DATE null,
	supportStartDate DATE null,
	supportEndDate DATE null,
	actualPrice DOUBLE,
	currencyCode VARCHAR(75) null,
	active_ BOOLEAN
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

create table OSB_LicenseKey (
	uuid_ VARCHAR(75) null,
	licenseKeyId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedUserId LONG,
	modifiedUserName VARCHAR(75) null,
	modifiedDate DATE null,
	licenseKeySetId LONG,
	assetReceiptLicenseUuid VARCHAR(75) null,
	accountEntryId LONG,
	orderEntryId LONG,
	offeringEntryId LONG,
	licenseEntryId LONG,
	productEntryId LONG,
	supportResponseId LONG,
	accountEntryName VARCHAR(500) null,
	licenseEntryName VARCHAR(75) null,
	licenseEntryType VARCHAR(75) null,
	licenseVersion INTEGER,
	productEntryName VARCHAR(75) null,
	productId VARCHAR(75) null,
	productVersion INTEGER,
	productVersionLabel VARCHAR(75) null,
	clusterId LONG,
	owner VARCHAR(75) null,
	maxServers INTEGER,
	maxConcurrentUsers LONG,
	maxUsers LONG,
	maxHttpSessions INTEGER,
	sizing INTEGER,
	description VARCHAR(255) null,
	hostName VARCHAR(75) null,
	ipAddresses STRING null,
	macAddresses STRING null,
	serverId STRING null,
	key_ STRING null,
	startDate DATE null,
	expirationDate DATE null,
	additionalInfo STRING null,
	complimentary BOOLEAN,
	active_ BOOLEAN
);

create table OSB_LicenseKeySet (
	licenseKeySetId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accountEntryId LONG,
	name VARCHAR(75) null
);

create table OSB_OfferingBundle (
	offeringBundleId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	name VARCHAR(75) null
);

create table OSB_OfferingBundles_OfferingDefinitions (
	companyId LONG not null,
	offeringBundleId LONG not null,
	offeringDefinitionId LONG not null,
	primary key (offeringBundleId, offeringDefinitionId)
);

create table OSB_OfferingDefinition (
	offeringDefinitionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	productEntryId LONG,
	supportResponseId LONG,
	productDescription VARCHAR(75) null,
	licenses BOOLEAN,
	unlimitedLicenses BOOLEAN,
	maxConcurrentUsers LONG,
	maxUsers LONG,
	supportTickets BOOLEAN
);

create table OSB_OfferingEntry (
	offeringEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accountEntryId LONG,
	orderEntryId LONG,
	productEntryId LONG,
	supportResponseId LONG,
	productDescription VARCHAR(75) null,
	type_ INTEGER,
	version INTEGER,
	platform VARCHAR(75) null,
	platformVersion VARCHAR(75) null,
	licenses BOOLEAN,
	licenseLifetime LONG,
	maxConcurrentUsers LONG,
	maxUsers LONG,
	supportTickets BOOLEAN,
	supportLifetime LONG,
	supportEndDate DATE null,
	sizing INTEGER,
	quantity INTEGER,
	status INTEGER
);

create table OSB_OrderEntry (
	uuid_ VARCHAR(75) null,
	orderEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedUserId LONG,
	modifiedUserName VARCHAR(75) null,
	modifiedDate DATE null,
	accountEntryId LONG,
	purchaseOrderKey VARCHAR(75) null,
	startDate DATE null,
	prorated BOOLEAN,
	actualStartDate DATE null,
	renewCount INTEGER,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	statusMessage STRING null
);

create table OSB_PartnerEntries_SupportRegions (
	companyId LONG not null,
	partnerEntryId LONG not null,
	supportRegionId LONG not null,
	primary key (partnerEntryId, supportRegionId)
);

create table OSB_PartnerEntry (
	partnerEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedUserId LONG,
	modifiedUserName VARCHAR(75) null,
	modifiedDate DATE null,
	parentPartnerEntryId LONG,
	dossieraAccountKey VARCHAR(75) null,
	jiraProjectKey VARCHAR(75) null,
	code_ VARCHAR(255) null,
	notes STRING null,
	status INTEGER
);

create table OSB_PartnerWorker (
	partnerWorkerId LONG not null primary key,
	userId LONG,
	partnerEntryId LONG,
	role INTEGER,
	notifications INTEGER
);

create table OSB_ProductEntry (
	productEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	type_ INTEGER,
	environment INTEGER,
	versionsListType VARCHAR(75) null
);

create table OSB_SecurityPatch (
	securityPatchId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	accountEntryId LONG,
	portletId VARCHAR(75) null,
	envLFR INTEGER,
	name VARCHAR(75) null,
	fileName VARCHAR(75) null
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

create table OSB_SupportResponse (
	supportResponseId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	supportLevel INTEGER,
	severity1Response INTEGER,
	severity1Resolution INTEGER,
	severity2Response INTEGER,
	severity2Resolution INTEGER,
	severity3Response INTEGER,
	severity3Resolution INTEGER
);