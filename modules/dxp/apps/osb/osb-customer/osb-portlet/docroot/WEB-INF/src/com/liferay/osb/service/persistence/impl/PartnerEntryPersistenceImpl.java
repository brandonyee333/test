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

package com.liferay.osb.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchPartnerEntryException;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.impl.PartnerEntryImpl;
import com.liferay.osb.model.impl.PartnerEntryModelImpl;
import com.liferay.osb.service.persistence.PartnerEntryPersistence;
import com.liferay.osb.service.persistence.SupportRegionPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the partner entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryPersistence
 * @see com.liferay.osb.service.persistence.PartnerEntryUtil
 * @generated
 */
@ProviderType
public class PartnerEntryPersistenceImpl extends BasePersistenceImpl<PartnerEntry>
	implements PartnerEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PartnerEntryUtil} to access the partner entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PartnerEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPARTNERENTRYID =
		new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByParentPartnerEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPARTNERENTRYID =
		new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByParentPartnerEntryId",
			new String[] { Long.class.getName() },
			PartnerEntryModelImpl.PARENTPARTNERENTRYID_COLUMN_BITMASK |
			PartnerEntryModelImpl.CODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTPARTNERENTRYID = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByParentPartnerEntryId", new String[] { Long.class.getName() });

	/**
	 * Returns all the partner entries where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @return the matching partner entries
	 */
	@Override
	public List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId) {
		return findByParentPartnerEntryId(parentPartnerEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner entries where parentPartnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @return the range of matching partner entries
	 */
	@Override
	public List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end) {
		return findByParentPartnerEntryId(parentPartnerEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner entries where parentPartnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching partner entries
	 */
	@Override
	public List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end,
		OrderByComparator<PartnerEntry> orderByComparator) {
		return findByParentPartnerEntryId(parentPartnerEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the partner entries where parentPartnerEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching partner entries
	 */
	@Override
	public List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end,
		OrderByComparator<PartnerEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPARTNERENTRYID;
			finderArgs = new Object[] { parentPartnerEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPARTNERENTRYID;
			finderArgs = new Object[] {
					parentPartnerEntryId,
					
					start, end, orderByComparator
				};
		}

		List<PartnerEntry> list = null;

		if (retrieveFromCache) {
			list = (List<PartnerEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PartnerEntry partnerEntry : list) {
					if ((parentPartnerEntryId != partnerEntry.getParentPartnerEntryId())) {
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

			query.append(_SQL_SELECT_PARTNERENTRY_WHERE);

			query.append(_FINDER_COLUMN_PARENTPARTNERENTRYID_PARENTPARTNERENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PartnerEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentPartnerEntryId);

				if (!pagination) {
					list = (List<PartnerEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PartnerEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first partner entry in the ordered set where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner entry
	 * @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	 */
	@Override
	public PartnerEntry findByParentPartnerEntryId_First(
		long parentPartnerEntryId,
		OrderByComparator<PartnerEntry> orderByComparator)
		throws NoSuchPartnerEntryException {
		PartnerEntry partnerEntry = fetchByParentPartnerEntryId_First(parentPartnerEntryId,
				orderByComparator);

		if (partnerEntry != null) {
			return partnerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentPartnerEntryId=");
		msg.append(parentPartnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerEntryException(msg.toString());
	}

	/**
	 * Returns the first partner entry in the ordered set where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 */
	@Override
	public PartnerEntry fetchByParentPartnerEntryId_First(
		long parentPartnerEntryId,
		OrderByComparator<PartnerEntry> orderByComparator) {
		List<PartnerEntry> list = findByParentPartnerEntryId(parentPartnerEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last partner entry in the ordered set where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner entry
	 * @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	 */
	@Override
	public PartnerEntry findByParentPartnerEntryId_Last(
		long parentPartnerEntryId,
		OrderByComparator<PartnerEntry> orderByComparator)
		throws NoSuchPartnerEntryException {
		PartnerEntry partnerEntry = fetchByParentPartnerEntryId_Last(parentPartnerEntryId,
				orderByComparator);

		if (partnerEntry != null) {
			return partnerEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentPartnerEntryId=");
		msg.append(parentPartnerEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPartnerEntryException(msg.toString());
	}

	/**
	 * Returns the last partner entry in the ordered set where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 */
	@Override
	public PartnerEntry fetchByParentPartnerEntryId_Last(
		long parentPartnerEntryId,
		OrderByComparator<PartnerEntry> orderByComparator) {
		int count = countByParentPartnerEntryId(parentPartnerEntryId);

		if (count == 0) {
			return null;
		}

		List<PartnerEntry> list = findByParentPartnerEntryId(parentPartnerEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the partner entries before and after the current partner entry in the ordered set where parentPartnerEntryId = &#63;.
	 *
	 * @param partnerEntryId the primary key of the current partner entry
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next partner entry
	 * @throws NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	 */
	@Override
	public PartnerEntry[] findByParentPartnerEntryId_PrevAndNext(
		long partnerEntryId, long parentPartnerEntryId,
		OrderByComparator<PartnerEntry> orderByComparator)
		throws NoSuchPartnerEntryException {
		PartnerEntry partnerEntry = findByPrimaryKey(partnerEntryId);

		Session session = null;

		try {
			session = openSession();

			PartnerEntry[] array = new PartnerEntryImpl[3];

			array[0] = getByParentPartnerEntryId_PrevAndNext(session,
					partnerEntry, parentPartnerEntryId, orderByComparator, true);

			array[1] = partnerEntry;

			array[2] = getByParentPartnerEntryId_PrevAndNext(session,
					partnerEntry, parentPartnerEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PartnerEntry getByParentPartnerEntryId_PrevAndNext(
		Session session, PartnerEntry partnerEntry, long parentPartnerEntryId,
		OrderByComparator<PartnerEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PARTNERENTRY_WHERE);

		query.append(_FINDER_COLUMN_PARENTPARTNERENTRYID_PARENTPARTNERENTRYID_2);

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
			query.append(PartnerEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentPartnerEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(partnerEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PartnerEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the partner entries where parentPartnerEntryId = &#63; from the database.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 */
	@Override
	public void removeByParentPartnerEntryId(long parentPartnerEntryId) {
		for (PartnerEntry partnerEntry : findByParentPartnerEntryId(
				parentPartnerEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(partnerEntry);
		}
	}

	/**
	 * Returns the number of partner entries where parentPartnerEntryId = &#63;.
	 *
	 * @param parentPartnerEntryId the parent partner entry ID
	 * @return the number of matching partner entries
	 */
	@Override
	public int countByParentPartnerEntryId(long parentPartnerEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PARENTPARTNERENTRYID;

		Object[] finderArgs = new Object[] { parentPartnerEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PARTNERENTRY_WHERE);

			query.append(_FINDER_COLUMN_PARENTPARTNERENTRYID_PARENTPARTNERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentPartnerEntryId);

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

	private static final String _FINDER_COLUMN_PARENTPARTNERENTRYID_PARENTPARTNERENTRYID_2 =
		"partnerEntry.parentPartnerEntryId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDossieraAccountKey",
			new String[] { String.class.getName() },
			PartnerEntryModelImpl.DOSSIERAACCOUNTKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDossieraAccountKey", new String[] { String.class.getName() });

	/**
	 * Returns the partner entry where dossieraAccountKey = &#63; or throws a {@link NoSuchPartnerEntryException} if it could not be found.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the matching partner entry
	 * @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	 */
	@Override
	public PartnerEntry findByDossieraAccountKey(String dossieraAccountKey)
		throws NoSuchPartnerEntryException {
		PartnerEntry partnerEntry = fetchByDossieraAccountKey(dossieraAccountKey);

		if (partnerEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("dossieraAccountKey=");
			msg.append(dossieraAccountKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPartnerEntryException(msg.toString());
		}

		return partnerEntry;
	}

	/**
	 * Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 */
	@Override
	public PartnerEntry fetchByDossieraAccountKey(String dossieraAccountKey) {
		return fetchByDossieraAccountKey(dossieraAccountKey, true);
	}

	/**
	 * Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 */
	@Override
	public PartnerEntry fetchByDossieraAccountKey(String dossieraAccountKey,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { dossieraAccountKey };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
					finderArgs, this);
		}

		if (result instanceof PartnerEntry) {
			PartnerEntry partnerEntry = (PartnerEntry)result;

			if (!Objects.equals(dossieraAccountKey,
						partnerEntry.getDossieraAccountKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PARTNERENTRY_WHERE);

			boolean bindDossieraAccountKey = false;

			if (dossieraAccountKey == null) {
				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1);
			}
			else if (dossieraAccountKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3);
			}
			else {
				bindDossieraAccountKey = true;

				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossieraAccountKey) {
					qPos.add(dossieraAccountKey);
				}

				List<PartnerEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PartnerEntryPersistenceImpl.fetchByDossieraAccountKey(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					PartnerEntry partnerEntry = list.get(0);

					result = partnerEntry;

					cacheResult(partnerEntry);

					if ((partnerEntry.getDossieraAccountKey() == null) ||
							!partnerEntry.getDossieraAccountKey()
											 .equals(dossieraAccountKey)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
							finderArgs, partnerEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
					finderArgs);

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
			return (PartnerEntry)result;
		}
	}

	/**
	 * Removes the partner entry where dossieraAccountKey = &#63; from the database.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the partner entry that was removed
	 */
	@Override
	public PartnerEntry removeByDossieraAccountKey(String dossieraAccountKey)
		throws NoSuchPartnerEntryException {
		PartnerEntry partnerEntry = findByDossieraAccountKey(dossieraAccountKey);

		return remove(partnerEntry);
	}

	/**
	 * Returns the number of partner entries where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the number of matching partner entries
	 */
	@Override
	public int countByDossieraAccountKey(String dossieraAccountKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY;

		Object[] finderArgs = new Object[] { dossieraAccountKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PARTNERENTRY_WHERE);

			boolean bindDossieraAccountKey = false;

			if (dossieraAccountKey == null) {
				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1);
			}
			else if (dossieraAccountKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3);
			}
			else {
				bindDossieraAccountKey = true;

				query.append(_FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDossieraAccountKey) {
					qPos.add(dossieraAccountKey);
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

	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_1 =
		"partnerEntry.dossieraAccountKey IS NULL";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_2 =
		"partnerEntry.dossieraAccountKey = ?";
	private static final String _FINDER_COLUMN_DOSSIERAACCOUNTKEY_DOSSIERAACCOUNTKEY_3 =
		"(partnerEntry.dossieraAccountKey IS NULL OR partnerEntry.dossieraAccountKey = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_CODE = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, PartnerEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByCode",
			new String[] { String.class.getName() },
			PartnerEntryModelImpl.CODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CODE = new FinderPath(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCode",
			new String[] { String.class.getName() });

	/**
	 * Returns the partner entry where code = &#63; or throws a {@link NoSuchPartnerEntryException} if it could not be found.
	 *
	 * @param code the code
	 * @return the matching partner entry
	 * @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	 */
	@Override
	public PartnerEntry findByCode(String code)
		throws NoSuchPartnerEntryException {
		PartnerEntry partnerEntry = fetchByCode(code);

		if (partnerEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("code=");
			msg.append(code);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchPartnerEntryException(msg.toString());
		}

		return partnerEntry;
	}

	/**
	 * Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param code the code
	 * @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 */
	@Override
	public PartnerEntry fetchByCode(String code) {
		return fetchByCode(code, true);
	}

	/**
	 * Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param code the code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	 */
	@Override
	public PartnerEntry fetchByCode(String code, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { code };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_CODE,
					finderArgs, this);
		}

		if (result instanceof PartnerEntry) {
			PartnerEntry partnerEntry = (PartnerEntry)result;

			if (!Objects.equals(code, partnerEntry.getCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PARTNERENTRY_WHERE);

			boolean bindCode = false;

			if (code == null) {
				query.append(_FINDER_COLUMN_CODE_CODE_1);
			}
			else if (code.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CODE_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_CODE_CODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCode) {
					qPos.add(code);
				}

				List<PartnerEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_CODE,
						finderArgs, list);
				}
				else {
					PartnerEntry partnerEntry = list.get(0);

					result = partnerEntry;

					cacheResult(partnerEntry);

					if ((partnerEntry.getCode() == null) ||
							!partnerEntry.getCode().equals(code)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_CODE,
							finderArgs, partnerEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_CODE, finderArgs);

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
			return (PartnerEntry)result;
		}
	}

	/**
	 * Removes the partner entry where code = &#63; from the database.
	 *
	 * @param code the code
	 * @return the partner entry that was removed
	 */
	@Override
	public PartnerEntry removeByCode(String code)
		throws NoSuchPartnerEntryException {
		PartnerEntry partnerEntry = findByCode(code);

		return remove(partnerEntry);
	}

	/**
	 * Returns the number of partner entries where code = &#63;.
	 *
	 * @param code the code
	 * @return the number of matching partner entries
	 */
	@Override
	public int countByCode(String code) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CODE;

		Object[] finderArgs = new Object[] { code };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PARTNERENTRY_WHERE);

			boolean bindCode = false;

			if (code == null) {
				query.append(_FINDER_COLUMN_CODE_CODE_1);
			}
			else if (code.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CODE_CODE_3);
			}
			else {
				bindCode = true;

				query.append(_FINDER_COLUMN_CODE_CODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCode) {
					qPos.add(code);
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

	private static final String _FINDER_COLUMN_CODE_CODE_1 = "partnerEntry.code IS NULL";
	private static final String _FINDER_COLUMN_CODE_CODE_2 = "partnerEntry.code = ?";
	private static final String _FINDER_COLUMN_CODE_CODE_3 = "(partnerEntry.code IS NULL OR partnerEntry.code = '')";

	public PartnerEntryPersistenceImpl() {
		setModelClass(PartnerEntry.class);
	}

	/**
	 * Caches the partner entry in the entity cache if it is enabled.
	 *
	 * @param partnerEntry the partner entry
	 */
	@Override
	public void cacheResult(PartnerEntry partnerEntry) {
		entityCache.putResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryImpl.class, partnerEntry.getPrimaryKey(), partnerEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
			new Object[] { partnerEntry.getDossieraAccountKey() }, partnerEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_CODE,
			new Object[] { partnerEntry.getCode() }, partnerEntry);

		partnerEntry.resetOriginalValues();
	}

	/**
	 * Caches the partner entries in the entity cache if it is enabled.
	 *
	 * @param partnerEntries the partner entries
	 */
	@Override
	public void cacheResult(List<PartnerEntry> partnerEntries) {
		for (PartnerEntry partnerEntry : partnerEntries) {
			if (entityCache.getResult(
						PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
						PartnerEntryImpl.class, partnerEntry.getPrimaryKey()) == null) {
				cacheResult(partnerEntry);
			}
			else {
				partnerEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all partner entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PartnerEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the partner entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PartnerEntry partnerEntry) {
		entityCache.removeResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryImpl.class, partnerEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PartnerEntryModelImpl)partnerEntry, true);
	}

	@Override
	public void clearCache(List<PartnerEntry> partnerEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PartnerEntry partnerEntry : partnerEntries) {
			entityCache.removeResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
				PartnerEntryImpl.class, partnerEntry.getPrimaryKey());

			clearUniqueFindersCache((PartnerEntryModelImpl)partnerEntry, true);
		}
	}

	protected void cacheUniqueFindersCache(
		PartnerEntryModelImpl partnerEntryModelImpl) {
		Object[] args = new Object[] {
				partnerEntryModelImpl.getDossieraAccountKey()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY, args,
			partnerEntryModelImpl, false);

		args = new Object[] { partnerEntryModelImpl.getCode() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_CODE, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_CODE, args,
			partnerEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		PartnerEntryModelImpl partnerEntryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					partnerEntryModelImpl.getDossieraAccountKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
				args);
		}

		if ((partnerEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					partnerEntryModelImpl.getOriginalDossieraAccountKey()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DOSSIERAACCOUNTKEY,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_DOSSIERAACCOUNTKEY,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { partnerEntryModelImpl.getCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CODE, args);
		}

		if ((partnerEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { partnerEntryModelImpl.getOriginalCode() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_CODE, args);
		}
	}

	/**
	 * Creates a new partner entry with the primary key. Does not add the partner entry to the database.
	 *
	 * @param partnerEntryId the primary key for the new partner entry
	 * @return the new partner entry
	 */
	@Override
	public PartnerEntry create(long partnerEntryId) {
		PartnerEntry partnerEntry = new PartnerEntryImpl();

		partnerEntry.setNew(true);
		partnerEntry.setPrimaryKey(partnerEntryId);

		partnerEntry.setCompanyId(companyProvider.getCompanyId());

		return partnerEntry;
	}

	/**
	 * Removes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param partnerEntryId the primary key of the partner entry
	 * @return the partner entry that was removed
	 * @throws NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	 */
	@Override
	public PartnerEntry remove(long partnerEntryId)
		throws NoSuchPartnerEntryException {
		return remove((Serializable)partnerEntryId);
	}

	/**
	 * Removes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the partner entry
	 * @return the partner entry that was removed
	 * @throws NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	 */
	@Override
	public PartnerEntry remove(Serializable primaryKey)
		throws NoSuchPartnerEntryException {
		Session session = null;

		try {
			session = openSession();

			PartnerEntry partnerEntry = (PartnerEntry)session.get(PartnerEntryImpl.class,
					primaryKey);

			if (partnerEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPartnerEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(partnerEntry);
		}
		catch (NoSuchPartnerEntryException nsee) {
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
	protected PartnerEntry removeImpl(PartnerEntry partnerEntry) {
		partnerEntry = toUnwrappedModel(partnerEntry);

		partnerEntryToSupportRegionTableMapper.deleteLeftPrimaryKeyTableMappings(partnerEntry.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(partnerEntry)) {
				partnerEntry = (PartnerEntry)session.get(PartnerEntryImpl.class,
						partnerEntry.getPrimaryKeyObj());
			}

			if (partnerEntry != null) {
				session.delete(partnerEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (partnerEntry != null) {
			clearCache(partnerEntry);
		}

		return partnerEntry;
	}

	@Override
	public PartnerEntry updateImpl(PartnerEntry partnerEntry) {
		partnerEntry = toUnwrappedModel(partnerEntry);

		boolean isNew = partnerEntry.isNew();

		PartnerEntryModelImpl partnerEntryModelImpl = (PartnerEntryModelImpl)partnerEntry;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (partnerEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				partnerEntry.setCreateDate(now);
			}
			else {
				partnerEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!partnerEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				partnerEntry.setModifiedDate(now);
			}
			else {
				partnerEntry.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (partnerEntry.isNew()) {
				session.save(partnerEntry);

				partnerEntry.setNew(false);
			}
			else {
				partnerEntry = (PartnerEntry)session.merge(partnerEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PartnerEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					partnerEntryModelImpl.getParentPartnerEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PARENTPARTNERENTRYID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPARTNERENTRYID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((partnerEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPARTNERENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						partnerEntryModelImpl.getOriginalParentPartnerEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARENTPARTNERENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPARTNERENTRYID,
					args);

				args = new Object[] {
						partnerEntryModelImpl.getParentPartnerEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARENTPARTNERENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPARTNERENTRYID,
					args);
			}
		}

		entityCache.putResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
			PartnerEntryImpl.class, partnerEntry.getPrimaryKey(), partnerEntry,
			false);

		clearUniqueFindersCache(partnerEntryModelImpl, false);
		cacheUniqueFindersCache(partnerEntryModelImpl);

		partnerEntry.resetOriginalValues();

		return partnerEntry;
	}

	protected PartnerEntry toUnwrappedModel(PartnerEntry partnerEntry) {
		if (partnerEntry instanceof PartnerEntryImpl) {
			return partnerEntry;
		}

		PartnerEntryImpl partnerEntryImpl = new PartnerEntryImpl();

		partnerEntryImpl.setNew(partnerEntry.isNew());
		partnerEntryImpl.setPrimaryKey(partnerEntry.getPrimaryKey());

		partnerEntryImpl.setPartnerEntryId(partnerEntry.getPartnerEntryId());
		partnerEntryImpl.setCompanyId(partnerEntry.getCompanyId());
		partnerEntryImpl.setUserId(partnerEntry.getUserId());
		partnerEntryImpl.setUserName(partnerEntry.getUserName());
		partnerEntryImpl.setCreateDate(partnerEntry.getCreateDate());
		partnerEntryImpl.setModifiedUserId(partnerEntry.getModifiedUserId());
		partnerEntryImpl.setModifiedUserName(partnerEntry.getModifiedUserName());
		partnerEntryImpl.setModifiedDate(partnerEntry.getModifiedDate());
		partnerEntryImpl.setParentPartnerEntryId(partnerEntry.getParentPartnerEntryId());
		partnerEntryImpl.setDossieraAccountKey(partnerEntry.getDossieraAccountKey());
		partnerEntryImpl.setCode(partnerEntry.getCode());
		partnerEntryImpl.setNotes(partnerEntry.getNotes());
		partnerEntryImpl.setStatus(partnerEntry.getStatus());

		return partnerEntryImpl;
	}

	/**
	 * Returns the partner entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the partner entry
	 * @return the partner entry
	 * @throws NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	 */
	@Override
	public PartnerEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPartnerEntryException {
		PartnerEntry partnerEntry = fetchByPrimaryKey(primaryKey);

		if (partnerEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPartnerEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return partnerEntry;
	}

	/**
	 * Returns the partner entry with the primary key or throws a {@link NoSuchPartnerEntryException} if it could not be found.
	 *
	 * @param partnerEntryId the primary key of the partner entry
	 * @return the partner entry
	 * @throws NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	 */
	@Override
	public PartnerEntry findByPrimaryKey(long partnerEntryId)
		throws NoSuchPartnerEntryException {
		return findByPrimaryKey((Serializable)partnerEntryId);
	}

	/**
	 * Returns the partner entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the partner entry
	 * @return the partner entry, or <code>null</code> if a partner entry with the primary key could not be found
	 */
	@Override
	public PartnerEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
				PartnerEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PartnerEntry partnerEntry = (PartnerEntry)serializable;

		if (partnerEntry == null) {
			Session session = null;

			try {
				session = openSession();

				partnerEntry = (PartnerEntry)session.get(PartnerEntryImpl.class,
						primaryKey);

				if (partnerEntry != null) {
					cacheResult(partnerEntry);
				}
				else {
					entityCache.putResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
						PartnerEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
					PartnerEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return partnerEntry;
	}

	/**
	 * Returns the partner entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param partnerEntryId the primary key of the partner entry
	 * @return the partner entry, or <code>null</code> if a partner entry with the primary key could not be found
	 */
	@Override
	public PartnerEntry fetchByPrimaryKey(long partnerEntryId) {
		return fetchByPrimaryKey((Serializable)partnerEntryId);
	}

	@Override
	public Map<Serializable, PartnerEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PartnerEntry> map = new HashMap<Serializable, PartnerEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PartnerEntry partnerEntry = fetchByPrimaryKey(primaryKey);

			if (partnerEntry != null) {
				map.put(primaryKey, partnerEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
					PartnerEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PartnerEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PARTNERENTRY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (PartnerEntry partnerEntry : (List<PartnerEntry>)q.list()) {
				map.put(partnerEntry.getPrimaryKeyObj(), partnerEntry);

				cacheResult(partnerEntry);

				uncachedPrimaryKeys.remove(partnerEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PartnerEntryModelImpl.ENTITY_CACHE_ENABLED,
					PartnerEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the partner entries.
	 *
	 * @return the partner entries
	 */
	@Override
	public List<PartnerEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the partner entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @return the range of partner entries
	 */
	@Override
	public List<PartnerEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the partner entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of partner entries
	 */
	@Override
	public List<PartnerEntry> findAll(int start, int end,
		OrderByComparator<PartnerEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the partner entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of partner entries
	 */
	@Override
	public List<PartnerEntry> findAll(int start, int end,
		OrderByComparator<PartnerEntry> orderByComparator,
		boolean retrieveFromCache) {
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

		List<PartnerEntry> list = null;

		if (retrieveFromCache) {
			list = (List<PartnerEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PARTNERENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PARTNERENTRY;

				if (pagination) {
					sql = sql.concat(PartnerEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PartnerEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PartnerEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the partner entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PartnerEntry partnerEntry : findAll()) {
			remove(partnerEntry);
		}
	}

	/**
	 * Returns the number of partner entries.
	 *
	 * @return the number of partner entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PARTNERENTRY);

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

	/**
	 * Returns the primaryKeys of support regions associated with the partner entry.
	 *
	 * @param pk the primary key of the partner entry
	 * @return long[] of the primaryKeys of support regions associated with the partner entry
	 */
	@Override
	public long[] getSupportRegionPrimaryKeys(long pk) {
		long[] pks = partnerEntryToSupportRegionTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the support regions associated with the partner entry.
	 *
	 * @param pk the primary key of the partner entry
	 * @return the support regions associated with the partner entry
	 */
	@Override
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(long pk) {
		return getSupportRegions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the support regions associated with the partner entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the partner entry
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @return the range of support regions associated with the partner entry
	 */
	@Override
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end) {
		return getSupportRegions(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support regions associated with the partner entry.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the partner entry
	 * @param start the lower bound of the range of partner entries
	 * @param end the upper bound of the range of partner entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support regions associated with the partner entry
	 */
	@Override
	public List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.model.SupportRegion> orderByComparator) {
		return partnerEntryToSupportRegionTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of support regions associated with the partner entry.
	 *
	 * @param pk the primary key of the partner entry
	 * @return the number of support regions associated with the partner entry
	 */
	@Override
	public int getSupportRegionsSize(long pk) {
		long[] pks = partnerEntryToSupportRegionTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the support region is associated with the partner entry.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPK the primary key of the support region
	 * @return <code>true</code> if the support region is associated with the partner entry; <code>false</code> otherwise
	 */
	@Override
	public boolean containsSupportRegion(long pk, long supportRegionPK) {
		return partnerEntryToSupportRegionTableMapper.containsTableMapping(pk,
			supportRegionPK);
	}

	/**
	 * Returns <code>true</code> if the partner entry has any support regions associated with it.
	 *
	 * @param pk the primary key of the partner entry to check for associations with support regions
	 * @return <code>true</code> if the partner entry has any support regions associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsSupportRegions(long pk) {
		if (getSupportRegionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPK the primary key of the support region
	 */
	@Override
	public void addSupportRegion(long pk, long supportRegionPK) {
		PartnerEntry partnerEntry = fetchByPrimaryKey(pk);

		if (partnerEntry == null) {
			partnerEntryToSupportRegionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, supportRegionPK);
		}
		else {
			partnerEntryToSupportRegionTableMapper.addTableMapping(partnerEntry.getCompanyId(),
				pk, supportRegionPK);
		}
	}

	/**
	 * Adds an association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegion the support region
	 */
	@Override
	public void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion) {
		PartnerEntry partnerEntry = fetchByPrimaryKey(pk);

		if (partnerEntry == null) {
			partnerEntryToSupportRegionTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, supportRegion.getPrimaryKey());
		}
		else {
			partnerEntryToSupportRegionTableMapper.addTableMapping(partnerEntry.getCompanyId(),
				pk, supportRegion.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPKs the primary keys of the support regions
	 */
	@Override
	public void addSupportRegions(long pk, long[] supportRegionPKs) {
		long companyId = 0;

		PartnerEntry partnerEntry = fetchByPrimaryKey(pk);

		if (partnerEntry == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = partnerEntry.getCompanyId();
		}

		partnerEntryToSupportRegionTableMapper.addTableMappings(companyId, pk,
			supportRegionPKs);
	}

	/**
	 * Adds an association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegions the support regions
	 */
	@Override
	public void addSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		addSupportRegions(pk,
			ListUtil.toLongArray(supportRegions,
				com.liferay.osb.model.SupportRegion.SUPPORT_REGION_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the partner entry and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry to clear the associated support regions from
	 */
	@Override
	public void clearSupportRegions(long pk) {
		partnerEntryToSupportRegionTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPK the primary key of the support region
	 */
	@Override
	public void removeSupportRegion(long pk, long supportRegionPK) {
		partnerEntryToSupportRegionTableMapper.deleteTableMapping(pk,
			supportRegionPK);
	}

	/**
	 * Removes the association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegion the support region
	 */
	@Override
	public void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion) {
		partnerEntryToSupportRegionTableMapper.deleteTableMapping(pk,
			supportRegion.getPrimaryKey());
	}

	/**
	 * Removes the association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPKs the primary keys of the support regions
	 */
	@Override
	public void removeSupportRegions(long pk, long[] supportRegionPKs) {
		partnerEntryToSupportRegionTableMapper.deleteTableMappings(pk,
			supportRegionPKs);
	}

	/**
	 * Removes the association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegions the support regions
	 */
	@Override
	public void removeSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		removeSupportRegions(pk,
			ListUtil.toLongArray(supportRegions,
				com.liferay.osb.model.SupportRegion.SUPPORT_REGION_ID_ACCESSOR));
	}

	/**
	 * Sets the support regions associated with the partner entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegionPKs the primary keys of the support regions to be associated with the partner entry
	 */
	@Override
	public void setSupportRegions(long pk, long[] supportRegionPKs) {
		Set<Long> newSupportRegionPKsSet = SetUtil.fromArray(supportRegionPKs);
		Set<Long> oldSupportRegionPKsSet = SetUtil.fromArray(partnerEntryToSupportRegionTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeSupportRegionPKsSet = new HashSet<Long>(oldSupportRegionPKsSet);

		removeSupportRegionPKsSet.removeAll(newSupportRegionPKsSet);

		partnerEntryToSupportRegionTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeSupportRegionPKsSet));

		newSupportRegionPKsSet.removeAll(oldSupportRegionPKsSet);

		long companyId = 0;

		PartnerEntry partnerEntry = fetchByPrimaryKey(pk);

		if (partnerEntry == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = partnerEntry.getCompanyId();
		}

		partnerEntryToSupportRegionTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newSupportRegionPKsSet));
	}

	/**
	 * Sets the support regions associated with the partner entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the partner entry
	 * @param supportRegions the support regions to be associated with the partner entry
	 */
	@Override
	public void setSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		try {
			long[] supportRegionPKs = new long[supportRegions.size()];

			for (int i = 0; i < supportRegions.size(); i++) {
				com.liferay.osb.model.SupportRegion supportRegion = supportRegions.get(i);

				supportRegionPKs[i] = supportRegion.getPrimaryKey();
			}

			setSupportRegions(pk, supportRegionPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PartnerEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the partner entry persistence.
	 */
	public void afterPropertiesSet() {
		partnerEntryToSupportRegionTableMapper = TableMapperFactory.getTableMapper("OSB_PartnerEntries_SupportRegions",
				"companyId", "partnerEntryId", "supportRegionId", this,
				supportRegionPersistence);
	}

	public void destroy() {
		entityCache.removeCache(PartnerEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"OSB_PartnerEntries_SupportRegions");
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	@BeanReference(type = SupportRegionPersistence.class)
	protected SupportRegionPersistence supportRegionPersistence;
	protected TableMapper<PartnerEntry, com.liferay.osb.model.SupportRegion> partnerEntryToSupportRegionTableMapper;
	private static final String _SQL_SELECT_PARTNERENTRY = "SELECT partnerEntry FROM PartnerEntry partnerEntry";
	private static final String _SQL_SELECT_PARTNERENTRY_WHERE_PKS_IN = "SELECT partnerEntry FROM PartnerEntry partnerEntry WHERE partnerEntryId IN (";
	private static final String _SQL_SELECT_PARTNERENTRY_WHERE = "SELECT partnerEntry FROM PartnerEntry partnerEntry WHERE ";
	private static final String _SQL_COUNT_PARTNERENTRY = "SELECT COUNT(partnerEntry) FROM PartnerEntry partnerEntry";
	private static final String _SQL_COUNT_PARTNERENTRY_WHERE = "SELECT COUNT(partnerEntry) FROM PartnerEntry partnerEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "partnerEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PartnerEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PartnerEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PartnerEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"code"
			});
}