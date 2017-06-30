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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TrainingCustomer}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingCustomer
 * @generated
 */
public class TrainingCustomerWrapper implements TrainingCustomer,
	ModelWrapper<TrainingCustomer> {
	public TrainingCustomerWrapper(TrainingCustomer trainingCustomer) {
		_trainingCustomer = trainingCustomer;
	}

	public Class<?> getModelClass() {
		return TrainingCustomer.class;
	}

	public String getModelClassName() {
		return TrainingCustomer.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingCustomerId", getTrainingCustomerId());
		attributes.put("userId", getUserId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("userProfileHistoryId", getUserProfileHistoryId());
		attributes.put("comments", getComments());
		attributes.put("status", getStatus());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingCustomerId = (Long)attributes.get("trainingCustomerId");

		if (trainingCustomerId != null) {
			setTrainingCustomerId(trainingCustomerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long userProfileHistoryId = (Long)attributes.get("userProfileHistoryId");

		if (userProfileHistoryId != null) {
			setUserProfileHistoryId(userProfileHistoryId);
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

	/**
	* Returns the primary key of this training customer.
	*
	* @return the primary key of this training customer
	*/
	public long getPrimaryKey() {
		return _trainingCustomer.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training customer.
	*
	* @param primaryKey the primary key of this training customer
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingCustomer.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training customer ID of this training customer.
	*
	* @return the training customer ID of this training customer
	*/
	public long getTrainingCustomerId() {
		return _trainingCustomer.getTrainingCustomerId();
	}

	/**
	* Sets the training customer ID of this training customer.
	*
	* @param trainingCustomerId the training customer ID of this training customer
	*/
	public void setTrainingCustomerId(long trainingCustomerId) {
		_trainingCustomer.setTrainingCustomerId(trainingCustomerId);
	}

	/**
	* Returns the user ID of this training customer.
	*
	* @return the user ID of this training customer
	*/
	public long getUserId() {
		return _trainingCustomer.getUserId();
	}

	/**
	* Sets the user ID of this training customer.
	*
	* @param userId the user ID of this training customer
	*/
	public void setUserId(long userId) {
		_trainingCustomer.setUserId(userId);
	}

	/**
	* Returns the user uuid of this training customer.
	*
	* @return the user uuid of this training customer
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomer.getUserUuid();
	}

	/**
	* Sets the user uuid of this training customer.
	*
	* @param userUuid the user uuid of this training customer
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_trainingCustomer.setUserUuid(userUuid);
	}

	/**
	* Returns the fully qualified class name of this training customer.
	*
	* @return the fully qualified class name of this training customer
	*/
	public java.lang.String getClassName() {
		return _trainingCustomer.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_trainingCustomer.setClassName(className);
	}

	/**
	* Returns the class name ID of this training customer.
	*
	* @return the class name ID of this training customer
	*/
	public long getClassNameId() {
		return _trainingCustomer.getClassNameId();
	}

	/**
	* Sets the class name ID of this training customer.
	*
	* @param classNameId the class name ID of this training customer
	*/
	public void setClassNameId(long classNameId) {
		_trainingCustomer.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this training customer.
	*
	* @return the class p k of this training customer
	*/
	public long getClassPK() {
		return _trainingCustomer.getClassPK();
	}

	/**
	* Sets the class p k of this training customer.
	*
	* @param classPK the class p k of this training customer
	*/
	public void setClassPK(long classPK) {
		_trainingCustomer.setClassPK(classPK);
	}

	/**
	* Returns the user profile history ID of this training customer.
	*
	* @return the user profile history ID of this training customer
	*/
	public long getUserProfileHistoryId() {
		return _trainingCustomer.getUserProfileHistoryId();
	}

	/**
	* Sets the user profile history ID of this training customer.
	*
	* @param userProfileHistoryId the user profile history ID of this training customer
	*/
	public void setUserProfileHistoryId(long userProfileHistoryId) {
		_trainingCustomer.setUserProfileHistoryId(userProfileHistoryId);
	}

	/**
	* Returns the comments of this training customer.
	*
	* @return the comments of this training customer
	*/
	public java.lang.String getComments() {
		return _trainingCustomer.getComments();
	}

	/**
	* Sets the comments of this training customer.
	*
	* @param comments the comments of this training customer
	*/
	public void setComments(java.lang.String comments) {
		_trainingCustomer.setComments(comments);
	}

	/**
	* Returns the status of this training customer.
	*
	* @return the status of this training customer
	*/
	public int getStatus() {
		return _trainingCustomer.getStatus();
	}

	/**
	* Sets the status of this training customer.
	*
	* @param status the status of this training customer
	*/
	public void setStatus(int status) {
		_trainingCustomer.setStatus(status);
	}

	public boolean isNew() {
		return _trainingCustomer.isNew();
	}

	public void setNew(boolean n) {
		_trainingCustomer.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingCustomer.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingCustomer.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingCustomer.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingCustomer.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingCustomer.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingCustomer.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingCustomer.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingCustomerWrapper((TrainingCustomer)_trainingCustomer.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TrainingCustomer trainingCustomer) {
		return _trainingCustomer.compareTo(trainingCustomer);
	}

	@Override
	public int hashCode() {
		return _trainingCustomer.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingCustomer> toCacheModel() {
		return _trainingCustomer.toCacheModel();
	}

	public com.liferay.osb.model.TrainingCustomer toEscapedModel() {
		return new TrainingCustomerWrapper(_trainingCustomer.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingCustomer toUnescapedModel() {
		return new TrainingCustomerWrapper(_trainingCustomer.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingCustomer.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingCustomer.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingCustomer.persist();
	}

	public java.lang.String getEmailAddress() {
		return _trainingCustomer.getEmailAddress();
	}

	public java.lang.String getFirstName() {
		return _trainingCustomer.getFirstName();
	}

	public java.lang.String getLastName() {
		return _trainingCustomer.getLastName();
	}

	public java.lang.String getTrainingCertificateKey()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomer.getTrainingCertificateKey();
	}

	public java.lang.String getTrainingSurveyResultHTML()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingCustomer.getTrainingSurveyResultHTML();
	}

	public java.lang.String getType() {
		return _trainingCustomer.getType();
	}

	public com.liferay.portal.model.User getUser() {
		return _trainingCustomer.getUser();
	}

	public boolean isTrainingUser() {
		return _trainingCustomer.isTrainingUser();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingCustomerWrapper)) {
			return false;
		}

		TrainingCustomerWrapper trainingCustomerWrapper = (TrainingCustomerWrapper)obj;

		if (Validator.equals(_trainingCustomer,
					trainingCustomerWrapper._trainingCustomer)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingCustomer getWrappedTrainingCustomer() {
		return _trainingCustomer;
	}

	public TrainingCustomer getWrappedModel() {
		return _trainingCustomer;
	}

	public void resetOriginalValues() {
		_trainingCustomer.resetOriginalValues();
	}

	private TrainingCustomer _trainingCustomer;
}