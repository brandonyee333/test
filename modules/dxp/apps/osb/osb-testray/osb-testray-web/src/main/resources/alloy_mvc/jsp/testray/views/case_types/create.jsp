<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="new-case-type" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayCaseType}" model="<%= TestrayCaseType.class %>" />

<portlet:actionURL var="addTestrayCaseTypeURL">
	<portlet:param name="controller" value="case_types" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<aui:form action="${addTestrayCaseTypeURL}" method="post">
	<portlet:renderURL var="viewTestrayCaseTypesURL">
		<portlet:param name="controller" value="case_types" />
		<portlet:param name="action" value="index" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${viewTestrayCaseTypesURL}" />

	<aui:input autoFocus="${true}" name="name" />

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="${viewTestrayCaseTypesURL}" value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>