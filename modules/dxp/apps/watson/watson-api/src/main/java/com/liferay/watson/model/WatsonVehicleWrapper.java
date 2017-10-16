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

package com.liferay.watson.model;

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
 * This class is a wrapper for {@link WatsonVehicle}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonVehicle
 * @generated
 */
@ProviderType
public class WatsonVehicleWrapper implements WatsonVehicle,
	ModelWrapper<WatsonVehicle> {
	public WatsonVehicleWrapper(WatsonVehicle watsonVehicle) {
		_watsonVehicle = watsonVehicle;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonVehicle.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonVehicle.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonVehicleId", getWatsonVehicleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("colorWatsonListTypeId", getColorWatsonListTypeId());
		attributes.put("makeWatsonListTypeId", getMakeWatsonListTypeId());
		attributes.put("modelWatsonListTypeId", getModelWatsonListTypeId());
		attributes.put("originalWatsonVehicleId", getOriginalWatsonVehicleId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("yearWatsonListTypeId", getYearWatsonListTypeId());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("year", getYear());
		attributes.put("description", getDescription());
		attributes.put("imagePayload", getImagePayload());
		attributes.put("licensePlate", getLicensePlate());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonVehicleId = (Long)attributes.get("watsonVehicleId");

		if (watsonVehicleId != null) {
			setWatsonVehicleId(watsonVehicleId);
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

		Long colorWatsonListTypeId = (Long)attributes.get(
				"colorWatsonListTypeId");

		if (colorWatsonListTypeId != null) {
			setColorWatsonListTypeId(colorWatsonListTypeId);
		}

		Long makeWatsonListTypeId = (Long)attributes.get("makeWatsonListTypeId");

		if (makeWatsonListTypeId != null) {
			setMakeWatsonListTypeId(makeWatsonListTypeId);
		}

		Long modelWatsonListTypeId = (Long)attributes.get(
				"modelWatsonListTypeId");

		if (modelWatsonListTypeId != null) {
			setModelWatsonListTypeId(modelWatsonListTypeId);
		}

		Long originalWatsonVehicleId = (Long)attributes.get(
				"originalWatsonVehicleId");

		if (originalWatsonVehicleId != null) {
			setOriginalWatsonVehicleId(originalWatsonVehicleId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long yearWatsonListTypeId = (Long)attributes.get("yearWatsonListTypeId");

		if (yearWatsonListTypeId != null) {
			setYearWatsonListTypeId(yearWatsonListTypeId);
		}

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}

		String licensePlate = (String)attributes.get("licensePlate");

		if (licensePlate != null) {
			setLicensePlate(licensePlate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WatsonVehicleWrapper((WatsonVehicle)_watsonVehicle.clone());
	}

	@Override
	public int compareTo(WatsonVehicle watsonVehicle) {
		return _watsonVehicle.compareTo(watsonVehicle);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _watsonVehicle.getAvailableLanguageIds();
	}

	/**
	* Returns the color watson list type ID of this watson vehicle.
	*
	* @return the color watson list type ID of this watson vehicle
	*/
	@Override
	public long getColorWatsonListTypeId() {
		return _watsonVehicle.getColorWatsonListTypeId();
	}

	/**
	* Returns the company ID of this watson vehicle.
	*
	* @return the company ID of this watson vehicle
	*/
	@Override
	public long getCompanyId() {
		return _watsonVehicle.getCompanyId();
	}

	/**
	* Returns the create date of this watson vehicle.
	*
	* @return the create date of this watson vehicle
	*/
	@Override
	public Date getCreateDate() {
		return _watsonVehicle.getCreateDate();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _watsonVehicle.getDefaultLanguageId();
	}

	/**
	* Returns the description of this watson vehicle.
	*
	* @return the description of this watson vehicle
	*/
	@Override
	public java.lang.String getDescription() {
		return _watsonVehicle.getDescription();
	}

	/**
	* Returns the localized description of this watson vehicle in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this watson vehicle
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _watsonVehicle.getDescription(locale);
	}

	/**
	* Returns the localized description of this watson vehicle in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson vehicle. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _watsonVehicle.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this watson vehicle in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this watson vehicle
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _watsonVehicle.getDescription(languageId);
	}

	/**
	* Returns the localized description of this watson vehicle in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson vehicle
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _watsonVehicle.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _watsonVehicle.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _watsonVehicle.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this watson vehicle.
	*
	* @return the locales and localized descriptions of this watson vehicle
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _watsonVehicle.getDescriptionMap();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonVehicle.getExpandoBridge();
	}

	/**
	* Returns the image payload of this watson vehicle.
	*
	* @return the image payload of this watson vehicle
	*/
	@Override
	public java.lang.String getImagePayload() {
		return _watsonVehicle.getImagePayload();
	}

	/**
	* Returns the license plate of this watson vehicle.
	*
	* @return the license plate of this watson vehicle
	*/
	@Override
	public java.lang.String getLicensePlate() {
		return _watsonVehicle.getLicensePlate();
	}

	/**
	* Returns the make watson list type ID of this watson vehicle.
	*
	* @return the make watson list type ID of this watson vehicle
	*/
	@Override
	public long getMakeWatsonListTypeId() {
		return _watsonVehicle.getMakeWatsonListTypeId();
	}

	/**
	* Returns the model watson list type ID of this watson vehicle.
	*
	* @return the model watson list type ID of this watson vehicle
	*/
	@Override
	public long getModelWatsonListTypeId() {
		return _watsonVehicle.getModelWatsonListTypeId();
	}

	/**
	* Returns the modified date of this watson vehicle.
	*
	* @return the modified date of this watson vehicle
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonVehicle.getModifiedDate();
	}

	/**
	* Returns the original watson vehicle ID of this watson vehicle.
	*
	* @return the original watson vehicle ID of this watson vehicle
	*/
	@Override
	public long getOriginalWatsonVehicleId() {
		return _watsonVehicle.getOriginalWatsonVehicleId();
	}

	/**
	* Returns the primary key of this watson vehicle.
	*
	* @return the primary key of this watson vehicle
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonVehicle.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonVehicle.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this watson vehicle.
	*
	* @return the status of this watson vehicle
	*/
	@Override
	public int getStatus() {
		return _watsonVehicle.getStatus();
	}

	/**
	* Returns the type watson list type ID of this watson vehicle.
	*
	* @return the type watson list type ID of this watson vehicle
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonVehicle.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson vehicle.
	*
	* @return the user ID of this watson vehicle
	*/
	@Override
	public long getUserId() {
		return _watsonVehicle.getUserId();
	}

	/**
	* Returns the user name of this watson vehicle.
	*
	* @return the user name of this watson vehicle
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonVehicle.getUserName();
	}

	/**
	* Returns the user uuid of this watson vehicle.
	*
	* @return the user uuid of this watson vehicle
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonVehicle.getUserUuid();
	}

	/**
	* Returns the watson incident ID of this watson vehicle.
	*
	* @return the watson incident ID of this watson vehicle
	*/
	@Override
	public long getWatsonIncidentId() {
		return _watsonVehicle.getWatsonIncidentId();
	}

	/**
	* Returns the watson vehicle ID of this watson vehicle.
	*
	* @return the watson vehicle ID of this watson vehicle
	*/
	@Override
	public long getWatsonVehicleId() {
		return _watsonVehicle.getWatsonVehicleId();
	}

	/**
	* Returns the year of this watson vehicle.
	*
	* @return the year of this watson vehicle
	*/
	@Override
	public int getYear() {
		return _watsonVehicle.getYear();
	}

	/**
	* Returns the year watson list type ID of this watson vehicle.
	*
	* @return the year watson list type ID of this watson vehicle
	*/
	@Override
	public long getYearWatsonListTypeId() {
		return _watsonVehicle.getYearWatsonListTypeId();
	}

	@Override
	public int hashCode() {
		return _watsonVehicle.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonVehicle.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonVehicle.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonVehicle.isNew();
	}

	@Override
	public void persist() {
		_watsonVehicle.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonVehicle.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonVehicle.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonVehicle.setCachedModel(cachedModel);
	}

	/**
	* Sets the color watson list type ID of this watson vehicle.
	*
	* @param colorWatsonListTypeId the color watson list type ID of this watson vehicle
	*/
	@Override
	public void setColorWatsonListTypeId(long colorWatsonListTypeId) {
		_watsonVehicle.setColorWatsonListTypeId(colorWatsonListTypeId);
	}

	/**
	* Sets the company ID of this watson vehicle.
	*
	* @param companyId the company ID of this watson vehicle
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonVehicle.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson vehicle.
	*
	* @param createDate the create date of this watson vehicle
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonVehicle.setCreateDate(createDate);
	}

	/**
	* Sets the description of this watson vehicle.
	*
	* @param description the description of this watson vehicle
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_watsonVehicle.setDescription(description);
	}

	/**
	* Sets the localized description of this watson vehicle in the language.
	*
	* @param description the localized description of this watson vehicle
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_watsonVehicle.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this watson vehicle in the language, and sets the default locale.
	*
	* @param description the localized description of this watson vehicle
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonVehicle.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_watsonVehicle.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this watson vehicle from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this watson vehicle
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_watsonVehicle.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this watson vehicle from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this watson vehicle
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_watsonVehicle.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonVehicle.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonVehicle.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonVehicle.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the image payload of this watson vehicle.
	*
	* @param imagePayload the image payload of this watson vehicle
	*/
	@Override
	public void setImagePayload(java.lang.String imagePayload) {
		_watsonVehicle.setImagePayload(imagePayload);
	}

	/**
	* Sets the license plate of this watson vehicle.
	*
	* @param licensePlate the license plate of this watson vehicle
	*/
	@Override
	public void setLicensePlate(java.lang.String licensePlate) {
		_watsonVehicle.setLicensePlate(licensePlate);
	}

	/**
	* Sets the make watson list type ID of this watson vehicle.
	*
	* @param makeWatsonListTypeId the make watson list type ID of this watson vehicle
	*/
	@Override
	public void setMakeWatsonListTypeId(long makeWatsonListTypeId) {
		_watsonVehicle.setMakeWatsonListTypeId(makeWatsonListTypeId);
	}

	/**
	* Sets the model watson list type ID of this watson vehicle.
	*
	* @param modelWatsonListTypeId the model watson list type ID of this watson vehicle
	*/
	@Override
	public void setModelWatsonListTypeId(long modelWatsonListTypeId) {
		_watsonVehicle.setModelWatsonListTypeId(modelWatsonListTypeId);
	}

	/**
	* Sets the modified date of this watson vehicle.
	*
	* @param modifiedDate the modified date of this watson vehicle
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonVehicle.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonVehicle.setNew(n);
	}

	/**
	* Sets the original watson vehicle ID of this watson vehicle.
	*
	* @param originalWatsonVehicleId the original watson vehicle ID of this watson vehicle
	*/
	@Override
	public void setOriginalWatsonVehicleId(long originalWatsonVehicleId) {
		_watsonVehicle.setOriginalWatsonVehicleId(originalWatsonVehicleId);
	}

	/**
	* Sets the primary key of this watson vehicle.
	*
	* @param primaryKey the primary key of this watson vehicle
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonVehicle.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonVehicle.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this watson vehicle.
	*
	* @param status the status of this watson vehicle
	*/
	@Override
	public void setStatus(int status) {
		_watsonVehicle.setStatus(status);
	}

	/**
	* Sets the type watson list type ID of this watson vehicle.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson vehicle
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonVehicle.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson vehicle.
	*
	* @param userId the user ID of this watson vehicle
	*/
	@Override
	public void setUserId(long userId) {
		_watsonVehicle.setUserId(userId);
	}

	/**
	* Sets the user name of this watson vehicle.
	*
	* @param userName the user name of this watson vehicle
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonVehicle.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson vehicle.
	*
	* @param userUuid the user uuid of this watson vehicle
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonVehicle.setUserUuid(userUuid);
	}

	/**
	* Sets the watson incident ID of this watson vehicle.
	*
	* @param watsonIncidentId the watson incident ID of this watson vehicle
	*/
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonVehicle.setWatsonIncidentId(watsonIncidentId);
	}

	/**
	* Sets the watson vehicle ID of this watson vehicle.
	*
	* @param watsonVehicleId the watson vehicle ID of this watson vehicle
	*/
	@Override
	public void setWatsonVehicleId(long watsonVehicleId) {
		_watsonVehicle.setWatsonVehicleId(watsonVehicleId);
	}

	/**
	* Sets the year of this watson vehicle.
	*
	* @param year the year of this watson vehicle
	*/
	@Override
	public void setYear(int year) {
		_watsonVehicle.setYear(year);
	}

	/**
	* Sets the year watson list type ID of this watson vehicle.
	*
	* @param yearWatsonListTypeId the year watson list type ID of this watson vehicle
	*/
	@Override
	public void setYearWatsonListTypeId(long yearWatsonListTypeId) {
		_watsonVehicle.setYearWatsonListTypeId(yearWatsonListTypeId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonVehicle> toCacheModel() {
		return _watsonVehicle.toCacheModel();
	}

	@Override
	public WatsonVehicle toEscapedModel() {
		return new WatsonVehicleWrapper(_watsonVehicle.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonVehicle.toString();
	}

	@Override
	public WatsonVehicle toUnescapedModel() {
		return new WatsonVehicleWrapper(_watsonVehicle.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonVehicle.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonVehicleWrapper)) {
			return false;
		}

		WatsonVehicleWrapper watsonVehicleWrapper = (WatsonVehicleWrapper)obj;

		if (Objects.equals(_watsonVehicle, watsonVehicleWrapper._watsonVehicle)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonVehicle getWrappedModel() {
		return _watsonVehicle;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonVehicle.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonVehicle.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonVehicle.resetOriginalValues();
	}

	private final WatsonVehicle _watsonVehicle;
}