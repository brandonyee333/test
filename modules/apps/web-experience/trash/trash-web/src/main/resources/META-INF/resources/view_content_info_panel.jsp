<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long classPK = trashDisplayContext.getClassPK();

TrashRenderer trashRenderer = trashDisplayContext.getTrashRenderer();

TrashHandler trashHandler = trashDisplayContext.getTrashHandler();
%>

<c:if test="<%= trashRenderer != null %>">
	<div class="sidebar-header">
		<ul class="sidebar-header-actions">
			<li>
				<liferay-util:include page="/container_action.jsp" servletContext="<%= application %>" />
			</li>
		</ul>

		<h4><%= HtmlUtil.escape(trashRenderer.getTitle(locale)) %></h4>
	</div>

	<aui:nav-bar cssClass="navbar-no-collapse" markupView="lexicon">
		<aui:nav collapsible="<%= false %>" cssClass="navbar-nav">
			<aui:nav-item label="details" selected="<%= true %>" />
		</aui:nav>
	</aui:nav-bar>

	<div class="sidebar-body">
		<h5><liferay-ui:message key="num-of-items" /></h5>

		<p>
			<%= trashHandler.getTrashModelsCount(classPK) %>
		</p>
	</div>
</c:if>