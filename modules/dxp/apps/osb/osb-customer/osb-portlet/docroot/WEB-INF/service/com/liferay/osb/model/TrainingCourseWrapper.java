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
 * This class is a wrapper for {@link TrainingCourse}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingCourse
 * @generated
 */
public class TrainingCourseWrapper implements TrainingCourse,
	ModelWrapper<TrainingCourse> {
	public TrainingCourseWrapper(TrainingCourse trainingCourse) {
		_trainingCourse = trainingCourse;
	}

	public Class<?> getModelClass() {
		return TrainingCourse.class;
	}

	public String getModelClassName() {
		return TrainingCourse.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingCourseId", getTrainingCourseId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("creditAmount", getCreditAmount());
		attributes.put("courseURL", getCourseURL());
		attributes.put("archived", getArchived());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingCourseId = (Long)attributes.get("trainingCourseId");

		if (trainingCourseId != null) {
			setTrainingCourseId(trainingCourseId);
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

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer creditAmount = (Integer)attributes.get("creditAmount");

		if (creditAmount != null) {
			setCreditAmount(creditAmount);
		}

		String courseURL = (String)attributes.get("courseURL");

		if (courseURL != null) {
			setCourseURL(courseURL);
		}

		Boolean archived = (Boolean)attributes.get("archived");

		if (archived != null) {
			setArchived(archived);
		}
	}

	/**
	* Returns the primary key of this training course.
	*
	* @return the primary key of this training course
	*/
	public long getPrimaryKey() {
		return _trainingCourse.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training course.
	*
	* @param primaryKey the primary key of this training course
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingCourse.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training course ID of this training course.
	*
	* @return the training course ID of this training course
	*/
	public long getTrainingCourseId() {
		return _trainingCourse.getTrainingCourseId();
	}

	/**
	* Sets the training course ID of this training course.
	*
	* @param trainingCourseId the training course ID of this training course
	*/
	public void setTrainingCourseId(long trainingCourseId) {
		_trainingCourse.setTrainingCourseId(trainingCourseId);
	}

	/**
	* Returns the user ID of this training course.
	*
	* @return the user ID of this training course
	*/
	public long getUserId() {
		return _trainingCourse.getUserId();
	}

	/**
	* Sets the user ID of this training course.
	*
	* @param userId the user ID of this training course
	*/
	public void setUserId(long userId) {
		_trainingCourse.setUserId(userId);
	}

	/**
	* Returns the user uuid of this training course.
	*
	* @return the user uuid of this training course
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCourse.getUserUuid();
	}

	/**
	* Sets the user uuid of this training course.
	*
	* @param userUuid the user uuid of this training course
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_trainingCourse.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this training course.
	*
	* @return the user name of this training course
	*/
	public java.lang.String getUserName() {
		return _trainingCourse.getUserName();
	}

	/**
	* Sets the user name of this training course.
	*
	* @param userName the user name of this training course
	*/
	public void setUserName(java.lang.String userName) {
		_trainingCourse.setUserName(userName);
	}

	/**
	* Returns the create date of this training course.
	*
	* @return the create date of this training course
	*/
	public java.util.Date getCreateDate() {
		return _trainingCourse.getCreateDate();
	}

	/**
	* Sets the create date of this training course.
	*
	* @param createDate the create date of this training course
	*/
	public void setCreateDate(java.util.Date createDate) {
		_trainingCourse.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this training course.
	*
	* @return the modified date of this training course
	*/
	public java.util.Date getModifiedDate() {
		return _trainingCourse.getModifiedDate();
	}

	/**
	* Sets the modified date of this training course.
	*
	* @param modifiedDate the modified date of this training course
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_trainingCourse.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this training course.
	*
	* @return the name of this training course
	*/
	public java.lang.String getName() {
		return _trainingCourse.getName();
	}

	/**
	* Sets the name of this training course.
	*
	* @param name the name of this training course
	*/
	public void setName(java.lang.String name) {
		_trainingCourse.setName(name);
	}

	/**
	* Returns the description of this training course.
	*
	* @return the description of this training course
	*/
	public java.lang.String getDescription() {
		return _trainingCourse.getDescription();
	}

	/**
	* Sets the description of this training course.
	*
	* @param description the description of this training course
	*/
	public void setDescription(java.lang.String description) {
		_trainingCourse.setDescription(description);
	}

	/**
	* Returns the credit amount of this training course.
	*
	* @return the credit amount of this training course
	*/
	public int getCreditAmount() {
		return _trainingCourse.getCreditAmount();
	}

	/**
	* Sets the credit amount of this training course.
	*
	* @param creditAmount the credit amount of this training course
	*/
	public void setCreditAmount(int creditAmount) {
		_trainingCourse.setCreditAmount(creditAmount);
	}

	/**
	* Returns the course u r l of this training course.
	*
	* @return the course u r l of this training course
	*/
	public java.lang.String getCourseURL() {
		return _trainingCourse.getCourseURL();
	}

	/**
	* Sets the course u r l of this training course.
	*
	* @param courseURL the course u r l of this training course
	*/
	public void setCourseURL(java.lang.String courseURL) {
		_trainingCourse.setCourseURL(courseURL);
	}

	/**
	* Returns the archived of this training course.
	*
	* @return the archived of this training course
	*/
	public boolean getArchived() {
		return _trainingCourse.getArchived();
	}

	/**
	* Returns <code>true</code> if this training course is archived.
	*
	* @return <code>true</code> if this training course is archived; <code>false</code> otherwise
	*/
	public boolean isArchived() {
		return _trainingCourse.isArchived();
	}

	/**
	* Sets whether this training course is archived.
	*
	* @param archived the archived of this training course
	*/
	public void setArchived(boolean archived) {
		_trainingCourse.setArchived(archived);
	}

	public boolean isNew() {
		return _trainingCourse.isNew();
	}

	public void setNew(boolean n) {
		_trainingCourse.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingCourse.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingCourse.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingCourse.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingCourse.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingCourse.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingCourse.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingCourse.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingCourseWrapper((TrainingCourse)_trainingCourse.clone());
	}

	public int compareTo(com.liferay.osb.model.TrainingCourse trainingCourse) {
		return _trainingCourse.compareTo(trainingCourse);
	}

	@Override
	public int hashCode() {
		return _trainingCourse.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingCourse> toCacheModel() {
		return _trainingCourse.toCacheModel();
	}

	public com.liferay.osb.model.TrainingCourse toEscapedModel() {
		return new TrainingCourseWrapper(_trainingCourse.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingCourse toUnescapedModel() {
		return new TrainingCourseWrapper(_trainingCourse.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingCourse.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingCourse.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingCourse.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingCourseWrapper)) {
			return false;
		}

		TrainingCourseWrapper trainingCourseWrapper = (TrainingCourseWrapper)obj;

		if (Validator.equals(_trainingCourse,
					trainingCourseWrapper._trainingCourse)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingCourse getWrappedTrainingCourse() {
		return _trainingCourse;
	}

	public TrainingCourse getWrappedModel() {
		return _trainingCourse;
	}

	public void resetOriginalValues() {
		_trainingCourse.resetOriginalValues();
	}

	private TrainingCourse _trainingCourse;
}