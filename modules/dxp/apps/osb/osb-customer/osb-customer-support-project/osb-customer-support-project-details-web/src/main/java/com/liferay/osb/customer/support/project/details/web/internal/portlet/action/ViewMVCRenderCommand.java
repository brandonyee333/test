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

import com.liferay.osb.customer.support.project.details.web.internal.constants.SupportProjectDetailsConstants;
import com.liferay.osb.customer.support.project.details.web.internal.constants.SupportProjectDetailsPortletKeys;
import com.liferay.osb.customer.support.project.details.web.internal.constants.SupportProjectDetailsWebKeys;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountEntryServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletException;
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
		"mvc.command.name=/", "mvc.command.name=/support_project_details/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			AccountEntry accountEntry = getAccountEntry(renderRequest);

			if (accountEntry != null) {
				renderRequest.setAttribute(
					SupportProjectDetailsWebKeys.ACCOUNT_ENTRY, accountEntry);

				return "/support_project_details/view_support_project.jsp";
			}
		}
		catch (Exception e) {
			SessionErrors.add(renderRequest, e.getClass());

			return "/support_project_details/error.jsp";
		}

		return "/support_project_details/search.jsp";
	}

	protected AccountEntry getAccountEntry(RenderRequest renderRequest)
		throws PortalException {

		long accountEntryId = ParamUtil.getLong(
			renderRequest, "accountEntryId");

		if (accountEntryId > 0) {
			return AccountEntryServiceUtil.getAccountEntry(accountEntryId);
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (organizationLocalService.hasUserOrganization(
				themeDisplay.getUserId(),
				SupportProjectDetailsConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return null;
		}

		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getUserActiveAccountEntries(
				themeDisplay.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (accountEntries.isEmpty()) {
			throw new PrincipalException();
		}

		if (accountEntries.size() == 1) {
			return accountEntries.get(0);
		}

		return null;
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {

		this.organizationLocalService = organizationLocalService;
	}

	protected OrganizationLocalService organizationLocalService;

}