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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CurrencyEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CurrencyEntry
 * @generated
 */
@ProviderType
public class CurrencyEntryWrapper implements CurrencyEntry,
	ModelWrapper<CurrencyEntry> {
	public CurrencyEntryWrapper(CurrencyEntry currencyEntry) {
		_currencyEntry = currencyEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return CurrencyEntry.class;
	}

	@Override
	public String getModelClassName() {
		return CurrencyEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("currencyEntryId", getCurrencyEntryId());
		attributes.put("countryId", getCountryId());
		attributes.put("currencyCode", getCurrencyCode());
		attributes.put("marketplaceEnabled", getMarketplaceEnabled());
		attributes.put("marketplaceMinPrice", getMarketplaceMinPrice());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long currencyEntryId = (Long)attributes.get("currencyEntryId");

		if (currencyEntryId != null) {
			setCurrencyEntryId(currencyEntryId);
		}

		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		String currencyCode = (String)attributes.get("currencyCode");

		if (currencyCode != null) {
			setCurrencyCode(currencyCode);
		}

		Boolean marketplaceEnabled = (Boolean)attributes.get(
				"marketplaceEnabled");

		if (marketplaceEnabled != null) {
			setMarketplaceEnabled(marketplaceEnabled);
		}

		Double marketplaceMinPrice = (Double)attributes.get(
				"marketplaceMinPrice");

		if (marketplaceMinPrice != null) {
			setMarketplaceMinPrice(marketplaceMinPrice);
		}
	}

	@Override
	public CurrencyEntry toEscapedModel() {
		return new CurrencyEntryWrapper(_currencyEntry.toEscapedModel());
	}

	@Override
	public CurrencyEntry toUnescapedModel() {
		return new CurrencyEntryWrapper(_currencyEntry.toUnescapedModel());
	}

	/**
	* Returns the marketplace enabled of this currency entry.
	*
	* @return the marketplace enabled of this currency entry
	*/
	@Override
	public boolean getMarketplaceEnabled() {
		return _currencyEntry.getMarketplaceEnabled();
	}

	@Override
	public boolean isCachedModel() {
		return _currencyEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _currencyEntry.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this currency entry is marketplace enabled.
	*
	* @return <code>true</code> if this currency entry is marketplace enabled; <code>false</code> otherwise
	*/
	@Override
	public boolean isMarketplaceEnabled() {
		return _currencyEntry.isMarketplaceEnabled();
	}

	@Override
	public boolean isNew() {
		return _currencyEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _currencyEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CurrencyEntry> toCacheModel() {
		return _currencyEntry.toCacheModel();
	}

	/**
	* Returns the marketplace min price of this currency entry.
	*
	* @return the marketplace min price of this currency entry
	*/
	@Override
	public double getMarketplaceMinPrice() {
		return _currencyEntry.getMarketplaceMinPrice();
	}

	@Override
	public int compareTo(CurrencyEntry currencyEntry) {
		return _currencyEntry.compareTo(currencyEntry);
	}

	@Override
	public int hashCode() {
		return _currencyEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _currencyEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new CurrencyEntryWrapper((CurrencyEntry)_currencyEntry.clone());
	}

	/**
	* Returns the currency code of this currency entry.
	*
	* @return the currency code of this currency entry
	*/
	@Override
	public java.lang.String getCurrencyCode() {
		return _currencyEntry.getCurrencyCode();
	}

	@Override
	public java.lang.String toString() {
		return _currencyEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _currencyEntry.toXmlString();
	}

	/**
	* Returns the country ID of this currency entry.
	*
	* @return the country ID of this currency entry
	*/
	@Override
	public long getCountryId() {
		return _currencyEntry.getCountryId();
	}

	/**
	* Returns the currency entry ID of this currency entry.
	*
	* @return the currency entry ID of this currency entry
	*/
	@Override
	public long getCurrencyEntryId() {
		return _currencyEntry.getCurrencyEntryId();
	}

	/**
	* Returns the primary key of this currency entry.
	*
	* @return the primary key of this currency entry
	*/
	@Override
	public long getPrimaryKey() {
		return _currencyEntry.getPrimaryKey();
	}

	@Override
	public void persist() {
		_currencyEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_currencyEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the country ID of this currency entry.
	*
	* @param countryId the country ID of this currency entry
	*/
	@Override
	public void setCountryId(long countryId) {
		_currencyEntry.setCountryId(countryId);
	}

	/**
	* Sets the currency code of this currency entry.
	*
	* @param currencyCode the currency code of this currency entry
	*/
	@Override
	public void setCurrencyCode(java.lang.String currencyCode) {
		_currencyEntry.setCurrencyCode(currencyCode);
	}

	/**
	* Sets the currency entry ID of this currency entry.
	*
	* @param currencyEntryId the currency entry ID of this currency entry
	*/
	@Override
	public void setCurrencyEntryId(long currencyEntryId) {
		_currencyEntry.setCurrencyEntryId(currencyEntryId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_currencyEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_currencyEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_currencyEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this currency entry is marketplace enabled.
	*
	* @param marketplaceEnabled the marketplace enabled of this currency entry
	*/
	@Override
	public void setMarketplaceEnabled(boolean marketplaceEnabled) {
		_currencyEntry.setMarketplaceEnabled(marketplaceEnabled);
	}

	/**
	* Sets the marketplace min price of this currency entry.
	*
	* @param marketplaceMinPrice the marketplace min price of this currency entry
	*/
	@Override
	public void setMarketplaceMinPrice(double marketplaceMinPrice) {
		_currencyEntry.setMarketplaceMinPrice(marketplaceMinPrice);
	}

	@Override
	public void setNew(boolean n) {
		_currencyEntry.setNew(n);
	}

	/**
	* Sets the primary key of this currency entry.
	*
	* @param primaryKey the primary key of this currency entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_currencyEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_currencyEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CurrencyEntryWrapper)) {
			return false;
		}

		CurrencyEntryWrapper currencyEntryWrapper = (CurrencyEntryWrapper)obj;

		if (Objects.equals(_currencyEntry, currencyEntryWrapper._currencyEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public CurrencyEntry getWrappedModel() {
		return _currencyEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _currencyEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _currencyEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_currencyEntry.resetOriginalValues();
	}

	private final CurrencyEntry _currencyEntry;
}