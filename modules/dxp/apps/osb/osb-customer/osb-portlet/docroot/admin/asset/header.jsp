<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
AccountEntry accountEntry = (AccountEntry)request.getAttribute("header.jsp-accountEntry");
String entryClassName = (String)request.getAttribute("header.jsp-entryClassName");
String salesforceOpportunityAction = (String)request.getAttribute("header.jsp-salesforceOpportunityAction");
String salesforceOpportunityStageName = (String)request.getAttribute("header.jsp-salesforceOpportunityStageName");
Integer salesforceOpportunityType = (Integer)request.getAttribute("header.jsp-salesforceOpportunityType");
%>

<style type="text/css">
	h3 {
		text-decoration: underline;
	}

	.opportunity-stage-icon {
		vertical-align: top;
	}

	.portlet-msg-alert {
		background-position: 6px 50%;
	}

	.workflow-task-header {
		background: #CCC;
		border: 1px solid #A4A4A4;
		text-align: center;
	}
</style>

<h1 class="workflow-task-header">
	<%= LanguageUtil.get(request, SalesforceConstants.getOpportunityTaskName(salesforceOpportunityType, salesforceOpportunityAction)) %>
</h1>

<liferay-ui:error exception="<%= AccountEntryCodeException.class %>" message="please-enter-a-valid-code" />
<liferay-ui:error exception="<%= AccountEntryIndustryException.class %>" message="please-enter-a-valid-industry" />
<liferay-ui:error exception="<%= AccountEntryLanguageIdException.class %>" message="please-select-valid-support-languages" />
<liferay-ui:error exception="<%= AccountEntryMaximumCustomersException.class %>" message="the-number-of-contacts-has-exceeded-the-maximum-number-of-contacts" />
<liferay-ui:error exception="<%= AccountEntryNameException.class %>" message="please-enter-a-valid-name" />
<liferay-ui:error exception="<%= AccountEntryPartnerEntryException.class %>" message="please-enter-a-valid-partner-entry" />
<liferay-ui:error exception="<%= AccountEntrySupportRegionException.class %>" message="please-select-valid-support-regions" />
<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-address-line" />
<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />
<liferay-ui:error exception="<%= DuplicateAccountEntryException.class %>" message="please-enter-a-unique-code" />
<liferay-ui:error exception="<%= PrincipalException.class %>" message="this-task-is-assigned-to-another-user" />
<liferay-ui:error exception="<%= RequiredFieldException.class %>" message="please-provide-a-reason-for-rejecting-this-task" />

<liferay-ui:error key="projectPending" message="please-approve-the-pending-project-task-first" />
<liferay-ui:error key="reservedTransition" message="this-transition-is-reserved" />
<liferay-ui:error key="updatesPending" message="please-approve-the-pending-update-tasks" />
<liferay-ui:error key="workflowTaskCompleted" message="this-task-is-already-completed" />

<c:if test="<%= !salesforceOpportunityAction.equals(Constants.UPDATE) %>">

	<%
	List<WorkflowTask> updateWorkflowTasks = new ArrayList<WorkflowTask>();

	List<WorkflowTask> workflowTasks = WorkflowTaskManagerUtil.search(OSBConstants.COMPANY_ID, 0, null, AccountEntry.class.getName(), new Long[] {accountEntry.getAccountEntryId()}, null, null, false, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

	for (WorkflowTask workflowTask : workflowTasks) {
		Map<String, Serializable> optionalAttributes = workflowTask.getOptionalAttributes();

		String curSalesforceOpportunityAction = GetterUtil.getString(optionalAttributes.get(WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION));

		if (curSalesforceOpportunityAction.equals(Constants.UPDATE)) {
			updateWorkflowTasks.add(workflowTask);
		}
	}
	%>

	<c:if test="<%= !updateWorkflowTasks.isEmpty() %>">

		<%
		StringBundler sb = new StringBundler((updateWorkflowTasks.size() * 6) - 1);

		for (WorkflowTask workflowTask : updateWorkflowTasks) {
			PortletURL workflowTaskURL = PortletURLFactoryUtil.create(request, PortletKeys.MY_WORKFLOW_TASK, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

			workflowTaskURL.setParameter("mvcPath", "/edit_workflow_task.jsp");
			workflowTaskURL.setParameter("redirect", currentURL);
			workflowTaskURL.setParameter("workflowTaskId", String.valueOf(workflowTask.getWorkflowTaskId()));

			if (sb.index() > 0) {
				sb.append(StringPool.COMMA_AND_SPACE);
			}

			sb.append("<a href=\"");
			sb.append(workflowTaskURL);
			sb.append("\" target=\"_blank\">");
			sb.append(workflowTask.getWorkflowTaskId());
			sb.append("</a>");
		}
		%>

		<div class="portlet-msg-alert">
			<liferay-ui:message arguments="<%= new Object[] {sb.toString()} %>" key="please-approve-the-following-update-tasks-before-approving-this-task-x" />
		</div>
	</c:if>
</c:if>

<c:if test="<%= entryClassName.equals(OrderEntry.class.getName()) && (accountEntry.getStatus() == WorkflowConstants.STATUS_PENDING) %>">
	<div class="portlet-msg-alert">
		<liferay-ui:message key="please-approve-the-project-before-approving-this-order" />
	</div>
</c:if>

<div>
	<strong><liferay-ui:message key="opportunity-stage" />:</strong> <%= salesforceOpportunityStageName %>

	<c:choose>
		<c:when test="<%= salesforceOpportunityStageName.equals(SalesforceConstants.OPPORTUNITY_STAGE_CLOSED_WON) %>">
			<img class="icon opportunity-stage-icon" src="<%= themeDisplay.getPathThemeImages() %>/messages/success.png" />
		</c:when>
		<c:otherwise>
			<img class="icon opportunity-stage-icon" src="<%= themeDisplay.getPathThemeImages() %>/messages/alert.png" />
		</c:otherwise>
	</c:choose>
</div>