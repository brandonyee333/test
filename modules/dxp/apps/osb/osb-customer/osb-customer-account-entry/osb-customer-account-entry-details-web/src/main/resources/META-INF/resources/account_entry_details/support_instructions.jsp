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