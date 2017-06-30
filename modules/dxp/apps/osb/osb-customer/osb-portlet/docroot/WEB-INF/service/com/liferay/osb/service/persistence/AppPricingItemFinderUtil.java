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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class AppPricingItemFinderUtil {
	public static java.util.List<com.liferay.osb.model.AppPricingItem> findByAVI_ALI(
		long appVersionId, long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByAVI_ALI(appVersionId, assetLicenseId);
	}

	public static AppPricingItemFinder getFinder() {
		if (_finder == null) {
			_finder = (AppPricingItemFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppPricingItemFinder.class.getName());

			ReferenceRegistry.registerReference(AppPricingItemFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AppPricingItemFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AppPricingItemFinderUtil.class,
			"_finder");
	}

	private static AppPricingItemFinder _finder;
}