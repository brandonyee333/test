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

package com.liferay.osb.asset;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.service.permission.OSBAppEntryPermission;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Douglas Wong
 */
public class AppEntryAssetRenderer extends BaseAssetRenderer {

	public AppEntryAssetRenderer(AppEntry appEntry) {
		_appEntry = appEntry;
	}

	@Override
	public long getClassPK() {
		return _appEntry.getAppEntryId();
	}

	@Override
	public long getGroupId() {
		return OSBConstants.GROUP_GUEST_ID;
	}

	@Override
	public String getSummary(Locale locale) {
		return HtmlUtil.stripHtml(_appEntry.getDescription(locale));
	}

	@Override
	public String getTitle(Locale locale) {
		return _appEntry.getTitle();
	}

	@Override
	public String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		try {
			long marketplacePlid = PortalUtil.getPlidFromPortletId(
				OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);

			PortletURL portletURL =
				liferayPortletResponse.createLiferayPortletURL(
					marketplacePlid, OSBPortletKeys.OSB_MARKETPLACE,
					PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"mvcPath", "/marketplace/view_app_entry.jsp");
			portletURL.setParameter(
				"appEntryId", String.valueOf(_appEntry.getAppEntryId()));

			return portletURL.toString();
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public long getUserId() {
		return _appEntry.getUserId();
	}

	@Override
	public String getUserName() {
		return _appEntry.getUserName();
	}

	@Override
	public String getUuid() {
		return _appEntry.getUuid();
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		try {
			return OSBAppEntryPermission.contains(
				permissionChecker, _appEntry, ActionKeys.UPDATE);
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		try {
			return OSBAppEntryPermission.contains(
				permissionChecker, _appEntry, ActionKeys.VIEW);
		}
		catch (Exception e) {
		}

		return true;
	}

	@Override
	public boolean isPrintable() {
		return true;
	}

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse,
		String template) {

		return null;
	}

	private AppEntry _appEntry;

}