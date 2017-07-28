<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/portlet/workflow_tasks/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "pending");
%>

<c:choose>
	<c:when test='<%= tabs1.equals("other-assignees") %>'>
		<liferay-util:include page="/html/portlet/workflow_tasks/other_assignees.jsp" />
	</c:when>
	<c:otherwise>
		<liferay-util:buffer var="html">
			<liferay-util:include page="/html/portlet/workflow_tasks/view.portal.jsp" />
		</liferay-util:buffer>

		<%
		int x = html.indexOf("<ul class=\"aui-tabview-list\"");
		int y = html.indexOf("</ul>", x);
		%>

		<%= html.substring(0, y) %>

		<li class="aui-tab aui-state-default last">
			<span class="aui-tab-content">

				<%
				PortletURL portletURL = renderResponse.createRenderURL();

				portletURL.setParameter("tabs1", "other-assignees");
				%>

				<a class="aui-tab-label" href="<%= portletURL.toString() %>"><liferay-ui:message key="other-assignees" /></a>
			</span>
		</li>

		<%= html.substring(y) %>
	</c:otherwise>
</c:choose>

<aui:script>
	var closeButtonElements = AUI().all('a[id*="closetaskChangeStatusLink"]');

	if (closeButtonElements) {
		closeButtonElements.each(
			function(closeButtonElement) {
				var parentElement = closeButtonElement.ancestor('li');

				if (parentElement) {
					parentElement.hide();
				}
			}
		);
	}
</aui:script>