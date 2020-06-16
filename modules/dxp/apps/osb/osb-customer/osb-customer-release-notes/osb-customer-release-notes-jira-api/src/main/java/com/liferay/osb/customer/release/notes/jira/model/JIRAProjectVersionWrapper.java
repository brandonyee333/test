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
 * This class is a wrapper for {@link JIRAProjectVersion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProjectVersion
 * @generated
 */
public class JIRAProjectVersionWrapper
	implements JIRAProjectVersion, ModelWrapper<JIRAProjectVersion> {

	public JIRAProjectVersionWrapper(JIRAProjectVersion jiraProjectVersion) {
		_jiraProjectVersion = jiraProjectVersion;
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAProjectVersion.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAProjectVersion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraProjectVersionId", getJiraProjectVersionId());
		attributes.put("jiraProjectId", getJiraProjectId());
		attributes.put("name", getName());
		attributes.put("released", getReleased());
		attributes.put("archived", getArchived());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraProjectVersionId = (Long)attributes.get(
			"jiraProjectVersionId");

		if (jiraProjectVersionId != null) {
			setJiraProjectVersionId(jiraProjectVersionId);
		}

		Long jiraProjectId = (Long)attributes.get("jiraProjectId");

		if (jiraProjectId != null) {
			setJiraProjectId(jiraProjectId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String released = (String)attributes.get("released");

		if (released != null) {
			setReleased(released);
		}

		String archived = (String)attributes.get("archived");

		if (archived != null) {
			setArchived(archived);
		}
	}

	@Override
	public Object clone() {
		return new JIRAProjectVersionWrapper(
			(JIRAProjectVersion)_jiraProjectVersion.clone());
	}

	@Override
	public int compareTo(JIRAProjectVersion jiraProjectVersion) {
		return _jiraProjectVersion.compareTo(jiraProjectVersion);
	}

	/**
	 * Returns the archived of this jira project version.
	 *
	 * @return the archived of this jira project version
	 */
	@Override
	public String getArchived() {
		return _jiraProjectVersion.getArchived();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _jiraProjectVersion.getExpandoBridge();
	}

	/**
	 * Returns the jira project ID of this jira project version.
	 *
	 * @return the jira project ID of this jira project version
	 */
	@Override
	public long getJiraProjectId() {
		return _jiraProjectVersion.getJiraProjectId();
	}

	/**
	 * Returns the jira project version ID of this jira project version.
	 *
	 * @return the jira project version ID of this jira project version
	 */
	@Override
	public long getJiraProjectVersionId() {
		return _jiraProjectVersion.getJiraProjectVersionId();
	}

	/**
	 * Returns the name of this jira project version.
	 *
	 * @return the name of this jira project version
	 */
	@Override
	public String getName() {
		return _jiraProjectVersion.getName();
	}

	/**
	 * Returns the primary key of this jira project version.
	 *
	 * @return the primary key of this jira project version
	 */
	@Override
	public long getPrimaryKey() {
		return _jiraProjectVersion.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jiraProjectVersion.getPrimaryKeyObj();
	}

	/**
	 * Returns the released of this jira project version.
	 *
	 * @return the released of this jira project version
	 */
	@Override
	public String getReleased() {
		return _jiraProjectVersion.getReleased();
	}

	@Override
	public int hashCode() {
		return _jiraProjectVersion.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _jiraProjectVersion.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _jiraProjectVersion.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _jiraProjectVersion.isNew();
	}

	@Override
	public void persist() {
		_jiraProjectVersion.persist();
	}

	/**
	 * Sets the archived of this jira project version.
	 *
	 * @param archived the archived of this jira project version
	 */
	@Override
	public void setArchived(String archived) {
		_jiraProjectVersion.setArchived(archived);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_jiraProjectVersion.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_jiraProjectVersion.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_jiraProjectVersion.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_jiraProjectVersion.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the jira project ID of this jira project version.
	 *
	 * @param jiraProjectId the jira project ID of this jira project version
	 */
	@Override
	public void setJiraProjectId(long jiraProjectId) {
		_jiraProjectVersion.setJiraProjectId(jiraProjectId);
	}

	/**
	 * Sets the jira project version ID of this jira project version.
	 *
	 * @param jiraProjectVersionId the jira project version ID of this jira project version
	 */
	@Override
	public void setJiraProjectVersionId(long jiraProjectVersionId) {
		_jiraProjectVersion.setJiraProjectVersionId(jiraProjectVersionId);
	}

	/**
	 * Sets the name of this jira project version.
	 *
	 * @param name the name of this jira project version
	 */
	@Override
	public void setName(String name) {
		_jiraProjectVersion.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_jiraProjectVersion.setNew(n);
	}

	/**
	 * Sets the primary key of this jira project version.
	 *
	 * @param primaryKey the primary key of this jira project version
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_jiraProjectVersion.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_jiraProjectVersion.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the released of this jira project version.
	 *
	 * @param released the released of this jira project version
	 */
	@Override
	public void setReleased(String released) {
		_jiraProjectVersion.setReleased(released);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<JIRAProjectVersion>
		toCacheModel() {

		return _jiraProjectVersion.toCacheModel();
	}

	@Override
	public JIRAProjectVersion toEscapedModel() {
		return new JIRAProjectVersionWrapper(
			_jiraProjectVersion.toEscapedModel());
	}

	@Override
	public String toString() {
		return _jiraProjectVersion.toString();
	}

	@Override
	public JIRAProjectVersion toUnescapedModel() {
		return new JIRAProjectVersionWrapper(
			_jiraProjectVersion.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _jiraProjectVersion.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAProjectVersionWrapper)) {
			return false;
		}

		JIRAProjectVersionWrapper jiraProjectVersionWrapper =
			(JIRAProjectVersionWrapper)obj;

		if (Objects.equals(
				_jiraProjectVersion,
				jiraProjectVersionWrapper._jiraProjectVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public JIRAProjectVersion getWrappedModel() {
		return _jiraProjectVersion;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _jiraProjectVersion.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _jiraProjectVersion.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_jiraProjectVersion.resetOriginalValues();
	}

	private final JIRAProjectVersion _jiraProjectVersion;

}