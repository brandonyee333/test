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

import com.liferay.osb.exception.AccountEnvironmentAttachmentException;
import com.liferay.osb.exception.AccountEnvironmentEnvASException;
import com.liferay.osb.exception.AccountEnvironmentEnvBrowserException;
import com.liferay.osb.exception.AccountEnvironmentEnvCSException;
import com.liferay.osb.exception.AccountEnvironmentEnvCommerceException;
import com.liferay.osb.exception.AccountEnvironmentEnvDBException;
import com.liferay.osb.exception.AccountEnvironmentEnvLFRException;
import com.liferay.osb.exception.AccountEnvironmentEnvOSException;
import com.liferay.osb.exception.AccountEnvironmentEnvSearchException;
import com.liferay.osb.exception.AccountEnvironmentNameException;
import com.liferay.osb.exception.DuplicateAccountEnvironmentException;
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.model.AccountEnvironmentConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.base.AccountEnvironmentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Lin Cui
 */
public class AccountEnvironmentLocalServiceImpl
	extends AccountEnvironmentLocalServiceBaseImpl {

	public AccountEnvironment addAccountEnvironment(
			long userId, long accountEntryId, long productEntryId, String name,
			int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
			int envLFR, int envCommerce, int envBrowser, int envCS,
			String envSearch, List<ObjectValuePair<String, File>> files,
			List<Integer> types)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(
			0, accountEntryId, productEntryId, name, envOS, envDB, envAS,
			envLFR, envCommerce, envBrowser, envCS, envSearch, files);

		long accountEnvironmentId = counterLocalService.increment();

		AccountEnvironment accountEnvironment =
			accountEnvironmentPersistence.create(accountEnvironmentId);

		accountEnvironment.setUserId(userId);
		accountEnvironment.setUserName(user.getFullName());
		accountEnvironment.setCreateDate(now);
		accountEnvironment.setModifiedDate(now);
		accountEnvironment.setAccountEntryId(accountEntryId);
		accountEnvironment.setProductEntryId(productEntryId);
		accountEnvironment.setName(name);
		accountEnvironment.setEnvOS(envOS);
		accountEnvironment.setEnvOSCustom(envOSCustom);
		accountEnvironment.setEnvDB(envDB);
		accountEnvironment.setEnvJVM(envJVM);
		accountEnvironment.setEnvAS(envAS);
		accountEnvironment.setEnvLFR(envLFR);
		accountEnvironment.setEnvCommerce(envCommerce);
		accountEnvironment.setEnvBrowser(envBrowser);
		accountEnvironment.setEnvCS(envCS);
		accountEnvironment.setEnvSearch(envSearch);

		accountEnvironmentPersistence.update(accountEnvironment);

		if (!files.isEmpty()) {
			accountEnvironmentAttachmentLocalService.
				addAccountEnvironmentAttachments(
					userId, accountEnvironmentId, files, types);
		}

		return accountEnvironment;
	}

	public AccountEnvironment deleteAccountEnvironment(
			long accountEnvironmentId)
		throws PortalException {

		AccountEnvironment accountEnvironment =
			accountEnvironmentPersistence.remove(accountEnvironmentId);

		List<AccountEnvironmentAttachment> accountEnvironmentAttachments =
			accountEnvironmentAttachmentPersistence.findByAccountEnvironmentId(
				accountEnvironmentId);

		for (AccountEnvironmentAttachment accountEnvironmentAttachment :
				accountEnvironmentAttachments) {

			accountEnvironmentAttachmentLocalService.
				deleteAccountEnvironmentAttachment(
					accountEnvironmentAttachment);
		}

		return accountEnvironment;
	}

	public AccountEnvironment fetchAccountEnvironment(
		long accountEntryId, long productEntryId, String name) {

		return accountEnvironmentPersistence.fetchByAEI_PEI_N(
			accountEntryId, productEntryId, name);
	}

	public List<AccountEnvironment> getAccountEnvironments(long accountEntryId)
		throws PortalException {

		return accountEnvironmentPersistence.findByAccountEntryId(
			accountEntryId);
	}

	public List<AccountEnvironment> getAccountEnvironments(
		long accountEntryId, long productEntryId) {

		return accountEnvironmentPersistence.findByAEI_PEI(
			accountEntryId, productEntryId);
	}

	public Map<String, List<AccountEnvironment>> getAccountEnvironmentsMap(
			long accountEntryId)
		throws PortalException {

		Map<String, List<AccountEnvironment>> accountEnvironmentsMap =
			new TreeMap<>();

		List<AccountEnvironment> accountEnvironments =
			accountEnvironmentPersistence.findByAccountEntryId(accountEntryId);

		for (AccountEnvironment accountEnvironment : accountEnvironments) {
			ProductEntry productEntry =
				productEntryLocalService.getProductEntry(
					accountEnvironment.getProductEntryId());

			List<AccountEnvironment> accountEnvironmentsList = null;

			if (accountEnvironmentsMap.containsKey(productEntry.getName())) {
				accountEnvironmentsList = accountEnvironmentsMap.get(
					productEntry.getName());
			}
			else {
				accountEnvironmentsList = new ArrayList<>();
			}

			accountEnvironmentsList.add(accountEnvironment);

			accountEnvironmentsMap.put(
				productEntry.getName(), accountEnvironmentsList);
		}

		return accountEnvironmentsMap;
	}

	public AccountEnvironment updateAccountEnvironment(
			long userId, long accountEnvironmentId, long productEntryId,
			String name, int envOS, String envOSCustom, int envDB, int envJVM,
			int envAS, int envLFR, int envCommerce, int envBrowser, int envCS,
			String envSearch, List<ObjectValuePair<String, File>> files,
			List<Integer> types)
		throws PortalException {

		AccountEnvironment accountEnvironment =
			accountEnvironmentPersistence.findByPrimaryKey(
				accountEnvironmentId);

		validate(
			accountEnvironmentId, accountEnvironment.getAccountEntryId(),
			productEntryId, name, envOS, envDB, envAS, envLFR, envCommerce,
			envBrowser, envCS, envSearch, files);

		accountEnvironment.setModifiedDate(new Date());
		accountEnvironment.setProductEntryId(productEntryId);
		accountEnvironment.setName(name);
		accountEnvironment.setEnvOS(envOS);
		accountEnvironment.setEnvOSCustom(envOSCustom);
		accountEnvironment.setEnvDB(envDB);
		accountEnvironment.setEnvJVM(envJVM);
		accountEnvironment.setEnvAS(envAS);
		accountEnvironment.setEnvLFR(envLFR);
		accountEnvironment.setEnvCommerce(envCommerce);
		accountEnvironment.setEnvBrowser(envBrowser);
		accountEnvironment.setEnvCS(envCS);
		accountEnvironment.setEnvSearch(envSearch);

		accountEnvironmentPersistence.update(accountEnvironment);

		if (!files.isEmpty()) {
			accountEnvironmentAttachmentLocalService.
				updateAccountEnvironmentAttachments(
					userId, accountEnvironmentId, files, types);
		}

		return accountEnvironment;
	}

	protected boolean isValidListType(int listTypeId, String listTypeType) {
		if (listTypeId <= 0) {
			return false;
		}

		ListType listType = listTypeLocalService.fetchListType(listTypeId);

		if (listType == null) {
			return false;
		}

		if (!listTypeType.equals(listType.getType())) {
			return false;
		}

		return true;
	}

	protected void validate(
			long accountEnvironmentId, long accountEntryId, long productEntryId,
			String name, int envOS, int envDB, int envAS, int envLFR,
			int envCommerce, int envBrowser, int envCS, String envSearch,
			List<ObjectValuePair<String, File>> files)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new AccountEnvironmentNameException();
		}

		if (files.size() != 2) {
			int accountEnvironmentAttachmentsCount =
				accountEnvironmentAttachmentPersistence.
					countByAccountEnvironmentId(accountEnvironmentId);

			if (accountEnvironmentAttachmentsCount != 2) {
				throw new AccountEnvironmentAttachmentException();
			}
		}

		AccountEnvironment accountEnvironment =
			accountEnvironmentPersistence.fetchByAEI_PEI_N(
				accountEntryId, productEntryId, name);

		if ((accountEnvironment != null) &&
			(accountEnvironment.getAccountEnvironmentId() !=
				accountEnvironmentId)) {

			throw new DuplicateAccountEnvironmentException();
		}

		if (!isValidListType(
				envAS, AccountEnvironmentConstants.LIST_TYPE_ENV_AS)) {

			throw new AccountEnvironmentEnvASException();
		}

		if (!isValidListType(
				envDB, AccountEnvironmentConstants.LIST_TYPE_ENV_DB)) {

			throw new AccountEnvironmentEnvDBException();
		}

		if (!isValidListType(
				envOS, AccountEnvironmentConstants.LIST_TYPE_ENV_OS)) {

			throw new AccountEnvironmentEnvOSException();
		}

		ProductEntry productEntry = productEntryPersistence.findByPrimaryKey(
			productEntryId);

		if (productEntry.isSocialOffice()) {
			if (!isValidListType(
					envLFR,
					ProductEntryConstants.
						LIST_TYPE_SOCIAL_OFFICE_ALL_VERSIONS)) {

				throw new AccountEnvironmentEnvLFRException();
			}
		}
		else {
			if (envLFR <= 0) {
				throw new AccountEnvironmentEnvLFRException();
			}

			ListType listType = listTypeLocalService.fetchListType(envLFR);

			if (listType == null) {
				throw new AccountEnvironmentEnvLFRException();
			}

			String type = listType.getType();

			if (!type.equals(
					ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS) &&
				!type.equals(
					ProductEntryConstants.
						LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS)) {

				throw new AccountEnvironmentEnvLFRException();
			}
		}

		if ((envCommerce > 0) &&
			!isValidListType(
				envCommerce,
				ProductEntryConstants.LIST_TYPE_COMMERCE_ALL_VERSIONS)) {

			throw new AccountEnvironmentEnvCommerceException();
		}

		if ((envBrowser > 0) &&
			!isValidListType(
				envBrowser,
				AccountEnvironmentConstants.LIST_TYPE_ENV_BROWSER)) {

			throw new AccountEnvironmentEnvBrowserException();
		}

		if (envCS > 0) {
			if (!isValidListType(
					envCS, AccountEnvironmentConstants.LIST_TYPE_ENV_CS)) {

				throw new AccountEnvironmentEnvCSException();
			}

			if (!ProductEntryConstants.isPortalVersion6_2(envLFR) &&
				!ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR) &&
				!ProductEntryConstants.isDigitalEnterpriseVersion7_1(envLFR)) {

				throw new AccountEnvironmentEnvLFRException();
			}
		}

		if (Validator.isNotNull(envSearch)) {
			int[] envSearches = StringUtil.split(
				envSearch, StringPool.NEW_LINE, 0);

			for (int curEnvSearch : envSearches) {
				if (!isValidListType(
						curEnvSearch,
						AccountEnvironmentConstants.LIST_TYPE_ENV_SEARCH)) {

					throw new AccountEnvironmentEnvSearchException();
				}
			}

			if (!ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR) &&
				!ProductEntryConstants.isDigitalEnterpriseVersion7_1(envLFR)) {

				throw new AccountEnvironmentEnvLFRException();
			}
		}
	}

}