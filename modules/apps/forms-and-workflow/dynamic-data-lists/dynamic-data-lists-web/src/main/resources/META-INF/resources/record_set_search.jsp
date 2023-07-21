<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
RecordSetDisplayTerms displayTerms = new RecordSetDisplayTerms(renderRequest);
%>

<liferay-ui:search-toggle
	autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>"
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_dynamic_data_lists_record_set_search"
	markupView="lexicon"
>
	<aui:fieldset>
		<aui:input inlineField="<%= true %>" name="<%= RecordSetDisplayTerms.NAME %>" size="20" type="text" value="<%= displayTerms.getName() %>" />

		<aui:input inlineField="<%= true %>" name="<%= RecordSetDisplayTerms.DESCRIPTION %>" size="20" type="text" value="<%= displayTerms.getDescription() %>" />
	</aui:fieldset>
</liferay-ui:search-toggle>