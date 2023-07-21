<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
DateSearchEntry dateSearchEntry = new DateSearchEntry();

String displayStyle = workflowInstanceViewDisplayContext.getDisplayStyle();

PortletURL portletURL = workflowInstanceViewDisplayContext.getViewPortletURL();
%>

<aui:form action="<%= portletURL %>" method="post" name="fm">
	<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
		<aui:nav cssClass="navbar-nav">
			<aui:nav-item label="<%= workflowInstanceViewDisplayContext.getHeaderTitle() %>" selected="<%= true %>" />
		</aui:nav>

		<aui:nav-bar-search>
			<aui:form action="<%= portletURL %>" method="post" name="fm1">
				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</aui:form>
		</aui:nav-bar-search>
	</aui:nav-bar>

	<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>" />
</aui:form>

<div class="container-fluid-1280 main-content-body">
	<%@ include file="/workflow_instance.jspf" %>
</div>