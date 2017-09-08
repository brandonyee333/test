<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
long accountEntryId = ParamUtil.getLong(request, "accountEntryId");
%>

<portlet:actionURL name="updateAccountEnvironment" var="updateAccountEnvironmentURL">
	<portlet:param name="mvcPath" value="/support/2/environment_details.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateAccountEnvironmentURL %>" method="post" name="fm" onSubmit="submitForm(this); return false;">
	<h1 class="section-heading">
		<liferay-ui:message key="project" />
	</h1>

	<%
	List<AccountEntry> accountEntries = AccountEntryLocalServiceUtil.getUserAccountEntries(user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	%>

	<div>
		<c:choose>
			<c:when test="<%= accountEntries.size() == 1 %>">

				<%
				AccountEntry accountEntry = accountEntries.get(0);

				accountEntryId = accountEntry.getAccountEntryId();
				%>

				<strong><%= HtmlUtil.escape(accountEntry.getName()) %></strong>

				<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />
			</c:when>
			<c:otherwise>
				<aui:select name="accountEntryId" onChange='<%= renderResponse.getNamespace() + "selectAccountEnvironment();" %>'>
					<aui:option value="" />

					<%
					for (AccountEntry accountEntry : accountEntries) {
						if (accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) {
							continue;
						}
					%>

						<aui:option label="<%= accountEntry.getName() %>" selected="<%= accountEntry.getAccountEntryId() == accountEntryId %>" value="<%= accountEntry.getAccountEntryId() %>" />

					<%
					}
					%>

				</aui:select>
			</c:otherwise>
		</c:choose>
	</div>

	<c:if test="<%= accountEntryId > 0 %>">
		<liferay-util:include page="/support/2/view_account_environments.jsp" servletContext="<%= application %>">
			<liferay-util:param name="redirect" value="<%= currentURL %>" />
			<liferay-util:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" />
		</liferay-util:include>
	</c:if>
</aui:form>

<aui:script>
	function <portlet:namespace />selectAccountEnvironment() {
		var selectAccountEnvironmentURL = '<portlet:renderURL><portlet:param name="mvcPath" value="/support/2/view.jsp" /><portlet:param name="tabs1" value="environment-details" /></portlet:renderURL>';

		submitForm(document.<portlet:namespace />fm, selectAccountEnvironmentURL);
	}
</aui:script>