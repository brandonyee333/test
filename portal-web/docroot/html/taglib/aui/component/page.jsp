<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/aui/component/init.jsp" %>

<c:if test="<%= useJavaScript %>">
	<aui:script position="<%= scriptPosition %>" use="<%= module %>">
		<c:if test="<%= Validator.isNotNull(var) %>">
			<%= var %> =
		</c:if>

		(new A.<%= name %>(<%= _serialize(jsonifiedOptions, javaScriptAttributes) %>));
	</aui:script>
</c:if>