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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AppEntryRel}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppEntryRel
 * @generated
 */
public class AppEntryRelWrapper implements AppEntryRel,
	ModelWrapper<AppEntryRel> {
	public AppEntryRelWrapper(AppEntryRel appEntryRel) {
		_appEntryRel = appEntryRel;
	}

	public Class<?> getModelClass() {
		return AppEntryRel.class;
	}

	public String getModelClassName() {
		return AppEntryRel.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("appEntryRelId", getAppEntryRelId());
		attributes.put("appEntryId1", getAppEntryId1());
		attributes.put("appEntryId2", getAppEntryId2());
		attributes.put("type", getType());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long appEntryRelId = (Long)attributes.get("appEntryRelId");

		if (appEntryRelId != null) {
			setAppEntryRelId(appEntryRelId);
		}

		Long appEntryId1 = (Long)attributes.get("appEntryId1");

		if (appEntryId1 != null) {
			setAppEntryId1(appEntryId1);
		}

		Long appEntryId2 = (Long)attributes.get("appEntryId2");

		if (appEntryId2 != null) {
			setAppEntryId2(appEntryId2);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	/**
	* Returns the primary key of this app entry rel.
	*
	* @return the primary key of this app entry rel
	*/
	public long getPrimaryKey() {
		return _appEntryRel.getPrimaryKey();
	}

	/**
	* Sets the primary key of this app entry rel.
	*
	* @param primaryKey the primary key of this app entry rel
	*/
	public void setPrimaryKey(long primaryKey) {
		_appEntryRel.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this app entry rel.
	*
	* @return the uuid of this app entry rel
	*/
	public java.lang.String getUuid() {
		return _appEntryRel.getUuid();
	}

	/**
	* Sets the uuid of this app entry rel.
	*
	* @param uuid the uuid of this app entry rel
	*/
	public void setUuid(java.lang.String uuid) {
		_appEntryRel.setUuid(uuid);
	}

	/**
	* Returns the app entry rel ID of this app entry rel.
	*
	* @return the app entry rel ID of this app entry rel
	*/
	public long getAppEntryRelId() {
		return _appEntryRel.getAppEntryRelId();
	}

	/**
	* Sets the app entry rel ID of this app entry rel.
	*
	* @param appEntryRelId the app entry rel ID of this app entry rel
	*/
	public void setAppEntryRelId(long appEntryRelId) {
		_appEntryRel.setAppEntryRelId(appEntryRelId);
	}

	/**
	* Returns the app entry id1 of this app entry rel.
	*
	* @return the app entry id1 of this app entry rel
	*/
	public long getAppEntryId1() {
		return _appEntryRel.getAppEntryId1();
	}

	/**
	* Sets the app entry id1 of this app entry rel.
	*
	* @param appEntryId1 the app entry id1 of this app entry rel
	*/
	public void setAppEntryId1(long appEntryId1) {
		_appEntryRel.setAppEntryId1(appEntryId1);
	}

	/**
	* Returns the app entry id2 of this app entry rel.
	*
	* @return the app entry id2 of this app entry rel
	*/
	public long getAppEntryId2() {
		return _appEntryRel.getAppEntryId2();
	}

	/**
	* Sets the app entry id2 of this app entry rel.
	*
	* @param appEntryId2 the app entry id2 of this app entry rel
	*/
	public void setAppEntryId2(long appEntryId2) {
		_appEntryRel.setAppEntryId2(appEntryId2);
	}

	/**
	* Returns the type of this app entry rel.
	*
	* @return the type of this app entry rel
	*/
	public int getType() {
		return _appEntryRel.getType();
	}

	/**
	* Sets the type of this app entry rel.
	*
	* @param type the type of this app entry rel
	*/
	public void setType(int type) {
		_appEntryRel.setType(type);
	}

	public boolean isNew() {
		return _appEntryRel.isNew();
	}

	public void setNew(boolean n) {
		_appEntryRel.setNew(n);
	}

	public boolean isCachedModel() {
		return _appEntryRel.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_appEntryRel.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _appEntryRel.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _appEntryRel.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_appEntryRel.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _appEntryRel.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_appEntryRel.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AppEntryRelWrapper((AppEntryRel)_appEntryRel.clone());
	}

	public int compareTo(com.liferay.osb.model.AppEntryRel appEntryRel) {
		return _appEntryRel.compareTo(appEntryRel);
	}

	@Override
	public int hashCode() {
		return _appEntryRel.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AppEntryRel> toCacheModel() {
		return _appEntryRel.toCacheModel();
	}

	public com.liferay.osb.model.AppEntryRel toEscapedModel() {
		return new AppEntryRelWrapper(_appEntryRel.toEscapedModel());
	}

	public com.liferay.osb.model.AppEntryRel toUnescapedModel() {
		return new AppEntryRelWrapper(_appEntryRel.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _appEntryRel.toString();
	}

	public java.lang.String toXmlString() {
		return _appEntryRel.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_appEntryRel.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AppEntryRelWrapper)) {
			return false;
		}

		AppEntryRelWrapper appEntryRelWrapper = (AppEntryRelWrapper)obj;

		if (Validator.equals(_appEntryRel, appEntryRelWrapper._appEntryRel)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AppEntryRel getWrappedAppEntryRel() {
		return _appEntryRel;
	}

	public AppEntryRel getWrappedModel() {
		return _appEntryRel;
	}

	public void resetOriginalValues() {
		_appEntryRel.resetOriginalValues();
	}

	private AppEntryRel _appEntryRel;
}