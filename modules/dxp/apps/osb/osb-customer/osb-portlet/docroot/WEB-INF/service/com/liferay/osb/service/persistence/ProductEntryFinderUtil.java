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
public class ProductEntryFinderUtil {
	public static int countByName(java.lang.String name,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getFinder().countByName(name, params);
	}

	public static java.util.List<com.liferay.osb.model.ProductEntry> findByAccountEntry(
		long accountEntryId, int start, int end) {
		return getFinder().findByAccountEntry(accountEntryId, start, end);
	}

	public static java.util.List<com.liferay.osb.model.ProductEntry> findByName(
		java.lang.String name,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end) {
		return getFinder().findByName(name, params, start, end);
	}

	public static ProductEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (ProductEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					ProductEntryFinder.class.getName());

			ReferenceRegistry.registerReference(ProductEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ProductEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ProductEntryFinderUtil.class,
			"_finder");
	}

	private static ProductEntryFinder _finder;
}