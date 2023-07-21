<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:set value='${requestScope["testray:dropdown-item:cssClass"]}' var="cssClass" />
<c:set value='${requestScope["testray:dropdown-item:icon"]}' var="icon" />
<c:set value='${requestScope["testray:dropdown-item:label"]}' var="label" />
<c:set value='${requestScope["testray:dropdown-item:onClick"]}' var="onClick" />
<c:set value='${requestScope["testray:dropdown-item:url"]}' var="url" />

<li class="${cssClass}">
	<c:choose>
		<c:when test="${not empty onClick}">
			<aui:a href="${url}" onClick="event.preventDefault(); ${onClick}">
				<aui:icon image="${icon}" label="${label}" />
			</aui:a>
		</c:when>
		<c:otherwise>
			<aui:a href="${url}">
				<aui:icon image="${icon}" label="${label}" />
			</aui:a>
		</c:otherwise>
	</c:choose>
</li>