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

<c:set value='${requestScope["testray:date:id"]}' var="id" />
<c:set value='${requestScope["testray:date:hideTime"]}' var="hideTime" />
<c:set value='${requestScope["testray:date:relative"]}' var="relative" />
<c:set value='${requestScope["testray:date:value"]}' var="value" />

<fmt:formatDate
	pattern="${hideTime ? 'MMM d, yyyy' : 'MMM d, yyyy h:mm a'}"
	timeZone="${timeZone}"
	value="${value}"
	var="formattedDate"
/>

<c:set value="${relative && not empty value}" var="displayRelative" />

<span class="testray-date ${displayRelative ? 'testray-tooltip-trigger' : StringPool.BLANK}" id="${id}" title="${formattedDate}">
	${formattedDate}
</span>

<c:if test="${displayRelative}">
	<aui:script use="aui-base,moment">
		if (typeof moment !== 'undefined') {
			var dateNode = A.one('#${id}');

			if (dateNode) {
				var relativeTime = moment('<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" timeZone="${timeZone}" value="${value}" />').fromNow();

				dateNode.text(relativeTime);
			}
		}
	</aui:script>
</c:if>