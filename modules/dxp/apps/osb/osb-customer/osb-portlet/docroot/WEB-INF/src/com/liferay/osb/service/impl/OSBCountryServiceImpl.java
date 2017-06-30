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

import com.liferay.osb.service.base.OSBCountryServiceBaseImpl;
import com.liferay.osb.service.permission.OSBCountryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.model.Country;

/**
 * @author Alan Zhang
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class OSBCountryServiceImpl extends OSBCountryServiceBaseImpl {

	public Country addCountry(
			String name, String a2, String a3, String number, String idd,
			boolean active)
		throws PortalException, SystemException {

		OSBCountryPermission.check(
			getPermissionChecker(), OSBActionKeys.UPDATE);

		return osbCountryLocalService.addCountry(
			name, a2, a3, number, idd, active);
	}

	public Country deleteCountry(long countryId)
		throws PortalException, SystemException {

		OSBCountryPermission.check(
			getPermissionChecker(), OSBActionKeys.DELETE);

		return osbCountryLocalService.deleteCountry(countryId);
	}

	public Country updateCountry(
			long countryId, String name, String a2, String a3, String number,
			String idd, boolean active)
		throws PortalException, SystemException {

		OSBCountryPermission.check(
			getPermissionChecker(), OSBActionKeys.UPDATE);

		return osbCountryLocalService.updateCountry(
			countryId, name, a2, a3, number, idd, active);
	}

}