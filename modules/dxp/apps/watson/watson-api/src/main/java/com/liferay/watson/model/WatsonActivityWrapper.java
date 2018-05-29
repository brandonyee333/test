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
 * This class is a wrapper for {@link WatsonActivity}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonActivity
 * @generated
 */
@ProviderType
public class WatsonActivityWrapper implements WatsonActivity,
	ModelWrapper<WatsonActivity> {
	public WatsonActivityWrapper(WatsonActivity watsonActivity) {
		_watsonActivity = watsonActivity;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonActivity.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonActivity.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonActivityId", getWatsonActivityId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
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
		Long watsonActivityId = (Long)attributes.get("watsonActivityId");

		if (watsonActivityId != null) {
			setWatsonActivityId(watsonActivityId);
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
		return new WatsonActivityWrapper((WatsonActivity)_watsonActivity.clone());
	}

	@Override
	public int compareTo(WatsonActivity watsonActivity) {
		return _watsonActivity.compareTo(watsonActivity);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _watsonActivity.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this watson activity.
	*
	* @return the company ID of this watson activity
	*/
	@Override
	public long getCompanyId() {
		return _watsonActivity.getCompanyId();
	}

	/**
	* Returns the create date of this watson activity.
	*
	* @return the create date of this watson activity
	*/
	@Override
	public Date getCreateDate() {
		return _watsonActivity.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _watsonActivity.getDefaultLanguageId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonActivity.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson activity.
	*
	* @return the group ID of this watson activity
	*/
	@Override
	public long getGroupId() {
		return _watsonActivity.getGroupId();
	}

	/**
	* Returns the modified date of this watson activity.
	*
	* @return the modified date of this watson activity
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonActivity.getModifiedDate();
	}

	/**
	* Returns the narrative of this watson activity.
	*
	* @return the narrative of this watson activity
	*/
	@Override
	public String getNarrative() {
		return _watsonActivity.getNarrative();
	}

	/**
	* Returns the localized narrative of this watson activity in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized narrative of this watson activity
	*/
	@Override
	public String getNarrative(java.util.Locale locale) {
		return _watsonActivity.getNarrative(locale);
	}

	/**
	* Returns the localized narrative of this watson activity in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized narrative of this watson activity. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getNarrative(java.util.Locale locale, boolean useDefault) {
		return _watsonActivity.getNarrative(locale, useDefault);
	}

	/**
	* Returns the localized narrative of this watson activity in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized narrative of this watson activity
	*/
	@Override
	public String getNarrative(String languageId) {
		return _watsonActivity.getNarrative(languageId);
	}

	/**
	* Returns the localized narrative of this watson activity in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized narrative of this watson activity
	*/
	@Override
	public String getNarrative(String languageId, boolean useDefault) {
		return _watsonActivity.getNarrative(languageId, useDefault);
	}

	@Override
	public String getNarrativeCurrentLanguageId() {
		return _watsonActivity.getNarrativeCurrentLanguageId();
	}

	@Override
	public String getNarrativeCurrentValue() {
		return _watsonActivity.getNarrativeCurrentValue();
	}

	/**
	* Returns a map of the locales and localized narratives of this watson activity.
	*
	* @return the locales and localized narratives of this watson activity
	*/
	@Override
	public Map<java.util.Locale, String> getNarrativeMap() {
		return _watsonActivity.getNarrativeMap();
	}

	/**
	* Returns the primary key of this watson activity.
	*
	* @return the primary key of this watson activity
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonActivity.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonActivity.getPrimaryKeyObj();
	}

	/**
	* Returns the report date of this watson activity.
	*
	* @return the report date of this watson activity
	*/
	@Override
	public Date getReportDate() {
		return _watsonActivity.getReportDate();
	}

	/**
	* Returns the start date of this watson activity.
	*
	* @return the start date of this watson activity
	*/
	@Override
	public Date getStartDate() {
		return _watsonActivity.getStartDate();
	}

	/**
	* Returns the status of this watson activity.
	*
	* @return the status of this watson activity
	*/
	@Override
	public int getStatus() {
		return _watsonActivity.getStatus();
	}

	/**
	* Returns the subtype watson list type ID of this watson activity.
	*
	* @return the subtype watson list type ID of this watson activity
	*/
	@Override
	public long getSubtypeWatsonListTypeId() {
		return _watsonActivity.getSubtypeWatsonListTypeId();
	}

	/**
	* Returns the type watson list type ID of this watson activity.
	*
	* @return the type watson list type ID of this watson activity
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonActivity.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson activity.
	*
	* @return the user ID of this watson activity
	*/
	@Override
	public long getUserId() {
		return _watsonActivity.getUserId();
	}

	/**
	* Returns the user name of this watson activity.
	*
	* @return the user name of this watson activity
	*/
	@Override
	public String getUserName() {
		return _watsonActivity.getUserName();
	}

	/**
	* Returns the user uuid of this watson activity.
	*
	* @return the user uuid of this watson activity
	*/
	@Override
	public String getUserUuid() {
		return _watsonActivity.getUserUuid();
	}

	/**
	* Returns the watson activity ID of this watson activity.
	*
	* @return the watson activity ID of this watson activity
	*/
	@Override
	public long getWatsonActivityId() {
		return _watsonActivity.getWatsonActivityId();
	}

	/**
	* Returns the watson incident ID of this watson activity.
	*
	* @return the watson incident ID of this watson activity
	*/
	@Override
	public long getWatsonIncidentId() {
		return _watsonActivity.getWatsonIncidentId();
	}

	@Override
	public int hashCode() {
		return _watsonActivity.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonActivity.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonActivity.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonActivity.isNew();
	}

	@Override
	public void persist() {
		_watsonActivity.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonActivity.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonActivity.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonActivity.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson activity.
	*
	* @param companyId the company ID of this watson activity
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonActivity.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson activity.
	*
	* @param createDate the create date of this watson activity
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonActivity.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonActivity.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonActivity.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonActivity.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson activity.
	*
	* @param groupId the group ID of this watson activity
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonActivity.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this watson activity.
	*
	* @param modifiedDate the modified date of this watson activity
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonActivity.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the narrative of this watson activity.
	*
	* @param narrative the narrative of this watson activity
	*/
	@Override
	public void setNarrative(String narrative) {
		_watsonActivity.setNarrative(narrative);
	}

	/**
	* Sets the localized narrative of this watson activity in the language.
	*
	* @param narrative the localized narrative of this watson activity
	* @param locale the locale of the language
	*/
	@Override
	public void setNarrative(String narrative, java.util.Locale locale) {
		_watsonActivity.setNarrative(narrative, locale);
	}

	/**
	* Sets the localized narrative of this watson activity in the language, and sets the default locale.
	*
	* @param narrative the localized narrative of this watson activity
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNarrative(String narrative, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonActivity.setNarrative(narrative, locale, defaultLocale);
	}

	@Override
	public void setNarrativeCurrentLanguageId(String languageId) {
		_watsonActivity.setNarrativeCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized narratives of this watson activity from the map of locales and localized narratives.
	*
	* @param narrativeMap the locales and localized narratives of this watson activity
	*/
	@Override
	public void setNarrativeMap(Map<java.util.Locale, String> narrativeMap) {
		_watsonActivity.setNarrativeMap(narrativeMap);
	}

	/**
	* Sets the localized narratives of this watson activity from the map of locales and localized narratives, and sets the default locale.
	*
	* @param narrativeMap the locales and localized narratives of this watson activity
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNarrativeMap(Map<java.util.Locale, String> narrativeMap,
		java.util.Locale defaultLocale) {
		_watsonActivity.setNarrativeMap(narrativeMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_watsonActivity.setNew(n);
	}

	/**
	* Sets the primary key of this watson activity.
	*
	* @param primaryKey the primary key of this watson activity
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonActivity.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonActivity.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the report date of this watson activity.
	*
	* @param reportDate the report date of this watson activity
	*/
	@Override
	public void setReportDate(Date reportDate) {
		_watsonActivity.setReportDate(reportDate);
	}

	/**
	* Sets the start date of this watson activity.
	*
	* @param startDate the start date of this watson activity
	*/
	@Override
	public void setStartDate(Date startDate) {
		_watsonActivity.setStartDate(startDate);
	}

	/**
	* Sets the status of this watson activity.
	*
	* @param status the status of this watson activity
	*/
	@Override
	public void setStatus(int status) {
		_watsonActivity.setStatus(status);
	}

	/**
	* Sets the subtype watson list type ID of this watson activity.
	*
	* @param subtypeWatsonListTypeId the subtype watson list type ID of this watson activity
	*/
	@Override
	public void setSubtypeWatsonListTypeId(long subtypeWatsonListTypeId) {
		_watsonActivity.setSubtypeWatsonListTypeId(subtypeWatsonListTypeId);
	}

	/**
	* Sets the type watson list type ID of this watson activity.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson activity
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonActivity.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson activity.
	*
	* @param userId the user ID of this watson activity
	*/
	@Override
	public void setUserId(long userId) {
		_watsonActivity.setUserId(userId);
	}

	/**
	* Sets the user name of this watson activity.
	*
	* @param userName the user name of this watson activity
	*/
	@Override
	public void setUserName(String userName) {
		_watsonActivity.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson activity.
	*
	* @param userUuid the user uuid of this watson activity
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_watsonActivity.setUserUuid(userUuid);
	}

	/**
	* Sets the watson activity ID of this watson activity.
	*
	* @param watsonActivityId the watson activity ID of this watson activity
	*/
	@Override
	public void setWatsonActivityId(long watsonActivityId) {
		_watsonActivity.setWatsonActivityId(watsonActivityId);
	}

	/**
	* Sets the watson incident ID of this watson activity.
	*
	* @param watsonIncidentId the watson incident ID of this watson activity
	*/
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonActivity.setWatsonIncidentId(watsonIncidentId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonActivity> toCacheModel() {
		return _watsonActivity.toCacheModel();
	}

	@Override
	public WatsonActivity toEscapedModel() {
		return new WatsonActivityWrapper(_watsonActivity.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonActivity.toString();
	}

	@Override
	public WatsonActivity toUnescapedModel() {
		return new WatsonActivityWrapper(_watsonActivity.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonActivity.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonActivityWrapper)) {
			return false;
		}

		WatsonActivityWrapper watsonActivityWrapper = (WatsonActivityWrapper)obj;

		if (Objects.equals(_watsonActivity,
					watsonActivityWrapper._watsonActivity)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonActivity getWrappedModel() {
		return _watsonActivity;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonActivity.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonActivity.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonActivity.resetOriginalValues();
	}

	private final WatsonActivity _watsonActivity;
}