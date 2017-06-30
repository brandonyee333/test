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

package com.liferay.osb.service.impl;

import com.liferay.osb.service.base.OSBRegionServiceBaseImpl;
import com.liferay.osb.service.permission.OSBCountryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.model.Region;

/**
 * @author Alan Zhang
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class OSBRegionServiceImpl extends OSBRegionServiceBaseImpl {

	public Region addRegion(
			long countryId, String regionCode, String name, boolean active)
		throws PortalException, SystemException {

		OSBCountryPermission.check(
			getPermissionChecker(), OSBActionKeys.UPDATE);

		return osbRegionLocalService.addRegion(
			countryId, regionCode, name, active);
	}

	public Region deleteRegion(long regionId)
		throws PortalException, SystemException {

		OSBCountryPermission.check(
			getPermissionChecker(), OSBActionKeys.DELETE);

		return osbRegionLocalService.deleteRegion(regionId);
	}

	public Region updateRegion(
			long regionId, long countryId, String regionCode, String name,
			boolean active)
		throws PortalException, SystemException {

		OSBCountryPermission.check(
			getPermissionChecker(), OSBActionKeys.UPDATE);

		return osbRegionLocalService.updateRegion(
			regionId, countryId, regionCode, name, active);
	}

}