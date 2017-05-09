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

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle=""
	title=""
/>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="Add" selected="<%= true %>" />
	</aui:nav>
</aui:nav-bar>

<portlet:actionURL name="/admin/add_application" var="addApplicationURL">
	<portlet:param name="mvcActionCommand" value="/admin/add_application" />
</portlet:actionURL>

<aui:form action="<%= addApplicationURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />

	<aui:fieldset>
		<aui:input label="application-name" name="name" />
		<aui:input name="description" />

		<aui:button-row>
			<aui:button type="submit" />
			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>