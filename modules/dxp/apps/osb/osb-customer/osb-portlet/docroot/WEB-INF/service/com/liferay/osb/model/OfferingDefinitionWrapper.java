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
 * This class is a wrapper for {@link OfferingDefinition}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinition
 * @generated
 */
@ProviderType
public class OfferingDefinitionWrapper implements OfferingDefinition,
	ModelWrapper<OfferingDefinition> {
	public OfferingDefinitionWrapper(OfferingDefinition offeringDefinition) {
		_offeringDefinition = offeringDefinition;
	}

	@Override
	public Class<?> getModelClass() {
		return OfferingDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return OfferingDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("offeringDefinitionId", getOfferingDefinitionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("supportResponseId", getSupportResponseId());
		attributes.put("productDescription", getProductDescription());
		attributes.put("licenses", getLicenses());
		attributes.put("unlimitedLicenses", getUnlimitedLicenses());
		attributes.put("maxConcurrentUsers", getMaxConcurrentUsers());
		attributes.put("maxUsers", getMaxUsers());
		attributes.put("supportTickets", getSupportTickets());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long offeringDefinitionId = (Long)attributes.get("offeringDefinitionId");

		if (offeringDefinitionId != null) {
			setOfferingDefinitionId(offeringDefinitionId);
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

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		Long supportResponseId = (Long)attributes.get("supportResponseId");

		if (supportResponseId != null) {
			setSupportResponseId(supportResponseId);
		}

		String productDescription = (String)attributes.get("productDescription");

		if (productDescription != null) {
			setProductDescription(productDescription);
		}

		Boolean licenses = (Boolean)attributes.get("licenses");

		if (licenses != null) {
			setLicenses(licenses);
		}

		Boolean unlimitedLicenses = (Boolean)attributes.get("unlimitedLicenses");

		if (unlimitedLicenses != null) {
			setUnlimitedLicenses(unlimitedLicenses);
		}

		Long maxConcurrentUsers = (Long)attributes.get("maxConcurrentUsers");

		if (maxConcurrentUsers != null) {
			setMaxConcurrentUsers(maxConcurrentUsers);
		}

		Long maxUsers = (Long)attributes.get("maxUsers");

		if (maxUsers != null) {
			setMaxUsers(maxUsers);
		}

		Boolean supportTickets = (Boolean)attributes.get("supportTickets");

		if (supportTickets != null) {
			setSupportTickets(supportTickets);
		}
	}

	/**
	* Returns the licenses of this offering definition.
	*
	* @return the licenses of this offering definition
	*/
	@Override
	public boolean getLicenses() {
		return _offeringDefinition.getLicenses();
	}

	/**
	* Returns the support tickets of this offering definition.
	*
	* @return the support tickets of this offering definition
	*/
	@Override
	public boolean getSupportTickets() {
		return _offeringDefinition.getSupportTickets();
	}

	/**
	* Returns the unlimited licenses of this offering definition.
	*
	* @return the unlimited licenses of this offering definition
	*/
	@Override
	public boolean getUnlimitedLicenses() {
		return _offeringDefinition.getUnlimitedLicenses();
	}

	@Override
	public boolean isCachedModel() {
		return _offeringDefinition.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _offeringDefinition.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this offering definition is licenses.
	*
	* @return <code>true</code> if this offering definition is licenses; <code>false</code> otherwise
	*/
	@Override
	public boolean isLicenses() {
		return _offeringDefinition.isLicenses();
	}

	@Override
	public boolean isNew() {
		return _offeringDefinition.isNew();
	}

	/**
	* Returns <code>true</code> if this offering definition is support tickets.
	*
	* @return <code>true</code> if this offering definition is support tickets; <code>false</code> otherwise
	*/
	@Override
	public boolean isSupportTickets() {
		return _offeringDefinition.isSupportTickets();
	}

	/**
	* Returns <code>true</code> if this offering definition is unlimited licenses.
	*
	* @return <code>true</code> if this offering definition is unlimited licenses; <code>false</code> otherwise
	*/
	@Override
	public boolean isUnlimitedLicenses() {
		return _offeringDefinition.isUnlimitedLicenses();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _offeringDefinition.getExpandoBridge();
	}

	@Override
	public OfferingDefinition toEscapedModel() {
		return new OfferingDefinitionWrapper(_offeringDefinition.toEscapedModel());
	}

	@Override
	public OfferingDefinition toUnescapedModel() {
		return new OfferingDefinitionWrapper(_offeringDefinition.toUnescapedModel());
	}

	@Override
	public ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringDefinition.getProductEntry();
	}

	@Override
	public SupportResponse getSupportResponse()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _offeringDefinition.getSupportResponse();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OfferingDefinition> toCacheModel() {
		return _offeringDefinition.toCacheModel();
	}

	@Override
	public int compareTo(OfferingDefinition offeringDefinition) {
		return _offeringDefinition.compareTo(offeringDefinition);
	}

	@Override
	public int getQuantity() {
		return _offeringDefinition.getQuantity();
	}

	@Override
	public int hashCode() {
		return _offeringDefinition.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _offeringDefinition.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new OfferingDefinitionWrapper((OfferingDefinition)_offeringDefinition.clone());
	}

	@Override
	public java.lang.String getLicensesLabel() {
		return _offeringDefinition.getLicensesLabel();
	}

	@Override
	public java.lang.String getMaxConcurrentUsersLabel() {
		return _offeringDefinition.getMaxConcurrentUsersLabel();
	}

	@Override
	public java.lang.String getMaxUsersLabel() {
		return _offeringDefinition.getMaxUsersLabel();
	}

	/**
	* Returns the product description of this offering definition.
	*
	* @return the product description of this offering definition
	*/
	@Override
	public java.lang.String getProductDescription() {
		return _offeringDefinition.getProductDescription();
	}

	@Override
	public java.lang.String getSupportTicketsLabel() {
		return _offeringDefinition.getSupportTicketsLabel();
	}

	/**
	* Returns the user name of this offering definition.
	*
	* @return the user name of this offering definition
	*/
	@Override
	public java.lang.String getUserName() {
		return _offeringDefinition.getUserName();
	}

	/**
	* Returns the user uuid of this offering definition.
	*
	* @return the user uuid of this offering definition
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _offeringDefinition.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _offeringDefinition.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _offeringDefinition.toXmlString();
	}

	/**
	* Returns the create date of this offering definition.
	*
	* @return the create date of this offering definition
	*/
	@Override
	public Date getCreateDate() {
		return _offeringDefinition.getCreateDate();
	}

	/**
	* Returns the modified date of this offering definition.
	*
	* @return the modified date of this offering definition
	*/
	@Override
	public Date getModifiedDate() {
		return _offeringDefinition.getModifiedDate();
	}

	/**
	* Returns the company ID of this offering definition.
	*
	* @return the company ID of this offering definition
	*/
	@Override
	public long getCompanyId() {
		return _offeringDefinition.getCompanyId();
	}

	/**
	* Returns the max concurrent users of this offering definition.
	*
	* @return the max concurrent users of this offering definition
	*/
	@Override
	public long getMaxConcurrentUsers() {
		return _offeringDefinition.getMaxConcurrentUsers();
	}

	/**
	* Returns the max users of this offering definition.
	*
	* @return the max users of this offering definition
	*/
	@Override
	public long getMaxUsers() {
		return _offeringDefinition.getMaxUsers();
	}

	/**
	* Returns the offering definition ID of this offering definition.
	*
	* @return the offering definition ID of this offering definition
	*/
	@Override
	public long getOfferingDefinitionId() {
		return _offeringDefinition.getOfferingDefinitionId();
	}

	/**
	* Returns the primary key of this offering definition.
	*
	* @return the primary key of this offering definition
	*/
	@Override
	public long getPrimaryKey() {
		return _offeringDefinition.getPrimaryKey();
	}

	/**
	* Returns the product entry ID of this offering definition.
	*
	* @return the product entry ID of this offering definition
	*/
	@Override
	public long getProductEntryId() {
		return _offeringDefinition.getProductEntryId();
	}

	/**
	* Returns the support response ID of this offering definition.
	*
	* @return the support response ID of this offering definition
	*/
	@Override
	public long getSupportResponseId() {
		return _offeringDefinition.getSupportResponseId();
	}

	/**
	* Returns the user ID of this offering definition.
	*
	* @return the user ID of this offering definition
	*/
	@Override
	public long getUserId() {
		return _offeringDefinition.getUserId();
	}

	@Override
	public void persist() {
		_offeringDefinition.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_offeringDefinition.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this offering definition.
	*
	* @param companyId the company ID of this offering definition
	*/
	@Override
	public void setCompanyId(long companyId) {
		_offeringDefinition.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this offering definition.
	*
	* @param createDate the create date of this offering definition
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_offeringDefinition.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_offeringDefinition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_offeringDefinition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_offeringDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this offering definition is licenses.
	*
	* @param licenses the licenses of this offering definition
	*/
	@Override
	public void setLicenses(boolean licenses) {
		_offeringDefinition.setLicenses(licenses);
	}

	/**
	* Sets the max concurrent users of this offering definition.
	*
	* @param maxConcurrentUsers the max concurrent users of this offering definition
	*/
	@Override
	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		_offeringDefinition.setMaxConcurrentUsers(maxConcurrentUsers);
	}

	/**
	* Sets the max users of this offering definition.
	*
	* @param maxUsers the max users of this offering definition
	*/
	@Override
	public void setMaxUsers(long maxUsers) {
		_offeringDefinition.setMaxUsers(maxUsers);
	}

	/**
	* Sets the modified date of this offering definition.
	*
	* @param modifiedDate the modified date of this offering definition
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_offeringDefinition.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_offeringDefinition.setNew(n);
	}

	/**
	* Sets the offering definition ID of this offering definition.
	*
	* @param offeringDefinitionId the offering definition ID of this offering definition
	*/
	@Override
	public void setOfferingDefinitionId(long offeringDefinitionId) {
		_offeringDefinition.setOfferingDefinitionId(offeringDefinitionId);
	}

	/**
	* Sets the primary key of this offering definition.
	*
	* @param primaryKey the primary key of this offering definition
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_offeringDefinition.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_offeringDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product description of this offering definition.
	*
	* @param productDescription the product description of this offering definition
	*/
	@Override
	public void setProductDescription(java.lang.String productDescription) {
		_offeringDefinition.setProductDescription(productDescription);
	}

	/**
	* Sets the product entry ID of this offering definition.
	*
	* @param productEntryId the product entry ID of this offering definition
	*/
	@Override
	public void setProductEntryId(long productEntryId) {
		_offeringDefinition.setProductEntryId(productEntryId);
	}

	/**
	* Sets the support response ID of this offering definition.
	*
	* @param supportResponseId the support response ID of this offering definition
	*/
	@Override
	public void setSupportResponseId(long supportResponseId) {
		_offeringDefinition.setSupportResponseId(supportResponseId);
	}

	/**
	* Sets whether this offering definition is support tickets.
	*
	* @param supportTickets the support tickets of this offering definition
	*/
	@Override
	public void setSupportTickets(boolean supportTickets) {
		_offeringDefinition.setSupportTickets(supportTickets);
	}

	/**
	* Sets whether this offering definition is unlimited licenses.
	*
	* @param unlimitedLicenses the unlimited licenses of this offering definition
	*/
	@Override
	public void setUnlimitedLicenses(boolean unlimitedLicenses) {
		_offeringDefinition.setUnlimitedLicenses(unlimitedLicenses);
	}

	/**
	* Sets the user ID of this offering definition.
	*
	* @param userId the user ID of this offering definition
	*/
	@Override
	public void setUserId(long userId) {
		_offeringDefinition.setUserId(userId);
	}

	/**
	* Sets the user name of this offering definition.
	*
	* @param userName the user name of this offering definition
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_offeringDefinition.setUserName(userName);
	}

	/**
	* Sets the user uuid of this offering definition.
	*
	* @param userUuid the user uuid of this offering definition
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_offeringDefinition.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfferingDefinitionWrapper)) {
			return false;
		}

		OfferingDefinitionWrapper offeringDefinitionWrapper = (OfferingDefinitionWrapper)obj;

		if (Objects.equals(_offeringDefinition,
					offeringDefinitionWrapper._offeringDefinition)) {
			return true;
		}

		return false;
	}

	@Override
	public OfferingDefinition getWrappedModel() {
		return _offeringDefinition;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _offeringDefinition.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _offeringDefinition.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_offeringDefinition.resetOriginalValues();
	}

	private final OfferingDefinition _offeringDefinition;
}