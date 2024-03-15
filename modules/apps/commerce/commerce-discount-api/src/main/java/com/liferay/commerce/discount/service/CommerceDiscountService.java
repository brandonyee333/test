/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.service;

import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.math.BigDecimal;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for CommerceDiscount. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceDiscountServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CommerceDiscountService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.commerce.discount.service.impl.CommerceDiscountServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the commerce discount remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link CommerceDiscountServiceUtil} if injection and service tracking are not available.
	 */
	public CommerceDiscount addCommerceDiscount(
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, BigDecimal level1,
			BigDecimal level2, BigDecimal level3, BigDecimal level4,
			int limitationTimes, String limitationType,
			BigDecimal maximumDiscountAmount, boolean neverExpire,
			String target, String title, boolean useCouponCode,
			boolean usePercentage, ServiceContext serviceContext)
		throws PortalException;

	public CommerceDiscount addCommerceDiscount(
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, String level,
			BigDecimal level1, BigDecimal level2, BigDecimal level3,
			BigDecimal level4, int limitationTimes, String limitationType,
			BigDecimal maximumDiscountAmount, boolean neverExpire,
			boolean rulesConjunction, String target, String title,
			boolean useCouponCode, boolean usePercentage,
			ServiceContext serviceContext)
		throws PortalException;

	public CommerceDiscount addCommerceDiscount(
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			String externalReferenceCode, String level, BigDecimal level1,
			BigDecimal level2, BigDecimal level3, BigDecimal level4,
			int limitationTimes, int limitationTimesPerAccount,
			String limitationType, BigDecimal maximumDiscountAmount,
			boolean neverExpire, boolean rulesConjunction, String target,
			String title, boolean useCouponCode, boolean usePercentage,
			ServiceContext serviceContext)
		throws PortalException;

	public CommerceDiscount addOrUpdateCommerceDiscount(
			String externalReferenceCode, long commerceDiscountId,
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, BigDecimal level1,
			BigDecimal level2, BigDecimal level3, BigDecimal level4,
			int limitationTimes, String limitationType,
			BigDecimal maximumDiscountAmount, boolean neverExpire,
			String target, String title, boolean useCouponCode,
			boolean usePercentage, ServiceContext serviceContext)
		throws PortalException;

	public CommerceDiscount addOrUpdateCommerceDiscount(
			String externalReferenceCode, long commerceDiscountId,
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, String level,
			BigDecimal level1, BigDecimal level2, BigDecimal level3,
			BigDecimal level4, int limitationTimes,
			int limitationTimesPerAccount, String limitationType,
			BigDecimal maximumDiscountAmount, boolean neverExpire,
			boolean rulesConjunction, String target, String title,
			boolean useCouponCode, boolean usePercentage,
			ServiceContext serviceContext)
		throws PortalException;

	public CommerceDiscount addOrUpdateCommerceDiscount(
			String externalReferenceCode, long commerceDiscountId,
			boolean active, String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, String level,
			BigDecimal level1, BigDecimal level2, BigDecimal level3,
			BigDecimal level4, int limitationTimes, String limitationType,
			BigDecimal maximumDiscountAmount, boolean neverExpire,
			boolean rulesConjunction, String target, String title,
			boolean useCouponCode, boolean usePercentage,
			ServiceContext serviceContext)
		throws PortalException;

	public void deleteCommerceDiscount(long commerceDiscountId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceDiscount fetchByExternalReferenceCode(
			String externalReferenceCode, long companyId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceDiscount fetchCommerceDiscount(long commerceDiscountId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceDiscount getCommerceDiscount(long commerceDiscountId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceDiscount> getCommerceDiscounts(
			long companyId, String level, boolean active, int status)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceDiscountsCountByPricingClassId(
			long commercePricingClassId, String title)
		throws PrincipalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceDiscount> searchByCommercePricingClassId(
			long commercePricingClassId, String title, int start, int end)
		throws PrincipalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<CommerceDiscount> searchCommerceDiscounts(
			long companyId, String keywords, int status, int start, int end,
			Sort sort)
		throws PortalException;

	public CommerceDiscount updateCommerceDiscount(
			long commerceDiscountId, boolean active,
			String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, BigDecimal level1,
			BigDecimal level2, BigDecimal level3, BigDecimal level4,
			int limitationTimes, String limitationType,
			BigDecimal maximumDiscountAmount, boolean neverExpire,
			String target, String title, boolean useCouponCode,
			boolean usePercentage, ServiceContext serviceContext)
		throws PortalException;

	public CommerceDiscount updateCommerceDiscount(
			long commerceDiscountId, boolean active,
			String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, String level,
			BigDecimal level1, BigDecimal level2, BigDecimal level3,
			BigDecimal level4, int limitationTimes,
			int limitationTimesPerAccount, String limitationType,
			BigDecimal maximumDiscountAmount, boolean neverExpire,
			boolean rulesConjunction, String target, String title,
			boolean useCouponCode, boolean usePercentage,
			ServiceContext serviceContext)
		throws PortalException;

	public CommerceDiscount updateCommerceDiscount(
			long commerceDiscountId, boolean active,
			String commerceCurrencyCode, String couponCode,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, String level,
			BigDecimal level1, BigDecimal level2, BigDecimal level3,
			BigDecimal level4, int limitationTimes, String limitationType,
			BigDecimal maximumDiscountAmount, boolean neverExpire,
			boolean rulesConjunction, String target, String title,
			boolean useCouponCode, boolean usePercentage,
			ServiceContext serviceContext)
		throws PortalException;

	public CommerceDiscount updateExternalReferenceCode(
			long commerceDiscountId, String externalReferenceCode)
		throws PortalException;

}