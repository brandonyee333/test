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
int cur = ParamUtil.getInteger(request, "cur");

List<AccountEntry> accountEntries = new ArrayList<AccountEntry>();
int count = 0;

AccountEntrySearchTerms searchTerms = new AccountEntrySearchTerms(renderRequest);

searchTerms.setAdvancedSearch(true);

if (searchTerms.hasSearchableValues() || supportPartnerWorker) {
	LinkedHashMap<String, Object> params = searchTerms.getParams();

	if (!ArrayUtil.isEmpty(searchTerms.getIndustries())) {
		params.put("industry", searchTerms.getIndustries());
	}

	if (!ArrayUtil.isEmpty(searchTerms.getStatuses())) {
		params.put("status", searchTerms.getStatuses());
	}

	if (!ArrayUtil.isEmpty(searchTerms.getTiers())) {
		params.put("tier", searchTerms.getTiers());
	}

	params.put("type", new int[] {AccountEntryConstants.TYPE_GROUP, AccountEntryConstants.TYPE_INDIVIDUAL, AccountEntryConstants.TYPE_INTERNAL_TEST});

	count = AccountEntryServiceUtil.searchCount(searchTerms.getKeywords(), params);

	if (cur <= 0) {
		cur = 1;
	}

	int start = (cur - 1) * 100;
	int end = start + 100;

	if (start > count) {
		start = count - 1;
	}

	if (end > count) {
		end = count;
	}

	accountEntries = AccountEntryServiceUtil.search(searchTerms.getKeywords(), params, start, end, new AccountEntryRelevancyComparator(searchTerms.getKeywords()));
}

request.setAttribute("accounts.jsp-results", accountEntries);
%>

<liferay-util:include page="/support/2/advanced_search/account_results_list.jsp" servletContext="<%= application %>">
	<portlet:param name="activeFilters" value="<%= searchTerms.getActiveFilters() %>" />
	<portlet:param name="count" value="<%= String.valueOf(count) %>" />
	<portlet:param name="cur" value="<%= String.valueOf(cur) %>" />
	<portlet:param name="paginate" value="<%= Boolean.TRUE.toString() %>" />
</liferay-util:include>