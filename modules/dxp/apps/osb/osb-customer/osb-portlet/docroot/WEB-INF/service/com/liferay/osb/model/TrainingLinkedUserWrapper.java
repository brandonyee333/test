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
 * This class is a wrapper for {@link TrainingLinkedUser}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingLinkedUser
 * @generated
 */
public class TrainingLinkedUserWrapper implements TrainingLinkedUser,
	ModelWrapper<TrainingLinkedUser> {
	public TrainingLinkedUserWrapper(TrainingLinkedUser trainingLinkedUser) {
		_trainingLinkedUser = trainingLinkedUser;
	}

	public Class<?> getModelClass() {
		return TrainingLinkedUser.class;
	}

	public String getModelClassName() {
		return TrainingLinkedUser.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingLinkedUserId", getTrainingLinkedUserId());
		attributes.put("userId", getUserId());
		attributes.put("primaryUserId", getPrimaryUserId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingLinkedUserId = (Long)attributes.get("trainingLinkedUserId");

		if (trainingLinkedUserId != null) {
			setTrainingLinkedUserId(trainingLinkedUserId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long primaryUserId = (Long)attributes.get("primaryUserId");

		if (primaryUserId != null) {
			setPrimaryUserId(primaryUserId);
		}
	}

	/**
	* Returns the primary key of this training linked user.
	*
	* @return the primary key of this training linked user
	*/
	public long getPrimaryKey() {
		return _trainingLinkedUser.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training linked user.
	*
	* @param primaryKey the primary key of this training linked user
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingLinkedUser.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training linked user ID of this training linked user.
	*
	* @return the training linked user ID of this training linked user
	*/
	public long getTrainingLinkedUserId() {
		return _trainingLinkedUser.getTrainingLinkedUserId();
	}

	/**
	* Sets the training linked user ID of this training linked user.
	*
	* @param trainingLinkedUserId the training linked user ID of this training linked user
	*/
	public void setTrainingLinkedUserId(long trainingLinkedUserId) {
		_trainingLinkedUser.setTrainingLinkedUserId(trainingLinkedUserId);
	}

	/**
	* Returns the training linked user uuid of this training linked user.
	*
	* @return the training linked user uuid of this training linked user
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getTrainingLinkedUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUser.getTrainingLinkedUserUuid();
	}

	/**
	* Sets the training linked user uuid of this training linked user.
	*
	* @param trainingLinkedUserUuid the training linked user uuid of this training linked user
	*/
	public void setTrainingLinkedUserUuid(
		java.lang.String trainingLinkedUserUuid) {
		_trainingLinkedUser.setTrainingLinkedUserUuid(trainingLinkedUserUuid);
	}

	/**
	* Returns the user ID of this training linked user.
	*
	* @return the user ID of this training linked user
	*/
	public long getUserId() {
		return _trainingLinkedUser.getUserId();
	}

	/**
	* Sets the user ID of this training linked user.
	*
	* @param userId the user ID of this training linked user
	*/
	public void setUserId(long userId) {
		_trainingLinkedUser.setUserId(userId);
	}

	/**
	* Returns the user uuid of this training linked user.
	*
	* @return the user uuid of this training linked user
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUser.getUserUuid();
	}

	/**
	* Sets the user uuid of this training linked user.
	*
	* @param userUuid the user uuid of this training linked user
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_trainingLinkedUser.setUserUuid(userUuid);
	}

	/**
	* Returns the primary user ID of this training linked user.
	*
	* @return the primary user ID of this training linked user
	*/
	public long getPrimaryUserId() {
		return _trainingLinkedUser.getPrimaryUserId();
	}

	/**
	* Sets the primary user ID of this training linked user.
	*
	* @param primaryUserId the primary user ID of this training linked user
	*/
	public void setPrimaryUserId(long primaryUserId) {
		_trainingLinkedUser.setPrimaryUserId(primaryUserId);
	}

	/**
	* Returns the primary user uuid of this training linked user.
	*
	* @return the primary user uuid of this training linked user
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getPrimaryUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUser.getPrimaryUserUuid();
	}

	/**
	* Sets the primary user uuid of this training linked user.
	*
	* @param primaryUserUuid the primary user uuid of this training linked user
	*/
	public void setPrimaryUserUuid(java.lang.String primaryUserUuid) {
		_trainingLinkedUser.setPrimaryUserUuid(primaryUserUuid);
	}

	public boolean isNew() {
		return _trainingLinkedUser.isNew();
	}

	public void setNew(boolean n) {
		_trainingLinkedUser.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingLinkedUser.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingLinkedUser.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingLinkedUser.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingLinkedUser.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingLinkedUser.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingLinkedUser.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingLinkedUser.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingLinkedUserWrapper((TrainingLinkedUser)_trainingLinkedUser.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser) {
		return _trainingLinkedUser.compareTo(trainingLinkedUser);
	}

	@Override
	public int hashCode() {
		return _trainingLinkedUser.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingLinkedUser> toCacheModel() {
		return _trainingLinkedUser.toCacheModel();
	}

	public com.liferay.osb.model.TrainingLinkedUser toEscapedModel() {
		return new TrainingLinkedUserWrapper(_trainingLinkedUser.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingLinkedUser toUnescapedModel() {
		return new TrainingLinkedUserWrapper(_trainingLinkedUser.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingLinkedUser.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingLinkedUser.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingLinkedUser.persist();
	}

	public com.liferay.portal.model.User getPrimaryUser()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUser.getPrimaryUser();
	}

	public com.liferay.portal.model.User getUser()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingLinkedUser.getUser();
	}

	public boolean isPrimaryUser() {
		return _trainingLinkedUser.isPrimaryUser();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingLinkedUserWrapper)) {
			return false;
		}

		TrainingLinkedUserWrapper trainingLinkedUserWrapper = (TrainingLinkedUserWrapper)obj;

		if (Validator.equals(_trainingLinkedUser,
					trainingLinkedUserWrapper._trainingLinkedUser)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingLinkedUser getWrappedTrainingLinkedUser() {
		return _trainingLinkedUser;
	}

	public TrainingLinkedUser getWrappedModel() {
		return _trainingLinkedUser;
	}

	public void resetOriginalValues() {
		_trainingLinkedUser.resetOriginalValues();
	}

	private TrainingLinkedUser _trainingLinkedUser;
}