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

AccountWorker accountWorker = (AccountWorker)row.getObject();

PortletURL portletURL = (PortletURL)request.getAttribute("edit_account_entry_workers.jsp-portletURL");
%>

<liferay-ui:icon-menu>
	<liferay-ui:icon
		message='<%= (accountWorker.getAccountWorkerId() > 0) ? "update" : "add" %>'
		url='<%= "javascript:" + renderResponse.getNamespace() + "updateAccountWorker(" + accountWorker.getAccountWorkerId() + ");" %>'
	/>

	<c:if test="<%= accountWorker.getAccountWorkerId() > 0 %>">
		<portlet:actionURL name="deleteAccountWorker" var="deleteAccountWorkerURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="accountWorkerId" value="<%= String.valueOf(accountWorker.getAccountWorkerId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			message="remove"
			url="<%= deleteAccountWorkerURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>