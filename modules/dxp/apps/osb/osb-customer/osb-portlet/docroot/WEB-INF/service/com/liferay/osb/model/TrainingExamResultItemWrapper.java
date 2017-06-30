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
 * This class is a wrapper for {@link TrainingExamResultItem}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingExamResultItem
 * @generated
 */
public class TrainingExamResultItemWrapper implements TrainingExamResultItem,
	ModelWrapper<TrainingExamResultItem> {
	public TrainingExamResultItemWrapper(
		TrainingExamResultItem trainingExamResultItem) {
		_trainingExamResultItem = trainingExamResultItem;
	}

	public Class<?> getModelClass() {
		return TrainingExamResultItem.class;
	}

	public String getModelClassName() {
		return TrainingExamResultItem.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingExamResultItemId", getTrainingExamResultItemId());
		attributes.put("trainingExamResultId", getTrainingExamResultId());
		attributes.put("trainingExamResultSectionId",
			getTrainingExamResultSectionId());
		attributes.put("name", getName());
		attributes.put("status", getStatus());
		attributes.put("key", getKey());
		attributes.put("distractorCount", getDistractorCount());
		attributes.put("type", getType());
		attributes.put("response", getResponse());
		attributes.put("score", getScore());
		attributes.put("time", getTime());
		attributes.put("learningResources", getLearningResources());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingExamResultItemId = (Long)attributes.get(
				"trainingExamResultItemId");

		if (trainingExamResultItemId != null) {
			setTrainingExamResultItemId(trainingExamResultItemId);
		}

		Long trainingExamResultId = (Long)attributes.get("trainingExamResultId");

		if (trainingExamResultId != null) {
			setTrainingExamResultId(trainingExamResultId);
		}

		Long trainingExamResultSectionId = (Long)attributes.get(
				"trainingExamResultSectionId");

		if (trainingExamResultSectionId != null) {
			setTrainingExamResultSectionId(trainingExamResultSectionId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		Integer distractorCount = (Integer)attributes.get("distractorCount");

		if (distractorCount != null) {
			setDistractorCount(distractorCount);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String response = (String)attributes.get("response");

		if (response != null) {
			setResponse(response);
		}

		String score = (String)attributes.get("score");

		if (score != null) {
			setScore(score);
		}

		Integer time = (Integer)attributes.get("time");

		if (time != null) {
			setTime(time);
		}

		String learningResources = (String)attributes.get("learningResources");

		if (learningResources != null) {
			setLearningResources(learningResources);
		}
	}

	/**
	* Returns the primary key of this training exam result item.
	*
	* @return the primary key of this training exam result item
	*/
	public long getPrimaryKey() {
		return _trainingExamResultItem.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training exam result item.
	*
	* @param primaryKey the primary key of this training exam result item
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingExamResultItem.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training exam result item ID of this training exam result item.
	*
	* @return the training exam result item ID of this training exam result item
	*/
	public long getTrainingExamResultItemId() {
		return _trainingExamResultItem.getTrainingExamResultItemId();
	}

	/**
	* Sets the training exam result item ID of this training exam result item.
	*
	* @param trainingExamResultItemId the training exam result item ID of this training exam result item
	*/
	public void setTrainingExamResultItemId(long trainingExamResultItemId) {
		_trainingExamResultItem.setTrainingExamResultItemId(trainingExamResultItemId);
	}

	/**
	* Returns the training exam result ID of this training exam result item.
	*
	* @return the training exam result ID of this training exam result item
	*/
	public long getTrainingExamResultId() {
		return _trainingExamResultItem.getTrainingExamResultId();
	}

	/**
	* Sets the training exam result ID of this training exam result item.
	*
	* @param trainingExamResultId the training exam result ID of this training exam result item
	*/
	public void setTrainingExamResultId(long trainingExamResultId) {
		_trainingExamResultItem.setTrainingExamResultId(trainingExamResultId);
	}

	/**
	* Returns the training exam result section ID of this training exam result item.
	*
	* @return the training exam result section ID of this training exam result item
	*/
	public long getTrainingExamResultSectionId() {
		return _trainingExamResultItem.getTrainingExamResultSectionId();
	}

	/**
	* Sets the training exam result section ID of this training exam result item.
	*
	* @param trainingExamResultSectionId the training exam result section ID of this training exam result item
	*/
	public void setTrainingExamResultSectionId(long trainingExamResultSectionId) {
		_trainingExamResultItem.setTrainingExamResultSectionId(trainingExamResultSectionId);
	}

	/**
	* Returns the name of this training exam result item.
	*
	* @return the name of this training exam result item
	*/
	public java.lang.String getName() {
		return _trainingExamResultItem.getName();
	}

	/**
	* Sets the name of this training exam result item.
	*
	* @param name the name of this training exam result item
	*/
	public void setName(java.lang.String name) {
		_trainingExamResultItem.setName(name);
	}

	/**
	* Returns the status of this training exam result item.
	*
	* @return the status of this training exam result item
	*/
	public java.lang.String getStatus() {
		return _trainingExamResultItem.getStatus();
	}

	/**
	* Sets the status of this training exam result item.
	*
	* @param status the status of this training exam result item
	*/
	public void setStatus(java.lang.String status) {
		_trainingExamResultItem.setStatus(status);
	}

	/**
	* Returns the key of this training exam result item.
	*
	* @return the key of this training exam result item
	*/
	public java.lang.String getKey() {
		return _trainingExamResultItem.getKey();
	}

	/**
	* Sets the key of this training exam result item.
	*
	* @param key the key of this training exam result item
	*/
	public void setKey(java.lang.String key) {
		_trainingExamResultItem.setKey(key);
	}

	/**
	* Returns the distractor count of this training exam result item.
	*
	* @return the distractor count of this training exam result item
	*/
	public int getDistractorCount() {
		return _trainingExamResultItem.getDistractorCount();
	}

	/**
	* Sets the distractor count of this training exam result item.
	*
	* @param distractorCount the distractor count of this training exam result item
	*/
	public void setDistractorCount(int distractorCount) {
		_trainingExamResultItem.setDistractorCount(distractorCount);
	}

	/**
	* Returns the type of this training exam result item.
	*
	* @return the type of this training exam result item
	*/
	public java.lang.String getType() {
		return _trainingExamResultItem.getType();
	}

	/**
	* Sets the type of this training exam result item.
	*
	* @param type the type of this training exam result item
	*/
	public void setType(java.lang.String type) {
		_trainingExamResultItem.setType(type);
	}

	/**
	* Returns the response of this training exam result item.
	*
	* @return the response of this training exam result item
	*/
	public java.lang.String getResponse() {
		return _trainingExamResultItem.getResponse();
	}

	/**
	* Sets the response of this training exam result item.
	*
	* @param response the response of this training exam result item
	*/
	public void setResponse(java.lang.String response) {
		_trainingExamResultItem.setResponse(response);
	}

	/**
	* Returns the score of this training exam result item.
	*
	* @return the score of this training exam result item
	*/
	public java.lang.String getScore() {
		return _trainingExamResultItem.getScore();
	}

	/**
	* Sets the score of this training exam result item.
	*
	* @param score the score of this training exam result item
	*/
	public void setScore(java.lang.String score) {
		_trainingExamResultItem.setScore(score);
	}

	/**
	* Returns the time of this training exam result item.
	*
	* @return the time of this training exam result item
	*/
	public int getTime() {
		return _trainingExamResultItem.getTime();
	}

	/**
	* Sets the time of this training exam result item.
	*
	* @param time the time of this training exam result item
	*/
	public void setTime(int time) {
		_trainingExamResultItem.setTime(time);
	}

	/**
	* Returns the learning resources of this training exam result item.
	*
	* @return the learning resources of this training exam result item
	*/
	public java.lang.String getLearningResources() {
		return _trainingExamResultItem.getLearningResources();
	}

	/**
	* Sets the learning resources of this training exam result item.
	*
	* @param learningResources the learning resources of this training exam result item
	*/
	public void setLearningResources(java.lang.String learningResources) {
		_trainingExamResultItem.setLearningResources(learningResources);
	}

	public boolean isNew() {
		return _trainingExamResultItem.isNew();
	}

	public void setNew(boolean n) {
		_trainingExamResultItem.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingExamResultItem.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingExamResultItem.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingExamResultItem.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingExamResultItem.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingExamResultItem.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingExamResultItem.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingExamResultItem.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingExamResultItemWrapper((TrainingExamResultItem)_trainingExamResultItem.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TrainingExamResultItem trainingExamResultItem) {
		return _trainingExamResultItem.compareTo(trainingExamResultItem);
	}

	@Override
	public int hashCode() {
		return _trainingExamResultItem.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingExamResultItem> toCacheModel() {
		return _trainingExamResultItem.toCacheModel();
	}

	public com.liferay.osb.model.TrainingExamResultItem toEscapedModel() {
		return new TrainingExamResultItemWrapper(_trainingExamResultItem.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingExamResultItem toUnescapedModel() {
		return new TrainingExamResultItemWrapper(_trainingExamResultItem.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingExamResultItem.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingExamResultItem.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingExamResultItem.persist();
	}

	public java.lang.String getScoreResult() {
		return _trainingExamResultItem.getScoreResult();
	}

	public java.lang.String getSectionKey()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingExamResultItem.getSectionKey();
	}

	public java.lang.String getStatusLabel() {
		return _trainingExamResultItem.getStatusLabel();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingExamResultItemWrapper)) {
			return false;
		}

		TrainingExamResultItemWrapper trainingExamResultItemWrapper = (TrainingExamResultItemWrapper)obj;

		if (Validator.equals(_trainingExamResultItem,
					trainingExamResultItemWrapper._trainingExamResultItem)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingExamResultItem getWrappedTrainingExamResultItem() {
		return _trainingExamResultItem;
	}

	public TrainingExamResultItem getWrappedModel() {
		return _trainingExamResultItem;
	}

	public void resetOriginalValues() {
		_trainingExamResultItem.resetOriginalValues();
	}

	private TrainingExamResultItem _trainingExamResultItem;
}