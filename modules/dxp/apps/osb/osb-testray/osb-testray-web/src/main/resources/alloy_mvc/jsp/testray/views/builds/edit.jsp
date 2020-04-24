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
	<liferay-util:param name="title" value="${testrayBuild.name}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayBuild}" model="<%= TestrayBuild.class %>" />

<portlet:actionURL var="updateTestrayBuildURL">
	<portlet:param name="controller" value="builds" />
	<portlet:param name="action" value='${not testrayBuild.template ? "update" : "updateTemplate"}' />
</portlet:actionURL>

<div class="testray-card">
	<aui:form action="${updateTestrayBuildURL}" method="post" name="editBuildForm" onSubmit="event.preventDefault(); ${htmlNamespace}submitBuild();">
		<portlet:renderURL var="viewTestrayBuildsURL">
			<portlet:param name="controller" value="builds" />
			<portlet:param name="action" value="index" />
			<portlet:param name="testrayRoutineId" value="${testrayBuild.testrayRoutineId}" />

			<testray:filter-preference
				value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "builds", "index", testrayProjectId)}'
			/>
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${not empty param.redirect ? param.redirect : viewTestrayBuildsURL}" />

		<aui:input name="id" type="hidden" value="${testrayBuild.testrayBuildId}" />

		<aui:input name="addTestrayCaseIds" type="hidden" value="${testrayCaseIds}" />
		<aui:input name="templateTestrayBuildId" type="hidden" />
		<aui:input name="testrayProjectId" type="hidden" />

		<aui:input autoFocus="${true}" name="name" />

		<aui:select label="routine" name="testrayRoutineId">
			<c:forEach items="${testrayRoutines}" var="testrayRoutine">
				<aui:option label="${fn:escapeXml(testrayRoutine.name)}" selected="${testrayBuild.testrayRoutineId == testrayRoutine.testrayRoutineId}" value="${testrayRoutine.testrayRoutineId}" />
			</c:forEach>
		</aui:select>

		<c:if test="${not testrayBuild.template}">
			<div class="input-icon-group">
				<aui:select label="product-version" name="testrayProductVersionId" required="${true}">
					<c:forEach items="${testrayProductVersions}" var="testrayProductVersion">
						<aui:option label="${testrayProductVersion.name}" selected="${testrayBuild.testrayProductVersionId == testrayProductVersion.testrayProductVersionId}" value="${testrayProductVersion.testrayProductVersionId}" />
					</c:forEach>
				</aui:select>

				<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayProductVersionClass, "create")}'>
					<portlet:renderURL var="addTestrayProductVersionURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="controller" value="product_versions" />
						<portlet:param name="action" value="create" />
						<portlet:param name="closeOnSave" value="${true}" />
						<portlet:param name="testrayProjectId" value="${testrayBuild.testrayProjectId}" />
					</portlet:renderURL>

					<c:set value='${AlloyLanguageUtil.getUnicode("new-product-version")}' var="addTestrayProductVersionURLTitle" />

					<c:set value="javascript:Liferay.Testray.openWindow('${addTestrayProductVersionURL}', '${addTestrayProductVersionURLTitle}', 1000)" var="addTestrayProductVersionURL" />

					<liferay-ui:icon
						cssClass="add-product-version"
						iconCssClass="icon-plus-sign-2"
						message="add-product-version"
						method="get"
						url="${addTestrayProductVersionURL}"
					/>
				</c:if>
			</div>
		</c:if>

		<aui:input name="git-hash" type="text" value="${testrayBuild.gitHash}" />

		<testray:rich-input
			label="description"
			name="description"
			selectedType=""
			types="${fn:split(types, StringPool.COMMA)}"
			value="${testrayBuild.description}"
		/>

		<c:choose>
			<c:when test="${not empty testrayFactorCategoryMap}">
				<h3 class="section-title">
					<liferay-ui:message key="runs" />
				</h3>

				<c:forEach items="${testrayFactorCategoryMap}" var="testrayFactorCategoryEntry" varStatus="i">
					<c:set value="${testrayFactorCategoryEntry.key}" var="testrayFactorCategory" />

					<aui:input cssClass="testray-factor-category-input" name="testrayFactorCategoryId_column${i.index}" type="hidden" value="${testrayFactorCategory.testrayFactorCategoryId}" />
					<aui:input name="testrayFactorCategoryName_column${i.index}" type="hidden" value="${testrayFactorCategory.name}" />
				</c:forEach>

				<aui:fieldset id="testrayFactors">
					<c:forEach items="${testrayRunComposites}" var="testrayRunComposite">
						<c:set value="${testrayRunComposite.getSelectedTestrayFactorOptionIds()}" var="selectedTestrayFactorOptionIds" />

						<aui:field-wrapper cssClass="duplicable lfr-form-row">
							<c:forEach items="${testrayFactorCategoryMap}" var="testrayFactorCategoryEntry" varStatus="l">
								<c:set value="${testrayFactorCategoryEntry.key}" var="testrayFactorCategory" />

								<div class="environment-options-size">
									<aui:select cssClass="testray-factor-category-${testrayFactorCategory.testrayFactorCategoryId} testray-factor-option-select" label="${testrayFactorCategory.name}" name="testrayFactorOptionName_column${testrayFactorCategory.testrayFactorCategoryId}_existing${testrayRunComposite.testrayRunId}" onChange="Liferay.Testray.validateUniqueFormRow('#testrayFactors', '.lfr-form-row')" required="${true}" showEmptyOption="${true}" showRequiredLabel="${false}" style="width: 220px;">
										<c:forEach items="${testrayFactorCategoryEntry.value}" var="testrayFactorOption">
											<aui:option label="${testrayFactorOption.name}" selected="${selectedTestrayFactorOptionIds.contains(testrayFactorOption.testrayFactorOptionId)}" value="${testrayFactorOption.testrayFactorOptionId}" />
										</c:forEach>
									</aui:select>
								</div>
							</c:forEach>

							<aui:input label="delete-run" name="delete-${testrayRunComposite.testrayRunId}" onChange="${htmlNamespace}deactivateTestrayRun(${testrayRunComposite.testrayRunId});" type="checkbox" />

							<aui:input cssClass="factors-required" name="factorsRequired" type="hidden" value="${false}" />
						</aui:field-wrapper>
					</c:forEach>

					<aui:input name="testrayFactorCategoriesCount" type="hidden" value="${testrayFactorCategoryMap.size()}" />
					<aui:input name="testrayFactorIndexes" type="hidden" />
				</aui:fieldset>
			</c:when>
			<c:otherwise>
				<div class="alert alert-info">
					<liferay-ui:message key="create-environment-factors-if-you-want-to-generate-runs" />
				</div>
			</c:otherwise>
		</c:choose>

		<h3 class="section-title">
			<liferay-ui:message key="cases" />
		</h3>

		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayBuild, "updateCases")}'>
			<aui:button-row>
				<portlet:renderURL var="selectTestrayCasesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="controller" value="cases" />
					<portlet:param name="action" value="select" />
					<portlet:param name="testrayBuildId" value="${testrayBuild.testrayBuildId}" />

					<testray:filter-preference
						value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "select", testrayProjectId)}'
					/>
				</portlet:renderURL>

				<c:set value='${AlloyLanguageUtil.getUnicode("cases")}' var="selectTestrayCasesURLTitle" />

				<c:set value="javascript:Liferay.Testray.openWindow('${selectTestrayCasesURL}', '${selectTestrayCasesURLTitle}', 1000)" var="selectTestrayCasesURL" />

				<aui:button onClick="${selectTestrayCasesURL}" value="add-cases" />

				<portlet:renderURL var="viewTestrayCasesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="controller" value="cases" />
					<portlet:param name="action" value="index" />
					<portlet:param name="format" value="js" />
				</portlet:renderURL>

				<portlet:renderURL var="selectTestraySuitesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="controller" value="suites" />
					<portlet:param name="action" value="select" />
					<portlet:param name="redirect" value="${viewTestrayCasesURL}" />
					<portlet:param name="testrayBuildId" value="${testrayBuild.testrayBuildId}" />
				</portlet:renderURL>

				<c:set value='${AlloyLanguageUtil.getUnicode("suites")}' var="selectTestraySuitesURLTitle" />

				<c:set value="javascript:Liferay.Testray.openWindow('${selectTestraySuitesURL}', '${selectTestraySuitesURLTitle}', 1000)" var="selectTestraySuitesURL" />

				<c:set var="noSuitesHelpMessage">
					<liferay-ui:message key="create-suites-if-you-want-to-use-them-to-link-cases" />
				</c:set>

				<span title='${(testraySuitesCount <= 0) ? noSuitesHelpMessage : StringPool.BLANK}'>
					<aui:button disabled="${testraySuitesCount <= 0}" onClick="${selectTestraySuitesURL}" value="add-suites" />
				</span>
			</aui:button-row>
		</c:if>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-linked-cases"
			headerNames="priority,component,name,remove"
			iteratorURL="${portletURL}"
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
					orderable="${true}"
					property="priority"
				/>

				<c:set value="<%= TestrayComponentLocalServiceUtil.getTestrayComponent(testrayCase.getTestrayComponentId()) %>" var="testrayComponent" />

				<liferay-ui:search-container-column-text
					name="component"
					value="${testrayComponent.name}"
				/>

				<liferay-ui:search-container-column-text
					orderable="${true}"
					property="name"
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>
					<liferay-ui:icon
						id="${testrayCase.testrayCaseId}"
						image="delete"
						label="<%= true %>"
						message="remove"
						onClick="${htmlNamespace}removeTestrayCase(this);"
						url="javascript:;"
					/>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				paginate="${false}"
			/>
		</liferay-ui:search-container>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="${not empty param.redirect ? param.redirect : viewTestrayBuildsURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	function ${htmlNamespace}getNewSelectOption(selectNode, newOptions, optionValueParamName) {
		var oldOptionValues = [];

		var selectDOMNode = selectNode.getDOMNode();

		var optionDOMNodes = selectDOMNode.options;

		for (var i = 0; i < optionDOMNodes.length; i++) {
			oldOptionValues.push(optionDOMNodes[i].value);
		}

		var retVal = '';

		if (newOptions) {
			retVal = newOptions.find(
				function(item, index) {
					var newOptionValue = item[optionValueParamName];

					var value = '';

					if (oldOptionValues.indexOf(newOptionValue.toString()) < 0) {
						value = newOptionValue.testrayProductVersionId;
					}

					return value;
				}
			);
		}

		return retVal;
	}

	function ${htmlNamespace}getSplitValue(node) {
		var value = node.val();

		var values = value.split(',');

		return values.filter(
			function(element) {
				return element;
			}
		);
	}

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

	function ${htmlNamespace}updateSelect(select, options, optionValueParamName, optionNameParamName) {
		var A = AUI();

		select.empty();

		var optionElement = A.Node.create('<option value="0"></option>');

		select.append(optionElement);

		for (var i = 0; i < options.length; i++) {
			var option = options[i];

			optionElement = A.Node.create('<option value="' + option[optionValueParamName] + '">' + option[optionNameParamName] + '</option>');

			select.append(optionElement);
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
						var removeLink = '<span><a href="javascript:;" id=' + testrayCase.testrayCaseId + ' onclick="${htmlNamespace}removeTestrayCase(this);"><img src="${themeDisplay.pathThemeImages}/common/delete.png"><span><liferay-ui:message key="remove" /></span></a></span>';

						searchContainer.addRow([testrayCase.priority, testrayCase.mainComponent.name, testrayCase.name, removeLink], testrayCase.testrayCaseId);

						searchContainer.syncUI();
					}
				}
			);

			Liferay.Testray.setFormInput(
				{
					formName: 'editBuildForm',
					inputId: '${htmlNamespace}addTestrayCaseIds',
					val: searchContainer.getData()
				}
			);
		},
		['liferay-search-container', 'testray-base']
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
					formName: 'editBuildForm',
					inputId: '${htmlNamespace}addTestrayCaseIds',
					val: searchContainer.getData()
				}
			);
		},
		['liferay-search-container', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}deactivateTestrayRun',
		function(testrayRunId) {
			var form = Liferay.Form.get('${htmlNamespace}editBuildForm');

			if (form) {
				var formNode = form.formNode;

				var factorsNode = formNode.one('#${htmlNamespace}testrayFactors');

				if (factorsNode) {
					var checkboxNode = factorsNode.one("#delete-" + testrayRunId);

					var checked = checkboxNode.get('checked');

					var testrayFactorCategoryIds = ${testrayFactorCategoryIds};

					testrayFactorCategoryIds.forEach(
						function(factorCategoryId) {
							var node = factorsNode.one("#testrayFactorOptionName_column" + factorCategoryId + "_existing" + testrayRunId);

							Liferay.Util.toggleDisabled(node, checked);
						}
					);
				}
			}
		},
		['liferay-search-container', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}submitBuild',
		function() {
			if (Liferay.Testray.validateUniqueFormRow('#testrayFactors', '.lfr-form-row')) {
				submitForm(document.${htmlNamespace}editBuildForm);
			}
			else {
				Liferay.Testray.addAlert(
					{
						message: '<liferay-ui:message key="runs-must-be-unique" />',
						type: 'danger'
					}
				);
			}
		},
		['testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}updateAvailableTestrayProductVersions',
		function(testrayProductVersions) {
			var A = AUI();

			var select = A.one('#${htmlNamespace}testrayProductVersionId');

			var newSelectOption = ${htmlNamespace}getNewSelectOption(select, testrayProductVersions, 'testrayProductVersionId');

			${htmlNamespace}updateSelect(select, testrayProductVersions, 'testrayProductVersionId', 'name');

			select.val(newSelectOption);
		},
		['aui-base']
	);
</aui:script>

<aui:script use="liferay-form,testray-base">
	var testrayForm = Liferay.Form.get('${htmlNamespace}editBuildForm');

	var testrayFormNode = testrayForm.formNode;
	var testrayFormValidator = testrayForm.formValidator;

	testrayFormValidator.after(
		'submit',
		function() {
			var submitButton = testrayFormNode.all('button[type="submit"]');

			if (!Liferay.Testray.validateUniqueFormRow('#testrayFactors', '.lfr-form-row')) {
				Liferay.Util.enableFormButtons(submitButton);

				submitButton.setStyle('opacity', '1');
			}
		}
	);
</aui:script>