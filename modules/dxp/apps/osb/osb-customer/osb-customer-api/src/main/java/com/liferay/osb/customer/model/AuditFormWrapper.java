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

package com.liferay.osb.customer.model;

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
 * This class is a wrapper for {@link AuditForm}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditForm
 * @generated
 */
@ProviderType
public class AuditFormWrapper implements AuditForm, ModelWrapper<AuditForm> {
	public AuditFormWrapper(AuditForm auditForm) {
		_auditForm = auditForm;
	}

	@Override
	public Class<?> getModelClass() {
		return AuditForm.class;
	}

	@Override
	public String getModelClassName() {
		return AuditForm.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("auditFormId", getAuditFormId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("endUserName", getEndUserName());
		attributes.put("endUserEmailAddress", getEndUserEmailAddress());
		attributes.put("companyName", getCompanyName());
		attributes.put("agreement", getAgreement());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long auditFormId = (Long)attributes.get("auditFormId");

		if (auditFormId != null) {
			setAuditFormId(auditFormId);
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

		String endUserName = (String)attributes.get("endUserName");

		if (endUserName != null) {
			setEndUserName(endUserName);
		}

		String endUserEmailAddress = (String)attributes.get(
				"endUserEmailAddress");

		if (endUserEmailAddress != null) {
			setEndUserEmailAddress(endUserEmailAddress);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		Boolean agreement = (Boolean)attributes.get("agreement");

		if (agreement != null) {
			setAgreement(agreement);
		}
	}

	@Override
	public AuditForm toEscapedModel() {
		return new AuditFormWrapper(_auditForm.toEscapedModel());
	}

	@Override
	public AuditForm toUnescapedModel() {
		return new AuditFormWrapper(_auditForm.toUnescapedModel());
	}

	/**
	* Returns the agreement of this audit form.
	*
	* @return the agreement of this audit form
	*/
	@Override
	public boolean getAgreement() {
		return _auditForm.getAgreement();
	}

	/**
	* Returns <code>true</code> if this audit form is agreement.
	*
	* @return <code>true</code> if this audit form is agreement; <code>false</code> otherwise
	*/
	@Override
	public boolean isAgreement() {
		return _auditForm.isAgreement();
	}

	@Override
	public boolean isCachedModel() {
		return _auditForm.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _auditForm.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _auditForm.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _auditForm.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AuditForm> toCacheModel() {
		return _auditForm.toCacheModel();
	}

	@Override
	public int compareTo(AuditForm auditForm) {
		return _auditForm.compareTo(auditForm);
	}

	@Override
	public int hashCode() {
		return _auditForm.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _auditForm.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AuditFormWrapper((AuditForm)_auditForm.clone());
	}

	/**
	* Returns the company name of this audit form.
	*
	* @return the company name of this audit form
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _auditForm.getCompanyName();
	}

	/**
	* Returns the end user email address of this audit form.
	*
	* @return the end user email address of this audit form
	*/
	@Override
	public java.lang.String getEndUserEmailAddress() {
		return _auditForm.getEndUserEmailAddress();
	}

	/**
	* Returns the end user name of this audit form.
	*
	* @return the end user name of this audit form
	*/
	@Override
	public java.lang.String getEndUserName() {
		return _auditForm.getEndUserName();
	}

	/**
	* Returns the user name of this audit form.
	*
	* @return the user name of this audit form
	*/
	@Override
	public java.lang.String getUserName() {
		return _auditForm.getUserName();
	}

	/**
	* Returns the user uuid of this audit form.
	*
	* @return the user uuid of this audit form
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _auditForm.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _auditForm.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _auditForm.toXmlString();
	}

	/**
	* Returns the create date of this audit form.
	*
	* @return the create date of this audit form
	*/
	@Override
	public Date getCreateDate() {
		return _auditForm.getCreateDate();
	}

	/**
	* Returns the audit form ID of this audit form.
	*
	* @return the audit form ID of this audit form
	*/
	@Override
	public long getAuditFormId() {
		return _auditForm.getAuditFormId();
	}

	/**
	* Returns the primary key of this audit form.
	*
	* @return the primary key of this audit form
	*/
	@Override
	public long getPrimaryKey() {
		return _auditForm.getPrimaryKey();
	}

	/**
	* Returns the user ID of this audit form.
	*
	* @return the user ID of this audit form
	*/
	@Override
	public long getUserId() {
		return _auditForm.getUserId();
	}

	@Override
	public void persist() {
		_auditForm.persist();
	}

	/**
	* Sets whether this audit form is agreement.
	*
	* @param agreement the agreement of this audit form
	*/
	@Override
	public void setAgreement(boolean agreement) {
		_auditForm.setAgreement(agreement);
	}

	/**
	* Sets the audit form ID of this audit form.
	*
	* @param auditFormId the audit form ID of this audit form
	*/
	@Override
	public void setAuditFormId(long auditFormId) {
		_auditForm.setAuditFormId(auditFormId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_auditForm.setCachedModel(cachedModel);
	}

	/**
	* Sets the company name of this audit form.
	*
	* @param companyName the company name of this audit form
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_auditForm.setCompanyName(companyName);
	}

	/**
	* Sets the create date of this audit form.
	*
	* @param createDate the create date of this audit form
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_auditForm.setCreateDate(createDate);
	}

	/**
	* Sets the end user email address of this audit form.
	*
	* @param endUserEmailAddress the end user email address of this audit form
	*/
	@Override
	public void setEndUserEmailAddress(java.lang.String endUserEmailAddress) {
		_auditForm.setEndUserEmailAddress(endUserEmailAddress);
	}

	/**
	* Sets the end user name of this audit form.
	*
	* @param endUserName the end user name of this audit form
	*/
	@Override
	public void setEndUserName(java.lang.String endUserName) {
		_auditForm.setEndUserName(endUserName);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_auditForm.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_auditForm.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_auditForm.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_auditForm.setNew(n);
	}

	/**
	* Sets the primary key of this audit form.
	*
	* @param primaryKey the primary key of this audit form
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_auditForm.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_auditForm.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this audit form.
	*
	* @param userId the user ID of this audit form
	*/
	@Override
	public void setUserId(long userId) {
		_auditForm.setUserId(userId);
	}

	/**
	* Sets the user name of this audit form.
	*
	* @param userName the user name of this audit form
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_auditForm.setUserName(userName);
	}

	/**
	* Sets the user uuid of this audit form.
	*
	* @param userUuid the user uuid of this audit form
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_auditForm.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditFormWrapper)) {
			return false;
		}

		AuditFormWrapper auditFormWrapper = (AuditFormWrapper)obj;

		if (Objects.equals(_auditForm, auditFormWrapper._auditForm)) {
			return true;
		}

		return false;
	}

	@Override
	public AuditForm getWrappedModel() {
		return _auditForm;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _auditForm.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _auditForm.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_auditForm.resetOriginalValues();
	}

	private final AuditForm _auditForm;
}