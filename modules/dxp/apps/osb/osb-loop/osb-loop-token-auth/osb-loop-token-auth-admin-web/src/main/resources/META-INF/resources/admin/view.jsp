<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
int tokenAuthEntriesCount = TokenAuthEntryLocalServiceUtil.getTokenAuthEntriesCount();

String orderByCol = ParamUtil.getString(request, "orderByCol", "name");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

OrderByComparator orderByComparator = TokenAuthUtil.getTokenAuthEntryOrderByComparator(orderByCol, orderByType);
%>

<h3><liferay-ui:message key="authorization-tokens" /></h3>

<liferay-ui:success key="tokenAuthEntryDeleted" message="the-token-was-deleted-successfully" />

<liferay-ui:search-container
	emptyResultsMessage="no-tokens-were-found"
	iteratorURL="<%= renderResponse.createRenderURL() %>"
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