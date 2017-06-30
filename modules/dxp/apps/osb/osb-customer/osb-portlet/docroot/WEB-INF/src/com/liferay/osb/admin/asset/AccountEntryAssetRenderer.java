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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Amos Fong
 */
public class AccountEntryAssetRenderer extends BaseAssetRenderer {

	public AccountEntryAssetRenderer(AccountEntry accountEntry) {
		_accountEntry = accountEntry;
	}

	public AccountEntry getAccountEntry() {
		return _accountEntry;
	}

	public long getClassPK() {
		return _accountEntry.getAccountEntryId();
	}

	public long getGroupId() {
		return 0;
	}

	public String getSummary(Locale locale) {
		return StringPool.BLANK;
	}

	public String getTitle(Locale locale) {
		return _accountEntry.getName();
	}

	@Override
	public String getURLViewInContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			String noSuchEntryRedirect)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			getControlPanelPlid(liferayPortletRequest),
			OSBPortletKeys.OSB_ADMIN, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/admin/edit_account_entry.jsp");
		portletURL.setParameter(
			"accountEntryId",
			String.valueOf(_accountEntry.getAccountEntryId()));

		return portletURL.toString();
	}

	public long getUserId() {
		return _accountEntry.getUserId();
	}

	public String getUserName() {
		return _accountEntry.getUserName();
	}

	public String getUuid() {
		return StringPool.BLANK;
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker)
		throws SystemException {

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

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker)
		throws SystemException {

		return hasEditPermission(permissionChecker);
	}

	@Override
	public boolean isPreviewInContext() {
		return true;
	}

	@Override
	public boolean isPrintable() {
		return true;
	}

	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse,
		String template) {

		return "/admin/asset/account_entry/" + template + ".jsp";
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/trees/page.png";
	}

	private AccountEntry _accountEntry;

}