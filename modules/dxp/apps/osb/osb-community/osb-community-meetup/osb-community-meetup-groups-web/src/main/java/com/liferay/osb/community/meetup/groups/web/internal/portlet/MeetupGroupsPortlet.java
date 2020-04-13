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

package com.liferay.osb.community.meetup.groups.web.internal.portlet;

import com.liferay.osb.community.meetup.groups.web.internal.constants.MeetupGroupsPortletKeys;
import com.liferay.portal.portlet.bridge.soy.SoyPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jamie Sammons
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.display-name=Meetup Groups",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=View",
		"javax.portlet.name=" + MeetupGroupsPortletKeys.MEETUP_GROUPS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MeetupGroupsPortlet extends SoyPortlet {
}