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
	<liferay-util:param name="title" value="${testrayProject.name}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayProject}" model="<%= TestrayProject.class %>" />

<portlet:actionURL var="updateTestrayProjectURL">
	<portlet:param name="controller" value="projects" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<div class="testray-card testray-card-medium">
	<aui:form action="${updateTestrayProjectURL}" method="post">
		<portlet:renderURL var="viewTestrayProjectsURL">
			<portlet:param name="controller" value="projects" />
			<portlet:param name="action" value="index" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${not empty param.redirect ? param.redirect : viewTestrayProjectsURL}" />

		<aui:input name="id" type="hidden" value="${testrayProject.testrayProjectId}" />

		<aui:input autoFocus="${true}" name="name" />

		<aui:input name="description" />

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="${not empty param.redirect ? param.redirect : viewTestrayProjectsURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>