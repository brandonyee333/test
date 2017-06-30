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
 * This class is a wrapper for {@link CountryAppPricing}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CountryAppPricing
 * @generated
 */
public class CountryAppPricingWrapper implements CountryAppPricing,
	ModelWrapper<CountryAppPricing> {
	public CountryAppPricingWrapper(CountryAppPricing countryAppPricing) {
		_countryAppPricing = countryAppPricing;
	}

	public Class<?> getModelClass() {
		return CountryAppPricing.class;
	}

	public String getModelClassName() {
		return CountryAppPricing.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("countryAppPricingId", getCountryAppPricingId());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("appPricingId", getAppPricingId());
		attributes.put("countryId", getCountryId());
		attributes.put("name", getName());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long countryAppPricingId = (Long)attributes.get("countryAppPricingId");

		if (countryAppPricingId != null) {
			setCountryAppPricingId(countryAppPricingId);
		}

		Long appEntryId = (Long)attributes.get("appEntryId");

		if (appEntryId != null) {
			setAppEntryId(appEntryId);
		}

		Long appVersionId = (Long)attributes.get("appVersionId");

		if (appVersionId != null) {
			setAppVersionId(appVersionId);
		}

		Long appPricingId = (Long)attributes.get("appPricingId");

		if (appPricingId != null) {
			setAppPricingId(appPricingId);
		}

		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	/**
	* Returns the primary key of this country app pricing.
	*
	* @return the primary key of this country app pricing
	*/
	public long getPrimaryKey() {
		return _countryAppPricing.getPrimaryKey();
	}

	/**
	* Sets the primary key of this country app pricing.
	*
	* @param primaryKey the primary key of this country app pricing
	*/
	public void setPrimaryKey(long primaryKey) {
		_countryAppPricing.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the country app pricing ID of this country app pricing.
	*
	* @return the country app pricing ID of this country app pricing
	*/
	public long getCountryAppPricingId() {
		return _countryAppPricing.getCountryAppPricingId();
	}

	/**
	* Sets the country app pricing ID of this country app pricing.
	*
	* @param countryAppPricingId the country app pricing ID of this country app pricing
	*/
	public void setCountryAppPricingId(long countryAppPricingId) {
		_countryAppPricing.setCountryAppPricingId(countryAppPricingId);
	}

	/**
	* Returns the app entry ID of this country app pricing.
	*
	* @return the app entry ID of this country app pricing
	*/
	public long getAppEntryId() {
		return _countryAppPricing.getAppEntryId();
	}

	/**
	* Sets the app entry ID of this country app pricing.
	*
	* @param appEntryId the app entry ID of this country app pricing
	*/
	public void setAppEntryId(long appEntryId) {
		_countryAppPricing.setAppEntryId(appEntryId);
	}

	/**
	* Returns the app version ID of this country app pricing.
	*
	* @return the app version ID of this country app pricing
	*/
	public long getAppVersionId() {
		return _countryAppPricing.getAppVersionId();
	}

	/**
	* Sets the app version ID of this country app pricing.
	*
	* @param appVersionId the app version ID of this country app pricing
	*/
	public void setAppVersionId(long appVersionId) {
		_countryAppPricing.setAppVersionId(appVersionId);
	}

	/**
	* Returns the app pricing ID of this country app pricing.
	*
	* @return the app pricing ID of this country app pricing
	*/
	public long getAppPricingId() {
		return _countryAppPricing.getAppPricingId();
	}

	/**
	* Sets the app pricing ID of this country app pricing.
	*
	* @param appPricingId the app pricing ID of this country app pricing
	*/
	public void setAppPricingId(long appPricingId) {
		_countryAppPricing.setAppPricingId(appPricingId);
	}

	/**
	* Returns the country ID of this country app pricing.
	*
	* @return the country ID of this country app pricing
	*/
	public long getCountryId() {
		return _countryAppPricing.getCountryId();
	}

	/**
	* Sets the country ID of this country app pricing.
	*
	* @param countryId the country ID of this country app pricing
	*/
	public void setCountryId(long countryId) {
		_countryAppPricing.setCountryId(countryId);
	}

	/**
	* Returns the name of this country app pricing.
	*
	* @return the name of this country app pricing
	*/
	public java.lang.String getName() {
		return _countryAppPricing.getName();
	}

	/**
	* Sets the name of this country app pricing.
	*
	* @param name the name of this country app pricing
	*/
	public void setName(java.lang.String name) {
		_countryAppPricing.setName(name);
	}

	public boolean isNew() {
		return _countryAppPricing.isNew();
	}

	public void setNew(boolean n) {
		_countryAppPricing.setNew(n);
	}

	public boolean isCachedModel() {
		return _countryAppPricing.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_countryAppPricing.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _countryAppPricing.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _countryAppPricing.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_countryAppPricing.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _countryAppPricing.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_countryAppPricing.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CountryAppPricingWrapper((CountryAppPricing)_countryAppPricing.clone());
	}

	public int compareTo(
		com.liferay.osb.model.CountryAppPricing countryAppPricing) {
		return _countryAppPricing.compareTo(countryAppPricing);
	}

	@Override
	public int hashCode() {
		return _countryAppPricing.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.CountryAppPricing> toCacheModel() {
		return _countryAppPricing.toCacheModel();
	}

	public com.liferay.osb.model.CountryAppPricing toEscapedModel() {
		return new CountryAppPricingWrapper(_countryAppPricing.toEscapedModel());
	}

	public com.liferay.osb.model.CountryAppPricing toUnescapedModel() {
		return new CountryAppPricingWrapper(_countryAppPricing.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _countryAppPricing.toString();
	}

	public java.lang.String toXmlString() {
		return _countryAppPricing.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_countryAppPricing.persist();
	}

	public com.liferay.osb.model.AppPricing getAppPricing()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _countryAppPricing.getAppPricing();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CountryAppPricingWrapper)) {
			return false;
		}

		CountryAppPricingWrapper countryAppPricingWrapper = (CountryAppPricingWrapper)obj;

		if (Validator.equals(_countryAppPricing,
					countryAppPricingWrapper._countryAppPricing)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public CountryAppPricing getWrappedCountryAppPricing() {
		return _countryAppPricing;
	}

	public CountryAppPricing getWrappedModel() {
		return _countryAppPricing;
	}

	public void resetOriginalValues() {
		_countryAppPricing.resetOriginalValues();
	}

	private CountryAppPricing _countryAppPricing;
}