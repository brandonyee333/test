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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long userId = ParamUtil.getLong(request, "userId");
%>

<portlet:actionURL name="updateUserProfile" var="updateUserProfileURL">
	<portlet:param name="mvcPath" value="/admin/edit_user_profile.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateUserProfileURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="userId" type="hidden" value="<%= userId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="profile"
	/>

	<liferay-ui:error exception="<%= UserProfileFirstNameException.class %>" message="please-enter-a-valid-first-name" />
	<liferay-ui:error exception="<%= UserProfileHistoryFirstNameException.class %>" message="please-enter-a-valid-first-name" />
	<liferay-ui:error exception="<%= UserProfileHistoryLastNameException.class %>" message="please-enter-a-valid-last-name" />
	<liferay-ui:error exception="<%= UserProfileLastNameException.class %>" message="please-enter-a-valid-last-name" />

	<aui:model-context model="<%= User.class %>" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="first-name" />
		</td>
		<td>
			<aui:input label="" name="firstName" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="last-name" />
		</td>
		<td>
			<aui:input label="" name="lastName" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="email-address" />
		</td>
		<td>
			<aui:input label="" name="emailAddress" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="legal-entity-name" />
		</td>
		<td>
			<aui:input label="" name="legalEntityName" style="width: 150px;" type="text" value="" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
	</table>

	<br />

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />firstName);
	</script>
</c:if>