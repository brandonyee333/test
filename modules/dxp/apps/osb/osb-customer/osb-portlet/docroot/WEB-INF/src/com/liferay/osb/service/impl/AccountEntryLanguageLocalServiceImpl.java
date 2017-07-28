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

import com.liferay.osb.model.AccountEntryLanguage;
import com.liferay.osb.service.base.AccountEntryLanguageLocalServiceBaseImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class AccountEntryLanguageLocalServiceImpl
	extends AccountEntryLanguageLocalServiceBaseImpl {

	public List<AccountEntryLanguage> getAccountEntryLanguages(
		long accountEntryId) {

		return accountEntryLanguagePersistence.findByAccountEntryId(
			accountEntryId);
	}

	public void setAccountEntryLanguageIds(
		long accountEntryId, String[] languageIds) {

		List<AccountEntryLanguage> accountEntryLanguages =
			accountEntryLanguagePersistence.findByAccountEntryId(
				accountEntryId);

		for (AccountEntryLanguage accountEntryLanguage :
				accountEntryLanguages) {

			String languageId = accountEntryLanguage.getLanguageId();

			if (ArrayUtil.contains(languageIds, languageId)) {
				languageIds = ArrayUtil.remove(languageIds, languageId);
			}
			else {
				accountEntryLanguagePersistence.remove(accountEntryLanguage);
			}
		}

		for (String languageId : languageIds) {
			long accountEntryLanguageId = counterLocalService.increment();

			AccountEntryLanguage accountEntryLanguage =
				accountEntryLanguagePersistence.create(accountEntryLanguageId);

			accountEntryLanguage.setAccountEntryId(accountEntryId);
			accountEntryLanguage.setLanguageId(languageId);

			//TODO implement serviceContext how needed

			ServiceContext serviceContext = new ServiceContext();

			accountEntryLanguagePersistence.update(
				accountEntryLanguage, serviceContext);
		}
	}

}