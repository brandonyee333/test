/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.portlet;

import com.liferay.alloy.mvc.AlloyPortlet;
import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Timothy Bell
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=loop-portlet",
		"com.liferay.portlet.display-category=category.tools",
		"com.liferay.portlet.header-portlet-css=/css/cropper.nocsf.css",
		"com.liferay.portlet.header-portlet-css=/dist/main.css",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.display-name=Loop", "javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Loop",
		"javax.portlet.info.short-title=Loop", "javax.portlet.info.title=Loop",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.name=" + LoopPortletKeys.LOOP,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class LoopPortlet extends AlloyPortlet {

	@Override
	protected String getPath(PortletRequest portletRequest) {
		StringBundler sb = new StringBundler(3);

		sb.append("/alloy_mvc/jsp/");
		sb.append(friendlyURLMapper.getMapping());
		sb.append("/controllers/init.jsp");

		return sb.toString();
	}

	@Override
	protected void include(
			String path, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws IOException, PortletException {

		portletRequest.setAttribute(
			"controllerName", getControllerPath(portletRequest));

		super.include(path, portletRequest, portletResponse);
	}

	@Override
	@Reference(target = "(name=osb-loop-friendly-url-mapper)", unbind = "-")
	protected void setFriendlyURLMapper(FriendlyURLMapper friendlyURLMapper) {
		this.friendlyURLMapper = friendlyURLMapper;
	}

}