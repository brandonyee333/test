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

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MarketingEvent}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MarketingEvent
 * @generated
 */
public class MarketingEventWrapper implements MarketingEvent,
	ModelWrapper<MarketingEvent> {
	public MarketingEventWrapper(MarketingEvent marketingEvent) {
		_marketingEvent = marketingEvent;
	}

	public Class<?> getModelClass() {
		return MarketingEvent.class;
	}

	public String getModelClassName() {
		return MarketingEvent.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("marketingEventId", getMarketingEventId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("type", getType());
		attributes.put("defaultLanguageId", getDefaultLanguageId());
		attributes.put("title", getTitle());
		attributes.put("titleURL", getTitleURL());
		attributes.put("hostedBy", getHostedBy());
		attributes.put("hostedByURL", getHostedByURL());
		attributes.put("summary", getSummary());
		attributes.put("imageFileEntryId", getImageFileEntryId());
		attributes.put("slidesFileEntryId", getSlidesFileEntryId());
		attributes.put("videoTitle", getVideoTitle());
		attributes.put("timeZoneId", getTimeZoneId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("dateTBA", getDateTBA());
		attributes.put("addressId", getAddressId());
		attributes.put("globalRegion", getGlobalRegion());
		attributes.put("online", getOnline());
		attributes.put("registrationType", getRegistrationType());
		attributes.put("registrationURL", getRegistrationURL());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long marketingEventId = (Long)attributes.get("marketingEventId");

		if (marketingEventId != null) {
			setMarketingEventId(marketingEventId);
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

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String defaultLanguageId = (String)attributes.get("defaultLanguageId");

		if (defaultLanguageId != null) {
			setDefaultLanguageId(defaultLanguageId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String titleURL = (String)attributes.get("titleURL");

		if (titleURL != null) {
			setTitleURL(titleURL);
		}

		String hostedBy = (String)attributes.get("hostedBy");

		if (hostedBy != null) {
			setHostedBy(hostedBy);
		}

		String hostedByURL = (String)attributes.get("hostedByURL");

		if (hostedByURL != null) {
			setHostedByURL(hostedByURL);
		}

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}

		Long imageFileEntryId = (Long)attributes.get("imageFileEntryId");

		if (imageFileEntryId != null) {
			setImageFileEntryId(imageFileEntryId);
		}

		Long slidesFileEntryId = (Long)attributes.get("slidesFileEntryId");

		if (slidesFileEntryId != null) {
			setSlidesFileEntryId(slidesFileEntryId);
		}

		String videoTitle = (String)attributes.get("videoTitle");

		if (videoTitle != null) {
			setVideoTitle(videoTitle);
		}

		String timeZoneId = (String)attributes.get("timeZoneId");

		if (timeZoneId != null) {
			setTimeZoneId(timeZoneId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Boolean dateTBA = (Boolean)attributes.get("dateTBA");

		if (dateTBA != null) {
			setDateTBA(dateTBA);
		}

		Long addressId = (Long)attributes.get("addressId");

		if (addressId != null) {
			setAddressId(addressId);
		}

		Integer globalRegion = (Integer)attributes.get("globalRegion");

		if (globalRegion != null) {
			setGlobalRegion(globalRegion);
		}

		Boolean online = (Boolean)attributes.get("online");

		if (online != null) {
			setOnline(online);
		}

		Integer registrationType = (Integer)attributes.get("registrationType");

		if (registrationType != null) {
			setRegistrationType(registrationType);
		}

		String registrationURL = (String)attributes.get("registrationURL");

		if (registrationURL != null) {
			setRegistrationURL(registrationURL);
		}
	}

	/**
	* Returns the primary key of this marketing event.
	*
	* @return the primary key of this marketing event
	*/
	public long getPrimaryKey() {
		return _marketingEvent.getPrimaryKey();
	}

	/**
	* Sets the primary key of this marketing event.
	*
	* @param primaryKey the primary key of this marketing event
	*/
	public void setPrimaryKey(long primaryKey) {
		_marketingEvent.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the marketing event ID of this marketing event.
	*
	* @return the marketing event ID of this marketing event
	*/
	public long getMarketingEventId() {
		return _marketingEvent.getMarketingEventId();
	}

	/**
	* Sets the marketing event ID of this marketing event.
	*
	* @param marketingEventId the marketing event ID of this marketing event
	*/
	public void setMarketingEventId(long marketingEventId) {
		_marketingEvent.setMarketingEventId(marketingEventId);
	}

	/**
	* Returns the company ID of this marketing event.
	*
	* @return the company ID of this marketing event
	*/
	public long getCompanyId() {
		return _marketingEvent.getCompanyId();
	}

	/**
	* Sets the company ID of this marketing event.
	*
	* @param companyId the company ID of this marketing event
	*/
	public void setCompanyId(long companyId) {
		_marketingEvent.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this marketing event.
	*
	* @return the user ID of this marketing event
	*/
	public long getUserId() {
		return _marketingEvent.getUserId();
	}

	/**
	* Sets the user ID of this marketing event.
	*
	* @param userId the user ID of this marketing event
	*/
	public void setUserId(long userId) {
		_marketingEvent.setUserId(userId);
	}

	/**
	* Returns the user uuid of this marketing event.
	*
	* @return the user uuid of this marketing event
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEvent.getUserUuid();
	}

	/**
	* Sets the user uuid of this marketing event.
	*
	* @param userUuid the user uuid of this marketing event
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_marketingEvent.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this marketing event.
	*
	* @return the user name of this marketing event
	*/
	public java.lang.String getUserName() {
		return _marketingEvent.getUserName();
	}

	/**
	* Sets the user name of this marketing event.
	*
	* @param userName the user name of this marketing event
	*/
	public void setUserName(java.lang.String userName) {
		_marketingEvent.setUserName(userName);
	}

	/**
	* Returns the create date of this marketing event.
	*
	* @return the create date of this marketing event
	*/
	public java.util.Date getCreateDate() {
		return _marketingEvent.getCreateDate();
	}

	/**
	* Sets the create date of this marketing event.
	*
	* @param createDate the create date of this marketing event
	*/
	public void setCreateDate(java.util.Date createDate) {
		_marketingEvent.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this marketing event.
	*
	* @return the modified date of this marketing event
	*/
	public java.util.Date getModifiedDate() {
		return _marketingEvent.getModifiedDate();
	}

	/**
	* Sets the modified date of this marketing event.
	*
	* @param modifiedDate the modified date of this marketing event
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_marketingEvent.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the type of this marketing event.
	*
	* @return the type of this marketing event
	*/
	public int getType() {
		return _marketingEvent.getType();
	}

	/**
	* Sets the type of this marketing event.
	*
	* @param type the type of this marketing event
	*/
	public void setType(int type) {
		_marketingEvent.setType(type);
	}

	/**
	* Returns the default language ID of this marketing event.
	*
	* @return the default language ID of this marketing event
	*/
	public java.lang.String getDefaultLanguageId() {
		return _marketingEvent.getDefaultLanguageId();
	}

	/**
	* Sets the default language ID of this marketing event.
	*
	* @param defaultLanguageId the default language ID of this marketing event
	*/
	public void setDefaultLanguageId(java.lang.String defaultLanguageId) {
		_marketingEvent.setDefaultLanguageId(defaultLanguageId);
	}

	/**
	* Returns the title of this marketing event.
	*
	* @return the title of this marketing event
	*/
	public java.lang.String getTitle() {
		return _marketingEvent.getTitle();
	}

	/**
	* Returns the localized title of this marketing event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this marketing event
	*/
	public java.lang.String getTitle(java.util.Locale locale) {
		return _marketingEvent.getTitle(locale);
	}

	/**
	* Returns the localized title of this marketing event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this marketing event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _marketingEvent.getTitle(locale, useDefault);
	}

	/**
	* Returns the localized title of this marketing event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this marketing event
	*/
	public java.lang.String getTitle(java.lang.String languageId) {
		return _marketingEvent.getTitle(languageId);
	}

	/**
	* Returns the localized title of this marketing event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this marketing event
	*/
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _marketingEvent.getTitle(languageId, useDefault);
	}

	public java.lang.String getTitleCurrentLanguageId() {
		return _marketingEvent.getTitleCurrentLanguageId();
	}

	public java.lang.String getTitleCurrentValue() {
		return _marketingEvent.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this marketing event.
	*
	* @return the locales and localized titles of this marketing event
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _marketingEvent.getTitleMap();
	}

	/**
	* Sets the title of this marketing event.
	*
	* @param title the title of this marketing event
	*/
	public void setTitle(java.lang.String title) {
		_marketingEvent.setTitle(title);
	}

	/**
	* Sets the localized title of this marketing event in the language.
	*
	* @param title the localized title of this marketing event
	* @param locale the locale of the language
	*/
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_marketingEvent.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this marketing event in the language, and sets the default locale.
	*
	* @param title the localized title of this marketing event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_marketingEvent.setTitle(title, locale, defaultLocale);
	}

	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_marketingEvent.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this marketing event from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this marketing event
	*/
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap) {
		_marketingEvent.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this marketing event from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this marketing event
	* @param defaultLocale the default locale
	*/
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_marketingEvent.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Returns the title u r l of this marketing event.
	*
	* @return the title u r l of this marketing event
	*/
	public java.lang.String getTitleURL() {
		return _marketingEvent.getTitleURL();
	}

	/**
	* Sets the title u r l of this marketing event.
	*
	* @param titleURL the title u r l of this marketing event
	*/
	public void setTitleURL(java.lang.String titleURL) {
		_marketingEvent.setTitleURL(titleURL);
	}

	/**
	* Returns the hosted by of this marketing event.
	*
	* @return the hosted by of this marketing event
	*/
	public java.lang.String getHostedBy() {
		return _marketingEvent.getHostedBy();
	}

	/**
	* Sets the hosted by of this marketing event.
	*
	* @param hostedBy the hosted by of this marketing event
	*/
	public void setHostedBy(java.lang.String hostedBy) {
		_marketingEvent.setHostedBy(hostedBy);
	}

	/**
	* Returns the hosted by u r l of this marketing event.
	*
	* @return the hosted by u r l of this marketing event
	*/
	public java.lang.String getHostedByURL() {
		return _marketingEvent.getHostedByURL();
	}

	/**
	* Sets the hosted by u r l of this marketing event.
	*
	* @param hostedByURL the hosted by u r l of this marketing event
	*/
	public void setHostedByURL(java.lang.String hostedByURL) {
		_marketingEvent.setHostedByURL(hostedByURL);
	}

	/**
	* Returns the summary of this marketing event.
	*
	* @return the summary of this marketing event
	*/
	public java.lang.String getSummary() {
		return _marketingEvent.getSummary();
	}

	/**
	* Returns the localized summary of this marketing event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized summary of this marketing event
	*/
	public java.lang.String getSummary(java.util.Locale locale) {
		return _marketingEvent.getSummary(locale);
	}

	/**
	* Returns the localized summary of this marketing event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized summary of this marketing event. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getSummary(java.util.Locale locale,
		boolean useDefault) {
		return _marketingEvent.getSummary(locale, useDefault);
	}

	/**
	* Returns the localized summary of this marketing event in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized summary of this marketing event
	*/
	public java.lang.String getSummary(java.lang.String languageId) {
		return _marketingEvent.getSummary(languageId);
	}

	/**
	* Returns the localized summary of this marketing event in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized summary of this marketing event
	*/
	public java.lang.String getSummary(java.lang.String languageId,
		boolean useDefault) {
		return _marketingEvent.getSummary(languageId, useDefault);
	}

	public java.lang.String getSummaryCurrentLanguageId() {
		return _marketingEvent.getSummaryCurrentLanguageId();
	}

	public java.lang.String getSummaryCurrentValue() {
		return _marketingEvent.getSummaryCurrentValue();
	}

	/**
	* Returns a map of the locales and localized summaries of this marketing event.
	*
	* @return the locales and localized summaries of this marketing event
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getSummaryMap() {
		return _marketingEvent.getSummaryMap();
	}

	/**
	* Sets the summary of this marketing event.
	*
	* @param summary the summary of this marketing event
	*/
	public void setSummary(java.lang.String summary) {
		_marketingEvent.setSummary(summary);
	}

	/**
	* Sets the localized summary of this marketing event in the language.
	*
	* @param summary the localized summary of this marketing event
	* @param locale the locale of the language
	*/
	public void setSummary(java.lang.String summary, java.util.Locale locale) {
		_marketingEvent.setSummary(summary, locale);
	}

	/**
	* Sets the localized summary of this marketing event in the language, and sets the default locale.
	*
	* @param summary the localized summary of this marketing event
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setSummary(java.lang.String summary, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_marketingEvent.setSummary(summary, locale, defaultLocale);
	}

	public void setSummaryCurrentLanguageId(java.lang.String languageId) {
		_marketingEvent.setSummaryCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized summaries of this marketing event from the map of locales and localized summaries.
	*
	* @param summaryMap the locales and localized summaries of this marketing event
	*/
	public void setSummaryMap(
		java.util.Map<java.util.Locale, java.lang.String> summaryMap) {
		_marketingEvent.setSummaryMap(summaryMap);
	}

	/**
	* Sets the localized summaries of this marketing event from the map of locales and localized summaries, and sets the default locale.
	*
	* @param summaryMap the locales and localized summaries of this marketing event
	* @param defaultLocale the default locale
	*/
	public void setSummaryMap(
		java.util.Map<java.util.Locale, java.lang.String> summaryMap,
		java.util.Locale defaultLocale) {
		_marketingEvent.setSummaryMap(summaryMap, defaultLocale);
	}

	/**
	* Returns the image file entry ID of this marketing event.
	*
	* @return the image file entry ID of this marketing event
	*/
	public long getImageFileEntryId() {
		return _marketingEvent.getImageFileEntryId();
	}

	/**
	* Sets the image file entry ID of this marketing event.
	*
	* @param imageFileEntryId the image file entry ID of this marketing event
	*/
	public void setImageFileEntryId(long imageFileEntryId) {
		_marketingEvent.setImageFileEntryId(imageFileEntryId);
	}

	/**
	* Returns the slides file entry ID of this marketing event.
	*
	* @return the slides file entry ID of this marketing event
	*/
	public long getSlidesFileEntryId() {
		return _marketingEvent.getSlidesFileEntryId();
	}

	/**
	* Sets the slides file entry ID of this marketing event.
	*
	* @param slidesFileEntryId the slides file entry ID of this marketing event
	*/
	public void setSlidesFileEntryId(long slidesFileEntryId) {
		_marketingEvent.setSlidesFileEntryId(slidesFileEntryId);
	}

	/**
	* Returns the video title of this marketing event.
	*
	* @return the video title of this marketing event
	*/
	public java.lang.String getVideoTitle() {
		return _marketingEvent.getVideoTitle();
	}

	/**
	* Sets the video title of this marketing event.
	*
	* @param videoTitle the video title of this marketing event
	*/
	public void setVideoTitle(java.lang.String videoTitle) {
		_marketingEvent.setVideoTitle(videoTitle);
	}

	/**
	* Returns the time zone ID of this marketing event.
	*
	* @return the time zone ID of this marketing event
	*/
	public java.lang.String getTimeZoneId() {
		return _marketingEvent.getTimeZoneId();
	}

	/**
	* Sets the time zone ID of this marketing event.
	*
	* @param timeZoneId the time zone ID of this marketing event
	*/
	public void setTimeZoneId(java.lang.String timeZoneId) {
		_marketingEvent.setTimeZoneId(timeZoneId);
	}

	/**
	* Returns the start date of this marketing event.
	*
	* @return the start date of this marketing event
	*/
	public java.util.Date getStartDate() {
		return _marketingEvent.getStartDate();
	}

	/**
	* Sets the start date of this marketing event.
	*
	* @param startDate the start date of this marketing event
	*/
	public void setStartDate(java.util.Date startDate) {
		_marketingEvent.setStartDate(startDate);
	}

	/**
	* Returns the end date of this marketing event.
	*
	* @return the end date of this marketing event
	*/
	public java.util.Date getEndDate() {
		return _marketingEvent.getEndDate();
	}

	/**
	* Sets the end date of this marketing event.
	*
	* @param endDate the end date of this marketing event
	*/
	public void setEndDate(java.util.Date endDate) {
		_marketingEvent.setEndDate(endDate);
	}

	/**
	* Returns the date t b a of this marketing event.
	*
	* @return the date t b a of this marketing event
	*/
	public boolean getDateTBA() {
		return _marketingEvent.getDateTBA();
	}

	/**
	* Returns <code>true</code> if this marketing event is date t b a.
	*
	* @return <code>true</code> if this marketing event is date t b a; <code>false</code> otherwise
	*/
	public boolean isDateTBA() {
		return _marketingEvent.isDateTBA();
	}

	/**
	* Sets whether this marketing event is date t b a.
	*
	* @param dateTBA the date t b a of this marketing event
	*/
	public void setDateTBA(boolean dateTBA) {
		_marketingEvent.setDateTBA(dateTBA);
	}

	/**
	* Returns the address ID of this marketing event.
	*
	* @return the address ID of this marketing event
	*/
	public long getAddressId() {
		return _marketingEvent.getAddressId();
	}

	/**
	* Sets the address ID of this marketing event.
	*
	* @param addressId the address ID of this marketing event
	*/
	public void setAddressId(long addressId) {
		_marketingEvent.setAddressId(addressId);
	}

	/**
	* Returns the global region of this marketing event.
	*
	* @return the global region of this marketing event
	*/
	public int getGlobalRegion() {
		return _marketingEvent.getGlobalRegion();
	}

	/**
	* Sets the global region of this marketing event.
	*
	* @param globalRegion the global region of this marketing event
	*/
	public void setGlobalRegion(int globalRegion) {
		_marketingEvent.setGlobalRegion(globalRegion);
	}

	/**
	* Returns the online of this marketing event.
	*
	* @return the online of this marketing event
	*/
	public boolean getOnline() {
		return _marketingEvent.getOnline();
	}

	/**
	* Returns <code>true</code> if this marketing event is online.
	*
	* @return <code>true</code> if this marketing event is online; <code>false</code> otherwise
	*/
	public boolean isOnline() {
		return _marketingEvent.isOnline();
	}

	/**
	* Sets whether this marketing event is online.
	*
	* @param online the online of this marketing event
	*/
	public void setOnline(boolean online) {
		_marketingEvent.setOnline(online);
	}

	/**
	* Returns the registration type of this marketing event.
	*
	* @return the registration type of this marketing event
	*/
	public int getRegistrationType() {
		return _marketingEvent.getRegistrationType();
	}

	/**
	* Sets the registration type of this marketing event.
	*
	* @param registrationType the registration type of this marketing event
	*/
	public void setRegistrationType(int registrationType) {
		_marketingEvent.setRegistrationType(registrationType);
	}

	/**
	* Returns the registration u r l of this marketing event.
	*
	* @return the registration u r l of this marketing event
	*/
	public java.lang.String getRegistrationURL() {
		return _marketingEvent.getRegistrationURL();
	}

	/**
	* Sets the registration u r l of this marketing event.
	*
	* @param registrationURL the registration u r l of this marketing event
	*/
	public void setRegistrationURL(java.lang.String registrationURL) {
		_marketingEvent.setRegistrationURL(registrationURL);
	}

	public boolean isNew() {
		return _marketingEvent.isNew();
	}

	public void setNew(boolean n) {
		_marketingEvent.setNew(n);
	}

	public boolean isCachedModel() {
		return _marketingEvent.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_marketingEvent.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _marketingEvent.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _marketingEvent.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_marketingEvent.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _marketingEvent.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_marketingEvent.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_marketingEvent.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new MarketingEventWrapper((MarketingEvent)_marketingEvent.clone());
	}

	public int compareTo(com.liferay.osb.model.MarketingEvent marketingEvent) {
		return _marketingEvent.compareTo(marketingEvent);
	}

	@Override
	public int hashCode() {
		return _marketingEvent.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.MarketingEvent> toCacheModel() {
		return _marketingEvent.toCacheModel();
	}

	public com.liferay.osb.model.MarketingEvent toEscapedModel() {
		return new MarketingEventWrapper(_marketingEvent.toEscapedModel());
	}

	public com.liferay.osb.model.MarketingEvent toUnescapedModel() {
		return new MarketingEventWrapper(_marketingEvent.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _marketingEvent.toString();
	}

	public java.lang.String toXmlString() {
		return _marketingEvent.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_marketingEvent.persist();
	}

	public com.liferay.portal.model.Address getAddress() {
		return _marketingEvent.getAddress();
	}

	public com.liferay.portal.kernel.repository.model.FileEntry getImageFileEntry() {
		return _marketingEvent.getImageFileEntry();
	}

	public com.liferay.portal.kernel.repository.model.FileEntry getSlidesFileEntry() {
		return _marketingEvent.getSlidesFileEntry();
	}

	public java.lang.String getVideoURL(java.lang.String extension) {
		return _marketingEvent.getVideoURL(extension);
	}

	public boolean hasVideo() {
		return _marketingEvent.hasVideo();
	}

	public boolean isTypeCommunity() {
		return _marketingEvent.isTypeCommunity();
	}

	public boolean isTypeConference() {
		return _marketingEvent.isTypeConference();
	}

	public boolean isTypeIndustry() {
		return _marketingEvent.isTypeIndustry();
	}

	public boolean isTypePartner() {
		return _marketingEvent.isTypePartner();
	}

	public boolean isTypeRoadshow() {
		return _marketingEvent.isTypeRoadshow();
	}

	public boolean isTypeWebinar() {
		return _marketingEvent.isTypeWebinar();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MarketingEventWrapper)) {
			return false;
		}

		MarketingEventWrapper marketingEventWrapper = (MarketingEventWrapper)obj;

		if (Validator.equals(_marketingEvent,
					marketingEventWrapper._marketingEvent)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public MarketingEvent getWrappedMarketingEvent() {
		return _marketingEvent;
	}

	public MarketingEvent getWrappedModel() {
		return _marketingEvent;
	}

	public void resetOriginalValues() {
		_marketingEvent.resetOriginalValues();
	}

	private MarketingEvent _marketingEvent;
}