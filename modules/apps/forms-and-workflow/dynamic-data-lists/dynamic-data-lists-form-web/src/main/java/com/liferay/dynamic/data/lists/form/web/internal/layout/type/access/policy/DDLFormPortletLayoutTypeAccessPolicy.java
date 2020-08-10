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

package com.liferay.dynamic.data.lists.form.web.internal.layout.type.access.policy;

import com.liferay.dynamic.data.lists.constants.DDLActionKeys;
import com.liferay.dynamic.data.lists.form.web.internal.constants.DDLFormPortletKeys;
import com.liferay.dynamic.data.lists.form.web.internal.layout.type.constants.DDLFormPortletLayoutTypeConstants;
import com.liferay.dynamic.data.lists.service.permission.DDLRecordSetPermission;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypeAccessPolicy;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.impl.DefaultLayoutTypeAccessPolicyImpl;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Leonardo Barros
 */
@Component(
	immediate = true,
	property = "layout.type=" + DDLFormPortletLayoutTypeConstants.LAYOUT_TYPE,
	service = LayoutTypeAccessPolicy.class
)
public class DDLFormPortletLayoutTypeAccessPolicy
	extends DefaultLayoutTypeAccessPolicyImpl {

	@Override
	public void checkAccessAllowedToPortlet(
			HttpServletRequest request, Layout layout, Portlet portlet)
		throws PortalException {

		if (isAccessAllowedToLayoutPortlet(request, layout, portlet)) {
			StringBundler sb = new StringBundler(4);

			sb.append(StringPool.UNDERLINE);
			sb.append(DDLFormPortletKeys.DYNAMIC_DATA_LISTS_FORM);
			sb.append(StringPool.UNDERLINE);
			sb.append("recordSetId");

			long recordSetId = ParamUtil.getLong(request, sb.toString());

			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			if (recordSetId > 0) {
				if (DDLRecordSetPermission.contains(
						themeDisplay.getPermissionChecker(), recordSetId,
						DDLActionKeys.ADD_RECORD)) {

					return;
				}

				if (DDLRecordSetPermission.contains(
						themeDisplay.getPermissionChecker(), recordSetId,
						ActionKeys.VIEW)) {

					return;
				}
			}
		}

		super.checkAccessAllowedToPortlet(request, layout, portlet);
	}

	@Override
	public boolean isAddLayoutAllowed(
		PermissionChecker permissionChecker, Layout layout) {

		return false;
	}

	@Override
	public boolean isCustomizeLayoutAllowed(
		PermissionChecker permissionChecker, Layout layout) {

		return false;
	}

	@Override
	public boolean isDeleteLayoutAllowed(
		PermissionChecker permissionChecker, Layout layout) {

		return false;
	}

	@Override
	public boolean isUpdateLayoutAllowed(
		PermissionChecker permissionChecker, Layout layout) {

		return false;
	}

	@Override
	protected boolean isAccessGrantedByPortletOnPage(
		Layout layout, Portlet portlet) {

		if (StringUtil.equalsIgnoreCase(
				portlet.getPortletId(),
				DDLFormPortletKeys.DYNAMIC_DATA_LISTS_FORM)) {

			return true;
		}

		return false;
	}

}