<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<portlet:renderURL var="mainURL" />

	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="<%= mainURL.toString() %>" label="entries" selected="<%= true %>" />
	</aui:nav>

	<aui:nav-bar-search>
		<liferay-portlet:renderURL varImpl="searchURL" />

		<aui:form action="<%= searchURL %>" method="get" name="searchFm">
			<liferay-portlet:renderURLParams varImpl="searchURL" />
			<aui:input name="redirect" type="hidden" value='<%= ParamUtil.getString(request, "redirect") %>' />
			<aui:input name="deleteTrashEntryIds" type="hidden" />
			<aui:input name="restoreTrashEntryIds" type="hidden" />

			<liferay-ui:input-search
				autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>"
				markupView="lexicon"
				placeholder='<%= LanguageUtil.get(request, "search") %>'
			/>
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>