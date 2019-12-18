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
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ExternalIdMapperSoap implements Serializable {

	public static ExternalIdMapperSoap toSoapModel(ExternalIdMapper model) {
		ExternalIdMapperSoap soapModel = new ExternalIdMapperSoap();

		soapModel.setExternalIdMapperId(model.getExternalIdMapperId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setType(model.getType());
		soapModel.setExternalId(model.getExternalId());

		return soapModel;
	}

	public static ExternalIdMapperSoap[] toSoapModels(
		ExternalIdMapper[] models) {

		ExternalIdMapperSoap[] soapModels =
			new ExternalIdMapperSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ExternalIdMapperSoap[][] toSoapModels(
		ExternalIdMapper[][] models) {

		ExternalIdMapperSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ExternalIdMapperSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ExternalIdMapperSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ExternalIdMapperSoap[] toSoapModels(
		List<ExternalIdMapper> models) {

		List<ExternalIdMapperSoap> soapModels =
			new ArrayList<ExternalIdMapperSoap>(models.size());

		for (ExternalIdMapper model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ExternalIdMapperSoap[soapModels.size()]);
	}

	public ExternalIdMapperSoap() {
	}

	public long getPrimaryKey() {
		return _externalIdMapperId;
	}

	public void setPrimaryKey(long pk) {
		setExternalIdMapperId(pk);
	}

	public long getExternalIdMapperId() {
		return _externalIdMapperId;
	}

	public void setExternalIdMapperId(long externalIdMapperId) {
		_externalIdMapperId = externalIdMapperId;
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

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getExternalId() {
		return _externalId;
	}

	public void setExternalId(String externalId) {
		_externalId = externalId;
	}

	private long _externalIdMapperId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private int _type;
	private String _externalId;

}