create index IX_1B961F96 on OSB_AccountAttachment (accountEntryId, accountProjectId, fileName[$COLUMN_LENGTH:75$], type_);
create index IX_D44EEA51 on OSB_AccountAttachment (accountEntryId, accountProjectId, type_);

create index IX_B268D724 on OSB_AccountCall (accountEntryId);

create index IX_B590328E on OSB_AccountCustomer (accountEntryId, role);
create index IX_F9BEADE on OSB_AccountCustomer (userId, accountEntryId);
create index IX_A47A5E94 on OSB_AccountCustomer (userId, role);

create index IX_E9054065 on OSB_AccountEntries_SupportRegions (accountEntryId);
create index IX_66F378D7 on OSB_AccountEntries_SupportRegions (companyId);
create index IX_B9459F3D on OSB_AccountEntries_SupportRegions (supportRegionId);

create index IX_1910ED5C on OSB_AccountEntries_SupportTeams (accountEntryId);
create index IX_C38F4D80 on OSB_AccountEntries_SupportTeams (companyId);
create index IX_7AF62D4F on OSB_AccountEntries_SupportTeams (supportTeamId);

create unique index IX_32214056 on OSB_AccountEntry (code_[$COLUMN_LENGTH:75$]);
create index IX_6AA361CE on OSB_AccountEntry (corpProjectId);
create index IX_2FB47967 on OSB_AccountEntry (name[$COLUMN_LENGTH:500$], code_[$COLUMN_LENGTH:75$], redirectAccountEntryId);
create index IX_1DCDCBE5 on OSB_AccountEntry (partnerEntryId);
create index IX_5F5D3902 on OSB_AccountEntry (redirectAccountEntryId, status);
create index IX_E57EB21F on OSB_AccountEntry (redirectAccountEntryId, type_, status);
create index IX_31DB3E6F on OSB_AccountEntry (userId, type_);

create index IX_8B166398 on OSB_AccountEntryLanguage (accountEntryId);

create index IX_4A401F32 on OSB_AccountEnvironment (accountEntryId, productEntryId, name[$COLUMN_LENGTH:75$]);

create index IX_8CAAC4C0 on OSB_AccountEnvironmentAttachment (accountEnvironmentId, fileName[$COLUMN_LENGTH:75$]);
create index IX_23139A90 on OSB_AccountEnvironmentAttachment (accountEnvironmentId, type_);

create unique index IX_9644DED0 on OSB_AccountInformation (accountEntryId, accountProjectId, fieldId);

create index IX_D37D5DC0 on OSB_AccountLink (accountEntryId);

create index IX_E16E8A07 on OSB_AccountProject (accountEntryId);

create index IX_640DDD4 on OSB_AccountWorker (accountEntryId, notifications);
create index IX_9D0A292E on OSB_AccountWorker (accountEntryId, role);
create index IX_72B97E on OSB_AccountWorker (userId, accountEntryId);
create index IX_1224F534 on OSB_AccountWorker (userId, role);

create index IX_2194BE84 on OSB_AuditAction (classNameId, classPK, mappingClassPK);
create index IX_6E655F39 on OSB_AuditAction (modifiedDate, classNameId, classPK, mappingClassPK, action);
create index IX_EA525690 on OSB_AuditAction (modifiedDate, classNameId, mappingClassPK, action);

create index IX_56D6EFE1 on OSB_AuditEntry (classNameId, classPK, field, action);
create index IX_6DC23F99 on OSB_AuditEntry (classNameId, classPK, visibility);
create index IX_558935D2 on OSB_AuditEntry (createDate, classNameId);
create index IX_A70C3087 on OSB_AuditEntry (fieldClassNameId, fieldClassPK, field);

create index IX_B1E34FE4 on OSB_CorpProject (dossieraProjectKey[$COLUMN_LENGTH:75$]);
create index IX_C2AF513 on OSB_CorpProject (name[$COLUMN_LENGTH:75$]);
create index IX_14940FF6 on OSB_CorpProject (organizationId);
create index IX_D682EE0 on OSB_CorpProject (uuid_[$COLUMN_LENGTH:75$]);

create index IX_86A7752 on OSB_ExternalIdMapper (classNameId, classPK, type_);
create index IX_8FBE42CF on OSB_ExternalIdMapper (classNameId, type_, externalId[$COLUMN_LENGTH:75$]);

create index IX_1738CAF1 on OSB_HolidayCalendarRel (holidayCalendarId, userId);

create index IX_8D6F9B60 on OSB_HolidayEntry (holidayCalendarId);

create index IX_8B5D7FE4 on OSB_LicenseEntry (productEntryId, portalVersionMin);
create unique index IX_7576438B on OSB_LicenseEntry (productEntryId, type_[$COLUMN_LENGTH:75$]);

create index IX_D9E7AC39 on OSB_LicenseKey (accountEntryId);
create index IX_EB731F6A on OSB_LicenseKey (assetReceiptLicenseId, active_);
create index IX_A1F1451A on OSB_LicenseKey (assetReceiptLicenseId, complimentary, active_);
create index IX_F44DE07C on OSB_LicenseKey (assetReceiptLicenseId, productId[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$], active_);
create index IX_3B9F5AEA on OSB_LicenseKey (licenseKeySetId);
create index IX_AEC46A56 on OSB_LicenseKey (offeringEntryId, clusterId, active_);
create index IX_34C23F6D on OSB_LicenseKey (offeringEntryId, complimentary, active_);
create index IX_8472FBAC on OSB_LicenseKey (offeringEntryId, licenseEntryType[$COLUMN_LENGTH:75$], complimentary, active_);
create index IX_4C3397AB on OSB_LicenseKey (orderEntryId, owner[$COLUMN_LENGTH:75$]);
create index IX_DE309610 on OSB_LicenseKey (productEntryName[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$], active_);
create index IX_AFB6E8E7 on OSB_LicenseKey (productId[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$]);
create index IX_A7B2FB73 on OSB_LicenseKey (userId, accountEntryId);
create index IX_FED7DC75 on OSB_LicenseKey (userId, assetReceiptLicenseId, productId[$COLUMN_LENGTH:75$]);

create index IX_AA77CE1D on OSB_LicenseKeySet (accountEntryId);
create index IX_62FEABD6 on OSB_LicenseKeySet (userId, accountEntryId, name[$COLUMN_LENGTH:75$]);

create unique index IX_DC1ECDCE on OSB_OfferingBundle (name[$COLUMN_LENGTH:75$]);

create index IX_5B0DF53D on OSB_OfferingBundles_OfferingDefinitions (companyId);
create index IX_480DB8E2 on OSB_OfferingBundles_OfferingDefinitions (offeringBundleId);
create index IX_5CB2DBB3 on OSB_OfferingBundles_OfferingDefinitions (offeringDefinitionId);

create index IX_CFDC51BF on OSB_OfferingDefinition (productEntryId, supportResponseId, productDescription[$COLUMN_LENGTH:75$], licenses, unlimitedLicenses, supportTickets);
create index IX_99AA46FB on OSB_OfferingDefinition (supportResponseId);

create index IX_C5126F05 on OSB_OfferingEntry (accountEntryId);
create index IX_2CFC6FC4 on OSB_OfferingEntry (orderEntryId);
create index IX_6455D563 on OSB_OfferingEntry (userId, accountEntryId, orderEntryId, type_);

create index IX_CF95221F on OSB_OrderEntry (accountEntryId);
create index IX_9903B2E9 on OSB_OrderEntry (uuid_[$COLUMN_LENGTH:75$]);

create index IX_81C0085C on OSB_PartnerEntries_SupportRegions (companyId);
create index IX_A39907C5 on OSB_PartnerEntries_SupportRegions (partnerEntryId);
create index IX_A1C7B702 on OSB_PartnerEntries_SupportRegions (supportRegionId);

create unique index IX_2D2C67B1 on OSB_PartnerEntry (code_[$COLUMN_LENGTH:255$]);
create index IX_E567A9ED on OSB_PartnerEntry (dossieraAccountKey[$COLUMN_LENGTH:75$]);
create index IX_5665A174 on OSB_PartnerEntry (parentPartnerEntryId);

create index IX_6F521474 on OSB_PartnerWorker (partnerEntryId, notifications);
create index IX_15A2FE8E on OSB_PartnerWorker (partnerEntryId, role);
create index IX_8D1D4BDE on OSB_PartnerWorker (userId, partnerEntryId);
create index IX_F36C918F on OSB_PartnerWorker (userId, role);

create unique index IX_190AFA9 on OSB_ProductEntry (name[$COLUMN_LENGTH:75$]);

create index IX_A84DAB0D on OSB_SearchFilter (userId, classNameId);

create index IX_18BFB3A4 on OSB_SecurityPatch (accountEntryId, portletId[$COLUMN_LENGTH:75$]);
create index IX_36A4B1D8 on OSB_SecurityPatch (portletId[$COLUMN_LENGTH:75$]);

create unique index IX_DC67F8E7 on OSB_SupportRegion (name[$COLUMN_LENGTH:75$]);

create unique index IX_9A2345B4 on OSB_SupportResponse (name[$COLUMN_LENGTH:75$]);

create unique index IX_CB537ED0 on OSB_SupportTeam (name[$COLUMN_LENGTH:75$]);
create index IX_259AEC3C on OSB_SupportTeam (parentSupportTeamId);
create index IX_92D16E1 on OSB_SupportTeam (supportLaborId);

create index IX_45C068E on OSB_SupportTeamLanguage (supportTeamId);

create index IX_7FEE4C9B on OSB_SupportTeams_SupportRegions (companyId);
create index IX_F1DB9001 on OSB_SupportTeams_SupportRegions (supportRegionId);
create index IX_E33A26EA on OSB_SupportTeams_SupportRegions (supportTeamId);

create index IX_C3CFE42 on OSB_SupportWorker (supportLaborId);
create index IX_5B76AC85 on OSB_SupportWorker (supportTeamId);
create unique index IX_C4442B0B on OSB_SupportWorker (userId, supportTeamId);

create index IX_749683CF on OSB_SupportWorkerAccountTier (supportWorkerId);

create index IX_9FADBCC1 on OSB_SupportWorkerComponent (supportWorkerId);

create index IX_58971029 on OSB_SupportWorkerSeverity (supportWorkerId);

create index IX_61FA03D on OSB_TicketAttachment (createDate, ticketEntryId);
create index IX_26149242 on OSB_TicketAttachment (ticketEntryId, fileName[$COLUMN_LENGTH:255$], visibility, status);
create index IX_CFC835A1 on OSB_TicketAttachment (ticketEntryId, status);
create index IX_46E45E8F on OSB_TicketAttachment (ticketEntryId, ticketSolutionId);
create index IX_36DB14A0 on OSB_TicketAttachment (ticketEntryId, type_, status);
create index IX_B8D980A6 on OSB_TicketAttachment (ticketEntryId, type_, visibility, status);
create index IX_47FFB24C on OSB_TicketAttachment (type_, deleteDate);
create index IX_6EAF2BAD on OSB_TicketAttachment (userId, ticketEntryId, visibility, status);

create index IX_3225AAFC on OSB_TicketComment (ticketEntryId, type_);
create index IX_304B8825 on OSB_TicketComment (ticketEntryId, visibility, status);
create index IX_565F15CA on OSB_TicketComment (userId, ticketEntryId, visibility, status, type_);

create unique index IX_854674BA on OSB_TicketEntry (accountEntryId, ticketId);
create index IX_F00D9FFD on OSB_TicketEntry (companyId);
create index IX_E0167256 on OSB_TicketEntry (modifiedDate);
create index IX_A5B233EC on OSB_TicketEntry (offeringEntryId, resolution);

create index IX_52ABA109 on OSB_TicketFeedback (ticketEntryId, subject, status);
create index IX_55467943 on OSB_TicketFeedback (userId, ticketEntryId, subject, status);

create index IX_34D48682 on OSB_TicketFlag (accountEntryId, type_);
create index IX_415DBAE3 on OSB_TicketFlag (ticketEntryId, type_, flag);
create unique index IX_FC942931 on OSB_TicketFlag (userId, accountEntryId, ticketEntryId, type_);

create index IX_C15F4C19 on OSB_TicketInformation (ticketEntryId, fieldId);

create index IX_90EFDA98 on OSB_TicketLink (ticketEntryId, ticketSolutionId);
create index IX_F493310A on OSB_TicketLink (ticketEntryId, visibility);

create index IX_F1F54CE5 on OSB_TicketSolution (ticketEntryId);

create index IX_1D95DF06 on OSB_TicketWorker (sourceClassNameId, sourceClassPK);
create index IX_26AF5491 on OSB_TicketWorker (ticketEntryId, primary_);
create index IX_B8E7B7C6 on OSB_TicketWorker (userId, ticketEntryId);