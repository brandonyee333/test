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
User selUser = (User)request.getAttribute("user.selUser");
%>

<h1>
	<liferay-ui:message key="projects" />
</h1>

<hr />

<h2>
	<liferay-ui:message key="customer" />
</h2>

<liferay-ui:search-container
	emptyResultsMessage="no-projects-were-found"
>

	<%
	LinkedHashMap<String, Object> params = new LinkedHashMap<>();

	params.put("accountCustomer", Long.valueOf(selUser.getUserId()));
	%>

	<liferay-ui:search-container-results
		results="<%= AccountEntryLocalServiceUtil.search(null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.model.AccountEntry"
		escapedModel="<%= true %>"
		keyProperty="accountEntryId"
		modelVar="accountEntry"
	>
		<liferay-portlet:renderURL portletName="<%= OSBPortletKeys.OSB_ADMIN %>" varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
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
			name="support-regions"
		>

			<%
			List<SupportRegion> supportRegions = accountEntry.getSupportRegions();
			%>

			<%
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

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="role"
		>

			<%
			AccountCustomer accountCustomer = AccountCustomerLocalServiceUtil.getAccountCustomer(selUser.getUserId(), accountEntry.getAccountEntryId());
			%>

			<liferay-ui:message key="<%= accountCustomer.getRoleLabel() %>" />
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>

<h2>
	<liferay-ui:message key="worker" />
</h2>

<liferay-ui:search-container
	emptyResultsMessage="no-projects-were-found"
>

	<%
	LinkedHashMap<String, Object> params = new LinkedHashMap<>();

	params.put("accountWorker", new Long[] {selUser.getUserId(), null});
	%>

	<liferay-ui:search-container-results
		results="<%= AccountEntryLocalServiceUtil.search(null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.model.AccountEntry"
		escapedModel="<%= true %>"
		keyProperty="accountEntryId"
		modelVar="accountEntry"
	>
		<liferay-portlet:renderURL portletName="<%= OSBPortletKeys.OSB_ADMIN %>" varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
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
			name="support-regions"
		>

			<%
			List<SupportRegion> supportRegions = accountEntry.getSupportRegions();
			%>

			<%
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

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="role"
		>

			<%
			AccountWorker accountWorker = AccountWorkerLocalServiceUtil.getAccountWorker(selUser.getUserId(), accountEntry.getAccountEntryId());
			%>

			<liferay-ui:message key="<%= accountWorker.getRoleLabel() %>" />
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>