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
 * This class is a wrapper for {@link WatsonActivityAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonActivityAudit
 * @generated
 */
@ProviderType
public class WatsonActivityAuditWrapper implements WatsonActivityAudit,
	ModelWrapper<WatsonActivityAudit> {
	public WatsonActivityAuditWrapper(WatsonActivityAudit watsonActivityAudit) {
		_watsonActivityAudit = watsonActivityAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonActivityAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonActivityAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonActivityAuditId", getWatsonActivityAuditId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("watsonActivityId", getWatsonActivityId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("subtypeWatsonListTypeId", getSubtypeWatsonListTypeId());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("narrative", getNarrative());
		attributes.put("reportDate", getReportDate());
		attributes.put("startDate", getStartDate());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonActivityAuditId = (Long)attributes.get(
				"watsonActivityAuditId");

		if (watsonActivityAuditId != null) {
			setWatsonActivityAuditId(watsonActivityAuditId);
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

		Long watsonActivityId = (Long)attributes.get("watsonActivityId");

		if (watsonActivityId != null) {
			setWatsonActivityId(watsonActivityId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long subtypeWatsonListTypeId = (Long)attributes.get(
				"subtypeWatsonListTypeId");

		if (subtypeWatsonListTypeId != null) {
			setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
		}

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		String narrative = (String)attributes.get("narrative");

		if (narrative != null) {
			setNarrative(narrative);
		}

		Date reportDate = (Date)attributes.get("reportDate");

		if (reportDate != null) {
			setReportDate(reportDate);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new WatsonActivityAuditWrapper((WatsonActivityAudit)_watsonActivityAudit.clone());
	}

	@Override
	public int compareTo(WatsonActivityAudit watsonActivityAudit) {
		return _watsonActivityAudit.compareTo(watsonActivityAudit);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _watsonActivityAudit.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this watson activity audit.
	*
	* @return the company ID of this watson activity audit
	*/
	@Override
	public long getCompanyId() {
		return _watsonActivityAudit.getCompanyId();
	}

	/**
	* Returns the create date of this watson activity audit.
	*
	* @return the create date of this watson activity audit
	*/
	@Override
	public Date getCreateDate() {
		return _watsonActivityAudit.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _watsonActivityAudit.getDefaultLanguageId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonActivityAudit.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson activity audit.
	*
	* @return the group ID of this watson activity audit
	*/
	@Override
	public long getGroupId() {
		return _watsonActivityAudit.getGroupId();
	}

	/**
	* Returns the modified date of this watson activity audit.
	*
	* @return the modified date of this watson activity audit
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonActivityAudit.getModifiedDate();
	}

	/**
	* Returns the narrative of this watson activity audit.
	*
	* @return the narrative of this watson activity audit
	*/
	@Override
	public String getNarrative() {
		return _watsonActivityAudit.getNarrative();
	}

	/**
	* Returns the localized narrative of this watson activity audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized narrative of this watson activity audit
	*/
	@Override
	public String getNarrative(java.util.Locale locale) {
		return _watsonActivityAudit.getNarrative(locale);
	}

	/**
	* Returns the localized narrative of this watson activity audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized narrative of this watson activity audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getNarrative(java.util.Locale locale, boolean useDefault) {
		return _watsonActivityAudit.getNarrative(locale, useDefault);
	}

	/**
	* Returns the localized narrative of this watson activity audit in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized narrative of this watson activity audit
	*/
	@Override
	public String getNarrative(String languageId) {
		return _watsonActivityAudit.getNarrative(languageId);
	}

	/**
	* Returns the localized narrative of this watson activity audit in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized narrative of this watson activity audit
	*/
	@Override
	public String getNarrative(String languageId, boolean useDefault) {
		return _watsonActivityAudit.getNarrative(languageId, useDefault);
	}

	@Override
	public String getNarrativeCurrentLanguageId() {
		return _watsonActivityAudit.getNarrativeCurrentLanguageId();
	}

	@Override
	public String getNarrativeCurrentValue() {
		return _watsonActivityAudit.getNarrativeCurrentValue();
	}

	/**
	* Returns a map of the locales and localized narratives of this watson activity audit.
	*
	* @return the locales and localized narratives of this watson activity audit
	*/
	@Override
	public Map<java.util.Locale, String> getNarrativeMap() {
		return _watsonActivityAudit.getNarrativeMap();
	}

	/**
	* Returns the primary key of this watson activity audit.
	*
	* @return the primary key of this watson activity audit
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonActivityAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonActivityAudit.getPrimaryKeyObj();
	}

	/**
	* Returns the report date of this watson activity audit.
	*
	* @return the report date of this watson activity audit
	*/
	@Override
	public Date getReportDate() {
		return _watsonActivityAudit.getReportDate();
	}

	/**
	* Returns the start date of this watson activity audit.
	*
	* @return the start date of this watson activity audit
	*/
	@Override
	public Date getStartDate() {
		return _watsonActivityAudit.getStartDate();
	}

	/**
	* Returns the status of this watson activity audit.
	*
	* @return the status of this watson activity audit
	*/
	@Override
	public int getStatus() {
		return _watsonActivityAudit.getStatus();
	}

	/**
	* Returns the subtype watson list type ID of this watson activity audit.
	*
	* @return the subtype watson list type ID of this watson activity audit
	*/
	@Override
	public long getSubtypeWatsonListTypeId() {
		return _watsonActivityAudit.getSubtypeWatsonListTypeId();
	}

	/**
	* Returns the type watson list type ID of this watson activity audit.
	*
	* @return the type watson list type ID of this watson activity audit
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonActivityAudit.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson activity audit.
	*
	* @return the user ID of this watson activity audit
	*/
	@Override
	public long getUserId() {
		return _watsonActivityAudit.getUserId();
	}

	/**
	* Returns the user name of this watson activity audit.
	*
	* @return the user name of this watson activity audit
	*/
	@Override
	public String getUserName() {
		return _watsonActivityAudit.getUserName();
	}

	/**
	* Returns the user uuid of this watson activity audit.
	*
	* @return the user uuid of this watson activity audit
	*/
	@Override
	public String getUserUuid() {
		return _watsonActivityAudit.getUserUuid();
	}

	/**
	* Returns the watson activity audit ID of this watson activity audit.
	*
	* @return the watson activity audit ID of this watson activity audit
	*/
	@Override
	public long getWatsonActivityAuditId() {
		return _watsonActivityAudit.getWatsonActivityAuditId();
	}

	/**
	* Returns the watson activity ID of this watson activity audit.
	*
	* @return the watson activity ID of this watson activity audit
	*/
	@Override
	public long getWatsonActivityId() {
		return _watsonActivityAudit.getWatsonActivityId();
	}

	/**
	* Returns the watson incident ID of this watson activity audit.
	*
	* @return the watson incident ID of this watson activity audit
	*/
	@Override
	public long getWatsonIncidentId() {
		return _watsonActivityAudit.getWatsonIncidentId();
	}

	@Override
	public int hashCode() {
		return _watsonActivityAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonActivityAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonActivityAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonActivityAudit.isNew();
	}

	@Override
	public void persist() {
		_watsonActivityAudit.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonActivityAudit.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonActivityAudit.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonActivityAudit.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson activity audit.
	*
	* @param companyId the company ID of this watson activity audit
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonActivityAudit.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson activity audit.
	*
	* @param createDate the create date of this watson activity audit
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonActivityAudit.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonActivityAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonActivityAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonActivityAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson activity audit.
	*
	* @param groupId the group ID of this watson activity audit
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonActivityAudit.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this watson activity audit.
	*
	* @param modifiedDate the modified date of this watson activity audit
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonActivityAudit.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the narrative of this watson activity audit.
	*
	* @param narrative the narrative of this watson activity audit
	*/
	@Override
	public void setNarrative(String narrative) {
		_watsonActivityAudit.setNarrative(narrative);
	}

	/**
	* Sets the localized narrative of this watson activity audit in the language.
	*
	* @param narrative the localized narrative of this watson activity audit
	* @param locale the locale of the language
	*/
	@Override
	public void setNarrative(String narrative, java.util.Locale locale) {
		_watsonActivityAudit.setNarrative(narrative, locale);
	}

	/**
	* Sets the localized narrative of this watson activity audit in the language, and sets the default locale.
	*
	* @param narrative the localized narrative of this watson activity audit
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNarrative(String narrative, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonActivityAudit.setNarrative(narrative, locale, defaultLocale);
	}

	@Override
	public void setNarrativeCurrentLanguageId(String languageId) {
		_watsonActivityAudit.setNarrativeCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized narratives of this watson activity audit from the map of locales and localized narratives.
	*
	* @param narrativeMap the locales and localized narratives of this watson activity audit
	*/
	@Override
	public void setNarrativeMap(Map<java.util.Locale, String> narrativeMap) {
		_watsonActivityAudit.setNarrativeMap(narrativeMap);
	}

	/**
	* Sets the localized narratives of this watson activity audit from the map of locales and localized narratives, and sets the default locale.
	*
	* @param narrativeMap the locales and localized narratives of this watson activity audit
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNarrativeMap(Map<java.util.Locale, String> narrativeMap,
		java.util.Locale defaultLocale) {
		_watsonActivityAudit.setNarrativeMap(narrativeMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_watsonActivityAudit.setNew(n);
	}

	/**
	* Sets the primary key of this watson activity audit.
	*
	* @param primaryKey the primary key of this watson activity audit
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonActivityAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonActivityAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the report date of this watson activity audit.
	*
	* @param reportDate the report date of this watson activity audit
	*/
	@Override
	public void setReportDate(Date reportDate) {
		_watsonActivityAudit.setReportDate(reportDate);
	}

	/**
	* Sets the start date of this watson activity audit.
	*
	* @param startDate the start date of this watson activity audit
	*/
	@Override
	public void setStartDate(Date startDate) {
		_watsonActivityAudit.setStartDate(startDate);
	}

	/**
	* Sets the status of this watson activity audit.
	*
	* @param status the status of this watson activity audit
	*/
	@Override
	public void setStatus(int status) {
		_watsonActivityAudit.setStatus(status);
	}

	/**
	* Sets the subtype watson list type ID of this watson activity audit.
	*
	* @param subtypeWatsonListTypeId the subtype watson list type ID of this watson activity audit
	*/
	@Override
	public void setSubtypeWatsonListTypeId(long subtypeWatsonListTypeId) {
		_watsonActivityAudit.setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
	}

	/**
	* Sets the type watson list type ID of this watson activity audit.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson activity audit
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonActivityAudit.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson activity audit.
	*
	* @param userId the user ID of this watson activity audit
	*/
	@Override
	public void setUserId(long userId) {
		_watsonActivityAudit.setUserId(userId);
	}

	/**
	* Sets the user name of this watson activity audit.
	*
	* @param userName the user name of this watson activity audit
	*/
	@Override
	public void setUserName(String userName) {
		_watsonActivityAudit.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson activity audit.
	*
	* @param userUuid the user uuid of this watson activity audit
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_watsonActivityAudit.setUserUuid(userUuid);
	}

	/**
	* Sets the watson activity audit ID of this watson activity audit.
	*
	* @param watsonActivityAuditId the watson activity audit ID of this watson activity audit
	*/
	@Override
	public void setWatsonActivityAuditId(long watsonActivityAuditId) {
		_watsonActivityAudit.setWatsonActivityAuditId(watsonActivityAuditId);
	}

	/**
	* Sets the watson activity ID of this watson activity audit.
	*
	* @param watsonActivityId the watson activity ID of this watson activity audit
	*/
	@Override
	public void setWatsonActivityId(long watsonActivityId) {
		_watsonActivityAudit.setWatsonActivityId(watsonActivityId);
	}

	/**
	* Sets the watson incident ID of this watson activity audit.
	*
	* @param watsonIncidentId the watson incident ID of this watson activity audit
	*/
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonActivityAudit.setWatsonIncidentId(watsonIncidentId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonActivityAudit> toCacheModel() {
		return _watsonActivityAudit.toCacheModel();
	}

	@Override
	public WatsonActivityAudit toEscapedModel() {
		return new WatsonActivityAuditWrapper(_watsonActivityAudit.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonActivityAudit.toString();
	}

	@Override
	public WatsonActivityAudit toUnescapedModel() {
		return new WatsonActivityAuditWrapper(_watsonActivityAudit.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonActivityAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonActivityAuditWrapper)) {
			return false;
		}

		WatsonActivityAuditWrapper watsonActivityAuditWrapper = (WatsonActivityAuditWrapper)obj;

		if (Objects.equals(_watsonActivityAudit,
					watsonActivityAuditWrapper._watsonActivityAudit)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonActivityAudit getWrappedModel() {
		return _watsonActivityAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonActivityAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonActivityAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonActivityAudit.resetOriginalValues();
	}

	private final WatsonActivityAudit _watsonActivityAudit;
}