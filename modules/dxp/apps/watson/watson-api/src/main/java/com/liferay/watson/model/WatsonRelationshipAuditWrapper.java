/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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
 * This class is a wrapper for {@link WatsonRelationshipAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonRelationshipAudit
 * @generated
 */
@ProviderType
public class WatsonRelationshipAuditWrapper implements WatsonRelationshipAudit,
	ModelWrapper<WatsonRelationshipAudit> {
	public WatsonRelationshipAuditWrapper(
		WatsonRelationshipAudit watsonRelationshipAudit) {
		_watsonRelationshipAudit = watsonRelationshipAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonRelationshipAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonRelationshipAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonRelationshipAuditId",
			getWatsonRelationshipAuditId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("watsonRelationshipId", getWatsonRelationshipId());
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
		Long watsonRelationshipAuditId = (Long)attributes.get(
				"watsonRelationshipAuditId");

		if (watsonRelationshipAuditId != null) {
			setWatsonRelationshipAuditId(watsonRelationshipAuditId);
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

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		Long watsonRelationshipId = (Long)attributes.get("watsonRelationshipId");

		if (watsonRelationshipId != null) {
			setWatsonRelationshipId(watsonRelationshipId);
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
	public Object clone() {
		return new WatsonRelationshipAuditWrapper((WatsonRelationshipAudit)_watsonRelationshipAudit.clone());
	}

	@Override
	public int compareTo(WatsonRelationshipAudit watsonRelationshipAudit) {
		return _watsonRelationshipAudit.compareTo(watsonRelationshipAudit);
	}

	/**
	* Returns the class name id1 of this watson relationship audit.
	*
	* @return the class name id1 of this watson relationship audit
	*/
	@Override
	public long getClassNameId1() {
		return _watsonRelationshipAudit.getClassNameId1();
	}

	/**
	* Returns the class name id2 of this watson relationship audit.
	*
	* @return the class name id2 of this watson relationship audit
	*/
	@Override
	public long getClassNameId2() {
		return _watsonRelationshipAudit.getClassNameId2();
	}

	/**
	* Returns the class pk1 of this watson relationship audit.
	*
	* @return the class pk1 of this watson relationship audit
	*/
	@Override
	public long getClassPK1() {
		return _watsonRelationshipAudit.getClassPK1();
	}

	/**
	* Returns the class pk2 of this watson relationship audit.
	*
	* @return the class pk2 of this watson relationship audit
	*/
	@Override
	public long getClassPK2() {
		return _watsonRelationshipAudit.getClassPK2();
	}

	/**
	* Returns the company ID of this watson relationship audit.
	*
	* @return the company ID of this watson relationship audit
	*/
	@Override
	public long getCompanyId() {
		return _watsonRelationshipAudit.getCompanyId();
	}

	/**
	* Returns the create date of this watson relationship audit.
	*
	* @return the create date of this watson relationship audit
	*/
	@Override
	public Date getCreateDate() {
		return _watsonRelationshipAudit.getCreateDate();
	}

	/**
	* Returns the description of this watson relationship audit.
	*
	* @return the description of this watson relationship audit
	*/
	@Override
	public String getDescription() {
		return _watsonRelationshipAudit.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonRelationshipAudit.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson relationship audit.
	*
	* @return the group ID of this watson relationship audit
	*/
	@Override
	public long getGroupId() {
		return _watsonRelationshipAudit.getGroupId();
	}

	/**
	* Returns the modified date of this watson relationship audit.
	*
	* @return the modified date of this watson relationship audit
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonRelationshipAudit.getModifiedDate();
	}

	/**
	* Returns the primary key of this watson relationship audit.
	*
	* @return the primary key of this watson relationship audit
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonRelationshipAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonRelationshipAudit.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this watson relationship audit.
	*
	* @return the status of this watson relationship audit
	*/
	@Override
	public int getStatus() {
		return _watsonRelationshipAudit.getStatus();
	}

	/**
	* Returns the type watson list type ID of this watson relationship audit.
	*
	* @return the type watson list type ID of this watson relationship audit
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonRelationshipAudit.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson relationship audit.
	*
	* @return the user ID of this watson relationship audit
	*/
	@Override
	public long getUserId() {
		return _watsonRelationshipAudit.getUserId();
	}

	/**
	* Returns the user name of this watson relationship audit.
	*
	* @return the user name of this watson relationship audit
	*/
	@Override
	public String getUserName() {
		return _watsonRelationshipAudit.getUserName();
	}

	/**
	* Returns the user uuid of this watson relationship audit.
	*
	* @return the user uuid of this watson relationship audit
	*/
	@Override
	public String getUserUuid() {
		return _watsonRelationshipAudit.getUserUuid();
	}

	/**
	* Returns the watson incident ID of this watson relationship audit.
	*
	* @return the watson incident ID of this watson relationship audit
	*/
	@Override
	public long getWatsonIncidentId() {
		return _watsonRelationshipAudit.getWatsonIncidentId();
	}

	/**
	* Returns the watson relationship audit ID of this watson relationship audit.
	*
	* @return the watson relationship audit ID of this watson relationship audit
	*/
	@Override
	public long getWatsonRelationshipAuditId() {
		return _watsonRelationshipAudit.getWatsonRelationshipAuditId();
	}

	/**
	* Returns the watson relationship ID of this watson relationship audit.
	*
	* @return the watson relationship ID of this watson relationship audit
	*/
	@Override
	public long getWatsonRelationshipId() {
		return _watsonRelationshipAudit.getWatsonRelationshipId();
	}

	@Override
	public int hashCode() {
		return _watsonRelationshipAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonRelationshipAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonRelationshipAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonRelationshipAudit.isNew();
	}

	@Override
	public void persist() {
		_watsonRelationshipAudit.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonRelationshipAudit.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name id1 of this watson relationship audit.
	*
	* @param classNameId1 the class name id1 of this watson relationship audit
	*/
	@Override
	public void setClassNameId1(long classNameId1) {
		_watsonRelationshipAudit.setClassNameId1(classNameId1);
	}

	/**
	* Sets the class name id2 of this watson relationship audit.
	*
	* @param classNameId2 the class name id2 of this watson relationship audit
	*/
	@Override
	public void setClassNameId2(long classNameId2) {
		_watsonRelationshipAudit.setClassNameId2(classNameId2);
	}

	/**
	* Sets the class pk1 of this watson relationship audit.
	*
	* @param classPK1 the class pk1 of this watson relationship audit
	*/
	@Override
	public void setClassPK1(long classPK1) {
		_watsonRelationshipAudit.setClassPK1(classPK1);
	}

	/**
	* Sets the class pk2 of this watson relationship audit.
	*
	* @param classPK2 the class pk2 of this watson relationship audit
	*/
	@Override
	public void setClassPK2(long classPK2) {
		_watsonRelationshipAudit.setClassPK2(classPK2);
	}

	/**
	* Sets the company ID of this watson relationship audit.
	*
	* @param companyId the company ID of this watson relationship audit
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonRelationshipAudit.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson relationship audit.
	*
	* @param createDate the create date of this watson relationship audit
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonRelationshipAudit.setCreateDate(createDate);
	}

	/**
	* Sets the description of this watson relationship audit.
	*
	* @param description the description of this watson relationship audit
	*/
	@Override
	public void setDescription(String description) {
		_watsonRelationshipAudit.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonRelationshipAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonRelationshipAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonRelationshipAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson relationship audit.
	*
	* @param groupId the group ID of this watson relationship audit
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonRelationshipAudit.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this watson relationship audit.
	*
	* @param modifiedDate the modified date of this watson relationship audit
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonRelationshipAudit.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonRelationshipAudit.setNew(n);
	}

	/**
	* Sets the primary key of this watson relationship audit.
	*
	* @param primaryKey the primary key of this watson relationship audit
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonRelationshipAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonRelationshipAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this watson relationship audit.
	*
	* @param status the status of this watson relationship audit
	*/
	@Override
	public void setStatus(int status) {
		_watsonRelationshipAudit.setStatus(status);
	}

	/**
	* Sets the type watson list type ID of this watson relationship audit.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson relationship audit
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonRelationshipAudit.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson relationship audit.
	*
	* @param userId the user ID of this watson relationship audit
	*/
	@Override
	public void setUserId(long userId) {
		_watsonRelationshipAudit.setUserId(userId);
	}

	/**
	* Sets the user name of this watson relationship audit.
	*
	* @param userName the user name of this watson relationship audit
	*/
	@Override
	public void setUserName(String userName) {
		_watsonRelationshipAudit.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson relationship audit.
	*
	* @param userUuid the user uuid of this watson relationship audit
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_watsonRelationshipAudit.setUserUuid(userUuid);
	}

	/**
	* Sets the watson incident ID of this watson relationship audit.
	*
	* @param watsonIncidentId the watson incident ID of this watson relationship audit
	*/
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonRelationshipAudit.setWatsonIncidentId(watsonIncidentId);
	}

	/**
	* Sets the watson relationship audit ID of this watson relationship audit.
	*
	* @param watsonRelationshipAuditId the watson relationship audit ID of this watson relationship audit
	*/
	@Override
	public void setWatsonRelationshipAuditId(long watsonRelationshipAuditId) {
		_watsonRelationshipAudit.setWatsonRelationshipAuditId(watsonRelationshipAuditId);
	}

	/**
	* Sets the watson relationship ID of this watson relationship audit.
	*
	* @param watsonRelationshipId the watson relationship ID of this watson relationship audit
	*/
	@Override
	public void setWatsonRelationshipId(long watsonRelationshipId) {
		_watsonRelationshipAudit.setWatsonRelationshipId(watsonRelationshipId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonRelationshipAudit> toCacheModel() {
		return _watsonRelationshipAudit.toCacheModel();
	}

	@Override
	public WatsonRelationshipAudit toEscapedModel() {
		return new WatsonRelationshipAuditWrapper(_watsonRelationshipAudit.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonRelationshipAudit.toString();
	}

	@Override
	public WatsonRelationshipAudit toUnescapedModel() {
		return new WatsonRelationshipAuditWrapper(_watsonRelationshipAudit.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonRelationshipAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonRelationshipAuditWrapper)) {
			return false;
		}

		WatsonRelationshipAuditWrapper watsonRelationshipAuditWrapper = (WatsonRelationshipAuditWrapper)obj;

		if (Objects.equals(_watsonRelationshipAudit,
					watsonRelationshipAuditWrapper._watsonRelationshipAudit)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonRelationshipAudit getWrappedModel() {
		return _watsonRelationshipAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonRelationshipAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonRelationshipAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonRelationshipAudit.resetOriginalValues();
	}

	private final WatsonRelationshipAudit _watsonRelationshipAudit;
}