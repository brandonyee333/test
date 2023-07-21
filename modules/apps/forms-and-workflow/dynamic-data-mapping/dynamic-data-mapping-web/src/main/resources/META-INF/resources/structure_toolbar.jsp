<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SearchContainer searchContainer = (SearchContainer)request.getAttribute(WebKeys.SEARCH_CONTAINER);

long classPK = ParamUtil.getLong(request, "classPK");
%>

<liferay-portlet:renderURL copyCurrentRenderParameters="<%= false %>" varImpl="searchURL">
	<portlet:param name="mvcPath" value="/select_structure.jsp" />
	<portlet:param name="classPK" value="<%= String.valueOf(classPK) %>" />
	<portlet:param name="eventName" value='<%= ParamUtil.getString(request, "eventName", "selectStructure") %>' />
</liferay-portlet:renderURL>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="<%= Validator.isNull(scopeTitle) ? ddmDisplay.getTitle(locale) : scopeTitle %>" selected="<%= true %>" />
	</aui:nav>

	<aui:nav-bar-search searchContainer="<%= searchContainer %>">
		<aui:form action="<%= searchURL %>" method="post" name="searchForm">
			<liferay-util:include page="/structure_search.jsp" servletContext="<%= application %>" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>