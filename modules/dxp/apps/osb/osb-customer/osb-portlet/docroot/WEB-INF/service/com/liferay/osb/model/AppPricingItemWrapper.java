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
 * This class is a wrapper for {@link AppPricingItem}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppPricingItem
 * @generated
 */
public class AppPricingItemWrapper implements AppPricingItem,
	ModelWrapper<AppPricingItem> {
	public AppPricingItemWrapper(AppPricingItem appPricingItem) {
		_appPricingItem = appPricingItem;
	}

	public Class<?> getModelClass() {
		return AppPricingItem.class;
	}

	public String getModelClassName() {
		return AppPricingItem.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appPricingItemId", getAppPricingItemId());
		attributes.put("appPricingId", getAppPricingId());
		attributes.put("assetLicenseId", getAssetLicenseId());
		attributes.put("currencyEntryId", getCurrencyEntryId());
		attributes.put("price", getPrice());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long appPricingItemId = (Long)attributes.get("appPricingItemId");

		if (appPricingItemId != null) {
			setAppPricingItemId(appPricingItemId);
		}

		Long appPricingId = (Long)attributes.get("appPricingId");

		if (appPricingId != null) {
			setAppPricingId(appPricingId);
		}

		Long assetLicenseId = (Long)attributes.get("assetLicenseId");

		if (assetLicenseId != null) {
			setAssetLicenseId(assetLicenseId);
		}

		Long currencyEntryId = (Long)attributes.get("currencyEntryId");

		if (currencyEntryId != null) {
			setCurrencyEntryId(currencyEntryId);
		}

		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}
	}

	/**
	* Returns the primary key of this app pricing item.
	*
	* @return the primary key of this app pricing item
	*/
	public long getPrimaryKey() {
		return _appPricingItem.getPrimaryKey();
	}

	/**
	* Sets the primary key of this app pricing item.
	*
	* @param primaryKey the primary key of this app pricing item
	*/
	public void setPrimaryKey(long primaryKey) {
		_appPricingItem.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the app pricing item ID of this app pricing item.
	*
	* @return the app pricing item ID of this app pricing item
	*/
	public long getAppPricingItemId() {
		return _appPricingItem.getAppPricingItemId();
	}

	/**
	* Sets the app pricing item ID of this app pricing item.
	*
	* @param appPricingItemId the app pricing item ID of this app pricing item
	*/
	public void setAppPricingItemId(long appPricingItemId) {
		_appPricingItem.setAppPricingItemId(appPricingItemId);
	}

	/**
	* Returns the app pricing ID of this app pricing item.
	*
	* @return the app pricing ID of this app pricing item
	*/
	public long getAppPricingId() {
		return _appPricingItem.getAppPricingId();
	}

	/**
	* Sets the app pricing ID of this app pricing item.
	*
	* @param appPricingId the app pricing ID of this app pricing item
	*/
	public void setAppPricingId(long appPricingId) {
		_appPricingItem.setAppPricingId(appPricingId);
	}

	/**
	* Returns the asset license ID of this app pricing item.
	*
	* @return the asset license ID of this app pricing item
	*/
	public long getAssetLicenseId() {
		return _appPricingItem.getAssetLicenseId();
	}

	/**
	* Sets the asset license ID of this app pricing item.
	*
	* @param assetLicenseId the asset license ID of this app pricing item
	*/
	public void setAssetLicenseId(long assetLicenseId) {
		_appPricingItem.setAssetLicenseId(assetLicenseId);
	}

	/**
	* Returns the currency entry ID of this app pricing item.
	*
	* @return the currency entry ID of this app pricing item
	*/
	public long getCurrencyEntryId() {
		return _appPricingItem.getCurrencyEntryId();
	}

	/**
	* Sets the currency entry ID of this app pricing item.
	*
	* @param currencyEntryId the currency entry ID of this app pricing item
	*/
	public void setCurrencyEntryId(long currencyEntryId) {
		_appPricingItem.setCurrencyEntryId(currencyEntryId);
	}

	/**
	* Returns the price of this app pricing item.
	*
	* @return the price of this app pricing item
	*/
	public double getPrice() {
		return _appPricingItem.getPrice();
	}

	/**
	* Sets the price of this app pricing item.
	*
	* @param price the price of this app pricing item
	*/
	public void setPrice(double price) {
		_appPricingItem.setPrice(price);
	}

	public boolean isNew() {
		return _appPricingItem.isNew();
	}

	public void setNew(boolean n) {
		_appPricingItem.setNew(n);
	}

	public boolean isCachedModel() {
		return _appPricingItem.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_appPricingItem.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _appPricingItem.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _appPricingItem.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_appPricingItem.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _appPricingItem.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_appPricingItem.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AppPricingItemWrapper((AppPricingItem)_appPricingItem.clone());
	}

	public int compareTo(com.liferay.osb.model.AppPricingItem appPricingItem) {
		return _appPricingItem.compareTo(appPricingItem);
	}

	@Override
	public int hashCode() {
		return _appPricingItem.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AppPricingItem> toCacheModel() {
		return _appPricingItem.toCacheModel();
	}

	public com.liferay.osb.model.AppPricingItem toEscapedModel() {
		return new AppPricingItemWrapper(_appPricingItem.toEscapedModel());
	}

	public com.liferay.osb.model.AppPricingItem toUnescapedModel() {
		return new AppPricingItemWrapper(_appPricingItem.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _appPricingItem.toString();
	}

	public java.lang.String toXmlString() {
		return _appPricingItem.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_appPricingItem.persist();
	}

	public com.liferay.portal.model.Country getCountry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingItem.getCountry();
	}

	public java.lang.String getCurrencyCode()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPricingItem.getCurrencyCode();
	}

	public com.liferay.osb.model.CurrencyEntry getCurrencyEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingItem.getCurrencyEntry();
	}

	public java.lang.String getFormattedPrice(java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingItem.getFormattedPrice(locale);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AppPricingItemWrapper)) {
			return false;
		}

		AppPricingItemWrapper appPricingItemWrapper = (AppPricingItemWrapper)obj;

		if (Validator.equals(_appPricingItem,
					appPricingItemWrapper._appPricingItem)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AppPricingItem getWrappedAppPricingItem() {
		return _appPricingItem;
	}

	public AppPricingItem getWrappedModel() {
		return _appPricingItem;
	}

	public void resetOriginalValues() {
		_appPricingItem.resetOriginalValues();
	}

	private AppPricingItem _appPricingItem;
}