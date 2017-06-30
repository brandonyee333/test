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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CurrencyEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CurrencyEntry
 * @generated
 */
public class CurrencyEntryWrapper implements CurrencyEntry,
	ModelWrapper<CurrencyEntry> {
	public CurrencyEntryWrapper(CurrencyEntry currencyEntry) {
		_currencyEntry = currencyEntry;
	}

	public Class<?> getModelClass() {
		return CurrencyEntry.class;
	}

	public String getModelClassName() {
		return CurrencyEntry.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("currencyEntryId", getCurrencyEntryId());
		attributes.put("countryId", getCountryId());
		attributes.put("currencyCode", getCurrencyCode());
		attributes.put("marketplaceEnabled", getMarketplaceEnabled());
		attributes.put("marketplaceMinPrice", getMarketplaceMinPrice());

		return attributes;
	}

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

	/**
	* Returns the primary key of this currency entry.
	*
	* @return the primary key of this currency entry
	*/
	public long getPrimaryKey() {
		return _currencyEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this currency entry.
	*
	* @param primaryKey the primary key of this currency entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_currencyEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the currency entry ID of this currency entry.
	*
	* @return the currency entry ID of this currency entry
	*/
	public long getCurrencyEntryId() {
		return _currencyEntry.getCurrencyEntryId();
	}

	/**
	* Sets the currency entry ID of this currency entry.
	*
	* @param currencyEntryId the currency entry ID of this currency entry
	*/
	public void setCurrencyEntryId(long currencyEntryId) {
		_currencyEntry.setCurrencyEntryId(currencyEntryId);
	}

	/**
	* Returns the country ID of this currency entry.
	*
	* @return the country ID of this currency entry
	*/
	public long getCountryId() {
		return _currencyEntry.getCountryId();
	}

	/**
	* Sets the country ID of this currency entry.
	*
	* @param countryId the country ID of this currency entry
	*/
	public void setCountryId(long countryId) {
		_currencyEntry.setCountryId(countryId);
	}

	/**
	* Returns the currency code of this currency entry.
	*
	* @return the currency code of this currency entry
	*/
	public java.lang.String getCurrencyCode() {
		return _currencyEntry.getCurrencyCode();
	}

	/**
	* Sets the currency code of this currency entry.
	*
	* @param currencyCode the currency code of this currency entry
	*/
	public void setCurrencyCode(java.lang.String currencyCode) {
		_currencyEntry.setCurrencyCode(currencyCode);
	}

	/**
	* Returns the marketplace enabled of this currency entry.
	*
	* @return the marketplace enabled of this currency entry
	*/
	public boolean getMarketplaceEnabled() {
		return _currencyEntry.getMarketplaceEnabled();
	}

	/**
	* Returns <code>true</code> if this currency entry is marketplace enabled.
	*
	* @return <code>true</code> if this currency entry is marketplace enabled; <code>false</code> otherwise
	*/
	public boolean isMarketplaceEnabled() {
		return _currencyEntry.isMarketplaceEnabled();
	}

	/**
	* Sets whether this currency entry is marketplace enabled.
	*
	* @param marketplaceEnabled the marketplace enabled of this currency entry
	*/
	public void setMarketplaceEnabled(boolean marketplaceEnabled) {
		_currencyEntry.setMarketplaceEnabled(marketplaceEnabled);
	}

	/**
	* Returns the marketplace min price of this currency entry.
	*
	* @return the marketplace min price of this currency entry
	*/
	public double getMarketplaceMinPrice() {
		return _currencyEntry.getMarketplaceMinPrice();
	}

	/**
	* Sets the marketplace min price of this currency entry.
	*
	* @param marketplaceMinPrice the marketplace min price of this currency entry
	*/
	public void setMarketplaceMinPrice(double marketplaceMinPrice) {
		_currencyEntry.setMarketplaceMinPrice(marketplaceMinPrice);
	}

	public boolean isNew() {
		return _currencyEntry.isNew();
	}

	public void setNew(boolean n) {
		_currencyEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _currencyEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_currencyEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _currencyEntry.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _currencyEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_currencyEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _currencyEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_currencyEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CurrencyEntryWrapper((CurrencyEntry)_currencyEntry.clone());
	}

	public int compareTo(com.liferay.osb.model.CurrencyEntry currencyEntry) {
		return _currencyEntry.compareTo(currencyEntry);
	}

	@Override
	public int hashCode() {
		return _currencyEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.CurrencyEntry> toCacheModel() {
		return _currencyEntry.toCacheModel();
	}

	public com.liferay.osb.model.CurrencyEntry toEscapedModel() {
		return new CurrencyEntryWrapper(_currencyEntry.toEscapedModel());
	}

	public com.liferay.osb.model.CurrencyEntry toUnescapedModel() {
		return new CurrencyEntryWrapper(_currencyEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _currencyEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _currencyEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_currencyEntry.persist();
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

		if (Validator.equals(_currencyEntry, currencyEntryWrapper._currencyEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public CurrencyEntry getWrappedCurrencyEntry() {
		return _currencyEntry;
	}

	public CurrencyEntry getWrappedModel() {
		return _currencyEntry;
	}

	public void resetOriginalValues() {
		_currencyEntry.resetOriginalValues();
	}

	private CurrencyEntry _currencyEntry;
}