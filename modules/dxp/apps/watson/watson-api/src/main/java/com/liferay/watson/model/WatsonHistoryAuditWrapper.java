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
 * This class is a wrapper for {@link WatsonHistoryAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonHistoryAudit
 * @generated
 */
@ProviderType
public class WatsonHistoryAuditWrapper implements WatsonHistoryAudit,
	ModelWrapper<WatsonHistoryAudit> {
	public WatsonHistoryAuditWrapper(WatsonHistoryAudit watsonHistoryAudit) {
		_watsonHistoryAudit = watsonHistoryAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonHistoryAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonHistoryAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonHistoryAuditId", getWatsonHistoryAuditId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("watsonHistoryId", getWatsonHistoryId());
		attributes.put("watsonParentId", getWatsonParentId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonHistoryAuditId = (Long)attributes.get("watsonHistoryAuditId");

		if (watsonHistoryAuditId != null) {
			setWatsonHistoryAuditId(watsonHistoryAuditId);
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

		Long watsonHistoryId = (Long)attributes.get("watsonHistoryId");

		if (watsonHistoryId != null) {
			setWatsonHistoryId(watsonHistoryId);
		}

		Long watsonParentId = (Long)attributes.get("watsonParentId");

		if (watsonParentId != null) {
			setWatsonParentId(watsonParentId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer type = (Integer)attributes.get("type");

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
		return new WatsonHistoryAuditWrapper((WatsonHistoryAudit)_watsonHistoryAudit.clone());
	}

	@Override
	public int compareTo(WatsonHistoryAudit watsonHistoryAudit) {
		return _watsonHistoryAudit.compareTo(watsonHistoryAudit);
	}

	/**
	* Returns the fully qualified class name of this watson history audit.
	*
	* @return the fully qualified class name of this watson history audit
	*/
	@Override
	public String getClassName() {
		return _watsonHistoryAudit.getClassName();
	}

	/**
	* Returns the class name ID of this watson history audit.
	*
	* @return the class name ID of this watson history audit
	*/
	@Override
	public long getClassNameId() {
		return _watsonHistoryAudit.getClassNameId();
	}

	/**
	* Returns the class pk of this watson history audit.
	*
	* @return the class pk of this watson history audit
	*/
	@Override
	public long getClassPK() {
		return _watsonHistoryAudit.getClassPK();
	}

	/**
	* Returns the company ID of this watson history audit.
	*
	* @return the company ID of this watson history audit
	*/
	@Override
	public long getCompanyId() {
		return _watsonHistoryAudit.getCompanyId();
	}

	/**
	* Returns the create date of this watson history audit.
	*
	* @return the create date of this watson history audit
	*/
	@Override
	public Date getCreateDate() {
		return _watsonHistoryAudit.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonHistoryAudit.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson history audit.
	*
	* @return the group ID of this watson history audit
	*/
	@Override
	public long getGroupId() {
		return _watsonHistoryAudit.getGroupId();
	}

	/**
	* Returns the modified date of this watson history audit.
	*
	* @return the modified date of this watson history audit
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonHistoryAudit.getModifiedDate();
	}

	/**
	* Returns the primary key of this watson history audit.
	*
	* @return the primary key of this watson history audit
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonHistoryAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonHistoryAudit.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this watson history audit.
	*
	* @return the status of this watson history audit
	*/
	@Override
	public int getStatus() {
		return _watsonHistoryAudit.getStatus();
	}

	/**
	* Returns the type of this watson history audit.
	*
	* @return the type of this watson history audit
	*/
	@Override
	public int getType() {
		return _watsonHistoryAudit.getType();
	}

	/**
	* Returns the user ID of this watson history audit.
	*
	* @return the user ID of this watson history audit
	*/
	@Override
	public long getUserId() {
		return _watsonHistoryAudit.getUserId();
	}

	/**
	* Returns the user name of this watson history audit.
	*
	* @return the user name of this watson history audit
	*/
	@Override
	public String getUserName() {
		return _watsonHistoryAudit.getUserName();
	}

	/**
	* Returns the user uuid of this watson history audit.
	*
	* @return the user uuid of this watson history audit
	*/
	@Override
	public String getUserUuid() {
		return _watsonHistoryAudit.getUserUuid();
	}

	/**
	* Returns the watson history audit ID of this watson history audit.
	*
	* @return the watson history audit ID of this watson history audit
	*/
	@Override
	public long getWatsonHistoryAuditId() {
		return _watsonHistoryAudit.getWatsonHistoryAuditId();
	}

	/**
	* Returns the watson history ID of this watson history audit.
	*
	* @return the watson history ID of this watson history audit
	*/
	@Override
	public long getWatsonHistoryId() {
		return _watsonHistoryAudit.getWatsonHistoryId();
	}

	/**
	* Returns the watson parent ID of this watson history audit.
	*
	* @return the watson parent ID of this watson history audit
	*/
	@Override
	public long getWatsonParentId() {
		return _watsonHistoryAudit.getWatsonParentId();
	}

	@Override
	public int hashCode() {
		return _watsonHistoryAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonHistoryAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonHistoryAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonHistoryAudit.isNew();
	}

	@Override
	public void persist() {
		_watsonHistoryAudit.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonHistoryAudit.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_watsonHistoryAudit.setClassName(className);
	}

	/**
	* Sets the class name ID of this watson history audit.
	*
	* @param classNameId the class name ID of this watson history audit
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_watsonHistoryAudit.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this watson history audit.
	*
	* @param classPK the class pk of this watson history audit
	*/
	@Override
	public void setClassPK(long classPK) {
		_watsonHistoryAudit.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this watson history audit.
	*
	* @param companyId the company ID of this watson history audit
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonHistoryAudit.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson history audit.
	*
	* @param createDate the create date of this watson history audit
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonHistoryAudit.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonHistoryAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonHistoryAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonHistoryAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson history audit.
	*
	* @param groupId the group ID of this watson history audit
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonHistoryAudit.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this watson history audit.
	*
	* @param modifiedDate the modified date of this watson history audit
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonHistoryAudit.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonHistoryAudit.setNew(n);
	}

	/**
	* Sets the primary key of this watson history audit.
	*
	* @param primaryKey the primary key of this watson history audit
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonHistoryAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonHistoryAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this watson history audit.
	*
	* @param status the status of this watson history audit
	*/
	@Override
	public void setStatus(int status) {
		_watsonHistoryAudit.setStatus(status);
	}

	/**
	* Sets the type of this watson history audit.
	*
	* @param type the type of this watson history audit
	*/
	@Override
	public void setType(int type) {
		_watsonHistoryAudit.setType(type);
	}

	/**
	* Sets the user ID of this watson history audit.
	*
	* @param userId the user ID of this watson history audit
	*/
	@Override
	public void setUserId(long userId) {
		_watsonHistoryAudit.setUserId(userId);
	}

	/**
	* Sets the user name of this watson history audit.
	*
	* @param userName the user name of this watson history audit
	*/
	@Override
	public void setUserName(String userName) {
		_watsonHistoryAudit.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson history audit.
	*
	* @param userUuid the user uuid of this watson history audit
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_watsonHistoryAudit.setUserUuid(userUuid);
	}

	/**
	* Sets the watson history audit ID of this watson history audit.
	*
	* @param watsonHistoryAuditId the watson history audit ID of this watson history audit
	*/
	@Override
	public void setWatsonHistoryAuditId(long watsonHistoryAuditId) {
		_watsonHistoryAudit.setWatsonHistoryAuditId(watsonHistoryAuditId);
	}

	/**
	* Sets the watson history ID of this watson history audit.
	*
	* @param watsonHistoryId the watson history ID of this watson history audit
	*/
	@Override
	public void setWatsonHistoryId(long watsonHistoryId) {
		_watsonHistoryAudit.setWatsonHistoryId(watsonHistoryId);
	}

	/**
	* Sets the watson parent ID of this watson history audit.
	*
	* @param watsonParentId the watson parent ID of this watson history audit
	*/
	@Override
	public void setWatsonParentId(long watsonParentId) {
		_watsonHistoryAudit.setWatsonParentId(watsonParentId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonHistoryAudit> toCacheModel() {
		return _watsonHistoryAudit.toCacheModel();
	}

	@Override
	public WatsonHistoryAudit toEscapedModel() {
		return new WatsonHistoryAuditWrapper(_watsonHistoryAudit.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonHistoryAudit.toString();
	}

	@Override
	public WatsonHistoryAudit toUnescapedModel() {
		return new WatsonHistoryAuditWrapper(_watsonHistoryAudit.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonHistoryAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonHistoryAuditWrapper)) {
			return false;
		}

		WatsonHistoryAuditWrapper watsonHistoryAuditWrapper = (WatsonHistoryAuditWrapper)obj;

		if (Objects.equals(_watsonHistoryAudit,
					watsonHistoryAuditWrapper._watsonHistoryAudit)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonHistoryAudit getWrappedModel() {
		return _watsonHistoryAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonHistoryAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonHistoryAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonHistoryAudit.resetOriginalValues();
	}

	private final WatsonHistoryAudit _watsonHistoryAudit;
}