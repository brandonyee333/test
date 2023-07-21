<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/message_boards/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MBStatsUser statsUser = (MBStatsUser)row.getObject();

String[] ranks = MBUtil.getUserRank(mbGroupServiceSettings, themeDisplay.getLanguageId(), statsUser);
%>

<liferay-ui:user-display
	userId="<%= statsUser.getUserId() %>"
>
	<c:if test="<%= Validator.isNotNull(ranks[0]) %>">
		<div class="thread-user-rank">
			<span><liferay-ui:message key="rank" />:</span> <%= HtmlUtil.escape(ranks[0]) %>
		</div>
	</c:if>

	<div class="thread-user-post-count">
		<span><liferay-ui:message key="posts" />:</span> <%= statsUser.getMessageCount() %>
	</div>

	<div class="thread-user-join-date">
		<span><liferay-ui:message key="join-date" />:</span> <%= dateFormatDate.format(userDisplay.getCreateDate()) %>
	</div>

	<c:if test="<%= statsUser.getLastPostDate() != null %>">
		<div class="thread-user-last-post-date">
			<span><liferay-ui:message key="last-post-date" />:</span> <%= dateFormatDate.format(statsUser.getLastPostDate()) %>
		</div>
	</c:if>
</liferay-ui:user-display>