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
int clientBuild = ParamUtil.getInteger(request, "clientBuild");

int compatibility = ParamUtil.getInteger(request, "compatibility");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(PortletPropsValues.MARKETPLACE_APP_ENTRY_ID);

AppPackage appPackage = AppPackageLocalServiceUtil.fetchCompatibleAppPackage(appEntry.getAppEntryId(), compatibility, WorkflowConstants.STATUS_APPROVED_HIDDEN);
%>

<div class="marketplace-server update">
	<div class="update-container">
		<img src="<%= PortalUtil.getPathContext(request) %>/marketplace_server/images/marketplace.svg" />

		<h2>
			<liferay-ui:message key="marketplace-update-available" />
		</h2>

		<p>
			<liferay-ui:message key="this-is-a-required-update-to-enable-new-features" />
		</p>

		<p class="update-app aui-helper-hidden">
			<liferay-ui:message key="click-the-update-button-to-automatically-update-the-marketplace-plugin-to-the-latest-version" />
		</p>

		<p class="hot-deploy-warning aui-helper-hidden">
			<liferay-ui:message key="click-the-download-button-to-download-the-marketplace-plugin-lpkg-file-and-manually-deploy-it-to-your-liferay-portal-installation" />

			<a href="https://web.liferay.com/marketplace/download#known-issues"><liferay-ui:message key="click-here-to-view-known-issues" /></a>
		</p>

		<div class="buttons">
			<a class="btn aui-helper-hidden" data-action="updateApp"><liferay-ui:message key="update" /></a>

			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveApp" var="downloadAppPackageURL">
				<portlet:param name="appPackageId" value="<%= String.valueOf(appPackage.getAppPackageId()) %>" />
			</liferay-portlet:resourceURL>

			<a class="btn aui-helper-hidden" data-action="downloadApp" href="<%= downloadAppPackageURL %>"><liferay-ui:message key="download" /></a>

			<div class="please-wait aui-helper-hidden">
				<liferay-ui:message key="please-wait-a-moment-while-marketplace-updates" />
			</div>
		</div>
	</div>
</div>

<c:choose>
	<c:when test="<%= clientBuild <= 3 %>">
		<%@ include file="/marketplace_server/update_js_client_build_1_to_3.jspf" %>
	</c:when>
	<c:when test="<%= clientBuild == 4 %>">
		<%@ include file="/marketplace_server/update_js_client_build_4.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/marketplace_server/update_js.jspf" %>
	</c:otherwise>
</c:choose>