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

package com.liferay.osb.hook.action;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.RequiredFieldException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Amos Fong
 */
public class OSBEditWorkflowTaskAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		try {
			String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

			long workflowTaskId = ParamUtil.getLong(
				actionRequest, "workflowTaskId");

			WorkflowTask workflowTask = WorkflowTaskManagerUtil.getWorkflowTask(
				OSBConstants.COMPANY_ID, workflowTaskId);

			if (cmd.equals(Constants.ASSIGN)) {
				validateAssign(workflowTask);
			}
			else if (cmd.equals(Constants.SAVE)) {
				validateSave(actionRequest, workflowTask);
			}

			originalStrutsPortletAction.processAction(
				portletConfig, actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (Validator.isNotNull(e.getMessage())) {
				SessionErrors.add(actionRequest, e.getMessage());
			}
			else {
				Class<?> clazz = e.getClass();

				SessionErrors.add(actionRequest, clazz.getName());
			}

			return;
		}

		long workflowTaskId = ParamUtil.getLong(
			actionRequest, "workflowTaskId");

		WorkflowTask workflowTask = WorkflowTaskManagerUtil.getWorkflowTask(
			OSBConstants.COMPANY_ID, workflowTaskId);

		Indexer indexer = IndexerRegistryUtil.getIndexer(
			WorkflowTask.class.getName());

		indexer.reindex(workflowTask);
	}

	@Override
	public String render(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return originalStrutsPortletAction.render(
			portletConfig, renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws Exception {

		originalStrutsPortletAction.serveResource(
			portletConfig, resourceRequest, resourceResponse);
	}

	protected void validateApproval(
			String className, long classPK,
			Map<String, Serializable> workflowContext)
		throws Exception {

		AccountEntry accountEntry = null;

		if (className.equals(AccountEntry.class.getName())) {
			accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(
				classPK);
		}
		else if (className.equals(OrderEntry.class.getName())) {
			OrderEntry orderEntry = OrderEntryLocalServiceUtil.getOrderEntry(
				classPK);

			accountEntry = orderEntry.getAccountEntry();

			if (accountEntry.getStatus() == WorkflowConstants.STATUS_PENDING) {
				throw new Exception("projectPending");
			}
		}

		String salesforceOpportunityAction = GetterUtil.getString(
			workflowContext.get(
				WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION));

		if (!salesforceOpportunityAction.equals(Constants.UPDATE)) {
			List<WorkflowTask> workflowTasks = WorkflowTaskManagerUtil.search(
				OSBConstants.COMPANY_ID, 0, null, AccountEntry.class.getName(),
				new Long[] {accountEntry.getAccountEntryId()}, null, null,
				false, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (WorkflowTask curWorkflowTask : workflowTasks) {
				Map<String, Serializable> optionalAttributes =
					curWorkflowTask.getOptionalAttributes();

				String curSalesforceOpportunityAction = GetterUtil.getString(
					optionalAttributes.get(
						WorkflowConstants.
							CONTEXT_SALESFORCE_OPPORTUNITY_ACTION));

				if (curSalesforceOpportunityAction.equals(Constants.UPDATE)) {
					throw new Exception("updatesPending");
				}
			}
		}

		if (accountEntry.getStatus() != WorkflowConstants.STATUS_APPROVED) {
			AccountEntryLocalServiceUtil.validate(accountEntry);
		}
	}

	protected void validateAssign(WorkflowTask workflowTask) throws Exception {
		if (workflowTask.isCompleted()) {
			throw new Exception("workflowTaskCompleted");
		}
	}

	protected void validateSave(
			ActionRequest actionRequest, WorkflowTask workflowTask)
		throws Exception {

		if (workflowTask.isCompleted()) {
			throw new Exception("workflowTaskCompleted");
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (themeDisplay.getUserId() != workflowTask.getAssigneeUserId()) {
			throw new PrincipalException();
		}

		String transitionName = ParamUtil.getString(
			actionRequest, "transitionName");

		if (transitionName.equals("approve")) {
			Map<String, Serializable> workflowContext =
				workflowTask.getOptionalAttributes();

			String className = GetterUtil.getString(
				(String)workflowContext.get(
					WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME));
			long classPK = GetterUtil.getLong(
				(String)workflowContext.get(
					WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

			validateApproval(className, classPK, workflowContext);
		}

		if (transitionName.equals("close")) {
			throw new Exception("reservedTransition");
		}

		String comment = ParamUtil.getString(actionRequest, "comment");

		if (transitionName.equals("reject") && Validator.isNull(comment)) {
			throw new RequiredFieldException("comment", "comment");
		}
	}

}