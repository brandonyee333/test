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

<%@ include file="/marketplace/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
%>

<portlet:actionURL name="addCorpProject" var="addCorpProjectURL" />

<aui:form action="<%= addCorpProjectURL %>" method="post" name="dialogFm">
	<aui:input name="<%= mvcPathParam %>" type="hidden" value="/marketplace_admin/edit_corp_project.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:model-context model="<%= CorpProject.class %>" />

	<aui:fieldset>
		<aui:input name="name" required="<%= true %>" />
	</aui:fieldset>

	<aui:button type="submit" value="add" />
</aui:form>