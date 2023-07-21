/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.util.PropsValues;
import com.liferay.shopping.model.ShoppingCategory;
import com.liferay.shopping.model.ShoppingCategoryConstants;
import com.liferay.shopping.model.ShoppingItem;
import com.liferay.shopping.service.ShoppingItemLocalServiceUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.shopping.model.ShoppingItem",
	service = ShoppingItemPermission.class
)
public class ShoppingItemPermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long itemId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, itemId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ShoppingItem.class.getName(), itemId,
				actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, ShoppingItem item,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, item, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ShoppingItem.class.getName(),
				item.getItemId(), actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long itemId, String actionId)
		throws PortalException {

		ShoppingItem item = ShoppingItemLocalServiceUtil.getItem(itemId);

		return contains(permissionChecker, item, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, ShoppingItem item,
			String actionId)
		throws PortalException {

		if (actionId.equals(ActionKeys.VIEW) &&
			PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {

			if (item.getCategoryId() ==
					ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

				if (!ShoppingPermission.contains(
						permissionChecker, item.getGroupId(), actionId)) {

					return false;
				}
			}
			else {
				ShoppingCategory category = item.getCategory();

				if (!_shoppingCategoryPermission.contains(
						permissionChecker, category, actionId)) {

					return false;
				}
			}
		}

		if (permissionChecker.hasOwnerPermission(
				item.getCompanyId(), ShoppingItem.class.getName(),
				item.getItemId(), item.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			item.getGroupId(), ShoppingItem.class.getName(), item.getItemId(),
			actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setShoppingCategoryPermission(
		ShoppingCategoryPermission shoppingCategoryPermission) {

		_shoppingCategoryPermission = shoppingCategoryPermission;
	}

	private static ShoppingCategoryPermission _shoppingCategoryPermission;

}