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

<%@ include file="/account_entry_details/init.jsp" %>

<%
AccountEntry accountEntry = accountEntryViewDisplayContext.getAccountEntry();

PortletURL portletURL = renderResponse.createRenderURL();
%>

<liferay-ui:search-container
	emptyResultsMessage="no-offerings-were-found"
	headerNames="product,sla,start-date,support-end-date,license,tickets,version,instance-size,status"
	iteratorURL="<%= portletURL %>"
>
	<liferay-ui:search-container-results>

		<%
		if (accountEntry.getType() != AccountEntryConstants.TYPE_INDIVIDUAL) {
			results = OfferingEntryGroupFactoryUtil.createOfferingEntryGroups(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, new LinkedHashMap(), true, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			searchContainer.setTotal(results.size());
			searchContainer.setResults(results);
		}
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.osb.model.OfferingEntryGroup"
		modelVar="offeringEntryGroup"
	>

		<%
		ProductEntry productEntry = offeringEntryGroup.getProductEntry();
		SupportResponse supportResponse = offeringEntryGroup.getSupportResponse();
		%>

		<liferay-ui:search-container-column-text
			name="product"
			value="<%= productEntry.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="sla"
			value="<%= supportResponse.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="start-date"
			value="<%= longDateFormatDate.format(offeringEntryGroup.getActualStartDate()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="support-end-date"
			value="<%= longDateFormatDate.format(offeringEntryGroup.getSupportEndDate()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="licenses"
		>
			<%= offeringEntryGroup.getLicenseKeysCount() %> / <%= offeringEntryGroup.getQuantity() %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="tickets"
			value='<%= offeringEntryGroup.isSupportTickets() ? LanguageUtil.get(request, "unlimited") : "0" %>'
		/>

		<liferay-ui:search-container-column-text
			name="version"
			value="<%= OfferingEntryConstants.getVersionLabel(offeringEntryGroup.getVersion()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="instance-size"
			value="<%= LanguageUtil.get(request, offeringEntryGroup.getSizingLabel()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="status"
		>

			<%
			String offeringStatus = OfferingEntryConstants.getStatusLabel(offeringEntryGroup.getStatus());
			%>

			<span class="label label-sm label-<%= offeringStatus %>"><%= offeringStatus %></span>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		paginate="<%= false %>"
	/>
</liferay-ui:search-container>