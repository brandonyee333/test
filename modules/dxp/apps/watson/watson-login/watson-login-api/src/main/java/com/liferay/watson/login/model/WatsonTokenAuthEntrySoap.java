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

package com.liferay.watson.login.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.watson.login.service.http.WatsonTokenAuthEntryServiceSoap}.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonTokenAuthEntrySoap implements Serializable {

	public static WatsonTokenAuthEntrySoap toSoapModel(
		WatsonTokenAuthEntry model) {

		WatsonTokenAuthEntrySoap soapModel = new WatsonTokenAuthEntrySoap();

		soapModel.setWatsonTokenAuthEntryId(model.getWatsonTokenAuthEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setActive(model.isActive());
		soapModel.setLoginIP(model.getLoginIP());
		soapModel.setToken(model.getToken());
		soapModel.setExpirationDate(model.getExpirationDate());
		soapModel.setLoginDate(model.getLoginDate());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonTokenAuthEntrySoap[] toSoapModels(
		WatsonTokenAuthEntry[] models) {

		WatsonTokenAuthEntrySoap[] soapModels =
			new WatsonTokenAuthEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonTokenAuthEntrySoap[][] toSoapModels(
		WatsonTokenAuthEntry[][] models) {

		WatsonTokenAuthEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new WatsonTokenAuthEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonTokenAuthEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonTokenAuthEntrySoap[] toSoapModels(
		List<WatsonTokenAuthEntry> models) {

		List<WatsonTokenAuthEntrySoap> soapModels =
			new ArrayList<WatsonTokenAuthEntrySoap>(models.size());

		for (WatsonTokenAuthEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new WatsonTokenAuthEntrySoap[soapModels.size()]);
	}

	public WatsonTokenAuthEntrySoap() {
	}

	public long getPrimaryKey() {
		return _watsonTokenAuthEntryId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonTokenAuthEntryId(pk);
	}

	public long getWatsonTokenAuthEntryId() {
		return _watsonTokenAuthEntryId;
	}

	public void setWatsonTokenAuthEntryId(long watsonTokenAuthEntryId) {
		_watsonTokenAuthEntryId = watsonTokenAuthEntryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public String getLoginIP() {
		return _loginIP;
	}

	public void setLoginIP(String loginIP) {
		_loginIP = loginIP;
	}

	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		_token = token;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public Date getLoginDate() {
		return _loginDate;
	}

	public void setLoginDate(Date loginDate) {
		_loginDate = loginDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonTokenAuthEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private boolean _active;
	private String _loginIP;
	private String _token;
	private Date _expirationDate;
	private Date _loginDate;
	private int _status;

}