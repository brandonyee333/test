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
 * This class is a wrapper for {@link TrainingCertificateTemplate}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingCertificateTemplate
 * @generated
 */
public class TrainingCertificateTemplateWrapper
	implements TrainingCertificateTemplate,
		ModelWrapper<TrainingCertificateTemplate> {
	public TrainingCertificateTemplateWrapper(
		TrainingCertificateTemplate trainingCertificateTemplate) {
		_trainingCertificateTemplate = trainingCertificateTemplate;
	}

	public Class<?> getModelClass() {
		return TrainingCertificateTemplate.class;
	}

	public String getModelClassName() {
		return TrainingCertificateTemplate.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingCertificateTemplateId",
			getTrainingCertificateTemplateId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("type", getType());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingCertificateTemplateId = (Long)attributes.get(
				"trainingCertificateTemplateId");

		if (trainingCertificateTemplateId != null) {
			setTrainingCertificateTemplateId(trainingCertificateTemplateId);
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

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	/**
	* Returns the primary key of this training certificate template.
	*
	* @return the primary key of this training certificate template
	*/
	public long getPrimaryKey() {
		return _trainingCertificateTemplate.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training certificate template.
	*
	* @param primaryKey the primary key of this training certificate template
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingCertificateTemplate.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training certificate template ID of this training certificate template.
	*
	* @return the training certificate template ID of this training certificate template
	*/
	public long getTrainingCertificateTemplateId() {
		return _trainingCertificateTemplate.getTrainingCertificateTemplateId();
	}

	/**
	* Sets the training certificate template ID of this training certificate template.
	*
	* @param trainingCertificateTemplateId the training certificate template ID of this training certificate template
	*/
	public void setTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) {
		_trainingCertificateTemplate.setTrainingCertificateTemplateId(trainingCertificateTemplateId);
	}

	/**
	* Returns the user ID of this training certificate template.
	*
	* @return the user ID of this training certificate template
	*/
	public long getUserId() {
		return _trainingCertificateTemplate.getUserId();
	}

	/**
	* Sets the user ID of this training certificate template.
	*
	* @param userId the user ID of this training certificate template
	*/
	public void setUserId(long userId) {
		_trainingCertificateTemplate.setUserId(userId);
	}

	/**
	* Returns the user uuid of this training certificate template.
	*
	* @return the user uuid of this training certificate template
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingCertificateTemplate.getUserUuid();
	}

	/**
	* Sets the user uuid of this training certificate template.
	*
	* @param userUuid the user uuid of this training certificate template
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_trainingCertificateTemplate.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this training certificate template.
	*
	* @return the user name of this training certificate template
	*/
	public java.lang.String getUserName() {
		return _trainingCertificateTemplate.getUserName();
	}

	/**
	* Sets the user name of this training certificate template.
	*
	* @param userName the user name of this training certificate template
	*/
	public void setUserName(java.lang.String userName) {
		_trainingCertificateTemplate.setUserName(userName);
	}

	/**
	* Returns the create date of this training certificate template.
	*
	* @return the create date of this training certificate template
	*/
	public java.util.Date getCreateDate() {
		return _trainingCertificateTemplate.getCreateDate();
	}

	/**
	* Sets the create date of this training certificate template.
	*
	* @param createDate the create date of this training certificate template
	*/
	public void setCreateDate(java.util.Date createDate) {
		_trainingCertificateTemplate.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this training certificate template.
	*
	* @return the modified date of this training certificate template
	*/
	public java.util.Date getModifiedDate() {
		return _trainingCertificateTemplate.getModifiedDate();
	}

	/**
	* Sets the modified date of this training certificate template.
	*
	* @param modifiedDate the modified date of this training certificate template
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_trainingCertificateTemplate.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this training certificate template.
	*
	* @return the name of this training certificate template
	*/
	public java.lang.String getName() {
		return _trainingCertificateTemplate.getName();
	}

	/**
	* Sets the name of this training certificate template.
	*
	* @param name the name of this training certificate template
	*/
	public void setName(java.lang.String name) {
		_trainingCertificateTemplate.setName(name);
	}

	/**
	* Returns the description of this training certificate template.
	*
	* @return the description of this training certificate template
	*/
	public java.lang.String getDescription() {
		return _trainingCertificateTemplate.getDescription();
	}

	/**
	* Sets the description of this training certificate template.
	*
	* @param description the description of this training certificate template
	*/
	public void setDescription(java.lang.String description) {
		_trainingCertificateTemplate.setDescription(description);
	}

	/**
	* Returns the type of this training certificate template.
	*
	* @return the type of this training certificate template
	*/
	public int getType() {
		return _trainingCertificateTemplate.getType();
	}

	/**
	* Sets the type of this training certificate template.
	*
	* @param type the type of this training certificate template
	*/
	public void setType(int type) {
		_trainingCertificateTemplate.setType(type);
	}

	public boolean isNew() {
		return _trainingCertificateTemplate.isNew();
	}

	public void setNew(boolean n) {
		_trainingCertificateTemplate.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingCertificateTemplate.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingCertificateTemplate.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingCertificateTemplate.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingCertificateTemplate.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingCertificateTemplate.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingCertificateTemplate.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingCertificateTemplate.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingCertificateTemplateWrapper((TrainingCertificateTemplate)_trainingCertificateTemplate.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TrainingCertificateTemplate trainingCertificateTemplate) {
		return _trainingCertificateTemplate.compareTo(trainingCertificateTemplate);
	}

	@Override
	public int hashCode() {
		return _trainingCertificateTemplate.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingCertificateTemplate> toCacheModel() {
		return _trainingCertificateTemplate.toCacheModel();
	}

	public com.liferay.osb.model.TrainingCertificateTemplate toEscapedModel() {
		return new TrainingCertificateTemplateWrapper(_trainingCertificateTemplate.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingCertificateTemplate toUnescapedModel() {
		return new TrainingCertificateTemplateWrapper(_trainingCertificateTemplate.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingCertificateTemplate.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingCertificateTemplate.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingCertificateTemplate.persist();
	}

	public java.lang.String getBadgesDir() {
		return _trainingCertificateTemplate.getBadgesDir();
	}

	public java.lang.String getTemplatesDir() {
		return _trainingCertificateTemplate.getTemplatesDir();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingCertificateTemplateWrapper)) {
			return false;
		}

		TrainingCertificateTemplateWrapper trainingCertificateTemplateWrapper = (TrainingCertificateTemplateWrapper)obj;

		if (Validator.equals(_trainingCertificateTemplate,
					trainingCertificateTemplateWrapper._trainingCertificateTemplate)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingCertificateTemplate getWrappedTrainingCertificateTemplate() {
		return _trainingCertificateTemplate;
	}

	public TrainingCertificateTemplate getWrappedModel() {
		return _trainingCertificateTemplate;
	}

	public void resetOriginalValues() {
		_trainingCertificateTemplate.resetOriginalValues();
	}

	private TrainingCertificateTemplate _trainingCertificateTemplate;
}