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

<portlet:renderURL var="createTestrayTaskURL">
	<portlet:param name="controller" value="tasks" />
	<portlet:param name="action" value="create" />
</portlet:renderURL>

<aui:form action="${createTestrayTaskURL}" method="post" name="createTaskFm" onSubmit="event.preventDefault(); ${htmlNamespace}createTask(this);">
	<aui:input name="testrayBuildId" onChange="${htmlNamespace}setSubmitDisabled()" type="hidden" />

	<div class="${popup ? "spacing-footer testray-modal-content" : StringPool.BLANK}">
		<div class="breadcrumb-finder-validator-container" id="${htmlNamespace}breadcrumbFinderValidatorContainer">
			<aui:icon cssClass="breadcrumb-finder-validator-item" image="circle-blank" label="project" />

			<aui:icon cssClass="breadcrumb-finder-validator-item" image="circle-blank" label="routine" />

			<aui:icon cssClass="breadcrumb-finder-validator-item" image="circle-blank" label="build" />
		</div>

		<div class="breadcrumb-finder-container breadcrumb-finder-selector">
			<div class="breadcrumb-finder-content" id="${htmlNamespace}breadcrumbBuildFinderContent">
				<span class="selected-container" id="${htmlNamespace}breadcrumbBuildSelectedContainer"></span>

				<aui:input cssClass="breadcrumb-input-edit" label="" name="${htmlNamespace}breadcrumbBuildInputEdit" type="text" wrapperCssClass="breadcrumb-input-edit-wrapper" />
			</div>
		</div>
	</div>

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button disabled="${true}" name="submitButton" type="submit" value="analyze" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
	</aui:button-row>
</aui:form>

<aui:script use="testray-breadcrumb-finder,testray-breadcrumb-finder-validator">
	var testrayBreadcrumbFinder = new Liferay.Testray.BreadcrumbFinder(
		{
			alertContainerId: '#${htmlNamespace}testrayAlertContainer',
			contentNode: '#${htmlNamespace}breadcrumbBuildFinderContent',
			enableAutoFocus: true,
			excludeTaskBuilds: true,
			inputNode: '#${htmlNamespace}breadcrumbBuildInputEdit',
			inputSelectLevel: 3,
			inputValueNode: '#testrayBuildId',
			selectedContainerNode: '#${htmlNamespace}breadcrumbBuildSelectedContainer'
		}
	).render();

	new Liferay.Testray.BreadcrumbFinderValidator(
		{
			container: '#${htmlNamespace}breadcrumbFinderValidatorContainer',
			testrayBreadcrumbFinder: testrayBreadcrumbFinder,
			validateLevel: 3
		}
	).render();
</aui:script>

<aui:script>
	function ${htmlNamespace}createTask(form) {
		var formNode = form.formNode;

		if (formNode) {
			var testrayBuildIdNode = formNode.get('testrayBuildId');

			var testrayBuildId = testrayBuildIdNode.val();

			var opener = Liferay.Util.getOpener();

			if (opener) {
				opener.location.href = Liferay.ThemeDisplay.getLayoutURL() + '/-/testray/tasks/create?testrayBuildId=' + testrayBuildId;
			}
		}
	}

	Liferay.provide(
		window,
		'${htmlNamespace}setSubmitDisabled',
		function() {
			var form = Liferay.Form.get('${htmlNamespace}createTaskFm');

			if (form) {
				var formNode = form.formNode;

				var inputNode = formNode.one('#${htmlNamespace}testrayBuildId');
				var submitButtonNode = formNode.one('#${htmlNamespace}submitButton');

				if (inputNode && submitButtonNode) {
					var inputValue = inputNode.val();

					var disabled = inputValue === '';

					Liferay.Util.toggleDisabled(submitButtonNode, disabled);
				}
			}
		},
		['liferay-form']
	);
</aui:script>