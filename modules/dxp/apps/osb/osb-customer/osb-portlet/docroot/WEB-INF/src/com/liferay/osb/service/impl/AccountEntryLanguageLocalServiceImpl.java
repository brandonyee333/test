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

import com.liferay.osb.model.AccountEntryLanguage;
import com.liferay.osb.service.base.AccountEntryLanguageLocalServiceBaseImpl;
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

			accountEntryLanguagePersistence.update(accountEntryLanguage);
		}
	}

}