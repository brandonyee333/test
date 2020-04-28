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

package com.liferay.osb.testray.model;

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
public class TestrayCaseResultWarningSoap implements Serializable {

	public static TestrayCaseResultWarningSoap toSoapModel(
		TestrayCaseResultWarning model) {

		TestrayCaseResultWarningSoap soapModel =
			new TestrayCaseResultWarningSoap();

		soapModel.setTestrayCaseResultWarningId(
			model.getTestrayCaseResultWarningId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTestrayCaseResultId(model.getTestrayCaseResultId());
		soapModel.setContent(model.getContent());

		return soapModel;
	}

	public static TestrayCaseResultWarningSoap[] toSoapModels(
		TestrayCaseResultWarning[] models) {

		TestrayCaseResultWarningSoap[] soapModels =
			new TestrayCaseResultWarningSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayCaseResultWarningSoap[][] toSoapModels(
		TestrayCaseResultWarning[][] models) {

		TestrayCaseResultWarningSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TestrayCaseResultWarningSoap
					[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayCaseResultWarningSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayCaseResultWarningSoap[] toSoapModels(
		List<TestrayCaseResultWarning> models) {

		List<TestrayCaseResultWarningSoap> soapModels =
			new ArrayList<TestrayCaseResultWarningSoap>(models.size());

		for (TestrayCaseResultWarning model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new TestrayCaseResultWarningSoap[soapModels.size()]);
	}

	public TestrayCaseResultWarningSoap() {
	}

	public long getPrimaryKey() {
		return _testrayCaseResultWarningId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayCaseResultWarningId(pk);
	}

	public long getTestrayCaseResultWarningId() {
		return _testrayCaseResultWarningId;
	}

	public void setTestrayCaseResultWarningId(long testrayCaseResultWarningId) {
		_testrayCaseResultWarningId = testrayCaseResultWarningId;
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

	public long getTestrayCaseResultId() {
		return _testrayCaseResultId;
	}

	public void setTestrayCaseResultId(long testrayCaseResultId) {
		_testrayCaseResultId = testrayCaseResultId;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	private long _testrayCaseResultWarningId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _testrayCaseResultId;
	private String _content;

}