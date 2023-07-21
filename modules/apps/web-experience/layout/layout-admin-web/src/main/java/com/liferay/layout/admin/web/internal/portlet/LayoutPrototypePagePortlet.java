/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.portlet;

import com.liferay.layout.admin.web.internal.constants.LayoutAdminPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Julio Camarero
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-layouts-admin",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.icon=/icons/group_pages.png",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.system=true",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Page Template Page",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LayoutAdminPortletKeys.LAYOUT_PROTOTYPE_PAGE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.supported-public-render-parameter=layoutSetBranchId",
		"javax.portlet.supported-public-render-parameter=privateLayout",
		"javax.portlet.supported-public-render-parameter=selPlid",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class LayoutPrototypePagePortlet extends GroupPagesPortlet {
}