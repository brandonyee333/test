<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/account_entry_details/init.jsp" %>

<%
AccountEntry accountEntry = accountEntryViewDisplayContext.getAccountEntry();

String instructions = StringPool.BLANK;

if (accountEntry != null) {
	instructions = HtmlUtil.escapeJS(accountEntry.getInstructions());
}
%>

<div class="card support-instructions" id="<portlet:namespace />supportInstructions"></div>

<aui:script>
	AccountDetails.render(
		AccountDetails.SupportInstructions,
		{
			instructions: '<%= instructions %>'
		},
		document.getElementById('<portlet:namespace />supportInstructions')
	);
</aui:script>