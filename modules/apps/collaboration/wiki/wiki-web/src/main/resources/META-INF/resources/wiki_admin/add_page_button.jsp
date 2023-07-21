<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
WikiPortletToolbarContributor wikiPortletToolbarContributor = (WikiPortletToolbarContributor)request.getAttribute(WikiWebKeys.WIKI_PORTLET_TOOLBAR_CONTRIBUTOR);

List<Menu> menus = wikiPortletToolbarContributor.getPortletTitleMenus(renderRequest, renderResponse);
%>

<div id="addButtonContainer">

	<%
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

</div>