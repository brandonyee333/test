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

<%@ include file="/marketplace_developer_apps/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "app-preview");

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace_developer_apps/view_app_entry.jsp");
portletURL.setParameter("appEntryId", String.valueOf(appEntryId));
%>

<nav class="tab-nav">
	<ul>
		<li<%= tabs1.equals("app-preview") ? " class=\"selected\"" : StringPool.BLANK %>>

			<%
			portletURL.setParameter("tabs1", "app-preview");
			%>

			<a href="<%= portletURL.toString() %>"><liferay-ui:message key="app-preview" /></a>
		</li>

		<c:if test="<%= appEntry.getLicenseType() > 0 %>">
			<li<%= tabs1.equals("app-pricing") ? " class=\"selected\"" : StringPool.BLANK %>>

				<%
				portletURL.setParameter("tabs1", "app-pricing");
				%>

				<a href="<%= portletURL.toString() %>"><liferay-ui:message key="app-pricing" /></a>
			</li>
		</c:if>

		<li<%= tabs1.equals("developer") ? " class=\"selected\"" : StringPool.BLANK %>>

			<%
			portletURL.setParameter("tabs1", "developer");
			%>

			<a href="<%= portletURL.toString() %>"><liferay-ui:message key="developer" /></a>
		</li>
	</ul>
</nav>