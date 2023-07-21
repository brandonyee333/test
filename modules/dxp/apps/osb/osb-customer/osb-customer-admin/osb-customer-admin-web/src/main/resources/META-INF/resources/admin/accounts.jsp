<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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