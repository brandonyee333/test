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
 * This class is a wrapper for {@link WatsonIncidentRel}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonIncidentRel
 * @generated
 */
@ProviderType
public class WatsonIncidentRelWrapper
	implements WatsonIncidentRel, ModelWrapper<WatsonIncidentRel> {

	public WatsonIncidentRelWrapper(WatsonIncidentRel watsonIncidentRel) {
		_watsonIncidentRel = watsonIncidentRel;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonIncidentRel.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonIncidentRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonIncidentRelId", getWatsonIncidentRelId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("watsonIncidentId1", getWatsonIncidentId1());
		attributes.put("watsonIncidentId2", getWatsonIncidentId2());
		attributes.put("type", getType());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonIncidentRelId = (Long)attributes.get("watsonIncidentRelId");

		if (watsonIncidentRelId != null) {
			setWatsonIncidentRelId(watsonIncidentRelId);
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
	public Object clone() {
		return new WatsonIncidentRelWrapper(
			(WatsonIncidentRel)_watsonIncidentRel.clone());
	}

	@Override
	public int compareTo(WatsonIncidentRel watsonIncidentRel) {
		return _watsonIncidentRel.compareTo(watsonIncidentRel);
	}

	/**
	 * Returns the company ID of this watson incident rel.
	 *
	 * @return the company ID of this watson incident rel
	 */
	@Override
	public long getCompanyId() {
		return _watsonIncidentRel.getCompanyId();
	}

	/**
	 * Returns the create date of this watson incident rel.
	 *
	 * @return the create date of this watson incident rel
	 */
	@Override
	public Date getCreateDate() {
		return _watsonIncidentRel.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonIncidentRel.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this watson incident rel.
	 *
	 * @return the group ID of this watson incident rel
	 */
	@Override
	public long getGroupId() {
		return _watsonIncidentRel.getGroupId();
	}

	/**
	 * Returns the modified date of this watson incident rel.
	 *
	 * @return the modified date of this watson incident rel
	 */
	@Override
	public Date getModifiedDate() {
		return _watsonIncidentRel.getModifiedDate();
	}

	/**
	 * Returns the primary key of this watson incident rel.
	 *
	 * @return the primary key of this watson incident rel
	 */
	@Override
	public long getPrimaryKey() {
		return _watsonIncidentRel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonIncidentRel.getPrimaryKeyObj();
	}

	/**
	 * Returns the status of this watson incident rel.
	 *
	 * @return the status of this watson incident rel
	 */
	@Override
	public int getStatus() {
		return _watsonIncidentRel.getStatus();
	}

	/**
	 * Returns the type of this watson incident rel.
	 *
	 * @return the type of this watson incident rel
	 */
	@Override
	public String getType() {
		return _watsonIncidentRel.getType();
	}

	/**
	 * Returns the user ID of this watson incident rel.
	 *
	 * @return the user ID of this watson incident rel
	 */
	@Override
	public long getUserId() {
		return _watsonIncidentRel.getUserId();
	}

	/**
	 * Returns the user name of this watson incident rel.
	 *
	 * @return the user name of this watson incident rel
	 */
	@Override
	public String getUserName() {
		return _watsonIncidentRel.getUserName();
	}

	/**
	 * Returns the user uuid of this watson incident rel.
	 *
	 * @return the user uuid of this watson incident rel
	 */
	@Override
	public String getUserUuid() {
		return _watsonIncidentRel.getUserUuid();
	}

	/**
	 * Returns the watson incident id1 of this watson incident rel.
	 *
	 * @return the watson incident id1 of this watson incident rel
	 */
	@Override
	public long getWatsonIncidentId1() {
		return _watsonIncidentRel.getWatsonIncidentId1();
	}

	/**
	 * Returns the watson incident id2 of this watson incident rel.
	 *
	 * @return the watson incident id2 of this watson incident rel
	 */
	@Override
	public long getWatsonIncidentId2() {
		return _watsonIncidentRel.getWatsonIncidentId2();
	}

	/**
	 * Returns the watson incident rel ID of this watson incident rel.
	 *
	 * @return the watson incident rel ID of this watson incident rel
	 */
	@Override
	public long getWatsonIncidentRelId() {
		return _watsonIncidentRel.getWatsonIncidentRelId();
	}

	@Override
	public int hashCode() {
		return _watsonIncidentRel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonIncidentRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonIncidentRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonIncidentRel.isNew();
	}

	@Override
	public void persist() {
		_watsonIncidentRel.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonIncidentRel.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this watson incident rel.
	 *
	 * @param companyId the company ID of this watson incident rel
	 */
	@Override
	public void setCompanyId(long companyId) {
		_watsonIncidentRel.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this watson incident rel.
	 *
	 * @param createDate the create date of this watson incident rel
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_watsonIncidentRel.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_watsonIncidentRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonIncidentRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonIncidentRel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this watson incident rel.
	 *
	 * @param groupId the group ID of this watson incident rel
	 */
	@Override
	public void setGroupId(long groupId) {
		_watsonIncidentRel.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this watson incident rel.
	 *
	 * @param modifiedDate the modified date of this watson incident rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonIncidentRel.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonIncidentRel.setNew(n);
	}

	/**
	 * Sets the primary key of this watson incident rel.
	 *
	 * @param primaryKey the primary key of this watson incident rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonIncidentRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonIncidentRel.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the status of this watson incident rel.
	 *
	 * @param status the status of this watson incident rel
	 */
	@Override
	public void setStatus(int status) {
		_watsonIncidentRel.setStatus(status);
	}

	/**
	 * Sets the type of this watson incident rel.
	 *
	 * @param type the type of this watson incident rel
	 */
	@Override
	public void setType(String type) {
		_watsonIncidentRel.setType(type);
	}

	/**
	 * Sets the user ID of this watson incident rel.
	 *
	 * @param userId the user ID of this watson incident rel
	 */
	@Override
	public void setUserId(long userId) {
		_watsonIncidentRel.setUserId(userId);
	}

	/**
	 * Sets the user name of this watson incident rel.
	 *
	 * @param userName the user name of this watson incident rel
	 */
	@Override
	public void setUserName(String userName) {
		_watsonIncidentRel.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this watson incident rel.
	 *
	 * @param userUuid the user uuid of this watson incident rel
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_watsonIncidentRel.setUserUuid(userUuid);
	}

	/**
	 * Sets the watson incident id1 of this watson incident rel.
	 *
	 * @param watsonIncidentId1 the watson incident id1 of this watson incident rel
	 */
	@Override
	public void setWatsonIncidentId1(long watsonIncidentId1) {
		_watsonIncidentRel.setWatsonIncidentId1(watsonIncidentId1);
	}

	/**
	 * Sets the watson incident id2 of this watson incident rel.
	 *
	 * @param watsonIncidentId2 the watson incident id2 of this watson incident rel
	 */
	@Override
	public void setWatsonIncidentId2(long watsonIncidentId2) {
		_watsonIncidentRel.setWatsonIncidentId2(watsonIncidentId2);
	}

	/**
	 * Sets the watson incident rel ID of this watson incident rel.
	 *
	 * @param watsonIncidentRelId the watson incident rel ID of this watson incident rel
	 */
	@Override
	public void setWatsonIncidentRelId(long watsonIncidentRelId) {
		_watsonIncidentRel.setWatsonIncidentRelId(watsonIncidentRelId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonIncidentRel>
		toCacheModel() {

		return _watsonIncidentRel.toCacheModel();
	}

	@Override
	public WatsonIncidentRel toEscapedModel() {
		return new WatsonIncidentRelWrapper(
			_watsonIncidentRel.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonIncidentRel.toString();
	}

	@Override
	public WatsonIncidentRel toUnescapedModel() {
		return new WatsonIncidentRelWrapper(
			_watsonIncidentRel.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonIncidentRel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonIncidentRelWrapper)) {
			return false;
		}

		WatsonIncidentRelWrapper watsonIncidentRelWrapper =
			(WatsonIncidentRelWrapper)obj;

		if (Objects.equals(
				_watsonIncidentRel,
				watsonIncidentRelWrapper._watsonIncidentRel)) {

			return true;
		}

		return false;
	}

	@Override
	public WatsonIncidentRel getWrappedModel() {
		return _watsonIncidentRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonIncidentRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonIncidentRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonIncidentRel.resetOriginalValues();
	}

	private final WatsonIncidentRel _watsonIncidentRel;

}