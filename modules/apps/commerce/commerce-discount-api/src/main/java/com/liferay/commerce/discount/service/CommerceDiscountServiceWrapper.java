/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceDiscountService}.
 *
 * @author Marco Leo
 * @see CommerceDiscountService
 * @generated
 */
public class CommerceDiscountServiceWrapper
	implements CommerceDiscountService,
			   ServiceWrapper<CommerceDiscountService> {

	public CommerceDiscountServiceWrapper() {
		this(null);
	}

	public CommerceDiscountServiceWrapper(
		CommerceDiscountService commerceDiscountService) {

		_commerceDiscountService = commerceDiscountService;
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			addCommerceDiscount(
				boolean active, String commerceCurrencyCode, String couponCode,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, java.math.BigDecimal level1,
				java.math.BigDecimal level2, java.math.BigDecimal level3,
				java.math.BigDecimal level4, int limitationTimes,
				String limitationType,
				java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
				String target, String title, boolean useCouponCode,
				boolean usePercentage,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.addCommerceDiscount(
			active, commerceCurrencyCode, couponCode, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, level1, level2, level3,
			level4, limitationTimes, limitationType, maximumDiscountAmount,
			neverExpire, target, title, useCouponCode, usePercentage,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			addCommerceDiscount(
				boolean active, String commerceCurrencyCode, String couponCode,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, String level,
				java.math.BigDecimal level1, java.math.BigDecimal level2,
				java.math.BigDecimal level3, java.math.BigDecimal level4,
				int limitationTimes, String limitationType,
				java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
				boolean rulesConjunction, String target, String title,
				boolean useCouponCode, boolean usePercentage,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.addCommerceDiscount(
			active, commerceCurrencyCode, couponCode, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, level, level1, level2,
			level3, level4, limitationTimes, limitationType,
			maximumDiscountAmount, neverExpire, rulesConjunction, target, title,
			useCouponCode, usePercentage, serviceContext);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			addCommerceDiscount(
				boolean active, String commerceCurrencyCode, String couponCode,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, String externalReferenceCode,
				String level, java.math.BigDecimal level1,
				java.math.BigDecimal level2, java.math.BigDecimal level3,
				java.math.BigDecimal level4, int limitationTimes,
				int limitationTimesPerAccount, String limitationType,
				java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
				boolean rulesConjunction, String target, String title,
				boolean useCouponCode, boolean usePercentage,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.addCommerceDiscount(
			active, commerceCurrencyCode, couponCode, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, externalReferenceCode,
			level, level1, level2, level3, level4, limitationTimes,
			limitationTimesPerAccount, limitationType, maximumDiscountAmount,
			neverExpire, rulesConjunction, target, title, useCouponCode,
			usePercentage, serviceContext);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			addOrUpdateCommerceDiscount(
				String externalReferenceCode, long commerceDiscountId,
				boolean active, String commerceCurrencyCode, String couponCode,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, java.math.BigDecimal level1,
				java.math.BigDecimal level2, java.math.BigDecimal level3,
				java.math.BigDecimal level4, int limitationTimes,
				String limitationType,
				java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
				String target, String title, boolean useCouponCode,
				boolean usePercentage,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.addOrUpdateCommerceDiscount(
			externalReferenceCode, commerceDiscountId, active,
			commerceCurrencyCode, couponCode, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, level1, level2, level3,
			level4, limitationTimes, limitationType, maximumDiscountAmount,
			neverExpire, target, title, useCouponCode, usePercentage,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			addOrUpdateCommerceDiscount(
				String externalReferenceCode, long commerceDiscountId,
				boolean active, String commerceCurrencyCode, String couponCode,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, String level,
				java.math.BigDecimal level1, java.math.BigDecimal level2,
				java.math.BigDecimal level3, java.math.BigDecimal level4,
				int limitationTimes, int limitationTimesPerAccount,
				String limitationType,
				java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
				boolean rulesConjunction, String target, String title,
				boolean useCouponCode, boolean usePercentage,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.addOrUpdateCommerceDiscount(
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

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			addOrUpdateCommerceDiscount(
				String externalReferenceCode, long commerceDiscountId,
				boolean active, String commerceCurrencyCode, String couponCode,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, String level,
				java.math.BigDecimal level1, java.math.BigDecimal level2,
				java.math.BigDecimal level3, java.math.BigDecimal level4,
				int limitationTimes, String limitationType,
				java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
				boolean rulesConjunction, String target, String title,
				boolean useCouponCode, boolean usePercentage,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.addOrUpdateCommerceDiscount(
			externalReferenceCode, commerceDiscountId, active,
			commerceCurrencyCode, couponCode, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, level, level1, level2,
			level3, level4, limitationTimes, limitationType,
			maximumDiscountAmount, neverExpire, rulesConjunction, target, title,
			useCouponCode, usePercentage, serviceContext);
	}

	@Override
	public void deleteCommerceDiscount(long commerceDiscountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceDiscountService.deleteCommerceDiscount(commerceDiscountId);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			fetchByExternalReferenceCode(
				String externalReferenceCode, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.fetchByExternalReferenceCode(
			externalReferenceCode, companyId);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			fetchCommerceDiscount(long commerceDiscountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.fetchCommerceDiscount(
			commerceDiscountId);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			getCommerceDiscount(long commerceDiscountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.getCommerceDiscount(commerceDiscountId);
	}

	@Override
	public java.util.List<com.liferay.commerce.discount.model.CommerceDiscount>
			getCommerceDiscounts(
				long companyId, String level, boolean active, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.getCommerceDiscounts(
			companyId, level, active, status);
	}

	@Override
	public int getCommerceDiscountsCountByPricingClassId(
			long commercePricingClassId, String title)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return _commerceDiscountService.
			getCommerceDiscountsCountByPricingClassId(
				commercePricingClassId, title);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceDiscountService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.commerce.discount.model.CommerceDiscount>
			searchByCommercePricingClassId(
				long commercePricingClassId, String title, int start, int end)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return _commerceDiscountService.searchByCommercePricingClassId(
			commercePricingClassId, title, start, end);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.discount.model.CommerceDiscount>
				searchCommerceDiscounts(
					long companyId, String keywords, int status, int start,
					int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.searchCommerceDiscounts(
			companyId, keywords, status, start, end, sort);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			updateCommerceDiscount(
				long commerceDiscountId, boolean active,
				String commerceCurrencyCode, String couponCode,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, java.math.BigDecimal level1,
				java.math.BigDecimal level2, java.math.BigDecimal level3,
				java.math.BigDecimal level4, int limitationTimes,
				String limitationType,
				java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
				String target, String title, boolean useCouponCode,
				boolean usePercentage,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.updateCommerceDiscount(
			commerceDiscountId, active, commerceCurrencyCode, couponCode,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			level1, level2, level3, level4, limitationTimes, limitationType,
			maximumDiscountAmount, neverExpire, target, title, useCouponCode,
			usePercentage, serviceContext);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			updateCommerceDiscount(
				long commerceDiscountId, boolean active,
				String commerceCurrencyCode, String couponCode,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, String level,
				java.math.BigDecimal level1, java.math.BigDecimal level2,
				java.math.BigDecimal level3, java.math.BigDecimal level4,
				int limitationTimes, int limitationTimesPerAccount,
				String limitationType,
				java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
				boolean rulesConjunction, String target, String title,
				boolean useCouponCode, boolean usePercentage,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.updateCommerceDiscount(
			commerceDiscountId, active, commerceCurrencyCode, couponCode,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute, level,
			level1, level2, level3, level4, limitationTimes,
			limitationTimesPerAccount, limitationType, maximumDiscountAmount,
			neverExpire, rulesConjunction, target, title, useCouponCode,
			usePercentage, serviceContext);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			updateCommerceDiscount(
				long commerceDiscountId, boolean active,
				String commerceCurrencyCode, String couponCode,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, String level,
				java.math.BigDecimal level1, java.math.BigDecimal level2,
				java.math.BigDecimal level3, java.math.BigDecimal level4,
				int limitationTimes, String limitationType,
				java.math.BigDecimal maximumDiscountAmount, boolean neverExpire,
				boolean rulesConjunction, String target, String title,
				boolean useCouponCode, boolean usePercentage,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.updateCommerceDiscount(
			commerceDiscountId, active, commerceCurrencyCode, couponCode,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute, level,
			level1, level2, level3, level4, limitationTimes, limitationType,
			maximumDiscountAmount, neverExpire, rulesConjunction, target, title,
			useCouponCode, usePercentage, serviceContext);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscount
			updateExternalReferenceCode(
				long commerceDiscountId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountService.updateExternalReferenceCode(
			commerceDiscountId, externalReferenceCode);
	}

	@Override
	public CommerceDiscountService getWrappedService() {
		return _commerceDiscountService;
	}

	@Override
	public void setWrappedService(
		CommerceDiscountService commerceDiscountService) {

		_commerceDiscountService = commerceDiscountService;
	}

	private CommerceDiscountService _commerceDiscountService;

}