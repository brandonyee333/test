/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.shopping.model.ShoppingItem;
import com.liferay.shopping.model.ShoppingItemField;
import com.liferay.shopping.model.ShoppingItemPrice;
import com.liferay.shopping.service.base.ShoppingItemServiceBaseImpl;
import com.liferay.shopping.service.permission.ShoppingCategoryPermission;
import com.liferay.shopping.service.permission.ShoppingItemPermission;

import java.io.File;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingItemServiceImpl extends ShoppingItemServiceBaseImpl {

	@Override
	public ShoppingItem addItem(
			long groupId, long categoryId, String sku, String name,
			String description, String properties, String fieldsQuantities,
			boolean requiresShipping, int stockQuantity, boolean featured,
			Boolean sale, boolean smallImage, String smallImageURL,
			File smallFile, boolean mediumImage, String mediumImageURL,
			File mediumFile, boolean largeImage, String largeImageURL,
			File largeFile, List<ShoppingItemField> itemFields,
			List<ShoppingItemPrice> itemPrices, ServiceContext serviceContext)
		throws PortalException {

		shoppingCategoryPermission.check(
			getPermissionChecker(), groupId, categoryId, ActionKeys.ADD_ITEM);

		return shoppingItemLocalService.addItem(
			getUserId(), groupId, categoryId, sku, name, description,
			properties, fieldsQuantities, requiresShipping, stockQuantity,
			featured, sale, smallImage, smallImageURL, smallFile, mediumImage,
			mediumImageURL, mediumFile, largeImage, largeImageURL, largeFile,
			itemFields, itemPrices, serviceContext);
	}

	@Override
	public void deleteItem(long itemId) throws PortalException {
		shoppingItemPermission.check(
			getPermissionChecker(), itemId, ActionKeys.DELETE);

		shoppingItemLocalService.deleteItem(itemId);
	}

	@Override
	public int getCategoriesItemsCount(long groupId, List<Long> categoryIds) {
		return shoppingItemFinder.filterCountByG_C(groupId, categoryIds);
	}

	@Override
	public ShoppingItem getItem(long itemId) throws PortalException {
		shoppingItemPermission.check(
			getPermissionChecker(), itemId, ActionKeys.VIEW);

		return shoppingItemLocalService.getItem(itemId);
	}

	@Override
	public List<ShoppingItem> getItems(long groupId, long categoryId) {
		return shoppingItemPersistence.filterFindByG_C(groupId, categoryId);
	}

	@Override
	public List<ShoppingItem> getItems(
		long groupId, long categoryId, int start, int end,
		OrderByComparator<ShoppingItem> obc) {

		return shoppingItemPersistence.filterFindByG_C(
			groupId, categoryId, start, end, obc);
	}

	@Override
	public int getItemsCount(long groupId, long categoryId) {
		return shoppingItemPersistence.filterCountByG_C(groupId, categoryId);
	}

	@Override
	public ShoppingItem[] getItemsPrevAndNext(
			long itemId, OrderByComparator<ShoppingItem> obc)
		throws PortalException {

		ShoppingItem item = shoppingItemPersistence.findByPrimaryKey(itemId);

		return shoppingItemPersistence.filterFindByG_C_PrevAndNext(
			item.getItemId(), item.getGroupId(), item.getCategoryId(), obc);
	}

	@Override
	public ShoppingItem updateItem(
			long itemId, long groupId, long categoryId, String sku, String name,
			String description, String properties, String fieldsQuantities,
			boolean requiresShipping, int stockQuantity, boolean featured,
			Boolean sale, boolean smallImage, String smallImageURL,
			File smallFile, boolean mediumImage, String mediumImageURL,
			File mediumFile, boolean largeImage, String largeImageURL,
			File largeFile, List<ShoppingItemField> itemFields,
			List<ShoppingItemPrice> itemPrices, ServiceContext serviceContext)
		throws PortalException {

		shoppingItemPermission.check(
			getPermissionChecker(), itemId, ActionKeys.UPDATE);

		return shoppingItemLocalService.updateItem(
			getUserId(), itemId, groupId, categoryId, sku, name, description,
			properties, fieldsQuantities, requiresShipping, stockQuantity,
			featured, sale, smallImage, smallImageURL, smallFile, mediumImage,
			mediumImageURL, mediumFile, largeImage, largeImageURL, largeFile,
			itemFields, itemPrices, serviceContext);
	}

	@ServiceReference(type = ShoppingCategoryPermission.class)
	protected ShoppingCategoryPermission shoppingCategoryPermission;

	@ServiceReference(type = ShoppingItemPermission.class)
	protected ShoppingItemPermission shoppingItemPermission;

}