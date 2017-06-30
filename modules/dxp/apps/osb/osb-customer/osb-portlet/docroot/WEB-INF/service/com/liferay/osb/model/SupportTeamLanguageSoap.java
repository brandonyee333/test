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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.SupportTeamLanguageServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.SupportTeamLanguageServiceSoap
 * @generated
 */
public class SupportTeamLanguageSoap implements Serializable {
	public static SupportTeamLanguageSoap toSoapModel(SupportTeamLanguage model) {
		SupportTeamLanguageSoap soapModel = new SupportTeamLanguageSoap();

		soapModel.setSupportTeamLanguageId(model.getSupportTeamLanguageId());
		soapModel.setSupportTeamId(model.getSupportTeamId());
		soapModel.setLanguageId(model.getLanguageId());

		return soapModel;
	}

	public static SupportTeamLanguageSoap[] toSoapModels(
		SupportTeamLanguage[] models) {
		SupportTeamLanguageSoap[] soapModels = new SupportTeamLanguageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SupportTeamLanguageSoap[][] toSoapModels(
		SupportTeamLanguage[][] models) {
		SupportTeamLanguageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SupportTeamLanguageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SupportTeamLanguageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SupportTeamLanguageSoap[] toSoapModels(
		List<SupportTeamLanguage> models) {
		List<SupportTeamLanguageSoap> soapModels = new ArrayList<SupportTeamLanguageSoap>(models.size());

		for (SupportTeamLanguage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SupportTeamLanguageSoap[soapModels.size()]);
	}

	public SupportTeamLanguageSoap() {
	}

	public long getPrimaryKey() {
		return _supportTeamLanguageId;
	}

	public void setPrimaryKey(long pk) {
		setSupportTeamLanguageId(pk);
	}

	public long getSupportTeamLanguageId() {
		return _supportTeamLanguageId;
	}

	public void setSupportTeamLanguageId(long supportTeamLanguageId) {
		_supportTeamLanguageId = supportTeamLanguageId;
	}

	public long getSupportTeamId() {
		return _supportTeamId;
	}

	public void setSupportTeamId(long supportTeamId) {
		_supportTeamId = supportTeamId;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	private long _supportTeamLanguageId;
	private long _supportTeamId;
	private String _languageId;
}