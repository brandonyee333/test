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

<%@ include file="/workflow_task_action.portal.jsp" %>

<%
boolean updateTask = false;

if (workflowTask != null) {
	WorkflowInstance workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(company.getCompanyId(), workflowTask.getWorkflowInstanceId());

	Map<String, Serializable> workflowContext = workflowInstance.getWorkflowContext();

	String salesforceOpportunityAction = GetterUtil.getString(workflowContext.get("osbSalesforceOpportunityAction"));

	if (salesforceOpportunityAction.equals(Constants.UPDATE)) {
		updateTask = true;
	}
}
%>

<aui:script use="aui-base">
	var A = AUI();

	<c:if test="<%= updateTask %>">
		var taskElement = A.one('#<%= workflowTask.getWorkflowTaskId() %>');

		if (taskElement) {
			taskElement.append('(<liferay-ui:message key="update" unicode="<%= true %>" />)');
		}
	</c:if>

	var closeButtonElement = A.one('#<portlet:namespace /><%= randomId %>closetaskChangeStatusLink');

	if (closeButtonElement) {
		var parentElement = closeButtonElement.ancestor('li');

		if (parentElement) {
			parentElement.hide();
		}
	}
</aui:script>