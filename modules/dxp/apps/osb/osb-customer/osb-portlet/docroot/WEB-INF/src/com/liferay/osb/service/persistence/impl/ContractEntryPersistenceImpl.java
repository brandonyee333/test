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

import com.liferay.osb.exception.NoSuchContractEntryException;
import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.model.impl.ContractEntryImpl;
import com.liferay.osb.model.impl.ContractEntryModelImpl;
import com.liferay.osb.service.persistence.ContractEntryPersistence;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the contract entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractEntryPersistence
 * @see com.liferay.osb.service.persistence.ContractEntryUtil
 * @generated
 */
@ProviderType
public class ContractEntryPersistenceImpl extends BasePersistenceImpl<ContractEntry>
	implements ContractEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContractEntryUtil} to access the contract entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContractEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED,
			ContractEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED,
			ContractEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CN_CP_T = new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED,
			ContractEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCN_CP_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CP_T =
		new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED,
			ContractEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCN_CP_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			ContractEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			ContractEntryModelImpl.CLASSPK_COLUMN_BITMASK |
			ContractEntryModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CN_CP_T = new FinderPath(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCN_CP_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching contract entries
	 */
	@Override
	public List<ContractEntry> findByCN_CP_T(long classNameId, long classPK,
		int type) {
		return findByCN_CP_T(classNameId, classPK, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of contract entries
	 * @param end the upper bound of the range of contract entries (not inclusive)
	 * @return the range of matching contract entries
	 */
	@Override
	public List<ContractEntry> findByCN_CP_T(long classNameId, long classPK,
		int type, int start, int end) {
		return findByCN_CP_T(classNameId, classPK, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of contract entries
	 * @param end the upper bound of the range of contract entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contract entries
	 */
	@Override
	public List<ContractEntry> findByCN_CP_T(long classNameId, long classPK,
		int type, int start, int end,
		OrderByComparator<ContractEntry> orderByComparator) {
		return findByCN_CP_T(classNameId, classPK, type, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of contract entries
	 * @param end the upper bound of the range of contract entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching contract entries
	 */
	@Override
	public List<ContractEntry> findByCN_CP_T(long classNameId, long classPK,
		int type, int start, int end,
		OrderByComparator<ContractEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CP_T;
			finderArgs = new Object[] { classNameId, classPK, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CN_CP_T;
			finderArgs = new Object[] {
					classNameId, classPK, type,
					
					start, end, orderByComparator
				};
		}

		List<ContractEntry> list = null;

		if (retrieveFromCache) {
			list = (List<ContractEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContractEntry contractEntry : list) {
					if ((classNameId != contractEntry.getClassNameId()) ||
							(classPK != contractEntry.getClassPK()) ||
							(type != contractEntry.getType())) {
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

			query.append(_SQL_SELECT_CONTRACTENTRY_WHERE);

			query.append(_FINDER_COLUMN_CN_CP_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CN_CP_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_CN_CP_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ContractEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<ContractEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContractEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract entry
	 * @throws NoSuchContractEntryException if a matching contract entry could not be found
	 */
	@Override
	public ContractEntry findByCN_CP_T_First(long classNameId, long classPK,
		int type, OrderByComparator<ContractEntry> orderByComparator)
		throws NoSuchContractEntryException {
		ContractEntry contractEntry = fetchByCN_CP_T_First(classNameId,
				classPK, type, orderByComparator);

		if (contractEntry != null) {
			return contractEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractEntryException(msg.toString());
	}

	/**
	 * Returns the first contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contract entry, or <code>null</code> if a matching contract entry could not be found
	 */
	@Override
	public ContractEntry fetchByCN_CP_T_First(long classNameId, long classPK,
		int type, OrderByComparator<ContractEntry> orderByComparator) {
		List<ContractEntry> list = findByCN_CP_T(classNameId, classPK, type, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract entry
	 * @throws NoSuchContractEntryException if a matching contract entry could not be found
	 */
	@Override
	public ContractEntry findByCN_CP_T_Last(long classNameId, long classPK,
		int type, OrderByComparator<ContractEntry> orderByComparator)
		throws NoSuchContractEntryException {
		ContractEntry contractEntry = fetchByCN_CP_T_Last(classNameId, classPK,
				type, orderByComparator);

		if (contractEntry != null) {
			return contractEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchContractEntryException(msg.toString());
	}

	/**
	 * Returns the last contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contract entry, or <code>null</code> if a matching contract entry could not be found
	 */
	@Override
	public ContractEntry fetchByCN_CP_T_Last(long classNameId, long classPK,
		int type, OrderByComparator<ContractEntry> orderByComparator) {
		int count = countByCN_CP_T(classNameId, classPK, type);

		if (count == 0) {
			return null;
		}

		List<ContractEntry> list = findByCN_CP_T(classNameId, classPK, type,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contract entries before and after the current contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param contractEntryId the primary key of the current contract entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contract entry
	 * @throws NoSuchContractEntryException if a contract entry with the primary key could not be found
	 */
	@Override
	public ContractEntry[] findByCN_CP_T_PrevAndNext(long contractEntryId,
		long classNameId, long classPK, int type,
		OrderByComparator<ContractEntry> orderByComparator)
		throws NoSuchContractEntryException {
		ContractEntry contractEntry = findByPrimaryKey(contractEntryId);

		Session session = null;

		try {
			session = openSession();

			ContractEntry[] array = new ContractEntryImpl[3];

			array[0] = getByCN_CP_T_PrevAndNext(session, contractEntry,
					classNameId, classPK, type, orderByComparator, true);

			array[1] = contractEntry;

			array[2] = getByCN_CP_T_PrevAndNext(session, contractEntry,
					classNameId, classPK, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ContractEntry getByCN_CP_T_PrevAndNext(Session session,
		ContractEntry contractEntry, long classNameId, long classPK, int type,
		OrderByComparator<ContractEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_CONTRACTENTRY_WHERE);

		query.append(_FINDER_COLUMN_CN_CP_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_CN_CP_T_CLASSPK_2);

		query.append(_FINDER_COLUMN_CN_CP_T_TYPE_2);

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
			query.append(ContractEntryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(contractEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ContractEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 */
	@Override
	public void removeByCN_CP_T(long classNameId, long classPK, int type) {
		for (ContractEntry contractEntry : findByCN_CP_T(classNameId, classPK,
				type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(contractEntry);
		}
	}

	/**
	 * Returns the number of contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching contract entries
	 */
	@Override
	public int countByCN_CP_T(long classNameId, long classPK, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CN_CP_T;

		Object[] finderArgs = new Object[] { classNameId, classPK, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CONTRACTENTRY_WHERE);

			query.append(_FINDER_COLUMN_CN_CP_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CN_CP_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_CN_CP_T_TYPE_2);

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

	private static final String _FINDER_COLUMN_CN_CP_T_CLASSNAMEID_2 = "contractEntry.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_CN_CP_T_CLASSPK_2 = "contractEntry.classPK = ? AND ";
	private static final String _FINDER_COLUMN_CN_CP_T_TYPE_2 = "contractEntry.type = ?";

	public ContractEntryPersistenceImpl() {
		setModelClass(ContractEntry.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

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
	 * Caches the contract entry in the entity cache if it is enabled.
	 *
	 * @param contractEntry the contract entry
	 */
	@Override
	public void cacheResult(ContractEntry contractEntry) {
		entityCache.putResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryImpl.class, contractEntry.getPrimaryKey(),
			contractEntry);

		contractEntry.resetOriginalValues();
	}

	/**
	 * Caches the contract entries in the entity cache if it is enabled.
	 *
	 * @param contractEntries the contract entries
	 */
	@Override
	public void cacheResult(List<ContractEntry> contractEntries) {
		for (ContractEntry contractEntry : contractEntries) {
			if (entityCache.getResult(
						ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
						ContractEntryImpl.class, contractEntry.getPrimaryKey()) == null) {
				cacheResult(contractEntry);
			}
			else {
				contractEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contract entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContractEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the contract entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContractEntry contractEntry) {
		entityCache.removeResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryImpl.class, contractEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ContractEntry> contractEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContractEntry contractEntry : contractEntries) {
			entityCache.removeResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
				ContractEntryImpl.class, contractEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new contract entry with the primary key. Does not add the contract entry to the database.
	 *
	 * @param contractEntryId the primary key for the new contract entry
	 * @return the new contract entry
	 */
	@Override
	public ContractEntry create(long contractEntryId) {
		ContractEntry contractEntry = new ContractEntryImpl();

		contractEntry.setNew(true);
		contractEntry.setPrimaryKey(contractEntryId);

		return contractEntry;
	}

	/**
	 * Removes the contract entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contractEntryId the primary key of the contract entry
	 * @return the contract entry that was removed
	 * @throws NoSuchContractEntryException if a contract entry with the primary key could not be found
	 */
	@Override
	public ContractEntry remove(long contractEntryId)
		throws NoSuchContractEntryException {
		return remove((Serializable)contractEntryId);
	}

	/**
	 * Removes the contract entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the contract entry
	 * @return the contract entry that was removed
	 * @throws NoSuchContractEntryException if a contract entry with the primary key could not be found
	 */
	@Override
	public ContractEntry remove(Serializable primaryKey)
		throws NoSuchContractEntryException {
		Session session = null;

		try {
			session = openSession();

			ContractEntry contractEntry = (ContractEntry)session.get(ContractEntryImpl.class,
					primaryKey);

			if (contractEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContractEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contractEntry);
		}
		catch (NoSuchContractEntryException nsee) {
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
	protected ContractEntry removeImpl(ContractEntry contractEntry) {
		contractEntry = toUnwrappedModel(contractEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contractEntry)) {
				contractEntry = (ContractEntry)session.get(ContractEntryImpl.class,
						contractEntry.getPrimaryKeyObj());
			}

			if (contractEntry != null) {
				session.delete(contractEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (contractEntry != null) {
			clearCache(contractEntry);
		}

		return contractEntry;
	}

	@Override
	public ContractEntry updateImpl(ContractEntry contractEntry) {
		contractEntry = toUnwrappedModel(contractEntry);

		boolean isNew = contractEntry.isNew();

		ContractEntryModelImpl contractEntryModelImpl = (ContractEntryModelImpl)contractEntry;

		Session session = null;

		try {
			session = openSession();

			if (contractEntry.isNew()) {
				session.save(contractEntry);

				contractEntry.setNew(false);
			}
			else {
				contractEntry = (ContractEntry)session.merge(contractEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ContractEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					contractEntryModelImpl.getClassNameId(),
					contractEntryModelImpl.getClassPK(),
					contractEntryModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CN_CP_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CP_T,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((contractEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CP_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						contractEntryModelImpl.getOriginalClassNameId(),
						contractEntryModelImpl.getOriginalClassPK(),
						contractEntryModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CN_CP_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CP_T,
					args);

				args = new Object[] {
						contractEntryModelImpl.getClassNameId(),
						contractEntryModelImpl.getClassPK(),
						contractEntryModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CN_CP_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CP_T,
					args);
			}
		}

		entityCache.putResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
			ContractEntryImpl.class, contractEntry.getPrimaryKey(),
			contractEntry, false);

		contractEntry.resetOriginalValues();

		return contractEntry;
	}

	protected ContractEntry toUnwrappedModel(ContractEntry contractEntry) {
		if (contractEntry instanceof ContractEntryImpl) {
			return contractEntry;
		}

		ContractEntryImpl contractEntryImpl = new ContractEntryImpl();

		contractEntryImpl.setNew(contractEntry.isNew());
		contractEntryImpl.setPrimaryKey(contractEntry.getPrimaryKey());

		contractEntryImpl.setContractEntryId(contractEntry.getContractEntryId());
		contractEntryImpl.setUserId(contractEntry.getUserId());
		contractEntryImpl.setUserName(contractEntry.getUserName());
		contractEntryImpl.setCreateDate(contractEntry.getCreateDate());
		contractEntryImpl.setClassNameId(contractEntry.getClassNameId());
		contractEntryImpl.setClassPK(contractEntry.getClassPK());
		contractEntryImpl.setType(contractEntry.getType());
		contractEntryImpl.setVersion(contractEntry.getVersion());
		contractEntryImpl.setContent(contractEntry.getContent());

		return contractEntryImpl;
	}

	/**
	 * Returns the contract entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract entry
	 * @return the contract entry
	 * @throws NoSuchContractEntryException if a contract entry with the primary key could not be found
	 */
	@Override
	public ContractEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContractEntryException {
		ContractEntry contractEntry = fetchByPrimaryKey(primaryKey);

		if (contractEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContractEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return contractEntry;
	}

	/**
	 * Returns the contract entry with the primary key or throws a {@link NoSuchContractEntryException} if it could not be found.
	 *
	 * @param contractEntryId the primary key of the contract entry
	 * @return the contract entry
	 * @throws NoSuchContractEntryException if a contract entry with the primary key could not be found
	 */
	@Override
	public ContractEntry findByPrimaryKey(long contractEntryId)
		throws NoSuchContractEntryException {
		return findByPrimaryKey((Serializable)contractEntryId);
	}

	/**
	 * Returns the contract entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract entry
	 * @return the contract entry, or <code>null</code> if a contract entry with the primary key could not be found
	 */
	@Override
	public ContractEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
				ContractEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ContractEntry contractEntry = (ContractEntry)serializable;

		if (contractEntry == null) {
			Session session = null;

			try {
				session = openSession();

				contractEntry = (ContractEntry)session.get(ContractEntryImpl.class,
						primaryKey);

				if (contractEntry != null) {
					cacheResult(contractEntry);
				}
				else {
					entityCache.putResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
						ContractEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
					ContractEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return contractEntry;
	}

	/**
	 * Returns the contract entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contractEntryId the primary key of the contract entry
	 * @return the contract entry, or <code>null</code> if a contract entry with the primary key could not be found
	 */
	@Override
	public ContractEntry fetchByPrimaryKey(long contractEntryId) {
		return fetchByPrimaryKey((Serializable)contractEntryId);
	}

	@Override
	public Map<Serializable, ContractEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ContractEntry> map = new HashMap<Serializable, ContractEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ContractEntry contractEntry = fetchByPrimaryKey(primaryKey);

			if (contractEntry != null) {
				map.put(primaryKey, contractEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
					ContractEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ContractEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONTRACTENTRY_WHERE_PKS_IN);

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

			for (ContractEntry contractEntry : (List<ContractEntry>)q.list()) {
				map.put(contractEntry.getPrimaryKeyObj(), contractEntry);

				cacheResult(contractEntry);

				uncachedPrimaryKeys.remove(contractEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ContractEntryModelImpl.ENTITY_CACHE_ENABLED,
					ContractEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the contract entries.
	 *
	 * @return the contract entries
	 */
	@Override
	public List<ContractEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract entries
	 * @param end the upper bound of the range of contract entries (not inclusive)
	 * @return the range of contract entries
	 */
	@Override
	public List<ContractEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract entries
	 * @param end the upper bound of the range of contract entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contract entries
	 */
	@Override
	public List<ContractEntry> findAll(int start, int end,
		OrderByComparator<ContractEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contract entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract entries
	 * @param end the upper bound of the range of contract entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of contract entries
	 */
	@Override
	public List<ContractEntry> findAll(int start, int end,
		OrderByComparator<ContractEntry> orderByComparator,
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

		List<ContractEntry> list = null;

		if (retrieveFromCache) {
			list = (List<ContractEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONTRACTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTRACTENTRY;

				if (pagination) {
					sql = sql.concat(ContractEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ContractEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContractEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the contract entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContractEntry contractEntry : findAll()) {
			remove(contractEntry);
		}
	}

	/**
	 * Returns the number of contract entries.
	 *
	 * @return the number of contract entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTRACTENTRY);

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
		return ContractEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the contract entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ContractEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_CONTRACTENTRY = "SELECT contractEntry FROM ContractEntry contractEntry";
	private static final String _SQL_SELECT_CONTRACTENTRY_WHERE_PKS_IN = "SELECT contractEntry FROM ContractEntry contractEntry WHERE contractEntryId IN (";
	private static final String _SQL_SELECT_CONTRACTENTRY_WHERE = "SELECT contractEntry FROM ContractEntry contractEntry WHERE ";
	private static final String _SQL_COUNT_CONTRACTENTRY = "SELECT COUNT(contractEntry) FROM ContractEntry contractEntry";
	private static final String _SQL_COUNT_CONTRACTENTRY_WHERE = "SELECT COUNT(contractEntry) FROM ContractEntry contractEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contractEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContractEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContractEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ContractEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}