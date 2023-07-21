<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CalendarResourceDisplayTerms displayTerms = new CalendarResourceDisplayTerms(renderRequest);
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_calendar_resource_search"
	markupView="lexicon"
>
	<aui:fieldset>
		<aui:input name="<%= CalendarResourceDisplayTerms.CODE %>" value="<%= displayTerms.getCode() %>" />

		<aui:input name="<%= CalendarResourceDisplayTerms.NAME %>" value="<%= displayTerms.getName() %>" />

		<aui:input name="<%= CalendarResourceDisplayTerms.DESCRIPTION %>" value="<%= displayTerms.getDescription() %>" />

		<aui:select name="<%= CalendarResourceDisplayTerms.ACTIVE %>">
			<aui:option label="yes" selected="<%= displayTerms.isActive() %>" value="<%= true %>" />
			<aui:option label="no" selected="<%= !displayTerms.isActive() %>" value="<%= false %>" />
		</aui:select>

		<aui:select name="<%= CalendarResourceDisplayTerms.SCOPE %>">
			<aui:option label="current" selected="<%= displayTerms.getScope() == themeDisplay.getScopeGroupId() %>" value="<%= themeDisplay.getScopeGroupId() %>" />
			<aui:option label="global" selected="<%= displayTerms.getScope() == themeDisplay.getCompanyGroupId() %>" value="<%= themeDisplay.getCompanyGroupId() %>" />
		</aui:select>
	</aui:fieldset>
</liferay-ui:search-toggle>