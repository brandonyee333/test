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

package com.liferay.osb.model;

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
 * This class is a wrapper for {@link ContractAudit}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractAudit
 * @generated
 */
@ProviderType
public class ContractAuditWrapper implements ContractAudit,
	ModelWrapper<ContractAudit> {
	public ContractAuditWrapper(ContractAudit contractAudit) {
		_contractAudit = contractAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return ContractAudit.class;
	}

	@Override
	public String getModelClassName() {
		return ContractAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contractAuditId", getContractAuditId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("userEmailAddress", getUserEmailAddress());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("contractEntryId", getContractEntryId());
		attributes.put("signatoryClassNameId", getSignatoryClassNameId());
		attributes.put("signatoryClassPK", getSignatoryClassPK());
		attributes.put("productClassNameId", getProductClassNameId());
		attributes.put("productClassPK", getProductClassPK());
		attributes.put("type", getType());
		attributes.put("languageId", getLanguageId());
		attributes.put("version", getVersion());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long contractAuditId = (Long)attributes.get("contractAuditId");

		if (contractAuditId != null) {
			setContractAuditId(contractAuditId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		String userEmailAddress = (String)attributes.get("userEmailAddress");

		if (userEmailAddress != null) {
			setUserEmailAddress(userEmailAddress);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long contractEntryId = (Long)attributes.get("contractEntryId");

		if (contractEntryId != null) {
			setContractEntryId(contractEntryId);
		}

		Long signatoryClassNameId = (Long)attributes.get("signatoryClassNameId");

		if (signatoryClassNameId != null) {
			setSignatoryClassNameId(signatoryClassNameId);
		}

		Long signatoryClassPK = (Long)attributes.get("signatoryClassPK");

		if (signatoryClassPK != null) {
			setSignatoryClassPK(signatoryClassPK);
		}

		Long productClassNameId = (Long)attributes.get("productClassNameId");

		if (productClassNameId != null) {
			setProductClassNameId(productClassNameId);
		}

		Long productClassPK = (Long)attributes.get("productClassPK");

		if (productClassPK != null) {
			setProductClassPK(productClassPK);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}

		Integer version = (Integer)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}
	}

	@Override
	public ContractAudit toEscapedModel() {
		return new ContractAuditWrapper(_contractAudit.toEscapedModel());
	}

	@Override
	public ContractAudit toUnescapedModel() {
		return new ContractAuditWrapper(_contractAudit.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _contractAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _contractAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _contractAudit.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _contractAudit.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ContractAudit> toCacheModel() {
		return _contractAudit.toCacheModel();
	}

	@Override
	public int compareTo(ContractAudit contractAudit) {
		return _contractAudit.compareTo(contractAudit);
	}

	/**
	* Returns the version of this contract audit.
	*
	* @return the version of this contract audit
	*/
	@Override
	public int getVersion() {
		return _contractAudit.getVersion();
	}

	@Override
	public int hashCode() {
		return _contractAudit.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contractAudit.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ContractAuditWrapper((ContractAudit)_contractAudit.clone());
	}

	/**
	* Returns the language ID of this contract audit.
	*
	* @return the language ID of this contract audit
	*/
	@Override
	public java.lang.String getLanguageId() {
		return _contractAudit.getLanguageId();
	}

	/**
	* Returns the type of this contract audit.
	*
	* @return the type of this contract audit
	*/
	@Override
	public java.lang.String getType() {
		return _contractAudit.getType();
	}

	/**
	* Returns the user email address of this contract audit.
	*
	* @return the user email address of this contract audit
	*/
	@Override
	public java.lang.String getUserEmailAddress() {
		return _contractAudit.getUserEmailAddress();
	}

	/**
	* Returns the user name of this contract audit.
	*
	* @return the user name of this contract audit
	*/
	@Override
	public java.lang.String getUserName() {
		return _contractAudit.getUserName();
	}

	/**
	* Returns the user uuid of this contract audit.
	*
	* @return the user uuid of this contract audit
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _contractAudit.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _contractAudit.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _contractAudit.toXmlString();
	}

	/**
	* Returns the create date of this contract audit.
	*
	* @return the create date of this contract audit
	*/
	@Override
	public Date getCreateDate() {
		return _contractAudit.getCreateDate();
	}

	/**
	* Returns the modified date of this contract audit.
	*
	* @return the modified date of this contract audit
	*/
	@Override
	public Date getModifiedDate() {
		return _contractAudit.getModifiedDate();
	}

	/**
	* Returns the contract audit ID of this contract audit.
	*
	* @return the contract audit ID of this contract audit
	*/
	@Override
	public long getContractAuditId() {
		return _contractAudit.getContractAuditId();
	}

	/**
	* Returns the contract entry ID of this contract audit.
	*
	* @return the contract entry ID of this contract audit
	*/
	@Override
	public long getContractEntryId() {
		return _contractAudit.getContractEntryId();
	}

	/**
	* Returns the primary key of this contract audit.
	*
	* @return the primary key of this contract audit
	*/
	@Override
	public long getPrimaryKey() {
		return _contractAudit.getPrimaryKey();
	}

	/**
	* Returns the product class name ID of this contract audit.
	*
	* @return the product class name ID of this contract audit
	*/
	@Override
	public long getProductClassNameId() {
		return _contractAudit.getProductClassNameId();
	}

	/**
	* Returns the product class pk of this contract audit.
	*
	* @return the product class pk of this contract audit
	*/
	@Override
	public long getProductClassPK() {
		return _contractAudit.getProductClassPK();
	}

	/**
	* Returns the signatory class name ID of this contract audit.
	*
	* @return the signatory class name ID of this contract audit
	*/
	@Override
	public long getSignatoryClassNameId() {
		return _contractAudit.getSignatoryClassNameId();
	}

	/**
	* Returns the signatory class pk of this contract audit.
	*
	* @return the signatory class pk of this contract audit
	*/
	@Override
	public long getSignatoryClassPK() {
		return _contractAudit.getSignatoryClassPK();
	}

	/**
	* Returns the user ID of this contract audit.
	*
	* @return the user ID of this contract audit
	*/
	@Override
	public long getUserId() {
		return _contractAudit.getUserId();
	}

	@Override
	public void persist() {
		_contractAudit.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_contractAudit.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract audit ID of this contract audit.
	*
	* @param contractAuditId the contract audit ID of this contract audit
	*/
	@Override
	public void setContractAuditId(long contractAuditId) {
		_contractAudit.setContractAuditId(contractAuditId);
	}

	/**
	* Sets the contract entry ID of this contract audit.
	*
	* @param contractEntryId the contract entry ID of this contract audit
	*/
	@Override
	public void setContractEntryId(long contractEntryId) {
		_contractAudit.setContractEntryId(contractEntryId);
	}

	/**
	* Sets the create date of this contract audit.
	*
	* @param createDate the create date of this contract audit
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_contractAudit.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_contractAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_contractAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_contractAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the language ID of this contract audit.
	*
	* @param languageId the language ID of this contract audit
	*/
	@Override
	public void setLanguageId(java.lang.String languageId) {
		_contractAudit.setLanguageId(languageId);
	}

	/**
	* Sets the modified date of this contract audit.
	*
	* @param modifiedDate the modified date of this contract audit
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_contractAudit.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_contractAudit.setNew(n);
	}

	/**
	* Sets the primary key of this contract audit.
	*
	* @param primaryKey the primary key of this contract audit
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_contractAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_contractAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product class name ID of this contract audit.
	*
	* @param productClassNameId the product class name ID of this contract audit
	*/
	@Override
	public void setProductClassNameId(long productClassNameId) {
		_contractAudit.setProductClassNameId(productClassNameId);
	}

	/**
	* Sets the product class pk of this contract audit.
	*
	* @param productClassPK the product class pk of this contract audit
	*/
	@Override
	public void setProductClassPK(long productClassPK) {
		_contractAudit.setProductClassPK(productClassPK);
	}

	/**
	* Sets the signatory class name ID of this contract audit.
	*
	* @param signatoryClassNameId the signatory class name ID of this contract audit
	*/
	@Override
	public void setSignatoryClassNameId(long signatoryClassNameId) {
		_contractAudit.setSignatoryClassNameId(signatoryClassNameId);
	}

	/**
	* Sets the signatory class pk of this contract audit.
	*
	* @param signatoryClassPK the signatory class pk of this contract audit
	*/
	@Override
	public void setSignatoryClassPK(long signatoryClassPK) {
		_contractAudit.setSignatoryClassPK(signatoryClassPK);
	}

	/**
	* Sets the type of this contract audit.
	*
	* @param type the type of this contract audit
	*/
	@Override
	public void setType(java.lang.String type) {
		_contractAudit.setType(type);
	}

	/**
	* Sets the user email address of this contract audit.
	*
	* @param userEmailAddress the user email address of this contract audit
	*/
	@Override
	public void setUserEmailAddress(java.lang.String userEmailAddress) {
		_contractAudit.setUserEmailAddress(userEmailAddress);
	}

	/**
	* Sets the user ID of this contract audit.
	*
	* @param userId the user ID of this contract audit
	*/
	@Override
	public void setUserId(long userId) {
		_contractAudit.setUserId(userId);
	}

	/**
	* Sets the user name of this contract audit.
	*
	* @param userName the user name of this contract audit
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_contractAudit.setUserName(userName);
	}

	/**
	* Sets the user uuid of this contract audit.
	*
	* @param userUuid the user uuid of this contract audit
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_contractAudit.setUserUuid(userUuid);
	}

	/**
	* Sets the version of this contract audit.
	*
	* @param version the version of this contract audit
	*/
	@Override
	public void setVersion(int version) {
		_contractAudit.setVersion(version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContractAuditWrapper)) {
			return false;
		}

		ContractAuditWrapper contractAuditWrapper = (ContractAuditWrapper)obj;

		if (Objects.equals(_contractAudit, contractAuditWrapper._contractAudit)) {
			return true;
		}

		return false;
	}

	@Override
	public ContractAudit getWrappedModel() {
		return _contractAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _contractAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _contractAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_contractAudit.resetOriginalValues();
	}

	private final ContractAudit _contractAudit;
}