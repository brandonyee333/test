<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/designer/init.jsp" %>

<%
PortletURL portletURL = kaleoDesignerDisplayContext.getBasePortletURL();

portletURL.setParameter("mvcPath", "/designer/view.jsp");
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= false %>"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews="<%= kaleoDesignerDisplayContext.getKaleoDraftDefinitionDisplayViews() %>"
			portletURL="<%= portletURL %>"
			selectedDisplayStyle="<%= kaleoDesignerDisplayContext.getKaleoDraftDefinitionDisplayStyle() %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= portletURL %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= kaleoDesignerDisplayContext.getOrderByCol() %>"
			orderByType="<%= kaleoDesignerDisplayContext.getOrderByType() %>"
			orderColumns="<%= kaleoDesignerDisplayContext.getOrderColumns() %>"
			portletURL="<%= portletURL %>"
		/>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>