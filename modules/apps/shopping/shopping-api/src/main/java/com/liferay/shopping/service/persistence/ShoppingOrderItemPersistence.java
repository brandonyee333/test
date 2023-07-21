/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.shopping.exception.NoSuchOrderItemException;
import com.liferay.shopping.model.ShoppingOrderItem;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the shopping order item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingOrderItemUtil
 * @generated
 */
@ProviderType
public interface ShoppingOrderItemPersistence
	extends BasePersistence<ShoppingOrderItem> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ShoppingOrderItemUtil} to access the shopping order item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, ShoppingOrderItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the shopping order items where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @return the matching shopping order items
	 */
	public java.util.List<ShoppingOrderItem> findByOrderId(long orderId);

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
	public java.util.List<ShoppingOrderItem> findByOrderId(
		long orderId, int start, int end);

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
	public java.util.List<ShoppingOrderItem> findByOrderId(
		long orderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ShoppingOrderItem>
			orderByComparator);

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
	public java.util.List<ShoppingOrderItem> findByOrderId(
		long orderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ShoppingOrderItem>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first shopping order item in the ordered set where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching shopping order item
	 * @throws NoSuchOrderItemException if a matching shopping order item could not be found
	 */
	public ShoppingOrderItem findByOrderId_First(
			long orderId,
			com.liferay.portal.kernel.util.OrderByComparator<ShoppingOrderItem>
				orderByComparator)
		throws NoSuchOrderItemException;

	/**
	 * Returns the first shopping order item in the ordered set where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching shopping order item, or <code>null</code> if a matching shopping order item could not be found
	 */
	public ShoppingOrderItem fetchByOrderId_First(
		long orderId,
		com.liferay.portal.kernel.util.OrderByComparator<ShoppingOrderItem>
			orderByComparator);

	/**
	 * Returns the last shopping order item in the ordered set where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching shopping order item
	 * @throws NoSuchOrderItemException if a matching shopping order item could not be found
	 */
	public ShoppingOrderItem findByOrderId_Last(
			long orderId,
			com.liferay.portal.kernel.util.OrderByComparator<ShoppingOrderItem>
				orderByComparator)
		throws NoSuchOrderItemException;

	/**
	 * Returns the last shopping order item in the ordered set where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching shopping order item, or <code>null</code> if a matching shopping order item could not be found
	 */
	public ShoppingOrderItem fetchByOrderId_Last(
		long orderId,
		com.liferay.portal.kernel.util.OrderByComparator<ShoppingOrderItem>
			orderByComparator);

	/**
	 * Returns the shopping order items before and after the current shopping order item in the ordered set where orderId = &#63;.
	 *
	 * @param orderItemId the primary key of the current shopping order item
	 * @param orderId the order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next shopping order item
	 * @throws NoSuchOrderItemException if a shopping order item with the primary key could not be found
	 */
	public ShoppingOrderItem[] findByOrderId_PrevAndNext(
			long orderItemId, long orderId,
			com.liferay.portal.kernel.util.OrderByComparator<ShoppingOrderItem>
				orderByComparator)
		throws NoSuchOrderItemException;

	/**
	 * Removes all the shopping order items where orderId = &#63; from the database.
	 *
	 * @param orderId the order ID
	 */
	public void removeByOrderId(long orderId);

	/**
	 * Returns the number of shopping order items where orderId = &#63;.
	 *
	 * @param orderId the order ID
	 * @return the number of matching shopping order items
	 */
	public int countByOrderId(long orderId);

	/**
	 * Caches the shopping order item in the entity cache if it is enabled.
	 *
	 * @param shoppingOrderItem the shopping order item
	 */
	public void cacheResult(ShoppingOrderItem shoppingOrderItem);

	/**
	 * Caches the shopping order items in the entity cache if it is enabled.
	 *
	 * @param shoppingOrderItems the shopping order items
	 */
	public void cacheResult(
		java.util.List<ShoppingOrderItem> shoppingOrderItems);

	/**
	 * Creates a new shopping order item with the primary key. Does not add the shopping order item to the database.
	 *
	 * @param orderItemId the primary key for the new shopping order item
	 * @return the new shopping order item
	 */
	public ShoppingOrderItem create(long orderItemId);

	/**
	 * Removes the shopping order item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orderItemId the primary key of the shopping order item
	 * @return the shopping order item that was removed
	 * @throws NoSuchOrderItemException if a shopping order item with the primary key could not be found
	 */
	public ShoppingOrderItem remove(long orderItemId)
		throws NoSuchOrderItemException;

	public ShoppingOrderItem updateImpl(ShoppingOrderItem shoppingOrderItem);

	/**
	 * Returns the shopping order item with the primary key or throws a <code>NoSuchOrderItemException</code> if it could not be found.
	 *
	 * @param orderItemId the primary key of the shopping order item
	 * @return the shopping order item
	 * @throws NoSuchOrderItemException if a shopping order item with the primary key could not be found
	 */
	public ShoppingOrderItem findByPrimaryKey(long orderItemId)
		throws NoSuchOrderItemException;

	/**
	 * Returns the shopping order item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orderItemId the primary key of the shopping order item
	 * @return the shopping order item, or <code>null</code> if a shopping order item with the primary key could not be found
	 */
	public ShoppingOrderItem fetchByPrimaryKey(long orderItemId);

	/**
	 * Returns all the shopping order items.
	 *
	 * @return the shopping order items
	 */
	public java.util.List<ShoppingOrderItem> findAll();

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
	public java.util.List<ShoppingOrderItem> findAll(int start, int end);

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
	public java.util.List<ShoppingOrderItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ShoppingOrderItem>
			orderByComparator);

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
	public java.util.List<ShoppingOrderItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ShoppingOrderItem>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the shopping order items from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of shopping order items.
	 *
	 * @return the number of shopping order items
	 */
	public int countAll();

}