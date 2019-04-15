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

portletURL.setParameter("mvcPath", "/admin/edit_account_entry_customers.jsp");
portletURL.setParameter("backURL", backURL);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));

request.setAttribute("edit_account_entry_customers.jsp-portletURL", portletURL);
%>

<portlet:actionURL name="updateAccountCustomer" var="updateAccountCustomerURL">
	<portlet:param name="mvcPath" value="/admin/edit_account_entry_customers.jsp" />
	<portlet:param name="backURL" value="<%= backURL %>" />
</portlet:actionURL>

<aui:form action="<%= updateAccountCustomerURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
	<aui:input name="accountCustomerId" type="hidden" />
	<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />

	<liferay-ui:message arguments="<%= HtmlUtil.escape(accountEntry.getName()) %>" key="edit-customers-for-project-x" />

	<br /><br />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="users"
	/>

	<liferay-util:include page="/common/exception.jsp" servletContext="<%= application %>" />

	<liferay-ui:error exception="<%= AccountEntryMaximumCustomersException.class %>" message="the-number-of-contacts-has-exceeded-the-maximum-number-of-contacts" />
	<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="the-user-could-not-be-found" />
	<liferay-ui:error exception="<%= RemoteServiceException.class %>" message="there-was-an-error-connecting-to-web.liferay.com" />

	<liferay-ui:search-container>

		<%
		List<AccountCustomer> accountCustomers = ListUtil.copy(AccountCustomerLocalServiceUtil.getAccountCustomers(accountEntryId));

		total = accountCustomers.size();

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
				name="role"
			>
				<aui:select label="" name='<%= "role_" + accountCustomer.getAccountCustomerId() %>'>
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

		<small class="search-results">
			<c:choose>
				<c:when test="<%= total != 1 %>">
					<liferay-ui:message arguments="<%= total %>" key="showing-x-results" translateArguments="<%= false %>" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message arguments="<%= total %>" key="showing-x-result" translateArguments="<%= false %>" />
				</c:otherwise>
			</c:choose>
		</small>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	function <portlet:namespace />updateAccountCustomer(accountCustomerId) {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.fm('accountCustomerId').val(accountCustomerId);

		submitForm(form);
	}
</aui:script>