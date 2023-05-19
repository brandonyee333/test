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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.shopping.model.ShoppingItem;

import java.util.List;

/**
 * Provides the remote service utility for ShoppingItem. This utility wraps
 * <code>com.liferay.shopping.service.impl.ShoppingItemServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemService
 * @generated
 */
public class ShoppingItemServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.shopping.service.impl.ShoppingItemServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ShoppingItem addItem(
			long groupId, long categoryId, String sku, String name,
			String description, String properties, String fieldsQuantities,
			boolean requiresShipping, int stockQuantity, boolean featured,
			Boolean sale, boolean smallImage, String smallImageURL,
			java.io.File smallFile, boolean mediumImage, String mediumImageURL,
			java.io.File mediumFile, boolean largeImage, String largeImageURL,
			java.io.File largeFile,
			List<com.liferay.shopping.model.ShoppingItemField> itemFields,
			List<com.liferay.shopping.model.ShoppingItemPrice> itemPrices,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addItem(
			groupId, categoryId, sku, name, description, properties,
			fieldsQuantities, requiresShipping, stockQuantity, featured, sale,
			smallImage, smallImageURL, smallFile, mediumImage, mediumImageURL,
			mediumFile, largeImage, largeImageURL, largeFile, itemFields,
			itemPrices, serviceContext);
	}

	public static void deleteItem(long itemId) throws PortalException {
		getService().deleteItem(itemId);
	}

	public static int getCategoriesItemsCount(
		long groupId, List<Long> categoryIds) {

		return getService().getCategoriesItemsCount(groupId, categoryIds);
	}

	public static ShoppingItem getItem(long itemId) throws PortalException {
		return getService().getItem(itemId);
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

	public static ShoppingItem updateItem(
			long itemId, long groupId, long categoryId, String sku, String name,
			String description, String properties, String fieldsQuantities,
			boolean requiresShipping, int stockQuantity, boolean featured,
			Boolean sale, boolean smallImage, String smallImageURL,
			java.io.File smallFile, boolean mediumImage, String mediumImageURL,
			java.io.File mediumFile, boolean largeImage, String largeImageURL,
			java.io.File largeFile,
			List<com.liferay.shopping.model.ShoppingItemField> itemFields,
			List<com.liferay.shopping.model.ShoppingItemPrice> itemPrices,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateItem(
			itemId, groupId, categoryId, sku, name, description, properties,
			fieldsQuantities, requiresShipping, stockQuantity, featured, sale,
			smallImage, smallImageURL, smallFile, mediumImage, mediumImageURL,
			mediumFile, largeImage, largeImageURL, largeFile, itemFields,
			itemPrices, serviceContext);
	}

	public static ShoppingItemService getService() {
		return _service;
	}

	public static void setService(ShoppingItemService service) {
		_service = service;
	}

	private static volatile ShoppingItemService _service;

}