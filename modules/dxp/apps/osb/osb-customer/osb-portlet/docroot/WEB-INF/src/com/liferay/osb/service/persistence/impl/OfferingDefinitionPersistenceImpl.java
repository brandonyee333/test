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

import com.liferay.osb.exception.NoSuchOfferingDefinitionException;
import com.liferay.osb.model.OfferingDefinition;
import com.liferay.osb.model.impl.OfferingDefinitionImpl;
import com.liferay.osb.model.impl.OfferingDefinitionModelImpl;
import com.liferay.osb.service.persistence.OfferingBundlePersistence;
import com.liferay.osb.service.persistence.OfferingDefinitionPersistence;

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

import java.util.Arrays;
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
 * The persistence implementation for the offering definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinitionPersistence
 * @see com.liferay.osb.service.persistence.OfferingDefinitionUtil
 * @generated
 */
@ProviderType
public class OfferingDefinitionPersistenceImpl extends BasePersistenceImpl<OfferingDefinition>
	implements OfferingDefinitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OfferingDefinitionUtil} to access the offering definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OfferingDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRODUCTENTRYID =
		new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID =
		new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProductEntryId",
			new String[] { Long.class.getName() },
			OfferingDefinitionModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.SUPPORTRESPONSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRODUCTENTRYID = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the offering definitions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByProductEntryId(long productEntryId) {
		return findByProductEntryId(productEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering definitions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByProductEntryId(long productEntryId,
		int start, int end) {
		return findByProductEntryId(productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering definitions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByProductEntryId(long productEntryId,
		int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		return findByProductEntryId(productEntryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offering definitions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByProductEntryId(long productEntryId,
		int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID;
			finderArgs = new Object[] { productEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRODUCTENTRYID;
			finderArgs = new Object[] {
					productEntryId,
					
					start, end, orderByComparator
				};
		}

		List<OfferingDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<OfferingDefinition>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfferingDefinition offeringDefinition : list) {
					if ((productEntryId != offeringDefinition.getProductEntryId())) {
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

			query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				if (!pagination) {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
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
	 * Returns the first offering definition in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition
	 * @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition findByProductEntryId_First(long productEntryId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = fetchByProductEntryId_First(productEntryId,
				orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the first offering definition in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition fetchByProductEntryId_First(long productEntryId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		List<OfferingDefinition> list = findByProductEntryId(productEntryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering definition in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition
	 * @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition findByProductEntryId_Last(long productEntryId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = fetchByProductEntryId_Last(productEntryId,
				orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the last offering definition in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition fetchByProductEntryId_Last(long productEntryId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		int count = countByProductEntryId(productEntryId);

		if (count == 0) {
			return null;
		}

		List<OfferingDefinition> list = findByProductEntryId(productEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering definitions before and after the current offering definition in the ordered set where productEntryId = &#63;.
	 *
	 * @param offeringDefinitionId the primary key of the current offering definition
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering definition
	 * @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 */
	@Override
	public OfferingDefinition[] findByProductEntryId_PrevAndNext(
		long offeringDefinitionId, long productEntryId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = findByPrimaryKey(offeringDefinitionId);

		Session session = null;

		try {
			session = openSession();

			OfferingDefinition[] array = new OfferingDefinitionImpl[3];

			array[0] = getByProductEntryId_PrevAndNext(session,
					offeringDefinition, productEntryId, orderByComparator, true);

			array[1] = offeringDefinition;

			array[2] = getByProductEntryId_PrevAndNext(session,
					offeringDefinition, productEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfferingDefinition getByProductEntryId_PrevAndNext(
		Session session, OfferingDefinition offeringDefinition,
		long productEntryId,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

		query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

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
			query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(productEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the offering definitions where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 */
	@Override
	public void removeByProductEntryId(long productEntryId) {
		for (OfferingDefinition offeringDefinition : findByProductEntryId(
				productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(offeringDefinition);
		}
	}

	/**
	 * Returns the number of offering definitions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching offering definitions
	 */
	@Override
	public int countByProductEntryId(long productEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRODUCTENTRYID;

		Object[] finderArgs = new Object[] { productEntryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

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

	private static final String _FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2 = "offeringDefinition.productEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTRESPONSEID =
		new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySupportResponseId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTRESPONSEID =
		new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySupportResponseId", new String[] { Long.class.getName() },
			OfferingDefinitionModelImpl.SUPPORTRESPONSEID_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.PRODUCTENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SUPPORTRESPONSEID = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySupportResponseId", new String[] { Long.class.getName() });

	/**
	 * Returns all the offering definitions where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @return the matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId) {
		return findBySupportResponseId(supportResponseId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering definitions where supportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportResponseId the support response ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end) {
		return findBySupportResponseId(supportResponseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering definitions where supportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportResponseId the support response ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		return findBySupportResponseId(supportResponseId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offering definitions where supportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param supportResponseId the support response ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTRESPONSEID;
			finderArgs = new Object[] { supportResponseId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUPPORTRESPONSEID;
			finderArgs = new Object[] {
					supportResponseId,
					
					start, end, orderByComparator
				};
		}

		List<OfferingDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<OfferingDefinition>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfferingDefinition offeringDefinition : list) {
					if ((supportResponseId != offeringDefinition.getSupportResponseId())) {
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

			query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTRESPONSEID_SUPPORTRESPONSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportResponseId);

				if (!pagination) {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
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
	 * Returns the first offering definition in the ordered set where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition
	 * @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition findBySupportResponseId_First(
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = fetchBySupportResponseId_First(supportResponseId,
				orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportResponseId=");
		msg.append(supportResponseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the first offering definition in the ordered set where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition fetchBySupportResponseId_First(
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		List<OfferingDefinition> list = findBySupportResponseId(supportResponseId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering definition in the ordered set where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition
	 * @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition findBySupportResponseId_Last(
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = fetchBySupportResponseId_Last(supportResponseId,
				orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("supportResponseId=");
		msg.append(supportResponseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the last offering definition in the ordered set where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition fetchBySupportResponseId_Last(
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		int count = countBySupportResponseId(supportResponseId);

		if (count == 0) {
			return null;
		}

		List<OfferingDefinition> list = findBySupportResponseId(supportResponseId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering definitions before and after the current offering definition in the ordered set where supportResponseId = &#63;.
	 *
	 * @param offeringDefinitionId the primary key of the current offering definition
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering definition
	 * @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 */
	@Override
	public OfferingDefinition[] findBySupportResponseId_PrevAndNext(
		long offeringDefinitionId, long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = findByPrimaryKey(offeringDefinitionId);

		Session session = null;

		try {
			session = openSession();

			OfferingDefinition[] array = new OfferingDefinitionImpl[3];

			array[0] = getBySupportResponseId_PrevAndNext(session,
					offeringDefinition, supportResponseId, orderByComparator,
					true);

			array[1] = offeringDefinition;

			array[2] = getBySupportResponseId_PrevAndNext(session,
					offeringDefinition, supportResponseId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfferingDefinition getBySupportResponseId_PrevAndNext(
		Session session, OfferingDefinition offeringDefinition,
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

		query.append(_FINDER_COLUMN_SUPPORTRESPONSEID_SUPPORTRESPONSEID_2);

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
			query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(supportResponseId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the offering definitions where supportResponseId = &#63; from the database.
	 *
	 * @param supportResponseId the support response ID
	 */
	@Override
	public void removeBySupportResponseId(long supportResponseId) {
		for (OfferingDefinition offeringDefinition : findBySupportResponseId(
				supportResponseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(offeringDefinition);
		}
	}

	/**
	 * Returns the number of offering definitions where supportResponseId = &#63;.
	 *
	 * @param supportResponseId the support response ID
	 * @return the number of matching offering definitions
	 */
	@Override
	public int countBySupportResponseId(long supportResponseId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SUPPORTRESPONSEID;

		Object[] finderArgs = new Object[] { supportResponseId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_SUPPORTRESPONSEID_SUPPORTRESPONSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(supportResponseId);

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

	private static final String _FINDER_COLUMN_SUPPORTRESPONSEID_SUPPORTRESPONSEID_2 =
		"offeringDefinition.supportResponseId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_SRI = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPEI_SRI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_SRI =
		new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPEI_SRI",
			new String[] { Long.class.getName(), Long.class.getName() },
			OfferingDefinitionModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.SUPPORTRESPONSEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PEI_SRI = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPEI_SRI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_SRI = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByPEI_SRI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @return the matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByPEI_SRI(long productEntryId,
		long supportResponseId) {
		return findByPEI_SRI(productEntryId, supportResponseId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByPEI_SRI(long productEntryId,
		long supportResponseId, int start, int end) {
		return findByPEI_SRI(productEntryId, supportResponseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByPEI_SRI(long productEntryId,
		long supportResponseId, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		return findByPEI_SRI(productEntryId, supportResponseId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByPEI_SRI(long productEntryId,
		long supportResponseId, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_SRI;
			finderArgs = new Object[] { productEntryId, supportResponseId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_SRI;
			finderArgs = new Object[] {
					productEntryId, supportResponseId,
					
					start, end, orderByComparator
				};
		}

		List<OfferingDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<OfferingDefinition>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfferingDefinition offeringDefinition : list) {
					if ((productEntryId != offeringDefinition.getProductEntryId()) ||
							(supportResponseId != offeringDefinition.getSupportResponseId())) {
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

			query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(supportResponseId);

				if (!pagination) {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
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
	 * Returns the first offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition
	 * @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition findByPEI_SRI_First(long productEntryId,
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = fetchByPEI_SRI_First(productEntryId,
				supportResponseId, orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(", supportResponseId=");
		msg.append(supportResponseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the first offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition fetchByPEI_SRI_First(long productEntryId,
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		List<OfferingDefinition> list = findByPEI_SRI(productEntryId,
				supportResponseId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition
	 * @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition findByPEI_SRI_Last(long productEntryId,
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = fetchByPEI_SRI_Last(productEntryId,
				supportResponseId, orderByComparator);

		if (offeringDefinition != null) {
			return offeringDefinition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("productEntryId=");
		msg.append(productEntryId);

		msg.append(", supportResponseId=");
		msg.append(supportResponseId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOfferingDefinitionException(msg.toString());
	}

	/**
	 * Returns the last offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition fetchByPEI_SRI_Last(long productEntryId,
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		int count = countByPEI_SRI(productEntryId, supportResponseId);

		if (count == 0) {
			return null;
		}

		List<OfferingDefinition> list = findByPEI_SRI(productEntryId,
				supportResponseId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offering definitions before and after the current offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param offeringDefinitionId the primary key of the current offering definition
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offering definition
	 * @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 */
	@Override
	public OfferingDefinition[] findByPEI_SRI_PrevAndNext(
		long offeringDefinitionId, long productEntryId, long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = findByPrimaryKey(offeringDefinitionId);

		Session session = null;

		try {
			session = openSession();

			OfferingDefinition[] array = new OfferingDefinitionImpl[3];

			array[0] = getByPEI_SRI_PrevAndNext(session, offeringDefinition,
					productEntryId, supportResponseId, orderByComparator, true);

			array[1] = offeringDefinition;

			array[2] = getByPEI_SRI_PrevAndNext(session, offeringDefinition,
					productEntryId, supportResponseId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OfferingDefinition getByPEI_SRI_PrevAndNext(Session session,
		OfferingDefinition offeringDefinition, long productEntryId,
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

		query.append(_FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_2);

		query.append(_FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_2);

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
			query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(productEntryId);

		qPos.add(supportResponseId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(offeringDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OfferingDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryIds the product entry IDs
	 * @param supportResponseIds the support response IDs
	 * @return the matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByPEI_SRI(long[] productEntryIds,
		long[] supportResponseIds) {
		return findByPEI_SRI(productEntryIds, supportResponseIds,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryIds the product entry IDs
	 * @param supportResponseIds the support response IDs
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByPEI_SRI(long[] productEntryIds,
		long[] supportResponseIds, int start, int end) {
		return findByPEI_SRI(productEntryIds, supportResponseIds, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryIds the product entry IDs
	 * @param supportResponseIds the support response IDs
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByPEI_SRI(long[] productEntryIds,
		long[] supportResponseIds, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		return findByPEI_SRI(productEntryIds, supportResponseIds, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching offering definitions
	 */
	@Override
	public List<OfferingDefinition> findByPEI_SRI(long[] productEntryIds,
		long[] supportResponseIds, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache) {
		if (productEntryIds == null) {
			productEntryIds = new long[0];
		}
		else if (productEntryIds.length > 1) {
			productEntryIds = ArrayUtil.unique(productEntryIds);

			Arrays.sort(productEntryIds);
		}

		if (supportResponseIds == null) {
			supportResponseIds = new long[0];
		}
		else if (supportResponseIds.length > 1) {
			supportResponseIds = ArrayUtil.unique(supportResponseIds);

			Arrays.sort(supportResponseIds);
		}

		if ((productEntryIds.length == 1) && (supportResponseIds.length == 1)) {
			return findByPEI_SRI(productEntryIds[0], supportResponseIds[0],
				start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(productEntryIds),
					StringUtil.merge(supportResponseIds)
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(productEntryIds),
					StringUtil.merge(supportResponseIds),
					
					start, end, orderByComparator
				};
		}

		List<OfferingDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<OfferingDefinition>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_SRI,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OfferingDefinition offeringDefinition : list) {
					if (!ArrayUtil.contains(productEntryIds,
								offeringDefinition.getProductEntryId()) ||
							!ArrayUtil.contains(supportResponseIds,
								offeringDefinition.getSupportResponseId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

			if (productEntryIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_7);

				query.append(StringUtil.merge(productEntryIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (supportResponseIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_7);

				query.append(StringUtil.merge(supportResponseIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_SRI,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_PEI_SRI,
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
	 * Removes all the offering definitions where productEntryId = &#63; and supportResponseId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 */
	@Override
	public void removeByPEI_SRI(long productEntryId, long supportResponseId) {
		for (OfferingDefinition offeringDefinition : findByPEI_SRI(
				productEntryId, supportResponseId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(offeringDefinition);
		}
	}

	/**
	 * Returns the number of offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @return the number of matching offering definitions
	 */
	@Override
	public int countByPEI_SRI(long productEntryId, long supportResponseId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PEI_SRI;

		Object[] finderArgs = new Object[] { productEntryId, supportResponseId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(supportResponseId);

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
	 * Returns the number of offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	 *
	 * @param productEntryIds the product entry IDs
	 * @param supportResponseIds the support response IDs
	 * @return the number of matching offering definitions
	 */
	@Override
	public int countByPEI_SRI(long[] productEntryIds, long[] supportResponseIds) {
		if (productEntryIds == null) {
			productEntryIds = new long[0];
		}
		else if (productEntryIds.length > 1) {
			productEntryIds = ArrayUtil.unique(productEntryIds);

			Arrays.sort(productEntryIds);
		}

		if (supportResponseIds == null) {
			supportResponseIds = new long[0];
		}
		else if (supportResponseIds.length > 1) {
			supportResponseIds = ArrayUtil.unique(supportResponseIds);

			Arrays.sort(supportResponseIds);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(productEntryIds),
				StringUtil.merge(supportResponseIds)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_SRI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_OFFERINGDEFINITION_WHERE);

			if (productEntryIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_7);

				query.append(StringUtil.merge(productEntryIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			if (supportResponseIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_7);

				query.append(StringUtil.merge(supportResponseIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_SRI,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_PEI_SRI,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_2 = "offeringDefinition.productEntryId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PRODUCTENTRYID_7 = "offeringDefinition.productEntryId IN (";
	private static final String _FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_2 = "offeringDefinition.supportResponseId = ?";
	private static final String _FINDER_COLUMN_PEI_SRI_SUPPORTRESPONSEID_7 = "offeringDefinition.supportResponseId IN (";
	public static final FinderPath FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED,
			OfferingDefinitionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByPEI_SRI_PD_L_UL_ST",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			},
			OfferingDefinitionModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.SUPPORTRESPONSEID_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.PRODUCTDESCRIPTION_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.LICENSES_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.UNLIMITEDLICENSES_COLUMN_BITMASK |
			OfferingDefinitionModelImpl.SUPPORTTICKETS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST = new FinderPath(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPEI_SRI_PD_L_UL_ST",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			});

	/**
	 * Returns the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; or throws a {@link NoSuchOfferingDefinitionException} if it could not be found.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param productDescription the product description
	 * @param licenses the licenses
	 * @param unlimitedLicenses the unlimited licenses
	 * @param supportTickets the support tickets
	 * @return the matching offering definition
	 * @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition findByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = fetchByPEI_SRI_PD_L_UL_ST(productEntryId,
				supportResponseId, productDescription, licenses,
				unlimitedLicenses, supportTickets);

		if (offeringDefinition == null) {
			StringBundler msg = new StringBundler(14);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("productEntryId=");
			msg.append(productEntryId);

			msg.append(", supportResponseId=");
			msg.append(supportResponseId);

			msg.append(", productDescription=");
			msg.append(productDescription);

			msg.append(", licenses=");
			msg.append(licenses);

			msg.append(", unlimitedLicenses=");
			msg.append(unlimitedLicenses);

			msg.append(", supportTickets=");
			msg.append(supportTickets);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchOfferingDefinitionException(msg.toString());
		}

		return offeringDefinition;
	}

	/**
	 * Returns the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param productDescription the product description
	 * @param licenses the licenses
	 * @param unlimitedLicenses the unlimited licenses
	 * @param supportTickets the support tickets
	 * @return the matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition fetchByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets) {
		return fetchByPEI_SRI_PD_L_UL_ST(productEntryId, supportResponseId,
			productDescription, licenses, unlimitedLicenses, supportTickets,
			true);
	}

	/**
	 * Returns the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param productDescription the product description
	 * @param licenses the licenses
	 * @param unlimitedLicenses the unlimited licenses
	 * @param supportTickets the support tickets
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching offering definition, or <code>null</code> if a matching offering definition could not be found
	 */
	@Override
	public OfferingDefinition fetchByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				productEntryId, supportResponseId, productDescription, licenses,
				unlimitedLicenses, supportTickets
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
					finderArgs, this);
		}

		if (result instanceof OfferingDefinition) {
			OfferingDefinition offeringDefinition = (OfferingDefinition)result;

			if ((productEntryId != offeringDefinition.getProductEntryId()) ||
					(supportResponseId != offeringDefinition.getSupportResponseId()) ||
					!Objects.equals(productDescription,
						offeringDefinition.getProductDescription()) ||
					(licenses != offeringDefinition.getLicenses()) ||
					(unlimitedLicenses != offeringDefinition.getUnlimitedLicenses()) ||
					(supportTickets != offeringDefinition.getSupportTickets())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(8);

			query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTRESPONSEID_2);

			boolean bindProductDescription = false;

			if (productDescription == null) {
				query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_1);
			}
			else if (productDescription.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_3);
			}
			else {
				bindProductDescription = true;

				query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_2);
			}

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_LICENSES_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_UNLIMITEDLICENSES_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTTICKETS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(supportResponseId);

				if (bindProductDescription) {
					qPos.add(productDescription);
				}

				qPos.add(licenses);

				qPos.add(unlimitedLicenses);

				qPos.add(supportTickets);

				List<OfferingDefinition> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"OfferingDefinitionPersistenceImpl.fetchByPEI_SRI_PD_L_UL_ST(long, long, String, boolean, boolean, boolean, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					OfferingDefinition offeringDefinition = list.get(0);

					result = offeringDefinition;

					cacheResult(offeringDefinition);

					if ((offeringDefinition.getProductEntryId() != productEntryId) ||
							(offeringDefinition.getSupportResponseId() != supportResponseId) ||
							(offeringDefinition.getProductDescription() == null) ||
							!offeringDefinition.getProductDescription()
												   .equals(productDescription) ||
							(offeringDefinition.getLicenses() != licenses) ||
							(offeringDefinition.getUnlimitedLicenses() != unlimitedLicenses) ||
							(offeringDefinition.getSupportTickets() != supportTickets)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
							finderArgs, offeringDefinition);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
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
			return (OfferingDefinition)result;
		}
	}

	/**
	 * Removes the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param productDescription the product description
	 * @param licenses the licenses
	 * @param unlimitedLicenses the unlimited licenses
	 * @param supportTickets the support tickets
	 * @return the offering definition that was removed
	 */
	@Override
	public OfferingDefinition removeByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = findByPEI_SRI_PD_L_UL_ST(productEntryId,
				supportResponseId, productDescription, licenses,
				unlimitedLicenses, supportTickets);

		return remove(offeringDefinition);
	}

	/**
	 * Returns the number of offering definitions where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param supportResponseId the support response ID
	 * @param productDescription the product description
	 * @param licenses the licenses
	 * @param unlimitedLicenses the unlimited licenses
	 * @param supportTickets the support tickets
	 * @return the number of matching offering definitions
	 */
	@Override
	public int countByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST;

		Object[] finderArgs = new Object[] {
				productEntryId, supportResponseId, productDescription, licenses,
				unlimitedLicenses, supportTickets
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(7);

			query.append(_SQL_COUNT_OFFERINGDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTENTRYID_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTRESPONSEID_2);

			boolean bindProductDescription = false;

			if (productDescription == null) {
				query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_1);
			}
			else if (productDescription.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_3);
			}
			else {
				bindProductDescription = true;

				query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_2);
			}

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_LICENSES_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_UNLIMITEDLICENSES_2);

			query.append(_FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTTICKETS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(productEntryId);

				qPos.add(supportResponseId);

				if (bindProductDescription) {
					qPos.add(productDescription);
				}

				qPos.add(licenses);

				qPos.add(unlimitedLicenses);

				qPos.add(supportTickets);

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

	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTENTRYID_2 =
		"offeringDefinition.productEntryId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTRESPONSEID_2 =
		"offeringDefinition.supportResponseId = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_1 =
		"offeringDefinition.productDescription IS NULL AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_2 =
		"offeringDefinition.productDescription = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_PRODUCTDESCRIPTION_3 =
		"(offeringDefinition.productDescription IS NULL OR offeringDefinition.productDescription = '') AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_LICENSES_2 = "offeringDefinition.licenses = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_UNLIMITEDLICENSES_2 =
		"offeringDefinition.unlimitedLicenses = ? AND ";
	private static final String _FINDER_COLUMN_PEI_SRI_PD_L_UL_ST_SUPPORTTICKETS_2 =
		"offeringDefinition.supportTickets = ?";

	public OfferingDefinitionPersistenceImpl() {
		setModelClass(OfferingDefinition.class);
	}

	/**
	 * Caches the offering definition in the entity cache if it is enabled.
	 *
	 * @param offeringDefinition the offering definition
	 */
	@Override
	public void cacheResult(OfferingDefinition offeringDefinition) {
		entityCache.putResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionImpl.class, offeringDefinition.getPrimaryKey(),
			offeringDefinition);

		finderCache.putResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
			new Object[] {
				offeringDefinition.getProductEntryId(),
				offeringDefinition.getSupportResponseId(),
				offeringDefinition.getProductDescription(),
				offeringDefinition.getLicenses(),
				offeringDefinition.getUnlimitedLicenses(),
				offeringDefinition.getSupportTickets()
			}, offeringDefinition);

		offeringDefinition.resetOriginalValues();
	}

	/**
	 * Caches the offering definitions in the entity cache if it is enabled.
	 *
	 * @param offeringDefinitions the offering definitions
	 */
	@Override
	public void cacheResult(List<OfferingDefinition> offeringDefinitions) {
		for (OfferingDefinition offeringDefinition : offeringDefinitions) {
			if (entityCache.getResult(
						OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						OfferingDefinitionImpl.class,
						offeringDefinition.getPrimaryKey()) == null) {
				cacheResult(offeringDefinition);
			}
			else {
				offeringDefinition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all offering definitions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OfferingDefinitionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the offering definition.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OfferingDefinition offeringDefinition) {
		entityCache.removeResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionImpl.class, offeringDefinition.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OfferingDefinitionModelImpl)offeringDefinition,
			true);
	}

	@Override
	public void clearCache(List<OfferingDefinition> offeringDefinitions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OfferingDefinition offeringDefinition : offeringDefinitions) {
			entityCache.removeResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				OfferingDefinitionImpl.class, offeringDefinition.getPrimaryKey());

			clearUniqueFindersCache((OfferingDefinitionModelImpl)offeringDefinition,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		OfferingDefinitionModelImpl offeringDefinitionModelImpl) {
		Object[] args = new Object[] {
				offeringDefinitionModelImpl.getProductEntryId(),
				offeringDefinitionModelImpl.getSupportResponseId(),
				offeringDefinitionModelImpl.getProductDescription(),
				offeringDefinitionModelImpl.getLicenses(),
				offeringDefinitionModelImpl.getUnlimitedLicenses(),
				offeringDefinitionModelImpl.getSupportTickets()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST, args,
			offeringDefinitionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OfferingDefinitionModelImpl offeringDefinitionModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					offeringDefinitionModelImpl.getProductEntryId(),
					offeringDefinitionModelImpl.getSupportResponseId(),
					offeringDefinitionModelImpl.getProductDescription(),
					offeringDefinitionModelImpl.getLicenses(),
					offeringDefinitionModelImpl.getUnlimitedLicenses(),
					offeringDefinitionModelImpl.getSupportTickets()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
				args);
		}

		if ((offeringDefinitionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					offeringDefinitionModelImpl.getOriginalProductEntryId(),
					offeringDefinitionModelImpl.getOriginalSupportResponseId(),
					offeringDefinitionModelImpl.getOriginalProductDescription(),
					offeringDefinitionModelImpl.getOriginalLicenses(),
					offeringDefinitionModelImpl.getOriginalUnlimitedLicenses(),
					offeringDefinitionModelImpl.getOriginalSupportTickets()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PEI_SRI_PD_L_UL_ST,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PEI_SRI_PD_L_UL_ST,
				args);
		}
	}

	/**
	 * Creates a new offering definition with the primary key. Does not add the offering definition to the database.
	 *
	 * @param offeringDefinitionId the primary key for the new offering definition
	 * @return the new offering definition
	 */
	@Override
	public OfferingDefinition create(long offeringDefinitionId) {
		OfferingDefinition offeringDefinition = new OfferingDefinitionImpl();

		offeringDefinition.setNew(true);
		offeringDefinition.setPrimaryKey(offeringDefinitionId);

		offeringDefinition.setCompanyId(companyProvider.getCompanyId());

		return offeringDefinition;
	}

	/**
	 * Removes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offeringDefinitionId the primary key of the offering definition
	 * @return the offering definition that was removed
	 * @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 */
	@Override
	public OfferingDefinition remove(long offeringDefinitionId)
		throws NoSuchOfferingDefinitionException {
		return remove((Serializable)offeringDefinitionId);
	}

	/**
	 * Removes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the offering definition
	 * @return the offering definition that was removed
	 * @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 */
	@Override
	public OfferingDefinition remove(Serializable primaryKey)
		throws NoSuchOfferingDefinitionException {
		Session session = null;

		try {
			session = openSession();

			OfferingDefinition offeringDefinition = (OfferingDefinition)session.get(OfferingDefinitionImpl.class,
					primaryKey);

			if (offeringDefinition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOfferingDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(offeringDefinition);
		}
		catch (NoSuchOfferingDefinitionException nsee) {
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
	protected OfferingDefinition removeImpl(
		OfferingDefinition offeringDefinition) {
		offeringDefinition = toUnwrappedModel(offeringDefinition);

		offeringDefinitionToOfferingBundleTableMapper.deleteLeftPrimaryKeyTableMappings(offeringDefinition.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(offeringDefinition)) {
				offeringDefinition = (OfferingDefinition)session.get(OfferingDefinitionImpl.class,
						offeringDefinition.getPrimaryKeyObj());
			}

			if (offeringDefinition != null) {
				session.delete(offeringDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (offeringDefinition != null) {
			clearCache(offeringDefinition);
		}

		return offeringDefinition;
	}

	@Override
	public OfferingDefinition updateImpl(OfferingDefinition offeringDefinition) {
		offeringDefinition = toUnwrappedModel(offeringDefinition);

		boolean isNew = offeringDefinition.isNew();

		OfferingDefinitionModelImpl offeringDefinitionModelImpl = (OfferingDefinitionModelImpl)offeringDefinition;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (offeringDefinition.getCreateDate() == null)) {
			if (serviceContext == null) {
				offeringDefinition.setCreateDate(now);
			}
			else {
				offeringDefinition.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!offeringDefinitionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				offeringDefinition.setModifiedDate(now);
			}
			else {
				offeringDefinition.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (offeringDefinition.isNew()) {
				session.save(offeringDefinition);

				offeringDefinition.setNew(false);
			}
			else {
				offeringDefinition = (OfferingDefinition)session.merge(offeringDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OfferingDefinitionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					offeringDefinitionModelImpl.getProductEntryId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
				args);

			args = new Object[] {
					offeringDefinitionModelImpl.getSupportResponseId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTRESPONSEID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTRESPONSEID,
				args);

			args = new Object[] {
					offeringDefinitionModelImpl.getProductEntryId(),
					offeringDefinitionModelImpl.getSupportResponseId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PEI_SRI, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_SRI,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((offeringDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						offeringDefinitionModelImpl.getOriginalProductEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
					args);

				args = new Object[] {
						offeringDefinitionModelImpl.getProductEntryId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRODUCTENTRYID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRODUCTENTRYID,
					args);
			}

			if ((offeringDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTRESPONSEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						offeringDefinitionModelImpl.getOriginalSupportResponseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTRESPONSEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTRESPONSEID,
					args);

				args = new Object[] {
						offeringDefinitionModelImpl.getSupportResponseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SUPPORTRESPONSEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUPPORTRESPONSEID,
					args);
			}

			if ((offeringDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_SRI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						offeringDefinitionModelImpl.getOriginalProductEntryId(),
						offeringDefinitionModelImpl.getOriginalSupportResponseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PEI_SRI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_SRI,
					args);

				args = new Object[] {
						offeringDefinitionModelImpl.getProductEntryId(),
						offeringDefinitionModelImpl.getSupportResponseId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PEI_SRI, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PEI_SRI,
					args);
			}
		}

		entityCache.putResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			OfferingDefinitionImpl.class, offeringDefinition.getPrimaryKey(),
			offeringDefinition, false);

		clearUniqueFindersCache(offeringDefinitionModelImpl, false);
		cacheUniqueFindersCache(offeringDefinitionModelImpl);

		offeringDefinition.resetOriginalValues();

		return offeringDefinition;
	}

	protected OfferingDefinition toUnwrappedModel(
		OfferingDefinition offeringDefinition) {
		if (offeringDefinition instanceof OfferingDefinitionImpl) {
			return offeringDefinition;
		}

		OfferingDefinitionImpl offeringDefinitionImpl = new OfferingDefinitionImpl();

		offeringDefinitionImpl.setNew(offeringDefinition.isNew());
		offeringDefinitionImpl.setPrimaryKey(offeringDefinition.getPrimaryKey());

		offeringDefinitionImpl.setOfferingDefinitionId(offeringDefinition.getOfferingDefinitionId());
		offeringDefinitionImpl.setCompanyId(offeringDefinition.getCompanyId());
		offeringDefinitionImpl.setUserId(offeringDefinition.getUserId());
		offeringDefinitionImpl.setUserName(offeringDefinition.getUserName());
		offeringDefinitionImpl.setCreateDate(offeringDefinition.getCreateDate());
		offeringDefinitionImpl.setModifiedDate(offeringDefinition.getModifiedDate());
		offeringDefinitionImpl.setProductEntryId(offeringDefinition.getProductEntryId());
		offeringDefinitionImpl.setSupportResponseId(offeringDefinition.getSupportResponseId());
		offeringDefinitionImpl.setProductDescription(offeringDefinition.getProductDescription());
		offeringDefinitionImpl.setLicenses(offeringDefinition.isLicenses());
		offeringDefinitionImpl.setUnlimitedLicenses(offeringDefinition.isUnlimitedLicenses());
		offeringDefinitionImpl.setMaxConcurrentUsers(offeringDefinition.getMaxConcurrentUsers());
		offeringDefinitionImpl.setMaxUsers(offeringDefinition.getMaxUsers());
		offeringDefinitionImpl.setSupportTickets(offeringDefinition.isSupportTickets());

		return offeringDefinitionImpl;
	}

	/**
	 * Returns the offering definition with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering definition
	 * @return the offering definition
	 * @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 */
	@Override
	public OfferingDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOfferingDefinitionException {
		OfferingDefinition offeringDefinition = fetchByPrimaryKey(primaryKey);

		if (offeringDefinition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOfferingDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return offeringDefinition;
	}

	/**
	 * Returns the offering definition with the primary key or throws a {@link NoSuchOfferingDefinitionException} if it could not be found.
	 *
	 * @param offeringDefinitionId the primary key of the offering definition
	 * @return the offering definition
	 * @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	 */
	@Override
	public OfferingDefinition findByPrimaryKey(long offeringDefinitionId)
		throws NoSuchOfferingDefinitionException {
		return findByPrimaryKey((Serializable)offeringDefinitionId);
	}

	/**
	 * Returns the offering definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the offering definition
	 * @return the offering definition, or <code>null</code> if a offering definition with the primary key could not be found
	 */
	@Override
	public OfferingDefinition fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				OfferingDefinitionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OfferingDefinition offeringDefinition = (OfferingDefinition)serializable;

		if (offeringDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				offeringDefinition = (OfferingDefinition)session.get(OfferingDefinitionImpl.class,
						primaryKey);

				if (offeringDefinition != null) {
					cacheResult(offeringDefinition);
				}
				else {
					entityCache.putResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						OfferingDefinitionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					OfferingDefinitionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return offeringDefinition;
	}

	/**
	 * Returns the offering definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param offeringDefinitionId the primary key of the offering definition
	 * @return the offering definition, or <code>null</code> if a offering definition with the primary key could not be found
	 */
	@Override
	public OfferingDefinition fetchByPrimaryKey(long offeringDefinitionId) {
		return fetchByPrimaryKey((Serializable)offeringDefinitionId);
	}

	@Override
	public Map<Serializable, OfferingDefinition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OfferingDefinition> map = new HashMap<Serializable, OfferingDefinition>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OfferingDefinition offeringDefinition = fetchByPrimaryKey(primaryKey);

			if (offeringDefinition != null) {
				map.put(primaryKey, offeringDefinition);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					OfferingDefinitionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OfferingDefinition)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OFFERINGDEFINITION_WHERE_PKS_IN);

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

			for (OfferingDefinition offeringDefinition : (List<OfferingDefinition>)q.list()) {
				map.put(offeringDefinition.getPrimaryKeyObj(),
					offeringDefinition);

				cacheResult(offeringDefinition);

				uncachedPrimaryKeys.remove(offeringDefinition.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OfferingDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					OfferingDefinitionImpl.class, primaryKey, nullModel);
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
	 * Returns all the offering definitions.
	 *
	 * @return the offering definitions
	 */
	@Override
	public List<OfferingDefinition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offering definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of offering definitions
	 */
	@Override
	public List<OfferingDefinition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offering definitions
	 */
	@Override
	public List<OfferingDefinition> findAll(int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offering definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of offering definitions
	 */
	@Override
	public List<OfferingDefinition> findAll(int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator,
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

		List<OfferingDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<OfferingDefinition>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OFFERINGDEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OFFERINGDEFINITION;

				if (pagination) {
					sql = sql.concat(OfferingDefinitionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OfferingDefinition>)QueryUtil.list(q,
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
	 * Removes all the offering definitions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OfferingDefinition offeringDefinition : findAll()) {
			remove(offeringDefinition);
		}
	}

	/**
	 * Returns the number of offering definitions.
	 *
	 * @return the number of offering definitions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OFFERINGDEFINITION);

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
	 * Returns the primaryKeys of offering bundles associated with the offering definition.
	 *
	 * @param pk the primary key of the offering definition
	 * @return long[] of the primaryKeys of offering bundles associated with the offering definition
	 */
	@Override
	public long[] getOfferingBundlePrimaryKeys(long pk) {
		long[] pks = offeringDefinitionToOfferingBundleTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the offering bundles associated with the offering definition.
	 *
	 * @param pk the primary key of the offering definition
	 * @return the offering bundles associated with the offering definition
	 */
	@Override
	public List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk) {
		return getOfferingBundles(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the offering bundles associated with the offering definition.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the offering definition
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @return the range of offering bundles associated with the offering definition
	 */
	@Override
	public List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk, int start, int end) {
		return getOfferingBundles(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offering bundles associated with the offering definition.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the offering definition
	 * @param start the lower bound of the range of offering definitions
	 * @param end the upper bound of the range of offering definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offering bundles associated with the offering definition
	 */
	@Override
	public List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.model.OfferingBundle> orderByComparator) {
		return offeringDefinitionToOfferingBundleTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of offering bundles associated with the offering definition.
	 *
	 * @param pk the primary key of the offering definition
	 * @return the number of offering bundles associated with the offering definition
	 */
	@Override
	public int getOfferingBundlesSize(long pk) {
		long[] pks = offeringDefinitionToOfferingBundleTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the offering bundle is associated with the offering definition.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePK the primary key of the offering bundle
	 * @return <code>true</code> if the offering bundle is associated with the offering definition; <code>false</code> otherwise
	 */
	@Override
	public boolean containsOfferingBundle(long pk, long offeringBundlePK) {
		return offeringDefinitionToOfferingBundleTableMapper.containsTableMapping(pk,
			offeringBundlePK);
	}

	/**
	 * Returns <code>true</code> if the offering definition has any offering bundles associated with it.
	 *
	 * @param pk the primary key of the offering definition to check for associations with offering bundles
	 * @return <code>true</code> if the offering definition has any offering bundles associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsOfferingBundles(long pk) {
		if (getOfferingBundlesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePK the primary key of the offering bundle
	 */
	@Override
	public void addOfferingBundle(long pk, long offeringBundlePK) {
		OfferingDefinition offeringDefinition = fetchByPrimaryKey(pk);

		if (offeringDefinition == null) {
			offeringDefinitionToOfferingBundleTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, offeringBundlePK);
		}
		else {
			offeringDefinitionToOfferingBundleTableMapper.addTableMapping(offeringDefinition.getCompanyId(),
				pk, offeringBundlePK);
		}
	}

	/**
	 * Adds an association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundle the offering bundle
	 */
	@Override
	public void addOfferingBundle(long pk,
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		OfferingDefinition offeringDefinition = fetchByPrimaryKey(pk);

		if (offeringDefinition == null) {
			offeringDefinitionToOfferingBundleTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, offeringBundle.getPrimaryKey());
		}
		else {
			offeringDefinitionToOfferingBundleTableMapper.addTableMapping(offeringDefinition.getCompanyId(),
				pk, offeringBundle.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePKs the primary keys of the offering bundles
	 */
	@Override
	public void addOfferingBundles(long pk, long[] offeringBundlePKs) {
		long companyId = 0;

		OfferingDefinition offeringDefinition = fetchByPrimaryKey(pk);

		if (offeringDefinition == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = offeringDefinition.getCompanyId();
		}

		offeringDefinitionToOfferingBundleTableMapper.addTableMappings(companyId,
			pk, offeringBundlePKs);
	}

	/**
	 * Adds an association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundles the offering bundles
	 */
	@Override
	public void addOfferingBundles(long pk,
		List<com.liferay.osb.model.OfferingBundle> offeringBundles) {
		addOfferingBundles(pk,
			ListUtil.toLongArray(offeringBundles,
				com.liferay.osb.model.OfferingBundle.OFFERING_BUNDLE_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the offering definition and its offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition to clear the associated offering bundles from
	 */
	@Override
	public void clearOfferingBundles(long pk) {
		offeringDefinitionToOfferingBundleTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePK the primary key of the offering bundle
	 */
	@Override
	public void removeOfferingBundle(long pk, long offeringBundlePK) {
		offeringDefinitionToOfferingBundleTableMapper.deleteTableMapping(pk,
			offeringBundlePK);
	}

	/**
	 * Removes the association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundle the offering bundle
	 */
	@Override
	public void removeOfferingBundle(long pk,
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		offeringDefinitionToOfferingBundleTableMapper.deleteTableMapping(pk,
			offeringBundle.getPrimaryKey());
	}

	/**
	 * Removes the association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePKs the primary keys of the offering bundles
	 */
	@Override
	public void removeOfferingBundles(long pk, long[] offeringBundlePKs) {
		offeringDefinitionToOfferingBundleTableMapper.deleteTableMappings(pk,
			offeringBundlePKs);
	}

	/**
	 * Removes the association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundles the offering bundles
	 */
	@Override
	public void removeOfferingBundles(long pk,
		List<com.liferay.osb.model.OfferingBundle> offeringBundles) {
		removeOfferingBundles(pk,
			ListUtil.toLongArray(offeringBundles,
				com.liferay.osb.model.OfferingBundle.OFFERING_BUNDLE_ID_ACCESSOR));
	}

	/**
	 * Sets the offering bundles associated with the offering definition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundlePKs the primary keys of the offering bundles to be associated with the offering definition
	 */
	@Override
	public void setOfferingBundles(long pk, long[] offeringBundlePKs) {
		Set<Long> newOfferingBundlePKsSet = SetUtil.fromArray(offeringBundlePKs);
		Set<Long> oldOfferingBundlePKsSet = SetUtil.fromArray(offeringDefinitionToOfferingBundleTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeOfferingBundlePKsSet = new HashSet<Long>(oldOfferingBundlePKsSet);

		removeOfferingBundlePKsSet.removeAll(newOfferingBundlePKsSet);

		offeringDefinitionToOfferingBundleTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeOfferingBundlePKsSet));

		newOfferingBundlePKsSet.removeAll(oldOfferingBundlePKsSet);

		long companyId = 0;

		OfferingDefinition offeringDefinition = fetchByPrimaryKey(pk);

		if (offeringDefinition == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = offeringDefinition.getCompanyId();
		}

		offeringDefinitionToOfferingBundleTableMapper.addTableMappings(companyId,
			pk, ArrayUtil.toLongArray(newOfferingBundlePKsSet));
	}

	/**
	 * Sets the offering bundles associated with the offering definition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the offering definition
	 * @param offeringBundles the offering bundles to be associated with the offering definition
	 */
	@Override
	public void setOfferingBundles(long pk,
		List<com.liferay.osb.model.OfferingBundle> offeringBundles) {
		try {
			long[] offeringBundlePKs = new long[offeringBundles.size()];

			for (int i = 0; i < offeringBundles.size(); i++) {
				com.liferay.osb.model.OfferingBundle offeringBundle = offeringBundles.get(i);

				offeringBundlePKs[i] = offeringBundle.getPrimaryKey();
			}

			setOfferingBundles(pk, offeringBundlePKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OfferingDefinitionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the offering definition persistence.
	 */
	public void afterPropertiesSet() {
		offeringDefinitionToOfferingBundleTableMapper = TableMapperFactory.getTableMapper("OSB_OfferingBundles_OfferingDefinitions",
				"companyId", "offeringDefinitionId", "offeringBundleId", this,
				offeringBundlePersistence);
	}

	public void destroy() {
		entityCache.removeCache(OfferingDefinitionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper(
			"OSB_OfferingBundles_OfferingDefinitions");
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	@BeanReference(type = OfferingBundlePersistence.class)
	protected OfferingBundlePersistence offeringBundlePersistence;
	protected TableMapper<OfferingDefinition, com.liferay.osb.model.OfferingBundle> offeringDefinitionToOfferingBundleTableMapper;
	private static final String _SQL_SELECT_OFFERINGDEFINITION = "SELECT offeringDefinition FROM OfferingDefinition offeringDefinition";
	private static final String _SQL_SELECT_OFFERINGDEFINITION_WHERE_PKS_IN = "SELECT offeringDefinition FROM OfferingDefinition offeringDefinition WHERE offeringDefinitionId IN (";
	private static final String _SQL_SELECT_OFFERINGDEFINITION_WHERE = "SELECT offeringDefinition FROM OfferingDefinition offeringDefinition WHERE ";
	private static final String _SQL_COUNT_OFFERINGDEFINITION = "SELECT COUNT(offeringDefinition) FROM OfferingDefinition offeringDefinition";
	private static final String _SQL_COUNT_OFFERINGDEFINITION_WHERE = "SELECT COUNT(offeringDefinition) FROM OfferingDefinition offeringDefinition WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "offeringDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OfferingDefinition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OfferingDefinition exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(OfferingDefinitionPersistenceImpl.class);
}