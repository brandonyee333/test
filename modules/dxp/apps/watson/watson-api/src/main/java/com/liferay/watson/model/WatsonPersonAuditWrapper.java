/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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
 * This class is a wrapper for {@link WatsonPersonAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonPersonAudit
 * @generated
 */
@ProviderType
public class WatsonPersonAuditWrapper
	implements WatsonPersonAudit, ModelWrapper<WatsonPersonAudit> {

	public WatsonPersonAuditWrapper(WatsonPersonAudit watsonPersonAudit) {
		_watsonPersonAudit = watsonPersonAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonPersonAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonPersonAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonPersonAuditId", getWatsonPersonAuditId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("birthCountryId", getBirthCountryId());
		attributes.put(
			"citizenshipWatsonListTypeId", getCitizenshipWatsonListTypeId());
		attributes.put("countryWatsonListTypeId", getCountryWatsonListTypeId());
		attributes.put(
			"ethnicityWatsonListTypeId", getEthnicityWatsonListTypeId());
		attributes.put("eyesWatsonListTypeId", getEyesWatsonListTypeId());
		attributes.put("hairWatsonListTypeId", getHairWatsonListTypeId());
		attributes.put("originalWatsonPersonId", getOriginalWatsonPersonId());
		attributes.put("sexWatsonListTypeId", getSexWatsonListTypeId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("watsonPersonId", getWatsonPersonId());
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
		attributes.put("accepted", isAccepted());
		attributes.put("rescued", isRescued());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonPersonAuditId = (Long)attributes.get("watsonPersonAuditId");

		if (watsonPersonAuditId != null) {
			setWatsonPersonAuditId(watsonPersonAuditId);
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

		Long eyesWatsonListTypeId = (Long)attributes.get(
			"eyesWatsonListTypeId");

		if (eyesWatsonListTypeId != null) {
			setEyesWatsonListTypeId(eyesWatsonListTypeId);
		}

		Long hairWatsonListTypeId = (Long)attributes.get(
			"hairWatsonListTypeId");

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

		Long typeWatsonListTypeId = (Long)attributes.get(
			"typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		Long watsonPersonId = (Long)attributes.get("watsonPersonId");

		if (watsonPersonId != null) {
			setWatsonPersonId(watsonPersonId);
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
	public Object clone() {
		return new WatsonPersonAuditWrapper(
			(WatsonPersonAudit)_watsonPersonAudit.clone());
	}

	@Override
	public int compareTo(WatsonPersonAudit watsonPersonAudit) {
		return _watsonPersonAudit.compareTo(watsonPersonAudit);
	}

	/**
	 * Returns the accepted of this watson person audit.
	 *
	 * @return the accepted of this watson person audit
	 */
	@Override
	public boolean getAccepted() {
		return _watsonPersonAudit.getAccepted();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _watsonPersonAudit.getAvailableLanguageIds();
	}

	/**
	 * Returns the birth country ID of this watson person audit.
	 *
	 * @return the birth country ID of this watson person audit
	 */
	@Override
	public long getBirthCountryId() {
		return _watsonPersonAudit.getBirthCountryId();
	}

	/**
	 * Returns the birth date of this watson person audit.
	 *
	 * @return the birth date of this watson person audit
	 */
	@Override
	public Date getBirthDate() {
		return _watsonPersonAudit.getBirthDate();
	}

	/**
	 * Returns the citizenship watson list type ID of this watson person audit.
	 *
	 * @return the citizenship watson list type ID of this watson person audit
	 */
	@Override
	public long getCitizenshipWatsonListTypeId() {
		return _watsonPersonAudit.getCitizenshipWatsonListTypeId();
	}

	/**
	 * Returns the company ID of this watson person audit.
	 *
	 * @return the company ID of this watson person audit
	 */
	@Override
	public long getCompanyId() {
		return _watsonPersonAudit.getCompanyId();
	}

	/**
	 * Returns the country watson list type ID of this watson person audit.
	 *
	 * @return the country watson list type ID of this watson person audit
	 */
	@Override
	public long getCountryWatsonListTypeId() {
		return _watsonPersonAudit.getCountryWatsonListTypeId();
	}

	/**
	 * Returns the create date of this watson person audit.
	 *
	 * @return the create date of this watson person audit
	 */
	@Override
	public Date getCreateDate() {
		return _watsonPersonAudit.getCreateDate();
	}

	/**
	 * Returns the date accepted of this watson person audit.
	 *
	 * @return the date accepted of this watson person audit
	 */
	@Override
	public Date getDateAccepted() {
		return _watsonPersonAudit.getDateAccepted();
	}

	/**
	 * Returns the date rescued of this watson person audit.
	 *
	 * @return the date rescued of this watson person audit
	 */
	@Override
	public Date getDateRescued() {
		return _watsonPersonAudit.getDateRescued();
	}

	@Override
	public String getDefaultLanguageId() {
		return _watsonPersonAudit.getDefaultLanguageId();
	}

	/**
	 * Returns the description of this watson person audit.
	 *
	 * @return the description of this watson person audit
	 */
	@Override
	public String getDescription() {
		return _watsonPersonAudit.getDescription();
	}

	/**
	 * Returns the localized description of this watson person audit in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this watson person audit
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return _watsonPersonAudit.getDescription(locale);
	}

	/**
	 * Returns the localized description of this watson person audit in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this watson person audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return _watsonPersonAudit.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this watson person audit in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this watson person audit
	 */
	@Override
	public String getDescription(String languageId) {
		return _watsonPersonAudit.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this watson person audit in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this watson person audit
	 */
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return _watsonPersonAudit.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _watsonPersonAudit.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return _watsonPersonAudit.getDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized descriptions of this watson person audit.
	 *
	 * @return the locales and localized descriptions of this watson person audit
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return _watsonPersonAudit.getDescriptionMap();
	}

	/**
	 * Returns the end age of this watson person audit.
	 *
	 * @return the end age of this watson person audit
	 */
	@Override
	public String getEndAge() {
		return _watsonPersonAudit.getEndAge();
	}

	/**
	 * Returns the ethnicity watson list type ID of this watson person audit.
	 *
	 * @return the ethnicity watson list type ID of this watson person audit
	 */
	@Override
	public long getEthnicityWatsonListTypeId() {
		return _watsonPersonAudit.getEthnicityWatsonListTypeId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonPersonAudit.getExpandoBridge();
	}

	/**
	 * Returns the eyes watson list type ID of this watson person audit.
	 *
	 * @return the eyes watson list type ID of this watson person audit
	 */
	@Override
	public long getEyesWatsonListTypeId() {
		return _watsonPersonAudit.getEyesWatsonListTypeId();
	}

	/**
	 * Returns the group ID of this watson person audit.
	 *
	 * @return the group ID of this watson person audit
	 */
	@Override
	public long getGroupId() {
		return _watsonPersonAudit.getGroupId();
	}

	/**
	 * Returns the hair watson list type ID of this watson person audit.
	 *
	 * @return the hair watson list type ID of this watson person audit
	 */
	@Override
	public long getHairWatsonListTypeId() {
		return _watsonPersonAudit.getHairWatsonListTypeId();
	}

	/**
	 * Returns the height of this watson person audit.
	 *
	 * @return the height of this watson person audit
	 */
	@Override
	public String getHeight() {
		return _watsonPersonAudit.getHeight();
	}

	/**
	 * Returns the image payload of this watson person audit.
	 *
	 * @return the image payload of this watson person audit
	 */
	@Override
	public String getImagePayload() {
		return _watsonPersonAudit.getImagePayload();
	}

	/**
	 * Returns the modified date of this watson person audit.
	 *
	 * @return the modified date of this watson person audit
	 */
	@Override
	public Date getModifiedDate() {
		return _watsonPersonAudit.getModifiedDate();
	}

	/**
	 * Returns the occupation of this watson person audit.
	 *
	 * @return the occupation of this watson person audit
	 */
	@Override
	public String getOccupation() {
		return _watsonPersonAudit.getOccupation();
	}

	/**
	 * Returns the localized occupation of this watson person audit in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized occupation of this watson person audit
	 */
	@Override
	public String getOccupation(java.util.Locale locale) {
		return _watsonPersonAudit.getOccupation(locale);
	}

	/**
	 * Returns the localized occupation of this watson person audit in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized occupation of this watson person audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getOccupation(java.util.Locale locale, boolean useDefault) {
		return _watsonPersonAudit.getOccupation(locale, useDefault);
	}

	/**
	 * Returns the localized occupation of this watson person audit in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized occupation of this watson person audit
	 */
	@Override
	public String getOccupation(String languageId) {
		return _watsonPersonAudit.getOccupation(languageId);
	}

	/**
	 * Returns the localized occupation of this watson person audit in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized occupation of this watson person audit
	 */
	@Override
	public String getOccupation(String languageId, boolean useDefault) {
		return _watsonPersonAudit.getOccupation(languageId, useDefault);
	}

	@Override
	public String getOccupationCurrentLanguageId() {
		return _watsonPersonAudit.getOccupationCurrentLanguageId();
	}

	@Override
	public String getOccupationCurrentValue() {
		return _watsonPersonAudit.getOccupationCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized occupations of this watson person audit.
	 *
	 * @return the locales and localized occupations of this watson person audit
	 */
	@Override
	public Map<java.util.Locale, String> getOccupationMap() {
		return _watsonPersonAudit.getOccupationMap();
	}

	/**
	 * Returns the original watson person ID of this watson person audit.
	 *
	 * @return the original watson person ID of this watson person audit
	 */
	@Override
	public long getOriginalWatsonPersonId() {
		return _watsonPersonAudit.getOriginalWatsonPersonId();
	}

	/**
	 * Returns the primary key of this watson person audit.
	 *
	 * @return the primary key of this watson person audit
	 */
	@Override
	public long getPrimaryKey() {
		return _watsonPersonAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonPersonAudit.getPrimaryKeyObj();
	}

	/**
	 * Returns the rescued of this watson person audit.
	 *
	 * @return the rescued of this watson person audit
	 */
	@Override
	public boolean getRescued() {
		return _watsonPersonAudit.getRescued();
	}

	/**
	 * Returns the sex watson list type ID of this watson person audit.
	 *
	 * @return the sex watson list type ID of this watson person audit
	 */
	@Override
	public long getSexWatsonListTypeId() {
		return _watsonPersonAudit.getSexWatsonListTypeId();
	}

	/**
	 * Returns the start age of this watson person audit.
	 *
	 * @return the start age of this watson person audit
	 */
	@Override
	public String getStartAge() {
		return _watsonPersonAudit.getStartAge();
	}

	/**
	 * Returns the status of this watson person audit.
	 *
	 * @return the status of this watson person audit
	 */
	@Override
	public int getStatus() {
		return _watsonPersonAudit.getStatus();
	}

	/**
	 * Returns the type watson list type ID of this watson person audit.
	 *
	 * @return the type watson list type ID of this watson person audit
	 */
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonPersonAudit.getTypeWatsonListTypeId();
	}

	/**
	 * Returns the user ID of this watson person audit.
	 *
	 * @return the user ID of this watson person audit
	 */
	@Override
	public long getUserId() {
		return _watsonPersonAudit.getUserId();
	}

	/**
	 * Returns the user name of this watson person audit.
	 *
	 * @return the user name of this watson person audit
	 */
	@Override
	public String getUserName() {
		return _watsonPersonAudit.getUserName();
	}

	/**
	 * Returns the user uuid of this watson person audit.
	 *
	 * @return the user uuid of this watson person audit
	 */
	@Override
	public String getUserUuid() {
		return _watsonPersonAudit.getUserUuid();
	}

	/**
	 * Returns the watson incident ID of this watson person audit.
	 *
	 * @return the watson incident ID of this watson person audit
	 */
	@Override
	public long getWatsonIncidentId() {
		return _watsonPersonAudit.getWatsonIncidentId();
	}

	/**
	 * Returns the watson person audit ID of this watson person audit.
	 *
	 * @return the watson person audit ID of this watson person audit
	 */
	@Override
	public long getWatsonPersonAuditId() {
		return _watsonPersonAudit.getWatsonPersonAuditId();
	}

	/**
	 * Returns the watson person ID of this watson person audit.
	 *
	 * @return the watson person ID of this watson person audit
	 */
	@Override
	public long getWatsonPersonId() {
		return _watsonPersonAudit.getWatsonPersonId();
	}

	/**
	 * Returns the weight of this watson person audit.
	 *
	 * @return the weight of this watson person audit
	 */
	@Override
	public String getWeight() {
		return _watsonPersonAudit.getWeight();
	}

	@Override
	public int hashCode() {
		return _watsonPersonAudit.hashCode();
	}

	/**
	 * Returns <code>true</code> if this watson person audit is accepted.
	 *
	 * @return <code>true</code> if this watson person audit is accepted; <code>false</code> otherwise
	 */
	@Override
	public boolean isAccepted() {
		return _watsonPersonAudit.isAccepted();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonPersonAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonPersonAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonPersonAudit.isNew();
	}

	/**
	 * Returns <code>true</code> if this watson person audit is rescued.
	 *
	 * @return <code>true</code> if this watson person audit is rescued; <code>false</code> otherwise
	 */
	@Override
	public boolean isRescued() {
		return _watsonPersonAudit.isRescued();
	}

	@Override
	public void persist() {
		_watsonPersonAudit.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_watsonPersonAudit.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_watsonPersonAudit.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	 * Sets whether this watson person audit is accepted.
	 *
	 * @param accepted the accepted of this watson person audit
	 */
	@Override
	public void setAccepted(boolean accepted) {
		_watsonPersonAudit.setAccepted(accepted);
	}

	/**
	 * Sets the birth country ID of this watson person audit.
	 *
	 * @param birthCountryId the birth country ID of this watson person audit
	 */
	@Override
	public void setBirthCountryId(long birthCountryId) {
		_watsonPersonAudit.setBirthCountryId(birthCountryId);
	}

	/**
	 * Sets the birth date of this watson person audit.
	 *
	 * @param birthDate the birth date of this watson person audit
	 */
	@Override
	public void setBirthDate(Date birthDate) {
		_watsonPersonAudit.setBirthDate(birthDate);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonPersonAudit.setCachedModel(cachedModel);
	}

	/**
	 * Sets the citizenship watson list type ID of this watson person audit.
	 *
	 * @param citizenshipWatsonListTypeId the citizenship watson list type ID of this watson person audit
	 */
	@Override
	public void setCitizenshipWatsonListTypeId(
		long citizenshipWatsonListTypeId) {

		_watsonPersonAudit.setCitizenshipWatsonListTypeId(
			citizenshipWatsonListTypeId);
	}

	/**
	 * Sets the company ID of this watson person audit.
	 *
	 * @param companyId the company ID of this watson person audit
	 */
	@Override
	public void setCompanyId(long companyId) {
		_watsonPersonAudit.setCompanyId(companyId);
	}

	/**
	 * Sets the country watson list type ID of this watson person audit.
	 *
	 * @param countryWatsonListTypeId the country watson list type ID of this watson person audit
	 */
	@Override
	public void setCountryWatsonListTypeId(long countryWatsonListTypeId) {
		_watsonPersonAudit.setCountryWatsonListTypeId(countryWatsonListTypeId);
	}

	/**
	 * Sets the create date of this watson person audit.
	 *
	 * @param createDate the create date of this watson person audit
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_watsonPersonAudit.setCreateDate(createDate);
	}

	/**
	 * Sets the date accepted of this watson person audit.
	 *
	 * @param dateAccepted the date accepted of this watson person audit
	 */
	@Override
	public void setDateAccepted(Date dateAccepted) {
		_watsonPersonAudit.setDateAccepted(dateAccepted);
	}

	/**
	 * Sets the date rescued of this watson person audit.
	 *
	 * @param dateRescued the date rescued of this watson person audit
	 */
	@Override
	public void setDateRescued(Date dateRescued) {
		_watsonPersonAudit.setDateRescued(dateRescued);
	}

	/**
	 * Sets the description of this watson person audit.
	 *
	 * @param description the description of this watson person audit
	 */
	@Override
	public void setDescription(String description) {
		_watsonPersonAudit.setDescription(description);
	}

	/**
	 * Sets the localized description of this watson person audit in the language.
	 *
	 * @param description the localized description of this watson person audit
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		_watsonPersonAudit.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this watson person audit in the language, and sets the default locale.
	 *
	 * @param description the localized description of this watson person audit
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescription(
		String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_watsonPersonAudit.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_watsonPersonAudit.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized descriptions of this watson person audit from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this watson person audit
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		_watsonPersonAudit.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this watson person audit from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this watson person audit
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		_watsonPersonAudit.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the end age of this watson person audit.
	 *
	 * @param endAge the end age of this watson person audit
	 */
	@Override
	public void setEndAge(String endAge) {
		_watsonPersonAudit.setEndAge(endAge);
	}

	/**
	 * Sets the ethnicity watson list type ID of this watson person audit.
	 *
	 * @param ethnicityWatsonListTypeId the ethnicity watson list type ID of this watson person audit
	 */
	@Override
	public void setEthnicityWatsonListTypeId(long ethnicityWatsonListTypeId) {
		_watsonPersonAudit.setEthnicityWatsonListTypeId(
			ethnicityWatsonListTypeId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_watsonPersonAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonPersonAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonPersonAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the eyes watson list type ID of this watson person audit.
	 *
	 * @param eyesWatsonListTypeId the eyes watson list type ID of this watson person audit
	 */
	@Override
	public void setEyesWatsonListTypeId(long eyesWatsonListTypeId) {
		_watsonPersonAudit.setEyesWatsonListTypeId(eyesWatsonListTypeId);
	}

	/**
	 * Sets the group ID of this watson person audit.
	 *
	 * @param groupId the group ID of this watson person audit
	 */
	@Override
	public void setGroupId(long groupId) {
		_watsonPersonAudit.setGroupId(groupId);
	}

	/**
	 * Sets the hair watson list type ID of this watson person audit.
	 *
	 * @param hairWatsonListTypeId the hair watson list type ID of this watson person audit
	 */
	@Override
	public void setHairWatsonListTypeId(long hairWatsonListTypeId) {
		_watsonPersonAudit.setHairWatsonListTypeId(hairWatsonListTypeId);
	}

	/**
	 * Sets the height of this watson person audit.
	 *
	 * @param height the height of this watson person audit
	 */
	@Override
	public void setHeight(String height) {
		_watsonPersonAudit.setHeight(height);
	}

	/**
	 * Sets the image payload of this watson person audit.
	 *
	 * @param imagePayload the image payload of this watson person audit
	 */
	@Override
	public void setImagePayload(String imagePayload) {
		_watsonPersonAudit.setImagePayload(imagePayload);
	}

	/**
	 * Sets the modified date of this watson person audit.
	 *
	 * @param modifiedDate the modified date of this watson person audit
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonPersonAudit.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonPersonAudit.setNew(n);
	}

	/**
	 * Sets the occupation of this watson person audit.
	 *
	 * @param occupation the occupation of this watson person audit
	 */
	@Override
	public void setOccupation(String occupation) {
		_watsonPersonAudit.setOccupation(occupation);
	}

	/**
	 * Sets the localized occupation of this watson person audit in the language.
	 *
	 * @param occupation the localized occupation of this watson person audit
	 * @param locale the locale of the language
	 */
	@Override
	public void setOccupation(String occupation, java.util.Locale locale) {
		_watsonPersonAudit.setOccupation(occupation, locale);
	}

	/**
	 * Sets the localized occupation of this watson person audit in the language, and sets the default locale.
	 *
	 * @param occupation the localized occupation of this watson person audit
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setOccupation(
		String occupation, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_watsonPersonAudit.setOccupation(occupation, locale, defaultLocale);
	}

	@Override
	public void setOccupationCurrentLanguageId(String languageId) {
		_watsonPersonAudit.setOccupationCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized occupations of this watson person audit from the map of locales and localized occupations.
	 *
	 * @param occupationMap the locales and localized occupations of this watson person audit
	 */
	@Override
	public void setOccupationMap(Map<java.util.Locale, String> occupationMap) {
		_watsonPersonAudit.setOccupationMap(occupationMap);
	}

	/**
	 * Sets the localized occupations of this watson person audit from the map of locales and localized occupations, and sets the default locale.
	 *
	 * @param occupationMap the locales and localized occupations of this watson person audit
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setOccupationMap(
		Map<java.util.Locale, String> occupationMap,
		java.util.Locale defaultLocale) {

		_watsonPersonAudit.setOccupationMap(occupationMap, defaultLocale);
	}

	/**
	 * Sets the original watson person ID of this watson person audit.
	 *
	 * @param originalWatsonPersonId the original watson person ID of this watson person audit
	 */
	@Override
	public void setOriginalWatsonPersonId(long originalWatsonPersonId) {
		_watsonPersonAudit.setOriginalWatsonPersonId(originalWatsonPersonId);
	}

	/**
	 * Sets the primary key of this watson person audit.
	 *
	 * @param primaryKey the primary key of this watson person audit
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonPersonAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonPersonAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets whether this watson person audit is rescued.
	 *
	 * @param rescued the rescued of this watson person audit
	 */
	@Override
	public void setRescued(boolean rescued) {
		_watsonPersonAudit.setRescued(rescued);
	}

	/**
	 * Sets the sex watson list type ID of this watson person audit.
	 *
	 * @param sexWatsonListTypeId the sex watson list type ID of this watson person audit
	 */
	@Override
	public void setSexWatsonListTypeId(long sexWatsonListTypeId) {
		_watsonPersonAudit.setSexWatsonListTypeId(sexWatsonListTypeId);
	}

	/**
	 * Sets the start age of this watson person audit.
	 *
	 * @param startAge the start age of this watson person audit
	 */
	@Override
	public void setStartAge(String startAge) {
		_watsonPersonAudit.setStartAge(startAge);
	}

	/**
	 * Sets the status of this watson person audit.
	 *
	 * @param status the status of this watson person audit
	 */
	@Override
	public void setStatus(int status) {
		_watsonPersonAudit.setStatus(status);
	}

	/**
	 * Sets the type watson list type ID of this watson person audit.
	 *
	 * @param typeWatsonListTypeId the type watson list type ID of this watson person audit
	 */
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonPersonAudit.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	 * Sets the user ID of this watson person audit.
	 *
	 * @param userId the user ID of this watson person audit
	 */
	@Override
	public void setUserId(long userId) {
		_watsonPersonAudit.setUserId(userId);
	}

	/**
	 * Sets the user name of this watson person audit.
	 *
	 * @param userName the user name of this watson person audit
	 */
	@Override
	public void setUserName(String userName) {
		_watsonPersonAudit.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this watson person audit.
	 *
	 * @param userUuid the user uuid of this watson person audit
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_watsonPersonAudit.setUserUuid(userUuid);
	}

	/**
	 * Sets the watson incident ID of this watson person audit.
	 *
	 * @param watsonIncidentId the watson incident ID of this watson person audit
	 */
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonPersonAudit.setWatsonIncidentId(watsonIncidentId);
	}

	/**
	 * Sets the watson person audit ID of this watson person audit.
	 *
	 * @param watsonPersonAuditId the watson person audit ID of this watson person audit
	 */
	@Override
	public void setWatsonPersonAuditId(long watsonPersonAuditId) {
		_watsonPersonAudit.setWatsonPersonAuditId(watsonPersonAuditId);
	}

	/**
	 * Sets the watson person ID of this watson person audit.
	 *
	 * @param watsonPersonId the watson person ID of this watson person audit
	 */
	@Override
	public void setWatsonPersonId(long watsonPersonId) {
		_watsonPersonAudit.setWatsonPersonId(watsonPersonId);
	}

	/**
	 * Sets the weight of this watson person audit.
	 *
	 * @param weight the weight of this watson person audit
	 */
	@Override
	public void setWeight(String weight) {
		_watsonPersonAudit.setWeight(weight);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonPersonAudit>
		toCacheModel() {

		return _watsonPersonAudit.toCacheModel();
	}

	@Override
	public WatsonPersonAudit toEscapedModel() {
		return new WatsonPersonAuditWrapper(
			_watsonPersonAudit.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonPersonAudit.toString();
	}

	@Override
	public WatsonPersonAudit toUnescapedModel() {
		return new WatsonPersonAuditWrapper(
			_watsonPersonAudit.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonPersonAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonPersonAuditWrapper)) {
			return false;
		}

		WatsonPersonAuditWrapper watsonPersonAuditWrapper =
			(WatsonPersonAuditWrapper)obj;

		if (Objects.equals(
				_watsonPersonAudit,
				watsonPersonAuditWrapper._watsonPersonAudit)) {

			return true;
		}

		return false;
	}

	@Override
	public WatsonPersonAudit getWrappedModel() {
		return _watsonPersonAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonPersonAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonPersonAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonPersonAudit.resetOriginalValues();
	}

	private final WatsonPersonAudit _watsonPersonAudit;

}