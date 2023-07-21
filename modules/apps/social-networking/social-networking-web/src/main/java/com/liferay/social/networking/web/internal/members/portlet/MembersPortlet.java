/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.web.internal.members.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.social.networking.constants.SocialNetworkingPortletKeys;
import com.liferay.social.networking.service.MeetupsEntryLocalService;
import com.liferay.social.networking.service.MeetupsRegistrationLocalService;
import com.liferay.social.networking.service.WallEntryLocalService;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = {
		"com.liferay.portlet.css-class-wrapper=social-networking-portlet-members",
		"com.liferay.portlet.display-category=category.social",
		"com.liferay.portlet.header-portlet-css=/members/css/main.css",
		"com.liferay.portlet.icon=/icons/members.png",
		"javax.portlet.display-name=Members",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Members",
		"javax.portlet.info.short-title=Members",
		"javax.portlet.info.title=Members",
		"javax.portlet.init-param.view-template=/members/view.jsp",
		"javax.portlet.name=" + SocialNetworkingPortletKeys.MEMBERS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref.role-name=administrator",
		"javax.portlet.security-role-ref.role-name=guest",
		"javax.portlet.security-role-ref.role-name=power-user",
		"javax.portlet.security-role-ref.role-name=user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class MembersPortlet extends MVCPortlet {

	@Reference(unbind = "-")
	protected void setMeetupsEntryLocalService(
		MeetupsEntryLocalService meetupsEntryLocalService) {
	}

	@Reference(unbind = "-")
	protected void setMeetupsRegistrationLocalService(
		MeetupsRegistrationLocalService meetupsRegistrationLocalService) {
	}

	@Reference(unbind = "-")
	protected void setWallEntryLocalService(
		WallEntryLocalService wallEntryLocalService) {
	}

}