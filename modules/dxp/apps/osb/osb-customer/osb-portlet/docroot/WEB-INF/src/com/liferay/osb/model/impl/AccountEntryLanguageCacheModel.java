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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.AccountEntryLanguage;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AccountEntryLanguage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLanguage
 * @generated
 */
@ProviderType
public class AccountEntryLanguageCacheModel implements CacheModel<AccountEntryLanguage>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountEntryLanguageCacheModel)) {
			return false;
		}

		AccountEntryLanguageCacheModel accountEntryLanguageCacheModel = (AccountEntryLanguageCacheModel)obj;

		if (accountEntryLanguageId == accountEntryLanguageCacheModel.accountEntryLanguageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountEntryLanguageId);
	}

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

	@Override
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

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountEntryLanguageId = objectInput.readLong();

		accountEntryId = objectInput.readLong();
		languageId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(accountEntryLanguageId);

		objectOutput.writeLong(accountEntryId);

		if (languageId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(languageId);
		}
	}

	public long accountEntryLanguageId;
	public long accountEntryId;
	public String languageId;
}