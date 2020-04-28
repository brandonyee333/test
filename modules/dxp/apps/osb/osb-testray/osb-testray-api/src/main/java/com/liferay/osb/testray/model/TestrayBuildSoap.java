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
public class TestrayBuildSoap implements Serializable {

	public static TestrayBuildSoap toSoapModel(TestrayBuild model) {
		TestrayBuildSoap soapModel = new TestrayBuildSoap();

		soapModel.setTestrayBuildId(model.getTestrayBuildId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTemplateTestrayBuildId(model.getTemplateTestrayBuildId());
		soapModel.setTestrayRoutineId(model.getTestrayRoutineId());
		soapModel.setTestrayProductVersionId(
			model.getTestrayProductVersionId());
		soapModel.setTestrayProjectId(model.getTestrayProjectId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setDescriptionType(model.getDescriptionType());
		soapModel.setTemplate(model.isTemplate());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setGitHash(model.getGitHash());
		soapModel.setGithubCompareURLs(model.getGithubCompareURLs());
		soapModel.setPromoted(model.isPromoted());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static TestrayBuildSoap[] toSoapModels(TestrayBuild[] models) {
		TestrayBuildSoap[] soapModels = new TestrayBuildSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayBuildSoap[][] toSoapModels(TestrayBuild[][] models) {
		TestrayBuildSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TestrayBuildSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayBuildSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayBuildSoap[] toSoapModels(List<TestrayBuild> models) {
		List<TestrayBuildSoap> soapModels = new ArrayList<TestrayBuildSoap>(
			models.size());

		for (TestrayBuild model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestrayBuildSoap[soapModels.size()]);
	}

	public TestrayBuildSoap() {
	}

	public long getPrimaryKey() {
		return _testrayBuildId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayBuildId(pk);
	}

	public long getTestrayBuildId() {
		return _testrayBuildId;
	}

	public void setTestrayBuildId(long testrayBuildId) {
		_testrayBuildId = testrayBuildId;
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

	public long getTemplateTestrayBuildId() {
		return _templateTestrayBuildId;
	}

	public void setTemplateTestrayBuildId(long templateTestrayBuildId) {
		_templateTestrayBuildId = templateTestrayBuildId;
	}

	public long getTestrayRoutineId() {
		return _testrayRoutineId;
	}

	public void setTestrayRoutineId(long testrayRoutineId) {
		_testrayRoutineId = testrayRoutineId;
	}

	public long getTestrayProductVersionId() {
		return _testrayProductVersionId;
	}

	public void setTestrayProductVersionId(long testrayProductVersionId) {
		_testrayProductVersionId = testrayProductVersionId;
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

	public boolean getTemplate() {
		return _template;
	}

	public boolean isTemplate() {
		return _template;
	}

	public void setTemplate(boolean template) {
		_template = template;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public String getGitHash() {
		return _gitHash;
	}

	public void setGitHash(String gitHash) {
		_gitHash = gitHash;
	}

	public String getGithubCompareURLs() {
		return _githubCompareURLs;
	}

	public void setGithubCompareURLs(String githubCompareURLs) {
		_githubCompareURLs = githubCompareURLs;
	}

	public boolean getPromoted() {
		return _promoted;
	}

	public boolean isPromoted() {
		return _promoted;
	}

	public void setPromoted(boolean promoted) {
		_promoted = promoted;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _testrayBuildId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _templateTestrayBuildId;
	private long _testrayRoutineId;
	private long _testrayProductVersionId;
	private long _testrayProjectId;
	private String _name;
	private String _description;
	private String _descriptionType;
	private boolean _template;
	private Date _dueDate;
	private String _gitHash;
	private String _githubCompareURLs;
	private boolean _promoted;
	private int _status;

}