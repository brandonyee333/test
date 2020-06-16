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

package com.liferay.watson.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonChildAuditSoap implements Serializable {
	public static WatsonChildAuditSoap toSoapModel(WatsonChildAudit model) {
		WatsonChildAuditSoap soapModel = new WatsonChildAuditSoap();

		soapModel.setWatsonChildAuditId(model.getWatsonChildAuditId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBirthCountryId(model.getBirthCountryId());
		soapModel.setCitizenshipWatsonListTypeId(model.getCitizenshipWatsonListTypeId());
		soapModel.setCountryWatsonListTypeId(model.getCountryWatsonListTypeId());
		soapModel.setDischargeWatsonListTypeId(model.getDischargeWatsonListTypeId());
		soapModel.setEthnicityWatsonListTypeId(model.getEthnicityWatsonListTypeId());
		soapModel.setOriginalWatsonPersonId(model.getOriginalWatsonPersonId());
		soapModel.setSexWatsonListTypeId(model.getSexWatsonListTypeId());
		soapModel.setSourceSubtypeWatsonListTypeId(model.getSourceSubtypeWatsonListTypeId());
		soapModel.setSourceWatsonListTypeId(model.getSourceWatsonListTypeId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setWatsonChildId(model.getWatsonChildId());
		soapModel.setDateAccepted(model.getDateAccepted());
		soapModel.setDateDischarged(model.getDateDischarged());
		soapModel.setDateFollowUp(model.getDateFollowUp());
		soapModel.setSource(model.getSource());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonChildAuditSoap[] toSoapModels(WatsonChildAudit[] models) {
		WatsonChildAuditSoap[] soapModels = new WatsonChildAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonChildAuditSoap[][] toSoapModels(
		WatsonChildAudit[][] models) {
		WatsonChildAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonChildAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonChildAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonChildAuditSoap[] toSoapModels(
		List<WatsonChildAudit> models) {
		List<WatsonChildAuditSoap> soapModels = new ArrayList<WatsonChildAuditSoap>(models.size());

		for (WatsonChildAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonChildAuditSoap[soapModels.size()]);
	}

	public WatsonChildAuditSoap() {
	}

	public long getPrimaryKey() {
		return _watsonChildAuditId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonChildAuditId(pk);
	}

	public long getWatsonChildAuditId() {
		return _watsonChildAuditId;
	}

	public void setWatsonChildAuditId(long watsonChildAuditId) {
		_watsonChildAuditId = watsonChildAuditId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public long getBirthCountryId() {
		return _birthCountryId;
	}

	public void setBirthCountryId(long birthCountryId) {
		_birthCountryId = birthCountryId;
	}

	public long getCitizenshipWatsonListTypeId() {
		return _citizenshipWatsonListTypeId;
	}

	public void setCitizenshipWatsonListTypeId(long citizenshipWatsonListTypeId) {
		_citizenshipWatsonListTypeId = citizenshipWatsonListTypeId;
	}

	public long getCountryWatsonListTypeId() {
		return _countryWatsonListTypeId;
	}

	public void setCountryWatsonListTypeId(long countryWatsonListTypeId) {
		_countryWatsonListTypeId = countryWatsonListTypeId;
	}

	public long getDischargeWatsonListTypeId() {
		return _dischargeWatsonListTypeId;
	}

	public void setDischargeWatsonListTypeId(long dischargeWatsonListTypeId) {
		_dischargeWatsonListTypeId = dischargeWatsonListTypeId;
	}

	public long getEthnicityWatsonListTypeId() {
		return _ethnicityWatsonListTypeId;
	}

	public void setEthnicityWatsonListTypeId(long ethnicityWatsonListTypeId) {
		_ethnicityWatsonListTypeId = ethnicityWatsonListTypeId;
	}

	public long getOriginalWatsonPersonId() {
		return _originalWatsonPersonId;
	}

	public void setOriginalWatsonPersonId(long originalWatsonPersonId) {
		_originalWatsonPersonId = originalWatsonPersonId;
	}

	public long getSexWatsonListTypeId() {
		return _sexWatsonListTypeId;
	}

	public void setSexWatsonListTypeId(long sexWatsonListTypeId) {
		_sexWatsonListTypeId = sexWatsonListTypeId;
	}

	public long getSourceSubtypeWatsonListTypeId() {
		return _sourceSubtypeWatsonListTypeId;
	}

	public void setSourceSubtypeWatsonListTypeId(
		long sourceSubtypeWatsonListTypeId) {
		_sourceSubtypeWatsonListTypeId = sourceSubtypeWatsonListTypeId;
	}

	public long getSourceWatsonListTypeId() {
		return _sourceWatsonListTypeId;
	}

	public void setSourceWatsonListTypeId(long sourceWatsonListTypeId) {
		_sourceWatsonListTypeId = sourceWatsonListTypeId;
	}

	public long getTypeWatsonListTypeId() {
		return _typeWatsonListTypeId;
	}

	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_typeWatsonListTypeId = typeWatsonListTypeId;
	}

	public long getWatsonChildId() {
		return _watsonChildId;
	}

	public void setWatsonChildId(long watsonChildId) {
		_watsonChildId = watsonChildId;
	}

	public Date getDateAccepted() {
		return _dateAccepted;
	}

	public void setDateAccepted(Date dateAccepted) {
		_dateAccepted = dateAccepted;
	}

	public Date getDateDischarged() {
		return _dateDischarged;
	}

	public void setDateDischarged(Date dateDischarged) {
		_dateDischarged = dateDischarged;
	}

	public Date getDateFollowUp() {
		return _dateFollowUp;
	}

	public void setDateFollowUp(Date dateFollowUp) {
		_dateFollowUp = dateFollowUp;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonChildAuditId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _birthCountryId;
	private long _citizenshipWatsonListTypeId;
	private long _countryWatsonListTypeId;
	private long _dischargeWatsonListTypeId;
	private long _ethnicityWatsonListTypeId;
	private long _originalWatsonPersonId;
	private long _sexWatsonListTypeId;
	private long _sourceSubtypeWatsonListTypeId;
	private long _sourceWatsonListTypeId;
	private long _typeWatsonListTypeId;
	private long _watsonChildId;
	private Date _dateAccepted;
	private Date _dateDischarged;
	private Date _dateFollowUp;
	private String _source;
	private int _status;
}