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
	<liferay-util:param name="title" value='${not testrayBuild.template ? "new-build" : "new-template"}' />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayBuild}" model="<%= TestrayBuild.class %>" />

<portlet:actionURL var="addTestrayBuildURL">
	<portlet:param name="controller" value="builds" />
	<portlet:param name="action" value='${not testrayBuild.template ? "add" : "addTemplate"}' />
</portlet:actionURL>

<div class="testray-card">
	<aui:form action="${addTestrayBuildURL}" method="post" name="createBuildForm">
		<aui:input name="redirect" type="hidden" value="${redirect}" />

		<aui:input name="addTestrayCaseIds" type="hidden" value="${testrayCaseIds}" />
		<aui:input name="templateTestrayBuildId" type="hidden" />

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

					<c:set value="javascript:Liferay.Testray.openWindow('${addTestrayProductVersionURL}', '${addTestrayProductVersionURLTitle}', 600, 250)" var="addTestrayProductVersionURL" />

					<liferay-ui:icon
						cssClass="add-product-version"
						iconCssClass="icon-plus-sign-2"
						message="add-product-version"
						method="get"
						onClick="${addTestrayProductVersionURL}"
						url="javascript:;"
					/>
				</c:if>
			</div>
		</c:if>

		<aui:input name="git-hash" type="text" />

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-content" md="12">
				<c:set value="${TestrayRichTextConstants.TYPE_MARKDOWN},${TestrayRichTextConstants.TYPE_PLAIN_TEXT}" var="types" />

				<testray:rich-input
					label="description"
					name="description"
					selectedType=""
					types="${fn:split(types, StringPool.COMMA)}"
					value=""
				/>
			</aui:col>
		</aui:row>

		<c:choose>
			<c:when test="${not empty testrayFactorCategoryMap}">
				<h3 class="section-title">
					<liferay-ui:message key="runs" />
				</h3>

				<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayFactorOptionClass, "create")}'>
					<aui:button-row>
						<portlet:renderURL var="addTestrayFactorOptionURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="controller" value="factor_options" />
							<portlet:param name="action" value="create" />
							<portlet:param name="closeOnSave" value="${true}" />
							<portlet:param name="testrayRoutineId" value="${testrayBuild.testrayRoutineId}" />
						</portlet:renderURL>

						<c:set value='${AlloyLanguageUtil.getUnicode("new-option")}' var="addTestrayFactorOptionURLTitle" />

						<c:set value="javascript:Liferay.Testray.openWindow('${addTestrayFactorOptionURL}', '${addTestrayFactorOptionURLTitle}', 600, 400)" var="addTestrayFactorOptionURL" />

						<aui:button onClick="${addTestrayFactorOptionURL}" value="add-option" />

						<portlet:renderURL var="selectTestrayFactorOptionsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="controller" value="factor_options" />
							<portlet:param name="action" value="select" />
							<portlet:param name="className" value="<%= TestrayBuild.class.getName() %>" />
							<portlet:param name="classPK" value="0" />
							<portlet:param name="multiselect" value="${true}" />
							<portlet:param name="testrayRoutineId" value="${testrayBuild.testrayRoutineId}" />
						</portlet:renderURL>

						<c:set value='${AlloyLanguageUtil.getUnicode("select-options")}' var="selectTestrayFactorOptionsURLTitle" />

						<c:set value="javascript:Liferay.Testray.openWindow('${selectTestrayFactorOptionsURL}', '${selectTestrayFactorOptionsURLTitle}', 1000)" var="selectTestrayFactorOptionsURL" />

						<aui:button onClick="${selectTestrayFactorOptionsURL}" value="select-stacks" />
					</aui:button-row>
				</c:if>

				<c:forEach items="${testrayFactorCategoryMap}" var="testrayFactorCategoryEntry" varStatus="i">
					<c:set value="${testrayFactorCategoryEntry.key}" var="testrayFactorCategory" />

					<aui:input cssClass="testray-factor-category-input" name="testrayFactorCategoryId_column${i.index}" type="hidden" value="${testrayFactorCategory.testrayFactorCategoryId}" />
					<aui:input name="testrayFactorCategoryName_column${i.index}" type="hidden" value="${testrayFactorCategory.name}" />
				</c:forEach>

				<aui:fieldset id="testrayFactors">
					<c:forEach items="${testrayRunComposites}" var="testrayRunComposite">
						<c:set value="${testrayRunComposite.testrayFactors.size()}" var="testrayFactorCategoriesCount" />

						<aui:field-wrapper cssClass="existing-testray-run lfr-form-row">
							<c:forEach items="${testrayRunComposite.testrayFactors}" var="testrayFactor">
								<aui:col span="${12 / testrayFactorCategoriesCount}">
									<aui:input name="testrayFactorOptionId_column${testrayFactor.testrayFactorCategoryId}_existing${testrayRunComposite.testrayRunId}" type="hidden" value="${testrayFactor.testrayFactorOptionId}" />

									<aui:input label="${testrayFactor.testrayFactorCategoryName}" name="testrayFactorOptionName_column${testrayFactor.testrayFactorCategoryId}_existing${testrayRunComposite.testrayRunId}" readonly="true" type="text" value="${testrayFactor.testrayFactorOptionName}" />
								</aui:col>
							</c:forEach>

							<span class="aui-toolbar lfr-autorow-controls">
								<span class="aui-toolbar-content">
									<button class="btn btn-content btn-icon-only delete-row toolbar-item toolbar-last" title="" type="button"><span class="btn-icon icon icon-minus"></span></button>
								</span>
							</span>
						</aui:field-wrapper>
					</c:forEach>

					<aui:field-wrapper cssClass="duplicable lfr-form-row">
						<c:forEach items="${testrayFactorCategoryMap}" var="testrayFactorCategoryEntry" varStatus="l">
							<c:set value="${testrayFactorCategoryEntry.key}" var="testrayFactorCategory" />

							<div class="environment-options-size">
								<aui:select cssClass="testray-factor-category-${testrayFactorCategory.testrayFactorCategoryId} testray-factor-option-select" label="${testrayFactorCategory.name}" name="testrayFactorOptionId_column${l.index}_new1" onChange="${htmlNamespace}updateFactorsRequired(this)" required="${true}" showEmptyOption="${true}" showRequiredLabel="${false}" style="width: 220px;">
									<c:forEach items="${testrayFactorCategoryEntry.value}" var="testrayFactorOption">
										<aui:option label="${testrayFactorOption.name}" selected="${testrayBuild.templateTestrayBuildId <= 0 ? selectedTestrayFactorOptionIds.contains(testrayFactorOption.testrayFactorOptionId) : false}" value="${testrayFactorOption.testrayFactorOptionId}" />
									</c:forEach>
								</aui:select>
							</div>
						</c:forEach>

						<aui:input cssClass="factors-required" name="factorsRequired" type="hidden" value="${false}" />
					</aui:field-wrapper>

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

		<c:choose>
			<c:when test="${testrayCasesCount > 0}">
				<h3 class="section-title">
					<liferay-ui:message key="cases" />
				</h3>

				<aui:button-row>
					<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayBuildClass, "create")}'>
						<portlet:renderURL var="selectTestrayCasesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="controller" value="cases" />
							<portlet:param name="action" value="select" />
							<portlet:param name="scopeByProject" value="${true}" />
							<portlet:param name="testrayBuildId" value="0" />
							<portlet:param name="testrayRoutineId" value="${testrayBuild.testrayRoutineId}" />

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

							<testray:filter-preference
								value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "index", testrayProjectId)}'
							/>
						</portlet:renderURL>

						<portlet:actionURL var="refeshTestraySuitesURL">
							<portlet:param name="controller" value="suites" />
							<portlet:param name="action" value="refreshSuites" />
							<portlet:param name="redirect" value="${viewTestrayCasesURL}" />
						</portlet:actionURL>

						<portlet:renderURL var="selectTestraySuitesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="controller" value="suites" />
							<portlet:param name="action" value="select" />
							<portlet:param name="redirect" value="${refeshTestraySuitesURL}" />
							<portlet:param name="testrayBuildId" value="0" />
							<portlet:param name="testrayRoutineId" value="${testrayBuild.testrayRoutineId}" />
						</portlet:renderURL>

						<c:set value='${AlloyLanguageUtil.getUnicode("suites")}' var="selectTestraySuitesURLTitle" />

						<c:set value="javascript:Liferay.Testray.openWindow('${selectTestraySuitesURL}', '${selectTestraySuitesURLTitle}', 1000)" var="selectTestraySuitesURL" />

						<c:set var="noSuitesHelpMessage">
							<liferay-ui:message key="create-suites-if-you-want-to-use-them-to-link-cases" />
						</c:set>

						<span title='${(testraySuitesCount <= 0) ? noSuitesHelpMessage : StringPool.BLANK}'>
							<aui:button disabled="${testraySuitesCount <= 0}" onClick="${selectTestraySuitesURL}" value="add-suites" />
						</span>
					</c:if>
				</aui:button-row>

				<liferay-ui:search-container
					emptyResultsMessage="there-are-no-linked-cases"
					headerNames="priority,component,name,remove"
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
							orderableProperty="name_sortable"
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
			</c:when>
			<c:otherwise>
				<div class="alert alert-info">
					<liferay-ui:message key="create-cases-if-you-want-to-link-cases-to-this-build" />
				</div>
			</c:otherwise>
		</c:choose>

		<aui:button-row>
			<aui:button type="submit" />

			<portlet:renderURL var="viewTestrayBuildsURL">
				<portlet:param name="controller" value="builds" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayRoutineId" value="${testrayBuild.testrayRoutineId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "builds", "index", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<aui:button href="${viewTestrayBuildsURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	var autoFields;

	if (${not empty testrayFactorCategoryMap}) {
		AUI().use(
			'liferay-auto-fields',
			function(A) {
				autoFields = new Liferay.AutoFields(
					{
						baseRows: '.duplicable',
						contentBox: '#${htmlNamespace}testrayFactors',
						fieldIndexes: '${htmlNamespace}testrayFactorIndexes'
					}
				).render();
			}
		);
	}

	var ${htmlNamespace}TPL_OPTION = '<option value={value}>{name}</option>';

	function ${htmlNamespace}addAutoFieldRows(autoFieldColumns) {
		var A = AUI();

		if (autoFields) {
			autoFieldColumns.each(
				function(node) {
					var nodeId = node.get('id');

					var nodeColumnId = nodeId.replace(/${htmlNamespace}testrayFactorOptionId_column(\d+)_.+/, '$1');

					var columnIndex = A.Lang.toInt(nodeColumnId);

					if (columnIndex == 0) {
						var customFieldRows = A.all('.lfr-form-row:not(:hidden)');

						var lastCustomFieldRow = customFieldRows.last();

						autoFields.addRow(lastCustomFieldRow);
					}

					var autoFieldRowSelects = A.all('.testray-factor-option-select:parent:not(:hidden)');

					var lastAutoFieldRowSelect = autoFieldRowSelects.last();

					var lastAutoFieldRowSelectId = lastAutoFieldRowSelect.get('id');

					var lastAutoFieldRowSelectColumnId = lastAutoFieldRowSelectId.replace(/${htmlNamespace}testrayFactorOptionId_column\d+_new(\d+)/, '$1');

					var lastAutoFieldRowIndex = A.Lang.toInt(lastAutoFieldRowSelectColumnId);

					var autoFieldSelect = A.one('#${htmlNamespace}testrayFactorOptionId_column' + columnIndex + '_new' + lastAutoFieldRowIndex);

					autoFieldSelect.val(node.val());
				}
			);
		}
	}

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
				function(item) {
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
					formName: 'createBuildForm',
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
					formName: 'createBuildForm',
					inputId: '${htmlNamespace}addTestrayCaseIds',
					val: searchContainer.getData()
				}
			);
		},
		['liferay-search-container', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}updateAvailableTestrayFactorOptions',
		function(testrayFactorOptions) {
			var A = AUI();

			var testrayFactorCategoryIdNodes = A.all('.testray-factor-category-input');

			testrayFactorCategoryIdNodes.each(
				function(testrayFactorCategoryIdNode) {
					var testrayFactorOptionIdNodes = A.all('.testray-factor-option-select.testray-factor-category-' + testrayFactorCategoryIdNode.val());

					testrayFactorOptionIdNodes.each(
						function(testrayFactorOptionIdNode) {
							var testrayFactorCategoryIdValue = testrayFactorCategoryIdNode.val();
							var testrayFactorOptionIdNodeValue = testrayFactorOptionIdNode.val();

							${htmlNamespace}updateSelectOptions(
								{
									currentOptionValue: testrayFactorOptionIdNodeValue,
									emptyOption: true,
									optionValueName: 'testrayFactorOptionId',
									requestController: 'factor_options',
									requestControllerMethod: 'index.json',
									requestParams: {
										testrayFactorCategoryId: testrayFactorCategoryIdValue
									},
									selectNewOption: false,
									selectNode: testrayFactorOptionIdNode
								}
							);
						}
					);
				}
			);
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}updateAvailableTestrayProductVersions',
		function() {
			var A = AUI();

			var selectTestrayProductVersionNode = A.one('#${htmlNamespace}testrayProductVersionId');

			if (selectTestrayProductVersionNode) {
				${htmlNamespace}updateSelectOptions(
					{
						emptyOption: false,
						optionValueName: 'testrayProductVersionId',
						requestController: 'product_versions',
						requestControllerMethod: 'index.json',
						requestParams: {
							testrayProjectId: ${testrayBuild.testrayProjectId}
						},
						selectNewOption: true,
						selectNode: selectTestrayProductVersionNode
					}
				);
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}updateFactorsRequired',
		function(node) {
			var A = AUI();

			var selectedNode = A.one('#' + node.name);

			if (selectedNode) {
				var selectedNodeParent = selectedNode.ancestor('.lfr-form-row');

				if (selectedNodeParent) {
					var factorOptionSelectNodeList = selectedNodeParent.all('.testray-factor-option-select');

					var requireFactorsNode = selectedNodeParent.one('.factors-required');

					if (requireFactorsNode) {
						var factorRequired = false;

						factorOptionSelectNodeList.each(
							function(select) {
								var factorValue = select.val();

								if (factorValue) {
									requireFactorsNode.val(true);

									factorRequired = true;
								}
							}
						);

						if (!factorRequired) {
							requireFactorsNode.val(false);
						}
					}
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}updateSelectOptions',
		function(config) {
			var A = AUI();

			var currentOptionValue = config.currentOptionValue || 0;
			var emptyOption = config.emptyOption;
			var optionValueName = config.optionValueName;
			var selectNewOption = config.selectNewOption;
			var selectNode = config.selectNode;

			var loadingMask = new A.LoadingMask(
				{
					target: selectNode.get('parentNode')
				}
			);

			loadingMask.show();

			Liferay.Testray.request(
				{
					controller: config.requestController,
					controllerMethod: config.requestControllerMethod,
					params: config.requestParams
				}
			).then(
				function(response) {
					var optionsJSON = response.data;

					var newSelectOption = '';

					if (selectNewOption) {
						newSelectOption = ${htmlNamespace}getNewSelectOption(selectNode, optionsJSON, optionValueName);
					}

					selectNode.empty();

					var buffer = [];

					if (emptyOption) {
						buffer.push(
							A.Lang.sub(
								${htmlNamespace}TPL_OPTION,
								{
									name: '',
									value: 0
								}
							)
						);
					}

					var sortedOptions = optionsJSON.sort(
						function(a, b) {
							var itemA = a.name.toLowerCase();
							var itemB = b.name.toLowerCase();

							var retVal = 0;

							if (itemA > itemB) {
								retVal = 1;
							}
							else if (itemA < itemB) {
								retVal = -1;
							}

							return retVal;
						}
					);

					A.Array.each(
						sortedOptions,
						function(option) {
							buffer.push(
								A.Lang.sub(
									${htmlNamespace}TPL_OPTION,
									{
										name: option.name,
										value: option[optionValueName]
									}
								)
							);
						}
					);

					var optionsElement = buffer.join('');

					optionsElement = A.Node.create(optionsElement);

					selectNode.append(optionsElement);

					var selectNodeVal = '';

					if (selectNewOption) {
						selectNodeVal = newSelectOption;
					}
					else if (currentOptionValue || currentOptionValue == 0) {
						selectNodeVal = currentOptionValue;
					}

					selectNode.val(selectNodeVal);

					loadingMask.hide();
				}
			);
		},
		['aui-loading-mask-deprecated', 'testray-base']
	);
</aui:script>

<aui:script use="liferay-search-container,testray-base">
	Liferay.on(
		'portletReady',
		function(event) {
			if (event.portletId == '${TestrayPortletKeys.TESTRAY}') {
				var searchContainer = Liferay.SearchContainer.get('${htmlNamespace}testrayCasesSearchContainer');

				Liferay.Testray.setFormInput(
					{
						formName: 'createBuildForm',
						inputId: '${htmlNamespace}addTestrayCaseIds',
						val: searchContainer.getData()
					}
				);
			}
		}
	);
</aui:script>

<aui:script use="aui-form-validator">
	var DEFAULT_FORM_VALIDATOR = A.config.FormValidator;

	var defaultFormValidatorRulesRequired = DEFAULT_FORM_VALIDATOR.RULES.required;

	A.mix(
		DEFAULT_FORM_VALIDATOR.RULES,
		{
			required: function(val, fieldNode, ruleValue) {
				var validatableField = defaultFormValidatorRulesRequired(val, fieldNode, ruleValue);

				if (fieldNode.get('name').indexOf('testrayFactorOptionId') != -1) {
					validatableField = true;

					var selectedNode = A.one(fieldNode);

					if (selectedNode) {
						var selectedNodeParent = selectedNode.ancestor('.lfr-form-row');

						if (selectedNodeParent) {
							var requireFactorsNode = selectedNodeParent.one('.factors-required');

							if (requireFactorsNode) {
								if ((requireFactorsNode.val() == 'true') && !selectedNode.val()) {
									validatableField = false;
								}
							}
						}
					}
				}

				return validatableField;
			}
		},
		true
	);
</aui:script>