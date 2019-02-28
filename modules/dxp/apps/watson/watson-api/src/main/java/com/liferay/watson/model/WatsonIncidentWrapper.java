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
 * This class is a wrapper for {@link WatsonIncident}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonIncident
 * @generated
 */
@ProviderType
public class WatsonIncidentWrapper
	implements WatsonIncident, ModelWrapper<WatsonIncident> {

	public WatsonIncidentWrapper(WatsonIncident watsonIncident) {
		_watsonIncident = watsonIncident;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonIncident.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonIncident.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put(
			"externalCaseWatsonListTypeId", getExternalCaseWatsonListTypeId());
		attributes.put("sourceWatsonListTypeId", getSourceWatsonListTypeId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("subtypeWatsonListTypeId", getSubtypeWatsonListTypeId());
		attributes.put("audienceAdultCount", getAudienceAdultCount());
		attributes.put("audienceChildCount", getAudienceChildCount());
		attributes.put("victimAdultCount", getVictimAdultCount());
		attributes.put("victimChildCount", getVictimChildCount());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("externalCaseId", getExternalCaseId());
		attributes.put("reportDate", getReportDate());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("expenses", getExpenses());
		attributes.put("incidentStatus", getIncidentStatus());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
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

		Long externalCaseWatsonListTypeId = (Long)attributes.get(
			"externalCaseWatsonListTypeId");

		if (externalCaseWatsonListTypeId != null) {
			setExternalCaseWatsonListTypeId(externalCaseWatsonListTypeId);
		}

		Long sourceWatsonListTypeId = (Long)attributes.get(
			"sourceWatsonListTypeId");

		if (sourceWatsonListTypeId != null) {
			setSourceWatsonListTypeId(sourceWatsonListTypeId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get(
			"typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long subtypeWatsonListTypeId = (Long)attributes.get(
			"subtypeWatsonListTypeId");

		if (subtypeWatsonListTypeId != null) {
			setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
		}

		Long audienceAdultCount = (Long)attributes.get("audienceAdultCount");

		if (audienceAdultCount != null) {
			setAudienceAdultCount(audienceAdultCount);
		}

		Long audienceChildCount = (Long)attributes.get("audienceChildCount");

		if (audienceChildCount != null) {
			setAudienceChildCount(audienceChildCount);
		}

		Long victimAdultCount = (Long)attributes.get("victimAdultCount");

		if (victimAdultCount != null) {
			setVictimAdultCount(victimAdultCount);
		}

		Long victimChildCount = (Long)attributes.get("victimChildCount");

		if (victimChildCount != null) {
			setVictimChildCount(victimChildCount);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String externalCaseId = (String)attributes.get("externalCaseId");

		if (externalCaseId != null) {
			setExternalCaseId(externalCaseId);
		}

		Date reportDate = (Date)attributes.get("reportDate");

		if (reportDate != null) {
			setReportDate(reportDate);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Double expenses = (Double)attributes.get("expenses");

		if (expenses != null) {
			setExpenses(expenses);
		}

		Integer incidentStatus = (Integer)attributes.get("incidentStatus");

		if (incidentStatus != null) {
			setIncidentStatus(incidentStatus);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new WatsonIncidentWrapper(
			(WatsonIncident)_watsonIncident.clone());
	}

	@Override
	public int compareTo(WatsonIncident watsonIncident) {
		return _watsonIncident.compareTo(watsonIncident);
	}

	/**
	 * Returns the audience adult count of this watson incident.
	 *
	 * @return the audience adult count of this watson incident
	 */
	@Override
	public long getAudienceAdultCount() {
		return _watsonIncident.getAudienceAdultCount();
	}

	/**
	 * Returns the audience child count of this watson incident.
	 *
	 * @return the audience child count of this watson incident
	 */
	@Override
	public long getAudienceChildCount() {
		return _watsonIncident.getAudienceChildCount();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _watsonIncident.getAvailableLanguageIds();
	}

	/**
	 * Returns the company ID of this watson incident.
	 *
	 * @return the company ID of this watson incident
	 */
	@Override
	public long getCompanyId() {
		return _watsonIncident.getCompanyId();
	}

	/**
	 * Returns the create date of this watson incident.
	 *
	 * @return the create date of this watson incident
	 */
	@Override
	public Date getCreateDate() {
		return _watsonIncident.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _watsonIncident.getDefaultLanguageId();
	}

	/**
	 * Returns the description of this watson incident.
	 *
	 * @return the description of this watson incident
	 */
	@Override
	public String getDescription() {
		return _watsonIncident.getDescription();
	}

	/**
	 * Returns the localized description of this watson incident in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this watson incident
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return _watsonIncident.getDescription(locale);
	}

	/**
	 * Returns the localized description of this watson incident in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this watson incident. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return _watsonIncident.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this watson incident in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this watson incident
	 */
	@Override
	public String getDescription(String languageId) {
		return _watsonIncident.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this watson incident in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this watson incident
	 */
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return _watsonIncident.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _watsonIncident.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return _watsonIncident.getDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized descriptions of this watson incident.
	 *
	 * @return the locales and localized descriptions of this watson incident
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return _watsonIncident.getDescriptionMap();
	}

	/**
	 * Returns the end date of this watson incident.
	 *
	 * @return the end date of this watson incident
	 */
	@Override
	public Date getEndDate() {
		return _watsonIncident.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonIncident.getExpandoBridge();
	}

	/**
	 * Returns the expenses of this watson incident.
	 *
	 * @return the expenses of this watson incident
	 */
	@Override
	public double getExpenses() {
		return _watsonIncident.getExpenses();
	}

	/**
	 * Returns the external case ID of this watson incident.
	 *
	 * @return the external case ID of this watson incident
	 */
	@Override
	public String getExternalCaseId() {
		return _watsonIncident.getExternalCaseId();
	}

	/**
	 * Returns the external case watson list type ID of this watson incident.
	 *
	 * @return the external case watson list type ID of this watson incident
	 */
	@Override
	public long getExternalCaseWatsonListTypeId() {
		return _watsonIncident.getExternalCaseWatsonListTypeId();
	}

	/**
	 * Returns the group ID of this watson incident.
	 *
	 * @return the group ID of this watson incident
	 */
	@Override
	public long getGroupId() {
		return _watsonIncident.getGroupId();
	}

	/**
	 * Returns the incident status of this watson incident.
	 *
	 * @return the incident status of this watson incident
	 */
	@Override
	public int getIncidentStatus() {
		return _watsonIncident.getIncidentStatus();
	}

	/**
	 * Returns the modified date of this watson incident.
	 *
	 * @return the modified date of this watson incident
	 */
	@Override
	public Date getModifiedDate() {
		return _watsonIncident.getModifiedDate();
	}

	/**
	 * Returns the name of this watson incident.
	 *
	 * @return the name of this watson incident
	 */
	@Override
	public String getName() {
		return _watsonIncident.getName();
	}

	/**
	 * Returns the primary key of this watson incident.
	 *
	 * @return the primary key of this watson incident
	 */
	@Override
	public long getPrimaryKey() {
		return _watsonIncident.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonIncident.getPrimaryKeyObj();
	}

	/**
	 * Returns the report date of this watson incident.
	 *
	 * @return the report date of this watson incident
	 */
	@Override
	public Date getReportDate() {
		return _watsonIncident.getReportDate();
	}

	/**
	 * Returns the source watson list type ID of this watson incident.
	 *
	 * @return the source watson list type ID of this watson incident
	 */
	@Override
	public long getSourceWatsonListTypeId() {
		return _watsonIncident.getSourceWatsonListTypeId();
	}

	/**
	 * Returns the start date of this watson incident.
	 *
	 * @return the start date of this watson incident
	 */
	@Override
	public Date getStartDate() {
		return _watsonIncident.getStartDate();
	}

	/**
	 * Returns the status of this watson incident.
	 *
	 * @return the status of this watson incident
	 */
	@Override
	public int getStatus() {
		return _watsonIncident.getStatus();
	}

	/**
	 * Returns the subtype watson list type ID of this watson incident.
	 *
	 * @return the subtype watson list type ID of this watson incident
	 */
	@Override
	public long getSubtypeWatsonListTypeId() {
		return _watsonIncident.getSubtypeWatsonListTypeId();
	}

	/**
	 * Returns the type watson list type ID of this watson incident.
	 *
	 * @return the type watson list type ID of this watson incident
	 */
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonIncident.getTypeWatsonListTypeId();
	}

	/**
	 * Returns the user ID of this watson incident.
	 *
	 * @return the user ID of this watson incident
	 */
	@Override
	public long getUserId() {
		return _watsonIncident.getUserId();
	}

	/**
	 * Returns the user name of this watson incident.
	 *
	 * @return the user name of this watson incident
	 */
	@Override
	public String getUserName() {
		return _watsonIncident.getUserName();
	}

	/**
	 * Returns the user uuid of this watson incident.
	 *
	 * @return the user uuid of this watson incident
	 */
	@Override
	public String getUserUuid() {
		return _watsonIncident.getUserUuid();
	}

	/**
	 * Returns the victim adult count of this watson incident.
	 *
	 * @return the victim adult count of this watson incident
	 */
	@Override
	public long getVictimAdultCount() {
		return _watsonIncident.getVictimAdultCount();
	}

	/**
	 * Returns the victim child count of this watson incident.
	 *
	 * @return the victim child count of this watson incident
	 */
	@Override
	public long getVictimChildCount() {
		return _watsonIncident.getVictimChildCount();
	}

	/**
	 * Returns the watson incident ID of this watson incident.
	 *
	 * @return the watson incident ID of this watson incident
	 */
	@Override
	public long getWatsonIncidentId() {
		return _watsonIncident.getWatsonIncidentId();
	}

	@Override
	public int hashCode() {
		return _watsonIncident.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonIncident.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonIncident.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonIncident.isNew();
	}

	@Override
	public void persist() {
		_watsonIncident.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_watsonIncident.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_watsonIncident.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	 * Sets the audience adult count of this watson incident.
	 *
	 * @param audienceAdultCount the audience adult count of this watson incident
	 */
	@Override
	public void setAudienceAdultCount(long audienceAdultCount) {
		_watsonIncident.setAudienceAdultCount(audienceAdultCount);
	}

	/**
	 * Sets the audience child count of this watson incident.
	 *
	 * @param audienceChildCount the audience child count of this watson incident
	 */
	@Override
	public void setAudienceChildCount(long audienceChildCount) {
		_watsonIncident.setAudienceChildCount(audienceChildCount);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonIncident.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this watson incident.
	 *
	 * @param companyId the company ID of this watson incident
	 */
	@Override
	public void setCompanyId(long companyId) {
		_watsonIncident.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this watson incident.
	 *
	 * @param createDate the create date of this watson incident
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_watsonIncident.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this watson incident.
	 *
	 * @param description the description of this watson incident
	 */
	@Override
	public void setDescription(String description) {
		_watsonIncident.setDescription(description);
	}

	/**
	 * Sets the localized description of this watson incident in the language.
	 *
	 * @param description the localized description of this watson incident
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		_watsonIncident.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this watson incident in the language, and sets the default locale.
	 *
	 * @param description the localized description of this watson incident
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescription(
		String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_watsonIncident.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_watsonIncident.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized descriptions of this watson incident from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this watson incident
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		_watsonIncident.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this watson incident from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this watson incident
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		_watsonIncident.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the end date of this watson incident.
	 *
	 * @param endDate the end date of this watson incident
	 */
	@Override
	public void setEndDate(Date endDate) {
		_watsonIncident.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_watsonIncident.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonIncident.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonIncident.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the expenses of this watson incident.
	 *
	 * @param expenses the expenses of this watson incident
	 */
	@Override
	public void setExpenses(double expenses) {
		_watsonIncident.setExpenses(expenses);
	}

	/**
	 * Sets the external case ID of this watson incident.
	 *
	 * @param externalCaseId the external case ID of this watson incident
	 */
	@Override
	public void setExternalCaseId(String externalCaseId) {
		_watsonIncident.setExternalCaseId(externalCaseId);
	}

	/**
	 * Sets the external case watson list type ID of this watson incident.
	 *
	 * @param externalCaseWatsonListTypeId the external case watson list type ID of this watson incident
	 */
	@Override
	public void setExternalCaseWatsonListTypeId(
		long externalCaseWatsonListTypeId) {

		_watsonIncident.setExternalCaseWatsonListTypeId(
			externalCaseWatsonListTypeId);
	}

	/**
	 * Sets the group ID of this watson incident.
	 *
	 * @param groupId the group ID of this watson incident
	 */
	@Override
	public void setGroupId(long groupId) {
		_watsonIncident.setGroupId(groupId);
	}

	/**
	 * Sets the incident status of this watson incident.
	 *
	 * @param incidentStatus the incident status of this watson incident
	 */
	@Override
	public void setIncidentStatus(int incidentStatus) {
		_watsonIncident.setIncidentStatus(incidentStatus);
	}

	/**
	 * Sets the modified date of this watson incident.
	 *
	 * @param modifiedDate the modified date of this watson incident
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonIncident.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this watson incident.
	 *
	 * @param name the name of this watson incident
	 */
	@Override
	public void setName(String name) {
		_watsonIncident.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_watsonIncident.setNew(n);
	}

	/**
	 * Sets the primary key of this watson incident.
	 *
	 * @param primaryKey the primary key of this watson incident
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonIncident.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonIncident.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the report date of this watson incident.
	 *
	 * @param reportDate the report date of this watson incident
	 */
	@Override
	public void setReportDate(Date reportDate) {
		_watsonIncident.setReportDate(reportDate);
	}

	/**
	 * Sets the source watson list type ID of this watson incident.
	 *
	 * @param sourceWatsonListTypeId the source watson list type ID of this watson incident
	 */
	@Override
	public void setSourceWatsonListTypeId(long sourceWatsonListTypeId) {
		_watsonIncident.setSourceWatsonListTypeId(sourceWatsonListTypeId);
	}

	/**
	 * Sets the start date of this watson incident.
	 *
	 * @param startDate the start date of this watson incident
	 */
	@Override
	public void setStartDate(Date startDate) {
		_watsonIncident.setStartDate(startDate);
	}

	/**
	 * Sets the status of this watson incident.
	 *
	 * @param status the status of this watson incident
	 */
	@Override
	public void setStatus(int status) {
		_watsonIncident.setStatus(status);
	}

	/**
	 * Sets the subtype watson list type ID of this watson incident.
	 *
	 * @param subtypeWatsonListTypeId the subtype watson list type ID of this watson incident
	 */
	@Override
	public void setSubtypeWatsonListTypeId(long subtypeWatsonListTypeId) {
		_watsonIncident.setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
	}

	/**
	 * Sets the type watson list type ID of this watson incident.
	 *
	 * @param typeWatsonListTypeId the type watson list type ID of this watson incident
	 */
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonIncident.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	 * Sets the user ID of this watson incident.
	 *
	 * @param userId the user ID of this watson incident
	 */
	@Override
	public void setUserId(long userId) {
		_watsonIncident.setUserId(userId);
	}

	/**
	 * Sets the user name of this watson incident.
	 *
	 * @param userName the user name of this watson incident
	 */
	@Override
	public void setUserName(String userName) {
		_watsonIncident.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this watson incident.
	 *
	 * @param userUuid the user uuid of this watson incident
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_watsonIncident.setUserUuid(userUuid);
	}

	/**
	 * Sets the victim adult count of this watson incident.
	 *
	 * @param victimAdultCount the victim adult count of this watson incident
	 */
	@Override
	public void setVictimAdultCount(long victimAdultCount) {
		_watsonIncident.setVictimAdultCount(victimAdultCount);
	}

	/**
	 * Sets the victim child count of this watson incident.
	 *
	 * @param victimChildCount the victim child count of this watson incident
	 */
	@Override
	public void setVictimChildCount(long victimChildCount) {
		_watsonIncident.setVictimChildCount(victimChildCount);
	}

	/**
	 * Sets the watson incident ID of this watson incident.
	 *
	 * @param watsonIncidentId the watson incident ID of this watson incident
	 */
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncident.setWatsonIncidentId(watsonIncidentId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonIncident>
		toCacheModel() {

		return _watsonIncident.toCacheModel();
	}

	@Override
	public WatsonIncident toEscapedModel() {
		return new WatsonIncidentWrapper(_watsonIncident.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonIncident.toString();
	}

	@Override
	public WatsonIncident toUnescapedModel() {
		return new WatsonIncidentWrapper(_watsonIncident.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonIncident.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonIncidentWrapper)) {
			return false;
		}

		WatsonIncidentWrapper watsonIncidentWrapper =
			(WatsonIncidentWrapper)obj;

		if (Objects.equals(
				_watsonIncident, watsonIncidentWrapper._watsonIncident)) {

			return true;
		}

		return false;
	}

	@Override
	public WatsonIncident getWrappedModel() {
		return _watsonIncident;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonIncident.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonIncident.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonIncident.resetOriginalValues();
	}

	private final WatsonIncident _watsonIncident;

}