<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/account_entry_details/init.jsp" %>

<liferay-util:include page="/account_entry_details/customer_portal_banner.jsp" servletContext="<%= application %>" />

<div class="card source-code-access" id="<portlet:namespace />sourceCodeAccess"></div>

<aui:script>
	AccountDetails.render(
		AccountDetails.SourceCodeAccess,
		{
			addCollaboratorURL: '<%= accountEntryViewDisplayContext.getCollaboratorAddURL() %>',
			collaborators: <%= accountEntryViewDisplayContext.getCollaboratorsJSONArray() %>
		},
		document.getElementById('<portlet:namespace />sourceCodeAccess')
	);
</aui:script>