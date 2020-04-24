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
public class TestrayRequirementSoap implements Serializable {

	public static TestrayRequirementSoap toSoapModel(TestrayRequirement model) {
		TestrayRequirementSoap soapModel = new TestrayRequirementSoap();

		soapModel.setTestrayRequirementId(model.getTestrayRequirementId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTestrayComponentId(model.getTestrayComponentId());
		soapModel.setTestrayProjectId(model.getTestrayProjectId());
		soapModel.setKey(model.getKey());
		soapModel.setSummary(model.getSummary());
		soapModel.setComponents(model.getComponents());
		soapModel.setLinkTitle(model.getLinkTitle());
		soapModel.setLinkURL(model.getLinkURL());
		soapModel.setDescription(model.getDescription());
		soapModel.setDescriptionType(model.getDescriptionType());
		soapModel.setGoals(model.getGoals());
		soapModel.setGoalsType(model.getGoalsType());
		soapModel.setVariations(model.getVariations());
		soapModel.setVariationsType(model.getVariationsType());

		return soapModel;
	}

	public static TestrayRequirementSoap[] toSoapModels(
		TestrayRequirement[] models) {

		TestrayRequirementSoap[] soapModels =
			new TestrayRequirementSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayRequirementSoap[][] toSoapModels(
		TestrayRequirement[][] models) {

		TestrayRequirementSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TestrayRequirementSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayRequirementSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayRequirementSoap[] toSoapModels(
		List<TestrayRequirement> models) {

		List<TestrayRequirementSoap> soapModels =
			new ArrayList<TestrayRequirementSoap>(models.size());

		for (TestrayRequirement model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new TestrayRequirementSoap[soapModels.size()]);
	}

	public TestrayRequirementSoap() {
	}

	public long getPrimaryKey() {
		return _testrayRequirementId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayRequirementId(pk);
	}

	public long getTestrayRequirementId() {
		return _testrayRequirementId;
	}

	public void setTestrayRequirementId(long testrayRequirementId) {
		_testrayRequirementId = testrayRequirementId;
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

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getSummary() {
		return _summary;
	}

	public void setSummary(String summary) {
		_summary = summary;
	}

	public String getComponents() {
		return _components;
	}

	public void setComponents(String components) {
		_components = components;
	}

	public String getLinkTitle() {
		return _linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		_linkTitle = linkTitle;
	}

	public String getLinkURL() {
		return _linkURL;
	}

	public void setLinkURL(String linkURL) {
		_linkURL = linkURL;
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

	public String getGoals() {
		return _goals;
	}

	public void setGoals(String goals) {
		_goals = goals;
	}

	public String getGoalsType() {
		return _goalsType;
	}

	public void setGoalsType(String goalsType) {
		_goalsType = goalsType;
	}

	public String getVariations() {
		return _variations;
	}

	public void setVariations(String variations) {
		_variations = variations;
	}

	public String getVariationsType() {
		return _variationsType;
	}

	public void setVariationsType(String variationsType) {
		_variationsType = variationsType;
	}

	private long _testrayRequirementId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _testrayComponentId;
	private long _testrayProjectId;
	private String _key;
	private String _summary;
	private String _components;
	private String _linkTitle;
	private String _linkURL;
	private String _description;
	private String _descriptionType;
	private String _goals;
	private String _goalsType;
	private String _variations;
	private String _variationsType;

}