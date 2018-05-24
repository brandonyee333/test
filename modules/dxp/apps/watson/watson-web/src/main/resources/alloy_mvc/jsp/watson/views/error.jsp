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

<c:set value='<%= (BrowserSnifferUtil.isChrome(request) && windowState.equals(LiferayWindowState.POP_UP)) ? request.getHeader(HttpHeaders.REFERER) : "javascript:history.go(-1);" %>' var="backURL" />

<liferay-ui:header
	backURL="${backURL}"
	title="error"
/>

<c:choose>
	<c:when test="${fn:length(pattern) > 0}">
		<liferay-ui:message arguments="${arguments}" key="${pattern}" />
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="an-unexpected-error-occurred" />
	</c:otherwise>
</c:choose>