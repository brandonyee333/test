/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.web.internal.membersactivities.portlet;

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
		"javax.portlet.display-name=Members' Activities",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Members' Activities",
		"javax.portlet.info.short-title=Members' Activities",
		"javax.portlet.info.title=Members' Activities",
		"javax.portlet.init-param.view-template=/members_activities/view.jsp",
		"javax.portlet.name=" + SocialNetworkingPortletKeys.MEMBERS_ACTIVITIES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class MembersActivitiesPortlet extends MVCPortlet {

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