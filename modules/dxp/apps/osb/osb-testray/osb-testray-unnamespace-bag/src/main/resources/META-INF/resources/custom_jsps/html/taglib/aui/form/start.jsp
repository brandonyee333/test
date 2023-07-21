<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/aui/form/init.jsp" %>

<c:choose>
	<c:when test='<%= (portletResponse != null) && Objects.equals(portletResponse.getNamespace(), "_com_liferay_osb_testray_portlet_TestrayPortlet_") %>'>
		<liferay-util:buffer
			var="html"
		>
			<liferay-util:include page="/html/taglib/aui/form/start.portal.jsp" servletContext="<%= application %>" />
		</liferay-util:buffer>

		<liferay-util:buffer
			var="oldHTML"
		>
			data-fm-namespace="<%= namespace %>"
		</liferay-util:buffer>

		<liferay-util:buffer
			var="newHTML"
		>
			data-fm-namespace="<%= namespace.concat(HtmlUtil.escapeAttribute(name)) %>"
		</liferay-util:buffer>

		<%= StringUtil.replace(html, oldHTML, newHTML) %>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/taglib/aui/form/start.portal.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>