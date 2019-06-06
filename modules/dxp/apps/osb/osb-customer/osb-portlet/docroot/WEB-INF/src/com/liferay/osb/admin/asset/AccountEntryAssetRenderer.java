/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.admin.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class AccountEntryAssetRenderer
	extends BaseJSPAssetRenderer<AccountEntry> {

	public AccountEntryAssetRenderer(AccountEntry accountEntry) {
		_accountEntry = accountEntry;
	}

	@Override
	public AccountEntry getAssetObject() {
		return _accountEntry;
	}

	@Override
	public String getClassName() {
		return AccountEntry.class.getName();
	}

	@Override
	public long getClassPK() {
		return _accountEntry.getAccountEntryId();
	}

	@Override
	public long getGroupId() {
		return 0;
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/admin/asset/account_entry/" + template + ".jsp";
		}

		return null;
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return null;
	}

	@Override
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

	@Override
	public long getUserId() {
		return _accountEntry.getUserId();
	}

	@Override
	public String getUserName() {
		return _accountEntry.getUserName();
	}

	@Override
	public String getUuid() {
		return StringPool.BLANK;
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
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
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		return hasEditPermission(permissionChecker);
	}

	@Override
	public boolean isPreviewInContext() {
		return true;
	}

	private final AccountEntry _accountEntry;

}