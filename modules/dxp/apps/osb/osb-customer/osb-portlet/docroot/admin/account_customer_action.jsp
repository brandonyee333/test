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

AccountCustomer accountCustomer = (AccountCustomer)row.getObject();

PortletURL portletURL = (PortletURL)request.getAttribute("edit_account_entry_customers.jsp-portletURL");
%>

<liferay-ui:icon-menu>
	<liferay-ui:icon
		message='<%= (accountCustomer.getAccountCustomerId() > 0) ? "update" : "add" %>'
		url='<%= "javascript:" + renderResponse.getNamespace() + "updateAccountCustomer(" + accountCustomer.getAccountCustomerId() + ");" %>'
	/>

	<c:if test="<%= accountCustomer.getAccountCustomerId() > 0 %>">
		<portlet:actionURL name="deleteAccountCustomer" var="deleteAccountCustomerURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="accountCustomerId" value="<%= String.valueOf(accountCustomer.getAccountCustomerId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			message="remove"
			url="<%= deleteAccountCustomerURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>