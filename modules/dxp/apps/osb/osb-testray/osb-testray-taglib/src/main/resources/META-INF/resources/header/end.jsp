<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/header/init.jsp" %>

<c:choose>
	<c:when test="${not popup}">
		</div>
	</c:when>
	<c:otherwise>
		<aui:script use="testray-base">
			Liferay.Testray.setWindowTitle('<liferay-ui:message key="${title}" />');
		</aui:script>
	</c:otherwise>
</c:choose>