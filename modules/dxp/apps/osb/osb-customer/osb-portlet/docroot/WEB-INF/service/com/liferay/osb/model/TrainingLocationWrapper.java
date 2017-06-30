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
 * This class is a wrapper for {@link TrainingLocation}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingLocation
 * @generated
 */
public class TrainingLocationWrapper implements TrainingLocation,
	ModelWrapper<TrainingLocation> {
	public TrainingLocationWrapper(TrainingLocation trainingLocation) {
		_trainingLocation = trainingLocation;
	}

	public Class<?> getModelClass() {
		return TrainingLocation.class;
	}

	public String getModelClassName() {
		return TrainingLocation.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingLocationId", getTrainingLocationId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("addressId", getAddressId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingLocationId = (Long)attributes.get("trainingLocationId");

		if (trainingLocationId != null) {
			setTrainingLocationId(trainingLocationId);
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

		Long addressId = (Long)attributes.get("addressId");

		if (addressId != null) {
			setAddressId(addressId);
		}
	}

	/**
	* Returns the primary key of this training location.
	*
	* @return the primary key of this training location
	*/
	public long getPrimaryKey() {
		return _trainingLocation.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training location.
	*
	* @param primaryKey the primary key of this training location
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingLocation.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training location ID of this training location.
	*
	* @return the training location ID of this training location
	*/
	public long getTrainingLocationId() {
		return _trainingLocation.getTrainingLocationId();
	}

	/**
	* Sets the training location ID of this training location.
	*
	* @param trainingLocationId the training location ID of this training location
	*/
	public void setTrainingLocationId(long trainingLocationId) {
		_trainingLocation.setTrainingLocationId(trainingLocationId);
	}

	/**
	* Returns the user ID of this training location.
	*
	* @return the user ID of this training location
	*/
	public long getUserId() {
		return _trainingLocation.getUserId();
	}

	/**
	* Sets the user ID of this training location.
	*
	* @param userId the user ID of this training location
	*/
	public void setUserId(long userId) {
		_trainingLocation.setUserId(userId);
	}

	/**
	* Returns the user uuid of this training location.
	*
	* @return the user uuid of this training location
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocation.getUserUuid();
	}

	/**
	* Sets the user uuid of this training location.
	*
	* @param userUuid the user uuid of this training location
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_trainingLocation.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this training location.
	*
	* @return the user name of this training location
	*/
	public java.lang.String getUserName() {
		return _trainingLocation.getUserName();
	}

	/**
	* Sets the user name of this training location.
	*
	* @param userName the user name of this training location
	*/
	public void setUserName(java.lang.String userName) {
		_trainingLocation.setUserName(userName);
	}

	/**
	* Returns the create date of this training location.
	*
	* @return the create date of this training location
	*/
	public java.util.Date getCreateDate() {
		return _trainingLocation.getCreateDate();
	}

	/**
	* Sets the create date of this training location.
	*
	* @param createDate the create date of this training location
	*/
	public void setCreateDate(java.util.Date createDate) {
		_trainingLocation.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this training location.
	*
	* @return the modified date of this training location
	*/
	public java.util.Date getModifiedDate() {
		return _trainingLocation.getModifiedDate();
	}

	/**
	* Sets the modified date of this training location.
	*
	* @param modifiedDate the modified date of this training location
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_trainingLocation.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this training location.
	*
	* @return the name of this training location
	*/
	public java.lang.String getName() {
		return _trainingLocation.getName();
	}

	/**
	* Sets the name of this training location.
	*
	* @param name the name of this training location
	*/
	public void setName(java.lang.String name) {
		_trainingLocation.setName(name);
	}

	/**
	* Returns the address ID of this training location.
	*
	* @return the address ID of this training location
	*/
	public long getAddressId() {
		return _trainingLocation.getAddressId();
	}

	/**
	* Sets the address ID of this training location.
	*
	* @param addressId the address ID of this training location
	*/
	public void setAddressId(long addressId) {
		_trainingLocation.setAddressId(addressId);
	}

	public boolean isNew() {
		return _trainingLocation.isNew();
	}

	public void setNew(boolean n) {
		_trainingLocation.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingLocation.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingLocation.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingLocation.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingLocation.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingLocation.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingLocation.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingLocation.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingLocationWrapper((TrainingLocation)_trainingLocation.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TrainingLocation trainingLocation) {
		return _trainingLocation.compareTo(trainingLocation);
	}

	@Override
	public int hashCode() {
		return _trainingLocation.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingLocation> toCacheModel() {
		return _trainingLocation.toCacheModel();
	}

	public com.liferay.osb.model.TrainingLocation toEscapedModel() {
		return new TrainingLocationWrapper(_trainingLocation.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingLocation toUnescapedModel() {
		return new TrainingLocationWrapper(_trainingLocation.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingLocation.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingLocation.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingLocation.persist();
	}

	public com.liferay.portal.model.Address getAddress()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingLocation.getAddress();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingLocationWrapper)) {
			return false;
		}

		TrainingLocationWrapper trainingLocationWrapper = (TrainingLocationWrapper)obj;

		if (Validator.equals(_trainingLocation,
					trainingLocationWrapper._trainingLocation)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingLocation getWrappedTrainingLocation() {
		return _trainingLocation;
	}

	public TrainingLocation getWrappedModel() {
		return _trainingLocation;
	}

	public void resetOriginalValues() {
		_trainingLocation.resetOriginalValues();
	}

	private TrainingLocation _trainingLocation;
}