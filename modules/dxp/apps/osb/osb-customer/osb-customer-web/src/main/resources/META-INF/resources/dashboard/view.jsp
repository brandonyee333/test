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

<%@ include file="/dashboard/init.jsp" %>

<%
String keywords = ParamUtil.getString(request, "keywords");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/dashboard/view.jsp");
portletURL.setWindowState(WindowState.MAXIMIZED);
%>

<div class="dashboard-wrapper">
	<div class="dashboard-header">
		<h1>
			<liferay-ui:message key="current-workflow-tasks" />
		</h1>

		<div class="search-form">
			<aui:input cssClass="lfr-search-keywords" id="keywords" label="" name="keywords" onChange='<%= renderResponse.getNamespace() + "updateSearchResults(false);" %>' value="<%= keywords %>" />

			<%
			String iconCss = "toggle-off-click";
			String onclick = renderResponse.getNamespace() + "updateSearchResults(false);";

			if (Validator.isNotNull(keywords)) {
				iconCss = iconCss.concat(" toggle-off-click-active");
				onclick = renderResponse.getNamespace() + "updateSearchResults(true);";
			}
			%>

			<div class="<%= iconCss %>" onclick="<%= onclick %>"></div>
		</div>
	</div>

	<div class="dashboard-content">
		<liferay-ui:search-container
			searchContainer="<%= new WorkflowTaskSearch(renderRequest, portletURL) %>"
		>
			<liferay-ui:search-container-results>
				<%@ include file="/dashboard/workflow_search_results.jspf" %>
			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.workflow.WorkflowTask"
				modelVar="workflowTask"
				stringKey="<%= true %>"
			>
				<liferay-ui:search-container-row-parameter
					name="workflowTask"
					value="<%= workflowTask %>"
				/>

				<%
				WorkflowInstance workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(company.getCompanyId(), workflowTask.getWorkflowInstanceId());

				Map<String, Serializable> workflowContext = workflowInstance.getWorkflowContext();

				String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
				long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

				WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

				JournalArticle journalArticle = JournalArticleLocalServiceUtil.getArticle(classPK);

				long controlPanelPlid = PortalUtil.getControlPanelPlid(OSBCustomerConstants.COMPANY_ID);

				LiferayPortletURL rowURL = PortletURLFactoryUtil.create(renderRequest, JournalPortletKeys.JOURNAL, controlPanelPlid, PortletRequest.RENDER_PHASE);

				rowURL.setDoAsGroupId(journalArticle.getGroupId());
				rowURL.setParameter("struts_action", "/journal/edit_article");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("groupId", String.valueOf(journalArticle.getGroupId()));
				rowURL.setParameter("folderId", String.valueOf(journalArticle.getFolderId()));
				rowURL.setParameter("articleId", journalArticle.getArticleId());
				rowURL.setParameter("version", String.valueOf(journalArticle.getVersion()));
				%>

				<liferay-ui:search-container-column-text
					name="assign-user"
				>
					<c:if test="<%= workflowTask.isAssignedToSingleUser() && (workflowTask.getAssigneeUserId() > 0) %>">

						<%
						User assigneeUser = UserLocalServiceUtil.getUser(workflowTask.getAssigneeUserId());
						%>

						<div class="assign-user">
							<img alt="<%= HtmlUtil.escape(assigneeUser.getFullName()) %>" class="user-avatar" src="<%= assigneeUser.getPortraitURL(themeDisplay) %>" title="<%= HtmlUtil.escape(assigneeUser.getFullName()) %>" />

							<%= HtmlUtil.escape(assigneeUser.getFullName()) %>
						</div>
					</c:if>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="type"
				>
					<%= LanguageUtil.get(request, (journalArticle.getVersion() == 1) ? "new-article" : "edit") %>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="task"
				>
					<span class="task-name" id="<%= workflowTask.getWorkflowTaskId() %>">
						<%= LanguageUtil.get(request, HtmlUtil.escape(workflowTask.getName())) %>
					</span>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="contributor"
				>
					<%= HtmlUtil.escape(journalArticle.getUserName()) %>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="document-lead"
				>
					<%= OSBCustomerWorkFlowTaskUtil.getArticleFirstLeaderName(journalArticle) %>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="title"
				>
					<%= HtmlUtil.escape(workflowHandler.getTitle(classPK, locale)) %>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					buffer="buffer"
					href="<%= rowURL %>"
					name="last-activity-date"
				>

					<%
					List<WorkflowLog> workflowLogs = WorkflowLogManagerUtil.getWorkflowLogsByWorkflowInstance(company.getCompanyId(), workflowTask.getWorkflowInstanceId(), null, 0, 1, WorkflowComparatorFactoryUtil.getLogCreateDateComparator());

					if (workflowLogs.isEmpty()) {
						buffer.append(LanguageUtil.get(request, "never"));
					}
					else {
						WorkflowLog workflowLog = workflowLogs.get(0);

						buffer.append(shortDateFormatDate.format(workflowLog.getCreateDate()));
					}
					%>

				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="due-date"
					orderable="<%= true %>"
				>
					<c:choose>
						<c:when test="<%= workflowTask.getDueDate() == null %>">
							<liferay-ui:message key="never" />
						</c:when>
						<c:otherwise>
							<%= shortDateFormatDate.format(workflowTask.getDueDate()) %>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-jsp
					align="right"
					name="admin-override"
					path="/dashboard/workflow_task_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</div>

<aui:script>
	function <portlet:namespace />updateSearchResults(clearKeywords) {
		var resultsURL = '<portlet:renderURL><portlet:param name="mvcPath" value="/dashboard/view.jsp" /></portlet:renderURL>';

		if (!clearKeywords) {
			var keywords = document.getElementById('<portlet:namespace />keywords').value;

			if (keywords.length > 0) {
				resultsURL += '&<portlet:namespace />keywords=' + keywords;
			}
		}

		window.location.href = resultsURL;
	}
</aui:script>