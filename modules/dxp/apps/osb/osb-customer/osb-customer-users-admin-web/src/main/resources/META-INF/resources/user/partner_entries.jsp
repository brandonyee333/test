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

<liferay-ui:search-container
	emptyResultsMessage="no-partners-were-found"
>
	<liferay-ui:search-container-results
		results="<%= PartnerEntryLocalServiceUtil.getUserPartnerEntries(selUser.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.model.PartnerEntry"
		escapedModel="<%= true %>"
		keyProperty="partnerEntryId"
		modelVar="partnerEntry"
	>
		<liferay-portlet:renderURL portletName="<%= OSBPortletKeys.OSB_ADMIN %>" varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/edit_partner_entry.jsp" />
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

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="role"
		>

			<%
			PartnerWorker partnerWorker = PartnerWorkerLocalServiceUtil.getPartnerWorker(selUser.getUserId(), partnerEntry.getPartnerEntryId());
			%>

			<liferay-ui:message key="<%= partnerWorker.getRoleLabel() %>" />
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>