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
			className="com.liferay.osb.customer.admin.model.AccountEntry"
			escapedModel="<%= true %>"
			keyProperty="accountEntryId"
			modelVar="accountEntry"
		>
			<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
				<portlet:param name="backURL" value="<%= portletURL.toString() %>" />
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
				name="support-regions"
			>

				<%
				List<SupportRegion> supportRegions = accountEntry.getSupportRegions();

				for (int i = 0; i < supportRegions.size(); i++) {
					SupportRegion supportRegion = supportRegions.get(i);
				%>

					<aui:a href="<%= rowURL.toString() %>" label="<%= supportRegion.getName() %>" />

					<%= ((i + 1) < supportRegions.size()) ? "<br />" : "" %>

				<%
				}
				%>

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
	function <portlet:namespace />toggleSupportRegions(accountEntryId, expand) {
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