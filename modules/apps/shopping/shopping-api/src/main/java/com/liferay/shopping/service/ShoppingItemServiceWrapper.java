/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ShoppingItemService}.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemService
 * @generated
 */
public class ShoppingItemServiceWrapper
	implements ServiceWrapper<ShoppingItemService>, ShoppingItemService {

	public ShoppingItemServiceWrapper(ShoppingItemService shoppingItemService) {
		_shoppingItemService = shoppingItemService;
	}

	@Override
	public com.liferay.shopping.model.ShoppingItem addItem(
			long groupId, long categoryId, String sku, String name,
			String description, String properties, String fieldsQuantities,
			boolean requiresShipping, int stockQuantity, boolean featured,
			Boolean sale, boolean smallImage, String smallImageURL,
			java.io.File smallFile, boolean mediumImage, String mediumImageURL,
			java.io.File mediumFile, boolean largeImage, String largeImageURL,
			java.io.File largeFile,
			java.util.List<com.liferay.shopping.model.ShoppingItemField>
				itemFields,
			java.util.List<com.liferay.shopping.model.ShoppingItemPrice>
				itemPrices,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _shoppingItemService.addItem(
			groupId, categoryId, sku, name, description, properties,
			fieldsQuantities, requiresShipping, stockQuantity, featured, sale,
			smallImage, smallImageURL, smallFile, mediumImage, mediumImageURL,
			mediumFile, largeImage, largeImageURL, largeFile, itemFields,
			itemPrices, serviceContext);
	}

	@Override
	public void deleteItem(long itemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_shoppingItemService.deleteItem(itemId);
	}

	@Override
	public int getCategoriesItemsCount(
		long groupId, java.util.List<Long> categoryIds) {

		return _shoppingItemService.getCategoriesItemsCount(
			groupId, categoryIds);
	}

	@Override
	public com.liferay.shopping.model.ShoppingItem getItem(long itemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _shoppingItemService.getItem(itemId);
	}

	@Override
	public java.util.List<com.liferay.shopping.model.ShoppingItem> getItems(
		long groupId, long categoryId) {

		return _shoppingItemService.getItems(groupId, categoryId);
	}

	@Override
	public java.util.List<com.liferay.shopping.model.ShoppingItem> getItems(
		long groupId, long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.shopping.model.ShoppingItem> obc) {

		return _shoppingItemService.getItems(
			groupId, categoryId, start, end, obc);
	}

	@Override
	public int getItemsCount(long groupId, long categoryId) {
		return _shoppingItemService.getItemsCount(groupId, categoryId);
	}

	@Override
	public com.liferay.shopping.model.ShoppingItem[] getItemsPrevAndNext(
			long itemId,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.shopping.model.ShoppingItem> obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _shoppingItemService.getItemsPrevAndNext(itemId, obc);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _shoppingItemService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.shopping.model.ShoppingItem updateItem(
			long itemId, long groupId, long categoryId, String sku, String name,
			String description, String properties, String fieldsQuantities,
			boolean requiresShipping, int stockQuantity, boolean featured,
			Boolean sale, boolean smallImage, String smallImageURL,
			java.io.File smallFile, boolean mediumImage, String mediumImageURL,
			java.io.File mediumFile, boolean largeImage, String largeImageURL,
			java.io.File largeFile,
			java.util.List<com.liferay.shopping.model.ShoppingItemField>
				itemFields,
			java.util.List<com.liferay.shopping.model.ShoppingItemPrice>
				itemPrices,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _shoppingItemService.updateItem(
			itemId, groupId, categoryId, sku, name, description, properties,
			fieldsQuantities, requiresShipping, stockQuantity, featured, sale,
			smallImage, smallImageURL, smallFile, mediumImage, mediumImageURL,
			mediumFile, largeImage, largeImageURL, largeFile, itemFields,
			itemPrices, serviceContext);
	}

	@Override
	public ShoppingItemService getWrappedService() {
		return _shoppingItemService;
	}

	@Override
	public void setWrappedService(ShoppingItemService shoppingItemService) {
		_shoppingItemService = shoppingItemService;
	}

	private ShoppingItemService _shoppingItemService;

}