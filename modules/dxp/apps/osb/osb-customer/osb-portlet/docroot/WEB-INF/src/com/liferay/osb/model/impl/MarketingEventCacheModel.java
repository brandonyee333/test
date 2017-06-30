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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.MarketingEvent;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing MarketingEvent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MarketingEvent
 * @generated
 */
public class MarketingEventCacheModel implements CacheModel<MarketingEvent>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{marketingEventId=");
		sb.append(marketingEventId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", type=");
		sb.append(type);
		sb.append(", defaultLanguageId=");
		sb.append(defaultLanguageId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", titleURL=");
		sb.append(titleURL);
		sb.append(", hostedBy=");
		sb.append(hostedBy);
		sb.append(", hostedByURL=");
		sb.append(hostedByURL);
		sb.append(", summary=");
		sb.append(summary);
		sb.append(", imageFileEntryId=");
		sb.append(imageFileEntryId);
		sb.append(", slidesFileEntryId=");
		sb.append(slidesFileEntryId);
		sb.append(", videoTitle=");
		sb.append(videoTitle);
		sb.append(", timeZoneId=");
		sb.append(timeZoneId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", dateTBA=");
		sb.append(dateTBA);
		sb.append(", addressId=");
		sb.append(addressId);
		sb.append(", globalRegion=");
		sb.append(globalRegion);
		sb.append(", online=");
		sb.append(online);
		sb.append(", registrationType=");
		sb.append(registrationType);
		sb.append(", registrationURL=");
		sb.append(registrationURL);
		sb.append("}");

		return sb.toString();
	}

	public MarketingEvent toEntityModel() {
		MarketingEventImpl marketingEventImpl = new MarketingEventImpl();

		marketingEventImpl.setMarketingEventId(marketingEventId);
		marketingEventImpl.setCompanyId(companyId);
		marketingEventImpl.setUserId(userId);

		if (userName == null) {
			marketingEventImpl.setUserName(StringPool.BLANK);
		}
		else {
			marketingEventImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			marketingEventImpl.setCreateDate(null);
		}
		else {
			marketingEventImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			marketingEventImpl.setModifiedDate(null);
		}
		else {
			marketingEventImpl.setModifiedDate(new Date(modifiedDate));
		}

		marketingEventImpl.setType(type);

		if (defaultLanguageId == null) {
			marketingEventImpl.setDefaultLanguageId(StringPool.BLANK);
		}
		else {
			marketingEventImpl.setDefaultLanguageId(defaultLanguageId);
		}

		if (title == null) {
			marketingEventImpl.setTitle(StringPool.BLANK);
		}
		else {
			marketingEventImpl.setTitle(title);
		}

		if (titleURL == null) {
			marketingEventImpl.setTitleURL(StringPool.BLANK);
		}
		else {
			marketingEventImpl.setTitleURL(titleURL);
		}

		if (hostedBy == null) {
			marketingEventImpl.setHostedBy(StringPool.BLANK);
		}
		else {
			marketingEventImpl.setHostedBy(hostedBy);
		}

		if (hostedByURL == null) {
			marketingEventImpl.setHostedByURL(StringPool.BLANK);
		}
		else {
			marketingEventImpl.setHostedByURL(hostedByURL);
		}

		if (summary == null) {
			marketingEventImpl.setSummary(StringPool.BLANK);
		}
		else {
			marketingEventImpl.setSummary(summary);
		}

		marketingEventImpl.setImageFileEntryId(imageFileEntryId);
		marketingEventImpl.setSlidesFileEntryId(slidesFileEntryId);

		if (videoTitle == null) {
			marketingEventImpl.setVideoTitle(StringPool.BLANK);
		}
		else {
			marketingEventImpl.setVideoTitle(videoTitle);
		}

		if (timeZoneId == null) {
			marketingEventImpl.setTimeZoneId(StringPool.BLANK);
		}
		else {
			marketingEventImpl.setTimeZoneId(timeZoneId);
		}

		if (startDate == Long.MIN_VALUE) {
			marketingEventImpl.setStartDate(null);
		}
		else {
			marketingEventImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			marketingEventImpl.setEndDate(null);
		}
		else {
			marketingEventImpl.setEndDate(new Date(endDate));
		}

		marketingEventImpl.setDateTBA(dateTBA);
		marketingEventImpl.setAddressId(addressId);
		marketingEventImpl.setGlobalRegion(globalRegion);
		marketingEventImpl.setOnline(online);
		marketingEventImpl.setRegistrationType(registrationType);

		if (registrationURL == null) {
			marketingEventImpl.setRegistrationURL(StringPool.BLANK);
		}
		else {
			marketingEventImpl.setRegistrationURL(registrationURL);
		}

		marketingEventImpl.resetOriginalValues();

		return marketingEventImpl;
	}

	public long marketingEventId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int type;
	public String defaultLanguageId;
	public String title;
	public String titleURL;
	public String hostedBy;
	public String hostedByURL;
	public String summary;
	public long imageFileEntryId;
	public long slidesFileEntryId;
	public String videoTitle;
	public String timeZoneId;
	public long startDate;
	public long endDate;
	public boolean dateTBA;
	public long addressId;
	public int globalRegion;
	public boolean online;
	public int registrationType;
	public String registrationURL;
}