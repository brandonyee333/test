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
 * This class is a wrapper for {@link AppPackage}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppPackage
 * @generated
 */
public class AppPackageWrapper implements AppPackage, ModelWrapper<AppPackage> {
	public AppPackageWrapper(AppPackage appPackage) {
		_appPackage = appPackage;
	}

	public Class<?> getModelClass() {
		return AppPackage.class;
	}

	public String getModelClassName() {
		return AppPackage.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appPackageId", getAppPackageId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("compatibility", getCompatibility());
		attributes.put("compatibilityPlus", getCompatibilityPlus());
		attributes.put("prepackaged", getPrepackaged());
		attributes.put("downloadCount", getDownloadCount());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long appPackageId = (Long)attributes.get("appPackageId");

		if (appPackageId != null) {
			setAppPackageId(appPackageId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long appEntryId = (Long)attributes.get("appEntryId");

		if (appEntryId != null) {
			setAppEntryId(appEntryId);
		}

		Long appVersionId = (Long)attributes.get("appVersionId");

		if (appVersionId != null) {
			setAppVersionId(appVersionId);
		}

		Integer compatibility = (Integer)attributes.get("compatibility");

		if (compatibility != null) {
			setCompatibility(compatibility);
		}

		Boolean compatibilityPlus = (Boolean)attributes.get("compatibilityPlus");

		if (compatibilityPlus != null) {
			setCompatibilityPlus(compatibilityPlus);
		}

		Boolean prepackaged = (Boolean)attributes.get("prepackaged");

		if (prepackaged != null) {
			setPrepackaged(prepackaged);
		}

		Integer downloadCount = (Integer)attributes.get("downloadCount");

		if (downloadCount != null) {
			setDownloadCount(downloadCount);
		}
	}

	/**
	* Returns the primary key of this app package.
	*
	* @return the primary key of this app package
	*/
	public long getPrimaryKey() {
		return _appPackage.getPrimaryKey();
	}

	/**
	* Sets the primary key of this app package.
	*
	* @param primaryKey the primary key of this app package
	*/
	public void setPrimaryKey(long primaryKey) {
		_appPackage.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the app package ID of this app package.
	*
	* @return the app package ID of this app package
	*/
	public long getAppPackageId() {
		return _appPackage.getAppPackageId();
	}

	/**
	* Sets the app package ID of this app package.
	*
	* @param appPackageId the app package ID of this app package
	*/
	public void setAppPackageId(long appPackageId) {
		_appPackage.setAppPackageId(appPackageId);
	}

	/**
	* Returns the create date of this app package.
	*
	* @return the create date of this app package
	*/
	public java.util.Date getCreateDate() {
		return _appPackage.getCreateDate();
	}

	/**
	* Sets the create date of this app package.
	*
	* @param createDate the create date of this app package
	*/
	public void setCreateDate(java.util.Date createDate) {
		_appPackage.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this app package.
	*
	* @return the modified date of this app package
	*/
	public java.util.Date getModifiedDate() {
		return _appPackage.getModifiedDate();
	}

	/**
	* Sets the modified date of this app package.
	*
	* @param modifiedDate the modified date of this app package
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_appPackage.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the app entry ID of this app package.
	*
	* @return the app entry ID of this app package
	*/
	public long getAppEntryId() {
		return _appPackage.getAppEntryId();
	}

	/**
	* Sets the app entry ID of this app package.
	*
	* @param appEntryId the app entry ID of this app package
	*/
	public void setAppEntryId(long appEntryId) {
		_appPackage.setAppEntryId(appEntryId);
	}

	/**
	* Returns the app version ID of this app package.
	*
	* @return the app version ID of this app package
	*/
	public long getAppVersionId() {
		return _appPackage.getAppVersionId();
	}

	/**
	* Sets the app version ID of this app package.
	*
	* @param appVersionId the app version ID of this app package
	*/
	public void setAppVersionId(long appVersionId) {
		_appPackage.setAppVersionId(appVersionId);
	}

	/**
	* Returns the compatibility of this app package.
	*
	* @return the compatibility of this app package
	*/
	public int getCompatibility() {
		return _appPackage.getCompatibility();
	}

	/**
	* Sets the compatibility of this app package.
	*
	* @param compatibility the compatibility of this app package
	*/
	public void setCompatibility(int compatibility) {
		_appPackage.setCompatibility(compatibility);
	}

	/**
	* Returns the compatibility plus of this app package.
	*
	* @return the compatibility plus of this app package
	*/
	public boolean getCompatibilityPlus() {
		return _appPackage.getCompatibilityPlus();
	}

	/**
	* Returns <code>true</code> if this app package is compatibility plus.
	*
	* @return <code>true</code> if this app package is compatibility plus; <code>false</code> otherwise
	*/
	public boolean isCompatibilityPlus() {
		return _appPackage.isCompatibilityPlus();
	}

	/**
	* Sets whether this app package is compatibility plus.
	*
	* @param compatibilityPlus the compatibility plus of this app package
	*/
	public void setCompatibilityPlus(boolean compatibilityPlus) {
		_appPackage.setCompatibilityPlus(compatibilityPlus);
	}

	/**
	* Returns the prepackaged of this app package.
	*
	* @return the prepackaged of this app package
	*/
	public boolean getPrepackaged() {
		return _appPackage.getPrepackaged();
	}

	/**
	* Returns <code>true</code> if this app package is prepackaged.
	*
	* @return <code>true</code> if this app package is prepackaged; <code>false</code> otherwise
	*/
	public boolean isPrepackaged() {
		return _appPackage.isPrepackaged();
	}

	/**
	* Sets whether this app package is prepackaged.
	*
	* @param prepackaged the prepackaged of this app package
	*/
	public void setPrepackaged(boolean prepackaged) {
		_appPackage.setPrepackaged(prepackaged);
	}

	/**
	* Returns the download count of this app package.
	*
	* @return the download count of this app package
	*/
	public int getDownloadCount() {
		return _appPackage.getDownloadCount();
	}

	/**
	* Sets the download count of this app package.
	*
	* @param downloadCount the download count of this app package
	*/
	public void setDownloadCount(int downloadCount) {
		_appPackage.setDownloadCount(downloadCount);
	}

	public boolean isNew() {
		return _appPackage.isNew();
	}

	public void setNew(boolean n) {
		_appPackage.setNew(n);
	}

	public boolean isCachedModel() {
		return _appPackage.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_appPackage.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _appPackage.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _appPackage.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_appPackage.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _appPackage.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_appPackage.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AppPackageWrapper((AppPackage)_appPackage.clone());
	}

	public int compareTo(com.liferay.osb.model.AppPackage appPackage) {
		return _appPackage.compareTo(appPackage);
	}

	@Override
	public int hashCode() {
		return _appPackage.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AppPackage> toCacheModel() {
		return _appPackage.toCacheModel();
	}

	public com.liferay.osb.model.AppPackage toEscapedModel() {
		return new AppPackageWrapper(_appPackage.toEscapedModel());
	}

	public com.liferay.osb.model.AppPackage toUnescapedModel() {
		return new AppPackageWrapper(_appPackage.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _appPackage.toString();
	}

	public java.lang.String toXmlString() {
		return _appPackage.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_appPackage.persist();
	}

	public com.liferay.osb.model.AppEntry getAppEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackage.getAppEntry();
	}

	public java.util.List<com.liferay.osb.model.AppPackagePlugin> getAppPackagePlugins()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackage.getAppPackagePlugins();
	}

	public com.liferay.osb.model.AppVersion getAppVersion()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackage.getAppVersion();
	}

	public com.liferay.portal.kernel.plugin.Version getVersion() {
		return _appPackage.getVersion();
	}

	public boolean isPortalRestartRequired()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackage.isPortalRestartRequired();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AppPackageWrapper)) {
			return false;
		}

		AppPackageWrapper appPackageWrapper = (AppPackageWrapper)obj;

		if (Validator.equals(_appPackage, appPackageWrapper._appPackage)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AppPackage getWrappedAppPackage() {
		return _appPackage;
	}

	public AppPackage getWrappedModel() {
		return _appPackage;
	}

	public void resetOriginalValues() {
		_appPackage.resetOriginalValues();
	}

	private AppPackage _appPackage;
}