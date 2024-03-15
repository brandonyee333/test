/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.service;

import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for CommerceDiscount. This utility wraps
 * <code>com.liferay.commerce.discount.service.impl.CommerceDiscountServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceDiscountService
 * @generated
 */
public class CommerceDiscountServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.discount.service.impl.CommerceDiscountServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceDiscount addCommerceDiscount(
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			java.math.BigDecimal level1, java.math.BigDecimal level2,
			java.math.BigDecimal level3, java.math.BigDecimal level4,
			int limitationTimes, String limitationType,
			java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
			String target, String title, boolean useCouponCode,
			boolean usePercentage,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceDiscount(
			active, commerceCurrencyCode, couponCode, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, level1, level2, level3,
			level4, limitationTimes, limitationType, maximumDiscountAmount,
			neverExpire, target, title, useCouponCode, usePercentage,
			serviceContext);
	}

	public static CommerceDiscount addCommerceDiscount(
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, String level,
			java.math.BigDecimal level1, java.math.BigDecimal level2,
			java.math.BigDecimal level3, java.math.BigDecimal level4,
			int limitationTimes, String limitationType,
			java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
			boolean rulesConjunction, String target, String title,
			boolean useCouponCode, boolean usePercentage,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceDiscount(
			active, commerceCurrencyCode, couponCode, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, level, level1, level2,
			level3, level4, limitationTimes, limitationType,
			maximumDiscountAmount, neverExpire, rulesConjunction, target, title,
			useCouponCode, usePercentage, serviceContext);
	}

	public static CommerceDiscount addCommerceDiscount(
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			String externalReferenceCode, String level,
			java.math.BigDecimal level1, java.math.BigDecimal level2,
			java.math.BigDecimal level3, java.math.BigDecimal level4,
			int limitationTimes, int limitationTimesPerAccount,
			String limitationType, java.math.BigDecimal maximumDiscountAmount,
			boolean neverExpire, boolean rulesConjunction, String target,
			String title, boolean useCouponCode, boolean usePercentage,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceDiscount(
			active, commerceCurrencyCode, couponCode, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, externalReferenceCode,
			level, level1, level2, level3, level4, limitationTimes,
			limitationTimesPerAccount, limitationType, maximumDiscountAmount,
			neverExpire, rulesConjunction, target, title, useCouponCode,
			usePercentage, serviceContext);
	}

	public static CommerceDiscount addOrUpdateCommerceDiscount(
			String externalReferenceCode, long commerceDiscountId,
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			java.math.BigDecimal level1, java.math.BigDecimal level2,
			java.math.BigDecimal level3, java.math.BigDecimal level4,
			int limitationTimes, String limitationType,
			java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
			String target, String title, boolean useCouponCode,
			boolean usePercentage,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addOrUpdateCommerceDiscount(
			externalReferenceCode, commerceDiscountId, active,
			commerceCurrencyCode, couponCode, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, level1, level2, level3,
			level4, limitationTimes, limitationType, maximumDiscountAmount,
			neverExpire, target, title, useCouponCode, usePercentage,
			serviceContext);
	}

	public static CommerceDiscount addOrUpdateCommerceDiscount(
			String externalReferenceCode, long commerceDiscountId,
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, String level,
			java.math.BigDecimal level1, java.math.BigDecimal level2,
			java.math.BigDecimal level3, java.math.BigDecimal level4,
			int limitationTimes, int limitationTimesPerAccount,
			String limitationType, java.math.BigDecimal maximumDiscountAmount,
			boolean neverExpire, boolean rulesConjunction, String target,
			String title, boolean useCouponCode, boolean usePercentage,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addOrUpdateCommerceDiscount(
			externalReferenceCode, commerceDiscountId, active,
			commerceCurrencyCode, couponCode, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, level, level1, level2,
			level3, level4, limitationTimes, limitationTimesPerAccount,
			limitationType, maximumDiscountAmount, neverExpire,
			rulesConjunction, target, title, useCouponCode, usePercentage,
			serviceContext);
	}

	public static CommerceDiscount addOrUpdateCommerceDiscount(
			String externalReferenceCode, long commerceDiscountId,
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, String level,
			java.math.BigDecimal level1, java.math.BigDecimal level2,
			java.math.BigDecimal level3, java.math.BigDecimal level4,
			int limitationTimes, String limitationType,
			java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
			boolean rulesConjunction, String target, String title,
			boolean useCouponCode, boolean usePercentage,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addOrUpdateCommerceDiscount(
			externalReferenceCode, commerceDiscountId, active,
			commerceCurrencyCode, couponCode, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, level, level1, level2,
			level3, level4, limitationTimes, limitationType,
			maximumDiscountAmount, neverExpire, rulesConjunction, target, title,
			useCouponCode, usePercentage, serviceContext);
	}

	public static void deleteCommerceDiscount(long commerceDiscountId)
		throws PortalException {

		getService().deleteCommerceDiscount(commerceDiscountId);
	}

	public static CommerceDiscount fetchByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException {

		return getService().fetchByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	public static CommerceDiscount fetchCommerceDiscount(
			long commerceDiscountId)
		throws PortalException {

		return getService().fetchCommerceDiscount(commerceDiscountId);
	}

	public static CommerceDiscount getCommerceDiscount(long commerceDiscountId)
		throws PortalException {

		return getService().getCommerceDiscount(commerceDiscountId);
	}

	public static List<CommerceDiscount> getCommerceDiscounts(
			long companyId, String level, boolean active, int status)
		throws PortalException {

		return getService().getCommerceDiscounts(
			companyId, level, active, status);
	}

	public static int getCommerceDiscountsCountByPricingClassId(
			long commercePricingClassId, String title)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().getCommerceDiscountsCountByPricingClassId(
			commercePricingClassId, title);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<CommerceDiscount> searchByCommercePricingClassId(
			long commercePricingClassId, String title, int start, int end)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().searchByCommercePricingClassId(
			commercePricingClassId, title, start, end);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<CommerceDiscount> searchCommerceDiscounts(
				long companyId, String keywords, int status, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
			throws PortalException {

		return getService().searchCommerceDiscounts(
			companyId, keywords, status, start, end, sort);
	}

	public static CommerceDiscount updateCommerceDiscount(
			long commerceDiscountId, boolean active,
			String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			java.math.BigDecimal level1, java.math.BigDecimal level2,
			java.math.BigDecimal level3, java.math.BigDecimal level4,
			int limitationTimes, String limitationType,
			java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
			String target, String title, boolean useCouponCode,
			boolean usePercentage,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCommerceDiscount(
			commerceDiscountId, active, commerceCurrencyCode, couponCode,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			level1, level2, level3, level4, limitationTimes, limitationType,
			maximumDiscountAmount, neverExpire, target, title, useCouponCode,
			usePercentage, serviceContext);
	}

	public static CommerceDiscount updateCommerceDiscount(
			long commerceDiscountId, boolean active,
			String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, String level,
			java.math.BigDecimal level1, java.math.BigDecimal level2,
			java.math.BigDecimal level3, java.math.BigDecimal level4,
			int limitationTimes, int limitationTimesPerAccount,
			String limitationType, java.math.BigDecimal maximumDiscountAmount,
			boolean neverExpire, boolean rulesConjunction, String target,
			String title, boolean useCouponCode, boolean usePercentage,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCommerceDiscount(
			commerceDiscountId, active, commerceCurrencyCode, couponCode,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute, level,
			level1, level2, level3, level4, limitationTimes,
			limitationTimesPerAccount, limitationType, maximumDiscountAmount,
			neverExpire, rulesConjunction, target, title, useCouponCode,
			usePercentage, serviceContext);
	}

	public static CommerceDiscount updateCommerceDiscount(
			long commerceDiscountId, boolean active,
			String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, String level,
			java.math.BigDecimal level1, java.math.BigDecimal level2,
			java.math.BigDecimal level3, java.math.BigDecimal level4,
			int limitationTimes, String limitationType,
			java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
			boolean rulesConjunction, String target, String title,
			boolean useCouponCode, boolean usePercentage,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCommerceDiscount(
			commerceDiscountId, active, commerceCurrencyCode, couponCode,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute, level,
			level1, level2, level3, level4, limitationTimes, limitationType,
			maximumDiscountAmount, neverExpire, rulesConjunction, target, title,
			useCouponCode, usePercentage, serviceContext);
	}

	public static CommerceDiscount updateExternalReferenceCode(
			long commerceDiscountId, String externalReferenceCode)
		throws PortalException {

		return getService().updateExternalReferenceCode(
			commerceDiscountId, externalReferenceCode);
	}

	public static CommerceDiscountService getService() {
		return _service;
	}

	public static void setService(CommerceDiscountService service) {
		_service = service;
	}

	private static volatile CommerceDiscountService _service;

}