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
 * This class is a wrapper for {@link AppPricing}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppPricing
 * @generated
 */
public class AppPricingWrapper implements AppPricing, ModelWrapper<AppPricing> {
	public AppPricingWrapper(AppPricing appPricing) {
		_appPricing = appPricing;
	}

	public Class<?> getModelClass() {
		return AppPricing.class;
	}

	public String getModelClassName() {
		return AppPricing.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("appPricingId", getAppPricingId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("appEntryId", getAppEntryId());
		attributes.put("appVersionId", getAppVersionId());
		attributes.put("name", getName());
		attributes.put("currencyEntryId", getCurrencyEntryId());
		attributes.put("standardSupportPrice", getStandardSupportPrice());
		attributes.put("developerSupportPrice", getDeveloperSupportPrice());
		attributes.put("rank", getRank());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long appPricingId = (Long)attributes.get("appPricingId");

		if (appPricingId != null) {
			setAppPricingId(appPricingId);
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

		Long appEntryId = (Long)attributes.get("appEntryId");

		if (appEntryId != null) {
			setAppEntryId(appEntryId);
		}

		Long appVersionId = (Long)attributes.get("appVersionId");

		if (appVersionId != null) {
			setAppVersionId(appVersionId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long currencyEntryId = (Long)attributes.get("currencyEntryId");

		if (currencyEntryId != null) {
			setCurrencyEntryId(currencyEntryId);
		}

		Double standardSupportPrice = (Double)attributes.get(
				"standardSupportPrice");

		if (standardSupportPrice != null) {
			setStandardSupportPrice(standardSupportPrice);
		}

		Double developerSupportPrice = (Double)attributes.get(
				"developerSupportPrice");

		if (developerSupportPrice != null) {
			setDeveloperSupportPrice(developerSupportPrice);
		}

		Integer rank = (Integer)attributes.get("rank");

		if (rank != null) {
			setRank(rank);
		}
	}

	/**
	* Returns the primary key of this app pricing.
	*
	* @return the primary key of this app pricing
	*/
	public long getPrimaryKey() {
		return _appPricing.getPrimaryKey();
	}

	/**
	* Sets the primary key of this app pricing.
	*
	* @param primaryKey the primary key of this app pricing
	*/
	public void setPrimaryKey(long primaryKey) {
		_appPricing.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the app pricing ID of this app pricing.
	*
	* @return the app pricing ID of this app pricing
	*/
	public long getAppPricingId() {
		return _appPricing.getAppPricingId();
	}

	/**
	* Sets the app pricing ID of this app pricing.
	*
	* @param appPricingId the app pricing ID of this app pricing
	*/
	public void setAppPricingId(long appPricingId) {
		_appPricing.setAppPricingId(appPricingId);
	}

	/**
	* Returns the user ID of this app pricing.
	*
	* @return the user ID of this app pricing
	*/
	public long getUserId() {
		return _appPricing.getUserId();
	}

	/**
	* Sets the user ID of this app pricing.
	*
	* @param userId the user ID of this app pricing
	*/
	public void setUserId(long userId) {
		_appPricing.setUserId(userId);
	}

	/**
	* Returns the user uuid of this app pricing.
	*
	* @return the user uuid of this app pricing
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPricing.getUserUuid();
	}

	/**
	* Sets the user uuid of this app pricing.
	*
	* @param userUuid the user uuid of this app pricing
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_appPricing.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this app pricing.
	*
	* @return the user name of this app pricing
	*/
	public java.lang.String getUserName() {
		return _appPricing.getUserName();
	}

	/**
	* Sets the user name of this app pricing.
	*
	* @param userName the user name of this app pricing
	*/
	public void setUserName(java.lang.String userName) {
		_appPricing.setUserName(userName);
	}

	/**
	* Returns the create date of this app pricing.
	*
	* @return the create date of this app pricing
	*/
	public java.util.Date getCreateDate() {
		return _appPricing.getCreateDate();
	}

	/**
	* Sets the create date of this app pricing.
	*
	* @param createDate the create date of this app pricing
	*/
	public void setCreateDate(java.util.Date createDate) {
		_appPricing.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this app pricing.
	*
	* @return the modified date of this app pricing
	*/
	public java.util.Date getModifiedDate() {
		return _appPricing.getModifiedDate();
	}

	/**
	* Sets the modified date of this app pricing.
	*
	* @param modifiedDate the modified date of this app pricing
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_appPricing.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the app entry ID of this app pricing.
	*
	* @return the app entry ID of this app pricing
	*/
	public long getAppEntryId() {
		return _appPricing.getAppEntryId();
	}

	/**
	* Sets the app entry ID of this app pricing.
	*
	* @param appEntryId the app entry ID of this app pricing
	*/
	public void setAppEntryId(long appEntryId) {
		_appPricing.setAppEntryId(appEntryId);
	}

	/**
	* Returns the app version ID of this app pricing.
	*
	* @return the app version ID of this app pricing
	*/
	public long getAppVersionId() {
		return _appPricing.getAppVersionId();
	}

	/**
	* Sets the app version ID of this app pricing.
	*
	* @param appVersionId the app version ID of this app pricing
	*/
	public void setAppVersionId(long appVersionId) {
		_appPricing.setAppVersionId(appVersionId);
	}

	/**
	* Returns the name of this app pricing.
	*
	* @return the name of this app pricing
	*/
	public java.lang.String getName() {
		return _appPricing.getName();
	}

	/**
	* Sets the name of this app pricing.
	*
	* @param name the name of this app pricing
	*/
	public void setName(java.lang.String name) {
		_appPricing.setName(name);
	}

	/**
	* Returns the currency entry ID of this app pricing.
	*
	* @return the currency entry ID of this app pricing
	*/
	public long getCurrencyEntryId() {
		return _appPricing.getCurrencyEntryId();
	}

	/**
	* Sets the currency entry ID of this app pricing.
	*
	* @param currencyEntryId the currency entry ID of this app pricing
	*/
	public void setCurrencyEntryId(long currencyEntryId) {
		_appPricing.setCurrencyEntryId(currencyEntryId);
	}

	/**
	* Returns the standard support price of this app pricing.
	*
	* @return the standard support price of this app pricing
	*/
	public double getStandardSupportPrice() {
		return _appPricing.getStandardSupportPrice();
	}

	/**
	* Sets the standard support price of this app pricing.
	*
	* @param standardSupportPrice the standard support price of this app pricing
	*/
	public void setStandardSupportPrice(double standardSupportPrice) {
		_appPricing.setStandardSupportPrice(standardSupportPrice);
	}

	/**
	* Returns the developer support price of this app pricing.
	*
	* @return the developer support price of this app pricing
	*/
	public double getDeveloperSupportPrice() {
		return _appPricing.getDeveloperSupportPrice();
	}

	/**
	* Sets the developer support price of this app pricing.
	*
	* @param developerSupportPrice the developer support price of this app pricing
	*/
	public void setDeveloperSupportPrice(double developerSupportPrice) {
		_appPricing.setDeveloperSupportPrice(developerSupportPrice);
	}

	/**
	* Returns the rank of this app pricing.
	*
	* @return the rank of this app pricing
	*/
	public int getRank() {
		return _appPricing.getRank();
	}

	/**
	* Sets the rank of this app pricing.
	*
	* @param rank the rank of this app pricing
	*/
	public void setRank(int rank) {
		_appPricing.setRank(rank);
	}

	public boolean isNew() {
		return _appPricing.isNew();
	}

	public void setNew(boolean n) {
		_appPricing.setNew(n);
	}

	public boolean isCachedModel() {
		return _appPricing.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_appPricing.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _appPricing.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _appPricing.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_appPricing.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _appPricing.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_appPricing.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AppPricingWrapper((AppPricing)_appPricing.clone());
	}

	public int compareTo(com.liferay.osb.model.AppPricing appPricing) {
		return _appPricing.compareTo(appPricing);
	}

	@Override
	public int hashCode() {
		return _appPricing.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AppPricing> toCacheModel() {
		return _appPricing.toCacheModel();
	}

	public com.liferay.osb.model.AppPricing toEscapedModel() {
		return new AppPricingWrapper(_appPricing.toEscapedModel());
	}

	public com.liferay.osb.model.AppPricing toUnescapedModel() {
		return new AppPricingWrapper(_appPricing.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _appPricing.toString();
	}

	public java.lang.String toXmlString() {
		return _appPricing.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_appPricing.persist();
	}

	public java.lang.String getCurrencyCode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricing.getCurrencyCode();
	}

	public java.lang.String getFormattedSupportPrice(int usageType,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricing.getFormattedSupportPrice(usageType, locale);
	}

	public double getSupportPrice(int usageType) {
		return _appPricing.getSupportPrice(usageType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AppPricingWrapper)) {
			return false;
		}

		AppPricingWrapper appPricingWrapper = (AppPricingWrapper)obj;

		if (Validator.equals(_appPricing, appPricingWrapper._appPricing)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AppPricing getWrappedAppPricing() {
		return _appPricing;
	}

	public AppPricing getWrappedModel() {
		return _appPricing;
	}

	public void resetOriginalValues() {
		_appPricing.resetOriginalValues();
	}

	private AppPricing _appPricing;
}