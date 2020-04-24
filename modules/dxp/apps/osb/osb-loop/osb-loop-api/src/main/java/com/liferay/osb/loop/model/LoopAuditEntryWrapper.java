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
 * This class is a wrapper for {@link LoopAuditEntry}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopAuditEntry
 * @generated
 */
@ProviderType
public class LoopAuditEntryWrapper implements LoopAuditEntry,
	ModelWrapper<LoopAuditEntry> {
	public LoopAuditEntryWrapper(LoopAuditEntry loopAuditEntry) {
		_loopAuditEntry = loopAuditEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopAuditEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LoopAuditEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopAuditEntryId", getLoopAuditEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopAuditEntryId = (Long)attributes.get("loopAuditEntryId");

		if (loopAuditEntryId != null) {
			setLoopAuditEntryId(loopAuditEntryId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public Object clone() {
		return new LoopAuditEntryWrapper((LoopAuditEntry)_loopAuditEntry.clone());
	}

	@Override
	public int compareTo(LoopAuditEntry loopAuditEntry) {
		return _loopAuditEntry.compareTo(loopAuditEntry);
	}

	/**
	* Returns the fully qualified class name of this loop audit entry.
	*
	* @return the fully qualified class name of this loop audit entry
	*/
	@Override
	public String getClassName() {
		return _loopAuditEntry.getClassName();
	}

	/**
	* Returns the class name ID of this loop audit entry.
	*
	* @return the class name ID of this loop audit entry
	*/
	@Override
	public long getClassNameId() {
		return _loopAuditEntry.getClassNameId();
	}

	/**
	* Returns the class pk of this loop audit entry.
	*
	* @return the class pk of this loop audit entry
	*/
	@Override
	public long getClassPK() {
		return _loopAuditEntry.getClassPK();
	}

	/**
	* Returns the company ID of this loop audit entry.
	*
	* @return the company ID of this loop audit entry
	*/
	@Override
	public long getCompanyId() {
		return _loopAuditEntry.getCompanyId();
	}

	/**
	* Returns the create date of this loop audit entry.
	*
	* @return the create date of this loop audit entry
	*/
	@Override
	public Date getCreateDate() {
		return _loopAuditEntry.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopAuditEntry.getExpandoBridge();
	}

	/**
	* Returns the loop audit entry ID of this loop audit entry.
	*
	* @return the loop audit entry ID of this loop audit entry
	*/
	@Override
	public long getLoopAuditEntryId() {
		return _loopAuditEntry.getLoopAuditEntryId();
	}

	/**
	* Returns the modified date of this loop audit entry.
	*
	* @return the modified date of this loop audit entry
	*/
	@Override
	public Date getModifiedDate() {
		return _loopAuditEntry.getModifiedDate();
	}

	/**
	* Returns the name of this loop audit entry.
	*
	* @return the name of this loop audit entry
	*/
	@Override
	public String getName() {
		return _loopAuditEntry.getName();
	}

	/**
	* Returns the primary key of this loop audit entry.
	*
	* @return the primary key of this loop audit entry
	*/
	@Override
	public long getPrimaryKey() {
		return _loopAuditEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopAuditEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this loop audit entry.
	*
	* @return the user ID of this loop audit entry
	*/
	@Override
	public long getUserId() {
		return _loopAuditEntry.getUserId();
	}

	/**
	* Returns the user name of this loop audit entry.
	*
	* @return the user name of this loop audit entry
	*/
	@Override
	public String getUserName() {
		return _loopAuditEntry.getUserName();
	}

	/**
	* Returns the user uuid of this loop audit entry.
	*
	* @return the user uuid of this loop audit entry
	*/
	@Override
	public String getUserUuid() {
		return _loopAuditEntry.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _loopAuditEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopAuditEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopAuditEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopAuditEntry.isNew();
	}

	@Override
	public void persist() {
		_loopAuditEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopAuditEntry.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_loopAuditEntry.setClassName(className);
	}

	/**
	* Sets the class name ID of this loop audit entry.
	*
	* @param classNameId the class name ID of this loop audit entry
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_loopAuditEntry.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this loop audit entry.
	*
	* @param classPK the class pk of this loop audit entry
	*/
	@Override
	public void setClassPK(long classPK) {
		_loopAuditEntry.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this loop audit entry.
	*
	* @param companyId the company ID of this loop audit entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_loopAuditEntry.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this loop audit entry.
	*
	* @param createDate the create date of this loop audit entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_loopAuditEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_loopAuditEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopAuditEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopAuditEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the loop audit entry ID of this loop audit entry.
	*
	* @param loopAuditEntryId the loop audit entry ID of this loop audit entry
	*/
	@Override
	public void setLoopAuditEntryId(long loopAuditEntryId) {
		_loopAuditEntry.setLoopAuditEntryId(loopAuditEntryId);
	}

	/**
	* Sets the modified date of this loop audit entry.
	*
	* @param modifiedDate the modified date of this loop audit entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_loopAuditEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this loop audit entry.
	*
	* @param name the name of this loop audit entry
	*/
	@Override
	public void setName(String name) {
		_loopAuditEntry.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_loopAuditEntry.setNew(n);
	}

	/**
	* Sets the primary key of this loop audit entry.
	*
	* @param primaryKey the primary key of this loop audit entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopAuditEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopAuditEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this loop audit entry.
	*
	* @param userId the user ID of this loop audit entry
	*/
	@Override
	public void setUserId(long userId) {
		_loopAuditEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this loop audit entry.
	*
	* @param userName the user name of this loop audit entry
	*/
	@Override
	public void setUserName(String userName) {
		_loopAuditEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this loop audit entry.
	*
	* @param userUuid the user uuid of this loop audit entry
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_loopAuditEntry.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopAuditEntry> toCacheModel() {
		return _loopAuditEntry.toCacheModel();
	}

	@Override
	public LoopAuditEntry toEscapedModel() {
		return new LoopAuditEntryWrapper(_loopAuditEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopAuditEntry.toString();
	}

	@Override
	public LoopAuditEntry toUnescapedModel() {
		return new LoopAuditEntryWrapper(_loopAuditEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopAuditEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopAuditEntryWrapper)) {
			return false;
		}

		LoopAuditEntryWrapper loopAuditEntryWrapper = (LoopAuditEntryWrapper)obj;

		if (Objects.equals(_loopAuditEntry,
					loopAuditEntryWrapper._loopAuditEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public LoopAuditEntry getWrappedModel() {
		return _loopAuditEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopAuditEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopAuditEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopAuditEntry.resetOriginalValues();
	}

	private final LoopAuditEntry _loopAuditEntry;
}