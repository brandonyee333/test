<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/aui/panel/init.jsp" %>

<%
String bodyContentString = StringPool.BLANK;

Object bodyContent = request.getAttribute("aui:panel:bodyContent");

if (bodyContent != null) {
	bodyContentString = bodyContent.toString();
}
%>

<liferay-ui:panel-container
	extended="<%= !collapsed %>"
	id='<%= id + "Container" %>'
	markupView="lexicon"
	persistState="<%= true %>"
>
	<liferay-ui:panel
		collapsible="<%= collapsible %>"
		extended="<%= !collapsed %>"
		id="<%= id %>"
		markupView="lexicon"
		persistState="<%= true %>"
		title="<%= localizeLabel ? LanguageUtil.get(request, label) : label %>"
	>
		<%= bodyContentString %>
	</liferay-ui:panel>
</liferay-ui:panel-container>