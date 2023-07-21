<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "calendar");

PortletURL portletURL = renderResponse.createRenderURL();
%>

<div id="<portlet:namespace />alert"></div>

<c:if test="<%= themeDisplay.isSignedIn() && !displaySchedulerOnly %>">
	<aui:nav-bar cssClass="collapse-basic-search container-fluid" markupView="lexicon">
		<aui:nav cssClass="navbar-nav">

			<%
			portletURL.setParameter("tabs1", "calendar");
			%>

			<aui:nav-item href="<%= portletURL.toString() %>" label="calendar" selected='<%= tabs1.equals("calendar") %>' />

			<%
			portletURL.setParameter("tabs1", "resources");
			%>

			<aui:nav-item href="<%= portletURL.toString() %>" label="resources" selected='<%= tabs1.equals("resources") %>' />
		</aui:nav>

		<c:if test='<%= tabs1.equals("resources") %>'>
			<aui:nav-bar-search>
				<liferay-portlet:renderURL varImpl="searchURL" />

				<aui:form action="<%= searchURL %>" method="get" name="fm">
					<liferay-portlet:renderURLParams varImpl="searchURL" />
					<aui:input name="mvcPath" type="hidden" value="/view.jsp" />
					<aui:input name="tabs1" type="hidden" value="resources" />

					<liferay-ui:search-form
						page="/calendar_resource_search.jsp"
						servletContext="<%= application %>"
					/>
				</aui:form>
			</aui:nav-bar-search>
		</c:if>
	</aui:nav-bar>
</c:if>

<c:choose>
	<c:when test='<%= tabs1.equals("calendar") %>'>
		<liferay-util:include page="/view_calendar.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("resources") %>'>
		<liferay-util:include page="/view_calendar_resources.jsp" servletContext="<%= application %>" />
	</c:when>
</c:choose>