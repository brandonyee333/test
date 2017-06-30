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
 * This class is a wrapper for {@link TrainingExamResult}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingExamResult
 * @generated
 */
public class TrainingExamResultWrapper implements TrainingExamResult,
	ModelWrapper<TrainingExamResult> {
	public TrainingExamResultWrapper(TrainingExamResult trainingExamResult) {
		_trainingExamResult = trainingExamResult;
	}

	public Class<?> getModelClass() {
		return TrainingExamResult.class;
	}

	public String getModelClassName() {
		return TrainingExamResult.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingExamResultId", getTrainingExamResultId());
		attributes.put("createDate", getCreateDate());
		attributes.put("trainingExamId", getTrainingExamId());
		attributes.put("recordType", getRecordType());
		attributes.put("registrationNumber", getRegistrationNumber());
		attributes.put("formKey", getFormKey());
		attributes.put("startDate", getStartDate());
		attributes.put("testScore", getTestScore());
		attributes.put("correctCount", getCorrectCount());
		attributes.put("incorrectCount", getIncorrectCount());
		attributes.put("skippedCount", getSkippedCount());
		attributes.put("grade", getGrade());
		attributes.put("status", getStatus());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingExamResultId = (Long)attributes.get("trainingExamResultId");

		if (trainingExamResultId != null) {
			setTrainingExamResultId(trainingExamResultId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long trainingExamId = (Long)attributes.get("trainingExamId");

		if (trainingExamId != null) {
			setTrainingExamId(trainingExamId);
		}

		Integer recordType = (Integer)attributes.get("recordType");

		if (recordType != null) {
			setRecordType(recordType);
		}

		String registrationNumber = (String)attributes.get("registrationNumber");

		if (registrationNumber != null) {
			setRegistrationNumber(registrationNumber);
		}

		String formKey = (String)attributes.get("formKey");

		if (formKey != null) {
			setFormKey(formKey);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		String testScore = (String)attributes.get("testScore");

		if (testScore != null) {
			setTestScore(testScore);
		}

		Integer correctCount = (Integer)attributes.get("correctCount");

		if (correctCount != null) {
			setCorrectCount(correctCount);
		}

		Integer incorrectCount = (Integer)attributes.get("incorrectCount");

		if (incorrectCount != null) {
			setIncorrectCount(incorrectCount);
		}

		Integer skippedCount = (Integer)attributes.get("skippedCount");

		if (skippedCount != null) {
			setSkippedCount(skippedCount);
		}

		Integer grade = (Integer)attributes.get("grade");

		if (grade != null) {
			setGrade(grade);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	* Returns the primary key of this training exam result.
	*
	* @return the primary key of this training exam result
	*/
	public long getPrimaryKey() {
		return _trainingExamResult.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training exam result.
	*
	* @param primaryKey the primary key of this training exam result
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingExamResult.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training exam result ID of this training exam result.
	*
	* @return the training exam result ID of this training exam result
	*/
	public long getTrainingExamResultId() {
		return _trainingExamResult.getTrainingExamResultId();
	}

	/**
	* Sets the training exam result ID of this training exam result.
	*
	* @param trainingExamResultId the training exam result ID of this training exam result
	*/
	public void setTrainingExamResultId(long trainingExamResultId) {
		_trainingExamResult.setTrainingExamResultId(trainingExamResultId);
	}

	/**
	* Returns the create date of this training exam result.
	*
	* @return the create date of this training exam result
	*/
	public java.util.Date getCreateDate() {
		return _trainingExamResult.getCreateDate();
	}

	/**
	* Sets the create date of this training exam result.
	*
	* @param createDate the create date of this training exam result
	*/
	public void setCreateDate(java.util.Date createDate) {
		_trainingExamResult.setCreateDate(createDate);
	}

	/**
	* Returns the training exam ID of this training exam result.
	*
	* @return the training exam ID of this training exam result
	*/
	public long getTrainingExamId() {
		return _trainingExamResult.getTrainingExamId();
	}

	/**
	* Sets the training exam ID of this training exam result.
	*
	* @param trainingExamId the training exam ID of this training exam result
	*/
	public void setTrainingExamId(long trainingExamId) {
		_trainingExamResult.setTrainingExamId(trainingExamId);
	}

	/**
	* Returns the record type of this training exam result.
	*
	* @return the record type of this training exam result
	*/
	public int getRecordType() {
		return _trainingExamResult.getRecordType();
	}

	/**
	* Sets the record type of this training exam result.
	*
	* @param recordType the record type of this training exam result
	*/
	public void setRecordType(int recordType) {
		_trainingExamResult.setRecordType(recordType);
	}

	/**
	* Returns the registration number of this training exam result.
	*
	* @return the registration number of this training exam result
	*/
	public java.lang.String getRegistrationNumber() {
		return _trainingExamResult.getRegistrationNumber();
	}

	/**
	* Sets the registration number of this training exam result.
	*
	* @param registrationNumber the registration number of this training exam result
	*/
	public void setRegistrationNumber(java.lang.String registrationNumber) {
		_trainingExamResult.setRegistrationNumber(registrationNumber);
	}

	/**
	* Returns the form key of this training exam result.
	*
	* @return the form key of this training exam result
	*/
	public java.lang.String getFormKey() {
		return _trainingExamResult.getFormKey();
	}

	/**
	* Sets the form key of this training exam result.
	*
	* @param formKey the form key of this training exam result
	*/
	public void setFormKey(java.lang.String formKey) {
		_trainingExamResult.setFormKey(formKey);
	}

	/**
	* Returns the start date of this training exam result.
	*
	* @return the start date of this training exam result
	*/
	public java.util.Date getStartDate() {
		return _trainingExamResult.getStartDate();
	}

	/**
	* Sets the start date of this training exam result.
	*
	* @param startDate the start date of this training exam result
	*/
	public void setStartDate(java.util.Date startDate) {
		_trainingExamResult.setStartDate(startDate);
	}

	/**
	* Returns the test score of this training exam result.
	*
	* @return the test score of this training exam result
	*/
	public java.lang.String getTestScore() {
		return _trainingExamResult.getTestScore();
	}

	/**
	* Sets the test score of this training exam result.
	*
	* @param testScore the test score of this training exam result
	*/
	public void setTestScore(java.lang.String testScore) {
		_trainingExamResult.setTestScore(testScore);
	}

	/**
	* Returns the correct count of this training exam result.
	*
	* @return the correct count of this training exam result
	*/
	public int getCorrectCount() {
		return _trainingExamResult.getCorrectCount();
	}

	/**
	* Sets the correct count of this training exam result.
	*
	* @param correctCount the correct count of this training exam result
	*/
	public void setCorrectCount(int correctCount) {
		_trainingExamResult.setCorrectCount(correctCount);
	}

	/**
	* Returns the incorrect count of this training exam result.
	*
	* @return the incorrect count of this training exam result
	*/
	public int getIncorrectCount() {
		return _trainingExamResult.getIncorrectCount();
	}

	/**
	* Sets the incorrect count of this training exam result.
	*
	* @param incorrectCount the incorrect count of this training exam result
	*/
	public void setIncorrectCount(int incorrectCount) {
		_trainingExamResult.setIncorrectCount(incorrectCount);
	}

	/**
	* Returns the skipped count of this training exam result.
	*
	* @return the skipped count of this training exam result
	*/
	public int getSkippedCount() {
		return _trainingExamResult.getSkippedCount();
	}

	/**
	* Sets the skipped count of this training exam result.
	*
	* @param skippedCount the skipped count of this training exam result
	*/
	public void setSkippedCount(int skippedCount) {
		_trainingExamResult.setSkippedCount(skippedCount);
	}

	/**
	* Returns the grade of this training exam result.
	*
	* @return the grade of this training exam result
	*/
	public int getGrade() {
		return _trainingExamResult.getGrade();
	}

	/**
	* Sets the grade of this training exam result.
	*
	* @param grade the grade of this training exam result
	*/
	public void setGrade(int grade) {
		_trainingExamResult.setGrade(grade);
	}

	/**
	* Returns the status of this training exam result.
	*
	* @return the status of this training exam result
	*/
	public int getStatus() {
		return _trainingExamResult.getStatus();
	}

	/**
	* Sets the status of this training exam result.
	*
	* @param status the status of this training exam result
	*/
	public void setStatus(int status) {
		_trainingExamResult.setStatus(status);
	}

	public boolean isNew() {
		return _trainingExamResult.isNew();
	}

	public void setNew(boolean n) {
		_trainingExamResult.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingExamResult.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingExamResult.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingExamResult.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingExamResult.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingExamResult.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingExamResult.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingExamResult.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingExamResultWrapper((TrainingExamResult)_trainingExamResult.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TrainingExamResult trainingExamResult) {
		return _trainingExamResult.compareTo(trainingExamResult);
	}

	@Override
	public int hashCode() {
		return _trainingExamResult.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingExamResult> toCacheModel() {
		return _trainingExamResult.toCacheModel();
	}

	public com.liferay.osb.model.TrainingExamResult toEscapedModel() {
		return new TrainingExamResultWrapper(_trainingExamResult.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingExamResult toUnescapedModel() {
		return new TrainingExamResultWrapper(_trainingExamResult.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingExamResult.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingExamResult.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingExamResult.persist();
	}

	public java.lang.String getCandidateKey()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResult.getCandidateKey();
	}

	public java.lang.String getResult() {
		return _trainingExamResult.getResult();
	}

	public java.lang.String getTrainingCertificateKey()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResult.getTrainingCertificateKey();
	}

	public com.liferay.osb.model.TrainingCertificateTemplate getTrainingCertificateTemplate()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResult.getTrainingCertificateTemplate();
	}

	public com.liferay.osb.model.TrainingCustomer getTrainingCustomer()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResult.getTrainingCustomer();
	}

	public com.liferay.osb.model.TrainingExam getTrainingExam()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResult.getTrainingExam();
	}

	public com.liferay.portal.model.User getUser()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResult.getUser();
	}

	public boolean hasPassedGrade() {
		return _trainingExamResult.hasPassedGrade();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingExamResultWrapper)) {
			return false;
		}

		TrainingExamResultWrapper trainingExamResultWrapper = (TrainingExamResultWrapper)obj;

		if (Validator.equals(_trainingExamResult,
					trainingExamResultWrapper._trainingExamResult)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingExamResult getWrappedTrainingExamResult() {
		return _trainingExamResult;
	}

	public TrainingExamResult getWrappedModel() {
		return _trainingExamResult;
	}

	public void resetOriginalValues() {
		_trainingExamResult.resetOriginalValues();
	}

	private TrainingExamResult _trainingExamResult;
}