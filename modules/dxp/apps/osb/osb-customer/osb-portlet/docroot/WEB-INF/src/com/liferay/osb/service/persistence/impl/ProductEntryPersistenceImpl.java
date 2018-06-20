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

import com.liferay.osb.exception.NoSuchProductEntryException;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.impl.ProductEntryImpl;
import com.liferay.osb.model.impl.ProductEntryModelImpl;
import com.liferay.osb.service.persistence.ProductEntryPersistence;

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
 * The persistence implementation for the product entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryPersistence
 * @see com.liferay.osb.service.persistence.ProductEntryUtil
 * @generated
 */
@ProviderType
public class ProductEntryPersistenceImpl extends BasePersistenceImpl<ProductEntry>
	implements ProductEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProductEntryUtil} to access the product entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProductEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProductEntryModelImpl.FINDER_CACHE_ENABLED, ProductEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProductEntryModelImpl.FINDER_CACHE_ENABLED, ProductEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProductEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProductEntryModelImpl.FINDER_CACHE_ENABLED, ProductEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			ProductEntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProductEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the product entry where name = &#63; or throws a {@link NoSuchProductEntryException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	@Override
	public ProductEntry findByName(String name)
		throws NoSuchProductEntryException {
		ProductEntry productEntry = fetchByName(name);

		if (productEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProductEntryException(msg.toString());
		}

		return productEntry;
	}

	/**
	 * Returns the product entry where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	@Override
	public ProductEntry fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the product entry where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	@Override
	public ProductEntry fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof ProductEntry) {
			ProductEntry productEntry = (ProductEntry)result;

			if (!Objects.equals(name, productEntry.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PRODUCTENTRY_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				List<ProductEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					ProductEntry productEntry = list.get(0);

					result = productEntry;

					cacheResult(productEntry);

					if ((productEntry.getName() == null) ||
							!productEntry.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, productEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, finderArgs);

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
			return (ProductEntry)result;
		}
	}

	/**
	 * Removes the product entry where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the product entry that was removed
	 */
	@Override
	public ProductEntry removeByName(String name)
		throws NoSuchProductEntryException {
		ProductEntry productEntry = findByName(name);

		return remove(productEntry);
	}

	/**
	 * Returns the number of product entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching product entries
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PRODUCTENTRY_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "productEntry.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "productEntry.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(productEntry.name IS NULL OR productEntry.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ENVIRONMENT =
		new FinderPath(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProductEntryModelImpl.FINDER_CACHE_ENABLED, ProductEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEnvironment",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENVIRONMENT =
		new FinderPath(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProductEntryModelImpl.FINDER_CACHE_ENABLED, ProductEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEnvironment",
			new String[] { Integer.class.getName() },
			ProductEntryModelImpl.ENVIRONMENT_COLUMN_BITMASK |
			ProductEntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ENVIRONMENT = new FinderPath(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProductEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEnvironment",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the product entries where environment = &#63;.
	 *
	 * @param environment the environment
	 * @return the matching product entries
	 */
	@Override
	public List<ProductEntry> findByEnvironment(int environment) {
		return findByEnvironment(environment, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product entries where environment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProductEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param environment the environment
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of matching product entries
	 */
	@Override
	public List<ProductEntry> findByEnvironment(int environment, int start,
		int end) {
		return findByEnvironment(environment, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product entries where environment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProductEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param environment the environment
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product entries
	 */
	@Override
	public List<ProductEntry> findByEnvironment(int environment, int start,
		int end, OrderByComparator<ProductEntry> orderByComparator) {
		return findByEnvironment(environment, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the product entries where environment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProductEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param environment the environment
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching product entries
	 */
	@Override
	public List<ProductEntry> findByEnvironment(int environment, int start,
		int end, OrderByComparator<ProductEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENVIRONMENT;
			finderArgs = new Object[] { environment };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ENVIRONMENT;
			finderArgs = new Object[] { environment, start, end, orderByComparator };
		}

		List<ProductEntry> list = null;

		if (retrieveFromCache) {
			list = (List<ProductEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductEntry productEntry : list) {
					if ((environment != productEntry.getEnvironment())) {
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

			query.append(_SQL_SELECT_PRODUCTENTRY_WHERE);

			query.append(_FINDER_COLUMN_ENVIRONMENT_ENVIRONMENT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProductEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(environment);

				if (!pagination) {
					list = (List<ProductEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProductEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	@Override
	public ProductEntry findByEnvironment_First(int environment,
		OrderByComparator<ProductEntry> orderByComparator)
		throws NoSuchProductEntryException {
		ProductEntry productEntry = fetchByEnvironment_First(environment,
				orderByComparator);

		if (productEntry != null) {
			return productEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("environment=");
		msg.append(environment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProductEntryException(msg.toString());
	}

	/**
	 * Returns the first product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	@Override
	public ProductEntry fetchByEnvironment_First(int environment,
		OrderByComparator<ProductEntry> orderByComparator) {
		List<ProductEntry> list = findByEnvironment(environment, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	@Override
	public ProductEntry findByEnvironment_Last(int environment,
		OrderByComparator<ProductEntry> orderByComparator)
		throws NoSuchProductEntryException {
		ProductEntry productEntry = fetchByEnvironment_Last(environment,
				orderByComparator);

		if (productEntry != null) {
			return productEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("environment=");
		msg.append(environment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProductEntryException(msg.toString());
	}

	/**
	 * Returns the last product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	@Override
	public ProductEntry fetchByEnvironment_Last(int environment,
		OrderByComparator<ProductEntry> orderByComparator) {
		int count = countByEnvironment(environment);

		if (count == 0) {
			return null;
		}

		List<ProductEntry> list = findByEnvironment(environment, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product entries before and after the current product entry in the ordered set where environment = &#63;.
	 *
	 * @param productEntryId the primary key of the current product entry
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	@Override
	public ProductEntry[] findByEnvironment_PrevAndNext(long productEntryId,
		int environment, OrderByComparator<ProductEntry> orderByComparator)
		throws NoSuchProductEntryException {
		ProductEntry productEntry = findByPrimaryKey(productEntryId);

		Session session = null;

		try {
			session = openSession();

			ProductEntry[] array = new ProductEntryImpl[3];

			array[0] = getByEnvironment_PrevAndNext(session, productEntry,
					environment, orderByComparator, true);

			array[1] = productEntry;

			array[2] = getByEnvironment_PrevAndNext(session, productEntry,
					environment, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProductEntry getByEnvironment_PrevAndNext(Session session,
		ProductEntry productEntry, int environment,
		OrderByComparator<ProductEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PRODUCTENTRY_WHERE);

		query.append(_FINDER_COLUMN_ENVIRONMENT_ENVIRONMENT_2);

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
			query.append(ProductEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(environment);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(productEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProductEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product entries where environment = &#63; from the database.
	 *
	 * @param environment the environment
	 */
	@Override
	public void removeByEnvironment(int environment) {
		for (ProductEntry productEntry : findByEnvironment(environment,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(productEntry);
		}
	}

	/**
	 * Returns the number of product entries where environment = &#63;.
	 *
	 * @param environment the environment
	 * @return the number of matching product entries
	 */
	@Override
	public int countByEnvironment(int environment) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ENVIRONMENT;

		Object[] finderArgs = new Object[] { environment };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PRODUCTENTRY_WHERE);

			query.append(_FINDER_COLUMN_ENVIRONMENT_ENVIRONMENT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(environment);

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

	private static final String _FINDER_COLUMN_ENVIRONMENT_ENVIRONMENT_2 = "productEntry.environment = ?";

	public ProductEntryPersistenceImpl() {
		setModelClass(ProductEntry.class);
	}

	/**
	 * Caches the product entry in the entity cache if it is enabled.
	 *
	 * @param productEntry the product entry
	 */
	@Override
	public void cacheResult(ProductEntry productEntry) {
		entityCache.putResult(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProductEntryImpl.class, productEntry.getPrimaryKey(), productEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { productEntry.getName() }, productEntry);

		productEntry.resetOriginalValues();
	}

	/**
	 * Caches the product entries in the entity cache if it is enabled.
	 *
	 * @param productEntries the product entries
	 */
	@Override
	public void cacheResult(List<ProductEntry> productEntries) {
		for (ProductEntry productEntry : productEntries) {
			if (entityCache.getResult(
						ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
						ProductEntryImpl.class, productEntry.getPrimaryKey()) == null) {
				cacheResult(productEntry);
			}
			else {
				productEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all product entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProductEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the product entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProductEntry productEntry) {
		entityCache.removeResult(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProductEntryImpl.class, productEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ProductEntryModelImpl)productEntry, true);
	}

	@Override
	public void clearCache(List<ProductEntry> productEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProductEntry productEntry : productEntries) {
			entityCache.removeResult(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
				ProductEntryImpl.class, productEntry.getPrimaryKey());

			clearUniqueFindersCache((ProductEntryModelImpl)productEntry, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ProductEntryModelImpl productEntryModelImpl) {
		Object[] args = new Object[] { productEntryModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
			productEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProductEntryModelImpl productEntryModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { productEntryModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((productEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { productEntryModelImpl.getOriginalName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new product entry with the primary key. Does not add the product entry to the database.
	 *
	 * @param productEntryId the primary key for the new product entry
	 * @return the new product entry
	 */
	@Override
	public ProductEntry create(long productEntryId) {
		ProductEntry productEntry = new ProductEntryImpl();

		productEntry.setNew(true);
		productEntry.setPrimaryKey(productEntryId);

		return productEntry;
	}

	/**
	 * Removes the product entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry that was removed
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	@Override
	public ProductEntry remove(long productEntryId)
		throws NoSuchProductEntryException {
		return remove((Serializable)productEntryId);
	}

	/**
	 * Removes the product entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the product entry
	 * @return the product entry that was removed
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	@Override
	public ProductEntry remove(Serializable primaryKey)
		throws NoSuchProductEntryException {
		Session session = null;

		try {
			session = openSession();

			ProductEntry productEntry = (ProductEntry)session.get(ProductEntryImpl.class,
					primaryKey);

			if (productEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProductEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(productEntry);
		}
		catch (NoSuchProductEntryException nsee) {
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
	protected ProductEntry removeImpl(ProductEntry productEntry) {
		productEntry = toUnwrappedModel(productEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(productEntry)) {
				productEntry = (ProductEntry)session.get(ProductEntryImpl.class,
						productEntry.getPrimaryKeyObj());
			}

			if (productEntry != null) {
				session.delete(productEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (productEntry != null) {
			clearCache(productEntry);
		}

		return productEntry;
	}

	@Override
	public ProductEntry updateImpl(ProductEntry productEntry) {
		productEntry = toUnwrappedModel(productEntry);

		boolean isNew = productEntry.isNew();

		ProductEntryModelImpl productEntryModelImpl = (ProductEntryModelImpl)productEntry;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (productEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				productEntry.setCreateDate(now);
			}
			else {
				productEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!productEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				productEntry.setModifiedDate(now);
			}
			else {
				productEntry.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (productEntry.isNew()) {
				session.save(productEntry);

				productEntry.setNew(false);
			}
			else {
				productEntry = (ProductEntry)session.merge(productEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ProductEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { productEntryModelImpl.getEnvironment() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ENVIRONMENT, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENVIRONMENT,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((productEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENVIRONMENT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						productEntryModelImpl.getOriginalEnvironment()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ENVIRONMENT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENVIRONMENT,
					args);

				args = new Object[] { productEntryModelImpl.getEnvironment() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ENVIRONMENT, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENVIRONMENT,
					args);
			}
		}

		entityCache.putResult(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProductEntryImpl.class, productEntry.getPrimaryKey(), productEntry,
			false);

		clearUniqueFindersCache(productEntryModelImpl, false);
		cacheUniqueFindersCache(productEntryModelImpl);

		productEntry.resetOriginalValues();

		return productEntry;
	}

	protected ProductEntry toUnwrappedModel(ProductEntry productEntry) {
		if (productEntry instanceof ProductEntryImpl) {
			return productEntry;
		}

		ProductEntryImpl productEntryImpl = new ProductEntryImpl();

		productEntryImpl.setNew(productEntry.isNew());
		productEntryImpl.setPrimaryKey(productEntry.getPrimaryKey());

		productEntryImpl.setProductEntryId(productEntry.getProductEntryId());
		productEntryImpl.setUserId(productEntry.getUserId());
		productEntryImpl.setUserName(productEntry.getUserName());
		productEntryImpl.setCreateDate(productEntry.getCreateDate());
		productEntryImpl.setModifiedDate(productEntry.getModifiedDate());
		productEntryImpl.setName(productEntry.getName());
		productEntryImpl.setType(productEntry.getType());
		productEntryImpl.setEnvironment(productEntry.getEnvironment());
		productEntryImpl.setVersionsListType(productEntry.getVersionsListType());

		return productEntryImpl;
	}

	/**
	 * Returns the product entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the product entry
	 * @return the product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	@Override
	public ProductEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProductEntryException {
		ProductEntry productEntry = fetchByPrimaryKey(primaryKey);

		if (productEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProductEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return productEntry;
	}

	/**
	 * Returns the product entry with the primary key or throws a {@link NoSuchProductEntryException} if it could not be found.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	@Override
	public ProductEntry findByPrimaryKey(long productEntryId)
		throws NoSuchProductEntryException {
		return findByPrimaryKey((Serializable)productEntryId);
	}

	/**
	 * Returns the product entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the product entry
	 * @return the product entry, or <code>null</code> if a product entry with the primary key could not be found
	 */
	@Override
	public ProductEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
				ProductEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProductEntry productEntry = (ProductEntry)serializable;

		if (productEntry == null) {
			Session session = null;

			try {
				session = openSession();

				productEntry = (ProductEntry)session.get(ProductEntryImpl.class,
						primaryKey);

				if (productEntry != null) {
					cacheResult(productEntry);
				}
				else {
					entityCache.putResult(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
						ProductEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
					ProductEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return productEntry;
	}

	/**
	 * Returns the product entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry, or <code>null</code> if a product entry with the primary key could not be found
	 */
	@Override
	public ProductEntry fetchByPrimaryKey(long productEntryId) {
		return fetchByPrimaryKey((Serializable)productEntryId);
	}

	@Override
	public Map<Serializable, ProductEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProductEntry> map = new HashMap<Serializable, ProductEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProductEntry productEntry = fetchByPrimaryKey(primaryKey);

			if (productEntry != null) {
				map.put(primaryKey, productEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
					ProductEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProductEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PRODUCTENTRY_WHERE_PKS_IN);

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

			for (ProductEntry productEntry : (List<ProductEntry>)q.list()) {
				map.put(productEntry.getPrimaryKeyObj(), productEntry);

				cacheResult(productEntry);

				uncachedPrimaryKeys.remove(productEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProductEntryModelImpl.ENTITY_CACHE_ENABLED,
					ProductEntryImpl.class, primaryKey, nullModel);
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
	 * Returns all the product entries.
	 *
	 * @return the product entries
	 */
	@Override
	public List<ProductEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProductEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of product entries
	 */
	@Override
	public List<ProductEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProductEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product entries
	 */
	@Override
	public List<ProductEntry> findAll(int start, int end,
		OrderByComparator<ProductEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProductEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of product entries
	 */
	@Override
	public List<ProductEntry> findAll(int start, int end,
		OrderByComparator<ProductEntry> orderByComparator,
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

		List<ProductEntry> list = null;

		if (retrieveFromCache) {
			list = (List<ProductEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PRODUCTENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PRODUCTENTRY;

				if (pagination) {
					sql = sql.concat(ProductEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProductEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProductEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the product entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProductEntry productEntry : findAll()) {
			remove(productEntry);
		}
	}

	/**
	 * Returns the number of product entries.
	 *
	 * @return the number of product entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PRODUCTENTRY);

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
		return ProductEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the product entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProductEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_PRODUCTENTRY = "SELECT productEntry FROM ProductEntry productEntry";
	private static final String _SQL_SELECT_PRODUCTENTRY_WHERE_PKS_IN = "SELECT productEntry FROM ProductEntry productEntry WHERE productEntryId IN (";
	private static final String _SQL_SELECT_PRODUCTENTRY_WHERE = "SELECT productEntry FROM ProductEntry productEntry WHERE ";
	private static final String _SQL_COUNT_PRODUCTENTRY = "SELECT COUNT(productEntry) FROM ProductEntry productEntry";
	private static final String _SQL_COUNT_PRODUCTENTRY_WHERE = "SELECT COUNT(productEntry) FROM ProductEntry productEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "productEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProductEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProductEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ProductEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}