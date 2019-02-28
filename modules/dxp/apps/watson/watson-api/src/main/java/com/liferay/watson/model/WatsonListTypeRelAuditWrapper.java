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
 * This class is a wrapper for {@link WatsonListTypeRelAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonListTypeRelAudit
 * @generated
 */
@ProviderType
public class WatsonListTypeRelAuditWrapper
	implements WatsonListTypeRelAudit, ModelWrapper<WatsonListTypeRelAudit> {

	public WatsonListTypeRelAuditWrapper(
		WatsonListTypeRelAudit watsonListTypeRelAudit) {

		_watsonListTypeRelAudit = watsonListTypeRelAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonListTypeRelAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonListTypeRelAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put(
			"watsonListTypeRelAuditId", getWatsonListTypeRelAuditId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("watsonListTypeId", getWatsonListTypeId());
		attributes.put("watsonListTypeRelId", getWatsonListTypeRelId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("primary", isPrimary());
		attributes.put("value", getValue());
		attributes.put("type", getType());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonListTypeRelAuditId = (Long)attributes.get(
			"watsonListTypeRelAuditId");

		if (watsonListTypeRelAuditId != null) {
			setWatsonListTypeRelAuditId(watsonListTypeRelAuditId);
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

		Long watsonListTypeId = (Long)attributes.get("watsonListTypeId");

		if (watsonListTypeId != null) {
			setWatsonListTypeId(watsonListTypeId);
		}

		Long watsonListTypeRelId = (Long)attributes.get("watsonListTypeRelId");

		if (watsonListTypeRelId != null) {
			setWatsonListTypeRelId(watsonListTypeRelId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Boolean primary = (Boolean)attributes.get("primary");

		if (primary != null) {
			setPrimary(primary);
		}

		String value = (String)attributes.get("value");

		if (value != null) {
			setValue(value);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new WatsonListTypeRelAuditWrapper(
			(WatsonListTypeRelAudit)_watsonListTypeRelAudit.clone());
	}

	@Override
	public int compareTo(WatsonListTypeRelAudit watsonListTypeRelAudit) {
		return _watsonListTypeRelAudit.compareTo(watsonListTypeRelAudit);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _watsonListTypeRelAudit.getAvailableLanguageIds();
	}

	/**
	 * Returns the fully qualified class name of this watson list type rel audit.
	 *
	 * @return the fully qualified class name of this watson list type rel audit
	 */
	@Override
	public String getClassName() {
		return _watsonListTypeRelAudit.getClassName();
	}

	/**
	 * Returns the class name ID of this watson list type rel audit.
	 *
	 * @return the class name ID of this watson list type rel audit
	 */
	@Override
	public long getClassNameId() {
		return _watsonListTypeRelAudit.getClassNameId();
	}

	/**
	 * Returns the class pk of this watson list type rel audit.
	 *
	 * @return the class pk of this watson list type rel audit
	 */
	@Override
	public long getClassPK() {
		return _watsonListTypeRelAudit.getClassPK();
	}

	/**
	 * Returns the company ID of this watson list type rel audit.
	 *
	 * @return the company ID of this watson list type rel audit
	 */
	@Override
	public long getCompanyId() {
		return _watsonListTypeRelAudit.getCompanyId();
	}

	/**
	 * Returns the create date of this watson list type rel audit.
	 *
	 * @return the create date of this watson list type rel audit
	 */
	@Override
	public Date getCreateDate() {
		return _watsonListTypeRelAudit.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _watsonListTypeRelAudit.getDefaultLanguageId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonListTypeRelAudit.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this watson list type rel audit.
	 *
	 * @return the group ID of this watson list type rel audit
	 */
	@Override
	public long getGroupId() {
		return _watsonListTypeRelAudit.getGroupId();
	}

	/**
	 * Returns the modified date of this watson list type rel audit.
	 *
	 * @return the modified date of this watson list type rel audit
	 */
	@Override
	public Date getModifiedDate() {
		return _watsonListTypeRelAudit.getModifiedDate();
	}

	/**
	 * Returns the primary of this watson list type rel audit.
	 *
	 * @return the primary of this watson list type rel audit
	 */
	@Override
	public boolean getPrimary() {
		return _watsonListTypeRelAudit.getPrimary();
	}

	/**
	 * Returns the primary key of this watson list type rel audit.
	 *
	 * @return the primary key of this watson list type rel audit
	 */
	@Override
	public long getPrimaryKey() {
		return _watsonListTypeRelAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonListTypeRelAudit.getPrimaryKeyObj();
	}

	/**
	 * Returns the status of this watson list type rel audit.
	 *
	 * @return the status of this watson list type rel audit
	 */
	@Override
	public int getStatus() {
		return _watsonListTypeRelAudit.getStatus();
	}

	/**
	 * Returns the type of this watson list type rel audit.
	 *
	 * @return the type of this watson list type rel audit
	 */
	@Override
	public String getType() {
		return _watsonListTypeRelAudit.getType();
	}

	/**
	 * Returns the user ID of this watson list type rel audit.
	 *
	 * @return the user ID of this watson list type rel audit
	 */
	@Override
	public long getUserId() {
		return _watsonListTypeRelAudit.getUserId();
	}

	/**
	 * Returns the user name of this watson list type rel audit.
	 *
	 * @return the user name of this watson list type rel audit
	 */
	@Override
	public String getUserName() {
		return _watsonListTypeRelAudit.getUserName();
	}

	/**
	 * Returns the user uuid of this watson list type rel audit.
	 *
	 * @return the user uuid of this watson list type rel audit
	 */
	@Override
	public String getUserUuid() {
		return _watsonListTypeRelAudit.getUserUuid();
	}

	/**
	 * Returns the value of this watson list type rel audit.
	 *
	 * @return the value of this watson list type rel audit
	 */
	@Override
	public String getValue() {
		return _watsonListTypeRelAudit.getValue();
	}

	/**
	 * Returns the localized value of this watson list type rel audit in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized value of this watson list type rel audit
	 */
	@Override
	public String getValue(java.util.Locale locale) {
		return _watsonListTypeRelAudit.getValue(locale);
	}

	/**
	 * Returns the localized value of this watson list type rel audit in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized value of this watson list type rel audit. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getValue(java.util.Locale locale, boolean useDefault) {
		return _watsonListTypeRelAudit.getValue(locale, useDefault);
	}

	/**
	 * Returns the localized value of this watson list type rel audit in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized value of this watson list type rel audit
	 */
	@Override
	public String getValue(String languageId) {
		return _watsonListTypeRelAudit.getValue(languageId);
	}

	/**
	 * Returns the localized value of this watson list type rel audit in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized value of this watson list type rel audit
	 */
	@Override
	public String getValue(String languageId, boolean useDefault) {
		return _watsonListTypeRelAudit.getValue(languageId, useDefault);
	}

	@Override
	public String getValueCurrentLanguageId() {
		return _watsonListTypeRelAudit.getValueCurrentLanguageId();
	}

	@Override
	public String getValueCurrentValue() {
		return _watsonListTypeRelAudit.getValueCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized values of this watson list type rel audit.
	 *
	 * @return the locales and localized values of this watson list type rel audit
	 */
	@Override
	public Map<java.util.Locale, String> getValueMap() {
		return _watsonListTypeRelAudit.getValueMap();
	}

	/**
	 * Returns the watson list type ID of this watson list type rel audit.
	 *
	 * @return the watson list type ID of this watson list type rel audit
	 */
	@Override
	public long getWatsonListTypeId() {
		return _watsonListTypeRelAudit.getWatsonListTypeId();
	}

	/**
	 * Returns the watson list type rel audit ID of this watson list type rel audit.
	 *
	 * @return the watson list type rel audit ID of this watson list type rel audit
	 */
	@Override
	public long getWatsonListTypeRelAuditId() {
		return _watsonListTypeRelAudit.getWatsonListTypeRelAuditId();
	}

	/**
	 * Returns the watson list type rel ID of this watson list type rel audit.
	 *
	 * @return the watson list type rel ID of this watson list type rel audit
	 */
	@Override
	public long getWatsonListTypeRelId() {
		return _watsonListTypeRelAudit.getWatsonListTypeRelId();
	}

	@Override
	public int hashCode() {
		return _watsonListTypeRelAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonListTypeRelAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonListTypeRelAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonListTypeRelAudit.isNew();
	}

	/**
	 * Returns <code>true</code> if this watson list type rel audit is primary.
	 *
	 * @return <code>true</code> if this watson list type rel audit is primary; <code>false</code> otherwise
	 */
	@Override
	public boolean isPrimary() {
		return _watsonListTypeRelAudit.isPrimary();
	}

	@Override
	public void persist() {
		_watsonListTypeRelAudit.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		_watsonListTypeRelAudit.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		_watsonListTypeRelAudit.prepareLocalizedFieldsForImport(
			defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonListTypeRelAudit.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_watsonListTypeRelAudit.setClassName(className);
	}

	/**
	 * Sets the class name ID of this watson list type rel audit.
	 *
	 * @param classNameId the class name ID of this watson list type rel audit
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_watsonListTypeRelAudit.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this watson list type rel audit.
	 *
	 * @param classPK the class pk of this watson list type rel audit
	 */
	@Override
	public void setClassPK(long classPK) {
		_watsonListTypeRelAudit.setClassPK(classPK);
	}

	/**
	 * Sets the company ID of this watson list type rel audit.
	 *
	 * @param companyId the company ID of this watson list type rel audit
	 */
	@Override
	public void setCompanyId(long companyId) {
		_watsonListTypeRelAudit.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this watson list type rel audit.
	 *
	 * @param createDate the create date of this watson list type rel audit
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_watsonListTypeRelAudit.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_watsonListTypeRelAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonListTypeRelAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonListTypeRelAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this watson list type rel audit.
	 *
	 * @param groupId the group ID of this watson list type rel audit
	 */
	@Override
	public void setGroupId(long groupId) {
		_watsonListTypeRelAudit.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this watson list type rel audit.
	 *
	 * @param modifiedDate the modified date of this watson list type rel audit
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonListTypeRelAudit.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonListTypeRelAudit.setNew(n);
	}

	/**
	 * Sets whether this watson list type rel audit is primary.
	 *
	 * @param primary the primary of this watson list type rel audit
	 */
	@Override
	public void setPrimary(boolean primary) {
		_watsonListTypeRelAudit.setPrimary(primary);
	}

	/**
	 * Sets the primary key of this watson list type rel audit.
	 *
	 * @param primaryKey the primary key of this watson list type rel audit
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonListTypeRelAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonListTypeRelAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the status of this watson list type rel audit.
	 *
	 * @param status the status of this watson list type rel audit
	 */
	@Override
	public void setStatus(int status) {
		_watsonListTypeRelAudit.setStatus(status);
	}

	/**
	 * Sets the type of this watson list type rel audit.
	 *
	 * @param type the type of this watson list type rel audit
	 */
	@Override
	public void setType(String type) {
		_watsonListTypeRelAudit.setType(type);
	}

	/**
	 * Sets the user ID of this watson list type rel audit.
	 *
	 * @param userId the user ID of this watson list type rel audit
	 */
	@Override
	public void setUserId(long userId) {
		_watsonListTypeRelAudit.setUserId(userId);
	}

	/**
	 * Sets the user name of this watson list type rel audit.
	 *
	 * @param userName the user name of this watson list type rel audit
	 */
	@Override
	public void setUserName(String userName) {
		_watsonListTypeRelAudit.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this watson list type rel audit.
	 *
	 * @param userUuid the user uuid of this watson list type rel audit
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_watsonListTypeRelAudit.setUserUuid(userUuid);
	}

	/**
	 * Sets the value of this watson list type rel audit.
	 *
	 * @param value the value of this watson list type rel audit
	 */
	@Override
	public void setValue(String value) {
		_watsonListTypeRelAudit.setValue(value);
	}

	/**
	 * Sets the localized value of this watson list type rel audit in the language.
	 *
	 * @param value the localized value of this watson list type rel audit
	 * @param locale the locale of the language
	 */
	@Override
	public void setValue(String value, java.util.Locale locale) {
		_watsonListTypeRelAudit.setValue(value, locale);
	}

	/**
	 * Sets the localized value of this watson list type rel audit in the language, and sets the default locale.
	 *
	 * @param value the localized value of this watson list type rel audit
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setValue(
		String value, java.util.Locale locale, java.util.Locale defaultLocale) {

		_watsonListTypeRelAudit.setValue(value, locale, defaultLocale);
	}

	@Override
	public void setValueCurrentLanguageId(String languageId) {
		_watsonListTypeRelAudit.setValueCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized values of this watson list type rel audit from the map of locales and localized values.
	 *
	 * @param valueMap the locales and localized values of this watson list type rel audit
	 */
	@Override
	public void setValueMap(Map<java.util.Locale, String> valueMap) {
		_watsonListTypeRelAudit.setValueMap(valueMap);
	}

	/**
	 * Sets the localized values of this watson list type rel audit from the map of locales and localized values, and sets the default locale.
	 *
	 * @param valueMap the locales and localized values of this watson list type rel audit
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setValueMap(
		Map<java.util.Locale, String> valueMap,
		java.util.Locale defaultLocale) {

		_watsonListTypeRelAudit.setValueMap(valueMap, defaultLocale);
	}

	/**
	 * Sets the watson list type ID of this watson list type rel audit.
	 *
	 * @param watsonListTypeId the watson list type ID of this watson list type rel audit
	 */
	@Override
	public void setWatsonListTypeId(long watsonListTypeId) {
		_watsonListTypeRelAudit.setWatsonListTypeId(watsonListTypeId);
	}

	/**
	 * Sets the watson list type rel audit ID of this watson list type rel audit.
	 *
	 * @param watsonListTypeRelAuditId the watson list type rel audit ID of this watson list type rel audit
	 */
	@Override
	public void setWatsonListTypeRelAuditId(long watsonListTypeRelAuditId) {
		_watsonListTypeRelAudit.setWatsonListTypeRelAuditId(
			watsonListTypeRelAuditId);
	}

	/**
	 * Sets the watson list type rel ID of this watson list type rel audit.
	 *
	 * @param watsonListTypeRelId the watson list type rel ID of this watson list type rel audit
	 */
	@Override
	public void setWatsonListTypeRelId(long watsonListTypeRelId) {
		_watsonListTypeRelAudit.setWatsonListTypeRelId(watsonListTypeRelId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonListTypeRelAudit>
		toCacheModel() {

		return _watsonListTypeRelAudit.toCacheModel();
	}

	@Override
	public WatsonListTypeRelAudit toEscapedModel() {
		return new WatsonListTypeRelAuditWrapper(
			_watsonListTypeRelAudit.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonListTypeRelAudit.toString();
	}

	@Override
	public WatsonListTypeRelAudit toUnescapedModel() {
		return new WatsonListTypeRelAuditWrapper(
			_watsonListTypeRelAudit.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonListTypeRelAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonListTypeRelAuditWrapper)) {
			return false;
		}

		WatsonListTypeRelAuditWrapper watsonListTypeRelAuditWrapper =
			(WatsonListTypeRelAuditWrapper)obj;

		if (Objects.equals(
				_watsonListTypeRelAudit,
				watsonListTypeRelAuditWrapper._watsonListTypeRelAudit)) {

			return true;
		}

		return false;
	}

	@Override
	public WatsonListTypeRelAudit getWrappedModel() {
		return _watsonListTypeRelAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonListTypeRelAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonListTypeRelAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonListTypeRelAudit.resetOriginalValues();
	}

	private final WatsonListTypeRelAudit _watsonListTypeRelAudit;

}