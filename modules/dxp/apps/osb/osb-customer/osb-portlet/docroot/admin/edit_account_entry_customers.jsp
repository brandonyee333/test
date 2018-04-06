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
String backURL = ParamUtil.getString(request, "backURL");

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

AccountEntry accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_account_entry_customers.jsp");
portletURL.setParameter("backURL", backURL);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));

request.setAttribute("edit_account_entry_customers.jsp-portletURL", portletURL);
%>

<portlet:actionURL name="updateAccountCustomer" var="updateAccountCustomerURL">
	<portlet:param name="mvcPath" value="/admin/edit_account_entry_customers.jsp" />
	<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
	<portlet:param name="backURL" value="<%= backURL %>" />
</portlet:actionURL>

<aui:form action="<%= updateAccountCustomerURL %>" method="post">
	<aui:input name="accountCustomerId" type="hidden" />
	<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />

	<liferay-ui:message arguments="<%= HtmlUtil.escape(accountEntry.getName()) %>" key="edit-customers-for-project-x" />

	<br /><br />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="users"
	/>

	<liferay-ui:error exception="<%= AccountEntryMaximumCustomersException.class %>" message="the-number-of-contacts-has-exceeded-the-maximum-number-of-contacts" />
	<liferay-ui:error exception="<%= DuplicateAccountCustomerException.class %>" message="the-user-is-already-a-customer" />
	<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="the-user-could-not-be-found" />
	<liferay-ui:error exception="<%= RemoteServiceException.class %>" message="there-was-an-error-connecting-to-web.liferay.com" />

	<liferay-ui:search-container>

		<%
		List<AccountCustomer> accountCustomers = ListUtil.copy(AccountCustomerLocalServiceUtil.getAccountCustomers(accountEntryId));

		accountCustomers.add(0, new AccountCustomerImpl());
		%>

		<liferay-ui:search-container-results
			results="<%= accountCustomers %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.AccountCustomer"
			escapedModel="<%= true %>"
			keyProperty="accountCustomerId"
			modelVar="accountCustomer"
		>

			<%
			User curUser = UserLocalServiceUtil.fetchUser(accountCustomer.getUserId());

			if ((accountCustomer.getAccountCustomerId() > 0) && ((curUser == null) || !curUser.isActive())) {
				row.setClassName("inactive");
			}
			%>

			<liferay-ui:search-container-column-text
				name="name"
			>
				<c:choose>
					<c:when test="<%= curUser != null %>">
						<%= HtmlUtil.escape(curUser.getFullName()) %>
					</c:when>
					<c:when test="<%= accountCustomer.getUserId() > 0 %>">
						<%= accountCustomer.getUserId() %>
					</c:when>
					<c:otherwise>
						<strong><liferay-ui:message key="new-user" /></strong>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="screen-name"
				value='<%= (curUser != null) ? curUser.getScreenName() : "" %>'
			/>

			<liferay-ui:search-container-column-text
				name="email-address"
			>
				<c:choose>
					<c:when test="<%= curUser != null %>">
						<%= HtmlUtil.escape(curUser.getEmailAddress()) %>
					</c:when>
					<c:when test="<%= accountCustomer.getAccountCustomerId() <= 0 %>">
						<aui:input label="" name="emailAddress" />
					</c:when>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="notifications"
			>
				<aui:select label="" name='<%= "notifications_" + accountCustomer.getAccountCustomerId() %>'>

					<%
					for (int i = 1; i <= 2; i++) {
					%>

						<aui:option label="<%= AccountCustomerConstants.getNotificationsLabel(i) %>" selected="<%= accountCustomer.getNotifications() == i %>" value="<%= i %>" />

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="role"
			>
				<aui:select label="" name='<%= "role_" + accountCustomer.getAccountCustomerId() %>' onChange='<%= renderResponse.getNamespace() + "setNotifications('" + accountCustomer.getAccountCustomerId() + "', this.value);" %>'>
					<aui:option value="" />

					<%
					for (int role : AccountCustomerConstants.ROLES) {
					%>

						<aui:option label="<%= AccountCustomerConstants.getRoleLabel(role) %>" selected="<%= accountCustomer.getRole() == role %>" value="<%= role %>" />

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="user-status"
			>
				<c:choose>
					<c:when test="<%= accountCustomer.getAccountCustomerId() <= 0 %>">
					</c:when>
					<c:when test="<%= curUser == null %>">
						<liferay-ui:message key="deleted" />
					</c:when>
					<c:when test="<%= !curUser.isActive() %>">
						<liferay-ui:message key="inactive" />
					</c:when>
					<c:when test="<%= RoleLocalServiceUtil.hasUserRole(accountCustomer.getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID) %>">
						<liferay-ui:message key="verified" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="unverified" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/admin/account_customer_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	function <portlet:namespace />setNotifications(accountCustomerId, role) {
		var A = AUI();

		if (role == <%= AccountCustomerConstants.ROLE_SALES %>) {
			A.one('#<portlet:namespace />notifications_' + accountCustomerId).val('<%= AccountCustomerConstants.NOTIFICATIONS_NONE %>');
		}
		else {
			A.one('#<portlet:namespace />notifications_' + accountCustomerId).val('<%= AccountCustomerConstants.NOTIFICATIONS_ALL %>');
		}
	}

	function <portlet:namespace />updateAccountCustomer(accountCustomerId) {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.fm('accountCustomerId').val(accountCustomerId);

		submitForm(form);
	}
</aui:script>