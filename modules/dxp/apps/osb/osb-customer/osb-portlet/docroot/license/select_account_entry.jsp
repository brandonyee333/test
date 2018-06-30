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
String callback = ParamUtil.getString(request, "callback");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/license/select_account_entry.jsp");
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

		<%@ include file="/license/account_entry_search.jspf" %>

		<liferay-ui:search-container-results>
			<%@ include file="/license/account_entry_search_results.jspf" %>
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