<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");
String tabs2 = ParamUtil.getString(request, "tabs2");

String tabsNames = StringPool.BLANK;

if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID)) {
	tabsNames = "accounts,entitlements,sales,tools";
}
else {
	List<String> tabsNamesList = new ArrayList<String>();

	if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBCustomerConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {
		tabsNamesList.add("accounts");
		tabsNamesList.add("sales");
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

portletURL.setWindowState(WindowState.MAXIMIZED);

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<aui:col md="12">
	<liferay-ui:tabs
		names="<%= tabsNames %>"
		param="tabs1"
		url="<%= portletURL.toString() %>"
	/>

	<liferay-util:include page="/common/exception.jsp" servletContext="<%= application %>" />

	<c:choose>
		<c:when test='<%= tabs1.equals("entitlements") %>'>
			<liferay-util:include page="/admin/entitlements.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs1.equals("sales") %>'>
			<liferay-util:include page="/admin/sales.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs1.equals("tools") %>'>
			<liferay-util:include page="/admin/tools.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/admin/accounts.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</aui:col>