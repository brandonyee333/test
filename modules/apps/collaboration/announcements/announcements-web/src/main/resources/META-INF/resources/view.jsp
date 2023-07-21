<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/announcements/view");
portletURL.setParameter("tabs1", announcementsRequestHelper.getTabs1());
%>

<c:if test="<%= announcementsDisplayContext.isTabs1Visible() %>">
	<liferay-ui:tabs
		names="<%= announcementsDisplayContext.getTabs1Names() %>"
		type="tabs nav-tabs-default"
		url="<%= announcementsDisplayContext.getTabs1PortletURL() %>"
	/>
</c:if>

<c:choose>
	<c:when test="<%= announcementsDisplayContext.isShowManageEntries() %>">
		<%@ include file="/view_manage_entries.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/view_entries.jspf" %>
	</c:otherwise>
</c:choose>