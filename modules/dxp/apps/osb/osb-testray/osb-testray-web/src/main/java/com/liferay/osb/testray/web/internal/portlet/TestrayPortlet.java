/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.web.internal.portlet;

import com.liferay.alloy.mvc.AlloyPortlet;
import com.liferay.osb.testray.web.constants.TestrayPortletKeys;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=testray-portlet",
		"com.liferay.portlet.display-category=category.tools",
		"com.liferay.portlet.footer-portlet-css=/css/c3/c3.min.css",
		"com.liferay.portlet.footer-portlet-css=/css/choices.min.css",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.display-name=Testray",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Testray",
		"javax.portlet.info.short-title=Testray",
		"javax.portlet.info.title=Testray", "javax.portlet.mime-type=text/html",
		"javax.portlet.name=" + TestrayPortletKeys.TESTRAY,
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class TestrayPortlet extends AlloyPortlet {

	@Override
	@Reference(target = "(name=osb-testray-friendly-url-mapper)", unbind = "-")
	protected void setFriendlyURLMapper(FriendlyURLMapper friendlyURLMapper) {
		this.friendlyURLMapper = friendlyURLMapper;
	}

}