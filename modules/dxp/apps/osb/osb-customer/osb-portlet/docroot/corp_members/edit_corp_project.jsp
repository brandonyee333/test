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
String backURL = ParamUtil.getString(request, "backURL", redirect);

long corpProjectId = ParamUtil.getLong(request, "corpProjectId");

CorpProject corpProject = CorpProjectLocalServiceUtil.getCorpProject(corpProjectId);
%>

<div class="corp-members edit-corp-project">
	<portlet:actionURL name="updateCorpProject" var="editCorpProjectURL" />

	<aui:form action="<%= editCorpProjectURL %>">
		<aui:input name="mvcPath" type="hidden" value="/corp_members/edit_corp_project.jsp" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="corpProjectId" type="hidden" value="<%= String.valueOf(corpProjectId) %>" />

		<aui:model-context bean="<%= corpProject %>" model="<%= CorpProject.class %>" />

		<aui:fieldset>
			<aui:input name="name" required="<%= true %>" />
		</aui:fieldset>

		<aui:button-row>
			<aui:button type="submit" value="save" />
		</aui:button-row>
	</aui:form>
</div>