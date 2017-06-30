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

<%@ include file="/marketplace/init.jsp" %>

<%
String tabs1 = (String)request.getAttribute("view_purchased.jsp-tabs1");

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

int compatibility = ParamUtil.getInteger(request, "compatibility");

AppVersion appVersion = null;

if (tabs1.equals("developer")) {
	List<AppVersion> appVersions = AppVersionLocalServiceUtil.getAppVersions(appEntryId, compatibility, WorkflowConstants.STATUS_DRAFT, 0, 1);

	if (!appVersions.isEmpty()) {
		appVersions = AppVersionLocalServiceUtil.getAppVersions(appEntryId, compatibility, WorkflowConstants.STATUS_APPROVED, 0, 1);
	}

	if (!appVersions.isEmpty()) {
		appVersion = appVersions.get(0);
	}
	else {
		appVersion = appEntry.getActionableAppVersion();
	}
}
else {
	List<AppVersion> appVersions = AppVersionLocalServiceUtil.getAppVersions(appEntryId, compatibility, WorkflowConstants.STATUS_APPROVED, 0, 1);

	if (!appVersions.isEmpty()) {
		appVersion = appVersions.get(0);
	}
	else {
		appVersion = appEntry.getApprovedAppVersion();
	}
}
%>

<div class="app-entry-display" data-appId="<%= appEntry.getAppEntryId() %>">
	<div class="item description">
		<div class="icon">
			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveIcon" var="iconURL">
				<portlet:param name="assetAttachmentId" value="<%= String.valueOf(appVersion.getIconImageId()) %>" />
			</liferay-portlet:resourceURL>

			<img class="small" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
		</div>

		<div class="info">
			<div>
				<c:choose>
					<c:when test="<%= !appEntry.isApproved() || appEntry.isHidden() %>">
						<%= appEntry.getTitle() %>
					</c:when>
					<c:otherwise>
						<a href="javascript:Liferay.MarketplaceUtil.openMarketplaceStore(<%= appEntryId %>);" title="<%= appEntry.getTitle() %>">
							<%= appEntry.getTitle() %>
						</a>
					</c:otherwise>
				</c:choose>

				<span class="developer">

					<%
					DeveloperEntry developerEntry = appEntry.getDeveloperEntry();
					%>

					<%= developerEntry.getName() %>
				</span>

				<span class="version">
					<liferay-ui:message arguments="<%= HtmlUtil.escape(appVersion.getVersion()) %>" key="version-x" />
				</span>
			</div>

			<div>
				<ul>
					<c:if test="<%= AppFlagLocalServiceUtil.hasAppFlag(appVersion.getAppVersionId(), AppFlagConstants.TYPE_DEPRECATED) %>">
						<li>
							<strong><liferay-ui:message key="final-version" />:</strong>

							<liferay-ui:message key="no-new-versions-will-be-available-for-this-app" />
						</li>
					</c:if>
				</ul>
			</div>

			<div class="description" onClick="Liferay.MarketplaceUtil.openMarketplaceStore(<%= appEntryId %>)">

				<%
				String description = appVersion.getDescription(themeDisplay.getLanguageId());

				description = MarketplaceUtil.shortenString(description, _MAX_DESCRIPTION_LENGTH);
				%>

				<%= MarketplaceMarkupUtil.getHTML(description) %>
			</div>

			<div class="fade-mask" onClick="Liferay.MarketplaceUtil.openMarketplaceStore(<%= appEntryId %>)"></div>
		</div>
	</div>
</div>

<%!
private static final int _MAX_DESCRIPTION_LENGTH = 500;
%>