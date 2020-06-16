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

package com.liferay.osb.loop.model;

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
 * This class is a wrapper for {@link LoopDivision}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopDivision
 * @generated
 */
public class LoopDivisionWrapper
	implements LoopDivision, ModelWrapper<LoopDivision> {

	public LoopDivisionWrapper(LoopDivision loopDivision) {
		_loopDivision = loopDivision;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopDivision.class;
	}

	@Override
	public String getModelClassName() {
		return LoopDivision.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopDivisionId", getLoopDivisionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("organizationId", getOrganizationId());
		attributes.put("parentLoopDivisionId", getParentLoopDivisionId());
		attributes.put("type", getType());
		attributes.put("subtype", getSubtype());
		attributes.put("extraData", getExtraData());
		attributes.put("imagePayload", getImagePayload());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopDivisionId = (Long)attributes.get("loopDivisionId");

		if (loopDivisionId != null) {
			setLoopDivisionId(loopDivisionId);
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

		Long organizationId = (Long)attributes.get("organizationId");

		if (organizationId != null) {
			setOrganizationId(organizationId);
		}

		Long parentLoopDivisionId = (Long)attributes.get(
			"parentLoopDivisionId");

		if (parentLoopDivisionId != null) {
			setParentLoopDivisionId(parentLoopDivisionId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer subtype = (Integer)attributes.get("subtype");

		if (subtype != null) {
			setSubtype(subtype);
		}

		String extraData = (String)attributes.get("extraData");

		if (extraData != null) {
			setExtraData(extraData);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}
	}

	@Override
	public Object clone() {
		return new LoopDivisionWrapper((LoopDivision)_loopDivision.clone());
	}

	@Override
	public int compareTo(LoopDivision loopDivision) {
		return _loopDivision.compareTo(loopDivision);
	}

	/**
	 * Returns the company ID of this loop division.
	 *
	 * @return the company ID of this loop division
	 */
	@Override
	public long getCompanyId() {
		return _loopDivision.getCompanyId();
	}

	/**
	 * Returns the create date of this loop division.
	 *
	 * @return the create date of this loop division
	 */
	@Override
	public Date getCreateDate() {
		return _loopDivision.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopDivision.getExpandoBridge();
	}

	/**
	 * Returns the extra data of this loop division.
	 *
	 * @return the extra data of this loop division
	 */
	@Override
	public String getExtraData() {
		return _loopDivision.getExtraData();
	}

	/**
	 * Returns the image payload of this loop division.
	 *
	 * @return the image payload of this loop division
	 */
	@Override
	public String getImagePayload() {
		return _loopDivision.getImagePayload();
	}

	/**
	 * Returns the loop division ID of this loop division.
	 *
	 * @return the loop division ID of this loop division
	 */
	@Override
	public long getLoopDivisionId() {
		return _loopDivision.getLoopDivisionId();
	}

	/**
	 * Returns the modified date of this loop division.
	 *
	 * @return the modified date of this loop division
	 */
	@Override
	public Date getModifiedDate() {
		return _loopDivision.getModifiedDate();
	}

	/**
	 * Returns the organization ID of this loop division.
	 *
	 * @return the organization ID of this loop division
	 */
	@Override
	public long getOrganizationId() {
		return _loopDivision.getOrganizationId();
	}

	/**
	 * Returns the parent loop division ID of this loop division.
	 *
	 * @return the parent loop division ID of this loop division
	 */
	@Override
	public long getParentLoopDivisionId() {
		return _loopDivision.getParentLoopDivisionId();
	}

	/**
	 * Returns the primary key of this loop division.
	 *
	 * @return the primary key of this loop division
	 */
	@Override
	public long getPrimaryKey() {
		return _loopDivision.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopDivision.getPrimaryKeyObj();
	}

	/**
	 * Returns the subtype of this loop division.
	 *
	 * @return the subtype of this loop division
	 */
	@Override
	public int getSubtype() {
		return _loopDivision.getSubtype();
	}

	/**
	 * Returns the type of this loop division.
	 *
	 * @return the type of this loop division
	 */
	@Override
	public int getType() {
		return _loopDivision.getType();
	}

	/**
	 * Returns the user ID of this loop division.
	 *
	 * @return the user ID of this loop division
	 */
	@Override
	public long getUserId() {
		return _loopDivision.getUserId();
	}

	/**
	 * Returns the user name of this loop division.
	 *
	 * @return the user name of this loop division
	 */
	@Override
	public String getUserName() {
		return _loopDivision.getUserName();
	}

	/**
	 * Returns the user uuid of this loop division.
	 *
	 * @return the user uuid of this loop division
	 */
	@Override
	public String getUserUuid() {
		return _loopDivision.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _loopDivision.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopDivision.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopDivision.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopDivision.isNew();
	}

	@Override
	public void persist() {
		_loopDivision.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopDivision.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this loop division.
	 *
	 * @param companyId the company ID of this loop division
	 */
	@Override
	public void setCompanyId(long companyId) {
		_loopDivision.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this loop division.
	 *
	 * @param createDate the create date of this loop division
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_loopDivision.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_loopDivision.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopDivision.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopDivision.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the extra data of this loop division.
	 *
	 * @param extraData the extra data of this loop division
	 */
	@Override
	public void setExtraData(String extraData) {
		_loopDivision.setExtraData(extraData);
	}

	/**
	 * Sets the image payload of this loop division.
	 *
	 * @param imagePayload the image payload of this loop division
	 */
	@Override
	public void setImagePayload(String imagePayload) {
		_loopDivision.setImagePayload(imagePayload);
	}

	/**
	 * Sets the loop division ID of this loop division.
	 *
	 * @param loopDivisionId the loop division ID of this loop division
	 */
	@Override
	public void setLoopDivisionId(long loopDivisionId) {
		_loopDivision.setLoopDivisionId(loopDivisionId);
	}

	/**
	 * Sets the modified date of this loop division.
	 *
	 * @param modifiedDate the modified date of this loop division
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_loopDivision.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_loopDivision.setNew(n);
	}

	/**
	 * Sets the organization ID of this loop division.
	 *
	 * @param organizationId the organization ID of this loop division
	 */
	@Override
	public void setOrganizationId(long organizationId) {
		_loopDivision.setOrganizationId(organizationId);
	}

	/**
	 * Sets the parent loop division ID of this loop division.
	 *
	 * @param parentLoopDivisionId the parent loop division ID of this loop division
	 */
	@Override
	public void setParentLoopDivisionId(long parentLoopDivisionId) {
		_loopDivision.setParentLoopDivisionId(parentLoopDivisionId);
	}

	/**
	 * Sets the primary key of this loop division.
	 *
	 * @param primaryKey the primary key of this loop division
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopDivision.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopDivision.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the subtype of this loop division.
	 *
	 * @param subtype the subtype of this loop division
	 */
	@Override
	public void setSubtype(int subtype) {
		_loopDivision.setSubtype(subtype);
	}

	/**
	 * Sets the type of this loop division.
	 *
	 * @param type the type of this loop division
	 */
	@Override
	public void setType(int type) {
		_loopDivision.setType(type);
	}

	/**
	 * Sets the user ID of this loop division.
	 *
	 * @param userId the user ID of this loop division
	 */
	@Override
	public void setUserId(long userId) {
		_loopDivision.setUserId(userId);
	}

	/**
	 * Sets the user name of this loop division.
	 *
	 * @param userName the user name of this loop division
	 */
	@Override
	public void setUserName(String userName) {
		_loopDivision.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this loop division.
	 *
	 * @param userUuid the user uuid of this loop division
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_loopDivision.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopDivision>
		toCacheModel() {

		return _loopDivision.toCacheModel();
	}

	@Override
	public LoopDivision toEscapedModel() {
		return new LoopDivisionWrapper(_loopDivision.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopDivision.toString();
	}

	@Override
	public LoopDivision toUnescapedModel() {
		return new LoopDivisionWrapper(_loopDivision.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopDivision.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopDivisionWrapper)) {
			return false;
		}

		LoopDivisionWrapper loopDivisionWrapper = (LoopDivisionWrapper)obj;

		if (Objects.equals(_loopDivision, loopDivisionWrapper._loopDivision)) {
			return true;
		}

		return false;
	}

	@Override
	public LoopDivision getWrappedModel() {
		return _loopDivision;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopDivision.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopDivision.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopDivision.resetOriginalValues();
	}

	private final LoopDivision _loopDivision;

}