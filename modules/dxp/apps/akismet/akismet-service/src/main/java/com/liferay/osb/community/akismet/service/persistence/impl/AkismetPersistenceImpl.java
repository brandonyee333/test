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

package com.liferay.osb.community.akismet.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.akismet.exception.NoSuchAkismetException;
import com.liferay.osb.community.akismet.model.Akismet;
import com.liferay.osb.community.akismet.model.impl.AkismetImpl;
import com.liferay.osb.community.akismet.model.impl.AkismetModelImpl;
import com.liferay.osb.community.akismet.service.persistence.AkismetPersistence;

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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the akismet service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see AkismetPersistence
 * @see com.liferay.osb.community.akismet.service.persistence.AkismetUtil
 * @generated
 */
@ProviderType
public class AkismetPersistenceImpl extends BasePersistenceImpl<Akismet>
	implements AkismetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AkismetUtil} to access the akismet persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AkismetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AkismetModelImpl.ENTITY_CACHE_ENABLED,
			AkismetModelImpl.FINDER_CACHE_ENABLED, AkismetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AkismetModelImpl.ENTITY_CACHE_ENABLED,
			AkismetModelImpl.FINDER_CACHE_ENABLED, AkismetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AkismetModelImpl.ENTITY_CACHE_ENABLED,
			AkismetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LTMODIFIEDDATE =
		new FinderPath(AkismetModelImpl.ENTITY_CACHE_ENABLED,
			AkismetModelImpl.FINDER_CACHE_ENABLED, AkismetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLtModifiedDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTMODIFIEDDATE =
		new FinderPath(AkismetModelImpl.ENTITY_CACHE_ENABLED,
			AkismetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtModifiedDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the akismets where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the matching akismets
	 */
	@Override
	public List<Akismet> findByLtModifiedDate(Date modifiedDate) {
		return findByLtModifiedDate(modifiedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the akismets where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of akismets
	 * @param end the upper bound of the range of akismets (not inclusive)
	 * @return the range of matching akismets
	 */
	@Override
	public List<Akismet> findByLtModifiedDate(Date modifiedDate, int start,
		int end) {
		return findByLtModifiedDate(modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the akismets where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of akismets
	 * @param end the upper bound of the range of akismets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching akismets
	 */
	@Override
	public List<Akismet> findByLtModifiedDate(Date modifiedDate, int start,
		int end, OrderByComparator<Akismet> orderByComparator) {
		return findByLtModifiedDate(modifiedDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the akismets where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of akismets
	 * @param end the upper bound of the range of akismets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching akismets
	 */
	@Override
	public List<Akismet> findByLtModifiedDate(Date modifiedDate, int start,
		int end, OrderByComparator<Akismet> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LTMODIFIEDDATE;
		finderArgs = new Object[] { modifiedDate, start, end, orderByComparator };

		List<Akismet> list = null;

		if (retrieveFromCache) {
			list = (List<Akismet>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Akismet akismet : list) {
					if ((modifiedDate.getTime() <= akismet.getModifiedDate()
															  .getTime())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_AKISMET_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AkismetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				if (!pagination) {
					list = (List<Akismet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Akismet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first akismet in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching akismet
	 * @throws NoSuchAkismetException if a matching akismet could not be found
	 */
	@Override
	public Akismet findByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator<Akismet> orderByComparator)
		throws NoSuchAkismetException {
		Akismet akismet = fetchByLtModifiedDate_First(modifiedDate,
				orderByComparator);

		if (akismet != null) {
			return akismet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchAkismetException(msg.toString());
	}

	/**
	 * Returns the first akismet in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching akismet, or <code>null</code> if a matching akismet could not be found
	 */
	@Override
	public Akismet fetchByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator<Akismet> orderByComparator) {
		List<Akismet> list = findByLtModifiedDate(modifiedDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last akismet in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching akismet
	 * @throws NoSuchAkismetException if a matching akismet could not be found
	 */
	@Override
	public Akismet findByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<Akismet> orderByComparator)
		throws NoSuchAkismetException {
		Akismet akismet = fetchByLtModifiedDate_Last(modifiedDate,
				orderByComparator);

		if (akismet != null) {
			return akismet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append("}");

		throw new NoSuchAkismetException(msg.toString());
	}

	/**
	 * Returns the last akismet in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching akismet, or <code>null</code> if a matching akismet could not be found
	 */
	@Override
	public Akismet fetchByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<Akismet> orderByComparator) {
		int count = countByLtModifiedDate(modifiedDate);

		if (count == 0) {
			return null;
		}

		List<Akismet> list = findByLtModifiedDate(modifiedDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the akismets before and after the current akismet in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param akismetId the primary key of the current akismet
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next akismet
	 * @throws NoSuchAkismetException if a akismet with the primary key could not be found
	 */
	@Override
	public Akismet[] findByLtModifiedDate_PrevAndNext(long akismetId,
		Date modifiedDate, OrderByComparator<Akismet> orderByComparator)
		throws NoSuchAkismetException {
		Akismet akismet = findByPrimaryKey(akismetId);

		Session session = null;

		try {
			session = openSession();

			Akismet[] array = new AkismetImpl[3];

			array[0] = getByLtModifiedDate_PrevAndNext(session, akismet,
					modifiedDate, orderByComparator, true);

			array[1] = akismet;

			array[2] = getByLtModifiedDate_PrevAndNext(session, akismet,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Akismet getByLtModifiedDate_PrevAndNext(Session session,
		Akismet akismet, Date modifiedDate,
		OrderByComparator<Akismet> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AKISMET_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
		}

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
			query.append(AkismetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(akismet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Akismet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the akismets where modifiedDate &lt; &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 */
	@Override
	public void removeByLtModifiedDate(Date modifiedDate) {
		for (Akismet akismet : findByLtModifiedDate(modifiedDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(akismet);
		}
	}

	/**
	 * Returns the number of akismets where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the number of matching akismets
	 */
	@Override
	public int countByLtModifiedDate(Date modifiedDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTMODIFIEDDATE;

		Object[] finderArgs = new Object[] { modifiedDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_AKISMET_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

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

	private static final String _FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1 = "akismet.modifiedDate IS NULL";
	private static final String _FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2 = "akismet.modifiedDate < ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C = new FinderPath(AkismetModelImpl.ENTITY_CACHE_ENABLED,
			AkismetModelImpl.FINDER_CACHE_ENABLED, AkismetImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AkismetModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AkismetModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(AkismetModelImpl.ENTITY_CACHE_ENABLED,
			AkismetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the akismet where classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchAkismetException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching akismet
	 * @throws NoSuchAkismetException if a matching akismet could not be found
	 */
	@Override
	public Akismet findByC_C(long classNameId, long classPK)
		throws NoSuchAkismetException {
		Akismet akismet = fetchByC_C(classNameId, classPK);

		if (akismet == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAkismetException(msg.toString());
		}

		return akismet;
	}

	/**
	 * Returns the akismet where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching akismet, or <code>null</code> if a matching akismet could not be found
	 */
	@Override
	public Akismet fetchByC_C(long classNameId, long classPK) {
		return fetchByC_C(classNameId, classPK, true);
	}

	/**
	 * Returns the akismet where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching akismet, or <code>null</code> if a matching akismet could not be found
	 */
	@Override
	public Akismet fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_C,
					finderArgs, this);
		}

		if (result instanceof Akismet) {
			Akismet akismet = (Akismet)result;

			if ((classNameId != akismet.getClassNameId()) ||
					(classPK != akismet.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_AKISMET_WHERE);

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

				List<Akismet> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_C, finderArgs,
						list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"AkismetPersistenceImpl.fetchByC_C(long, long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Akismet akismet = list.get(0);

					result = akismet;

					cacheResult(akismet);

					if ((akismet.getClassNameId() != classNameId) ||
							(akismet.getClassPK() != classPK)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_C_C,
							finderArgs, akismet);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Akismet)result;
		}
	}

	/**
	 * Removes the akismet where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the akismet that was removed
	 */
	@Override
	public Akismet removeByC_C(long classNameId, long classPK)
		throws NoSuchAkismetException {
		Akismet akismet = findByC_C(classNameId, classPK);

		return remove(akismet);
	}

	/**
	 * Returns the number of akismets where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching akismets
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_AKISMET_WHERE);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "akismet.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "akismet.classPK = ?";

	public AkismetPersistenceImpl() {
		setModelClass(Akismet.class);

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
	 * Caches the akismet in the entity cache if it is enabled.
	 *
	 * @param akismet the akismet
	 */
	@Override
	public void cacheResult(Akismet akismet) {
		entityCache.putResult(AkismetModelImpl.ENTITY_CACHE_ENABLED,
			AkismetImpl.class, akismet.getPrimaryKey(), akismet);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] { akismet.getClassNameId(), akismet.getClassPK() },
			akismet);

		akismet.resetOriginalValues();
	}

	/**
	 * Caches the akismets in the entity cache if it is enabled.
	 *
	 * @param akismets the akismets
	 */
	@Override
	public void cacheResult(List<Akismet> akismets) {
		for (Akismet akismet : akismets) {
			if (entityCache.getResult(AkismetModelImpl.ENTITY_CACHE_ENABLED,
						AkismetImpl.class, akismet.getPrimaryKey()) == null) {
				cacheResult(akismet);
			}
			else {
				akismet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all akismets.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AkismetImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the akismet.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Akismet akismet) {
		entityCache.removeResult(AkismetModelImpl.ENTITY_CACHE_ENABLED,
			AkismetImpl.class, akismet.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AkismetModelImpl)akismet, true);
	}

	@Override
	public void clearCache(List<Akismet> akismets) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Akismet akismet : akismets) {
			entityCache.removeResult(AkismetModelImpl.ENTITY_CACHE_ENABLED,
				AkismetImpl.class, akismet.getPrimaryKey());

			clearUniqueFindersCache((AkismetModelImpl)akismet, true);
		}
	}

	protected void cacheUniqueFindersCache(AkismetModelImpl akismetModelImpl) {
		Object[] args = new Object[] {
				akismetModelImpl.getClassNameId(), akismetModelImpl.getClassPK()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_C, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_C, args, akismetModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(AkismetModelImpl akismetModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					akismetModelImpl.getClassNameId(),
					akismetModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C, args);
		}

		if ((akismetModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					akismetModelImpl.getOriginalClassNameId(),
					akismetModelImpl.getOriginalClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_C, args);
		}
	}

	/**
	 * Creates a new akismet with the primary key. Does not add the akismet to the database.
	 *
	 * @param akismetId the primary key for the new akismet
	 * @return the new akismet
	 */
	@Override
	public Akismet create(long akismetId) {
		Akismet akismet = new AkismetImpl();

		akismet.setNew(true);
		akismet.setPrimaryKey(akismetId);

		return akismet;
	}

	/**
	 * Removes the akismet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param akismetId the primary key of the akismet
	 * @return the akismet that was removed
	 * @throws NoSuchAkismetException if a akismet with the primary key could not be found
	 */
	@Override
	public Akismet remove(long akismetId) throws NoSuchAkismetException {
		return remove((Serializable)akismetId);
	}

	/**
	 * Removes the akismet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the akismet
	 * @return the akismet that was removed
	 * @throws NoSuchAkismetException if a akismet with the primary key could not be found
	 */
	@Override
	public Akismet remove(Serializable primaryKey)
		throws NoSuchAkismetException {
		Session session = null;

		try {
			session = openSession();

			Akismet akismet = (Akismet)session.get(AkismetImpl.class, primaryKey);

			if (akismet == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAkismetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(akismet);
		}
		catch (NoSuchAkismetException nsee) {
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
	protected Akismet removeImpl(Akismet akismet) {
		akismet = toUnwrappedModel(akismet);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(akismet)) {
				akismet = (Akismet)session.get(AkismetImpl.class,
						akismet.getPrimaryKeyObj());
			}

			if (akismet != null) {
				session.delete(akismet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (akismet != null) {
			clearCache(akismet);
		}

		return akismet;
	}

	@Override
	public Akismet updateImpl(Akismet akismet) {
		akismet = toUnwrappedModel(akismet);

		boolean isNew = akismet.isNew();

		AkismetModelImpl akismetModelImpl = (AkismetModelImpl)akismet;

		Session session = null;

		try {
			session = openSession();

			if (akismet.isNew()) {
				session.save(akismet);

				akismet.setNew(false);
			}
			else {
				akismet = (Akismet)session.merge(akismet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!AkismetModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(AkismetModelImpl.ENTITY_CACHE_ENABLED,
			AkismetImpl.class, akismet.getPrimaryKey(), akismet, false);

		clearUniqueFindersCache(akismetModelImpl, false);
		cacheUniqueFindersCache(akismetModelImpl);

		akismet.resetOriginalValues();

		return akismet;
	}

	protected Akismet toUnwrappedModel(Akismet akismet) {
		if (akismet instanceof AkismetImpl) {
			return akismet;
		}

		AkismetImpl akismetImpl = new AkismetImpl();

		akismetImpl.setNew(akismet.isNew());
		akismetImpl.setPrimaryKey(akismet.getPrimaryKey());

		akismetImpl.setAkismetId(akismet.getAkismetId());
		akismetImpl.setModifiedDate(akismet.getModifiedDate());
		akismetImpl.setClassNameId(akismet.getClassNameId());
		akismetImpl.setClassPK(akismet.getClassPK());
		akismetImpl.setType(akismet.getType());
		akismetImpl.setPermalink(akismet.getPermalink());
		akismetImpl.setReferrer(akismet.getReferrer());
		akismetImpl.setUserAgent(akismet.getUserAgent());
		akismetImpl.setUserIP(akismet.getUserIP());
		akismetImpl.setUserURL(akismet.getUserURL());

		return akismetImpl;
	}

	/**
	 * Returns the akismet with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the akismet
	 * @return the akismet
	 * @throws NoSuchAkismetException if a akismet with the primary key could not be found
	 */
	@Override
	public Akismet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAkismetException {
		Akismet akismet = fetchByPrimaryKey(primaryKey);

		if (akismet == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAkismetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return akismet;
	}

	/**
	 * Returns the akismet with the primary key or throws a {@link NoSuchAkismetException} if it could not be found.
	 *
	 * @param akismetId the primary key of the akismet
	 * @return the akismet
	 * @throws NoSuchAkismetException if a akismet with the primary key could not be found
	 */
	@Override
	public Akismet findByPrimaryKey(long akismetId)
		throws NoSuchAkismetException {
		return findByPrimaryKey((Serializable)akismetId);
	}

	/**
	 * Returns the akismet with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the akismet
	 * @return the akismet, or <code>null</code> if a akismet with the primary key could not be found
	 */
	@Override
	public Akismet fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AkismetModelImpl.ENTITY_CACHE_ENABLED,
				AkismetImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Akismet akismet = (Akismet)serializable;

		if (akismet == null) {
			Session session = null;

			try {
				session = openSession();

				akismet = (Akismet)session.get(AkismetImpl.class, primaryKey);

				if (akismet != null) {
					cacheResult(akismet);
				}
				else {
					entityCache.putResult(AkismetModelImpl.ENTITY_CACHE_ENABLED,
						AkismetImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AkismetModelImpl.ENTITY_CACHE_ENABLED,
					AkismetImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return akismet;
	}

	/**
	 * Returns the akismet with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param akismetId the primary key of the akismet
	 * @return the akismet, or <code>null</code> if a akismet with the primary key could not be found
	 */
	@Override
	public Akismet fetchByPrimaryKey(long akismetId) {
		return fetchByPrimaryKey((Serializable)akismetId);
	}

	@Override
	public Map<Serializable, Akismet> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Akismet> map = new HashMap<Serializable, Akismet>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Akismet akismet = fetchByPrimaryKey(primaryKey);

			if (akismet != null) {
				map.put(primaryKey, akismet);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AkismetModelImpl.ENTITY_CACHE_ENABLED,
					AkismetImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Akismet)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_AKISMET_WHERE_PKS_IN);

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

			for (Akismet akismet : (List<Akismet>)q.list()) {
				map.put(akismet.getPrimaryKeyObj(), akismet);

				cacheResult(akismet);

				uncachedPrimaryKeys.remove(akismet.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AkismetModelImpl.ENTITY_CACHE_ENABLED,
					AkismetImpl.class, primaryKey, nullModel);
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
	 * Returns all the akismets.
	 *
	 * @return the akismets
	 */
	@Override
	public List<Akismet> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the akismets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of akismets
	 * @param end the upper bound of the range of akismets (not inclusive)
	 * @return the range of akismets
	 */
	@Override
	public List<Akismet> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the akismets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of akismets
	 * @param end the upper bound of the range of akismets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of akismets
	 */
	@Override
	public List<Akismet> findAll(int start, int end,
		OrderByComparator<Akismet> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the akismets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of akismets
	 * @param end the upper bound of the range of akismets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of akismets
	 */
	@Override
	public List<Akismet> findAll(int start, int end,
		OrderByComparator<Akismet> orderByComparator, boolean retrieveFromCache) {
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

		List<Akismet> list = null;

		if (retrieveFromCache) {
			list = (List<Akismet>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_AKISMET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AKISMET;

				if (pagination) {
					sql = sql.concat(AkismetModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Akismet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Akismet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the akismets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Akismet akismet : findAll()) {
			remove(akismet);
		}
	}

	/**
	 * Returns the number of akismets.
	 *
	 * @return the number of akismets
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AKISMET);

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
		return AkismetModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the akismet persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AkismetImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_AKISMET = "SELECT akismet FROM Akismet akismet";
	private static final String _SQL_SELECT_AKISMET_WHERE_PKS_IN = "SELECT akismet FROM Akismet akismet WHERE akismetId IN (";
	private static final String _SQL_SELECT_AKISMET_WHERE = "SELECT akismet FROM Akismet akismet WHERE ";
	private static final String _SQL_COUNT_AKISMET = "SELECT COUNT(akismet) FROM Akismet akismet";
	private static final String _SQL_COUNT_AKISMET_WHERE = "SELECT COUNT(akismet) FROM Akismet akismet WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "akismet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Akismet exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Akismet exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AkismetPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}