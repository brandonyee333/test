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

package com.liferay.osb.customer.release.notes.jira.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class JIRAIssueSoap implements Serializable {
	public static JIRAIssueSoap toSoapModel(JIRAIssue model) {
		JIRAIssueSoap soapModel = new JIRAIssueSoap();

		soapModel.setJiraIssueId(model.getJiraIssueId());
		soapModel.setJiraProjectId(model.getJiraProjectId());
		soapModel.setIssueNumber(model.getIssueNumber());
		soapModel.setType(model.getType());
		soapModel.setSummary(model.getSummary());
		soapModel.setDescription(model.getDescription());
		soapModel.setPriority(model.getPriority());

		return soapModel;
	}

	public static JIRAIssueSoap[] toSoapModels(JIRAIssue[] models) {
		JIRAIssueSoap[] soapModels = new JIRAIssueSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static JIRAIssueSoap[][] toSoapModels(JIRAIssue[][] models) {
		JIRAIssueSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new JIRAIssueSoap[models.length][models[0].length];
		}
		else {
			soapModels = new JIRAIssueSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static JIRAIssueSoap[] toSoapModels(List<JIRAIssue> models) {
		List<JIRAIssueSoap> soapModels = new ArrayList<JIRAIssueSoap>(models.size());

		for (JIRAIssue model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JIRAIssueSoap[soapModels.size()]);
	}

	public JIRAIssueSoap() {
	}

	public long getPrimaryKey() {
		return _jiraIssueId;
	}

	public void setPrimaryKey(long pk) {
		setJiraIssueId(pk);
	}

	public long getJiraIssueId() {
		return _jiraIssueId;
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssueId = jiraIssueId;
	}

	public long getJiraProjectId() {
		return _jiraProjectId;
	}

	public void setJiraProjectId(long jiraProjectId) {
		_jiraProjectId = jiraProjectId;
	}

	public long getIssueNumber() {
		return _issueNumber;
	}

	public void setIssueNumber(long issueNumber) {
		_issueNumber = issueNumber;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getSummary() {
		return _summary;
	}

	public void setSummary(String summary) {
		_summary = summary;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	private long _jiraIssueId;
	private long _jiraProjectId;
	private long _issueNumber;
	private int _type;
	private String _summary;
	private String _description;
	private int _priority;
}