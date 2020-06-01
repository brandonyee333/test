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
 * This class is a wrapper for {@link JIRAProject}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProject
 * @generated
 */
public class JIRAProjectWrapper
	implements JIRAProject, ModelWrapper<JIRAProject> {

	public JIRAProjectWrapper(JIRAProject jiraProject) {
		_jiraProject = jiraProject;
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAProject.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAProject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraProjectId", getJiraProjectId());
		attributes.put("key", getKey());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraProjectId = (Long)attributes.get("jiraProjectId");

		if (jiraProjectId != null) {
			setJiraProjectId(jiraProjectId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public Object clone() {
		return new JIRAProjectWrapper((JIRAProject)_jiraProject.clone());
	}

	@Override
	public int compareTo(JIRAProject jiraProject) {
		return _jiraProject.compareTo(jiraProject);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _jiraProject.getExpandoBridge();
	}

	/**
	 * Returns the jira project ID of this jira project.
	 *
	 * @return the jira project ID of this jira project
	 */
	@Override
	public long getJiraProjectId() {
		return _jiraProject.getJiraProjectId();
	}

	/**
	 * Returns the key of this jira project.
	 *
	 * @return the key of this jira project
	 */
	@Override
	public String getKey() {
		return _jiraProject.getKey();
	}

	/**
	 * Returns the name of this jira project.
	 *
	 * @return the name of this jira project
	 */
	@Override
	public String getName() {
		return _jiraProject.getName();
	}

	/**
	 * Returns the primary key of this jira project.
	 *
	 * @return the primary key of this jira project
	 */
	@Override
	public long getPrimaryKey() {
		return _jiraProject.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jiraProject.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _jiraProject.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _jiraProject.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _jiraProject.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _jiraProject.isNew();
	}

	@Override
	public void persist() {
		_jiraProject.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jiraProject.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_jiraProject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_jiraProject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_jiraProject.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the jira project ID of this jira project.
	 *
	 * @param jiraProjectId the jira project ID of this jira project
	 */
	@Override
	public void setJiraProjectId(long jiraProjectId) {
		_jiraProject.setJiraProjectId(jiraProjectId);
	}

	/**
	 * Sets the key of this jira project.
	 *
	 * @param key the key of this jira project
	 */
	@Override
	public void setKey(String key) {
		_jiraProject.setKey(key);
	}

	/**
	 * Sets the name of this jira project.
	 *
	 * @param name the name of this jira project
	 */
	@Override
	public void setName(String name) {
		_jiraProject.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_jiraProject.setNew(n);
	}

	/**
	 * Sets the primary key of this jira project.
	 *
	 * @param primaryKey the primary key of this jira project
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jiraProject.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_jiraProject.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<JIRAProject>
		toCacheModel() {

		return _jiraProject.toCacheModel();
	}

	@Override
	public JIRAProject toEscapedModel() {
		return new JIRAProjectWrapper(_jiraProject.toEscapedModel());
	}

	@Override
	public String toString() {
		return _jiraProject.toString();
	}

	@Override
	public JIRAProject toUnescapedModel() {
		return new JIRAProjectWrapper(_jiraProject.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _jiraProject.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAProjectWrapper)) {
			return false;
		}

		JIRAProjectWrapper jiraProjectWrapper = (JIRAProjectWrapper)obj;

		if (Objects.equals(_jiraProject, jiraProjectWrapper._jiraProject)) {
			return true;
		}

		return false;
	}

	@Override
	public JIRAProject getWrappedModel() {
		return _jiraProject;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jiraProject.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jiraProject.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jiraProject.resetOriginalValues();
	}

	private final JIRAProject _jiraProject;

}