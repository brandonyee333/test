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

create table OSB_AccountCall (
	accountCallId LONG not null primary key,
	createDate DATE null,
	modifiedUserId LONG,
	modifiedUserName VARCHAR(75) null,
	modifiedDate DATE null,
	accountEntryId LONG,
	type_ INTEGER,
	callDate DATE null,
	callLength LONG,
	summary STRING null,
	clientsPresent STRING null,
	notes STRING null,
	actionItems STRING null
);

create table OSB_AccountCustomer (
	accountCustomerId LONG not null primary key,
	userId LONG,
	accountEntryId LONG,
	role INTEGER,
	notifications INTEGER
);

create table OSB_AccountEntries_SupportRegions (
	accountEntryId LONG not null,
	supportRegionId LONG not null,
	primary key (accountEntryId, supportRegionId)
);

create table OSB_AccountEntries_SupportTeams (
	accountEntryId LONG not null,
	supportTeamId LONG not null,
	primary key (accountEntryId, supportTeamId)
);

create table OSB_AccountEntry (
	accountEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedUserId LONG,
	modifiedUserName VARCHAR(75) null,
	modifiedDate DATE null,
	corpProjectId LONG,
	corpEntryName VARCHAR(75) null,
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
	envLFR INTEGER
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

create table OSB_AccountLink (
	accountLinkId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	accountEntryId LONG,
	url STRING null
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

create table OSB_AppAudit (
	uuid_ VARCHAR(75) null,
	appAuditId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	appEntryId LONG,
	appVersionId LONG,
	status INTEGER
);

create table OSB_AppEntry (
	uuid_ VARCHAR(75) null,
	appEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	developerEntryId LONG,
	developerName VARCHAR(75) null,
	title VARCHAR(75) null,
	description STRING null,
	website STRING null,
	demoWebsite STRING null,
	documentationWebsite STRING null,
	referenceWebsite STRING null,
	sourceCodeWebsite STRING null,
	supportWebsite STRING null,
	labs BOOLEAN,
	productType INTEGER,
	version VARCHAR(20) null,
	changeLog STRING null,
	iconImageId LONG,
	paclEnabled BOOLEAN,
	size_ LONG,
	downloadCount INTEGER,
	licenseType INTEGER,
	licenseLifetime LONG,
	supported BOOLEAN,
	orderURL STRING null,
	hidden_ BOOLEAN,
	portalRequired BOOLEAN,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	statusVersionDate DATE null
);

create table OSB_AppEntryRel (
	uuid_ VARCHAR(75) null,
	appEntryRelId LONG not null primary key,
	appEntryId1 LONG,
	appEntryId2 LONG,
	type_ INTEGER
);

create table OSB_AppFlag (
	uuid_ VARCHAR(75) null,
	appFlagId LONG not null primary key,
	createDate DATE null,
	appEntryId LONG,
	appVersionId LONG,
	type_ INTEGER
);

create table OSB_AppPackage (
	appPackageId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	appEntryId LONG,
	appVersionId LONG,
	compatibility INTEGER,
	compatibilityPlus BOOLEAN,
	prepackaged BOOLEAN,
	downloadCount INTEGER
);

create table OSB_AppPackagePlugin (
	appPackagePluginId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	appEntryId LONG,
	appVersionId LONG,
	appPackageId LONG,
	assetAttachmentId LONG,
	fileName VARCHAR(255) null,
	bundleSymbolicName VARCHAR(500) null,
	bundleVersion VARCHAR(75) null,
	contextName VARCHAR(255) null,
	paclEnabled BOOLEAN,
	relengHash VARCHAR(75) null,
	portalRestartRequired BOOLEAN
);

create table OSB_AppPricing (
	appPricingId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	appEntryId LONG,
	appVersionId LONG,
	name VARCHAR(75) null,
	currencyEntryId LONG,
	standardSupportPrice DOUBLE,
	developerSupportPrice DOUBLE,
	rank INTEGER
);

create table OSB_AppPricingItem (
	appPricingItemId LONG not null primary key,
	appPricingId LONG,
	assetLicenseId LONG,
	currencyEntryId LONG,
	price DOUBLE
);

create table OSB_AppVersion (
	appVersionId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	appEntryId LONG,
	developerEntryId LONG,
	developerName VARCHAR(75) null,
	title VARCHAR(75) null,
	description STRING null,
	website STRING null,
	demoWebsite STRING null,
	documentationWebsite STRING null,
	referenceWebsite STRING null,
	sourceCodeWebsite STRING null,
	supportWebsite STRING null,
	labs BOOLEAN,
	productType INTEGER,
	version VARCHAR(20) null,
	versionId INTEGER,
	versionOrder INTEGER,
	changeLog STRING null,
	iconImageId LONG,
	size_ LONG,
	downloadCount INTEGER,
	paclEnabled BOOLEAN,
	releaseDate DATE null,
	releaseType INTEGER,
	contractEntryId LONG,
	supported BOOLEAN,
	orderURL STRING null,
	hidden_ BOOLEAN,
	portalRequired BOOLEAN,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	statusMessage STRING null
);

create table OSB_AssetAttachment (
	assetAttachmentId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	classNameId LONG,
	classPK LONG,
	fileName VARCHAR(255) null,
	type_ INTEGER,
	rank INTEGER
);

create table OSB_AssetAudit (
	assetAuditId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	legalEntityName VARCHAR(75) null,
	classNameId LONG,
	classPK LONG,
	vendorClassNameId LONG,
	vendorClassPK LONG,
	type_ INTEGER,
	domain INTEGER,
	time_ LONG,
	currencyCode VARCHAR(75) null,
	price DOUBLE
);

create table OSB_AssetLicense (
	assetLicenseId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	licenseId VARCHAR(75) null,
	name VARCHAR(75) null,
	requiredVersion DOUBLE,
	usageType INTEGER,
	licenseType INTEGER,
	licenseTypeAllotment LONG,
	lifetime LONG,
	status INTEGER
);

create table OSB_AssetList (
	assetListId LONG not null primary key,
	assetCategoryId LONG,
	type_ INTEGER
);

create table OSB_AssetListAssetEntry (
	assetListAssetEntryId LONG not null primary key,
	assetListId LONG,
	assetEntryId LONG
);

create table OSB_AssetReceipt (
	assetReceiptId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	assetEntryId LONG,
	ownerClassNameId LONG,
	ownerClassPK LONG,
	legalEntityName VARCHAR(75) null,
	productClassNameId LONG,
	productClassPK LONG,
	type_ LONG,
	currencyEntryId LONG,
	actualPrice DOUBLE
);

create table OSB_AssetReceiptLicense (
	uuid_ VARCHAR(75) null,
	assetReceiptLicenseId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	assetReceiptId LONG,
	assetLicenseId LONG,
	assetEntryId LONG,
	ownerClassNameId LONG,
	ownerClassPK LONG,
	productClassNameId LONG,
	productClassPK LONG,
	productId VARCHAR(75) null,
	startDate DATE null,
	endDate DATE null,
	usageType INTEGER,
	licenseType INTEGER,
	licenseTypeAllotment LONG,
	licenseLifetime LONG
);

create table OSB_AssetReceiptRedeemToken (
	AssetReceiptRedeemTokenId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	classNameId LONG,
	classPK LONG,
	redeemEmailAddress VARCHAR(75) null,
	redeemDate DATE null,
	token VARCHAR(75) null
);

create table OSB_AssetReceiptSupport (
	uuid_ VARCHAR(75) null,
	assetReceiptSupportId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	assetReceiptId LONG,
	assetLicenseId LONG,
	assetEntryId LONG,
	ownerClassNameId LONG,
	ownerClassPK LONG,
	productClassNameId LONG,
	productClassPK LONG,
	productId VARCHAR(75) null,
	startDate DATE null,
	endDate DATE null,
	usageType INTEGER,
	supportLifetime LONG
);

create table OSB_AssetRecommendationEntry (
	assetRecommendationEntryId LONG not null primary key,
	assetRecommendationSetId LONG,
	classNameId LONG,
	classPK LONG,
	viewedAlsoPurchasedCount INTEGER,
	purchasedAlsoPurchasedCount INTEGER
);

create table OSB_AssetRecommendationSet (
	assetRecommendationSetId LONG not null primary key,
	classNameId LONG,
	classPK LONG
);

create table OSB_AssetStatsDay (
	assetStatsDayId LONG not null primary key,
	classNameId LONG,
	classPK LONG,
	day INTEGER,
	year INTEGER,
	viewCount LONG,
	downloadCount LONG,
	purchaseCount LONG,
	currencyCodeRevenues VARCHAR(75) null
);

create table OSB_AssetStatsMonth (
	assetStatsMonthId LONG not null primary key,
	classNameId LONG,
	classPK LONG,
	month INTEGER,
	year INTEGER,
	viewCount LONG,
	downloadCount LONG,
	purchaseCount LONG,
	currencyCodeRevenues VARCHAR(75) null
);

create table OSB_AssetStatsWeek (
	assetStatsWeekId LONG not null primary key,
	classNameId LONG,
	classPK LONG,
	week INTEGER,
	year INTEGER,
	viewCount LONG,
	downloadCount LONG,
	purchaseCount LONG,
	currencyCodeRevenues VARCHAR(75) null
);

create table OSB_AuditAction (
	auditActionId LONG not null primary key,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	mappingClassPK LONG,
	action INTEGER
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

create table OSB_ContractAudit (
	contractAuditId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	userEmailAddress VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	contractEntryId LONG,
	signatoryClassNameId LONG,
	signatoryClassPK LONG,
	productClassNameId LONG,
	productClassPK LONG,
	type_ VARCHAR(75) null,
	languageId VARCHAR(75) null,
	version INTEGER
);

create table OSB_ContractEntry (
	contractEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	classNameId LONG,
	classPK LONG,
	type_ INTEGER,
	version INTEGER,
	content STRING null
);

create table OSB_CorpEntry (
	corpEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description STRING null,
	organizationId LONG,
	logoId LONG,
	addressId LONG,
	contactEmailAddress VARCHAR(75) null,
	profileEmailAddress VARCHAR(75) null,
	phoneNumber VARCHAR(75) null,
	faxNumber VARCHAR(75) null,
	website VARCHAR(75) null,
	dossieraAccountKey VARCHAR(75) null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	statusMessage STRING null
);

create table OSB_CorpEntry_CorpGroup (
	corpEntryId LONG not null,
	corpGroupId LONG not null,
	primary key (corpEntryId, corpGroupId)
);

create table OSB_CorpGroup (
	corpGroupId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description STRING null,
	organizationId LONG,
	logoId LONG,
	emailAddress VARCHAR(75) null,
	website VARCHAR(75) null
);

create table OSB_CorpMembershipRequest (
	corpMembershipRequestId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	corpEntryId LONG,
	key_ VARCHAR(75) null,
	emailAddress VARCHAR(75) null,
	status INTEGER
);

create table OSB_CorpProject (
	corpProjectId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	dossieraProjectKey VARCHAR(75) null,
	salesforceProjectKey VARCHAR(75) null,
	name VARCHAR(150) null,
	organizationId LONG
);

create table OSB_CorpProjectMessage (
	corpProjectMessageId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	corpProjectId LONG,
	type_ INTEGER,
	severityLevel INTEGER,
	title VARCHAR(150) null,
	content STRING null,
	displayCP BOOLEAN,
	displayLCS BOOLEAN,
	displayLESA BOOLEAN
);

create table OSB_CountryAppPricing (
	countryAppPricingId LONG not null primary key,
	appEntryId LONG,
	appVersionId LONG,
	appPricingId LONG,
	countryId LONG,
	name VARCHAR(75) null
);

create table OSB_CurrencyEntry (
	currencyEntryId LONG not null primary key,
	countryId LONG,
	currencyCode VARCHAR(75) null,
	marketplaceEnabled BOOLEAN,
	marketplaceMinPrice DOUBLE
);

create table OSB_DeveloperEntry (
	developerEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	screenName VARCHAR(75) null,
	firstName VARCHAR(75) null,
	middleName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	legalEntityName VARCHAR(75) null,
	emailAddress VARCHAR(75) null,
	contractEntryId LONG,
	phoneNumber VARCHAR(75) null,
	faxNumber VARCHAR(75) null,
	domainName VARCHAR(75) null,
	domainStatus INTEGER,
	addressId LONG,
	countryId LONG,
	profileDescription STRING null,
	profileEmailAddress VARCHAR(75) null,
	profileLogoId LONG,
	profileWebsite VARCHAR(75) null,
	paymentEmailAddress VARCHAR(75) null,
	googleAnalyticsKey VARCHAR(75) null,
	subscriptionExpirationDate DATE null,
	subscriptionStatus INTEGER,
	fatcaWithholdingPercentage DOUBLE,
	dossieraAccountKey VARCHAR(75) null,
	type_ INTEGER,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	statusMessage STRING null
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

create table OSB_FeedbackEntry (
	feedbackEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	classNameId LONG,
	classPK LONG,
	satisfied INTEGER,
	comments STRING null,
	pageURL STRING null
);

create table OSB_HolidayCalendar (
	holidayCalendarId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table OSB_HolidayCalendarRel (
	holidayCalendarRelId LONG not null primary key,
	holidayCalendarId LONG,
	userId LONG
);

create table OSB_HolidayEntry (
	holidayEntryId LONG not null primary key,
	holidayCalendarId LONG,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	startDate DATE null,
	endDate DATE null,
	repeatYearly BOOLEAN
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
	licenseKeyId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedUserId LONG,
	modifiedUserName VARCHAR(75) null,
	modifiedDate DATE null,
	licenseKeySetId LONG,
	assetReceiptLicenseId LONG,
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

create table OSB_MarketingEvent (
	marketingEventId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	type_ INTEGER,
	defaultLanguageId VARCHAR(75) null,
	title STRING null,
	titleURL STRING null,
	hostedBy VARCHAR(75) null,
	hostedByURL STRING null,
	summary STRING null,
	imageFileEntryId LONG,
	slidesFileEntryId LONG,
	videoTitle VARCHAR(150) null,
	timeZoneId VARCHAR(75) null,
	startDate DATE null,
	endDate DATE null,
	dateTBA BOOLEAN,
	addressId LONG,
	globalRegion INTEGER,
	online_ BOOLEAN,
	registrationType INTEGER,
	registrationURL STRING null
);

create table OSB_OfferingBundle (
	offeringBundleId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	name VARCHAR(75) null
);

create table OSB_OfferingBundles_OfferingDefinitions (
	offeringBundleId LONG not null,
	offeringDefinitionId LONG not null,
	primary key (offeringBundleId, offeringDefinitionId)
);

create table OSB_OfferingDefinition (
	offeringDefinitionId LONG not null primary key,
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
	partnerEntryId LONG not null,
	supportRegionId LONG not null,
	primary key (partnerEntryId, supportRegionId)
);

create table OSB_PartnerEntry (
	partnerEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedUserId LONG,
	modifiedUserName VARCHAR(75) null,
	modifiedDate DATE null,
	parentPartnerEntryId LONG,
	dossieraAccountKey VARCHAR(75) null,
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

create table OSB_PortalRelease (
	portalReleaseId LONG not null primary key,
	versionName VARCHAR(75) null,
	buildNumber INTEGER,
	fixPackName VARCHAR(75) null,
	ee BOOLEAN,
	marketplaceSupport BOOLEAN,
	osgiSupport BOOLEAN,
	paclSupport BOOLEAN,
	hidden_ BOOLEAN
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

create table OSB_ProductSubscription (
	productSubscriptionId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	productEntryId LONG,
	startDate DATE null,
	endDate DATE null,
	actualPrice DOUBLE,
	currencyCode VARCHAR(75) null,
	quantity INTEGER,
	unlimitedQuantity BOOLEAN
);

create table OSB_SalesPartnership (
	salesPartnershipId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	corpEntryId LONG,
	type_ INTEGER,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table OSB_SalesPartnershipAssignment (
	salesPartnershipAssignmentId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	salesPartnershipId LONG,
	salesPartnershipCountryId LONG,
	level INTEGER
);

create table OSB_SalesPartnershipCountry (
	salesPartnershipCountryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	label STRING null,
	active_ BOOLEAN
);

create table OSB_SearchFilter (
	searchFilterId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	name VARCHAR(75) null,
	filter TEXT null,
	visibility INTEGER
);

create table OSB_SecurityPatch (
	securityPatchId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	accountEntryId LONG,
	ticketAttachmentId LONG,
	portletId VARCHAR(75) null,
	envLFR INTEGER,
	name VARCHAR(150) null,
	fileName STRING null
);

create table OSB_SupportLabor (
	supportLaborId LONG not null primary key,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	timeZoneId VARCHAR(75) null,
	sunOpen INTEGER,
	sunClose INTEGER,
	monOpen INTEGER,
	monClose INTEGER,
	tueOpen INTEGER,
	tueClose INTEGER,
	wedOpen INTEGER,
	wedClose INTEGER,
	thuOpen INTEGER,
	thuClose INTEGER,
	friOpen INTEGER,
	friClose INTEGER,
	satOpen INTEGER,
	satClose INTEGER
);

create table OSB_SupportRegion (
	supportRegionId LONG not null primary key,
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

create table OSB_SupportTeam (
	supportTeamId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	parentSupportTeamId LONG,
	supportLaborId LONG,
	locationSupportRegionId LONG,
	name VARCHAR(75) null,
	description STRING null,
	type_ INTEGER,
	assignedWork DOUBLE,
	maxWork DOUBLE
);

create table OSB_SupportTeamLanguage (
	supportTeamLanguageId LONG not null primary key,
	supportTeamId LONG,
	languageId VARCHAR(75) null
);

create table OSB_SupportTeams_SupportRegions (
	supportRegionId LONG not null,
	supportTeamId LONG not null,
	primary key (supportRegionId, supportTeamId)
);

create table OSB_SupportWorker (
	supportWorkerId LONG not null primary key,
	userId LONG,
	supportTeamId LONG,
	supportLaborId LONG,
	autoAssign BOOLEAN,
	assignedWork DOUBLE,
	maxWork DOUBLE,
	escalationLevel INTEGER,
	role INTEGER,
	escalationLevel2Role INTEGER,
	notifications INTEGER,
	clockedIn BOOLEAN
);

create table OSB_SupportWorkerAccountTier (
	supportWorkerAccountTierId LONG not null primary key,
	supportWorkerId LONG,
	accountTier INTEGER
);

create table OSB_SupportWorkerComponent (
	supportWorkerComponentId LONG not null primary key,
	supportWorkerId LONG,
	component INTEGER
);

create table OSB_SupportWorkerSeverity (
	supportWorkerSeverityId LONG not null primary key,
	supportWorkerId LONG,
	severity INTEGER
);

create table OSB_TicketAttachment (
	ticketAttachmentId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	ticketEntryId LONG,
	ticketSolutionId LONG,
	releaseNotesId LONG,
	fileName VARCHAR(255) null,
	fileSize LONG,
	type_ INTEGER,
	visibility INTEGER,
	extractedText TEXT null,
	availableFileRepositoryIds STRING null,
	replicate BOOLEAN,
	deleteDate DATE null,
	status INTEGER
);

create table OSB_TicketCall (
	ticketCallId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	ticketEntryId LONG,
	type_ INTEGER,
	callDate DATE null,
	callLength LONG,
	customerName VARCHAR(75) null,
	customerContact VARCHAR(75) null,
	confirmation TEXT null,
	instructions TEXT null
);

create table OSB_TicketCannedResponse (
	ticketCannedResponseId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	content STRING null,
	useCount INTEGER
);

create table OSB_TicketComment (
	ticketCommentId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	ticketEntryId LONG,
	body TEXT null,
	type_ INTEGER,
	format VARCHAR(75) null,
	visibility INTEGER,
	settings_ VARCHAR(75) null,
	status INTEGER
);

create table OSB_TicketEntry (
	ticketEntryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accountEntryId LONG,
	orderEntryId LONG,
	productEntryId LONG,
	supportResponseId LONG,
	offeringEntryId LONG,
	supportRegionId LONG,
	languageId VARCHAR(75) null,
	ticketId LONG,
	subject VARCHAR(255) null,
	description STRING null,
	reproductionSteps STRING null,
	severity INTEGER,
	status INTEGER,
	weight INTEGER,
	escalationLevel INTEGER,
	envName VARCHAR(75) null,
	envOS INTEGER,
	envOSCustom VARCHAR(150) null,
	envDB INTEGER,
	envJVM INTEGER,
	envAS INTEGER,
	envLFR INTEGER,
	envBrowser INTEGER,
	envBrowserCustom VARCHAR(150) null,
	envCS INTEGER,
	envSearch VARCHAR(75) null,
	component INTEGER,
	subcomponent INTEGER,
	subcomponentCustom VARCHAR(150) null,
	resolution INTEGER,
	holdDate DATE null,
	closedDate DATE null,
	dueDate DATE null,
	ignoreDueDate BOOLEAN,
	customerModifiedDate DATE null,
	workerModifiedDate DATE null
);

create table OSB_TicketFeedback (
	ticketFeedbackId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accountEntryId LONG,
	ticketEntryId LONG,
	subject INTEGER,
	satisfied INTEGER,
	answer1 INTEGER,
	answer2 INTEGER,
	answer3 INTEGER,
	rating1 INTEGER,
	rating2 INTEGER,
	rating3 INTEGER,
	rating4 INTEGER,
	comments STRING null,
	status INTEGER
);

create table OSB_TicketFlag (
	ticketFlagId LONG not null primary key,
	userId LONG,
	modifiedDate DATE null,
	accountEntryId LONG,
	ticketEntryId LONG,
	type_ INTEGER,
	flag INTEGER
);

create table OSB_TicketInformation (
	ticketInformationId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	ticketEntryId LONG,
	fieldId LONG,
	data_ STRING null
);

create table OSB_TicketLink (
	ticketLinkId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	ticketEntryId LONG,
	ticketSolutionId LONG,
	url STRING null,
	type_ INTEGER,
	visibility INTEGER
);

create table OSB_TicketSolution (
	ticketSolutionId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	ticketEntryId LONG,
	summary STRING null,
	useCustomerSummary BOOLEAN,
	issueType INTEGER,
	solution STRING null,
	type_ INTEGER,
	customerSpecific BOOLEAN,
	environmentSpecific BOOLEAN,
	versionSpecific BOOLEAN,
	reviewForKB BOOLEAN,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	statusMessage TEXT null,
	statusReason INTEGER
);

create table OSB_TicketWorker (
	ticketWorkerId LONG not null primary key,
	userId LONG,
	ticketEntryId LONG,
	sourceClassNameId LONG,
	sourceClassPK LONG,
	role INTEGER,
	primary_ BOOLEAN
);

create table OSB_TrainingCertificate (
	trainingCertificateId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	trainingCustomerId LONG,
	userProfileHistoryId LONG,
	key_ VARCHAR(75) null,
	certifiedDate DATE null,
	comments VARCHAR(75) null,
	surveyStatus INTEGER
);

create table OSB_TrainingCertificateTemplate (
	trainingCertificateTemplateId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description STRING null,
	type_ INTEGER
);

create table OSB_TrainingCourse (
	trainingCourseId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description STRING null,
	creditAmount INTEGER,
	courseURL STRING null,
	archived BOOLEAN
);

create table OSB_TrainingCustomer (
	trainingCustomerId LONG not null primary key,
	userId LONG,
	classNameId LONG,
	classPK LONG,
	userProfileHistoryId LONG,
	comments STRING null,
	status INTEGER
);

create table OSB_TrainingEvent (
	trainingEventId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	DDLRecordSetId LONG,
	partnerEntryId LONG,
	trainingCertificateTemplateId LONG,
	trainingCourseId LONG,
	trainingLocationId LONG,
	name VARCHAR(150) null,
	emailAddress STRING null,
	portalMinorVersion INTEGER,
	type_ INTEGER,
	languageId VARCHAR(75) null,
	localizedSlides BOOLEAN,
	timeZoneId VARCHAR(75) null,
	startDate DATE null,
	endDate DATE null,
	addressId LONG,
	maxCustomers INTEGER,
	enrollmentURL STRING null
);

create table OSB_TrainingExam (
	trainingExamId LONG not null primary key,
	trainingCertificateTemplateId LONG,
	name VARCHAR(75) null
);

create table OSB_TrainingExamResult (
	trainingExamResultId LONG not null primary key,
	createDate DATE null,
	trainingExamId LONG,
	recordType INTEGER,
	registrationNumber VARCHAR(75) null,
	formKey VARCHAR(75) null,
	startDate DATE null,
	testScore VARCHAR(75) null,
	correctCount INTEGER,
	incorrectCount INTEGER,
	skippedCount INTEGER,
	grade INTEGER,
	status INTEGER
);

create table OSB_TrainingExamResultItem (
	trainingExamResultItemId LONG not null primary key,
	trainingExamResultId LONG,
	trainingExamResultSectionId LONG,
	name VARCHAR(75) null,
	status VARCHAR(75) null,
	key_ VARCHAR(75) null,
	distractorCount INTEGER,
	type_ VARCHAR(75) null,
	response STRING null,
	score VARCHAR(75) null,
	time_ INTEGER,
	learningResources VARCHAR(75) null
);

create table OSB_TrainingExamResultSection (
	trainingExamResultSectionId LONG not null primary key,
	trainingExamResultId LONG,
	title VARCHAR(75) null,
	sectionKey VARCHAR(75) null,
	scoreIndicator BOOLEAN,
	scoringAlgorithm VARCHAR(75) null,
	masteryScore VARCHAR(75) null,
	score VARCHAR(75) null,
	standardErrorOfEstimate VARCHAR(75) null,
	correctCount INTEGER,
	incorrectCount INTEGER,
	skippedCount INTEGER,
	grade INTEGER
);

create table OSB_TrainingImportLog (
	trainingImportLogId LONG not null primary key,
	type_ INTEGER,
	importDate DATE null
);

create table OSB_TrainingLinkedUser (
	trainingLinkedUserId LONG not null primary key,
	userId LONG,
	primaryUserId LONG
);

create table OSB_TrainingLocation (
	trainingLocationId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	addressId LONG
);

create table OSB_TrainingWorker (
	trainingWorkerId LONG not null primary key,
	userId LONG,
	classNameId LONG,
	classPK LONG,
	userProfileHistoryId LONG
);

create table OSB_UserProfile (
	userProfileId LONG not null primary key,
	userId LONG,
	emailAddress VARCHAR(75) null,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	legalEntityName VARCHAR(75) null
);

create table OSB_UserProfileHistory (
	userProfileHistoryId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	classNameId LONG,
	classPK LONG,
	emailAddress VARCHAR(75) null,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	legalEntityName VARCHAR(75) null
);

create table OSB_WebinarCustomer (
	webinarCustomerId LONG not null primary key,
	userId LONG,
	webinarEventId LONG,
	attended BOOLEAN
);

create table OSB_WebinarEvent (
	webinarEventId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	name VARCHAR(75) null,
	description STRING null,
	summary STRING null,
	airDate DATE null,
	registrationURL STRING null,
	archiveURL STRING null,
	slidesFileEntryId LONG
);