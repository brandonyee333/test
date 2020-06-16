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

package com.liferay.osb.loop.token.auth.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.loop.token.auth.service.http.TokenAuthEntryServiceSoap}.
 *
 * @author Bruno Farache
 * @generated
 */
public class TokenAuthEntrySoap implements Serializable {

	public static TokenAuthEntrySoap toSoapModel(TokenAuthEntry model) {
		TokenAuthEntrySoap soapModel = new TokenAuthEntrySoap();

		soapModel.setTokenAuthEntryId(model.getTokenAuthEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setDevice(model.getDevice());
		soapModel.setToken(model.getToken());
		soapModel.setLoginDate(model.getLoginDate());
		soapModel.setLoginIP(model.getLoginIP());

		return soapModel;
	}

	public static TokenAuthEntrySoap[] toSoapModels(TokenAuthEntry[] models) {
		TokenAuthEntrySoap[] soapModels = new TokenAuthEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TokenAuthEntrySoap[][] toSoapModels(
		TokenAuthEntry[][] models) {

		TokenAuthEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TokenAuthEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new TokenAuthEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TokenAuthEntrySoap[] toSoapModels(
		List<TokenAuthEntry> models) {

		List<TokenAuthEntrySoap> soapModels = new ArrayList<TokenAuthEntrySoap>(
			models.size());

		for (TokenAuthEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TokenAuthEntrySoap[soapModels.size()]);
	}

	public TokenAuthEntrySoap() {
	}

	public long getPrimaryKey() {
		return _tokenAuthEntryId;
	}

	public void setPrimaryKey(long pk) {
		setTokenAuthEntryId(pk);
	}

	public long getTokenAuthEntryId() {
		return _tokenAuthEntryId;
	}

	public void setTokenAuthEntryId(long tokenAuthEntryId) {
		_tokenAuthEntryId = tokenAuthEntryId;
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

	public String getDevice() {
		return _device;
	}

	public void setDevice(String device) {
		_device = device;
	}

	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		_token = token;
	}

	public Date getLoginDate() {
		return _loginDate;
	}

	public void setLoginDate(Date loginDate) {
		_loginDate = loginDate;
	}

	public String getLoginIP() {
		return _loginIP;
	}

	public void setLoginIP(String loginIP) {
		_loginIP = loginIP;
	}

	private long _tokenAuthEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private String _device;
	private String _token;
	private Date _loginDate;
	private String _loginIP;

}