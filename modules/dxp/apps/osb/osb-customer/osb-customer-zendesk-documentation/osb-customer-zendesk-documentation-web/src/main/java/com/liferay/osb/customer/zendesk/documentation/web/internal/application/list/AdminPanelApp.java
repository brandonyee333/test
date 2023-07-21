/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.osb.customer.zendesk.documentation.web.internal.constants.ZendeskDocumentationPortletKeys;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.util.PortletCategoryKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=1000",
		"panel.category.key=" + PortletCategoryKeys.CONFIGURATION
	},
	service = PanelApp.class
)
public class AdminPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return ZendeskDocumentationPortletKeys.ADMIN;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + ZendeskDocumentationPortletKeys.ADMIN + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}