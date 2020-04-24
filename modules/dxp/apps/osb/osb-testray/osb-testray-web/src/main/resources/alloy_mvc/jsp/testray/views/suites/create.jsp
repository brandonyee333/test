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
	<liferay-util:param name="title" value="new-suite" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testraySuite}" model="<%= TestraySuite.class %>" />

<portlet:actionURL var="addTestraySuiteURL">
	<portlet:param name="controller" value="suites" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<div class="testray-card">
	<aui:form action="${addTestraySuiteURL}" method="post" name="createSuiteForm">
		<portlet:renderURL var="viewTestraySuitesURL">
			<portlet:param name="controller" value="suites" />
			<portlet:param name="action" value="index" />
			<portlet:param name="testrayProjectId" value="${testrayProjectId}" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${viewTestraySuitesURL}" />

		<aui:input name="addTestrayCaseIds" type="hidden" />
		<aui:input name="caseParametersString" type="hidden" />
		<aui:input name="testrayProjectId" type="hidden" />

		<aui:input autoFocus="${true}" name="name" />

		<aui:input name="description" />

		<aui:input label="smart-suite" name="smartSuite" onChange="Liferay.Util.toggleDisabled(${htmlNamespace}selectCases, this.checked); Liferay.Util.toggleDisabled(${htmlNamespace}selectCaseParameters, !this.checked);" type="checkbox" />

		<aui:button-row>
			<portlet:renderURL var="selectTestrayCasesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="cases" />
				<portlet:param name="action" value="select" />
				<portlet:param name="scopeByProject" value="${true}" />
				<portlet:param name="testrayProjectId" value="${testrayProjectId}" />
				<portlet:param name="testraySuiteId" value="0" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "select", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("cases")}' var="selectTestrayCasesURLTitle" />

			<c:set value="javascript:Liferay.Testray.openWindow('${selectTestrayCasesURL}', '${selectTestrayCasesURLTitle}', 1000)" var="selectTestrayCasesURL" />

			<aui:button name="selectCases" onClick="${selectTestrayCasesURL}" value="select-cases" />

			<aui:button disabled="${true}" name="selectCaseParameters" onClick="${htmlNamespace}openSelectCaseParameters();" value="select-case-parameters" />
		</aui:button-row>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-linked-cases"
			headerNames="priority,name,description,remove"
			orderByCol="${testrayCasesOrderByCol}"
			orderByColParam="testrayCasesOrderByCol"
			orderByType="${testrayCasesOrderByType}"
			orderByTypeParam="testrayCasesOrderByType"
			total="${fn:length(testrayCases)}"
		>
			<liferay-ui:search-container-results
				results="${testrayCases}"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.testray.model.TestrayCase"
				escapedModel="${true}"
				keyProperty="testrayCaseId"
				modelVar="testrayCase"
			>
				<liferay-ui:search-container-column-text
					property="priority"
				/>

				<c:set value="<%= TestrayComponentLocalServiceUtil.getTestrayComponent(testrayCase.getTestrayComponentId()) %>" var="testrayComponent" />

				<liferay-ui:search-container-column-text
					name="component"
					value="${testrayComponent.name}"
				/>

				<liferay-ui:search-container-column-text
					name="case"
					orderable="${true}"
					orderableProperty="name_sortable"
					property="name"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				paginate="${false}"
			/>
		</liferay-ui:search-container>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="${viewTestraySuitesURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	function ${htmlNamespace}removeEmptyResultsMessage(searchContainer) {
		var removeNode = function(node) {
			var nodeText = node.text().trim();

			var langKey = '<liferay-ui:message key="there-are-no-linked-cases" />';

			if (nodeText === langKey) {
				node.remove();
			}
		};

		var searchContainerParentContainer = searchContainer._parentContainer;

		if (searchContainerParentContainer) {
			var previousNode = searchContainerParentContainer.previous();

			if (previousNode) {
				removeNode(previousNode);
			}
		}

		var searchContainerTable = searchContainer._table;

		if (searchContainerTable) {
			var tableData = searchContainerTable.one('.table-data');

			if (tableData) {
				var firstRow = tableData.get('firstElementChild');

				if (firstRow) {
					removeNode(firstRow);
				}
			}
		}
	}

	Liferay.provide(
		window,
		'${htmlNamespace}buildSearchContainer',
		function(testrayCases) {
			var searchContainer = Liferay.SearchContainer.get('${htmlNamespace}testrayCasesSearchContainer');

			${htmlNamespace}removeEmptyResultsMessage(searchContainer);

			var searchContainerData = searchContainer.getData();

			testrayCases.forEach(
				function(testrayCase) {
					if (searchContainerData.indexOf(testrayCase.testrayCaseId.toString()) < 0) {
						var createSuiteForm = Liferay.Form.get('${htmlNamespace}createSuiteForm');

						var smartSuiteInput = createSuiteForm.form.smartSuite;

						if (smartSuiteInput && smartSuiteInput.checked) {
							searchContainer.addRow([testrayCase.priority, testrayCase.name, testrayCase.description], testrayCase.testrayCaseId);
						}
						else {
							var removeLink = '<span><a href="javascript:;" id=' + testrayCase.testrayCaseId + ' onclick="${htmlNamespace}removeTestrayCase(this);"><img src="${themeDisplay.pathThemeImages}/common/delete.png"><span><liferay-ui:message key="remove" /></span></a></span>';

							searchContainer.addRow([testrayCase.priority, testrayCase.name, testrayCase.description, removeLink], testrayCase.testrayCaseId);
						}

						searchContainer.syncUI();
					}
				}
			);

			Liferay.Testray.setFormInput(
				{
					formName: 'createSuiteForm',
					inputId: '${htmlNamespace}addTestrayCaseIds',
					val: searchContainer.getData()
				}
			);
		},
		['liferay-form', 'liferay-search-container', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}openSelectCaseParameters',
		function() {
			var A = AUI();

			<portlet:renderURL var="viewTestrayCasesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="cases" />
				<portlet:param name="action" value="index" />
				<portlet:param name="format" value="js" />
				<portlet:param name="testrayProjectId" value="${testrayProjectId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "index", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<portlet:renderURL var="selectTestrayCaseParametersURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="suites" />
				<portlet:param name="action" value="selectCaseParameters" />
				<portlet:param name="scopeByProject" value="${true}" />
				<portlet:param name="testrayProjectId" value="${testrayProjectId}" />
				<portlet:param name="id" value="0" />
				<portlet:param name="redirect" value="${viewTestrayCasesURL}" />
			</portlet:renderURL>

			var selectTestrayCaseParametersURL = new A.Url('${selectTestrayCaseParametersURL}');

			var createSuiteForm = Liferay.Form.get('${htmlNamespace}createSuiteForm');

			if (createSuiteForm) {
				var caseParametersStringElement = createSuiteForm.form.caseParametersString;

				var caseParametersStringValue = '';

				if (caseParametersStringElement) {
					caseParametersStringValue = caseParametersStringElement.value;
				}
			}

			selectTestrayCaseParametersURL.addParameter('caseParametersString', caseParametersStringValue);

			Liferay.Testray.openWindow(selectTestrayCaseParametersURL, '<liferay-ui:message key="case-parameters" />', 1000);
		},
		['aui-url', 'liferay-form', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}removeTestrayCase',
		function(element) {
			var searchContainer = Liferay.SearchContainer.get('${htmlNamespace}testrayCasesSearchContainer');

			var elementId = element.id;

			var testrayCaseId = elementId.replace('${htmlNamespace}', '');

			searchContainer.deleteRow(element, testrayCaseId);

			Liferay.Testray.setFormInput(
				{
					formName: 'createSuiteForm',
					inputId: '${htmlNamespace}addTestrayCaseIds',
					val: searchContainer.getData()
				}
			);
		},
		['liferay-search-container', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}setCaseParametersString',
		function(caseParametersString) {
			Liferay.Testray.clearSearchContainer('${htmlNamespace}testrayCasesSearchContainer');

			Liferay.Testray.setFormInput(
				{
					formName: 'createSuiteForm',
					inputId: '${htmlNamespace}caseParametersString',
					val: caseParametersString
				}
			);
		},
		['liferay-search-container', 'testray-base']
	);
</aui:script>