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

package com.liferay.osb.loop.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LoopPerson}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopPerson
 * @generated
 */
@ProviderType
public class LoopPersonWrapper implements LoopPerson, ModelWrapper<LoopPerson> {
	public LoopPersonWrapper(LoopPerson loopPerson) {
		_loopPerson = loopPerson;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopPerson.class;
	}

	@Override
	public String getModelClassName() {
		return LoopPerson.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopPersonId", getLoopPersonId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("loopJobTitleId", getLoopJobTitleId());
		attributes.put("managerLoopPersonId", getManagerLoopPersonId());
		attributes.put("personUserId", getPersonUserId());
		attributes.put("extraData", getExtraData());
		attributes.put("groupedUserNotificationEventsCount",
			getGroupedUserNotificationEventsCount());
		attributes.put("imagePayload", getImagePayload());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopPersonId = (Long)attributes.get("loopPersonId");

		if (loopPersonId != null) {
			setLoopPersonId(loopPersonId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		Long loopJobTitleId = (Long)attributes.get("loopJobTitleId");

		if (loopJobTitleId != null) {
			setLoopJobTitleId(loopJobTitleId);
		}

		Long managerLoopPersonId = (Long)attributes.get("managerLoopPersonId");

		if (managerLoopPersonId != null) {
			setManagerLoopPersonId(managerLoopPersonId);
		}

		Long personUserId = (Long)attributes.get("personUserId");

		if (personUserId != null) {
			setPersonUserId(personUserId);
		}

		String extraData = (String)attributes.get("extraData");

		if (extraData != null) {
			setExtraData(extraData);
		}

		Integer groupedUserNotificationEventsCount = (Integer)attributes.get(
				"groupedUserNotificationEventsCount");

		if (groupedUserNotificationEventsCount != null) {
			setGroupedUserNotificationEventsCount(groupedUserNotificationEventsCount);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}
	}

	@Override
	public Object clone() {
		return new LoopPersonWrapper((LoopPerson)_loopPerson.clone());
	}

	@Override
	public int compareTo(LoopPerson loopPerson) {
		return _loopPerson.compareTo(loopPerson);
	}

	/**
	* Returns the company ID of this loop person.
	*
	* @return the company ID of this loop person
	*/
	@Override
	public long getCompanyId() {
		return _loopPerson.getCompanyId();
	}

	/**
	* Returns the create date of this loop person.
	*
	* @return the create date of this loop person
	*/
	@Override
	public Date getCreateDate() {
		return _loopPerson.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopPerson.getExpandoBridge();
	}

	/**
	* Returns the extra data of this loop person.
	*
	* @return the extra data of this loop person
	*/
	@Override
	public String getExtraData() {
		return _loopPerson.getExtraData();
	}

	/**
	* Returns the grouped user notification events count of this loop person.
	*
	* @return the grouped user notification events count of this loop person
	*/
	@Override
	public int getGroupedUserNotificationEventsCount() {
		return _loopPerson.getGroupedUserNotificationEventsCount();
	}

	/**
	* Returns the image payload of this loop person.
	*
	* @return the image payload of this loop person
	*/
	@Override
	public String getImagePayload() {
		return _loopPerson.getImagePayload();
	}

	/**
	* Returns the loop job title ID of this loop person.
	*
	* @return the loop job title ID of this loop person
	*/
	@Override
	public long getLoopJobTitleId() {
		return _loopPerson.getLoopJobTitleId();
	}

	/**
	* Returns the loop person ID of this loop person.
	*
	* @return the loop person ID of this loop person
	*/
	@Override
	public long getLoopPersonId() {
		return _loopPerson.getLoopPersonId();
	}

	/**
	* Returns the manager loop person ID of this loop person.
	*
	* @return the manager loop person ID of this loop person
	*/
	@Override
	public long getManagerLoopPersonId() {
		return _loopPerson.getManagerLoopPersonId();
	}

	/**
	* Returns the modified date of this loop person.
	*
	* @return the modified date of this loop person
	*/
	@Override
	public Date getModifiedDate() {
		return _loopPerson.getModifiedDate();
	}

	/**
	* Returns the person user ID of this loop person.
	*
	* @return the person user ID of this loop person
	*/
	@Override
	public long getPersonUserId() {
		return _loopPerson.getPersonUserId();
	}

	/**
	* Returns the person user uuid of this loop person.
	*
	* @return the person user uuid of this loop person
	*/
	@Override
	public String getPersonUserUuid() {
		return _loopPerson.getPersonUserUuid();
	}

	/**
	* Returns the primary key of this loop person.
	*
	* @return the primary key of this loop person
	*/
	@Override
	public long getPrimaryKey() {
		return _loopPerson.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopPerson.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this loop person.
	*
	* @return the user ID of this loop person
	*/
	@Override
	public long getUserId() {
		return _loopPerson.getUserId();
	}

	/**
	* Returns the user name of this loop person.
	*
	* @return the user name of this loop person
	*/
	@Override
	public String getUserName() {
		return _loopPerson.getUserName();
	}

	/**
	* Returns the user uuid of this loop person.
	*
	* @return the user uuid of this loop person
	*/
	@Override
	public String getUserUuid() {
		return _loopPerson.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _loopPerson.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopPerson.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopPerson.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopPerson.isNew();
	}

	@Override
	public void persist() {
		_loopPerson.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopPerson.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this loop person.
	*
	* @param companyId the company ID of this loop person
	*/
	@Override
	public void setCompanyId(long companyId) {
		_loopPerson.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this loop person.
	*
	* @param createDate the create date of this loop person
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_loopPerson.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_loopPerson.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopPerson.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopPerson.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the extra data of this loop person.
	*
	* @param extraData the extra data of this loop person
	*/
	@Override
	public void setExtraData(String extraData) {
		_loopPerson.setExtraData(extraData);
	}

	/**
	* Sets the grouped user notification events count of this loop person.
	*
	* @param groupedUserNotificationEventsCount the grouped user notification events count of this loop person
	*/
	@Override
	public void setGroupedUserNotificationEventsCount(
		int groupedUserNotificationEventsCount) {
		_loopPerson.setGroupedUserNotificationEventsCount(groupedUserNotificationEventsCount);
	}

	/**
	* Sets the image payload of this loop person.
	*
	* @param imagePayload the image payload of this loop person
	*/
	@Override
	public void setImagePayload(String imagePayload) {
		_loopPerson.setImagePayload(imagePayload);
	}

	/**
	* Sets the loop job title ID of this loop person.
	*
	* @param loopJobTitleId the loop job title ID of this loop person
	*/
	@Override
	public void setLoopJobTitleId(long loopJobTitleId) {
		_loopPerson.setLoopJobTitleId(loopJobTitleId);
	}

	/**
	* Sets the loop person ID of this loop person.
	*
	* @param loopPersonId the loop person ID of this loop person
	*/
	@Override
	public void setLoopPersonId(long loopPersonId) {
		_loopPerson.setLoopPersonId(loopPersonId);
	}

	/**
	* Sets the manager loop person ID of this loop person.
	*
	* @param managerLoopPersonId the manager loop person ID of this loop person
	*/
	@Override
	public void setManagerLoopPersonId(long managerLoopPersonId) {
		_loopPerson.setManagerLoopPersonId(managerLoopPersonId);
	}

	/**
	* Sets the modified date of this loop person.
	*
	* @param modifiedDate the modified date of this loop person
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_loopPerson.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_loopPerson.setNew(n);
	}

	/**
	* Sets the person user ID of this loop person.
	*
	* @param personUserId the person user ID of this loop person
	*/
	@Override
	public void setPersonUserId(long personUserId) {
		_loopPerson.setPersonUserId(personUserId);
	}

	/**
	* Sets the person user uuid of this loop person.
	*
	* @param personUserUuid the person user uuid of this loop person
	*/
	@Override
	public void setPersonUserUuid(String personUserUuid) {
		_loopPerson.setPersonUserUuid(personUserUuid);
	}

	/**
	* Sets the primary key of this loop person.
	*
	* @param primaryKey the primary key of this loop person
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopPerson.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopPerson.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this loop person.
	*
	* @param userId the user ID of this loop person
	*/
	@Override
	public void setUserId(long userId) {
		_loopPerson.setUserId(userId);
	}

	/**
	* Sets the user name of this loop person.
	*
	* @param userName the user name of this loop person
	*/
	@Override
	public void setUserName(String userName) {
		_loopPerson.setUserName(userName);
	}

	/**
	* Sets the user uuid of this loop person.
	*
	* @param userUuid the user uuid of this loop person
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_loopPerson.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopPerson> toCacheModel() {
		return _loopPerson.toCacheModel();
	}

	@Override
	public LoopPerson toEscapedModel() {
		return new LoopPersonWrapper(_loopPerson.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopPerson.toString();
	}

	@Override
	public LoopPerson toUnescapedModel() {
		return new LoopPersonWrapper(_loopPerson.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopPerson.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopPersonWrapper)) {
			return false;
		}

		LoopPersonWrapper loopPersonWrapper = (LoopPersonWrapper)obj;

		if (Objects.equals(_loopPerson, loopPersonWrapper._loopPerson)) {
			return true;
		}

		return false;
	}

	@Override
	public LoopPerson getWrappedModel() {
		return _loopPerson;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopPerson.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopPerson.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopPerson.resetOriginalValues();
	}

	private final LoopPerson _loopPerson;
}