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
 * This class is a wrapper for {@link OfferingDefinition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OfferingDefinition
 * @generated
 */
public class OfferingDefinitionWrapper implements OfferingDefinition,
	ModelWrapper<OfferingDefinition> {
	public OfferingDefinitionWrapper(OfferingDefinition offeringDefinition) {
		_offeringDefinition = offeringDefinition;
	}

	public Class<?> getModelClass() {
		return OfferingDefinition.class;
	}

	public String getModelClassName() {
		return OfferingDefinition.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("offeringDefinitionId", getOfferingDefinitionId());
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

	public void setModelAttributes(Map<String, Object> attributes) {
		Long offeringDefinitionId = (Long)attributes.get("offeringDefinitionId");

		if (offeringDefinitionId != null) {
			setOfferingDefinitionId(offeringDefinitionId);
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
	* Returns the primary key of this offering definition.
	*
	* @return the primary key of this offering definition
	*/
	public long getPrimaryKey() {
		return _offeringDefinition.getPrimaryKey();
	}

	/**
	* Sets the primary key of this offering definition.
	*
	* @param primaryKey the primary key of this offering definition
	*/
	public void setPrimaryKey(long primaryKey) {
		_offeringDefinition.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the offering definition ID of this offering definition.
	*
	* @return the offering definition ID of this offering definition
	*/
	public long getOfferingDefinitionId() {
		return _offeringDefinition.getOfferingDefinitionId();
	}

	/**
	* Sets the offering definition ID of this offering definition.
	*
	* @param offeringDefinitionId the offering definition ID of this offering definition
	*/
	public void setOfferingDefinitionId(long offeringDefinitionId) {
		_offeringDefinition.setOfferingDefinitionId(offeringDefinitionId);
	}

	/**
	* Returns the user ID of this offering definition.
	*
	* @return the user ID of this offering definition
	*/
	public long getUserId() {
		return _offeringDefinition.getUserId();
	}

	/**
	* Sets the user ID of this offering definition.
	*
	* @param userId the user ID of this offering definition
	*/
	public void setUserId(long userId) {
		_offeringDefinition.setUserId(userId);
	}

	/**
	* Returns the user uuid of this offering definition.
	*
	* @return the user uuid of this offering definition
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinition.getUserUuid();
	}

	/**
	* Sets the user uuid of this offering definition.
	*
	* @param userUuid the user uuid of this offering definition
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_offeringDefinition.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this offering definition.
	*
	* @return the user name of this offering definition
	*/
	public java.lang.String getUserName() {
		return _offeringDefinition.getUserName();
	}

	/**
	* Sets the user name of this offering definition.
	*
	* @param userName the user name of this offering definition
	*/
	public void setUserName(java.lang.String userName) {
		_offeringDefinition.setUserName(userName);
	}

	/**
	* Returns the create date of this offering definition.
	*
	* @return the create date of this offering definition
	*/
	public java.util.Date getCreateDate() {
		return _offeringDefinition.getCreateDate();
	}

	/**
	* Sets the create date of this offering definition.
	*
	* @param createDate the create date of this offering definition
	*/
	public void setCreateDate(java.util.Date createDate) {
		_offeringDefinition.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this offering definition.
	*
	* @return the modified date of this offering definition
	*/
	public java.util.Date getModifiedDate() {
		return _offeringDefinition.getModifiedDate();
	}

	/**
	* Sets the modified date of this offering definition.
	*
	* @param modifiedDate the modified date of this offering definition
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_offeringDefinition.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the product entry ID of this offering definition.
	*
	* @return the product entry ID of this offering definition
	*/
	public long getProductEntryId() {
		return _offeringDefinition.getProductEntryId();
	}

	/**
	* Sets the product entry ID of this offering definition.
	*
	* @param productEntryId the product entry ID of this offering definition
	*/
	public void setProductEntryId(long productEntryId) {
		_offeringDefinition.setProductEntryId(productEntryId);
	}

	/**
	* Returns the support response ID of this offering definition.
	*
	* @return the support response ID of this offering definition
	*/
	public long getSupportResponseId() {
		return _offeringDefinition.getSupportResponseId();
	}

	/**
	* Sets the support response ID of this offering definition.
	*
	* @param supportResponseId the support response ID of this offering definition
	*/
	public void setSupportResponseId(long supportResponseId) {
		_offeringDefinition.setSupportResponseId(supportResponseId);
	}

	/**
	* Returns the product description of this offering definition.
	*
	* @return the product description of this offering definition
	*/
	public java.lang.String getProductDescription() {
		return _offeringDefinition.getProductDescription();
	}

	/**
	* Sets the product description of this offering definition.
	*
	* @param productDescription the product description of this offering definition
	*/
	public void setProductDescription(java.lang.String productDescription) {
		_offeringDefinition.setProductDescription(productDescription);
	}

	/**
	* Returns the licenses of this offering definition.
	*
	* @return the licenses of this offering definition
	*/
	public boolean getLicenses() {
		return _offeringDefinition.getLicenses();
	}

	/**
	* Returns <code>true</code> if this offering definition is licenses.
	*
	* @return <code>true</code> if this offering definition is licenses; <code>false</code> otherwise
	*/
	public boolean isLicenses() {
		return _offeringDefinition.isLicenses();
	}

	/**
	* Sets whether this offering definition is licenses.
	*
	* @param licenses the licenses of this offering definition
	*/
	public void setLicenses(boolean licenses) {
		_offeringDefinition.setLicenses(licenses);
	}

	/**
	* Returns the unlimited licenses of this offering definition.
	*
	* @return the unlimited licenses of this offering definition
	*/
	public boolean getUnlimitedLicenses() {
		return _offeringDefinition.getUnlimitedLicenses();
	}

	/**
	* Returns <code>true</code> if this offering definition is unlimited licenses.
	*
	* @return <code>true</code> if this offering definition is unlimited licenses; <code>false</code> otherwise
	*/
	public boolean isUnlimitedLicenses() {
		return _offeringDefinition.isUnlimitedLicenses();
	}

	/**
	* Sets whether this offering definition is unlimited licenses.
	*
	* @param unlimitedLicenses the unlimited licenses of this offering definition
	*/
	public void setUnlimitedLicenses(boolean unlimitedLicenses) {
		_offeringDefinition.setUnlimitedLicenses(unlimitedLicenses);
	}

	/**
	* Returns the max concurrent users of this offering definition.
	*
	* @return the max concurrent users of this offering definition
	*/
	public long getMaxConcurrentUsers() {
		return _offeringDefinition.getMaxConcurrentUsers();
	}

	/**
	* Sets the max concurrent users of this offering definition.
	*
	* @param maxConcurrentUsers the max concurrent users of this offering definition
	*/
	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		_offeringDefinition.setMaxConcurrentUsers(maxConcurrentUsers);
	}

	/**
	* Returns the max users of this offering definition.
	*
	* @return the max users of this offering definition
	*/
	public long getMaxUsers() {
		return _offeringDefinition.getMaxUsers();
	}

	/**
	* Sets the max users of this offering definition.
	*
	* @param maxUsers the max users of this offering definition
	*/
	public void setMaxUsers(long maxUsers) {
		_offeringDefinition.setMaxUsers(maxUsers);
	}

	/**
	* Returns the support tickets of this offering definition.
	*
	* @return the support tickets of this offering definition
	*/
	public boolean getSupportTickets() {
		return _offeringDefinition.getSupportTickets();
	}

	/**
	* Returns <code>true</code> if this offering definition is support tickets.
	*
	* @return <code>true</code> if this offering definition is support tickets; <code>false</code> otherwise
	*/
	public boolean isSupportTickets() {
		return _offeringDefinition.isSupportTickets();
	}

	/**
	* Sets whether this offering definition is support tickets.
	*
	* @param supportTickets the support tickets of this offering definition
	*/
	public void setSupportTickets(boolean supportTickets) {
		_offeringDefinition.setSupportTickets(supportTickets);
	}

	public boolean isNew() {
		return _offeringDefinition.isNew();
	}

	public void setNew(boolean n) {
		_offeringDefinition.setNew(n);
	}

	public boolean isCachedModel() {
		return _offeringDefinition.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_offeringDefinition.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _offeringDefinition.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _offeringDefinition.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_offeringDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _offeringDefinition.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_offeringDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OfferingDefinitionWrapper((OfferingDefinition)_offeringDefinition.clone());
	}

	public int compareTo(
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		return _offeringDefinition.compareTo(offeringDefinition);
	}

	@Override
	public int hashCode() {
		return _offeringDefinition.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.OfferingDefinition> toCacheModel() {
		return _offeringDefinition.toCacheModel();
	}

	public com.liferay.osb.model.OfferingDefinition toEscapedModel() {
		return new OfferingDefinitionWrapper(_offeringDefinition.toEscapedModel());
	}

	public com.liferay.osb.model.OfferingDefinition toUnescapedModel() {
		return new OfferingDefinitionWrapper(_offeringDefinition.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _offeringDefinition.toString();
	}

	public java.lang.String toXmlString() {
		return _offeringDefinition.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringDefinition.persist();
	}

	public java.lang.String getLicensesLabel() {
		return _offeringDefinition.getLicensesLabel();
	}

	public java.lang.String getMaxConcurrentUsersLabel() {
		return _offeringDefinition.getMaxConcurrentUsersLabel();
	}

	public java.lang.String getMaxUsersLabel() {
		return _offeringDefinition.getMaxUsersLabel();
	}

	public com.liferay.osb.model.ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinition.getProductEntry();
	}

	public int getQuantity() {
		return _offeringDefinition.getQuantity();
	}

	public com.liferay.osb.model.SupportResponse getSupportResponse()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _offeringDefinition.getSupportResponse();
	}

	public java.lang.String getSupportTicketsLabel() {
		return _offeringDefinition.getSupportTicketsLabel();
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

		if (Validator.equals(_offeringDefinition,
					offeringDefinitionWrapper._offeringDefinition)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public OfferingDefinition getWrappedOfferingDefinition() {
		return _offeringDefinition;
	}

	public OfferingDefinition getWrappedModel() {
		return _offeringDefinition;
	}

	public void resetOriginalValues() {
		_offeringDefinition.resetOriginalValues();
	}

	private OfferingDefinition _offeringDefinition;
}