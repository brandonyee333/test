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

		for (Map.Entry<Integer, String> entry : data.entrySet()) {
			AccountInformation accountInformation =
				accountInformationPersistence.fetchByAEI_API_FI(
					accountEntryId, accountProjectId, entry.getKey());

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
			accountInformation.setFieldId(entry.getKey());
			accountInformation.setData(entry.getValue());

			accountInformationPersistence.update(accountInformation);

			accountInformationList.add(accountInformation);
		}

		return accountInformationList;
	}

}