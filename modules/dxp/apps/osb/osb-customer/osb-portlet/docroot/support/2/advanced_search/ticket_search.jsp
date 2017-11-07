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
boolean bulkEdit = ParamUtil.getBoolean(request, "bulkEdit");
int cur = ParamUtil.getInteger(request, "cur");
String divId = ParamUtil.getString(request, "divId");
String keywords = ParamUtil.getString(request, "keywords");
int needsResponseCount = ParamUtil.getInteger(request, "needsResponseCount");
String orderableColumns = ParamUtil.getString(request, "orderableColumns");
String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");
long searchFilterId = ParamUtil.getLong(request, "searchFilterId");
boolean supportManager = ParamUtil.getBoolean(request, "supportManager");

TicketEntry ticketEntry = null;

boolean invalidSearch = false;

try {
	ticketEntry = SupportUtil.getTicketEntry(keywords);
}
catch (Exception e) {
	invalidSearch = true;
}
%>

<c:choose>
	<c:when test="<%= ticketEntry != null %>">
		<liferay-portlet:renderURL varImpl="ticketEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" />
			<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
		</liferay-portlet:renderURL>

		redirect:<%= ticketEntryURL %>
	</c:when>
	<c:when test="<%= invalidSearch %>">
		<liferay-util:include page="/support/2/advanced_search/ticket_results_list.jsp" servletContext="<%= application %>">
			<portlet:param name="invalidSearch" value="<%= String.valueOf(invalidSearch) %>" />
			<portlet:param name="orderableColumns" value="assignee,create-date,display-id,due-date,modified-date,status,subject" />
			<portlet:param name="orderByCol" value="<%= orderByCol %>" />
			<portlet:param name="orderByType" value="<%= orderByType %>" />
		</liferay-util:include>
	</c:when>
	<c:when test="<%= Validator.isNotNull(divId) %>">

		<%
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		Sort[] sorts = new Sort[0];

		if (Validator.isNotNull(orderByCol) && Validator.isNotNull(orderByType)) {
			if (orderByCol.equals("display-id")) {
				sorts = new Sort[] {SortFactoryUtil.getSort(TicketEntry.class, "account-code", orderByType), SortFactoryUtil.getSort(TicketEntry.class, "ticket-id", orderByType)};
			}
			else if (orderByCol.equals("status")) {
				sorts = new Sort[] {SortFactoryUtil.getSort(TicketEntry.class, "status", orderByType), SortFactoryUtil.getSort(TicketEntry.class, "resolution", orderByType)};
			}
			else {
				sorts = new Sort[] {SortFactoryUtil.getSort(TicketEntry.class, orderByCol, orderByType)};
			}
		}

		int[] statuses = null;

		String cssClass = StringPool.BLANK;

		if (liferayIncOrg || supportPartnerWorker) {
			statuses = TicketEntryConstants.STATUSES_WORKER_OPEN;

			if (divId.equals("auxiliary")) {
				params.put("primaryTicketWorker", new Object[] {user.getUserId(), false});

				cssClass = "other";
			}
			else if (divId.equals("needsResponse")) {
				params.put("pendingTypes", new int[] {TicketFlagConstants.TYPE_PENDING_PARTNER});
				params.put("ticketWorkerUserIds", new long[] {user.getUserId()});

				cssClass = "top";
			}
			else if (divId.equals("primary")) {
				params.put("primaryTicketWorker", new Object[] {user.getUserId(), true});

				cssClass = (needsResponseCount <= 0) ? "top" : "other";
			}
			else if (divId.equals("watching")) {
				params.put("subscription", user.getUserId());

				cssClass = "other";
			}
		}
		else {
			statuses = TicketEntryConstants.STATUSES_OPEN;

			if (divId.equals("needsResponse")) {
				params.put("pendingCustomer", StringPool.BLANK);
				params.put("pendingTypes", new int[] {TicketFlagConstants.TYPE_PENDING_CUSTOMER});

				cssClass = "top";
			}
			else if (divId.equals("openTickets")) {
				cssClass = (needsResponseCount <= 0) ? "top" : "other";
			}
		}

		Hits hits = TicketEntryServiceUtil.search(0, 0, null, new int[0], null, null, null, null, statuses, new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], null, null, null, null, params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, sorts);

		Tuple tuple = TicketEntryLocalServiceUtil.getTicketEntries(hits);

		List<TicketEntry> ticketEntries = (List<TicketEntry>)tuple.getObject(0);

		int hitsLength = hits.getLength();

		request.setAttribute("tickets.jsp-results", ticketEntries);
		%>

		<c:if test='<%= (hitsLength > 0) || ((liferayIncOrg || supportPartnerWorker) && divId.equals("primary")) || divId.equals("openTickets") %>'>
			<liferay-util:include page="/support/2/advanced_search/ticket_results_list.jsp" servletContext="<%= application %>">
				<portlet:param name="cssClass" value="<%= cssClass %>" />
				<portlet:param name="id" value='<%= divId + "ResultsList" %>' />
				<portlet:param name="orderableColumns" value="<%= orderableColumns %>" />
				<portlet:param name="orderByCol" value="<%= orderByCol %>" />
				<portlet:param name="orderByType" value="<%= orderByType %>" />
			</liferay-util:include>
		</c:if>
	</c:when>
	<c:otherwise>

		<%
		List<TicketEntry> ticketEntries = new ArrayList<TicketEntry>();
		int count = 0;

		TicketEntrySearchTerms searchTerms = null;

		if (searchFilterId > 0) {
			SearchFilter searchFilter = SearchFilterLocalServiceUtil.getSearchFilter(searchFilterId);

			searchTerms = new TicketEntrySearchTerms(renderRequest, searchFilter.getFilter());
		}
		else {
			searchTerms = new TicketEntrySearchTerms(renderRequest);
		}

		searchTerms.setAdvancedSearch(true);

		if (searchTerms.hasSearchableValues()) {
			Sort[] sorts = new Sort[0];

			if (Validator.isNotNull(orderByCol) && Validator.isNotNull(orderByType)) {
				if (orderByCol.equals("display-id")) {
					sorts = new Sort[] {SortFactoryUtil.getSort(TicketEntry.class, "account-code", orderByType), SortFactoryUtil.getSort(TicketEntry.class, "ticket-id", orderByType)};
				}
				else if (orderByCol.equals("status")) {
					sorts = new Sort[] {SortFactoryUtil.getSort(TicketEntry.class, "status", orderByType), SortFactoryUtil.getSort(TicketEntry.class, "resolution", orderByType)};
				}
				else {
					sorts = new Sort[] {SortFactoryUtil.getSort(TicketEntry.class, orderByCol, orderByType)};
				}
			}

			if (cur <= 0) {
				cur = 1;
			}

			int start = (cur - 1) * 100;
			int end = start + 100;

			if (bulkEdit && supportManager) {
				start = QueryUtil.ALL_POS;
				end = QueryUtil.ALL_POS;
			}

			Hits hits = TicketEntryServiceUtil.search(0, 0, searchTerms.getKeywords(), searchTerms.getAccountEntryTiers(), searchTerms.getSatisfiedDueDate(), searchTerms.getCreateDateGT(), searchTerms.getCreateDateLT(), searchTerms.getKeywords(), searchTerms.getStatuses(), searchTerms.getSeverities(), searchTerms.getEscalationLevels(), searchTerms.getEnvOSIds(), searchTerms.getEnvDBIds(), searchTerms.getEnvJVMIds(), searchTerms.getEnvASIds(), searchTerms.getEnvLFRIds(), searchTerms.getComponents(), searchTerms.getResolutions(), searchTerms.getClosedDateGT(), searchTerms.getClosedDateLT(), searchTerms.getDueDateGT(), searchTerms.getDueDateLT(), searchTerms.getParams(), true, start, end, sorts);

			Tuple tuple = TicketEntryLocalServiceUtil.getTicketEntries(hits);

			ticketEntries = (List<TicketEntry>)tuple.getObject(0);
			count = hits.getLength();
		}

		request.setAttribute("tickets.jsp-results", ticketEntries);
		%>

		<input class="hide" id="<portlet:namespace />activeFilters" value="<%= HtmlUtil.escapeAttribute(searchTerms.getActiveFilters()) %>" />
		<input class="hide" id="<portlet:namespace />ticketResultsListCount" value="<%= count %>" />

		<liferay-util:include page="/support/2/advanced_search/ticket_results_list.jsp" servletContext="<%= application %>">
			<portlet:param name="advancedSearch" value="<%= Boolean.TRUE.toString() %>" />
			<portlet:param name="bulkEdit" value="<%= String.valueOf(bulkEdit) %>" />
			<portlet:param name="count" value="<%= String.valueOf(count) %>" />
			<portlet:param name="cur" value="<%= String.valueOf(cur) %>" />
			<portlet:param name="orderableColumns" value="assignee,create-date,display-id,due-date,modified-date,status,subject" />
			<portlet:param name="orderByCol" value="<%= orderByCol %>" />
			<portlet:param name="orderByType" value="<%= orderByType %>" />
			<portlet:param name="paginate" value="<%= (supportManager && bulkEdit) ? Boolean.FALSE.toString() : Boolean.TRUE.toString() %>" />
			<portlet:param name="supportManager" value="<%= String.valueOf(supportManager) %>" />
		</liferay-util:include>
	</c:otherwise>
</c:choose>