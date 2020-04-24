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

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopUserNotificationEventException;
import com.liferay.osb.loop.model.LoopUserNotificationEvent;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the loop user notification event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.loop.service.persistence.impl.LoopUserNotificationEventPersistenceImpl
 * @see LoopUserNotificationEventUtil
 * @generated
 */
@ProviderType
public interface LoopUserNotificationEventPersistence extends BasePersistence<LoopUserNotificationEvent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopUserNotificationEventUtil} to access the loop user notification event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the loop user notification events where groupKey = &#63;.
	*
	* @param groupKey the group key
	* @return the matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGroupKey(
		long groupKey);

	/**
	* Returns a range of all the loop user notification events where groupKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupKey the group key
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @return the range of matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGroupKey(
		long groupKey, int start, int end);

	/**
	* Returns an ordered range of all the loop user notification events where groupKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupKey the group key
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGroupKey(
		long groupKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the loop user notification events where groupKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupKey the group key
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGroupKey(
		long groupKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first loop user notification event in the ordered set where groupKey = &#63;.
	*
	* @param groupKey the group key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching loop user notification event
	* @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent findByGroupKey_First(long groupKey,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException;

	/**
	* Returns the first loop user notification event in the ordered set where groupKey = &#63;.
	*
	* @param groupKey the group key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent fetchByGroupKey_First(long groupKey,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator);

	/**
	* Returns the last loop user notification event in the ordered set where groupKey = &#63;.
	*
	* @param groupKey the group key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching loop user notification event
	* @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent findByGroupKey_Last(long groupKey,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException;

	/**
	* Returns the last loop user notification event in the ordered set where groupKey = &#63;.
	*
	* @param groupKey the group key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent fetchByGroupKey_Last(long groupKey,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator);

	/**
	* Returns the loop user notification events before and after the current loop user notification event in the ordered set where groupKey = &#63;.
	*
	* @param loopUserNotificationEventId the primary key of the current loop user notification event
	* @param groupKey the group key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next loop user notification event
	* @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	*/
	public LoopUserNotificationEvent[] findByGroupKey_PrevAndNext(
		long loopUserNotificationEventId, long groupKey,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException;

	/**
	* Removes all the loop user notification events where groupKey = &#63; from the database.
	*
	* @param groupKey the group key
	*/
	public void removeByGroupKey(long groupKey);

	/**
	* Returns the number of loop user notification events where groupKey = &#63;.
	*
	* @param groupKey the group key
	* @return the number of matching loop user notification events
	*/
	public int countByGroupKey(long groupKey);

	/**
	* Returns all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @return the matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK);

	/**
	* Returns a range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @return the range of matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK, int start, int end);

	/**
	* Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching loop user notification event
	* @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent findByGCNI_GCP_First(
		long groupClassNameId, long groupClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException;

	/**
	* Returns the first loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent fetchByGCNI_GCP_First(
		long groupClassNameId, long groupClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator);

	/**
	* Returns the last loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching loop user notification event
	* @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent findByGCNI_GCP_Last(
		long groupClassNameId, long groupClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException;

	/**
	* Returns the last loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent fetchByGCNI_GCP_Last(
		long groupClassNameId, long groupClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator);

	/**
	* Returns the loop user notification events before and after the current loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	*
	* @param loopUserNotificationEventId the primary key of the current loop user notification event
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next loop user notification event
	* @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	*/
	public LoopUserNotificationEvent[] findByGCNI_GCP_PrevAndNext(
		long loopUserNotificationEventId, long groupClassNameId,
		long groupClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException;

	/**
	* Removes all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; from the database.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	*/
	public void removeByGCNI_GCP(long groupClassNameId, long groupClassPK);

	/**
	* Returns the number of loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @return the number of matching loop user notification events
	*/
	public int countByGCNI_GCP(long groupClassNameId, long groupClassPK);

	/**
	* Returns all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param type the type
	* @return the matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type);

	/**
	* Returns a range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param type the type
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @return the range of matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type, int start, int end);

	/**
	* Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param type the type
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param type the type
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching loop user notification event
	* @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent findByGCNI_GCP_T_First(
		long groupClassNameId, long groupClassPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException;

	/**
	* Returns the first loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent fetchByGCNI_GCP_T_First(
		long groupClassNameId, long groupClassPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator);

	/**
	* Returns the last loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching loop user notification event
	* @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent findByGCNI_GCP_T_Last(
		long groupClassNameId, long groupClassPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException;

	/**
	* Returns the last loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	*/
	public LoopUserNotificationEvent fetchByGCNI_GCP_T_Last(
		long groupClassNameId, long groupClassPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator);

	/**
	* Returns the loop user notification events before and after the current loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	*
	* @param loopUserNotificationEventId the primary key of the current loop user notification event
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next loop user notification event
	* @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	*/
	public LoopUserNotificationEvent[] findByGCNI_GCP_T_PrevAndNext(
		long loopUserNotificationEventId, long groupClassNameId,
		long groupClassPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws NoSuchLoopUserNotificationEventException;

	/**
	* Removes all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63; from the database.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param type the type
	*/
	public void removeByGCNI_GCP_T(long groupClassNameId, long groupClassPK,
		int type);

	/**
	* Returns the number of loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	*
	* @param groupClassNameId the group class name ID
	* @param groupClassPK the group class pk
	* @param type the type
	* @return the number of matching loop user notification events
	*/
	public int countByGCNI_GCP_T(long groupClassNameId, long groupClassPK,
		int type);

	/**
	* Caches the loop user notification event in the entity cache if it is enabled.
	*
	* @param loopUserNotificationEvent the loop user notification event
	*/
	public void cacheResult(LoopUserNotificationEvent loopUserNotificationEvent);

	/**
	* Caches the loop user notification events in the entity cache if it is enabled.
	*
	* @param loopUserNotificationEvents the loop user notification events
	*/
	public void cacheResult(
		java.util.List<LoopUserNotificationEvent> loopUserNotificationEvents);

	/**
	* Creates a new loop user notification event with the primary key. Does not add the loop user notification event to the database.
	*
	* @param loopUserNotificationEventId the primary key for the new loop user notification event
	* @return the new loop user notification event
	*/
	public LoopUserNotificationEvent create(long loopUserNotificationEventId);

	/**
	* Removes the loop user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopUserNotificationEventId the primary key of the loop user notification event
	* @return the loop user notification event that was removed
	* @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	*/
	public LoopUserNotificationEvent remove(long loopUserNotificationEventId)
		throws NoSuchLoopUserNotificationEventException;

	public LoopUserNotificationEvent updateImpl(
		LoopUserNotificationEvent loopUserNotificationEvent);

	/**
	* Returns the loop user notification event with the primary key or throws a {@link NoSuchLoopUserNotificationEventException} if it could not be found.
	*
	* @param loopUserNotificationEventId the primary key of the loop user notification event
	* @return the loop user notification event
	* @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	*/
	public LoopUserNotificationEvent findByPrimaryKey(
		long loopUserNotificationEventId)
		throws NoSuchLoopUserNotificationEventException;

	/**
	* Returns the loop user notification event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopUserNotificationEventId the primary key of the loop user notification event
	* @return the loop user notification event, or <code>null</code> if a loop user notification event with the primary key could not be found
	*/
	public LoopUserNotificationEvent fetchByPrimaryKey(
		long loopUserNotificationEventId);

	@Override
	public java.util.Map<java.io.Serializable, LoopUserNotificationEvent> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the loop user notification events.
	*
	* @return the loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findAll();

	/**
	* Returns a range of all the loop user notification events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @return the range of loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findAll(int start, int end);

	/**
	* Returns an ordered range of all the loop user notification events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator);

	/**
	* Returns an ordered range of all the loop user notification events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop user notification events
	* @param end the upper bound of the range of loop user notification events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop user notification events
	*/
	public java.util.List<LoopUserNotificationEvent> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the loop user notification events from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of loop user notification events.
	*
	* @return the number of loop user notification events
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}