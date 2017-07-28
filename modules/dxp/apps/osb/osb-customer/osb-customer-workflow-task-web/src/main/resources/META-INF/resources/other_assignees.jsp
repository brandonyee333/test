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

<%@ page import="com.liferay.portal.kernel.search.Document" %>
<%@ page import="com.liferay.portal.kernel.search.Field" %>
<%@ page import="com.liferay.portal.kernel.workflow.WorkflowTaskAssignee" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "pending");

String accountEntryCode = ParamUtil.getString(request, "accountEntryCode");
String accountEntryName = ParamUtil.getString(request, "accountEntryName");
String assigneeClassName = ParamUtil.getString(request, "assigneeClassName");
String roleName = ParamUtil.getString(request, "roleName");
int salesforceOpportunityType = ParamUtil.getInteger(request, "salesforceOpportunityType");
String userEmailAddress = ParamUtil.getString(request, "userEmailAddress");

Boolean completed = null;

String completedString = ParamUtil.getString(request, "completed");

if (Validator.isNotNull(completedString)) {
	completed = GetterUtil.getBoolean(completedString);
}

Role role = null;
User searchUser = null;

if (assigneeClassName.equals(Role.class.getName())) {
	if (Validator.isNotNull(roleName)) {
		role = RoleLocalServiceUtil.fetchRole(company.getCompanyId(), roleName);
	}
}
else if (assigneeClassName.equals(User.class.getName())) {
	if (Validator.isNotNull(userEmailAddress)) {
		searchUser = UserLocalServiceUtil.fetchUserByEmailAddress(company.getCompanyId(), userEmailAddress);
	}
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
%>

<liferay-ui:tabs
	names="pending,completed,other-assignees"
	portletURL="<%= portletURL %>"
/>

<c:choose>
	<c:when test="<%= assigneeClassName.equals(Role.class.getName()) && Validator.isNotNull(roleName) && (role == null) %>">
		<div class="portlet-msg-error">
			<liferay-ui:message key="that-role-does-not-exist" />
		</div>
	</c:when>
	<c:when test="<%= assigneeClassName.equals(User.class.getName()) && Validator.isNotNull(userEmailAddress) && (searchUser == null) %>">
		<div class="portlet-msg-error">
			<liferay-ui:message key="that-user-does-not-exist" />
		</div>
	</c:when>
</c:choose>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
	<div class="taglib-search-toggle">
		<table class="lfr-table">
		<tr>
			<td>
				<aui:input label="project-code" name="accountEntryCode" size="20" value="<%= accountEntryCode %>" />
			</td>
			<td>
				<aui:input label="project-name" name="accountEntryName" size="20" value="<%= accountEntryName %>" />
			</td>
			<td>
				<aui:select label="assignee-type" name="assigneeClassName" onChange='<%= renderResponse.getNamespace() + "selectAssigneeClassName(this.value);" %>'>
					<aui:option label="" value="" />
					<aui:option label="role" selected="<%= assigneeClassName.equals(Role.class.getName()) %>" value="<%= Role.class.getName() %>" />
					<aui:option label="user" selected="<%= assigneeClassName.equals(User.class.getName()) %>" value="<%= User.class.getName() %>" />
				</aui:select>
			</td>
			<td>
				<div class="<%= assigneeClassName.equals(Role.class.getName()) ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />assigneeClassNameRole">
					<aui:input name="roleName" size="20" value="<%= roleName %>" />
				</div>

				<div class="<%= assigneeClassName.equals(User.class.getName()) ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />assigneeClassNameUser">
					<aui:input name="userEmailAddress" size="20" value="<%= userEmailAddress %>" />
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<aui:select label="completed" name="completed">
					<aui:option label="" value="" />
					<aui:option label="yes" selected="<%= (completed != null) && completed %>" value="<%= true %>" />
					<aui:option label="no" selected="<%= (completed != null) && !completed %>" value="<%= false %>" />
				</aui:select>
			</td>
			<td colspan="3">
				<aui:select label="opportunity-type" name="salesforceOpportunityType">
					<aui:option label="" value="" />
					<aui:option label="existing-business" selected="<%= salesforceOpportunityType == 1 %>" value="1" />
					<aui:option label="new-business" selected="<%= salesforceOpportunityType == 2 %>" value="2" />
					<aui:option label="new-project-existing-business" selected="<%= salesforceOpportunityType == 3 %>" value="3" />
					<aui:option label="renewal" selected="<%= salesforceOpportunityType == 4 %>" value="4" />
				</aui:select>
			</td>
		</tr>
		</table>

		<aui:button-row>
			<aui:button type="submit" value="search" />
		</aui:button-row>
	</div>
</aui:form>

<div class="separator"></div>

<%
PortletURL iteratorURL = renderResponse.createRenderURL();

iteratorURL.setParameter("tabs1", tabs1);

iteratorURL.setParameter("accountEntryCode", accountEntryCode);
iteratorURL.setParameter("accountEntryName", accountEntryName);
iteratorURL.setParameter("assigneeClassName", assigneeClassName);
iteratorURL.setParameter("completed", completedString);
iteratorURL.setParameter("roleName", roleName);
iteratorURL.setParameter("salesforceOpportunityType", String.valueOf(salesforceOpportunityType));
iteratorURL.setParameter("userEmailAddress", userEmailAddress);
%>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-tasks"
	iteratorURL="<%= iteratorURL %>"
>
	<liferay-ui:search-container-results>
		<%@ include file="/html/portlet/workflow_tasks/other_assignees_search_results.jspf" %>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.search.Document"
		modelVar="document"
	>

		<%
		long workflowTaskId = GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));

		WorkflowTask workflowTask = WorkflowTaskManagerUtil.getWorkflowTask(company.getCompanyId(), workflowTaskId);

		WorkflowInstance workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(company.getCompanyId(), workflowTask.getWorkflowInstanceId());

		Map<String, Serializable> workflowContext = workflowInstance.getWorkflowContext();

		String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
		long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);
		%>

		<liferay-ui:search-container-row-parameter
			name="workflowTask"
			value="<%= workflowTask %>"
		/>

		<portlet:renderURL var="rowURL">
			<portlet:param name="struts_action" value="/workflow_tasks/edit_workflow_task" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="workflowTaskId" value="<%= String.valueOf(workflowTask.getWorkflowTaskId()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="opportunity-type"
			translate="<%= true %>"
			value='<%= document.get("salesforceOpportunityTaskName") %>'
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="asset-title"
			value="<%= HtmlUtil.escape(workflowHandler.getTitle(classPK, locale)) %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="assigned-to"
		>

			<%
			List<WorkflowTaskAssignee> workflowTaskAssignees = workflowTask.getWorkflowTaskAssignees();

			for (WorkflowTaskAssignee workflowTaskAssignee : workflowTaskAssignees) {
				String curAssigneeClassName = workflowTaskAssignee.getAssigneeClassName();

				String assigneeName = StringPool.BLANK;

				if (curAssigneeClassName.equals(Role.class.getName())) {
					Role curRole = RoleLocalServiceUtil.fetchRole(workflowTaskAssignee.getAssigneeClassPK());

					if (curRole != null) {
						assigneeName = curRole.getName();
					}
				}
				else if (curAssigneeClassName.equals(User.class.getName())) {
					User curUser = UserLocalServiceUtil.fetchUser(workflowTaskAssignee.getAssigneeClassPK());

					if (curUser != null) {
						assigneeName = curUser.getFullName();
					}
				}
			%>

				<%= HtmlUtil.escape(assigneeName) %><br />

			<%
			}
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			href="<%= rowURL %>"
			name="last-activity-date"
		>

			<%
			List<WorkflowLog> workflowLogs = WorkflowLogManagerUtil.getWorkflowLogsByWorkflowInstance(company.getCompanyId(), workflowTask.getWorkflowInstanceId(), null, 0, 1, WorkflowComparatorFactoryUtil.getLogCreateDateComparator());

			if (workflowLogs.isEmpty()) {
				buffer.append(LanguageUtil.get(pageContext, "never"));
			}
			else {
				WorkflowLog workflowLog = workflowLogs.get(0);

				buffer.append(dateFormatDateTime.format(workflowLog.getCreateDate()));
			}
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			href="<%= rowURL %>"
			name="due-date"
		>

			<%
			if (workflowTask.isCompleted()) {
				buffer.append(LanguageUtil.get(pageContext, "completed"));
			}
			else if (workflowTask.getDueDate() == null) {
				buffer.append(LanguageUtil.get(pageContext, "never"));
			}
			else {
				buffer.append(dateFormatDateTime.format(workflowTask.getDueDate()));
			}
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/portlet/workflow_tasks/workflow_task_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<aui:script>
	function <portlet:namespace />selectAssigneeClassName(assigneeClassName) {
		var A = AUI();

		if (assigneeClassName == '<%= Role.class.getName() %>') {
			A.one('#<portlet:namespace />assigneeClassNameRole').show();
			A.one('#<portlet:namespace />assigneeClassNameUser').hide();
		}
		else if (assigneeClassName == '<%= User.class.getName() %>') {
			A.one('#<portlet:namespace />assigneeClassNameRole').hide();
			A.one('#<portlet:namespace />assigneeClassNameUser').show();
		}
		else {
			A.one('#<portlet:namespace />assigneeClassNameRole').hide();
			A.one('#<portlet:namespace />assigneeClassNameUser').hide();
		}
	}
</aui:script>