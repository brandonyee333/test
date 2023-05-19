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
import com.liferay.shopping.model.ShoppingItem;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ShoppingItem. This utility wraps
 * <code>com.liferay.shopping.service.impl.ShoppingItemLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemLocalService
 * @generated
 */
public class ShoppingItemLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.shopping.service.impl.ShoppingItemLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ShoppingItem addItem(
			long userId, long groupId, long categoryId, String sku, String name,
			String description, String properties, String fieldsQuantities,
			boolean requiresShipping, int stockQuantity, boolean featured,
			Boolean sale, boolean smallImage, String smallImageURL,
			java.io.File smallImageFile, boolean mediumImage,
			String mediumImageURL, java.io.File mediumImageFile,
			boolean largeImage, String largeImageURL,
			java.io.File largeImageFile,
			List<com.liferay.shopping.model.ShoppingItemField> itemFields,
			List<com.liferay.shopping.model.ShoppingItemPrice> itemPrices,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addItem(
			userId, groupId, categoryId, sku, name, description, properties,
			fieldsQuantities, requiresShipping, stockQuantity, featured, sale,
			smallImage, smallImageURL, smallImageFile, mediumImage,
			mediumImageURL, mediumImageFile, largeImage, largeImageURL,
			largeImageFile, itemFields, itemPrices, serviceContext);
	}

	public static void addItemResources(
			long itemId, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		getService().addItemResources(
			itemId, addGroupPermissions, addGuestPermissions);
	}

	public static void addItemResources(
			long itemId,
			com.liferay.portal.kernel.service.permission.ModelPermissions
				modelPermissions)
		throws PortalException {

		getService().addItemResources(itemId, modelPermissions);
	}

	public static void addItemResources(
			ShoppingItem item, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		getService().addItemResources(
			item, addGroupPermissions, addGuestPermissions);
	}

	public static void addItemResources(
			ShoppingItem item,
			com.liferay.portal.kernel.service.permission.ModelPermissions
				modelPermissions)
		throws PortalException {

		getService().addItemResources(item, modelPermissions);
	}

	/**
	 * Adds the shopping item to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingItem the shopping item
	 * @return the shopping item that was added
	 */
	public static ShoppingItem addShoppingItem(ShoppingItem shoppingItem) {
		return getService().addShoppingItem(shoppingItem);
	}

	/**
	 * Creates a new shopping item with the primary key. Does not add the shopping item to the database.
	 *
	 * @param itemId the primary key for the new shopping item
	 * @return the new shopping item
	 */
	public static ShoppingItem createShoppingItem(long itemId) {
		return getService().createShoppingItem(itemId);
	}

	public static void deleteItem(long itemId) throws PortalException {
		getService().deleteItem(itemId);
	}

	public static void deleteItem(ShoppingItem item) throws PortalException {
		getService().deleteItem(item);
	}

	public static void deleteItems(long groupId, long categoryId)
		throws PortalException {

		getService().deleteItems(groupId, categoryId);
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
	 * Deletes the shopping item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param itemId the primary key of the shopping item
	 * @return the shopping item that was removed
	 * @throws PortalException if a shopping item with the primary key could not be found
	 */
	public static ShoppingItem deleteShoppingItem(long itemId)
		throws PortalException {

		return getService().deleteShoppingItem(itemId);
	}

	/**
	 * Deletes the shopping item from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingItem the shopping item
	 * @return the shopping item that was removed
	 */
	public static ShoppingItem deleteShoppingItem(ShoppingItem shoppingItem) {
		return getService().deleteShoppingItem(shoppingItem);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingItemModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingItemModelImpl</code>.
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

	public static ShoppingItem fetchShoppingItem(long itemId) {
		return getService().fetchShoppingItem(itemId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static int getCategoriesItemsCount(
		long groupId, List<Long> categoryIds) {

		return getService().getCategoriesItemsCount(groupId, categoryIds);
	}

	public static List<ShoppingItem> getFeaturedItems(
		long groupId, long categoryId, int numOfItems) {

		return getService().getFeaturedItems(groupId, categoryId, numOfItems);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static ShoppingItem getItem(long itemId) throws PortalException {
		return getService().getItem(itemId);
	}

	public static ShoppingItem getItem(long companyId, String sku)
		throws PortalException {

		return getService().getItem(companyId, sku);
	}

	public static ShoppingItem getItemByLargeImageId(long largeImageId)
		throws PortalException {

		return getService().getItemByLargeImageId(largeImageId);
	}

	public static ShoppingItem getItemByMediumImageId(long mediumImageId)
		throws PortalException {

		return getService().getItemByMediumImageId(mediumImageId);
	}

	public static ShoppingItem getItemBySmallImageId(long smallImageId)
		throws PortalException {

		return getService().getItemBySmallImageId(smallImageId);
	}

	public static List<ShoppingItem> getItems(long groupId, long categoryId) {
		return getService().getItems(groupId, categoryId);
	}

	public static List<ShoppingItem> getItems(
		long groupId, long categoryId, int start, int end,
		OrderByComparator<ShoppingItem> obc) {

		return getService().getItems(groupId, categoryId, start, end, obc);
	}

	public static int getItemsCount(long groupId, long categoryId) {
		return getService().getItemsCount(groupId, categoryId);
	}

	public static ShoppingItem[] getItemsPrevAndNext(
			long itemId, OrderByComparator<ShoppingItem> obc)
		throws PortalException {

		return getService().getItemsPrevAndNext(itemId, obc);
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

	public static List<ShoppingItem> getSaleItems(
		long groupId, long categoryId, int numOfItems) {

		return getService().getSaleItems(groupId, categoryId, numOfItems);
	}

	/**
	 * Returns the shopping item with the primary key.
	 *
	 * @param itemId the primary key of the shopping item
	 * @return the shopping item
	 * @throws PortalException if a shopping item with the primary key could not be found
	 */
	public static ShoppingItem getShoppingItem(long itemId)
		throws PortalException {

		return getService().getShoppingItem(itemId);
	}

	/**
	 * Returns a range of all the shopping items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.shopping.model.impl.ShoppingItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of shopping items
	 * @param end the upper bound of the range of shopping items (not inclusive)
	 * @return the range of shopping items
	 */
	public static List<ShoppingItem> getShoppingItems(int start, int end) {
		return getService().getShoppingItems(start, end);
	}

	/**
	 * Returns the number of shopping items.
	 *
	 * @return the number of shopping items
	 */
	public static int getShoppingItemsCount() {
		return getService().getShoppingItemsCount();
	}

	public static List<ShoppingItem> search(
		long groupId, long[] categoryIds, String keywords, int start, int end) {

		return getService().search(groupId, categoryIds, keywords, start, end);
	}

	public static List<ShoppingItem> search(
		long groupId, long[] categoryIds, String keywords, int start, int end,
		OrderByComparator<ShoppingItem> obc) {

		return getService().search(
			groupId, categoryIds, keywords, start, end, obc);
	}

	public static int searchCount(
		long groupId, long[] categoryIds, String keywords) {

		return getService().searchCount(groupId, categoryIds, keywords);
	}

	public static int searchCount(
		long groupId, long[] categoryIds, String keywords,
		OrderByComparator<ShoppingItem> obc) {

		return getService().searchCount(groupId, categoryIds, keywords, obc);
	}

	public static ShoppingItem updateItem(
			long userId, long itemId, long groupId, long categoryId, String sku,
			String name, String description, String properties,
			String fieldsQuantities, boolean requiresShipping,
			int stockQuantity, boolean featured, Boolean sale,
			boolean smallImage, String smallImageURL,
			java.io.File smallImageFile, boolean mediumImage,
			String mediumImageURL, java.io.File mediumImageFile,
			boolean largeImage, String largeImageURL,
			java.io.File largeImageFile,
			List<com.liferay.shopping.model.ShoppingItemField> itemFields,
			List<com.liferay.shopping.model.ShoppingItemPrice> itemPrices,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateItem(
			userId, itemId, groupId, categoryId, sku, name, description,
			properties, fieldsQuantities, requiresShipping, stockQuantity,
			featured, sale, smallImage, smallImageURL, smallImageFile,
			mediumImage, mediumImageURL, mediumImageFile, largeImage,
			largeImageURL, largeImageFile, itemFields, itemPrices,
			serviceContext);
	}

	/**
	 * Updates the shopping item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ShoppingItemLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param shoppingItem the shopping item
	 * @return the shopping item that was updated
	 */
	public static ShoppingItem updateShoppingItem(ShoppingItem shoppingItem) {
		return getService().updateShoppingItem(shoppingItem);
	}

	public static ShoppingItemLocalService getService() {
		return _service;
	}

	public static void setService(ShoppingItemLocalService service) {
		_service = service;
	}

	private static volatile ShoppingItemLocalService _service;

}