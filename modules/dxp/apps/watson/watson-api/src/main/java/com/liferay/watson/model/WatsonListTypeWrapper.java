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
 * This class is a wrapper for {@link WatsonListType}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonListType
 * @generated
 */
@ProviderType
public class WatsonListTypeWrapper implements WatsonListType,
	ModelWrapper<WatsonListType> {
	public WatsonListTypeWrapper(WatsonListType watsonListType) {
		_watsonListType = watsonListType;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonListType.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonListType.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonListTypeId", getWatsonListTypeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentWatsonListTypeId", getParentWatsonListTypeId());
		attributes.put("name", getName());
		attributes.put("type", getType());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonListTypeId = (Long)attributes.get("watsonListTypeId");

		if (watsonListTypeId != null) {
			setWatsonListTypeId(watsonListTypeId);
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

		Long parentWatsonListTypeId = (Long)attributes.get(
				"parentWatsonListTypeId");

		if (parentWatsonListTypeId != null) {
			setParentWatsonListTypeId(parentWatsonListTypeId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
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
		return new WatsonListTypeWrapper((WatsonListType)_watsonListType.clone());
	}

	@Override
	public int compareTo(WatsonListType watsonListType) {
		return _watsonListType.compareTo(watsonListType);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return _watsonListType.getAvailableLanguageIds();
	}

	/**
	* Returns the company ID of this watson list type.
	*
	* @return the company ID of this watson list type
	*/
	@Override
	public long getCompanyId() {
		return _watsonListType.getCompanyId();
	}

	/**
	* Returns the create date of this watson list type.
	*
	* @return the create date of this watson list type
	*/
	@Override
	public Date getCreateDate() {
		return _watsonListType.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return _watsonListType.getDefaultLanguageId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonListType.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson list type.
	*
	* @return the group ID of this watson list type
	*/
	@Override
	public long getGroupId() {
		return _watsonListType.getGroupId();
	}

	/**
	* Returns the modified date of this watson list type.
	*
	* @return the modified date of this watson list type
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonListType.getModifiedDate();
	}

	/**
	* Returns the name of this watson list type.
	*
	* @return the name of this watson list type
	*/
	@Override
	public String getName() {
		return _watsonListType.getName();
	}

	/**
	* Returns the localized name of this watson list type in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this watson list type
	*/
	@Override
	public String getName(java.util.Locale locale) {
		return _watsonListType.getName(locale);
	}

	/**
	* Returns the localized name of this watson list type in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson list type. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public String getName(java.util.Locale locale, boolean useDefault) {
		return _watsonListType.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this watson list type in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this watson list type
	*/
	@Override
	public String getName(String languageId) {
		return _watsonListType.getName(languageId);
	}

	/**
	* Returns the localized name of this watson list type in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this watson list type
	*/
	@Override
	public String getName(String languageId, boolean useDefault) {
		return _watsonListType.getName(languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _watsonListType.getNameCurrentLanguageId();
	}

	@Override
	public String getNameCurrentValue() {
		return _watsonListType.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this watson list type.
	*
	* @return the locales and localized names of this watson list type
	*/
	@Override
	public Map<java.util.Locale, String> getNameMap() {
		return _watsonListType.getNameMap();
	}

	/**
	* Returns the parent watson list type ID of this watson list type.
	*
	* @return the parent watson list type ID of this watson list type
	*/
	@Override
	public long getParentWatsonListTypeId() {
		return _watsonListType.getParentWatsonListTypeId();
	}

	/**
	* Returns the primary key of this watson list type.
	*
	* @return the primary key of this watson list type
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonListType.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonListType.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this watson list type.
	*
	* @return the status of this watson list type
	*/
	@Override
	public int getStatus() {
		return _watsonListType.getStatus();
	}

	/**
	* Returns the type of this watson list type.
	*
	* @return the type of this watson list type
	*/
	@Override
	public String getType() {
		return _watsonListType.getType();
	}

	/**
	* Returns the user ID of this watson list type.
	*
	* @return the user ID of this watson list type
	*/
	@Override
	public long getUserId() {
		return _watsonListType.getUserId();
	}

	/**
	* Returns the user name of this watson list type.
	*
	* @return the user name of this watson list type
	*/
	@Override
	public String getUserName() {
		return _watsonListType.getUserName();
	}

	/**
	* Returns the user uuid of this watson list type.
	*
	* @return the user uuid of this watson list type
	*/
	@Override
	public String getUserUuid() {
		return _watsonListType.getUserUuid();
	}

	/**
	* Returns the watson list type ID of this watson list type.
	*
	* @return the watson list type ID of this watson list type
	*/
	@Override
	public long getWatsonListTypeId() {
		return _watsonListType.getWatsonListTypeId();
	}

	@Override
	public int hashCode() {
		return _watsonListType.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonListType.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonListType.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonListType.isNew();
	}

	@Override
	public void persist() {
		_watsonListType.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonListType.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {
		_watsonListType.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonListType.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this watson list type.
	*
	* @param companyId the company ID of this watson list type
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonListType.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this watson list type.
	*
	* @param createDate the create date of this watson list type
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonListType.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonListType.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonListType.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonListType.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson list type.
	*
	* @param groupId the group ID of this watson list type
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonListType.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this watson list type.
	*
	* @param modifiedDate the modified date of this watson list type
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonListType.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this watson list type.
	*
	* @param name the name of this watson list type
	*/
	@Override
	public void setName(String name) {
		_watsonListType.setName(name);
	}

	/**
	* Sets the localized name of this watson list type in the language.
	*
	* @param name the localized name of this watson list type
	* @param locale the locale of the language
	*/
	@Override
	public void setName(String name, java.util.Locale locale) {
		_watsonListType.setName(name, locale);
	}

	/**
	* Sets the localized name of this watson list type in the language, and sets the default locale.
	*
	* @param name the localized name of this watson list type
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_watsonListType.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_watsonListType.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this watson list type from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this watson list type
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, String> nameMap) {
		_watsonListType.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this watson list type from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this watson list type
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(Map<java.util.Locale, String> nameMap,
		java.util.Locale defaultLocale) {
		_watsonListType.setNameMap(nameMap, defaultLocale);
	}

	@Override
	public void setNew(boolean n) {
		_watsonListType.setNew(n);
	}

	/**
	* Sets the parent watson list type ID of this watson list type.
	*
	* @param parentWatsonListTypeId the parent watson list type ID of this watson list type
	*/
	@Override
	public void setParentWatsonListTypeId(long parentWatsonListTypeId) {
		_watsonListType.setParentWatsonListTypeId(parentWatsonListTypeId);
	}

	/**
	* Sets the primary key of this watson list type.
	*
	* @param primaryKey the primary key of this watson list type
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonListType.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonListType.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this watson list type.
	*
	* @param status the status of this watson list type
	*/
	@Override
	public void setStatus(int status) {
		_watsonListType.setStatus(status);
	}

	/**
	* Sets the type of this watson list type.
	*
	* @param type the type of this watson list type
	*/
	@Override
	public void setType(String type) {
		_watsonListType.setType(type);
	}

	/**
	* Sets the user ID of this watson list type.
	*
	* @param userId the user ID of this watson list type
	*/
	@Override
	public void setUserId(long userId) {
		_watsonListType.setUserId(userId);
	}

	/**
	* Sets the user name of this watson list type.
	*
	* @param userName the user name of this watson list type
	*/
	@Override
	public void setUserName(String userName) {
		_watsonListType.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson list type.
	*
	* @param userUuid the user uuid of this watson list type
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_watsonListType.setUserUuid(userUuid);
	}

	/**
	* Sets the watson list type ID of this watson list type.
	*
	* @param watsonListTypeId the watson list type ID of this watson list type
	*/
	@Override
	public void setWatsonListTypeId(long watsonListTypeId) {
		_watsonListType.setWatsonListTypeId(watsonListTypeId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonListType> toCacheModel() {
		return _watsonListType.toCacheModel();
	}

	@Override
	public WatsonListType toEscapedModel() {
		return new WatsonListTypeWrapper(_watsonListType.toEscapedModel());
	}

	@Override
	public String toString() {
		return _watsonListType.toString();
	}

	@Override
	public WatsonListType toUnescapedModel() {
		return new WatsonListTypeWrapper(_watsonListType.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _watsonListType.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonListTypeWrapper)) {
			return false;
		}

		WatsonListTypeWrapper watsonListTypeWrapper = (WatsonListTypeWrapper)obj;

		if (Objects.equals(_watsonListType,
					watsonListTypeWrapper._watsonListType)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonListType getWrappedModel() {
		return _watsonListType;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonListType.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonListType.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonListType.resetOriginalValues();
	}

	private final WatsonListType _watsonListType;
}