<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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