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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.MarketingEvent;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the marketing event service. This utility wraps {@link MarketingEventPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MarketingEventPersistence
 * @see MarketingEventPersistenceImpl
 * @generated
 */
public class MarketingEventUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(MarketingEvent marketingEvent) {
		getPersistence().clearCache(marketingEvent);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MarketingEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MarketingEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MarketingEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static MarketingEvent update(MarketingEvent marketingEvent,
		boolean merge) throws SystemException {
		return getPersistence().update(marketingEvent, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static MarketingEvent update(MarketingEvent marketingEvent,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(marketingEvent, merge, serviceContext);
	}

	/**
	* Caches the marketing event in the entity cache if it is enabled.
	*
	* @param marketingEvent the marketing event
	*/
	public static void cacheResult(
		com.liferay.osb.model.MarketingEvent marketingEvent) {
		getPersistence().cacheResult(marketingEvent);
	}

	/**
	* Caches the marketing events in the entity cache if it is enabled.
	*
	* @param marketingEvents the marketing events
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.MarketingEvent> marketingEvents) {
		getPersistence().cacheResult(marketingEvents);
	}

	/**
	* Creates a new marketing event with the primary key. Does not add the marketing event to the database.
	*
	* @param marketingEventId the primary key for the new marketing event
	* @return the new marketing event
	*/
	public static com.liferay.osb.model.MarketingEvent create(
		long marketingEventId) {
		return getPersistence().create(marketingEventId);
	}

	/**
	* Removes the marketing event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param marketingEventId the primary key of the marketing event
	* @return the marketing event that was removed
	* @throws com.liferay.osb.NoSuchMarketingEventException if a marketing event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent remove(
		long marketingEventId)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(marketingEventId);
	}

	public static com.liferay.osb.model.MarketingEvent updateImpl(
		com.liferay.osb.model.MarketingEvent marketingEvent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(marketingEvent, merge);
	}

	/**
	* Returns the marketing event with the primary key or throws a {@link com.liferay.osb.NoSuchMarketingEventException} if it could not be found.
	*
	* @param marketingEventId the primary key of the marketing event
	* @return the marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a marketing event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent findByPrimaryKey(
		long marketingEventId)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(marketingEventId);
	}

	/**
	* Returns the marketing event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param marketingEventId the primary key of the marketing event
	* @return the marketing event, or <code>null</code> if a marketing event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent fetchByPrimaryKey(
		long marketingEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(marketingEventId);
	}

	/**
	* Returns all the marketing events where type = &#63;.
	*
	* @param type the type
	* @return the matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.MarketingEvent> findByType(
		int type) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type);
	}

	/**
	* Returns a range of all the marketing events where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of marketing events
	* @param end the upper bound of the range of marketing events (not inclusive)
	* @return the range of matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.MarketingEvent> findByType(
		int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end);
	}

	/**
	* Returns an ordered range of all the marketing events where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of marketing events
	* @param end the upper bound of the range of marketing events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.MarketingEvent> findByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	* Returns the first marketing event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent findByType_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	* Returns the first marketing event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marketing event, or <code>null</code> if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent fetchByType_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	* Returns the last marketing event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent findByType_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	* Returns the last marketing event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marketing event, or <code>null</code> if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent fetchByType_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	* Returns the marketing events before and after the current marketing event in the ordered set where type = &#63;.
	*
	* @param marketingEventId the primary key of the current marketing event
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a marketing event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent[] findByType_PrevAndNext(
		long marketingEventId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByType_PrevAndNext(marketingEventId, type,
			orderByComparator);
	}

	/**
	* Returns all the marketing events where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @return the matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.MarketingEvent> findByGlobalRegion(
		int globalRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGlobalRegion(globalRegion);
	}

	/**
	* Returns a range of all the marketing events where globalRegion = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param globalRegion the global region
	* @param start the lower bound of the range of marketing events
	* @param end the upper bound of the range of marketing events (not inclusive)
	* @return the range of matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.MarketingEvent> findByGlobalRegion(
		int globalRegion, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGlobalRegion(globalRegion, start, end);
	}

	/**
	* Returns an ordered range of all the marketing events where globalRegion = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param globalRegion the global region
	* @param start the lower bound of the range of marketing events
	* @param end the upper bound of the range of marketing events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.MarketingEvent> findByGlobalRegion(
		int globalRegion, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGlobalRegion(globalRegion, start, end,
			orderByComparator);
	}

	/**
	* Returns the first marketing event in the ordered set where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent findByGlobalRegion_First(
		int globalRegion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGlobalRegion_First(globalRegion, orderByComparator);
	}

	/**
	* Returns the first marketing event in the ordered set where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marketing event, or <code>null</code> if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent fetchByGlobalRegion_First(
		int globalRegion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGlobalRegion_First(globalRegion, orderByComparator);
	}

	/**
	* Returns the last marketing event in the ordered set where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent findByGlobalRegion_Last(
		int globalRegion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGlobalRegion_Last(globalRegion, orderByComparator);
	}

	/**
	* Returns the last marketing event in the ordered set where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marketing event, or <code>null</code> if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent fetchByGlobalRegion_Last(
		int globalRegion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGlobalRegion_Last(globalRegion, orderByComparator);
	}

	/**
	* Returns the marketing events before and after the current marketing event in the ordered set where globalRegion = &#63;.
	*
	* @param marketingEventId the primary key of the current marketing event
	* @param globalRegion the global region
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a marketing event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.MarketingEvent[] findByGlobalRegion_PrevAndNext(
		long marketingEventId, int globalRegion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGlobalRegion_PrevAndNext(marketingEventId,
			globalRegion, orderByComparator);
	}

	/**
	* Returns all the marketing events.
	*
	* @return the marketing events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.MarketingEvent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.osb.model.MarketingEvent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the marketing events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of marketing events
	* @param end the upper bound of the range of marketing events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of marketing events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.MarketingEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the marketing events where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByType(type);
	}

	/**
	* Removes all the marketing events where globalRegion = &#63; from the database.
	*
	* @param globalRegion the global region
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGlobalRegion(int globalRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGlobalRegion(globalRegion);
	}

	/**
	* Removes all the marketing events from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of marketing events where type = &#63;.
	*
	* @param type the type
	* @return the number of matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByType(type);
	}

	/**
	* Returns the number of marketing events where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @return the number of matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGlobalRegion(int globalRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGlobalRegion(globalRegion);
	}

	/**
	* Returns the number of marketing events.
	*
	* @return the number of marketing events
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MarketingEventPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MarketingEventPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					MarketingEventPersistence.class.getName());

			ReferenceRegistry.registerReference(MarketingEventUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(MarketingEventPersistence persistence) {
	}

	private static MarketingEventPersistence _persistence;
}