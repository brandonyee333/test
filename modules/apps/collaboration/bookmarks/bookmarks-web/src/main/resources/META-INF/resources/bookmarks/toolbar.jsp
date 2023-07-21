<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/bookmarks/init.jsp" %>

<%
String searchContainerId = ParamUtil.getString(request, "searchContainerId");

int total = GetterUtil.getInteger((String)request.getAttribute("view.jsp-total"));

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("categoryId", StringPool.BLANK);
portletURL.setParameter("tag", StringPool.BLANK);
%>

<liferay-frontend:management-bar
	disabled="<%= total == 0 %>"
	includeCheckBox="<%= !user.isDefaultUser() %>"
	searchContainerId="<%= searchContainerId %>"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-sidenav-toggler-button
			icon="info-circle"
			label="info"
		/>

		<liferay-util:include page="/bookmarks/display_style_buttons.jsp" servletContext="<%= application %>" />
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>

		<%
		String[] navigationKeys = null;

		if (themeDisplay.isSignedIn()) {
			navigationKeys = new String[] {"all", "recent", "mine"};
		}
		else {
			navigationKeys = new String[] {"all", "recent"};
		}
		%>

		<liferay-frontend:management-bar-navigation
			navigationKeys="<%= navigationKeys %>"
			portletURL="<%= PortletURLUtil.clone(portletURL, liferayPortletResponse) %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-action-buttons>
		<liferay-frontend:management-bar-sidenav-toggler-button
			icon="info-circle"
			label="info"
		/>

		<liferay-frontend:management-bar-button
			href='<%= "javascript:" + renderResponse.getNamespace() + "deleteEntries();" %>'
			icon='<%= TrashUtil.isTrashEnabled(scopeGroupId) ? "trash" : "times" %>'
			label='<%= TrashUtil.isTrashEnabled(scopeGroupId) ? "recycle-bin" : "delete" %>'
		/>
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<aui:script>
	function <portlet:namespace />deleteEntries() {
		if (<%= TrashUtil.isTrashEnabled(scopeGroupId) %> || confirm(' <%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-selected-entries") %>')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val('<%= TrashUtil.isTrashEnabled(scopeGroupId) ? Constants.MOVE_TO_TRASH : Constants.DELETE %>');

			submitForm(form, '<portlet:actionURL name="/bookmarks/edit_entry" />');
		}
	}
</aui:script>