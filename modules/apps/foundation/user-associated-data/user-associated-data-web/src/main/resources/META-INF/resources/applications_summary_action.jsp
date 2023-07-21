<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

UADApplicationSummaryDisplay uadApplicationSummaryDisplay = (UADApplicationSummaryDisplay)row.getObject();
%>

<c:if test="<%= uadApplicationSummaryDisplay.hasItems() %>">
	<liferay-ui:icon-menu
		direction="left-side"
		icon="<%= StringPool.BLANK %>"
		markupView="lexicon"
		message="<%= StringPool.BLANK %>"
		showWhenSingleIcon="<%= true %>"
	>
		<c:if test="<%= Validator.isNotNull(uadApplicationSummaryDisplay.getViewURL()) %>">
			<liferay-ui:icon
				message="view"
				url="<%= uadApplicationSummaryDisplay.getViewURL() %>"
			/>
		</c:if>

		<portlet:actionURL name="/anonymize_application_uad_entities" var="anonymizeUADEntitiesURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="p_u_i_d" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
			<portlet:param name="applicationKey" value="<%= uadApplicationSummaryDisplay.getApplicationKey() %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message="anonymize"
			url="<%= anonymizeUADEntitiesURL.toString() %>"
		/>

		<portlet:actionURL name="/delete_application_uad_entities" var="deleteUADEntitiesURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="p_u_i_d" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
			<portlet:param name="applicationKey" value="<%= uadApplicationSummaryDisplay.getApplicationKey() %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message="delete"
			url="<%= deleteUADEntitiesURL.toString() %>"
		/>
	</liferay-ui:icon-menu>
</c:if>