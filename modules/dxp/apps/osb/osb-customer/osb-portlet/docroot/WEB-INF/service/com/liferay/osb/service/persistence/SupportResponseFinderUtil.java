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
public class SupportResponseFinderUtil {
	public static com.liferay.osb.model.SupportResponse fetchByAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getFinder().fetchByAccountEntry(accountEntryId);
	}

	public static SupportResponseFinder getFinder() {
		if (_finder == null) {
			_finder = (SupportResponseFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportResponseFinder.class.getName());

			ReferenceRegistry.registerReference(SupportResponseFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SupportResponseFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SupportResponseFinderUtil.class,
			"_finder");
	}

	private static SupportResponseFinder _finder;
}