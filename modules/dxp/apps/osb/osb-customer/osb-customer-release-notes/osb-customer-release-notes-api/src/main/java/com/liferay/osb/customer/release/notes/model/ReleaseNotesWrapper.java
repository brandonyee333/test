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

package com.liferay.osb.customer.release.notes.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ReleaseNotes}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReleaseNotes
 * @generated
 */
public class ReleaseNotesWrapper
	implements ModelWrapper<ReleaseNotes>, ReleaseNotes {

	public ReleaseNotesWrapper(ReleaseNotes releaseNotes) {
		_releaseNotes = releaseNotes;
	}

	@Override
	public Class<?> getModelClass() {
		return ReleaseNotes.class;
	}

	@Override
	public String getModelClassName() {
		return ReleaseNotes.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("releaseNotesId", getReleaseNotesId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("jiraIssueKeys", getJiraIssueKeys());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long releaseNotesId = (Long)attributes.get("releaseNotesId");

		if (releaseNotesId != null) {
			setReleaseNotesId(releaseNotesId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String jiraIssueKeys = (String)attributes.get("jiraIssueKeys");

		if (jiraIssueKeys != null) {
			setJiraIssueKeys(jiraIssueKeys);
		}
	}

	@Override
	public Object clone() {
		return new ReleaseNotesWrapper((ReleaseNotes)_releaseNotes.clone());
	}

	@Override
	public int compareTo(ReleaseNotes releaseNotes) {
		return _releaseNotes.compareTo(releaseNotes);
	}

	/**
	 * Returns the create date of this release notes.
	 *
	 * @return the create date of this release notes
	 */
	@Override
	public Date getCreateDate() {
		return _releaseNotes.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _releaseNotes.getExpandoBridge();
	}

	/**
	 * Returns the jira issue keys of this release notes.
	 *
	 * @return the jira issue keys of this release notes
	 */
	@Override
	public String getJiraIssueKeys() {
		return _releaseNotes.getJiraIssueKeys();
	}

	@Override
	public String[] getJiraIssueKeysArray() {
		return _releaseNotes.getJiraIssueKeysArray();
	}

	/**
	 * Returns the modified date of this release notes.
	 *
	 * @return the modified date of this release notes
	 */
	@Override
	public Date getModifiedDate() {
		return _releaseNotes.getModifiedDate();
	}

	/**
	 * Returns the name of this release notes.
	 *
	 * @return the name of this release notes
	 */
	@Override
	public String getName() {
		return _releaseNotes.getName();
	}

	/**
	 * Returns the primary key of this release notes.
	 *
	 * @return the primary key of this release notes
	 */
	@Override
	public long getPrimaryKey() {
		return _releaseNotes.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _releaseNotes.getPrimaryKeyObj();
	}

	/**
	 * Returns the release notes ID of this release notes.
	 *
	 * @return the release notes ID of this release notes
	 */
	@Override
	public long getReleaseNotesId() {
		return _releaseNotes.getReleaseNotesId();
	}

	/**
	 * Returns the user ID of this release notes.
	 *
	 * @return the user ID of this release notes
	 */
	@Override
	public long getUserId() {
		return _releaseNotes.getUserId();
	}

	/**
	 * Returns the user name of this release notes.
	 *
	 * @return the user name of this release notes
	 */
	@Override
	public String getUserName() {
		return _releaseNotes.getUserName();
	}

	/**
	 * Returns the user uuid of this release notes.
	 *
	 * @return the user uuid of this release notes
	 */
	@Override
	public String getUserUuid() {
		return _releaseNotes.getUserUuid();
	}

	/**
	 * Returns the uuid of this release notes.
	 *
	 * @return the uuid of this release notes
	 */
	@Override
	public String getUuid() {
		return _releaseNotes.getUuid();
	}

	@Override
	public int hashCode() {
		return _releaseNotes.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _releaseNotes.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _releaseNotes.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _releaseNotes.isNew();
	}

	@Override
	public void persist() {
		_releaseNotes.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_releaseNotes.setCachedModel(cachedModel);
	}

	/**
	 * Sets the create date of this release notes.
	 *
	 * @param createDate the create date of this release notes
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_releaseNotes.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_releaseNotes.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_releaseNotes.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_releaseNotes.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the jira issue keys of this release notes.
	 *
	 * @param jiraIssueKeys the jira issue keys of this release notes
	 */
	@Override
	public void setJiraIssueKeys(String jiraIssueKeys) {
		_releaseNotes.setJiraIssueKeys(jiraIssueKeys);
	}

	/**
	 * Sets the modified date of this release notes.
	 *
	 * @param modifiedDate the modified date of this release notes
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_releaseNotes.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this release notes.
	 *
	 * @param name the name of this release notes
	 */
	@Override
	public void setName(String name) {
		_releaseNotes.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_releaseNotes.setNew(n);
	}

	/**
	 * Sets the primary key of this release notes.
	 *
	 * @param primaryKey the primary key of this release notes
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_releaseNotes.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_releaseNotes.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the release notes ID of this release notes.
	 *
	 * @param releaseNotesId the release notes ID of this release notes
	 */
	@Override
	public void setReleaseNotesId(long releaseNotesId) {
		_releaseNotes.setReleaseNotesId(releaseNotesId);
	}

	/**
	 * Sets the user ID of this release notes.
	 *
	 * @param userId the user ID of this release notes
	 */
	@Override
	public void setUserId(long userId) {
		_releaseNotes.setUserId(userId);
	}

	/**
	 * Sets the user name of this release notes.
	 *
	 * @param userName the user name of this release notes
	 */
	@Override
	public void setUserName(String userName) {
		_releaseNotes.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this release notes.
	 *
	 * @param userUuid the user uuid of this release notes
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_releaseNotes.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this release notes.
	 *
	 * @param uuid the uuid of this release notes
	 */
	@Override
	public void setUuid(String uuid) {
		_releaseNotes.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ReleaseNotes>
		toCacheModel() {

		return _releaseNotes.toCacheModel();
	}

	@Override
	public ReleaseNotes toEscapedModel() {
		return new ReleaseNotesWrapper(_releaseNotes.toEscapedModel());
	}

	@Override
	public String toString() {
		return _releaseNotes.toString();
	}

	@Override
	public ReleaseNotes toUnescapedModel() {
		return new ReleaseNotesWrapper(_releaseNotes.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _releaseNotes.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReleaseNotesWrapper)) {
			return false;
		}

		ReleaseNotesWrapper releaseNotesWrapper = (ReleaseNotesWrapper)obj;

		if (Objects.equals(_releaseNotes, releaseNotesWrapper._releaseNotes)) {
			return true;
		}

		return false;
	}

	@Override
	public ReleaseNotes getWrappedModel() {
		return _releaseNotes;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _releaseNotes.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _releaseNotes.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_releaseNotes.resetOriginalValues();
	}

	private final ReleaseNotes _releaseNotes;

}