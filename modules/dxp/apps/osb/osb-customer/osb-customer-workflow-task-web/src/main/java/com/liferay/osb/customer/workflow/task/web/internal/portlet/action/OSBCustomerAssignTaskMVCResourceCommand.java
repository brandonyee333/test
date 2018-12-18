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
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PortletKeys.MY_WORKFLOW_TASK,
		"mvc.command.name=assignWorkflowTask", "service.ranking:Integer=100"
	},
	service = MVCResourceCommand.class
)
public class OSBCustomerAssignTaskMVCResourceCommand
	extends BaseMVCResourceCommand {

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

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)resourceRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			long workflowTaskId = ParamUtil.getLong(
				resourceRequest, "workflowTaskId");

			long assigneeUserId = ParamUtil.getLong(
				resourceRequest, "assigneeUserId");
			String comment = ParamUtil.getString(resourceRequest, "comment");

			WorkflowTask workflowTask = WorkflowTaskManagerUtil.getWorkflowTask(
				themeDisplay.getCompanyId(), workflowTaskId);

			validateAssign(workflowTask);

			checkWorkflowTaskAssignmentPermission(workflowTaskId, themeDisplay);

			workflowTask = WorkflowTaskManagerUtil.assignWorkflowTaskToUser(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				workflowTaskId, assigneeUserId, comment, null, null);

			Indexer indexer = IndexerRegistryUtil.getIndexer(
				WorkflowTask.class.getName());

			indexer.reindex(workflowTask);

			SessionMessages.add(resourceRequest, "requestProcessed", "");
		}
		catch (Exception e) {
			if (Validator.isNotNull(e.getMessage())) {
				SessionErrors.add(resourceRequest, e.getMessage());
			}
			else {
				SessionErrors.add(resourceRequest, e.getClass());
			}
		}
	}

	protected void validateAssign(WorkflowTask workflowTask) throws Exception {
		if (workflowTask.isCompleted()) {
			throw new Exception("workflowTaskCompleted");
		}
	}

	@Reference
	private RoleLocalService _roleLocalService;

}