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

package com.liferay.osb.customer.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AuditFormSoap implements Serializable {

	public static AuditFormSoap toSoapModel(AuditForm model) {
		AuditFormSoap soapModel = new AuditFormSoap();

		soapModel.setAuditFormId(model.getAuditFormId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setEndUserName(model.getEndUserName());
		soapModel.setEndUserEmailAddress(model.getEndUserEmailAddress());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setAgreement(model.isAgreement());

		return soapModel;
	}

	public static AuditFormSoap[] toSoapModels(AuditForm[] models) {
		AuditFormSoap[] soapModels = new AuditFormSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuditFormSoap[][] toSoapModels(AuditForm[][] models) {
		AuditFormSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AuditFormSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuditFormSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuditFormSoap[] toSoapModels(List<AuditForm> models) {
		List<AuditFormSoap> soapModels = new ArrayList<AuditFormSoap>(
			models.size());

		for (AuditForm model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AuditFormSoap[soapModels.size()]);
	}

	public AuditFormSoap() {
	}

	public long getPrimaryKey() {
		return _auditFormId;
	}

	public void setPrimaryKey(long pk) {
		setAuditFormId(pk);
	}

	public long getAuditFormId() {
		return _auditFormId;
	}

	public void setAuditFormId(long auditFormId) {
		_auditFormId = auditFormId;
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

	public String getEndUserName() {
		return _endUserName;
	}

	public void setEndUserName(String endUserName) {
		_endUserName = endUserName;
	}

	public String getEndUserEmailAddress() {
		return _endUserEmailAddress;
	}

	public void setEndUserEmailAddress(String endUserEmailAddress) {
		_endUserEmailAddress = endUserEmailAddress;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public boolean getAgreement() {
		return _agreement;
	}

	public boolean isAgreement() {
		return _agreement;
	}

	public void setAgreement(boolean agreement) {
		_agreement = agreement;
	}

	private long _auditFormId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private String _endUserName;
	private String _endUserEmailAddress;
	private String _companyName;
	private boolean _agreement;

}