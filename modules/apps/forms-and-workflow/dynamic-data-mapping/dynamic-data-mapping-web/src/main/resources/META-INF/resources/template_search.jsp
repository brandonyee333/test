<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
TemplateDisplayTerms displayTerms = new TemplateDisplayTerms(renderRequest);
%>

<liferay-ui:search-toggle
	autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>"
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_ddm_template_search"
	markupView="lexicon"
>
	<aui:fieldset cssClass="lfr-ddm-search-form">
		<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" inlineField="<%= true %>" name="<%= TemplateDisplayTerms.NAME %>" size="20" value="<%= displayTerms.getName() %>" />

		<aui:input inlineField="<%= true %>" name="<%= TemplateDisplayTerms.DESCRIPTION %>" size="20" value="<%= displayTerms.getDescription() %>" />
	</aui:fieldset>
</liferay-ui:search-toggle>