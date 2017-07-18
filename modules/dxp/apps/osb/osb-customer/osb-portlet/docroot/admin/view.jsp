<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");
String tabs2 = ParamUtil.getString(request, "tabs2");
String tabs3 = ParamUtil.getString(request, "tabs3");

String tabsNames = StringPool.BLANK;

if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {
	tabsNames = "projects,partners,sales,support,tools";
}
else {
	List<String> tabsNamesList = new ArrayList<String>();

	if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {
		tabsNamesList.add("projects");
		tabsNamesList.add("partners");
		tabsNamesList.add("sales");
	}

	if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_SUPPORT_ADMIN_ID)) {
		tabsNamesList.add("support");
	}

	if (!tabsNamesList.contains(tabs1)) {
		tabs1 = tabsNamesList.get(0);
	}

	tabsNames = StringUtil.merge(tabsNamesList);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/view.jsp");
portletURL.setParameter("tabs1", tabs1);

if (Validator.isNotNull(tabs2)) {
	portletURL.setParameter("tabs2", tabs2);
}

if (Validator.isNotNull(tabs3)) {
	portletURL.setParameter("tabs3", tabs3);
}

portletURL.setWindowState(WindowState.MAXIMIZED);

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<liferay-ui:tabs
	names="<%= tabsNames %>"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("partners") %>'>
		<liferay-util:include page="/admin/partners.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("sales") %>'>
		<liferay-util:include page="/admin/sales.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("support") %>'>
		<liferay-util:include page="/admin/support.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("tools") %>'>
		<liferay-util:include page="/admin/tools.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/admin/accounts.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>