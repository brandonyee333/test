<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
boolean enableRSS = !PortalUtil.isRSSFeedsEnabled() ? false : GetterUtil.getBoolean(portletPreferences.getValue("enableRss", null), true);
int rssDelta = GetterUtil.getInteger(portletPreferences.getValue("rssDelta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String rssDisplayStyle = portletPreferences.getValue("rssDisplayStyle", RSSUtil.DISPLAY_STYLE_DEFAULT);
String rssFeedType = portletPreferences.getValue("rssFeedType", RSSUtil.FEED_TYPE_DEFAULT);

Group guestGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), GroupConstants.GUEST);

String blogsPortletId = PortletProviderUtil.getPortletId(BlogsEntry.class.getName(), PortletProvider.Action.VIEW);

long blogsPlid = PortalUtil.getPlidFromPortletId(group.getGroupId(), blogsPortletId);

String blogsFriendlyURL = null;

if (blogsPlid != LayoutConstants.DEFAULT_PLID) {
	blogsFriendlyURL = PortalUtil.getLayoutFullURL(group.getGroupId(), blogsPortletId, request.isSecure());
}

String messageBoardsPortletId = PortletProviderUtil.getPortletId(MBMessage.class.getName(), PortletProvider.Action.VIEW);

long mbPlid = PortalUtil.getPlidFromPortletId(guestGroup.getGroupId(), messageBoardsPortletId);

String mbFriendlyURL = null;

if (mbPlid != LayoutConstants.DEFAULT_PLID) {
	mbFriendlyURL = PortalUtil.getLayoutFullURL(guestGroup.getGroupId(), messageBoardsPortletId, request.isSecure());
}
%>