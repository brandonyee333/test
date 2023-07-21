<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AccountEntrySearchDisplayContext accountEntrySearchDisplayContext = (AccountEntrySearchDisplayContext)liferayPortletRequest.getAttribute(AccountEntrySearchDisplayContext.class.getName());

PortletURL portletURL = renderResponse.createRenderURL();
%>

<div class="col-md-8 col-md-offset-2">
	<liferay-util:include page="/account_entry_details/customer_portal_banner.jsp" servletContext="<%= application %>" />

	<h1>
		<liferay-ui:message key="select-an-account" />
	</h1>

	<aui:form action="<%= portletURL %>" method="post" name="searchFm">
		<liferay-ui:input-search
			markupView="lexicon"
			placeholder='<%= LanguageUtil.get(request, "search-accounts") %>'
		/>
	</aui:form>

	<liferay-ui:search-container
		searchContainer="<%= accountEntrySearchDisplayContext.getAccountsSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account"
			modelVar="koroneikiAccount"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/view_account_entry" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="koroneikiAccountKey" value="<%= koroneikiAccount.getKey() %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				cssClass="project-name-column"
				name="name"
			>
				<aui:a cssClass="project-name" href="<%= rowURL.toString() %>" label="<%= HtmlUtil.escape(koroneikiAccount.getName()) %>" />
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="state"
			>

				<%
				String state = accountEntrySearchDisplayContext.getState(koroneikiAccount.getKey());
				%>

				<span class="label label-sm label-<%= state.toLowerCase() %>"><%= state %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>