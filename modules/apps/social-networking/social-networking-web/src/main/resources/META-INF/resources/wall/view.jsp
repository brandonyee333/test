<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test="<%= user2 == null %>">
		<div class="alert alert-error">
			<liferay-ui:message key="this-application-only-functions-when-placed-on-a-user-page" />
		</div>
	</c:when>
	<c:otherwise>
		<%@ include file="/wall/view_wall.jspf" %>
	</c:otherwise>
</c:choose>