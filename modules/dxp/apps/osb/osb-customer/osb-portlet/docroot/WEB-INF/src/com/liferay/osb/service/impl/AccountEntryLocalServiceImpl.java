/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.service.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.exception.AccountEntryCodeException;
import com.liferay.osb.exception.AccountEntryCorpProjectException;
import com.liferay.osb.exception.AccountEntryIndustryException;
import com.liferay.osb.exception.AccountEntryLanguageIdException;
import com.liferay.osb.exception.AccountEntryMaximumCustomersException;
import com.liferay.osb.exception.AccountEntryNameException;
import com.liferay.osb.exception.AccountEntryPartnerEntryException;
import com.liferay.osb.exception.AccountEntrySupportRegionException;
import com.liferay.osb.exception.DuplicateAccountEntryException;
import com.liferay.osb.exception.NoSuchOrderEntryException;
import com.liferay.osb.exception.RequiredAccountEntryException;
import com.liferay.osb.model.AccountAttachment;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.LicenseEntry;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeyConstants;
import com.liferay.osb.model.OfferingDefinitionConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.rabbitmq.ProvisioningAuditRabbitMQConsumer;
import com.liferay.osb.remote.dossiera.DossieraRESTWebServiceUtil;
import com.liferay.osb.service.base.AccountEntryLocalServiceBaseImpl;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.SalesforceConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.AccountEntryLastAuditDateComparator;
import com.liferay.osb.util.comparator.AccountEntryNameComparator;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchCountryException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.util.portlet.PortletProps;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class AccountEntryLocalServiceImpl
	extends AccountEntryLocalServiceBaseImpl {

	public AccountEntry addAccountEntry(
			long userId, String corpProjectUuid, String corpEntryName,
			String name, String code, int type, int industry,
			long partnerEntryId, boolean partnerManagedSupport, int tier,
			int maxCustomers, String instructions, String notes,
			String[] languageIds, long[] supportRegionIds, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId, String ewsaDossieraProjectKey)
		throws PortalException {

		validate(
			0, corpProjectUuid, name, code, type, industry, partnerEntryId,
			maxCustomers, languageIds, supportRegionIds);

		return doAddAccountEntry(
			userId, corpProjectUuid, corpEntryName, name, code, type, industry,
			partnerEntryId, partnerManagedSupport, tier, maxCustomers,
			instructions, notes, languageIds, supportRegionIds, street1,
			street2, street3, city, zip, regionId, countryId,
			ewsaDossieraProjectKey);
	}

	public AccountEntry addAccountEntryWithWorkflow(
			String salesforceOpportunityKey, AccountEntry accountEntry,
			CorpProject corpProject, PartnerEntry partnerEntry, Address address,
			AccountWorker accountWorker, List<OrderEntry> orderEntries,
			List<User> users, ServiceContext serviceContext)
		throws PortalException {

		// Corp project

		if (corpProject.getCorpProjectId() > 0) {
			remoteCorpProjectLocalService.updateCorpProject(
				corpProject.getCorpProjectId(), corpProject.getName());
		}
		else {
			corpProject = remoteCorpProjectLocalService.addCorpProject(
				corpProject.getUserId(), 0, corpProject.getDossieraProjectKey(),
				corpProject.getSalesforceProjectKey(), corpProject.getName());
		}

		// Partner entry

		long partnerEntryId = 0;

		if (partnerEntry != null) {
			partnerEntryId = partnerEntry.getPartnerEntryId();
		}

		// Account entry

		accountEntry = doAddAccountEntry(
			accountEntry.getUserId(), corpProject.getUuid(),
			accountEntry.getCorpEntryName(), accountEntry.getName(),
			accountEntry.getCode(), accountEntry.getType(),
			accountEntry.getIndustry(), partnerEntryId,
			accountEntry.getPartnerManagedSupport(), accountEntry.getTier(),
			accountEntry.getMaxCustomers(), accountEntry.getInstructions(),
			accountEntry.getNotes(), accountEntry.getLanguageIds(),
			accountEntry.getSupportRegionIds(), address.getStreet1(),
			address.getStreet2(), address.getStreet3(), address.getCity(),
			address.getZip(), address.getRegionId(), address.getCountryId(),
			accountEntry.getEWSADossieraProjectKey());

		// Account worker

		if (accountWorker != null) {
			accountWorkerLocalService.addAccountWorkers(
				OSBConstants.USER_DEFAULT_USER_ID,
				new long[] {accountWorker.getUserId()},
				accountEntry.getAccountEntryId(),
				new int[] {accountWorker.getRole()},
				new int[] {accountWorker.getNotifications()});
		}

		// Order entry

		List<OrderEntry> addedOrderEntries = new ArrayList<>();

		for (OrderEntry orderEntry : orderEntries) {
			Calendar startCal = Calendar.getInstance();

			startCal.setTime(orderEntry.getStartDate());

			orderEntry = orderEntryLocalService.addOrderEntry(
				orderEntry.getUserId(), accountEntry.getAccountEntryId(),
				StringPool.BLANK, startCal.get(Calendar.MONTH),
				startCal.get(Calendar.DATE), startCal.get(Calendar.YEAR), false,
				0, 0, 0, WorkflowConstants.STATUS_PENDING_VALIDATION,
				salesforceOpportunityKey, orderEntry.getOfferingEntries());

			addedOrderEntries.add(orderEntry);
		}

		// Users

		ArrayList<User> missingAnalyticsCloudUsers = new ArrayList<>();

		List<User> analyticsCloudUsers =
			(List<User>)serviceContext.getAttribute("analyticsCloudUsers");

		if ((analyticsCloudUsers != null) && !analyticsCloudUsers.isEmpty()) {
			missingAnalyticsCloudUsers = addCorpProjectUsers(
				accountEntry, corpProject, analyticsCloudUsers,
				new long[] {
					OSBConstants.ROLE_OSB_CORP_ANALYTICS_CLOUD_OWNER_ID
				});
		}

		ArrayList<User> missingUsers = addCorpProjectUsers(
			accountEntry, corpProject, users,
			new long[] {
				OSBConstants.ROLE_OSB_CORP_ADMIN_ID,
				OSBConstants.ROLE_OSB_CORP_BUYER_ID,
				OSBConstants.ROLE_OSB_CORP_LCS_USER_ID
			});

		// Workflow

		HashMap<String, Serializable> workflowContext = new HashMap<>();

		if (!missingAnalyticsCloudUsers.isEmpty()) {
			workflowContext.put(
				WorkflowConstants.CONTEXT_MISSING_ANALYTICS_CLOUD_USERS,
				missingAnalyticsCloudUsers);
		}

		if (!missingUsers.isEmpty()) {
			workflowContext.put(
				WorkflowConstants.CONTEXT_MISSING_USERS, missingUsers);
		}

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION,
			Constants.ADD);

		int salesforceOpportunityType = GetterUtil.getInteger(
			serviceContext.getAttribute("salesforceOpportunityType"));

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TYPE,
			salesforceOpportunityType);

		String salesforceOpportunityTaskName =
			SalesforceConstants.getOpportunityTaskName(
				salesforceOpportunityType, Constants.ADD);

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TASK_NAME,
			salesforceOpportunityTaskName);

		workflowContext.put(
			WorkflowConstants.CONTEXT_ORDER_ENTRIES,
			SupportUtil.serialize(addedOrderEntries));
		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_KEY,
			salesforceOpportunityKey);
		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_STAGE_NAME,
			serviceContext.getAttribute("salesforceOpportunityStageName"));

		long[] supportRegionIds = accountEntry.getSupportRegionIds();

		SupportRegion supportRegion =
			supportRegionLocalService.getSupportRegion(supportRegionIds[0]);

		workflowContext.put(
			WorkflowConstants.CONTEXT_SUPPORT_REGION_NAME,
			supportRegion.getName());

		ArrayList<String> warningMessages =
			(ArrayList)serviceContext.getAttribute("warningMessages");

		String code = getCode(
			accountEntry.getCorpEntryName(), accountEntry.getName());

		if (!StringUtil.equalsIgnoreCase(code, accountEntry.getCode())) {
			warningMessages.add("Project code is already taken");
		}

		workflowContext.put(
			WorkflowConstants.CONTEXT_WARNING_MESSAGES, warningMessages);

		ServiceContext workflowServiceContext = new ServiceContext();

		workflowServiceContext.setAttribute("workflowContext", workflowContext);

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			OSBConstants.COMPANY_ID, accountEntry.getUserId(),
			AccountEntry.class.getName(), accountEntry.getAccountEntryId(),
			accountEntry, workflowServiceContext);

		return accountEntry;
	}

	public void addTrialAccountEntry(long userId) throws Exception {
		User user = userLocalService.getUser(userId);

		validateTrial(userId);

		// Corp project

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		String companyName = GetterUtil.getString(
			expandoBridge.getAttribute("osbCompany", false));

		companyName = getTrialName(companyName);

		CorpProject corpProject = remoteCorpProjectLocalService.addCorpProject(
			userId, userId, StringPool.BLANK, StringPool.BLANK, companyName);

		remoteCorpProjectLocalService.addUserCorpProjectRoles(
			corpProject.getUuid(), new long[] {userId},
			OSBConstants.ROLE_OSB_CORP_LCS_USER_ID);

		// Account entry

		String[] industries = (String[])expandoBridge.getAttribute(
			"osbIndustry", false);

		if (ArrayUtil.isEmpty(industries)) {
			industries = new String[] {"other"};
		}

		int industry = SupportUtil.getListTypeIdFromName(
			AccountEntryConstants.LIST_TYPE_INDUSTRY, industries[0], false);

		String[] countries = (String[])expandoBridge.getAttribute(
			"osbCountry", false);

		long countryId = 0;

		if (ArrayUtil.isNotEmpty(countries)) {
			String countryName = LanguageUtil.get(Locale.US, countries[0]);

			if (countryName.equals("Taiwan")) {
				countryName += ", ROC";
			}

			try {
				Country country = countryService.getCountryByName(countryName);

				countryId = country.getCountryId();
			}
			catch (NoSuchCountryException nsce) {
			}
		}

		AccountEntry accountEntry = doAddAccountEntry(
			userId, corpProject.getUuid(), StringPool.BLANK, companyName, null,
			AccountEntryConstants.TYPE_TRIAL, industry, 0L, false,
			AccountEntryConstants.TIER_REGULAR, 1, StringPool.BLANK,
			StringPool.BLANK, new String[0], new long[0], "N/A",
			StringPool.BLANK, StringPool.BLANK, "N/A", "N/A", 0L, countryId,
			StringPool.BLANK);

		if (countryId != 0) {
			accountEntry.setCountryId(countryId);

			accountEntryPersistence.update(accountEntry);
		}

		// Account customer

		accountCustomerLocalService.addAccountCustomer(
			userId, userId, accountEntry.getAccountEntryId(),
			AccountCustomerConstants.ROLE_DEVELOPER,
			AccountCustomerConstants.NOTIFICATIONS_NONE);

		// Order entry

		Calendar cal = Calendar.getInstance();

		OrderEntry orderEntry = orderEntryLocalService.addOrderEntry(
			userId, accountEntry.getAccountEntryId(), StringPool.BLANK,
			cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
			cal.get(Calendar.YEAR), false, cal.get(Calendar.MONTH),
			cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.YEAR),
			WorkflowConstants.STATUS_APPROVED, StringPool.BLANK,
			new ArrayList<OfferingEntry>());

		// Offering entry

		long licenseLifetime = OfferingDefinitionConstants.getLifetimeValue(
			"30-days");
		long supportLifetime = OfferingDefinitionConstants.getLifetimeValue(
			"30-days");

		PortletPreferences portletPreferences =
			AdminUtil.getPortletPreferences();

		long productEntryId = GetterUtil.getLong(
			portletPreferences.getValue("trialProductEntryId", null));
		long supportResponseId = GetterUtil.getLong(
			portletPreferences.getValue("trialSupportResponseId", null));

		OfferingEntry offeringEntry =
			offeringEntryLocalService.addOfferingEntry(
				userId, accountEntry.getAccountEntryId(),
				orderEntry.getOrderEntryId(), productEntryId, supportResponseId,
				"Trial", OfferingEntryConstants.TYPE_TRIAL,
				ProductEntryConstants.DIGITAL_ENTERPRISE_MAJOR_VERSION_7, true,
				licenseLifetime, 0, 0, false, supportLifetime,
				OfferingEntryConstants.SIZING_1,
				OfferingEntryConstants.QUANTITY_UNLIMITED,
				OfferingEntryConstants.STATUS_ACTIVE);

		// License key

		LicenseEntry licenseEntry = licenseEntryLocalService.getLicenseEntry(
			offeringEntry.getProductEntryId(),
			LicenseEntryConstants.TYPE_DEVELOPER);

		int productVersion = getLatestProductVersion(
			portletPreferences, offeringEntry.getProductEntryId());

		LicenseKey licenseKey = licenseKeyLocalService.addLicenseKey(
			userId, null, "Trial Licenses", offeringEntry, licenseEntry, null,
			productVersion, 0, user.getFullName(), 1, 5, "30-Day Trial License",
			new String[0], new String[0], new String[0],
			new String[] {LicenseKeyConstants.SERVER_ID_DEVELOPER}, new Date(),
			null, StringPool.BLANK, false, true);

		licenseKeyLocalService.sendRegisteredEmail(user, licenseKey);

		// LCS

		try {
			lcsSubscriptionEntryLocalService.syncToLCS(
				corpProject.getCorpProjectId());
		}
		catch (Exception e) {
			_log.error(
				"Unable to sync account entry " +
					accountEntry.getAccountEntryId() + " to LCS",
				e);
		}
	}

	public void addWorkflowTask(
			String salesforceOpportunityKey, AccountEntry accountEntry,
			ServiceContext serviceContext)
		throws PortalException {

		HashMap<String, Serializable> workflowContext = new HashMap<>();

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION,
			Constants.VIEW);

		int salesforceOpportunityType = GetterUtil.getInteger(
			serviceContext.getAttribute("salesforceOpportunityType"));

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TYPE,
			salesforceOpportunityType);

		String salesforceOpportunityTaskName =
			SalesforceConstants.getOpportunityTaskName(
				salesforceOpportunityType, Constants.VIEW);

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TASK_NAME,
			salesforceOpportunityTaskName);

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_KEY,
			salesforceOpportunityKey);
		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_STAGE_NAME,
			serviceContext.getAttribute("salesforceOpportunityStageName"));

		List<SupportRegion> supportRegions = accountEntry.getSupportRegions();

		if (!supportRegions.isEmpty()) {
			SupportRegion supportRegion = supportRegions.get(0);

			workflowContext.put(
				WorkflowConstants.CONTEXT_SUPPORT_REGION_NAME,
				supportRegion.getName());
		}

		List<WorkflowTask> workflowTasks = WorkflowTaskManagerUtil.search(
			OSBConstants.COMPANY_ID, 0, null, AccountEntry.class.getName(),
			new Long[] {accountEntry.getAccountEntryId()}, null, null, false,
			null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (WorkflowTask workflowTask : workflowTasks) {
			Map<String, Serializable> curWorkflowContext =
				workflowTask.getOptionalAttributes();

			String salesforceOpportunityAction = GetterUtil.getString(
				curWorkflowContext.get(
					WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION));

			if (!salesforceOpportunityAction.equals(Constants.VIEW)) {
				continue;
			}

			String curSalesforceOpportunityKey = (String)curWorkflowContext.get(
				WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_KEY);

			if (!salesforceOpportunityKey.equals(curSalesforceOpportunityKey)) {
				continue;
			}

			WorkflowTaskManagerUtil.completeWorkflowTask(
				OSBConstants.COMPANY_ID, OSBConstants.USER_AMOS_FONG_USER_ID,
				workflowTask.getWorkflowTaskId(), "close",
				"This view task is out of date.", null);

			Indexer indexer = IndexerRegistryUtil.getIndexer(
				WorkflowTask.class.getName());

			indexer.reindex(workflowTask);
		}

		ServiceContext workflowServiceContext = new ServiceContext();

		workflowServiceContext.setAttribute("workflowContext", workflowContext);

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			OSBConstants.COMPANY_ID, OSBConstants.USER_DEFAULT_USER_ID,
			AccountEntry.class.getName(), accountEntry.getAccountEntryId(),
			accountEntry, workflowServiceContext);
	}

	public void auditAccountEntries() throws PortalException {
		if (!PortletPropsValues.REMOTE_REST_SERVICE_API_DOSSIERA_ENABLED) {
			return;
		}

		int[] types = {
			AccountEntryConstants.TYPE_INTERNAL_TEST,
			AccountEntryConstants.TYPE_TRIAL
		};

		List<AccountEntry> accountEntries =
			accountEntryPersistence.findByRAEI_NotT_S(
				0, types, new int[] {WorkflowConstants.STATUS_APPROVED}, 0, 10,
				new AccountEntryLastAuditDateComparator());

		for (AccountEntry accountEntry : accountEntries) {
			auditAccountEntry(
				OSBConstants.USER_DEFAULT_USER_ID,
				accountEntry.getAccountEntryId());
		}
	}

	public void auditAccountEntry(long userId, long accountEntryId)
		throws PortalException {

		if (!PortletPropsValues.REMOTE_REST_SERVICE_API_DOSSIERA_ENABLED) {
			return;
		}

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		CorpProject corpProject = corpProjectLocalService.fetchCorpProject(
			accountEntry.getCorpProjectId());

		if (corpProject == null) {
			updateLastAuditDate(
				userId, accountEntryId,
				"this-project-cannot-be-found-in-dossiera", StringPool.BLANK);

			return;
		}

		try {
			JSONArray jsonArray =
				DossieraRESTWebServiceUtil.getOpportunitiesJSONArray(
					corpProject.getSalesforceProjectKey());

			if (jsonArray == null) {
				updateLastAuditDate(
					userId, accountEntryId,
					"this-project-cannot-be-found-in-dossiera",
					StringPool.BLANK);

				return;
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("accountEntryId", accountEntryId);
			jsonObject.put("opportunities", jsonArray);
			jsonObject.put("userId", userId);

			ProvisioningAuditRabbitMQConsumer
				provisioningAuditRabbitMQConsumer =
					new ProvisioningAuditRabbitMQConsumer();

			provisioningAuditRabbitMQConsumer.parse(
				StringPool.BLANK, jsonObject.toString(), null);
		}
		catch (Exception e) {
			_log.error(
				"There was an error auditing account entry " +
					accountEntry.getAccountEntryId(),
				e);

			updateLastAuditDate(
				userId, accountEntryId,
				"there-was-an-error-auditing-this-project", StringPool.BLANK);
		}
	}

	@Override
	public AccountEntry deleteAccountEntry(long accountEntryId)
		throws PortalException {

		// Account entry

		if ((accountEntryId == OSBConstants.ACCOUNT_ENTRY_TRIAL_ID) ||
			(accountCustomerPersistence.countByAccountEntryId(accountEntryId) >
				0) ||
			(accountWorkerPersistence.countByAccountEntryId(accountEntryId) >
				0)) {

			throw new RequiredAccountEntryException();
		}

		AccountEntry accountEntry = accountEntryPersistence.remove(
			accountEntryId);

		// Account entries

		accountEntryPersistence.removeByRedirectAccountEntryId(accountEntryId);

		// Account attachments

		List<AccountAttachment> accountAttachments =
			accountAttachmentPersistence.findByAccountEntryId(accountEntryId);

		for (AccountAttachment accountAttachment : accountAttachments) {
			accountAttachmentLocalService.deleteAccountAttachment(
				accountAttachment);
		}

		// Addresses

		addressLocalService.deleteAddresses(
			OSBConstants.COMPANY_ID, AccountEntry.class.getName(),
			accountEntryId);

		// External ids

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());

		externalIdMapperPersistence.removeByC_C(classNameId, accountEntryId);

		// License keys

		licenseKeyPersistence.removeByAccountEntryId(accountEntryId);

		// Offering entries

		offeringEntryPersistence.removeByAccountEntryId(accountEntryId);

		// Order entries

		List<OrderEntry> orderEntries =
			orderEntryPersistence.findByAccountEntryId(accountEntryId);

		for (OrderEntry orderEntry : orderEntries) {
			orderEntryLocalService.deleteOrderEntry(orderEntry);
		}

		return accountEntry;
	}

	@Override
	public AccountEntry fetchCorpProjectAccountEntry(long corpProjectId) {
		return accountEntryPersistence.fetchByCorpProjectId(corpProjectId);
	}

	@Override
	public AccountEntry fetchCorpProjectAccountEntry(String corpProjectUuid) {
		return accountEntryPersistence.fetchByCorpProjectUuid(corpProjectUuid);
	}

	public AccountEntry fetchUserTrialAccountEntry(long userId) {
		return accountEntryPersistence.fetchByU_T_First(
			userId, AccountEntryConstants.TYPE_TRIAL, null);
	}

	@Override
	public List<AccountEntry> getAccountEntries(
		int[] statuses, int start, int end) {

		return accountEntryPersistence.findByRAEI_S(0, statuses, start, end);
	}

	public List<AccountEntry> getAccountEntries(
		int[] notTypes, int[] statuses, int start, int end) {

		return accountEntryPersistence.findByRAEI_NotT_S(
			0, notTypes, statuses, start, end);
	}

	@Override
	public AccountEntry getAccountEntry(long accountEntryId)
		throws PortalException {

		return accountEntryPersistence.findByPrimaryKey(accountEntryId);
	}

	public AccountEntry getAccountEntryByCode(String code)
		throws PortalException {

		AccountEntry accountEntry = accountEntryPersistence.findByCode(code);

		long redirectAccountEntryId = accountEntry.getRedirectAccountEntryId();

		if (redirectAccountEntryId > 0) {
			accountEntry = accountEntryPersistence.findByPrimaryKey(
				redirectAccountEntryId);
		}

		return accountEntry;
	}

	public AccountEntry getAccountEntryByName(String name)
		throws PortalException {

		return accountEntryPersistence.findByName_First(name, null);
	}

	public List<AccountEntry> getActiveAccountEntries(int start, int end) {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("activeSupport", true);
		params.put("expiredSupport", new boolean[] {false, true});

		return accountEntryFinder.findByKeywords(
			null, params, start, end, new AccountEntryNameComparator(true));
	}

	public List<AccountEntry> getPartnerAccountEntries(long partnerEntryId) {
		return accountEntryPersistence.findByPartnerEntryId(partnerEntryId);
	}

	public List<AccountEntry> getRedirectAccountEntries(long accountEntryId) {
		return accountEntryPersistence.findByRedirectAccountEntryId(
			accountEntryId);
	}

	public List<AccountEntry> getSecurityPatchAccountEntries(
		String portletId, LinkedHashMap<String, Object> params) {

		return accountEntryFinder.findBySecurityPatch(portletId, params);
	}

	public List<AccountEntry> getUserAccountEntries(
		long userId, int start, int end) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountEntryMembership", Long.valueOf(userId));
		params.put("status", AccountEntryConstants.STATUSES_ACTIVE);

		return accountEntryFinder.findByKeywords(
			null, params, start, end, new AccountEntryNameComparator(true));
	}

	public int getUserAccountEntriesCount(long userId) {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountEntryMembership", Long.valueOf(userId));
		params.put("status", AccountEntryConstants.STATUSES_ACTIVE);

		return accountEntryFinder.countByKeywords(null, params);
	}

	public List<Long> getUserAccountEntryIds(long userId, int start, int end) {
		List<AccountEntry> accountEntries = getUserAccountEntries(
			userId, start, end);

		List<Long> accountEntryIds = new ArrayList<>(accountEntries.size());

		for (AccountEntry accountEntry : accountEntries) {
			accountEntryIds.add(accountEntry.getAccountEntryId());
		}

		return accountEntryIds;
	}

	public List<AccountEntry> getUserActiveAccountEntries(
		long userId, int start, int end) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountEntryMembership", Long.valueOf(userId));
		params.put("activeSupport", true);
		params.put("expiredSupport", new boolean[] {false, true});

		return accountEntryFinder.findByKeywords(
			null, params, start, end, new AccountEntryNameComparator(true));
	}

	public boolean hasValidLicenseAccountEntry(long userId) {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountEntryMembership", Long.valueOf(userId));
		params.put(
			"activeLicense",
			Long.valueOf(OfferingEntryConstants.STATUS_ACTIVE));

		if (accountEntryFinder.countByKeywords(null, params) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasValidSupportAccountEntry(long userId) {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountEntryMembership", Long.valueOf(userId));
		params.put("activeSupport", true);
		params.put("ticketSupport", true);

		if (accountEntryFinder.countByKeywords(null, params) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void recalculateHighestSupportResponse(long accountEntryId)
		throws PortalException {

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		SupportResponse supportResponse =
			supportResponseFinder.fetchByAccountEntry(accountEntryId);

		if (supportResponse != null) {
			accountEntry.setHighestSupportResponseId(
				supportResponse.getSupportResponseId());
		}
		else {
			accountEntry.setHighestSupportResponseId(0);
		}

		accountEntryPersistence.update(accountEntry);
	}

	public List<AccountEntry> search(
		Long createUserId, int createDateGTDay, int createDateGTMonth,
		int createDateGTYear, int createDateLTDay, int createDateLTMonth,
		int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear, String name,
		String code, int[] industries, Boolean partnerManagedSupport,
		int[] tiers, int[] statuses, String instructions, String notes,
		String partnerEntryCode, String street, Long countryId, Long regionId,
		String city, String zip, LinkedHashMap<String, Object> params,
		boolean andOperator, int start, int end, OrderByComparator obc) {

		Date createDateGT = PortalUtil.getDate(
			createDateGTMonth, createDateGTDay, createDateGTYear);
		Date createDateLT = PortalUtil.getDate(
			createDateLTMonth, createDateLTDay, createDateLTYear);
		Date modifiedDateGT = PortalUtil.getDate(
			modifiedDateGTMonth, modifiedDateGTDay, modifiedDateGTYear);
		Date modifiedDateLT = PortalUtil.getDate(
			modifiedDateLTMonth, modifiedDateLTDay, modifiedDateLTYear);

		return
			accountEntryFinder.findByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
				createUserId, createDateGT, createDateLT, modifiedUserId,
				modifiedDateGT, modifiedDateLT, name, code, industries,
				partnerManagedSupport, tiers, statuses, instructions, notes,
				partnerEntryCode, street, countryId, regionId, city, zip,
				params, andOperator, start, end, obc);
	}

	public List<AccountEntry> search(
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end, OrderByComparator obc) {

		return accountEntryFinder.findByKeywords(
			keywords, params, start, end, obc);
	}

	public List<AccountEntry> search(String name, String code) {
		return accountEntryPersistence.findByN_C_RAEI(
			name, code, AccountEntryConstants.DEFAULT_REDIRECT_ACCOUNT_ENTRYID);
	}

	public int searchCount(
		Long createUserId, int createDateGTDay, int createDateGTMonth,
		int createDateGTYear, int createDateLTDay, int createDateLTMonth,
		int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear, String name,
		String code, int[] industries, Boolean partnerManagedSupport,
		int[] tiers, int[] statuses, String instructions, String notes,
		String partnerEntryCode, String street, Long countryId, Long regionId,
		String city, String zip, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		Date createDateGT = PortalUtil.getDate(
			createDateGTMonth, createDateGTDay, createDateGTYear);
		Date createDateLT = PortalUtil.getDate(
			createDateLTMonth, createDateLTDay, createDateLTYear);
		Date modifiedDateGT = PortalUtil.getDate(
			modifiedDateGTDay, modifiedDateGTMonth, modifiedDateGTYear);
		Date modifiedDateLT = PortalUtil.getDate(
			modifiedDateLTDay, modifiedDateLTMonth, modifiedDateLTYear);

		return
			accountEntryFinder.countByU_CD_MU_MD_N_C_I_PMS_T_S_I_N_P_S_C_R_C_Z(
				createUserId, createDateGT, createDateLT, modifiedUserId,
				modifiedDateGT, modifiedDateLT, name, code, industries,
				partnerManagedSupport, tiers, statuses, instructions, notes,
				partnerEntryCode, street, countryId, regionId, city, zip,
				params, andOperator);
	}

	public int searchCount(
		String keywords, LinkedHashMap<String, Object> params) {

		return accountEntryFinder.countByKeywords(keywords, params);
	}

	public AccountEntry updateAccountEntry(
			long userId, long accountEntryId, String corpProjectUuid,
			String corpEntryName, String name, String code, int type,
			int industry, long partnerEntryId, boolean partnerManagedSupport,
			int tier, int maxCustomers, String instructions, String notes,
			String[] languageIds, long[] supportRegionIds, long addressId,
			String street1, String street2, String street3, String city,
			String zip, long regionId, long countryId,
			String ewsaDossieraProjectKey)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		code = StringUtil.toUpperCase(code);

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		AccountEntry oldAccountEntry = (AccountEntry)accountEntry.clone();

		validate(
			accountEntryId, corpProjectUuid, name, code, type, industry,
			partnerEntryId, maxCustomers, languageIds, supportRegionIds);

		accountEntry.setModifiedUserId(user.getUserId());
		accountEntry.setModifiedUserName(user.getFullName());
		accountEntry.setModifiedDate(new Date());

		accountEntry.setCorpProjectUuid(corpProjectUuid);

		if (Validator.isNotNull(corpProjectUuid)) {
			CorpProject corpProject =
				corpProjectLocalService.fetchCorpProjectByUuid(corpProjectUuid);

			if (corpProject != null) {
				accountEntry.setCorpProjectId(corpProject.getCorpProjectId());
			}
		}

		accountEntry.setCorpEntryName(corpEntryName);
		accountEntry.setName(name);
		accountEntry.setCode(code);
		accountEntry.setRedirectAccountEntryId(0);
		accountEntry.setType(type);
		accountEntry.setIndustry(industry);
		accountEntry.setPartnerEntryId(partnerEntryId);
		accountEntry.setPartnerManagedSupport(partnerManagedSupport);
		accountEntry.setTier(tier);
		accountEntry.setMaxCustomers(maxCustomers);
		accountEntry.setInstructions(instructions);
		accountEntry.setNotes(notes);

		if (addressId <= 0) {
			addressLocalService.addAddress(
				userId, AccountEntry.class.getName(), accountEntryId, street1,
				street2, street3, city, zip, regionId, countryId, 0, false,
				true, new ServiceContext());
		}
		else {
			addressLocalService.updateAddress(
				addressId, street1, street2, street3, city, zip, regionId,
				countryId, 0, false, true);
		}

		accountEntryPersistence.update(accountEntry);

		updateEWSADosseriaProjectKey(accountEntryId, ewsaDossieraProjectKey);

		accountEntryLanguageLocalService.setAccountEntryLanguageIds(
			accountEntryId, languageIds);

		accountEntryPersistence.setSupportRegions(
			accountEntryId, supportRegionIds);

		updateAuditEntry(
			user.getUserId(), user.getFullName(), oldAccountEntry,
			accountEntry);

		return accountEntry;
	}

	public void updateAccountEntryWithWorkflow(
			String salesforceOpportunityKey, AccountEntry accountEntry,
			PartnerEntry partnerEntry, AccountWorker accountWorker,
			Address address, List<OrderEntry> orderEntries, List<User> users,
			ServiceContext serviceContext)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(
			OrderEntry.class.getName());

		List<ExternalIdMapper> externalIdMappers =
			externalIdMapperLocalService.getExternalIdMappers(
				classNameId, ExternalIdMapperConstants.TYPE_SALESFORCE,
				salesforceOpportunityKey);

		if (externalIdMappers.isEmpty()) {
			throw new NoSuchOrderEntryException(
				"No orders found with key " + salesforceOpportunityKey);
		}

		AccountEntry oldAccountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntry.getAccountEntryId());

		Address oldAddress = oldAccountEntry.getAddress();

		String oldAddressString = AdminUtil.formatAddress(oldAddress);

		String addressString = AdminUtil.formatAddress(address);

		String oldCorpEntryName = oldAccountEntry.getCorpEntryName();

		if ((oldAccountEntry.getIndustry() != accountEntry.getIndustry()) ||
			!oldAddressString.equals(addressString) ||
			!oldCorpEntryName.equals(accountEntry.getCorpEntryName())) {

			updateAccountEntry(
				OSBConstants.USER_DEFAULT_USER_ID,
				oldAccountEntry.getAccountEntryId(),
				oldAccountEntry.getCorpProjectUuid(),
				accountEntry.getCorpEntryName(), oldAccountEntry.getName(),
				oldAccountEntry.getCode(), oldAccountEntry.getType(),
				accountEntry.getIndustry(), oldAccountEntry.getPartnerEntryId(),
				oldAccountEntry.getPartnerManagedSupport(),
				oldAccountEntry.getTier(), oldAccountEntry.getMaxCustomers(),
				oldAccountEntry.getInstructions(), oldAccountEntry.getNotes(),
				oldAccountEntry.getLanguageIds(),
				oldAccountEntry.getSupportRegionIds(),
				oldAddress.getAddressId(), address.getStreet1(),
				address.getStreet2(), address.getStreet3(), address.getCity(),
				address.getZip(), address.getRegionId(), address.getCountryId(),
				oldAccountEntry.getEWSADossieraProjectKey());
		}

		if (accountWorker != null) {
			accountWorkerLocalService.addAccountWorkers(
				OSBConstants.USER_DEFAULT_USER_ID,
				new long[] {accountWorker.getUserId()},
				accountEntry.getAccountEntryId(),
				new int[] {accountWorker.getRole()},
				new int[] {accountWorker.getNotifications()});
		}

		ArrayList<User> newUsers = new ArrayList<>();

		ArrayList<User> missingAnalyticsCloudUsers = new ArrayList<>();

		List<User> analyticsCloudUsers =
			(List<User>)serviceContext.getAttribute("analyticsCloudUsers");

		if (analyticsCloudUsers != null) {
			missingAnalyticsCloudUsers = getMissingUsers(analyticsCloudUsers);

			newUsers.addAll(getNewUsers(oldAccountEntry, analyticsCloudUsers));
		}

		ArrayList<User> missingUsers = getMissingUsers(users);

		newUsers.addAll(getNewUsers(oldAccountEntry, users));

		ListUtil.distinct(newUsers);

		HashMap<String, Serializable> workflowContext = new HashMap<>();

		TreeMap<String, String> oldAccountEntryAttributes = new TreeMap<>();
		TreeMap<String, String> newAccountEntryAttributes = new TreeMap<>();

		if (partnerEntry != null) {
			if (oldAccountEntry.getPartnerEntryId() !=
					partnerEntry.getPartnerEntryId()) {

				oldAccountEntryAttributes.put(
					"partnerEntryId",
					String.valueOf(oldAccountEntry.getPartnerEntryId()));
				newAccountEntryAttributes.put(
					"partnerEntryId",
					String.valueOf(partnerEntry.getPartnerEntryId()));
			}
		}
		else if (oldAccountEntry.getPartnerEntryId() > 0) {
			oldAccountEntryAttributes.put(
				"partnerEntryId",
				String.valueOf(oldAccountEntry.getPartnerEntryId()));
			newAccountEntryAttributes.put("partnerEntryId", StringPool.BLANK);
		}

		if (oldAccountEntry.getPartnerManagedSupport() !=
				accountEntry.getPartnerManagedSupport()) {

			oldAccountEntryAttributes.put(
				"partnerManagedSupport",
				String.valueOf(oldAccountEntry.getPartnerManagedSupport()));
			newAccountEntryAttributes.put(
				"partnerManagedSupport",
				String.valueOf(accountEntry.getPartnerManagedSupport()));
		}

		String[] oldLanguageIds = oldAccountEntry.getLanguageIds();
		String[] languageIds = accountEntry.getLanguageIds();

		Arrays.sort(oldLanguageIds);
		Arrays.sort(languageIds);

		if (!Arrays.equals(oldLanguageIds, languageIds)) {
			oldAccountEntryAttributes.put(
				"languageIds", StringUtil.merge(oldLanguageIds));
			newAccountEntryAttributes.put(
				"languageIds", StringUtil.merge(languageIds));
		}

		long[] oldSupportRegionIds = oldAccountEntry.getSupportRegionIds();
		long[] supportRegionIds = accountEntry.getSupportRegionIds();

		Arrays.sort(oldSupportRegionIds);
		Arrays.sort(supportRegionIds);

		if (!Arrays.equals(oldSupportRegionIds, supportRegionIds)) {
			oldAccountEntryAttributes.put(
				"supportRegionIds", StringUtil.merge(oldSupportRegionIds));
			newAccountEntryAttributes.put(
				"supportRegionIds", StringUtil.merge(supportRegionIds));
		}

		String oldEWSADossieraProjectKey =
			oldAccountEntry.getEWSADossieraProjectKey();

		if (!oldEWSADossieraProjectKey.equals(
				accountEntry.getEWSADossieraProjectKey())) {

			oldAccountEntryAttributes.put(
				"ewsaDossieraProjectKey",
				oldAccountEntry.getEWSADossieraProjectKey());
			newAccountEntryAttributes.put(
				"ewsaDossieraProjectKey",
				accountEntry.getEWSADossieraProjectKey());
		}

		List<OrderEntry> externalIdMapperOrderEntries = new ArrayList<>();

		for (ExternalIdMapper externalIdMapper : externalIdMappers) {
			OrderEntry orderEntry = orderEntryLocalService.getOrderEntry(
				externalIdMapper.getClassPK());

			externalIdMapperOrderEntries.add(orderEntry);
		}

		Map<String, Integer> oldOfferingEntriesMap =
			SupportUtil.getOfferingEntriesMap(externalIdMapperOrderEntries);

		Map<String, Integer> offeringEntriesMap =
			SupportUtil.getOfferingEntriesMap(orderEntries);

		if (!oldOfferingEntriesMap.equals(offeringEntriesMap)) {
			workflowContext.put(
				WorkflowConstants.CONTEXT_ORDER_ENTRIES,
				SupportUtil.serialize(orderEntries));
		}

		if (workflowContext.isEmpty() && oldAccountEntryAttributes.isEmpty() &&
			newAccountEntryAttributes.isEmpty()) {

			return;
		}

		String oldAccountEntryName = oldAccountEntry.getName();

		if (!oldAccountEntryName.equals(accountEntry.getName())) {
			oldAccountEntryAttributes.put("name", oldAccountEntryName);
			newAccountEntryAttributes.put("name", accountEntry.getName());
		}

		String notes = oldAccountEntry.getNotes();

		if (!notes.equals(accountEntry.getNotes())) {
			oldAccountEntryAttributes.put("notes", notes);
			newAccountEntryAttributes.put("notes", accountEntry.getNotes());
		}

		if (!oldAccountEntryAttributes.isEmpty() &&
			!newAccountEntryAttributes.isEmpty()) {

			workflowContext.put(
				WorkflowConstants.CONTEXT_OLD_ACCOUNT_ENTRY_ATTRIBUTES,
				oldAccountEntryAttributes);
			workflowContext.put(
				WorkflowConstants.CONTEXT_NEW_ACCOUNT_ENTRY_ATTRIBUTES,
				newAccountEntryAttributes);
		}

		if (!missingAnalyticsCloudUsers.isEmpty()) {
			workflowContext.put(
				WorkflowConstants.CONTEXT_MISSING_ANALYTICS_CLOUD_USERS,
				missingAnalyticsCloudUsers);
		}

		if (!missingUsers.isEmpty()) {
			workflowContext.put(
				WorkflowConstants.CONTEXT_MISSING_USERS, missingUsers);
		}

		if (!newUsers.isEmpty()) {
			workflowContext.put(WorkflowConstants.CONTEXT_NEW_USERS, newUsers);
		}

		List<Long> existingOrderEntryIds = new ArrayList<>();

		for (ExternalIdMapper externalIdMapper : externalIdMappers) {
			OrderEntry orderEntry = orderEntryPersistence.findByPrimaryKey(
				externalIdMapper.getClassPK());

			if (orderEntry.getAccountEntryId() !=
					accountEntry.getAccountEntryId()) {

				continue;
			}

			existingOrderEntryIds.add(externalIdMapper.getClassPK());
		}

		workflowContext.put(
			WorkflowConstants.CONTEXT_EXISTING_ORDER_ENTRY_IDS,
			StringUtil.merge(existingOrderEntryIds));

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION,
			Constants.UPDATE);

		int salesforceOpportunityType = GetterUtil.getInteger(
			serviceContext.getAttribute("salesforceOpportunityType"));

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TYPE,
			salesforceOpportunityType);

		String salesforceOpportunityTaskName =
			SalesforceConstants.getOpportunityTaskName(
				salesforceOpportunityType, Constants.UPDATE);

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TASK_NAME,
			salesforceOpportunityTaskName);

		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_KEY,
			salesforceOpportunityKey);
		workflowContext.put(
			WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_STAGE_NAME,
			serviceContext.getAttribute("salesforceOpportunityStageName"));

		List<SupportRegion> supportRegions =
			oldAccountEntry.getSupportRegions();

		if (!supportRegions.isEmpty()) {
			SupportRegion supportRegion = supportRegions.get(0);

			workflowContext.put(
				WorkflowConstants.CONTEXT_SUPPORT_REGION_NAME,
				supportRegion.getName());
		}

		workflowContext.put(
			WorkflowConstants.CONTEXT_WARNING_MESSAGES,
			serviceContext.getAttribute("warningMessages"));

		List<WorkflowTask> workflowTasks = WorkflowTaskManagerUtil.search(
			OSBConstants.COMPANY_ID, 0, null, AccountEntry.class.getName(),
			new Long[] {accountEntry.getAccountEntryId()}, null, null, false,
			null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (WorkflowTask workflowTask : workflowTasks) {
			Map<String, Serializable> curWorkflowContext =
				workflowTask.getOptionalAttributes();

			String salesforceOpportunityAction = GetterUtil.getString(
				curWorkflowContext.get(
					WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION));

			if (!salesforceOpportunityAction.equals(Constants.UPDATE)) {
				continue;
			}

			String curSalesforceOpportunityKey = (String)curWorkflowContext.get(
				WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_KEY);

			if (!salesforceOpportunityKey.equals(curSalesforceOpportunityKey)) {
				continue;
			}

			WorkflowTaskManagerUtil.completeWorkflowTask(
				OSBConstants.COMPANY_ID, OSBConstants.USER_AMOS_FONG_USER_ID,
				workflowTask.getWorkflowTaskId(), "close",
				"This update task is out of date.", null);

			Indexer indexer = IndexerRegistryUtil.getIndexer(
				WorkflowTask.class.getName());

			indexer.reindex(workflowTask);
		}

		ServiceContext workflowServiceContext = new ServiceContext();

		workflowServiceContext.setAttribute("workflowContext", workflowContext);

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			OSBConstants.COMPANY_ID, OSBConstants.USER_DEFAULT_USER_ID,
			AccountEntry.class.getName(), oldAccountEntry.getAccountEntryId(),
			oldAccountEntry, workflowServiceContext);
	}

	public void updateLastAuditDate(
			long userId, long accountEntryId, String auditLabel,
			String auditValue)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		accountEntry.setLastAuditDate(now);

		accountEntryPersistence.update(accountEntry);

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId, accountEntryId, 0,
			classNameId, accountEntryId, AuditEntryConstants.ACTION_AUDIT,
			AuditEntryConstants.FIELD_NOT_APPLICABLE, VisibilityConstants.ADMIN,
			auditLabel, auditValue, StringPool.BLANK, StringPool.BLANK, true,
			false);
	}

	public void updateStatus(long accountEntryId) throws PortalException {
		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		int oldStatus = accountEntry.getStatus();

		if ((oldStatus == WorkflowConstants.STATUS_PENDING) ||
			(oldStatus == WorkflowConstants.STATUS_REJECTED)) {

			return;
		}

		accountEntry.setStatus(getStatus(accountEntryId));

		accountEntryPersistence.update(accountEntry);

		if ((oldStatus != accountEntry.getStatus()) &&
			(accountEntry.getStatus() == WorkflowConstants.STATUS_CLOSED)) {

			long classNameId = classNameLocalService.getClassNameId(
				AccountEntry.class.getName());

			auditEntryLocalService.addAuditEntry(
				OSBConstants.USER_DEFAULT_USER_ID, StringPool.BLANK, new Date(),
				classNameId, accountEntryId, 0, classNameId, accountEntryId,
				AuditEntryConstants.ACTION_UPDATE,
				AuditEntryConstants.FIELD_STATUS, VisibilityConstants.ADMIN,
				WorkflowConstants.getStatusLabel(oldStatus),
				String.valueOf(oldStatus), accountEntry.getStatusLabel(),
				String.valueOf(accountEntry.getStatus()));
		}
	}

	public AccountEntry updateStatus(
			long userId, long accountEntryId, int status,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		if ((accountEntry.getStatus() != WorkflowConstants.STATUS_APPROVED) &&
			(status == WorkflowConstants.STATUS_APPROVED)) {

			validate(
				accountEntryId, accountEntry.getCorpProjectUuid(),
				accountEntry.getName(), accountEntry.getCode(),
				accountEntry.getType(), accountEntry.getIndustry(),
				accountEntry.getPartnerEntryId(),
				accountEntry.getMaxCustomers(), accountEntry.getLanguageIds(),
				accountEntry.getSupportRegionIds());
		}

		List<User> missingAnalyticsCloudUsers = new ArrayList<>();
		List<User> missingUsers = new ArrayList<>();

		if ((accountEntry.getStatus() != WorkflowConstants.STATUS_APPROVED) &&
			(accountEntry.getStatus() != status)) {

			// Account entry

			accountEntry.setModifiedDate(serviceContext.getModifiedDate(now));

			if (status == WorkflowConstants.STATUS_APPROVED) {
				missingAnalyticsCloudUsers =
					(List<User>)serviceContext.getAttribute(
						"missingAnalyticsCloudUsers");

				if (missingAnalyticsCloudUsers == null) {
					missingAnalyticsCloudUsers = new ArrayList<>();
				}

				missingUsers = (List<User>)serviceContext.getAttribute(
					"missingUsers");

				if (missingUsers == null) {
					missingUsers = new ArrayList<>();
				}

				accountEntry.setStatus(getStatus(accountEntryId));
			}
			else {
				accountEntry.setStatus(status);
			}

			accountEntry.setStatusByUserId(userId);
			accountEntry.setStatusByUserName(user.getFullName());
			accountEntry.setStatusDate(serviceContext.getModifiedDate(now));

			accountEntry = accountEntryPersistence.update(accountEntry);
		}

		if ((status == WorkflowConstants.STATUS_APPROVED) ||
			(status == WorkflowConstants.STATUS_REJECTED)) {

			// Order entries

			Long[] orderEntryIds = (Long[])serviceContext.getAttribute(
				"orderEntryIds");

			if (orderEntryIds != null) {
				for (long orderEntryId : orderEntryIds) {
					orderEntryLocalService.updateStatus(
						userId, orderEntryId, status, serviceContext);
				}
			}
		}

		if ((status == WorkflowConstants.STATUS_APPROVED) &&
			(!missingAnalyticsCloudUsers.isEmpty() ||
			 !missingUsers.isEmpty())) {

			List<User> users = new ArrayList<>(missingAnalyticsCloudUsers);

			sendUserCreationNotification(
				users, accountEntry,
				"Analytics Cloud, Customer Portal, all of our downloads, and " +
					"our support system");

			users = new ArrayList<>(missingUsers);

			users.removeAll(missingAnalyticsCloudUsers);

			sendUserCreationNotification(
				users, accountEntry,
				"Customer Portal, all of our downloads, and our support " +
					"system");
		}

		return accountEntry;
	}

	public AccountEntry updateStatus(
			long userId, long accountEntryId, String salesforceOpportunityKey,
			int status, ServiceContext serviceContext)
		throws PortalException {

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		if ((status != WorkflowConstants.STATUS_APPROVED) &&
			(status != WorkflowConstants.STATUS_REJECTED)) {

			return accountEntry;
		}

		if (accountEntry.getStatus() == WorkflowConstants.STATUS_PENDING) {
			List<WorkflowTask> workflowTasks = WorkflowTaskManagerUtil.search(
				OSBConstants.COMPANY_ID, 0, null, AccountEntry.class.getName(),
				new Long[] {accountEntry.getAccountEntryId()}, null, null,
				false, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (WorkflowTask workflowTask : workflowTasks) {
				Map<String, Serializable> workflowContext =
					workflowTask.getOptionalAttributes();

				String curSalesforceOpportunityKey =
					(String)workflowContext.get(
						WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_KEY);

				if (!salesforceOpportunityKey.equals(
						curSalesforceOpportunityKey)) {

					continue;
				}

				WorkflowTaskManagerUtil.completeWorkflowTask(
					OSBConstants.COMPANY_ID,
					OSBConstants.USER_AMOS_FONG_USER_ID,
					workflowTask.getWorkflowTaskId(), "close",
					"This task was closed by an update task.", null);

				Indexer indexer = IndexerRegistryUtil.getIndexer(
					WorkflowTask.class.getName());

				indexer.reindex(workflowTask);
			}
		}

		if ((accountEntry.getStatus() == WorkflowConstants.STATUS_PENDING) ||
			((accountEntry.getStatus() == WorkflowConstants.STATUS_REJECTED) &&
			 (status == WorkflowConstants.STATUS_APPROVED))) {

			updateStatus(userId, accountEntryId, status, serviceContext);
		}

		List<ExternalIdMapper> externalIdMappers =
			externalIdMapperLocalService.getExternalIdMappers(
				classNameLocalService.getClassNameId(OrderEntry.class),
				ExternalIdMapperConstants.TYPE_SALESFORCE,
				salesforceOpportunityKey);

		int[] orderEntryStatuses = {
			WorkflowConstants.STATUS_PENDING,
			WorkflowConstants.STATUS_PENDING_VALIDATION
		};

		if (status == WorkflowConstants.STATUS_APPROVED) {
			orderEntryStatuses = ArrayUtil.append(
				orderEntryStatuses, WorkflowConstants.STATUS_REJECTED);
		}

		for (ExternalIdMapper externalIdMapper : externalIdMappers) {
			OrderEntry orderEntry = orderEntryPersistence.findByPrimaryKey(
				externalIdMapper.getClassPK());

			if (orderEntry.getAccountEntryId() != accountEntryId) {
				continue;
			}

			int orderEntryStatus = orderEntry.getStatus();

			if (!ArrayUtil.contains(orderEntryStatuses, orderEntryStatus)) {
				continue;
			}

			orderEntryLocalService.updateStatus(
				userId, orderEntry.getOrderEntryId(), status, serviceContext);

			if (orderEntryStatus != WorkflowConstants.STATUS_PENDING) {
				continue;
			}

			List<WorkflowTask> workflowTasks = WorkflowTaskManagerUtil.search(
				OSBConstants.COMPANY_ID, 0, null, OrderEntry.class.getName(),
				new Long[] {orderEntry.getOrderEntryId()}, null, null, false,
				null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (WorkflowTask workflowTask : workflowTasks) {
				WorkflowTaskManagerUtil.completeWorkflowTask(
					OSBConstants.COMPANY_ID,
					OSBConstants.USER_AMOS_FONG_USER_ID,
					workflowTask.getWorkflowTaskId(), "close",
					"This task was closed by an update task.", null);

				Indexer indexer = IndexerRegistryUtil.getIndexer(
					WorkflowTask.class.getName());

				indexer.reindex(workflowTask);
			}
		}

		return accountEntry;
	}

	public void validate(AccountEntry accountEntry) throws PortalException {
		validate(
			accountEntry.getAccountEntryId(), accountEntry.getCorpProjectUuid(),
			accountEntry.getName(), accountEntry.getCode(),
			accountEntry.getType(), accountEntry.getIndustry(),
			accountEntry.getPartnerEntryId(), accountEntry.getMaxCustomers(),
			accountEntry.getLanguageIds(), accountEntry.getSupportRegionIds());
	}

	protected ArrayList<User> addCorpProjectUsers(
			AccountEntry accountEntry, CorpProject corpProject,
			List<User> users, long[] roleIds)
		throws PortalException {

		ArrayList<User> missingUsers = new ArrayList<>(users);

		Iterator<User> itr = missingUsers.iterator();

		while (itr.hasNext()) {
			User user = itr.next();

			if (user.getUserId() <= 0) {
				continue;
			}

			remoteCorpProjectLocalService.addCorpProjectUsers(
				corpProject.getCorpProjectId(), new long[] {user.getUserId()});

			for (long roleId : roleIds) {
				remoteCorpProjectLocalService.addUserCorpProjectRoles(
					corpProject.getCorpProjectId(),
					new long[] {user.getUserId()}, roleId);
			}

			accountCustomerLocalService.addAccountCustomer(
				accountEntry.getUserId(), user.getUserId(),
				accountEntry.getAccountEntryId(),
				AccountCustomerConstants.ROLE_WATCHER,
				AccountCustomerConstants.NOTIFICATIONS_ALL);

			itr.remove();
		}

		return missingUsers;
	}

	protected AccountEntry doAddAccountEntry(
			long userId, String corpProjectUuid, String corpEntryName,
			String name, String code, int type, int industry,
			long partnerEntryId, boolean partnerManagedSupport, int tier,
			int maxCustomers, String instructions, String notes,
			String[] languageIds, long[] supportRegionIds, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId, String ewsaDossieraProjectKey)
		throws PortalException {

		// Account entry

		User user = userLocalService.getUser(userId);
		code = getCode(corpEntryName, name, code);
		Date now = new Date();

		long accountEntryId = counterLocalService.increment();

		AccountEntry accountEntry = accountEntryPersistence.create(
			accountEntryId);

		accountEntry.setCompanyId(OSBConstants.COMPANY_ID);
		accountEntry.setUserId(user.getUserId());
		accountEntry.setUserName(user.getFullName());
		accountEntry.setCreateDate(now);
		accountEntry.setModifiedUserId(user.getUserId());
		accountEntry.setModifiedUserName(user.getFullName());
		accountEntry.setModifiedDate(now);

		accountEntry.setCorpProjectUuid(corpProjectUuid);

		if (Validator.isNotNull(corpProjectUuid)) {
			CorpProject corpProject =
				corpProjectLocalService.fetchCorpProjectByUuid(corpProjectUuid);

			if (corpProject != null) {
				accountEntry.setCorpProjectId(corpProject.getCorpProjectId());
			}
		}

		accountEntry.setCorpEntryName(corpEntryName);
		accountEntry.setName(name);
		accountEntry.setCode(code);
		accountEntry.setType(type);
		accountEntry.setIndustry(industry);
		accountEntry.setPartnerEntryId(partnerEntryId);
		accountEntry.setPartnerManagedSupport(partnerManagedSupport);
		accountEntry.setTier(tier);
		accountEntry.setMaxCustomers(maxCustomers);
		accountEntry.setInstructions(instructions);
		accountEntry.setNotes(notes);
		accountEntry.setStatus(WorkflowConstants.STATUS_CLOSED);

		accountEntryPersistence.update(accountEntry);

		// Address

		addressLocalService.addAddress(
			userId, AccountEntry.class.getName(),
			accountEntry.getAccountEntryId(), street1, street2, street3, city,
			zip, regionId, countryId, 0, false, true, new ServiceContext());

		// External ids

		updateEWSADosseriaProjectKey(accountEntryId, ewsaDossieraProjectKey);

		// Languages

		accountEntryLanguageLocalService.setAccountEntryLanguageIds(
			accountEntryId, languageIds);

		// Support regions

		accountEntryPersistence.setSupportRegions(
			accountEntryId, supportRegionIds);

		return accountEntry;
	}

	protected String formatLanguageIds(String[] languageIds) {
		List<String> formattedLanguageIds = new ArrayList<>(languageIds.length);

		for (String languageId : languageIds) {
			Locale locale = LocaleUtil.fromLanguageId(languageId);

			formattedLanguageIds.add(locale.getDisplayName());
		}

		return StringUtil.merge(formattedLanguageIds);
	}

	protected String formatSupportRegionIds(long[] supportRegionIds) {
		List<String> supportRegionNames = new ArrayList<>(
			supportRegionIds.length);

		for (long supportRegionId : supportRegionIds) {
			SupportRegion supportRegion =
				supportRegionPersistence.fetchByPrimaryKey(supportRegionId);

			if (supportRegion != null) {
				supportRegionNames.add(supportRegion.getName());
			}
		}

		return StringUtil.merge(supportRegionNames);
	}

	protected String getCode(String corpEntryName, String name) {
		String code = StringUtil.extractChars(corpEntryName);

		if (code.length() > 6) {
			code = code.substring(0, 6);
		}

		code += StringUtil.extractChars(name);

		if (code.length() > 12) {
			code = code.substring(0, 12);
		}

		return StringUtil.toUpperCase(code);
	}

	protected String getCode(String corpEntryName, String name, String code) {
		if (Validator.isNull(code)) {
			code = getCode(corpEntryName, name);

			if (!isDuplicateCode(code)) {
				return code;
			}

			int max = (int)Math.pow(10, 15 - code.length());

			for (int i = 1; i < max; i++) {
				String tempCode = code + i;

				if (!isDuplicateCode(tempCode)) {
					return tempCode;
				}
			}

			code = code.substring(0, code.length() - 1);

			code = getCode(corpEntryName, code, null);
		}

		return StringUtil.toUpperCase(code);
	}

	protected int getLatestProductVersion(
			PortletPreferences portletPreferences, long productEntryId)
		throws PortalException {

		int trialLiferayVersion = GetterUtil.getInteger(
			portletPreferences.getValue("trialLiferayVersion", null));

		if (trialLiferayVersion > 0) {
			return trialLiferayVersion;
		}

		ProductEntry productEntry = productEntryLocalService.getProductEntry(
			productEntryId);

		String listType =
			ProductEntry.class.getName() + StringPool.PERIOD +
				productEntry.getVersionsListType();

		List<ListType> productVersionTypes = listTypeLocalService.getListTypes(
			listType);

		ListType latestProductVersionType = productVersionTypes.get(
			productVersionTypes.size() - 1);

		String name = latestProductVersionType.getName();

		if (name.equals("other")) {
			latestProductVersionType = productVersionTypes.get(
				productVersionTypes.size() - 2);
		}

		return (int)latestProductVersionType.getListTypeId();
	}

	protected ArrayList<User> getMissingUsers(List<User> users) {
		ArrayList<User> missingUsers = new ArrayList<>();

		for (User user : users) {
			if (user.getUserId() <= 0) {
				missingUsers.add(user);
			}
		}

		return missingUsers;
	}

	protected ArrayList<User> getNewUsers(
		AccountEntry accountEntry, List<User> users) {

		ArrayList<User> newUsers = new ArrayList<>();

		for (User user : users) {
			if (user.getUserId() <= 0) {
				continue;
			}

			AccountCustomer accountCustomer =
				accountCustomerLocalService.fetchAccountCustomer(
					user.getUserId(), accountEntry.getAccountEntryId());

			if (accountCustomer == null) {
				newUsers.add(user);
			}
		}

		return newUsers;
	}

	protected int getStatus(long accountEntryId) throws PortalException {
		List<OfferingEntry> offeringEntries =
			offeringEntryPersistence.findByAccountEntryId(accountEntryId);

		int status = WorkflowConstants.STATUS_CLOSED;

		Date now = new Date();

		for (OfferingEntry offeringEntry : offeringEntries) {
			if (offeringEntry.getStatus() !=
					OfferingEntryConstants.STATUS_ACTIVE) {

				continue;
			}

			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (productEntry.getType() != ProductEntryConstants.TYPE_PRIMARY) {
				continue;
			}

			Calendar cal = Calendar.getInstance();

			cal.setTime(offeringEntry.getSupportEndDate());

			if (offeringEntry.getType() != OfferingEntryConstants.TYPE_TRIAL) {
				cal.add(Calendar.DAY_OF_YEAR, 30);
			}

			if (now.before(cal.getTime())) {
				return WorkflowConstants.STATUS_APPROVED;
			}
			else {
				status = WorkflowConstants.STATUS_EXPIRED;
			}
		}

		return status;
	}

	protected String getTrialName(String name) {
		String trialName = "Trial - " + name;

		int count = 1;

		while (isDuplicateTrialName(trialName)) {
			trialName = "Trial - " + name + StringPool.SPACE + count;

			count++;
		}

		return trialName;
	}

	protected boolean isDuplicateCode(String code) {
		if (accountEntryPersistence.countByCode(code) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isDuplicateTrialName(String name) {
		if (accountEntryPersistence.countByName(name) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	protected void sendEmail(AccountEntry accountEntry, int oldTier)
		throws PortalException {

		PortletPreferences preferences = AdminUtil.getPortletPreferences();

		String fromName = PrefsPropsUtil.getString(
			OSBConstants.COMPANY_ID, PropsKeys.ADMIN_EMAIL_FROM_NAME);

		Map<Locale, String> subjectMap =
			SupportUtil.getEmailAccountEntryTierSubjectMap(preferences);
		Map<Locale, String> bodyMap =
			SupportUtil.getEmailAccountEntryTierBodyMap(preferences);

		for (Map.Entry<Locale, String> subjectEntry : subjectMap.entrySet()) {
			String subject = StringUtil.replace(
				subjectEntry.getValue(),
				new String[] {"[$ACCOUNT_ENTRY_NAME$]"},
				new String[] {accountEntry.getName()});

			subjectEntry.setValue(subject);
		}

		for (Map.Entry<Locale, String> bodyEntry : bodyMap.entrySet()) {
			Locale locale = bodyEntry.getKey();

			String body = StringUtil.replace(
				bodyEntry.getValue(),
				new String[] {
					"[$ACCOUNT_ENTRY_NAME$]", "[$OLD_TIER$]", "[$TIER$]"
				},
				new String[] {
					accountEntry.getName(),
					LanguageUtil.get(
						locale, AccountEntryConstants.getTierLabel(oldTier)),
					LanguageUtil.get(
						locale,
						AccountEntryConstants.getTierLabel(
							accountEntry.getTier()))
				});

			bodyEntry.setValue(body);
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
		subscriptionSender.setFrom(
			PortletPropsValues.SUPPORT_EMAIL_ADDRESS_FROM, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"account_entry", accountEntry.getAccountEntryId());

		List<AccountWorker> accountWorkers =
			accountWorkerLocalService.getAccountWorkers(
				accountEntry.getAccountEntryId());

		for (AccountWorker accountWorker : accountWorkers) {
			if (accountWorker.getNotifications() ==
					AccountWorkerConstants.NOTIFICATIONS_NONE) {

				continue;
			}

			User user = userLocalService.getUser(accountWorker.getUserId());

			if (organizationLocalService.hasUserOrganization(
					user.getUserId(),
					OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				subscriptionSender.addRuntimeSubscribers(
					user.getEmailAddress(), user.getFullName());
			}
		}

		subscriptionSender.flushNotificationsAsync();
	}

	protected void sendUserCreationNotification(
		List<User> users, AccountEntry accountEntry,
		String subscriptionServices) {

		if ((users == null) || users.isEmpty()) {
			return;
		}

		ListUtil.distinct(users);

		String supportRegionName = StringPool.BLANK;

		List<SupportRegion> supportRegions = accountEntry.getSupportRegions();

		if (!supportRegions.isEmpty()) {
			SupportRegion supportRegion = supportRegions.get(0);

			supportRegionName = supportRegion.getName();
		}

		String provisioningEmailAddress = PortletProps.get(
			PortletPropsKeys.PROVISIONING_EMAIL_ADDRESS,
			new Filter(supportRegionName));

		if (Validator.isNull(provisioningEmailAddress)) {
			provisioningEmailAddress = PortletProps.get(
				PortletPropsKeys.PROVISIONING_EMAIL_ADDRESS,
				new Filter("Global"));
		}

		Locale locale = LocaleUtil.getDefault();

		String[] languageIds = accountEntry.getLanguageIds();

		if (!ArrayUtil.isEmpty(languageIds)) {
			locale = LocaleUtil.fromLanguageId(languageIds[0]);
		}

		PortletPreferences portletPreferences =
			AdminUtil.getPortletPreferences();

		Map<Locale, String> subjectMap =
			AdminUtil.getEmailProvisioningCreateAccountSubjectMap(
				portletPreferences);
		Map<Locale, String> bodyMap =
			AdminUtil.getEmailProvisioningCreateAccountBodyMap(
				portletPreferences);

		String subject = subjectMap.get(locale);

		if (Validator.isNull(subject)) {
			subject = subjectMap.get(LocaleUtil.getDefault());
		}

		String body = bodyMap.get(locale);

		if (Validator.isNull(body)) {
			body = bodyMap.get(LocaleUtil.getDefault());
		}

		for (User user : users) {
			User curUser = userLocalService.fetchUserByEmailAddress(
				OSBConstants.COMPANY_ID, user.getEmailAddress());

			if (curUser != null) {
				continue;
			}

			user.setLanguageId(LocaleUtil.toLanguageId(locale));

			SubscriptionSender subscriptionSender = new SubscriptionSender();

			subscriptionSender.setBody(body);
			subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
			subscriptionSender.setContextAttributes(
				"[$ACCOUNT_ENTRY_NAME$]", accountEntry.getName(),
				"[$SUBSCRIPTION_SERVICES$]", subscriptionServices);
			subscriptionSender.setFrom(
				provisioningEmailAddress, "Liferay Provisioning");
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setMailId("provisioning");
			subscriptionSender.setReplyToAddress(provisioningEmailAddress);
			subscriptionSender.setSubject(subject);

			subscriptionSender.addRuntimeSubscribers(
				user.getEmailAddress(), user.getFullName());
			subscriptionSender.addRuntimeSubscribers(
				provisioningEmailAddress, user.getFullName());

			subscriptionSender.flushNotificationsAsync();
		}
	}

	protected void updateAuditEntry(
			long userId, String userName, AccountEntry oldAccountEntry,
			AccountEntry accountEntry)
		throws PortalException {

		long classPK = accountEntry.getAccountEntryId();
		Date createDate = accountEntry.getModifiedDate();
		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long auditSetId = auditEntryLocalService.getNextAuditSetId(
			AccountEntry.class.getName(), classPK);
		int auditAction = AuditEntryConstants.ACTION_UPDATE;

		if (oldAccountEntry.getCorpProjectId() !=
				accountEntry.getCorpProjectId()) {

			CorpProject oldCorpProject =
				corpProjectLocalService.fetchCorpProject(
					oldAccountEntry.getCorpProjectId());
			CorpProject corpProject = corpProjectLocalService.fetchCorpProject(
				accountEntry.getCorpProjectId());

			String oldCorpProjectString = StringPool.BLANK;
			String corpProjectString = StringPool.BLANK;

			if (oldCorpProject != null) {
				oldCorpProjectString = oldCorpProject.getName();
			}

			if (corpProject != null) {
				corpProjectString = corpProject.getName();
			}

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_CORP_PROJECT,
				VisibilityConstants.LIFERAY_INC, oldCorpProjectString,
				String.valueOf(oldAccountEntry.getCorpProjectId()),
				corpProjectString,
				String.valueOf(accountEntry.getCorpProjectId()));
		}

		String oldCorpEntryName = oldAccountEntry.getCorpEntryName();

		if (!oldCorpEntryName.equals(accountEntry.getCorpEntryName())) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_CORP_ENTRY_NAME,
				VisibilityConstants.LIFERAY_INC, StringPool.BLANK,
				oldCorpEntryName, StringPool.BLANK,
				accountEntry.getCorpEntryName());
		}

		String oldName = oldAccountEntry.getName();

		if (!oldName.equals(accountEntry.getName())) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_NAME, VisibilityConstants.LIFERAY_INC,
				StringPool.BLANK, oldName, StringPool.BLANK,
				accountEntry.getName());
		}

		String oldCode = oldAccountEntry.getCode();

		if (!oldCode.equals(accountEntry.getCode())) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_CODE, VisibilityConstants.LIFERAY_INC,
				StringPool.BLANK, oldCode, StringPool.BLANK,
				accountEntry.getCode());
		}

		if (oldAccountEntry.getType() != accountEntry.getType()) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_TYPE, VisibilityConstants.LIFERAY_INC,
				oldAccountEntry.getTypeLabel(),
				String.valueOf(oldAccountEntry.getType()),
				accountEntry.getTypeLabel(),
				String.valueOf(accountEntry.getType()));
		}

		if (oldAccountEntry.getIndustry() != accountEntry.getIndustry()) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_INDUSTRY,
				VisibilityConstants.LIFERAY_INC,
				oldAccountEntry.getIndustryLabel(),
				String.valueOf(oldAccountEntry.getIndustry()),
				accountEntry.getIndustryLabel(),
				String.valueOf(accountEntry.getIndustry()));
		}

		if (oldAccountEntry.getPartnerEntryId() !=
				accountEntry.getPartnerEntryId()) {

			String oldPartnerEntryCode = StringPool.BLANK;
			String partnerEntryCode = StringPool.BLANK;

			PartnerEntry oldPartnerEntry =
				partnerEntryPersistence.fetchByPrimaryKey(
					oldAccountEntry.getPartnerEntryId());
			PartnerEntry partnerEntry =
				partnerEntryPersistence.fetchByPrimaryKey(
					accountEntry.getPartnerEntryId());

			if (oldPartnerEntry != null) {
				oldPartnerEntryCode = oldPartnerEntry.getCode();
			}

			if (partnerEntry != null) {
				partnerEntryCode = partnerEntry.getCode();
			}

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_PARTNER,
				VisibilityConstants.LIFERAY_INC, oldPartnerEntryCode,
				String.valueOf(oldAccountEntry.getPartnerEntryId()),
				partnerEntryCode,
				String.valueOf(accountEntry.getPartnerEntryId()));
		}

		if (oldAccountEntry.getPartnerManagedSupport() !=
				accountEntry.getPartnerManagedSupport()) {

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_PARTNER_MANAGED_SUPPORT,
				VisibilityConstants.LIFERAY_INC, StringPool.BLANK,
				String.valueOf(oldAccountEntry.getPartnerManagedSupport()),
				StringPool.BLANK,
				String.valueOf(accountEntry.getPartnerManagedSupport()));
		}

		if (oldAccountEntry.getTier() != accountEntry.getTier()) {
			sendEmail(accountEntry, oldAccountEntry.getTier());

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_TIER, VisibilityConstants.LIFERAY_INC,
				AccountEntryConstants.getTierLabel(oldAccountEntry.getTier()),
				String.valueOf(oldAccountEntry.getTier()),
				AccountEntryConstants.getTierLabel(accountEntry.getTier()),
				String.valueOf(accountEntry.getTier()));
		}

		String oldInstructions = oldAccountEntry.getInstructions();

		if (!oldInstructions.equals(accountEntry.getInstructions())) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_INSTRUCTIONS,
				VisibilityConstants.LIFERAY_INC, StringPool.BLANK,
				oldInstructions, StringPool.BLANK,
				accountEntry.getInstructions());
		}

		String oldNotes = oldAccountEntry.getNotes();

		if (!oldNotes.equals(accountEntry.getNotes())) {
			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_NOTES,
				VisibilityConstants.LIFERAY_INC, StringPool.BLANK, oldNotes,
				StringPool.BLANK, accountEntry.getNotes());
		}

		if (oldAccountEntry.getStatus() != accountEntry.getStatus()) {
			User statusByUser = userLocalService.getUser(
				accountEntry.getStatusByUserId());

			auditEntryLocalService.addAuditEntry(
				statusByUser.getUserId(), statusByUser.getFullName(),
				createDate, classNameId, classPK, auditSetId, classNameId,
				classPK, auditAction, AuditEntryConstants.FIELD_STATUS,
				VisibilityConstants.LIFERAY_INC,
				oldAccountEntry.getStatusLabel(),
				String.valueOf(oldAccountEntry.getStatus()),
				accountEntry.getStatusLabel(),
				String.valueOf(accountEntry.getStatus()));

			auditEntryLocalService.addAuditEntry(
				statusByUser.getUserId(), statusByUser.getFullName(),
				createDate, classNameId, classPK, auditSetId, classNameId,
				classPK, auditAction, AuditEntryConstants.FIELD_STATUS_MESSAGE,
				VisibilityConstants.LIFERAY_INC, StringPool.BLANK,
				oldAccountEntry.getStatusMessage(), StringPool.BLANK,
				accountEntry.getStatusMessage());
		}

		Address oldAddress = oldAccountEntry.getAddress();
		Address address = accountEntry.getAddress();

		String oldAddressString = AdminUtil.formatAddress(oldAddress);
		String addressString = AdminUtil.formatAddress(address);

		if (!oldAddressString.equals(addressString)) {
			long oldAddressId = 0;
			long addressId = 0;

			if (oldAddress != null) {
				oldAddressId = oldAddress.getAddressId();
			}

			if (address != null) {
				addressId = address.getAddressId();
			}

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_ADDRESS,
				VisibilityConstants.LIFERAY_INC, oldAddressString,
				String.valueOf(oldAddressId), addressString,
				String.valueOf(addressId));
		}

		String[] oldLanguageIds = oldAccountEntry.getLanguageIds();
		String[] languageIds = accountEntry.getLanguageIds();

		Arrays.sort(oldLanguageIds);
		Arrays.sort(languageIds);

		if (!Arrays.equals(oldLanguageIds, languageIds)) {
			String oldLanguageIdsString = formatLanguageIds(oldLanguageIds);
			String languageIdsString = formatLanguageIds(languageIds);

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_LANGUAGES,
				VisibilityConstants.LIFERAY_INC, oldLanguageIdsString,
				StringUtil.merge(oldLanguageIds), languageIdsString,
				StringUtil.merge(languageIds));
		}

		long[] oldSupportRegionIds = oldAccountEntry.getSupportRegionIds();
		long[] supportRegionIds = accountEntry.getSupportRegionIds();

		Arrays.sort(oldSupportRegionIds);
		Arrays.sort(supportRegionIds);

		if (!Arrays.equals(oldSupportRegionIds, supportRegionIds)) {
			String oldSupportRegionIdsString = formatSupportRegionIds(
				oldSupportRegionIds);
			String sSupportRegionIdsString = formatSupportRegionIds(
				supportRegionIds);

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction,
				AuditEntryConstants.FIELD_SUPPORT_REGIONS,
				VisibilityConstants.LIFERAY_INC, oldSupportRegionIdsString,
				StringUtil.merge(oldSupportRegionIds), sSupportRegionIdsString,
				StringUtil.merge(supportRegionIds));
		}
	}

	protected void updateEWSADosseriaProjectKey(
			long accountEntryId, String ewsaDossieraProjectKey)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class);

		List<ExternalIdMapper> externalIdMappers =
			externalIdMapperLocalService.getExternalIdMappers(
				classNameId, accountEntryId,
				ExternalIdMapperConstants.TYPE_EWSA_DOSSIERA_ACCOUNT_KEY);

		if (Validator.isNotNull(ewsaDossieraProjectKey)) {
			if (externalIdMappers.isEmpty()) {
				externalIdMapperLocalService.addExternalIdMapper(
					classNameId, accountEntryId,
					ExternalIdMapperConstants.TYPE_EWSA_DOSSIERA_ACCOUNT_KEY,
					ewsaDossieraProjectKey);
			}
			else {
				ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

				externalIdMapperLocalService.updateExternalIdMapper(
					externalIdMapper.getExternalIdMapperId(), classNameId,
					accountEntryId,
					ExternalIdMapperConstants.TYPE_EWSA_DOSSIERA_ACCOUNT_KEY,
					ewsaDossieraProjectKey);
			}
		}
		else if (!externalIdMappers.isEmpty()) {
			for (ExternalIdMapper externalIdMapper : externalIdMappers) {
				externalIdMapperLocalService.deleteExternalIdMapper(
					externalIdMapper.getExternalIdMapperId());
			}
		}
	}

	protected void validate(long accountEntryId, String corpProjectUuid)
		throws PortalException {

		if (Validator.isNotNull(corpProjectUuid)) {
			AccountEntry accountEntry =
				accountEntryPersistence.fetchByCorpProjectUuid(corpProjectUuid);

			if ((accountEntry != null) &&
				(accountEntry.getAccountEntryId() != accountEntryId)) {

				throw new AccountEntryCorpProjectException();
			}
		}
	}

	protected void validate(
			long accountEntryId, String corpProjectUuid, String name,
			String code, int type, int industry, long partnerEntryId,
			int maxCustomers, String[] languageIds, long[] supportRegionIds)
		throws PortalException {

		validate(accountEntryId, corpProjectUuid);

		if (Validator.isNull(name)) {
			throw new AccountEntryNameException();
		}

		if ((type == AccountEntryConstants.TYPE_TRIAL) &&
			!name.startsWith("Trial - ") &&
			(accountEntryId != OSBConstants.ACCOUNT_ENTRY_TRIAL_ID)) {

			throw new AccountEntryNameException();
		}

		if (Validator.isNull(code)) {
			throw new AccountEntryCodeException();
		}

		if ((code.length() > 15) || code.equals("LEP") || code.equals("LPE") ||
			code.equals("LPS")) {

			throw new AccountEntryCodeException();
		}

		for (char c : code.toCharArray()) {
			if (!Validator.isChar(c) && !Validator.isDigit(c)) {
				throw new AccountEntryCodeException();
			}
		}

		if (industry <= 0) {
			throw new AccountEntryIndustryException();
		}

		ListType listType = listTypeLocalService.fetchListType(industry);

		if (listType == null) {
			throw new AccountEntryIndustryException();
		}

		if (!StringUtil.equals(
				listType.getType(), AccountEntryConstants.LIST_TYPE_INDUSTRY)) {

			throw new AccountEntryIndustryException();
		}

		if (partnerEntryId > 0) {
			PartnerEntry partnerEntry =
				partnerEntryLocalService.fetchPartnerEntry(partnerEntryId);

			if (partnerEntry == null) {
				throw new AccountEntryPartnerEntryException();
			}

			if (partnerEntry.getStatus() == WorkflowConstants.STATUS_INACTIVE) {
				throw new AccountEntryPartnerEntryException();
			}
		}

		AccountEntry accountEntry = accountEntryPersistence.fetchByCode(code);

		if (accountEntry != null) {
			if (accountEntry.getAccountEntryId() != accountEntryId) {
				throw new DuplicateAccountEntryException();
			}

			int accountEntryCustomersCount =
				accountCustomerPersistence.countByAccountEntryId(
					accountEntryId);

			if (accountEntryCustomersCount > maxCustomers) {
				throw new AccountEntryMaximumCustomersException();
			}
		}

		if (type != AccountEntryConstants.TYPE_TRIAL) {
			if (ArrayUtil.isEmpty(supportRegionIds)) {
				throw new AccountEntrySupportRegionException();
			}
			else if (ArrayUtil.isEmpty(languageIds)) {
				throw new AccountEntryLanguageIdException();
			}
		}

		for (String languageId : languageIds) {
			if (!ArrayUtil.contains(
					AccountEntryConstants.LANGUAGES, languageId)) {

				throw new AccountEntryLanguageIdException();
			}
		}
	}

	protected void validateTrial(long userId) throws PortalException {
		if (accountEntryPersistence.countByU_T(
				userId, AccountEntryConstants.TYPE_TRIAL) > 0) {

			throw new PrincipalException();
		}
	}

	@BeanReference(type = CountryService.class)
	protected CountryService countryService;

	private static final Log _log = LogFactoryUtil.getLog(
		AccountEntryLocalServiceImpl.class);

}