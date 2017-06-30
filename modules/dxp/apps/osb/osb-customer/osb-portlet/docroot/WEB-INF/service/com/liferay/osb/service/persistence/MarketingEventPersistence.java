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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the marketing event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MarketingEventPersistenceImpl
 * @see MarketingEventUtil
 * @generated
 */
public interface MarketingEventPersistence extends BasePersistence<MarketingEvent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MarketingEventUtil} to access the marketing event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the marketing event in the entity cache if it is enabled.
	*
	* @param marketingEvent the marketing event
	*/
	public void cacheResult(com.liferay.osb.model.MarketingEvent marketingEvent);

	/**
	* Caches the marketing events in the entity cache if it is enabled.
	*
	* @param marketingEvents the marketing events
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.MarketingEvent> marketingEvents);

	/**
	* Creates a new marketing event with the primary key. Does not add the marketing event to the database.
	*
	* @param marketingEventId the primary key for the new marketing event
	* @return the new marketing event
	*/
	public com.liferay.osb.model.MarketingEvent create(long marketingEventId);

	/**
	* Removes the marketing event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param marketingEventId the primary key of the marketing event
	* @return the marketing event that was removed
	* @throws com.liferay.osb.NoSuchMarketingEventException if a marketing event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent remove(long marketingEventId)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.MarketingEvent updateImpl(
		com.liferay.osb.model.MarketingEvent marketingEvent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the marketing event with the primary key or throws a {@link com.liferay.osb.NoSuchMarketingEventException} if it could not be found.
	*
	* @param marketingEventId the primary key of the marketing event
	* @return the marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a marketing event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent findByPrimaryKey(
		long marketingEventId)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the marketing event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param marketingEventId the primary key of the marketing event
	* @return the marketing event, or <code>null</code> if a marketing event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent fetchByPrimaryKey(
		long marketingEventId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the marketing events where type = &#63;.
	*
	* @param type the type
	* @return the matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.MarketingEvent> findByType(
		int type) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.MarketingEvent> findByType(
		int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.MarketingEvent> findByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first marketing event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent findByType_First(int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first marketing event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marketing event, or <code>null</code> if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent fetchByType_First(int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last marketing event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent findByType_Last(int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last marketing event in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marketing event, or <code>null</code> if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent fetchByType_Last(int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.MarketingEvent[] findByType_PrevAndNext(
		long marketingEventId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the marketing events where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @return the matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.MarketingEvent> findByGlobalRegion(
		int globalRegion)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.MarketingEvent> findByGlobalRegion(
		int globalRegion, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.MarketingEvent> findByGlobalRegion(
		int globalRegion, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first marketing event in the ordered set where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent findByGlobalRegion_First(
		int globalRegion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first marketing event in the ordered set where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marketing event, or <code>null</code> if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent fetchByGlobalRegion_First(
		int globalRegion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last marketing event in the ordered set where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marketing event
	* @throws com.liferay.osb.NoSuchMarketingEventException if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent findByGlobalRegion_Last(
		int globalRegion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last marketing event in the ordered set where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marketing event, or <code>null</code> if a matching marketing event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.MarketingEvent fetchByGlobalRegion_Last(
		int globalRegion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.MarketingEvent[] findByGlobalRegion_PrevAndNext(
		long marketingEventId, int globalRegion,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchMarketingEventException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the marketing events.
	*
	* @return the marketing events
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.MarketingEvent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.MarketingEvent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.MarketingEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the marketing events where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the marketing events where globalRegion = &#63; from the database.
	*
	* @param globalRegion the global region
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGlobalRegion(int globalRegion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the marketing events from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of marketing events where type = &#63;.
	*
	* @param type the type
	* @return the number of matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public int countByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of marketing events where globalRegion = &#63;.
	*
	* @param globalRegion the global region
	* @return the number of matching marketing events
	* @throws SystemException if a system exception occurred
	*/
	public int countByGlobalRegion(int globalRegion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of marketing events.
	*
	* @return the number of marketing events
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}