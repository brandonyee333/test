<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
UADExportProcessDisplayContext uadExportProcessDisplayContext = new UADExportProcessDisplayContext(request, renderResponse);

portletDisplay.setShowBackIcon(true);

LiferayPortletURL usersAdminURL = liferayPortletResponse.createLiferayPortletURL(UsersAdminPortletKeys.USERS_ADMIN, PortletRequest.RENDER_PHASE);

portletDisplay.setURLBack(usersAdminURL.toString());

renderResponse.setTitle(StringBundler.concat(selectedUser.getFullName(), " - ", LanguageUtil.get(request, "export-personal-data")));
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="export-processes" selected="<%= true %>" />
	</aui:nav>
</aui:nav-bar>

<liferay-frontend:add-menu>
	<portlet:renderURL var="addExportProcessesURL">
		<portlet:param name="mvcRenderCommandName" value="/add_uad_export_processes" />
		<portlet:param name="backURL" value="<%= currentURL %>" />
		<portlet:param name="p_u_i_d" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
	</portlet:renderURL>

	<liferay-frontend:add-menu-item
		title='<%= LanguageUtil.get(request, "add-export-processes") %>'
		url="<%= addExportProcessesURL.toString() %>"
	/>
</liferay-frontend:add-menu>

<liferay-frontend:management-bar>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all", "in-progress", "successful", "failed"} %>'
			portletURL="<%= PortletURLUtil.clone(uadExportProcessDisplayContext.getPortletURL(), renderResponse) %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= uadExportProcessDisplayContext.getOrderByCol() %>"
			orderByType="<%= uadExportProcessDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"create-date", "name"} %>'
			portletURL="<%= PortletURLUtil.clone(uadExportProcessDisplayContext.getPortletURL(), renderResponse) %>"
		/>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<aui:form cssClass="container-fluid-1280">
	<div id="<portlet:namespace />exportProcesses">

		<%
		request.setAttribute("UADExportProcessDisplayContext", uadExportProcessDisplayContext);
		%>

		<liferay-util:include page="/export_processes.jsp" servletContext="<%= application %>" />
	</div>
</aui:form>

<aui:script use="liferay-uad-export">
	<portlet:resourceURL id="/get_export_processes" var="exportProcessesURL">
		<portlet:param name="p_u_i_d" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
		<portlet:param name="<%= SearchContainer.DEFAULT_CUR_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_CUR_PARAM) %>" />
		<portlet:param name="<%= SearchContainer.DEFAULT_DELTA_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_DELTA_PARAM) %>" />
	</portlet:resourceURL>

	new Liferay.UADExport(
		{
			exportProcessesNode: '#exportProcesses',
			exportProcessesResourceURL: '<%= exportProcessesURL.toString() %>',
			namespace: '<portlet:namespace />'
		}
	);
</aui:script>