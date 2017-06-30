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
 * This class is a wrapper for {@link AssetAudit}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetAudit
 * @generated
 */
public class AssetAuditWrapper implements AssetAudit, ModelWrapper<AssetAudit> {
	public AssetAuditWrapper(AssetAudit assetAudit) {
		_assetAudit = assetAudit;
	}

	public Class<?> getModelClass() {
		return AssetAudit.class;
	}

	public String getModelClassName() {
		return AssetAudit.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetAuditId", getAssetAuditId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("legalEntityName", getLegalEntityName());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("vendorClassNameId", getVendorClassNameId());
		attributes.put("vendorClassPK", getVendorClassPK());
		attributes.put("type", getType());
		attributes.put("domain", getDomain());
		attributes.put("time", getTime());
		attributes.put("currencyCode", getCurrencyCode());
		attributes.put("price", getPrice());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetAuditId = (Long)attributes.get("assetAuditId");

		if (assetAuditId != null) {
			setAssetAuditId(assetAuditId);
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

		String legalEntityName = (String)attributes.get("legalEntityName");

		if (legalEntityName != null) {
			setLegalEntityName(legalEntityName);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long vendorClassNameId = (Long)attributes.get("vendorClassNameId");

		if (vendorClassNameId != null) {
			setVendorClassNameId(vendorClassNameId);
		}

		Long vendorClassPK = (Long)attributes.get("vendorClassPK");

		if (vendorClassPK != null) {
			setVendorClassPK(vendorClassPK);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer domain = (Integer)attributes.get("domain");

		if (domain != null) {
			setDomain(domain);
		}

		Long time = (Long)attributes.get("time");

		if (time != null) {
			setTime(time);
		}

		String currencyCode = (String)attributes.get("currencyCode");

		if (currencyCode != null) {
			setCurrencyCode(currencyCode);
		}

		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}
	}

	/**
	* Returns the primary key of this asset audit.
	*
	* @return the primary key of this asset audit
	*/
	public long getPrimaryKey() {
		return _assetAudit.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset audit.
	*
	* @param primaryKey the primary key of this asset audit
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetAudit.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset audit ID of this asset audit.
	*
	* @return the asset audit ID of this asset audit
	*/
	public long getAssetAuditId() {
		return _assetAudit.getAssetAuditId();
	}

	/**
	* Sets the asset audit ID of this asset audit.
	*
	* @param assetAuditId the asset audit ID of this asset audit
	*/
	public void setAssetAuditId(long assetAuditId) {
		_assetAudit.setAssetAuditId(assetAuditId);
	}

	/**
	* Returns the company ID of this asset audit.
	*
	* @return the company ID of this asset audit
	*/
	public long getCompanyId() {
		return _assetAudit.getCompanyId();
	}

	/**
	* Sets the company ID of this asset audit.
	*
	* @param companyId the company ID of this asset audit
	*/
	public void setCompanyId(long companyId) {
		_assetAudit.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this asset audit.
	*
	* @return the user ID of this asset audit
	*/
	public long getUserId() {
		return _assetAudit.getUserId();
	}

	/**
	* Sets the user ID of this asset audit.
	*
	* @param userId the user ID of this asset audit
	*/
	public void setUserId(long userId) {
		_assetAudit.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset audit.
	*
	* @return the user uuid of this asset audit
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAudit.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset audit.
	*
	* @param userUuid the user uuid of this asset audit
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_assetAudit.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this asset audit.
	*
	* @return the user name of this asset audit
	*/
	public java.lang.String getUserName() {
		return _assetAudit.getUserName();
	}

	/**
	* Sets the user name of this asset audit.
	*
	* @param userName the user name of this asset audit
	*/
	public void setUserName(java.lang.String userName) {
		_assetAudit.setUserName(userName);
	}

	/**
	* Returns the create date of this asset audit.
	*
	* @return the create date of this asset audit
	*/
	public java.util.Date getCreateDate() {
		return _assetAudit.getCreateDate();
	}

	/**
	* Sets the create date of this asset audit.
	*
	* @param createDate the create date of this asset audit
	*/
	public void setCreateDate(java.util.Date createDate) {
		_assetAudit.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this asset audit.
	*
	* @return the modified date of this asset audit
	*/
	public java.util.Date getModifiedDate() {
		return _assetAudit.getModifiedDate();
	}

	/**
	* Sets the modified date of this asset audit.
	*
	* @param modifiedDate the modified date of this asset audit
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_assetAudit.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the legal entity name of this asset audit.
	*
	* @return the legal entity name of this asset audit
	*/
	public java.lang.String getLegalEntityName() {
		return _assetAudit.getLegalEntityName();
	}

	/**
	* Sets the legal entity name of this asset audit.
	*
	* @param legalEntityName the legal entity name of this asset audit
	*/
	public void setLegalEntityName(java.lang.String legalEntityName) {
		_assetAudit.setLegalEntityName(legalEntityName);
	}

	/**
	* Returns the fully qualified class name of this asset audit.
	*
	* @return the fully qualified class name of this asset audit
	*/
	public java.lang.String getClassName() {
		return _assetAudit.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_assetAudit.setClassName(className);
	}

	/**
	* Returns the class name ID of this asset audit.
	*
	* @return the class name ID of this asset audit
	*/
	public long getClassNameId() {
		return _assetAudit.getClassNameId();
	}

	/**
	* Sets the class name ID of this asset audit.
	*
	* @param classNameId the class name ID of this asset audit
	*/
	public void setClassNameId(long classNameId) {
		_assetAudit.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this asset audit.
	*
	* @return the class p k of this asset audit
	*/
	public long getClassPK() {
		return _assetAudit.getClassPK();
	}

	/**
	* Sets the class p k of this asset audit.
	*
	* @param classPK the class p k of this asset audit
	*/
	public void setClassPK(long classPK) {
		_assetAudit.setClassPK(classPK);
	}

	/**
	* Returns the vendor class name ID of this asset audit.
	*
	* @return the vendor class name ID of this asset audit
	*/
	public long getVendorClassNameId() {
		return _assetAudit.getVendorClassNameId();
	}

	/**
	* Sets the vendor class name ID of this asset audit.
	*
	* @param vendorClassNameId the vendor class name ID of this asset audit
	*/
	public void setVendorClassNameId(long vendorClassNameId) {
		_assetAudit.setVendorClassNameId(vendorClassNameId);
	}

	/**
	* Returns the vendor class p k of this asset audit.
	*
	* @return the vendor class p k of this asset audit
	*/
	public long getVendorClassPK() {
		return _assetAudit.getVendorClassPK();
	}

	/**
	* Sets the vendor class p k of this asset audit.
	*
	* @param vendorClassPK the vendor class p k of this asset audit
	*/
	public void setVendorClassPK(long vendorClassPK) {
		_assetAudit.setVendorClassPK(vendorClassPK);
	}

	/**
	* Returns the type of this asset audit.
	*
	* @return the type of this asset audit
	*/
	public int getType() {
		return _assetAudit.getType();
	}

	/**
	* Sets the type of this asset audit.
	*
	* @param type the type of this asset audit
	*/
	public void setType(int type) {
		_assetAudit.setType(type);
	}

	/**
	* Returns the domain of this asset audit.
	*
	* @return the domain of this asset audit
	*/
	public int getDomain() {
		return _assetAudit.getDomain();
	}

	/**
	* Sets the domain of this asset audit.
	*
	* @param domain the domain of this asset audit
	*/
	public void setDomain(int domain) {
		_assetAudit.setDomain(domain);
	}

	/**
	* Returns the time of this asset audit.
	*
	* @return the time of this asset audit
	*/
	public long getTime() {
		return _assetAudit.getTime();
	}

	/**
	* Sets the time of this asset audit.
	*
	* @param time the time of this asset audit
	*/
	public void setTime(long time) {
		_assetAudit.setTime(time);
	}

	/**
	* Returns the currency code of this asset audit.
	*
	* @return the currency code of this asset audit
	*/
	public java.lang.String getCurrencyCode() {
		return _assetAudit.getCurrencyCode();
	}

	/**
	* Sets the currency code of this asset audit.
	*
	* @param currencyCode the currency code of this asset audit
	*/
	public void setCurrencyCode(java.lang.String currencyCode) {
		_assetAudit.setCurrencyCode(currencyCode);
	}

	/**
	* Returns the price of this asset audit.
	*
	* @return the price of this asset audit
	*/
	public double getPrice() {
		return _assetAudit.getPrice();
	}

	/**
	* Sets the price of this asset audit.
	*
	* @param price the price of this asset audit
	*/
	public void setPrice(double price) {
		_assetAudit.setPrice(price);
	}

	public boolean isNew() {
		return _assetAudit.isNew();
	}

	public void setNew(boolean n) {
		_assetAudit.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetAudit.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetAudit.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetAudit.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetAudit.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetAudit.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetAudit.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetAuditWrapper((AssetAudit)_assetAudit.clone());
	}

	public int compareTo(com.liferay.osb.model.AssetAudit assetAudit) {
		return _assetAudit.compareTo(assetAudit);
	}

	@Override
	public int hashCode() {
		return _assetAudit.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetAudit> toCacheModel() {
		return _assetAudit.toCacheModel();
	}

	public com.liferay.osb.model.AssetAudit toEscapedModel() {
		return new AssetAuditWrapper(_assetAudit.toEscapedModel());
	}

	public com.liferay.osb.model.AssetAudit toUnescapedModel() {
		return new AssetAuditWrapper(_assetAudit.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetAudit.toString();
	}

	public java.lang.String toXmlString() {
		return _assetAudit.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetAudit.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetAuditWrapper)) {
			return false;
		}

		AssetAuditWrapper assetAuditWrapper = (AssetAuditWrapper)obj;

		if (Validator.equals(_assetAudit, assetAuditWrapper._assetAudit)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetAudit getWrappedAssetAudit() {
		return _assetAudit;
	}

	public AssetAudit getWrappedModel() {
		return _assetAudit;
	}

	public void resetOriginalValues() {
		_assetAudit.resetOriginalValues();
	}

	private AssetAudit _assetAudit;
}