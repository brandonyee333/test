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

package com.liferay.watson.model;

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
 * This class is a wrapper for {@link WatsonRelationship}.
 * </p>
 *
 * @author Eddie Olson
 * @see WatsonRelationship
 * @generated
 */
@ProviderType
public class WatsonRelationshipWrapper implements WatsonRelationship,
	ModelWrapper<WatsonRelationship> {
	public WatsonRelationshipWrapper(WatsonRelationship watsonRelationship) {
		_watsonRelationship = watsonRelationship;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonRelationship.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonRelationship.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonRelationshipId", getWatsonRelationshipId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("classNameId1", getClassNameId1());
		attributes.put("classPK1", getClassPK1());
		attributes.put("classNameId2", getClassNameId2());
		attributes.put("classPK2", getClassPK2());
		attributes.put("description", getDescription());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonRelationshipId = (Long)attributes.get("watsonRelationshipId");

		if (watsonRelationshipId != null) {
			setWatsonRelationshipId(watsonRelationshipId);
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

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long classNameId1 = (Long)attributes.get("classNameId1");

		if (classNameId1 != null) {
			setClassNameId1(classNameId1);
		}

		Long classPK1 = (Long)attributes.get("classPK1");

		if (classPK1 != null) {
			setClassPK1(classPK1);
		}

		Long classNameId2 = (Long)attributes.get("classNameId2");

		if (classNameId2 != null) {
			setClassNameId2(classNameId2);
		}

		Long classPK2 = (Long)attributes.get("classPK2");

		if (classPK2 != null) {
			setClassPK2(classPK2);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WatsonRelationshipWrapper((WatsonRelationship)_watsonRelationship.clone());
	}

	@Override
	public int compareTo(WatsonRelationship watsonRelationship) {
		return _watsonRelationship.compareTo(watsonRelationship);
	}

	/**
	* Returns the class name id1 of this watson relationship.
	*
	* @return the class name id1 of this watson relationship
	*/
	@Override
	public long getClassNameId1() {
		return _watsonRelationship.getClassNameId1();
	}

	/**
	* Returns the class name id2 of this watson relationship.
	*
	* @return the class name id2 of this watson relationship
	*/
	@Override
	public long getClassNameId2() {
		return _watsonRelationship.getClassNameId2();
	}

	/**
	* Returns the class pk1 of this watson relationship.
	*
	* @return the class pk1 of this watson relationship
	*/
	@Override
	public long getClassPK1() {
		return _watsonRelationship.getClassPK1();
	}

	/**
	* Returns the class pk2 of this watson relationship.
	*
	* @return the class pk2 of this watson relationship
	*/
	@Override
	public long getClassPK2() {
		return _watsonRelationship.getClassPK2();
	}

	/**
	* Returns the company ID of this watson relationship.
	*
	* @return the company ID of this watson relationship
	*/
	@Override
	public long getCompanyId() {
		return _watsonRelationship.getCompanyId();
	}

	/**
	* Returns the create date of this watson relationship.
	*
	* @return the create date of this watson relationship
	*/
	@Override
	public Date getCreateDate() {
		return _watsonRelationship.getCreateDate();
	}

	/**
	* Returns the description of this watson relationship.
	*
	* @return the description of this watson relationship
	*/
	@Override
	public java.lang.String getDescription() {
		return _watsonRelationship.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonRelationship.getExpandoBridge();
	}

	/**
	* Returns the modified date of this watson relationship.
	*
	* @return the modified date of this watson relationship
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonRelationship.getModifiedDate();
	}

	/**
	* Returns the primary key of this watson relationship.
	*
	* @return the primary key of this watson relationship
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonRelationship.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonRelationship.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this watson relationship.
	*
	* @return the status of this watson relationship
	*/
	@Override
	public int getStatus() {
		return _watsonRelationship.getStatus();
	}

	/**
	* Returns the type watson list type ID of this watson relationship.
	*
	* @return the type watson list type ID of this watson relationship
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonRelationship.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson relationship.
	*
	* @return the user ID of this watson relationship
	*/
	@Override
	public long getUserId() {
		return _watsonRelationship.getUserId();
	}

	/**
	* Returns the user name of this watson relationship.
	*
	* @return the user name of this watson relationship
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonRelationship.getUserName();
	}

	/**
	* Returns the user uuid of this watson relationship.
	*
	* @return the user uuid of this watson relationship
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonRelationship.getUserUuid();
	}

	/**
	* Returns the watson incident ID of this watson relationship.
	*
	* @return the watson incident ID of this watson relationship
	*/
	@Override
	public long getWatsonIncidentId() {
		return _watsonRelationship.getWatsonIncidentId();
	}

	/**
	* Returns the watson relationship ID of this watson relationship.
	*
	* @return the watson relationship ID of this watson relationship
	*/
	@Override
	public long getWatsonRelationshipId() {
		return _watsonRelationship.getWatsonRelationshipId();
	}

	@Override
	public int hashCode() {
		return _watsonRelationship.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonRelationship.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonRelationship.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonRelationship.isNew();
	}

	@Override
	public void persist() {
		_watsonRelationship.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonRelationship.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name id1 of this watson relationship.
	*
	* @param classNameId1 the class name id1 of this watson relationship
	*/
	@Override
	public void setClassNameId1(long classNameId1) {
		_watsonRelationship.setClassNameId1(classNameId1);
	}

	/**
	* Sets the class name id2 of this watson relationship.
	*
	* @param classNameId2 the class name id2 of this watson relationship
	*/
	@Override
	public void setClassNameId2(long classNameId2) {
		_watsonRelationship.setClassNameId2(classNameId2);
	}

	/**
	* Sets the class pk1 of this watson relationship.
	*
	* @param classPK1 the class pk1 of this watson relationship
	*/
	@Override
	public void setClassPK1(long classPK1) {
		_watsonRelationship.setClassPK1(classPK1);
	}

	/**
	* Sets the class pk2 of this watson relationship.
	*
	* @param classPK2 the class pk2 of this watson relationship
	*/
	@Override
	public void setClassPK2(long classPK2) {
		_watsonRelationship.setClassPK2(classPK2);
	}

	/**
	* Sets the company ID of this watson relationship.
	*
	* @param companyId the company ID of this watson relationship
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonRelationship.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson relationship.
	*
	* @param createDate the create date of this watson relationship
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonRelationship.setCreateDate(createDate);
	}

	/**
	* Sets the description of this watson relationship.
	*
	* @param description the description of this watson relationship
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_watsonRelationship.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonRelationship.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonRelationship.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonRelationship.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this watson relationship.
	*
	* @param modifiedDate the modified date of this watson relationship
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonRelationship.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonRelationship.setNew(n);
	}

	/**
	* Sets the primary key of this watson relationship.
	*
	* @param primaryKey the primary key of this watson relationship
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonRelationship.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonRelationship.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this watson relationship.
	*
	* @param status the status of this watson relationship
	*/
	@Override
	public void setStatus(int status) {
		_watsonRelationship.setStatus(status);
	}

	/**
	* Sets the type watson list type ID of this watson relationship.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson relationship
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonRelationship.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson relationship.
	*
	* @param userId the user ID of this watson relationship
	*/
	@Override
	public void setUserId(long userId) {
		_watsonRelationship.setUserId(userId);
	}

	/**
	* Sets the user name of this watson relationship.
	*
	* @param userName the user name of this watson relationship
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonRelationship.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson relationship.
	*
	* @param userUuid the user uuid of this watson relationship
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonRelationship.setUserUuid(userUuid);
	}

	/**
	* Sets the watson incident ID of this watson relationship.
	*
	* @param watsonIncidentId the watson incident ID of this watson relationship
	*/
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonRelationship.setWatsonIncidentId(watsonIncidentId);
	}

	/**
	* Sets the watson relationship ID of this watson relationship.
	*
	* @param watsonRelationshipId the watson relationship ID of this watson relationship
	*/
	@Override
	public void setWatsonRelationshipId(long watsonRelationshipId) {
		_watsonRelationship.setWatsonRelationshipId(watsonRelationshipId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonRelationship> toCacheModel() {
		return _watsonRelationship.toCacheModel();
	}

	@Override
	public WatsonRelationship toEscapedModel() {
		return new WatsonRelationshipWrapper(_watsonRelationship.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonRelationship.toString();
	}

	@Override
	public WatsonRelationship toUnescapedModel() {
		return new WatsonRelationshipWrapper(_watsonRelationship.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonRelationship.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonRelationshipWrapper)) {
			return false;
		}

		WatsonRelationshipWrapper watsonRelationshipWrapper = (WatsonRelationshipWrapper)obj;

		if (Objects.equals(_watsonRelationship,
					watsonRelationshipWrapper._watsonRelationship)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonRelationship getWrappedModel() {
		return _watsonRelationship;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonRelationship.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonRelationship.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonRelationship.resetOriginalValues();
	}

	private final WatsonRelationship _watsonRelationship;
}