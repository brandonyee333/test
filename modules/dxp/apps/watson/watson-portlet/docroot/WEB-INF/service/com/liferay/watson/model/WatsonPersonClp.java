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
import com.liferay.watson.service.WatsonPersonLocalServiceUtil;

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
public class WatsonPersonClp extends BaseModelImpl<WatsonPerson>
	implements WatsonPerson {
	public WatsonPersonClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonPerson.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonPerson.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _watsonPersonId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWatsonPersonId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonPersonId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonPersonId", getWatsonPersonId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("birthCountryId", getBirthCountryId());
		attributes.put("citizenshipWatsonListTypeId",
			getCitizenshipWatsonListTypeId());
		attributes.put("countryWatsonListTypeId", getCountryWatsonListTypeId());
		attributes.put("ethnicityWatsonListTypeId",
			getEthnicityWatsonListTypeId());
		attributes.put("eyesWatsonListTypeId", getEyesWatsonListTypeId());
		attributes.put("hairWatsonListTypeId", getHairWatsonListTypeId());
		attributes.put("originalWatsonPersonId", getOriginalWatsonPersonId());
		attributes.put("sexWatsonListTypeId", getSexWatsonListTypeId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("description", getDescription());
		attributes.put("imagePayload", getImagePayload());
		attributes.put("birthDate", getBirthDate());
		attributes.put("dateAccepted", getDateAccepted());
		attributes.put("startAge", getStartAge());
		attributes.put("endAge", getEndAge());
		attributes.put("occupation", getOccupation());
		attributes.put("height", getHeight());
		attributes.put("weight", getWeight());
		attributes.put("accepted", getAccepted());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonPersonId = (Long)attributes.get("watsonPersonId");

		if (watsonPersonId != null) {
			setWatsonPersonId(watsonPersonId);
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

		Long birthCountryId = (Long)attributes.get("birthCountryId");

		if (birthCountryId != null) {
			setBirthCountryId(birthCountryId);
		}

		Long citizenshipWatsonListTypeId = (Long)attributes.get(
				"citizenshipWatsonListTypeId");

		if (citizenshipWatsonListTypeId != null) {
			setCitizenshipWatsonListTypeId(citizenshipWatsonListTypeId);
		}

		Long countryWatsonListTypeId = (Long)attributes.get(
				"countryWatsonListTypeId");

		if (countryWatsonListTypeId != null) {
			setCountryWatsonListTypeId(countryWatsonListTypeId);
		}

		Long ethnicityWatsonListTypeId = (Long)attributes.get(
				"ethnicityWatsonListTypeId");

		if (ethnicityWatsonListTypeId != null) {
			setEthnicityWatsonListTypeId(ethnicityWatsonListTypeId);
		}

		Long eyesWatsonListTypeId = (Long)attributes.get("eyesWatsonListTypeId");

		if (eyesWatsonListTypeId != null) {
			setEyesWatsonListTypeId(eyesWatsonListTypeId);
		}

		Long hairWatsonListTypeId = (Long)attributes.get("hairWatsonListTypeId");

		if (hairWatsonListTypeId != null) {
			setHairWatsonListTypeId(hairWatsonListTypeId);
		}

		Long originalWatsonPersonId = (Long)attributes.get(
				"originalWatsonPersonId");

		if (originalWatsonPersonId != null) {
			setOriginalWatsonPersonId(originalWatsonPersonId);
		}

		Long sexWatsonListTypeId = (Long)attributes.get("sexWatsonListTypeId");

		if (sexWatsonListTypeId != null) {
			setSexWatsonListTypeId(sexWatsonListTypeId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}

		Date birthDate = (Date)attributes.get("birthDate");

		if (birthDate != null) {
			setBirthDate(birthDate);
		}

		Date dateAccepted = (Date)attributes.get("dateAccepted");

		if (dateAccepted != null) {
			setDateAccepted(dateAccepted);
		}

		String startAge = (String)attributes.get("startAge");

		if (startAge != null) {
			setStartAge(startAge);
		}

		String endAge = (String)attributes.get("endAge");

		if (endAge != null) {
			setEndAge(endAge);
		}

		String occupation = (String)attributes.get("occupation");

		if (occupation != null) {
			setOccupation(occupation);
		}

		String height = (String)attributes.get("height");

		if (height != null) {
			setHeight(height);
		}

		String weight = (String)attributes.get("weight");

		if (weight != null) {
			setWeight(weight);
		}

		Boolean accepted = (Boolean)attributes.get("accepted");

		if (accepted != null) {
			setAccepted(accepted);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getWatsonPersonId() {
		return _watsonPersonId;
	}

	@Override
	public void setWatsonPersonId(long watsonPersonId) {
		_watsonPersonId = watsonPersonId;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonPersonId", long.class);

				method.invoke(_watsonPersonRemoteModel, watsonPersonId);
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

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_watsonPersonRemoteModel, companyId);
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

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_watsonPersonRemoteModel, userId);
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

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_watsonPersonRemoteModel, userName);
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

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_watsonPersonRemoteModel, createDate);
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

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_watsonPersonRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getBirthCountryId() {
		return _birthCountryId;
	}

	@Override
	public void setBirthCountryId(long birthCountryId) {
		_birthCountryId = birthCountryId;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setBirthCountryId", long.class);

				method.invoke(_watsonPersonRemoteModel, birthCountryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCitizenshipWatsonListTypeId() {
		return _citizenshipWatsonListTypeId;
	}

	@Override
	public void setCitizenshipWatsonListTypeId(long citizenshipWatsonListTypeId) {
		_citizenshipWatsonListTypeId = citizenshipWatsonListTypeId;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setCitizenshipWatsonListTypeId",
						long.class);

				method.invoke(_watsonPersonRemoteModel,
					citizenshipWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCountryWatsonListTypeId() {
		return _countryWatsonListTypeId;
	}

	@Override
	public void setCountryWatsonListTypeId(long countryWatsonListTypeId) {
		_countryWatsonListTypeId = countryWatsonListTypeId;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setCountryWatsonListTypeId",
						long.class);

				method.invoke(_watsonPersonRemoteModel, countryWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getEthnicityWatsonListTypeId() {
		return _ethnicityWatsonListTypeId;
	}

	@Override
	public void setEthnicityWatsonListTypeId(long ethnicityWatsonListTypeId) {
		_ethnicityWatsonListTypeId = ethnicityWatsonListTypeId;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setEthnicityWatsonListTypeId",
						long.class);

				method.invoke(_watsonPersonRemoteModel,
					ethnicityWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getEyesWatsonListTypeId() {
		return _eyesWatsonListTypeId;
	}

	@Override
	public void setEyesWatsonListTypeId(long eyesWatsonListTypeId) {
		_eyesWatsonListTypeId = eyesWatsonListTypeId;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setEyesWatsonListTypeId",
						long.class);

				method.invoke(_watsonPersonRemoteModel, eyesWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getHairWatsonListTypeId() {
		return _hairWatsonListTypeId;
	}

	@Override
	public void setHairWatsonListTypeId(long hairWatsonListTypeId) {
		_hairWatsonListTypeId = hairWatsonListTypeId;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setHairWatsonListTypeId",
						long.class);

				method.invoke(_watsonPersonRemoteModel, hairWatsonListTypeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getOriginalWatsonPersonId() {
		return _originalWatsonPersonId;
	}

	@Override
	public void setOriginalWatsonPersonId(long originalWatsonPersonId) {
		_originalWatsonPersonId = originalWatsonPersonId;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setOriginalWatsonPersonId",
						long.class);

				method.invoke(_watsonPersonRemoteModel, originalWatsonPersonId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSexWatsonListTypeId() {
		return _sexWatsonListTypeId;
	}

	@Override
	public void setSexWatsonListTypeId(long sexWatsonListTypeId) {
		_sexWatsonListTypeId = sexWatsonListTypeId;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setSexWatsonListTypeId",
						long.class);

				method.invoke(_watsonPersonRemoteModel, sexWatsonListTypeId);
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

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setTypeWatsonListTypeId",
						long.class);

				method.invoke(_watsonPersonRemoteModel, typeWatsonListTypeId);
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

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setWatsonIncidentId",
						long.class);

				method.invoke(_watsonPersonRemoteModel, watsonIncidentId);
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

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_watsonPersonRemoteModel, description);
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

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setImagePayload", String.class);

				method.invoke(_watsonPersonRemoteModel, imagePayload);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getBirthDate() {
		return _birthDate;
	}

	@Override
	public void setBirthDate(Date birthDate) {
		_birthDate = birthDate;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setBirthDate", Date.class);

				method.invoke(_watsonPersonRemoteModel, birthDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getDateAccepted() {
		return _dateAccepted;
	}

	@Override
	public void setDateAccepted(Date dateAccepted) {
		_dateAccepted = dateAccepted;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setDateAccepted", Date.class);

				method.invoke(_watsonPersonRemoteModel, dateAccepted);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStartAge() {
		return _startAge;
	}

	@Override
	public void setStartAge(String startAge) {
		_startAge = startAge;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setStartAge", String.class);

				method.invoke(_watsonPersonRemoteModel, startAge);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEndAge() {
		return _endAge;
	}

	@Override
	public void setEndAge(String endAge) {
		_endAge = endAge;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setEndAge", String.class);

				method.invoke(_watsonPersonRemoteModel, endAge);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getOccupation() {
		return _occupation;
	}

	@Override
	public String getOccupation(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getOccupation(languageId);
	}

	@Override
	public String getOccupation(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getOccupation(languageId, useDefault);
	}

	@Override
	public String getOccupation(String languageId) {
		return LocalizationUtil.getLocalization(getOccupation(), languageId);
	}

	@Override
	public String getOccupation(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getOccupation(), languageId,
			useDefault);
	}

	@Override
	public String getOccupationCurrentLanguageId() {
		return _occupationCurrentLanguageId;
	}

	@Override
	public String getOccupationCurrentValue() {
		Locale locale = getLocale(_occupationCurrentLanguageId);

		return getOccupation(locale);
	}

	@Override
	public Map<Locale, String> getOccupationMap() {
		return LocalizationUtil.getLocalizationMap(getOccupation());
	}

	@Override
	public void setOccupation(String occupation) {
		_occupation = occupation;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setOccupation", String.class);

				method.invoke(_watsonPersonRemoteModel, occupation);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setOccupation(String occupation, Locale locale) {
		setOccupation(occupation, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setOccupation(String occupation, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(occupation)) {
			setOccupation(LocalizationUtil.updateLocalization(getOccupation(),
					"Occupation", occupation, languageId, defaultLanguageId));
		}
		else {
			setOccupation(LocalizationUtil.removeLocalization(getOccupation(),
					"Occupation", languageId));
		}
	}

	@Override
	public void setOccupationCurrentLanguageId(String languageId) {
		_occupationCurrentLanguageId = languageId;
	}

	@Override
	public void setOccupationMap(Map<Locale, String> occupationMap) {
		setOccupationMap(occupationMap, LocaleUtil.getDefault());
	}

	@Override
	public void setOccupationMap(Map<Locale, String> occupationMap,
		Locale defaultLocale) {
		if (occupationMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setOccupation(LocalizationUtil.updateLocalization(occupationMap,
					getOccupation(), "Occupation",
					LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public String getHeight() {
		return _height;
	}

	@Override
	public void setHeight(String height) {
		_height = height;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setHeight", String.class);

				method.invoke(_watsonPersonRemoteModel, height);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getWeight() {
		return _weight;
	}

	@Override
	public void setWeight(String weight) {
		_weight = weight;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setWeight", String.class);

				method.invoke(_watsonPersonRemoteModel, weight);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAccepted() {
		return _accepted;
	}

	@Override
	public boolean isAccepted() {
		return _accepted;
	}

	@Override
	public void setAccepted(boolean accepted) {
		_accepted = accepted;

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setAccepted", boolean.class);

				method.invoke(_watsonPersonRemoteModel, accepted);
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

		if (_watsonPersonRemoteModel != null) {
			try {
				Class<?> clazz = _watsonPersonRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_watsonPersonRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getWatsonPersonRemoteModel() {
		return _watsonPersonRemoteModel;
	}

	public void setWatsonPersonRemoteModel(BaseModel<?> watsonPersonRemoteModel) {
		_watsonPersonRemoteModel = watsonPersonRemoteModel;
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

		Class<?> remoteModelClass = _watsonPersonRemoteModel.getClass();

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

		Object returnValue = method.invoke(_watsonPersonRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			WatsonPersonLocalServiceUtil.addWatsonPerson(this);
		}
		else {
			WatsonPersonLocalServiceUtil.updateWatsonPerson(this);
		}
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> occupationMap = getOccupationMap();

		for (Map.Entry<Locale, String> entry : occupationMap.entrySet()) {
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
		String xml = getDescription();

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

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(getDescription(defaultLocale), defaultLocale,
				defaultLocale);
		}

		String occupation = getOccupation(defaultLocale);

		if (Validator.isNull(occupation)) {
			setOccupation(getOccupation(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setOccupation(getOccupation(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public WatsonPerson toEscapedModel() {
		return (WatsonPerson)ProxyUtil.newProxyInstance(WatsonPerson.class.getClassLoader(),
			new Class[] { WatsonPerson.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WatsonPersonClp clone = new WatsonPersonClp();

		clone.setWatsonPersonId(getWatsonPersonId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setBirthCountryId(getBirthCountryId());
		clone.setCitizenshipWatsonListTypeId(getCitizenshipWatsonListTypeId());
		clone.setCountryWatsonListTypeId(getCountryWatsonListTypeId());
		clone.setEthnicityWatsonListTypeId(getEthnicityWatsonListTypeId());
		clone.setEyesWatsonListTypeId(getEyesWatsonListTypeId());
		clone.setHairWatsonListTypeId(getHairWatsonListTypeId());
		clone.setOriginalWatsonPersonId(getOriginalWatsonPersonId());
		clone.setSexWatsonListTypeId(getSexWatsonListTypeId());
		clone.setTypeWatsonListTypeId(getTypeWatsonListTypeId());
		clone.setWatsonIncidentId(getWatsonIncidentId());
		clone.setDescription(getDescription());
		clone.setImagePayload(getImagePayload());
		clone.setBirthDate(getBirthDate());
		clone.setDateAccepted(getDateAccepted());
		clone.setStartAge(getStartAge());
		clone.setEndAge(getEndAge());
		clone.setOccupation(getOccupation());
		clone.setHeight(getHeight());
		clone.setWeight(getWeight());
		clone.setAccepted(getAccepted());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(WatsonPerson watsonPerson) {
		long primaryKey = watsonPerson.getPrimaryKey();

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

		if (!(obj instanceof WatsonPersonClp)) {
			return false;
		}

		WatsonPersonClp watsonPerson = (WatsonPersonClp)obj;

		long primaryKey = watsonPerson.getPrimaryKey();

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

		sb.append("{watsonPersonId=");
		sb.append(getWatsonPersonId());
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
		sb.append(", birthCountryId=");
		sb.append(getBirthCountryId());
		sb.append(", citizenshipWatsonListTypeId=");
		sb.append(getCitizenshipWatsonListTypeId());
		sb.append(", countryWatsonListTypeId=");
		sb.append(getCountryWatsonListTypeId());
		sb.append(", ethnicityWatsonListTypeId=");
		sb.append(getEthnicityWatsonListTypeId());
		sb.append(", eyesWatsonListTypeId=");
		sb.append(getEyesWatsonListTypeId());
		sb.append(", hairWatsonListTypeId=");
		sb.append(getHairWatsonListTypeId());
		sb.append(", originalWatsonPersonId=");
		sb.append(getOriginalWatsonPersonId());
		sb.append(", sexWatsonListTypeId=");
		sb.append(getSexWatsonListTypeId());
		sb.append(", typeWatsonListTypeId=");
		sb.append(getTypeWatsonListTypeId());
		sb.append(", watsonIncidentId=");
		sb.append(getWatsonIncidentId());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", imagePayload=");
		sb.append(getImagePayload());
		sb.append(", birthDate=");
		sb.append(getBirthDate());
		sb.append(", dateAccepted=");
		sb.append(getDateAccepted());
		sb.append(", startAge=");
		sb.append(getStartAge());
		sb.append(", endAge=");
		sb.append(getEndAge());
		sb.append(", occupation=");
		sb.append(getOccupation());
		sb.append(", height=");
		sb.append(getHeight());
		sb.append(", weight=");
		sb.append(getWeight());
		sb.append(", accepted=");
		sb.append(getAccepted());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(85);

		sb.append("<model><model-name>");
		sb.append("com.liferay.watson.model.WatsonPerson");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>watsonPersonId</column-name><column-value><![CDATA[");
		sb.append(getWatsonPersonId());
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
			"<column><column-name>birthCountryId</column-name><column-value><![CDATA[");
		sb.append(getBirthCountryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>citizenshipWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getCitizenshipWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>countryWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getCountryWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ethnicityWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getEthnicityWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>eyesWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getEyesWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hairWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getHairWatsonListTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>originalWatsonPersonId</column-name><column-value><![CDATA[");
		sb.append(getOriginalWatsonPersonId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sexWatsonListTypeId</column-name><column-value><![CDATA[");
		sb.append(getSexWatsonListTypeId());
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
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imagePayload</column-name><column-value><![CDATA[");
		sb.append(getImagePayload());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>birthDate</column-name><column-value><![CDATA[");
		sb.append(getBirthDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dateAccepted</column-name><column-value><![CDATA[");
		sb.append(getDateAccepted());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startAge</column-name><column-value><![CDATA[");
		sb.append(getStartAge());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endAge</column-name><column-value><![CDATA[");
		sb.append(getEndAge());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>occupation</column-name><column-value><![CDATA[");
		sb.append(getOccupation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>height</column-name><column-value><![CDATA[");
		sb.append(getHeight());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>weight</column-name><column-value><![CDATA[");
		sb.append(getWeight());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accepted</column-name><column-value><![CDATA[");
		sb.append(getAccepted());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _watsonPersonId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _birthCountryId;
	private long _citizenshipWatsonListTypeId;
	private long _countryWatsonListTypeId;
	private long _ethnicityWatsonListTypeId;
	private long _eyesWatsonListTypeId;
	private long _hairWatsonListTypeId;
	private long _originalWatsonPersonId;
	private long _sexWatsonListTypeId;
	private long _typeWatsonListTypeId;
	private long _watsonIncidentId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _imagePayload;
	private Date _birthDate;
	private Date _dateAccepted;
	private String _startAge;
	private String _endAge;
	private String _occupation;
	private String _occupationCurrentLanguageId;
	private String _height;
	private String _weight;
	private boolean _accepted;
	private int _status;
	private BaseModel<?> _watsonPersonRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}