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

<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>" />

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "assigned-to-me");

String keywords = ParamUtil.getString(request, "keywords");
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

<div class="container-fluid-1280 main-content-body">
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

	<%
	PortletURL iteratorURL = renderResponse.createRenderURL();

	iteratorURL.setParameter("accountEntryCode", accountEntryCode);
	iteratorURL.setParameter("accountEntryName", accountEntryName);
	iteratorURL.setParameter("assigneeClassName", assigneeClassName);
	iteratorURL.setParameter("completed", completedString);
	iteratorURL.setParameter("roleName", roleName);
	iteratorURL.setParameter("salesforceOpportunityType", String.valueOf(salesforceOpportunityType));
	iteratorURL.setParameter("tabs1", tabs1);
	iteratorURL.setParameter("userEmailAddress", userEmailAddress);
	%>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-tasks"
		iteratorURL="<%= iteratorURL %>"
	>
		<liferay-ui:search-container-results>
			<%@ include file="/other_assignees_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.search.Document"
			cssClass="entry-display-style"
			modelVar="document"
		>

			<%
			long workflowTaskId = GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));

			WorkflowTask workflowTask = WorkflowTaskManagerUtil.getWorkflowTask(company.getCompanyId(), workflowTaskId);

			WorkflowInstance workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(company.getCompanyId(), workflowTask.getWorkflowInstanceId());

			Map<String, Serializable> workflowContext = workflowInstance.getWorkflowContext();

			String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
			%>

			<liferay-ui:search-container-row-parameter
				name="workflowTask"
				value="<%= workflowTask %>"
			/>

			<portlet:renderURL var="rowURL">
				<portlet:param name="osbMVCPath" value="/edit_workflow_task.jsp" />
				<portlet:param name="backURL" value="<%= currentURL %>" />
				<portlet:param name="osbWorkflowTaskId" value="<%= String.valueOf(workflowTask.getWorkflowTaskId()) %>" />
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
				value="<%= workflowTaskDisplayContext.getAssetTitle(workflowTask) %>"
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

			<liferay-ui:search-container-column-date
				href="<%= rowURL %>"
				name="last-activity-date"
				value="<%= workflowTaskDisplayContext.getLastActivityDate(workflowTask) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="due-date"
				value='<%= workflowTask.isCompleted() ? LanguageUtil.get(request, "completed") : workflowTaskDisplayContext.getDueDateString(workflowTask) %>'
			/>

			<liferay-ui:search-container-column-jsp
				name="project-differences"
				path="/workflow_task_project_differences.jsp"
			/>

			<c:choose>
				<c:when test="<%= !workflowTask.isCompleted() %>">
					<liferay-ui:search-container-column-jsp
						align="right"
						path="/workflow_task_action.jsp"
					/>
				</c:when>
				<c:otherwise>
					<liferay-ui:search-container-column-text
						value="<%= StringPool.BLANK %>"
					/>
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>