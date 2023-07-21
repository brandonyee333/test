<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>