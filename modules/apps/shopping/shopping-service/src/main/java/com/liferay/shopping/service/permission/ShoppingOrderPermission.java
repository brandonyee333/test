/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.shopping.model.ShoppingOrder;
import com.liferay.shopping.service.ShoppingOrderLocalServiceUtil;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.shopping.model.ShoppingOrder",
	service = ShoppingOrderPermission.class
)
public class ShoppingOrderPermission {

	public static void check(
			PermissionChecker permissionChecker, long groupId, long orderId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, orderId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ShoppingOrder.class.getName(), orderId,
				actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId,
			ShoppingOrder order, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, order, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ShoppingOrder.class.getName(),
				order.getOrderId(), actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long orderId,
			String actionId)
		throws PortalException {

		ShoppingOrder order = ShoppingOrderLocalServiceUtil.getOrder(orderId);

		return contains(permissionChecker, groupId, order, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long groupId, ShoppingOrder order,
		String actionId) {

		if (ShoppingPermission.contains(
				permissionChecker, groupId, ActionKeys.MANAGE_ORDERS)) {

			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				order.getCompanyId(), ShoppingOrder.class.getName(),
				order.getOrderId(), order.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			order.getGroupId(), ShoppingOrder.class.getName(),
			order.getOrderId(), actionId);
	}

}