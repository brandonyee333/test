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

package com.liferay.osb.util;

import com.liferay.osb.configuration.OSBCustomerQAConfigurationKeys;
import com.liferay.osb.configuration.OSBCustomerQAConfigurationUtil;
import com.liferay.osb.configuration.OSBCustomerQAConfigurationValues;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.AccountEnvironmentAttachmentConstants;
import com.liferay.osb.model.OfferingDefinitionConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Jeremy Fu
 */
public class OSBCustomerQAInfrastructureUtil {

	public static void setupQAInfrastructure() throws Exception {
		checkPartnerEntries();

		checkAccountEntries();

		checkUsers(OSBConstants.COMPANY_ID);

		checkAccountCustomers(OSBConstants.COMPANY_ID);
		checkAccountEnvironments();
		checkAccountWorkers(OSBConstants.COMPANY_ID);
		checkOfferingEntries();
		checkPartnerWorkers(OSBConstants.COMPANY_ID);
		checkSupportRegions();
	}

	protected static void checkAccountCustomer(
			long companyId, String emailAddress, String accountEntryCode,
			int notifications, int role)
		throws Exception {

		long userId = UserLocalServiceUtil.getUserIdByEmailAddress(
			companyId, emailAddress);

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntryByCode(
				accountEntryCode);

		if (!AccountCustomerLocalServiceUtil.hasAccountCustomer(
				userId, accountEntry.getAccountEntryId())) {

			AccountCustomerLocalServiceUtil.addAccountCustomer(
				OSBConstants.USER_DEFAULT_USER_ID, userId,
				accountEntry.getAccountEntryId(), role, notifications);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Checked account customer: " + emailAddress);
		}
	}

	protected static void checkAccountCustomers(long companyId)
		throws Exception {

		for (String accountCustomerEmailAddress :
				OSBCustomerQAConfigurationValues.
					OSB_QA_ACCOUNT_CUSTOMER_EMAIL_ADDRESSES) {

			String accountEntryCode = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_CUSTOMER,
				new Filter(accountCustomerEmailAddress, "account-entry-code"));
			String notifications = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_CUSTOMER,
				new Filter(accountCustomerEmailAddress, "notifications"));
			String role = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_CUSTOMER,
				new Filter(accountCustomerEmailAddress, "role"));

			checkAccountCustomer(
				companyId, accountCustomerEmailAddress, accountEntryCode,
				GetterUtil.getInteger(notifications),
				GetterUtil.getInteger(role));
		}
	}

	protected static void checkAccountEntries() throws Exception {
		for (String accountEntryCode :
				OSBCustomerQAConfigurationValues.OSB_QA_ACCOUNT_ENTRY_CODES) {

			String maxCustomers = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENTRY,
				new Filter(accountEntryCode, "max-customers"));
			String name = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENTRY,
				new Filter(accountEntryCode, "name"));
			String partnerEntryCode = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENTRY,
				new Filter(accountEntryCode, "partner-entry-code"));
			String partnerManagedSupport = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENTRY,
				new Filter(accountEntryCode, "partner-managed"));

			checkAccountEntry(
				name, accountEntryCode, GetterUtil.getInteger(maxCustomers),
				partnerEntryCode, GetterUtil.getBoolean(partnerManagedSupport));
		}
	}

	protected static void checkAccountEntry(
			String name, String code, int maxCustomers, String partnerEntryCode,
			boolean partnerManagedSupport)
		throws Exception {

		List<AccountEntry> accountEntries = AccountEntryLocalServiceUtil.search(
			name, code);

		if (accountEntries.isEmpty()) {
			long partnerEntryId = 0;

			if (Validator.isNotNull(partnerEntryCode)) {
				PartnerEntry partnerEntry =
					PartnerEntryLocalServiceUtil.getPartnerEntryByCode(
						partnerEntryCode);

				partnerEntryId = partnerEntry.getPartnerEntryId();
			}

			String[] supportRegionIds = OSBCustomerQAConfigurationUtil.getArray(
				OSBCustomerQAConfigurationKeys.OSB_QA_SUPPORT_REGIONS,
				new Filter(code, "support-region-ids"));

			AccountEntryLocalServiceUtil.addAccountEntry(
				OSBConstants.USER_DEFAULT_USER_ID, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, name, code,
				AccountEntryConstants.TYPE_INDIVIDUAL,
				AccountEntryConstants.INDUSTRY_OTHER, partnerEntryId,
				partnerManagedSupport, AccountEntryConstants.TIER_REGULAR,
				maxCustomers, StringPool.BLANK, StringPool.BLANK,
				new String[] {AccountEntryConstants.LANGUAGE_ID_ENGLISH},
				GetterUtil.getLongValues(supportRegionIds), "N/A",
				StringPool.BLANK, StringPool.BLANK, "N/A", "N/A", 0, 0,
				StringPool.BLANK);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Checked account entry: " + code);
		}
	}

	protected static void checkAccountEnvironment(
			long accountEntryId, long productEntryId, String name, int envOS,
			String envOSCustom, int envDB, int envJVM, int envAS, int envLFR,
			int envBrowser, int envCS, String envSearch)
		throws Exception {

		AccountEnvironment accountEnvironment =
			AccountEnvironmentLocalServiceUtil.fetchAccountEnvironment(
				accountEntryId, productEntryId, name);

		if (accountEnvironment == null) {
			ArrayList<ObjectValuePair<String, File>> files = new ArrayList<>();

			String[] fileNames = {"patchinfo.txt", "portal-ext.properties"};

			for (String fileName : fileNames) {
				File file = File.createTempFile("temp", fileName);

				FileWriter fileWriter = new FileWriter(file, true);

				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				bufferedWriter.write(fileName);
				bufferedWriter.close();

				ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
					fileName, file);

				files.add(ovp);
			}

			List<Integer> types = new ArrayList<>();

			types.add(AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
			types.add(AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);

			AccountEnvironmentLocalServiceUtil.addAccountEnvironment(
				OSBConstants.USER_DEFAULT_USER_ID, accountEntryId,
				productEntryId, name, envOS, envOSCustom, envDB, envJVM, envAS,
				envLFR, envBrowser, envCS, envSearch, files, types);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Checked account environment: " + name);
		}
	}

	protected static void checkAccountEnvironments() throws Exception {
		for (String accountEnvironmentName :
				OSBCustomerQAConfigurationValues.
					OSB_QA_ACCOUNT_ENVIRONMENT_NAMES) {

			String accountEnvironmentNameFilter =
				accountEnvironmentName.replaceAll("\\s+", StringPool.BLANK);

			String accountEntryCode = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT,
				new Filter(accountEnvironmentNameFilter, "account-entry-code"));
			String envAS = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT,
				new Filter(accountEnvironmentNameFilter, "envAS"));
			String envBrowser = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT,
				new Filter(accountEnvironmentNameFilter, "envBrowser"));
			String envCS = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT,
				new Filter(accountEnvironmentNameFilter, "envCS"));
			String envDB = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT,
				new Filter(accountEnvironmentNameFilter, "envDB"));
			String envJVM = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT,
				new Filter(accountEnvironmentNameFilter, "envJVM"));
			String envLFR = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT,
				new Filter(accountEnvironmentNameFilter, "envLFR"));
			String envOS = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT,
				new Filter(accountEnvironmentNameFilter, "envOS"));
			String envOSCustom = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT,
				new Filter(accountEnvironmentNameFilter, "envOSCustom"));
			String envSearch = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT,
				new Filter(accountEnvironmentNameFilter, "envSearch"));
			String productEntryId = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT,
				new Filter(accountEnvironmentNameFilter, "productEntryId"));

			AccountEntry accountEntry =
				AccountEntryLocalServiceUtil.getAccountEntryByCode(
					accountEntryCode);

			checkAccountEnvironment(
				accountEntry.getAccountEntryId(),
				GetterUtil.getLong(productEntryId), accountEnvironmentName,
				GetterUtil.getInteger(envOS), envOSCustom,
				GetterUtil.getInteger(envDB), GetterUtil.getInteger(envJVM),
				GetterUtil.getInteger(envAS), GetterUtil.getInteger(envLFR),
				GetterUtil.getInteger(envBrowser), GetterUtil.getInteger(envCS),
				GetterUtil.getString(envSearch));
		}
	}

	protected static void checkAccountWorker(
			long companyId, String emailAddress, String accountEntryCode,
			int notifications, int role)
		throws Exception {

		long userId = UserLocalServiceUtil.getUserIdByEmailAddress(
			companyId, emailAddress);

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntryByCode(
				accountEntryCode);

		if (!AccountWorkerLocalServiceUtil.hasAccountWorker(
				userId, accountEntry.getAccountEntryId())) {

			AccountWorkerLocalServiceUtil.addAccountWorkers(
				OSBConstants.USER_DEFAULT_USER_ID, new long[] {userId},
				accountEntry.getAccountEntryId(), new int[] {role},
				new int[] {notifications});
		}

		if (_log.isInfoEnabled()) {
			_log.info("Checked account worker: " + emailAddress);
		}
	}

	protected static void checkAccountWorkers(long companyId) throws Exception {
		String[] accountWorkerEmailAddresses =
			OSBCustomerQAConfigurationValues.
				OSB_QA_ACCOUNT_WORKER_EMAIL_ADDRESSES;

		for (String accountWorkerEmailAddress : accountWorkerEmailAddresses) {
			String accountEntryCode = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_WORKER,
				new Filter(accountWorkerEmailAddress, "account-entry-code"));
			String notifications = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_WORKER,
				new Filter(accountWorkerEmailAddress, "notifications"));
			String role = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_WORKER,
				new Filter(accountWorkerEmailAddress, "role"));

			checkAccountWorker(
				companyId, accountWorkerEmailAddress, accountEntryCode,
				GetterUtil.getInteger(notifications),
				GetterUtil.getInteger(role));
		}
	}

	protected static void checkOfferingEntries() throws Exception {
		for (String accountEntryCode :
				OSBCustomerQAConfigurationValues.OSB_QA_ACCOUNT_ENTRY_CODES) {

			String[] productEntryIds = OSBCustomerQAConfigurationUtil.getArray(
				OSBCustomerQAConfigurationKeys.OSB_QA_OFFERING_ENTRY,
				new Filter(accountEntryCode, "product-entry-ids"));
			String[] quantities = OSBCustomerQAConfigurationUtil.getArray(
				OSBCustomerQAConfigurationKeys.OSB_QA_OFFERING_ENTRY,
				new Filter(accountEntryCode, "quantities"));
			String[] supportResponseIds =
				OSBCustomerQAConfigurationUtil.getArray(
					OSBCustomerQAConfigurationKeys.OSB_QA_OFFERING_ENTRY,
					new Filter(accountEntryCode, "support-response-ids"));
			String[] types = OSBCustomerQAConfigurationUtil.getArray(
				OSBCustomerQAConfigurationKeys.OSB_QA_OFFERING_ENTRY,
				new Filter(accountEntryCode, "types"));
			String[] versions = OSBCustomerQAConfigurationUtil.getArray(
				OSBCustomerQAConfigurationKeys.OSB_QA_OFFERING_ENTRY,
				new Filter(accountEntryCode, "versions"));

			for (int i = 0; i < productEntryIds.length; i++) {
				checkOfferingEntry(
					accountEntryCode, GetterUtil.getLong(productEntryIds[i]),
					GetterUtil.getInteger(supportResponseIds[i]),
					GetterUtil.getInteger(quantities[i]),
					GetterUtil.getInteger(types[i]),
					GetterUtil.getInteger(versions[i]));
			}
		}
	}

	protected static void checkOfferingEntry(
			String accountEntryCode, long productEntryId,
			long supportResponseId, int quantity, int type, int version)
		throws Exception {

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntryByCode(
				accountEntryCode);

		List<OfferingEntry> accountOfferingEntries =
			OfferingEntryLocalServiceUtil.getAccountEntryOfferingEntries(
				accountEntry.getAccountEntryId());

		OfferingEntry offeringEntry = null;

		for (OfferingEntry accountOfferingEntry : accountOfferingEntries) {
			if ((productEntryId == accountOfferingEntry.getProductEntryId()) &&
				(supportResponseId ==
					accountOfferingEntry.getSupportResponseId()) &&
				(type == accountOfferingEntry.getType()) &&
				(version == accountOfferingEntry.getVersion()) &&
				(quantity == accountOfferingEntry.getQuantity())) {

				offeringEntry = accountOfferingEntry;

				break;
			}
		}

		if (offeringEntry == null) {
			List<OfferingEntry> offeringEntries = new ArrayList<>();

			offeringEntry = OfferingEntryLocalServiceUtil.createOfferingEntry(
				0);

			offeringEntry.setLicenseLifetime(
				OfferingDefinitionConstants.LIFETIME_INDEFINITE_VALUE);
			offeringEntry.setLicenses(true);
			offeringEntry.setProductEntryId(productEntryId);
			offeringEntry.setQuantity(quantity);
			offeringEntry.setSizing(OfferingEntryConstants.SIZING_4);
			offeringEntry.setSupportLifetime(
				OfferingDefinitionConstants.LIFETIME_INDEFINITE_VALUE);
			offeringEntry.setSupportResponseId(supportResponseId);
			offeringEntry.setSupportTickets(true);
			offeringEntry.setType(type);
			offeringEntry.setVersion(version);

			offeringEntries.add(offeringEntry);

			Calendar cal = Calendar.getInstance();

			OrderEntryLocalServiceUtil.addOrderEntry(
				OSBConstants.USER_DEFAULT_USER_ID,
				accountEntry.getAccountEntryId(), StringPool.BLANK,
				cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.YEAR), Boolean.FALSE, cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.YEAR),
				WorkflowConstants.STATUS_APPROVED, StringPool.BLANK,
				offeringEntries);
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Checked offering entry: " +
					offeringEntry.getOfferingEntryId());
		}
	}

	protected static void checkPartnerEntries() throws Exception {
		for (String partnerEntryCode :
				OSBCustomerQAConfigurationValues.OSB_QA_PARTNER_ENTRY_CODES) {

			String partnerEntryCodeFilter = partnerEntryCode.replaceAll(
				"\\s+", StringPool.BLANK);

			String[] supportRegionIds = OSBCustomerQAConfigurationUtil.getArray(
				OSBCustomerQAConfigurationKeys.OSB_QA_PARTNER_ENTRY,
				new Filter(partnerEntryCodeFilter, "support-region-ids"));

			checkPartnerEntry(
				partnerEntryCode, GetterUtil.getLongValues(supportRegionIds));
		}
	}

	protected static void checkPartnerEntry(
			String code, long[] supportRegionIds)
		throws Exception {

		try {
			PartnerEntryLocalServiceUtil.getPartnerEntryByCode(code);
		}
		catch (Exception e) {
			String partnerEntryCodeFilter = code.replaceAll(
				"\\s+", StringPool.BLANK);

			String dossieraAccountKey = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_PARTNER_ENTRY,
				new Filter(partnerEntryCodeFilter, "dossiera-account-key"));

			PartnerEntryLocalServiceUtil.addPartnerEntry(
				OSBConstants.USER_DEFAULT_USER_ID, 0, dossieraAccountKey, code,
				StringPool.BLANK, supportRegionIds);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Checked partner entry: " + code);
		}
	}

	protected static void checkPartnerWorker(
			long companyId, String emailAddress, String partnerEntryCode,
			int notifications, int role)
		throws Exception {

		long userId = UserLocalServiceUtil.getUserIdByEmailAddress(
			companyId, emailAddress);

		PartnerEntry partnerEntry =
			PartnerEntryLocalServiceUtil.getPartnerEntryByCode(
				partnerEntryCode);

		if (!PartnerWorkerLocalServiceUtil.hasPartnerWorker(
				userId, partnerEntry.getPartnerEntryId())) {

			PartnerWorkerLocalServiceUtil.addPartnerWorkers(
				new long[] {userId}, partnerEntry.getPartnerEntryId(),
				new int[] {role}, new int[] {notifications});
		}

		if (_log.isInfoEnabled()) {
			_log.info("Checked partner worker: " + emailAddress);
		}
	}

	protected static void checkPartnerWorkers(long companyId) throws Exception {
		for (String partnerWorkerEmailAddress :
				OSBCustomerQAConfigurationValues.
					OSB_QA_PARTNER_WORKER_EMAIL_ADDRESSES) {

			String notifications = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_PARTNER_WORKER,
				new Filter(partnerWorkerEmailAddress, "notifications"));
			String partnerEntryCode = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_PARTNER_WORKER,
				new Filter(partnerWorkerEmailAddress, "partner-entry-code"));
			String role = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_PARTNER_WORKER,
				new Filter(partnerWorkerEmailAddress, "role"));

			checkPartnerWorker(
				companyId, partnerWorkerEmailAddress, partnerEntryCode,
				GetterUtil.getInteger(notifications),
				GetterUtil.getInteger(role));
		}
	}

	protected static void checkSupportRegion(
			String accountEntryCode, long supportRegionId)
		throws Exception {

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntryByCode(
				accountEntryCode);

		List<SupportRegion> supportRegions = accountEntry.getSupportRegions();

		boolean hasSupportRegion = false;

		for (SupportRegion supportRegion : supportRegions) {
			if (supportRegionId == supportRegion.getSupportRegionId()) {
				hasSupportRegion = true;
			}
		}

		if (!hasSupportRegion) {
			SupportRegionLocalServiceUtil.addAccountEntrySupportRegions(
				accountEntry.getAccountEntryId(), new long[] {supportRegionId});
		}

		if (_log.isInfoEnabled()) {
			_log.info("Checked support region: " + supportRegionId);
		}
	}

	protected static void checkSupportRegions() throws Exception {
		for (String accountEntryCode :
				OSBCustomerQAConfigurationValues.OSB_QA_ACCOUNT_ENTRY_CODES) {

			String[] supportRegionIds = OSBCustomerQAConfigurationUtil.getArray(
				OSBCustomerQAConfigurationKeys.OSB_QA_SUPPORT_REGIONS,
				new Filter(accountEntryCode, "support-region-ids"));

			for (String supportRegionId : supportRegionIds) {
				checkSupportRegion(
					accountEntryCode, GetterUtil.getLong(supportRegionId));
			}
		}
	}

	protected static User checkUser(
			String uuid, long companyId, long[] organizationIds, long[] roleIds,
			long siteId, long userGroupId, String emailAddress,
			String screenName, String firstName, String lastName,
			String password)
		throws Exception {

		User user = UserLocalServiceUtil.fetchUserByEmailAddress(
			companyId, emailAddress);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(uuid);

		if (user == null) {
			try {
				user = UserLocalServiceUtil.addUser(
					0, companyId, false, password, password, false, screenName,
					emailAddress, 0, StringPool.BLANK, LocaleUtil.getDefault(),
					firstName, StringPool.BLANK, lastName, 0, 0, true, 0, 1,
					1970, StringPool.BLANK, null, null, null, null, false,
					serviceContext);
			}
			catch (UserScreenNameException.MustNotBeDuplicate usnemnbd) {
				String key = PwdGenerator.getPassword(PwdGenerator.KEY3, 4);

				screenName = screenName + StringPool.PERIOD + key;

				user = UserLocalServiceUtil.addUser(
					0, companyId, false, password, password, false, screenName,
					emailAddress, 0, StringPool.BLANK, LocaleUtil.getDefault(),
					firstName, StringPool.BLANK, lastName, 0, 0, true, 0, 1,
					1970, StringPool.BLANK, null, null, null, null, false,
					serviceContext);
			}
		}

		for (long organizationId : organizationIds) {
			UserLocalServiceUtil.addOrganizationUsers(
				organizationId, new long[] {user.getUserId()});
		}

		for (long roleId : roleIds) {
			Role role = RoleLocalServiceUtil.getRole(roleId);

			if ((role.getType() == RoleConstants.TYPE_SITE) && (siteId > 0)) {
				UserLocalServiceUtil.addGroupUsers(
					siteId, new long[] {user.getUserId()});

				UserGroupRoleLocalServiceUtil.addUserGroupRoles(
					user.getUserId(), siteId, new long[] {roleId});
			}
			else {
				UserLocalServiceUtil.addRoleUsers(
					roleId, new long[] {user.getUserId()});
			}
		}

		UserLocalServiceUtil.addUserGroupUsers(
			userGroupId, new long[] {user.getUserId()});

		user = UserLocalServiceUtil.updateAgreedToTermsOfUse(
			user.getUserId(), true);

		user = UserLocalServiceUtil.updateEmailAddressVerified(
			user.getUserId(), true);

		user = UserLocalServiceUtil.updatePasswordReset(
			user.getUserId(), false);

		UserLocalServiceUtil.authenticateForBasic(
			companyId, CompanyConstants.AUTH_TYPE_EA, user.getEmailAddress(),
			password);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

		indexer.reindex(user);

		if (_log.isInfoEnabled()) {
			_log.info("Checked user: " + user.getEmailAddress());
		}

		return user;
	}

	protected static void checkUsers(long companyId) throws Exception {
		String[] emailAddresses =
			OSBCustomerQAConfigurationValues.OSB_QA_USER_EMAIL_ADDRESSES;

		for (String emailAddress : emailAddresses) {
			String firstName = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "first-name"));
			String lastName = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "last-name"));
			String password = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "password"));
			String[] organizationIds = OSBCustomerQAConfigurationUtil.getArray(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "organization-ids"));
			String[] roleIds = OSBCustomerQAConfigurationUtil.getArray(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "role-ids"));
			String screenName = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "screen-name"));
			String siteId = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "site-id"));
			String userGroupId = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "user-group"));
			String uuid = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "uuid"));

			checkUser(
				uuid, companyId, GetterUtil.getLongValues(organizationIds),
				GetterUtil.getLongValues(roleIds), GetterUtil.getLong(siteId),
				GetterUtil.getLong(userGroupId), emailAddress, screenName,
				firstName, lastName, password);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OSBCustomerQAInfrastructureUtil.class);

}