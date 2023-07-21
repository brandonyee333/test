<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="new-team" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayTeam}" model="<%= TestrayTeam.class %>" />

<portlet:actionURL var="addTestrayTeamURL">
	<portlet:param name="controller" value="teams" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<aui:form action="${addTestrayTeamURL}" method="post" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
	<portlet:renderURL var="viewTestrayTeamsURL">
		<portlet:param name="controller" value="teams" />
		<portlet:param name="action" value="index" />
		<portlet:param name="testrayProjectId" value="${testrayTeam.testrayProjectId}" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${viewTestrayTeamsURL}" />

	<aui:input name="testrayProjectId" type="hidden" />

	<aui:input autoFocus="${true}" name="name" />

	<aui:input name="testrayComponentIds" type="hidden" />

	<liferay-ui:input-move-boxes
		leftBoxName="unassignedTestrayComponentFields"
		leftList="${unassignedTestrayComponents}"
		leftReorder="<%= Boolean.FALSE.toString() %>"
		leftTitle="unassigned"
		rightBoxName="currentTestrayComponentFields"
		rightList="${currentTestrayComponents}"
		rightTitle="current"
	/>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="${viewTestrayTeamsURL}" value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			document.${htmlNamespace}fm.${htmlNamespace}testrayComponentIds.value = Liferay.Util.listSelect(document.${htmlNamespace}fm.${htmlNamespace}currentTestrayComponentFields);

			submitForm(document.${htmlNamespace}fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>