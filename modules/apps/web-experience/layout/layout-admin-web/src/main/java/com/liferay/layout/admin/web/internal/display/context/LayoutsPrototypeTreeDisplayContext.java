/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.display.context;

import com.liferay.layout.admin.web.internal.constants.LayoutAdminPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Objects;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Julio Camarero
 */
public class LayoutsPrototypeTreeDisplayContext
	extends BaseLayoutDisplayContext {

	public LayoutsPrototypeTreeDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException {

		super(liferayPortletRequest, liferayPortletResponse);
	}

	public String getEditLayoutURL() {
		PortletURL editLayoutURL = PortalUtil.getControlPanelPortletURL(
			liferayPortletRequest, LayoutAdminPortletKeys.LAYOUT_PROTOTYPE_PAGE,
			PortletRequest.RENDER_PHASE);

		Group group = getSelGroup();

		editLayoutURL.setParameter(
			"groupId", String.valueOf(group.getGroupId()));

		Layout layout = getLayout();

		editLayoutURL.setParameter(
			"backURL", PortalUtil.getCurrentURL(liferayPortletRequest));
		editLayoutURL.setParameter("selPlid", String.valueOf(layout.getPlid()));
		editLayoutURL.setParameter("privateLayout", Boolean.TRUE.toString());

		return editLayoutURL.toString();
	}

	public Layout getLayout() {
		if (_layout != null) {
			return _layout;
		}

		Group group = getSelGroup();

		_layout = LayoutLocalServiceUtil.fetchFirstLayout(
			group.getGroupId(), true, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		return _layout;
	}

	public String getLayoutName() throws PortalException {
		Layout layout = getLayout();

		return layout.getName(themeDisplay.getLocale());
	}

	public String getLayoutURL() throws PortalException {
		Layout layout = getLayout();

		return layout.getRegularURL(
			PortalUtil.getHttpServletRequest(liferayPortletRequest));
	}

	public boolean isLayoutSelected() {
		Layout layout = getLayout();

		if ((layout.getPlid() == themeDisplay.getPlid()) ||
			Objects.equals(
				LayoutAdminPortletKeys.LAYOUT_PROTOTYPE_PAGE,
				themeDisplay.getPpid())) {

			return true;
		}

		return false;
	}

	private Layout _layout;

}