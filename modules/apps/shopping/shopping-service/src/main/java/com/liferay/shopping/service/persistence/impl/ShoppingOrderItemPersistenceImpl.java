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

package com.liferay.shopping.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.shopping.exception.NoSuchOrderItemException;
import com.liferay.shopping.model.ShoppingOrderItem;
import com.liferay.shopping.model.impl.ShoppingOrderItemImpl;
import com.liferay.shopping.model.impl.ShoppingOrderItemModelImpl;
import com.liferay.shopping.service.persistence.ShoppingOrderItemPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the shopping order item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ShoppingOrderItemPersistenceImpl
	extends BasePersistenceImpl<ShoppingOrderItem>
	implements ShoppingOrderItemPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ShoppingOrderItemUtil</code> to access the shopping order item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ShoppingOrderItemImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByOrderId;
	private FinderPath _finderPathWithoutPaginationFindByOrderId;
	private FinderPath _finderPathCountByOrderId;

	/**
	 * Returns all the shopping order items where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @return the matching shopping order items
	 */
	@Override
	public List<ShoppingOrderItem> findByOrderId(long orderId) {
		return findByOrderId(
			orderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the shopping order items where orderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ShoppingOrderItemModelImpl</code>.
	 * </p>
	 *
	 * @param orderId the order ID
	 * @param start the lower bound of the range of shopping order items
	 * @param end the upper bound of the range of shopping order items (not inclusive)
	 * @return the range of matching shopping order items
	 */
	@Override
	public List<ShoppingOrderItem> findByOrderId(
		long orderId, int start, int end) {

		return findByOrderId(orderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the shopping order items where orderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ShoppingOrderItemModelImpl</code>.
	 * </p>
	 *
	 * @param orderId the order ID
	 * @param start the lower bound of the range of shopping order items
	 * @param end the upper bound of the range of shopping order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching shopping order items
	 */
	@Override
	public List<ShoppingOrderItem> findByOrderId(
		long orderId, int start, int end,
		OrderByComparator<ShoppingOrderItem> orderByComparator) {

		return findByOrderId(orderId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the shopping order items where orderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ShoppingOrderItemModelImpl</code>.
	 * </p>
	 *
	 * @param orderId the order ID
	 * @param start the lower bound of the range of shopping order items
	 * @param end the upper bound of the range of shopping order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching shopping order items
	 */
	@Override
	public List<ShoppingOrderItem> findByOrderId(
		long orderId, int start, int end,
		OrderByComparator<ShoppingOrderItem> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByOrderId;
				finderArgs = new Object[] {orderId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByOrderId;
			finderArgs = new Object[] {orderId, start, end, orderByComparator};
		}

		List<ShoppingOrderItem> list = null;

		if (useFinderCache) {
			list = (List<ShoppingOrderItem>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ShoppingOrderItem shoppingOrderItem : list) {
					if (orderId != shoppingOrderItem.getOrderId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_SHOPPINGORDERITEM_WHERE);

			sb.append(_FINDER_COLUMN_ORDERID_ORDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ShoppingOrderItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(orderId);

				list = (List<ShoppingOrderItem>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first shopping order item in the ordered set where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching shopping order item
	 * @throws NoSuchOrderItemException if a matching shopping order item could not be found
	 */
	@Override
	public ShoppingOrderItem findByOrderId_First(
			long orderId,
			OrderByComparator<ShoppingOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		ShoppingOrderItem shoppingOrderItem = fetchByOrderId_First(
			orderId, orderByComparator);

		if (shoppingOrderItem != null) {
			return shoppingOrderItem;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("orderId=");
		sb.append(orderId);

		sb.append("}");

		throw new NoSuchOrderItemException(sb.toString());
	}

	/**
	 * Returns the first shopping order item in the ordered set where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching shopping order item, or <code>null</code> if a matching shopping order item could not be found
	 */
	@Override
	public ShoppingOrderItem fetchByOrderId_First(
		long orderId, OrderByComparator<ShoppingOrderItem> orderByComparator) {

		List<ShoppingOrderItem> list = findByOrderId(
			orderId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last shopping order item in the ordered set where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching shopping order item
	 * @throws NoSuchOrderItemException if a matching shopping order item could not be found
	 */
	@Override
	public ShoppingOrderItem findByOrderId_Last(
			long orderId,
			OrderByComparator<ShoppingOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		ShoppingOrderItem shoppingOrderItem = fetchByOrderId_Last(
			orderId, orderByComparator);

		if (shoppingOrderItem != null) {
			return shoppingOrderItem;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("orderId=");
		sb.append(orderId);

		sb.append("}");

		throw new NoSuchOrderItemException(sb.toString());
	}

	/**
	 * Returns the last shopping order item in the ordered set where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching shopping order item, or <code>null</code> if a matching shopping order item could not be found
	 */
	@Override
	public ShoppingOrderItem fetchByOrderId_Last(
		long orderId, OrderByComparator<ShoppingOrderItem> orderByComparator) {

		int count = countByOrderId(orderId);

		if (count == 0) {
			return null;
		}

		List<ShoppingOrderItem> list = findByOrderId(
			orderId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the shopping order items before and after the current shopping order item in the ordered set where orderId = &#63;.
	 *
	 * @param orderItemId the primary key of the current shopping order item
	 * @param orderId the order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next shopping order item
	 * @throws NoSuchOrderItemException if a shopping order item with the primary key could not be found
	 */
	@Override
	public ShoppingOrderItem[] findByOrderId_PrevAndNext(
			long orderItemId, long orderId,
			OrderByComparator<ShoppingOrderItem> orderByComparator)
		throws NoSuchOrderItemException {

		ShoppingOrderItem shoppingOrderItem = findByPrimaryKey(orderItemId);

		Session session = null;

		try {
			session = openSession();

			ShoppingOrderItem[] array = new ShoppingOrderItemImpl[3];

			array[0] = getByOrderId_PrevAndNext(
				session, shoppingOrderItem, orderId, orderByComparator, true);

			array[1] = shoppingOrderItem;

			array[2] = getByOrderId_PrevAndNext(
				session, shoppingOrderItem, orderId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ShoppingOrderItem getByOrderId_PrevAndNext(
		Session session, ShoppingOrderItem shoppingOrderItem, long orderId,
		OrderByComparator<ShoppingOrderItem> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SHOPPINGORDERITEM_WHERE);

		sb.append(_FINDER_COLUMN_ORDERID_ORDERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(ShoppingOrderItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(orderId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						shoppingOrderItem)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ShoppingOrderItem> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the shopping order items where orderId = &#63; from the database.
	 *
	 * @param orderId the order ID
	 */
	@Override
	public void removeByOrderId(long orderId) {
		for (ShoppingOrderItem shoppingOrderItem :
				findByOrderId(
					orderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(shoppingOrderItem);
		}
	}

	/**
	 * Returns the number of shopping order items where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @return the number of matching shopping order items
	 */
	@Override
	public int countByOrderId(long orderId) {
		FinderPath finderPath = _finderPathCountByOrderId;

		Object[] finderArgs = new Object[] {orderId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SHOPPINGORDERITEM_WHERE);

			sb.append(_FINDER_COLUMN_ORDERID_ORDERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(orderId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ORDERID_ORDERID_2 =
		"shoppingOrderItem.orderId = ?";

	public ShoppingOrderItemPersistenceImpl() {
		setModelClass(ShoppingOrderItem.class);
	}

	/**
	 * Caches the shopping order item in the entity cache if it is enabled.
	 *
	 * @param shoppingOrderItem the shopping order item
	 */
	@Override
	public void cacheResult(ShoppingOrderItem shoppingOrderItem) {
		entityCache.putResult(
			ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemImpl.class, shoppingOrderItem.getPrimaryKey(),
			shoppingOrderItem);

		shoppingOrderItem.resetOriginalValues();
	}

	/**
	 * Caches the shopping order items in the entity cache if it is enabled.
	 *
	 * @param shoppingOrderItems the shopping order items
	 */
	@Override
	public void cacheResult(List<ShoppingOrderItem> shoppingOrderItems) {
		for (ShoppingOrderItem shoppingOrderItem : shoppingOrderItems) {
			if (entityCache.getResult(
					ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
					ShoppingOrderItemImpl.class,
					shoppingOrderItem.getPrimaryKey()) == null) {

				cacheResult(shoppingOrderItem);
			}
			else {
				shoppingOrderItem.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all shopping order items.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ShoppingOrderItemImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the shopping order item.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ShoppingOrderItem shoppingOrderItem) {
		entityCache.removeResult(
			ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemImpl.class, shoppingOrderItem.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ShoppingOrderItem> shoppingOrderItems) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ShoppingOrderItem shoppingOrderItem : shoppingOrderItems) {
			entityCache.removeResult(
				ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
				ShoppingOrderItemImpl.class, shoppingOrderItem.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
				ShoppingOrderItemImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new shopping order item with the primary key. Does not add the shopping order item to the database.
	 *
	 * @param orderItemId the primary key for the new shopping order item
	 * @return the new shopping order item
	 */
	@Override
	public ShoppingOrderItem create(long orderItemId) {
		ShoppingOrderItem shoppingOrderItem = new ShoppingOrderItemImpl();

		shoppingOrderItem.setNew(true);
		shoppingOrderItem.setPrimaryKey(orderItemId);

		shoppingOrderItem.setCompanyId(CompanyThreadLocal.getCompanyId());

		return shoppingOrderItem;
	}

	/**
	 * Removes the shopping order item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orderItemId the primary key of the shopping order item
	 * @return the shopping order item that was removed
	 * @throws NoSuchOrderItemException if a shopping order item with the primary key could not be found
	 */
	@Override
	public ShoppingOrderItem remove(long orderItemId)
		throws NoSuchOrderItemException {

		return remove((Serializable)orderItemId);
	}

	/**
	 * Removes the shopping order item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the shopping order item
	 * @return the shopping order item that was removed
	 * @throws NoSuchOrderItemException if a shopping order item with the primary key could not be found
	 */
	@Override
	public ShoppingOrderItem remove(Serializable primaryKey)
		throws NoSuchOrderItemException {

		Session session = null;

		try {
			session = openSession();

			ShoppingOrderItem shoppingOrderItem =
				(ShoppingOrderItem)session.get(
					ShoppingOrderItemImpl.class, primaryKey);

			if (shoppingOrderItem == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOrderItemException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(shoppingOrderItem);
		}
		catch (NoSuchOrderItemException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ShoppingOrderItem removeImpl(
		ShoppingOrderItem shoppingOrderItem) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(shoppingOrderItem)) {
				shoppingOrderItem = (ShoppingOrderItem)session.get(
					ShoppingOrderItemImpl.class,
					shoppingOrderItem.getPrimaryKeyObj());
			}

			if (shoppingOrderItem != null) {
				session.delete(shoppingOrderItem);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (shoppingOrderItem != null) {
			clearCache(shoppingOrderItem);
		}

		return shoppingOrderItem;
	}

	@Override
	public ShoppingOrderItem updateImpl(ShoppingOrderItem shoppingOrderItem) {
		boolean isNew = shoppingOrderItem.isNew();

		if (!(shoppingOrderItem instanceof ShoppingOrderItemModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(shoppingOrderItem.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					shoppingOrderItem);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in shoppingOrderItem proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ShoppingOrderItem implementation " +
					shoppingOrderItem.getClass());
		}

		ShoppingOrderItemModelImpl shoppingOrderItemModelImpl =
			(ShoppingOrderItemModelImpl)shoppingOrderItem;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(shoppingOrderItem);

				shoppingOrderItem.setNew(false);
			}
			else {
				shoppingOrderItem = (ShoppingOrderItem)session.merge(
					shoppingOrderItem);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ShoppingOrderItemModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				shoppingOrderItemModelImpl.getOrderId()
			};

			finderCache.removeResult(_finderPathCountByOrderId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByOrderId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((shoppingOrderItemModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByOrderId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					shoppingOrderItemModelImpl.getOriginalOrderId()
				};

				finderCache.removeResult(_finderPathCountByOrderId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOrderId, args);

				args = new Object[] {shoppingOrderItemModelImpl.getOrderId()};

				finderCache.removeResult(_finderPathCountByOrderId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOrderId, args);
			}
		}

		entityCache.putResult(
			ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemImpl.class, shoppingOrderItem.getPrimaryKey(),
			shoppingOrderItem, false);

		shoppingOrderItem.resetOriginalValues();

		return shoppingOrderItem;
	}

	/**
	 * Returns the shopping order item with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the shopping order item
	 * @return the shopping order item
	 * @throws NoSuchOrderItemException if a shopping order item with the primary key could not be found
	 */
	@Override
	public ShoppingOrderItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOrderItemException {

		ShoppingOrderItem shoppingOrderItem = fetchByPrimaryKey(primaryKey);

		if (shoppingOrderItem == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOrderItemException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return shoppingOrderItem;
	}

	/**
	 * Returns the shopping order item with the primary key or throws a <code>NoSuchOrderItemException</code> if it could not be found.
	 *
	 * @param orderItemId the primary key of the shopping order item
	 * @return the shopping order item
	 * @throws NoSuchOrderItemException if a shopping order item with the primary key could not be found
	 */
	@Override
	public ShoppingOrderItem findByPrimaryKey(long orderItemId)
		throws NoSuchOrderItemException {

		return findByPrimaryKey((Serializable)orderItemId);
	}

	/**
	 * Returns the shopping order item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the shopping order item
	 * @return the shopping order item, or <code>null</code> if a shopping order item with the primary key could not be found
	 */
	@Override
	public ShoppingOrderItem fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ShoppingOrderItem shoppingOrderItem = (ShoppingOrderItem)serializable;

		if (shoppingOrderItem == null) {
			Session session = null;

			try {
				session = openSession();

				shoppingOrderItem = (ShoppingOrderItem)session.get(
					ShoppingOrderItemImpl.class, primaryKey);

				if (shoppingOrderItem != null) {
					cacheResult(shoppingOrderItem);
				}
				else {
					entityCache.putResult(
						ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
						ShoppingOrderItemImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
					ShoppingOrderItemImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return shoppingOrderItem;
	}

	/**
	 * Returns the shopping order item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orderItemId the primary key of the shopping order item
	 * @return the shopping order item, or <code>null</code> if a shopping order item with the primary key could not be found
	 */
	@Override
	public ShoppingOrderItem fetchByPrimaryKey(long orderItemId) {
		return fetchByPrimaryKey((Serializable)orderItemId);
	}

	@Override
	public Map<Serializable, ShoppingOrderItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ShoppingOrderItem> map =
			new HashMap<Serializable, ShoppingOrderItem>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ShoppingOrderItem shoppingOrderItem = fetchByPrimaryKey(primaryKey);

			if (shoppingOrderItem != null) {
				map.put(primaryKey, shoppingOrderItem);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
				ShoppingOrderItemImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ShoppingOrderItem)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_SHOPPINGORDERITEM_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (ShoppingOrderItem shoppingOrderItem :
					(List<ShoppingOrderItem>)query.list()) {

				map.put(
					shoppingOrderItem.getPrimaryKeyObj(), shoppingOrderItem);

				cacheResult(shoppingOrderItem);

				uncachedPrimaryKeys.remove(
					shoppingOrderItem.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
					ShoppingOrderItemImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the shopping order items.
	 *
	 * @return the shopping order items
	 */
	@Override
	public List<ShoppingOrderItem> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the shopping order items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ShoppingOrderItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of shopping order items
	 * @param end the upper bound of the range of shopping order items (not inclusive)
	 * @return the range of shopping order items
	 */
	@Override
	public List<ShoppingOrderItem> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the shopping order items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ShoppingOrderItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of shopping order items
	 * @param end the upper bound of the range of shopping order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of shopping order items
	 */
	@Override
	public List<ShoppingOrderItem> findAll(
		int start, int end,
		OrderByComparator<ShoppingOrderItem> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the shopping order items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ShoppingOrderItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of shopping order items
	 * @param end the upper bound of the range of shopping order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of shopping order items
	 */
	@Override
	public List<ShoppingOrderItem> findAll(
		int start, int end,
		OrderByComparator<ShoppingOrderItem> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<ShoppingOrderItem> list = null;

		if (useFinderCache) {
			list = (List<ShoppingOrderItem>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SHOPPINGORDERITEM);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SHOPPINGORDERITEM;

				sql = sql.concat(ShoppingOrderItemModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ShoppingOrderItem>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the shopping order items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ShoppingOrderItem shoppingOrderItem : findAll()) {
			remove(shoppingOrderItem);
		}
	}

	/**
	 * Returns the number of shopping order items.
	 *
	 * @return the number of shopping order items
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SHOPPINGORDERITEM);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ShoppingOrderItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the shopping order item persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemModelImpl.FINDER_CACHE_ENABLED,
			ShoppingOrderItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemModelImpl.FINDER_CACHE_ENABLED,
			ShoppingOrderItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByOrderId = new FinderPath(
			ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemModelImpl.FINDER_CACHE_ENABLED,
			ShoppingOrderItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByOrderId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByOrderId = new FinderPath(
			ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemModelImpl.FINDER_CACHE_ENABLED,
			ShoppingOrderItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOrderId",
			new String[] {Long.class.getName()},
			ShoppingOrderItemModelImpl.ORDERID_COLUMN_BITMASK |
			ShoppingOrderItemModelImpl.NAME_COLUMN_BITMASK |
			ShoppingOrderItemModelImpl.DESCRIPTION_COLUMN_BITMASK);

		_finderPathCountByOrderId = new FinderPath(
			ShoppingOrderItemModelImpl.ENTITY_CACHE_ENABLED,
			ShoppingOrderItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOrderId",
			new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(ShoppingOrderItemImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SHOPPINGORDERITEM =
		"SELECT shoppingOrderItem FROM ShoppingOrderItem shoppingOrderItem";

	private static final String _SQL_SELECT_SHOPPINGORDERITEM_WHERE_PKS_IN =
		"SELECT shoppingOrderItem FROM ShoppingOrderItem shoppingOrderItem WHERE orderItemId IN (";

	private static final String _SQL_SELECT_SHOPPINGORDERITEM_WHERE =
		"SELECT shoppingOrderItem FROM ShoppingOrderItem shoppingOrderItem WHERE ";

	private static final String _SQL_COUNT_SHOPPINGORDERITEM =
		"SELECT COUNT(shoppingOrderItem) FROM ShoppingOrderItem shoppingOrderItem";

	private static final String _SQL_COUNT_SHOPPINGORDERITEM_WHERE =
		"SELECT COUNT(shoppingOrderItem) FROM ShoppingOrderItem shoppingOrderItem WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "shoppingOrderItem.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ShoppingOrderItem exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ShoppingOrderItem exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ShoppingOrderItemPersistenceImpl.class);

}