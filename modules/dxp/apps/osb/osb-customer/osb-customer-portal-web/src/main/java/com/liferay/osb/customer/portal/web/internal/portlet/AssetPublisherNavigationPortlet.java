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
		"com.liferay.portlet.css-class-wrapper=osb-portal-asset-publisher-navigation-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.header-portlet-css=/asset_publisher_navigation/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=OSB Portal Asset Publisher Navigation",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/asset_publisher_navigation/",
		"javax.portlet.init-param.view-template=/asset_publisher_navigation/view.jsp",
		"javax.portlet.name=" + OSBCustomerPortalPortletKeys.ASSET_PUBLISHER_NAVIGATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=categoryId",
		"javax.portlet.supported-public-render-parameter=resetCur",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class AssetPublisherNavigationPortlet extends MVCPortlet {
}