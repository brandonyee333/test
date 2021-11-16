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
PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<aui:form action="<%= portletURL %>" method="post">
	<liferay-ui:search-container
		searchContainer="<%= new AccountEntrySearch(renderRequest, portletURL) %>"
	>

		<%
		AccountEntryDisplayTerms displayTerms = (AccountEntryDisplayTerms)searchContainer.getDisplayTerms();
		AccountEntrySearchTerms searchTerms = (AccountEntrySearchTerms)searchContainer.getSearchTerms();
		%>

		<%@ include file="/admin/account_entry_search.jspf" %>

		<liferay-ui:search-container-results>
			<%@ include file="/admin/account_entry_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.customer.admin.model.AccountEntry"
			escapedModel="<%= true %>"
			keyProperty="accountEntryId"
			modelVar="accountEntry"
		>
			<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
				<portlet:param name="backURL" value="<%= portletURL.toString() %>" />
				<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="project-name"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				property="code"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
			>
				<%= LanguageUtil.get(request, accountEntry.getStatusLabel()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/admin/account_entry_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<div>
			<portlet:renderURL var="editAccountEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			</portlet:renderURL>

			<aui:a cssClass="btn btn-default" href="<%= editAccountEntryURL %>" label="add-project" />
		</div>

		<br />

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>