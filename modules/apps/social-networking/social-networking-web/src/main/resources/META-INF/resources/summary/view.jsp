<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/summary/init.jsp" %>

<c:choose>
	<c:when test="<%= organization != null %>">
		<%@ include file="/summary/view_organization.jspf" %>
	</c:when>
	<c:when test="<%= user2 != null %>">
		<%@ include file="/summary/view_user.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/summary/view_site.jspf" %>
	</c:otherwise>
</c:choose>