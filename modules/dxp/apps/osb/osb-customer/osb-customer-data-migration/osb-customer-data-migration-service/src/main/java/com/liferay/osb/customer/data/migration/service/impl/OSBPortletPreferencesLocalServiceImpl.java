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

package com.liferay.osb.customer.data.migration.service.impl;

import com.liferay.osb.customer.data.migration.service.base.OSBPortletPreferencesLocalServiceBaseImpl;
import com.liferay.portal.kernel.model.PortletPreferences;

/**
 * @author Amos Fong
 */
public class OSBPortletPreferencesLocalServiceImpl
	extends OSBPortletPreferencesLocalServiceBaseImpl {

	@Override
	public PortletPreferences addPortletPreferences(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId, String preferences) {

		return portletPreferencesLocalService.addPortletPreferences(
			companyId, ownerId, ownerType, plid, portletId, null, preferences);
	}

}