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
public class WatsonReportSoap implements Serializable {

	public static WatsonReportSoap toSoapModel(WatsonReport model) {
		WatsonReportSoap soapModel = new WatsonReportSoap();

		soapModel.setWatsonReportId(model.getWatsonReportId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOriginalWatsonReportId(model.getOriginalWatsonReportId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setWatsonChildId(model.getWatsonChildId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setFullReport(model.getFullReport());
		soapModel.setImagePayload(model.getImagePayload());
		soapModel.setTimeSpent(model.getTimeSpent());
		soapModel.setReportedUser(model.getReportedUser());
		soapModel.setReportDate(model.getReportDate());
		soapModel.setKey(model.getKey());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonReportSoap[] toSoapModels(WatsonReport[] models) {
		WatsonReportSoap[] soapModels = new WatsonReportSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonReportSoap[][] toSoapModels(WatsonReport[][] models) {
		WatsonReportSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonReportSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonReportSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonReportSoap[] toSoapModels(List<WatsonReport> models) {
		List<WatsonReportSoap> soapModels = new ArrayList<WatsonReportSoap>(
			models.size());

		for (WatsonReport model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonReportSoap[soapModels.size()]);
	}

	public WatsonReportSoap() {
	}

	public long getPrimaryKey() {
		return _watsonReportId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonReportId(pk);
	}

	public long getWatsonReportId() {
		return _watsonReportId;
	}

	public void setWatsonReportId(long watsonReportId) {
		_watsonReportId = watsonReportId;
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

	public long getOriginalWatsonReportId() {
		return _originalWatsonReportId;
	}

	public void setOriginalWatsonReportId(long originalWatsonReportId) {
		_originalWatsonReportId = originalWatsonReportId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getFullReport() {
		return _fullReport;
	}

	public void setFullReport(String fullReport) {
		_fullReport = fullReport;
	}

	public String getImagePayload() {
		return _imagePayload;
	}

	public void setImagePayload(String imagePayload) {
		_imagePayload = imagePayload;
	}

	public String getTimeSpent() {
		return _timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		_timeSpent = timeSpent;
	}

	public String getReportedUser() {
		return _reportedUser;
	}

	public void setReportedUser(String reportedUser) {
		_reportedUser = reportedUser;
	}

	public Date getReportDate() {
		return _reportDate;
	}

	public void setReportDate(Date reportDate) {
		_reportDate = reportDate;
	}

	public int getKey() {
		return _key;
	}

	public void setKey(int key) {
		_key = key;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonReportId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _originalWatsonReportId;
	private long _typeWatsonListTypeId;
	private long _watsonChildId;
	private String _name;
	private String _description;
	private String _fullReport;
	private String _imagePayload;
	private String _timeSpent;
	private String _reportedUser;
	private Date _reportDate;
	private int _key;
	private int _status;

}