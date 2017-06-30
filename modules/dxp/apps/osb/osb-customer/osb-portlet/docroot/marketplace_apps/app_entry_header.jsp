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

<%@ include file="/marketplace_apps/init.jsp" %>

<%
String backURL = ParamUtil.getString(request, "backURL");

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);
%>

<div class="app-heading">

	<%
	long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);
	%>

	<liferay-portlet:renderURL plid="<%= marketplacePlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE %>" var="marketplaceURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
		<portlet:param name="mvcPath" value="/marketplace/view_app_entry.jsp" />
		<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
	</liferay-portlet:renderURL>

	<div class="icon">
		<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveIcon" var="iconURL">
			<portlet:param name="assetAttachmentId" value="<%= String.valueOf(appEntry.getIconImageId()) %>" />
		</liferay-portlet:resourceURL>

		<a src="<%= marketplaceURL %>"><img src="<%= iconURL %>" /></a>
	</div>

	<div class="title">
		<h1>
			<a href="<%= marketplaceURL %>"><%= HtmlUtil.escape(appEntry.getTitle()) %></a>
		</h1>

		<div class="owner">

			<%
			DeveloperEntry developerEntry = appEntry.getDeveloperEntry();
			%>

			<c:choose>
				<c:when test="<%= Validator.isNull(developerEntry.getProfileURL(themeDisplay)) %>">
					<%= HtmlUtil.escape(developerEntry.getName()) %>
				</c:when>
				<c:otherwise>
					<a href="<%= developerEntry.getProfileURL(themeDisplay) %>"><%= HtmlUtil.escape(developerEntry.getName()) %></a>
				</c:otherwise>
			</c:choose>
		</div>

		<c:if test="<%= Validator.isNotNull(backURL) %>">
			<div class="back">
				<a href="<%= HtmlUtil.escapeAttribute(backURL) %>">&laquo; <liferay-ui:message key="back" /></a>
			</div>
		</c:if>
	</div>
</div>