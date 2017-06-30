Liferay.Service.register("Liferay.Service.OSB", "com.liferay.osb.service", "osb-portlet");

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AccountAttachment",
	{
		addAccountAttachments: true,
		deleteAccountAttachment: true,
		getAccountAttachment: true,
		getAccountAttachments: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AccountCall",
	{
		deleteAccountCall: true,
		updateAccountCall: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AccountCustomer",
	{
		addAccountCustomers: true,
		deleteAccountCustomers: true,
		getCorpProjectAccountCustomerUUIDs: true,
		toggleNotifications: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AccountEntry",
	{
		fetchCorpProjectAccountEntry: true,
		getAccountEntry: true,
		getAccountEntryByCode: true,
		getSecurityPatchAccountEntries: true,
		search: true,
		searchCount: true,
		updateAccountEntry: true,
		updateInstructions: true,
		updateTier: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AccountEnvironment",
	{
		addAccountEnvironment: true,
		deleteAccountEnvironment: true,
		getAccountEnvironment: true,
		getAccountEnvironments: true,
		getAccountEnvironmentsMap: true,
		updateAccountEnvironment: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AccountEnvironmentAttachment",
	{
		getAccountEnvironmentAttachment: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AccountInformation",
	{
		updateAccountInformation: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AccountLink",
	{
		addAccountLinks: true,
		deleteAccountLink: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AccountProject",
	{
		deleteAccountProject: true,
		updateAccountProject: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AccountWorker",
	{
		addAccountWorkers: true,
		deleteAccountWorkers: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AppEntry",
	{
		deleteAppEntry: true,
		deleteAppEntryMedia: true,
		getAppEntry: true,
		updateAppEntry: true,
		updateStatus: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AppEntryRel",
	{
		addAppEntryRel: true,
		deleteAppEntryRel: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AppFlag",
	{
		addAppFlag: true,
		deleteAppFlag: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AppPackage",
	{
		addAppPackage: true,
		deleteAppPackage: true,
		deleteAppPackageSrc: true,
		fetchAppPackage: true,
		getAppPackage: true,
		updateAppPackage: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AppPackagePlugin",
	{
		deleteAppPackagePlugin: true,
		deleteInvalidAppPackagePlugins: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AppPricing",
	{
		addAppPricing: true,
		deleteAppPricing: true,
		updateAppPricing: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AppPricingItem",
	{
		updateAppPricingItem: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AppVersion",
	{
		deleteAppVersion: true,
		getAppVersion: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AssetAttachment",
	{
		fetchAssetAttachment: true,
		getAssetAttachments: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AssetReceipt",
	{
		fetchAssetReceipt: true,
		getAssetReceipt: true,
		purchaseAssets: true,
		updateAssetReceipt: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "AssetRecommendationEntry",
	{
		getAssetRecommendationEntries: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "ContractAudit",
	{
		addContractAudit: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "ContractEntry",
	{
		addContractEntry: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "CorpEntry",
	{
		addCorpEntryUsers: true,
		addUserCorpEntryRoles: true,
		deleteCorpEntry: true,
		deleteUserCorpEntryRoles: true,
		getCorpEntry: true,
		hasUserCorpEntry: true,
		mergeCorpEntry: true,
		unsetCorpEntryUsers: true,
		updateStatus: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "CorpGroup",
	{
		deleteCorpGroup: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "CorpMembershipRequest",
	{
		updateStatus: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "CorpProject",
	{
		addCorpProject: true,
		addCorpProjectUsers: true,
		addUserCorpProjectRoles: true,
		deleteCorpProject: true,
		deleteUserCorpProjectRoles: true,
		getCorpProject: true,
		getUserCorpProjects: true,
		hasUserCorpProject: true,
		hasUserCorpProjectRole: true,
		unsetCorpProjectUsers: true,
		updateCorpProject: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "CorpProjectMessage",
	{
		getCorpProjectMessages: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "CountryAppPricing",
	{
		deleteCountryAppPricings: true,
		updateCountryAppPricings: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "DeveloperEntry",
	{
		addUserDeveloperEntry: true,
		updateDeveloperEntry: true,
		updateDeveloperEntryGoogleAnalyticsKey: true,
		updateStatus: true,
		updateSubscription: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "LCSSubscriptionEntry",
	{
		getLCSSubscriptionEntries: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "LicenseKey",
	{
		addLicenseKey: true,
		exportToXML: true,
		getLicenseKey: true,
		getLicenseKeys: true,
		getLicenseKeySetLicenseKeys: true,
		getOfferingEntryGroupLicenseKeys: true,
		getOfferingEntryGroupLicenseKeysCount: true,
		isActive: true,
		renewLicenseKey: true,
		search: true,
		searchCount: true,
		updateLicenseKey: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "LicenseKeySet",
	{
		addLicenseKeySet: true,
		deleteLicenseKeySet: true,
		exportToXML: true,
		getLicenseKeySet: true,
		updateLicenseKeySet: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "OfferingEntry",
	{
		getAccountEntryOfferingEntries: true,
		getOrderEntryOfferingEntries: true,
		updateOfferingEntry: true,
		updateStatus: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "OrderEntry",
	{
		getOrderEntries: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "OSBCountry",
	{
		addCountry: true,
		deleteCountry: true,
		updateCountry: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "OSBRegion",
	{
		addRegion: true,
		deleteRegion: true,
		updateRegion: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "PartnerEntry",
	{
		getPartnerEntry: true,
		search: true,
		searchCount: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "SearchFilter",
	{
		addSearchFilter: true,
		deleteSearchFilter: true,
		getSearchFilter: true,
		updateSearchFilter: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "SecurityPatch",
	{
		getSecurityPatch: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "SupportWorker",
	{
		clockInOut: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "TicketAttachment",
	{
		addTicketAttachment: true,
		addTicketAttachments: true,
		checkAvailability: true,
		deleteTicketAttachment: true,
		getTicketAttachment: true,
		getUploadToken: true,
		replicateTicketAttachment: true,
		updateDeleteDate: true,
		updateTicketAttachment: true,
		updateTicketAttachments: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "TicketCall",
	{
		addTicketCall: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "TicketCannedResponse",
	{
		incrementUseCount: true,
		search: true,
		searchCount: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "TicketComment",
	{
		addTicketComment: true,
		deleteTicketComment: true,
		updateTicketComment: true,
		updateTicketCommentType: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "TicketEntry",
	{
		addTicketEntry: true,
		closeTicketEntry: true,
		escalateTicketEntry: true,
		forwardTicketEntry: true,
		getTicketEntries: true,
		getTicketEntriesCount: true,
		getTicketEntry: true,
		search: true,
		searchCount: true,
		updatePendingTypes: true,
		updateTicketEntry: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "TicketFeedback",
	{
		addTicketFeedback: true,
		fetchFirstOpenTicketFeedback: true,
		fetchFirstTicketFeedback: true,
		getTicketFeedback: true,
		getTicketFeedbacks: true,
		search: true,
		searchCount: true,
		updateTicketFeedback: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "TicketLink",
	{
		addTicketLink: true,
		deleteTicketLink: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "TicketSolution",
	{
		addTicketSolution: true,
		updateTicketSolution: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.OSB, "TicketWorker",
	{
		addTicketWorkers: true,
		deleteTicketWorkers: true,
		updateTicketWorkers: true
	}
);