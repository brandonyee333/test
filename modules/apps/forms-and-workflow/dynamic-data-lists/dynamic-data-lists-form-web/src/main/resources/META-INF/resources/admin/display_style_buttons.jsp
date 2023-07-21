<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
PortletURL displayStyleURL = renderResponse.createRenderURL();

int delta = ParamUtil.getInteger(request, "delta");

if (delta > 0) {
	displayStyleURL.setParameter("delta", String.valueOf(delta));
}
%>

<liferay-frontend:management-bar-display-buttons
	displayViews="<%= ddlFormAdminDisplayContext.getDisplayViews() %>"
	portletURL="<%= displayStyleURL %>"
	selectedDisplayStyle="<%= ddlFormAdminDisplayContext.getDisplayStyle() %>"
/>