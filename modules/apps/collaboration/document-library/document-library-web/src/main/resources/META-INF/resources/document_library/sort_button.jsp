<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
String navigation = ParamUtil.getString(request, "navigation", "home");

int deltaEntry = ParamUtil.getInteger(request, "deltaEntry");

long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

long fileEntryTypeId = ParamUtil.getLong(request, "fileEntryTypeId", DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_ALL);

String orderByCol = GetterUtil.getString((String)request.getAttribute("view.jsp-orderByCol"));
String orderByType = GetterUtil.getString((String)request.getAttribute("view.jsp-orderByType"));

Map<String, String> orderColumns = new HashMap<String, String>();

orderColumns.put("creationDate", "create-date");
orderColumns.put("downloads", "downloads");
orderColumns.put("modifiedDate", "modified-date");
orderColumns.put("size", "size");
orderColumns.put("title", "title");

PortletURL sortURL = renderResponse.createRenderURL();

sortURL.setParameter("mvcRenderCommandName", (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) ? "/document_library/view" : "/document_library/view_folder");
sortURL.setParameter("navigation", navigation);

if (deltaEntry > 0) {
	sortURL.setParameter("deltaEntry", String.valueOf(deltaEntry));
}

sortURL.setParameter("folderId", String.valueOf(folderId));
sortURL.setParameter("fileEntryTypeId", String.valueOf(fileEntryTypeId));
%>

<liferay-frontend:management-bar-sort
	orderByCol="<%= orderByCol %>"
	orderByType="<%= orderByType %>"
	orderColumns="<%= orderColumns %>"
	portletURL="<%= sortURL %>"
/>