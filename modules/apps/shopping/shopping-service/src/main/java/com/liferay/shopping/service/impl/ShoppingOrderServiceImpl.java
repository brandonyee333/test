/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.shopping.model.ShoppingOrder;
import com.liferay.shopping.service.base.ShoppingOrderServiceBaseImpl;
import com.liferay.shopping.service.permission.ShoppingOrderPermission;
import com.liferay.shopping.service.permission.ShoppingPermission;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingOrderServiceImpl extends ShoppingOrderServiceBaseImpl {

	@Override
	public void completeOrder(
			long groupId, String number, String ppTxnId, String ppPaymentStatus,
			double ppPaymentGross, String ppReceiverEmail, String ppPayerEmail,
			ServiceContext serviceContext)
		throws PortalException {

		ShoppingOrder order = shoppingOrderPersistence.findByNumber(number);

		ShoppingOrderPermission.check(
			getPermissionChecker(), groupId, order.getOrderId(),
			ActionKeys.UPDATE);

		shoppingOrderLocalService.completeOrder(
			number, ppTxnId, ppPaymentStatus, ppPaymentGross, ppReceiverEmail,
			ppPayerEmail, false, serviceContext);
	}

	@Override
	public void deleteOrder(long groupId, long orderId) throws PortalException {
		ShoppingOrderPermission.check(
			getPermissionChecker(), groupId, orderId, ActionKeys.DELETE);

		shoppingOrderLocalService.deleteOrder(orderId);
	}

	@Override
	public ShoppingOrder getOrder(long groupId, long orderId)
		throws PortalException {

		ShoppingOrder order = shoppingOrderLocalService.getOrder(orderId);

		if (order.getUserId() == getUserId()) {
			return order;
		}

		ShoppingPermission.check(
			getPermissionChecker(), groupId, ActionKeys.MANAGE_ORDERS);

		return order;
	}

	@Override
	public void sendEmail(
			long groupId, long orderId, String emailType,
			ServiceContext serviceContext)
		throws PortalException {

		ShoppingOrderPermission.check(
			getPermissionChecker(), groupId, orderId, ActionKeys.UPDATE);

		shoppingOrderLocalService.sendEmail(orderId, emailType, serviceContext);
	}

	@Override
	public ShoppingOrder updateOrder(
			long groupId, long orderId, String ppTxnId, String ppPaymentStatus,
			double ppPaymentGross, String ppReceiverEmail, String ppPayerEmail)
		throws PortalException {

		ShoppingOrderPermission.check(
			getPermissionChecker(), groupId, orderId, ActionKeys.UPDATE);

		return shoppingOrderLocalService.updateOrder(
			orderId, ppTxnId, ppPaymentStatus, ppPaymentGross, ppReceiverEmail,
			ppPayerEmail);
	}

	@Override
	public ShoppingOrder updateOrder(
			long groupId, long orderId, String billingFirstName,
			String billingLastName, String billingEmailAddress,
			String billingCompany, String billingStreet, String billingCity,
			String billingState, String billingZip, String billingCountry,
			String billingPhone, boolean shipToBilling,
			String shippingFirstName, String shippingLastName,
			String shippingEmailAddress, String shippingCompany,
			String shippingStreet, String shippingCity, String shippingState,
			String shippingZip, String shippingCountry, String shippingPhone,
			String ccName, String ccType, String ccNumber, int ccExpMonth,
			int ccExpYear, String ccVerNumber, String comments)
		throws PortalException {

		ShoppingOrderPermission.check(
			getPermissionChecker(), groupId, orderId, ActionKeys.UPDATE);

		return shoppingOrderLocalService.updateOrder(
			orderId, billingFirstName, billingLastName, billingEmailAddress,
			billingCompany, billingStreet, billingCity, billingState,
			billingZip, billingCountry, billingPhone, shipToBilling,
			shippingFirstName, shippingLastName, shippingEmailAddress,
			shippingCompany, shippingStreet, shippingCity, shippingState,
			shippingZip, shippingCountry, shippingPhone, ccName, ccType,
			ccNumber, ccExpMonth, ccExpYear, ccVerNumber, comments);
	}

}