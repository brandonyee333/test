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
public class AssetReceiptLicenseFinderUtil {
	public static int countByOCN_OCP(long ownerClassNameId, long ownerClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByOCN_OCP(ownerClassNameId, ownerClassPK);
	}

	public static java.util.List<com.liferay.osb.model.AssetReceiptLicense> findByOCN_OCP(
		long ownerClassNameId, long ownerClassPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByOCN_OCP(ownerClassNameId, ownerClassPK, start, end);
	}

	public static AssetReceiptLicenseFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetReceiptLicenseFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetReceiptLicenseFinder.class.getName());

			ReferenceRegistry.registerReference(AssetReceiptLicenseFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetReceiptLicenseFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetReceiptLicenseFinderUtil.class,
			"_finder");
	}

	private static AssetReceiptLicenseFinder _finder;
}