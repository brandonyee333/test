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
String backURL = ParamUtil.getString(request, "backURL");

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

AccountEntry accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_account_entry_workers.jsp");
portletURL.setParameter("backURL", backURL);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));

request.setAttribute("edit_account_entry_workers.jsp-portletURL", portletURL);
%>

<portlet:actionURL name="updateAccountWorker" var="updateAccountWorkerURL">
	<portlet:param name="mvcPath" value="/admin/edit_account_entry_workers.jsp" />
	<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
	<portlet:param name="backURL" value="<%= backURL %>" />
</portlet:actionURL>

<aui:form action="<%= updateAccountWorkerURL %>" method="post">
	<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />
	<aui:input name="accountWorkerId" type="hidden" />

	<liferay-ui:message arguments="<%= HtmlUtil.escape(accountEntry.getName()) %>" key="edit-workers-for-project-x" />

	<br /><br />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="users"
	/>

	<liferay-util:include page="/common/exception.jsp" servletContext="<%= application %>" />

	<liferay-ui:error exception="<%= RequiredPartnerEntryException.class %>" message="this-project-must-be-assigned-a-partner-before-assigning-workers" />

	<liferay-ui:search-container>

		<%
		List<AccountWorker> accountWorkers = ListUtil.copy(AccountWorkerLocalServiceUtil.getAccountWorkers(accountEntryId));

		accountWorkers.add(0, new AccountWorkerImpl());
		%>

		<liferay-ui:search-container-results
			results="<%= accountWorkers %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.AccountWorker"
			escapedModel="<%= true %>"
			keyProperty="accountWorkerId"
			modelVar="accountWorker"
		>

			<%
			User curUser = UserLocalServiceUtil.fetchUser(accountWorker.getUserId());

			if ((accountWorker.getAccountWorkerId() > 0) && ((curUser == null) || !curUser.isActive())) {
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
					<c:when test="<%= accountWorker.getUserId() > 0 %>">
						<%= accountWorker.getUserId() %>
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
					<c:when test="<%= accountWorker.getAccountWorkerId() <= 0 %>">
						<aui:input label="" name="emailAddress" />
					</c:when>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="role"
			>
				<aui:select label="" name='<%= "role_" + accountWorker.getAccountWorkerId() %>'>
					<aui:option />

					<%
					for (int i = 1; i <= 5; i++) {
						if (ArrayUtil.contains(AccountWorkerConstants.ROLES_DEPRECATED, i)) {
							continue;
						}
					%>

						<aui:option label="<%= AccountWorkerConstants.getRoleLabel(i) %>" selected="<%= accountWorker.getRole() == i %>" value="<%= i %>" />

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="user-status"
			>
				<c:choose>
					<c:when test="<%= accountWorker.getAccountWorkerId() <= 0 %>">
					</c:when>
					<c:when test="<%= curUser == null %>">
						<liferay-ui:message key="deleted" />
					</c:when>
					<c:when test="<%= !curUser.isActive() %>">
						<liferay-ui:message key="inactive" />
					</c:when>
					<c:when test="<%= RoleLocalServiceUtil.hasUserRole(accountWorker.getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID) %>">
						<liferay-ui:message key="verified" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="unverified" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/admin/account_worker_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	function <portlet:namespace />updateAccountWorker(accountWorkerId) {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.fm('accountWorkerId').val(accountWorkerId);

		submitForm(form);
	}
</aui:script>