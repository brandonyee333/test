/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.web.internal.map.portlet;

import com.liferay.ip.geocoder.IPGeocoder;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.social.networking.constants.SocialNetworkingPortletKeys;
import com.liferay.social.networking.service.MeetupsEntryLocalService;
import com.liferay.social.networking.service.MeetupsRegistrationLocalService;
import com.liferay.social.networking.service.WallEntryLocalService;
import com.liferay.social.networking.web.internal.constants.SocialNetworkingWebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = {
		"com.liferay.portlet.css-class-wrapper=social-networking-portlet-map",
		"com.liferay.portlet.display-category=category.social",
		"javax.portlet.display-name=Map", "javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Map", "javax.portlet.info.short-title=Map",
		"javax.portlet.info.title=Map",
		"javax.portlet.init-param.view-template=/map/view.jsp",
		"javax.portlet.name=" + SocialNetworkingPortletKeys.MAP,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class MapPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		HttpServletRequest request = _portal.getHttpServletRequest(
			renderRequest);

		request.setAttribute(SocialNetworkingWebKeys.IP_GEOCODER, _ipGeocoder);

		super.render(renderRequest, renderResponse);
	}

	@Reference(unbind = "-")
	protected void setIPGeocoder(IPGeocoder ipGeocoder) {
		_ipGeocoder = ipGeocoder;
	}

	@Reference(unbind = "-")
	protected void setMeetupsEntryLocalService(
		MeetupsEntryLocalService meetupsEntryLocalService) {
	}

	@Reference(unbind = "-")
	protected void setMeetupsRegistrationLocalService(
		MeetupsRegistrationLocalService meetupsRegistrationLocalService) {
	}

	@Reference(unbind = "-")
	protected void setPortal(Portal portal) {
		_portal = portal;
	}

	@Reference(unbind = "-")
	protected void setWallEntryLocalService(
		WallEntryLocalService wallEntryLocalService) {
	}

	private IPGeocoder _ipGeocoder;
	private Portal _portal;

}