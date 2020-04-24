<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="${testrayTeam.name}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayTeam}" model="<%= TestrayTeam.class %>" />

<portlet:actionURL var="updateTestrayTeamURL">
	<portlet:param name="controller" value="teams" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<aui:form action="${updateTestrayTeamURL}" method="post" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
	<portlet:renderURL var="viewTestrayTeamsURL">
		<portlet:param name="controller" value="teams" />
		<portlet:param name="action" value="index" />
		<portlet:param name="testrayProjectId" value="${testrayTeam.testrayProjectId}" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${viewTestrayTeamsURL}" />

	<aui:input name="id" type="hidden" value="${testrayTeam.testrayTeamId}" />

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