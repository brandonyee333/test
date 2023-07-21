<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
WikiNode node = (WikiNode)request.getAttribute(WikiWebKeys.WIKI_NODE);

String navigation = ParamUtil.getString(request, "navigation", "all-pages");

String orderByCol = GetterUtil.getString((String)request.getAttribute("view_pages.jsp-orderByCol"));
String orderByType = GetterUtil.getString((String)request.getAttribute("view_pages.jsp-orderByType"));

PortletURL portletURL = PortletURLUtil.clone(currentURLObj, liferayPortletResponse);

portletURL.setParameter("mvcRenderCommandName", "/wiki/view_pages");
portletURL.setParameter("redirect", currentURL);
portletURL.setParameter("nodeId", String.valueOf(node.getNodeId()));
portletURL.setParameter("navigation", navigation);

Map<String, String> orderColumns = new HashMap<String, String>();

orderColumns.put("modifiedDate", "modified-date");
orderColumns.put("title", "title");
%>

<liferay-frontend:management-bar-navigation
	navigationKeys='<%= new String[] {"all-pages", "draft-pages", "frontpage", "orphan-pages", "recent-changes"} %>'
	portletURL="<%= PortletURLUtil.clone(portletURL, liferayPortletResponse) %>"
/>

<c:if test='<%= navigation.equals("all-pages") %>'>
	<liferay-frontend:management-bar-sort
		orderByCol="<%= orderByCol %>"
		orderByType="<%= orderByType %>"
		orderColumns="<%= orderColumns %>"
		portletURL="<%= portletURL %>"
	/>
</c:if>