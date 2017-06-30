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
 * This class is a wrapper for {@link PortalRelease}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PortalRelease
 * @generated
 */
public class PortalReleaseWrapper implements PortalRelease,
	ModelWrapper<PortalRelease> {
	public PortalReleaseWrapper(PortalRelease portalRelease) {
		_portalRelease = portalRelease;
	}

	public Class<?> getModelClass() {
		return PortalRelease.class;
	}

	public String getModelClassName() {
		return PortalRelease.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("portalReleaseId", getPortalReleaseId());
		attributes.put("versionName", getVersionName());
		attributes.put("buildNumber", getBuildNumber());
		attributes.put("fixPackName", getFixPackName());
		attributes.put("ee", getEe());
		attributes.put("marketplaceSupport", getMarketplaceSupport());
		attributes.put("osgiSupport", getOsgiSupport());
		attributes.put("paclSupport", getPaclSupport());
		attributes.put("hidden", getHidden());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long portalReleaseId = (Long)attributes.get("portalReleaseId");

		if (portalReleaseId != null) {
			setPortalReleaseId(portalReleaseId);
		}

		String versionName = (String)attributes.get("versionName");

		if (versionName != null) {
			setVersionName(versionName);
		}

		Integer buildNumber = (Integer)attributes.get("buildNumber");

		if (buildNumber != null) {
			setBuildNumber(buildNumber);
		}

		String fixPackName = (String)attributes.get("fixPackName");

		if (fixPackName != null) {
			setFixPackName(fixPackName);
		}

		Boolean ee = (Boolean)attributes.get("ee");

		if (ee != null) {
			setEe(ee);
		}

		Boolean marketplaceSupport = (Boolean)attributes.get(
				"marketplaceSupport");

		if (marketplaceSupport != null) {
			setMarketplaceSupport(marketplaceSupport);
		}

		Boolean osgiSupport = (Boolean)attributes.get("osgiSupport");

		if (osgiSupport != null) {
			setOsgiSupport(osgiSupport);
		}

		Boolean paclSupport = (Boolean)attributes.get("paclSupport");

		if (paclSupport != null) {
			setPaclSupport(paclSupport);
		}

		Boolean hidden = (Boolean)attributes.get("hidden");

		if (hidden != null) {
			setHidden(hidden);
		}
	}

	/**
	* Returns the primary key of this portal release.
	*
	* @return the primary key of this portal release
	*/
	public long getPrimaryKey() {
		return _portalRelease.getPrimaryKey();
	}

	/**
	* Sets the primary key of this portal release.
	*
	* @param primaryKey the primary key of this portal release
	*/
	public void setPrimaryKey(long primaryKey) {
		_portalRelease.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the portal release ID of this portal release.
	*
	* @return the portal release ID of this portal release
	*/
	public long getPortalReleaseId() {
		return _portalRelease.getPortalReleaseId();
	}

	/**
	* Sets the portal release ID of this portal release.
	*
	* @param portalReleaseId the portal release ID of this portal release
	*/
	public void setPortalReleaseId(long portalReleaseId) {
		_portalRelease.setPortalReleaseId(portalReleaseId);
	}

	/**
	* Returns the version name of this portal release.
	*
	* @return the version name of this portal release
	*/
	public java.lang.String getVersionName() {
		return _portalRelease.getVersionName();
	}

	/**
	* Sets the version name of this portal release.
	*
	* @param versionName the version name of this portal release
	*/
	public void setVersionName(java.lang.String versionName) {
		_portalRelease.setVersionName(versionName);
	}

	/**
	* Returns the build number of this portal release.
	*
	* @return the build number of this portal release
	*/
	public int getBuildNumber() {
		return _portalRelease.getBuildNumber();
	}

	/**
	* Sets the build number of this portal release.
	*
	* @param buildNumber the build number of this portal release
	*/
	public void setBuildNumber(int buildNumber) {
		_portalRelease.setBuildNumber(buildNumber);
	}

	/**
	* Returns the fix pack name of this portal release.
	*
	* @return the fix pack name of this portal release
	*/
	public java.lang.String getFixPackName() {
		return _portalRelease.getFixPackName();
	}

	/**
	* Sets the fix pack name of this portal release.
	*
	* @param fixPackName the fix pack name of this portal release
	*/
	public void setFixPackName(java.lang.String fixPackName) {
		_portalRelease.setFixPackName(fixPackName);
	}

	/**
	* Returns the ee of this portal release.
	*
	* @return the ee of this portal release
	*/
	public boolean getEe() {
		return _portalRelease.getEe();
	}

	/**
	* Returns <code>true</code> if this portal release is ee.
	*
	* @return <code>true</code> if this portal release is ee; <code>false</code> otherwise
	*/
	public boolean isEe() {
		return _portalRelease.isEe();
	}

	/**
	* Sets whether this portal release is ee.
	*
	* @param ee the ee of this portal release
	*/
	public void setEe(boolean ee) {
		_portalRelease.setEe(ee);
	}

	/**
	* Returns the marketplace support of this portal release.
	*
	* @return the marketplace support of this portal release
	*/
	public boolean getMarketplaceSupport() {
		return _portalRelease.getMarketplaceSupport();
	}

	/**
	* Returns <code>true</code> if this portal release is marketplace support.
	*
	* @return <code>true</code> if this portal release is marketplace support; <code>false</code> otherwise
	*/
	public boolean isMarketplaceSupport() {
		return _portalRelease.isMarketplaceSupport();
	}

	/**
	* Sets whether this portal release is marketplace support.
	*
	* @param marketplaceSupport the marketplace support of this portal release
	*/
	public void setMarketplaceSupport(boolean marketplaceSupport) {
		_portalRelease.setMarketplaceSupport(marketplaceSupport);
	}

	/**
	* Returns the osgi support of this portal release.
	*
	* @return the osgi support of this portal release
	*/
	public boolean getOsgiSupport() {
		return _portalRelease.getOsgiSupport();
	}

	/**
	* Returns <code>true</code> if this portal release is osgi support.
	*
	* @return <code>true</code> if this portal release is osgi support; <code>false</code> otherwise
	*/
	public boolean isOsgiSupport() {
		return _portalRelease.isOsgiSupport();
	}

	/**
	* Sets whether this portal release is osgi support.
	*
	* @param osgiSupport the osgi support of this portal release
	*/
	public void setOsgiSupport(boolean osgiSupport) {
		_portalRelease.setOsgiSupport(osgiSupport);
	}

	/**
	* Returns the pacl support of this portal release.
	*
	* @return the pacl support of this portal release
	*/
	public boolean getPaclSupport() {
		return _portalRelease.getPaclSupport();
	}

	/**
	* Returns <code>true</code> if this portal release is pacl support.
	*
	* @return <code>true</code> if this portal release is pacl support; <code>false</code> otherwise
	*/
	public boolean isPaclSupport() {
		return _portalRelease.isPaclSupport();
	}

	/**
	* Sets whether this portal release is pacl support.
	*
	* @param paclSupport the pacl support of this portal release
	*/
	public void setPaclSupport(boolean paclSupport) {
		_portalRelease.setPaclSupport(paclSupport);
	}

	/**
	* Returns the hidden of this portal release.
	*
	* @return the hidden of this portal release
	*/
	public boolean getHidden() {
		return _portalRelease.getHidden();
	}

	/**
	* Returns <code>true</code> if this portal release is hidden.
	*
	* @return <code>true</code> if this portal release is hidden; <code>false</code> otherwise
	*/
	public boolean isHidden() {
		return _portalRelease.isHidden();
	}

	/**
	* Sets whether this portal release is hidden.
	*
	* @param hidden the hidden of this portal release
	*/
	public void setHidden(boolean hidden) {
		_portalRelease.setHidden(hidden);
	}

	public boolean isNew() {
		return _portalRelease.isNew();
	}

	public void setNew(boolean n) {
		_portalRelease.setNew(n);
	}

	public boolean isCachedModel() {
		return _portalRelease.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_portalRelease.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _portalRelease.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _portalRelease.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_portalRelease.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _portalRelease.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_portalRelease.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PortalReleaseWrapper((PortalRelease)_portalRelease.clone());
	}

	public int compareTo(com.liferay.osb.model.PortalRelease portalRelease) {
		return _portalRelease.compareTo(portalRelease);
	}

	@Override
	public int hashCode() {
		return _portalRelease.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.PortalRelease> toCacheModel() {
		return _portalRelease.toCacheModel();
	}

	public com.liferay.osb.model.PortalRelease toEscapedModel() {
		return new PortalReleaseWrapper(_portalRelease.toEscapedModel());
	}

	public com.liferay.osb.model.PortalRelease toUnescapedModel() {
		return new PortalReleaseWrapper(_portalRelease.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _portalRelease.toString();
	}

	public java.lang.String toXmlString() {
		return _portalRelease.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_portalRelease.persist();
	}

	public boolean hasMarketplaceSupport() {
		return _portalRelease.hasMarketplaceSupport();
	}

	public boolean hasOsgiSupport() {
		return _portalRelease.hasOsgiSupport();
	}

	public boolean hasPaclSupport() {
		return _portalRelease.hasPaclSupport();
	}

	public boolean isEE() {
		return _portalRelease.isEE();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PortalReleaseWrapper)) {
			return false;
		}

		PortalReleaseWrapper portalReleaseWrapper = (PortalReleaseWrapper)obj;

		if (Validator.equals(_portalRelease, portalReleaseWrapper._portalRelease)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public PortalRelease getWrappedPortalRelease() {
		return _portalRelease;
	}

	public PortalRelease getWrappedModel() {
		return _portalRelease;
	}

	public void resetOriginalValues() {
		_portalRelease.resetOriginalValues();
	}

	private PortalRelease _portalRelease;
}