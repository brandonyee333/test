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

package com.liferay.shopping.service;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.shopping.model.ShoppingCart;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for ShoppingCart. This utility wraps
 * <code>com.liferay.shopping.service.impl.ShoppingCartLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCartLocalService
 * @generated
 */
public class ShoppingCartLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.shopping.service.impl.ShoppingCartLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the shopping cart to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingCartLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingCart the shopping cart
	 * @return the shopping cart that was added
	 */
	public static ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
		return getService().addShoppingCart(shoppingCart);
	}

	/**
	 * Creates a new shopping cart with the primary key. Does not add the shopping cart to the database.
	 *
	 * @param cartId the primary key for the new shopping cart
	 * @return the new shopping cart
	 */
	public static ShoppingCart createShoppingCart(long cartId) {
		return getService().createShoppingCart(cartId);
	}

	public static void deleteGroupCarts(long groupId) {
		getService().deleteGroupCarts(groupId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the shopping cart with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingCartLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cartId the primary key of the shopping cart
	 * @return the shopping cart that was removed
	 * @throws PortalException if a shopping cart with the primary key could not be found
	 */
	public static ShoppingCart deleteShoppingCart(long cartId)
		throws PortalException {

		return getService().deleteShoppingCart(cartId);
	}

	/**
	 * Deletes the shopping cart from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingCartLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingCart the shopping cart
	 * @return the shopping cart that was removed
	 */
	public static ShoppingCart deleteShoppingCart(ShoppingCart shoppingCart) {
		return getService().deleteShoppingCart(shoppingCart);
	}

	public static void deleteUserCarts(long userId) {
		getService().deleteUserCarts(userId);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingCartModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingCartModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static ShoppingCart fetchShoppingCart(long cartId) {
		return getService().fetchShoppingCart(cartId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static ShoppingCart getCart(long userId, long groupId)
		throws PortalException {

		return getService().getCart(userId, groupId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static Map<com.liferay.shopping.model.ShoppingCartItem, Integer>
		getItems(long groupId, String itemIds) {

		return getService().getItems(groupId, itemIds);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the shopping cart with the primary key.
	 *
	 * @param cartId the primary key of the shopping cart
	 * @return the shopping cart
	 * @throws PortalException if a shopping cart with the primary key could not be found
	 */
	public static ShoppingCart getShoppingCart(long cartId)
		throws PortalException {

		return getService().getShoppingCart(cartId);
	}

	/**
	 * Returns a range of all the shopping carts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingCartModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of shopping carts
	 * @param end the upper bound of the range of shopping carts (not inclusive)
	 * @return the range of shopping carts
	 */
	public static List<ShoppingCart> getShoppingCarts(int start, int end) {
		return getService().getShoppingCarts(start, end);
	}

	/**
	 * Returns the number of shopping carts.
	 *
	 * @return the number of shopping carts
	 */
	public static int getShoppingCartsCount() {
		return getService().getShoppingCartsCount();
	}

	public static ShoppingCart updateCart(
			long userId, long groupId, String itemIds, String couponCodes,
			int altShipping, boolean insure)
		throws PortalException {

		return getService().updateCart(
			userId, groupId, itemIds, couponCodes, altShipping, insure);
	}

	/**
	 * Updates the shopping cart in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingCartLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingCart the shopping cart
	 * @return the shopping cart that was updated
	 */
	public static ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
		return getService().updateShoppingCart(shoppingCart);
	}

	public static ShoppingCartLocalService getService() {
		return _service;
	}

	public static void setService(ShoppingCartLocalService service) {
		_service = service;
	}

	private static volatile ShoppingCartLocalService _service;

}