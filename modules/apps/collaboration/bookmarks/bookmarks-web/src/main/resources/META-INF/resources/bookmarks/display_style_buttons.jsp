<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/bookmarks/init.jsp" %>

<%
String navigation = ParamUtil.getString(request, "navigation", "all");

int curEntry = ParamUtil.getInteger(request, "curEntry");
int deltaEntry = ParamUtil.getInteger(request, "deltaEntry");

long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

String displayStyle = ParamUtil.getString(request, "displayStyle");

if (Validator.isNull(displayStyle)) {
	displayStyle = portalPreferences.getValue(BookmarksPortletKeys.BOOKMARKS, "display-style", "descriptive");
}

String keywords = ParamUtil.getString(request, "keywords");

PortletURL displayStyleURL = renderResponse.createRenderURL();

if (Validator.isNull(keywords)) {
	if (folderId == BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
		displayStyleURL.setParameter("mvcRenderCommandName", "/bookmarks/view");
	}
	else {
		displayStyleURL.setParameter("mvcRenderCommandName", "/bookmarks/view_folder");
		displayStyleURL.setParameter("folderId", String.valueOf(folderId));
	}
}
else {
	displayStyleURL.setParameter("mvcRenderCommandName", "/bookmarks/view");
	displayStyleURL.setParameter("folderId", String.valueOf(folderId));
}

displayStyleURL.setParameter("navigation", HtmlUtil.escapeJS(navigation));

if (curEntry > 0) {
	displayStyleURL.setParameter("curEntry", String.valueOf(curEntry));
}

if (deltaEntry > 0) {
	displayStyleURL.setParameter("deltaEntry", String.valueOf(deltaEntry));
}
%>

<liferay-frontend:management-bar-display-buttons
	displayViews='<%= new String[] {"descriptive", "list"} %>'
	portletURL="<%= displayStyleURL %>"
	selectedDisplayStyle="<%= displayStyle %>"
/>