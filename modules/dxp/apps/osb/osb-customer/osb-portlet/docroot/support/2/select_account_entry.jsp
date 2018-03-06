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

<%@ include file="/support/2/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/2/select_account_entry.jsp");
%>

<aui:form method="post" name="fm">
	<liferay-ui:tabs names="projects" />

	<liferay-ui:search-container
		emptyResultsMessage="no-projects-were-found"
		id="accountEntrySearchContainer"
		iteratorURL="<%= portletURL %>"
		searchContainer="<%= new AccountEntrySearch(renderRequest, portletURL) %>"
	>

		<%
		AccountEntryDisplayTerms displayTerms = (AccountEntryDisplayTerms)searchContainer.getDisplayTerms();
		AccountEntrySearchTerms searchTerms = (AccountEntrySearchTerms)searchContainer.getSearchTerms();
		%>

		<%@ include file="/support/2/account_entry_search.jspf" %>

		<%@ include file="/support/2/account_entry_search_results.jspf" %>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.AccountEntry"
			keyProperty="accountEntryId"
			modelVar="accountEntry"
		>

			<%
			StringBundler sb = new StringBundler(5);

			sb.append("javascript:opener.");
			sb.append(renderResponse.getNamespace());
			sb.append("selectAccountEntry(");
			sb.append(accountEntry.getAccountEntryId());
			sb.append(", '");
			sb.append(UnicodeFormatter.toString(accountEntry.getName()));
			sb.append("');");

			String rowHREF = sb.toString();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="code"
				value="<%= HtmlUtil.escape(accountEntry.getCode()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="name"
				value="<%= HtmlUtil.escape(accountEntry.getName()) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" />
	</liferay-ui:search-container>
</aui:form>