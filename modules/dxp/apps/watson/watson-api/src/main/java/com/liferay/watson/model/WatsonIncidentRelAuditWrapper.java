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
 * This class is a wrapper for {@link WatsonIncidentRelAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonIncidentRelAudit
 * @generated
 */
@ProviderType
public class WatsonIncidentRelAuditWrapper implements WatsonIncidentRelAudit,
	ModelWrapper<WatsonIncidentRelAudit> {
	public WatsonIncidentRelAuditWrapper(
		WatsonIncidentRelAudit watsonIncidentRelAudit) {
		_watsonIncidentRelAudit = watsonIncidentRelAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonIncidentRelAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonIncidentRelAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonIncidentRelAuditId", getWatsonIncidentRelAuditId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("watsonIncidentId1", getWatsonIncidentId1());
		attributes.put("watsonIncidentId2", getWatsonIncidentId2());
		attributes.put("watsonIncidentRelId", getWatsonIncidentRelId());
		attributes.put("type", getType());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonIncidentRelAuditId = (Long)attributes.get(
				"watsonIncidentRelAuditId");

		if (watsonIncidentRelAuditId != null) {
			setWatsonIncidentRelAuditId(watsonIncidentRelAuditId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long watsonIncidentId1 = (Long)attributes.get("watsonIncidentId1");

		if (watsonIncidentId1 != null) {
			setWatsonIncidentId1(watsonIncidentId1);
		}

		Long watsonIncidentId2 = (Long)attributes.get("watsonIncidentId2");

		if (watsonIncidentId2 != null) {
			setWatsonIncidentId2(watsonIncidentId2);
		}

		Long watsonIncidentRelId = (Long)attributes.get("watsonIncidentRelId");

		if (watsonIncidentRelId != null) {
			setWatsonIncidentRelId(watsonIncidentRelId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WatsonIncidentRelAuditWrapper((WatsonIncidentRelAudit)_watsonIncidentRelAudit.clone());
	}

	@Override
	public int compareTo(WatsonIncidentRelAudit watsonIncidentRelAudit) {
		return _watsonIncidentRelAudit.compareTo(watsonIncidentRelAudit);
	}

	/**
	* Returns the company ID of this watson incident rel audit.
	*
	* @return the company ID of this watson incident rel audit
	*/
	@Override
	public long getCompanyId() {
		return _watsonIncidentRelAudit.getCompanyId();
	}

	/**
	* Returns the create date of this watson incident rel audit.
	*
	* @return the create date of this watson incident rel audit
	*/
	@Override
	public Date getCreateDate() {
		return _watsonIncidentRelAudit.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonIncidentRelAudit.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson incident rel audit.
	*
	* @return the group ID of this watson incident rel audit
	*/
	@Override
	public long getGroupId() {
		return _watsonIncidentRelAudit.getGroupId();
	}

	/**
	* Returns the modified date of this watson incident rel audit.
	*
	* @return the modified date of this watson incident rel audit
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonIncidentRelAudit.getModifiedDate();
	}

	/**
	* Returns the primary key of this watson incident rel audit.
	*
	* @return the primary key of this watson incident rel audit
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonIncidentRelAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonIncidentRelAudit.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this watson incident rel audit.
	*
	* @return the status of this watson incident rel audit
	*/
	@Override
	public int getStatus() {
		return _watsonIncidentRelAudit.getStatus();
	}

	/**
	* Returns the type of this watson incident rel audit.
	*
	* @return the type of this watson incident rel audit
	*/
	@Override
	public java.lang.String getType() {
		return _watsonIncidentRelAudit.getType();
	}

	/**
	* Returns the user ID of this watson incident rel audit.
	*
	* @return the user ID of this watson incident rel audit
	*/
	@Override
	public long getUserId() {
		return _watsonIncidentRelAudit.getUserId();
	}

	/**
	* Returns the user name of this watson incident rel audit.
	*
	* @return the user name of this watson incident rel audit
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonIncidentRelAudit.getUserName();
	}

	/**
	* Returns the user uuid of this watson incident rel audit.
	*
	* @return the user uuid of this watson incident rel audit
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonIncidentRelAudit.getUserUuid();
	}

	/**
	* Returns the watson incident id1 of this watson incident rel audit.
	*
	* @return the watson incident id1 of this watson incident rel audit
	*/
	@Override
	public long getWatsonIncidentId1() {
		return _watsonIncidentRelAudit.getWatsonIncidentId1();
	}

	/**
	* Returns the watson incident id2 of this watson incident rel audit.
	*
	* @return the watson incident id2 of this watson incident rel audit
	*/
	@Override
	public long getWatsonIncidentId2() {
		return _watsonIncidentRelAudit.getWatsonIncidentId2();
	}

	/**
	* Returns the watson incident rel audit ID of this watson incident rel audit.
	*
	* @return the watson incident rel audit ID of this watson incident rel audit
	*/
	@Override
	public long getWatsonIncidentRelAuditId() {
		return _watsonIncidentRelAudit.getWatsonIncidentRelAuditId();
	}

	/**
	* Returns the watson incident rel ID of this watson incident rel audit.
	*
	* @return the watson incident rel ID of this watson incident rel audit
	*/
	@Override
	public long getWatsonIncidentRelId() {
		return _watsonIncidentRelAudit.getWatsonIncidentRelId();
	}

	@Override
	public int hashCode() {
		return _watsonIncidentRelAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonIncidentRelAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonIncidentRelAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonIncidentRelAudit.isNew();
	}

	@Override
	public void persist() {
		_watsonIncidentRelAudit.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonIncidentRelAudit.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson incident rel audit.
	*
	* @param companyId the company ID of this watson incident rel audit
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonIncidentRelAudit.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson incident rel audit.
	*
	* @param createDate the create date of this watson incident rel audit
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonIncidentRelAudit.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonIncidentRelAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonIncidentRelAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonIncidentRelAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson incident rel audit.
	*
	* @param groupId the group ID of this watson incident rel audit
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonIncidentRelAudit.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this watson incident rel audit.
	*
	* @param modifiedDate the modified date of this watson incident rel audit
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonIncidentRelAudit.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonIncidentRelAudit.setNew(n);
	}

	/**
	* Sets the primary key of this watson incident rel audit.
	*
	* @param primaryKey the primary key of this watson incident rel audit
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonIncidentRelAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonIncidentRelAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this watson incident rel audit.
	*
	* @param status the status of this watson incident rel audit
	*/
	@Override
	public void setStatus(int status) {
		_watsonIncidentRelAudit.setStatus(status);
	}

	/**
	* Sets the type of this watson incident rel audit.
	*
	* @param type the type of this watson incident rel audit
	*/
	@Override
	public void setType(java.lang.String type) {
		_watsonIncidentRelAudit.setType(type);
	}

	/**
	* Sets the user ID of this watson incident rel audit.
	*
	* @param userId the user ID of this watson incident rel audit
	*/
	@Override
	public void setUserId(long userId) {
		_watsonIncidentRelAudit.setUserId(userId);
	}

	/**
	* Sets the user name of this watson incident rel audit.
	*
	* @param userName the user name of this watson incident rel audit
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonIncidentRelAudit.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson incident rel audit.
	*
	* @param userUuid the user uuid of this watson incident rel audit
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonIncidentRelAudit.setUserUuid(userUuid);
	}

	/**
	* Sets the watson incident id1 of this watson incident rel audit.
	*
	* @param watsonIncidentId1 the watson incident id1 of this watson incident rel audit
	*/
	@Override
	public void setWatsonIncidentId1(long watsonIncidentId1) {
		_watsonIncidentRelAudit.setWatsonIncidentId1(watsonIncidentId1);
	}

	/**
	* Sets the watson incident id2 of this watson incident rel audit.
	*
	* @param watsonIncidentId2 the watson incident id2 of this watson incident rel audit
	*/
	@Override
	public void setWatsonIncidentId2(long watsonIncidentId2) {
		_watsonIncidentRelAudit.setWatsonIncidentId2(watsonIncidentId2);
	}

	/**
	* Sets the watson incident rel audit ID of this watson incident rel audit.
	*
	* @param watsonIncidentRelAuditId the watson incident rel audit ID of this watson incident rel audit
	*/
	@Override
	public void setWatsonIncidentRelAuditId(long watsonIncidentRelAuditId) {
		_watsonIncidentRelAudit.setWatsonIncidentRelAuditId(watsonIncidentRelAuditId);
	}

	/**
	* Sets the watson incident rel ID of this watson incident rel audit.
	*
	* @param watsonIncidentRelId the watson incident rel ID of this watson incident rel audit
	*/
	@Override
	public void setWatsonIncidentRelId(long watsonIncidentRelId) {
		_watsonIncidentRelAudit.setWatsonIncidentRelId(watsonIncidentRelId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonIncidentRelAudit> toCacheModel() {
		return _watsonIncidentRelAudit.toCacheModel();
	}

	@Override
	public WatsonIncidentRelAudit toEscapedModel() {
		return new WatsonIncidentRelAuditWrapper(_watsonIncidentRelAudit.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonIncidentRelAudit.toString();
	}

	@Override
	public WatsonIncidentRelAudit toUnescapedModel() {
		return new WatsonIncidentRelAuditWrapper(_watsonIncidentRelAudit.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonIncidentRelAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonIncidentRelAuditWrapper)) {
			return false;
		}

		WatsonIncidentRelAuditWrapper watsonIncidentRelAuditWrapper = (WatsonIncidentRelAuditWrapper)obj;

		if (Objects.equals(_watsonIncidentRelAudit,
					watsonIncidentRelAuditWrapper._watsonIncidentRelAudit)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonIncidentRelAudit getWrappedModel() {
		return _watsonIncidentRelAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonIncidentRelAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonIncidentRelAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonIncidentRelAudit.resetOriginalValues();
	}

	private final WatsonIncidentRelAudit _watsonIncidentRelAudit;
}