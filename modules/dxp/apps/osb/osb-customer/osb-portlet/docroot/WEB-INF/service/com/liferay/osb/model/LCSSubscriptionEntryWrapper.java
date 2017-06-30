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
 * This class is a wrapper for {@link LCSSubscriptionEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LCSSubscriptionEntry
 * @generated
 */
public class LCSSubscriptionEntryWrapper implements LCSSubscriptionEntry,
	ModelWrapper<LCSSubscriptionEntry> {
	public LCSSubscriptionEntryWrapper(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		_lcsSubscriptionEntry = lcsSubscriptionEntry;
	}

	public Class<?> getModelClass() {
		return LCSSubscriptionEntry.class;
	}

	public String getModelClassName() {
		return LCSSubscriptionEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsSubscriptionEntryId", getLcsSubscriptionEntryId());
		attributes.put("lcsProjectId", getLcsProjectId());
		attributes.put("product", getProduct());
		attributes.put("productVersion", getProductVersion());
		attributes.put("type", getType());
		attributes.put("platform", getPlatform());
		attributes.put("platformVersion", getPlatformVersion());
		attributes.put("serversAllowed", getServersAllowed());
		attributes.put("serversUsed", getServersUsed());
		attributes.put("instanceSize", getInstanceSize());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("supportStartDate", getSupportStartDate());
		attributes.put("supportEndDate", getSupportEndDate());
		attributes.put("actualPrice", getActualPrice());
		attributes.put("currencyCode", getCurrencyCode());
		attributes.put("active", getActive());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsSubscriptionEntryId = (Long)attributes.get(
				"lcsSubscriptionEntryId");

		if (lcsSubscriptionEntryId != null) {
			setLcsSubscriptionEntryId(lcsSubscriptionEntryId);
		}

		Long lcsProjectId = (Long)attributes.get("lcsProjectId");

		if (lcsProjectId != null) {
			setLcsProjectId(lcsProjectId);
		}

		String product = (String)attributes.get("product");

		if (product != null) {
			setProduct(product);
		}

		Integer productVersion = (Integer)attributes.get("productVersion");

		if (productVersion != null) {
			setProductVersion(productVersion);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String platform = (String)attributes.get("platform");

		if (platform != null) {
			setPlatform(platform);
		}

		String platformVersion = (String)attributes.get("platformVersion");

		if (platformVersion != null) {
			setPlatformVersion(platformVersion);
		}

		Integer serversAllowed = (Integer)attributes.get("serversAllowed");

		if (serversAllowed != null) {
			setServersAllowed(serversAllowed);
		}

		Integer serversUsed = (Integer)attributes.get("serversUsed");

		if (serversUsed != null) {
			setServersUsed(serversUsed);
		}

		Integer instanceSize = (Integer)attributes.get("instanceSize");

		if (instanceSize != null) {
			setInstanceSize(instanceSize);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date supportStartDate = (Date)attributes.get("supportStartDate");

		if (supportStartDate != null) {
			setSupportStartDate(supportStartDate);
		}

		Date supportEndDate = (Date)attributes.get("supportEndDate");

		if (supportEndDate != null) {
			setSupportEndDate(supportEndDate);
		}

		Double actualPrice = (Double)attributes.get("actualPrice");

		if (actualPrice != null) {
			setActualPrice(actualPrice);
		}

		String currencyCode = (String)attributes.get("currencyCode");

		if (currencyCode != null) {
			setCurrencyCode(currencyCode);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	/**
	* Returns the primary key of this l c s subscription entry.
	*
	* @return the primary key of this l c s subscription entry
	*/
	public long getPrimaryKey() {
		return _lcsSubscriptionEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this l c s subscription entry.
	*
	* @param primaryKey the primary key of this l c s subscription entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_lcsSubscriptionEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the lcs subscription entry ID of this l c s subscription entry.
	*
	* @return the lcs subscription entry ID of this l c s subscription entry
	*/
	public long getLcsSubscriptionEntryId() {
		return _lcsSubscriptionEntry.getLcsSubscriptionEntryId();
	}

	/**
	* Sets the lcs subscription entry ID of this l c s subscription entry.
	*
	* @param lcsSubscriptionEntryId the lcs subscription entry ID of this l c s subscription entry
	*/
	public void setLcsSubscriptionEntryId(long lcsSubscriptionEntryId) {
		_lcsSubscriptionEntry.setLcsSubscriptionEntryId(lcsSubscriptionEntryId);
	}

	/**
	* Returns the lcs project ID of this l c s subscription entry.
	*
	* @return the lcs project ID of this l c s subscription entry
	*/
	public long getLcsProjectId() {
		return _lcsSubscriptionEntry.getLcsProjectId();
	}

	/**
	* Sets the lcs project ID of this l c s subscription entry.
	*
	* @param lcsProjectId the lcs project ID of this l c s subscription entry
	*/
	public void setLcsProjectId(long lcsProjectId) {
		_lcsSubscriptionEntry.setLcsProjectId(lcsProjectId);
	}

	/**
	* Returns the product of this l c s subscription entry.
	*
	* @return the product of this l c s subscription entry
	*/
	public java.lang.String getProduct() {
		return _lcsSubscriptionEntry.getProduct();
	}

	/**
	* Sets the product of this l c s subscription entry.
	*
	* @param product the product of this l c s subscription entry
	*/
	public void setProduct(java.lang.String product) {
		_lcsSubscriptionEntry.setProduct(product);
	}

	/**
	* Returns the product version of this l c s subscription entry.
	*
	* @return the product version of this l c s subscription entry
	*/
	public int getProductVersion() {
		return _lcsSubscriptionEntry.getProductVersion();
	}

	/**
	* Sets the product version of this l c s subscription entry.
	*
	* @param productVersion the product version of this l c s subscription entry
	*/
	public void setProductVersion(int productVersion) {
		_lcsSubscriptionEntry.setProductVersion(productVersion);
	}

	/**
	* Returns the type of this l c s subscription entry.
	*
	* @return the type of this l c s subscription entry
	*/
	public java.lang.String getType() {
		return _lcsSubscriptionEntry.getType();
	}

	/**
	* Sets the type of this l c s subscription entry.
	*
	* @param type the type of this l c s subscription entry
	*/
	public void setType(java.lang.String type) {
		_lcsSubscriptionEntry.setType(type);
	}

	/**
	* Returns the platform of this l c s subscription entry.
	*
	* @return the platform of this l c s subscription entry
	*/
	public java.lang.String getPlatform() {
		return _lcsSubscriptionEntry.getPlatform();
	}

	/**
	* Sets the platform of this l c s subscription entry.
	*
	* @param platform the platform of this l c s subscription entry
	*/
	public void setPlatform(java.lang.String platform) {
		_lcsSubscriptionEntry.setPlatform(platform);
	}

	/**
	* Returns the platform version of this l c s subscription entry.
	*
	* @return the platform version of this l c s subscription entry
	*/
	public java.lang.String getPlatformVersion() {
		return _lcsSubscriptionEntry.getPlatformVersion();
	}

	/**
	* Sets the platform version of this l c s subscription entry.
	*
	* @param platformVersion the platform version of this l c s subscription entry
	*/
	public void setPlatformVersion(java.lang.String platformVersion) {
		_lcsSubscriptionEntry.setPlatformVersion(platformVersion);
	}

	/**
	* Returns the servers allowed of this l c s subscription entry.
	*
	* @return the servers allowed of this l c s subscription entry
	*/
	public int getServersAllowed() {
		return _lcsSubscriptionEntry.getServersAllowed();
	}

	/**
	* Sets the servers allowed of this l c s subscription entry.
	*
	* @param serversAllowed the servers allowed of this l c s subscription entry
	*/
	public void setServersAllowed(int serversAllowed) {
		_lcsSubscriptionEntry.setServersAllowed(serversAllowed);
	}

	/**
	* Returns the servers used of this l c s subscription entry.
	*
	* @return the servers used of this l c s subscription entry
	*/
	public int getServersUsed() {
		return _lcsSubscriptionEntry.getServersUsed();
	}

	/**
	* Sets the servers used of this l c s subscription entry.
	*
	* @param serversUsed the servers used of this l c s subscription entry
	*/
	public void setServersUsed(int serversUsed) {
		_lcsSubscriptionEntry.setServersUsed(serversUsed);
	}

	/**
	* Returns the instance size of this l c s subscription entry.
	*
	* @return the instance size of this l c s subscription entry
	*/
	public int getInstanceSize() {
		return _lcsSubscriptionEntry.getInstanceSize();
	}

	/**
	* Sets the instance size of this l c s subscription entry.
	*
	* @param instanceSize the instance size of this l c s subscription entry
	*/
	public void setInstanceSize(int instanceSize) {
		_lcsSubscriptionEntry.setInstanceSize(instanceSize);
	}

	/**
	* Returns the start date of this l c s subscription entry.
	*
	* @return the start date of this l c s subscription entry
	*/
	public java.util.Date getStartDate() {
		return _lcsSubscriptionEntry.getStartDate();
	}

	/**
	* Sets the start date of this l c s subscription entry.
	*
	* @param startDate the start date of this l c s subscription entry
	*/
	public void setStartDate(java.util.Date startDate) {
		_lcsSubscriptionEntry.setStartDate(startDate);
	}

	/**
	* Returns the end date of this l c s subscription entry.
	*
	* @return the end date of this l c s subscription entry
	*/
	public java.util.Date getEndDate() {
		return _lcsSubscriptionEntry.getEndDate();
	}

	/**
	* Sets the end date of this l c s subscription entry.
	*
	* @param endDate the end date of this l c s subscription entry
	*/
	public void setEndDate(java.util.Date endDate) {
		_lcsSubscriptionEntry.setEndDate(endDate);
	}

	/**
	* Returns the support start date of this l c s subscription entry.
	*
	* @return the support start date of this l c s subscription entry
	*/
	public java.util.Date getSupportStartDate() {
		return _lcsSubscriptionEntry.getSupportStartDate();
	}

	/**
	* Sets the support start date of this l c s subscription entry.
	*
	* @param supportStartDate the support start date of this l c s subscription entry
	*/
	public void setSupportStartDate(java.util.Date supportStartDate) {
		_lcsSubscriptionEntry.setSupportStartDate(supportStartDate);
	}

	/**
	* Returns the support end date of this l c s subscription entry.
	*
	* @return the support end date of this l c s subscription entry
	*/
	public java.util.Date getSupportEndDate() {
		return _lcsSubscriptionEntry.getSupportEndDate();
	}

	/**
	* Sets the support end date of this l c s subscription entry.
	*
	* @param supportEndDate the support end date of this l c s subscription entry
	*/
	public void setSupportEndDate(java.util.Date supportEndDate) {
		_lcsSubscriptionEntry.setSupportEndDate(supportEndDate);
	}

	/**
	* Returns the actual price of this l c s subscription entry.
	*
	* @return the actual price of this l c s subscription entry
	*/
	public double getActualPrice() {
		return _lcsSubscriptionEntry.getActualPrice();
	}

	/**
	* Sets the actual price of this l c s subscription entry.
	*
	* @param actualPrice the actual price of this l c s subscription entry
	*/
	public void setActualPrice(double actualPrice) {
		_lcsSubscriptionEntry.setActualPrice(actualPrice);
	}

	/**
	* Returns the currency code of this l c s subscription entry.
	*
	* @return the currency code of this l c s subscription entry
	*/
	public java.lang.String getCurrencyCode() {
		return _lcsSubscriptionEntry.getCurrencyCode();
	}

	/**
	* Sets the currency code of this l c s subscription entry.
	*
	* @param currencyCode the currency code of this l c s subscription entry
	*/
	public void setCurrencyCode(java.lang.String currencyCode) {
		_lcsSubscriptionEntry.setCurrencyCode(currencyCode);
	}

	/**
	* Returns the active of this l c s subscription entry.
	*
	* @return the active of this l c s subscription entry
	*/
	public boolean getActive() {
		return _lcsSubscriptionEntry.getActive();
	}

	/**
	* Returns <code>true</code> if this l c s subscription entry is active.
	*
	* @return <code>true</code> if this l c s subscription entry is active; <code>false</code> otherwise
	*/
	public boolean isActive() {
		return _lcsSubscriptionEntry.isActive();
	}

	/**
	* Sets whether this l c s subscription entry is active.
	*
	* @param active the active of this l c s subscription entry
	*/
	public void setActive(boolean active) {
		_lcsSubscriptionEntry.setActive(active);
	}

	public boolean isNew() {
		return _lcsSubscriptionEntry.isNew();
	}

	public void setNew(boolean n) {
		_lcsSubscriptionEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _lcsSubscriptionEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_lcsSubscriptionEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _lcsSubscriptionEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _lcsSubscriptionEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_lcsSubscriptionEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _lcsSubscriptionEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_lcsSubscriptionEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new LCSSubscriptionEntryWrapper((LCSSubscriptionEntry)_lcsSubscriptionEntry.clone());
	}

	public int compareTo(
		com.liferay.osb.model.LCSSubscriptionEntry lcsSubscriptionEntry) {
		return _lcsSubscriptionEntry.compareTo(lcsSubscriptionEntry);
	}

	@Override
	public int hashCode() {
		return _lcsSubscriptionEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.LCSSubscriptionEntry> toCacheModel() {
		return _lcsSubscriptionEntry.toCacheModel();
	}

	public com.liferay.osb.model.LCSSubscriptionEntry toEscapedModel() {
		return new LCSSubscriptionEntryWrapper(_lcsSubscriptionEntry.toEscapedModel());
	}

	public com.liferay.osb.model.LCSSubscriptionEntry toUnescapedModel() {
		return new LCSSubscriptionEntryWrapper(_lcsSubscriptionEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _lcsSubscriptionEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _lcsSubscriptionEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_lcsSubscriptionEntry.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSSubscriptionEntryWrapper)) {
			return false;
		}

		LCSSubscriptionEntryWrapper lcsSubscriptionEntryWrapper = (LCSSubscriptionEntryWrapper)obj;

		if (Validator.equals(_lcsSubscriptionEntry,
					lcsSubscriptionEntryWrapper._lcsSubscriptionEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public LCSSubscriptionEntry getWrappedLCSSubscriptionEntry() {
		return _lcsSubscriptionEntry;
	}

	public LCSSubscriptionEntry getWrappedModel() {
		return _lcsSubscriptionEntry;
	}

	public void resetOriginalValues() {
		_lcsSubscriptionEntry.resetOriginalValues();
	}

	private LCSSubscriptionEntry _lcsSubscriptionEntry;
}