/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.AccountEnvironmentAttachmentException;
import com.liferay.osb.exception.AccountEnvironmentEnvASException;
import com.liferay.osb.exception.AccountEnvironmentEnvDBException;
import com.liferay.osb.exception.AccountEnvironmentEnvLFRException;
import com.liferay.osb.exception.AccountEnvironmentEnvOSException;
import com.liferay.osb.exception.AccountEnvironmentNameException;
import com.liferay.osb.exception.DuplicateAccountEnvironmentException;
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.AccountEnvironmentAttachment;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.service.base.AccountEnvironmentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ObjectValuePair;
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
			int envLFR, List<ObjectValuePair<String, File>> files,
			List<Integer> types)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(
			0, accountEntryId, productEntryId, name, envOS, envDB, envAS,
			envLFR, files);

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

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		accountEnvironmentPersistence.update(
			accountEnvironment, serviceContext);

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
			int envAS, int envLFR, List<ObjectValuePair<String, File>> files,
			List<Integer> types)
		throws PortalException {

		AccountEnvironment accountEnvironment =
			accountEnvironmentPersistence.findByPrimaryKey(
				accountEnvironmentId);

		validate(
			accountEnvironmentId, accountEnvironment.getAccountEntryId(),
			productEntryId, name, envOS, envDB, envAS, envLFR, files);

		accountEnvironment.setModifiedDate(new Date());
		accountEnvironment.setProductEntryId(productEntryId);
		accountEnvironment.setName(name);
		accountEnvironment.setEnvOS(envOS);
		accountEnvironment.setEnvOSCustom(envOSCustom);
		accountEnvironment.setEnvDB(envDB);
		accountEnvironment.setEnvJVM(envJVM);
		accountEnvironment.setEnvAS(envAS);
		accountEnvironment.setEnvLFR(envLFR);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		accountEnvironmentPersistence.update(
			accountEnvironment, serviceContext);

		if (!files.isEmpty()) {
			accountEnvironmentAttachmentLocalService.
				updateAccountEnvironmentAttachments(
					userId, accountEnvironmentId, files, types);
		}

		return accountEnvironment;
	}

	protected void validate(
			long accountEnvironmentId, long accountEntryId, long productEntryId,
			String name, int envOS, int envDB, int envAS, int envLFR,
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

		try {
			listTypeLocalService.validate(
				envAS, TicketEntryConstants.LIST_TYPE_ENV_AS);
		}
		catch (NoSuchListTypeException nslte) {
			throw new AccountEnvironmentEnvASException();
		}

		try {
			listTypeLocalService.validate(
				envDB, TicketEntryConstants.LIST_TYPE_ENV_DB);
		}
		catch (NoSuchListTypeException nslte) {
			throw new AccountEnvironmentEnvDBException();
		}

		try {
			listTypeLocalService.validate(
				envOS, TicketEntryConstants.LIST_TYPE_ENV_OS);
		}
		catch (NoSuchListTypeException nslte) {
			throw new AccountEnvironmentEnvOSException();
		}

		ProductEntry productEntry = productEntryPersistence.findByPrimaryKey(
			productEntryId);

		if (productEntry.isSocialOffice()) {
			try {
				listTypeLocalService.validate(
					envLFR,
					ProductEntryConstants.LIST_TYPE_SOCIAL_OFFICE_ALL_VERSIONS);
			}
			catch (NoSuchListTypeException nslte) {
				throw new AccountEnvironmentEnvLFRException();
			}
		}
		else {
			try {
				ListType listType = listTypeLocalService.getListType(envLFR);

				String type = listType.getType();

				if (!type.equals(
						ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS) &&
					!type.equals(
						ProductEntryConstants.
							LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS)) {

					throw new AccountEnvironmentEnvLFRException();
				}
			}
			catch (NoSuchListTypeException nslte) {
				throw new AccountEnvironmentEnvLFRException();
			}
		}
	}

}