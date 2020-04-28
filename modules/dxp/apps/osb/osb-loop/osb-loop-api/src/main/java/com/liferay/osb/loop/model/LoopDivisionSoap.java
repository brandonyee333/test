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

package com.liferay.osb.loop.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopDivisionSoap implements Serializable {

	public static LoopDivisionSoap toSoapModel(LoopDivision model) {
		LoopDivisionSoap soapModel = new LoopDivisionSoap();

		soapModel.setLoopDivisionId(model.getLoopDivisionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOrganizationId(model.getOrganizationId());
		soapModel.setParentLoopDivisionId(model.getParentLoopDivisionId());
		soapModel.setType(model.getType());
		soapModel.setSubtype(model.getSubtype());
		soapModel.setExtraData(model.getExtraData());
		soapModel.setImagePayload(model.getImagePayload());

		return soapModel;
	}

	public static LoopDivisionSoap[] toSoapModels(LoopDivision[] models) {
		LoopDivisionSoap[] soapModels = new LoopDivisionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopDivisionSoap[][] toSoapModels(LoopDivision[][] models) {
		LoopDivisionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopDivisionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopDivisionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopDivisionSoap[] toSoapModels(List<LoopDivision> models) {
		List<LoopDivisionSoap> soapModels = new ArrayList<LoopDivisionSoap>(
			models.size());

		for (LoopDivision model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopDivisionSoap[soapModels.size()]);
	}

	public LoopDivisionSoap() {
	}

	public long getPrimaryKey() {
		return _loopDivisionId;
	}

	public void setPrimaryKey(long pk) {
		setLoopDivisionId(pk);
	}

	public long getLoopDivisionId() {
		return _loopDivisionId;
	}

	public void setLoopDivisionId(long loopDivisionId) {
		_loopDivisionId = loopDivisionId;
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

	public long getOrganizationId() {
		return _organizationId;
	}

	public void setOrganizationId(long organizationId) {
		_organizationId = organizationId;
	}

	public long getParentLoopDivisionId() {
		return _parentLoopDivisionId;
	}

	public void setParentLoopDivisionId(long parentLoopDivisionId) {
		_parentLoopDivisionId = parentLoopDivisionId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getSubtype() {
		return _subtype;
	}

	public void setSubtype(int subtype) {
		_subtype = subtype;
	}

	public String getExtraData() {
		return _extraData;
	}

	public void setExtraData(String extraData) {
		_extraData = extraData;
	}

	public String getImagePayload() {
		return _imagePayload;
	}

	public void setImagePayload(String imagePayload) {
		_imagePayload = imagePayload;
	}

	private long _loopDivisionId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _organizationId;
	private long _parentLoopDivisionId;
	private int _type;
	private int _subtype;
	private String _extraData;
	private String _imagePayload;

}