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

import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.CountryAppPricing;
import com.liferay.osb.service.base.CountryAppPricingServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAppEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

import java.util.List;

/**
 * @author Douglas Wong
 * @author Ryan Park
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class CountryAppPricingServiceImpl
	extends CountryAppPricingServiceBaseImpl {

	public void deleteCountryAppPricings(long appVersionId, long[] countryIds)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appVersion.getAppEntryId(),
			OSBActionKeys.UPDATE);

		countryAppPricingLocalService.deleteCountryAppPricings(
			appVersionId, countryIds);
	}

	public List<CountryAppPricing> updateCountryAppPricings(
			long appVersionId, long appPricingId, long[] countryIds)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appVersion.getAppEntryId(),
			OSBActionKeys.UPDATE);

		return countryAppPricingLocalService.updateCountryAppPricings(
			appVersionId, appPricingId, countryIds);
	}

}