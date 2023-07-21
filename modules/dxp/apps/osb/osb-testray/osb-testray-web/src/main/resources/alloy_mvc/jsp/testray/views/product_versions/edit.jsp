<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="${testrayProductVersion.name}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayProductVersion}" model="<%= TestrayProductVersion.class %>" />

<portlet:actionURL var="updateTestrayProductVersionURL">
	<portlet:param name="controller" value="product_versions" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<aui:form action="${updateTestrayProductVersionURL}" method="post">
	<portlet:renderURL var="viewTestrayProductVersionsURL">
		<portlet:param name="controller" value="product_versions" />
		<portlet:param name="action" value="index" />
		<portlet:param name="testrayProjectId" value="${testrayProductVersion.testrayProjectId}" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${viewTestrayProductVersionsURL}" />

	<aui:input name="id" type="hidden" value="${testrayProductVersion.testrayProductVersionId}" />

	<aui:input name="testrayProjectId" type="hidden" />

	<aui:input autoFocus="${true}" name="name" />

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="${viewTestrayProductVersionsURL}" value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>