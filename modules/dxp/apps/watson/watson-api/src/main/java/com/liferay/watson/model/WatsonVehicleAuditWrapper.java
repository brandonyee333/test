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
 * This class is a wrapper for {@link WatsonVehicleAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonVehicleAudit
 * @generated
 */
@ProviderType
public class WatsonVehicleAuditWrapper
	implements WatsonVehicleAudit, ModelWrapper<WatsonVehicleAudit> {

	public WatsonVehicleAuditWrapper(WatsonVehicleAudit watsonVehicleAudit) {
		_watsonVehicleAudit = watsonVehicleAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonVehicleAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonVehicleAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonVehicleAuditId", getWatsonVehicleAuditId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("colorWatsonListTypeId", getColorWatsonListTypeId());
		attributes.put("makeWatsonListTypeId", getMakeWatsonListTypeId());
		attributes.put("modelWatsonListTypeId", getModelWatsonListTypeId());
		attributes.put("originalWatsonVehicleId", getOriginalWatsonVehicleId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("yearWatsonListTypeId", getYearWatsonListTypeId());
		attributes.put("watsonIncidentId", getWatsonIncidentId());
		attributes.put("watsonVehicleId", getWatsonVehicleId());
		attributes.put("year", getYear());
		attributes.put("description", getDescription());
		attributes.put("imagePayload", getImagePayload());
		attributes.put("licensePlate", getLicensePlate());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonVehicleAuditId = (Long)attributes.get(
			"watsonVehicleAuditId");

		if (watsonVehicleAuditId != null) {
			setWatsonVehicleAuditId(watsonVehicleAuditId);
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

		Long colorWatsonListTypeId = (Long)attributes.get(
			"colorWatsonListTypeId");

		if (colorWatsonListTypeId != null) {
			setColorWatsonListTypeId(colorWatsonListTypeId);
		}

		Long makeWatsonListTypeId = (Long)attributes.get(
			"makeWatsonListTypeId");

		if (makeWatsonListTypeId != null) {
			setMakeWatsonListTypeId(makeWatsonListTypeId);
		}

		Long modelWatsonListTypeId = (Long)attributes.get(
			"modelWatsonListTypeId");

		if (modelWatsonListTypeId != null) {
			setModelWatsonListTypeId(modelWatsonListTypeId);
		}

		Long originalWatsonVehicleId = (Long)attributes.get(
			"originalWatsonVehicleId");

		if (originalWatsonVehicleId != null) {
			setOriginalWatsonVehicleId(originalWatsonVehicleId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get(
			"typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Long yearWatsonListTypeId = (Long)attributes.get(
			"yearWatsonListTypeId");

		if (yearWatsonListTypeId != null) {
			setYearWatsonListTypeId(yearWatsonListTypeId);
		}

		Long watsonIncidentId = (Long)attributes.get("watsonIncidentId");

		if (watsonIncidentId != null) {
			setWatsonIncidentId(watsonIncidentId);
		}

		Long watsonVehicleId = (Long)attributes.get("watsonVehicleId");

		if (watsonVehicleId != null) {
			setWatsonVehicleId(watsonVehicleId);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String imagePayload = (String)attributes.get("imagePayload");

		if (imagePayload != null) {
			setImagePayload(imagePayload);
		}

		String licensePlate = (String)attributes.get("licensePlate");

		if (licensePlate != null) {
			setLicensePlate(licensePlate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new WatsonVehicleAuditWrapper(
			(WatsonVehicleAudit)_watsonVehicleAudit.clone());
	}

	@Override
	public int compareTo(WatsonVehicleAudit watsonVehicleAudit) {
		return _watsonVehicleAudit.compareTo(watsonVehicleAudit);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _watsonVehicleAudit.getAvailableLanguageIds();
	}

	/**
	 * Returns the color watson list type ID of this watson vehicle audit.
	 *
	 * @return the color watson list type ID of this watson vehicle audit
	 */
	@Override
	public long getColorWatsonListTypeId() {
		return _watsonVehicleAudit.getColorWatsonListTypeId();
	}

	/**
	 * Returns the company ID of this watson vehicle audit.
	 *
	 * @return the company ID of this watson vehicle audit
	 */
	@Override
	public long getCompanyId() {
		return _watsonVehicleAudit.getCompanyId();
	}

	/**
	 * Returns the create date of this watson vehicle audit.
	 *
	 * @return the create date of this watson vehicle audit
	 */
	@Override
	public Date getCreateDate() {
		return _watsonVehicleAudit.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _watsonVehicleAudit.getDefaultLanguageId();
	}

	/**
	 * Returns the description of this watson vehicle audit.
	 *
	 * @return the description of this watson vehicle audit
	 */
	@Override
	public String getDescription() {
		return _watsonVehicleAudit.getDescription();
	}

	/**
	 * Returns the localized description of this watson vehicle audit in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this watson vehicle audit
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return _watsonVehicleAudit.getDescription(locale);
	}

	/**
	 * Returns the localized description of this watson vehicle audit in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this watson vehicle audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return _watsonVehicleAudit.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this watson vehicle audit in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this watson vehicle audit
	 */
	@Override
	public String getDescription(String languageId) {
		return _watsonVehicleAudit.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this watson vehicle audit in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this watson vehicle audit
	 */
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return _watsonVehicleAudit.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _watsonVehicleAudit.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return _watsonVehicleAudit.getDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized descriptions of this watson vehicle audit.
	 *
	 * @return the locales and localized descriptions of this watson vehicle audit
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return _watsonVehicleAudit.getDescriptionMap();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonVehicleAudit.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this watson vehicle audit.
	 *
	 * @return the group ID of this watson vehicle audit
	 */
	@Override
	public long getGroupId() {
		return _watsonVehicleAudit.getGroupId();
	}

	/**
	 * Returns the image payload of this watson vehicle audit.
	 *
	 * @return the image payload of this watson vehicle audit
	 */
	@Override
	public String getImagePayload() {
		return _watsonVehicleAudit.getImagePayload();
	}

	/**
	 * Returns the license plate of this watson vehicle audit.
	 *
	 * @return the license plate of this watson vehicle audit
	 */
	@Override
	public String getLicensePlate() {
		return _watsonVehicleAudit.getLicensePlate();
	}

	/**
	 * Returns the make watson list type ID of this watson vehicle audit.
	 *
	 * @return the make watson list type ID of this watson vehicle audit
	 */
	@Override
	public long getMakeWatsonListTypeId() {
		return _watsonVehicleAudit.getMakeWatsonListTypeId();
	}

	/**
	 * Returns the model watson list type ID of this watson vehicle audit.
	 *
	 * @return the model watson list type ID of this watson vehicle audit
	 */
	@Override
	public long getModelWatsonListTypeId() {
		return _watsonVehicleAudit.getModelWatsonListTypeId();
	}

	/**
	 * Returns the modified date of this watson vehicle audit.
	 *
	 * @return the modified date of this watson vehicle audit
	 */
	@Override
	public Date getModifiedDate() {
		return _watsonVehicleAudit.getModifiedDate();
	}

	/**
	 * Returns the original watson vehicle ID of this watson vehicle audit.
	 *
	 * @return the original watson vehicle ID of this watson vehicle audit
	 */
	@Override
	public long getOriginalWatsonVehicleId() {
		return _watsonVehicleAudit.getOriginalWatsonVehicleId();
	}

	/**
	 * Returns the primary key of this watson vehicle audit.
	 *
	 * @return the primary key of this watson vehicle audit
	 */
	@Override
	public long getPrimaryKey() {
		return _watsonVehicleAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonVehicleAudit.getPrimaryKeyObj();
	}

	/**
	 * Returns the status of this watson vehicle audit.
	 *
	 * @return the status of this watson vehicle audit
	 */
	@Override
	public int getStatus() {
		return _watsonVehicleAudit.getStatus();
	}

	/**
	 * Returns the type watson list type ID of this watson vehicle audit.
	 *
	 * @return the type watson list type ID of this watson vehicle audit
	 */
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonVehicleAudit.getTypeWatsonListTypeId();
	}

	/**
	 * Returns the user ID of this watson vehicle audit.
	 *
	 * @return the user ID of this watson vehicle audit
	 */
	@Override
	public long getUserId() {
		return _watsonVehicleAudit.getUserId();
	}

	/**
	 * Returns the user name of this watson vehicle audit.
	 *
	 * @return the user name of this watson vehicle audit
	 */
	@Override
	public String getUserName() {
		return _watsonVehicleAudit.getUserName();
	}

	/**
	 * Returns the user uuid of this watson vehicle audit.
	 *
	 * @return the user uuid of this watson vehicle audit
	 */
	@Override
	public String getUserUuid() {
		return _watsonVehicleAudit.getUserUuid();
	}

	/**
	 * Returns the watson incident ID of this watson vehicle audit.
	 *
	 * @return the watson incident ID of this watson vehicle audit
	 */
	@Override
	public long getWatsonIncidentId() {
		return _watsonVehicleAudit.getWatsonIncidentId();
	}

	/**
	 * Returns the watson vehicle audit ID of this watson vehicle audit.
	 *
	 * @return the watson vehicle audit ID of this watson vehicle audit
	 */
	@Override
	public long getWatsonVehicleAuditId() {
		return _watsonVehicleAudit.getWatsonVehicleAuditId();
	}

	/**
	 * Returns the watson vehicle ID of this watson vehicle audit.
	 *
	 * @return the watson vehicle ID of this watson vehicle audit
	 */
	@Override
	public long getWatsonVehicleId() {
		return _watsonVehicleAudit.getWatsonVehicleId();
	}

	/**
	 * Returns the year of this watson vehicle audit.
	 *
	 * @return the year of this watson vehicle audit
	 */
	@Override
	public int getYear() {
		return _watsonVehicleAudit.getYear();
	}

	/**
	 * Returns the year watson list type ID of this watson vehicle audit.
	 *
	 * @return the year watson list type ID of this watson vehicle audit
	 */
	@Override
	public long getYearWatsonListTypeId() {
		return _watsonVehicleAudit.getYearWatsonListTypeId();
	}

	@Override
	public int hashCode() {
		return _watsonVehicleAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonVehicleAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonVehicleAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonVehicleAudit.isNew();
	}

	@Override
	public void persist() {
		_watsonVehicleAudit.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_watsonVehicleAudit.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_watsonVehicleAudit.prepareLocalizedFieldsForImport(
			defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonVehicleAudit.setCachedModel(cachedModel);
	}

	/**
	 * Sets the color watson list type ID of this watson vehicle audit.
	 *
	 * @param colorWatsonListTypeId the color watson list type ID of this watson vehicle audit
	 */
	@Override
	public void setColorWatsonListTypeId(long colorWatsonListTypeId) {
		_watsonVehicleAudit.setColorWatsonListTypeId(colorWatsonListTypeId);
	}

	/**
	 * Sets the company ID of this watson vehicle audit.
	 *
	 * @param companyId the company ID of this watson vehicle audit
	 */
	@Override
	public void setCompanyId(long companyId) {
		_watsonVehicleAudit.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this watson vehicle audit.
	 *
	 * @param createDate the create date of this watson vehicle audit
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_watsonVehicleAudit.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this watson vehicle audit.
	 *
	 * @param description the description of this watson vehicle audit
	 */
	@Override
	public void setDescription(String description) {
		_watsonVehicleAudit.setDescription(description);
	}

	/**
	 * Sets the localized description of this watson vehicle audit in the language.
	 *
	 * @param description the localized description of this watson vehicle audit
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		_watsonVehicleAudit.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this watson vehicle audit in the language, and sets the default locale.
	 *
	 * @param description the localized description of this watson vehicle audit
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescription(
		String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		_watsonVehicleAudit.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_watsonVehicleAudit.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized descriptions of this watson vehicle audit from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this watson vehicle audit
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		_watsonVehicleAudit.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this watson vehicle audit from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this watson vehicle audit
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		_watsonVehicleAudit.setDescriptionMap(descriptionMap, defaultLocale);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_watsonVehicleAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonVehicleAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonVehicleAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this watson vehicle audit.
	 *
	 * @param groupId the group ID of this watson vehicle audit
	 */
	@Override
	public void setGroupId(long groupId) {
		_watsonVehicleAudit.setGroupId(groupId);
	}

	/**
	 * Sets the image payload of this watson vehicle audit.
	 *
	 * @param imagePayload the image payload of this watson vehicle audit
	 */
	@Override
	public void setImagePayload(String imagePayload) {
		_watsonVehicleAudit.setImagePayload(imagePayload);
	}

	/**
	 * Sets the license plate of this watson vehicle audit.
	 *
	 * @param licensePlate the license plate of this watson vehicle audit
	 */
	@Override
	public void setLicensePlate(String licensePlate) {
		_watsonVehicleAudit.setLicensePlate(licensePlate);
	}

	/**
	 * Sets the make watson list type ID of this watson vehicle audit.
	 *
	 * @param makeWatsonListTypeId the make watson list type ID of this watson vehicle audit
	 */
	@Override
	public void setMakeWatsonListTypeId(long makeWatsonListTypeId) {
		_watsonVehicleAudit.setMakeWatsonListTypeId(makeWatsonListTypeId);
	}

	/**
	 * Sets the model watson list type ID of this watson vehicle audit.
	 *
	 * @param modelWatsonListTypeId the model watson list type ID of this watson vehicle audit
	 */
	@Override
	public void setModelWatsonListTypeId(long modelWatsonListTypeId) {
		_watsonVehicleAudit.setModelWatsonListTypeId(modelWatsonListTypeId);
	}

	/**
	 * Sets the modified date of this watson vehicle audit.
	 *
	 * @param modifiedDate the modified date of this watson vehicle audit
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonVehicleAudit.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonVehicleAudit.setNew(n);
	}

	/**
	 * Sets the original watson vehicle ID of this watson vehicle audit.
	 *
	 * @param originalWatsonVehicleId the original watson vehicle ID of this watson vehicle audit
	 */
	@Override
	public void setOriginalWatsonVehicleId(long originalWatsonVehicleId) {
		_watsonVehicleAudit.setOriginalWatsonVehicleId(originalWatsonVehicleId);
	}

	/**
	 * Sets the primary key of this watson vehicle audit.
	 *
	 * @param primaryKey the primary key of this watson vehicle audit
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonVehicleAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonVehicleAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the status of this watson vehicle audit.
	 *
	 * @param status the status of this watson vehicle audit
	 */
	@Override
	public void setStatus(int status) {
		_watsonVehicleAudit.setStatus(status);
	}

	/**
	 * Sets the type watson list type ID of this watson vehicle audit.
	 *
	 * @param typeWatsonListTypeId the type watson list type ID of this watson vehicle audit
	 */
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonVehicleAudit.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	 * Sets the user ID of this watson vehicle audit.
	 *
	 * @param userId the user ID of this watson vehicle audit
	 */
	@Override
	public void setUserId(long userId) {
		_watsonVehicleAudit.setUserId(userId);
	}

	/**
	 * Sets the user name of this watson vehicle audit.
	 *
	 * @param userName the user name of this watson vehicle audit
	 */
	@Override
	public void setUserName(String userName) {
		_watsonVehicleAudit.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this watson vehicle audit.
	 *
	 * @param userUuid the user uuid of this watson vehicle audit
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_watsonVehicleAudit.setUserUuid(userUuid);
	}

	/**
	 * Sets the watson incident ID of this watson vehicle audit.
	 *
	 * @param watsonIncidentId the watson incident ID of this watson vehicle audit
	 */
	@Override
	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonVehicleAudit.setWatsonIncidentId(watsonIncidentId);
	}

	/**
	 * Sets the watson vehicle audit ID of this watson vehicle audit.
	 *
	 * @param watsonVehicleAuditId the watson vehicle audit ID of this watson vehicle audit
	 */
	@Override
	public void setWatsonVehicleAuditId(long watsonVehicleAuditId) {
		_watsonVehicleAudit.setWatsonVehicleAuditId(watsonVehicleAuditId);
	}

	/**
	 * Sets the watson vehicle ID of this watson vehicle audit.
	 *
	 * @param watsonVehicleId the watson vehicle ID of this watson vehicle audit
	 */
	@Override
	public void setWatsonVehicleId(long watsonVehicleId) {
		_watsonVehicleAudit.setWatsonVehicleId(watsonVehicleId);
	}

	/**
	 * Sets the year of this watson vehicle audit.
	 *
	 * @param year the year of this watson vehicle audit
	 */
	@Override
	public void setYear(int year) {
		_watsonVehicleAudit.setYear(year);
	}

	/**
	 * Sets the year watson list type ID of this watson vehicle audit.
	 *
	 * @param yearWatsonListTypeId the year watson list type ID of this watson vehicle audit
	 */
	@Override
	public void setYearWatsonListTypeId(long yearWatsonListTypeId) {
		_watsonVehicleAudit.setYearWatsonListTypeId(yearWatsonListTypeId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonVehicleAudit>
		toCacheModel() {

		return _watsonVehicleAudit.toCacheModel();
	}

	@Override
	public WatsonVehicleAudit toEscapedModel() {
		return new WatsonVehicleAuditWrapper(
			_watsonVehicleAudit.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonVehicleAudit.toString();
	}

	@Override
	public WatsonVehicleAudit toUnescapedModel() {
		return new WatsonVehicleAuditWrapper(
			_watsonVehicleAudit.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonVehicleAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonVehicleAuditWrapper)) {
			return false;
		}

		WatsonVehicleAuditWrapper watsonVehicleAuditWrapper =
			(WatsonVehicleAuditWrapper)obj;

		if (Objects.equals(
				_watsonVehicleAudit,
				watsonVehicleAuditWrapper._watsonVehicleAudit)) {

			return true;
		}

		return false;
	}

	@Override
	public WatsonVehicleAudit getWrappedModel() {
		return _watsonVehicleAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonVehicleAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonVehicleAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonVehicleAudit.resetOriginalValues();
	}

	private final WatsonVehicleAudit _watsonVehicleAudit;

}