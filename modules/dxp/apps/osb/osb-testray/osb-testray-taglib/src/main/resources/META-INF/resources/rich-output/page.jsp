<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:set value='${requestScope["testray:rich-output:type"]}' var="type" />
<c:set value='${requestScope["testray:rich-output:value"]}' var="value" />

<c:choose>
	<c:when test='${type == "markdown"}'>
		<%= MarkdownUtil.markdownToHtml((String)request.getAttribute("testray:rich-output:value")) %>
	</c:when>
	<c:otherwise>
		<pre><c:out value="${value}" /></pre>
	</c:otherwise>
</c:choose>