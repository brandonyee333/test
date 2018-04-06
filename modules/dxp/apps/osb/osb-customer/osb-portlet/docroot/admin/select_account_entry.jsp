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
String callback = ParamUtil.getString(request, "callback");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/select_account_entry.jsp");
portletURL.setParameter("callback", callback);
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
	<liferay-ui:tabs
		names="projects"
	/>

	<liferay-ui:search-container
		headerNames="name,code"
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
			className="com.liferay.osb.model.AccountEntry"
			keyProperty="accountEntryId"
			modelVar="accountEntry"
		>

			<%
			StringBundler sb = new StringBundler(10);

			sb.append("javascript:opener.");
			sb.append(renderResponse.getNamespace());
			sb.append(callback);
			sb.append("('");
			sb.append(accountEntry.getAccountEntryId());
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(accountEntry.getName()));
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(accountEntry.getCode()));
			sb.append("'); window.close();");

			String rowHREF = sb.toString();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="project"
				value="<%= HtmlUtil.escape(accountEntry.getName()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="code"
				property="code"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>