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

package com.liferay.osb.loop.service.persistence;

import com.liferay.osb.loop.model.LoopUserNotificationEvent;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the loop user notification event service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopUserNotificationEventPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationEventPersistence
 * @generated
 */
public class LoopUserNotificationEventUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		LoopUserNotificationEvent loopUserNotificationEvent) {

		getPersistence().clearCache(loopUserNotificationEvent);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, LoopUserNotificationEvent>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoopUserNotificationEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopUserNotificationEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopUserNotificationEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopUserNotificationEvent update(
		LoopUserNotificationEvent loopUserNotificationEvent) {

		return getPersistence().update(loopUserNotificationEvent);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopUserNotificationEvent update(
		LoopUserNotificationEvent loopUserNotificationEvent,
		ServiceContext serviceContext) {

		return getPersistence().update(
			loopUserNotificationEvent, serviceContext);
	}

	/**
	 * Returns all the loop user notification events where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @return the matching loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findByGroupKey(
		long groupKey) {

		return getPersistence().findByGroupKey(groupKey);
	}

	/**
	 * Returns a range of all the loop user notification events where groupKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param groupKey the group key
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @return the range of matching loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findByGroupKey(
		long groupKey, int start, int end) {

		return getPersistence().findByGroupKey(groupKey, start, end);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param groupKey the group key
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findByGroupKey(
		long groupKey, int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {

		return getPersistence().findByGroupKey(
			groupKey, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param groupKey the group key
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findByGroupKey(
		long groupKey, int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupKey(
			groupKey, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first loop user notification event in the ordered set where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	 */
	public static LoopUserNotificationEvent findByGroupKey_First(
			long groupKey,
			OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationEventException {

		return getPersistence().findByGroupKey_First(
			groupKey, orderByComparator);
	}

	/**
	 * Returns the first loop user notification event in the ordered set where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	public static LoopUserNotificationEvent fetchByGroupKey_First(
		long groupKey,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {

		return getPersistence().fetchByGroupKey_First(
			groupKey, orderByComparator);
	}

	/**
	 * Returns the last loop user notification event in the ordered set where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	 */
	public static LoopUserNotificationEvent findByGroupKey_Last(
			long groupKey,
			OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationEventException {

		return getPersistence().findByGroupKey_Last(
			groupKey, orderByComparator);
	}

	/**
	 * Returns the last loop user notification event in the ordered set where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	public static LoopUserNotificationEvent fetchByGroupKey_Last(
		long groupKey,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {

		return getPersistence().fetchByGroupKey_Last(
			groupKey, orderByComparator);
	}

	/**
	 * Returns the loop user notification events before and after the current loop user notification event in the ordered set where groupKey = &#63;.
	 *
	 * @param loopUserNotificationEventId the primary key of the current loop user notification event
	 * @param groupKey the group key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	 */
	public static LoopUserNotificationEvent[] findByGroupKey_PrevAndNext(
			long loopUserNotificationEventId, long groupKey,
			OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationEventException {

		return getPersistence().findByGroupKey_PrevAndNext(
			loopUserNotificationEventId, groupKey, orderByComparator);
	}

	/**
	 * Removes all the loop user notification events where groupKey = &#63; from the database.
	 *
	 * @param groupKey the group key
	 */
	public static void removeByGroupKey(long groupKey) {
		getPersistence().removeByGroupKey(groupKey);
	}

	/**
	 * Returns the number of loop user notification events where groupKey = &#63;.
	 *
	 * @param groupKey the group key
	 * @return the number of matching loop user notification events
	 */
	public static int countByGroupKey(long groupKey) {
		return getPersistence().countByGroupKey(groupKey);
	}

	/**
	 * Returns all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @return the matching loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK) {

		return getPersistence().findByGCNI_GCP(groupClassNameId, groupClassPK);
	}

	/**
	 * Returns a range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @return the range of matching loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK, int start, int end) {

		return getPersistence().findByGCNI_GCP(
			groupClassNameId, groupClassPK, start, end);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK, int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {

		return getPersistence().findByGCNI_GCP(
			groupClassNameId, groupClassPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findByGCNI_GCP(
		long groupClassNameId, long groupClassPK, int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGCNI_GCP(
			groupClassNameId, groupClassPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	 */
	public static LoopUserNotificationEvent findByGCNI_GCP_First(
			long groupClassNameId, long groupClassPK,
			OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationEventException {

		return getPersistence().findByGCNI_GCP_First(
			groupClassNameId, groupClassPK, orderByComparator);
	}

	/**
	 * Returns the first loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	public static LoopUserNotificationEvent fetchByGCNI_GCP_First(
		long groupClassNameId, long groupClassPK,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {

		return getPersistence().fetchByGCNI_GCP_First(
			groupClassNameId, groupClassPK, orderByComparator);
	}

	/**
	 * Returns the last loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a matching loop user notification event could not be found
	 */
	public static LoopUserNotificationEvent findByGCNI_GCP_Last(
			long groupClassNameId, long groupClassPK,
			OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationEventException {

		return getPersistence().findByGCNI_GCP_Last(
			groupClassNameId, groupClassPK, orderByComparator);
	}

	/**
	 * Returns the last loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	public static LoopUserNotificationEvent fetchByGCNI_GCP_Last(
		long groupClassNameId, long groupClassPK,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {

		return getPersistence().fetchByGCNI_GCP_Last(
			groupClassNameId, groupClassPK, orderByComparator);
	}

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
	public static LoopUserNotificationEvent[] findByGCNI_GCP_PrevAndNext(
			long loopUserNotificationEventId, long groupClassNameId,
			long groupClassPK,
			OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationEventException {

		return getPersistence().findByGCNI_GCP_PrevAndNext(
			loopUserNotificationEventId, groupClassNameId, groupClassPK,
			orderByComparator);
	}

	/**
	 * Removes all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; from the database.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 */
	public static void removeByGCNI_GCP(
		long groupClassNameId, long groupClassPK) {

		getPersistence().removeByGCNI_GCP(groupClassNameId, groupClassPK);
	}

	/**
	 * Returns the number of loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @return the number of matching loop user notification events
	 */
	public static int countByGCNI_GCP(
		long groupClassNameId, long groupClassPK) {

		return getPersistence().countByGCNI_GCP(groupClassNameId, groupClassPK);
	}

	/**
	 * Returns all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @return the matching loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type) {

		return getPersistence().findByGCNI_GCP_T(
			groupClassNameId, groupClassPK, type);
	}

	/**
	 * Returns a range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @return the range of matching loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type, int start,
		int end) {

		return getPersistence().findByGCNI_GCP_T(
			groupClassNameId, groupClassPK, type, start, end);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
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
	public static List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type, int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {

		return getPersistence().findByGCNI_GCP_T(
			groupClassNameId, groupClassPK, type, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type, int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGCNI_GCP_T(
			groupClassNameId, groupClassPK, type, start, end, orderByComparator,
			useFinderCache);
	}

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
	public static LoopUserNotificationEvent findByGCNI_GCP_T_First(
			long groupClassNameId, long groupClassPK, int type,
			OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationEventException {

		return getPersistence().findByGCNI_GCP_T_First(
			groupClassNameId, groupClassPK, type, orderByComparator);
	}

	/**
	 * Returns the first loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	public static LoopUserNotificationEvent fetchByGCNI_GCP_T_First(
		long groupClassNameId, long groupClassPK, int type,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {

		return getPersistence().fetchByGCNI_GCP_T_First(
			groupClassNameId, groupClassPK, type, orderByComparator);
	}

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
	public static LoopUserNotificationEvent findByGCNI_GCP_T_Last(
			long groupClassNameId, long groupClassPK, int type,
			OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationEventException {

		return getPersistence().findByGCNI_GCP_T_Last(
			groupClassNameId, groupClassPK, type, orderByComparator);
	}

	/**
	 * Returns the last loop user notification event in the ordered set where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching loop user notification event, or <code>null</code> if a matching loop user notification event could not be found
	 */
	public static LoopUserNotificationEvent fetchByGCNI_GCP_T_Last(
		long groupClassNameId, long groupClassPK, int type,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {

		return getPersistence().fetchByGCNI_GCP_T_Last(
			groupClassNameId, groupClassPK, type, orderByComparator);
	}

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
	public static LoopUserNotificationEvent[] findByGCNI_GCP_T_PrevAndNext(
			long loopUserNotificationEventId, long groupClassNameId,
			long groupClassPK, int type,
			OrderByComparator<LoopUserNotificationEvent> orderByComparator)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationEventException {

		return getPersistence().findByGCNI_GCP_T_PrevAndNext(
			loopUserNotificationEventId, groupClassNameId, groupClassPK, type,
			orderByComparator);
	}

	/**
	 * Removes all the loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63; from the database.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 */
	public static void removeByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type) {

		getPersistence().removeByGCNI_GCP_T(
			groupClassNameId, groupClassPK, type);
	}

	/**
	 * Returns the number of loop user notification events where groupClassNameId = &#63; and groupClassPK = &#63; and type = &#63;.
	 *
	 * @param groupClassNameId the group class name ID
	 * @param groupClassPK the group class pk
	 * @param type the type
	 * @return the number of matching loop user notification events
	 */
	public static int countByGCNI_GCP_T(
		long groupClassNameId, long groupClassPK, int type) {

		return getPersistence().countByGCNI_GCP_T(
			groupClassNameId, groupClassPK, type);
	}

	/**
	 * Caches the loop user notification event in the entity cache if it is enabled.
	 *
	 * @param loopUserNotificationEvent the loop user notification event
	 */
	public static void cacheResult(
		LoopUserNotificationEvent loopUserNotificationEvent) {

		getPersistence().cacheResult(loopUserNotificationEvent);
	}

	/**
	 * Caches the loop user notification events in the entity cache if it is enabled.
	 *
	 * @param loopUserNotificationEvents the loop user notification events
	 */
	public static void cacheResult(
		List<LoopUserNotificationEvent> loopUserNotificationEvents) {

		getPersistence().cacheResult(loopUserNotificationEvents);
	}

	/**
	 * Creates a new loop user notification event with the primary key. Does not add the loop user notification event to the database.
	 *
	 * @param loopUserNotificationEventId the primary key for the new loop user notification event
	 * @return the new loop user notification event
	 */
	public static LoopUserNotificationEvent create(
		long loopUserNotificationEventId) {

		return getPersistence().create(loopUserNotificationEventId);
	}

	/**
	 * Removes the loop user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationEventId the primary key of the loop user notification event
	 * @return the loop user notification event that was removed
	 * @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	 */
	public static LoopUserNotificationEvent remove(
			long loopUserNotificationEventId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationEventException {

		return getPersistence().remove(loopUserNotificationEventId);
	}

	public static LoopUserNotificationEvent updateImpl(
		LoopUserNotificationEvent loopUserNotificationEvent) {

		return getPersistence().updateImpl(loopUserNotificationEvent);
	}

	/**
	 * Returns the loop user notification event with the primary key or throws a <code>NoSuchLoopUserNotificationEventException</code> if it could not be found.
	 *
	 * @param loopUserNotificationEventId the primary key of the loop user notification event
	 * @return the loop user notification event
	 * @throws NoSuchLoopUserNotificationEventException if a loop user notification event with the primary key could not be found
	 */
	public static LoopUserNotificationEvent findByPrimaryKey(
			long loopUserNotificationEventId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationEventException {

		return getPersistence().findByPrimaryKey(loopUserNotificationEventId);
	}

	/**
	 * Returns the loop user notification event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopUserNotificationEventId the primary key of the loop user notification event
	 * @return the loop user notification event, or <code>null</code> if a loop user notification event with the primary key could not be found
	 */
	public static LoopUserNotificationEvent fetchByPrimaryKey(
		long loopUserNotificationEventId) {

		return getPersistence().fetchByPrimaryKey(loopUserNotificationEventId);
	}

	/**
	 * Returns all the loop user notification events.
	 *
	 * @return the loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the loop user notification events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @return the range of loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the loop user notification events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findAll(
		int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop user notification events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop user notification events
	 */
	public static List<LoopUserNotificationEvent> findAll(
		int start, int end,
		OrderByComparator<LoopUserNotificationEvent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the loop user notification events from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of loop user notification events.
	 *
	 * @return the number of loop user notification events
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LoopUserNotificationEventPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LoopUserNotificationEventPersistence,
		 LoopUserNotificationEventPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			LoopUserNotificationEventPersistence.class);

		ServiceTracker
			<LoopUserNotificationEventPersistence,
			 LoopUserNotificationEventPersistence> serviceTracker =
				new ServiceTracker
					<LoopUserNotificationEventPersistence,
					 LoopUserNotificationEventPersistence>(
						 bundle.getBundleContext(),
						 LoopUserNotificationEventPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}