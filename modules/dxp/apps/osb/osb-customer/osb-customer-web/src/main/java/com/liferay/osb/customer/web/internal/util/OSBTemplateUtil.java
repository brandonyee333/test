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

package com.liferay.osb.customer.web.internal.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.osb.customer.asset.model.AssetCategoryDisplay;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alan Zhang
 */
@Component(immediate = true, service = OSBTemplateUtil.class)
public class OSBTemplateUtil {

	public String getPortraitURL(String imagePath, long userId) {
		try {
			User user = _userLocalService.fetchUser(userId);

			if ((user != null) && (user.getPortraitId() > 0)) {
				return UserConstants.getPortraitURL(
					imagePath, user.getMale(), user.getPortraitId(),
					user.getUserUuid());
			}
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
	}

	public boolean hasPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		//return OSBArticlePermission.contains(permissionChecker, actionId);
		return true;
	}

	public String renderAssetCategories(
			Locale locale, String className, long classPK)
		throws PortalException {

		List<AssetCategory> assetCategories =
			_assetCategoryService.getCategories(className, classPK);

		AssetCategoryDisplay assetCategoryDisplay = new AssetCategoryDisplay(
			locale, assetCategories);

		return assetCategoryDisplay.renderFull();
	}

	@Reference(unbind = "-")
	protected void setAssetCategoryService(
		AssetCategoryService assetCategoryService) {

		_assetCategoryService = assetCategoryService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private AssetCategoryService _assetCategoryService;
	private UserLocalService _userLocalService;

}