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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AccountEntryLanguage;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing AccountEntryLanguage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLanguage
 * @generated
 */
public class AccountEntryLanguageCacheModel implements CacheModel<AccountEntryLanguage>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{accountEntryLanguageId=");
		sb.append(accountEntryLanguageId);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append("}");

		return sb.toString();
	}

	public AccountEntryLanguage toEntityModel() {
		AccountEntryLanguageImpl accountEntryLanguageImpl = new AccountEntryLanguageImpl();

		accountEntryLanguageImpl.setAccountEntryLanguageId(accountEntryLanguageId);
		accountEntryLanguageImpl.setAccountEntryId(accountEntryId);

		if (languageId == null) {
			accountEntryLanguageImpl.setLanguageId(StringPool.BLANK);
		}
		else {
			accountEntryLanguageImpl.setLanguageId(languageId);
		}

		accountEntryLanguageImpl.resetOriginalValues();

		return accountEntryLanguageImpl;
	}

	public long accountEntryLanguageId;
	public long accountEntryId;
	public String languageId;
}