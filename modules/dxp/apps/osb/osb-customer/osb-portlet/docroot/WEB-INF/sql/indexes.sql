create index IX_1B961F96 on OSB_AccountAttachment (accountEntryId, accountProjectId, fileName[$COLUMN_LENGTH:75$], type_);
create index IX_D44EEA51 on OSB_AccountAttachment (accountEntryId, accountProjectId, type_);

create index IX_B268D724 on OSB_AccountCall (accountEntryId);

create index IX_54FF6074 on OSB_AccountCustomer (accountEntryId, notifications);
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
create index IX_142E34C7 on OSB_AccountEntry (highestSupportResponseId);
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

create index IX_6291B647 on OSB_AppAudit (appVersionId);
create index IX_4DEC1EF3 on OSB_AppAudit (uuid_);

create index IX_E706B295 on OSB_AppEntry (developerEntryId, status);
create index IX_8DB55D79 on OSB_AppEntry (developerEntryId, title);
create index IX_AE78CD5E on OSB_AppEntry (status);
create unique index IX_83A04590 on OSB_AppEntry (title);
create index IX_CC61EC3C on OSB_AppEntry (uuid_);

create unique index IX_7B497867 on OSB_AppEntryRel (appEntryId1, appEntryId2, type_);
create index IX_C31738ED on OSB_AppEntryRel (appEntryId1, type_);
create index IX_AD86FF07 on OSB_AppEntryRel (uuid_);

create index IX_A49D947D on OSB_AppFlag (appVersionId, type_);
create index IX_1671D402 on OSB_AppFlag (uuid_);

create index IX_5EA83F2E on OSB_AppPackage (appVersionId, compatibility);
create index IX_8A6F5C68 on OSB_AppPackage (appVersionId, compatibilityPlus);

create index IX_79A3A0CB on OSB_AppPackagePlugin (appEntryId, contextName);
create index IX_C3DF7540 on OSB_AppPackagePlugin (appPackageId, bundleSymbolicName, bundleVersion);
create index IX_B3515777 on OSB_AppPackagePlugin (appPackageId, contextName);
create index IX_C26FF18E on OSB_AppPackagePlugin (appPackageId, fileName);
create index IX_FA6AAB24 on OSB_AppPackagePlugin (appPackageId, paclEnabled);
create index IX_24E0179F on OSB_AppPackagePlugin (appPackageId, portalRestartRequired);
create index IX_11140D1F on OSB_AppPackagePlugin (assetAttachmentId);
create index IX_C8B7258B on OSB_AppPackagePlugin (contextName);

create index IX_B380D432 on OSB_AppPricing (appVersionId);

create unique index IX_EE718AF3 on OSB_AppPricingItem (appPricingId, assetLicenseId);
create index IX_BEE670DF on OSB_AppPricingItem (assetLicenseId);

create index IX_38D38CF3 on OSB_AppVersion (appEntryId, releaseType);
create index IX_B7266084 on OSB_AppVersion (appEntryId, status);
create index IX_552C882A on OSB_AppVersion (appEntryId, version);
create index IX_9ECC20E8 on OSB_AppVersion (appEntryId, versionOrder);
create index IX_B0B86A44 on OSB_AppVersion (status);

create index IX_653ECD6D on OSB_AssetAttachment (classNameId, classPK, fileName, type_);
create index IX_CC67A8E8 on OSB_AssetAttachment (classNameId, classPK, type_);
create index IX_C9B1988B on OSB_AssetAttachment (createDate, classNameId, classPK);

create index IX_5F11F887 on OSB_AssetAudit (classNameId, classPK);
create index IX_42980870 on OSB_AssetAudit (createDate, classNameId, classPK, type_);
create index IX_63E33774 on OSB_AssetAudit (userId, classNameId, classPK, type_);

create index IX_519B1093 on OSB_AssetLicense (classNameId, classPK, status);
create index IX_F8157850 on OSB_AssetLicense (classNameId, classPK, usageType, licenseType, licenseTypeAllotment, status);
create index IX_53516693 on OSB_AssetLicense (classNameId, classPK, usageType, licenseType, status);

create unique index IX_64A6D5EF on OSB_AssetList (assetCategoryId, type_);

create index IX_A9BE68C6 on OSB_AssetListAssetEntry (assetEntryId);
create unique index IX_99B4BA95 on OSB_AssetListAssetEntry (assetListId, assetEntryId);

create index IX_964E6720 on OSB_AssetReceipt (assetEntryId);
create unique index IX_9CB20B7D on OSB_AssetReceipt (ownerClassNameId, ownerClassPK, productClassNameId, productClassPK);
create index IX_A7D5FC24 on OSB_AssetReceipt (userId, productClassNameId, productClassPK);

create index IX_86274F5E on OSB_AssetReceiptLicense (assetLicenseId);
create index IX_DC4E821D on OSB_AssetReceiptLicense (assetReceiptId, startDate, endDate, usageType);
create index IX_F0A1AA0 on OSB_AssetReceiptLicense (assetReceiptId, startDate, usageType);
create index IX_59371C34 on OSB_AssetReceiptLicense (ownerClassNameId, ownerClassPK, productClassNameId);
create index IX_5C2F0818 on OSB_AssetReceiptLicense (ownerClassNameId, ownerClassPK, productId, licenseType, licenseLifetime);
create index IX_F61CD736 on OSB_AssetReceiptLicense (uuid_);

create unique index IX_183FDF00 on OSB_AssetReceiptRedeemToken (redeemEmailAddress, redeemDate);
create unique index IX_2605E56F on OSB_AssetReceiptRedeemToken (token);

create index IX_260FF77 on OSB_AssetReceiptSupport (assetReceiptId, productClassNameId, productClassPK, startDate, endDate);
create index IX_4CE80C4F on OSB_AssetReceiptSupport (assetReceiptId, startDate, endDate, usageType);
create index IX_4C8CF02E on OSB_AssetReceiptSupport (assetReceiptId, startDate, usageType);
create index IX_D261FB68 on OSB_AssetReceiptSupport (uuid_);

create unique index IX_BB00C4ED on OSB_AssetRecommendationEntry (assetRecommendationSetId, classNameId, classPK);
create index IX_89CAB4EE on OSB_AssetRecommendationEntry (assetRecommendationSetId, purchasedAlsoPurchasedCount);
create index IX_827847E3 on OSB_AssetRecommendationEntry (assetRecommendationSetId, viewedAlsoPurchasedCount);
create index IX_695A7525 on OSB_AssetRecommendationEntry (classNameId, classPK);

create unique index IX_723EBF15 on OSB_AssetRecommendationSet (classNameId, classPK);

create unique index IX_D2F824F6 on OSB_AssetStatsDay (classNameId, classPK, day, year);

create unique index IX_C8BB39B6 on OSB_AssetStatsMonth (classNameId, classPK, month, year);

create unique index IX_5E9668B8 on OSB_AssetStatsWeek (classNameId, classPK, week, year);

create index IX_2194BE84 on OSB_AuditAction (classNameId, classPK, mappingClassPK);
create index IX_6E655F39 on OSB_AuditAction (modifiedDate, classNameId, classPK, mappingClassPK, action);
create index IX_EA525690 on OSB_AuditAction (modifiedDate, classNameId, mappingClassPK, action);

create index IX_56D6EFE1 on OSB_AuditEntry (classNameId, classPK, field, action);
create index IX_6DC23F99 on OSB_AuditEntry (classNameId, classPK, visibility);
create index IX_558935D2 on OSB_AuditEntry (createDate, classNameId);
create index IX_A70C3087 on OSB_AuditEntry (fieldClassNameId, fieldClassPK, field);

create index IX_4772426A on OSB_ContractAudit (contractEntryId, signatoryClassNameId, signatoryClassPK);
create index IX_10AD3D03 on OSB_ContractAudit (userId, contractEntryId);

create index IX_E089E53B on OSB_ContractEntry (classNameId, classPK, type_);

create index IX_ADDCEB29 on OSB_CorpEntry (dossieraAccountKey);
create index IX_A1DFC2AC on OSB_CorpEntry (name);
create index IX_391B724F on OSB_CorpEntry (organizationId);

create index IX_2F99E8B0 on OSB_CorpEntry_CorpGroup (corpEntryId);
create index IX_F3C8083D on OSB_CorpEntry_CorpGroup (corpGroupId);

create index IX_5BA953B9 on OSB_CorpGroup (name);
create index IX_2480D71C on OSB_CorpGroup (organizationId);

create index IX_72919D25 on OSB_CorpMembershipRequest (corpEntryId, status);
create unique index IX_F0B813A8 on OSB_CorpMembershipRequest (key_);
create index IX_E927212B on OSB_CorpMembershipRequest (userId, corpEntryId, status);

create index IX_B1E34FE4 on OSB_CorpProject (dossieraProjectKey);
create index IX_C2AF513 on OSB_CorpProject (name);
create index IX_14940FF6 on OSB_CorpProject (organizationId);

create index IX_F22B253A on OSB_CorpProjectMessage (corpProjectId, type_);
create index IX_73BC3756 on OSB_CorpProjectMessage (type_);

create index IX_C7A9B0F4 on OSB_CountryAppPricing (appPricingId);
create unique index IX_1DDA55FB on OSB_CountryAppPricing (appVersionId, countryId);
create index IX_CBEE1A21 on OSB_CountryAppPricing (countryId);

create unique index IX_64FD3078 on OSB_CurrencyEntry (currencyCode[$COLUMN_LENGTH:75$]);
create index IX_671B950 on OSB_CurrencyEntry (marketplaceEnabled);

create index IX_4256345C on OSB_DeveloperEntry (domainName, domainStatus);
create unique index IX_B0F6096B on OSB_DeveloperEntry (dossieraAccountKey);
create index IX_C19D5AB3 on OSB_DeveloperEntry (legalEntityName, type_, status);
create index IX_F1259BD5 on OSB_DeveloperEntry (status);
create index IX_147C0BEC on OSB_DeveloperEntry (type_, status);
create index IX_AB013772 on OSB_DeveloperEntry (userId, type_, status);

create index IX_86A7752 on OSB_ExternalIdMapper (classNameId, classPK, type_);
create index IX_8FBE42CF on OSB_ExternalIdMapper (classNameId, type_, externalId[$COLUMN_LENGTH:75$]);

create index IX_1738CAF1 on OSB_HolidayCalendarRel (holidayCalendarId, userId);
create index IX_B2BCBD24 on OSB_HolidayCalendarRel (userId);

create index IX_8D6F9B60 on OSB_HolidayEntry (holidayCalendarId);

create index IX_8B5D7FE4 on OSB_LicenseEntry (productEntryId, portalVersionMin);
create unique index IX_7576438B on OSB_LicenseEntry (productEntryId, type_[$COLUMN_LENGTH:75$]);
create index IX_12886FD on OSB_LicenseEntry (type_[$COLUMN_LENGTH:75$]);

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

create index IX_EED5EFA6 on OSB_MarketingEvent (globalRegion);
create index IX_F81473A on OSB_MarketingEvent (type_);

create unique index IX_DC1ECDCE on OSB_OfferingBundle (name[$COLUMN_LENGTH:75$]);

create index IX_5B0DF53D on OSB_OfferingBundles_OfferingDefinitions (companyId);
create index IX_480DB8E2 on OSB_OfferingBundles_OfferingDefinitions (offeringBundleId);
create index IX_5CB2DBB3 on OSB_OfferingBundles_OfferingDefinitions (offeringDefinitionId);

create index IX_CFDC51BF on OSB_OfferingDefinition (productEntryId, supportResponseId, productDescription[$COLUMN_LENGTH:75$], licenses, unlimitedLicenses, supportTickets);
create index IX_99AA46FB on OSB_OfferingDefinition (supportResponseId);

create index IX_ACC7D8EB on OSB_OfferingEntry (accountEntryId, status);
create index IX_2CFC6FC4 on OSB_OfferingEntry (orderEntryId);
create index IX_91C36F37 on OSB_OfferingEntry (status);
create index IX_E0CF6A9F on OSB_OfferingEntry (supportEndDate);
create index IX_6455D563 on OSB_OfferingEntry (userId, accountEntryId, orderEntryId, type_);
create index IX_CE314FD7 on OSB_OfferingEntry (version);

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

create index IX_86585E47 on OSB_PortalRelease (buildNumber);
create index IX_3F851494 on OSB_PortalRelease (ee);
create index IX_2D2FADF8 on OSB_PortalRelease (marketplaceSupport);
create index IX_8515A999 on OSB_PortalRelease (osgiSupport);
create index IX_726497C5 on OSB_PortalRelease (paclSupport);

create unique index IX_190AFA9 on OSB_ProductEntry (name[$COLUMN_LENGTH:75$]);

create index IX_A84DAB0D on OSB_SearchFilter (userId, classNameId);

create index IX_18BFB3A4 on OSB_SecurityPatch (accountEntryId, portletId);
create index IX_36A4B1D8 on OSB_SecurityPatch (portletId);

create unique index IX_DC67F8E7 on OSB_SupportRegion (name[$COLUMN_LENGTH:75$]);

create unique index IX_9A2345B4 on OSB_SupportResponse (name[$COLUMN_LENGTH:75$]);
create index IX_21D8E69E on OSB_SupportResponse (supportLevel);

create unique index IX_CB537ED0 on OSB_SupportTeam (name[$COLUMN_LENGTH:75$]);
create index IX_259AEC3C on OSB_SupportTeam (parentSupportTeamId);
create index IX_92D16E1 on OSB_SupportTeam (supportLaborId);

create index IX_45C068E on OSB_SupportTeamLanguage (supportTeamId);

create index IX_7FEE4C9B on OSB_SupportTeams_SupportRegions (companyId);
create index IX_F1DB9001 on OSB_SupportTeams_SupportRegions (supportRegionId);
create index IX_E33A26EA on OSB_SupportTeams_SupportRegions (supportTeamId);

create index IX_C3CFE42 on OSB_SupportWorker (supportLaborId);
create index IX_5B76AC85 on OSB_SupportWorker (supportTeamId);
create index IX_211496C3 on OSB_SupportWorker (userId, maxWork, role);
create unique index IX_C4442B0B on OSB_SupportWorker (userId, supportTeamId);

create index IX_749683CF on OSB_SupportWorkerAccountTier (supportWorkerId);

create index IX_9FADBCC1 on OSB_SupportWorkerComponent (supportWorkerId);

create index IX_58971029 on OSB_SupportWorkerSeverity (supportWorkerId);

create index IX_61FA03D on OSB_TicketAttachment (createDate, ticketEntryId);
create index IX_C5D75521 on OSB_TicketAttachment (createDate, type_);
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

create unique index IX_2F6A1E76 on OSB_TrainingCertificate (key_);
create unique index IX_940016E9 on OSB_TrainingCertificate (trainingCustomerId);

create index IX_7E1FDA19 on OSB_TrainingCertificateTemplate (type_);

create index IX_6CC54BBA on OSB_TrainingCustomer (classNameId, classPK, status);
create index IX_2248428E on OSB_TrainingCustomer (userId, classNameId, classPK);

create index IX_CD618D79 on OSB_TrainingEvent (DDLRecordSetId);
create index IX_DFBDC0DD on OSB_TrainingEvent (trainingCertificateTemplateId);
create index IX_7D10E314 on OSB_TrainingEvent (trainingCourseId, type_, startDate);
create index IX_44DDD7C3 on OSB_TrainingEvent (trainingLocationId);
create index IX_3773B4D0 on OSB_TrainingEvent (type_, startDate);

create index IX_B1D428FF on OSB_TrainingExam (name);
create index IX_C2119662 on OSB_TrainingExam (trainingCertificateTemplateId);

create index IX_359F9FB on OSB_TrainingExamResult (createDate);
create unique index IX_ED881F93 on OSB_TrainingExamResult (registrationNumber);
create index IX_CD97224A on OSB_TrainingExamResult (startDate, grade, status);

create index IX_51323EB5 on OSB_TrainingExamResultItem (trainingExamResultId);
create index IX_41864BEA on OSB_TrainingExamResultItem (trainingExamResultSectionId);

create index IX_FF29C879 on OSB_TrainingExamResultSection (trainingExamResultId);

create index IX_FE8EC56B on OSB_TrainingImportLog (type_);

create index IX_64CA91D3 on OSB_TrainingLinkedUser (primaryUserId);
create unique index IX_DEA7805F on OSB_TrainingLinkedUser (userId);

create index IX_930AF34 on OSB_TrainingWorker (classNameId, classPK);
create index IX_FCB970EE on OSB_TrainingWorker (userId, classNameId, classPK);

create index IX_86ECAD1D on OSB_UserProfile (userId);

create index IX_154A2B12 on OSB_UserProfileHistory (classNameId, classPK);
create index IX_BB200AF7 on OSB_UserProfileHistory (userId, classNameId);