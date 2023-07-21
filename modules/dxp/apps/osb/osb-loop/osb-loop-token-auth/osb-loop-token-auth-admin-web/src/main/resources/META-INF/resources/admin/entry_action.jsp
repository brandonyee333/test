<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

TokenAuthEntry tokenAuthEntry = (TokenAuthEntry)row.getObject();

long tokenAuthEntryId = tokenAuthEntry.getTokenAuthEntryId();
%>

<c:if test="<%= TokenAuthPermission.contains(permissionChecker, tokenAuthEntry, ActionKeys.DELETE) %>">
	<liferay-ui:icon-menu>
		<liferay-portlet:actionURL name="deleteTokenAuthEntry" var="deleteURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="tokenAuthEntryId" value="<%= String.valueOf(tokenAuthEntryId) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon-delete
			confirmation="are-you-sure-you-want-to-delete-this-token"
			url="<%= deleteURL %>"
		/>
	</liferay-ui:icon-menu>
</c:if>