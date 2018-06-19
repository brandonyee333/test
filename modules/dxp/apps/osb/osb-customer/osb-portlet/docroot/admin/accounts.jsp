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
PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<aui:form action="<%= portletURL.toString() %>" method="post">
	<liferay-ui:error exception="<%= RequiredAccountEntryException.class %>" message="you-cannot-remove-projects-that-have-users" />

	<liferay-ui:search-container
		searchContainer="<%= new AccountEntrySearch(renderRequest, portletURL) %>"
	>

		<%
		AccountEntryDisplayTerms displayTerms = (AccountEntryDisplayTerms)searchContainer.getDisplayTerms();
		AccountEntrySearchTerms searchTerms = (AccountEntrySearchTerms)searchContainer.getSearchTerms();
		%>

		<%@ include file="/admin/account_entry_search.jspf" %>

		<liferay-ui:search-container-results>
			<%@ include file="/admin/account_entry_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.AccountEntry"
			escapedModel="<%= true %>"
			keyProperty="accountEntryId"
			modelVar="accountEntry"
		>
			<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
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

			<liferay-ui:search-container-column-text
				buffer="buffer"
				href="<%= rowURL %>"
				name="partner"
			>

				<%
				PartnerEntry partnerEntry = accountEntry.getPartnerEntry();

				if (partnerEntry != null) {
					buffer.append(HtmlUtil.escape(partnerEntry.getCode()));
				}
				%>

			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-end-date"
			>

				<%
				LinkedHashMap params = new LinkedHashMap();

				params.put("productEntry", ProductEntryConstants.TYPE_PRIMARY);

				List<OfferingEntry> offeringEntries = OfferingEntryLocalServiceUtil.search(0, accountEntry.getAccountEntryId(), new int[0], new int[] {OfferingEntryConstants.STATUS_ACTIVE}, 0, 0, 0, 0, 0, 0, params, true, 0, 1, new OfferingEntrySupportEndDateComparator());

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

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="highest-sla"
			>

				<%
				long supportResponseId = accountEntry.getHighestSupportResponseId();

				SupportResponse supportResponse = SupportResponseLocalServiceUtil.fetchSupportResponse(supportResponseId);
				%>

				<c:if test="<%= supportResponse != null %>">
					<liferay-ui:message key="<%= supportResponse.getSupportLevelLabel() %>" />
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="tier"
			>
				<liferay-ui:message key="<%= AccountEntryConstants.getTierLabel(accountEntry.getTier()) %>" />
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="support-regions"
			>

				<%
				List<SupportRegion> supportRegions = accountEntry.getSupportRegions();
				%>

				<c:if test="<%= !supportRegions.isEmpty() %>">
					<span class="expand" id="<portlet:namespace />expand_<%= accountEntry.getAccountEntryId() %>_supportRegions">
						<liferay-ui:icon
							image="../arrows/01_plus"
							label="<%= true %>"
							message="expand"
							url='<%= "javascript:" + renderResponse.getNamespace() + "toggleSupportRegions(" + accountEntry.getAccountEntryId() + ", true);" %>'
						/>
					</span>
					<span class="collapse hide" id="<portlet:namespace />collapse_<%= accountEntry.getAccountEntryId() %>_supportRegions">
						<liferay-ui:icon
							image="../arrows/01_minus"
							label="<%= true %>"
							message="collapse"
							url='<%= "javascript:" + renderResponse.getNamespace() + "toggleSupportRegions(" + accountEntry.getAccountEntryId() + ", false);" %>'
						/>
					</span>

					<div class="hide" id="<portlet:namespace />supportRegions_<%= accountEntry.getAccountEntryId() %>">

						<%
						for (int i = 0; i < supportRegions.size(); i++) {
							SupportRegion supportRegion = supportRegions.get(i);
						%>

							<aui:a href="<%= rowURL.toString() %>" label="<%= supportRegion.getName() %>" />

							<%= ((i + 1) < supportRegions.size()) ? "<br />" : "" %>

						<%
						}
						%>

					</div>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
			>
				<%= LanguageUtil.get(request, accountEntry.getStatusLabel()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/admin/account_entry_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<div>
			<portlet:renderURL var="editAccountEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			</portlet:renderURL>

			<aui:a cssClass="btn btn-default" href="<%= editAccountEntryURL %>" label="add-project" />
		</div>

		<br />

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	function <portlet:namespace />toggleSupportRegions(accountEntryIdId, expand) {
		var A = AUI();

		if (expand) {
			A.one('#<portlet:namespace />supportRegions_' + accountEntryId).show();
			A.one('#<portlet:namespace />collapse_' + accountEntryId + '_supportRegions').show();
			A.one('#<portlet:namespace />expand_' + accountEntryId + '_supportRegions').hide();
		}
		else {
			A.one('#<portlet:namespace />supportRegions_' + accountEntryId).hide();
			A.one('#<portlet:namespace />collapse_' + accountEntryId + '_supportRegions').hide();
			A.one('#<portlet:namespace />expand_' + accountEntryId + '_supportRegions').show();
		}
	}
</aui:script>