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

String addEnvironmentURL = StringPool.BLANK;
JSONArray accountEnvironmentsJSONArray = JSONFactoryUtil.createJSONArray();
JSONObject environmentConfigurationJSONObject = JSONFactoryUtil.createJSONObject();
boolean permitAdd = false;
boolean permitDelete = false;
boolean permitEdit = false;

if ((accountEntry != null) && (!accountEntryViewDisplayContext.hasOnlyLXC() || permissionChecker.isOmniadmin())) {
	addEnvironmentURL = accountEntryViewDisplayContext.getAccountEnvironmentAddURL(accountEntry);
	accountEnvironmentsJSONArray = accountEntryViewDisplayContext.getAccountEnvironmentsJSONArray();
	environmentConfigurationJSONObject = accountEntryViewDisplayContext.getEnvironmentConfigurationJSONObject();
	permitAdd = AccountEnvironmentPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT);
	permitDelete = AccountEnvironmentPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.DELETE);
	permitEdit = AccountEnvironmentPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.UPDATE);
}
%>

<liferay-util:include page="/account_entry_details/customer_portal_banner.jsp" servletContext="<%= application %>" />

<c:if test="<%= !accountEntryViewDisplayContext.hasOnlyLXC() || permissionChecker.isOmniadmin() %>">
	<div class="account-environments card" id="<portlet:namespace />accountEnvironments"></div>

	<aui:script>
		AccountDetails.render(
			AccountDetails.AccountEnvironments,
			{
				addEnvironmentURL: '<%= addEnvironmentURL %>',
				environmentConfiguration: <%= environmentConfigurationJSONObject %>,
				environments: <%= accountEnvironmentsJSONArray %>,
				permitAdd: <%= permitAdd %>,
				permitDelete: <%= permitDelete %>,
				permitEdit: <%= permitEdit %>
			},
			document.getElementById('<portlet:namespace />accountEnvironments')
		);
	</aui:script>
</c:if>