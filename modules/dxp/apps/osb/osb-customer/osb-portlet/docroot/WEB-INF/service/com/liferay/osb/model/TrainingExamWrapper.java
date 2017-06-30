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
 * This class is a wrapper for {@link TrainingExam}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingExam
 * @generated
 */
public class TrainingExamWrapper implements TrainingExam,
	ModelWrapper<TrainingExam> {
	public TrainingExamWrapper(TrainingExam trainingExam) {
		_trainingExam = trainingExam;
	}

	public Class<?> getModelClass() {
		return TrainingExam.class;
	}

	public String getModelClassName() {
		return TrainingExam.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingExamId", getTrainingExamId());
		attributes.put("trainingCertificateTemplateId",
			getTrainingCertificateTemplateId());
		attributes.put("name", getName());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingExamId = (Long)attributes.get("trainingExamId");

		if (trainingExamId != null) {
			setTrainingExamId(trainingExamId);
		}

		Long trainingCertificateTemplateId = (Long)attributes.get(
				"trainingCertificateTemplateId");

		if (trainingCertificateTemplateId != null) {
			setTrainingCertificateTemplateId(trainingCertificateTemplateId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	/**
	* Returns the primary key of this training exam.
	*
	* @return the primary key of this training exam
	*/
	public long getPrimaryKey() {
		return _trainingExam.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training exam.
	*
	* @param primaryKey the primary key of this training exam
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingExam.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training exam ID of this training exam.
	*
	* @return the training exam ID of this training exam
	*/
	public long getTrainingExamId() {
		return _trainingExam.getTrainingExamId();
	}

	/**
	* Sets the training exam ID of this training exam.
	*
	* @param trainingExamId the training exam ID of this training exam
	*/
	public void setTrainingExamId(long trainingExamId) {
		_trainingExam.setTrainingExamId(trainingExamId);
	}

	/**
	* Returns the training certificate template ID of this training exam.
	*
	* @return the training certificate template ID of this training exam
	*/
	public long getTrainingCertificateTemplateId() {
		return _trainingExam.getTrainingCertificateTemplateId();
	}

	/**
	* Sets the training certificate template ID of this training exam.
	*
	* @param trainingCertificateTemplateId the training certificate template ID of this training exam
	*/
	public void setTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) {
		_trainingExam.setTrainingCertificateTemplateId(trainingCertificateTemplateId);
	}

	/**
	* Returns the name of this training exam.
	*
	* @return the name of this training exam
	*/
	public java.lang.String getName() {
		return _trainingExam.getName();
	}

	/**
	* Sets the name of this training exam.
	*
	* @param name the name of this training exam
	*/
	public void setName(java.lang.String name) {
		_trainingExam.setName(name);
	}

	public boolean isNew() {
		return _trainingExam.isNew();
	}

	public void setNew(boolean n) {
		_trainingExam.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingExam.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingExam.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingExam.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingExam.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingExam.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingExam.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingExam.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingExamWrapper((TrainingExam)_trainingExam.clone());
	}

	public int compareTo(com.liferay.osb.model.TrainingExam trainingExam) {
		return _trainingExam.compareTo(trainingExam);
	}

	@Override
	public int hashCode() {
		return _trainingExam.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingExam> toCacheModel() {
		return _trainingExam.toCacheModel();
	}

	public com.liferay.osb.model.TrainingExam toEscapedModel() {
		return new TrainingExamWrapper(_trainingExam.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingExam toUnescapedModel() {
		return new TrainingExamWrapper(_trainingExam.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingExam.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingExam.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingExam.persist();
	}

	public java.lang.String getTestKey()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExam.getTestKey();
	}

	public com.liferay.osb.model.TrainingCertificateTemplate getTrainingCertificateTemplate()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExam.getTrainingCertificateTemplate();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingExamWrapper)) {
			return false;
		}

		TrainingExamWrapper trainingExamWrapper = (TrainingExamWrapper)obj;

		if (Validator.equals(_trainingExam, trainingExamWrapper._trainingExam)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingExam getWrappedTrainingExam() {
		return _trainingExam;
	}

	public TrainingExam getWrappedModel() {
		return _trainingExam;
	}

	public void resetOriginalValues() {
		_trainingExam.resetOriginalValues();
	}

	private TrainingExam _trainingExam;
}