<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/portlet/workflow_tasks/init.jsp" %>

<liferay-util:include page="/html/portlet/workflow_tasks/workflow_task_action.portal.jsp" />

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

boolean updateTask = false;

WorkflowTask workflowTask = null;

if (row != null) {
	workflowTask = (WorkflowTask)row.getParameter("workflowTask");
}
else {
	workflowTask = (WorkflowTask)request.getAttribute(WebKeys.WORKFLOW_TASK);
}

if (workflowTask != null) {
	WorkflowInstance workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(company.getCompanyId(), workflowTask.getWorkflowInstanceId());

	Map<String, Serializable> workflowContext = workflowInstance.getWorkflowContext();

	String salesforceOpportunityAction = GetterUtil.getString(workflowContext.get("osbSalesforceOpportunityAction"));

	if (salesforceOpportunityAction.equals(Constants.UPDATE)) {
		updateTask = true;
	}
}
%>

<aui:script>
	var A = AUI();

	<c:if test="<%= updateTask %>">
		var taskElement = A.one('#<%= workflowTask.getWorkflowTaskId() %>');

		if (taskElement) {
			taskElement.append('(<liferay-ui:message key="update" unicode="<%= true %>" />)');
		}
	</c:if>

	<c:if test="<%= row == null %>">
		var closeButtonElement = A.one('#<portlet:namespace />closetaskChangeStatusLink');

		if (closeButtonElement) {
			var parentElement = closeButtonElement.ancestor('li');

			if (parentElement) {
				parentElement.hide();
			}
		}
	</c:if>
</aui:script>