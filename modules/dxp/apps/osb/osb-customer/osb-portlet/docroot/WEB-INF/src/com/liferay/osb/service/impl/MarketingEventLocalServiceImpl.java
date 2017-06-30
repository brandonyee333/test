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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.MarketingEventEndDateException;
import com.liferay.osb.MarketingEventHostedByException;
import com.liferay.osb.MarketingEventHostedByURLException;
import com.liferay.osb.MarketingEventRegistrationURLException;
import com.liferay.osb.MarketingEventStartDateException;
import com.liferay.osb.MarketingEventTitleException;
import com.liferay.osb.MarketingEventTitleURLException;
import com.liferay.osb.MarketingEventVideoTitleException;
import com.liferay.osb.model.MarketingEvent;
import com.liferay.osb.model.MarketingEventConstants;
import com.liferay.osb.service.base.MarketingEventLocalServiceBaseImpl;
import com.liferay.osb.util.comparator.MarketingEventStartDateComparator;
import com.liferay.osb.util.comparator.MarketingEventTypeComparator;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author Rachael Koestartyo
 */
public class MarketingEventLocalServiceImpl
	extends MarketingEventLocalServiceBaseImpl {

	public MarketingEvent addMarketingEvent(
			long userId, int type, String defaultLanguageId,
			Map<Locale, String> titleMap, String titleURL, String hostedBy,
			String hostedByURL, Map<Locale, String> summaryMap,
			long imageFileEntryId, long slidesFileEntryId, String videoTitle,
			String timeZoneId, int startDateMonth, int startDateDay,
			int startDateYear, int startDateHour, int startDateMinute,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, boolean dateTBA, String addressStreet1,
			String addressStreet2, String addressStreet3, String addressCity,
			String addressZip, long addressRegionId, long addressCountryId,
			int globalRegion, boolean online, int registrationType,
			String registrationURL, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Marketing event

		User user = userPersistence.findByPrimaryKey(userId);
		TimeZone timeZone = TimeZoneUtil.getTimeZone(timeZoneId);
		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, timeZone, MarketingEventStartDateException.class);

		Date endDate = null;

		if (!dateTBA) {
			endDate = PortalUtil.getDate(
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, timeZone, MarketingEventEndDateException.class);
		}

		if (online) {
			globalRegion = MarketingEventConstants.GLOBAL_REGION_WORLDWIDE;
		}

		Date now = new Date();

		validate(
			defaultLanguageId, titleMap, titleURL, hostedBy, hostedByURL,
			summaryMap, videoTitle, registrationURL);

		long marketingEventId = counterLocalService.increment();

		MarketingEvent marketingEvent = marketingEventPersistence.create(
			marketingEventId);

		marketingEvent.setCompanyId(user.getCompanyId());
		marketingEvent.setUserId(user.getUserId());
		marketingEvent.setUserName(user.getFullName());
		marketingEvent.setCreateDate(serviceContext.getCreateDate(now));
		marketingEvent.setModifiedDate(serviceContext.getModifiedDate(now));
		marketingEvent.setType(type);
		marketingEvent.setDefaultLanguageId(defaultLanguageId);
		marketingEvent.setTitleMap(
			titleMap, LocaleUtil.fromLanguageId(defaultLanguageId));
		marketingEvent.setTitleURL(titleURL);
		marketingEvent.setHostedBy(hostedBy);
		marketingEvent.setHostedByURL(hostedByURL);
		marketingEvent.setSummaryMap(
			summaryMap, LocaleUtil.fromLanguageId(defaultLanguageId));
		marketingEvent.setImageFileEntryId(imageFileEntryId);
		marketingEvent.setSlidesFileEntryId(slidesFileEntryId);
		marketingEvent.setVideoTitle(videoTitle);
		marketingEvent.setTimeZoneId(timeZoneId);
		marketingEvent.setStartDate(startDate);
		marketingEvent.setEndDate(endDate);
		marketingEvent.setDateTBA(dateTBA);
		marketingEvent.setGlobalRegion(globalRegion);
		marketingEvent.setOnline(online);
		marketingEvent.setRegistrationType(registrationType);
		marketingEvent.setRegistrationURL(registrationURL);

		// Address

		Address address = addAddress(
			user.getUserId(), marketingEvent.getMarketingEventId(),
			addressStreet1, addressStreet2, addressStreet3, addressCity,
			addressZip, addressRegionId, addressCountryId,
			marketingEvent.isOnline());

		if (address != null) {
			marketingEvent.setAddressId(address.getAddressId());
		}
		else {
			marketingEvent.setAddressId(
				MarketingEventConstants.DEFAULT_ADDRESS_ID);
		}

		marketingEventPersistence.update(marketingEvent, false);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			MarketingEvent.class);

		indexer.reindex(marketingEvent);

		return marketingEvent;
	}

	@Override
	public MarketingEvent deleteMarketingEvent(long marketingEventId)
		throws PortalException, SystemException {

		MarketingEvent marketingEvent =
			marketingEventPersistence.findByPrimaryKey(marketingEventId);

		return deleteMarketingEvent(marketingEvent);
	}

	@Override
	public MarketingEvent deleteMarketingEvent(MarketingEvent marketingEvent)
		throws PortalException, SystemException {

		// Marketing event

		marketingEventPersistence.remove(marketingEvent);

		// Address

		deleteAddress(marketingEvent.getAddressId());

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			MarketingEvent.class);

		indexer.delete(marketingEvent);

		return marketingEvent;
	}

	public List<MarketingEvent> getMarketingEvents(
			int type, int start, int end, OrderByComparator obc)
		throws SystemException {

		return marketingEventPersistence.findByType(type, start, end, obc);
	}

	public List<MarketingEvent> getMarketingEvents(
			int[] types, int[] globalRegions, long[] countryIds,
			int[] locationTypes, boolean pastEvents, String userLanguageId,
			String orderByCol, String orderByType)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			types, globalRegions, countryIds, locationTypes, pastEvents);

		List<MarketingEvent> marketingEvents = dynamicQuery(dynamicQuery);

		return sort(marketingEvents, userLanguageId, orderByCol, orderByType);
	}

	public Hits search(
			long companyId, String title, String summary, int[] types,
			int[] globalRegions, Date startDateGT, Date startDateLT,
			boolean andSearch, int start, int end, Sort sort)
		throws SystemException {

		try {
			SearchContext searchContext = new SearchContext();

			searchContext.setAndSearch(andSearch);

			Map<String, Serializable> attributes =
				new HashMap<String, Serializable>();

			attributes.put("globalRegion", globalRegions);
			attributes.put("startDateGT", startDateGT);
			attributes.put("startDateLT", startDateLT);
			attributes.put("summary", summary);
			attributes.put("title", title);
			attributes.put("type", types);

			searchContext.setAttributes(attributes);

			searchContext.setCompanyId(companyId);
			searchContext.setEnd(end);

			QueryConfig queryConfig = new QueryConfig();

			queryConfig.setHighlightEnabled(false);
			queryConfig.setScoreEnabled(false);

			searchContext.setQueryConfig(queryConfig);

			if (sort != null) {
				searchContext.setSorts(new Sort[] {sort});
			}

			searchContext.setStart(start);

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				MarketingEvent.class);

			return indexer.search(searchContext);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public MarketingEvent updateMarketingEvent(
			long marketingEventId, int type, String defaultLanguageId,
			Map<Locale, String> titleMap, String titleURL, String hostedBy,
			String hostedByURL, Map<Locale, String> summaryMap,
			long imageFileEntryId, long slidesFileEntryId, String videoTitle,
			String timeZoneId, int startDateMonth, int startDateDay,
			int startDateYear, int startDateHour, int startDateMinute,
			int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute, boolean dateTBA, String addressStreet1,
			String addressStreet2, String addressStreet3, String addressCity,
			String addressZip, long addressRegionId, long addressCountryId,
			int globalRegion, boolean online, int registrationType,
			String registrationURL, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Marketing event

		TimeZone timeZone = TimeZoneUtil.getTimeZone(timeZoneId);
		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, timeZone, MarketingEventStartDateException.class);

		Date endDate = null;

		if (!dateTBA) {
			endDate = PortalUtil.getDate(
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, timeZone, MarketingEventEndDateException.class);
		}

		if (online) {
			globalRegion = MarketingEventConstants.GLOBAL_REGION_WORLDWIDE;
		}

		validate(
			defaultLanguageId, titleMap, titleURL, hostedBy, hostedByURL,
			summaryMap, videoTitle, registrationURL);

		MarketingEvent marketingEvent =
			marketingEventPersistence.findByPrimaryKey(marketingEventId);

		marketingEvent.setModifiedDate(serviceContext.getModifiedDate(null));
		marketingEvent.setType(type);
		marketingEvent.setDefaultLanguageId(defaultLanguageId);
		marketingEvent.setTitleMap(
			titleMap, LocaleUtil.fromLanguageId(defaultLanguageId));
		marketingEvent.setTitleURL(titleURL);
		marketingEvent.setHostedBy(hostedBy);
		marketingEvent.setHostedByURL(hostedByURL);
		marketingEvent.setSummaryMap(
			summaryMap, LocaleUtil.fromLanguageId(defaultLanguageId));
		marketingEvent.setImageFileEntryId(imageFileEntryId);
		marketingEvent.setSlidesFileEntryId(slidesFileEntryId);
		marketingEvent.setVideoTitle(videoTitle);
		marketingEvent.setTimeZoneId(timeZoneId);
		marketingEvent.setStartDate(startDate);
		marketingEvent.setEndDate(endDate);
		marketingEvent.setDateTBA(dateTBA);
		marketingEvent.setGlobalRegion(globalRegion);
		marketingEvent.setOnline(online);
		marketingEvent.setRegistrationType(registrationType);
		marketingEvent.setRegistrationURL(registrationURL);

		// Address

		Address address = updateAddress(
			addressStreet1, addressStreet2, addressStreet3, addressCity,
			addressZip, addressRegionId, addressCountryId, marketingEvent);

		if (address != null) {
			marketingEvent.setAddressId(address.getAddressId());
		}
		else {
			marketingEvent.setAddressId(
				MarketingEventConstants.DEFAULT_ADDRESS_ID);
		}

		marketingEventPersistence.update(marketingEvent, false);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			MarketingEvent.class);

		indexer.reindex(marketingEvent);

		return marketingEvent;
	}

	protected Address addAddress(
			long userId, long marketingEventId, String addressStreet1,
			String addressStreet2, String addressStreet3, String addressCity,
			String addressZip, long addressRegionId, long addressCountryId,
			boolean online)
		throws PortalException, SystemException {

		if (online) {
			return null;
		}

		if (Validator.isNull(addressStreet1) &&
			Validator.isNull(addressStreet2) &&
			Validator.isNull(addressStreet3) && Validator.isNull(addressCity) &&
			Validator.isNull(addressZip) && (addressRegionId == 0) &&
			(addressCountryId == 0)) {

			return null;
		}

		if (Validator.isNull(addressZip)) {
			addressZip = MarketingEventConstants.ADDRESS_ZIP_NOT_AVAILABLE;
		}

		return addressLocalService.addAddress(
			userId, MarketingEvent.class.getName(), marketingEventId,
			addressStreet1, addressStreet2, addressStreet3, addressCity,
			addressZip, addressRegionId, addressCountryId, 0, false, true);
	}

	protected DynamicQuery buildDynamicQuery(
			int[] types, int[] globalRegions, long[] countryIds,
			int[] locationTypes, boolean pastEvents)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			MarketingEvent.class, getClassLoader());

		Junction junction = RestrictionsFactoryUtil.conjunction();

		if ((types != null) && (types.length > 0)) {
			Property typeProperty = PropertyFactoryUtil.forName("type");

			junction.add(in(typeProperty, types));
		}

		if ((globalRegions != null) && (globalRegions.length > 0) &&
			!ArrayUtil.contains(
				globalRegions,
				MarketingEventConstants.GLOBAL_REGION_WORLDWIDE)) {

			Property globalRegionProperty = PropertyFactoryUtil.forName(
				"globalRegion");

			int[] globalRegionValues = ArrayUtil.append(
				globalRegions, MarketingEventConstants.GLOBAL_REGION_WORLDWIDE);

			junction.add(in(globalRegionProperty, globalRegionValues));
		}

		if ((countryIds != null) && (countryIds.length > 0)) {
			List<Address> addresses = getAddresses(countryIds);

			long[] addressIds = StringUtil.split(
				ListUtil.toString(addresses, "addressId"), 0L);

			long[] addressIdValues = ArrayUtil.append(
				addressIds, MarketingEventConstants.DEFAULT_ADDRESS_ID);

			Property addressIdProperty = PropertyFactoryUtil.forName(
				"addressId");

			junction.add(in(addressIdProperty, addressIdValues));
		}

		if ((locationTypes != null) && (locationTypes.length > 0)) {
			boolean[] onlineValues = new boolean[0];

			if (ArrayUtil.contains(
					locationTypes,
					MarketingEventConstants.LOCATION_TYPE_IN_PERSON)) {

				onlineValues = ArrayUtil.append(onlineValues, Boolean.FALSE);
			}

			if (ArrayUtil.contains(
					locationTypes,
					MarketingEventConstants.LOCATION_TYPE_ONLINE)) {

				onlineValues = ArrayUtil.append(onlineValues, Boolean.TRUE);
			}

			Property onlineProperty = PropertyFactoryUtil.forName("online");

			junction.add(in(onlineProperty, onlineValues));
		}

		Property endDateProperty = PropertyFactoryUtil.forName("endDate");

		Date now = new Date();

		if (pastEvents) {
			junction.add(endDateProperty.lt(now));
		}
		else {
			junction.add(endDateProperty.ge(now));
		}

		return dynamicQuery.add(junction);
	}

	protected Address deleteAddress(long addressId)
		throws PortalException, SystemException {

		Address address = addressLocalService.fetchAddress(addressId);

		if (address == null) {
			return null;
		}

		return addressLocalService.deleteAddress(addressId);
	}

	protected List<Address> getAddresses(long[] countryIds)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Address.class, PortalClassLoaderUtil.getClassLoader());

		long classNameId = PortalUtil.getClassNameId(
			MarketingEvent.class.getName());

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("classNameId", classNameId));

		Property property = PropertyFactoryUtil.forName("countryId");

		dynamicQuery.add(in(property, countryIds));

		return addressLocalService.dynamicQuery(dynamicQuery);
	}

	protected Criterion in(Property property, Object values) {
		List<Object> list = new ArrayList<Object>();

		if (values instanceof boolean[]) {
			list.addAll(ListUtil.toList((boolean[])values));
		}
		else if (values instanceof int[]) {
			list.addAll(ListUtil.toList((int[])values));
		}
		else if (values instanceof long[]) {
			list.addAll(ListUtil.toList((long[])values));
		}

		return property.in(list);
	}

	protected List<MarketingEvent> sort(
		List<MarketingEvent> marketingEvents, String userLanguageId,
		String orderByCol, String orderByType) {

		Locale locale = LocaleUtil.fromLanguageId(userLanguageId, false);

		boolean asc = false;

		if (StringUtil.equalsIgnoreCase(
				orderByType, MarketingEventConstants.ORDER_BY_TYPE_ASC)) {

			asc = true;
		}

		if (StringUtil.contains(orderByCol, StringPool.DASH)) {
			orderByCol = TextFormatter.format(orderByCol, TextFormatter.M);
		}

		Comparator<MarketingEvent> comparator = null;

		if (StringUtil.equalsIgnoreCase(orderByCol, "type")) {
			comparator = new MarketingEventTypeComparator(locale, asc);
		}
		else {
			comparator = new MarketingEventStartDateComparator(locale, asc);
		}

		return ListUtil.sort(marketingEvents, comparator);
	}

	protected Address updateAddress(
			String addressStreet1, String addressStreet2, String addressStreet3,
			String addressCity, String addressZip, long addressRegionId,
			long addressCountryId, MarketingEvent marketingEvent)
		throws PortalException, SystemException {

		if (marketingEvent.getAddressId() ==
				MarketingEventConstants.DEFAULT_ADDRESS_ID) {

			long userId = marketingEvent.getUserId();
			long marketingEventId = marketingEvent.getMarketingEventId();
			boolean online = marketingEvent.isOnline();

			return addAddress(
				userId, marketingEventId, addressStreet1, addressStreet2,
				addressStreet3, addressCity, addressZip, addressRegionId,
				addressCountryId, online);
		}

		if (marketingEvent.isOnline()) {
			return deleteAddress(marketingEvent.getAddressId());
		}

		if (Validator.isNull(addressStreet1) &&
			Validator.isNull(addressStreet2) &&
			Validator.isNull(addressStreet3) && Validator.isNull(addressCity) &&
			Validator.isNull(addressZip) && (addressRegionId == 0) &&
			(addressCountryId == 0)) {

			return deleteAddress(marketingEvent.getAddressId());
		}

		if (Validator.isNull(addressZip)) {
			addressZip = MarketingEventConstants.ADDRESS_ZIP_NOT_AVAILABLE;
		}

		return addressLocalService.updateAddress(
			marketingEvent.getAddressId(), addressStreet1, addressStreet2,
			addressStreet3, addressCity, addressZip, addressRegionId,
			addressCountryId, 0, false, true);
	}

	protected void validate(
			String defaultLanguageId, Map<Locale, String> titleMap,
			String titleURL, String hostedBy, String hostedByURL,
			Map<Locale, String> summaryMap, String videoTitle,
			String registrationURL)
		throws PortalException {

		Set<Locale> locales = new HashSet<Locale>();

		locales.addAll(titleMap.keySet());
		locales.addAll(summaryMap.keySet());

		if (locales.isEmpty()) {
			throw new MarketingEventTitleException(
				ListUtil.toList(new String[] {defaultLanguageId}));
		}

		List<String> languageIds = new ArrayList<String>();

		for (Locale locale : locales) {
			if (Validator.isNull(titleMap.get(locale))) {
				languageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		if (!languageIds.isEmpty()) {
			throw new MarketingEventTitleException(languageIds);
		}

		if (Validator.isNotNull(titleURL) && !Validator.isUrl(titleURL)) {
			throw new MarketingEventTitleURLException();
		}

		if (Validator.isNull(hostedBy) && Validator.isNotNull(hostedByURL)) {
			throw new MarketingEventHostedByException();
		}

		if (Validator.isNotNull(hostedByURL) && !Validator.isUrl(hostedByURL)) {
			throw new MarketingEventHostedByURLException();
		}

		if (Validator.isNotNull(videoTitle)) {
			String s = videoTitle;

			s = FileUtil.getShortFileName(videoTitle);
			s = FileUtil.stripExtension(videoTitle);

			if (!videoTitle.equals(s)) {
				throw new MarketingEventVideoTitleException();
			}
		}

		if (Validator.isNotNull(registrationURL) &&
			!Validator.isUrl(registrationURL)) {

			throw new MarketingEventRegistrationURLException();
		}
	}

}