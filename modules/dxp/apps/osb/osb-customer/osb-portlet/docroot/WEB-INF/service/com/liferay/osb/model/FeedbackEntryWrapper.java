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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FeedbackEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FeedbackEntry
 * @generated
 */
public class FeedbackEntryWrapper implements FeedbackEntry,
	ModelWrapper<FeedbackEntry> {
	public FeedbackEntryWrapper(FeedbackEntry feedbackEntry) {
		_feedbackEntry = feedbackEntry;
	}

	public Class<?> getModelClass() {
		return FeedbackEntry.class;
	}

	public String getModelClassName() {
		return FeedbackEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("feedbackEntryId", getFeedbackEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("satisfied", getSatisfied());
		attributes.put("comments", getComments());
		attributes.put("pageURL", getPageURL());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long feedbackEntryId = (Long)attributes.get("feedbackEntryId");

		if (feedbackEntryId != null) {
			setFeedbackEntryId(feedbackEntryId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer satisfied = (Integer)attributes.get("satisfied");

		if (satisfied != null) {
			setSatisfied(satisfied);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		String pageURL = (String)attributes.get("pageURL");

		if (pageURL != null) {
			setPageURL(pageURL);
		}
	}

	/**
	* Returns the primary key of this feedback entry.
	*
	* @return the primary key of this feedback entry
	*/
	public long getPrimaryKey() {
		return _feedbackEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this feedback entry.
	*
	* @param primaryKey the primary key of this feedback entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_feedbackEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the feedback entry ID of this feedback entry.
	*
	* @return the feedback entry ID of this feedback entry
	*/
	public long getFeedbackEntryId() {
		return _feedbackEntry.getFeedbackEntryId();
	}

	/**
	* Sets the feedback entry ID of this feedback entry.
	*
	* @param feedbackEntryId the feedback entry ID of this feedback entry
	*/
	public void setFeedbackEntryId(long feedbackEntryId) {
		_feedbackEntry.setFeedbackEntryId(feedbackEntryId);
	}

	/**
	* Returns the user ID of this feedback entry.
	*
	* @return the user ID of this feedback entry
	*/
	public long getUserId() {
		return _feedbackEntry.getUserId();
	}

	/**
	* Sets the user ID of this feedback entry.
	*
	* @param userId the user ID of this feedback entry
	*/
	public void setUserId(long userId) {
		_feedbackEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this feedback entry.
	*
	* @return the user uuid of this feedback entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedbackEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this feedback entry.
	*
	* @param userUuid the user uuid of this feedback entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_feedbackEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this feedback entry.
	*
	* @return the user name of this feedback entry
	*/
	public java.lang.String getUserName() {
		return _feedbackEntry.getUserName();
	}

	/**
	* Sets the user name of this feedback entry.
	*
	* @param userName the user name of this feedback entry
	*/
	public void setUserName(java.lang.String userName) {
		_feedbackEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this feedback entry.
	*
	* @return the create date of this feedback entry
	*/
	public java.util.Date getCreateDate() {
		return _feedbackEntry.getCreateDate();
	}

	/**
	* Sets the create date of this feedback entry.
	*
	* @param createDate the create date of this feedback entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_feedbackEntry.setCreateDate(createDate);
	}

	/**
	* Returns the fully qualified class name of this feedback entry.
	*
	* @return the fully qualified class name of this feedback entry
	*/
	public java.lang.String getClassName() {
		return _feedbackEntry.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_feedbackEntry.setClassName(className);
	}

	/**
	* Returns the class name ID of this feedback entry.
	*
	* @return the class name ID of this feedback entry
	*/
	public long getClassNameId() {
		return _feedbackEntry.getClassNameId();
	}

	/**
	* Sets the class name ID of this feedback entry.
	*
	* @param classNameId the class name ID of this feedback entry
	*/
	public void setClassNameId(long classNameId) {
		_feedbackEntry.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this feedback entry.
	*
	* @return the class p k of this feedback entry
	*/
	public long getClassPK() {
		return _feedbackEntry.getClassPK();
	}

	/**
	* Sets the class p k of this feedback entry.
	*
	* @param classPK the class p k of this feedback entry
	*/
	public void setClassPK(long classPK) {
		_feedbackEntry.setClassPK(classPK);
	}

	/**
	* Returns the satisfied of this feedback entry.
	*
	* @return the satisfied of this feedback entry
	*/
	public int getSatisfied() {
		return _feedbackEntry.getSatisfied();
	}

	/**
	* Sets the satisfied of this feedback entry.
	*
	* @param satisfied the satisfied of this feedback entry
	*/
	public void setSatisfied(int satisfied) {
		_feedbackEntry.setSatisfied(satisfied);
	}

	/**
	* Returns the comments of this feedback entry.
	*
	* @return the comments of this feedback entry
	*/
	public java.lang.String getComments() {
		return _feedbackEntry.getComments();
	}

	/**
	* Sets the comments of this feedback entry.
	*
	* @param comments the comments of this feedback entry
	*/
	public void setComments(java.lang.String comments) {
		_feedbackEntry.setComments(comments);
	}

	/**
	* Returns the page u r l of this feedback entry.
	*
	* @return the page u r l of this feedback entry
	*/
	public java.lang.String getPageURL() {
		return _feedbackEntry.getPageURL();
	}

	/**
	* Sets the page u r l of this feedback entry.
	*
	* @param pageURL the page u r l of this feedback entry
	*/
	public void setPageURL(java.lang.String pageURL) {
		_feedbackEntry.setPageURL(pageURL);
	}

	public boolean isNew() {
		return _feedbackEntry.isNew();
	}

	public void setNew(boolean n) {
		_feedbackEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _feedbackEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_feedbackEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _feedbackEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _feedbackEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_feedbackEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _feedbackEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_feedbackEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new FeedbackEntryWrapper((FeedbackEntry)_feedbackEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.FeedbackEntry feedbackEntry) {
		return _feedbackEntry.compareTo(feedbackEntry);
	}

	@Override
	public int hashCode() {
		return _feedbackEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.FeedbackEntry> toCacheModel() {
		return _feedbackEntry.toCacheModel();
	}

	public com.liferay.osb.model.FeedbackEntry toEscapedModel() {
		return new FeedbackEntryWrapper(_feedbackEntry.toEscapedModel());
	}

	public com.liferay.osb.model.FeedbackEntry toUnescapedModel() {
		return new FeedbackEntryWrapper(_feedbackEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _feedbackEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _feedbackEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_feedbackEntry.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FeedbackEntryWrapper)) {
			return false;
		}

		FeedbackEntryWrapper feedbackEntryWrapper = (FeedbackEntryWrapper)obj;

		if (Validator.equals(_feedbackEntry, feedbackEntryWrapper._feedbackEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public FeedbackEntry getWrappedFeedbackEntry() {
		return _feedbackEntry;
	}

	public FeedbackEntry getWrappedModel() {
		return _feedbackEntry;
	}

	public void resetOriginalValues() {
		_feedbackEntry.resetOriginalValues();
	}

	private FeedbackEntry _feedbackEntry;
}