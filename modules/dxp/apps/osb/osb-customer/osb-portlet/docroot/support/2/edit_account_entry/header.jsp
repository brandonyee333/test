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

<%@ include file="/support/2/init.jsp" %>

<%
AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_account_entry.jsp-accountEntry");
String backURL = (String)request.getAttribute("edit_account_entry.jsp-backURL");
PortletURL portletURL = (PortletURL)request.getAttribute("edit_account_entry.jsp-portletURL");
%>

<div class="detail-view-header">
	<div class="back-link clearfix txt-sb">
		<a href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back" /></a>
	</div>

	<div class="header">
		<div class="buttons">
			<liferay-util:include page="/support/2/edit_account_entry/buttons.jsp" servletContext="<%= application %>" />
		</div>

		<div class="page-heading" id="<portlet:namespace/>pageHeading">

			<%
			long customerPlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_SUPPORT_ID, OSBPortletKeys.OSB_SUPPORT);

			PortletURL accountEntryURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_SUPPORT, customerPlid, PortletRequest.RENDER_PHASE);

			accountEntryURL.setParameter("mvcPath", "/support/2/edit_account_entry.jsp");
			accountEntryURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
			accountEntryURL.setParameter("friendly", String.valueOf(Boolean.TRUE));
			%>

			<span class="account-code"><a href="<%= accountEntryURL.toString() %>"><%= accountEntry.getCode() %></a></span>

			<span><%= HtmlUtil.escape(accountEntry.getName()) %></span>
		</div>

		<div class="sub-header">
			<span class="first segment">
				<liferay-ui:message key="support-region" />:

				<span class="txt-sb"><%= HtmlUtil.escape(ListUtil.toString(accountEntry.getSupportRegions(), "name")) %></span>
			</span>
			<span class="spacer"></span>

			<span class="<%= !liferayIncOrg ? "last" : "" %> segment">
				<liferay-ui:message key="industry" />:

				<span class="txt-sb"><%= LanguageUtil.get(request, accountEntry.getIndustryLabel()) %></span>
			</span>

			<c:if test="<%= liferayIncOrg %>">
				<span class="spacer"></span>

				<span class="segment">

					<%
					String tierOnClick = StringPool.BLANK;

					if (OSBAccountEntryPermission.contains(themeDisplay.getPermissionChecker(), accountEntry.getAccountEntryId(), OSBActionKeys.UPDATE_ACCOUNT_TIER)) {
						tierOnClick = renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "tierDisplay', '" + renderResponse.getNamespace() + "tierDropDown');";
					}
					%>

					<div id="<portlet:namespace />tierDisplay" onClick="<%= tierOnClick %>">
						<liferay-ui:message key="tier" />:

						<span class="txt-sb"><%= LanguageUtil.get(request, AccountEntryConstants.getTierLabel(accountEntry.getTier())) %></span>
					</div>

					<div id="<portlet:namespace/>tierDropDown" style="display: none;">
						<aui:select name="tier" onChange='<%= renderResponse.getNamespace() + "updateAccountTier();" %>'>

							<%
							for (int tier : AccountEntryConstants.TIERS) {
							%>

								<aui:option label="<%= AccountEntryConstants.getTierLabel(tier) %>" selected="<%= accountEntry.getTier() == tier %>" value="<%= tier %>" />

							<%
							}
							%>

						</aui:select>

						<%
						String taglibCancel = renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "tierDropDown, '" + renderResponse.getNamespace() + "tierDisplay');";
						%>

						<aui:button cssClass="aui-button-input" name="tierCancel" onclick="<%= taglibCancel %>" value="cancel" />
					</div>
				</span>
				<span class="spacer"></span>

				<%
				WorkflowTask workflowTask = null;

				List<WorkflowTask> workflowTasks = WorkflowTaskManagerUtil.search(OSBConstants.COMPANY_ID, 0, null, AccountEntry.class.getName(), new Long[] {accountEntry.getAccountEntryId()}, null, null, false, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

				if (accountEntry.isPending()) {
					for (WorkflowTask curWorkflowTask : workflowTasks) {
						Map<String, Serializable> optionalAttributes = curWorkflowTask.getOptionalAttributes();

						String entryClassName = GetterUtil.getString(optionalAttributes.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME));

						if (entryClassName.equals(AccountEntry.class.getName())) {
							workflowTask = curWorkflowTask;

							break;
						}
					}
				}
				%>

				<span class="<%= (workflowTask == null) ? "last" : "" %> segment">
					<liferay-ui:message key="status" />:

					<c:choose>
						<c:when test="<%= workflowTask != null %>">

							<%
							Group controlPanelGroup = GroupLocalServiceUtil.getGroup(user.getCompanyId(), GroupConstants.CONTROL_PANEL);

							long controlPanelPlid = LayoutLocalServiceUtil.getDefaultPlid(controlPanelGroup.getGroupId(), true);

							PortletURL workflowTaskURL = PortletURLFactoryUtil.create(request, PortletKeys.MY_WORKFLOW_TASK, controlPanelPlid, PortletRequest.RENDER_PHASE);

							workflowTaskURL.setParameter("struts_action", "/my_workflow_tasks/edit_workflow_task");
							workflowTaskURL.setParameter("redirect", currentURL);
							workflowTaskURL.setParameter("workflowTaskId", String.valueOf(workflowTask.getWorkflowTaskId()));
							%>

							<a href="<%= workflowTaskURL.toString() %>" target="_blank"><%= LanguageUtil.get(request, accountEntry.getStatusLabel()) %></a>
						</c:when>
						<c:otherwise>
							<span class="txt-sb"><%= LanguageUtil.get(request, accountEntry.getStatusLabel()) %></span>
						</c:otherwise>
					</c:choose>

					<c:if test="<%= !workflowTasks.isEmpty() %>">

						<%
						Group controlPanelGroup = GroupLocalServiceUtil.getGroup(user.getCompanyId(), GroupConstants.CONTROL_PANEL);

						long controlPanelPlid = LayoutLocalServiceUtil.getDefaultPlid(controlPanelGroup.getGroupId(), true);

						PortletURL workflowTasksURL = PortletURLFactoryUtil.create(request, PortletKeys.MY_WORKFLOW_TASK, controlPanelPlid, PortletRequest.RENDER_PHASE);

						workflowTasksURL.setParameter("tabs1", "other-assignees");
						workflowTasksURL.setParameter("accountEntryCode", StringPool.QUOTE + accountEntry.getCode() + StringPool.QUOTE);
						%>

						(<a href="<%= workflowTasksURL.toString() %>" target="_blank"><liferay-ui:message key="open-workflow-tasks" /></a>)
					</c:if>
				</span>

				<c:if test="<%= workflowTask != null %>">
					<span class="spacer"></span>

					<span class="last segment">
						<liferay-ui:message key="assigned-to" />:

						<span class="txt-sb">

							<%
							List<WorkflowTaskAssignee> workflowTaskAssignees = workflowTask.getWorkflowTaskAssignees();

							for (int i = 0; i < workflowTaskAssignees.size(); i++) {
								WorkflowTaskAssignee workflowTaskAssignee = workflowTaskAssignees.get(i);

								String assignedToName = StringPool.BLANK;

								String assigneeClassName = workflowTaskAssignee.getAssigneeClassName();

								if (assigneeClassName.equals(Role.class.getName())) {
									Role role = RoleLocalServiceUtil.getRole(workflowTaskAssignee.getAssigneeClassPK());

									assignedToName = role.getName();
								}
								else if (assigneeClassName.equals(User.class.getName())) {
									User curUser = UserLocalServiceUtil.getUser(workflowTaskAssignee.getAssigneeClassPK());

									assignedToName = curUser.getFullName();
								}
							%>

								<%= HtmlUtil.escape(assignedToName) %><%= ((i + 1) < workflowTaskAssignees.size()) ? StringPool.COMMA_AND_SPACE : "" %>

							<%
							}
							%>

						</span>
					</span>
				</c:if>
			</c:if>
		</div>
	</div>
</div>

<%
String tierMessage = HtmlUtil.escapeJS(SupportUtil.getPreferenceValue(locale, "tierMessage_" + accountEntry.getTier()));
%>

<c:if test="<%= Validator.isNotNull(tierMessage) %>">
	<aui:script use="aui-tooltip">
		new A.Tooltip(
			{
				bodyContent: '<%= tierMessage %>',
				trigger: '#<portlet:namespace />tierDisplay'
			}
		).render();
	</aui:script>
</c:if>

<aui:script>
	function <portlet:namespace />toggleForm(hideId, showId) {
		document.getElementById(showId).style.display = '';
		document.getElementById(hideId).style.display = 'none';
	}

	function <portlet:namespace />updateAccountTier() {
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';

		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="updateAccountTier"><portlet:param name="mvcPath" value="/support/2/edit_account_entry.jsp" /></portlet:actionURL>');
	}
</aui:script>