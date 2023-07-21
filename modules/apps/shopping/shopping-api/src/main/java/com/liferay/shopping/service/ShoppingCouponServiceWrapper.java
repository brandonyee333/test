/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ShoppingCouponService}.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCouponService
 * @generated
 */
public class ShoppingCouponServiceWrapper
	implements ServiceWrapper<ShoppingCouponService>, ShoppingCouponService {

	public ShoppingCouponServiceWrapper(
		ShoppingCouponService shoppingCouponService) {

		_shoppingCouponService = shoppingCouponService;
	}

	@Override
	public com.liferay.shopping.model.ShoppingCoupon addCoupon(
			String code, boolean autoCode, String name, String description,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			boolean neverExpire, boolean active, String limitCategories,
			String limitSkus, double minOrder, double discount,
			String discountType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _shoppingCouponService.addCoupon(
			code, autoCode, name, description, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, neverExpire,
			active, limitCategories, limitSkus, minOrder, discount,
			discountType, serviceContext);
	}

	@Override
	public void deleteCoupon(long groupId, long couponId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_shoppingCouponService.deleteCoupon(groupId, couponId);
	}

	@Override
	public com.liferay.shopping.model.ShoppingCoupon getCoupon(
			long groupId, long couponId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _shoppingCouponService.getCoupon(groupId, couponId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _shoppingCouponService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.shopping.model.ShoppingCoupon> search(
			long groupId, long companyId, String code, boolean active,
			String discountType, boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _shoppingCouponService.search(
			groupId, companyId, code, active, discountType, andOperator, start,
			end);
	}

	@Override
	public com.liferay.shopping.model.ShoppingCoupon updateCoupon(
			long couponId, String name, String description, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute,
			boolean neverExpire, boolean active, String limitCategories,
			String limitSkus, double minOrder, double discount,
			String discountType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _shoppingCouponService.updateCoupon(
			couponId, name, description, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, neverExpire,
			active, limitCategories, limitSkus, minOrder, discount,
			discountType, serviceContext);
	}

	@Override
	public ShoppingCouponService getWrappedService() {
		return _shoppingCouponService;
	}

	@Override
	public void setWrappedService(ShoppingCouponService shoppingCouponService) {
		_shoppingCouponService = shoppingCouponService;
	}

	private ShoppingCouponService _shoppingCouponService;

}