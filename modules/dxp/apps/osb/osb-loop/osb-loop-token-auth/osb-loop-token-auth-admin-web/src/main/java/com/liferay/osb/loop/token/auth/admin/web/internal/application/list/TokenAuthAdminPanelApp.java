/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.admin.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.osb.loop.token.auth.constants.TokenAuthPortletKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Calvin Keum
 */
@Component(
	immediate = true,
	property = "panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL_CONFIGURATION,
	service = PanelApp.class
)
public class TokenAuthAdminPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return TokenAuthPortletKeys.TOKEN_AUTH_ADMIN;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + TokenAuthPortletKeys.TOKEN_AUTH_ADMIN + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}