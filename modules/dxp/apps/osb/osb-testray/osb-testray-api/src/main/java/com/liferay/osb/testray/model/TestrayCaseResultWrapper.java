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
 * This class is a wrapper for {@link TestrayCaseResult}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayCaseResult
 * @generated
 */
public class TestrayCaseResultWrapper
	implements ModelWrapper<TestrayCaseResult>, TestrayCaseResult {

	public TestrayCaseResultWrapper(TestrayCaseResult testrayCaseResult) {
		_testrayCaseResult = testrayCaseResult;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayCaseResult.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayCaseResult.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayCaseResultId", getTestrayCaseResultId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commentMBMessageId", getCommentMBMessageId());
		attributes.put("testrayBuildId", getTestrayBuildId());
		attributes.put("testrayCaseId", getTestrayCaseId());
		attributes.put("testrayComponentId", getTestrayComponentId());
		attributes.put("testrayRunId", getTestrayRunId());
		attributes.put("assignedUserId", getAssignedUserId());
		attributes.put("startDate", getStartDate());
		attributes.put("closedDate", getClosedDate());
		attributes.put("attachments", getAttachments());
		attributes.put("errors", getErrors());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayCaseResultId = (Long)attributes.get("testrayCaseResultId");

		if (testrayCaseResultId != null) {
			setTestrayCaseResultId(testrayCaseResultId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		Long commentMBMessageId = (Long)attributes.get("commentMBMessageId");

		if (commentMBMessageId != null) {
			setCommentMBMessageId(commentMBMessageId);
		}

		Long testrayBuildId = (Long)attributes.get("testrayBuildId");

		if (testrayBuildId != null) {
			setTestrayBuildId(testrayBuildId);
		}

		Long testrayCaseId = (Long)attributes.get("testrayCaseId");

		if (testrayCaseId != null) {
			setTestrayCaseId(testrayCaseId);
		}

		Long testrayComponentId = (Long)attributes.get("testrayComponentId");

		if (testrayComponentId != null) {
			setTestrayComponentId(testrayComponentId);
		}

		Long testrayRunId = (Long)attributes.get("testrayRunId");

		if (testrayRunId != null) {
			setTestrayRunId(testrayRunId);
		}

		Long assignedUserId = (Long)attributes.get("assignedUserId");

		if (assignedUserId != null) {
			setAssignedUserId(assignedUserId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date closedDate = (Date)attributes.get("closedDate");

		if (closedDate != null) {
			setClosedDate(closedDate);
		}

		String attachments = (String)attributes.get("attachments");

		if (attachments != null) {
			setAttachments(attachments);
		}

		String errors = (String)attributes.get("errors");

		if (errors != null) {
			setErrors(errors);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new TestrayCaseResultWrapper(
			(TestrayCaseResult)_testrayCaseResult.clone());
	}

	@Override
	public int compareTo(TestrayCaseResult testrayCaseResult) {
		return _testrayCaseResult.compareTo(testrayCaseResult);
	}

	/**
	 * Returns the assigned user ID of this testray case result.
	 *
	 * @return the assigned user ID of this testray case result
	 */
	@Override
	public long getAssignedUserId() {
		return _testrayCaseResult.getAssignedUserId();
	}

	/**
	 * Returns the assigned user uuid of this testray case result.
	 *
	 * @return the assigned user uuid of this testray case result
	 */
	@Override
	public String getAssignedUserUuid() {
		return _testrayCaseResult.getAssignedUserUuid();
	}

	/**
	 * Returns the attachments of this testray case result.
	 *
	 * @return the attachments of this testray case result
	 */
	@Override
	public String getAttachments() {
		return _testrayCaseResult.getAttachments();
	}

	/**
	 * Returns the closed date of this testray case result.
	 *
	 * @return the closed date of this testray case result
	 */
	@Override
	public Date getClosedDate() {
		return _testrayCaseResult.getClosedDate();
	}

	/**
	 * Returns the comment mb message ID of this testray case result.
	 *
	 * @return the comment mb message ID of this testray case result
	 */
	@Override
	public long getCommentMBMessageId() {
		return _testrayCaseResult.getCommentMBMessageId();
	}

	/**
	 * Returns the company ID of this testray case result.
	 *
	 * @return the company ID of this testray case result
	 */
	@Override
	public long getCompanyId() {
		return _testrayCaseResult.getCompanyId();
	}

	/**
	 * Returns the create date of this testray case result.
	 *
	 * @return the create date of this testray case result
	 */
	@Override
	public Date getCreateDate() {
		return _testrayCaseResult.getCreateDate();
	}

	/**
	 * Returns the errors of this testray case result.
	 *
	 * @return the errors of this testray case result
	 */
	@Override
	public String getErrors() {
		return _testrayCaseResult.getErrors();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayCaseResult.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray case result.
	 *
	 * @return the group ID of this testray case result
	 */
	@Override
	public long getGroupId() {
		return _testrayCaseResult.getGroupId();
	}

	/**
	 * Returns the modified date of this testray case result.
	 *
	 * @return the modified date of this testray case result
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayCaseResult.getModifiedDate();
	}

	/**
	 * Returns the primary key of this testray case result.
	 *
	 * @return the primary key of this testray case result
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayCaseResult.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayCaseResult.getPrimaryKeyObj();
	}

	/**
	 * Returns the start date of this testray case result.
	 *
	 * @return the start date of this testray case result
	 */
	@Override
	public Date getStartDate() {
		return _testrayCaseResult.getStartDate();
	}

	/**
	 * Returns the status of this testray case result.
	 *
	 * @return the status of this testray case result
	 */
	@Override
	public int getStatus() {
		return _testrayCaseResult.getStatus();
	}

	/**
	 * Returns the testray build ID of this testray case result.
	 *
	 * @return the testray build ID of this testray case result
	 */
	@Override
	public long getTestrayBuildId() {
		return _testrayCaseResult.getTestrayBuildId();
	}

	/**
	 * Returns the testray case ID of this testray case result.
	 *
	 * @return the testray case ID of this testray case result
	 */
	@Override
	public long getTestrayCaseId() {
		return _testrayCaseResult.getTestrayCaseId();
	}

	/**
	 * Returns the testray case result ID of this testray case result.
	 *
	 * @return the testray case result ID of this testray case result
	 */
	@Override
	public long getTestrayCaseResultId() {
		return _testrayCaseResult.getTestrayCaseResultId();
	}

	/**
	 * Returns the testray component ID of this testray case result.
	 *
	 * @return the testray component ID of this testray case result
	 */
	@Override
	public long getTestrayComponentId() {
		return _testrayCaseResult.getTestrayComponentId();
	}

	/**
	 * Returns the testray run ID of this testray case result.
	 *
	 * @return the testray run ID of this testray case result
	 */
	@Override
	public long getTestrayRunId() {
		return _testrayCaseResult.getTestrayRunId();
	}

	/**
	 * Returns the user ID of this testray case result.
	 *
	 * @return the user ID of this testray case result
	 */
	@Override
	public long getUserId() {
		return _testrayCaseResult.getUserId();
	}

	/**
	 * Returns the user name of this testray case result.
	 *
	 * @return the user name of this testray case result
	 */
	@Override
	public String getUserName() {
		return _testrayCaseResult.getUserName();
	}

	/**
	 * Returns the user uuid of this testray case result.
	 *
	 * @return the user uuid of this testray case result
	 */
	@Override
	public String getUserUuid() {
		return _testrayCaseResult.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayCaseResult.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayCaseResult.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayCaseResult.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayCaseResult.isNew();
	}

	@Override
	public void persist() {
		_testrayCaseResult.persist();
	}

	/**
	 * Sets the assigned user ID of this testray case result.
	 *
	 * @param assignedUserId the assigned user ID of this testray case result
	 */
	@Override
	public void setAssignedUserId(long assignedUserId) {
		_testrayCaseResult.setAssignedUserId(assignedUserId);
	}

	/**
	 * Sets the assigned user uuid of this testray case result.
	 *
	 * @param assignedUserUuid the assigned user uuid of this testray case result
	 */
	@Override
	public void setAssignedUserUuid(String assignedUserUuid) {
		_testrayCaseResult.setAssignedUserUuid(assignedUserUuid);
	}

	/**
	 * Sets the attachments of this testray case result.
	 *
	 * @param attachments the attachments of this testray case result
	 */
	@Override
	public void setAttachments(String attachments) {
		_testrayCaseResult.setAttachments(attachments);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayCaseResult.setCachedModel(cachedModel);
	}

	/**
	 * Sets the closed date of this testray case result.
	 *
	 * @param closedDate the closed date of this testray case result
	 */
	@Override
	public void setClosedDate(Date closedDate) {
		_testrayCaseResult.setClosedDate(closedDate);
	}

	/**
	 * Sets the comment mb message ID of this testray case result.
	 *
	 * @param commentMBMessageId the comment mb message ID of this testray case result
	 */
	@Override
	public void setCommentMBMessageId(long commentMBMessageId) {
		_testrayCaseResult.setCommentMBMessageId(commentMBMessageId);
	}

	/**
	 * Sets the company ID of this testray case result.
	 *
	 * @param companyId the company ID of this testray case result
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayCaseResult.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this testray case result.
	 *
	 * @param createDate the create date of this testray case result
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayCaseResult.setCreateDate(createDate);
	}

	/**
	 * Sets the errors of this testray case result.
	 *
	 * @param errors the errors of this testray case result
	 */
	@Override
	public void setErrors(String errors) {
		_testrayCaseResult.setErrors(errors);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayCaseResult.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayCaseResult.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayCaseResult.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray case result.
	 *
	 * @param groupId the group ID of this testray case result
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayCaseResult.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray case result.
	 *
	 * @param modifiedDate the modified date of this testray case result
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayCaseResult.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_testrayCaseResult.setNew(n);
	}

	/**
	 * Sets the primary key of this testray case result.
	 *
	 * @param primaryKey the primary key of this testray case result
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayCaseResult.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayCaseResult.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the start date of this testray case result.
	 *
	 * @param startDate the start date of this testray case result
	 */
	@Override
	public void setStartDate(Date startDate) {
		_testrayCaseResult.setStartDate(startDate);
	}

	/**
	 * Sets the status of this testray case result.
	 *
	 * @param status the status of this testray case result
	 */
	@Override
	public void setStatus(int status) {
		_testrayCaseResult.setStatus(status);
	}

	/**
	 * Sets the testray build ID of this testray case result.
	 *
	 * @param testrayBuildId the testray build ID of this testray case result
	 */
	@Override
	public void setTestrayBuildId(long testrayBuildId) {
		_testrayCaseResult.setTestrayBuildId(testrayBuildId);
	}

	/**
	 * Sets the testray case ID of this testray case result.
	 *
	 * @param testrayCaseId the testray case ID of this testray case result
	 */
	@Override
	public void setTestrayCaseId(long testrayCaseId) {
		_testrayCaseResult.setTestrayCaseId(testrayCaseId);
	}

	/**
	 * Sets the testray case result ID of this testray case result.
	 *
	 * @param testrayCaseResultId the testray case result ID of this testray case result
	 */
	@Override
	public void setTestrayCaseResultId(long testrayCaseResultId) {
		_testrayCaseResult.setTestrayCaseResultId(testrayCaseResultId);
	}

	/**
	 * Sets the testray component ID of this testray case result.
	 *
	 * @param testrayComponentId the testray component ID of this testray case result
	 */
	@Override
	public void setTestrayComponentId(long testrayComponentId) {
		_testrayCaseResult.setTestrayComponentId(testrayComponentId);
	}

	/**
	 * Sets the testray run ID of this testray case result.
	 *
	 * @param testrayRunId the testray run ID of this testray case result
	 */
	@Override
	public void setTestrayRunId(long testrayRunId) {
		_testrayCaseResult.setTestrayRunId(testrayRunId);
	}

	/**
	 * Sets the user ID of this testray case result.
	 *
	 * @param userId the user ID of this testray case result
	 */
	@Override
	public void setUserId(long userId) {
		_testrayCaseResult.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray case result.
	 *
	 * @param userName the user name of this testray case result
	 */
	@Override
	public void setUserName(String userName) {
		_testrayCaseResult.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray case result.
	 *
	 * @param userUuid the user uuid of this testray case result
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayCaseResult.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayCaseResult>
		toCacheModel() {

		return _testrayCaseResult.toCacheModel();
	}

	@Override
	public TestrayCaseResult toEscapedModel() {
		return new TestrayCaseResultWrapper(
			_testrayCaseResult.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayCaseResult.toString();
	}

	@Override
	public TestrayCaseResult toUnescapedModel() {
		return new TestrayCaseResultWrapper(
			_testrayCaseResult.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayCaseResult.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayCaseResultWrapper)) {
			return false;
		}

		TestrayCaseResultWrapper testrayCaseResultWrapper =
			(TestrayCaseResultWrapper)obj;

		if (Objects.equals(
				_testrayCaseResult,
				testrayCaseResultWrapper._testrayCaseResult)) {

			return true;
		}

		return false;
	}

	@Override
	public TestrayCaseResult getWrappedModel() {
		return _testrayCaseResult;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayCaseResult.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayCaseResult.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayCaseResult.resetOriginalValues();
	}

	private final TestrayCaseResult _testrayCaseResult;

}