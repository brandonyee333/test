<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
boolean includeSelectAll = GetterUtil.getBoolean(request.getAttribute("liferay-ui:app_view_toolbar:includeSelectAll"));
String searchJsp = (String)request.getAttribute("liferay-ui:app_view_toolbar:searchJsp");

String cssClass = "select-all-entries";

if (!includeSelectAll) {
	cssClass += " hide";
}
%>

<div class="app-view-taglib">
	<div class="lfr-header-row-content">
		<c:if test="<%= Validator.isNotNull(searchJsp) %>">
			<liferay-util:include page="<%= searchJsp %>" />
		</c:if>

		<div>
			<c:if test="<%= !user.isDefaultUser() %>">
				<aui:input cssClass="<%= cssClass %>" inline="<%= true %>" label="" name="<%= RowChecker.ALL_ROW_IDS %>" title="select-all" type="checkbox" />
			</c:if>