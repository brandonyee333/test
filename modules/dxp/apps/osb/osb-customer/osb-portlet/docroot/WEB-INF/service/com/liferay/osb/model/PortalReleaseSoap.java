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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class PortalReleaseSoap implements Serializable {
	public static PortalReleaseSoap toSoapModel(PortalRelease model) {
		PortalReleaseSoap soapModel = new PortalReleaseSoap();

		soapModel.setPortalReleaseId(model.getPortalReleaseId());
		soapModel.setVersionName(model.getVersionName());
		soapModel.setBuildNumber(model.getBuildNumber());
		soapModel.setFixPackName(model.getFixPackName());
		soapModel.setEe(model.getEe());
		soapModel.setMarketplaceSupport(model.getMarketplaceSupport());
		soapModel.setOsgiSupport(model.getOsgiSupport());
		soapModel.setPaclSupport(model.getPaclSupport());
		soapModel.setHidden(model.getHidden());

		return soapModel;
	}

	public static PortalReleaseSoap[] toSoapModels(PortalRelease[] models) {
		PortalReleaseSoap[] soapModels = new PortalReleaseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PortalReleaseSoap[][] toSoapModels(PortalRelease[][] models) {
		PortalReleaseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PortalReleaseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PortalReleaseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PortalReleaseSoap[] toSoapModels(List<PortalRelease> models) {
		List<PortalReleaseSoap> soapModels = new ArrayList<PortalReleaseSoap>(models.size());

		for (PortalRelease model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PortalReleaseSoap[soapModels.size()]);
	}

	public PortalReleaseSoap() {
	}

	public long getPrimaryKey() {
		return _portalReleaseId;
	}

	public void setPrimaryKey(long pk) {
		setPortalReleaseId(pk);
	}

	public long getPortalReleaseId() {
		return _portalReleaseId;
	}

	public void setPortalReleaseId(long portalReleaseId) {
		_portalReleaseId = portalReleaseId;
	}

	public String getVersionName() {
		return _versionName;
	}

	public void setVersionName(String versionName) {
		_versionName = versionName;
	}

	public int getBuildNumber() {
		return _buildNumber;
	}

	public void setBuildNumber(int buildNumber) {
		_buildNumber = buildNumber;
	}

	public String getFixPackName() {
		return _fixPackName;
	}

	public void setFixPackName(String fixPackName) {
		_fixPackName = fixPackName;
	}

	public boolean getEe() {
		return _ee;
	}

	public boolean isEe() {
		return _ee;
	}

	public void setEe(boolean ee) {
		_ee = ee;
	}

	public boolean getMarketplaceSupport() {
		return _marketplaceSupport;
	}

	public boolean isMarketplaceSupport() {
		return _marketplaceSupport;
	}

	public void setMarketplaceSupport(boolean marketplaceSupport) {
		_marketplaceSupport = marketplaceSupport;
	}

	public boolean getOsgiSupport() {
		return _osgiSupport;
	}

	public boolean isOsgiSupport() {
		return _osgiSupport;
	}

	public void setOsgiSupport(boolean osgiSupport) {
		_osgiSupport = osgiSupport;
	}

	public boolean getPaclSupport() {
		return _paclSupport;
	}

	public boolean isPaclSupport() {
		return _paclSupport;
	}

	public void setPaclSupport(boolean paclSupport) {
		_paclSupport = paclSupport;
	}

	public boolean getHidden() {
		return _hidden;
	}

	public boolean isHidden() {
		return _hidden;
	}

	public void setHidden(boolean hidden) {
		_hidden = hidden;
	}

	private long _portalReleaseId;
	private String _versionName;
	private int _buildNumber;
	private String _fixPackName;
	private boolean _ee;
	private boolean _marketplaceSupport;
	private boolean _osgiSupport;
	private boolean _paclSupport;
	private boolean _hidden;
}