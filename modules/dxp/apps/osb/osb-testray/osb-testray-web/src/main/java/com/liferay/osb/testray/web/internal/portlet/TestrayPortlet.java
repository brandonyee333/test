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