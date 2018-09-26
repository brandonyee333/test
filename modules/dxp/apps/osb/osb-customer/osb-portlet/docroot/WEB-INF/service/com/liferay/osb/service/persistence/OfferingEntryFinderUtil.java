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
public class OfferingEntryFinderUtil {
	public static int countByU_AEI_PEI_T_S_SED(long userId,
		long accountEntryId, long[] productEntryIds, int[] types,
		int[] statuses, java.util.Date supportEndDateGT,
		java.util.Date supportEndDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getFinder()
				   .countByU_AEI_PEI_T_S_SED(userId, accountEntryId,
			productEntryIds, types, statuses, supportEndDateGT,
			supportEndDateLT, params, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByU_AEI_PEI_T_S_SED(
		long userId, long accountEntryId, long[] productEntryIds, int[] types,
		int[] statuses, java.util.Date supportEndDateGT,
		java.util.Date supportEndDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder()
				   .findByU_AEI_PEI_T_S_SED(userId, accountEntryId,
			productEntryIds, types, statuses, supportEndDateGT,
			supportEndDateLT, params, andOperator, start, end, obc);
	}

	public static OfferingEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (OfferingEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					OfferingEntryFinder.class.getName());

			ReferenceRegistry.registerReference(OfferingEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(OfferingEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(OfferingEntryFinderUtil.class,
			"_finder");
	}

	private static OfferingEntryFinder _finder;
}