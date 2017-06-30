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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

AppEntry appEntry = (AppEntry)row.getObject();
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="statsURL">
		<portlet:param name="mvcPath" value="/marketplace_developer_apps/stats_views.jsp" />
		<portlet:param name="tabs1" value="apps" />
		<portlet:param name="tabs2" value="views" />
		<portlet:param name="assetEntryId" value="<%= String.valueOf(appEntry.getAssetEntry().getEntryId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="services"
		message="metrics"
		url="<%= statsURL %>"
	/>

	<portlet:renderURL var="editURL">
		<portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" />
		<portlet:param name="backURL" value="<%= currentURL %>" />
		<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="edit"
		url="<%= editURL %>"
	/>

	<c:if test="<%= appEntry.isApproved() %>">
		<portlet:actionURL name="deactivateAppEntry" var="deactivateURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
		</portlet:actionURL>

		<%
		String taglibDeactivateURL = "javascript:if (confirm('" + themeDisplay.translate("are-you-sure-you-want-to-deactivate-this") + "')) {submitForm(document.hrefFm, '" + deactivateURL + "');}";
		%>

		<liferay-ui:icon
			image="deactivate"
			url="<%= taglibDeactivateURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>