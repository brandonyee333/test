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

package com.liferay.osb.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class OrderEntryFinderUtil {
	public static int countByKeywords(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getFinder().countByKeywords(keywords, params);
	}

	public static int countByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
		java.lang.Long createUserId, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.Long modifiedUserId,
		java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
		java.lang.Long accountEntryId, java.lang.String purchaseOrderKey,
		int[] statuses, java.util.Date startDateGT, java.util.Date startDateLT,
		java.lang.Boolean prorated, java.util.Date actualStartDateGT,
		java.util.Date actualStartDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getFinder()
				   .countByU_CD_MU_MD_AE_PO_S_SD_P_ASD(createUserId,
			createDateGT, createDateLT, modifiedUserId, modifiedDateGT,
			modifiedDateLT, accountEntryId, purchaseOrderKey, statuses,
			startDateGT, startDateLT, prorated, actualStartDateGT,
			actualStartDateLT, params, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.OrderEntry> findByKeywords(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder().findByKeywords(keywords, params, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.OrderEntry> findByU_CD_MU_MD_AE_PO_S_SD_P_ASD(
		java.lang.Long createUserId, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.Long modifiedUserId,
		java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
		java.lang.Long accountEntryId, java.lang.String purchaseOrderKey,
		int[] statuses, java.util.Date startDateGT, java.util.Date startDateLT,
		java.lang.Boolean prorated, java.util.Date actualStartDateGT,
		java.util.Date actualStartDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder()
				   .findByU_CD_MU_MD_AE_PO_S_SD_P_ASD(createUserId,
			createDateGT, createDateLT, modifiedUserId, modifiedDateGT,
			modifiedDateLT, accountEntryId, purchaseOrderKey, statuses,
			startDateGT, startDateLT, prorated, actualStartDateGT,
			actualStartDateLT, params, andOperator, start, end, obc);
	}

	public static OrderEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (OrderEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					OrderEntryFinder.class.getName());

			ReferenceRegistry.registerReference(OrderEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(OrderEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(OrderEntryFinderUtil.class,
			"_finder");
	}

	private static OrderEntryFinder _finder;
}