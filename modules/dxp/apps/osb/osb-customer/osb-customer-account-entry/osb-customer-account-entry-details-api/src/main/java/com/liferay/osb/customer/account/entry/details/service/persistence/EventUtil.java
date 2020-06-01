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

package com.liferay.osb.customer.account.entry.details.service.persistence;

import com.liferay.osb.customer.account.entry.details.model.Event;
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
 * The persistence utility for the event service. This utility wraps <code>com.liferay.osb.customer.account.entry.details.service.persistence.impl.EventPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventPersistence
 * @generated
 */
public class EventUtil {

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
	public static void clearCache(Event event) {
		getPersistence().clearCache(event);
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
	public static Map<Serializable, Event> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Event> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Event> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Event> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Event update(Event event) {
		return getPersistence().update(event);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Event update(Event event, ServiceContext serviceContext) {
		return getPersistence().update(event, serviceContext);
	}

	/**
	 * Returns all the events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @return the matching events
	 */
	public static List<Event> findByA_C(long accountEntryId, long classNameId) {
		return getPersistence().findByA_C(accountEntryId, classNameId);
	}

	/**
	 * Returns a range of all the events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of matching events
	 */
	public static List<Event> findByA_C(
		long accountEntryId, long classNameId, int start, int end) {

		return getPersistence().findByA_C(
			accountEntryId, classNameId, start, end);
	}

	/**
	 * Returns an ordered range of all the events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching events
	 */
	public static List<Event> findByA_C(
		long accountEntryId, long classNameId, int start, int end,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().findByA_C(
			accountEntryId, classNameId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching events
	 */
	public static List<Event> findByA_C(
		long accountEntryId, long classNameId, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByA_C(
			accountEntryId, classNameId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first event in the ordered set where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public static Event findByA_C_First(
			long accountEntryId, long classNameId,
			OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByA_C_First(
			accountEntryId, classNameId, orderByComparator);
	}

	/**
	 * Returns the first event in the ordered set where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event, or <code>null</code> if a matching event could not be found
	 */
	public static Event fetchByA_C_First(
		long accountEntryId, long classNameId,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().fetchByA_C_First(
			accountEntryId, classNameId, orderByComparator);
	}

	/**
	 * Returns the last event in the ordered set where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public static Event findByA_C_Last(
			long accountEntryId, long classNameId,
			OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByA_C_Last(
			accountEntryId, classNameId, orderByComparator);
	}

	/**
	 * Returns the last event in the ordered set where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event, or <code>null</code> if a matching event could not be found
	 */
	public static Event fetchByA_C_Last(
		long accountEntryId, long classNameId,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().fetchByA_C_Last(
			accountEntryId, classNameId, orderByComparator);
	}

	/**
	 * Returns the events before and after the current event in the ordered set where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param eventId the primary key of the current event
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	public static Event[] findByA_C_PrevAndNext(
			long eventId, long accountEntryId, long classNameId,
			OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByA_C_PrevAndNext(
			eventId, accountEntryId, classNameId, orderByComparator);
	}

	/**
	 * Removes all the events where accountEntryId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 */
	public static void removeByA_C(long accountEntryId, long classNameId) {
		getPersistence().removeByA_C(accountEntryId, classNameId);
	}

	/**
	 * Returns the number of events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @return the number of matching events
	 */
	public static int countByA_C(long accountEntryId, long classNameId) {
		return getPersistence().countByA_C(accountEntryId, classNameId);
	}

	/**
	 * Returns all the events where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching events
	 */
	public static List<Event> findByC_C(long classNameId, long classPK) {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	 * Returns a range of all the events where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of matching events
	 */
	public static List<Event> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching events
	 */
	public static List<Event> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching events
	 */
	public static List<Event> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first event in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public static Event findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first event in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event, or <code>null</code> if a matching event could not be found
	 */
	public static Event fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().fetchByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last event in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public static Event findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last event in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event, or <code>null</code> if a matching event could not be found
	 */
	public static Event fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().fetchByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the events before and after the current event in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param eventId the primary key of the current event
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	public static Event[] findByC_C_PrevAndNext(
			long eventId, long classNameId, long classPK,
			OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByC_C_PrevAndNext(
			eventId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the events where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	 * Returns the number of events where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching events
	 */
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	 * Returns all the events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching events
	 */
	public static List<Event> findByC_C_T(
		long classNameId, long classPK, int type) {

		return getPersistence().findByC_C_T(classNameId, classPK, type);
	}

	/**
	 * Returns a range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of matching events
	 */
	public static List<Event> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end) {

		return getPersistence().findByC_C_T(
			classNameId, classPK, type, start, end);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching events
	 */
	public static List<Event> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().findByC_C_T(
			classNameId, classPK, type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching events
	 */
	public static List<Event> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByC_C_T(
			classNameId, classPK, type, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public static Event findByC_C_T_First(
			long classNameId, long classPK, int type,
			OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByC_C_T_First(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the first event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event, or <code>null</code> if a matching event could not be found
	 */
	public static Event fetchByC_C_T_First(
		long classNameId, long classPK, int type,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().fetchByC_C_T_First(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the last event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public static Event findByC_C_T_Last(
			long classNameId, long classPK, int type,
			OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByC_C_T_Last(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the last event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event, or <code>null</code> if a matching event could not be found
	 */
	public static Event fetchByC_C_T_Last(
		long classNameId, long classPK, int type,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().fetchByC_C_T_Last(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the events before and after the current event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param eventId the primary key of the current event
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	public static Event[] findByC_C_T_PrevAndNext(
			long eventId, long classNameId, long classPK, int type,
			OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByC_C_T_PrevAndNext(
			eventId, classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns all the events where classNameId = &#63; and classPK = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param types the types
	 * @return the matching events
	 */
	public static List<Event> findByC_C_T(
		long classNameId, long classPK, int[] types) {

		return getPersistence().findByC_C_T(classNameId, classPK, types);
	}

	/**
	 * Returns a range of all the events where classNameId = &#63; and classPK = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param types the types
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of matching events
	 */
	public static List<Event> findByC_C_T(
		long classNameId, long classPK, int[] types, int start, int end) {

		return getPersistence().findByC_C_T(
			classNameId, classPK, types, start, end);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param types the types
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching events
	 */
	public static List<Event> findByC_C_T(
		long classNameId, long classPK, int[] types, int start, int end,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().findByC_C_T(
			classNameId, classPK, types, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching events
	 */
	public static List<Event> findByC_C_T(
		long classNameId, long classPK, int[] types, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByC_C_T(
			classNameId, classPK, types, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Removes all the events where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 */
	public static void removeByC_C_T(long classNameId, long classPK, int type) {
		getPersistence().removeByC_C_T(classNameId, classPK, type);
	}

	/**
	 * Returns the number of events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching events
	 */
	public static int countByC_C_T(long classNameId, long classPK, int type) {
		return getPersistence().countByC_C_T(classNameId, classPK, type);
	}

	/**
	 * Returns the number of events where classNameId = &#63; and classPK = &#63; and type = any &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param types the types
	 * @return the number of matching events
	 */
	public static int countByC_C_T(
		long classNameId, long classPK, int[] types) {

		return getPersistence().countByC_C_T(classNameId, classPK, types);
	}

	/**
	 * Returns all the events where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 * @return the matching events
	 */
	public static List<Event> findByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK) {

		return getPersistence().findByC_C_T_TC_TC(
			classNameId, classPK, type, typeClassNameId, typeClassPK);
	}

	/**
	 * Returns a range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of matching events
	 */
	public static List<Event> findByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK, int start, int end) {

		return getPersistence().findByC_C_T_TC_TC(
			classNameId, classPK, type, typeClassNameId, typeClassPK, start,
			end);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching events
	 */
	public static List<Event> findByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK, int start, int end,
		OrderByComparator<Event> orderByComparator) {

		return getPersistence().findByC_C_T_TC_TC(
			classNameId, classPK, type, typeClassNameId, typeClassPK, start,
			end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching events
	 */
	public static List<Event> findByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByC_C_T_TC_TC(
			classNameId, classPK, type, typeClassNameId, typeClassPK, start,
			end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public static Event findByC_C_T_TC_TC_First(
			long classNameId, long classPK, int type, long typeClassNameId,
			long typeClassPK, OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByC_C_T_TC_TC_First(
			classNameId, classPK, type, typeClassNameId, typeClassPK,
			orderByComparator);
	}

	/**
	 * Returns the first event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event, or <code>null</code> if a matching event could not be found
	 */
	public static Event fetchByC_C_T_TC_TC_First(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK, OrderByComparator<Event> orderByComparator) {

		return getPersistence().fetchByC_C_T_TC_TC_First(
			classNameId, classPK, type, typeClassNameId, typeClassPK,
			orderByComparator);
	}

	/**
	 * Returns the last event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public static Event findByC_C_T_TC_TC_Last(
			long classNameId, long classPK, int type, long typeClassNameId,
			long typeClassPK, OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByC_C_T_TC_TC_Last(
			classNameId, classPK, type, typeClassNameId, typeClassPK,
			orderByComparator);
	}

	/**
	 * Returns the last event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event, or <code>null</code> if a matching event could not be found
	 */
	public static Event fetchByC_C_T_TC_TC_Last(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK, OrderByComparator<Event> orderByComparator) {

		return getPersistence().fetchByC_C_T_TC_TC_Last(
			classNameId, classPK, type, typeClassNameId, typeClassPK,
			orderByComparator);
	}

	/**
	 * Returns the events before and after the current event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * @param eventId the primary key of the current event
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	public static Event[] findByC_C_T_TC_TC_PrevAndNext(
			long eventId, long classNameId, long classPK, int type,
			long typeClassNameId, long typeClassPK,
			OrderByComparator<Event> orderByComparator)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByC_C_T_TC_TC_PrevAndNext(
			eventId, classNameId, classPK, type, typeClassNameId, typeClassPK,
			orderByComparator);
	}

	/**
	 * Removes all the events where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 */
	public static void removeByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK) {

		getPersistence().removeByC_C_T_TC_TC(
			classNameId, classPK, type, typeClassNameId, typeClassPK);
	}

	/**
	 * Returns the number of events where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 * @return the number of matching events
	 */
	public static int countByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK) {

		return getPersistence().countByC_C_T_TC_TC(
			classNameId, classPK, type, typeClassNameId, typeClassPK);
	}

	/**
	 * Caches the event in the entity cache if it is enabled.
	 *
	 * @param event the event
	 */
	public static void cacheResult(Event event) {
		getPersistence().cacheResult(event);
	}

	/**
	 * Caches the events in the entity cache if it is enabled.
	 *
	 * @param events the events
	 */
	public static void cacheResult(List<Event> events) {
		getPersistence().cacheResult(events);
	}

	/**
	 * Creates a new event with the primary key. Does not add the event to the database.
	 *
	 * @param eventId the primary key for the new event
	 * @return the new event
	 */
	public static Event create(long eventId) {
		return getPersistence().create(eventId);
	}

	/**
	 * Removes the event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the event
	 * @return the event that was removed
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	public static Event remove(long eventId)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().remove(eventId);
	}

	public static Event updateImpl(Event event) {
		return getPersistence().updateImpl(event);
	}

	/**
	 * Returns the event with the primary key or throws a <code>NoSuchEventException</code> if it could not be found.
	 *
	 * @param eventId the primary key of the event
	 * @return the event
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	public static Event findByPrimaryKey(long eventId)
		throws com.liferay.osb.customer.account.entry.details.exception.
			NoSuchEventException {

		return getPersistence().findByPrimaryKey(eventId);
	}

	/**
	 * Returns the event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventId the primary key of the event
	 * @return the event, or <code>null</code> if a event with the primary key could not be found
	 */
	public static Event fetchByPrimaryKey(long eventId) {
		return getPersistence().fetchByPrimaryKey(eventId);
	}

	/**
	 * Returns all the events.
	 *
	 * @return the events
	 */
	public static List<Event> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of events
	 */
	public static List<Event> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of events
	 */
	public static List<Event> findAll(
		int start, int end, OrderByComparator<Event> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of events
	 */
	public static List<Event> findAll(
		int start, int end, OrderByComparator<Event> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the events from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of events.
	 *
	 * @return the number of events
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EventPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EventPersistence, EventPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(EventPersistence.class);

		ServiceTracker<EventPersistence, EventPersistence> serviceTracker =
			new ServiceTracker<EventPersistence, EventPersistence>(
				bundle.getBundleContext(), EventPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}