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

import com.liferay.osb.exception.NoSuchExternalIdMapperException;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.impl.ExternalIdMapperImpl;
import com.liferay.osb.model.impl.ExternalIdMapperModelImpl;
import com.liferay.osb.service.persistence.ExternalIdMapperPersistence;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

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
 * The persistence implementation for the external ID mapper service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalIdMapperPersistence
 * @see com.liferay.osb.service.persistence.ExternalIdMapperUtil
 * @generated
 */
@ProviderType
public class ExternalIdMapperPersistenceImpl extends BasePersistenceImpl<ExternalIdMapper>
	implements ExternalIdMapperPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExternalIdMapperUtil} to access the external ID mapper persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExternalIdMapperImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			ExternalIdMapperModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			ExternalIdMapperModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_C(long classNameId, long classPK) {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_C(long classNameId, long classPK,
		int start, int end) {
		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_C(long classNameId, long classPK,
		int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator) {
		return findByC_C(classNameId, classPK, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_C(long classNameId, long classPK,
		int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator,
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

		List<ExternalIdMapper> list = null;

		if (retrieveFromCache) {
			list = (List<ExternalIdMapper>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ExternalIdMapper externalIdMapper : list) {
					if ((classNameId != externalIdMapper.getClassNameId()) ||
							(classPK != externalIdMapper.getClassPK())) {
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

			query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ExternalIdMapperModelImpl.ORDER_BY_JPQL);
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
					list = (List<ExternalIdMapper>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ExternalIdMapper>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper findByC_C_First(long classNameId, long classPK,
		OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException {
		ExternalIdMapper externalIdMapper = fetchByC_C_First(classNameId,
				classPK, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator<ExternalIdMapper> orderByComparator) {
		List<ExternalIdMapper> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper findByC_C_Last(long classNameId, long classPK,
		OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException {
		ExternalIdMapper externalIdMapper = fetchByC_C_Last(classNameId,
				classPK, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator<ExternalIdMapper> orderByComparator) {
		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<ExternalIdMapper> list = findByC_C(classNameId, classPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param externalIdMapperId the primary key of the current external ID mapper
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next external ID mapper
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	@Override
	public ExternalIdMapper[] findByC_C_PrevAndNext(long externalIdMapperId,
		long classNameId, long classPK,
		OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException {
		ExternalIdMapper externalIdMapper = findByPrimaryKey(externalIdMapperId);

		Session session = null;

		try {
			session = openSession();

			ExternalIdMapper[] array = new ExternalIdMapperImpl[3];

			array[0] = getByC_C_PrevAndNext(session, externalIdMapper,
					classNameId, classPK, orderByComparator, true);

			array[1] = externalIdMapper;

			array[2] = getByC_C_PrevAndNext(session, externalIdMapper,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExternalIdMapper getByC_C_PrevAndNext(Session session,
		ExternalIdMapper externalIdMapper, long classNameId, long classPK,
		OrderByComparator<ExternalIdMapper> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

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
			query.append(ExternalIdMapperModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(externalIdMapper);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExternalIdMapper> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the external ID mappers where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (ExternalIdMapper externalIdMapper : findByC_C(classNameId,
				classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(externalIdMapper);
		}
	}

	/**
	 * Returns the number of external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching external ID mappers
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_EXTERNALIDMAPPER_WHERE);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "externalIdMapper.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "externalIdMapper.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			ExternalIdMapperModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			ExternalIdMapperModelImpl.CLASSPK_COLUMN_BITMASK |
			ExternalIdMapperModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_T = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_C_T(long classNameId, long classPK,
		int type) {
		return findByC_C_T(classNameId, classPK, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end) {
		return findByC_C_T(classNameId, classPK, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator) {
		return findByC_C_T(classNameId, classPK, type, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator,
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

		List<ExternalIdMapper> list = null;

		if (retrieveFromCache) {
			list = (List<ExternalIdMapper>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ExternalIdMapper externalIdMapper : list) {
					if ((classNameId != externalIdMapper.getClassNameId()) ||
							(classPK != externalIdMapper.getClassPK()) ||
							(type != externalIdMapper.getType())) {
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

			query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ExternalIdMapperModelImpl.ORDER_BY_JPQL);
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
					list = (List<ExternalIdMapper>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ExternalIdMapper>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper findByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException {
		ExternalIdMapper externalIdMapper = fetchByC_C_T_First(classNameId,
				classPK, type, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
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

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper fetchByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator<ExternalIdMapper> orderByComparator) {
		List<ExternalIdMapper> list = findByC_C_T(classNameId, classPK, type,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper findByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException {
		ExternalIdMapper externalIdMapper = fetchByC_C_T_Last(classNameId,
				classPK, type, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
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

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper fetchByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator<ExternalIdMapper> orderByComparator) {
		int count = countByC_C_T(classNameId, classPK, type);

		if (count == 0) {
			return null;
		}

		List<ExternalIdMapper> list = findByC_C_T(classNameId, classPK, type,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param externalIdMapperId the primary key of the current external ID mapper
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next external ID mapper
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	@Override
	public ExternalIdMapper[] findByC_C_T_PrevAndNext(long externalIdMapperId,
		long classNameId, long classPK, int type,
		OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException {
		ExternalIdMapper externalIdMapper = findByPrimaryKey(externalIdMapperId);

		Session session = null;

		try {
			session = openSession();

			ExternalIdMapper[] array = new ExternalIdMapperImpl[3];

			array[0] = getByC_C_T_PrevAndNext(session, externalIdMapper,
					classNameId, classPK, type, orderByComparator, true);

			array[1] = externalIdMapper;

			array[2] = getByC_C_T_PrevAndNext(session, externalIdMapper,
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

	protected ExternalIdMapper getByC_C_T_PrevAndNext(Session session,
		ExternalIdMapper externalIdMapper, long classNameId, long classPK,
		int type, OrderByComparator<ExternalIdMapper> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

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
			query.append(ExternalIdMapperModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(externalIdMapper);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExternalIdMapper> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 */
	@Override
	public void removeByC_C_T(long classNameId, long classPK, int type) {
		for (ExternalIdMapper externalIdMapper : findByC_C_T(classNameId,
				classPK, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(externalIdMapper);
		}
	}

	/**
	 * Returns the number of external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching external ID mappers
	 */
	@Override
	public int countByC_C_T(long classNameId, long classPK, int type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_T;

		Object[] finderArgs = new Object[] { classNameId, classPK, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_EXTERNALIDMAPPER_WHERE);

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

	private static final String _FINDER_COLUMN_C_C_T_CLASSNAMEID_2 = "externalIdMapper.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_CLASSPK_2 = "externalIdMapper.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TYPE_2 = "externalIdMapper.type = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_T_EI = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_T_EI",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T_EI =
		new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED,
			ExternalIdMapperImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_T_EI",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName()
			},
			ExternalIdMapperModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			ExternalIdMapperModelImpl.TYPE_COLUMN_BITMASK |
			ExternalIdMapperModelImpl.EXTERNALID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_T_EI = new FinderPath(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T_EI",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @return the matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_T_EI(long classNameId, int type,
		String externalId) {
		return findByC_T_EI(classNameId, type, externalId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_T_EI(long classNameId, int type,
		String externalId, int start, int end) {
		return findByC_T_EI(classNameId, type, externalId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_T_EI(long classNameId, int type,
		String externalId, int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator) {
		return findByC_T_EI(classNameId, type, externalId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findByC_T_EI(long classNameId, int type,
		String externalId, int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T_EI;
			finderArgs = new Object[] { classNameId, type, externalId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_T_EI;
			finderArgs = new Object[] {
					classNameId, type, externalId,
					
					start, end, orderByComparator
				};
		}

		List<ExternalIdMapper> list = null;

		if (retrieveFromCache) {
			list = (List<ExternalIdMapper>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ExternalIdMapper externalIdMapper : list) {
					if ((classNameId != externalIdMapper.getClassNameId()) ||
							(type != externalIdMapper.getType()) ||
							!Objects.equals(externalId,
								externalIdMapper.getExternalId())) {
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

			query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

			query.append(_FINDER_COLUMN_C_T_EI_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_T_EI_TYPE_2);

			boolean bindExternalId = false;

			if (externalId == null) {
				query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_1);
			}
			else if (externalId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_3);
			}
			else {
				bindExternalId = true;

				query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ExternalIdMapperModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(type);

				if (bindExternalId) {
					qPos.add(externalId);
				}

				if (!pagination) {
					list = (List<ExternalIdMapper>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ExternalIdMapper>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper findByC_T_EI_First(long classNameId, int type,
		String externalId, OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException {
		ExternalIdMapper externalIdMapper = fetchByC_T_EI_First(classNameId,
				type, externalId, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", externalId=");
		msg.append(externalId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper fetchByC_T_EI_First(long classNameId, int type,
		String externalId, OrderByComparator<ExternalIdMapper> orderByComparator) {
		List<ExternalIdMapper> list = findByC_T_EI(classNameId, type,
				externalId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper findByC_T_EI_Last(long classNameId, int type,
		String externalId, OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException {
		ExternalIdMapper externalIdMapper = fetchByC_T_EI_Last(classNameId,
				type, externalId, orderByComparator);

		if (externalIdMapper != null) {
			return externalIdMapper;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", type=");
		msg.append(type);

		msg.append(", externalId=");
		msg.append(externalId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchExternalIdMapperException(msg.toString());
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	@Override
	public ExternalIdMapper fetchByC_T_EI_Last(long classNameId, int type,
		String externalId, OrderByComparator<ExternalIdMapper> orderByComparator) {
		int count = countByC_T_EI(classNameId, type, externalId);

		if (count == 0) {
			return null;
		}

		List<ExternalIdMapper> list = findByC_T_EI(classNameId, type,
				externalId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param externalIdMapperId the primary key of the current external ID mapper
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next external ID mapper
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	@Override
	public ExternalIdMapper[] findByC_T_EI_PrevAndNext(
		long externalIdMapperId, long classNameId, int type, String externalId,
		OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException {
		ExternalIdMapper externalIdMapper = findByPrimaryKey(externalIdMapperId);

		Session session = null;

		try {
			session = openSession();

			ExternalIdMapper[] array = new ExternalIdMapperImpl[3];

			array[0] = getByC_T_EI_PrevAndNext(session, externalIdMapper,
					classNameId, type, externalId, orderByComparator, true);

			array[1] = externalIdMapper;

			array[2] = getByC_T_EI_PrevAndNext(session, externalIdMapper,
					classNameId, type, externalId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ExternalIdMapper getByC_T_EI_PrevAndNext(Session session,
		ExternalIdMapper externalIdMapper, long classNameId, int type,
		String externalId,
		OrderByComparator<ExternalIdMapper> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE);

		query.append(_FINDER_COLUMN_C_T_EI_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_T_EI_TYPE_2);

		boolean bindExternalId = false;

		if (externalId == null) {
			query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_1);
		}
		else if (externalId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_3);
		}
		else {
			bindExternalId = true;

			query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_2);
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
			query.append(ExternalIdMapperModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(type);

		if (bindExternalId) {
			qPos.add(externalId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(externalIdMapper);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ExternalIdMapper> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 */
	@Override
	public void removeByC_T_EI(long classNameId, int type, String externalId) {
		for (ExternalIdMapper externalIdMapper : findByC_T_EI(classNameId,
				type, externalId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(externalIdMapper);
		}
	}

	/**
	 * Returns the number of external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @return the number of matching external ID mappers
	 */
	@Override
	public int countByC_T_EI(long classNameId, int type, String externalId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_T_EI;

		Object[] finderArgs = new Object[] { classNameId, type, externalId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_EXTERNALIDMAPPER_WHERE);

			query.append(_FINDER_COLUMN_C_T_EI_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_T_EI_TYPE_2);

			boolean bindExternalId = false;

			if (externalId == null) {
				query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_1);
			}
			else if (externalId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_3);
			}
			else {
				bindExternalId = true;

				query.append(_FINDER_COLUMN_C_T_EI_EXTERNALID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(type);

				if (bindExternalId) {
					qPos.add(externalId);
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

	private static final String _FINDER_COLUMN_C_T_EI_CLASSNAMEID_2 = "externalIdMapper.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_T_EI_TYPE_2 = "externalIdMapper.type = ? AND ";
	private static final String _FINDER_COLUMN_C_T_EI_EXTERNALID_1 = "externalIdMapper.externalId IS NULL";
	private static final String _FINDER_COLUMN_C_T_EI_EXTERNALID_2 = "externalIdMapper.externalId = ?";
	private static final String _FINDER_COLUMN_C_T_EI_EXTERNALID_3 = "(externalIdMapper.externalId IS NULL OR externalIdMapper.externalId = '')";

	public ExternalIdMapperPersistenceImpl() {
		setModelClass(ExternalIdMapper.class);
	}

	/**
	 * Caches the external ID mapper in the entity cache if it is enabled.
	 *
	 * @param externalIdMapper the external ID mapper
	 */
	@Override
	public void cacheResult(ExternalIdMapper externalIdMapper) {
		entityCache.putResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperImpl.class, externalIdMapper.getPrimaryKey(),
			externalIdMapper);

		externalIdMapper.resetOriginalValues();
	}

	/**
	 * Caches the external ID mappers in the entity cache if it is enabled.
	 *
	 * @param externalIdMappers the external ID mappers
	 */
	@Override
	public void cacheResult(List<ExternalIdMapper> externalIdMappers) {
		for (ExternalIdMapper externalIdMapper : externalIdMappers) {
			if (entityCache.getResult(
						ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
						ExternalIdMapperImpl.class,
						externalIdMapper.getPrimaryKey()) == null) {
				cacheResult(externalIdMapper);
			}
			else {
				externalIdMapper.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all external ID mappers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ExternalIdMapperImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the external ID mapper.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ExternalIdMapper externalIdMapper) {
		entityCache.removeResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperImpl.class, externalIdMapper.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ExternalIdMapper> externalIdMappers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ExternalIdMapper externalIdMapper : externalIdMappers) {
			entityCache.removeResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
				ExternalIdMapperImpl.class, externalIdMapper.getPrimaryKey());
		}
	}

	/**
	 * Creates a new external ID mapper with the primary key. Does not add the external ID mapper to the database.
	 *
	 * @param externalIdMapperId the primary key for the new external ID mapper
	 * @return the new external ID mapper
	 */
	@Override
	public ExternalIdMapper create(long externalIdMapperId) {
		ExternalIdMapper externalIdMapper = new ExternalIdMapperImpl();

		externalIdMapper.setNew(true);
		externalIdMapper.setPrimaryKey(externalIdMapperId);

		return externalIdMapper;
	}

	/**
	 * Removes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper that was removed
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	@Override
	public ExternalIdMapper remove(long externalIdMapperId)
		throws NoSuchExternalIdMapperException {
		return remove((Serializable)externalIdMapperId);
	}

	/**
	 * Removes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the external ID mapper
	 * @return the external ID mapper that was removed
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	@Override
	public ExternalIdMapper remove(Serializable primaryKey)
		throws NoSuchExternalIdMapperException {
		Session session = null;

		try {
			session = openSession();

			ExternalIdMapper externalIdMapper = (ExternalIdMapper)session.get(ExternalIdMapperImpl.class,
					primaryKey);

			if (externalIdMapper == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchExternalIdMapperException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(externalIdMapper);
		}
		catch (NoSuchExternalIdMapperException nsee) {
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
	protected ExternalIdMapper removeImpl(ExternalIdMapper externalIdMapper) {
		externalIdMapper = toUnwrappedModel(externalIdMapper);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(externalIdMapper)) {
				externalIdMapper = (ExternalIdMapper)session.get(ExternalIdMapperImpl.class,
						externalIdMapper.getPrimaryKeyObj());
			}

			if (externalIdMapper != null) {
				session.delete(externalIdMapper);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (externalIdMapper != null) {
			clearCache(externalIdMapper);
		}

		return externalIdMapper;
	}

	@Override
	public ExternalIdMapper updateImpl(ExternalIdMapper externalIdMapper) {
		externalIdMapper = toUnwrappedModel(externalIdMapper);

		boolean isNew = externalIdMapper.isNew();

		ExternalIdMapperModelImpl externalIdMapperModelImpl = (ExternalIdMapperModelImpl)externalIdMapper;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (externalIdMapper.getCreateDate() == null)) {
			if (serviceContext == null) {
				externalIdMapper.setCreateDate(now);
			}
			else {
				externalIdMapper.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!externalIdMapperModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				externalIdMapper.setModifiedDate(now);
			}
			else {
				externalIdMapper.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (externalIdMapper.isNew()) {
				session.save(externalIdMapper);

				externalIdMapper.setNew(false);
			}
			else {
				externalIdMapper = (ExternalIdMapper)session.merge(externalIdMapper);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ExternalIdMapperModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					externalIdMapperModelImpl.getClassNameId(),
					externalIdMapperModelImpl.getClassPK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
				args);

			args = new Object[] {
					externalIdMapperModelImpl.getClassNameId(),
					externalIdMapperModelImpl.getClassPK(),
					externalIdMapperModelImpl.getType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
				args);

			args = new Object[] {
					externalIdMapperModelImpl.getClassNameId(),
					externalIdMapperModelImpl.getType(),
					externalIdMapperModelImpl.getExternalId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_T_EI, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T_EI,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((externalIdMapperModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						externalIdMapperModelImpl.getOriginalClassNameId(),
						externalIdMapperModelImpl.getOriginalClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						externalIdMapperModelImpl.getClassNameId(),
						externalIdMapperModelImpl.getClassPK()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((externalIdMapperModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						externalIdMapperModelImpl.getOriginalClassNameId(),
						externalIdMapperModelImpl.getOriginalClassPK(),
						externalIdMapperModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);

				args = new Object[] {
						externalIdMapperModelImpl.getClassNameId(),
						externalIdMapperModelImpl.getClassPK(),
						externalIdMapperModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);
			}

			if ((externalIdMapperModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T_EI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						externalIdMapperModelImpl.getOriginalClassNameId(),
						externalIdMapperModelImpl.getOriginalType(),
						externalIdMapperModelImpl.getOriginalExternalId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_T_EI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T_EI,
					args);

				args = new Object[] {
						externalIdMapperModelImpl.getClassNameId(),
						externalIdMapperModelImpl.getType(),
						externalIdMapperModelImpl.getExternalId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_T_EI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T_EI,
					args);
			}
		}

		entityCache.putResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
			ExternalIdMapperImpl.class, externalIdMapper.getPrimaryKey(),
			externalIdMapper, false);

		externalIdMapper.resetOriginalValues();

		return externalIdMapper;
	}

	protected ExternalIdMapper toUnwrappedModel(
		ExternalIdMapper externalIdMapper) {
		if (externalIdMapper instanceof ExternalIdMapperImpl) {
			return externalIdMapper;
		}

		ExternalIdMapperImpl externalIdMapperImpl = new ExternalIdMapperImpl();

		externalIdMapperImpl.setNew(externalIdMapper.isNew());
		externalIdMapperImpl.setPrimaryKey(externalIdMapper.getPrimaryKey());

		externalIdMapperImpl.setExternalIdMapperId(externalIdMapper.getExternalIdMapperId());
		externalIdMapperImpl.setCreateDate(externalIdMapper.getCreateDate());
		externalIdMapperImpl.setModifiedDate(externalIdMapper.getModifiedDate());
		externalIdMapperImpl.setClassNameId(externalIdMapper.getClassNameId());
		externalIdMapperImpl.setClassPK(externalIdMapper.getClassPK());
		externalIdMapperImpl.setType(externalIdMapper.getType());
		externalIdMapperImpl.setExternalId(externalIdMapper.getExternalId());

		return externalIdMapperImpl;
	}

	/**
	 * Returns the external ID mapper with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the external ID mapper
	 * @return the external ID mapper
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	@Override
	public ExternalIdMapper findByPrimaryKey(Serializable primaryKey)
		throws NoSuchExternalIdMapperException {
		ExternalIdMapper externalIdMapper = fetchByPrimaryKey(primaryKey);

		if (externalIdMapper == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchExternalIdMapperException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return externalIdMapper;
	}

	/**
	 * Returns the external ID mapper with the primary key or throws a {@link NoSuchExternalIdMapperException} if it could not be found.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	@Override
	public ExternalIdMapper findByPrimaryKey(long externalIdMapperId)
		throws NoSuchExternalIdMapperException {
		return findByPrimaryKey((Serializable)externalIdMapperId);
	}

	/**
	 * Returns the external ID mapper with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the external ID mapper
	 * @return the external ID mapper, or <code>null</code> if a external ID mapper with the primary key could not be found
	 */
	@Override
	public ExternalIdMapper fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
				ExternalIdMapperImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ExternalIdMapper externalIdMapper = (ExternalIdMapper)serializable;

		if (externalIdMapper == null) {
			Session session = null;

			try {
				session = openSession();

				externalIdMapper = (ExternalIdMapper)session.get(ExternalIdMapperImpl.class,
						primaryKey);

				if (externalIdMapper != null) {
					cacheResult(externalIdMapper);
				}
				else {
					entityCache.putResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
						ExternalIdMapperImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
					ExternalIdMapperImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return externalIdMapper;
	}

	/**
	 * Returns the external ID mapper with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper, or <code>null</code> if a external ID mapper with the primary key could not be found
	 */
	@Override
	public ExternalIdMapper fetchByPrimaryKey(long externalIdMapperId) {
		return fetchByPrimaryKey((Serializable)externalIdMapperId);
	}

	@Override
	public Map<Serializable, ExternalIdMapper> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ExternalIdMapper> map = new HashMap<Serializable, ExternalIdMapper>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ExternalIdMapper externalIdMapper = fetchByPrimaryKey(primaryKey);

			if (externalIdMapper != null) {
				map.put(primaryKey, externalIdMapper);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
					ExternalIdMapperImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ExternalIdMapper)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_EXTERNALIDMAPPER_WHERE_PKS_IN);

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

			for (ExternalIdMapper externalIdMapper : (List<ExternalIdMapper>)q.list()) {
				map.put(externalIdMapper.getPrimaryKeyObj(), externalIdMapper);

				cacheResult(externalIdMapper);

				uncachedPrimaryKeys.remove(externalIdMapper.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ExternalIdMapperModelImpl.ENTITY_CACHE_ENABLED,
					ExternalIdMapperImpl.class, primaryKey, nullModel);
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
	 * Returns all the external ID mappers.
	 *
	 * @return the external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the external ID mappers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the external ID mappers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findAll(int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the external ID mappers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of external ID mappers
	 */
	@Override
	public List<ExternalIdMapper> findAll(int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator,
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

		List<ExternalIdMapper> list = null;

		if (retrieveFromCache) {
			list = (List<ExternalIdMapper>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EXTERNALIDMAPPER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXTERNALIDMAPPER;

				if (pagination) {
					sql = sql.concat(ExternalIdMapperModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ExternalIdMapper>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ExternalIdMapper>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the external ID mappers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ExternalIdMapper externalIdMapper : findAll()) {
			remove(externalIdMapper);
		}
	}

	/**
	 * Returns the number of external ID mappers.
	 *
	 * @return the number of external ID mappers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EXTERNALIDMAPPER);

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
		return ExternalIdMapperModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the external ID mapper persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ExternalIdMapperImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_EXTERNALIDMAPPER = "SELECT externalIdMapper FROM ExternalIdMapper externalIdMapper";
	private static final String _SQL_SELECT_EXTERNALIDMAPPER_WHERE_PKS_IN = "SELECT externalIdMapper FROM ExternalIdMapper externalIdMapper WHERE externalIdMapperId IN (";
	private static final String _SQL_SELECT_EXTERNALIDMAPPER_WHERE = "SELECT externalIdMapper FROM ExternalIdMapper externalIdMapper WHERE ";
	private static final String _SQL_COUNT_EXTERNALIDMAPPER = "SELECT COUNT(externalIdMapper) FROM ExternalIdMapper externalIdMapper";
	private static final String _SQL_COUNT_EXTERNALIDMAPPER_WHERE = "SELECT COUNT(externalIdMapper) FROM ExternalIdMapper externalIdMapper WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "externalIdMapper.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ExternalIdMapper exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ExternalIdMapper exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ExternalIdMapperPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}