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
String tabs2 = ParamUtil.getString(request, "tabs2", "current");

int cur = ParamUtil.getInteger(request, "cur");

String redirect = ParamUtil.getString(request, "redirect");

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

AccountEntry accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_account_entry_customers.jsp");
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
	<input name="<portlet:namespace />assignmentsRedirect" type="hidden" value="" />
	<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntryId %>" />

	<liferay-ui:message arguments="<%= HtmlUtil.escape(accountEntry.getName()) %>" key="edit-customers-for-project-x" />

	<br /><br />

	<liferay-ui:tabs
		backURL="<%= redirect %>"
		names="users"
	/>

	<liferay-ui:error exception="<%= AccountEntryMaximumCustomersException.class %>" message="the-number-of-contacts-has-exceeded-the-maximum-number-of-contacts" />

	<input name="<portlet:namespace />addUserIds" type="hidden" value="" />
	<input name="<portlet:namespace />removeUserIds" type="hidden" value="" />

	<liferay-ui:tabs
		names="current,available"
		param="tabs2"
		url="<%= portletURL.toString() %>"
	/>

	<%
	UserAccountCustomerChecker rowChecker = new UserAccountCustomerChecker(renderResponse, accountEntry);

	LinkedHashMap userParams = new LinkedHashMap();

	if (tabs2.equals("current")) {
		userParams.put("status", WorkflowConstants.STATUS_ANY);

		OSBCustomSQLParam osbCustomSQLParam = new OSBCustomSQLParam("usersAccountCustomers", CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByAccountCustomer"), new Object[] {accountEntry.getAccountEntryId(), AccountCustomerConstants.ROLES});

		userParams.put("usersAccountCustomers", osbCustomSQLParam);
	}
	%>

	<liferay-ui:user-search
		portletURL="<%= portletURL %>"
		rowChecker="<%= rowChecker %>"
		userParams="<%= userParams %>"
	>

		<%
		SearchContainer userSearchContainer = (SearchContainer)request.getAttribute(WebKeys.SEARCH_CONTAINER);
		%>

		<liferay-ui:search-container
			headerNames="name,screen-name,email-address,role,ticket-notifications"
			rowChecker="<%= rowChecker %>"
			searchContainer="<%= userSearchContainer %>"
		>
			<liferay-ui:search-container-results
				results="<%= userSearchContainer.getResults() %>"
				total="<%= userSearchContainer.getTotal() %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.User"
				escapedModel="<%= true %>"
				keyProperty="userId"
				modelVar="curUser"
			>

				<%
				AccountCustomer accountCustomer = null;

				int role = 0;
				int notifications = 0;

				try {
					accountCustomer = AccountCustomerLocalServiceUtil.getAccountCustomer(curUser.getUserId(), accountEntryId);

					role = accountCustomer.getRole();
					notifications = accountCustomer.getNotifications();
				}
				catch (Exception e) {
				}

				if (!curUser.isActive()) {
					row.setClassName("inactive");
				}
				%>

				<liferay-ui:search-container-column-text
					name="name"
					property="fullName"
				/>

				<liferay-ui:search-container-column-text
					name="screen-name"
					property="screenName"
				/>

				<liferay-ui:search-container-column-text
					name="email-address"
					property="emailAddress"
				/>

				<liferay-ui:search-container-column-text
					name="notifications"
				>
					<select <%= curUser.isActive() ? StringPool.BLANK : "disabled" %> id="<portlet:namespace />notifications_<%= curUser.getUserId() %>" name="<portlet:namespace />notifications_<%= curUser.getUserId() %>">

						<%
						for (int i = 1; i <= 2; i++) {
						%>

							<option <%= (notifications == i) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(pageContext, AccountCustomerConstants.getNotificationsLabel(i)) %></option>

						<%
						}
						%>

					</select>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="role"
				>
					<select <%= curUser.isActive() ? StringPool.BLANK : "disabled" %> name="<portlet:namespace />role_<%= curUser.getUserId() %>" onChange="<portlet:namespace />setNotifications('<%= curUser.getUserId() %>', this.value);">
						<option></option>

						<%
						for (int curRole : AccountCustomerConstants.ROLES) {
						%>

							<option <%= (role == curRole) ? "selected" : "" %> value="<%= curRole %>"><%= LanguageUtil.get(pageContext, AccountCustomerConstants.getRoleLabel(curRole)) %></option>

						<%
						}
						%>

					</select>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="user-status"
				>
					<%= RoleLocalServiceUtil.hasUserRole(curUser.getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID) ? StringPool.BLANK : LanguageUtil.get(pageContext, "unverified") %>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<div class="separator"><!-- --></div>

			<input onClick="<portlet:namespace />updateAccountCustomers('<%= portletURL.toString() %>&<portlet:namespace />cur=<%= cur %>');" type="button" value="<liferay-ui:message key="update-associations" />" />

			<br /><br />

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</liferay-ui:user-search>
</aui:form>

<aui:script>
	function <portlet:namespace />setNotifications(userId, role) {
		var A = AUI();

		if (role == <%= AccountCustomerConstants.ROLE_SALES %>) {
			A.one('#<portlet:namespace />notifications_' + userId).val('<%= AccountCustomerConstants.NOTIFICATIONS_NONE %>');
		}
		else {
			A.one('#<portlet:namespace />notifications_' + userId).val('<%= AccountCustomerConstants.NOTIFICATIONS_ALL %>');
		}
	}

	Liferay.provide(
		window,
		'<portlet:namespace />updateAccountCustomers',
		function(assignmentsRedirect) {
			document.<portlet:namespace />fm.<portlet:namespace />assignmentsRedirect.value = assignmentsRedirect;
			document.<portlet:namespace />fm.<portlet:namespace />addUserIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			document.<portlet:namespace />fm.<portlet:namespace />removeUserIds.value = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="updateAccountCustomers"><portlet:param name="mvcPath" value="/admin/edit_account_entry_customers.jsp" /></portlet:actionURL>");
		},
		['liferay-util-list-fields']
	);
</aui:script>