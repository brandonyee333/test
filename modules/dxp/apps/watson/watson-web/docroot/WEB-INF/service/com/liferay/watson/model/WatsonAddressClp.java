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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import com.liferay.watson.service.ClpSerializer;
import com.liferay.watson.service.WatsonAddressLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @generated
 */
@ProviderType
public class WatsonAddressClp extends BaseModelImpl<WatsonAddress>
	implements WatsonAddress {
	public WatsonAddressClp() {
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
	public long getPrimaryKey() {
		return _watsonAddressId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWatsonAddressId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonAddressId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonAddressId", getWatsonAddressId());
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
		attributes.put("lastSeenDate", getLastSeenDate());
		attributes.put("latitude", getLatitude());
		attributes.put("longitude", getLongitude());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonAddressId = (Long)attributes.get("watsonAddressId");

		if (watsonAddressId != null) {
			setWatsonAddressId(watsonAddressId);
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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getWatsonAddressId() {
		return _watsonAddressId;
	}

	@Override
	public void setWatsonAddressId(long watsonAddressId) {
		_watsonAddressId = watsonAddressId;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonAddressId", long.class);

				method.invoke(_watsonAddressRemoteModel, watsonAddressId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_watsonAddressRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_watsonAddressRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_watsonAddressRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_watsonAddressRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_watsonAddressRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCountryId() {
		return _countryId;
	}

	@Override
	public void setCountryId(long countryId) {
		_countryId = countryId;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setCountryId", long.class);

				method.invoke(_watsonAddressRemoteModel, countryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getDistrictWatsonListTypeId() {
		return _districtWatsonListTypeId;
	}

	@Override
	public void setDistrictWatsonListTypeId(long districtWatsonListTypeId) {
		_districtWatsonListTypeId = districtWatsonListTypeId;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setDistrictWatsonListTypeId",
						long.class);

				method.invoke(_watsonAddressRemoteModel,
					districtWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getOriginalWatsonAddressId() {
		return _originalWatsonAddressId;
	}

	@Override
	public void setOriginalWatsonAddressId(long originalWatsonAddressId) {
		_originalWatsonAddressId = originalWatsonAddressId;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setOriginalWatsonAddressId",
						long.class);

				method.invoke(_watsonAddressRemoteModel, originalWatsonAddressId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getProvinceWatsonListTypeId() {
		return _provinceWatsonListTypeId;
	}

	@Override
	public void setProvinceWatsonListTypeId(long provinceWatsonListTypeId) {
		_provinceWatsonListTypeId = provinceWatsonListTypeId;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setProvinceWatsonListTypeId",
						long.class);

				method.invoke(_watsonAddressRemoteModel,
					provinceWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSubDistrictWatsonListTypeId() {
		return _subDistrictWatsonListTypeId;
	}

	@Override
	public void setSubDistrictWatsonListTypeId(long subDistrictWatsonListTypeId) {
		_subDistrictWatsonListTypeId = subDistrictWatsonListTypeId;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setSubDistrictWatsonListTypeId",
						long.class);

				method.invoke(_watsonAddressRemoteModel,
					subDistrictWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTypeWatsonListTypeId() {
		return _typeWatsonListTypeId;
	}

	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_typeWatsonListTypeId = typeWatsonListTypeId;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setTypeWatsonListTypeId",
						long.class);

				method.invoke(_watsonAddressRemoteModel, typeWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getWatsonIncidentId() {
		return _watsonIncidentId;
	}

	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncidentId = watsonIncidentId;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonIncidentId",
						long.class);

				method.invoke(_watsonAddressRemoteModel, watsonIncidentId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getName(), languageId,
			useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_watsonAddressRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(LocalizationUtil.updateLocalization(getName(), "Name",
					name, languageId, defaultLanguageId));
		}
		else {
			setName(LocalizationUtil.removeLocalization(getName(), "Name",
					languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setName(LocalizationUtil.updateLocalization(nameMap, getName(),
					"Name", LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getPostalCode() {
		return _postalCode;
	}

	@Override
	public void setPostalCode(String postalCode) {
		_postalCode = postalCode;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setPostalCode", String.class);

				method.invoke(_watsonAddressRemoteModel, postalCode);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRegion() {
		return _region;
	}

	@Override
	public void setRegion(String region) {
		_region = region;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setRegion", String.class);

				method.invoke(_watsonAddressRemoteModel, region);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStreet() {
		return _street;
	}

	@Override
	public void setStreet(String street) {
		_street = street;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setStreet", String.class);

				method.invoke(_watsonAddressRemoteModel, street);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNumber() {
		return _number;
	}

	@Override
	public void setNumber(String number) {
		_number = number;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setNumber", String.class);

				method.invoke(_watsonAddressRemoteModel, number);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getBuilding() {
		return _building;
	}

	@Override
	public String getBuilding(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getBuilding(languageId);
	}

	@Override
	public String getBuilding(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getBuilding(languageId, useDefault);
	}

	@Override
	public String getBuilding(String languageId) {
		return LocalizationUtil.getLocalization(getBuilding(), languageId);
	}

	@Override
	public String getBuilding(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getBuilding(), languageId,
			useDefault);
	}

	@Override
	public String getBuildingCurrentLanguageId() {
		return _buildingCurrentLanguageId;
	}

	@Override
	public String getBuildingCurrentValue() {
		Locale locale = getLocale(_buildingCurrentLanguageId);

		return getBuilding(locale);
	}

	@Override
	public Map<Locale, String> getBuildingMap() {
		return LocalizationUtil.getLocalizationMap(getBuilding());
	}

	@Override
	public void setBuilding(String building) {
		_building = building;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setBuilding", String.class);

				method.invoke(_watsonAddressRemoteModel, building);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setBuilding(String building, Locale locale) {
		setBuilding(building, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setBuilding(String building, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(building)) {
			setBuilding(LocalizationUtil.updateLocalization(getBuilding(),
					"Building", building, languageId, defaultLanguageId));
		}
		else {
			setBuilding(LocalizationUtil.removeLocalization(getBuilding(),
					"Building", languageId));
		}
	}

	@Override
	public void setBuildingCurrentLanguageId(String languageId) {
		_buildingCurrentLanguageId = languageId;
	}

	@Override
	public void setBuildingMap(Map<Locale, String> buildingMap) {
		setBuildingMap(buildingMap, LocaleUtil.getDefault());
	}

	@Override
	public void setBuildingMap(Map<Locale, String> buildingMap,
		Locale defaultLocale) {
		if (buildingMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setBuilding(LocalizationUtil.updateLocalization(buildingMap,
					getBuilding(), "Building",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getFloor() {
		return _floor;
	}

	@Override
	public void setFloor(String floor) {
		_floor = floor;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setFloor", String.class);

				method.invoke(_watsonAddressRemoteModel, floor);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRoom() {
		return _room;
	}

	@Override
	public void setRoom(String room) {
		_room = room;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setRoom", String.class);

				method.invoke(_watsonAddressRemoteModel, room);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_watsonAddressRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setDescription(String description, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale) {
		if (descriptionMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setDescription(LocalizationUtil.updateLocalization(descriptionMap,
					getDescription(), "Description",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getImagePayload() {
		return _imagePayload;
	}

	@Override
	public void setImagePayload(String imagePayload) {
		_imagePayload = imagePayload;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setImagePayload", String.class);

				method.invoke(_watsonAddressRemoteModel, imagePayload);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getLastSeenDate() {
		return _lastSeenDate;
	}

	@Override
	public void setLastSeenDate(Date lastSeenDate) {
		_lastSeenDate = lastSeenDate;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setLastSeenDate", Date.class);

				method.invoke(_watsonAddressRemoteModel, lastSeenDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public double getLatitude() {
		return _latitude;
	}

	@Override
	public void setLatitude(double latitude) {
		_latitude = latitude;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setLatitude", double.class);

				method.invoke(_watsonAddressRemoteModel, latitude);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public double getLongitude() {
		return _longitude;
	}

	@Override
	public void setLongitude(double longitude) {
		_longitude = longitude;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setLongitude", double.class);

				method.invoke(_watsonAddressRemoteModel, longitude);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_watsonAddressRemoteModel != null) {
			try {
				Class<?> clazz = _watsonAddressRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_watsonAddressRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getWatsonAddressRemoteModel() {
		return _watsonAddressRemoteModel;
	}

	public void setWatsonAddressRemoteModel(
		BaseModel<?> watsonAddressRemoteModel) {
		_watsonAddressRemoteModel = watsonAddressRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _watsonAddressRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_watsonAddressRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			WatsonAddressLocalServiceUtil.addWatsonAddress(this);
		}
		else {
			WatsonAddressLocalServiceUtil.updateWatsonAddress(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> buildingMap = getBuildingMap();

		for (Map.Entry<Locale, String> entry : buildingMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return StringPool.BLANK;
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		prepareLocalizedFieldsForImport(null);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}

		String building = getBuilding(defaultLocale);

		if (Validator.isNull(building)) {
			setBuilding(getBuilding(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setBuilding(getBuilding(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(getDescription(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public WatsonAddress toEscapedModel() {
		return (WatsonAddress)ProxyUtil.newProxyInstance(WatsonAddress.class.getClassLoader(),
			new Class[] { WatsonAddress.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WatsonAddressClp clone = new WatsonAddressClp();

		clone.setWatsonAddressId(getWatsonAddressId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setCountryId(getCountryId());
		clone.setDistrictWatsonListTypeId(getDistrictWatsonListTypeId());
		clone.setOriginalWatsonAddressId(getOriginalWatsonAddressId());
		clone.setProvinceWatsonListTypeId(getProvinceWatsonListTypeId());
		clone.setSubDistrictWatsonListTypeId(getSubDistrictWatsonListTypeId());
		clone.setTypeWatsonListTypeId(getTypeWatsonListTypeId());
		clone.setWatsonIncidentId(getWatsonIncidentId());
		clone.setName(getName());
		clone.setPostalCode(getPostalCode());
		clone.setRegion(getRegion());
		clone.setStreet(getStreet());
		clone.setNumber(getNumber());
		clone.setBuilding(getBuilding());
		clone.setFloor(getFloor());
		clone.setRoom(getRoom());
		clone.setDescription(getDescription());
		clone.setImagePayload(getImagePayload());
		clone.setLastSeenDate(getLastSeenDate());
		clone.setLatitude(getLatitude());
		clone.setLongitude(getLongitude());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(WatsonAddress watsonAddress) {
		long primaryKey = watsonAddress.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonAddressClp)) {
			return false;
		}

		WatsonAddressClp watsonAddress = (WatsonAddressClp)obj;

		long primaryKey = watsonAddress.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(55);

		sb.append("{watsonAddressId=");
		sb.append(getWatsonAddressId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", countryId=");
		sb.append(getCountryId());
		sb.append(", districtWatsonListTypeId=");
		sb.append(getDistrictWatsonListTypeId());
		sb.append(", originalWatsonAddressId=");
		sb.append(getOriginalWatsonAddressId());
		sb.append(", provinceWatsonListTypeId=");
		sb.append(getProvinceWatsonListTypeId());
		sb.append(", subDistrictWatsonListTypeId=");
		sb.append(getSubDistrictWatsonListTypeId());
		sb.append(", typeWatsonListTypeId=");
		sb.append(getTypeWatsonListTypeId());
		sb.append(", watsonIncidentId=");
		sb.append(getWatsonIncidentId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", postalCode=");
		sb.append(getPostalCode());
		sb.append(", region=");
		sb.append(getRegion());
		sb.append(", street=");
		sb.append(getStreet());
		sb.append(", number=");
		sb.append(getNumber());
		sb.append(", building=");
		sb.append(getBuilding());
		sb.append(", floor=");
		sb.append(getFloor());
		sb.append(", room=");
		sb.append(getRoom());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", imagePayload=");
		sb.append(getImagePayload());
		sb.append(", lastSeenDate=");
		sb.append(getLastSeenDate());
		sb.append(", latitude=");
		sb.append(getLatitude());
		sb.append(", longitude=");
		sb.append(getLongitude());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(85);

		sb.append("<model><model-name>");
		sb.append("com.liferay.watson.model.WatsonAddress");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>watsonAddressId</column-name><column-value><![CDATA[");
		sb.append(getWatsonAddressId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>countryId</column-name><column-value><![CDATA[");
		sb.append(getCountryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>districtWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getDistrictWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>originalWatsonAddressId</column-name><column-value><![CDATA[");
		sb.append(getOriginalWatsonAddressId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>provinceWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getProvinceWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subDistrictWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getSubDistrictWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>typeWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getTypeWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>watsonIncidentId</column-name><column-value><![CDATA[");
		sb.append(getWatsonIncidentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>postalCode</column-name><column-value><![CDATA[");
		sb.append(getPostalCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>region</column-name><column-value><![CDATA[");
		sb.append(getRegion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>street</column-name><column-value><![CDATA[");
		sb.append(getStreet());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>number</column-name><column-value><![CDATA[");
		sb.append(getNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>building</column-name><column-value><![CDATA[");
		sb.append(getBuilding());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>floor</column-name><column-value><![CDATA[");
		sb.append(getFloor());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>room</column-name><column-value><![CDATA[");
		sb.append(getRoom());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imagePayload</column-name><column-value><![CDATA[");
		sb.append(getImagePayload());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastSeenDate</column-name><column-value><![CDATA[");
		sb.append(getLastSeenDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>latitude</column-name><column-value><![CDATA[");
		sb.append(getLatitude());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>longitude</column-name><column-value><![CDATA[");
		sb.append(getLongitude());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _watsonAddressId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _countryId;
	private long _districtWatsonListTypeId;
	private long _originalWatsonAddressId;
	private long _provinceWatsonListTypeId;
	private long _subDistrictWatsonListTypeId;
	private long _typeWatsonListTypeId;
	private long _watsonIncidentId;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _postalCode;
	private String _region;
	private String _street;
	private String _number;
	private String _building;
	private String _buildingCurrentLanguageId;
	private String _floor;
	private String _room;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _imagePayload;
	private Date _lastSeenDate;
	private double _latitude;
	private double _longitude;
	private int _status;
	private BaseModel<?> _watsonAddressRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}