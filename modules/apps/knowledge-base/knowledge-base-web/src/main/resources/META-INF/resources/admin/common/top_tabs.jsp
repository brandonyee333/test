<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/common/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), KBActionKeys.VIEW) %>">
			<portlet:renderURL var="viewKBObjectsURL">
				<portlet:param name="mvcPath" value="/admin/view.jsp" />
			</portlet:renderURL>

			<aui:nav-item href="<%= viewKBObjectsURL %>" label="articles" selected='<%= !mvcPath.equals("/admin/view_suggestions.jsp") %>' />
		</c:if>

		<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, KBActionKeys.VIEW_SUGGESTIONS) %>">
			<portlet:renderURL var="viewKBSuggestionsURL">
				<portlet:param name="mvcPath" value="/admin/view_suggestions.jsp" />
			</portlet:renderURL>

			<aui:nav-item href="<%= viewKBSuggestionsURL %>" label="suggestions" selected='<%= mvcPath.equals("/admin/view_suggestions.jsp") %>' />
		</c:if>
	</aui:nav>

	<aui:nav-bar-search>
		<liferay-portlet:renderURL varImpl="searchURL">
			<portlet:param name="mvcPath" value="/admin/search.jsp" />
		</liferay-portlet:renderURL>

		<aui:form action="<%= searchURL %>" method="get" name="searchFm">
			<liferay-portlet:renderURLParams varImpl="searchURL" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

			<liferay-ui:input-search
				id="keywords"
				markupView="lexicon"
			/>
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>