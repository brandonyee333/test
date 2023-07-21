/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.lcs.client.constants.LCSClientPortletKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true,
	property = "panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL_CONFIGURATION,
	service = PanelApp.class
)
public class LCSClientPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return LCSClientPortletKeys.CLIENT;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + LCSClientPortletKeys.CLIENT + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}