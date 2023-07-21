<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String callback = ParamUtil.getString(request, "callback");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/license/select_account.jsp");
portletURL.setParameter("callback", callback);
%>

<aui:form action="<%= portletURL %>" method="post" name="fm">
	<liferay-ui:tabs
		names="accounts"
	/>

	<liferay-ui:search-container
		headerNames="name,code"
		searchContainer="<%= new AccountSearch(renderRequest, portletURL) %>"
	>

		<%
		AccountDisplayTerms displayTerms = (AccountDisplayTerms)searchContainer.getDisplayTerms();
		AccountSearchTerms searchTerms = (AccountSearchTerms)searchContainer.getSearchTerms();
		%>

		<%@ include file="/license/account_search.jspf" %>

		<liferay-ui:search-container-results>
			<%@ include file="/license/account_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account"
			keyProperty="key"
			modelVar="koroneikiAccount"
		>

			<%
			StringBundler sb = new StringBundler(10);

			sb.append("javascript:opener.");
			sb.append(renderResponse.getNamespace());
			sb.append(callback);
			sb.append("('");
			sb.append(koroneikiAccount.getKey());
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(koroneikiAccount.getName()));
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(koroneikiAccount.getCode()));
			sb.append("'); window.close();");

			String rowHREF = sb.toString();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="account"
				value="<%= HtmlUtil.escape(koroneikiAccount.getName()) %>"
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