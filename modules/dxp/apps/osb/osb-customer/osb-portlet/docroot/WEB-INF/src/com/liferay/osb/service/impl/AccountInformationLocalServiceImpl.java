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

import com.liferay.osb.model.AccountInformation;
import com.liferay.osb.model.AccountInformationDisplay;
import com.liferay.osb.model.AccountProjectConstants;
import com.liferay.osb.service.base.AccountInformationLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Alan Zhang
 */
public class AccountInformationLocalServiceImpl
	extends AccountInformationLocalServiceBaseImpl {

	public void deleteAccountInformation(
		long accountEntryId, long accountProjectId) {

		accountInformationPersistence.removeByAEI_API(
			accountEntryId, accountProjectId);
	}

	public List<AccountInformation> getAccountEntryAccountInformation(
		long accountEntryId) {

		return accountInformationPersistence.findByAEI_NotAPI(
			accountEntryId, AccountProjectConstants.DEFAULT_ACCOUNT_PROJECT_ID);
	}

	public List<AccountInformation> getAccountInformation(
		long accountEntryId, long accountProjectId) {

		return accountInformationPersistence.findByAEI_API(
			accountEntryId, accountProjectId);
	}

	public AccountInformationDisplay getAccountInformationDisplay(
			long accountEntryId)
		throws PortalException {

		List<AccountInformation> accountInformationList =
			accountInformationPersistence.findByAEI_API(
				accountEntryId,
				AccountProjectConstants.DEFAULT_ACCOUNT_PROJECT_ID);

		return new AccountInformationDisplay(accountInformationList);
	}

	public List<AccountInformation> updateAccountInformation(
			long userId, long accountEntryId, long accountProjectId,
			Map<Integer, String> data)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		List<AccountInformation> accountInformationList = new ArrayList<>(
			data.size());

		for (int fieldId : data.keySet()) {
			AccountInformation accountInformation =
				accountInformationPersistence.fetchByAEI_API_FI(
					accountEntryId, accountProjectId, fieldId);

			if (accountInformation == null) {
				long accountInformationId = counterLocalService.increment();

				accountInformation = accountInformationPersistence.create(
					accountInformationId);
			}

			accountInformation.setModifiedUserId(userId);
			accountInformation.setModifiedUserName(user.getFullName());
			accountInformation.setModifiedDate(now);
			accountInformation.setAccountEntryId(accountEntryId);
			accountInformation.setAccountProjectId(accountProjectId);
			accountInformation.setFieldId(fieldId);
			accountInformation.setData(data.get(fieldId));

			accountInformationPersistence.update(accountInformation);

			accountInformationList.add(accountInformation);
		}

		return accountInformationList;
	}

}