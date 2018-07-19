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

package com.liferay.osb.customer.support.project.details.web.internal.portlet;

import com.liferay.osb.customer.support.project.details.web.internal.constants.SupportProjectDetailsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-support-project-details-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"javax.portlet.display-name=OSB Support Project Details",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.mvc-command-names-default-views=/support_project_details/view",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + SupportProjectDetailsPortletKeys.SUPPORT_PROJECT_DETAILS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class SupportProjectDetailsPortlet extends MVCPortlet {
}