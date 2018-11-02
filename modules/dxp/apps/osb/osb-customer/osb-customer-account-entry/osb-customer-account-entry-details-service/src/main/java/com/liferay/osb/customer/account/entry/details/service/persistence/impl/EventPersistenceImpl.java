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

package com.liferay.osb.customer.account.entry.details.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.account.entry.details.exception.NoSuchEventException;
import com.liferay.osb.customer.account.entry.details.model.Event;
import com.liferay.osb.customer.account.entry.details.model.impl.EventImpl;
import com.liferay.osb.customer.account.entry.details.model.impl.EventModelImpl;
import com.liferay.osb.customer.account.entry.details.service.persistence.EventPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventPersistence
 * @see com.liferay.osb.customer.account.entry.details.service.persistence.EventUtil
 * @generated
 */
@ProviderType
public class EventPersistenceImpl extends BasePersistenceImpl<Event>
	implements EventPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EventUtil} to access the event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EventImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_A_C = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByA_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByA_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			EventModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			EventModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			EventModelImpl.OCCURDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_C = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @return the matching events
	 */
	@Override
	public List<Event> findByA_C(long accountEntryId, long classNameId) {
		return findByA_C(accountEntryId, classNameId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of matching events
	 */
	@Override
	public List<Event> findByA_C(long accountEntryId, long classNameId,
		int start, int end) {
		return findByA_C(accountEntryId, classNameId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching events
	 */
	@Override
	public List<Event> findByA_C(long accountEntryId, long classNameId,
		int start, int end, OrderByComparator<Event> orderByComparator) {
		return findByA_C(accountEntryId, classNameId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching events
	 */
	@Override
	public List<Event> findByA_C(long accountEntryId, long classNameId,
		int start, int end, OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C;
			finderArgs = new Object[] { accountEntryId, classNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_A_C;
			finderArgs = new Object[] {
					accountEntryId, classNameId,
					
					start, end, orderByComparator
				};
		}

		List<Event> list = null;

		if (retrieveFromCache) {
			list = (List<Event>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Event event : list) {
					if ((accountEntryId != event.getAccountEntryId()) ||
							(classNameId != event.getClassNameId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_A_C_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_A_C_CLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(classNameId);

				if (!pagination) {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Event findByA_C_First(long accountEntryId, long classNameId,
		OrderByComparator<Event> orderByComparator) throws NoSuchEventException {
		Event event = fetchByA_C_First(accountEntryId, classNameId,
				orderByComparator);

		if (event != null) {
			return event;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append("}");

		throw new NoSuchEventException(msg.toString());
	}

	/**
	 * Returns the first event in the ordered set where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event, or <code>null</code> if a matching event could not be found
	 */
	@Override
	public Event fetchByA_C_First(long accountEntryId, long classNameId,
		OrderByComparator<Event> orderByComparator) {
		List<Event> list = findByA_C(accountEntryId, classNameId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Event findByA_C_Last(long accountEntryId, long classNameId,
		OrderByComparator<Event> orderByComparator) throws NoSuchEventException {
		Event event = fetchByA_C_Last(accountEntryId, classNameId,
				orderByComparator);

		if (event != null) {
			return event;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountEntryId=");
		msg.append(accountEntryId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append("}");

		throw new NoSuchEventException(msg.toString());
	}

	/**
	 * Returns the last event in the ordered set where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event, or <code>null</code> if a matching event could not be found
	 */
	@Override
	public Event fetchByA_C_Last(long accountEntryId, long classNameId,
		OrderByComparator<Event> orderByComparator) {
		int count = countByA_C(accountEntryId, classNameId);

		if (count == 0) {
			return null;
		}

		List<Event> list = findByA_C(accountEntryId, classNameId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Event[] findByA_C_PrevAndNext(long eventId, long accountEntryId,
		long classNameId, OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException {
		Event event = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			Event[] array = new EventImpl[3];

			array[0] = getByA_C_PrevAndNext(session, event, accountEntryId,
					classNameId, orderByComparator, true);

			array[1] = event;

			array[2] = getByA_C_PrevAndNext(session, event, accountEntryId,
					classNameId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Event getByA_C_PrevAndNext(Session session, Event event,
		long accountEntryId, long classNameId,
		OrderByComparator<Event> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EVENT_WHERE);

		query.append(_FINDER_COLUMN_A_C_ACCOUNTENTRYID_2);

		query.append(_FINDER_COLUMN_A_C_CLASSNAMEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountEntryId);

		qPos.add(classNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(event);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Event> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the events where accountEntryId = &#63; and classNameId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 */
	@Override
	public void removeByA_C(long accountEntryId, long classNameId) {
		for (Event event : findByA_C(accountEntryId, classNameId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(event);
		}
	}

	/**
	 * Returns the number of events where accountEntryId = &#63; and classNameId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param classNameId the class name ID
	 * @return the number of matching events
	 */
	@Override
	public int countByA_C(long accountEntryId, long classNameId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_A_C;

		Object[] finderArgs = new Object[] { accountEntryId, classNameId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_A_C_ACCOUNTENTRYID_2);

			query.append(_FINDER_COLUMN_A_C_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				qPos.add(classNameId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_A_C_ACCOUNTENTRYID_2 = "event.accountEntryId = ? AND ";
	private static final String _FINDER_COLUMN_A_C_CLASSNAMEID_2 = "event.classNameId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			EventModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			EventModelImpl.CLASSPK_COLUMN_BITMASK |
			EventModelImpl.OCCURDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the events where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching events
	 */
	@Override
	public List<Event> findByC_C(long classNameId, long classPK) {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the events where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of matching events
	 */
	@Override
	public List<Event> findByC_C(long classNameId, long classPK, int start,
		int end) {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching events
	 */
	@Override
	public List<Event> findByC_C(long classNameId, long classPK, int start,
		int end, OrderByComparator<Event> orderByComparator) {
		return findByC_C(classNameId, classPK, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching events
	 */
	@Override
	public List<Event> findByC_C(long classNameId, long classPK, int start,
		int end, OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] { classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] {
					classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<Event> list = null;

		if (retrieveFromCache) {
			list = (List<Event>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Event event : list) {
					if ((classNameId != event.getClassNameId()) ||
							(classPK != event.getClassPK())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Event findByC_C_First(long classNameId, long classPK,
		OrderByComparator<Event> orderByComparator) throws NoSuchEventException {
		Event event = fetchByC_C_First(classNameId, classPK, orderByComparator);

		if (event != null) {
			return event;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchEventException(msg.toString());
	}

	/**
	 * Returns the first event in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event, or <code>null</code> if a matching event could not be found
	 */
	@Override
	public Event fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator<Event> orderByComparator) {
		List<Event> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Event findByC_C_Last(long classNameId, long classPK,
		OrderByComparator<Event> orderByComparator) throws NoSuchEventException {
		Event event = fetchByC_C_Last(classNameId, classPK, orderByComparator);

		if (event != null) {
			return event;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append("}");

		throw new NoSuchEventException(msg.toString());
	}

	/**
	 * Returns the last event in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event, or <code>null</code> if a matching event could not be found
	 */
	@Override
	public Event fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator<Event> orderByComparator) {
		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<Event> list = findByC_C(classNameId, classPK, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Event[] findByC_C_PrevAndNext(long eventId, long classNameId,
		long classPK, OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException {
		Event event = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			Event[] array = new EventImpl[3];

			array[0] = getByC_C_PrevAndNext(session, event, classNameId,
					classPK, orderByComparator, true);

			array[1] = event;

			array[2] = getByC_C_PrevAndNext(session, event, classNameId,
					classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Event getByC_C_PrevAndNext(Session session, Event event,
		long classNameId, long classPK,
		OrderByComparator<Event> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EVENT_WHERE);

		query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(event);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Event> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the events where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (Event event : findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(event);
		}
	}

	/**
	 * Returns the number of events where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching events
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "event.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "event.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			EventModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			EventModelImpl.CLASSPK_COLUMN_BITMASK |
			EventModelImpl.TYPE_COLUMN_BITMASK |
			EventModelImpl.OCCURDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_T = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_C_T = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching events
	 */
	@Override
	public List<Event> findByC_C_T(long classNameId, long classPK, int type) {
		return findByC_C_T(classNameId, classPK, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of matching events
	 */
	@Override
	public List<Event> findByC_C_T(long classNameId, long classPK, int type,
		int start, int end) {
		return findByC_C_T(classNameId, classPK, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	@Override
	public List<Event> findByC_C_T(long classNameId, long classPK, int type,
		int start, int end, OrderByComparator<Event> orderByComparator) {
		return findByC_C_T(classNameId, classPK, type, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching events
	 */
	@Override
	public List<Event> findByC_C_T(long classNameId, long classPK, int type,
		int start, int end, OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T;
			finderArgs = new Object[] { classNameId, classPK, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T;
			finderArgs = new Object[] {
					classNameId, classPK, type,
					
					start, end, orderByComparator
				};
		}

		List<Event> list = null;

		if (retrieveFromCache) {
			list = (List<Event>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Event event : list) {
					if ((classNameId != event.getClassNameId()) ||
							(classPK != event.getClassPK()) ||
							(type != event.getType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				if (!pagination) {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Event findByC_C_T_First(long classNameId, long classPK, int type,
		OrderByComparator<Event> orderByComparator) throws NoSuchEventException {
		Event event = fetchByC_C_T_First(classNameId, classPK, type,
				orderByComparator);

		if (event != null) {
			return event;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchEventException(msg.toString());
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
	@Override
	public Event fetchByC_C_T_First(long classNameId, long classPK, int type,
		OrderByComparator<Event> orderByComparator) {
		List<Event> list = findByC_C_T(classNameId, classPK, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Event findByC_C_T_Last(long classNameId, long classPK, int type,
		OrderByComparator<Event> orderByComparator) throws NoSuchEventException {
		Event event = fetchByC_C_T_Last(classNameId, classPK, type,
				orderByComparator);

		if (event != null) {
			return event;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append("}");

		throw new NoSuchEventException(msg.toString());
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
	@Override
	public Event fetchByC_C_T_Last(long classNameId, long classPK, int type,
		OrderByComparator<Event> orderByComparator) {
		int count = countByC_C_T(classNameId, classPK, type);

		if (count == 0) {
			return null;
		}

		List<Event> list = findByC_C_T(classNameId, classPK, type, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Event[] findByC_C_T_PrevAndNext(long eventId, long classNameId,
		long classPK, int type, OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException {
		Event event = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			Event[] array = new EventImpl[3];

			array[0] = getByC_C_T_PrevAndNext(session, event, classNameId,
					classPK, type, orderByComparator, true);

			array[1] = event;

			array[2] = getByC_C_T_PrevAndNext(session, event, classNameId,
					classPK, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Event getByC_C_T_PrevAndNext(Session session, Event event,
		long classNameId, long classPK, int type,
		OrderByComparator<Event> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_EVENT_WHERE);

		query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(event);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Event> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the events where classNameId = &#63; and classPK = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param types the types
	 * @return the matching events
	 */
	@Override
	public List<Event> findByC_C_T(long classNameId, long classPK, int[] types) {
		return findByC_C_T(classNameId, classPK, types, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the events where classNameId = &#63; and classPK = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param types the types
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of matching events
	 */
	@Override
	public List<Event> findByC_C_T(long classNameId, long classPK, int[] types,
		int start, int end) {
		return findByC_C_T(classNameId, classPK, types, start, end, null);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	@Override
	public List<Event> findByC_C_T(long classNameId, long classPK, int[] types,
		int start, int end, OrderByComparator<Event> orderByComparator) {
		return findByC_C_T(classNameId, classPK, types, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching events
	 */
	@Override
	public List<Event> findByC_C_T(long classNameId, long classPK, int[] types,
		int start, int end, OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache) {
		if (types == null) {
			types = new int[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.unique(types);

			Arrays.sort(types);
		}

		if (types.length == 1) {
			return findByC_C_T(classNameId, classPK, types[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					classNameId, classPK, StringUtil.merge(types)
				};
		}
		else {
			finderArgs = new Object[] {
					classNameId, classPK, StringUtil.merge(types),
					
					start, end, orderByComparator
				};
		}

		List<Event> list = null;

		if (retrieveFromCache) {
			list = (List<Event>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Event event : list) {
					if ((classNameId != event.getClassNameId()) ||
							(classPK != event.getClassPK()) ||
							!ArrayUtil.contains(types, event.getType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			if (types.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_C_C_T_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the events where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 */
	@Override
	public void removeByC_C_T(long classNameId, long classPK, int type) {
		for (Event event : findByC_C_T(classNameId, classPK, type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(event);
		}
	}

	/**
	 * Returns the number of events where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching events
	 */
	@Override
	public int countByC_C_T(long classNameId, long classPK, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_T;

		Object[] finderArgs = new Object[] { classNameId, classPK, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of events where classNameId = &#63; and classPK = &#63; and type = any &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param types the types
	 * @return the number of matching events
	 */
	@Override
	public int countByC_C_T(long classNameId, long classPK, int[] types) {
		if (types == null) {
			types = new int[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.unique(types);

			Arrays.sort(types);
		}

		Object[] finderArgs = new Object[] {
				classNameId, classPK, StringUtil.merge(types)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_C_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			if (types.length > 0) {
				query.append("(");

				query.append(_FINDER_COLUMN_C_C_T_TYPE_7);

				query.append(StringUtil.merge(types));

				query.append(")");

				query.append(")");
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_C_T,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_C_T,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_C_T_CLASSNAMEID_2 = "event.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_CLASSPK_2 = "event.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TYPE_2 = "event.type = ?";
	private static final String _FINDER_COLUMN_C_C_T_TYPE_7 = "event.type IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T_TC_TC =
		new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_T_TC_TC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T_TC_TC =
		new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_T_TC_TC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			EventModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			EventModelImpl.CLASSPK_COLUMN_BITMASK |
			EventModelImpl.TYPE_COLUMN_BITMASK |
			EventModelImpl.TYPECLASSNAMEID_COLUMN_BITMASK |
			EventModelImpl.TYPECLASSPK_COLUMN_BITMASK |
			EventModelImpl.OCCURDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_T_TC_TC = new FinderPath(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_T_TC_TC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

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
	@Override
	public List<Event> findByC_C_T_TC_TC(long classNameId, long classPK,
		int type, long typeClassNameId, long typeClassPK) {
		return findByC_C_T_TC_TC(classNameId, classPK, type, typeClassNameId,
			typeClassPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	@Override
	public List<Event> findByC_C_T_TC_TC(long classNameId, long classPK,
		int type, long typeClassNameId, long typeClassPK, int start, int end) {
		return findByC_C_T_TC_TC(classNameId, classPK, type, typeClassNameId,
			typeClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	@Override
	public List<Event> findByC_C_T_TC_TC(long classNameId, long classPK,
		int type, long typeClassNameId, long typeClassPK, int start, int end,
		OrderByComparator<Event> orderByComparator) {
		return findByC_C_T_TC_TC(classNameId, classPK, type, typeClassNameId,
			typeClassPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the events where classNameId = &#63; and classPK = &#63; and type = &#63; and typeClassNameId = &#63; and typeClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching events
	 */
	@Override
	public List<Event> findByC_C_T_TC_TC(long classNameId, long classPK,
		int type, long typeClassNameId, long typeClassPK, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T_TC_TC;
			finderArgs = new Object[] {
					classNameId, classPK, type, typeClassNameId, typeClassPK
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T_TC_TC;
			finderArgs = new Object[] {
					classNameId, classPK, type, typeClassNameId, typeClassPK,
					
					start, end, orderByComparator
				};
		}

		List<Event> list = null;

		if (retrieveFromCache) {
			list = (List<Event>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Event event : list) {
					if ((classNameId != event.getClassNameId()) ||
							(classPK != event.getClassPK()) ||
							(type != event.getType()) ||
							(typeClassNameId != event.getTypeClassNameId()) ||
							(typeClassPK != event.getTypeClassPK())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_TC_TC_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_TC_TC_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TC_TC_TYPE_2);

			query.append(_FINDER_COLUMN_C_C_T_TC_TC_TYPECLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_TC_TC_TYPECLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				qPos.add(typeClassNameId);

				qPos.add(typeClassPK);

				if (!pagination) {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Event findByC_C_T_TC_TC_First(long classNameId, long classPK,
		int type, long typeClassNameId, long typeClassPK,
		OrderByComparator<Event> orderByComparator) throws NoSuchEventException {
		Event event = fetchByC_C_T_TC_TC_First(classNameId, classPK, type,
				typeClassNameId, typeClassPK, orderByComparator);

		if (event != null) {
			return event;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(", typeClassNameId=");
		msg.append(typeClassNameId);

		msg.append(", typeClassPK=");
		msg.append(typeClassPK);

		msg.append("}");

		throw new NoSuchEventException(msg.toString());
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
	@Override
	public Event fetchByC_C_T_TC_TC_First(long classNameId, long classPK,
		int type, long typeClassNameId, long typeClassPK,
		OrderByComparator<Event> orderByComparator) {
		List<Event> list = findByC_C_T_TC_TC(classNameId, classPK, type,
				typeClassNameId, typeClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Event findByC_C_T_TC_TC_Last(long classNameId, long classPK,
		int type, long typeClassNameId, long typeClassPK,
		OrderByComparator<Event> orderByComparator) throws NoSuchEventException {
		Event event = fetchByC_C_T_TC_TC_Last(classNameId, classPK, type,
				typeClassNameId, typeClassPK, orderByComparator);

		if (event != null) {
			return event;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(", typeClassNameId=");
		msg.append(typeClassNameId);

		msg.append(", typeClassPK=");
		msg.append(typeClassPK);

		msg.append("}");

		throw new NoSuchEventException(msg.toString());
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
	@Override
	public Event fetchByC_C_T_TC_TC_Last(long classNameId, long classPK,
		int type, long typeClassNameId, long typeClassPK,
		OrderByComparator<Event> orderByComparator) {
		int count = countByC_C_T_TC_TC(classNameId, classPK, type,
				typeClassNameId, typeClassPK);

		if (count == 0) {
			return null;
		}

		List<Event> list = findByC_C_T_TC_TC(classNameId, classPK, type,
				typeClassNameId, typeClassPK, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Event[] findByC_C_T_TC_TC_PrevAndNext(long eventId,
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK, OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException {
		Event event = findByPrimaryKey(eventId);

		Session session = null;

		try {
			session = openSession();

			Event[] array = new EventImpl[3];

			array[0] = getByC_C_T_TC_TC_PrevAndNext(session, event,
					classNameId, classPK, type, typeClassNameId, typeClassPK,
					orderByComparator, true);

			array[1] = event;

			array[2] = getByC_C_T_TC_TC_PrevAndNext(session, event,
					classNameId, classPK, type, typeClassNameId, typeClassPK,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Event getByC_C_T_TC_TC_PrevAndNext(Session session, Event event,
		long classNameId, long classPK, int type, long typeClassNameId,
		long typeClassPK, OrderByComparator<Event> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_EVENT_WHERE);

		query.append(_FINDER_COLUMN_C_C_T_TC_TC_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_T_TC_TC_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_T_TC_TC_TYPE_2);

		query.append(_FINDER_COLUMN_C_C_T_TC_TC_TYPECLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_T_TC_TC_TYPECLASSPK_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(type);

		qPos.add(typeClassNameId);

		qPos.add(typeClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(event);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Event> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
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
	@Override
	public void removeByC_C_T_TC_TC(long classNameId, long classPK, int type,
		long typeClassNameId, long typeClassPK) {
		for (Event event : findByC_C_T_TC_TC(classNameId, classPK, type,
				typeClassNameId, typeClassPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(event);
		}
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
	@Override
	public int countByC_C_T_TC_TC(long classNameId, long classPK, int type,
		long typeClassNameId, long typeClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_T_TC_TC;

		Object[] finderArgs = new Object[] {
				classNameId, classPK, type, typeClassNameId, typeClassPK
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_TC_TC_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_TC_TC_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TC_TC_TYPE_2);

			query.append(_FINDER_COLUMN_C_C_T_TC_TC_TYPECLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_TC_TC_TYPECLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				qPos.add(typeClassNameId);

				qPos.add(typeClassPK);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_C_T_TC_TC_CLASSNAMEID_2 = "event.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TC_TC_CLASSPK_2 = "event.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TC_TC_TYPE_2 = "event.type = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TC_TC_TYPECLASSNAMEID_2 = "event.typeClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TC_TC_TYPECLASSPK_2 = "event.typeClassPK = ?";

	public EventPersistenceImpl() {
		setModelClass(Event.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("type", "type_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the event in the entity cache if it is enabled.
	 *
	 * @param event the event
	 */
	@Override
	public void cacheResult(Event event) {
		entityCache.putResult(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventImpl.class, event.getPrimaryKey(), event);

		event.resetOriginalValues();
	}

	/**
	 * Caches the events in the entity cache if it is enabled.
	 *
	 * @param events the events
	 */
	@Override
	public void cacheResult(List<Event> events) {
		for (Event event : events) {
			if (entityCache.getResult(EventModelImpl.ENTITY_CACHE_ENABLED,
						EventImpl.class, event.getPrimaryKey()) == null) {
				cacheResult(event);
			}
			else {
				event.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all events.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EventImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the event.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Event event) {
		entityCache.removeResult(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventImpl.class, event.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Event> events) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Event event : events) {
			entityCache.removeResult(EventModelImpl.ENTITY_CACHE_ENABLED,
				EventImpl.class, event.getPrimaryKey());
		}
	}

	/**
	 * Creates a new event with the primary key. Does not add the event to the database.
	 *
	 * @param eventId the primary key for the new event
	 * @return the new event
	 */
	@Override
	public Event create(long eventId) {
		Event event = new EventImpl();

		event.setNew(true);
		event.setPrimaryKey(eventId);

		return event;
	}

	/**
	 * Removes the event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the event
	 * @return the event that was removed
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	@Override
	public Event remove(long eventId) throws NoSuchEventException {
		return remove((Serializable)eventId);
	}

	/**
	 * Removes the event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the event
	 * @return the event that was removed
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	@Override
	public Event remove(Serializable primaryKey) throws NoSuchEventException {
		Session session = null;

		try {
			session = openSession();

			Event event = (Event)session.get(EventImpl.class, primaryKey);

			if (event == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(event);
		}
		catch (NoSuchEventException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Event removeImpl(Event event) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(event)) {
				event = (Event)session.get(EventImpl.class,
						event.getPrimaryKeyObj());
			}

			if (event != null) {
				session.delete(event);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (event != null) {
			clearCache(event);
		}

		return event;
	}

	@Override
	public Event updateImpl(Event event) {
		boolean isNew = event.isNew();

		if (!(event instanceof EventModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(event.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(event);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in event proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Event implementation " +
				event.getClass());
		}

		EventModelImpl eventModelImpl = (EventModelImpl)event;

		Session session = null;

		try {
			session = openSession();

			if (event.isNew()) {
				session.save(event);

				event.setNew(false);
			}
			else {
				event = (Event)session.merge(event);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!EventModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					eventModelImpl.getAccountEntryId(),
					eventModelImpl.getClassNameId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_A_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C,
				args);

			args = new Object[] {
					eventModelImpl.getClassNameId(), eventModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
				args);

			args = new Object[] {
					eventModelImpl.getClassNameId(), eventModelImpl.getClassPK(),
					eventModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
				args);

			args = new Object[] {
					eventModelImpl.getClassNameId(), eventModelImpl.getClassPK(),
					eventModelImpl.getType(),
					eventModelImpl.getTypeClassNameId(),
					eventModelImpl.getTypeClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T_TC_TC, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T_TC_TC,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((eventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eventModelImpl.getOriginalAccountEntryId(),
						eventModelImpl.getOriginalClassNameId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_A_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C,
					args);

				args = new Object[] {
						eventModelImpl.getAccountEntryId(),
						eventModelImpl.getClassNameId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_A_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_C,
					args);
			}

			if ((eventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eventModelImpl.getOriginalClassNameId(),
						eventModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						eventModelImpl.getClassNameId(),
						eventModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((eventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eventModelImpl.getOriginalClassNameId(),
						eventModelImpl.getOriginalClassPK(),
						eventModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);

				args = new Object[] {
						eventModelImpl.getClassNameId(),
						eventModelImpl.getClassPK(), eventModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);
			}

			if ((eventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T_TC_TC.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						eventModelImpl.getOriginalClassNameId(),
						eventModelImpl.getOriginalClassPK(),
						eventModelImpl.getOriginalType(),
						eventModelImpl.getOriginalTypeClassNameId(),
						eventModelImpl.getOriginalTypeClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T_TC_TC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T_TC_TC,
					args);

				args = new Object[] {
						eventModelImpl.getClassNameId(),
						eventModelImpl.getClassPK(), eventModelImpl.getType(),
						eventModelImpl.getTypeClassNameId(),
						eventModelImpl.getTypeClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T_TC_TC, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T_TC_TC,
					args);
			}
		}

		entityCache.putResult(EventModelImpl.ENTITY_CACHE_ENABLED,
			EventImpl.class, event.getPrimaryKey(), event, false);

		event.resetOriginalValues();

		return event;
	}

	/**
	 * Returns the event with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the event
	 * @return the event
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	@Override
	public Event findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEventException {
		Event event = fetchByPrimaryKey(primaryKey);

		if (event == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return event;
	}

	/**
	 * Returns the event with the primary key or throws a {@link NoSuchEventException} if it could not be found.
	 *
	 * @param eventId the primary key of the event
	 * @return the event
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	@Override
	public Event findByPrimaryKey(long eventId) throws NoSuchEventException {
		return findByPrimaryKey((Serializable)eventId);
	}

	/**
	 * Returns the event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the event
	 * @return the event, or <code>null</code> if a event with the primary key could not be found
	 */
	@Override
	public Event fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EventModelImpl.ENTITY_CACHE_ENABLED,
				EventImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Event event = (Event)serializable;

		if (event == null) {
			Session session = null;

			try {
				session = openSession();

				event = (Event)session.get(EventImpl.class, primaryKey);

				if (event != null) {
					cacheResult(event);
				}
				else {
					entityCache.putResult(EventModelImpl.ENTITY_CACHE_ENABLED,
						EventImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EventModelImpl.ENTITY_CACHE_ENABLED,
					EventImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return event;
	}

	/**
	 * Returns the event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventId the primary key of the event
	 * @return the event, or <code>null</code> if a event with the primary key could not be found
	 */
	@Override
	public Event fetchByPrimaryKey(long eventId) {
		return fetchByPrimaryKey((Serializable)eventId);
	}

	@Override
	public Map<Serializable, Event> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Event> map = new HashMap<Serializable, Event>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Event event = fetchByPrimaryKey(primaryKey);

			if (event != null) {
				map.put(primaryKey, event);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EventModelImpl.ENTITY_CACHE_ENABLED,
					EventImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Event)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_EVENT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Event event : (List<Event>)q.list()) {
				map.put(event.getPrimaryKeyObj(), event);

				cacheResult(event);

				uncachedPrimaryKeys.remove(event.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EventModelImpl.ENTITY_CACHE_ENABLED,
					EventImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the events.
	 *
	 * @return the events
	 */
	@Override
	public List<Event> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of events
	 */
	@Override
	public List<Event> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of events
	 */
	@Override
	public List<Event> findAll(int start, int end,
		OrderByComparator<Event> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of events
	 */
	@Override
	public List<Event> findAll(int start, int end,
		OrderByComparator<Event> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Event> list = null;

		if (retrieveFromCache) {
			list = (List<Event>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EVENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EVENT;

				if (pagination) {
					sql = sql.concat(EventModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Event>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the events from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Event event : findAll()) {
			remove(event);
		}
	}

	/**
	 * Returns the number of events.
	 *
	 * @return the number of events
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EVENT);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EventModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the event persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EventImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_EVENT = "SELECT event FROM Event event";
	private static final String _SQL_SELECT_EVENT_WHERE_PKS_IN = "SELECT event FROM Event event WHERE eventId IN (";
	private static final String _SQL_SELECT_EVENT_WHERE = "SELECT event FROM Event event WHERE ";
	private static final String _SQL_COUNT_EVENT = "SELECT COUNT(event) FROM Event event";
	private static final String _SQL_COUNT_EVENT_WHERE = "SELECT COUNT(event) FROM Event event WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "event.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Event exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Event exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EventPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}