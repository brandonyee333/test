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

package com.liferay.osb.community.advanced.administration.web.internal.portlet.action;

import com.liferay.osb.community.advanced.administration.web.internal.constants.AdvancedAdministrationPortletKeys;
import com.liferay.osb.community.generator.site.SiteGenerator;
import com.liferay.osb.community.generator.site.SiteGeneratorRegistry;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component(
	property = {
		"javax.portlet.name=" + AdvancedAdministrationPortletKeys.ADVANCED_ADMINISTRATION,
		"mvc.command.name=/regenerate_site"
	},
	service = MVCActionCommand.class
)
public class RegenerateSiteMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long groupId = ParamUtil.getLong(actionRequest, "groupId");

		String siteGeneratorKey = ParamUtil.getString(
			actionRequest, "siteGeneratorKey");

		SiteGenerator siteGenerator = _siteGeneratorRegistry.getSiteGenerator(
			siteGeneratorKey);

		siteGenerator.generate(groupId);
	}

	@Reference
	private SiteGeneratorRegistry _siteGeneratorRegistry;

}