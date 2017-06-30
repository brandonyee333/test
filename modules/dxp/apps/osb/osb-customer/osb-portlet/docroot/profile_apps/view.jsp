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
DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(themeDisplay.getScopeGroupId());

int maxDisplay = 0;

if (developerEntry.isCompany()) {
	maxDisplay = PortletPropsValues.PROFILE_CORP_APPS_MAX_DISPLAYED;
}
else if (developerEntry.isUser()) {
	maxDisplay = PortletPropsValues.PROFILE_USER_APPS_MAX_DISPLAYED;
}
%>

<div class="asset cleared container">

	<%
	long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);

	List<AppEntry> appEntries = AppEntryLocalServiceUtil.getAppEntries(developerEntry.getDeveloperEntryId(), WorkflowConstants.STATUS_APPROVED, 0, maxDisplay, new AppEntryDownloadCountComparator(false));

	for (AppEntry appEntry : appEntries) {
	%>

		<liferay-portlet:renderURL plid="<%= marketplacePlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE %>" var="appEntryURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
			<portlet:param name="mvcPath" value="/marketplace/view_app_entry.jsp" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-util:include page="/marketplace/asset_entry_display.jsp" servletContext="<%= application %>">
			<liferay-util:param name="assetEntryURL" value="<%= appEntryURL.toString() %>" />
			<liferay-util:param name="className" value="<%= AppEntry.class.getName() %>" />
			<liferay-util:param name="classPK" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
			<liferay-util:param name="displayStyle" value="1" />
			<liferay-util:param name="title" value="<%= appEntry.getTitle() %>" />
		</liferay-util:include>

	<%
	}

	int appEntriesCount = AppEntryLocalServiceUtil.getAppEntriesCount(developerEntry.getDeveloperEntryId(), WorkflowConstants.STATUS_APPROVED);

	String keywordPrefix = StringPool.BLANK;

	if (developerEntry.isCompany()) {
		keywordPrefix = MarketplaceUtil.ASSET_COMPANY_KEYWORD;
	}
	else {
		keywordPrefix = MarketplaceUtil.ASSET_DEVELOPER_KEYWORD;
	}
	%>

	<c:if test="<%= appEntriesCount > maxDisplay %>">
		<liferay-portlet:renderURL plid="<%= marketplacePlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE %>" var="moreAppsURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
			<portlet:param name="mvcPath" value="/marketplace/search.jsp" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="keywords" value="<%= keywordPrefix + developerEntry.getDeveloperEntryId() %>" />
		</liferay-portlet:renderURL>

		<div class="cleared">
			<a class="unit" href="<%= moreAppsURL.toString() %>"><liferay-ui:message key="more-apps" /></a>
		</div>
	</c:if>

	<c:if test="<%= appEntries.isEmpty() %>">
		<liferay-ui:message key="there-are-no-apps" />
	</c:if>
</div>