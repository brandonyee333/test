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

package com.liferay.watson.web.internal.portlet;

import com.liferay.alloy.mvc.AlloyPortlet;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.watson.web.constants.WatsonPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Steven Smith
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.application-type=full-page-application",
		"com.liferay.portlet.css-class-wrapper=watson-portlet",
		"com.liferay.portlet.display-category=category.tools",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.display-name=Watson", "javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Watson",
		"javax.portlet.info.short-title=Watson",
		"javax.portlet.info.title=Watson", "javax.portlet.mime-type=text/html",
		"javax.portlet.name=" + WatsonPortletKeys.WATSON,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class WatsonPortlet extends AlloyPortlet {

	@Override
	@Reference(target = "(name=watson-friendly-url-mapper)", unbind = "-")
	protected void setFriendlyURLMapper(FriendlyURLMapper friendlyURLMapper) {
		this.friendlyURLMapper = friendlyURLMapper;
	}

}