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

<%@ include file="/support/init.jsp" %>

<%
PortletURL searchURL = renderResponse.createActionURL();

searchURL.setParameter("mvcPath", "/support/view.jsp");
searchURL.setParameter("tabs1", "partners");

pageContext.setAttribute("searchURL", searchURL);

request.setAttribute("view.jsp-portletURL", searchURL);
%>

<aui:form action="<%= searchURL.toString() %>" method="get" name="fm" onSubmit="submitForm(this); return false;">
	<liferay-portlet:renderURLParams varImpl="searchURL" />

	<liferay-ui:search-container
		searchContainer="<%= new PartnerEntrySearch(renderRequest, searchURL) %>"
	>

		<%
		PartnerEntryDisplayTerms displayTerms = (PartnerEntryDisplayTerms)searchContainer.getDisplayTerms();
		PartnerEntrySearchTerms searchTerms = (PartnerEntrySearchTerms)searchContainer.getSearchTerms();
		%>

		<%@ include file="/support/partner_entry_search.jspf" %>

		<liferay-ui:search-container-results>
			<%@ include file="/support/partner_entry_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.PartnerEntry"
			escapedModel="<%= true %>"
			keyProperty="partnerEntryId"
			modelVar="partnerEntry"
		>
			<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/support/edit_partner_entry.jsp" />
				<portlet:param name="redirect" value="<%= searchURL.toString() %>" />
				<portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				property="code"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/support/partner_entry_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<div class="table-report">
			<liferay-ui:search-iterator />
		</div>
	</liferay-ui:search-container>
</aui:form>