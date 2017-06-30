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
LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

userParams.put("partnerWorker", new Long(user.getUserId()));
userParams.put("status", AccountEntryConstants.STATUSES_ACTIVE);

boolean ticketWorker = liferayIncOrg || (AccountEntryLocalServiceUtil.searchCount(null, userParams) > 0);

PortletURL searchURL = renderResponse.createActionURL();

searchURL.setParameter("mvcPath", "/support/view.jsp");
searchURL.setParameter("tabs1", "projects");

pageContext.setAttribute("searchURL", searchURL);

request.setAttribute("view.jsp-portletURL", searchURL);
%>

<aui:form action="<%= searchURL.toString() %>" method="get" name="fm" onSubmit="submitForm(this); return false;">
	<liferay-portlet:renderURLParams varImpl="searchURL" />

	<liferay-ui:search-container
		delta="<%= 50 %>"
		searchContainer="<%= new AccountEntrySearch(renderRequest, searchURL) %>"
	>

		<%
		AccountEntryDisplayTerms displayTerms = (AccountEntryDisplayTerms)searchContainer.getDisplayTerms();
		AccountEntrySearchTerms searchTerms = (AccountEntrySearchTerms)searchContainer.getSearchTerms();
		%>

		<%@ include file="/support/account_entry_search.jspf" %>

		<liferay-ui:search-container-results>
			<%@ include file="/support/account_entry_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.AccountEntry"
			escapedModel="<%= true %>"
			keyProperty="accountEntryId"
			modelVar="curAccountEntry"
		>
			<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/support/edit_account_entry.jsp" />
				<portlet:param name="redirect" value="<%= searchURL.toString() %>" />
				<portlet:param name="accountEntryId" value="<%= String.valueOf(curAccountEntry.getAccountEntryId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="project-name"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				property="code"
			/>

			<c:if test="<%= ticketWorker %>">
				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="partner"
				>

					<%
					PartnerEntry partnerEntry = curAccountEntry.getPartnerEntry();
					%>

					<c:if test="<%= partnerEntry != null %>">
						<c:if test="<%= curAccountEntry.isPartnerManagedSupport() %>">
							<liferay-ui:icon
								image="telephone"
								message="partner-first-line-support"
							/>
						</c:if>

						<%= HtmlUtil.escape(partnerEntry.getCode()) %>
					</c:if>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="<%= liferayIncOrg %>">
				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="tier"
				>
					<liferay-ui:message key="<%= AccountEntryConstants.getTierLabel(curAccountEntry.getTier()) %>" />
				</liferay-ui:search-container-column-text>
			</c:if>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-end-date"
			>

				<%
				List<OfferingEntry> offeringEntries = OfferingEntryLocalServiceUtil.search(0, curAccountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, null, true, 0, 1, new OfferingEntrySupportEndDateComparator());

				OfferingEntry offeringEntry = null;

				if (!offeringEntries.isEmpty()) {
					offeringEntry = offeringEntries.get(0);
				}
				%>

				<c:if test="<%= offeringEntry != null %>">
					<%= longDateFormatDate.format(offeringEntry.getSupportEndDate()) %><br />

					<%= longDateFormatTime.format(offeringEntry.getSupportEndDate()) %>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/support/account_entry_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<div class="table-report">
			<liferay-ui:search-iterator />
		</div>
	</liferay-ui:search-container>
</aui:form>