<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="new-project" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayProject}" model="<%= TestrayProject.class %>" />

<portlet:actionURL var="addTestrayProjectURL">
	<portlet:param name="controller" value="projects" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<div class="testray-card testray-card-medium">
	<aui:form action="${addTestrayProjectURL}" method="post">
		<portlet:renderURL var="viewTestrayProjectsURL">
			<portlet:param name="controller" value="projects" />
			<portlet:param name="action" value="index" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${viewTestrayProjectsURL}" />

		<aui:input autoFocus="${true}" name="name" required="${true}" />

		<aui:input name="description" />

		<aui:button-row>
			<aui:button type="submit" value="add" />

			<aui:button href="${not empty param.redirect ? param.redirect : viewTestrayProjectsURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>