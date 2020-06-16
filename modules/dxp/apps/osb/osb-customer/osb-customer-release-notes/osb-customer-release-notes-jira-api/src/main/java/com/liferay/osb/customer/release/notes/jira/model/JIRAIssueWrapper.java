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

package com.liferay.osb.customer.release.notes.jira.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link JIRAIssue}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssue
 * @generated
 */
public class JIRAIssueWrapper implements JIRAIssue, ModelWrapper<JIRAIssue> {

	public JIRAIssueWrapper(JIRAIssue jiraIssue) {
		_jiraIssue = jiraIssue;
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAIssue.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAIssue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraIssueId", getJiraIssueId());
		attributes.put("jiraProjectId", getJiraProjectId());
		attributes.put("issueNumber", getIssueNumber());
		attributes.put("type", getType());
		attributes.put("summary", getSummary());
		attributes.put("description", getDescription());
		attributes.put("priority", getPriority());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraIssueId = (Long)attributes.get("jiraIssueId");

		if (jiraIssueId != null) {
			setJiraIssueId(jiraIssueId);
		}

		Long jiraProjectId = (Long)attributes.get("jiraProjectId");

		if (jiraProjectId != null) {
			setJiraProjectId(jiraProjectId);
		}

		Long issueNumber = (Long)attributes.get("issueNumber");

		if (issueNumber != null) {
			setIssueNumber(issueNumber);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer priority = (Integer)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}
	}

	@Override
	public Object clone() {
		return new JIRAIssueWrapper((JIRAIssue)_jiraIssue.clone());
	}

	@Override
	public int compareTo(JIRAIssue jiraIssue) {
		return _jiraIssue.compareTo(jiraIssue);
	}

	@Override
	public String getAPIChange() {
		return _jiraIssue.getAPIChange();
	}

	/**
	 * Returns the description of this jira issue.
	 *
	 * @return the description of this jira issue
	 */
	@Override
	public String getDescription() {
		return _jiraIssue.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _jiraIssue.getExpandoBridge();
	}

	@Override
	public java.util.List<JIRAIssue> getIsRelatedToJiraIssues() {
		return _jiraIssue.getIsRelatedToJiraIssues();
	}

	/**
	 * Returns the issue number of this jira issue.
	 *
	 * @return the issue number of this jira issue
	 */
	@Override
	public long getIssueNumber() {
		return _jiraIssue.getIssueNumber();
	}

	@Override
	public java.util.List<JIRAComponent> getJIRAComponents() {
		return _jiraIssue.getJIRAComponents();
	}

	/**
	 * Returns the jira issue ID of this jira issue.
	 *
	 * @return the jira issue ID of this jira issue
	 */
	@Override
	public long getJiraIssueId() {
		return _jiraIssue.getJiraIssueId();
	}

	/**
	 * Returns the jira project ID of this jira issue.
	 *
	 * @return the jira project ID of this jira issue
	 */
	@Override
	public long getJiraProjectId() {
		return _jiraIssue.getJiraProjectId();
	}

	@Override
	public String getKey() {
		return _jiraIssue.getKey();
	}

	/**
	 * Returns the primary key of this jira issue.
	 *
	 * @return the primary key of this jira issue
	 */
	@Override
	public long getPrimaryKey() {
		return _jiraIssue.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jiraIssue.getPrimaryKeyObj();
	}

	/**
	 * Returns the priority of this jira issue.
	 *
	 * @return the priority of this jira issue
	 */
	@Override
	public int getPriority() {
		return _jiraIssue.getPriority();
	}

	/**
	 * Returns the summary of this jira issue.
	 *
	 * @return the summary of this jira issue
	 */
	@Override
	public String getSummary() {
		return _jiraIssue.getSummary();
	}

	/**
	 * Returns the type of this jira issue.
	 *
	 * @return the type of this jira issue
	 */
	@Override
	public int getType() {
		return _jiraIssue.getType();
	}

	@Override
	public String getUpgradeNote() {
		return _jiraIssue.getUpgradeNote();
	}

	@Override
	public int hashCode() {
		return _jiraIssue.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _jiraIssue.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _jiraIssue.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _jiraIssue.isNew();
	}

	@Override
	public void persist() {
		_jiraIssue.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jiraIssue.setCachedModel(cachedModel);
	}

	/**
	 * Sets the description of this jira issue.
	 *
	 * @param description the description of this jira issue
	 */
	@Override
	public void setDescription(String description) {
		_jiraIssue.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_jiraIssue.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_jiraIssue.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_jiraIssue.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the issue number of this jira issue.
	 *
	 * @param issueNumber the issue number of this jira issue
	 */
	@Override
	public void setIssueNumber(long issueNumber) {
		_jiraIssue.setIssueNumber(issueNumber);
	}

	/**
	 * Sets the jira issue ID of this jira issue.
	 *
	 * @param jiraIssueId the jira issue ID of this jira issue
	 */
	@Override
	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssue.setJiraIssueId(jiraIssueId);
	}

	/**
	 * Sets the jira project ID of this jira issue.
	 *
	 * @param jiraProjectId the jira project ID of this jira issue
	 */
	@Override
	public void setJiraProjectId(long jiraProjectId) {
		_jiraIssue.setJiraProjectId(jiraProjectId);
	}

	@Override
	public void setNew(boolean n) {
		_jiraIssue.setNew(n);
	}

	/**
	 * Sets the primary key of this jira issue.
	 *
	 * @param primaryKey the primary key of this jira issue
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jiraIssue.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_jiraIssue.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the priority of this jira issue.
	 *
	 * @param priority the priority of this jira issue
	 */
	@Override
	public void setPriority(int priority) {
		_jiraIssue.setPriority(priority);
	}

	/**
	 * Sets the summary of this jira issue.
	 *
	 * @param summary the summary of this jira issue
	 */
	@Override
	public void setSummary(String summary) {
		_jiraIssue.setSummary(summary);
	}

	/**
	 * Sets the type of this jira issue.
	 *
	 * @param type the type of this jira issue
	 */
	@Override
	public void setType(int type) {
		_jiraIssue.setType(type);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<JIRAIssue>
		toCacheModel() {

		return _jiraIssue.toCacheModel();
	}

	@Override
	public JIRAIssue toEscapedModel() {
		return new JIRAIssueWrapper(_jiraIssue.toEscapedModel());
	}

	@Override
	public String toString() {
		return _jiraIssue.toString();
	}

	@Override
	public JIRAIssue toUnescapedModel() {
		return new JIRAIssueWrapper(_jiraIssue.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _jiraIssue.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAIssueWrapper)) {
			return false;
		}

		JIRAIssueWrapper jiraIssueWrapper = (JIRAIssueWrapper)obj;

		if (Objects.equals(_jiraIssue, jiraIssueWrapper._jiraIssue)) {
			return true;
		}

		return false;
	}

	@Override
	public JIRAIssue getWrappedModel() {
		return _jiraIssue;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jiraIssue.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jiraIssue.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jiraIssue.resetOriginalValues();
	}

	private final JIRAIssue _jiraIssue;

}