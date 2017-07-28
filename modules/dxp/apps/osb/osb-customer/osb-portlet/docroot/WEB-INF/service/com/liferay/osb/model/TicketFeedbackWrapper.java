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
 * This class is a wrapper for {@link TicketFeedback}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedback
 * @generated
 */
@ProviderType
public class TicketFeedbackWrapper implements TicketFeedback,
	ModelWrapper<TicketFeedback> {
	public TicketFeedbackWrapper(TicketFeedback ticketFeedback) {
		_ticketFeedback = ticketFeedback;
	}

	@Override
	public Class<?> getModelClass() {
		return TicketFeedback.class;
	}

	@Override
	public String getModelClassName() {
		return TicketFeedback.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketFeedbackId", getTicketFeedbackId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("subject", getSubject());
		attributes.put("satisfied", getSatisfied());
		attributes.put("answer1", getAnswer1());
		attributes.put("answer2", getAnswer2());
		attributes.put("answer3", getAnswer3());
		attributes.put("rating1", getRating1());
		attributes.put("rating2", getRating2());
		attributes.put("rating3", getRating3());
		attributes.put("rating4", getRating4());
		attributes.put("comments", getComments());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketFeedbackId = (Long)attributes.get("ticketFeedbackId");

		if (ticketFeedbackId != null) {
			setTicketFeedbackId(ticketFeedbackId);
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

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Integer subject = (Integer)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		Integer satisfied = (Integer)attributes.get("satisfied");

		if (satisfied != null) {
			setSatisfied(satisfied);
		}

		Integer answer1 = (Integer)attributes.get("answer1");

		if (answer1 != null) {
			setAnswer1(answer1);
		}

		Integer answer2 = (Integer)attributes.get("answer2");

		if (answer2 != null) {
			setAnswer2(answer2);
		}

		Integer answer3 = (Integer)attributes.get("answer3");

		if (answer3 != null) {
			setAnswer3(answer3);
		}

		Integer rating1 = (Integer)attributes.get("rating1");

		if (rating1 != null) {
			setRating1(rating1);
		}

		Integer rating2 = (Integer)attributes.get("rating2");

		if (rating2 != null) {
			setRating2(rating2);
		}

		Integer rating3 = (Integer)attributes.get("rating3");

		if (rating3 != null) {
			setRating3(rating3);
		}

		Integer rating4 = (Integer)attributes.get("rating4");

		if (rating4 != null) {
			setRating4(rating4);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public TicketEntry getTicketEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketFeedback.getTicketEntry();
	}

	@Override
	public TicketFeedback toEscapedModel() {
		return new TicketFeedbackWrapper(_ticketFeedback.toEscapedModel());
	}

	@Override
	public TicketFeedback toUnescapedModel() {
		return new TicketFeedbackWrapper(_ticketFeedback.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _ticketFeedback.isCachedModel();
	}

	@Override
	public boolean isClosed() {
		return _ticketFeedback.isClosed();
	}

	@Override
	public boolean isEscapedModel() {
		return _ticketFeedback.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ticketFeedback.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ticketFeedback.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TicketFeedback> toCacheModel() {
		return _ticketFeedback.toCacheModel();
	}

	@Override
	public double getAverageRating() {
		return _ticketFeedback.getAverageRating();
	}

	@Override
	public int compareTo(TicketFeedback ticketFeedback) {
		return _ticketFeedback.compareTo(ticketFeedback);
	}

	/**
	* Returns the answer1 of this ticket feedback.
	*
	* @return the answer1 of this ticket feedback
	*/
	@Override
	public int getAnswer1() {
		return _ticketFeedback.getAnswer1();
	}

	/**
	* Returns the answer2 of this ticket feedback.
	*
	* @return the answer2 of this ticket feedback
	*/
	@Override
	public int getAnswer2() {
		return _ticketFeedback.getAnswer2();
	}

	/**
	* Returns the answer3 of this ticket feedback.
	*
	* @return the answer3 of this ticket feedback
	*/
	@Override
	public int getAnswer3() {
		return _ticketFeedback.getAnswer3();
	}

	/**
	* Returns the rating1 of this ticket feedback.
	*
	* @return the rating1 of this ticket feedback
	*/
	@Override
	public int getRating1() {
		return _ticketFeedback.getRating1();
	}

	/**
	* Returns the rating2 of this ticket feedback.
	*
	* @return the rating2 of this ticket feedback
	*/
	@Override
	public int getRating2() {
		return _ticketFeedback.getRating2();
	}

	/**
	* Returns the rating3 of this ticket feedback.
	*
	* @return the rating3 of this ticket feedback
	*/
	@Override
	public int getRating3() {
		return _ticketFeedback.getRating3();
	}

	/**
	* Returns the rating4 of this ticket feedback.
	*
	* @return the rating4 of this ticket feedback
	*/
	@Override
	public int getRating4() {
		return _ticketFeedback.getRating4();
	}

	/**
	* Returns the satisfied of this ticket feedback.
	*
	* @return the satisfied of this ticket feedback
	*/
	@Override
	public int getSatisfied() {
		return _ticketFeedback.getSatisfied();
	}

	/**
	* Returns the status of this ticket feedback.
	*
	* @return the status of this ticket feedback
	*/
	@Override
	public int getStatus() {
		return _ticketFeedback.getStatus();
	}

	/**
	* Returns the subject of this ticket feedback.
	*
	* @return the subject of this ticket feedback
	*/
	@Override
	public int getSubject() {
		return _ticketFeedback.getSubject();
	}

	@Override
	public int hashCode() {
		return _ticketFeedback.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketFeedback.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TicketFeedbackWrapper((TicketFeedback)_ticketFeedback.clone());
	}

	@Override
	public java.lang.String getAnswer1Label() {
		return _ticketFeedback.getAnswer1Label();
	}

	@Override
	public java.lang.String getAnswer2Label() {
		return _ticketFeedback.getAnswer2Label();
	}

	@Override
	public java.lang.String getAnswer3Label() {
		return _ticketFeedback.getAnswer3Label();
	}

	@Override
	public java.lang.String getAverageRatingDisplay() {
		return _ticketFeedback.getAverageRatingDisplay();
	}

	/**
	* Returns the comments of this ticket feedback.
	*
	* @return the comments of this ticket feedback
	*/
	@Override
	public java.lang.String getComments() {
		return _ticketFeedback.getComments();
	}

	@Override
	public java.lang.String getRating1Label() {
		return _ticketFeedback.getRating1Label();
	}

	@Override
	public java.lang.String getRating2Label() {
		return _ticketFeedback.getRating2Label();
	}

	@Override
	public java.lang.String getRating3Label() {
		return _ticketFeedback.getRating3Label();
	}

	@Override
	public java.lang.String getRating4Label() {
		return _ticketFeedback.getRating4Label();
	}

	@Override
	public java.lang.String getSatisfiedLabel() {
		return _ticketFeedback.getSatisfiedLabel();
	}

	/**
	* Returns the user name of this ticket feedback.
	*
	* @return the user name of this ticket feedback
	*/
	@Override
	public java.lang.String getUserName() {
		return _ticketFeedback.getUserName();
	}

	/**
	* Returns the user uuid of this ticket feedback.
	*
	* @return the user uuid of this ticket feedback
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _ticketFeedback.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _ticketFeedback.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ticketFeedback.toXmlString();
	}

	/**
	* Returns the create date of this ticket feedback.
	*
	* @return the create date of this ticket feedback
	*/
	@Override
	public Date getCreateDate() {
		return _ticketFeedback.getCreateDate();
	}

	/**
	* Returns the modified date of this ticket feedback.
	*
	* @return the modified date of this ticket feedback
	*/
	@Override
	public Date getModifiedDate() {
		return _ticketFeedback.getModifiedDate();
	}

	/**
	* Returns the account entry ID of this ticket feedback.
	*
	* @return the account entry ID of this ticket feedback
	*/
	@Override
	public long getAccountEntryId() {
		return _ticketFeedback.getAccountEntryId();
	}

	/**
	* Returns the primary key of this ticket feedback.
	*
	* @return the primary key of this ticket feedback
	*/
	@Override
	public long getPrimaryKey() {
		return _ticketFeedback.getPrimaryKey();
	}

	/**
	* Returns the ticket entry ID of this ticket feedback.
	*
	* @return the ticket entry ID of this ticket feedback
	*/
	@Override
	public long getTicketEntryId() {
		return _ticketFeedback.getTicketEntryId();
	}

	/**
	* Returns the ticket feedback ID of this ticket feedback.
	*
	* @return the ticket feedback ID of this ticket feedback
	*/
	@Override
	public long getTicketFeedbackId() {
		return _ticketFeedback.getTicketFeedbackId();
	}

	/**
	* Returns the user ID of this ticket feedback.
	*
	* @return the user ID of this ticket feedback
	*/
	@Override
	public long getUserId() {
		return _ticketFeedback.getUserId();
	}

	@Override
	public void persist() {
		_ticketFeedback.persist();
	}

	/**
	* Sets the account entry ID of this ticket feedback.
	*
	* @param accountEntryId the account entry ID of this ticket feedback
	*/
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_ticketFeedback.setAccountEntryId(accountEntryId);
	}

	/**
	* Sets the answer1 of this ticket feedback.
	*
	* @param answer1 the answer1 of this ticket feedback
	*/
	@Override
	public void setAnswer1(int answer1) {
		_ticketFeedback.setAnswer1(answer1);
	}

	/**
	* Sets the answer2 of this ticket feedback.
	*
	* @param answer2 the answer2 of this ticket feedback
	*/
	@Override
	public void setAnswer2(int answer2) {
		_ticketFeedback.setAnswer2(answer2);
	}

	/**
	* Sets the answer3 of this ticket feedback.
	*
	* @param answer3 the answer3 of this ticket feedback
	*/
	@Override
	public void setAnswer3(int answer3) {
		_ticketFeedback.setAnswer3(answer3);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ticketFeedback.setCachedModel(cachedModel);
	}

	/**
	* Sets the comments of this ticket feedback.
	*
	* @param comments the comments of this ticket feedback
	*/
	@Override
	public void setComments(java.lang.String comments) {
		_ticketFeedback.setComments(comments);
	}

	/**
	* Sets the create date of this ticket feedback.
	*
	* @param createDate the create date of this ticket feedback
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_ticketFeedback.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ticketFeedback.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ticketFeedback.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ticketFeedback.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this ticket feedback.
	*
	* @param modifiedDate the modified date of this ticket feedback
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ticketFeedback.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ticketFeedback.setNew(n);
	}

	/**
	* Sets the primary key of this ticket feedback.
	*
	* @param primaryKey the primary key of this ticket feedback
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ticketFeedback.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ticketFeedback.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rating1 of this ticket feedback.
	*
	* @param rating1 the rating1 of this ticket feedback
	*/
	@Override
	public void setRating1(int rating1) {
		_ticketFeedback.setRating1(rating1);
	}

	/**
	* Sets the rating2 of this ticket feedback.
	*
	* @param rating2 the rating2 of this ticket feedback
	*/
	@Override
	public void setRating2(int rating2) {
		_ticketFeedback.setRating2(rating2);
	}

	/**
	* Sets the rating3 of this ticket feedback.
	*
	* @param rating3 the rating3 of this ticket feedback
	*/
	@Override
	public void setRating3(int rating3) {
		_ticketFeedback.setRating3(rating3);
	}

	/**
	* Sets the rating4 of this ticket feedback.
	*
	* @param rating4 the rating4 of this ticket feedback
	*/
	@Override
	public void setRating4(int rating4) {
		_ticketFeedback.setRating4(rating4);
	}

	/**
	* Sets the satisfied of this ticket feedback.
	*
	* @param satisfied the satisfied of this ticket feedback
	*/
	@Override
	public void setSatisfied(int satisfied) {
		_ticketFeedback.setSatisfied(satisfied);
	}

	/**
	* Sets the status of this ticket feedback.
	*
	* @param status the status of this ticket feedback
	*/
	@Override
	public void setStatus(int status) {
		_ticketFeedback.setStatus(status);
	}

	/**
	* Sets the subject of this ticket feedback.
	*
	* @param subject the subject of this ticket feedback
	*/
	@Override
	public void setSubject(int subject) {
		_ticketFeedback.setSubject(subject);
	}

	/**
	* Sets the ticket entry ID of this ticket feedback.
	*
	* @param ticketEntryId the ticket entry ID of this ticket feedback
	*/
	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketFeedback.setTicketEntryId(ticketEntryId);
	}

	/**
	* Sets the ticket feedback ID of this ticket feedback.
	*
	* @param ticketFeedbackId the ticket feedback ID of this ticket feedback
	*/
	@Override
	public void setTicketFeedbackId(long ticketFeedbackId) {
		_ticketFeedback.setTicketFeedbackId(ticketFeedbackId);
	}

	/**
	* Sets the user ID of this ticket feedback.
	*
	* @param userId the user ID of this ticket feedback
	*/
	@Override
	public void setUserId(long userId) {
		_ticketFeedback.setUserId(userId);
	}

	/**
	* Sets the user name of this ticket feedback.
	*
	* @param userName the user name of this ticket feedback
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_ticketFeedback.setUserName(userName);
	}

	/**
	* Sets the user uuid of this ticket feedback.
	*
	* @param userUuid the user uuid of this ticket feedback
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_ticketFeedback.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketFeedbackWrapper)) {
			return false;
		}

		TicketFeedbackWrapper ticketFeedbackWrapper = (TicketFeedbackWrapper)obj;

		if (Objects.equals(_ticketFeedback,
					ticketFeedbackWrapper._ticketFeedback)) {
			return true;
		}

		return false;
	}

	@Override
	public TicketFeedback getWrappedModel() {
		return _ticketFeedback;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ticketFeedback.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ticketFeedback.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ticketFeedback.resetOriginalValues();
	}

	private final TicketFeedback _ticketFeedback;
}