/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.shopping.model.ShoppingOrder;

/**
 * Provides the remote service utility for ShoppingOrder. This utility wraps
 * <code>com.liferay.shopping.service.impl.ShoppingOrderServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingOrderService
 * @generated
 */
public class ShoppingOrderServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.shopping.service.impl.ShoppingOrderServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void completeOrder(
			long groupId, String number, String ppTxnId, String ppPaymentStatus,
			double ppPaymentGross, String ppReceiverEmail, String ppPayerEmail,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().completeOrder(
			groupId, number, ppTxnId, ppPaymentStatus, ppPaymentGross,
			ppReceiverEmail, ppPayerEmail, serviceContext);
	}

	public static void deleteOrder(long groupId, long orderId)
		throws PortalException {

		getService().deleteOrder(groupId, orderId);
	}

	public static ShoppingOrder getOrder(long groupId, long orderId)
		throws PortalException {

		return getService().getOrder(groupId, orderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void sendEmail(
			long groupId, long orderId, String emailType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().sendEmail(groupId, orderId, emailType, serviceContext);
	}

	public static ShoppingOrder updateOrder(
			long groupId, long orderId, String ppTxnId, String ppPaymentStatus,
			double ppPaymentGross, String ppReceiverEmail, String ppPayerEmail)
		throws PortalException {

		return getService().updateOrder(
			groupId, orderId, ppTxnId, ppPaymentStatus, ppPaymentGross,
			ppReceiverEmail, ppPayerEmail);
	}

	public static ShoppingOrder updateOrder(
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

		return getService().updateOrder(
			groupId, orderId, billingFirstName, billingLastName,
			billingEmailAddress, billingCompany, billingStreet, billingCity,
			billingState, billingZip, billingCountry, billingPhone,
			shipToBilling, shippingFirstName, shippingLastName,
			shippingEmailAddress, shippingCompany, shippingStreet, shippingCity,
			shippingState, shippingZip, shippingCountry, shippingPhone, ccName,
			ccType, ccNumber, ccExpMonth, ccExpYear, ccVerNumber, comments);
	}

	public static ShoppingOrderService getService() {
		return _service;
	}

	public static void setService(ShoppingOrderService service) {
		_service = service;
	}

	private static volatile ShoppingOrderService _service;

}