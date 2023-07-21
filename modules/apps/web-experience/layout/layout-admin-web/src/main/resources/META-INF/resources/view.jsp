<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SitesUtil.addPortletBreadcrumbEntries(layoutsAdminDisplayContext.getGroup(), layoutsAdminDisplayContext.getPagesName(), layoutsAdminDisplayContext.getRedirectURL(), request, renderResponse);

Layout selLayout = layoutsAdminDisplayContext.getSelLayout();

String targetNode = null;
%>

<%@ include file="/layout_exception.jspf" %>

<div class="container-fluid-1280">
	<div class="lfr-app-column-view manage-view">
		<c:choose>
			<c:when test="<%= layoutsAdminDisplayContext.getSelPlid() > 0 %>">
				<liferay-util:include page="/edit_layout.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/edit_layout_set.jsp" servletContext="<%= application %>" />
			</c:otherwise>
		</c:choose>
	</div>
</div>