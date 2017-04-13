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

<%@ include file="/search/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "search-settings");
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
</liferay-portlet:renderURL>

<liferay-ui:tabs
	names='<%= "search-settings" %>'
	param="tabs2"
	url="<%= configurationRenderURL %>"
/>

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />

	<aui:fieldset>
		<c:choose>
			<c:when test='<%= tabs2.equals("search-settings") %>'>
				<aui:select ignoreRequestValue="<%= true %>" label="structures" multiple="<%= true %>" name="searchStructureIds">

					<%
					List<DDMStructure> ddmStructures = DDMStructureServiceUtil.getStructures(company.getCompanyId(), new long[] {themeDisplay.getScopeGroupId()}, PortalUtil.getClassNameId(JournalArticle.class), WorkflowConstants.STATUS_ANY);

					for (DDMStructure ddmStructure : ddmStructures) {
					%>

						<aui:option label="<%= ddmStructure.getName() %>" selected="<%= ArrayUtil.contains(searchStructureIds, String.valueOf(ddmStructure.getStructureKey())) %>" value="<%= String.valueOf(ddmStructure.getStructureKey()) %>" />

					<%
					}
					%>

				</aui:select>
			</c:when>
		</c:choose>
	</aui:fieldset>

	<aui:button-row cssClass="kb-submit-buttons">
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>