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

portletURL.setParameter("mvcPath", "/admin/edit_account_entry_workers.jsp");
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
	<input name="<portlet:namespace />assignmentsRedirect" type="hidden" value="" />
	<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntryId %>" />

	<liferay-ui:message arguments="<%= HtmlUtil.escape(accountEntry.getName()) %>" key="edit-workers-for-project-x" />

	<br /><br />

	<liferay-ui:tabs
		backURL="<%= redirect %>"
		names="users"
	/>

	<liferay-ui:error exception="<%= AccountWorkerPartnerRoleException.class %>" message="the-user-must-be-a-member-of-the-partner-that-is-assigned-to-this-project" />
	<liferay-ui:error exception="<%= RequiredPartnerEntryException.class %>" message="this-project-must-be-assigned-a-partner-before-assigning-workers" />

	<input name="<portlet:namespace />addUserIds" type="hidden" value="" />
	<input name="<portlet:namespace />removeUserIds" type="hidden" value="" />

	<liferay-ui:tabs
		names="current,available"
		param="tabs2"
		url="<%= portletURL.toString() %>"
	/>

	<%
	UserAccountWorkerChecker rowChecker = new UserAccountWorkerChecker(renderResponse, accountEntry);

	LinkedHashMap userParams = new LinkedHashMap();

	if (tabs2.equals("current")) {
		userParams.put("status", WorkflowConstants.STATUS_ANY);
		userParams.put("usersAccountWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByAccountWorker"), new Long(accountEntry.getAccountEntryId())));
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
			headerNames="name,screen-name,email-address,role"
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
				AccountWorker accountWorker = null;

				int role = 0;
				int notifications = 0;

				try {
					accountWorker = AccountWorkerLocalServiceUtil.getAccountWorker(curUser.getUserId(), accountEntryId);

					role = accountWorker.getRole();
					notifications = accountWorker.getNotifications();
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
					<select <%= curUser.isActive() ? StringPool.BLANK : "disabled" %> name="<portlet:namespace />notifications_<%= curUser.getUserId() %>">

						<%
						for (int i = 1; i <= 4; i++) {
						%>

							<option <%= (notifications == i) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(pageContext, AccountWorkerConstants.getNotificationsLabel(i)) %></option>

						<%
						}
						%>

					</select>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="role"
				>
					<select <%= curUser.isActive() ? StringPool.BLANK : "disabled" %> name="<portlet:namespace />role_<%= curUser.getUserId() %>">
						<option></option>

						<%
						for (int i = 1; i <= 5; i++) {
							if (ArrayUtil.contains(AccountWorkerConstants.ROLES_DEPRECATED, i)) {
								continue;
							}
						%>

							<option <%= (role == i) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(pageContext, AccountWorkerConstants.getRoleLabel(i)) %></option>

						<%
						}
						%>

					</select>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<div class="separator"><!-- --></div>

			<input onClick="<portlet:namespace />updateAccountWorkers('<%= portletURL.toString() %>&<portlet:namespace />cur=<%= cur %>');" type="button" value="<liferay-ui:message key="update-associations" />" />

			<br /><br />

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</liferay-ui:user-search>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />updateAccountWorkers',
		function(assignmentsRedirect) {
			document.<portlet:namespace />fm.<portlet:namespace />assignmentsRedirect.value = assignmentsRedirect;
			document.<portlet:namespace />fm.<portlet:namespace />addUserIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			document.<portlet:namespace />fm.<portlet:namespace />removeUserIds.value = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="updateAccountWorkers"><portlet:param name="mvcPath" value="/admin/edit_account_entry_workers.jsp" /><portlet:param name="tabs2" value="<%= tabs2 %>" /></portlet:actionURL>");
		},
		['liferay-util-list-fields']
	);
</aui:script>