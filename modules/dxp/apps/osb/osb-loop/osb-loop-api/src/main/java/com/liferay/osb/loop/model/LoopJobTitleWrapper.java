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
 * This class is a wrapper for {@link LoopJobTitle}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopJobTitle
 * @generated
 */
public class LoopJobTitleWrapper
	implements LoopJobTitle, ModelWrapper<LoopJobTitle> {

	public LoopJobTitleWrapper(LoopJobTitle loopJobTitle) {
		_loopJobTitle = loopJobTitle;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopJobTitle.class;
	}

	@Override
	public String getModelClassName() {
		return LoopJobTitle.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopJobTitleId", getLoopJobTitleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopJobTitleId = (Long)attributes.get("loopJobTitleId");

		if (loopJobTitleId != null) {
			setLoopJobTitleId(loopJobTitleId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
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
		return new LoopJobTitleWrapper((LoopJobTitle)_loopJobTitle.clone());
	}

	@Override
	public int compareTo(LoopJobTitle loopJobTitle) {
		return _loopJobTitle.compareTo(loopJobTitle);
	}

	/**
	 * Returns the company ID of this loop job title.
	 *
	 * @return the company ID of this loop job title
	 */
	@Override
	public long getCompanyId() {
		return _loopJobTitle.getCompanyId();
	}

	/**
	 * Returns the create date of this loop job title.
	 *
	 * @return the create date of this loop job title
	 */
	@Override
	public Date getCreateDate() {
		return _loopJobTitle.getCreateDate();
	}

	/**
	 * Returns the description of this loop job title.
	 *
	 * @return the description of this loop job title
	 */
	@Override
	public String getDescription() {
		return _loopJobTitle.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopJobTitle.getExpandoBridge();
	}

	/**
	 * Returns the loop job title ID of this loop job title.
	 *
	 * @return the loop job title ID of this loop job title
	 */
	@Override
	public long getLoopJobTitleId() {
		return _loopJobTitle.getLoopJobTitleId();
	}

	/**
	 * Returns the modified date of this loop job title.
	 *
	 * @return the modified date of this loop job title
	 */
	@Override
	public Date getModifiedDate() {
		return _loopJobTitle.getModifiedDate();
	}

	/**
	 * Returns the name of this loop job title.
	 *
	 * @return the name of this loop job title
	 */
	@Override
	public String getName() {
		return _loopJobTitle.getName();
	}

	/**
	 * Returns the primary key of this loop job title.
	 *
	 * @return the primary key of this loop job title
	 */
	@Override
	public long getPrimaryKey() {
		return _loopJobTitle.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopJobTitle.getPrimaryKeyObj();
	}

	/**
	 * Returns the status of this loop job title.
	 *
	 * @return the status of this loop job title
	 */
	@Override
	public int getStatus() {
		return _loopJobTitle.getStatus();
	}

	/**
	 * Returns the user ID of this loop job title.
	 *
	 * @return the user ID of this loop job title
	 */
	@Override
	public long getUserId() {
		return _loopJobTitle.getUserId();
	}

	/**
	 * Returns the user name of this loop job title.
	 *
	 * @return the user name of this loop job title
	 */
	@Override
	public String getUserName() {
		return _loopJobTitle.getUserName();
	}

	/**
	 * Returns the user uuid of this loop job title.
	 *
	 * @return the user uuid of this loop job title
	 */
	@Override
	public String getUserUuid() {
		return _loopJobTitle.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _loopJobTitle.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopJobTitle.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopJobTitle.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopJobTitle.isNew();
	}

	@Override
	public void persist() {
		_loopJobTitle.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopJobTitle.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this loop job title.
	 *
	 * @param companyId the company ID of this loop job title
	 */
	@Override
	public void setCompanyId(long companyId) {
		_loopJobTitle.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this loop job title.
	 *
	 * @param createDate the create date of this loop job title
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_loopJobTitle.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this loop job title.
	 *
	 * @param description the description of this loop job title
	 */
	@Override
	public void setDescription(String description) {
		_loopJobTitle.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_loopJobTitle.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopJobTitle.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopJobTitle.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the loop job title ID of this loop job title.
	 *
	 * @param loopJobTitleId the loop job title ID of this loop job title
	 */
	@Override
	public void setLoopJobTitleId(long loopJobTitleId) {
		_loopJobTitle.setLoopJobTitleId(loopJobTitleId);
	}

	/**
	 * Sets the modified date of this loop job title.
	 *
	 * @param modifiedDate the modified date of this loop job title
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_loopJobTitle.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this loop job title.
	 *
	 * @param name the name of this loop job title
	 */
	@Override
	public void setName(String name) {
		_loopJobTitle.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_loopJobTitle.setNew(n);
	}

	/**
	 * Sets the primary key of this loop job title.
	 *
	 * @param primaryKey the primary key of this loop job title
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopJobTitle.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopJobTitle.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the status of this loop job title.
	 *
	 * @param status the status of this loop job title
	 */
	@Override
	public void setStatus(int status) {
		_loopJobTitle.setStatus(status);
	}

	/**
	 * Sets the user ID of this loop job title.
	 *
	 * @param userId the user ID of this loop job title
	 */
	@Override
	public void setUserId(long userId) {
		_loopJobTitle.setUserId(userId);
	}

	/**
	 * Sets the user name of this loop job title.
	 *
	 * @param userName the user name of this loop job title
	 */
	@Override
	public void setUserName(String userName) {
		_loopJobTitle.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this loop job title.
	 *
	 * @param userUuid the user uuid of this loop job title
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_loopJobTitle.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopJobTitle>
		toCacheModel() {

		return _loopJobTitle.toCacheModel();
	}

	@Override
	public LoopJobTitle toEscapedModel() {
		return new LoopJobTitleWrapper(_loopJobTitle.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopJobTitle.toString();
	}

	@Override
	public LoopJobTitle toUnescapedModel() {
		return new LoopJobTitleWrapper(_loopJobTitle.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopJobTitle.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopJobTitleWrapper)) {
			return false;
		}

		LoopJobTitleWrapper loopJobTitleWrapper = (LoopJobTitleWrapper)obj;

		if (Objects.equals(_loopJobTitle, loopJobTitleWrapper._loopJobTitle)) {
			return true;
		}

		return false;
	}

	@Override
	public LoopJobTitle getWrappedModel() {
		return _loopJobTitle;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopJobTitle.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopJobTitle.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopJobTitle.resetOriginalValues();
	}

	private final LoopJobTitle _loopJobTitle;

}