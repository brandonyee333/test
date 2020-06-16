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

package com.liferay.shopping.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.shopping.service.ShoppingCouponServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ShoppingCouponServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.shopping.model.ShoppingCouponSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.shopping.model.ShoppingCoupon</code>, that is translated to a
 * <code>com.liferay.shopping.model.ShoppingCouponSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCouponServiceHttp
 * @generated
 */
public class ShoppingCouponServiceSoap {

	public static com.liferay.shopping.model.ShoppingCouponSoap addCoupon(
			String code, boolean autoCode, String name, String description,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			boolean neverExpire, boolean active, String limitCategories,
			String limitSkus, double minOrder, double discount,
			String discountType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.shopping.model.ShoppingCoupon returnValue =
				ShoppingCouponServiceUtil.addCoupon(
					code, autoCode, name, description, startDateMonth,
					startDateDay, startDateYear, startDateHour, startDateMinute,
					endDateMonth, endDateDay, endDateYear, endDateHour,
					endDateMinute, neverExpire, active, limitCategories,
					limitSkus, minOrder, discount, discountType,
					serviceContext);

			return com.liferay.shopping.model.ShoppingCouponSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void deleteCoupon(long groupId, long couponId)
		throws RemoteException {

		try {
			ShoppingCouponServiceUtil.deleteCoupon(groupId, couponId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.shopping.model.ShoppingCouponSoap getCoupon(
			long groupId, long couponId)
		throws RemoteException {

		try {
			com.liferay.shopping.model.ShoppingCoupon returnValue =
				ShoppingCouponServiceUtil.getCoupon(groupId, couponId);

			return com.liferay.shopping.model.ShoppingCouponSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.shopping.model.ShoppingCouponSoap[] search(
			long groupId, long companyId, String code, boolean active,
			String discountType, boolean andOperator, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.shopping.model.ShoppingCoupon>
				returnValue = ShoppingCouponServiceUtil.search(
					groupId, companyId, code, active, discountType, andOperator,
					start, end);

			return com.liferay.shopping.model.ShoppingCouponSoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.shopping.model.ShoppingCouponSoap updateCoupon(
			long couponId, String name, String description, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute,
			boolean neverExpire, boolean active, String limitCategories,
			String limitSkus, double minOrder, double discount,
			String discountType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.shopping.model.ShoppingCoupon returnValue =
				ShoppingCouponServiceUtil.updateCoupon(
					couponId, name, description, startDateMonth, startDateDay,
					startDateYear, startDateHour, startDateMinute, endDateMonth,
					endDateDay, endDateYear, endDateHour, endDateMinute,
					neverExpire, active, limitCategories, limitSkus, minOrder,
					discount, discountType, serviceContext);

			return com.liferay.shopping.model.ShoppingCouponSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ShoppingCouponServiceSoap.class);

}