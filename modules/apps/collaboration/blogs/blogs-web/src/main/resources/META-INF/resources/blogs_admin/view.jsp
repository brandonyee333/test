<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/blogs_admin/init.jsp" %>

<%
String navigation = ParamUtil.getString(request, "navigation", "entries");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/blogs/view");
portletURL.setParameter("navigation", navigation);
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<portlet:renderURL var="viewEntriesURL" />

		<aui:nav-item href="<%= viewEntriesURL %>" label="entries" selected='<%= navigation.equals("entries") %>' />

		<portlet:renderURL var="viewImagesURL">
			<portlet:param name="navigation" value="images" />
		</portlet:renderURL>

		<aui:nav-item href="<%= viewImagesURL %>" label="images" selected='<%= navigation.equals("images") %>' />
	</aui:nav>

	<aui:form action="<%= portletURL %>" name="searchFm">
		<aui:nav-bar-search>
			<liferay-ui:input-search
				markupView="lexicon"
			/>
		</aui:nav-bar-search>
	</aui:form>
</aui:nav-bar>

<c:choose>
	<c:when test='<%= navigation.equals("entries") %>'>
		<liferay-util:include page="/blogs_admin/view_entries.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/blogs_admin/view_images.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>