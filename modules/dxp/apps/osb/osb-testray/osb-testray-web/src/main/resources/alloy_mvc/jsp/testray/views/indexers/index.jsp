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

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="manage-indexers" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:button-row>
	<aui:field-wrapper helpMessage="execute-a-rolling-update-of-all-indexed-data.-the-system-will-continue-to-operate-as-usual-while-the-reindex-is-running">
		<portlet:actionURL var="executeRollingReindexURL">
			<portlet:param name="controller" value="indexers" />
			<portlet:param name="action" value="executeRollingReindex" />
			<portlet:param name="redirect" value="${portletURL}" />
		</portlet:actionURL>

		<aui:button href="${executeRollingReindexURL}" icon="icon-refresh" primary="${true}" value="execute-rolling-reindex" />
	</aui:field-wrapper>
</aui:button-row>

<aui:field-wrapper>
	<c:forEach items="${indexers}" var="indexer">
		<aui:button-row>
			<portlet:actionURL var="executeSingleReindexURL">
				<portlet:param name="controller" value="indexers" />
				<portlet:param name="action" value="executeSingleReindex" />
				<portlet:param name="className" value="${indexer.className}" />
				<portlet:param name="redirect" value="${portletURL}" />
			</portlet:actionURL>

			<aui:button href="${executeSingleReindexURL}" icon="icon-refresh" primary="${true}" value="reindex" />

			${indexer.className}
		</aui:button-row>
	</c:forEach>
</aui:field-wrapper>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>