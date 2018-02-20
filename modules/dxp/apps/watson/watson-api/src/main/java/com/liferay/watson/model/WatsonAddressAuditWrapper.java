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
 * This class is a wrapper for {@link WatsonAddressAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonAddressAudit
 * @generated
 */
@ProviderType
public class WatsonAddressAuditWrapper implements WatsonAddressAudit,
	ModelWrapper<WatsonAddressAudit> {
	public WatsonAddressAuditWrapper(WatsonAddressAudit watsonAddressAudit) {
		_watsonAddressAudit = watsonAddressAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonAddressAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonAddressAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonAddressAuditId", getWatsonAddressAuditId());
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
		attributes.put("watsonAddressId", getWatsonAddressId());
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
		Long watsonAddressAuditId = (Long)attributes.get("watsonAddressAuditId");

		if (watsonAddressAuditId != null) {
			setWatsonAddressAuditId(watsonAddressAuditId);
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

		Long watsonAddressId = (Long)attributes.get("watsonAddressId");

		if (watsonAddressId != null) {
			setWatsonAddressId(watsonAddressId);
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
		return new WatsonAddressAuditWrapper((WatsonAddressAudit)_watsonAddressAudit.clone());
	}

	@Override
	public int compareTo(WatsonAddressAudit watsonAddressAudit) {
		return _watsonAddressAudit.compareTo(watsonAddressAudit);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _watsonAddressAudit.getAvailableLanguageIds();
	}

	/**
	* Returns the building of this watson address audit.
	*
	* @return the building of this watson address audit
	*/
	@Override
	public java.lang.String getBuilding() {
		return _watsonAddressAudit.getBuilding();
	}

	/**
	* Returns the localized building of this watson address audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized building of this watson address audit
	*/
	@Override
	public java.lang.String getBuilding(java.util.Locale locale) {
		return _watsonAddressAudit.getBuilding(locale);
	}

	/**
	* Returns the localized building of this watson address audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized building of this watson address audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getBuilding(java.util.Locale locale,
		boolean useDefault) {
		return _watsonAddressAudit.getBuilding(locale, useDefault);
	}

	/**
	* Returns the localized building of this watson address audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized building of this watson address audit
	*/
	@Override
	public java.lang.String getBuilding(java.lang.String languageId) {
		return _watsonAddressAudit.getBuilding(languageId);
	}

	/**
	* Returns the localized building of this watson address audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized building of this watson address audit
	*/
	@Override
	public java.lang.String getBuilding(java.lang.String languageId,
		boolean useDefault) {
		return _watsonAddressAudit.getBuilding(languageId, useDefault);
	}

	@Override
	public java.lang.String getBuildingCurrentLanguageId() {
		return _watsonAddressAudit.getBuildingCurrentLanguageId();
	}

	@Override
	public java.lang.String getBuildingCurrentValue() {
		return _watsonAddressAudit.getBuildingCurrentValue();
	}

	/**
	* Returns a map of the locales and localized buildings of this watson address audit.
	*
	* @return the locales and localized buildings of this watson address audit
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getBuildingMap() {
		return _watsonAddressAudit.getBuildingMap();
	}

	/**
	* Returns the company ID of this watson address audit.
	*
	* @return the company ID of this watson address audit
	*/
	@Override
	public long getCompanyId() {
		return _watsonAddressAudit.getCompanyId();
	}

	/**
	* Returns the country ID of this watson address audit.
	*
	* @return the country ID of this watson address audit
	*/
	@Override
	public long getCountryId() {
		return _watsonAddressAudit.getCountryId();
	}

	/**
	* Returns the create date of this watson address audit.
	*
	* @return the create date of this watson address audit
	*/
	@Override
	public Date getCreateDate() {
		return _watsonAddressAudit.getCreateDate();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _watsonAddressAudit.getDefaultLanguageId();
	}

	/**
	* Returns the description of this watson address audit.
	*
	* @return the description of this watson address audit
	*/
	@Override
	public java.lang.String getDescription() {
		return _watsonAddressAudit.getDescription();
	}

	/**
	* Returns the localized description of this watson address audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this watson address audit
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _watsonAddressAudit.getDescription(locale);
	}

	/**
	* Returns the localized description of this watson address audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson address audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _watsonAddressAudit.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this watson address audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this watson address audit
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _watsonAddressAudit.getDescription(languageId);
	}

	/**
	* Returns the localized description of this watson address audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson address audit
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _watsonAddressAudit.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _watsonAddressAudit.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _watsonAddressAudit.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this watson address audit.
	*
	* @return the locales and localized descriptions of this watson address audit
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _watsonAddressAudit.getDescriptionMap();
	}

	/**
	* Returns the district watson list type ID of this watson address audit.
	*
	* @return the district watson list type ID of this watson address audit
	*/
	@Override
	public long getDistrictWatsonListTypeId() {
		return _watsonAddressAudit.getDistrictWatsonListTypeId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonAddressAudit.getExpandoBridge();
	}

	/**
	* Returns the floor of this watson address audit.
	*
	* @return the floor of this watson address audit
	*/
	@Override
	public java.lang.String getFloor() {
		return _watsonAddressAudit.getFloor();
	}

	/**
	* Returns the group ID of this watson address audit.
	*
	* @return the group ID of this watson address audit
	*/
	@Override
	public long getGroupId() {
		return _watsonAddressAudit.getGroupId();
	}

	/**
	* Returns the image payload of this watson address audit.
	*
	* @return the image payload of this watson address audit
	*/
	@Override
	public java.lang.String getImagePayload() {
		return _watsonAddressAudit.getImagePayload();
	}

	/**
	* Returns the last seen date of this watson address audit.
	*
	* @return the last seen date of this watson address audit
	*/
	@Override
	public Date getLastSeenDate() {
		return _watsonAddressAudit.getLastSeenDate();
	}

	/**
	* Returns the latitude of this watson address audit.
	*
	* @return the latitude of this watson address audit
	*/
	@Override
	public double getLatitude() {
		return _watsonAddressAudit.getLatitude();
	}

	/**
	* Returns the longitude of this watson address audit.
	*
	* @return the longitude of this watson address audit
	*/
	@Override
	public double getLongitude() {
		return _watsonAddressAudit.getLongitude();
	}

	/**
	* Returns the modified date of this watson address audit.
	*
	* @return the modified date of this watson address audit
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonAddressAudit.getModifiedDate();
	}

	/**
	* Returns the name of this watson address audit.
	*
	* @return the name of this watson address audit
	*/
	@Override
	public java.lang.String getName() {
		return _watsonAddressAudit.getName();
	}

	/**
	* Returns the localized name of this watson address audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this watson address audit
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _watsonAddressAudit.getName(locale);
	}

	/**
	* Returns the localized name of this watson address audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson address audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _watsonAddressAudit.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this watson address audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this watson address audit
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _watsonAddressAudit.getName(languageId);
	}

	/**
	* Returns the localized name of this watson address audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson address audit
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _watsonAddressAudit.getName(languageId, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _watsonAddressAudit.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _watsonAddressAudit.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this watson address audit.
	*
	* @return the locales and localized names of this watson address audit
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getNameMap() {
		return _watsonAddressAudit.getNameMap();
	}

	/**
	* Returns the number of this watson address audit.
	*
	* @return the number of this watson address audit
	*/
	@Override
	public java.lang.String getNumber() {
		return _watsonAddressAudit.getNumber();
	}

	/**
	* Returns the original watson address ID of this watson address audit.
	*
	* @return the original watson address ID of this watson address audit
	*/
	@Override
	public long getOriginalWatsonAddressId() {
		return _watsonAddressAudit.getOriginalWatsonAddressId();
	}

	/**
	* Returns the other type of this watson address audit.
	*
	* @return the other type of this watson address audit
	*/
	@Override
	public java.lang.String getOtherType() {
		return _watsonAddressAudit.getOtherType();
	}

	/**
	* Returns the postal code of this watson address audit.
	*
	* @return the postal code of this watson address audit
	*/
	@Override
	public java.lang.String getPostalCode() {
		return _watsonAddressAudit.getPostalCode();
	}

	/**
	* Returns the primary key of this watson address audit.
	*
	* @return the primary key of this watson address audit
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonAddressAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonAddressAudit.getPrimaryKeyObj();
	}

	/**
	* Returns the province watson list type ID of this watson address audit.
	*
	* @return the province watson list type ID of this watson address audit
	*/
	@Override
	public long getProvinceWatsonListTypeId() {
		return _watsonAddressAudit.getProvinceWatsonListTypeId();
	}

	/**
	* Returns the region of this watson address audit.
	*
	* @return the region of this watson address audit
	*/
	@Override
	public java.lang.String getRegion() {
		return _watsonAddressAudit.getRegion();
	}

	/**
	* Returns the room of this watson address audit.
	*
	* @return the room of this watson address audit
	*/
	@Override
	public java.lang.String getRoom() {
		return _watsonAddressAudit.getRoom();
	}

	/**
	* Returns the status of this watson address audit.
	*
	* @return the status of this watson address audit
	*/
	@Override
	public int getStatus() {
		return _watsonAddressAudit.getStatus();
	}

	/**
	* Returns the street of this watson address audit.
	*
	* @return the street of this watson address audit
	*/
	@Override
	public java.lang.String getStreet() {
		return _watsonAddressAudit.getStreet();
	}

	/**
	* Returns the sub district watson list type ID of this watson address audit.
	*
	* @return the sub district watson list type ID of this watson address audit
	*/
	@Override
	public long getSubDistrictWatsonListTypeId() {
		return _watsonAddressAudit.getSubDistrictWatsonListTypeId();
	}

	/**
	* Returns the type watson list type ID of this watson address audit.
	*
	* @return the type watson list type ID of this watson address audit
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonAddressAudit.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson address audit.
	*
	* @return the user ID of this watson address audit
	*/
	@Override
	public long getUserId() {
		return _watsonAddressAudit.getUserId();
	}

	/**
	* Returns the user name of this watson address audit.
	*
	* @return the user name of this watson address audit
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonAddressAudit.getUserName();
	}

	/**
	* Returns the user uuid of this watson address audit.
	*
	* @return the user uuid of this watson address audit
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonAddressAudit.getUserUuid();
	}

	/**
	* Returns the watson address audit ID of this watson address audit.
	*
	* @return the watson address audit ID of this watson address audit
	*/
	@Override
	public long getWatsonAddressAuditId() {
		return _watsonAddressAudit.getWatsonAddressAuditId();
	}

	/**
	* Returns the watson address ID of this watson address audit.
	*
	* @return the watson address ID of this watson address audit
	*/
	@Override
	public long getWatsonAddressId() {
		return _watsonAddressAudit.getWatsonAddressId();
	}

	/**
	* Returns the watson incident ID of this watson address audit.
	*
	* @return the watson incident ID of this watson address audit
	*/
	@Override
	public long getWatsonIncidentId() {
		return _watsonAddressAudit.getWatsonIncidentId();
	}

	@Override
	public int hashCode() {
		return _watsonAddressAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonAddressAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonAddressAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonAddressAudit.isNew();
	}

	@Override
	public void persist() {
		_watsonAddressAudit.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonAddressAudit.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonAddressAudit.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets the building of this watson address audit.
	*
	* @param building the building of this watson address audit
	*/
	@Override
	public void setBuilding(java.lang.String building) {
		_watsonAddressAudit.setBuilding(building);
	}

	/**
	* Sets the localized building of this watson address audit in the language.
	*
	* @param building the localized building of this watson address audit
	* @param locale the locale of the language
	*/
	@Override
	public void setBuilding(java.lang.String building, java.util.Locale locale) {
		_watsonAddressAudit.setBuilding(building, locale);
	}

	/**
	* Sets the localized building of this watson address audit in the language, and sets the default locale.
	*
	* @param building the localized building of this watson address audit
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setBuilding(java.lang.String building, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonAddressAudit.setBuilding(building, locale, defaultLocale);
	}

	@Override
	public void setBuildingCurrentLanguageId(java.lang.String languageId) {
		_watsonAddressAudit.setBuildingCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized buildings of this watson address audit from the map of locales and localized buildings.
	*
	* @param buildingMap the locales and localized buildings of this watson address audit
	*/
	@Override
	public void setBuildingMap(
		Map<java.util.Locale, java.lang.String> buildingMap) {
		_watsonAddressAudit.setBuildingMap(buildingMap);
	}

	/**
	* Sets the localized buildings of this watson address audit from the map of locales and localized buildings, and sets the default locale.
	*
	* @param buildingMap the locales and localized buildings of this watson address audit
	* @param defaultLocale the default locale
	*/
	@Override
	public void setBuildingMap(
		Map<java.util.Locale, java.lang.String> buildingMap,
		java.util.Locale defaultLocale) {
		_watsonAddressAudit.setBuildingMap(buildingMap, defaultLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonAddressAudit.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson address audit.
	*
	* @param companyId the company ID of this watson address audit
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonAddressAudit.setCompanyId(companyId);
	}

	/**
	* Sets the country ID of this watson address audit.
	*
	* @param countryId the country ID of this watson address audit
	*/
	@Override
	public void setCountryId(long countryId) {
		_watsonAddressAudit.setCountryId(countryId);
	}

	/**
	* Sets the create date of this watson address audit.
	*
	* @param createDate the create date of this watson address audit
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonAddressAudit.setCreateDate(createDate);
	}

	/**
	* Sets the description of this watson address audit.
	*
	* @param description the description of this watson address audit
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_watsonAddressAudit.setDescription(description);
	}

	/**
	* Sets the localized description of this watson address audit in the language.
	*
	* @param description the localized description of this watson address audit
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_watsonAddressAudit.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this watson address audit in the language, and sets the default locale.
	*
	* @param description the localized description of this watson address audit
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonAddressAudit.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_watsonAddressAudit.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this watson address audit from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this watson address audit
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_watsonAddressAudit.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this watson address audit from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this watson address audit
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_watsonAddressAudit.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the district watson list type ID of this watson address audit.
	*
	* @param districtWatsonListTypeId the district watson list type ID of this watson address audit
	*/
	@Override
	public void setDistrictWatsonListTypeId(long districtWatsonListTypeId) {
		_watsonAddressAudit.setDistrictWatsonListTypeId(districtWatsonListTypeId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonAddressAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonAddressAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonAddressAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the floor of this watson address audit.
	*
	* @param floor the floor of this watson address audit
	*/
	@Override
	public void setFloor(java.lang.String floor) {
		_watsonAddressAudit.setFloor(floor);
	}

	/**
	* Sets the group ID of this watson address audit.
	*
	* @param groupId the group ID of this watson address audit
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonAddressAudit.setGroupId(groupId);
	}

	/**
	* Sets the image payload of this watson address audit.
	*
	* @param imagePayload the image payload of this watson address audit
	*/
	@Override
	public void setImagePayload(java.lang.String imagePayload) {
		_watsonAddressAudit.setImagePayload(imagePayload);
	}

	/**
	* Sets the last seen date of this watson address audit.
	*
	* @param lastSeenDate the last seen date of this watson address audit
	*/
	@Override
	public void setLastSeenDate(Date lastSeenDate) {
		_watsonAddressAudit.setLastSeenDate(lastSeenDate);
	}

	/**
	* Sets the latitude of this watson address audit.
	*
	* @param latitude the latitude of this watson address audit
	*/
	@Override
	public void setLatitude(double latitude) {
		_watsonAddressAudit.setLatitude(latitude);
	}

	/**
	* Sets the longitude of this watson address audit.
	*
	* @param longitude the longitude of this watson address audit
	*/
	@Override
	public void setLongitude(double longitude) {
		_watsonAddressAudit.setLongitude(longitude);
	}

	/**
	* Sets the modified date of this watson address audit.
	*
	* @param modifiedDate the modified date of this watson address audit
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonAddressAudit.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this watson address audit.
	*
	* @param name the name of this watson address audit
	*/
	@Override
	public void setName(java.lang.String name) {
		_watsonAddressAudit.setName(name);
	}

	/**
	* Sets the localized name of this watson address audit in the language.
	*
	* @param name the localized name of this watson address audit
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_watsonAddressAudit.setName(name, locale);
	}

	/**
	* Sets the localized name of this watson address audit in the language, and sets the default locale.
	*
	* @param name the localized name of this watson address audit
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonAddressAudit.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_watsonAddressAudit.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this watson address audit from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this watson address audit
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap) {
		_watsonAddressAudit.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this watson address audit from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this watson address audit
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_watsonAddressAudit.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_watsonAddressAudit.setNew(n);
	}

	/**
	* Sets the number of this watson address audit.
	*
	* @param number the number of this watson address audit
	*/
	@Override
	public void setNumber(java.lang.String number) {
		_watsonAddressAudit.setNumber(number);
	}

	/**
	* Sets the original watson address ID of this watson address audit.
	*
	* @param originalWatsonAddressId the original watson address ID of this watson address audit
	*/
	@Override
	public void setOriginalWatsonAddressId(long originalWatsonAddressId) {
		_watsonAddressAudit.setOriginalWatsonAddressId(originalWatsonAddressId);
	}

	/**
	* Sets the other type of this watson address audit.
	*
	* @param otherType the other type of this watson address audit
	*/
	@Override
	public void setOtherType(java.lang.String otherType) {
		_watsonAddressAudit.setOtherType(otherType);
	}

	/**
	* Sets the postal code of this watson address audit.
	*
	* @param postalCode the postal code of this watson address audit
	*/
	@Override
	public void setPostalCode(java.lang.String postalCode) {
		_watsonAddressAudit.setPostalCode(postalCode);
	}

	/**
	* Sets the primary key of this watson address audit.
	*
	* @param primaryKey the primary key of this watson address audit
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonAddressAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonAddressAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the province watson list type ID of this watson address audit.
	*
	* @param provinceWatsonListTypeId the province watson list type ID of this watson address audit
	*/
	@Override
	public void setProvinceWatsonListTypeId(long provinceWatsonListTypeId) {
		_watsonAddressAudit.setProvinceWatsonListTypeId(provinceWatsonListTypeId);
	}

	/**
	* Sets the region of this watson address audit.
	*
	* @param region the region of this watson address audit
	*/
	@Override
	public void setRegion(java.lang.String region) {
		_watsonAddressAudit.setRegion(region);
	}

	/**
	* Sets the room of this watson address audit.
	*
	* @param room the room of this watson address audit
	*/
	@Override
	public void setRoom(java.lang.String room) {
		_watsonAddressAudit.setRoom(room);
	}

	/**
	* Sets the status of this watson address audit.
	*
	* @param status the status of this watson address audit
	*/
	@Override
	public void setStatus(int status) {
		_watsonAddressAudit.setStatus(status);
	}

	/**
	* Sets the street of this watson address audit.
	*
	* @param street the street of this watson address audit
	*/
	@Override
	public void setStreet(java.lang.String street) {
		_watsonAddressAudit.setStreet(street);
	}

	/**
	* Sets the sub district watson list type ID of this watson address audit.
	*
	* @param subDistrictWatsonListTypeId the sub district watson list type ID of this watson address audit
	*/
	@Override
	public void setSubDistrictWatsonListTypeId(long subDistrictWatsonListTypeId) {
		_watsonAddressAudit.setSubDistrictWatsonListTypeId(subDistrictWatsonListTypeId);
	}

	/**
	* Sets the type watson list type ID of this watson address audit.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson address audit
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonAddressAudit.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson address audit.
	*
	* @param userId the user ID of this watson address audit
	*/
	@Override
	public void setUserId(long userId) {
		_watsonAddressAudit.setUserId(userId);
	}

	/**
	* Sets the user name of this watson address audit.
	*
	* @param userName the user name of this watson address audit
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonAddressAudit.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson address audit.
	*
	* @param userUuid the user uuid of this watson address audit
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonAddressAudit.setUserUuid(userUuid);
	}

	/**
	* Sets the watson address audit ID of this watson address audit.
	*
	* @param watsonAddressAuditId the watson address audit ID of this watson address audit
	*/
	@Override
	public void setWatsonAddressAuditId(long watsonAddressAuditId) {
		_watsonAddressAudit.setWatsonAddressAuditId(watsonAddressAuditId);
	}

	/**
	* Sets the watson address ID of this watson address audit.
	*
	* @param watsonAddressId the watson address ID of this watson address audit
	*/
	@Override
	public void setWatsonAddressId(long watsonAddressId) {
		_watsonAddressAudit.setWatsonAddressId(watsonAddressId);
	}

	/**
	* Sets the watson incident ID of this watson address audit.
	*
	* @param watsonIncidentId the watson incident ID of this watson address audit
	*/
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonAddressAudit.setWatsonIncidentId(watsonIncidentId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonAddressAudit> toCacheModel() {
		return _watsonAddressAudit.toCacheModel();
	}

	@Override
	public WatsonAddressAudit toEscapedModel() {
		return new WatsonAddressAuditWrapper(_watsonAddressAudit.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonAddressAudit.toString();
	}

	@Override
	public WatsonAddressAudit toUnescapedModel() {
		return new WatsonAddressAuditWrapper(_watsonAddressAudit.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonAddressAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonAddressAuditWrapper)) {
			return false;
		}

		WatsonAddressAuditWrapper watsonAddressAuditWrapper = (WatsonAddressAuditWrapper)obj;

		if (Objects.equals(_watsonAddressAudit,
					watsonAddressAuditWrapper._watsonAddressAudit)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonAddressAudit getWrappedModel() {
		return _watsonAddressAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonAddressAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonAddressAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonAddressAudit.resetOriginalValues();
	}

	private final WatsonAddressAudit _watsonAddressAudit;
}