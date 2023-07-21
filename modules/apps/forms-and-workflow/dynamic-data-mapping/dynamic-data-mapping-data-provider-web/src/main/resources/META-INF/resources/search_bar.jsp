<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
DDMDataProviderDisplayTerms displayTerms = new DDMDataProviderDisplayTerms(renderRequest);
%>

<aui:nav-bar cssClass="collapse-basic-search" id="toolbar" markupView="lexicon">
	<aui:nav-bar-search>
		<aui:form action="<%= ddmDataProviderDisplayContext.getPortletURL() %>" method="post" name="fm1">
			<liferay-ui:search-toggle
				autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>"
				buttonLabel="search"
				displayTerms="<%= displayTerms %>"
				id="toggle_id_dynamic_data_mapping_data_provider_search"
				markupView="lexicon"
			>
				<aui:fieldset>
					<aui:input inlineField="<%= true %>" name="<%= DDMDataProviderDisplayTerms.NAME %>" size="20" type="text" value="<%= displayTerms.getName() %>" />

					<aui:input inlineField="<%= true %>" name="<%= DDMDataProviderDisplayTerms.DESCRIPTION %>" size="20" type="text" value="<%= displayTerms.getDescription() %>" />
				</aui:fieldset>
			</liferay-ui:search-toggle>
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>