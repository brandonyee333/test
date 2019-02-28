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
 * This class is a wrapper for {@link WatsonIncidentAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonIncidentAudit
 * @generated
 */
@ProviderType
public class WatsonIncidentAuditWrapper
	implements WatsonIncidentAudit, ModelWrapper<WatsonIncidentAudit> {

	public WatsonIncidentAuditWrapper(WatsonIncidentAudit watsonIncidentAudit) {
		_watsonIncidentAudit = watsonIncidentAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonIncidentAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonIncidentAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonIncidentAuditId", getWatsonIncidentAuditId());
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
		attributes.put("watsonIncidentId", getWatsonIncidentId());
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
		Long watsonIncidentAuditId = (Long)attributes.get(
			"watsonIncidentAuditId");

		if (watsonIncidentAuditId != null) {
			setWatsonIncidentAuditId(watsonIncidentAuditId);
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

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
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
		return new WatsonIncidentAuditWrapper(
			(WatsonIncidentAudit)_watsonIncidentAudit.clone());
	}

	@Override
	public int compareTo(WatsonIncidentAudit watsonIncidentAudit) {
		return _watsonIncidentAudit.compareTo(watsonIncidentAudit);
	}

	/**
	 * Returns the audience adult count of this watson incident audit.
	 *
	 * @return the audience adult count of this watson incident audit
	 */
	@Override
	public long getAudienceAdultCount() {
		return _watsonIncidentAudit.getAudienceAdultCount();
	}

	/**
	 * Returns the audience child count of this watson incident audit.
	 *
	 * @return the audience child count of this watson incident audit
	 */
	@Override
	public long getAudienceChildCount() {
		return _watsonIncidentAudit.getAudienceChildCount();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _watsonIncidentAudit.getAvailableLanguageIds();
	}

	/**
	 * Returns the company ID of this watson incident audit.
	 *
	 * @return the company ID of this watson incident audit
	 */
	@Override
	public long getCompanyId() {
		return _watsonIncidentAudit.getCompanyId();
	}

	/**
	 * Returns the create date of this watson incident audit.
	 *
	 * @return the create date of this watson incident audit
	 */
	@Override
	public Date getCreateDate() {
		return _watsonIncidentAudit.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _watsonIncidentAudit.getDefaultLanguageId();
	}

	/**
	 * Returns the description of this watson incident audit.
	 *
	 * @return the description of this watson incident audit
	 */
	@Override
	public String getDescription() {
		return _watsonIncidentAudit.getDescription();
	}

	/**
	 * Returns the localized description of this watson incident audit in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this watson incident audit
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return _watsonIncidentAudit.getDescription(locale);
	}

	/**
	 * Returns the localized description of this watson incident audit in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this watson incident audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return _watsonIncidentAudit.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this watson incident audit in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this watson incident audit
	 */
	@Override
	public String getDescription(String languageId) {
		return _watsonIncidentAudit.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this watson incident audit in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this watson incident audit
	 */
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return _watsonIncidentAudit.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _watsonIncidentAudit.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return _watsonIncidentAudit.getDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized descriptions of this watson incident audit.
	 *
	 * @return the locales and localized descriptions of this watson incident audit
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return _watsonIncidentAudit.getDescriptionMap();
	}

	/**
	 * Returns the end date of this watson incident audit.
	 *
	 * @return the end date of this watson incident audit
	 */
	@Override
	public Date getEndDate() {
		return _watsonIncidentAudit.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonIncidentAudit.getExpandoBridge();
	}

	/**
	 * Returns the expenses of this watson incident audit.
	 *
	 * @return the expenses of this watson incident audit
	 */
	@Override
	public double getExpenses() {
		return _watsonIncidentAudit.getExpenses();
	}

	/**
	 * Returns the external case ID of this watson incident audit.
	 *
	 * @return the external case ID of this watson incident audit
	 */
	@Override
	public String getExternalCaseId() {
		return _watsonIncidentAudit.getExternalCaseId();
	}

	/**
	 * Returns the external case watson list type ID of this watson incident audit.
	 *
	 * @return the external case watson list type ID of this watson incident audit
	 */
	@Override
	public long getExternalCaseWatsonListTypeId() {
		return _watsonIncidentAudit.getExternalCaseWatsonListTypeId();
	}

	/**
	 * Returns the group ID of this watson incident audit.
	 *
	 * @return the group ID of this watson incident audit
	 */
	@Override
	public long getGroupId() {
		return _watsonIncidentAudit.getGroupId();
	}

	/**
	 * Returns the incident status of this watson incident audit.
	 *
	 * @return the incident status of this watson incident audit
	 */
	@Override
	public int getIncidentStatus() {
		return _watsonIncidentAudit.getIncidentStatus();
	}

	/**
	 * Returns the modified date of this watson incident audit.
	 *
	 * @return the modified date of this watson incident audit
	 */
	@Override
	public Date getModifiedDate() {
		return _watsonIncidentAudit.getModifiedDate();
	}

	/**
	 * Returns the name of this watson incident audit.
	 *
	 * @return the name of this watson incident audit
	 */
	@Override
	public String getName() {
		return _watsonIncidentAudit.getName();
	}

	/**
	 * Returns the primary key of this watson incident audit.
	 *
	 * @return the primary key of this watson incident audit
	 */
	@Override
	public long getPrimaryKey() {
		return _watsonIncidentAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonIncidentAudit.getPrimaryKeyObj();
	}

	/**
	 * Returns the report date of this watson incident audit.
	 *
	 * @return the report date of this watson incident audit
	 */
	@Override
	public Date getReportDate() {
		return _watsonIncidentAudit.getReportDate();
	}

	/**
	 * Returns the source watson list type ID of this watson incident audit.
	 *
	 * @return the source watson list type ID of this watson incident audit
	 */
	@Override
	public long getSourceWatsonListTypeId() {
		return _watsonIncidentAudit.getSourceWatsonListTypeId();
	}

	/**
	 * Returns the start date of this watson incident audit.
	 *
	 * @return the start date of this watson incident audit
	 */
	@Override
	public Date getStartDate() {
		return _watsonIncidentAudit.getStartDate();
	}

	/**
	 * Returns the status of this watson incident audit.
	 *
	 * @return the status of this watson incident audit
	 */
	@Override
	public int getStatus() {
		return _watsonIncidentAudit.getStatus();
	}

	/**
	 * Returns the subtype watson list type ID of this watson incident audit.
	 *
	 * @return the subtype watson list type ID of this watson incident audit
	 */
	@Override
	public long getSubtypeWatsonListTypeId() {
		return _watsonIncidentAudit.getSubtypeWatsonListTypeId();
	}

	/**
	 * Returns the type watson list type ID of this watson incident audit.
	 *
	 * @return the type watson list type ID of this watson incident audit
	 */
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonIncidentAudit.getTypeWatsonListTypeId();
	}

	/**
	 * Returns the user ID of this watson incident audit.
	 *
	 * @return the user ID of this watson incident audit
	 */
	@Override
	public long getUserId() {
		return _watsonIncidentAudit.getUserId();
	}

	/**
	 * Returns the user name of this watson incident audit.
	 *
	 * @return the user name of this watson incident audit
	 */
	@Override
	public String getUserName() {
		return _watsonIncidentAudit.getUserName();
	}

	/**
	 * Returns the user uuid of this watson incident audit.
	 *
	 * @return the user uuid of this watson incident audit
	 */
	@Override
	public String getUserUuid() {
		return _watsonIncidentAudit.getUserUuid();
	}

	/**
	 * Returns the victim adult count of this watson incident audit.
	 *
	 * @return the victim adult count of this watson incident audit
	 */
	@Override
	public long getVictimAdultCount() {
		return _watsonIncidentAudit.getVictimAdultCount();
	}

	/**
	 * Returns the victim child count of this watson incident audit.
	 *
	 * @return the victim child count of this watson incident audit
	 */
	@Override
	public long getVictimChildCount() {
		return _watsonIncidentAudit.getVictimChildCount();
	}

	/**
	 * Returns the watson incident audit ID of this watson incident audit.
	 *
	 * @return the watson incident audit ID of this watson incident audit
	 */
	@Override
	public long getWatsonIncidentAuditId() {
		return _watsonIncidentAudit.getWatsonIncidentAuditId();
	}

	/**
	 * Returns the watson incident ID of this watson incident audit.
	 *
	 * @return the watson incident ID of this watson incident audit
	 */
	@Override
	public long getWatsonIncidentId() {
		return _watsonIncidentAudit.getWatsonIncidentId();
	}

	@Override
	public int hashCode() {
		return _watsonIncidentAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonIncidentAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonIncidentAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonIncidentAudit.isNew();
	}

	@Override
	public void persist() {
		_watsonIncidentAudit.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_watsonIncidentAudit.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_watsonIncidentAudit.prepareLocalizedFieldsForImport(
			defaultImportLocale);
	}

	/**
	 * Sets the audience adult count of this watson incident audit.
	 *
	 * @param audienceAdultCount the audience adult count of this watson incident audit
	 */
	@Override
	public void setAudienceAdultCount(long audienceAdultCount) {
		_watsonIncidentAudit.setAudienceAdultCount(audienceAdultCount);
	}

	/**
	 * Sets the audience child count of this watson incident audit.
	 *
	 * @param audienceChildCount the audience child count of this watson incident audit
	 */
	@Override
	public void setAudienceChildCount(long audienceChildCount) {
		_watsonIncidentAudit.setAudienceChildCount(audienceChildCount);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonIncidentAudit.setCachedModel(cachedModel);
	}

	/**
	 * Sets the company ID of this watson incident audit.
	 *
	 * @param companyId the company ID of this watson incident audit
	 */
	@Override
	public void setCompanyId(long companyId) {
		_watsonIncidentAudit.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this watson incident audit.
	 *
	 * @param createDate the create date of this watson incident audit
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_watsonIncidentAudit.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this watson incident audit.
	 *
	 * @param description the description of this watson incident audit
	 */
	@Override
	public void setDescription(String description) {
		_watsonIncidentAudit.setDescription(description);
	}

	/**
	 * Sets the localized description of this watson incident audit in the language.
	 *
	 * @param description the localized description of this watson incident audit
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		_watsonIncidentAudit.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this watson incident audit in the language, and sets the default locale.
	 *
	 * @param description the localized description of this watson incident audit
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescription(
		String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_watsonIncidentAudit.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_watsonIncidentAudit.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized descriptions of this watson incident audit from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this watson incident audit
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		_watsonIncidentAudit.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this watson incident audit from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this watson incident audit
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		_watsonIncidentAudit.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the end date of this watson incident audit.
	 *
	 * @param endDate the end date of this watson incident audit
	 */
	@Override
	public void setEndDate(Date endDate) {
		_watsonIncidentAudit.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_watsonIncidentAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonIncidentAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonIncidentAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the expenses of this watson incident audit.
	 *
	 * @param expenses the expenses of this watson incident audit
	 */
	@Override
	public void setExpenses(double expenses) {
		_watsonIncidentAudit.setExpenses(expenses);
	}

	/**
	 * Sets the external case ID of this watson incident audit.
	 *
	 * @param externalCaseId the external case ID of this watson incident audit
	 */
	@Override
	public void setExternalCaseId(String externalCaseId) {
		_watsonIncidentAudit.setExternalCaseId(externalCaseId);
	}

	/**
	 * Sets the external case watson list type ID of this watson incident audit.
	 *
	 * @param externalCaseWatsonListTypeId the external case watson list type ID of this watson incident audit
	 */
	@Override
	public void setExternalCaseWatsonListTypeId(
		long externalCaseWatsonListTypeId) {

		_watsonIncidentAudit.setExternalCaseWatsonListTypeId(
			externalCaseWatsonListTypeId);
	}

	/**
	 * Sets the group ID of this watson incident audit.
	 *
	 * @param groupId the group ID of this watson incident audit
	 */
	@Override
	public void setGroupId(long groupId) {
		_watsonIncidentAudit.setGroupId(groupId);
	}

	/**
	 * Sets the incident status of this watson incident audit.
	 *
	 * @param incidentStatus the incident status of this watson incident audit
	 */
	@Override
	public void setIncidentStatus(int incidentStatus) {
		_watsonIncidentAudit.setIncidentStatus(incidentStatus);
	}

	/**
	 * Sets the modified date of this watson incident audit.
	 *
	 * @param modifiedDate the modified date of this watson incident audit
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonIncidentAudit.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this watson incident audit.
	 *
	 * @param name the name of this watson incident audit
	 */
	@Override
	public void setName(String name) {
		_watsonIncidentAudit.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_watsonIncidentAudit.setNew(n);
	}

	/**
	 * Sets the primary key of this watson incident audit.
	 *
	 * @param primaryKey the primary key of this watson incident audit
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonIncidentAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonIncidentAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the report date of this watson incident audit.
	 *
	 * @param reportDate the report date of this watson incident audit
	 */
	@Override
	public void setReportDate(Date reportDate) {
		_watsonIncidentAudit.setReportDate(reportDate);
	}

	/**
	 * Sets the source watson list type ID of this watson incident audit.
	 *
	 * @param sourceWatsonListTypeId the source watson list type ID of this watson incident audit
	 */
	@Override
	public void setSourceWatsonListTypeId(long sourceWatsonListTypeId) {
		_watsonIncidentAudit.setSourceWatsonListTypeId(sourceWatsonListTypeId);
	}

	/**
	 * Sets the start date of this watson incident audit.
	 *
	 * @param startDate the start date of this watson incident audit
	 */
	@Override
	public void setStartDate(Date startDate) {
		_watsonIncidentAudit.setStartDate(startDate);
	}

	/**
	 * Sets the status of this watson incident audit.
	 *
	 * @param status the status of this watson incident audit
	 */
	@Override
	public void setStatus(int status) {
		_watsonIncidentAudit.setStatus(status);
	}

	/**
	 * Sets the subtype watson list type ID of this watson incident audit.
	 *
	 * @param subtypeWatsonListTypeId the subtype watson list type ID of this watson incident audit
	 */
	@Override
	public void setSubtypeWatsonListTypeId(long subtypeWatsonListTypeId) {
		_watsonIncidentAudit.setSubtypeWatsonListTypeId(
			subtypeWatsonListTypeId);
	}

	/**
	 * Sets the type watson list type ID of this watson incident audit.
	 *
	 * @param typeWatsonListTypeId the type watson list type ID of this watson incident audit
	 */
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonIncidentAudit.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	 * Sets the user ID of this watson incident audit.
	 *
	 * @param userId the user ID of this watson incident audit
	 */
	@Override
	public void setUserId(long userId) {
		_watsonIncidentAudit.setUserId(userId);
	}

	/**
	 * Sets the user name of this watson incident audit.
	 *
	 * @param userName the user name of this watson incident audit
	 */
	@Override
	public void setUserName(String userName) {
		_watsonIncidentAudit.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this watson incident audit.
	 *
	 * @param userUuid the user uuid of this watson incident audit
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_watsonIncidentAudit.setUserUuid(userUuid);
	}

	/**
	 * Sets the victim adult count of this watson incident audit.
	 *
	 * @param victimAdultCount the victim adult count of this watson incident audit
	 */
	@Override
	public void setVictimAdultCount(long victimAdultCount) {
		_watsonIncidentAudit.setVictimAdultCount(victimAdultCount);
	}

	/**
	 * Sets the victim child count of this watson incident audit.
	 *
	 * @param victimChildCount the victim child count of this watson incident audit
	 */
	@Override
	public void setVictimChildCount(long victimChildCount) {
		_watsonIncidentAudit.setVictimChildCount(victimChildCount);
	}

	/**
	 * Sets the watson incident audit ID of this watson incident audit.
	 *
	 * @param watsonIncidentAuditId the watson incident audit ID of this watson incident audit
	 */
	@Override
	public void setWatsonIncidentAuditId(long watsonIncidentAuditId) {
		_watsonIncidentAudit.setWatsonIncidentAuditId(watsonIncidentAuditId);
	}

	/**
	 * Sets the watson incident ID of this watson incident audit.
	 *
	 * @param watsonIncidentId the watson incident ID of this watson incident audit
	 */
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncidentAudit.setWatsonIncidentId(watsonIncidentId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonIncidentAudit>
		toCacheModel() {

		return _watsonIncidentAudit.toCacheModel();
	}

	@Override
	public WatsonIncidentAudit toEscapedModel() {
		return new WatsonIncidentAuditWrapper(
			_watsonIncidentAudit.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonIncidentAudit.toString();
	}

	@Override
	public WatsonIncidentAudit toUnescapedModel() {
		return new WatsonIncidentAuditWrapper(
			_watsonIncidentAudit.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonIncidentAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonIncidentAuditWrapper)) {
			return false;
		}

		WatsonIncidentAuditWrapper watsonIncidentAuditWrapper =
			(WatsonIncidentAuditWrapper)obj;

		if (Objects.equals(
				_watsonIncidentAudit,
				watsonIncidentAuditWrapper._watsonIncidentAudit)) {

			return true;
		}

		return false;
	}

	@Override
	public WatsonIncidentAudit getWrappedModel() {
		return _watsonIncidentAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonIncidentAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonIncidentAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonIncidentAudit.resetOriginalValues();
	}

	private final WatsonIncidentAudit _watsonIncidentAudit;

}