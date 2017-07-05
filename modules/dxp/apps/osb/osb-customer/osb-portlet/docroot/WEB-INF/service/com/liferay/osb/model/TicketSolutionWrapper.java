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

package com.liferay.osb.model;

import aQute.bnd.annotation.ProviderType;

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
 * This class is a wrapper for {@link TicketSolution}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketSolution
 * @generated
 */
@ProviderType
public class TicketSolutionWrapper implements TicketSolution,
	ModelWrapper<TicketSolution> {
	public TicketSolutionWrapper(TicketSolution ticketSolution) {
		_ticketSolution = ticketSolution;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketSolution.class;
	}

	@Override
	public String getModelClassName() {
		return TicketSolution.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketSolutionId", getTicketSolutionId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("summary", getSummary());
		attributes.put("useCustomerSummary", getUseCustomerSummary());
		attributes.put("issueType", getIssueType());
		attributes.put("solution", getSolution());
		attributes.put("type", getType());
		attributes.put("customerSpecific", getCustomerSpecific());
		attributes.put("environmentSpecific", getEnvironmentSpecific());
		attributes.put("versionSpecific", getVersionSpecific());
		attributes.put("reviewForKB", getReviewForKB());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("statusMessage", getStatusMessage());
		attributes.put("statusReason", getStatusReason());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketSolutionId = (Long)attributes.get("ticketSolutionId");

		if (ticketSolutionId != null) {
			setTicketSolutionId(ticketSolutionId);
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

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}

		Boolean useCustomerSummary = (Boolean)attributes.get(
				"useCustomerSummary");

		if (useCustomerSummary != null) {
			setUseCustomerSummary(useCustomerSummary);
		}

		Integer issueType = (Integer)attributes.get("issueType");

		if (issueType != null) {
			setIssueType(issueType);
		}

		String solution = (String)attributes.get("solution");

		if (solution != null) {
			setSolution(solution);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Boolean customerSpecific = (Boolean)attributes.get("customerSpecific");

		if (customerSpecific != null) {
			setCustomerSpecific(customerSpecific);
		}

		Boolean environmentSpecific = (Boolean)attributes.get(
				"environmentSpecific");

		if (environmentSpecific != null) {
			setEnvironmentSpecific(environmentSpecific);
		}

		Boolean versionSpecific = (Boolean)attributes.get("versionSpecific");

		if (versionSpecific != null) {
			setVersionSpecific(versionSpecific);
		}

		Boolean reviewForKB = (Boolean)attributes.get("reviewForKB");

		if (reviewForKB != null) {
			setReviewForKB(reviewForKB);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String statusMessage = (String)attributes.get("statusMessage");

		if (statusMessage != null) {
			setStatusMessage(statusMessage);
		}

		Integer statusReason = (Integer)attributes.get("statusReason");

		if (statusReason != null) {
			setStatusReason(statusReason);
		}
	}

	@Override
	public TicketSolution toEscapedModel() {
		return new TicketSolutionWrapper(_ticketSolution.toEscapedModel());
	}

	@Override
	public TicketSolution toUnescapedModel() {
		return new TicketSolutionWrapper(_ticketSolution.toUnescapedModel());
	}

	/**
	* Returns the customer specific of this ticket solution.
	*
	* @return the customer specific of this ticket solution
	*/
	@Override
	public boolean getCustomerSpecific() {
		return _ticketSolution.getCustomerSpecific();
	}

	/**
	* Returns the environment specific of this ticket solution.
	*
	* @return the environment specific of this ticket solution
	*/
	@Override
	public boolean getEnvironmentSpecific() {
		return _ticketSolution.getEnvironmentSpecific();
	}

	/**
	* Returns the review for kb of this ticket solution.
	*
	* @return the review for kb of this ticket solution
	*/
	@Override
	public boolean getReviewForKB() {
		return _ticketSolution.getReviewForKB();
	}

	/**
	* Returns the use customer summary of this ticket solution.
	*
	* @return the use customer summary of this ticket solution
	*/
	@Override
	public boolean getUseCustomerSummary() {
		return _ticketSolution.getUseCustomerSummary();
	}

	/**
	* Returns the version specific of this ticket solution.
	*
	* @return the version specific of this ticket solution
	*/
	@Override
	public boolean getVersionSpecific() {
		return _ticketSolution.getVersionSpecific();
	}

	/**
	* Returns <code>true</code> if this ticket solution is approved.
	*
	* @return <code>true</code> if this ticket solution is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _ticketSolution.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _ticketSolution.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ticket solution is customer specific.
	*
	* @return <code>true</code> if this ticket solution is customer specific; <code>false</code> otherwise
	*/
	@Override
	public boolean isCustomerSpecific() {
		return _ticketSolution.isCustomerSpecific();
	}

	/**
	* Returns <code>true</code> if this ticket solution is denied.
	*
	* @return <code>true</code> if this ticket solution is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _ticketSolution.isDenied();
	}

	/**
	* Returns <code>true</code> if this ticket solution is a draft.
	*
	* @return <code>true</code> if this ticket solution is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _ticketSolution.isDraft();
	}

	/**
	* Returns <code>true</code> if this ticket solution is environment specific.
	*
	* @return <code>true</code> if this ticket solution is environment specific; <code>false</code> otherwise
	*/
	@Override
	public boolean isEnvironmentSpecific() {
		return _ticketSolution.isEnvironmentSpecific();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketSolution.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this ticket solution is expired.
	*
	* @return <code>true</code> if this ticket solution is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _ticketSolution.isExpired();
	}

	/**
	* Returns <code>true</code> if this ticket solution is inactive.
	*
	* @return <code>true</code> if this ticket solution is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _ticketSolution.isInactive();
	}

	/**
	* Returns <code>true</code> if this ticket solution is incomplete.
	*
	* @return <code>true</code> if this ticket solution is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _ticketSolution.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _ticketSolution.isNew();
	}

	/**
	* Returns <code>true</code> if this ticket solution is pending.
	*
	* @return <code>true</code> if this ticket solution is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _ticketSolution.isPending();
	}

	/**
	* Returns <code>true</code> if this ticket solution is review for kb.
	*
	* @return <code>true</code> if this ticket solution is review for kb; <code>false</code> otherwise
	*/
	@Override
	public boolean isReviewForKB() {
		return _ticketSolution.isReviewForKB();
	}

	/**
	* Returns <code>true</code> if this ticket solution is scheduled.
	*
	* @return <code>true</code> if this ticket solution is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _ticketSolution.isScheduled();
	}

	/**
	* Returns <code>true</code> if this ticket solution is use customer summary.
	*
	* @return <code>true</code> if this ticket solution is use customer summary; <code>false</code> otherwise
	*/
	@Override
	public boolean isUseCustomerSummary() {
		return _ticketSolution.isUseCustomerSummary();
	}

	/**
	* Returns <code>true</code> if this ticket solution is version specific.
	*
	* @return <code>true</code> if this ticket solution is version specific; <code>false</code> otherwise
	*/
	@Override
	public boolean isVersionSpecific() {
		return _ticketSolution.isVersionSpecific();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketSolution.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketSolution> toCacheModel() {
		return _ticketSolution.toCacheModel();
	}

	@Override
	public int compareTo(TicketSolution ticketSolution) {
		return _ticketSolution.compareTo(ticketSolution);
	}

	/**
	* Returns the issue type of this ticket solution.
	*
	* @return the issue type of this ticket solution
	*/
	@Override
	public int getIssueType() {
		return _ticketSolution.getIssueType();
	}

	/**
	* Returns the status of this ticket solution.
	*
	* @return the status of this ticket solution
	*/
	@Override
	public int getStatus() {
		return _ticketSolution.getStatus();
	}

	/**
	* Returns the status reason of this ticket solution.
	*
	* @return the status reason of this ticket solution
	*/
	@Override
	public int getStatusReason() {
		return _ticketSolution.getStatusReason();
	}

	/**
	* Returns the type of this ticket solution.
	*
	* @return the type of this ticket solution
	*/
	@Override
	public int getType() {
		return _ticketSolution.getType();
	}

	@Override
	public int hashCode() {
		return _ticketSolution.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketSolution.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TicketSolutionWrapper((TicketSolution)_ticketSolution.clone());
	}

	/**
	* Returns the solution of this ticket solution.
	*
	* @return the solution of this ticket solution
	*/
	@Override
	public java.lang.String getSolution() {
		return _ticketSolution.getSolution();
	}

	/**
	* Returns the status by user name of this ticket solution.
	*
	* @return the status by user name of this ticket solution
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _ticketSolution.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this ticket solution.
	*
	* @return the status by user uuid of this ticket solution
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _ticketSolution.getStatusByUserUuid();
	}

	/**
	* Returns the status message of this ticket solution.
	*
	* @return the status message of this ticket solution
	*/
	@Override
	public java.lang.String getStatusMessage() {
		return _ticketSolution.getStatusMessage();
	}

	/**
	* Returns the summary of this ticket solution.
	*
	* @return the summary of this ticket solution
	*/
	@Override
	public java.lang.String getSummary() {
		return _ticketSolution.getSummary();
	}

	/**
	* Returns the user name of this ticket solution.
	*
	* @return the user name of this ticket solution
	*/
	@Override
	public java.lang.String getUserName() {
		return _ticketSolution.getUserName();
	}

	/**
	* Returns the user uuid of this ticket solution.
	*
	* @return the user uuid of this ticket solution
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _ticketSolution.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _ticketSolution.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ticketSolution.toXmlString();
	}

	/**
	* Returns the create date of this ticket solution.
	*
	* @return the create date of this ticket solution
	*/
	@Override
	public Date getCreateDate() {
		return _ticketSolution.getCreateDate();
	}

	/**
	* Returns the status date of this ticket solution.
	*
	* @return the status date of this ticket solution
	*/
	@Override
	public Date getStatusDate() {
		return _ticketSolution.getStatusDate();
	}

	/**
	* Returns the primary key of this ticket solution.
	*
	* @return the primary key of this ticket solution
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketSolution.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this ticket solution.
	*
	* @return the status by user ID of this ticket solution
	*/
	@Override
	public long getStatusByUserId() {
		return _ticketSolution.getStatusByUserId();
	}

	/**
	* Returns the ticket entry ID of this ticket solution.
	*
	* @return the ticket entry ID of this ticket solution
	*/
	@Override
	public long getTicketEntryId() {
		return _ticketSolution.getTicketEntryId();
	}

	/**
	* Returns the ticket solution ID of this ticket solution.
	*
	* @return the ticket solution ID of this ticket solution
	*/
	@Override
	public long getTicketSolutionId() {
		return _ticketSolution.getTicketSolutionId();
	}

	/**
	* Returns the user ID of this ticket solution.
	*
	* @return the user ID of this ticket solution
	*/
	@Override
	public long getUserId() {
		return _ticketSolution.getUserId();
	}

	@Override
	public void persist() {
		_ticketSolution.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketSolution.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this ticket solution.
	*
	* @param createDate the create date of this ticket solution
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_ticketSolution.setCreateDate(createDate);
	}

	/**
	* Sets whether this ticket solution is customer specific.
	*
	* @param customerSpecific the customer specific of this ticket solution
	*/
	@Override
	public void setCustomerSpecific(boolean customerSpecific) {
		_ticketSolution.setCustomerSpecific(customerSpecific);
	}

	/**
	* Sets whether this ticket solution is environment specific.
	*
	* @param environmentSpecific the environment specific of this ticket solution
	*/
	@Override
	public void setEnvironmentSpecific(boolean environmentSpecific) {
		_ticketSolution.setEnvironmentSpecific(environmentSpecific);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketSolution.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketSolution.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketSolution.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the issue type of this ticket solution.
	*
	* @param issueType the issue type of this ticket solution
	*/
	@Override
	public void setIssueType(int issueType) {
		_ticketSolution.setIssueType(issueType);
	}

	@Override
	public void setNew(boolean n) {
		_ticketSolution.setNew(n);
	}

	/**
	* Sets the primary key of this ticket solution.
	*
	* @param primaryKey the primary key of this ticket solution
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketSolution.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketSolution.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this ticket solution is review for kb.
	*
	* @param reviewForKB the review for kb of this ticket solution
	*/
	@Override
	public void setReviewForKB(boolean reviewForKB) {
		_ticketSolution.setReviewForKB(reviewForKB);
	}

	/**
	* Sets the solution of this ticket solution.
	*
	* @param solution the solution of this ticket solution
	*/
	@Override
	public void setSolution(java.lang.String solution) {
		_ticketSolution.setSolution(solution);
	}

	/**
	* Sets the status of this ticket solution.
	*
	* @param status the status of this ticket solution
	*/
	@Override
	public void setStatus(int status) {
		_ticketSolution.setStatus(status);
	}

	/**
	* Sets the status by user ID of this ticket solution.
	*
	* @param statusByUserId the status by user ID of this ticket solution
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_ticketSolution.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this ticket solution.
	*
	* @param statusByUserName the status by user name of this ticket solution
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_ticketSolution.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this ticket solution.
	*
	* @param statusByUserUuid the status by user uuid of this ticket solution
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_ticketSolution.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this ticket solution.
	*
	* @param statusDate the status date of this ticket solution
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_ticketSolution.setStatusDate(statusDate);
	}

	/**
	* Sets the status message of this ticket solution.
	*
	* @param statusMessage the status message of this ticket solution
	*/
	@Override
	public void setStatusMessage(java.lang.String statusMessage) {
		_ticketSolution.setStatusMessage(statusMessage);
	}

	/**
	* Sets the status reason of this ticket solution.
	*
	* @param statusReason the status reason of this ticket solution
	*/
	@Override
	public void setStatusReason(int statusReason) {
		_ticketSolution.setStatusReason(statusReason);
	}

	/**
	* Sets the summary of this ticket solution.
	*
	* @param summary the summary of this ticket solution
	*/
	@Override
	public void setSummary(java.lang.String summary) {
		_ticketSolution.setSummary(summary);
	}

	/**
	* Sets the ticket entry ID of this ticket solution.
	*
	* @param ticketEntryId the ticket entry ID of this ticket solution
	*/
	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketSolution.setTicketEntryId(ticketEntryId);
	}

	/**
	* Sets the ticket solution ID of this ticket solution.
	*
	* @param ticketSolutionId the ticket solution ID of this ticket solution
	*/
	@Override
	public void setTicketSolutionId(long ticketSolutionId) {
		_ticketSolution.setTicketSolutionId(ticketSolutionId);
	}

	/**
	* Sets the type of this ticket solution.
	*
	* @param type the type of this ticket solution
	*/
	@Override
	public void setType(int type) {
		_ticketSolution.setType(type);
	}

	/**
	* Sets whether this ticket solution is use customer summary.
	*
	* @param useCustomerSummary the use customer summary of this ticket solution
	*/
	@Override
	public void setUseCustomerSummary(boolean useCustomerSummary) {
		_ticketSolution.setUseCustomerSummary(useCustomerSummary);
	}

	/**
	* Sets the user ID of this ticket solution.
	*
	* @param userId the user ID of this ticket solution
	*/
	@Override
	public void setUserId(long userId) {
		_ticketSolution.setUserId(userId);
	}

	/**
	* Sets the user name of this ticket solution.
	*
	* @param userName the user name of this ticket solution
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_ticketSolution.setUserName(userName);
	}

	/**
	* Sets the user uuid of this ticket solution.
	*
	* @param userUuid the user uuid of this ticket solution
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_ticketSolution.setUserUuid(userUuid);
	}

	/**
	* Sets whether this ticket solution is version specific.
	*
	* @param versionSpecific the version specific of this ticket solution
	*/
	@Override
	public void setVersionSpecific(boolean versionSpecific) {
		_ticketSolution.setVersionSpecific(versionSpecific);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketSolutionWrapper)) {
			return false;
		}

		TicketSolutionWrapper ticketSolutionWrapper = (TicketSolutionWrapper)obj;

		if (Objects.equals(_ticketSolution,
					ticketSolutionWrapper._ticketSolution)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketSolution getWrappedModel() {
		return _ticketSolution;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketSolution.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketSolution.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketSolution.resetOriginalValues();
	}

	private final TicketSolution _ticketSolution;
}