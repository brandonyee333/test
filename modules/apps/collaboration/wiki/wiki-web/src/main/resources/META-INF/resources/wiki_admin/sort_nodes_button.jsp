<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
String orderByCol = GetterUtil.getString((String)request.getAttribute("view.jsp-orderByCol"));
String orderByType = GetterUtil.getString((String)request.getAttribute("view.jsp-orderByType"));

Map<String, String> orderColumns = new HashMap<String, String>();

orderColumns.put("modifiedDate", "last-post-date");
orderColumns.put("name", "name");

PortletURL sortURL = renderResponse.createRenderURL();

sortURL.setParameter("mvcRenderCommandName", "/wiki_admin/view");
%>

<liferay-frontend:management-bar-sort
	orderByCol="<%= orderByCol %>"
	orderByType="<%= orderByType %>"
	orderColumns="<%= orderColumns %>"
	portletURL="<%= sortURL %>"
/>