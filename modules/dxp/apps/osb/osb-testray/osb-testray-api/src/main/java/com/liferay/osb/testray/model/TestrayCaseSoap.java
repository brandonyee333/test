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

import aQute.bnd.annotation.ProviderType;

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
@ProviderType
public class TestrayCaseSoap implements Serializable {

	public static TestrayCaseSoap toSoapModel(TestrayCase model) {
		TestrayCaseSoap soapModel = new TestrayCaseSoap();

		soapModel.setTestrayCaseId(model.getTestrayCaseId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTestrayCaseTypeId(model.getTestrayCaseTypeId());
		soapModel.setTestrayComponentId(model.getTestrayComponentId());
		soapModel.setTestrayProjectId(model.getTestrayProjectId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setDescriptionType(model.getDescriptionType());
		soapModel.setOriginationKey(model.getOriginationKey());
		soapModel.setPriority(model.getPriority());
		soapModel.setEstimatedDuration(model.getEstimatedDuration());
		soapModel.setSteps(model.getSteps());
		soapModel.setStepsType(model.getStepsType());
		soapModel.setCaseNumber(model.getCaseNumber());

		return soapModel;
	}

	public static TestrayCaseSoap[] toSoapModels(TestrayCase[] models) {
		TestrayCaseSoap[] soapModels = new TestrayCaseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayCaseSoap[][] toSoapModels(TestrayCase[][] models) {
		TestrayCaseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TestrayCaseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayCaseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayCaseSoap[] toSoapModels(List<TestrayCase> models) {
		List<TestrayCaseSoap> soapModels = new ArrayList<TestrayCaseSoap>(
			models.size());

		for (TestrayCase model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestrayCaseSoap[soapModels.size()]);
	}

	public TestrayCaseSoap() {
	}

	public long getPrimaryKey() {
		return _testrayCaseId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayCaseId(pk);
	}

	public long getTestrayCaseId() {
		return _testrayCaseId;
	}

	public void setTestrayCaseId(long testrayCaseId) {
		_testrayCaseId = testrayCaseId;
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

	public long getTestrayCaseTypeId() {
		return _testrayCaseTypeId;
	}

	public void setTestrayCaseTypeId(long testrayCaseTypeId) {
		_testrayCaseTypeId = testrayCaseTypeId;
	}

	public long getTestrayComponentId() {
		return _testrayComponentId;
	}

	public void setTestrayComponentId(long testrayComponentId) {
		_testrayComponentId = testrayComponentId;
	}

	public long getTestrayProjectId() {
		return _testrayProjectId;
	}

	public void setTestrayProjectId(long testrayProjectId) {
		_testrayProjectId = testrayProjectId;
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

	public String getDescriptionType() {
		return _descriptionType;
	}

	public void setDescriptionType(String descriptionType) {
		_descriptionType = descriptionType;
	}

	public long getOriginationKey() {
		return _originationKey;
	}

	public void setOriginationKey(long originationKey) {
		_originationKey = originationKey;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public int getEstimatedDuration() {
		return _estimatedDuration;
	}

	public void setEstimatedDuration(int estimatedDuration) {
		_estimatedDuration = estimatedDuration;
	}

	public String getSteps() {
		return _steps;
	}

	public void setSteps(String steps) {
		_steps = steps;
	}

	public String getStepsType() {
		return _stepsType;
	}

	public void setStepsType(String stepsType) {
		_stepsType = stepsType;
	}

	public long getCaseNumber() {
		return _caseNumber;
	}

	public void setCaseNumber(long caseNumber) {
		_caseNumber = caseNumber;
	}

	private long _testrayCaseId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _testrayCaseTypeId;
	private long _testrayComponentId;
	private long _testrayProjectId;
	private String _name;
	private String _description;
	private String _descriptionType;
	private long _originationKey;
	private int _priority;
	private int _estimatedDuration;
	private String _steps;
	private String _stepsType;
	private long _caseNumber;

}