<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
DLPortletToolbarContributor dlPortletToolbarContributor = (DLPortletToolbarContributor)request.getAttribute(DLWebKeys.DOCUMENT_LIBRARY_PORTLET_TOOLBAR_CONTRIBUTOR);

List<Menu> menus = dlPortletToolbarContributor.getPortletTitleMenus(renderRequest, renderResponse);

for (Menu menu : menus) {
	List<URLMenuItem> urlMenuItems = (List<URLMenuItem>)(List<?>)menu.getMenuItems();

	List<AddMenuItem> addMenuItems = new ArrayList<AddMenuItem>();

	for (URLMenuItem urlMenuItem : urlMenuItems) {
		addMenuItems.add(new AddMenuItem(urlMenuItem.getLabel(), urlMenuItem.getURL()));
	}
%>

	<liferay-frontend:add-menu
		addMenuItems="<%= addMenuItems %>"
	/>

<%
}
%>

<aui:script use="aui-base,uploader">
	if (!A.UA.ios && (A.Uploader.TYPE != 'none')) {
		var uploadMultipleDocumentsIcon = A.all('.upload-multiple-documents:hidden');

		uploadMultipleDocumentsIcon.show();
	}
</aui:script>