<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
String navigation = ParamUtil.getString(request, "navigation", "home");

int curEntry = ParamUtil.getInteger(request, "curEntry");
int deltaEntry = ParamUtil.getInteger(request, "deltaEntry");

long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

long fileEntryTypeId = ParamUtil.getLong(request, "fileEntryTypeId", -1);

String displayStyle = ParamUtil.getString(request, "displayStyle");

if (Validator.isNull(displayStyle)) {
	displayStyle = portalPreferences.getValue(DLPortletKeys.DOCUMENT_LIBRARY, "display-style", PropsValues.DL_DEFAULT_DISPLAY_VIEW);
}

String keywords = ParamUtil.getString(request, "keywords");

PortletURL displayStyleURL = renderResponse.createRenderURL();

String mvcRenderCommandName = "/document_library/search";

if (Validator.isNull(keywords)) {
	if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
		mvcRenderCommandName = "/document_library/view";
	}
	else {
		mvcRenderCommandName = "/document_library/view_folder";
	}
}

displayStyleURL.setParameter("mvcRenderCommandName", mvcRenderCommandName);

displayStyleURL.setParameter("navigation", HtmlUtil.escapeJS(navigation));

if (curEntry > 0) {
	displayStyleURL.setParameter("curEntry", String.valueOf(curEntry));
}

if (deltaEntry > 0) {
	displayStyleURL.setParameter("deltaEntry", String.valueOf(deltaEntry));
}

displayStyleURL.setParameter("folderId", String.valueOf(folderId));

if (fileEntryTypeId != -1) {
	displayStyleURL.setParameter("fileEntryTypeId", String.valueOf(fileEntryTypeId));
}
%>

<liferay-frontend:management-bar-display-buttons
	displayViews="<%= dlPortletInstanceSettings.getDisplayViews() %>"
	portletURL="<%= displayStyleURL %>"
	selectedDisplayStyle="<%= displayStyle %>"
/>