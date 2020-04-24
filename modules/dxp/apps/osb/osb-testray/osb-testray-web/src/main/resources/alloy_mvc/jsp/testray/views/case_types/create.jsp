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