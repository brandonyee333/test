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
AccountEntry accountEntry = (AccountEntry)request.getAttribute(OSBWebKeys.ACCOUNT_ENTRY);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_account_entry.jsp");
portletURL.setParameter("detailTab", "all-offerings");
portletURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
%>

<h2>
	<liferay-ui:message key="primary" />
</h2>

<liferay-ui:search-container
	deltaParam="offeringEntryDelta"
	searchContainer="<%= new OfferingEntrySearch(renderRequest, portletURL) %>"
>

	<%
	OfferingEntryDisplayTerms displayTerms = (OfferingEntryDisplayTerms)searchContainer.getDisplayTerms();
	%>

	<c:if test="<%= accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL %>">
		<%@ include file="/admin/offering_entry_search.jspf" %>
	</c:if>

	<liferay-ui:search-container-results>

		<%
		LinkedHashMap params = new LinkedHashMap();

		params.put("productEntry", ProductEntryConstants.TYPE_PRIMARY);

		if (accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL) {
			params.put("user", new String[] {displayTerms.getFirstName(), displayTerms.getMiddleName(), displayTerms.getLastName(), displayTerms.getScreenName(), displayTerms.getEmailAddress()});

			results = OfferingEntryGroupFactoryUtil.createOfferingEntryGroups(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true, searchContainer.getStart(), searchContainer.getEnd());
			total = OfferingEntryLocalServiceUtil.searchCount(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true);
		}
		else {
			List<OfferingEntryGroup> offeringEntryGroups = OfferingEntryGroupFactoryUtil.createOfferingEntryGroups(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			results = ListUtil.subList(offeringEntryGroups, searchContainer.getStart(), searchContainer.getEnd());
			total = offeringEntryGroups.size();
		}

		searchContainer.setResults(results);
		searchContainer.setTotal(total);
		%>

	</liferay-ui:search-container-results>

	<%@ include file="/admin/edit_account_entry/offering_results_row.jspf" %>

	<div class="table-report">
		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</div>
</liferay-ui:search-container>

<h2>
	<liferay-ui:message key="add-on" />
</h2>

<liferay-ui:search-container
	deltaParam="offeringEntryDelta"
	searchContainer="<%= new OfferingEntrySearch(renderRequest, portletURL) %>"
>

	<%
	OfferingEntryDisplayTerms displayTerms = (OfferingEntryDisplayTerms)searchContainer.getDisplayTerms();
	%>

	<c:if test="<%= accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL %>">
		<%@ include file="/admin/offering_entry_search.jspf" %>
	</c:if>

	<liferay-ui:search-container-results>

		<%
		LinkedHashMap params = new LinkedHashMap();

		params.put("productEntry", ProductEntryConstants.TYPE_ADD_ON);

		if (accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL) {
			params.put("user", new String[] {displayTerms.getFirstName(), displayTerms.getMiddleName(), displayTerms.getLastName(), displayTerms.getScreenName(), displayTerms.getEmailAddress()});

			results = OfferingEntryGroupFactoryUtil.createOfferingEntryGroups(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true, searchContainer.getStart(), searchContainer.getEnd());
			total = OfferingEntryLocalServiceUtil.searchCount(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true);
		}
		else {
			List<OfferingEntryGroup> offeringEntryGroups = OfferingEntryGroupFactoryUtil.createOfferingEntryGroups(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			results = ListUtil.subList(offeringEntryGroups, searchContainer.getStart(), searchContainer.getEnd());
			total = offeringEntryGroups.size();
		}

		searchContainer.setResults(results);
		searchContainer.setTotal(total);
		%>

	</liferay-ui:search-container-results>

	<%@ include file="/admin/edit_account_entry/offering_results_row.jspf" %>

	<div class="table-report">
		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</div>
</liferay-ui:search-container>