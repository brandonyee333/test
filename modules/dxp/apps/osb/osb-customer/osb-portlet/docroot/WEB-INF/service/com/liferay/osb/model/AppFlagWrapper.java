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
 * This class is a wrapper for {@link AppFlag}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppFlag
 * @generated
 */
public class AppFlagWrapper implements AppFlag, ModelWrapper<AppFlag> {
	public AppFlagWrapper(AppFlag appFlag) {
		_appFlag = appFlag;
	}

	public Class<?> getModelClass() {
		return AppFlag.class;
	}

	public String getModelClassName() {
		return AppFlag.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("appFlagId", getAppFlagId());
		attributes.put("createDate", getCreateDate());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("type", getType());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long appFlagId = (Long)attributes.get("appFlagId");

		if (appFlagId != null) {
			setAppFlagId(appFlagId);
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

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	/**
	* Returns the primary key of this app flag.
	*
	* @return the primary key of this app flag
	*/
	public long getPrimaryKey() {
		return _appFlag.getPrimaryKey();
	}

	/**
	* Sets the primary key of this app flag.
	*
	* @param primaryKey the primary key of this app flag
	*/
	public void setPrimaryKey(long primaryKey) {
		_appFlag.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this app flag.
	*
	* @return the uuid of this app flag
	*/
	public java.lang.String getUuid() {
		return _appFlag.getUuid();
	}

	/**
	* Sets the uuid of this app flag.
	*
	* @param uuid the uuid of this app flag
	*/
	public void setUuid(java.lang.String uuid) {
		_appFlag.setUuid(uuid);
	}

	/**
	* Returns the app flag ID of this app flag.
	*
	* @return the app flag ID of this app flag
	*/
	public long getAppFlagId() {
		return _appFlag.getAppFlagId();
	}

	/**
	* Sets the app flag ID of this app flag.
	*
	* @param appFlagId the app flag ID of this app flag
	*/
	public void setAppFlagId(long appFlagId) {
		_appFlag.setAppFlagId(appFlagId);
	}

	/**
	* Returns the create date of this app flag.
	*
	* @return the create date of this app flag
	*/
	public java.util.Date getCreateDate() {
		return _appFlag.getCreateDate();
	}

	/**
	* Sets the create date of this app flag.
	*
	* @param createDate the create date of this app flag
	*/
	public void setCreateDate(java.util.Date createDate) {
		_appFlag.setCreateDate(createDate);
	}

	/**
	* Returns the app entry ID of this app flag.
	*
	* @return the app entry ID of this app flag
	*/
	public long getAppEntryId() {
		return _appFlag.getAppEntryId();
	}

	/**
	* Sets the app entry ID of this app flag.
	*
	* @param appEntryId the app entry ID of this app flag
	*/
	public void setAppEntryId(long appEntryId) {
		_appFlag.setAppEntryId(appEntryId);
	}

	/**
	* Returns the app version ID of this app flag.
	*
	* @return the app version ID of this app flag
	*/
	public long getAppVersionId() {
		return _appFlag.getAppVersionId();
	}

	/**
	* Sets the app version ID of this app flag.
	*
	* @param appVersionId the app version ID of this app flag
	*/
	public void setAppVersionId(long appVersionId) {
		_appFlag.setAppVersionId(appVersionId);
	}

	/**
	* Returns the type of this app flag.
	*
	* @return the type of this app flag
	*/
	public int getType() {
		return _appFlag.getType();
	}

	/**
	* Sets the type of this app flag.
	*
	* @param type the type of this app flag
	*/
	public void setType(int type) {
		_appFlag.setType(type);
	}

	public boolean isNew() {
		return _appFlag.isNew();
	}

	public void setNew(boolean n) {
		_appFlag.setNew(n);
	}

	public boolean isCachedModel() {
		return _appFlag.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_appFlag.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _appFlag.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _appFlag.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_appFlag.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _appFlag.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_appFlag.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AppFlagWrapper((AppFlag)_appFlag.clone());
	}

	public int compareTo(com.liferay.osb.model.AppFlag appFlag) {
		return _appFlag.compareTo(appFlag);
	}

	@Override
	public int hashCode() {
		return _appFlag.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AppFlag> toCacheModel() {
		return _appFlag.toCacheModel();
	}

	public com.liferay.osb.model.AppFlag toEscapedModel() {
		return new AppFlagWrapper(_appFlag.toEscapedModel());
	}

	public com.liferay.osb.model.AppFlag toUnescapedModel() {
		return new AppFlagWrapper(_appFlag.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _appFlag.toString();
	}

	public java.lang.String toXmlString() {
		return _appFlag.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_appFlag.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AppFlagWrapper)) {
			return false;
		}

		AppFlagWrapper appFlagWrapper = (AppFlagWrapper)obj;

		if (Validator.equals(_appFlag, appFlagWrapper._appFlag)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AppFlag getWrappedAppFlag() {
		return _appFlag;
	}

	public AppFlag getWrappedModel() {
		return _appFlag;
	}

	public void resetOriginalValues() {
		_appFlag.resetOriginalValues();
	}

	private AppFlag _appFlag;
}