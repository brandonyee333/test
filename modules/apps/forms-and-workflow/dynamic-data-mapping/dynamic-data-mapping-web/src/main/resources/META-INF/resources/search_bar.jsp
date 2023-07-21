<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "structures");

long groupId = ParamUtil.getLong(request, "groupId", themeDisplay.getSiteGroupId());
%>

<portlet:renderURL var="portletURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
</portlet:renderURL>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="<%= Validator.isNull(scopeTitle) ? ddmDisplay.getTitle(locale) : scopeTitle %>" selected="<%= true %>" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="<%= portletURL %>" method="post" name="searchForm">
			<liferay-util:include page="/structure_search.jsp" servletContext="<%= application %>" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>