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

package com.liferay.watson.web.interal.portlet;

import com.liferay.alloy.mvc.AlloyPortlet;
import com.liferay.watson.constants.WatsonPortletKeys;
import com.liferay.watson.internal.util.WatsonRoleUtil;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import java.lang.reflect.Field;
import java.util.Map;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Steven Smith
 */
@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.css-class-wrapper=watson-portlet",
				"com.liferay.portlet.display-category=category.tools",
				"com.liferay.portlet.requires-namespaced-parameters=false",
				"javax.portlet.display-name=Watson",
				"javax.portlet.expiration-cache=0",
				"javax.portlet.info.keywords=Watson",
				"javax.portlet.info.short-title=Watson",
				"javax.portlet.info.title=Watson",
				"javax.portlet.mime-type=text/html",
				"javax.portlet.name=" + WatsonPortletKeys.WATSON,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		},
		service = Portlet.class
)
public class WatsonPortlet extends AlloyPortlet {

	@Activate
	public void activate() throws Exception {
		WatsonRoleUtil.initResourceActions();
		WatsonRoleUtil.initRoles();
	}

	@Override
	@Reference(
			target = "(name=watson-friendly-url-mapper)",
			unbind = "-"
	)
	protected void setFriendlyURLMapper(FriendlyURLMapper friendlyURLMapper) {
		this.friendlyURLMapper = friendlyURLMapper;
	}
}