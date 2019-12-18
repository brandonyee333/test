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

package com.liferay.osb.customer.admin.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.customer.admin.service.http.LicenseEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseEntrySoap implements Serializable {

	public static LicenseEntrySoap toSoapModel(LicenseEntry model) {
		LicenseEntrySoap soapModel = new LicenseEntrySoap();

		soapModel.setLicenseEntryId(model.getLicenseEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setProductEntryId(model.getProductEntryId());
		soapModel.setName(model.getName());
		soapModel.setType(model.getType());
		soapModel.setPortalVersionMin(model.getPortalVersionMin());
		soapModel.setPortalVersionMax(model.getPortalVersionMax());

		return soapModel;
	}

	public static LicenseEntrySoap[] toSoapModels(LicenseEntry[] models) {
		LicenseEntrySoap[] soapModels = new LicenseEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LicenseEntrySoap[][] toSoapModels(LicenseEntry[][] models) {
		LicenseEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LicenseEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LicenseEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LicenseEntrySoap[] toSoapModels(List<LicenseEntry> models) {
		List<LicenseEntrySoap> soapModels = new ArrayList<LicenseEntrySoap>(
			models.size());

		for (LicenseEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LicenseEntrySoap[soapModels.size()]);
	}

	public LicenseEntrySoap() {
	}

	public long getPrimaryKey() {
		return _licenseEntryId;
	}

	public void setPrimaryKey(long pk) {
		setLicenseEntryId(pk);
	}

	public long getLicenseEntryId() {
		return _licenseEntryId;
	}

	public void setLicenseEntryId(long licenseEntryId) {
		_licenseEntryId = licenseEntryId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public int getPortalVersionMin() {
		return _portalVersionMin;
	}

	public void setPortalVersionMin(int portalVersionMin) {
		_portalVersionMin = portalVersionMin;
	}

	public int getPortalVersionMax() {
		return _portalVersionMax;
	}

	public void setPortalVersionMax(int portalVersionMax) {
		_portalVersionMax = portalVersionMax;
	}

	private long _licenseEntryId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _productEntryId;
	private String _name;
	private String _type;
	private int _portalVersionMin;
	private int _portalVersionMax;

}