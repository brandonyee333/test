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