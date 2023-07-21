<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/cases/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="tabs1" value="requirements" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card">
	<testray:table-toolbar
		title="requirements"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/requirements/index_filter.jspf" %>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayRequirementClass, "select")}'>
			<portlet:renderURL var="selectTestrayRequirementsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="requirements" />
				<portlet:param name="action" value="select" />
				<portlet:param name="testrayCaseId" value="${testrayCase.testrayCaseId}" />
				<portlet:param name="testrayProjectId" value="${testrayCase.testrayProjectId}" />
			</portlet:renderURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("select-requirements")}' var="selectTestrayRequirementsURLTitle" />

			<c:set value="javascript:Liferay.Testray.openWindow('${selectTestrayRequirementsURL}', '${selectTestrayRequirementsURLTitle}', 1000)" var="selectTestrayRequirementsURL" />

			<aui:button icon="icon-list" onClick="${selectTestrayRequirementsURL}" value="link-requirements" />
		</c:if>
	</testray:table-toolbar>

	<%@ include file="/alloy_mvc/jsp/testray/views/requirements/index_table.jspf" %>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>