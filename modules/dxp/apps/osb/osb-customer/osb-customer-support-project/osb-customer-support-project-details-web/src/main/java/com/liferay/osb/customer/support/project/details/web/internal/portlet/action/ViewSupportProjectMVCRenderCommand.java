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

package com.liferay.osb.customer.support.project.details.web.internal.portlet.action;

import com.liferay.osb.customer.support.project.details.web.internal.constants.SupportProjectDetailsPortletKeys;
import com.liferay.osb.customer.support.project.details.web.internal.constants.SupportProjectDetailsWebKeys;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryServiceUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + SupportProjectDetailsPortletKeys.SUPPORT_PROJECT_DETAILS,
		"mvc.command.name=/view_support_project"
	},
	service = MVCRenderCommand.class
)
public class ViewSupportProjectMVCRenderCommand extends BaseMVCRenderCommand {

	@Override
	protected String doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountEntryId = ParamUtil.getLong(
			renderRequest, "accountEntryId");

		AccountEntry accountEntry = AccountEntryServiceUtil.getAccountEntry(
			accountEntryId);

		renderRequest.setAttribute(
			SupportProjectDetailsWebKeys.ACCOUNT_ENTRY, accountEntry);

		if (isLiferayIncOrg(themeDisplay.getUserId())) {
			return "/support_project_details/worker/view_support_project.jsp";
		}
		else {
			return "/support_project_details/customer/view_support_project.jsp";
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}