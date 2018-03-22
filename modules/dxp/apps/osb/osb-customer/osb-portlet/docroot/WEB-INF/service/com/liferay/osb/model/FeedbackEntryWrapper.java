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
 * This class is a wrapper for {@link FeedbackEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedbackEntry
 * @generated
 */
@ProviderType
public class FeedbackEntryWrapper implements FeedbackEntry,
	ModelWrapper<FeedbackEntry> {
	public FeedbackEntryWrapper(FeedbackEntry feedbackEntry) {
		_feedbackEntry = feedbackEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return FeedbackEntry.class;
	}

	@Override
	public String getModelClassName() {
		return FeedbackEntry.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public FeedbackEntry toEscapedModel() {
		return new FeedbackEntryWrapper(_feedbackEntry.toEscapedModel());
	}

	@Override
	public FeedbackEntry toUnescapedModel() {
		return new FeedbackEntryWrapper(_feedbackEntry.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _feedbackEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _feedbackEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _feedbackEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _feedbackEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<FeedbackEntry> toCacheModel() {
		return _feedbackEntry.toCacheModel();
	}

	@Override
	public int compareTo(FeedbackEntry feedbackEntry) {
		return _feedbackEntry.compareTo(feedbackEntry);
	}

	/**
	* Returns the satisfied of this feedback entry.
	*
	* @return the satisfied of this feedback entry
	*/
	@Override
	public int getSatisfied() {
		return _feedbackEntry.getSatisfied();
	}

	@Override
	public int hashCode() {
		return _feedbackEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _feedbackEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new FeedbackEntryWrapper((FeedbackEntry)_feedbackEntry.clone());
	}

	/**
	* Returns the fully qualified class name of this feedback entry.
	*
	* @return the fully qualified class name of this feedback entry
	*/
	@Override
	public java.lang.String getClassName() {
		return _feedbackEntry.getClassName();
	}

	/**
	* Returns the comments of this feedback entry.
	*
	* @return the comments of this feedback entry
	*/
	@Override
	public java.lang.String getComments() {
		return _feedbackEntry.getComments();
	}

	/**
	* Returns the page url of this feedback entry.
	*
	* @return the page url of this feedback entry
	*/
	@Override
	public java.lang.String getPageURL() {
		return _feedbackEntry.getPageURL();
	}

	/**
	* Returns the user name of this feedback entry.
	*
	* @return the user name of this feedback entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _feedbackEntry.getUserName();
	}

	/**
	* Returns the user uuid of this feedback entry.
	*
	* @return the user uuid of this feedback entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _feedbackEntry.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _feedbackEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _feedbackEntry.toXmlString();
	}

	/**
	* Returns the create date of this feedback entry.
	*
	* @return the create date of this feedback entry
	*/
	@Override
	public Date getCreateDate() {
		return _feedbackEntry.getCreateDate();
	}

	/**
	* Returns the class name ID of this feedback entry.
	*
	* @return the class name ID of this feedback entry
	*/
	@Override
	public long getClassNameId() {
		return _feedbackEntry.getClassNameId();
	}

	/**
	* Returns the class pk of this feedback entry.
	*
	* @return the class pk of this feedback entry
	*/
	@Override
	public long getClassPK() {
		return _feedbackEntry.getClassPK();
	}

	/**
	* Returns the feedback entry ID of this feedback entry.
	*
	* @return the feedback entry ID of this feedback entry
	*/
	@Override
	public long getFeedbackEntryId() {
		return _feedbackEntry.getFeedbackEntryId();
	}

	/**
	* Returns the primary key of this feedback entry.
	*
	* @return the primary key of this feedback entry
	*/
	@Override
	public long getPrimaryKey() {
		return _feedbackEntry.getPrimaryKey();
	}

	/**
	* Returns the user ID of this feedback entry.
	*
	* @return the user ID of this feedback entry
	*/
	@Override
	public long getUserId() {
		return _feedbackEntry.getUserId();
	}

	@Override
	public void persist() {
		_feedbackEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_feedbackEntry.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_feedbackEntry.setClassName(className);
	}

	/**
	* Sets the class name ID of this feedback entry.
	*
	* @param classNameId the class name ID of this feedback entry
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_feedbackEntry.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this feedback entry.
	*
	* @param classPK the class pk of this feedback entry
	*/
	@Override
	public void setClassPK(long classPK) {
		_feedbackEntry.setClassPK(classPK);
	}

	/**
	* Sets the comments of this feedback entry.
	*
	* @param comments the comments of this feedback entry
	*/
	@Override
	public void setComments(java.lang.String comments) {
		_feedbackEntry.setComments(comments);
	}

	/**
	* Sets the create date of this feedback entry.
	*
	* @param createDate the create date of this feedback entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_feedbackEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_feedbackEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_feedbackEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_feedbackEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the feedback entry ID of this feedback entry.
	*
	* @param feedbackEntryId the feedback entry ID of this feedback entry
	*/
	@Override
	public void setFeedbackEntryId(long feedbackEntryId) {
		_feedbackEntry.setFeedbackEntryId(feedbackEntryId);
	}

	@Override
	public void setNew(boolean n) {
		_feedbackEntry.setNew(n);
	}

	/**
	* Sets the page url of this feedback entry.
	*
	* @param pageURL the page url of this feedback entry
	*/
	@Override
	public void setPageURL(java.lang.String pageURL) {
		_feedbackEntry.setPageURL(pageURL);
	}

	/**
	* Sets the primary key of this feedback entry.
	*
	* @param primaryKey the primary key of this feedback entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_feedbackEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_feedbackEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the satisfied of this feedback entry.
	*
	* @param satisfied the satisfied of this feedback entry
	*/
	@Override
	public void setSatisfied(int satisfied) {
		_feedbackEntry.setSatisfied(satisfied);
	}

	/**
	* Sets the user ID of this feedback entry.
	*
	* @param userId the user ID of this feedback entry
	*/
	@Override
	public void setUserId(long userId) {
		_feedbackEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this feedback entry.
	*
	* @param userName the user name of this feedback entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_feedbackEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this feedback entry.
	*
	* @param userUuid the user uuid of this feedback entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_feedbackEntry.setUserUuid(userUuid);
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

		if (Objects.equals(_feedbackEntry, feedbackEntryWrapper._feedbackEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public FeedbackEntry getWrappedModel() {
		return _feedbackEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _feedbackEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _feedbackEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_feedbackEntry.resetOriginalValues();
	}

	private final FeedbackEntry _feedbackEntry;
}