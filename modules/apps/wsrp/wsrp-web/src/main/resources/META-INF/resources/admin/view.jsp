<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "consumers");

PortletURL portletURL = renderResponse.createRenderURL();
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">

		<%
		portletURL.setParameter("tabs1", "consumers");
		%>

		<aui:nav-item href="<%= portletURL.toString() %>" label="consumers" selected='<%= Objects.equals(tabs1, "consumers") %>' />

		<%
		portletURL.setParameter("tabs1", "producers");
		%>

		<aui:nav-item href="<%= portletURL.toString() %>" label="producers" selected='<%= Objects.equals(tabs1, "producers") %>' />
	</aui:nav>
</aui:nav-bar>

<c:choose>
	<c:when test='<%= tabs1.equals("producers") %>'>
		<%@ include file="/admin/producers.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/admin/consumers.jspf" %>
	</c:otherwise>
</c:choose>