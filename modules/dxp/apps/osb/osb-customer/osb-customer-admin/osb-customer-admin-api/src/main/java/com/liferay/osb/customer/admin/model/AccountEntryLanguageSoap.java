/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.customer.admin.service.http.AccountEntryLanguageServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountEntryLanguageSoap implements Serializable {

	public static AccountEntryLanguageSoap toSoapModel(
		AccountEntryLanguage model) {

		AccountEntryLanguageSoap soapModel = new AccountEntryLanguageSoap();

		soapModel.setAccountEntryLanguageId(model.getAccountEntryLanguageId());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setLanguageId(model.getLanguageId());

		return soapModel;
	}

	public static AccountEntryLanguageSoap[] toSoapModels(
		AccountEntryLanguage[] models) {

		AccountEntryLanguageSoap[] soapModels =
			new AccountEntryLanguageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountEntryLanguageSoap[][] toSoapModels(
		AccountEntryLanguage[][] models) {

		AccountEntryLanguageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new AccountEntryLanguageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountEntryLanguageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountEntryLanguageSoap[] toSoapModels(
		List<AccountEntryLanguage> models) {

		List<AccountEntryLanguageSoap> soapModels =
			new ArrayList<AccountEntryLanguageSoap>(models.size());

		for (AccountEntryLanguage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new AccountEntryLanguageSoap[soapModels.size()]);
	}

	public AccountEntryLanguageSoap() {
	}

	public long getPrimaryKey() {
		return _accountEntryLanguageId;
	}

	public void setPrimaryKey(long pk) {
		setAccountEntryLanguageId(pk);
	}

	public long getAccountEntryLanguageId() {
		return _accountEntryLanguageId;
	}

	public void setAccountEntryLanguageId(long accountEntryLanguageId) {
		_accountEntryLanguageId = accountEntryLanguageId;
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	private long _accountEntryLanguageId;
	private long _accountEntryId;
	private String _languageId;

}