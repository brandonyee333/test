<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:set value='${requestScope["testray:header:breadcrumbComposites"]}' var="breadcrumbComposites" />
<c:set value='${requestScope["testray:header:title"]}' var="title" />

<%
String pageTitle = (String)pageContext.getAttribute("title");

if (Validator.isNotNull(pageTitle)) {
	PortalUtil.setPageTitle(pageTitle, request);
}
%>