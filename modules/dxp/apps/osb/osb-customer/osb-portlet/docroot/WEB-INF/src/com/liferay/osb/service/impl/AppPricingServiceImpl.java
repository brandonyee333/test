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

import com.liferay.osb.model.AppPricing;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.service.base.AppPricingServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAppEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

/**
 * @author Douglas Wong
 * @author Ryan Park
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AppPricingServiceImpl extends AppPricingServiceBaseImpl {

	public AppPricing addAppPricing(long appVersionId, String name)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appVersion.getAppEntryId(),
			OSBActionKeys.UPDATE);

		return appPricingLocalService.addAppPricing(
			getUserId(), appVersionId, name);
	}

	public AppPricing deleteAppPricing(long appPricingId)
		throws PortalException, SystemException {

		AppPricing appPricing = appPricingPersistence.findByPrimaryKey(
			appPricingId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appPricing.getAppEntryId(),
			OSBActionKeys.UPDATE);

		return appPricingLocalService.deleteAppPricing(appPricingId);
	}

	public AppPricing updateAppPricing(
			long appPricingId, String name, long currencyEntryId,
			double standardSupportPrice, double developerSupportPrice)
		throws PortalException, SystemException {

		AppPricing appPricing = appPricingPersistence.findByPrimaryKey(
			appPricingId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appPricing.getAppEntryId(),
			OSBActionKeys.UPDATE);

		return appPricingLocalService.updateAppPricing(
			appPricingId, name, currencyEntryId, standardSupportPrice,
			developerSupportPrice);
	}

}