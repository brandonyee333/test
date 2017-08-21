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

import com.liferay.osb.exception.AccountProjectNameException;
import com.liferay.osb.model.AccountInformation;
import com.liferay.osb.model.AccountProject;
import com.liferay.osb.service.base.AccountProjectLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alan Zhang
 */
public class AccountProjectLocalServiceImpl
	extends AccountProjectLocalServiceBaseImpl {

	public AccountProject deleteAccountProject(long accountProjectId)
		throws PortalException {

		// Account project

		AccountProject accountProject = accountProjectPersistence.remove(
			accountProjectId);

		// Account attachments

		accountAttachmentLocalService.deleteAccountAttachments(
			accountProject.getAccountEntryId(), accountProjectId);

		// Account information

		accountInformationLocalService.deleteAccountInformation(
			accountProject.getAccountEntryId(), accountProjectId);

		return accountProject;
	}

	public AccountProject getAccountProject(long accountProjectId)
		throws PortalException {

		AccountProject accountProject =
			accountProjectPersistence.findByPrimaryKey(accountProjectId);

		List<AccountInformation> accountInformation =
			accountInformationLocalService.getAccountInformation(
				accountProject.getAccountEntryId(), accountProjectId);

		accountProject.setData(accountInformation);

		return accountProject;
	}

	public List<AccountProject> getAccountProjects(long accountEntryId) {
		List<AccountProject> accountProjects =
			accountProjectPersistence.findByAccountEntryId(accountEntryId);

		Map<Long, AccountProject> accountProjectsMap = new HashMap<>();

		for (AccountProject accountProject : accountProjects) {
			accountProjectsMap.put(
				accountProject.getAccountProjectId(), accountProject);
		}

		List<AccountInformation> accountInformationList =
			accountInformationLocalService.getAccountEntryAccountInformation(
				accountEntryId);

		for (AccountInformation accountInformation : accountInformationList) {
			AccountProject accountProject = accountProjectsMap.get(
				accountInformation.getAccountProjectId());

			if (accountProject == null) {
				continue;
			}

			accountProject.addData(accountInformation);
		}

		return accountProjects;
	}

	public AccountProject updateAccountProject(
			long userId, long accountProjectId, long accountEntryId,
			String name, Map<Integer, String> data)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);

		validate(name);

		// Account project

		AccountProject accountProject = null;

		if (accountProjectId <= 0) {
			accountProjectId = counterLocalService.increment();

			accountProject = accountProjectPersistence.create(accountProjectId);
		}
		else {
			accountProject = accountProjectPersistence.findByPrimaryKey(
				accountProjectId);
		}

		accountProject.setModifiedUserId(userId);
		accountProject.setModifiedUserName(user.getFullName());
		accountProject.setModifiedDate(new Date());
		accountProject.setAccountEntryId(accountEntryId);
		accountProject.setName(name);

		accountProjectPersistence.update(accountProject);

		// Account information

		accountInformationLocalService.updateAccountInformation(
			userId, accountEntryId, accountProjectId, data);

		return accountProject;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new AccountProjectNameException();
		}
	}

}