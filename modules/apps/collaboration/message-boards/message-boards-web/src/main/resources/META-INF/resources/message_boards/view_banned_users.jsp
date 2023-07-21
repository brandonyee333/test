<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/message_boards/init.jsp" %>

<liferay-util:include page="/message_boards/top_links.jsp" servletContext="<%= application %>" />

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/message_boards/view_banned_users");
%>

<div class="main-content-body">
	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-banned-users"
		headerNames="banned-user,banned-by,ban-date"
		iteratorURL="<%= portletURL %>"
		total="<%= MBBanLocalServiceUtil.getBansCount(scopeGroupId) %>"
	>
		<liferay-ui:search-container-results
			results="<%= MBBanLocalServiceUtil.getBans(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.message.boards.kernel.model.MBBan"
			keyProperty="banId"
			modelVar="ban"
		>

			<%
			User bannedUser = UserLocalServiceUtil.fetchUser(ban.getBanUserId());
			%>

			<liferay-ui:search-container-column-text
				href="<%= ((bannedUser != null) && bannedUser.isActive()) ? bannedUser.getDisplayURL(themeDisplay) : null %>"
				name="banned-user"
				value="<%= HtmlUtil.escape(PortalUtil.getUserName(ban.getBanUserId(), StringPool.BLANK)) %>"
			/>

			<%
			User bannedByUser = UserLocalServiceUtil.fetchUser(ban.getUserId());
			%>

			<liferay-ui:search-container-column-text
				href="<%= ((bannedByUser != null) && bannedByUser.isActive()) ? bannedByUser.getDisplayURL(themeDisplay) : null %>"
				name="banned-by"
				value="<%= HtmlUtil.escape(PortalUtil.getUserName(ban.getUserId(), StringPool.BLANK)) %>"
			/>

			<liferay-ui:search-container-column-date
				name="ban-date"
				value="<%= ban.getCreateDate() %>"
			/>

			<c:if test="<%= PropsValues.MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL > 0 %>">
				<liferay-ui:search-container-column-date
					name="unban-date"
					value="<%= MBUtil.getUnbanDate(ban, PropsValues.MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL) %>"
				/>
			</c:if>

			<liferay-ui:search-container-column-jsp
				align="right"
				cssClass="entry-action"
				path="/message_boards/ban_user_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>

<%
PortalUtil.setPageSubtitle(LanguageUtil.get(request, "banned-users"), request);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, TextFormatter.format("banned-users", TextFormatter.O)), portletURL.toString());
%>