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

package com.liferay.osb.customer.workflow.task.web.internal.portlet.action;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;

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
		"javax.portlet.name=" + PortletKeys.MY_WORKFLOW_TASK,
		"mvc.command.name=/"
	},
	service = MVCRenderCommand.class
)
public class OSBCustomerViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long osbWorkflowTaskId = ParamUtil.getLong(
				renderRequest, "osbWorkflowTaskId");

			if (osbWorkflowTaskId > 0) {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)renderRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				WorkflowTask workflowTask =
					WorkflowTaskManagerUtil.getWorkflowTask(
						themeDisplay.getCompanyId(), osbWorkflowTaskId);

				checkWorkflowTaskAssignmentPermission(
					osbWorkflowTaskId, themeDisplay);

				renderRequest.setAttribute(WebKeys.WORKFLOW_TASK, workflowTask);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		return ParamUtil.getString(renderRequest, "osbMVCPath");
	}

	protected void checkWorkflowTaskAssignmentPermission(
			long workflowTaskId, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (permissionChecker.isCompanyAdmin()) {
			return;
		}

		if (_roleLocalService.hasUserRole(
				themeDisplay.getUserId(),
				OSBCustomerConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {

			return;
		}

		if (_roleLocalService.hasUserRole(
				themeDisplay.getUserId(),
				OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return;
		}

		throw new PrincipalException();
	}

	@Reference
	private RoleLocalService _roleLocalService;

}