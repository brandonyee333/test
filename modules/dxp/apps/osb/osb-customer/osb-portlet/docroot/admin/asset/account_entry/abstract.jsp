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
AccountEntryAssetRenderer accountEntryAssetRenderer = (AccountEntryAssetRenderer)request.getAttribute(WebKeys.ASSET_RENDERER);
WorkflowInstance workflowInstance = (WorkflowInstance)request.getAttribute(OSBWebKeys.WORKFLOW_INSTANCE);
WorkflowTask workflowTask = (WorkflowTask)request.getAttribute(OSBWebKeys.WORKFLOW_TASK);

if (workflowInstance == null) {
	workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(company.getCompanyId(), workflowTask.getWorkflowInstanceId());
}

Map<String, Serializable> workflowContext = workflowInstance.getWorkflowContext();

request.setAttribute("header.jsp-accountEntry", accountEntryAssetRenderer.getAssetObject());
request.setAttribute("header.jsp-entryClassName", workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME));
request.setAttribute("header.jsp-salesforceOpportunityAction", workflowContext.get(WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION));
request.setAttribute("header.jsp-salesforceOpportunityStageName", workflowContext.get(WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_STAGE_NAME));
request.setAttribute("header.jsp-salesforceOpportunityType", workflowContext.get(WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_TYPE));
%>

<liferay-util:include page="/admin/asset/header.jsp" servletContext="<%= application %>" />

<%
request.setAttribute("summary.jsp-accountEntry", accountEntryAssetRenderer.getAssetObject());
%>

<liferay-util:include page="/admin/asset/account_entry/summary.jsp" servletContext="<%= application %>" />

<%
request.setAttribute("diff.jsp-newAccountEntryAttributes", workflowContext.get(WorkflowConstants.CONTEXT_NEW_ACCOUNT_ENTRY_ATTRIBUTES));
request.setAttribute("diff.jsp-oldAccountEntryAttributes", workflowContext.get(WorkflowConstants.CONTEXT_OLD_ACCOUNT_ENTRY_ATTRIBUTES));
%>

<liferay-util:include page="/admin/asset/account_entry/diff.jsp" servletContext="<%= application %>" />

<%
request.setAttribute("linked_objects.jsp-existingOrderEntryIds", workflowContext.get(WorkflowConstants.CONTEXT_EXISTING_ORDER_ENTRY_IDS));
request.setAttribute("linked_objects.jsp-missingUsers", workflowContext.get(WorkflowConstants.CONTEXT_MISSING_USERS));
request.setAttribute("linked_objects.jsp-orderEntries", workflowContext.get(WorkflowConstants.CONTEXT_ORDER_ENTRIES));
request.setAttribute("linked_objects.jsp-salesforceOpportunityAction", workflowContext.get(WorkflowConstants.CONTEXT_SALESFORCE_OPPORTUNITY_ACTION));
%>

<liferay-util:include page="/admin/asset/account_entry/linked_objects.jsp" servletContext="<%= application %>" />

<%
request.setAttribute("warning_messages.jsp-warningMessages", workflowContext.get(WorkflowConstants.CONTEXT_WARNING_MESSAGES));
%>

<liferay-util:include page="/admin/asset/warning_messages.jsp" servletContext="<%= application %>" />