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

package com.liferay.osb.admin.asset;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

/**
 * @author Amos Fong
 */
public class AccountEntryAssetRendererFactory extends BaseAssetRendererFactory {

	public static final String CLASS_NAME = AccountEntry.class.getName();

	public static final String TYPE = "support-project";

	@Override
	public AssetEntry getAssetEntry(long assetEntryId) {
		return AssetEntryLocalServiceUtil.createAssetEntry(0);
	}

	@Override
	public AssetEntry getAssetEntry(String className, long classPK) {
		return AssetEntryLocalServiceUtil.createAssetEntry(0);
	}

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException {

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntry(classPK);

		return new AccountEntryAssetRenderer(accountEntry);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getPortletId() {
		return OSBPortletKeys.OSB_ADMIN;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {

			return true;
		}

		return false;
	}

}