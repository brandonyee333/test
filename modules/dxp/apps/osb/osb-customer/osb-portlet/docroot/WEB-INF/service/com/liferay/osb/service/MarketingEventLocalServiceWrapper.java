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

package com.liferay.osb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MarketingEventLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MarketingEventLocalService
 * @generated
 */
public class MarketingEventLocalServiceWrapper
	implements MarketingEventLocalService,
		ServiceWrapper<MarketingEventLocalService> {
	public MarketingEventLocalServiceWrapper(
		MarketingEventLocalService marketingEventLocalService) {
		_marketingEventLocalService = marketingEventLocalService;
	}

	/**
	* Adds the marketing event to the database. Also notifies the appropriate model listeners.
	*
	* @param marketingEvent the marketing event
	* @return the marketing event that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent addMarketingEvent(
		com.liferay.osb.model.MarketingEvent marketingEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.addMarketingEvent(marketingEvent);
	}

	/**
	* Creates a new marketing event with the primary key. Does not add the marketing event to the database.
	*
	* @param marketingEventId the primary key for the new marketing event
	* @return the new marketing event
	*/
	public com.liferay.osb.model.MarketingEvent createMarketingEvent(
		long marketingEventId) {
		return _marketingEventLocalService.createMarketingEvent(marketingEventId);
	}

	/**
	* Deletes the marketing event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param marketingEventId the primary key of the marketing event
	* @return the marketing event that was removed
	* @throws PortalException if a marketing event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent deleteMarketingEvent(
		long marketingEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.deleteMarketingEvent(marketingEventId);
	}

	/**
	* Deletes the marketing event from the database. Also notifies the appropriate model listeners.
	*
	* @param marketingEvent the marketing event
	* @return the marketing event that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent deleteMarketingEvent(
		com.liferay.osb.model.MarketingEvent marketingEvent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.deleteMarketingEvent(marketingEvent);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _marketingEventLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.MarketingEvent fetchMarketingEvent(
		long marketingEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.fetchMarketingEvent(marketingEventId);
	}

	/**
	* Returns the marketing event with the primary key.
	*
	* @param marketingEventId the primary key of the marketing event
	* @return the marketing event
	* @throws PortalException if a marketing event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent getMarketingEvent(
		long marketingEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.getMarketingEvent(marketingEventId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the marketing events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of marketing events
	* @param end the upper bound of the range of marketing events (not inclusive)
	* @return the range of marketing events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.MarketingEvent> getMarketingEvents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.getMarketingEvents(start, end);
	}

	/**
	* Returns the number of marketing events.
	*
	* @return the number of marketing events
	* @throws SystemException if a system exception occurred
	*/
	public int getMarketingEventsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.getMarketingEventsCount();
	}

	/**
	* Updates the marketing event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param marketingEvent the marketing event
	* @return the marketing event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent updateMarketingEvent(
		com.liferay.osb.model.MarketingEvent marketingEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.updateMarketingEvent(marketingEvent);
	}

	/**
	* Updates the marketing event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param marketingEvent the marketing event
	* @param merge whether to merge the marketing event with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the marketing event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent updateMarketingEvent(
		com.liferay.osb.model.MarketingEvent marketingEvent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.updateMarketingEvent(marketingEvent,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _marketingEventLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_marketingEventLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _marketingEventLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.MarketingEvent addMarketingEvent(long userId,
		int type, java.lang.String defaultLanguageId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.lang.String titleURL, java.lang.String hostedBy,
		java.lang.String hostedByURL,
		java.util.Map<java.util.Locale, java.lang.String> summaryMap,
		long imageFileEntryId, long slidesFileEntryId,
		java.lang.String videoTitle, java.lang.String timeZoneId,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
		boolean dateTBA, java.lang.String addressStreet1,
		java.lang.String addressStreet2, java.lang.String addressStreet3,
		java.lang.String addressCity, java.lang.String addressZip,
		long addressRegionId, long addressCountryId, int globalRegion,
		boolean online, int registrationType, java.lang.String registrationURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.addMarketingEvent(userId, type,
			defaultLanguageId, titleMap, titleURL, hostedBy, hostedByURL,
			summaryMap, imageFileEntryId, slidesFileEntryId, videoTitle,
			timeZoneId, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, dateTBA, addressStreet1,
			addressStreet2, addressStreet3, addressCity, addressZip,
			addressRegionId, addressCountryId, globalRegion, online,
			registrationType, registrationURL, serviceContext);
	}

	public java.util.List<com.liferay.osb.model.MarketingEvent> getMarketingEvents(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.getMarketingEvents(type, start, end,
			obc);
	}

	public java.util.List<com.liferay.osb.model.MarketingEvent> getMarketingEvents(
		int[] types, int[] globalRegions, long[] countryIds,
		int[] locationTypes, boolean pastEvents,
		java.lang.String userLanguageId, java.lang.String orderByCol,
		java.lang.String orderByType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.getMarketingEvents(types,
			globalRegions, countryIds, locationTypes, pastEvents,
			userLanguageId, orderByCol, orderByType);
	}

	public com.liferay.portal.kernel.search.Hits search(long companyId,
		java.lang.String title, java.lang.String summary, int[] types,
		int[] globalRegions, java.util.Date startDateGT,
		java.util.Date startDateLT, boolean andSearch, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.search(companyId, title, summary,
			types, globalRegions, startDateGT, startDateLT, andSearch, start,
			end, sort);
	}

	public com.liferay.osb.model.MarketingEvent updateMarketingEvent(
		long marketingEventId, int type, java.lang.String defaultLanguageId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.lang.String titleURL, java.lang.String hostedBy,
		java.lang.String hostedByURL,
		java.util.Map<java.util.Locale, java.lang.String> summaryMap,
		long imageFileEntryId, long slidesFileEntryId,
		java.lang.String videoTitle, java.lang.String timeZoneId,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
		boolean dateTBA, java.lang.String addressStreet1,
		java.lang.String addressStreet2, java.lang.String addressStreet3,
		java.lang.String addressCity, java.lang.String addressZip,
		long addressRegionId, long addressCountryId, int globalRegion,
		boolean online, int registrationType, java.lang.String registrationURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _marketingEventLocalService.updateMarketingEvent(marketingEventId,
			type, defaultLanguageId, titleMap, titleURL, hostedBy, hostedByURL,
			summaryMap, imageFileEntryId, slidesFileEntryId, videoTitle,
			timeZoneId, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, dateTBA, addressStreet1,
			addressStreet2, addressStreet3, addressCity, addressZip,
			addressRegionId, addressCountryId, globalRegion, online,
			registrationType, registrationURL, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public MarketingEventLocalService getWrappedMarketingEventLocalService() {
		return _marketingEventLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedMarketingEventLocalService(
		MarketingEventLocalService marketingEventLocalService) {
		_marketingEventLocalService = marketingEventLocalService;
	}

	public MarketingEventLocalService getWrappedService() {
		return _marketingEventLocalService;
	}

	public void setWrappedService(
		MarketingEventLocalService marketingEventLocalService) {
		_marketingEventLocalService = marketingEventLocalService;
	}

	private MarketingEventLocalService _marketingEventLocalService;
}