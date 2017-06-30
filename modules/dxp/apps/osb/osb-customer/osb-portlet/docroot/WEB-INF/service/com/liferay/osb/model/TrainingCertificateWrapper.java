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
 * This class is a wrapper for {@link TrainingCertificate}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingCertificate
 * @generated
 */
public class TrainingCertificateWrapper implements TrainingCertificate,
	ModelWrapper<TrainingCertificate> {
	public TrainingCertificateWrapper(TrainingCertificate trainingCertificate) {
		_trainingCertificate = trainingCertificate;
	}

	public Class<?> getModelClass() {
		return TrainingCertificate.class;
	}

	public String getModelClassName() {
		return TrainingCertificate.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingCertificateId", getTrainingCertificateId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("trainingCustomerId", getTrainingCustomerId());
		attributes.put("userProfileHistoryId", getUserProfileHistoryId());
		attributes.put("key", getKey());
		attributes.put("certifiedDate", getCertifiedDate());
		attributes.put("comments", getComments());
		attributes.put("surveyStatus", getSurveyStatus());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingCertificateId = (Long)attributes.get(
				"trainingCertificateId");

		if (trainingCertificateId != null) {
			setTrainingCertificateId(trainingCertificateId);
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

		Long trainingCustomerId = (Long)attributes.get("trainingCustomerId");

		if (trainingCustomerId != null) {
			setTrainingCustomerId(trainingCustomerId);
		}

		Long userProfileHistoryId = (Long)attributes.get("userProfileHistoryId");

		if (userProfileHistoryId != null) {
			setUserProfileHistoryId(userProfileHistoryId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		Date certifiedDate = (Date)attributes.get("certifiedDate");

		if (certifiedDate != null) {
			setCertifiedDate(certifiedDate);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		Integer surveyStatus = (Integer)attributes.get("surveyStatus");

		if (surveyStatus != null) {
			setSurveyStatus(surveyStatus);
		}
	}

	/**
	* Returns the primary key of this training certificate.
	*
	* @return the primary key of this training certificate
	*/
	public long getPrimaryKey() {
		return _trainingCertificate.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training certificate.
	*
	* @param primaryKey the primary key of this training certificate
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingCertificate.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training certificate ID of this training certificate.
	*
	* @return the training certificate ID of this training certificate
	*/
	public long getTrainingCertificateId() {
		return _trainingCertificate.getTrainingCertificateId();
	}

	/**
	* Sets the training certificate ID of this training certificate.
	*
	* @param trainingCertificateId the training certificate ID of this training certificate
	*/
	public void setTrainingCertificateId(long trainingCertificateId) {
		_trainingCertificate.setTrainingCertificateId(trainingCertificateId);
	}

	/**
	* Returns the user ID of this training certificate.
	*
	* @return the user ID of this training certificate
	*/
	public long getUserId() {
		return _trainingCertificate.getUserId();
	}

	/**
	* Sets the user ID of this training certificate.
	*
	* @param userId the user ID of this training certificate
	*/
	public void setUserId(long userId) {
		_trainingCertificate.setUserId(userId);
	}

	/**
	* Returns the user uuid of this training certificate.
	*
	* @return the user uuid of this training certificate
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificate.getUserUuid();
	}

	/**
	* Sets the user uuid of this training certificate.
	*
	* @param userUuid the user uuid of this training certificate
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_trainingCertificate.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this training certificate.
	*
	* @return the user name of this training certificate
	*/
	public java.lang.String getUserName() {
		return _trainingCertificate.getUserName();
	}

	/**
	* Sets the user name of this training certificate.
	*
	* @param userName the user name of this training certificate
	*/
	public void setUserName(java.lang.String userName) {
		_trainingCertificate.setUserName(userName);
	}

	/**
	* Returns the create date of this training certificate.
	*
	* @return the create date of this training certificate
	*/
	public java.util.Date getCreateDate() {
		return _trainingCertificate.getCreateDate();
	}

	/**
	* Sets the create date of this training certificate.
	*
	* @param createDate the create date of this training certificate
	*/
	public void setCreateDate(java.util.Date createDate) {
		_trainingCertificate.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this training certificate.
	*
	* @return the modified date of this training certificate
	*/
	public java.util.Date getModifiedDate() {
		return _trainingCertificate.getModifiedDate();
	}

	/**
	* Sets the modified date of this training certificate.
	*
	* @param modifiedDate the modified date of this training certificate
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_trainingCertificate.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the training customer ID of this training certificate.
	*
	* @return the training customer ID of this training certificate
	*/
	public long getTrainingCustomerId() {
		return _trainingCertificate.getTrainingCustomerId();
	}

	/**
	* Sets the training customer ID of this training certificate.
	*
	* @param trainingCustomerId the training customer ID of this training certificate
	*/
	public void setTrainingCustomerId(long trainingCustomerId) {
		_trainingCertificate.setTrainingCustomerId(trainingCustomerId);
	}

	/**
	* Returns the user profile history ID of this training certificate.
	*
	* @return the user profile history ID of this training certificate
	*/
	public long getUserProfileHistoryId() {
		return _trainingCertificate.getUserProfileHistoryId();
	}

	/**
	* Sets the user profile history ID of this training certificate.
	*
	* @param userProfileHistoryId the user profile history ID of this training certificate
	*/
	public void setUserProfileHistoryId(long userProfileHistoryId) {
		_trainingCertificate.setUserProfileHistoryId(userProfileHistoryId);
	}

	/**
	* Returns the key of this training certificate.
	*
	* @return the key of this training certificate
	*/
	public java.lang.String getKey() {
		return _trainingCertificate.getKey();
	}

	/**
	* Sets the key of this training certificate.
	*
	* @param key the key of this training certificate
	*/
	public void setKey(java.lang.String key) {
		_trainingCertificate.setKey(key);
	}

	/**
	* Returns the certified date of this training certificate.
	*
	* @return the certified date of this training certificate
	*/
	public java.util.Date getCertifiedDate() {
		return _trainingCertificate.getCertifiedDate();
	}

	/**
	* Sets the certified date of this training certificate.
	*
	* @param certifiedDate the certified date of this training certificate
	*/
	public void setCertifiedDate(java.util.Date certifiedDate) {
		_trainingCertificate.setCertifiedDate(certifiedDate);
	}

	/**
	* Returns the comments of this training certificate.
	*
	* @return the comments of this training certificate
	*/
	public java.lang.String getComments() {
		return _trainingCertificate.getComments();
	}

	/**
	* Sets the comments of this training certificate.
	*
	* @param comments the comments of this training certificate
	*/
	public void setComments(java.lang.String comments) {
		_trainingCertificate.setComments(comments);
	}

	/**
	* Returns the survey status of this training certificate.
	*
	* @return the survey status of this training certificate
	*/
	public int getSurveyStatus() {
		return _trainingCertificate.getSurveyStatus();
	}

	/**
	* Sets the survey status of this training certificate.
	*
	* @param surveyStatus the survey status of this training certificate
	*/
	public void setSurveyStatus(int surveyStatus) {
		_trainingCertificate.setSurveyStatus(surveyStatus);
	}

	public boolean isNew() {
		return _trainingCertificate.isNew();
	}

	public void setNew(boolean n) {
		_trainingCertificate.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingCertificate.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingCertificate.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingCertificate.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingCertificate.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingCertificate.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingCertificate.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingCertificate.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingCertificateWrapper((TrainingCertificate)_trainingCertificate.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TrainingCertificate trainingCertificate) {
		return _trainingCertificate.compareTo(trainingCertificate);
	}

	@Override
	public int hashCode() {
		return _trainingCertificate.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingCertificate> toCacheModel() {
		return _trainingCertificate.toCacheModel();
	}

	public com.liferay.osb.model.TrainingCertificate toEscapedModel() {
		return new TrainingCertificateWrapper(_trainingCertificate.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingCertificate toUnescapedModel() {
		return new TrainingCertificateWrapper(_trainingCertificate.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingCertificate.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingCertificate.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingCertificate.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingCertificateWrapper)) {
			return false;
		}

		TrainingCertificateWrapper trainingCertificateWrapper = (TrainingCertificateWrapper)obj;

		if (Validator.equals(_trainingCertificate,
					trainingCertificateWrapper._trainingCertificate)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingCertificate getWrappedTrainingCertificate() {
		return _trainingCertificate;
	}

	public TrainingCertificate getWrappedModel() {
		return _trainingCertificate;
	}

	public void resetOriginalValues() {
		_trainingCertificate.resetOriginalValues();
	}

	private TrainingCertificate _trainingCertificate;
}