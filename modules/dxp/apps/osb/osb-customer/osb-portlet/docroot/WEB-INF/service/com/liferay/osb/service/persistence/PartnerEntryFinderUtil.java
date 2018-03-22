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
public class PartnerEntryFinderUtil {
	public static int countByKeywords(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getFinder().countByKeywords(keywords, params);
	}

	public static int countByC_S(java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getFinder().countByC_S(code, statuses, params, andOperator);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> findByKeywords(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end) {
		return getFinder().findByKeywords(keywords, params, start, end);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> findByC_S(
		java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end) {
		return getFinder()
				   .findByC_S(code, statuses, params, andOperator, start, end);
	}

	public static PartnerEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (PartnerEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					PartnerEntryFinder.class.getName());

			ReferenceRegistry.registerReference(PartnerEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(PartnerEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(PartnerEntryFinderUtil.class,
			"_finder");
	}

	private static PartnerEntryFinder _finder;
}