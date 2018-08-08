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
String keywords = ParamUtil.getString(request, "keywords");

PortletURL portletURL = renderResponse.createRenderURL();
%>

<h1>
	<liferay-ui:message key="select-a-project" />
</h1>

<aui:form action="<%= portletURL.toString() %>" method="post" name="searchFm">
	<liferay-ui:input-search
		markupView="lexicon"
		placeholder='<%= LanguageUtil.get(request, "search-projects") %>'
	/>
</aui:form>

<liferay-ui:search-container
	emptyResultsMessage="no-projects-were-found"
	headerNames="name,status"
	iteratorURL="<%= portletURL %>"
>
	<liferay-ui:search-container-results>

		<%
		boolean andOperator = false;

		if (Validator.isNull(keywords)) {
			andOperator = true;
		}

		total = AccountEntryServiceUtil.searchCount(null, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, keywords, keywords, new int[0], null, new int[0], new int[0], null, null, null, null, null, null, null, null, null, andOperator);

		searchContainer.setTotal(total);

		results = AccountEntryServiceUtil.search(null, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, keywords, keywords, new int[0], null, new int[0], new int[0], null, null, null, null, null, null, null, null, null, andOperator, searchContainer.getStart(), searchContainer.getEnd(), new AccountEntryNameComparator(true));

		searchContainer.setResults(results);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.osb.model.AccountEntry"
		keyProperty="accountEntryId"
		modelVar="accountEntry"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="mvcRenderCommandName" value="/view_account_entry" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			name="status"
		>
			<span class="label label-<%= accountEntry.getStatusLabel() %> label-small"><%= accountEntry.getStatusLabel() %></span>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>