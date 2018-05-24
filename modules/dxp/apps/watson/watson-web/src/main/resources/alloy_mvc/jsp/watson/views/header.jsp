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

<%@ include file="/alloy_mvc/jsp/watson/views/init.jsp" %>

<c:if test="${not empty param.title}">
	<c:if test="${empty param.redirect}">
		<c:if test="<%= !windowState.equals(LiferayWindowState.POP_UP) %>">
			<portlet:renderURL var="backURL">
				<portlet:param name="controller" value="${param.controller}" />
				<portlet:param name="action" value="${param.action}" />
				<portlet:param name="watsonIncidentId" value="${watsonIncidentId}" />
			</portlet:renderURL>
		</c:if>
	</c:if>

	<liferay-ui:header
		backURL="${backURL}"
		title="${param.title}"
	/>
</c:if>