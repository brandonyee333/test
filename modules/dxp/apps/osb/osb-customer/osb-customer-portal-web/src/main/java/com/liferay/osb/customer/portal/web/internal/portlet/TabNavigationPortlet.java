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

package com.liferay.osb.customer.portal.web.internal.portlet;

import com.liferay.osb.customer.portal.web.internal.constants.OSBCustomerPortalPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-portal-tab-navigation-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.header-portlet-css=/tab_navigation/css/main.css",
		"javax.portlet.display-name=OSB Portal Tab Navigation",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/tab_navigation/",
		"javax.portlet.init-param.view-template=/tab_navigation/view.jsp",
		"javax.portlet.name=" + OSBCustomerPortalPortletKeys.TAB_NAVIGATION,
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class TabNavigationPortlet extends MVCPortlet {
}