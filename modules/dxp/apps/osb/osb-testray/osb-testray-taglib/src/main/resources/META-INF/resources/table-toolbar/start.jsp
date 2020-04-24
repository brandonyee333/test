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

<%@ include file="/table-toolbar/init.jsp" %>

<div class="table-toolbar">
	<c:if test="${not empty title}">
		<h4 class="table-title">
			<liferay-ui:message key="${title}" />
		</h4>
	</c:if>

	<c:if test="${not empty count}">
		<h5 class="table-count">
			${count}
		</h5>
	</c:if>

	<div class="actions-container">