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