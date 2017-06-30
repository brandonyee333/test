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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AppAudit}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppAudit
 * @generated
 */
public class AppAuditWrapper implements AppAudit, ModelWrapper<AppAudit> {
	public AppAuditWrapper(AppAudit appAudit) {
		_appAudit = appAudit;
	}

	public Class<?> getModelClass() {
		return AppAudit.class;
	}

	public String getModelClassName() {
		return AppAudit.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("appAuditId", getAppAuditId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("status", getStatus());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long appAuditId = (Long)attributes.get("appAuditId");

		if (appAuditId != null) {
			setAppAuditId(appAuditId);
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

		Long appEntryId = (Long)attributes.get("appEntryId");

		if (appEntryId != null) {
			setAppEntryId(appEntryId);
		}

		Long appVersionId = (Long)attributes.get("appVersionId");

		if (appVersionId != null) {
			setAppVersionId(appVersionId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	* Returns the primary key of this app audit.
	*
	* @return the primary key of this app audit
	*/
	public long getPrimaryKey() {
		return _appAudit.getPrimaryKey();
	}

	/**
	* Sets the primary key of this app audit.
	*
	* @param primaryKey the primary key of this app audit
	*/
	public void setPrimaryKey(long primaryKey) {
		_appAudit.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this app audit.
	*
	* @return the uuid of this app audit
	*/
	public java.lang.String getUuid() {
		return _appAudit.getUuid();
	}

	/**
	* Sets the uuid of this app audit.
	*
	* @param uuid the uuid of this app audit
	*/
	public void setUuid(java.lang.String uuid) {
		_appAudit.setUuid(uuid);
	}

	/**
	* Returns the app audit ID of this app audit.
	*
	* @return the app audit ID of this app audit
	*/
	public long getAppAuditId() {
		return _appAudit.getAppAuditId();
	}

	/**
	* Sets the app audit ID of this app audit.
	*
	* @param appAuditId the app audit ID of this app audit
	*/
	public void setAppAuditId(long appAuditId) {
		_appAudit.setAppAuditId(appAuditId);
	}

	/**
	* Returns the user ID of this app audit.
	*
	* @return the user ID of this app audit
	*/
	public long getUserId() {
		return _appAudit.getUserId();
	}

	/**
	* Sets the user ID of this app audit.
	*
	* @param userId the user ID of this app audit
	*/
	public void setUserId(long userId) {
		_appAudit.setUserId(userId);
	}

	/**
	* Returns the user uuid of this app audit.
	*
	* @return the user uuid of this app audit
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appAudit.getUserUuid();
	}

	/**
	* Sets the user uuid of this app audit.
	*
	* @param userUuid the user uuid of this app audit
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_appAudit.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this app audit.
	*
	* @return the user name of this app audit
	*/
	public java.lang.String getUserName() {
		return _appAudit.getUserName();
	}

	/**
	* Sets the user name of this app audit.
	*
	* @param userName the user name of this app audit
	*/
	public void setUserName(java.lang.String userName) {
		_appAudit.setUserName(userName);
	}

	/**
	* Returns the create date of this app audit.
	*
	* @return the create date of this app audit
	*/
	public java.util.Date getCreateDate() {
		return _appAudit.getCreateDate();
	}

	/**
	* Sets the create date of this app audit.
	*
	* @param createDate the create date of this app audit
	*/
	public void setCreateDate(java.util.Date createDate) {
		_appAudit.setCreateDate(createDate);
	}

	/**
	* Returns the app entry ID of this app audit.
	*
	* @return the app entry ID of this app audit
	*/
	public long getAppEntryId() {
		return _appAudit.getAppEntryId();
	}

	/**
	* Sets the app entry ID of this app audit.
	*
	* @param appEntryId the app entry ID of this app audit
	*/
	public void setAppEntryId(long appEntryId) {
		_appAudit.setAppEntryId(appEntryId);
	}

	/**
	* Returns the app version ID of this app audit.
	*
	* @return the app version ID of this app audit
	*/
	public long getAppVersionId() {
		return _appAudit.getAppVersionId();
	}

	/**
	* Sets the app version ID of this app audit.
	*
	* @param appVersionId the app version ID of this app audit
	*/
	public void setAppVersionId(long appVersionId) {
		_appAudit.setAppVersionId(appVersionId);
	}

	/**
	* Returns the status of this app audit.
	*
	* @return the status of this app audit
	*/
	public int getStatus() {
		return _appAudit.getStatus();
	}

	/**
	* Sets the status of this app audit.
	*
	* @param status the status of this app audit
	*/
	public void setStatus(int status) {
		_appAudit.setStatus(status);
	}

	public boolean isNew() {
		return _appAudit.isNew();
	}

	public void setNew(boolean n) {
		_appAudit.setNew(n);
	}

	public boolean isCachedModel() {
		return _appAudit.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_appAudit.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _appAudit.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _appAudit.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_appAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _appAudit.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_appAudit.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AppAuditWrapper((AppAudit)_appAudit.clone());
	}

	public int compareTo(com.liferay.osb.model.AppAudit appAudit) {
		return _appAudit.compareTo(appAudit);
	}

	@Override
	public int hashCode() {
		return _appAudit.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AppAudit> toCacheModel() {
		return _appAudit.toCacheModel();
	}

	public com.liferay.osb.model.AppAudit toEscapedModel() {
		return new AppAuditWrapper(_appAudit.toEscapedModel());
	}

	public com.liferay.osb.model.AppAudit toUnescapedModel() {
		return new AppAuditWrapper(_appAudit.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _appAudit.toString();
	}

	public java.lang.String toXmlString() {
		return _appAudit.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_appAudit.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AppAuditWrapper)) {
			return false;
		}

		AppAuditWrapper appAuditWrapper = (AppAuditWrapper)obj;

		if (Validator.equals(_appAudit, appAuditWrapper._appAudit)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AppAudit getWrappedAppAudit() {
		return _appAudit;
	}

	public AppAudit getWrappedModel() {
		return _appAudit;
	}

	public void resetOriginalValues() {
		_appAudit.resetOriginalValues();
	}

	private AppAudit _appAudit;
}