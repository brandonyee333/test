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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.account.entry.details.exception.NoSuchEventException;
import com.liferay.osb.customer.account.entry.details.model.Event;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventUtil
 * @generated
 */
@ProviderType
public interface EventPersistence extends BasePersistence<Event> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventUtil} to access the event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, Event> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @return the matching events
	 */
	public java.util.List<Event> findByA_C(
		long accountEntryId, long classNameId);

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
	public java.util.List<Event> findByA_C(
		long accountEntryId, long classNameId, int start, int end);

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
	public java.util.List<Event> findByA_C(
		long accountEntryId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public java.util.List<Event> findByA_C(
		long accountEntryId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first event in the ordered set where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public Event findByA_C_First(
			long accountEntryId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

	/**
	 * Returns the first event in the ordered set where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event, or <code>null</code> if a matching event could not be found
	 */
	public Event fetchByA_C_First(
		long accountEntryId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

	/**
	 * Returns the last event in the ordered set where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public Event findByA_C_Last(
			long accountEntryId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

	/**
	 * Returns the last event in the ordered set where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event, or <code>null</code> if a matching event could not be found
	 */
	public Event fetchByA_C_Last(
		long accountEntryId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public Event[] findByA_C_PrevAndNext(
			long eventId, long accountEntryId, long classNameId,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

	/**
	 * Removes all the events where accountEntryId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 */
	public void removeByA_C(long accountEntryId, long classNameId);

	/**
	 * Returns the number of events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @return the number of matching events
	 */
	public int countByA_C(long accountEntryId, long classNameId);

	/**
	 * Returns all the events where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching events
	 */
	public java.util.List<Event> findByC_C(long classNameId, long classPK);

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
	public java.util.List<Event> findByC_C(
		long classNameId, long classPK, int start, int end);

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
	public java.util.List<Event> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public java.util.List<Event> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first event in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public Event findByC_C_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

	/**
	 * Returns the first event in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event, or <code>null</code> if a matching event could not be found
	 */
	public Event fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

	/**
	 * Returns the last event in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	public Event findByC_C_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

	/**
	 * Returns the last event in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event, or <code>null</code> if a matching event could not be found
	 */
	public Event fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public Event[] findByC_C_PrevAndNext(
			long eventId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

	/**
	 * Removes all the events where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C(long classNameId, long classPK);

	/**
	 * Returns the number of events where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching events
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Returns all the events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching events
	 */
	public java.util.List<Event> findByC_C_T(
		long classNameId, long classPK, int type);

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
	public java.util.List<Event> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end);

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
	public java.util.List<Event> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public java.util.List<Event> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator,
		boolean useFinderCache);

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
	public Event findByC_C_T_First(
			long classNameId, long classPK, int type,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

	/**
	 * Returns the first event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event, or <code>null</code> if a matching event could not be found
	 */
	public Event fetchByC_C_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public Event findByC_C_T_Last(
			long classNameId, long classPK, int type,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

	/**
	 * Returns the last event in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event, or <code>null</code> if a matching event could not be found
	 */
	public Event fetchByC_C_T_Last(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public Event[] findByC_C_T_PrevAndNext(
			long eventId, long classNameId, long classPK, int type,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

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
	public java.util.List<Event> findByC_C_T(
		long classNameId, long classPK, int[] types);

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
	public java.util.List<Event> findByC_C_T(
		long classNameId, long classPK, int[] types, int start, int end);

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
	public java.util.List<Event> findByC_C_T(
		long classNameId, long classPK, int[] types, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public java.util.List<Event> findByC_C_T(
		long classNameId, long classPK, int[] types, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the events where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 */
	public void removeByC_C_T(long classNameId, long classPK, int type);

	/**
	 * Returns the number of events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching events
	 */
	public int countByC_C_T(long classNameId, long classPK, int type);

	/**
	 * Returns the number of events where classNameId = &#63; and classPK = &#63; and type = any &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param types the types
	 * @return the number of matching events
	 */
	public int countByC_C_T(long classNameId, long classPK, int[] types);

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
	public java.util.List<Event> findByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK);

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
	public java.util.List<Event> findByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK, int start, int end);

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
	public java.util.List<Event> findByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public java.util.List<Event> findByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator,
		boolean useFinderCache);

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
	public Event findByC_C_T_TC_TC_First(
			long classNameId, long classPK, int type, long typeClassNameId,
			long typeClassPK,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

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
	public Event fetchByC_C_T_TC_TC_First(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public Event findByC_C_T_TC_TC_Last(
			long classNameId, long classPK, int type, long typeClassNameId,
			long typeClassPK,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

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
	public Event fetchByC_C_T_TC_TC_Last(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public Event[] findByC_C_T_TC_TC_PrevAndNext(
			long eventId, long classNameId, long classPK, int type,
			long typeClassNameId, long typeClassPK,
			com.liferay.portal.kernel.util.OrderByComparator<Event>
				orderByComparator)
		throws NoSuchEventException;

	/**
	 * Removes all the events where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param typeClassNameId the type class name ID
	 * @param typeClassPK the type class pk
	 */
	public void removeByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK);

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
	public int countByC_C_T_TC_TC(
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK);

	/**
	 * Caches the event in the entity cache if it is enabled.
	 *
	 * @param event the event
	 */
	public void cacheResult(Event event);

	/**
	 * Caches the events in the entity cache if it is enabled.
	 *
	 * @param events the events
	 */
	public void cacheResult(java.util.List<Event> events);

	/**
	 * Creates a new event with the primary key. Does not add the event to the database.
	 *
	 * @param eventId the primary key for the new event
	 * @return the new event
	 */
	public Event create(long eventId);

	/**
	 * Removes the event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the event
	 * @return the event that was removed
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	public Event remove(long eventId) throws NoSuchEventException;

	public Event updateImpl(Event event);

	/**
	 * Returns the event with the primary key or throws a <code>NoSuchEventException</code> if it could not be found.
	 *
	 * @param eventId the primary key of the event
	 * @return the event
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	public Event findByPrimaryKey(long eventId) throws NoSuchEventException;

	/**
	 * Returns the event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventId the primary key of the event
	 * @return the event, or <code>null</code> if a event with the primary key could not be found
	 */
	public Event fetchByPrimaryKey(long eventId);

	/**
	 * Returns all the events.
	 *
	 * @return the events
	 */
	public java.util.List<Event> findAll();

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
	public java.util.List<Event> findAll(int start, int end);

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
	public java.util.List<Event> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator);

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
	public java.util.List<Event> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the events from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of events.
	 *
	 * @return the number of events
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}