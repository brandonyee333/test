<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Organization organization = (Organization)row.getObject();

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<liferay-ui:icon-menu>
	<portlet:actionURL name="resyncEntitlement" var="resyncURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="organizationId" value="<%= String.valueOf(organization.getOrganizationId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		confirmation="are-you-sure-you-want-to-resync"
		message="resync"
		url="<%= resyncURL %>"
	/>
</liferay-ui:icon-menu>