<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-frontend:management-bar
	includeCheckBox="<%= false %>"
>
	<liferay-frontend:management-bar-buttons>
		<c:if test="<%= !workflowInstanceViewDisplayContext.isSearch() %>">
			<liferay-frontend:management-bar-display-buttons
				displayViews="<%= workflowInstanceViewDisplayContext.getDisplayViews() %>"
				portletURL="<%= workflowInstanceViewDisplayContext.getViewPortletURL() %>"
				selectedDisplayStyle="<%= workflowInstanceViewDisplayContext.getDisplayStyle() %>"
			/>
		</c:if>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<portlet:renderURL var="viewURL" />

		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all", "pending", "completed"} %>'
			portletURL="<%= workflowInstanceViewDisplayContext.getViewPortletURL() %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= workflowInstanceViewDisplayContext.getOrderByCol() %>"
			orderByType="<%= workflowInstanceViewDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"last-activity-date", "end-date"} %>'
			portletURL="<%= workflowInstanceViewDisplayContext.getViewPortletURL() %>"
		/>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>