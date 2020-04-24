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

package com.liferay.osb.loop.token.auth.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link TokenAuthEntry}.
 * </p>
 *
 * @author Bruno Farache
 * @see TokenAuthEntry
 * @generated
 */
@ProviderType
public class TokenAuthEntryWrapper implements TokenAuthEntry,
	ModelWrapper<TokenAuthEntry> {
	public TokenAuthEntryWrapper(TokenAuthEntry tokenAuthEntry) {
		_tokenAuthEntry = tokenAuthEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return TokenAuthEntry.class;
	}

	@Override
	public String getModelClassName() {
		return TokenAuthEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("tokenAuthEntryId", getTokenAuthEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("device", getDevice());
		attributes.put("token", getToken());
		attributes.put("loginDate", getLoginDate());
		attributes.put("loginIP", getLoginIP());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long tokenAuthEntryId = (Long)attributes.get("tokenAuthEntryId");

		if (tokenAuthEntryId != null) {
			setTokenAuthEntryId(tokenAuthEntryId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String device = (String)attributes.get("device");

		if (device != null) {
			setDevice(device);
		}

		String token = (String)attributes.get("token");

		if (token != null) {
			setToken(token);
		}

		Date loginDate = (Date)attributes.get("loginDate");

		if (loginDate != null) {
			setLoginDate(loginDate);
		}

		String loginIP = (String)attributes.get("loginIP");

		if (loginIP != null) {
			setLoginIP(loginIP);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new TokenAuthEntryWrapper((TokenAuthEntry)_tokenAuthEntry.clone());
	}

	@Override
	public int compareTo(TokenAuthEntry tokenAuthEntry) {
		return _tokenAuthEntry.compareTo(tokenAuthEntry);
	}

	/**
	* Returns the company ID of this token auth entry.
	*
	* @return the company ID of this token auth entry
	*/
	@Override
	public long getCompanyId() {
		return _tokenAuthEntry.getCompanyId();
	}

	/**
	* Returns the create date of this token auth entry.
	*
	* @return the create date of this token auth entry
	*/
	@Override
	public Date getCreateDate() {
		return _tokenAuthEntry.getCreateDate();
	}

	/**
	* Returns the device of this token auth entry.
	*
	* @return the device of this token auth entry
	*/
	@Override
	public java.lang.String getDevice() {
		return _tokenAuthEntry.getDevice();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _tokenAuthEntry.getExpandoBridge();
	}

	/**
	* Returns the login date of this token auth entry.
	*
	* @return the login date of this token auth entry
	*/
	@Override
	public Date getLoginDate() {
		return _tokenAuthEntry.getLoginDate();
	}

	/**
	* Returns the login ip of this token auth entry.
	*
	* @return the login ip of this token auth entry
	*/
	@Override
	public java.lang.String getLoginIP() {
		return _tokenAuthEntry.getLoginIP();
	}

	/**
	* Returns the primary key of this token auth entry.
	*
	* @return the primary key of this token auth entry
	*/
	@Override
	public long getPrimaryKey() {
		return _tokenAuthEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _tokenAuthEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the token of this token auth entry.
	*
	* @return the token of this token auth entry
	*/
	@Override
	public java.lang.String getToken() {
		return _tokenAuthEntry.getToken();
	}

	/**
	* Returns the token auth entry ID of this token auth entry.
	*
	* @return the token auth entry ID of this token auth entry
	*/
	@Override
	public long getTokenAuthEntryId() {
		return _tokenAuthEntry.getTokenAuthEntryId();
	}

	/**
	* Returns the user ID of this token auth entry.
	*
	* @return the user ID of this token auth entry
	*/
	@Override
	public long getUserId() {
		return _tokenAuthEntry.getUserId();
	}

	/**
	* Returns the user name of this token auth entry.
	*
	* @return the user name of this token auth entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _tokenAuthEntry.getUserName();
	}

	/**
	* Returns the user uuid of this token auth entry.
	*
	* @return the user uuid of this token auth entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _tokenAuthEntry.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _tokenAuthEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _tokenAuthEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _tokenAuthEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _tokenAuthEntry.isNew();
	}

	@Override
	public void persist() {
		_tokenAuthEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_tokenAuthEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this token auth entry.
	*
	* @param companyId the company ID of this token auth entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_tokenAuthEntry.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this token auth entry.
	*
	* @param createDate the create date of this token auth entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_tokenAuthEntry.setCreateDate(createDate);
	}

	/**
	* Sets the device of this token auth entry.
	*
	* @param device the device of this token auth entry
	*/
	@Override
	public void setDevice(java.lang.String device) {
		_tokenAuthEntry.setDevice(device);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_tokenAuthEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_tokenAuthEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_tokenAuthEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the login date of this token auth entry.
	*
	* @param loginDate the login date of this token auth entry
	*/
	@Override
	public void setLoginDate(Date loginDate) {
		_tokenAuthEntry.setLoginDate(loginDate);
	}

	/**
	* Sets the login ip of this token auth entry.
	*
	* @param loginIP the login ip of this token auth entry
	*/
	@Override
	public void setLoginIP(java.lang.String loginIP) {
		_tokenAuthEntry.setLoginIP(loginIP);
	}

	@Override
	public void setNew(boolean n) {
		_tokenAuthEntry.setNew(n);
	}

	/**
	* Sets the primary key of this token auth entry.
	*
	* @param primaryKey the primary key of this token auth entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_tokenAuthEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_tokenAuthEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the token of this token auth entry.
	*
	* @param token the token of this token auth entry
	*/
	@Override
	public void setToken(java.lang.String token) {
		_tokenAuthEntry.setToken(token);
	}

	/**
	* Sets the token auth entry ID of this token auth entry.
	*
	* @param tokenAuthEntryId the token auth entry ID of this token auth entry
	*/
	@Override
	public void setTokenAuthEntryId(long tokenAuthEntryId) {
		_tokenAuthEntry.setTokenAuthEntryId(tokenAuthEntryId);
	}

	/**
	* Sets the user ID of this token auth entry.
	*
	* @param userId the user ID of this token auth entry
	*/
	@Override
	public void setUserId(long userId) {
		_tokenAuthEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this token auth entry.
	*
	* @param userName the user name of this token auth entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_tokenAuthEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this token auth entry.
	*
	* @param userUuid the user uuid of this token auth entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_tokenAuthEntry.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TokenAuthEntry> toCacheModel() {
		return _tokenAuthEntry.toCacheModel();
	}

	@Override
	public TokenAuthEntry toEscapedModel() {
		return new TokenAuthEntryWrapper(_tokenAuthEntry.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _tokenAuthEntry.toString();
	}

	@Override
	public TokenAuthEntry toUnescapedModel() {
		return new TokenAuthEntryWrapper(_tokenAuthEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _tokenAuthEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TokenAuthEntryWrapper)) {
			return false;
		}

		TokenAuthEntryWrapper tokenAuthEntryWrapper = (TokenAuthEntryWrapper)obj;

		if (Objects.equals(_tokenAuthEntry,
					tokenAuthEntryWrapper._tokenAuthEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public TokenAuthEntry getWrappedModel() {
		return _tokenAuthEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _tokenAuthEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _tokenAuthEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_tokenAuthEntry.resetOriginalValues();
	}

	private final TokenAuthEntry _tokenAuthEntry;
}