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
public class AccountEntryFinderUtil {
	public static int countByKeywords(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getFinder().countByKeywords(keywords, params);
	}

	public static int countByKAK_DAK_N_C_I_S(
		java.lang.String koroneikiAccountKey,
		java.lang.String dossieraAccountKey, java.lang.String name,
		java.lang.String code, int[] statuses, java.lang.String instructions,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getFinder()
				   .countByKAK_DAK_N_C_I_S(koroneikiAccountKey,
			dossieraAccountKey, name, code, statuses, instructions, params,
			andOperator);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> findByKeywords(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder().findByKeywords(keywords, params, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> findByKAK_DAK_N_C_I_S(
		java.lang.String koroneikiAccountKey,
		java.lang.String dossieraAccountKey, java.lang.String name,
		java.lang.String code, int[] statuses, java.lang.String instructions,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getFinder()
				   .findByKAK_DAK_N_C_I_S(koroneikiAccountKey,
			dossieraAccountKey, name, code, statuses, instructions, params,
			andOperator, start, end, obc);
	}

	public static AccountEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (AccountEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AccountEntryFinder.class.getName());

			ReferenceRegistry.registerReference(AccountEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AccountEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AccountEntryFinderUtil.class,
			"_finder");
	}

	private static AccountEntryFinder _finder;
}