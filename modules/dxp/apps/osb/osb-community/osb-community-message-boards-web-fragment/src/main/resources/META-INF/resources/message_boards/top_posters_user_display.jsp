<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/message_boards/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MBStatsUser statsUser = (MBStatsUser)row.getObject();

User topUser = UserLocalServiceUtil.fetchUserById(statsUser.getUserId());

String[] ranks = MBUtil.getUserRank(mbGroupServiceSettings, themeDisplay.getLanguageId(), statsUser);
%>

<c:if test="<%= topUser != null %>">
	<liferay-ui:user-portrait
		userId="<%= topUser.getUserId() %>"
	/>

	<h4 title="<%= HtmlUtil.escapeAttribute(topUser.getFullName()) %>">
		<%= HtmlUtil.escape(topUser.getFullName()) %>
	</h4>

	<c:if test="<%= Validator.isNotNull(ranks[0]) %>">
		<div class="thread-user-rank">
			<span><liferay-ui:message key="rank" />:</span> <%= HtmlUtil.escape(ranks[0]) %>
		</div>
	</c:if>

	<div class="thread-user-post-count">
		<span><liferay-ui:message key="posts" />:</span> <%= statsUser.getMessageCount() %>
	</div>

	<div class="thread-user-join-date">
		<span><liferay-ui:message key="join-date" />:</span> <%= dateFormatDate.format(topUser.getCreateDate()) %>
	</div>

	<c:if test="<%= statsUser.getLastPostDate() != null %>">
		<div class="thread-user-last-post-date">
			<span><liferay-ui:message key="last-post-date" />:</span> <%= dateFormatDate.format(statsUser.getLastPostDate()) %>
		</div>
	</c:if>
</c:if>