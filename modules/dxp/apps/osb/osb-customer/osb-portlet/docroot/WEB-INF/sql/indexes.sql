create index IX_1B961F96 on OSB_AccountAttachment (accountEntryId, accountProjectId, fileName[$COLUMN_LENGTH:75$], type_);
create index IX_D44EEA51 on OSB_AccountAttachment (accountEntryId, accountProjectId, type_);

create index IX_B590328E on OSB_AccountCustomer (accountEntryId, role);
create index IX_F9BEADE on OSB_AccountCustomer (userId, accountEntryId);
create index IX_A47A5E94 on OSB_AccountCustomer (userId, role);

create index IX_E9054065 on OSB_AccountEntries_SupportRegions (accountEntryId);
create index IX_66F378D7 on OSB_AccountEntries_SupportRegions (companyId);
create index IX_B9459F3D on OSB_AccountEntries_SupportRegions (supportRegionId);

create unique index IX_32214056 on OSB_AccountEntry (code_[$COLUMN_LENGTH:75$]);
create index IX_6AA361CE on OSB_AccountEntry (corpProjectId);
create index IX_64D9942E on OSB_AccountEntry (corpProjectUuid[$COLUMN_LENGTH:75$]);
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

create index IX_E16E8A07 on OSB_AccountProject (accountEntryId);

create index IX_640DDD4 on OSB_AccountWorker (accountEntryId, notifications);
create index IX_9D0A292E on OSB_AccountWorker (accountEntryId, role);
create index IX_72B97E on OSB_AccountWorker (userId, accountEntryId);
create index IX_1224F534 on OSB_AccountWorker (userId, role);

create index IX_56D6EFE1 on OSB_AuditEntry (classNameId, classPK, field, action);
create index IX_6DC23F99 on OSB_AuditEntry (classNameId, classPK, visibility);
create index IX_558935D2 on OSB_AuditEntry (createDate, classNameId);
create index IX_A70C3087 on OSB_AuditEntry (fieldClassNameId, fieldClassPK, field);

create index IX_B1E34FE4 on OSB_CorpProject (dossieraProjectKey[$COLUMN_LENGTH:75$]);
create index IX_C2AF513 on OSB_CorpProject (name[$COLUMN_LENGTH:75$]);
create index IX_14940FF6 on OSB_CorpProject (organizationId);
create index IX_D682EE0 on OSB_CorpProject (uuid_[$COLUMN_LENGTH:75$]);

create index IX_F22B253A on OSB_CorpProjectMessage (corpProjectId, type_);
create index IX_73BC3756 on OSB_CorpProjectMessage (type_);
create index IX_A16BF735 on OSB_CorpProjectMessage (uuid_[$COLUMN_LENGTH:75$]);

create index IX_86A7752 on OSB_ExternalIdMapper (classNameId, classPK, type_);
create index IX_8FBE42CF on OSB_ExternalIdMapper (classNameId, type_, externalId[$COLUMN_LENGTH:75$]);

create index IX_8B5D7FE4 on OSB_LicenseEntry (productEntryId, portalVersionMin);
create unique index IX_7576438B on OSB_LicenseEntry (productEntryId, type_[$COLUMN_LENGTH:75$]);

create index IX_D9E7AC39 on OSB_LicenseKey (accountEntryId);
create index IX_5D0EB94A on OSB_LicenseKey (assetReceiptLicenseUuid[$COLUMN_LENGTH:75$], active_);
create index IX_F432AF3A on OSB_LicenseKey (assetReceiptLicenseUuid[$COLUMN_LENGTH:75$], complimentary, active_);
create index IX_C31F329C on OSB_LicenseKey (assetReceiptLicenseUuid[$COLUMN_LENGTH:75$], productId[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$], active_);
create index IX_3B9F5AEA on OSB_LicenseKey (licenseKeySetId);
create index IX_AEC46A56 on OSB_LicenseKey (offeringEntryId, clusterId, active_);
create index IX_34C23F6D on OSB_LicenseKey (offeringEntryId, complimentary, active_);
create index IX_8472FBAC on OSB_LicenseKey (offeringEntryId, licenseEntryType[$COLUMN_LENGTH:75$], complimentary, active_);
create index IX_DE309610 on OSB_LicenseKey (productEntryName[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$], active_);
create index IX_AFB6E8E7 on OSB_LicenseKey (productId[$COLUMN_LENGTH:75$], serverId[$COLUMN_LENGTH:4000$]);
create index IX_A7B2FB73 on OSB_LicenseKey (userId, accountEntryId);
create index IX_1A89947B on OSB_LicenseKey (userId, productId[$COLUMN_LENGTH:75$]);
create index IX_F6FA838F on OSB_LicenseKey (uuid_[$COLUMN_LENGTH:75$]);

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

create index IX_18BFB3A4 on OSB_SecurityPatch (accountEntryId, portletId[$COLUMN_LENGTH:75$]);
create index IX_36A4B1D8 on OSB_SecurityPatch (portletId[$COLUMN_LENGTH:75$]);

create unique index IX_DC67F8E7 on OSB_SupportRegion (name[$COLUMN_LENGTH:75$]);

create unique index IX_9A2345B4 on OSB_SupportResponse (name[$COLUMN_LENGTH:75$]);