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
 * This class is a wrapper for {@link WatsonDocumentAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonDocumentAudit
 * @generated
 */
@ProviderType
public class WatsonDocumentAuditWrapper
	implements WatsonDocumentAudit, ModelWrapper<WatsonDocumentAudit> {

	public WatsonDocumentAuditWrapper(WatsonDocumentAudit watsonDocumentAudit) {
		_watsonDocumentAudit = watsonDocumentAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonDocumentAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonDocumentAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonDocumentAuditId", getWatsonDocumentAuditId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put(
			"parentTypeWatsonListTypeId", getParentTypeWatsonListTypeId());
		attributes.put("subtypeWatsonListTypeId", getSubtypeWatsonListTypeId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("watsonChildId", getWatsonChildId());
		attributes.put("watsonDocumentId", getWatsonDocumentId());
		attributes.put("originalDocument", isOriginalDocument());
		attributes.put("receivedDate", getReceivedDate());
		attributes.put("imagePayload", getImagePayload());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonDocumentAuditId = (Long)attributes.get(
			"watsonDocumentAuditId");

		if (watsonDocumentAuditId != null) {
			setWatsonDocumentAuditId(watsonDocumentAuditId);
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

		Long parentTypeWatsonListTypeId = (Long)attributes.get(
			"parentTypeWatsonListTypeId");

		if (parentTypeWatsonListTypeId != null) {
			setParentTypeWatsonListTypeId(parentTypeWatsonListTypeId);
		}

		Long subtypeWatsonListTypeId = (Long)attributes.get(
			"subtypeWatsonListTypeId");

		if (subtypeWatsonListTypeId != null) {
			setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get(
			"typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long watsonChildId = (Long)attributes.get("watsonChildId");

		if (watsonChildId != null) {
			setWatsonChildId(watsonChildId);
		}

		Long watsonDocumentId = (Long)attributes.get("watsonDocumentId");

		if (watsonDocumentId != null) {
			setWatsonDocumentId(watsonDocumentId);
		}

		Boolean originalDocument = (Boolean)attributes.get("originalDocument");

		if (originalDocument != null) {
			setOriginalDocument(originalDocument);
		}

		Date receivedDate = (Date)attributes.get("receivedDate");

		if (receivedDate != null) {
			setReceivedDate(receivedDate);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new WatsonDocumentAuditWrapper(
			(WatsonDocumentAudit)_watsonDocumentAudit.clone());
	}

	@Override
	public int compareTo(WatsonDocumentAudit watsonDocumentAudit) {
		return _watsonDocumentAudit.compareTo(watsonDocumentAudit);
	}

	/**
	 * Returns the company ID of this watson document audit.
	 *
	 * @return the company ID of this watson document audit
	 */
	@Override
	public long getCompanyId() {
		return _watsonDocumentAudit.getCompanyId();
	}

	/**
	 * Returns the create date of this watson document audit.
	 *
	 * @return the create date of this watson document audit
	 */
	@Override
	public Date getCreateDate() {
		return _watsonDocumentAudit.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonDocumentAudit.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this watson document audit.
	 *
	 * @return the group ID of this watson document audit
	 */
	@Override
	public long getGroupId() {
		return _watsonDocumentAudit.getGroupId();
	}

	/**
	 * Returns the image payload of this watson document audit.
	 *
	 * @return the image payload of this watson document audit
	 */
	@Override
	public String getImagePayload() {
		return _watsonDocumentAudit.getImagePayload();
	}

	/**
	 * Returns the modified date of this watson document audit.
	 *
	 * @return the modified date of this watson document audit
	 */
	@Override
	public Date getModifiedDate() {
		return _watsonDocumentAudit.getModifiedDate();
	}

	/**
	 * Returns the original document of this watson document audit.
	 *
	 * @return the original document of this watson document audit
	 */
	@Override
	public boolean getOriginalDocument() {
		return _watsonDocumentAudit.getOriginalDocument();
	}

	/**
	 * Returns the parent type watson list type ID of this watson document audit.
	 *
	 * @return the parent type watson list type ID of this watson document audit
	 */
	@Override
	public long getParentTypeWatsonListTypeId() {
		return _watsonDocumentAudit.getParentTypeWatsonListTypeId();
	}

	/**
	 * Returns the primary key of this watson document audit.
	 *
	 * @return the primary key of this watson document audit
	 */
	@Override
	public long getPrimaryKey() {
		return _watsonDocumentAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonDocumentAudit.getPrimaryKeyObj();
	}

	/**
	 * Returns the received date of this watson document audit.
	 *
	 * @return the received date of this watson document audit
	 */
	@Override
	public Date getReceivedDate() {
		return _watsonDocumentAudit.getReceivedDate();
	}

	/**
	 * Returns the status of this watson document audit.
	 *
	 * @return the status of this watson document audit
	 */
	@Override
	public int getStatus() {
		return _watsonDocumentAudit.getStatus();
	}

	/**
	 * Returns the subtype watson list type ID of this watson document audit.
	 *
	 * @return the subtype watson list type ID of this watson document audit
	 */
	@Override
	public long getSubtypeWatsonListTypeId() {
		return _watsonDocumentAudit.getSubtypeWatsonListTypeId();
	}

	/**
	 * Returns the type watson list type ID of this watson document audit.
	 *
	 * @return the type watson list type ID of this watson document audit
	 */
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonDocumentAudit.getTypeWatsonListTypeId();
	}

	/**
	 * Returns the user ID of this watson document audit.
	 *
	 * @return the user ID of this watson document audit
	 */
	@Override
	public long getUserId() {
		return _watsonDocumentAudit.getUserId();
	}

	/**
	 * Returns the user name of this watson document audit.
	 *
	 * @return the user name of this watson document audit
	 */
	@Override
	public String getUserName() {
		return _watsonDocumentAudit.getUserName();
	}

	/**
	 * Returns the user uuid of this watson document audit.
	 *
	 * @return the user uuid of this watson document audit
	 */
	@Override
	public String getUserUuid() {
		return _watsonDocumentAudit.getUserUuid();
	}

	/**
	 * Returns the watson child ID of this watson document audit.
	 *
	 * @return the watson child ID of this watson document audit
	 */
	@Override
	public long getWatsonChildId() {
		return _watsonDocumentAudit.getWatsonChildId();
	}

	/**
	 * Returns the watson document audit ID of this watson document audit.
	 *
	 * @return the watson document audit ID of this watson document audit
	 */
	@Override
	public long getWatsonDocumentAuditId() {
		return _watsonDocumentAudit.getWatsonDocumentAuditId();
	}

	/**
	 * Returns the watson document ID of this watson document audit.
	 *
	 * @return the watson document ID of this watson document audit
	 */
	@Override
	public long getWatsonDocumentId() {
		return _watsonDocumentAudit.getWatsonDocumentId();
	}

	@Override
	public int hashCode() {
		return _watsonDocumentAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonDocumentAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonDocumentAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonDocumentAudit.isNew();
	}

	/**
	 * Returns <code>true</code> if this watson document audit is original document.
	 *
	 * @return <code>true</code> if this watson document audit is original document; <code>false</code> otherwise
	 */
	@Override
	public boolean isOriginalDocument() {
		return _watsonDocumentAudit.isOriginalDocument();
	}

	@Override
	public void persist() {
		_watsonDocumentAudit.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonDocumentAudit.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this watson document audit.
	 *
	 * @param companyId the company ID of this watson document audit
	 */
	@Override
	public void setCompanyId(long companyId) {
		_watsonDocumentAudit.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this watson document audit.
	 *
	 * @param createDate the create date of this watson document audit
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_watsonDocumentAudit.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_watsonDocumentAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonDocumentAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonDocumentAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this watson document audit.
	 *
	 * @param groupId the group ID of this watson document audit
	 */
	@Override
	public void setGroupId(long groupId) {
		_watsonDocumentAudit.setGroupId(groupId);
	}

	/**
	 * Sets the image payload of this watson document audit.
	 *
	 * @param imagePayload the image payload of this watson document audit
	 */
	@Override
	public void setImagePayload(String imagePayload) {
		_watsonDocumentAudit.setImagePayload(imagePayload);
	}

	/**
	 * Sets the modified date of this watson document audit.
	 *
	 * @param modifiedDate the modified date of this watson document audit
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonDocumentAudit.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonDocumentAudit.setNew(n);
	}

	/**
	 * Sets whether this watson document audit is original document.
	 *
	 * @param originalDocument the original document of this watson document audit
	 */
	@Override
	public void setOriginalDocument(boolean originalDocument) {
		_watsonDocumentAudit.setOriginalDocument(originalDocument);
	}

	/**
	 * Sets the parent type watson list type ID of this watson document audit.
	 *
	 * @param parentTypeWatsonListTypeId the parent type watson list type ID of this watson document audit
	 */
	@Override
	public void setParentTypeWatsonListTypeId(long parentTypeWatsonListTypeId) {
		_watsonDocumentAudit.setParentTypeWatsonListTypeId(
			parentTypeWatsonListTypeId);
	}

	/**
	 * Sets the primary key of this watson document audit.
	 *
	 * @param primaryKey the primary key of this watson document audit
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonDocumentAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonDocumentAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the received date of this watson document audit.
	 *
	 * @param receivedDate the received date of this watson document audit
	 */
	@Override
	public void setReceivedDate(Date receivedDate) {
		_watsonDocumentAudit.setReceivedDate(receivedDate);
	}

	/**
	 * Sets the status of this watson document audit.
	 *
	 * @param status the status of this watson document audit
	 */
	@Override
	public void setStatus(int status) {
		_watsonDocumentAudit.setStatus(status);
	}

	/**
	 * Sets the subtype watson list type ID of this watson document audit.
	 *
	 * @param subtypeWatsonListTypeId the subtype watson list type ID of this watson document audit
	 */
	@Override
	public void setSubtypeWatsonListTypeId(long subtypeWatsonListTypeId) {
		_watsonDocumentAudit.setSubtypeWatsonListTypeId(
			subtypeWatsonListTypeId);
	}

	/**
	 * Sets the type watson list type ID of this watson document audit.
	 *
	 * @param typeWatsonListTypeId the type watson list type ID of this watson document audit
	 */
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonDocumentAudit.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	 * Sets the user ID of this watson document audit.
	 *
	 * @param userId the user ID of this watson document audit
	 */
	@Override
	public void setUserId(long userId) {
		_watsonDocumentAudit.setUserId(userId);
	}

	/**
	 * Sets the user name of this watson document audit.
	 *
	 * @param userName the user name of this watson document audit
	 */
	@Override
	public void setUserName(String userName) {
		_watsonDocumentAudit.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this watson document audit.
	 *
	 * @param userUuid the user uuid of this watson document audit
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_watsonDocumentAudit.setUserUuid(userUuid);
	}

	/**
	 * Sets the watson child ID of this watson document audit.
	 *
	 * @param watsonChildId the watson child ID of this watson document audit
	 */
	@Override
	public void setWatsonChildId(long watsonChildId) {
		_watsonDocumentAudit.setWatsonChildId(watsonChildId);
	}

	/**
	 * Sets the watson document audit ID of this watson document audit.
	 *
	 * @param watsonDocumentAuditId the watson document audit ID of this watson document audit
	 */
	@Override
	public void setWatsonDocumentAuditId(long watsonDocumentAuditId) {
		_watsonDocumentAudit.setWatsonDocumentAuditId(watsonDocumentAuditId);
	}

	/**
	 * Sets the watson document ID of this watson document audit.
	 *
	 * @param watsonDocumentId the watson document ID of this watson document audit
	 */
	@Override
	public void setWatsonDocumentId(long watsonDocumentId) {
		_watsonDocumentAudit.setWatsonDocumentId(watsonDocumentId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonDocumentAudit>
		toCacheModel() {

		return _watsonDocumentAudit.toCacheModel();
	}

	@Override
	public WatsonDocumentAudit toEscapedModel() {
		return new WatsonDocumentAuditWrapper(
			_watsonDocumentAudit.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonDocumentAudit.toString();
	}

	@Override
	public WatsonDocumentAudit toUnescapedModel() {
		return new WatsonDocumentAuditWrapper(
			_watsonDocumentAudit.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonDocumentAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonDocumentAuditWrapper)) {
			return false;
		}

		WatsonDocumentAuditWrapper watsonDocumentAuditWrapper =
			(WatsonDocumentAuditWrapper)obj;

		if (Objects.equals(
				_watsonDocumentAudit,
				watsonDocumentAuditWrapper._watsonDocumentAudit)) {

			return true;
		}

		return false;
	}

	@Override
	public WatsonDocumentAudit getWrappedModel() {
		return _watsonDocumentAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonDocumentAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonDocumentAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonDocumentAudit.resetOriginalValues();
	}

	private final WatsonDocumentAudit _watsonDocumentAudit;

}