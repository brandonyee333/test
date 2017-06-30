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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContractAudit}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContractAudit
 * @generated
 */
public class ContractAuditWrapper implements ContractAudit,
	ModelWrapper<ContractAudit> {
	public ContractAuditWrapper(ContractAudit contractAudit) {
		_contractAudit = contractAudit;
	}

	public Class<?> getModelClass() {
		return ContractAudit.class;
	}

	public String getModelClassName() {
		return ContractAudit.class.getName();
	}

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

	/**
	* Returns the primary key of this contract audit.
	*
	* @return the primary key of this contract audit
	*/
	public long getPrimaryKey() {
		return _contractAudit.getPrimaryKey();
	}

	/**
	* Sets the primary key of this contract audit.
	*
	* @param primaryKey the primary key of this contract audit
	*/
	public void setPrimaryKey(long primaryKey) {
		_contractAudit.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the contract audit ID of this contract audit.
	*
	* @return the contract audit ID of this contract audit
	*/
	public long getContractAuditId() {
		return _contractAudit.getContractAuditId();
	}

	/**
	* Sets the contract audit ID of this contract audit.
	*
	* @param contractAuditId the contract audit ID of this contract audit
	*/
	public void setContractAuditId(long contractAuditId) {
		_contractAudit.setContractAuditId(contractAuditId);
	}

	/**
	* Returns the user ID of this contract audit.
	*
	* @return the user ID of this contract audit
	*/
	public long getUserId() {
		return _contractAudit.getUserId();
	}

	/**
	* Sets the user ID of this contract audit.
	*
	* @param userId the user ID of this contract audit
	*/
	public void setUserId(long userId) {
		_contractAudit.setUserId(userId);
	}

	/**
	* Returns the user uuid of this contract audit.
	*
	* @return the user uuid of this contract audit
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _contractAudit.getUserUuid();
	}

	/**
	* Sets the user uuid of this contract audit.
	*
	* @param userUuid the user uuid of this contract audit
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_contractAudit.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this contract audit.
	*
	* @return the user name of this contract audit
	*/
	public java.lang.String getUserName() {
		return _contractAudit.getUserName();
	}

	/**
	* Sets the user name of this contract audit.
	*
	* @param userName the user name of this contract audit
	*/
	public void setUserName(java.lang.String userName) {
		_contractAudit.setUserName(userName);
	}

	/**
	* Returns the user email address of this contract audit.
	*
	* @return the user email address of this contract audit
	*/
	public java.lang.String getUserEmailAddress() {
		return _contractAudit.getUserEmailAddress();
	}

	/**
	* Sets the user email address of this contract audit.
	*
	* @param userEmailAddress the user email address of this contract audit
	*/
	public void setUserEmailAddress(java.lang.String userEmailAddress) {
		_contractAudit.setUserEmailAddress(userEmailAddress);
	}

	/**
	* Returns the create date of this contract audit.
	*
	* @return the create date of this contract audit
	*/
	public java.util.Date getCreateDate() {
		return _contractAudit.getCreateDate();
	}

	/**
	* Sets the create date of this contract audit.
	*
	* @param createDate the create date of this contract audit
	*/
	public void setCreateDate(java.util.Date createDate) {
		_contractAudit.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this contract audit.
	*
	* @return the modified date of this contract audit
	*/
	public java.util.Date getModifiedDate() {
		return _contractAudit.getModifiedDate();
	}

	/**
	* Sets the modified date of this contract audit.
	*
	* @param modifiedDate the modified date of this contract audit
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_contractAudit.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the contract entry ID of this contract audit.
	*
	* @return the contract entry ID of this contract audit
	*/
	public long getContractEntryId() {
		return _contractAudit.getContractEntryId();
	}

	/**
	* Sets the contract entry ID of this contract audit.
	*
	* @param contractEntryId the contract entry ID of this contract audit
	*/
	public void setContractEntryId(long contractEntryId) {
		_contractAudit.setContractEntryId(contractEntryId);
	}

	/**
	* Returns the signatory class name ID of this contract audit.
	*
	* @return the signatory class name ID of this contract audit
	*/
	public long getSignatoryClassNameId() {
		return _contractAudit.getSignatoryClassNameId();
	}

	/**
	* Sets the signatory class name ID of this contract audit.
	*
	* @param signatoryClassNameId the signatory class name ID of this contract audit
	*/
	public void setSignatoryClassNameId(long signatoryClassNameId) {
		_contractAudit.setSignatoryClassNameId(signatoryClassNameId);
	}

	/**
	* Returns the signatory class p k of this contract audit.
	*
	* @return the signatory class p k of this contract audit
	*/
	public long getSignatoryClassPK() {
		return _contractAudit.getSignatoryClassPK();
	}

	/**
	* Sets the signatory class p k of this contract audit.
	*
	* @param signatoryClassPK the signatory class p k of this contract audit
	*/
	public void setSignatoryClassPK(long signatoryClassPK) {
		_contractAudit.setSignatoryClassPK(signatoryClassPK);
	}

	/**
	* Returns the product class name ID of this contract audit.
	*
	* @return the product class name ID of this contract audit
	*/
	public long getProductClassNameId() {
		return _contractAudit.getProductClassNameId();
	}

	/**
	* Sets the product class name ID of this contract audit.
	*
	* @param productClassNameId the product class name ID of this contract audit
	*/
	public void setProductClassNameId(long productClassNameId) {
		_contractAudit.setProductClassNameId(productClassNameId);
	}

	/**
	* Returns the product class p k of this contract audit.
	*
	* @return the product class p k of this contract audit
	*/
	public long getProductClassPK() {
		return _contractAudit.getProductClassPK();
	}

	/**
	* Sets the product class p k of this contract audit.
	*
	* @param productClassPK the product class p k of this contract audit
	*/
	public void setProductClassPK(long productClassPK) {
		_contractAudit.setProductClassPK(productClassPK);
	}

	/**
	* Returns the type of this contract audit.
	*
	* @return the type of this contract audit
	*/
	public java.lang.String getType() {
		return _contractAudit.getType();
	}

	/**
	* Sets the type of this contract audit.
	*
	* @param type the type of this contract audit
	*/
	public void setType(java.lang.String type) {
		_contractAudit.setType(type);
	}

	/**
	* Returns the language ID of this contract audit.
	*
	* @return the language ID of this contract audit
	*/
	public java.lang.String getLanguageId() {
		return _contractAudit.getLanguageId();
	}

	/**
	* Sets the language ID of this contract audit.
	*
	* @param languageId the language ID of this contract audit
	*/
	public void setLanguageId(java.lang.String languageId) {
		_contractAudit.setLanguageId(languageId);
	}

	/**
	* Returns the version of this contract audit.
	*
	* @return the version of this contract audit
	*/
	public int getVersion() {
		return _contractAudit.getVersion();
	}

	/**
	* Sets the version of this contract audit.
	*
	* @param version the version of this contract audit
	*/
	public void setVersion(int version) {
		_contractAudit.setVersion(version);
	}

	public boolean isNew() {
		return _contractAudit.isNew();
	}

	public void setNew(boolean n) {
		_contractAudit.setNew(n);
	}

	public boolean isCachedModel() {
		return _contractAudit.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_contractAudit.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _contractAudit.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _contractAudit.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_contractAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _contractAudit.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_contractAudit.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ContractAuditWrapper((ContractAudit)_contractAudit.clone());
	}

	public int compareTo(com.liferay.osb.model.ContractAudit contractAudit) {
		return _contractAudit.compareTo(contractAudit);
	}

	@Override
	public int hashCode() {
		return _contractAudit.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.ContractAudit> toCacheModel() {
		return _contractAudit.toCacheModel();
	}

	public com.liferay.osb.model.ContractAudit toEscapedModel() {
		return new ContractAuditWrapper(_contractAudit.toEscapedModel());
	}

	public com.liferay.osb.model.ContractAudit toUnescapedModel() {
		return new ContractAuditWrapper(_contractAudit.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _contractAudit.toString();
	}

	public java.lang.String toXmlString() {
		return _contractAudit.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_contractAudit.persist();
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

		if (Validator.equals(_contractAudit, contractAuditWrapper._contractAudit)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public ContractAudit getWrappedContractAudit() {
		return _contractAudit;
	}

	public ContractAudit getWrappedModel() {
		return _contractAudit;
	}

	public void resetOriginalValues() {
		_contractAudit.resetOriginalValues();
	}

	private ContractAudit _contractAudit;
}