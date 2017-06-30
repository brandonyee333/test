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

List<TicketFeedback> ticketFeedbacks = new ArrayList<TicketFeedback>();
int count = 0;

TicketFeedbackSearchTerms searchTerms = new TicketFeedbackSearchTerms(renderRequest);

searchTerms.setAdvancedSearch(true);

if (searchTerms.hasSearchableValues()) {
	LinkedHashMap<String, Object> params = searchTerms.getParams();

	Date createDateGT = searchTerms.getCreateDateGT();
	Date createDateLT = searchTerms.getCreateDateLT();

	if ((createDateGT != null) || (createDateLT != null)) {
		params.put("createDate", new Date[] {createDateGT, createDateGT, createDateLT, createDateLT});
	}

	count = TicketFeedbackServiceUtil.searchCount(searchTerms.getKeywords(), params);

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

	OrderByComparator obc = null;

	if (liferayIncOrg) {
		obc = new TicketFeedbackCreateDateComparator();
	}

	ticketFeedbacks = TicketFeedbackServiceUtil.search(searchTerms.getKeywords(), params, start, end, obc);
}

request.setAttribute("feedback.jsp-results", ticketFeedbacks);
%>

<liferay-util:include page="/support/2/advanced_search/feedback_results_list.jsp" servletContext="<%= application %>">
	<portlet:param name="activeFilters" value="<%= searchTerms.getActiveFilters() %>" />
	<portlet:param name="count" value="<%= String.valueOf(count) %>" />
	<portlet:param name="cur" value="<%= String.valueOf(cur) %>" />
	<portlet:param name="paginate" value="<%= Boolean.TRUE.toString() %>" />
</liferay-util:include>