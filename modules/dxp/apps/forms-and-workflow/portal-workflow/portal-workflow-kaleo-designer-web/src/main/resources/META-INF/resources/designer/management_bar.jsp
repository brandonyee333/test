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