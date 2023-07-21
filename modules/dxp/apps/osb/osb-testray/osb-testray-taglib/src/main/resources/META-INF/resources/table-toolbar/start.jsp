<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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