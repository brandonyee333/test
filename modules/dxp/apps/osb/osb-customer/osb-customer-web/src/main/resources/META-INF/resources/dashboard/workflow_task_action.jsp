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

<%@ include file="/dashboard/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	redirect = currentURL;
}

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

String randomId = StringPool.BLANK;

WorkflowTask workflowTask = null;

if (row != null) {
	randomId = StringUtil.randomId();

	workflowTask = (WorkflowTask)row.getParameter("workflowTask");
}
else {
	workflowTask = (WorkflowTask)request.getAttribute(OSBCustomerWebKeys.WORKFLOW_TASK);
}

long[] pooledActorsIds = WorkflowTaskManagerUtil.getPooledActorsIds(company.getCompanyId(), workflowTask.getWorkflowTaskId());
%>

<liferay-ui:icon-menu showExpanded="<%= (row == null) %>" showWhenSingleIcon="<%= (row == null) %>">
	<c:if test="<%= !workflowTask.isCompleted() && OSBCustomerWorkFlowTaskUtil.isAssignedToUser(workflowTask, user) %>">

		<%
		List<String> transitionNames = WorkflowTaskManagerUtil.getNextTransitionNames(company.getCompanyId(), user.getUserId(), workflowTask.getWorkflowTaskId());

		for (String transitionName : transitionNames) {
			String message = "proceed";

			if (Validator.isNotNull(transitionName)) {
				message = HtmlUtil.escape(transitionName);
			}
		%>

			<liferay-portlet:actionURL name="completeTask" var="editURL">
				<portlet:param name="mvcPath" value="/dashboard/view.jsp" />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="workflowTaskId" value="<%= String.valueOf(workflowTask.getWorkflowTaskId()) %>" />
				<portlet:param name="assigneeUserId" value="<%= String.valueOf(workflowTask.getAssigneeUserId()) %>" />

				<c:if test="<%= transitionName != null %>">
					<portlet:param name="transitionName" value="<%= transitionName %>" />
				</c:if>
			</liferay-portlet:actionURL>

			<liferay-ui:icon
				cssClass='<%= "workflow-task-" + randomId + " task-change-status-link" %>'
				id='<%= randomId + HtmlUtil.escapeAttribute(transitionName) + "taskChangeStatusLink" %>'
				image="../aui/random"
				message="<%= message %>"
				method="get"
				url="<%= editURL.toString() %>"
			/>

		<%
		}
		%>

	</c:if>

	<c:if test="<%= !workflowTask.isCompleted() && !OSBCustomerWorkFlowTaskUtil.isAssignedToUser(workflowTask, user) %>">
		<liferay-portlet:actionURL name="assignTask" var="assignToMeURL">
			<portlet:param name="mvcPath" value="/dashboard/view.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="workflowTaskId" value="<%= String.valueOf(workflowTask.getWorkflowTaskId()) %>" />
			<portlet:param name="assigneeUserId" value="<%= String.valueOf(user.getUserId()) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon
			cssClass='<%= "workflow-task-" + randomId + " task-assign-to-me-link" %>'
			id='<%= randomId + "taskAssignToMeLink" %>'
			image="assign"
			message="assign-to-me"
			method="get"
			url="<%= assignToMeURL.toString() %>"
		/>
	</c:if>

	<c:if test="<%= OSBCustomerWorkFlowTaskUtil.hasOtherAssignees(pooledActorsIds, workflowTask, user) %>">
		<liferay-portlet:actionURL name="assignTask" var="assignURL">
			<portlet:param name="mvcPath" value="/dashboard/view.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="workflowTaskId" value="<%= String.valueOf(workflowTask.getWorkflowTaskId()) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon
			cssClass='<%= "workflow-task-" + randomId + " task-assign-link" %>'
			id='<%= randomId + "taskAssignLink" %>'
			image="assign"
			message="assign-to-..."
			method="get"
			url="<%= assignURL.toString() %>"
		/>
	</c:if>

	<liferay-portlet:actionURL name="updateTask" var="updateDueDateURL">
		<portlet:param name="mvcPath" value="/dashboard/view.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="workflowTaskId" value="<%= StringUtil.valueOf(workflowTask.getWorkflowTaskId()) %>" />
	</liferay-portlet:actionURL>

	<liferay-ui:icon
		cssClass='<%= "workflow-task-" + randomId + " task-due-date-link" %>'
		id='<%= randomId + "taskDueDateLink" %>'
		image="time"
		message="update-due-date"
		method="get"
		url="<%= updateDueDateURL.toString() %>"
	/>
</liferay-ui:icon-menu>

<div class="hide" id="<%= randomId %>updateAsignee">
	<c:if test="<%= OSBCustomerWorkFlowTaskUtil.hasOtherAssignees(pooledActorsIds, workflowTask, user) %>">
		<aui:select label="assign-to" name="assigneeUserId">

			<%
			for (long pooledActorId : pooledActorsIds) {
				if (pooledActorId == user.getUserId()) {
					continue;
				}
			%>

				<aui:option label="<%= HtmlUtil.escape(PortalUtil.getUserName(pooledActorId, StringPool.BLANK)) %>" selected="<%= workflowTask.getAssigneeUserId() == pooledActorId %>" value="<%= String.valueOf(pooledActorId) %>" />

			<%
			}
			%>

		</aui:select>
	</c:if>
</div>

<div class="hide" id="<%= randomId %>updateAsigneeToMe">
	<aui:input name="asigneeUserId" type="hidden" useNamespace="<%= false %>" value="<%= user.getUserId() %>" />
</div>

<div class="hide" id="<%= randomId %>updateDueDate">
	<aui:input bean="<%= workflowTask %>" model="<%= WorkflowTask.class %>" name="dueDate" />
</div>

<div class="hide" id="<%= randomId %>updateComments">
	<aui:input cols="55" name="comment" rows="10" type="textarea" useNamespace="<%= false %>" />
</div>

<aui:script use="liferay-workflow-tasks">
	var onTaskClickFn = A.rbind('onTaskClick', Liferay.WorkflowTasks, '<%= randomId %>');

	<c:if test="<%= !workflowTask.isCompleted() && OSBCustomerWorkFlowTaskUtil.isAssignedToUser(workflowTask, user) %>">

		<%
		List<String> transitionNames = WorkflowTaskManagerUtil.getNextTransitionNames(company.getCompanyId(), user.getUserId(), workflowTask.getWorkflowTaskId());

		for (String transitionName : transitionNames) {
			String message = "proceed";

			if (Validator.isNotNull(transitionName)) {
				message = transitionName;
			}
		%>

			Liferay.delegateClick('<portlet:namespace /><%= randomId + HtmlUtil.escapeJS(transitionName) %>taskChangeStatusLink', onTaskClickFn);

		<%
		}
		%>

	</c:if>

	Liferay.delegateClick('<portlet:namespace /><%= randomId %>taskAssignToMeLink', onTaskClickFn);
	Liferay.delegateClick('<portlet:namespace /><%= randomId %>taskAssignLink', onTaskClickFn);
	Liferay.delegateClick('<portlet:namespace /><%= randomId %>taskDueDateLink', onTaskClickFn);
</aui:script>