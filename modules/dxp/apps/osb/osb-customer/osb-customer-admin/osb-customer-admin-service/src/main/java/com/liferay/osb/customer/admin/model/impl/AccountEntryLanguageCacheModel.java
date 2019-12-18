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

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.osb.customer.admin.model.AccountEntryLanguage;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AccountEntryLanguage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountEntryLanguageCacheModel
	implements CacheModel<AccountEntryLanguage>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountEntryLanguageCacheModel)) {
			return false;
		}

		AccountEntryLanguageCacheModel accountEntryLanguageCacheModel =
			(AccountEntryLanguageCacheModel)obj;

		if (accountEntryLanguageId ==
				accountEntryLanguageCacheModel.accountEntryLanguageId) {

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
		AccountEntryLanguageImpl accountEntryLanguageImpl =
			new AccountEntryLanguageImpl();

		accountEntryLanguageImpl.setAccountEntryLanguageId(
			accountEntryLanguageId);
		accountEntryLanguageImpl.setAccountEntryId(accountEntryId);

		if (languageId == null) {
			accountEntryLanguageImpl.setLanguageId("");
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(accountEntryLanguageId);

		objectOutput.writeLong(accountEntryId);

		if (languageId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(languageId);
		}
	}

	public long accountEntryLanguageId;
	public long accountEntryId;
	public String languageId;

}