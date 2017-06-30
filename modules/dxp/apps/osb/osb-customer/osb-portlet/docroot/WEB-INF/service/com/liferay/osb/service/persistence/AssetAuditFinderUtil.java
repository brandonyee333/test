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
public class AssetAuditFinderUtil {
	public static java.util.List<com.liferay.osb.model.AssetAudit> findByU_T(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByU_T(userId, type, start, end);
	}

	public static java.util.List<java.lang.Object[]> findByGtCD_LtCD_VCNI_VCPK_T(
		java.util.Date createDateGT, java.util.Date createDateLT,
		long vendorClassNameId, long vendorClassPK, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByGtCD_LtCD_VCNI_VCPK_T(createDateGT, createDateLT,
			vendorClassNameId, vendorClassPK, type, start, end);
	}

	public static AssetAuditFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetAuditFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetAuditFinder.class.getName());

			ReferenceRegistry.registerReference(AssetAuditFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetAuditFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetAuditFinderUtil.class,
			"_finder");
	}

	private static AssetAuditFinder _finder;
}