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

package com.liferay.osb.customer.web.internal.portlet;

import com.liferay.osb.customer.constants.OSBCustomerPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jeremy Fu
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-knowledge-base-side-navigation-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=OSB Knowledge Base Side Navigation",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/side_navigation/",
		"javax.portlet.init-param.view-template=/side_navigation/view.jsp",
		"javax.portlet.name=" + OSBCustomerPortletKeys.SIDE_NAVIGATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=articleId"
	},
	service = Portlet.class
)
public class OSBKnowledgeBaseSideNavigationPortlet extends MVCPortlet {
}