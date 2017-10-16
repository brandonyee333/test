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
 * This class is a wrapper for {@link WatsonPerson}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonPerson
 * @generated
 */
@ProviderType
public class WatsonPersonWrapper implements WatsonPerson,
	ModelWrapper<WatsonPerson> {
	public WatsonPersonWrapper(WatsonPerson watsonPerson) {
		_watsonPerson = watsonPerson;
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
		attributes.put("dateRescued", getDateRescued());
		attributes.put("startAge", getStartAge());
		attributes.put("endAge", getEndAge());
		attributes.put("occupation", getOccupation());
		attributes.put("height", getHeight());
		attributes.put("weight", getWeight());
		attributes.put("accepted", getAccepted());
		attributes.put("rescued", getRescued());
		attributes.put("status", getStatus());

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

		Date dateRescued = (Date)attributes.get("dateRescued");

		if (dateRescued != null) {
			setDateRescued(dateRescued);
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

		Boolean rescued = (Boolean)attributes.get("rescued");

		if (rescued != null) {
			setRescued(rescued);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WatsonPersonWrapper((WatsonPerson)_watsonPerson.clone());
	}

	@Override
	public int compareTo(WatsonPerson watsonPerson) {
		return _watsonPerson.compareTo(watsonPerson);
	}

	/**
	* Returns the accepted of this watson person.
	*
	* @return the accepted of this watson person
	*/
	@Override
	public boolean getAccepted() {
		return _watsonPerson.getAccepted();
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _watsonPerson.getAvailableLanguageIds();
	}

	/**
	* Returns the birth country ID of this watson person.
	*
	* @return the birth country ID of this watson person
	*/
	@Override
	public long getBirthCountryId() {
		return _watsonPerson.getBirthCountryId();
	}

	/**
	* Returns the birth date of this watson person.
	*
	* @return the birth date of this watson person
	*/
	@Override
	public Date getBirthDate() {
		return _watsonPerson.getBirthDate();
	}

	/**
	* Returns the citizenship watson list type ID of this watson person.
	*
	* @return the citizenship watson list type ID of this watson person
	*/
	@Override
	public long getCitizenshipWatsonListTypeId() {
		return _watsonPerson.getCitizenshipWatsonListTypeId();
	}

	/**
	* Returns the company ID of this watson person.
	*
	* @return the company ID of this watson person
	*/
	@Override
	public long getCompanyId() {
		return _watsonPerson.getCompanyId();
	}

	/**
	* Returns the country watson list type ID of this watson person.
	*
	* @return the country watson list type ID of this watson person
	*/
	@Override
	public long getCountryWatsonListTypeId() {
		return _watsonPerson.getCountryWatsonListTypeId();
	}

	/**
	* Returns the create date of this watson person.
	*
	* @return the create date of this watson person
	*/
	@Override
	public Date getCreateDate() {
		return _watsonPerson.getCreateDate();
	}

	/**
	* Returns the date accepted of this watson person.
	*
	* @return the date accepted of this watson person
	*/
	@Override
	public Date getDateAccepted() {
		return _watsonPerson.getDateAccepted();
	}

	/**
	* Returns the date rescued of this watson person.
	*
	* @return the date rescued of this watson person
	*/
	@Override
	public Date getDateRescued() {
		return _watsonPerson.getDateRescued();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _watsonPerson.getDefaultLanguageId();
	}

	/**
	* Returns the description of this watson person.
	*
	* @return the description of this watson person
	*/
	@Override
	public java.lang.String getDescription() {
		return _watsonPerson.getDescription();
	}

	/**
	* Returns the localized description of this watson person in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this watson person
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _watsonPerson.getDescription(locale);
	}

	/**
	* Returns the localized description of this watson person in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson person. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _watsonPerson.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this watson person in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this watson person
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _watsonPerson.getDescription(languageId);
	}

	/**
	* Returns the localized description of this watson person in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this watson person
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _watsonPerson.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _watsonPerson.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _watsonPerson.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this watson person.
	*
	* @return the locales and localized descriptions of this watson person
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _watsonPerson.getDescriptionMap();
	}

	/**
	* Returns the end age of this watson person.
	*
	* @return the end age of this watson person
	*/
	@Override
	public java.lang.String getEndAge() {
		return _watsonPerson.getEndAge();
	}

	/**
	* Returns the ethnicity watson list type ID of this watson person.
	*
	* @return the ethnicity watson list type ID of this watson person
	*/
	@Override
	public long getEthnicityWatsonListTypeId() {
		return _watsonPerson.getEthnicityWatsonListTypeId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonPerson.getExpandoBridge();
	}

	/**
	* Returns the eyes watson list type ID of this watson person.
	*
	* @return the eyes watson list type ID of this watson person
	*/
	@Override
	public long getEyesWatsonListTypeId() {
		return _watsonPerson.getEyesWatsonListTypeId();
	}

	/**
	* Returns the hair watson list type ID of this watson person.
	*
	* @return the hair watson list type ID of this watson person
	*/
	@Override
	public long getHairWatsonListTypeId() {
		return _watsonPerson.getHairWatsonListTypeId();
	}

	/**
	* Returns the height of this watson person.
	*
	* @return the height of this watson person
	*/
	@Override
	public java.lang.String getHeight() {
		return _watsonPerson.getHeight();
	}

	/**
	* Returns the image payload of this watson person.
	*
	* @return the image payload of this watson person
	*/
	@Override
	public java.lang.String getImagePayload() {
		return _watsonPerson.getImagePayload();
	}

	/**
	* Returns the modified date of this watson person.
	*
	* @return the modified date of this watson person
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonPerson.getModifiedDate();
	}

	/**
	* Returns the occupation of this watson person.
	*
	* @return the occupation of this watson person
	*/
	@Override
	public java.lang.String getOccupation() {
		return _watsonPerson.getOccupation();
	}

	/**
	* Returns the localized occupation of this watson person in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized occupation of this watson person
	*/
	@Override
	public java.lang.String getOccupation(java.util.Locale locale) {
		return _watsonPerson.getOccupation(locale);
	}

	/**
	* Returns the localized occupation of this watson person in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized occupation of this watson person. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getOccupation(java.util.Locale locale,
		boolean useDefault) {
		return _watsonPerson.getOccupation(locale, useDefault);
	}

	/**
	* Returns the localized occupation of this watson person in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized occupation of this watson person
	*/
	@Override
	public java.lang.String getOccupation(java.lang.String languageId) {
		return _watsonPerson.getOccupation(languageId);
	}

	/**
	* Returns the localized occupation of this watson person in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized occupation of this watson person
	*/
	@Override
	public java.lang.String getOccupation(java.lang.String languageId,
		boolean useDefault) {
		return _watsonPerson.getOccupation(languageId, useDefault);
	}

	@Override
	public java.lang.String getOccupationCurrentLanguageId() {
		return _watsonPerson.getOccupationCurrentLanguageId();
	}

	@Override
	public java.lang.String getOccupationCurrentValue() {
		return _watsonPerson.getOccupationCurrentValue();
	}

	/**
	* Returns a map of the locales and localized occupations of this watson person.
	*
	* @return the locales and localized occupations of this watson person
	*/
	@Override
	public Map<java.util.Locale, java.lang.String> getOccupationMap() {
		return _watsonPerson.getOccupationMap();
	}

	/**
	* Returns the original watson person ID of this watson person.
	*
	* @return the original watson person ID of this watson person
	*/
	@Override
	public long getOriginalWatsonPersonId() {
		return _watsonPerson.getOriginalWatsonPersonId();
	}

	/**
	* Returns the primary key of this watson person.
	*
	* @return the primary key of this watson person
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonPerson.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonPerson.getPrimaryKeyObj();
	}

	/**
	* Returns the rescued of this watson person.
	*
	* @return the rescued of this watson person
	*/
	@Override
	public boolean getRescued() {
		return _watsonPerson.getRescued();
	}

	/**
	* Returns the sex watson list type ID of this watson person.
	*
	* @return the sex watson list type ID of this watson person
	*/
	@Override
	public long getSexWatsonListTypeId() {
		return _watsonPerson.getSexWatsonListTypeId();
	}

	/**
	* Returns the start age of this watson person.
	*
	* @return the start age of this watson person
	*/
	@Override
	public java.lang.String getStartAge() {
		return _watsonPerson.getStartAge();
	}

	/**
	* Returns the status of this watson person.
	*
	* @return the status of this watson person
	*/
	@Override
	public int getStatus() {
		return _watsonPerson.getStatus();
	}

	/**
	* Returns the type watson list type ID of this watson person.
	*
	* @return the type watson list type ID of this watson person
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonPerson.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson person.
	*
	* @return the user ID of this watson person
	*/
	@Override
	public long getUserId() {
		return _watsonPerson.getUserId();
	}

	/**
	* Returns the user name of this watson person.
	*
	* @return the user name of this watson person
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonPerson.getUserName();
	}

	/**
	* Returns the user uuid of this watson person.
	*
	* @return the user uuid of this watson person
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonPerson.getUserUuid();
	}

	/**
	* Returns the watson incident ID of this watson person.
	*
	* @return the watson incident ID of this watson person
	*/
	@Override
	public long getWatsonIncidentId() {
		return _watsonPerson.getWatsonIncidentId();
	}

	/**
	* Returns the watson person ID of this watson person.
	*
	* @return the watson person ID of this watson person
	*/
	@Override
	public long getWatsonPersonId() {
		return _watsonPerson.getWatsonPersonId();
	}

	/**
	* Returns the weight of this watson person.
	*
	* @return the weight of this watson person
	*/
	@Override
	public java.lang.String getWeight() {
		return _watsonPerson.getWeight();
	}

	@Override
	public int hashCode() {
		return _watsonPerson.hashCode();
	}

	/**
	* Returns <code>true</code> if this watson person is accepted.
	*
	* @return <code>true</code> if this watson person is accepted; <code>false</code> otherwise
	*/
	@Override
	public boolean isAccepted() {
		return _watsonPerson.isAccepted();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonPerson.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonPerson.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonPerson.isNew();
	}

	/**
	* Returns <code>true</code> if this watson person is rescued.
	*
	* @return <code>true</code> if this watson person is rescued; <code>false</code> otherwise
	*/
	@Override
	public boolean isRescued() {
		return _watsonPerson.isRescued();
	}

	@Override
	public void persist() {
		_watsonPerson.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonPerson.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonPerson.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	* Sets whether this watson person is accepted.
	*
	* @param accepted the accepted of this watson person
	*/
	@Override
	public void setAccepted(boolean accepted) {
		_watsonPerson.setAccepted(accepted);
	}

	/**
	* Sets the birth country ID of this watson person.
	*
	* @param birthCountryId the birth country ID of this watson person
	*/
	@Override
	public void setBirthCountryId(long birthCountryId) {
		_watsonPerson.setBirthCountryId(birthCountryId);
	}

	/**
	* Sets the birth date of this watson person.
	*
	* @param birthDate the birth date of this watson person
	*/
	@Override
	public void setBirthDate(Date birthDate) {
		_watsonPerson.setBirthDate(birthDate);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonPerson.setCachedModel(cachedModel);
	}

	/**
	* Sets the citizenship watson list type ID of this watson person.
	*
	* @param citizenshipWatsonListTypeId the citizenship watson list type ID of this watson person
	*/
	@Override
	public void setCitizenshipWatsonListTypeId(long citizenshipWatsonListTypeId) {
		_watsonPerson.setCitizenshipWatsonListTypeId(citizenshipWatsonListTypeId);
	}

	/**
	* Sets the company ID of this watson person.
	*
	* @param companyId the company ID of this watson person
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonPerson.setCompanyId(companyId);
	}

	/**
	* Sets the country watson list type ID of this watson person.
	*
	* @param countryWatsonListTypeId the country watson list type ID of this watson person
	*/
	@Override
	public void setCountryWatsonListTypeId(long countryWatsonListTypeId) {
		_watsonPerson.setCountryWatsonListTypeId(countryWatsonListTypeId);
	}

	/**
	* Sets the create date of this watson person.
	*
	* @param createDate the create date of this watson person
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonPerson.setCreateDate(createDate);
	}

	/**
	* Sets the date accepted of this watson person.
	*
	* @param dateAccepted the date accepted of this watson person
	*/
	@Override
	public void setDateAccepted(Date dateAccepted) {
		_watsonPerson.setDateAccepted(dateAccepted);
	}

	/**
	* Sets the date rescued of this watson person.
	*
	* @param dateRescued the date rescued of this watson person
	*/
	@Override
	public void setDateRescued(Date dateRescued) {
		_watsonPerson.setDateRescued(dateRescued);
	}

	/**
	* Sets the description of this watson person.
	*
	* @param description the description of this watson person
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_watsonPerson.setDescription(description);
	}

	/**
	* Sets the localized description of this watson person in the language.
	*
	* @param description the localized description of this watson person
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_watsonPerson.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this watson person in the language, and sets the default locale.
	*
	* @param description the localized description of this watson person
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonPerson.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_watsonPerson.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this watson person from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this watson person
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap) {
		_watsonPerson.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this watson person from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this watson person
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_watsonPerson.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Sets the end age of this watson person.
	*
	* @param endAge the end age of this watson person
	*/
	@Override
	public void setEndAge(java.lang.String endAge) {
		_watsonPerson.setEndAge(endAge);
	}

	/**
	* Sets the ethnicity watson list type ID of this watson person.
	*
	* @param ethnicityWatsonListTypeId the ethnicity watson list type ID of this watson person
	*/
	@Override
	public void setEthnicityWatsonListTypeId(long ethnicityWatsonListTypeId) {
		_watsonPerson.setEthnicityWatsonListTypeId(ethnicityWatsonListTypeId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonPerson.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonPerson.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonPerson.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the eyes watson list type ID of this watson person.
	*
	* @param eyesWatsonListTypeId the eyes watson list type ID of this watson person
	*/
	@Override
	public void setEyesWatsonListTypeId(long eyesWatsonListTypeId) {
		_watsonPerson.setEyesWatsonListTypeId(eyesWatsonListTypeId);
	}

	/**
	* Sets the hair watson list type ID of this watson person.
	*
	* @param hairWatsonListTypeId the hair watson list type ID of this watson person
	*/
	@Override
	public void setHairWatsonListTypeId(long hairWatsonListTypeId) {
		_watsonPerson.setHairWatsonListTypeId(hairWatsonListTypeId);
	}

	/**
	* Sets the height of this watson person.
	*
	* @param height the height of this watson person
	*/
	@Override
	public void setHeight(java.lang.String height) {
		_watsonPerson.setHeight(height);
	}

	/**
	* Sets the image payload of this watson person.
	*
	* @param imagePayload the image payload of this watson person
	*/
	@Override
	public void setImagePayload(java.lang.String imagePayload) {
		_watsonPerson.setImagePayload(imagePayload);
	}

	/**
	* Sets the modified date of this watson person.
	*
	* @param modifiedDate the modified date of this watson person
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonPerson.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonPerson.setNew(n);
	}

	/**
	* Sets the occupation of this watson person.
	*
	* @param occupation the occupation of this watson person
	*/
	@Override
	public void setOccupation(java.lang.String occupation) {
		_watsonPerson.setOccupation(occupation);
	}

	/**
	* Sets the localized occupation of this watson person in the language.
	*
	* @param occupation the localized occupation of this watson person
	* @param locale the locale of the language
	*/
	@Override
	public void setOccupation(java.lang.String occupation,
		java.util.Locale locale) {
		_watsonPerson.setOccupation(occupation, locale);
	}

	/**
	* Sets the localized occupation of this watson person in the language, and sets the default locale.
	*
	* @param occupation the localized occupation of this watson person
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setOccupation(java.lang.String occupation,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_watsonPerson.setOccupation(occupation, locale, defaultLocale);
	}

	@Override
	public void setOccupationCurrentLanguageId(java.lang.String languageId) {
		_watsonPerson.setOccupationCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized occupations of this watson person from the map of locales and localized occupations.
	*
	* @param occupationMap the locales and localized occupations of this watson person
	*/
	@Override
	public void setOccupationMap(
		Map<java.util.Locale, java.lang.String> occupationMap) {
		_watsonPerson.setOccupationMap(occupationMap);
	}

	/**
	* Sets the localized occupations of this watson person from the map of locales and localized occupations, and sets the default locale.
	*
	* @param occupationMap the locales and localized occupations of this watson person
	* @param defaultLocale the default locale
	*/
	@Override
	public void setOccupationMap(
		Map<java.util.Locale, java.lang.String> occupationMap,
		java.util.Locale defaultLocale) {
		_watsonPerson.setOccupationMap(occupationMap, defaultLocale);
	}

	/**
	* Sets the original watson person ID of this watson person.
	*
	* @param originalWatsonPersonId the original watson person ID of this watson person
	*/
	@Override
	public void setOriginalWatsonPersonId(long originalWatsonPersonId) {
		_watsonPerson.setOriginalWatsonPersonId(originalWatsonPersonId);
	}

	/**
	* Sets the primary key of this watson person.
	*
	* @param primaryKey the primary key of this watson person
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonPerson.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonPerson.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this watson person is rescued.
	*
	* @param rescued the rescued of this watson person
	*/
	@Override
	public void setRescued(boolean rescued) {
		_watsonPerson.setRescued(rescued);
	}

	/**
	* Sets the sex watson list type ID of this watson person.
	*
	* @param sexWatsonListTypeId the sex watson list type ID of this watson person
	*/
	@Override
	public void setSexWatsonListTypeId(long sexWatsonListTypeId) {
		_watsonPerson.setSexWatsonListTypeId(sexWatsonListTypeId);
	}

	/**
	* Sets the start age of this watson person.
	*
	* @param startAge the start age of this watson person
	*/
	@Override
	public void setStartAge(java.lang.String startAge) {
		_watsonPerson.setStartAge(startAge);
	}

	/**
	* Sets the status of this watson person.
	*
	* @param status the status of this watson person
	*/
	@Override
	public void setStatus(int status) {
		_watsonPerson.setStatus(status);
	}

	/**
	* Sets the type watson list type ID of this watson person.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson person
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonPerson.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson person.
	*
	* @param userId the user ID of this watson person
	*/
	@Override
	public void setUserId(long userId) {
		_watsonPerson.setUserId(userId);
	}

	/**
	* Sets the user name of this watson person.
	*
	* @param userName the user name of this watson person
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonPerson.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson person.
	*
	* @param userUuid the user uuid of this watson person
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonPerson.setUserUuid(userUuid);
	}

	/**
	* Sets the watson incident ID of this watson person.
	*
	* @param watsonIncidentId the watson incident ID of this watson person
	*/
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonPerson.setWatsonIncidentId(watsonIncidentId);
	}

	/**
	* Sets the watson person ID of this watson person.
	*
	* @param watsonPersonId the watson person ID of this watson person
	*/
	@Override
	public void setWatsonPersonId(long watsonPersonId) {
		_watsonPerson.setWatsonPersonId(watsonPersonId);
	}

	/**
	* Sets the weight of this watson person.
	*
	* @param weight the weight of this watson person
	*/
	@Override
	public void setWeight(java.lang.String weight) {
		_watsonPerson.setWeight(weight);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonPerson> toCacheModel() {
		return _watsonPerson.toCacheModel();
	}

	@Override
	public WatsonPerson toEscapedModel() {
		return new WatsonPersonWrapper(_watsonPerson.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonPerson.toString();
	}

	@Override
	public WatsonPerson toUnescapedModel() {
		return new WatsonPersonWrapper(_watsonPerson.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonPerson.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonPersonWrapper)) {
			return false;
		}

		WatsonPersonWrapper watsonPersonWrapper = (WatsonPersonWrapper)obj;

		if (Objects.equals(_watsonPerson, watsonPersonWrapper._watsonPerson)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonPerson getWrappedModel() {
		return _watsonPerson;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonPerson.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonPerson.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonPerson.resetOriginalValues();
	}

	private final WatsonPerson _watsonPerson;
}