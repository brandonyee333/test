<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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