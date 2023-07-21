<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/blogs/init.jsp" %>

<%
BlogsEntry entry = (BlogsEntry)request.getAttribute("view_entry_content.jsp-entry");

String socialBookmarksDisplayStyle = blogsPortletInstanceConfiguration.socialBookmarksDisplayStyle();
%>

<portlet:renderURL var="bookmarkURL" windowState="<%= WindowState.NORMAL.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="/blogs/view_entry" />

	<c:choose>
		<c:when test="<%= Validator.isNotNull(entry.getUrlTitle()) %>">
			<portlet:param name="urlTitle" value="<%= entry.getUrlTitle() %>" />
		</c:when>
		<c:otherwise>
			<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
		</c:otherwise>
	</c:choose>
</portlet:renderURL>

<div class="<%= socialBookmarksDisplayStyle.equals("vertical") ? "pull-right" : StringPool.BLANK %> social-bookmarks">
	<liferay-ui:social-bookmarks
		contentId="<%= String.valueOf(entry.getEntryId()) %>"
		displayStyle="<%= blogsPortletInstanceConfiguration.socialBookmarksDisplayStyle() %>"
		target="_blank"
		title="<%= entry.getTitle() %>"
		types="<%= blogsPortletInstanceConfiguration.socialBookmarksTypes() %>"
		url="<%= PortalUtil.getCanonicalURL(bookmarkURL.toString(), themeDisplay, layout) %>"
	/>
</div>