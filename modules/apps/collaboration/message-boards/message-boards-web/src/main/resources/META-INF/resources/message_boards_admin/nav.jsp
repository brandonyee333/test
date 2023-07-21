<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/message_boards/init.jsp" %>

<%
long categoryId = ParamUtil.getLong(request, "categoryId");
String navItemSelected = ParamUtil.getString(request, "navItemSelected");
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">

		<%
		PortletURL messageBoardsHomeURL = renderResponse.createRenderURL();

		messageBoardsHomeURL.setParameter("mvcRenderCommandName", "/message_boards/view");
		messageBoardsHomeURL.setParameter("tag", StringPool.BLANK);
		%>

		<aui:nav-item href="<%= messageBoardsHomeURL.toString() %>" label="threads" selected='<%= navItemSelected.equals("threads") %>' />

		<%
		PortletURL viewStatisticsURL = renderResponse.createRenderURL();

		viewStatisticsURL.setParameter("mvcRenderCommandName", "/message_boards/view_statistics");
		%>

		<aui:nav-item href="<%= viewStatisticsURL.toString() %>" label="statistics" selected='<%= navItemSelected.equals("statistics") %>' />

		<%
		PortletURL bannedUsersURL = renderResponse.createRenderURL();

		bannedUsersURL.setParameter("mvcRenderCommandName", "/message_boards/view_banned_users");
		%>

		<aui:nav-item href="<%= bannedUsersURL.toString() %>" label="banned-users" selected='<%= navItemSelected.equals("banned-users") %>' />
	</aui:nav>

	<c:if test='<%= GetterUtil.getBoolean(ParamUtil.getString(request, "showSearchFm"), Boolean.FALSE) %>'>
		<liferay-portlet:renderURL varImpl="searchURL">
			<portlet:param name="mvcRenderCommandName" value="/message_boards_admin/search" />
		</liferay-portlet:renderURL>

		<aui:form action="<%= searchURL %>" name="searchFm">
			<aui:nav-bar-search>
				<liferay-portlet:renderURLParams varImpl="searchURL" />
				<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
				<aui:input name="breadcrumbsCategoryId" type="hidden" value="<%= categoryId %>" />
				<aui:input name="searchCategoryId" type="hidden" value="<%= categoryId %>" />

				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</aui:nav-bar-search>
		</aui:form>
	</c:if>
</aui:nav-bar>