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
 * This class is a wrapper for {@link AppPackagePlugin}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppPackagePlugin
 * @generated
 */
public class AppPackagePluginWrapper implements AppPackagePlugin,
	ModelWrapper<AppPackagePlugin> {
	public AppPackagePluginWrapper(AppPackagePlugin appPackagePlugin) {
		_appPackagePlugin = appPackagePlugin;
	}

	public Class<?> getModelClass() {
		return AppPackagePlugin.class;
	}

	public String getModelClassName() {
		return AppPackagePlugin.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appPackagePluginId", getAppPackagePluginId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("appPackageId", getAppPackageId());
		attributes.put("assetAttachmentId", getAssetAttachmentId());
		attributes.put("fileName", getFileName());
		attributes.put("bundleSymbolicName", getBundleSymbolicName());
		attributes.put("bundleVersion", getBundleVersion());
		attributes.put("contextName", getContextName());
		attributes.put("paclEnabled", getPaclEnabled());
		attributes.put("relengHash", getRelengHash());
		attributes.put("portalRestartRequired", getPortalRestartRequired());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long appPackagePluginId = (Long)attributes.get("appPackagePluginId");

		if (appPackagePluginId != null) {
			setAppPackagePluginId(appPackagePluginId);
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

		Long appPackageId = (Long)attributes.get("appPackageId");

		if (appPackageId != null) {
			setAppPackageId(appPackageId);
		}

		Long assetAttachmentId = (Long)attributes.get("assetAttachmentId");

		if (assetAttachmentId != null) {
			setAssetAttachmentId(assetAttachmentId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		String bundleSymbolicName = (String)attributes.get("bundleSymbolicName");

		if (bundleSymbolicName != null) {
			setBundleSymbolicName(bundleSymbolicName);
		}

		String bundleVersion = (String)attributes.get("bundleVersion");

		if (bundleVersion != null) {
			setBundleVersion(bundleVersion);
		}

		String contextName = (String)attributes.get("contextName");

		if (contextName != null) {
			setContextName(contextName);
		}

		Boolean paclEnabled = (Boolean)attributes.get("paclEnabled");

		if (paclEnabled != null) {
			setPaclEnabled(paclEnabled);
		}

		String relengHash = (String)attributes.get("relengHash");

		if (relengHash != null) {
			setRelengHash(relengHash);
		}

		Boolean portalRestartRequired = (Boolean)attributes.get(
				"portalRestartRequired");

		if (portalRestartRequired != null) {
			setPortalRestartRequired(portalRestartRequired);
		}
	}

	/**
	* Returns the primary key of this app package plugin.
	*
	* @return the primary key of this app package plugin
	*/
	public long getPrimaryKey() {
		return _appPackagePlugin.getPrimaryKey();
	}

	/**
	* Sets the primary key of this app package plugin.
	*
	* @param primaryKey the primary key of this app package plugin
	*/
	public void setPrimaryKey(long primaryKey) {
		_appPackagePlugin.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the app package plugin ID of this app package plugin.
	*
	* @return the app package plugin ID of this app package plugin
	*/
	public long getAppPackagePluginId() {
		return _appPackagePlugin.getAppPackagePluginId();
	}

	/**
	* Sets the app package plugin ID of this app package plugin.
	*
	* @param appPackagePluginId the app package plugin ID of this app package plugin
	*/
	public void setAppPackagePluginId(long appPackagePluginId) {
		_appPackagePlugin.setAppPackagePluginId(appPackagePluginId);
	}

	/**
	* Returns the create date of this app package plugin.
	*
	* @return the create date of this app package plugin
	*/
	public java.util.Date getCreateDate() {
		return _appPackagePlugin.getCreateDate();
	}

	/**
	* Sets the create date of this app package plugin.
	*
	* @param createDate the create date of this app package plugin
	*/
	public void setCreateDate(java.util.Date createDate) {
		_appPackagePlugin.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this app package plugin.
	*
	* @return the modified date of this app package plugin
	*/
	public java.util.Date getModifiedDate() {
		return _appPackagePlugin.getModifiedDate();
	}

	/**
	* Sets the modified date of this app package plugin.
	*
	* @param modifiedDate the modified date of this app package plugin
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_appPackagePlugin.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the app entry ID of this app package plugin.
	*
	* @return the app entry ID of this app package plugin
	*/
	public long getAppEntryId() {
		return _appPackagePlugin.getAppEntryId();
	}

	/**
	* Sets the app entry ID of this app package plugin.
	*
	* @param appEntryId the app entry ID of this app package plugin
	*/
	public void setAppEntryId(long appEntryId) {
		_appPackagePlugin.setAppEntryId(appEntryId);
	}

	/**
	* Returns the app version ID of this app package plugin.
	*
	* @return the app version ID of this app package plugin
	*/
	public long getAppVersionId() {
		return _appPackagePlugin.getAppVersionId();
	}

	/**
	* Sets the app version ID of this app package plugin.
	*
	* @param appVersionId the app version ID of this app package plugin
	*/
	public void setAppVersionId(long appVersionId) {
		_appPackagePlugin.setAppVersionId(appVersionId);
	}

	/**
	* Returns the app package ID of this app package plugin.
	*
	* @return the app package ID of this app package plugin
	*/
	public long getAppPackageId() {
		return _appPackagePlugin.getAppPackageId();
	}

	/**
	* Sets the app package ID of this app package plugin.
	*
	* @param appPackageId the app package ID of this app package plugin
	*/
	public void setAppPackageId(long appPackageId) {
		_appPackagePlugin.setAppPackageId(appPackageId);
	}

	/**
	* Returns the asset attachment ID of this app package plugin.
	*
	* @return the asset attachment ID of this app package plugin
	*/
	public long getAssetAttachmentId() {
		return _appPackagePlugin.getAssetAttachmentId();
	}

	/**
	* Sets the asset attachment ID of this app package plugin.
	*
	* @param assetAttachmentId the asset attachment ID of this app package plugin
	*/
	public void setAssetAttachmentId(long assetAttachmentId) {
		_appPackagePlugin.setAssetAttachmentId(assetAttachmentId);
	}

	/**
	* Returns the file name of this app package plugin.
	*
	* @return the file name of this app package plugin
	*/
	public java.lang.String getFileName() {
		return _appPackagePlugin.getFileName();
	}

	/**
	* Sets the file name of this app package plugin.
	*
	* @param fileName the file name of this app package plugin
	*/
	public void setFileName(java.lang.String fileName) {
		_appPackagePlugin.setFileName(fileName);
	}

	/**
	* Returns the bundle symbolic name of this app package plugin.
	*
	* @return the bundle symbolic name of this app package plugin
	*/
	public java.lang.String getBundleSymbolicName() {
		return _appPackagePlugin.getBundleSymbolicName();
	}

	/**
	* Sets the bundle symbolic name of this app package plugin.
	*
	* @param bundleSymbolicName the bundle symbolic name of this app package plugin
	*/
	public void setBundleSymbolicName(java.lang.String bundleSymbolicName) {
		_appPackagePlugin.setBundleSymbolicName(bundleSymbolicName);
	}

	/**
	* Returns the bundle version of this app package plugin.
	*
	* @return the bundle version of this app package plugin
	*/
	public java.lang.String getBundleVersion() {
		return _appPackagePlugin.getBundleVersion();
	}

	/**
	* Sets the bundle version of this app package plugin.
	*
	* @param bundleVersion the bundle version of this app package plugin
	*/
	public void setBundleVersion(java.lang.String bundleVersion) {
		_appPackagePlugin.setBundleVersion(bundleVersion);
	}

	/**
	* Returns the context name of this app package plugin.
	*
	* @return the context name of this app package plugin
	*/
	public java.lang.String getContextName() {
		return _appPackagePlugin.getContextName();
	}

	/**
	* Sets the context name of this app package plugin.
	*
	* @param contextName the context name of this app package plugin
	*/
	public void setContextName(java.lang.String contextName) {
		_appPackagePlugin.setContextName(contextName);
	}

	/**
	* Returns the pacl enabled of this app package plugin.
	*
	* @return the pacl enabled of this app package plugin
	*/
	public boolean getPaclEnabled() {
		return _appPackagePlugin.getPaclEnabled();
	}

	/**
	* Returns <code>true</code> if this app package plugin is pacl enabled.
	*
	* @return <code>true</code> if this app package plugin is pacl enabled; <code>false</code> otherwise
	*/
	public boolean isPaclEnabled() {
		return _appPackagePlugin.isPaclEnabled();
	}

	/**
	* Sets whether this app package plugin is pacl enabled.
	*
	* @param paclEnabled the pacl enabled of this app package plugin
	*/
	public void setPaclEnabled(boolean paclEnabled) {
		_appPackagePlugin.setPaclEnabled(paclEnabled);
	}

	/**
	* Returns the releng hash of this app package plugin.
	*
	* @return the releng hash of this app package plugin
	*/
	public java.lang.String getRelengHash() {
		return _appPackagePlugin.getRelengHash();
	}

	/**
	* Sets the releng hash of this app package plugin.
	*
	* @param relengHash the releng hash of this app package plugin
	*/
	public void setRelengHash(java.lang.String relengHash) {
		_appPackagePlugin.setRelengHash(relengHash);
	}

	/**
	* Returns the portal restart required of this app package plugin.
	*
	* @return the portal restart required of this app package plugin
	*/
	public boolean getPortalRestartRequired() {
		return _appPackagePlugin.getPortalRestartRequired();
	}

	/**
	* Returns <code>true</code> if this app package plugin is portal restart required.
	*
	* @return <code>true</code> if this app package plugin is portal restart required; <code>false</code> otherwise
	*/
	public boolean isPortalRestartRequired() {
		return _appPackagePlugin.isPortalRestartRequired();
	}

	/**
	* Sets whether this app package plugin is portal restart required.
	*
	* @param portalRestartRequired the portal restart required of this app package plugin
	*/
	public void setPortalRestartRequired(boolean portalRestartRequired) {
		_appPackagePlugin.setPortalRestartRequired(portalRestartRequired);
	}

	public boolean isNew() {
		return _appPackagePlugin.isNew();
	}

	public void setNew(boolean n) {
		_appPackagePlugin.setNew(n);
	}

	public boolean isCachedModel() {
		return _appPackagePlugin.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_appPackagePlugin.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _appPackagePlugin.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _appPackagePlugin.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_appPackagePlugin.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _appPackagePlugin.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_appPackagePlugin.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AppPackagePluginWrapper((AppPackagePlugin)_appPackagePlugin.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AppPackagePlugin appPackagePlugin) {
		return _appPackagePlugin.compareTo(appPackagePlugin);
	}

	@Override
	public int hashCode() {
		return _appPackagePlugin.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AppPackagePlugin> toCacheModel() {
		return _appPackagePlugin.toCacheModel();
	}

	public com.liferay.osb.model.AppPackagePlugin toEscapedModel() {
		return new AppPackagePluginWrapper(_appPackagePlugin.toEscapedModel());
	}

	public com.liferay.osb.model.AppPackagePlugin toUnescapedModel() {
		return new AppPackagePluginWrapper(_appPackagePlugin.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _appPackagePlugin.toString();
	}

	public java.lang.String toXmlString() {
		return _appPackagePlugin.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_appPackagePlugin.persist();
	}

	public com.liferay.osb.model.AppPackage getAppPackage()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePlugin.getAppPackage();
	}

	public com.liferay.osb.model.AssetAttachment getAssetAttachment()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePlugin.getAssetAttachment();
	}

	public java.lang.String getIdentifyingKey() {
		return _appPackagePlugin.getIdentifyingKey();
	}

	public java.lang.String getIdentifyingName() {
		return _appPackagePlugin.getIdentifyingName();
	}

	public com.liferay.portal.kernel.util.UnicodeProperties getPACLProperties()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePlugin.getPACLProperties();
	}

	public java.lang.String getType() {
		return _appPackagePlugin.getType();
	}

	public boolean hasConflictingContextName()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPackagePlugin.hasConflictingContextName();
	}

	public boolean isType(java.lang.String type) {
		return _appPackagePlugin.isType(type);
	}

	public boolean isTypeBundle() {
		return _appPackagePlugin.isTypeBundle();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AppPackagePluginWrapper)) {
			return false;
		}

		AppPackagePluginWrapper appPackagePluginWrapper = (AppPackagePluginWrapper)obj;

		if (Validator.equals(_appPackagePlugin,
					appPackagePluginWrapper._appPackagePlugin)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AppPackagePlugin getWrappedAppPackagePlugin() {
		return _appPackagePlugin;
	}

	public AppPackagePlugin getWrappedModel() {
		return _appPackagePlugin;
	}

	public void resetOriginalValues() {
		_appPackagePlugin.resetOriginalValues();
	}

	private AppPackagePlugin _appPackagePlugin;
}