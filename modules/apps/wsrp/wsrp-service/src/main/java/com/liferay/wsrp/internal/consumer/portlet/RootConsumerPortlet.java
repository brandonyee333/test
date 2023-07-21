/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.consumer.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.wsrp.constants.WSRPPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 * @author Peter Fellwock
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=WSRP Consumer",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.name=" + WSRPPortletKeys.WSRP_CONSUMER,
		"javax.portlet.portlet-info.keywords=WSRP Consumer",
		"javax.portlet.portlet-info.short-title=WSRP Consumer",
		"javax.portlet.portlet-info.title=WSRP Consumer",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class RootConsumerPortlet extends MVCPortlet {
}