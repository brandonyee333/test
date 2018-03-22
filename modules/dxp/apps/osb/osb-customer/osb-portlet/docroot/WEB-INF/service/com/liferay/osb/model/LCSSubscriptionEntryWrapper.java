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
 * This class is a wrapper for {@link LCSSubscriptionEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LCSSubscriptionEntry
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryWrapper implements LCSSubscriptionEntry,
	ModelWrapper<LCSSubscriptionEntry> {
	public LCSSubscriptionEntryWrapper(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		_lcsSubscriptionEntry = lcsSubscriptionEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSSubscriptionEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LCSSubscriptionEntry.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public LCSSubscriptionEntry toEscapedModel() {
		return new LCSSubscriptionEntryWrapper(_lcsSubscriptionEntry.toEscapedModel());
	}

	@Override
	public LCSSubscriptionEntry toUnescapedModel() {
		return new LCSSubscriptionEntryWrapper(_lcsSubscriptionEntry.toUnescapedModel());
	}

	/**
	* Returns the active of this lcs subscription entry.
	*
	* @return the active of this lcs subscription entry
	*/
	@Override
	public boolean getActive() {
		return _lcsSubscriptionEntry.getActive();
	}

	/**
	* Returns <code>true</code> if this lcs subscription entry is active.
	*
	* @return <code>true</code> if this lcs subscription entry is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _lcsSubscriptionEntry.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _lcsSubscriptionEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsSubscriptionEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lcsSubscriptionEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsSubscriptionEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSSubscriptionEntry> toCacheModel() {
		return _lcsSubscriptionEntry.toCacheModel();
	}

	/**
	* Returns the actual price of this lcs subscription entry.
	*
	* @return the actual price of this lcs subscription entry
	*/
	@Override
	public double getActualPrice() {
		return _lcsSubscriptionEntry.getActualPrice();
	}

	@Override
	public int compareTo(LCSSubscriptionEntry lcsSubscriptionEntry) {
		return _lcsSubscriptionEntry.compareTo(lcsSubscriptionEntry);
	}

	/**
	* Returns the instance size of this lcs subscription entry.
	*
	* @return the instance size of this lcs subscription entry
	*/
	@Override
	public int getInstanceSize() {
		return _lcsSubscriptionEntry.getInstanceSize();
	}

	/**
	* Returns the product version of this lcs subscription entry.
	*
	* @return the product version of this lcs subscription entry
	*/
	@Override
	public int getProductVersion() {
		return _lcsSubscriptionEntry.getProductVersion();
	}

	/**
	* Returns the servers allowed of this lcs subscription entry.
	*
	* @return the servers allowed of this lcs subscription entry
	*/
	@Override
	public int getServersAllowed() {
		return _lcsSubscriptionEntry.getServersAllowed();
	}

	/**
	* Returns the servers used of this lcs subscription entry.
	*
	* @return the servers used of this lcs subscription entry
	*/
	@Override
	public int getServersUsed() {
		return _lcsSubscriptionEntry.getServersUsed();
	}

	@Override
	public int hashCode() {
		return _lcsSubscriptionEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsSubscriptionEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSSubscriptionEntryWrapper((LCSSubscriptionEntry)_lcsSubscriptionEntry.clone());
	}

	/**
	* Returns the currency code of this lcs subscription entry.
	*
	* @return the currency code of this lcs subscription entry
	*/
	@Override
	public java.lang.String getCurrencyCode() {
		return _lcsSubscriptionEntry.getCurrencyCode();
	}

	/**
	* Returns the platform of this lcs subscription entry.
	*
	* @return the platform of this lcs subscription entry
	*/
	@Override
	public java.lang.String getPlatform() {
		return _lcsSubscriptionEntry.getPlatform();
	}

	/**
	* Returns the platform version of this lcs subscription entry.
	*
	* @return the platform version of this lcs subscription entry
	*/
	@Override
	public java.lang.String getPlatformVersion() {
		return _lcsSubscriptionEntry.getPlatformVersion();
	}

	/**
	* Returns the product of this lcs subscription entry.
	*
	* @return the product of this lcs subscription entry
	*/
	@Override
	public java.lang.String getProduct() {
		return _lcsSubscriptionEntry.getProduct();
	}

	/**
	* Returns the type of this lcs subscription entry.
	*
	* @return the type of this lcs subscription entry
	*/
	@Override
	public java.lang.String getType() {
		return _lcsSubscriptionEntry.getType();
	}

	@Override
	public java.lang.String toString() {
		return _lcsSubscriptionEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsSubscriptionEntry.toXmlString();
	}

	/**
	* Returns the end date of this lcs subscription entry.
	*
	* @return the end date of this lcs subscription entry
	*/
	@Override
	public Date getEndDate() {
		return _lcsSubscriptionEntry.getEndDate();
	}

	/**
	* Returns the start date of this lcs subscription entry.
	*
	* @return the start date of this lcs subscription entry
	*/
	@Override
	public Date getStartDate() {
		return _lcsSubscriptionEntry.getStartDate();
	}

	/**
	* Returns the support end date of this lcs subscription entry.
	*
	* @return the support end date of this lcs subscription entry
	*/
	@Override
	public Date getSupportEndDate() {
		return _lcsSubscriptionEntry.getSupportEndDate();
	}

	/**
	* Returns the support start date of this lcs subscription entry.
	*
	* @return the support start date of this lcs subscription entry
	*/
	@Override
	public Date getSupportStartDate() {
		return _lcsSubscriptionEntry.getSupportStartDate();
	}

	/**
	* Returns the lcs project ID of this lcs subscription entry.
	*
	* @return the lcs project ID of this lcs subscription entry
	*/
	@Override
	public long getLcsProjectId() {
		return _lcsSubscriptionEntry.getLcsProjectId();
	}

	/**
	* Returns the lcs subscription entry ID of this lcs subscription entry.
	*
	* @return the lcs subscription entry ID of this lcs subscription entry
	*/
	@Override
	public long getLcsSubscriptionEntryId() {
		return _lcsSubscriptionEntry.getLcsSubscriptionEntryId();
	}

	/**
	* Returns the primary key of this lcs subscription entry.
	*
	* @return the primary key of this lcs subscription entry
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsSubscriptionEntry.getPrimaryKey();
	}

	@Override
	public void persist() {
		_lcsSubscriptionEntry.persist();
	}

	/**
	* Sets whether this lcs subscription entry is active.
	*
	* @param active the active of this lcs subscription entry
	*/
	@Override
	public void setActive(boolean active) {
		_lcsSubscriptionEntry.setActive(active);
	}

	/**
	* Sets the actual price of this lcs subscription entry.
	*
	* @param actualPrice the actual price of this lcs subscription entry
	*/
	@Override
	public void setActualPrice(double actualPrice) {
		_lcsSubscriptionEntry.setActualPrice(actualPrice);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsSubscriptionEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the currency code of this lcs subscription entry.
	*
	* @param currencyCode the currency code of this lcs subscription entry
	*/
	@Override
	public void setCurrencyCode(java.lang.String currencyCode) {
		_lcsSubscriptionEntry.setCurrencyCode(currencyCode);
	}

	/**
	* Sets the end date of this lcs subscription entry.
	*
	* @param endDate the end date of this lcs subscription entry
	*/
	@Override
	public void setEndDate(Date endDate) {
		_lcsSubscriptionEntry.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsSubscriptionEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsSubscriptionEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsSubscriptionEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the instance size of this lcs subscription entry.
	*
	* @param instanceSize the instance size of this lcs subscription entry
	*/
	@Override
	public void setInstanceSize(int instanceSize) {
		_lcsSubscriptionEntry.setInstanceSize(instanceSize);
	}

	/**
	* Sets the lcs project ID of this lcs subscription entry.
	*
	* @param lcsProjectId the lcs project ID of this lcs subscription entry
	*/
	@Override
	public void setLcsProjectId(long lcsProjectId) {
		_lcsSubscriptionEntry.setLcsProjectId(lcsProjectId);
	}

	/**
	* Sets the lcs subscription entry ID of this lcs subscription entry.
	*
	* @param lcsSubscriptionEntryId the lcs subscription entry ID of this lcs subscription entry
	*/
	@Override
	public void setLcsSubscriptionEntryId(long lcsSubscriptionEntryId) {
		_lcsSubscriptionEntry.setLcsSubscriptionEntryId(lcsSubscriptionEntryId);
	}

	@Override
	public void setNew(boolean n) {
		_lcsSubscriptionEntry.setNew(n);
	}

	/**
	* Sets the platform of this lcs subscription entry.
	*
	* @param platform the platform of this lcs subscription entry
	*/
	@Override
	public void setPlatform(java.lang.String platform) {
		_lcsSubscriptionEntry.setPlatform(platform);
	}

	/**
	* Sets the platform version of this lcs subscription entry.
	*
	* @param platformVersion the platform version of this lcs subscription entry
	*/
	@Override
	public void setPlatformVersion(java.lang.String platformVersion) {
		_lcsSubscriptionEntry.setPlatformVersion(platformVersion);
	}

	/**
	* Sets the primary key of this lcs subscription entry.
	*
	* @param primaryKey the primary key of this lcs subscription entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsSubscriptionEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsSubscriptionEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the product of this lcs subscription entry.
	*
	* @param product the product of this lcs subscription entry
	*/
	@Override
	public void setProduct(java.lang.String product) {
		_lcsSubscriptionEntry.setProduct(product);
	}

	/**
	* Sets the product version of this lcs subscription entry.
	*
	* @param productVersion the product version of this lcs subscription entry
	*/
	@Override
	public void setProductVersion(int productVersion) {
		_lcsSubscriptionEntry.setProductVersion(productVersion);
	}

	/**
	* Sets the servers allowed of this lcs subscription entry.
	*
	* @param serversAllowed the servers allowed of this lcs subscription entry
	*/
	@Override
	public void setServersAllowed(int serversAllowed) {
		_lcsSubscriptionEntry.setServersAllowed(serversAllowed);
	}

	/**
	* Sets the servers used of this lcs subscription entry.
	*
	* @param serversUsed the servers used of this lcs subscription entry
	*/
	@Override
	public void setServersUsed(int serversUsed) {
		_lcsSubscriptionEntry.setServersUsed(serversUsed);
	}

	/**
	* Sets the start date of this lcs subscription entry.
	*
	* @param startDate the start date of this lcs subscription entry
	*/
	@Override
	public void setStartDate(Date startDate) {
		_lcsSubscriptionEntry.setStartDate(startDate);
	}

	/**
	* Sets the support end date of this lcs subscription entry.
	*
	* @param supportEndDate the support end date of this lcs subscription entry
	*/
	@Override
	public void setSupportEndDate(Date supportEndDate) {
		_lcsSubscriptionEntry.setSupportEndDate(supportEndDate);
	}

	/**
	* Sets the support start date of this lcs subscription entry.
	*
	* @param supportStartDate the support start date of this lcs subscription entry
	*/
	@Override
	public void setSupportStartDate(Date supportStartDate) {
		_lcsSubscriptionEntry.setSupportStartDate(supportStartDate);
	}

	/**
	* Sets the type of this lcs subscription entry.
	*
	* @param type the type of this lcs subscription entry
	*/
	@Override
	public void setType(java.lang.String type) {
		_lcsSubscriptionEntry.setType(type);
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

		if (Objects.equals(_lcsSubscriptionEntry,
					lcsSubscriptionEntryWrapper._lcsSubscriptionEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSSubscriptionEntry getWrappedModel() {
		return _lcsSubscriptionEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsSubscriptionEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsSubscriptionEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsSubscriptionEntry.resetOriginalValues();
	}

	private final LCSSubscriptionEntry _lcsSubscriptionEntry;
}