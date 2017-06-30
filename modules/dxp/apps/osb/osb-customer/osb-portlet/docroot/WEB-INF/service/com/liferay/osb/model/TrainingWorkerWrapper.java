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
 * This class is a wrapper for {@link TrainingWorker}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingWorker
 * @generated
 */
public class TrainingWorkerWrapper implements TrainingWorker,
	ModelWrapper<TrainingWorker> {
	public TrainingWorkerWrapper(TrainingWorker trainingWorker) {
		_trainingWorker = trainingWorker;
	}

	public Class<?> getModelClass() {
		return TrainingWorker.class;
	}

	public String getModelClassName() {
		return TrainingWorker.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingWorkerId", getTrainingWorkerId());
		attributes.put("userId", getUserId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("userProfileHistoryId", getUserProfileHistoryId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingWorkerId = (Long)attributes.get("trainingWorkerId");

		if (trainingWorkerId != null) {
			setTrainingWorkerId(trainingWorkerId);
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
	}

	/**
	* Returns the primary key of this training worker.
	*
	* @return the primary key of this training worker
	*/
	public long getPrimaryKey() {
		return _trainingWorker.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training worker.
	*
	* @param primaryKey the primary key of this training worker
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingWorker.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training worker ID of this training worker.
	*
	* @return the training worker ID of this training worker
	*/
	public long getTrainingWorkerId() {
		return _trainingWorker.getTrainingWorkerId();
	}

	/**
	* Sets the training worker ID of this training worker.
	*
	* @param trainingWorkerId the training worker ID of this training worker
	*/
	public void setTrainingWorkerId(long trainingWorkerId) {
		_trainingWorker.setTrainingWorkerId(trainingWorkerId);
	}

	/**
	* Returns the user ID of this training worker.
	*
	* @return the user ID of this training worker
	*/
	public long getUserId() {
		return _trainingWorker.getUserId();
	}

	/**
	* Sets the user ID of this training worker.
	*
	* @param userId the user ID of this training worker
	*/
	public void setUserId(long userId) {
		_trainingWorker.setUserId(userId);
	}

	/**
	* Returns the user uuid of this training worker.
	*
	* @return the user uuid of this training worker
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingWorker.getUserUuid();
	}

	/**
	* Sets the user uuid of this training worker.
	*
	* @param userUuid the user uuid of this training worker
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_trainingWorker.setUserUuid(userUuid);
	}

	/**
	* Returns the fully qualified class name of this training worker.
	*
	* @return the fully qualified class name of this training worker
	*/
	public java.lang.String getClassName() {
		return _trainingWorker.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_trainingWorker.setClassName(className);
	}

	/**
	* Returns the class name ID of this training worker.
	*
	* @return the class name ID of this training worker
	*/
	public long getClassNameId() {
		return _trainingWorker.getClassNameId();
	}

	/**
	* Sets the class name ID of this training worker.
	*
	* @param classNameId the class name ID of this training worker
	*/
	public void setClassNameId(long classNameId) {
		_trainingWorker.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this training worker.
	*
	* @return the class p k of this training worker
	*/
	public long getClassPK() {
		return _trainingWorker.getClassPK();
	}

	/**
	* Sets the class p k of this training worker.
	*
	* @param classPK the class p k of this training worker
	*/
	public void setClassPK(long classPK) {
		_trainingWorker.setClassPK(classPK);
	}

	/**
	* Returns the user profile history ID of this training worker.
	*
	* @return the user profile history ID of this training worker
	*/
	public long getUserProfileHistoryId() {
		return _trainingWorker.getUserProfileHistoryId();
	}

	/**
	* Sets the user profile history ID of this training worker.
	*
	* @param userProfileHistoryId the user profile history ID of this training worker
	*/
	public void setUserProfileHistoryId(long userProfileHistoryId) {
		_trainingWorker.setUserProfileHistoryId(userProfileHistoryId);
	}

	public boolean isNew() {
		return _trainingWorker.isNew();
	}

	public void setNew(boolean n) {
		_trainingWorker.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingWorker.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingWorker.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingWorker.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingWorker.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingWorker.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingWorker.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingWorker.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingWorkerWrapper((TrainingWorker)_trainingWorker.clone());
	}

	public int compareTo(com.liferay.osb.model.TrainingWorker trainingWorker) {
		return _trainingWorker.compareTo(trainingWorker);
	}

	@Override
	public int hashCode() {
		return _trainingWorker.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingWorker> toCacheModel() {
		return _trainingWorker.toCacheModel();
	}

	public com.liferay.osb.model.TrainingWorker toEscapedModel() {
		return new TrainingWorkerWrapper(_trainingWorker.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingWorker toUnescapedModel() {
		return new TrainingWorkerWrapper(_trainingWorker.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingWorker.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingWorker.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingWorker.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingWorkerWrapper)) {
			return false;
		}

		TrainingWorkerWrapper trainingWorkerWrapper = (TrainingWorkerWrapper)obj;

		if (Validator.equals(_trainingWorker,
					trainingWorkerWrapper._trainingWorker)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingWorker getWrappedTrainingWorker() {
		return _trainingWorker;
	}

	public TrainingWorker getWrappedModel() {
		return _trainingWorker;
	}

	public void resetOriginalValues() {
		_trainingWorker.resetOriginalValues();
	}

	private TrainingWorker _trainingWorker;
}