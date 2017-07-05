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

package com.liferay.osb.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AccountEntryLanguageServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.AccountEntryLanguageServiceSoap
 * @generated
 */
@ProviderType
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
		AccountEntryLanguageSoap[] soapModels = new AccountEntryLanguageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountEntryLanguageSoap[][] toSoapModels(
		AccountEntryLanguage[][] models) {
		AccountEntryLanguageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountEntryLanguageSoap[models.length][models[0].length];
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
		List<AccountEntryLanguageSoap> soapModels = new ArrayList<AccountEntryLanguageSoap>(models.size());

		for (AccountEntryLanguage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountEntryLanguageSoap[soapModels.size()]);
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