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
String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "assigned-to-me");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view.jsp");
portletURL.setParameter("tabs1", tabs1);
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="my-workflow-tasks" selected="<%= true %>" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="<%= portletURL.toString() %>" method="post" name="fm1">
			<liferay-ui:search-form
				page="/search.jsp"
				servletContext="<%= application %>"
			/>
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="nav-bar-workflow nav-tabs nav-tabs-default">
		<portlet:renderURL var="viewAssignedToMyRolesURL">
			<portlet:param name="mvcPath" value="/view.jsp" />
			<portlet:param name="tabs1" value="assigned-to-my-roles" />
		</portlet:renderURL>

		<aui:nav-item href="<%= viewAssignedToMyRolesURL %>" label="assigned-to-my-roles" selected='<%= tabs1.equals("assigned-to-my-roles") %>' />

		<portlet:renderURL var="viewAssignedToMeURL">
			<portlet:param name="mvcPath" value="/view.jsp" />
			<portlet:param name="tabs1" value="assigned-to-me" />
		</portlet:renderURL>

		<aui:nav-item href="<%= viewAssignedToMeURL %>" label="assigned-to-me" selected='<%= tabs1.equals("assigned-to-me") %>' />

		<portlet:renderURL var="viewOtherAssigneesURL">
			<portlet:param name="mvcPath" value="/other_assignees.jsp" />
			<portlet:param name="tabs1" value="other-assignees" />
		</portlet:renderURL>

		<aui:nav-item href="<%= viewOtherAssigneesURL %>" label="other-assignees" selected='<%= tabs1.equals("other-assignees") %>' />
	</aui:nav>
</aui:nav-bar>

<c:if test='<%= !tabs1.equals("other-assignees") %>'>
	<liferay-frontend:management-bar
		includeCheckBox="<%= false %>"
	>
		<liferay-frontend:management-bar-buttons>
			<c:if test="<%= !workflowTaskDisplayContext.isSearch() %>">
				<liferay-frontend:management-bar-display-buttons
					displayViews="<%= workflowTaskDisplayContext.getDisplayViews() %>"
					portletURL="<%= workflowTaskDisplayContext.getPortletURL() %>"
					selectedDisplayStyle="<%= workflowTaskDisplayContext.getDisplayStyle() %>"
				/>
			</c:if>
		</liferay-frontend:management-bar-buttons>

		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-navigation
				navigationKeys='<%= new String[] {"all", "pending", "completed"} %>'
				portletURL="<%= PortletURLUtil.clone(portletURL, liferayPortletResponse) %>"
			/>

			<liferay-frontend:management-bar-sort
				orderByCol="<%= workflowTaskDisplayContext.getOrderByCol() %>"
				orderByType="<%= workflowTaskDisplayContext.getOrderByType() %>"
				orderColumns='<%= new String[] {"last-activity-date", "due-date"} %>'
				portletURL="<%= workflowTaskDisplayContext.getPortletURL() %>"
			/>
		</liferay-frontend:management-bar-filters>
	</liferay-frontend:management-bar>
</c:if>