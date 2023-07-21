/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.internal.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.shopping.service.ShoppingCartLocalService;
import com.liferay.shopping.service.ShoppingCategoryLocalService;
import com.liferay.shopping.service.ShoppingCouponLocalService;
import com.liferay.shopping.service.ShoppingOrderLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Peter Fellwock
 */
@Component(immediate = true, service = ModelListener.class)
public class GroupModelListener extends BaseModelListener<Group> {

	@Override
	public void onAfterRemove(Group group) throws ModelListenerException {
		try {
			_shoppingCartLocalService.deleteGroupCarts(group.getGroupId());

			_shoppingCategoryLocalService.deleteCategories(group.getGroupId());

			_shoppingCouponLocalService.deleteCoupons(group.getGroupId());

			_shoppingOrderLocalService.deleteOrders(group.getGroupId());
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Reference(unbind = "-")
	protected void setShoppingCartLocalService(
		ShoppingCartLocalService shoppingCartLocalService) {

		_shoppingCartLocalService = shoppingCartLocalService;
	}

	@Reference(unbind = "-")
	protected void setShoppingCategoryLocalService(
		ShoppingCategoryLocalService shoppingCategoryLocalService) {

		_shoppingCategoryLocalService = shoppingCategoryLocalService;
	}

	@Reference(unbind = "-")
	protected void setShoppingCouponLocalService(
		ShoppingCouponLocalService shoppingCouponLocalService) {

		_shoppingCouponLocalService = shoppingCouponLocalService;
	}

	@Reference(unbind = "-")
	protected void setShoppingOrderLocalService(
		ShoppingOrderLocalService shoppingOrderLocalService) {

		_shoppingOrderLocalService = shoppingOrderLocalService;
	}

	private ShoppingCartLocalService _shoppingCartLocalService;
	private ShoppingCategoryLocalService _shoppingCategoryLocalService;
	private ShoppingCouponLocalService _shoppingCouponLocalService;
	private ShoppingOrderLocalService _shoppingOrderLocalService;

}