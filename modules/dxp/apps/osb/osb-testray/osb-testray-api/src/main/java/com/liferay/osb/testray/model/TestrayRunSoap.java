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
public class TestrayRunSoap implements Serializable {

	public static TestrayRunSoap toSoapModel(TestrayRun model) {
		TestrayRunSoap soapModel = new TestrayRunSoap();

		soapModel.setTestrayRunId(model.getTestrayRunId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTestrayBuildId(model.getTestrayBuildId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setExternalReferencePK(model.getExternalReferencePK());
		soapModel.setExternalReferenceType(model.getExternalReferenceType());
		soapModel.setJenkinsJobKey(model.getJenkinsJobKey());
		soapModel.setNumber(model.getNumber());
		soapModel.setEnvironmentHash(model.getEnvironmentHash());

		return soapModel;
	}

	public static TestrayRunSoap[] toSoapModels(TestrayRun[] models) {
		TestrayRunSoap[] soapModels = new TestrayRunSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayRunSoap[][] toSoapModels(TestrayRun[][] models) {
		TestrayRunSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TestrayRunSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayRunSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayRunSoap[] toSoapModels(List<TestrayRun> models) {
		List<TestrayRunSoap> soapModels = new ArrayList<TestrayRunSoap>(
			models.size());

		for (TestrayRun model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestrayRunSoap[soapModels.size()]);
	}

	public TestrayRunSoap() {
	}

	public long getPrimaryKey() {
		return _testrayRunId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayRunId(pk);
	}

	public long getTestrayRunId() {
		return _testrayRunId;
	}

	public void setTestrayRunId(long testrayRunId) {
		_testrayRunId = testrayRunId;
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

	public long getTestrayBuildId() {
		return _testrayBuildId;
	}

	public void setTestrayBuildId(long testrayBuildId) {
		_testrayBuildId = testrayBuildId;
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

	public String getExternalReferencePK() {
		return _externalReferencePK;
	}

	public void setExternalReferencePK(String externalReferencePK) {
		_externalReferencePK = externalReferencePK;
	}

	public int getExternalReferenceType() {
		return _externalReferenceType;
	}

	public void setExternalReferenceType(int externalReferenceType) {
		_externalReferenceType = externalReferenceType;
	}

	public long getJenkinsJobKey() {
		return _jenkinsJobKey;
	}

	public void setJenkinsJobKey(long jenkinsJobKey) {
		_jenkinsJobKey = jenkinsJobKey;
	}

	public long getNumber() {
		return _number;
	}

	public void setNumber(long number) {
		_number = number;
	}

	public String getEnvironmentHash() {
		return _environmentHash;
	}

	public void setEnvironmentHash(String environmentHash) {
		_environmentHash = environmentHash;
	}

	private long _testrayRunId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _testrayBuildId;
	private String _name;
	private String _description;
	private String _externalReferencePK;
	private int _externalReferenceType;
	private long _jenkinsJobKey;
	private long _number;
	private String _environmentHash;

}