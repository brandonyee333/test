<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath", "/view_template.jsp");

String tabs1 = ParamUtil.getString(request, "tabs1", "templates");

long templateId = ParamUtil.getLong(request, "templateId");

long groupId = ParamUtil.getLong(request, "groupId", themeDisplay.getSiteGroupId());
long classNameId = ParamUtil.getLong(request, "classNameId");
long classPK = ParamUtil.getLong(request, "classPK");
String eventName = ParamUtil.getString(request, "eventName", "selectTemplate");

long resourceClassNameId = ParamUtil.getLong(request, "resourceClassNameId");

if (resourceClassNameId == 0) {
	resourceClassNameId = PortalUtil.getClassNameId(PortletDisplayTemplate.class);
}
%>

<portlet:renderURL var="portletURL">
	<portlet:param name="mvcPath" value="<%= mvcPath %>" />
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="templateId" value="<%= String.valueOf(templateId) %>" />
	<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
	<portlet:param name="classNameId" value="<%= String.valueOf(classNameId) %>" />
	<portlet:param name="classPK" value="<%= String.valueOf(classPK) %>" />
	<portlet:param name="resourceClassNameId" value="<%= String.valueOf(resourceClassNameId) %>" />
	<portlet:param name="eventName" value="<%= eventName %>" />
</portlet:renderURL>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="<%= ddmDisplay.getViewTemplatesTitle(null, locale) %>" selected="<%= true %>" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="<%= portletURL %>" method="post" name="searchForm">
			<liferay-util:include page="/template_search.jsp" servletContext="<%= application %>" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>