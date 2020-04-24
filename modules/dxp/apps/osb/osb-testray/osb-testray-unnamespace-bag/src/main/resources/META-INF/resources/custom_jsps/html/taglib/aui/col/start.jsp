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

<%@ include file="/html/taglib/aui/col/init.jsp" %>

<c:choose>
	<c:when test='<%= (portletResponse != null) && Objects.equals(portletResponse.getNamespace(), "_com_liferay_osb_testray_portlet_TestrayPortlet_") %>'>
		<liferay-util:buffer
			var="html"
		>
			<liferay-util:include page="/html/taglib/aui/col/start.portal.jsp" servletContext="<%= application %>" />
		</liferay-util:buffer>

		<liferay-util:buffer
			var="oldHtml"
		>
			id="<portlet:namespace />
		</liferay-util:buffer>

		<liferay-util:buffer
			var="newHtml"
		>
			id="
		</liferay-util:buffer>

		<%= StringUtil.replace(html, oldHtml, newHtml) %>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/taglib/aui/col/start.portal.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>