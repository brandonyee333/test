<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/polls/init.jsp" %>

<%
PortletURL portletURL = pollsDisplayContext.getBasePortletURL();

portletURL.setParameter("mvcRenderCommandName", "/polls/view");
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= false %>"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews="<%= pollsDisplayContext.getPollsQuestionDisplayViews() %>"
			portletURL="<%= portletURL %>"
			selectedDisplayStyle="<%= pollsDisplayContext.getPollsQuestionDisplayStyle() %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= portletURL %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= pollsDisplayContext.getOrderByCol() %>"
			orderByType="<%= pollsDisplayContext.getOrderByType() %>"
			orderColumns="<%= pollsDisplayContext.getOrderColumns() %>"
			portletURL="<%= portletURL %>"
		/>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>