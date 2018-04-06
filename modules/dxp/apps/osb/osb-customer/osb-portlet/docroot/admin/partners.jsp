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

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm" onSubmit="submitForm(this); return false;">
	<liferay-ui:error exception="<%= RequiredPartnerEntryException.class %>">

		<%
		RequiredPartnerEntryException rpee = (RequiredPartnerEntryException)errorException;
		%>

		<c:if test="<%= rpee.getType() == RequiredPartnerEntryException.REFERENCED_ACCOUNT_ENTRY %>">
			<liferay-ui:message key="you-cannot-remove-partners-that-are-assigned-to-projects" />
		</c:if>

		<c:if test="<%= rpee.getType() == RequiredPartnerEntryException.REFERENCED_PARTNER_ENTRY %>">
			<liferay-ui:message key="you-cannot-remove-a-partner-that-has-child-partners-assigned-to-it" />
		</c:if>

		<c:if test="<%= rpee.getType() == RequiredPartnerEntryException.REFERENCED_PARTNER_WORKER %>">
			<liferay-ui:message key="you-cannot-remove-a-partner-that-has-child-workers-assigned-to-it" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:search-container
		searchContainer="<%= new PartnerEntrySearch(renderRequest, portletURL) %>"
	>

		<%
		PartnerEntryDisplayTerms displayTerms = (PartnerEntryDisplayTerms)searchContainer.getDisplayTerms();
		PartnerEntrySearchTerms searchTerms = (PartnerEntrySearchTerms)searchContainer.getSearchTerms();
		%>

		<%@ include file="/admin/partner_entry_search.jspf" %>

		<liferay-ui:search-container-results>
			<%@ include file="/admin/partner_entry_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.PartnerEntry"
			escapedModel="<%= true %>"
			keyProperty="partnerEntryId"
			modelVar="partnerEntry"
		>
			<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_partner_entry.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				property="code"
			/>

			<liferay-ui:search-container-column-text
				buffer="buffer"
				href="<%= rowURL %>"
				name="parent-partner"
			>

				<%
				PartnerEntry parentPartnerEntry = partnerEntry.getParentPartnerEntry();

				if (parentPartnerEntry != null) {
					buffer.append(HtmlUtil.escape(parentPartnerEntry.getCode()));
				}
				%>

			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
			>
				<%= LanguageUtil.get(request, WorkflowConstants.getStatusLabel(partnerEntry.getStatus())) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/admin/partner_entry_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>