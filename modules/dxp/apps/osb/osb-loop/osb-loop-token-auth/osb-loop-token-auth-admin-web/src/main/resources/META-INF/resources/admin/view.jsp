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
int tokenAuthEntriesCount = TokenAuthEntryLocalServiceUtil.getTokenAuthEntriesCount();

String orderByCol = ParamUtil.getString(request, "orderByCol", "name");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

OrderByComparator orderByComparator = TokenAuthUtil.getTokenAuthEntryOrderByComparator(orderByCol, orderByType);

PortletURL portletURL = renderResponse.createRenderURL();
%>

<h3><liferay-ui:message key="authorization-tokens" /></h3>

<liferay-ui:success key="tokenAuthEntryDeleted" message="the-token-was-deleted-successfully" />

<liferay-ui:search-container
	emptyResultsMessage="no-tokens-were-found"
	iteratorURL="<%= portletURL %>"
	orderByCol="<%= orderByCol %>"
	orderByComparator="<%= orderByComparator %>"
	orderByType="<%= orderByType %>"
	total="<%= tokenAuthEntriesCount %>"
>
	<liferay-ui:search-container-results
		results="<%= TokenAuthEntryLocalServiceUtil.getTokenAuthEntries(searchContainer.getStart(), searchContainer.getEnd(), orderByComparator) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.loop.token.auth.model.TokenAuthEntry"
		escapedModel="<%= true %>"
		keyProperty="tokenAuthEntryId"
		modelVar="tokenAuthEntry"
	>

		<%
		String lastLoginDate = StringPool.BLANK;

		if (tokenAuthEntry.getLoginDate() != null) {
			lastLoginDate = dateFormatDateTime.format(tokenAuthEntry.getLoginDate());
		}
		%>

		<liferay-ui:search-container-column-text
			name="name"
			orderable="<%= true %>"
			property="userName"
		/>

		<liferay-ui:search-container-column-text
			name="create-date"
			orderable="<%= true %>"
			value="<%= dateFormatDateTime.format(tokenAuthEntry.getCreateDate()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="device"
		/>

		<liferay-ui:search-container-column-text
			name="token"
		/>

		<liferay-ui:search-container-column-text
			name="last-login"
			orderable="<%= true %>"
			value="<%= lastLoginDate %>"
		/>

		<liferay-ui:search-container-column-text
			name="last-ip-address"
			property="loginIP"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/admin/entry_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>