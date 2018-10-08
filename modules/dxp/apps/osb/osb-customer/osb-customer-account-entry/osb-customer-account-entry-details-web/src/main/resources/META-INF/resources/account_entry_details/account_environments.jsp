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

String addEnvironmentURL = accountEntryViewDisplayContext.getAccountEnvironmentAddURL(accountEntry);

JSONArray accountEnvironmentsJSONArray = accountEntryViewDisplayContext.getAccountEnvironmentsJSONArray();

JSONObject environmentConfigurationJSONObject = accountEntryViewDisplayContext.getEnvironmentConfigurationJSONObject();
%>

<div class="account-environments card" id="<portlet:namespace />accountEnvironments"></div>

<aui:script>
	AccountDetails.render(
		AccountDetails.AccountEnvironments,
		{
			addEnvironmentURL: '<%= addEnvironmentURL %>',
			environmentConfiguration: <%= environmentConfigurationJSONObject %>,
			environments: <%= accountEnvironmentsJSONArray %>,
			permitAdd: <%= OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT) %>
		},
		document.getElementById('<portlet:namespace />accountEnvironments')
	);
</aui:script>