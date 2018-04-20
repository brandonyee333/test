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

package com.liferay.watson.login.model;

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
 * This class is a wrapper for {@link WatsonTokenAuthEntry}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonTokenAuthEntry
 * @generated
 */
@ProviderType
public class WatsonTokenAuthEntryWrapper implements WatsonTokenAuthEntry,
	ModelWrapper<WatsonTokenAuthEntry> {
	public WatsonTokenAuthEntryWrapper(
		WatsonTokenAuthEntry watsonTokenAuthEntry) {
		_watsonTokenAuthEntry = watsonTokenAuthEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonTokenAuthEntry.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonTokenAuthEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonTokenAuthEntryId", getWatsonTokenAuthEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("active", getActive());
		attributes.put("loginIP", getLoginIP());
		attributes.put("token", getToken());
		attributes.put("expirationDate", getExpirationDate());
		attributes.put("loginDate", getLoginDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonTokenAuthEntryId = (Long)attributes.get(
				"watsonTokenAuthEntryId");

		if (watsonTokenAuthEntryId != null) {
			setWatsonTokenAuthEntryId(watsonTokenAuthEntryId);
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

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		String loginIP = (String)attributes.get("loginIP");

		if (loginIP != null) {
			setLoginIP(loginIP);
		}

		String token = (String)attributes.get("token");

		if (token != null) {
			setToken(token);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}

		Date loginDate = (Date)attributes.get("loginDate");

		if (loginDate != null) {
			setLoginDate(loginDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WatsonTokenAuthEntryWrapper((WatsonTokenAuthEntry)_watsonTokenAuthEntry.clone());
	}

	@Override
	public int compareTo(WatsonTokenAuthEntry watsonTokenAuthEntry) {
		return _watsonTokenAuthEntry.compareTo(watsonTokenAuthEntry);
	}

	/**
	* Returns the active of this watson token auth entry.
	*
	* @return the active of this watson token auth entry
	*/
	@Override
	public boolean getActive() {
		return _watsonTokenAuthEntry.getActive();
	}

	/**
	* Returns the company ID of this watson token auth entry.
	*
	* @return the company ID of this watson token auth entry
	*/
	@Override
	public long getCompanyId() {
		return _watsonTokenAuthEntry.getCompanyId();
	}

	/**
	* Returns the create date of this watson token auth entry.
	*
	* @return the create date of this watson token auth entry
	*/
	@Override
	public Date getCreateDate() {
		return _watsonTokenAuthEntry.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonTokenAuthEntry.getExpandoBridge();
	}

	/**
	* Returns the expiration date of this watson token auth entry.
	*
	* @return the expiration date of this watson token auth entry
	*/
	@Override
	public Date getExpirationDate() {
		return _watsonTokenAuthEntry.getExpirationDate();
	}

	/**
	* Returns the login date of this watson token auth entry.
	*
	* @return the login date of this watson token auth entry
	*/
	@Override
	public Date getLoginDate() {
		return _watsonTokenAuthEntry.getLoginDate();
	}

	/**
	* Returns the login ip of this watson token auth entry.
	*
	* @return the login ip of this watson token auth entry
	*/
	@Override
	public java.lang.String getLoginIP() {
		return _watsonTokenAuthEntry.getLoginIP();
	}

	/**
	* Returns the primary key of this watson token auth entry.
	*
	* @return the primary key of this watson token auth entry
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonTokenAuthEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonTokenAuthEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the token of this watson token auth entry.
	*
	* @return the token of this watson token auth entry
	*/
	@Override
	public java.lang.String getToken() {
		return _watsonTokenAuthEntry.getToken();
	}

	/**
	* Returns the user ID of this watson token auth entry.
	*
	* @return the user ID of this watson token auth entry
	*/
	@Override
	public long getUserId() {
		return _watsonTokenAuthEntry.getUserId();
	}

	/**
	* Returns the user name of this watson token auth entry.
	*
	* @return the user name of this watson token auth entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonTokenAuthEntry.getUserName();
	}

	/**
	* Returns the user uuid of this watson token auth entry.
	*
	* @return the user uuid of this watson token auth entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonTokenAuthEntry.getUserUuid();
	}

	/**
	* Returns the watson token auth entry ID of this watson token auth entry.
	*
	* @return the watson token auth entry ID of this watson token auth entry
	*/
	@Override
	public long getWatsonTokenAuthEntryId() {
		return _watsonTokenAuthEntry.getWatsonTokenAuthEntryId();
	}

	@Override
	public int hashCode() {
		return _watsonTokenAuthEntry.hashCode();
	}

	/**
	* Returns <code>true</code> if this watson token auth entry is active.
	*
	* @return <code>true</code> if this watson token auth entry is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _watsonTokenAuthEntry.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonTokenAuthEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonTokenAuthEntry.isEscapedModel();
	}

	@Override
	public boolean isExpired() {
		return _watsonTokenAuthEntry.isExpired();
	}

	@Override
	public boolean isNew() {
		return _watsonTokenAuthEntry.isNew();
	}

	@Override
	public void persist() {
		_watsonTokenAuthEntry.persist();
	}

	/**
	* Sets whether this watson token auth entry is active.
	*
	* @param active the active of this watson token auth entry
	*/
	@Override
	public void setActive(boolean active) {
		_watsonTokenAuthEntry.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonTokenAuthEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson token auth entry.
	*
	* @param companyId the company ID of this watson token auth entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonTokenAuthEntry.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson token auth entry.
	*
	* @param createDate the create date of this watson token auth entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonTokenAuthEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonTokenAuthEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonTokenAuthEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonTokenAuthEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expiration date of this watson token auth entry.
	*
	* @param expirationDate the expiration date of this watson token auth entry
	*/
	@Override
	public void setExpirationDate(Date expirationDate) {
		_watsonTokenAuthEntry.setExpirationDate(expirationDate);
	}

	/**
	* Sets the login date of this watson token auth entry.
	*
	* @param loginDate the login date of this watson token auth entry
	*/
	@Override
	public void setLoginDate(Date loginDate) {
		_watsonTokenAuthEntry.setLoginDate(loginDate);
	}

	/**
	* Sets the login ip of this watson token auth entry.
	*
	* @param loginIP the login ip of this watson token auth entry
	*/
	@Override
	public void setLoginIP(java.lang.String loginIP) {
		_watsonTokenAuthEntry.setLoginIP(loginIP);
	}

	@Override
	public void setNew(boolean n) {
		_watsonTokenAuthEntry.setNew(n);
	}

	/**
	* Sets the primary key of this watson token auth entry.
	*
	* @param primaryKey the primary key of this watson token auth entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonTokenAuthEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonTokenAuthEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the token of this watson token auth entry.
	*
	* @param token the token of this watson token auth entry
	*/
	@Override
	public void setToken(java.lang.String token) {
		_watsonTokenAuthEntry.setToken(token);
	}

	/**
	* Sets the user ID of this watson token auth entry.
	*
	* @param userId the user ID of this watson token auth entry
	*/
	@Override
	public void setUserId(long userId) {
		_watsonTokenAuthEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this watson token auth entry.
	*
	* @param userName the user name of this watson token auth entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonTokenAuthEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson token auth entry.
	*
	* @param userUuid the user uuid of this watson token auth entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonTokenAuthEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the watson token auth entry ID of this watson token auth entry.
	*
	* @param watsonTokenAuthEntryId the watson token auth entry ID of this watson token auth entry
	*/
	@Override
	public void setWatsonTokenAuthEntryId(long watsonTokenAuthEntryId) {
		_watsonTokenAuthEntry.setWatsonTokenAuthEntryId(watsonTokenAuthEntryId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonTokenAuthEntry> toCacheModel() {
		return _watsonTokenAuthEntry.toCacheModel();
	}

	@Override
	public WatsonTokenAuthEntry toEscapedModel() {
		return new WatsonTokenAuthEntryWrapper(_watsonTokenAuthEntry.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonTokenAuthEntry.toString();
	}

	@Override
	public WatsonTokenAuthEntry toUnescapedModel() {
		return new WatsonTokenAuthEntryWrapper(_watsonTokenAuthEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonTokenAuthEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonTokenAuthEntryWrapper)) {
			return false;
		}

		WatsonTokenAuthEntryWrapper watsonTokenAuthEntryWrapper = (WatsonTokenAuthEntryWrapper)obj;

		if (Objects.equals(_watsonTokenAuthEntry,
					watsonTokenAuthEntryWrapper._watsonTokenAuthEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonTokenAuthEntry getWrappedModel() {
		return _watsonTokenAuthEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonTokenAuthEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonTokenAuthEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonTokenAuthEntry.resetOriginalValues();
	}

	private final WatsonTokenAuthEntry _watsonTokenAuthEntry;
}