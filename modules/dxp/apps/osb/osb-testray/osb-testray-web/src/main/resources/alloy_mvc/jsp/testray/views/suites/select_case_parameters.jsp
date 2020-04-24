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
	<liferay-util:param name="title" value="select-case-parameters" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="updateCaseParametersTestraySuiteURL">
	<portlet:param name="controller" value="suites" />
	<portlet:param name="action" value="updateCaseParameters" />
</portlet:actionURL>

<aui:form action="${updateCaseParametersTestraySuiteURL}" method="post" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
	<aui:input name="redirect" type="hidden" value="" />

	<aui:input name="id" type="hidden" value="${id}" />

	<aui:input name="testrayTeamId" type="hidden" />

	<liferay-ui:input-move-boxes
		leftBoxName="availableTestrayTeams"
		leftList="${availableTestrayTeams}"
		leftReorder="<%= Boolean.FALSE.toString() %>"
		leftTitle="available-teams"
		rightBoxName="currentTestrayTeams"
		rightList="${currentTestrayTeams}"
		rightTitle="current-teams"
	/>

	<aui:input name="testrayCaseTypeId" type="hidden" />

	<liferay-ui:input-move-boxes
		leftBoxName="availableTestrayCaseTypes"
		leftList="${availableTestrayCaseTypes}"
		leftReorder="<%= Boolean.FALSE.toString() %>"
		leftTitle="available-case-types"
		rightBoxName="currentTestrayCaseTypes"
		rightList="${currentTestrayCaseTypes}"
		rightTitle="current-case-types"
	/>

	<aui:input name="testrayComponentId" type="hidden" />

	<liferay-ui:input-move-boxes
		leftBoxName="availableTestrayComponents"
		leftList="${availableTestrayComponents}"
		leftReorder="<%= Boolean.FALSE.toString() %>"
		leftTitle="available-main-components"
		rightBoxName="currentTestrayComponents"
		rightList="${currentTestrayComponents}"
		rightTitle="current-main-components"
	/>

	<aui:input name="subcomponentId" type="hidden" />

	<liferay-ui:input-move-boxes
		leftBoxName="availableSubcomponents"
		leftList="${availableSubcomponents}"
		leftReorder="<%= Boolean.FALSE.toString() %>"
		leftTitle="available-subcomponents"
		rightBoxName="currentSubcomponents"
		rightList="${currentSubcomponents}"
		rightTitle="current-subcomponents"
	/>

	<aui:input name="priority" type="hidden" />

	<liferay-ui:input-move-boxes
		leftBoxName="availablePriorities"
		leftList="${availablePriorities}"
		leftReorder="<%= Boolean.FALSE.toString() %>"
		leftTitle="available-priorities"
		rightBoxName="currentPriorities"
		rightList="${currentPriorities}"
		rightTitle="current-priorities"
	/>

	<aui:input name="testrayRequirementId" type="hidden" />

	<liferay-ui:input-move-boxes
		cssClass="option-title-content"
		leftBoxName="availableTestrayRequirements"
		leftList="${availableTestrayRequirements}"
		leftReorder="<%= Boolean.FALSE.toString() %>"
		leftTitle="available-requirements"
		rightBoxName="currentTestrayRequirements"
		rightList="${currentTestrayRequirements}"
		rightTitle="current-requirements"
	/>

	<aui:button-row>
		<aui:button name="save" type="submit" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			var A = AUI();

			document.${htmlNamespace}fm.${htmlNamespace}priority.value = Liferay.Util.listSelect(document.${htmlNamespace}fm.${htmlNamespace}currentPriorities);

			document.${htmlNamespace}fm.${htmlNamespace}subcomponentId.value = Liferay.Util.listSelect(document.${htmlNamespace}fm.${htmlNamespace}currentSubcomponents);

			document.${htmlNamespace}fm.${htmlNamespace}testrayCaseTypeId.value = Liferay.Util.listSelect(document.${htmlNamespace}fm.${htmlNamespace}currentTestrayCaseTypes);

			document.${htmlNamespace}fm.${htmlNamespace}testrayComponentId.value = Liferay.Util.listSelect(document.${htmlNamespace}fm.${htmlNamespace}currentTestrayComponents);

			document.${htmlNamespace}fm.${htmlNamespace}testrayRequirementId.value = Liferay.Util.listSelect(document.${htmlNamespace}fm.${htmlNamespace}currentTestrayRequirements);

			document.${htmlNamespace}fm.${htmlNamespace}testrayTeamId.value = Liferay.Util.listSelect(document.${htmlNamespace}fm.${htmlNamespace}currentTestrayTeams);

			if (${empty param.redirect}) {
				submitForm(document.${htmlNamespace}fm);

				Liferay.Testray.closeWindow();
			}
			else {
				var redirectURL = new A.Url('${param.redirect}');

				redirectURL.setParameter('${htmlNamespace}priority', document.${htmlNamespace}fm.${htmlNamespace}priority.value);
				redirectURL.setParameter('${htmlNamespace}subcomponentId', document.${htmlNamespace}fm.${htmlNamespace}subcomponentId.value);
				redirectURL.setParameter('${htmlNamespace}testrayCaseTypeId', document.${htmlNamespace}fm.${htmlNamespace}testrayCaseTypeId.value);
				redirectURL.setParameter('${htmlNamespace}testrayComponentId', document.${htmlNamespace}fm.${htmlNamespace}testrayComponentId.value);
				redirectURL.setParameter('${htmlNamespace}testrayRequirementId', document.${htmlNamespace}fm.${htmlNamespace}testrayRequirementId.value);
				redirectURL.setParameter('${htmlNamespace}testrayTeamId', document.${htmlNamespace}fm.${htmlNamespace}testrayTeamId.value);

				window.location.href = redirectURL;
			}
		},
		['aui-url', 'liferay-util-list-fields', 'testray-base']
	);

	Liferay.on(
		'portletReady',
		function(event) {
			if (event.portletId == '${TestrayPortletKeys.TESTRAY}') {
				var A = AUI();

				var relevantMoveBoxOptions = A.all('.taglib-move-boxes.option-title-content option');

				relevantMoveBoxOptions.each(
					function(item, index, nodeList) {
						item.set('title', item.html());
					}
				);
			}
		}
	);
</aui:script>