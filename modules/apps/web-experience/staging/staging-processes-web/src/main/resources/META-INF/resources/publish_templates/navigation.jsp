<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="publish-templates" selected="<%= true %>" />
	</aui:nav>

	<aui:nav-bar-search>
		<liferay-portlet:renderURL varImpl="searchURL">
			<portlet:param name="mvcRenderCommandName" value="viewPublishConfigurations" />
		</liferay-portlet:renderURL>

		<aui:form action="<%= searchURL %>" name="searchFm">
			<liferay-portlet:renderURLParams varImpl="searchURL" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

			<liferay-ui:input-search
				markupView="lexicon"
			/>
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>