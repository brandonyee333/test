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
 * This class is a wrapper for {@link WatsonAddress}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonAddress
 * @generated
 */
@ProviderType
public class WatsonAddressWrapper implements WatsonAddress,
	ModelWrapper<WatsonAddress> {
	public WatsonAddressWrapper(WatsonAddress watsonAddress) {
		_watsonAddress = watsonAddress;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonAddress.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonAddress.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonAddressId", getWatsonAddressId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("countryId", getCountryId());
		attributes.put("districtWatsonListTypeId", getDistrictWatsonListTypeId());
		attributes.put("originalWatsonAddressId", getOriginalWatsonAddressId());
		attributes.put("provinceWatsonListTypeId", getProvinceWatsonListTypeId());
		attributes.put("subDistrictWatsonListTypeId",
			getSubDistrictWatsonListTypeId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("name", getName());
		attributes.put("postalCode", getPostalCode());
		attributes.put("region", getRegion());
		attributes.put("street", getStreet());
		attributes.put("number", getNumber());
		attributes.put("building", getBuilding());
		attributes.put("floor", getFloor());
		attributes.put("room", getRoom());
		attributes.put("description", getDescription());
		attributes.put("imagePayload", getImagePayload());
		attributes.put("otherType", getOtherType());
		attributes.put("lastSeenDate", getLastSeenDate());
		attributes.put("latitude", getLatitude());
		attributes.put("longitude", getLongitude());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonAddressId = (Long)attributes.get("watsonAddressId");

		if (watsonAddressId != null) {
			setWatsonAddressId(watsonAddressId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		Long districtWatsonListTypeId = (Long)attributes.get(
				"districtWatsonListTypeId");

		if (districtWatsonListTypeId != null) {
			setDistrictWatsonListTypeId(districtWatsonListTypeId);
		}

		Long originalWatsonAddressId = (Long)attributes.get(
				"originalWatsonAddressId");

		if (originalWatsonAddressId != null) {
			setOriginalWatsonAddressId(originalWatsonAddressId);
		}

		Long provinceWatsonListTypeId = (Long)attributes.get(
				"provinceWatsonListTypeId");

		if (provinceWatsonListTypeId != null) {
			setProvinceWatsonListTypeId(provinceWatsonListTypeId);
		}

		Long subDistrictWatsonListTypeId = (Long)attributes.get(
				"subDistrictWatsonListTypeId");

		if (subDistrictWatsonListTypeId != null) {
			setSubDistrictWatsonListTypeId(subDistrictWatsonListTypeId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String postalCode = (String)attributes.get("postalCode");

		if (postalCode != null) {
			setPostalCode(postalCode);
		}

		String region = (String)attributes.get("region");

		if (region != null) {
			setRegion(region);
		}

		String street = (String)attributes.get("street");

		if (street != null) {
			setStreet(street);
		}

		String number = (String)attributes.get("number");

		if (number != null) {
			setNumber(number);
		}

		String building = (String)attributes.get("building");

		if (building != null) {
			setBuilding(building);
		}

		String floor = (String)attributes.get("floor");

		if (floor != null) {
			setFloor(floor);
		}

		String room = (String)attributes.get("room");

		if (room != null) {
			setRoom(room);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}

		String otherType = (String)attributes.get("otherType");

		if (otherType != null) {
			setOtherType(otherType);
		}

		Date lastSeenDate = (Date)attributes.get("lastSeenDate");

		if (lastSeenDate != null) {
			setLastSeenDate(lastSeenDate);
		}

		Double latitude = (Double)attributes.get("latitude");

		if (latitude != null) {
			setLatitude(latitude);
		}

		Double longitude = (Double)attributes.get("longitude");

		if (longitude != null) {
			setLongitude(longitude);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WatsonAddressWrapper((WatsonAddress)_watsonAddress.clone());
	}

	@Override
	public int compareTo(WatsonAddress watsonAddress) {
		return _watsonAddress.compareTo(watsonAddress);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _watsonAddress.getAvailableLanguageIds();
	}

	/**
	* Returns the building of this watson address.
	*
	* @return the building of this watson address
	*/
	@Override
	public java.lang.String getBuilding() {
		return _watsonAddress.getBuilding();
	}

	/**
	* Returns the localized building of this watson address in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized building of this watson address
	*/
	@Override
	public java.lang.String getBuilding(java.util.Locale locale) {
		return _watsonAddress.getBuilding(locale);
	}

	/**
	* Returns the localized building of this watson address in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized building of this watson address. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getBuilding(java.util.Locale locale,
		boolean useDefault) {
		return _watsonAddress.getBuilding(locale, useDefault);
	}

	/**
	* Returns the localized building of this watson address in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized building of this watson address
	*/
	@Override
	public java.lang.String getBuilding(java.lang.String languageId) {
		return _watsonAddress.getBuilding(languageId);
	}

	/**
	* Returns the localized building of this watson address in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized building of this watson address
	*/
	@Override
	public java.lang.String getBuilding(java.lang.String languageId,
		boolean useDefault) {
		return _watsonAddress.getBuilding(languageId, useDefault);
	}

	@Override
	public java.lang.String getBuildingCurrentLanguageId() {
		return _watsonAddress.getBuildingCurrentLanguageId();
	}

	@Override
	public java.lang.String getBuildingCurrentValue() {
		return _watsonAddress.getBuildingCurrentValue();
	}

	/**
	* Returns a map of the locales and localized buildings of this watson address.
	*
	* @return the locales and localized buildings of this watson address
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getBuildingMap() {
		return _watsonAddress.getBuildingMap();
	}

	/**
	* Returns the company ID of this watson address.
	*
	* @return the company ID of this watson address
	*/
	@Override
	public long getCompanyId() {
		return _watsonAddress.getCompanyId();
	}

	/**
	* Returns the country ID of this watson address.
	*
	* @return the country ID of this watson address
	*/
	@Override
	public long getCountryId() {
		return _watsonAddress.getCountryId();
	}

	/**
	* Returns the create date of this watson address.
	*
	* @return the create date of this watson address
	*/
	@Override
	public Date getCreateDate() {
		return _watsonAddress.getCreateDate();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _watsonAddress.getDefaultLanguageId();
	}

	/**
	* Returns the description of this watson address.
	*
	* @return the description of this watson address
	*/
	@Override
	public java.lang.String getDescription() {
		return _watsonAddress.getDescription();
	}

	/**
	* Returns the localized description of this watson address in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this watson address
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _watsonAddress.getDescription(locale);
	}

	/**
	* Returns the localized description of this watson address in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson address. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _watsonAddress.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this watson address in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this watson address
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _watsonAddress.getDescription(languageId);
	}

	/**
	* Returns the localized description of this watson address in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson address
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _watsonAddress.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _watsonAddress.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _watsonAddress.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this watson address.
	*
	* @return the locales and localized descriptions of this watson address
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _watsonAddress.getDescriptionMap();
	}

	/**
	* Returns the district watson list type ID of this watson address.
	*
	* @return the district watson list type ID of this watson address
	*/
	@Override
	public long getDistrictWatsonListTypeId() {
		return _watsonAddress.getDistrictWatsonListTypeId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonAddress.getExpandoBridge();
	}

	/**
	* Returns the floor of this watson address.
	*
	* @return the floor of this watson address
	*/
	@Override
	public java.lang.String getFloor() {
		return _watsonAddress.getFloor();
	}

	/**
	* Returns the group ID of this watson address.
	*
	* @return the group ID of this watson address
	*/
	@Override
	public long getGroupId() {
		return _watsonAddress.getGroupId();
	}

	/**
	* Returns the image payload of this watson address.
	*
	* @return the image payload of this watson address
	*/
	@Override
	public java.lang.String getImagePayload() {
		return _watsonAddress.getImagePayload();
	}

	/**
	* Returns the last seen date of this watson address.
	*
	* @return the last seen date of this watson address
	*/
	@Override
	public Date getLastSeenDate() {
		return _watsonAddress.getLastSeenDate();
	}

	/**
	* Returns the latitude of this watson address.
	*
	* @return the latitude of this watson address
	*/
	@Override
	public double getLatitude() {
		return _watsonAddress.getLatitude();
	}

	/**
	* Returns the longitude of this watson address.
	*
	* @return the longitude of this watson address
	*/
	@Override
	public double getLongitude() {
		return _watsonAddress.getLongitude();
	}

	/**
	* Returns the modified date of this watson address.
	*
	* @return the modified date of this watson address
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonAddress.getModifiedDate();
	}

	/**
	* Returns the name of this watson address.
	*
	* @return the name of this watson address
	*/
	@Override
	public java.lang.String getName() {
		return _watsonAddress.getName();
	}

	/**
	* Returns the localized name of this watson address in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this watson address
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _watsonAddress.getName(locale);
	}

	/**
	* Returns the localized name of this watson address in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson address. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _watsonAddress.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this watson address in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this watson address
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _watsonAddress.getName(languageId);
	}

	/**
	* Returns the localized name of this watson address in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson address
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _watsonAddress.getName(languageId, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _watsonAddress.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _watsonAddress.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this watson address.
	*
	* @return the locales and localized names of this watson address
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _watsonAddress.getNameMap();
	}

	/**
	* Returns the number of this watson address.
	*
	* @return the number of this watson address
	*/
	@Override
	public java.lang.String getNumber() {
		return _watsonAddress.getNumber();
	}

	/**
	* Returns the original watson address ID of this watson address.
	*
	* @return the original watson address ID of this watson address
	*/
	@Override
	public long getOriginalWatsonAddressId() {
		return _watsonAddress.getOriginalWatsonAddressId();
	}

	/**
	* Returns the other type of this watson address.
	*
	* @return the other type of this watson address
	*/
	@Override
	public java.lang.String getOtherType() {
		return _watsonAddress.getOtherType();
	}

	/**
	* Returns the postal code of this watson address.
	*
	* @return the postal code of this watson address
	*/
	@Override
	public java.lang.String getPostalCode() {
		return _watsonAddress.getPostalCode();
	}

	/**
	* Returns the primary key of this watson address.
	*
	* @return the primary key of this watson address
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonAddress.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonAddress.getPrimaryKeyObj();
	}

	/**
	* Returns the province watson list type ID of this watson address.
	*
	* @return the province watson list type ID of this watson address
	*/
	@Override
	public long getProvinceWatsonListTypeId() {
		return _watsonAddress.getProvinceWatsonListTypeId();
	}

	/**
	* Returns the region of this watson address.
	*
	* @return the region of this watson address
	*/
	@Override
	public java.lang.String getRegion() {
		return _watsonAddress.getRegion();
	}

	/**
	* Returns the room of this watson address.
	*
	* @return the room of this watson address
	*/
	@Override
	public java.lang.String getRoom() {
		return _watsonAddress.getRoom();
	}

	/**
	* Returns the status of this watson address.
	*
	* @return the status of this watson address
	*/
	@Override
	public int getStatus() {
		return _watsonAddress.getStatus();
	}

	/**
	* Returns the street of this watson address.
	*
	* @return the street of this watson address
	*/
	@Override
	public java.lang.String getStreet() {
		return _watsonAddress.getStreet();
	}

	/**
	* Returns the sub district watson list type ID of this watson address.
	*
	* @return the sub district watson list type ID of this watson address
	*/
	@Override
	public long getSubDistrictWatsonListTypeId() {
		return _watsonAddress.getSubDistrictWatsonListTypeId();
	}

	/**
	* Returns the type watson list type ID of this watson address.
	*
	* @return the type watson list type ID of this watson address
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonAddress.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson address.
	*
	* @return the user ID of this watson address
	*/
	@Override
	public long getUserId() {
		return _watsonAddress.getUserId();
	}

	/**
	* Returns the user name of this watson address.
	*
	* @return the user name of this watson address
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonAddress.getUserName();
	}

	/**
	* Returns the user uuid of this watson address.
	*
	* @return the user uuid of this watson address
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonAddress.getUserUuid();
	}

	/**
	* Returns the watson address ID of this watson address.
	*
	* @return the watson address ID of this watson address
	*/
	@Override
	public long getWatsonAddressId() {
		return _watsonAddress.getWatsonAddressId();
	}

	/**
	* Returns the watson incident ID of this watson address.
	*
	* @return the watson incident ID of this watson address
	*/
	@Override
	public long getWatsonIncidentId() {
		return _watsonAddress.getWatsonIncidentId();
	}

	@Override
	public int hashCode() {
		return _watsonAddress.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonAddress.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonAddress.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonAddress.isNew();
	}

	@Override
	public void persist() {
		_watsonAddress.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonAddress.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonAddress.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the building of this watson address.
	*
	* @param building the building of this watson address
	*/
	@Override
	public void setBuilding(java.lang.String building) {
		_watsonAddress.setBuilding(building);
	}

	/**
	* Sets the localized building of this watson address in the language.
	*
	* @param building the localized building of this watson address
	* @param locale the locale of the language
	*/
	@Override
	public void setBuilding(java.lang.String building, java.util.Locale locale) {
		_watsonAddress.setBuilding(building, locale);
	}

	/**
	* Sets the localized building of this watson address in the language, and sets the default locale.
	*
	* @param building the localized building of this watson address
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setBuilding(java.lang.String building, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonAddress.setBuilding(building, locale, defaultLocale);
	}

	@Override
	public void setBuildingCurrentLanguageId(java.lang.String languageId) {
		_watsonAddress.setBuildingCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized buildings of this watson address from the map of locales and localized buildings.
	*
	* @param buildingMap the locales and localized buildings of this watson address
	*/
	@Override
	public void setBuildingMap(
		Map<java.util.Locale, java.lang.String> buildingMap) {
		_watsonAddress.setBuildingMap(buildingMap);
	}

	/**
	* Sets the localized buildings of this watson address from the map of locales and localized buildings, and sets the default locale.
	*
	* @param buildingMap the locales and localized buildings of this watson address
	* @param defaultLocale the default locale
	*/
	@Override
	public void setBuildingMap(
		Map<java.util.Locale, java.lang.String> buildingMap,
		java.util.Locale defaultLocale) {
		_watsonAddress.setBuildingMap(buildingMap, defaultLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonAddress.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson address.
	*
	* @param companyId the company ID of this watson address
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonAddress.setCompanyId(companyId);
	}

	/**
	* Sets the country ID of this watson address.
	*
	* @param countryId the country ID of this watson address
	*/
	@Override
	public void setCountryId(long countryId) {
		_watsonAddress.setCountryId(countryId);
	}

	/**
	* Sets the create date of this watson address.
	*
	* @param createDate the create date of this watson address
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonAddress.setCreateDate(createDate);
	}

	/**
	* Sets the description of this watson address.
	*
	* @param description the description of this watson address
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_watsonAddress.setDescription(description);
	}

	/**
	* Sets the localized description of this watson address in the language.
	*
	* @param description the localized description of this watson address
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_watsonAddress.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this watson address in the language, and sets the default locale.
	*
	* @param description the localized description of this watson address
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonAddress.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_watsonAddress.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this watson address from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this watson address
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_watsonAddress.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this watson address from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this watson address
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_watsonAddress.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the district watson list type ID of this watson address.
	*
	* @param districtWatsonListTypeId the district watson list type ID of this watson address
	*/
	@Override
	public void setDistrictWatsonListTypeId(long districtWatsonListTypeId) {
		_watsonAddress.setDistrictWatsonListTypeId(districtWatsonListTypeId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonAddress.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonAddress.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonAddress.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the floor of this watson address.
	*
	* @param floor the floor of this watson address
	*/
	@Override
	public void setFloor(java.lang.String floor) {
		_watsonAddress.setFloor(floor);
	}

	/**
	* Sets the group ID of this watson address.
	*
	* @param groupId the group ID of this watson address
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonAddress.setGroupId(groupId);
	}

	/**
	* Sets the image payload of this watson address.
	*
	* @param imagePayload the image payload of this watson address
	*/
	@Override
	public void setImagePayload(java.lang.String imagePayload) {
		_watsonAddress.setImagePayload(imagePayload);
	}

	/**
	* Sets the last seen date of this watson address.
	*
	* @param lastSeenDate the last seen date of this watson address
	*/
	@Override
	public void setLastSeenDate(Date lastSeenDate) {
		_watsonAddress.setLastSeenDate(lastSeenDate);
	}

	/**
	* Sets the latitude of this watson address.
	*
	* @param latitude the latitude of this watson address
	*/
	@Override
	public void setLatitude(double latitude) {
		_watsonAddress.setLatitude(latitude);
	}

	/**
	* Sets the longitude of this watson address.
	*
	* @param longitude the longitude of this watson address
	*/
	@Override
	public void setLongitude(double longitude) {
		_watsonAddress.setLongitude(longitude);
	}

	/**
	* Sets the modified date of this watson address.
	*
	* @param modifiedDate the modified date of this watson address
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonAddress.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this watson address.
	*
	* @param name the name of this watson address
	*/
	@Override
	public void setName(java.lang.String name) {
		_watsonAddress.setName(name);
	}

	/**
	* Sets the localized name of this watson address in the language.
	*
	* @param name the localized name of this watson address
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_watsonAddress.setName(name, locale);
	}

	/**
	* Sets the localized name of this watson address in the language, and sets the default locale.
	*
	* @param name the localized name of this watson address
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonAddress.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_watsonAddress.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this watson address from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this watson address
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_watsonAddress.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this watson address from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this watson address
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_watsonAddress.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_watsonAddress.setNew(n);
	}

	/**
	* Sets the number of this watson address.
	*
	* @param number the number of this watson address
	*/
	@Override
	public void setNumber(java.lang.String number) {
		_watsonAddress.setNumber(number);
	}

	/**
	* Sets the original watson address ID of this watson address.
	*
	* @param originalWatsonAddressId the original watson address ID of this watson address
	*/
	@Override
	public void setOriginalWatsonAddressId(long originalWatsonAddressId) {
		_watsonAddress.setOriginalWatsonAddressId(originalWatsonAddressId);
	}

	/**
	* Sets the other type of this watson address.
	*
	* @param otherType the other type of this watson address
	*/
	@Override
	public void setOtherType(java.lang.String otherType) {
		_watsonAddress.setOtherType(otherType);
	}

	/**
	* Sets the postal code of this watson address.
	*
	* @param postalCode the postal code of this watson address
	*/
	@Override
	public void setPostalCode(java.lang.String postalCode) {
		_watsonAddress.setPostalCode(postalCode);
	}

	/**
	* Sets the primary key of this watson address.
	*
	* @param primaryKey the primary key of this watson address
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonAddress.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonAddress.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the province watson list type ID of this watson address.
	*
	* @param provinceWatsonListTypeId the province watson list type ID of this watson address
	*/
	@Override
	public void setProvinceWatsonListTypeId(long provinceWatsonListTypeId) {
		_watsonAddress.setProvinceWatsonListTypeId(provinceWatsonListTypeId);
	}

	/**
	* Sets the region of this watson address.
	*
	* @param region the region of this watson address
	*/
	@Override
	public void setRegion(java.lang.String region) {
		_watsonAddress.setRegion(region);
	}

	/**
	* Sets the room of this watson address.
	*
	* @param room the room of this watson address
	*/
	@Override
	public void setRoom(java.lang.String room) {
		_watsonAddress.setRoom(room);
	}

	/**
	* Sets the status of this watson address.
	*
	* @param status the status of this watson address
	*/
	@Override
	public void setStatus(int status) {
		_watsonAddress.setStatus(status);
	}

	/**
	* Sets the street of this watson address.
	*
	* @param street the street of this watson address
	*/
	@Override
	public void setStreet(java.lang.String street) {
		_watsonAddress.setStreet(street);
	}

	/**
	* Sets the sub district watson list type ID of this watson address.
	*
	* @param subDistrictWatsonListTypeId the sub district watson list type ID of this watson address
	*/
	@Override
	public void setSubDistrictWatsonListTypeId(long subDistrictWatsonListTypeId) {
		_watsonAddress.setSubDistrictWatsonListTypeId(subDistrictWatsonListTypeId);
	}

	/**
	* Sets the type watson list type ID of this watson address.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson address
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonAddress.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson address.
	*
	* @param userId the user ID of this watson address
	*/
	@Override
	public void setUserId(long userId) {
		_watsonAddress.setUserId(userId);
	}

	/**
	* Sets the user name of this watson address.
	*
	* @param userName the user name of this watson address
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonAddress.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson address.
	*
	* @param userUuid the user uuid of this watson address
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonAddress.setUserUuid(userUuid);
	}

	/**
	* Sets the watson address ID of this watson address.
	*
	* @param watsonAddressId the watson address ID of this watson address
	*/
	@Override
	public void setWatsonAddressId(long watsonAddressId) {
		_watsonAddress.setWatsonAddressId(watsonAddressId);
	}

	/**
	* Sets the watson incident ID of this watson address.
	*
	* @param watsonIncidentId the watson incident ID of this watson address
	*/
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonAddress.setWatsonIncidentId(watsonIncidentId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonAddress> toCacheModel() {
		return _watsonAddress.toCacheModel();
	}

	@Override
	public WatsonAddress toEscapedModel() {
		return new WatsonAddressWrapper(_watsonAddress.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonAddress.toString();
	}

	@Override
	public WatsonAddress toUnescapedModel() {
		return new WatsonAddressWrapper(_watsonAddress.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonAddress.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonAddressWrapper)) {
			return false;
		}

		WatsonAddressWrapper watsonAddressWrapper = (WatsonAddressWrapper)obj;

		if (Objects.equals(_watsonAddress, watsonAddressWrapper._watsonAddress)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonAddress getWrappedModel() {
		return _watsonAddress;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonAddress.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonAddress.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonAddress.resetOriginalValues();
	}

	private final WatsonAddress _watsonAddress;
}