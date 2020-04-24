AUI.add(
	'testray-breadcrumb-finder',
	function(A) {
		var AArray = A.Array;
		var Lang = A.Lang;

		var Testray = Liferay.Testray;

		var BREADCRUMB_DATA_LIST = [
			{
				autoCompleteFormatResults: '_formatResultsDefault',
				autoCompleteTextLocator: 'name',
				controller: 'projects',
				controllerMethod: 'index.json',
				params: {
					end: -1,
					start: -1
				}
			},
			{
				autoCompleteFormatResults: '_formatResultsDefault',
				autoCompleteTextLocator: 'name',
				controller: 'routines',
				controllerMethod: 'index.json',
				param: 'testrayProjectId',
				params: {
					end: -1,
					start: -1
				}
			},
			{
				autoCompleteFormatResults: '_formatResultsDefault',
				autoCompleteTextLocator: 'name',
				controller: 'builds',
				controllerMethod: 'index.json',
				param: 'testrayRoutineId',
				params: {
					end: 20,
					start: 0
				}
			},
			{
				autoCompleteFormatResults: '_formatResultsRun',
				autoCompleteTextLocator: 'number',
				controller: 'runs',
				controllerMethod: 'index.json',
				param: 'testrayBuildId',
				params: {
					end: -1,
					start: -1
				}
			},
			{
				autoCompleteFormatResults: '_formatResultsCaseResult',
				autoCompleteTextLocator: 'testrayCaseName',
				controller: 'case_results',
				controllerMethod: 'index.json',
				param: 'testrayRunId'
			}
		];

		var BREADCRUMB_STRUCTURE = ['projectName', 'routineName', 'buildName', 'runName', 'caseResultName'];

		var CSS_ACTIVE = 'active';

		var CSS_BEADCRUMB_SELECTED_ITEM = 'breadcrumb-selected-item';

		var CSS_BREADCRUMB_NAME = 'breadcrumb-name';

		var CSS_CURRENT_BREADCRUMB = 'breadcrumb-current';

		var CSS_DIVIDER = 'divider';

		var CSS_TOOLTIP = 'testray-tooltip-trigger';

		var EVENT_KEY = 'key';

		var STR_ALERT_CONTAINER_ID = 'alertContainerId';

		var STR_AUTOCOMPLETE_FILTER = 'subWordMatch';

		var STR_BLANK = '';

		var STR_CONTAINER_NODE = 'containerNode';

		var STR_CONTENT_NODE = 'contentNode';

		var STR_FILTER_STATUSES = 'filterStatuses';

		var STR_OFFSET_WIDTH = 'offsetWidth';

		var STR_RESULT_LIST_LOCATOR = 'data';

		var STR_SCROLL_WIDTH = 'scrollWidth';

		var STR_SELECTED_CONTAINER_NODE = 'selectedContainerNode';

		var STR_TRIGGER_NODE = 'triggerNode';

		var TPL_AUTOCOMPLETE_RESULT = '<div class="result-item">' +
				'<span class="result-text">{value}</span>' +
				'<span class="result-hotkey-hint-container">{hotkeyHint}</span>' +
			'</div>';

		var TPL_BREADCRUMB_SELECTED_ITEM = '<span class="' + CSS_DIVIDER + '">/</span>' +
			'<span class="' + CSS_BEADCRUMB_SELECTED_ITEM + '">{name}</span>';

		var TPL_HOTKEY_HINT = '<span class="result-hotkey-hint"><kbd>{key}</kbd>{label}</span>';

		var VALUE_TASK_STATUS_OPEN = 3;

		var BreadcrumbFinder = A.Component.create(
			{
				ATTRS: {
					alertContainerId: {
						value: null
					},

					breadcrumbNode: {
						setter: A.one,
						value: null
					},

					containerNode: {
						setter: A.one,
						value: null
					},

					contentNode: {
						setter: A.one,
						value: null
					},

					enableAutoFocus: {
						value: false
					},

					enableNavigation: {
						value: false
					},

					enableTooltips: {
						value: false
					},

					excludeTaskBuilds: {
						value: false
					},

					inputNode: {
						setter: A.one,
						value: null
					},

					inputSelectLevel: {
						value: null
					},

					inputValueNode: {
						setter: A.one,
						value: null
					},

					selectedContainerNode: {
						setter: A.one,
						value: null
					},

					triggerNode: {
						setter: A.one,
						value: null
					}
				},

				NAME: 'breadcrumb-finder',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._setupBreadcrumbDataList();

						instance._inputNode = instance.get('inputNode');

						instance._updateCurrentLevel(0);

						instance._selectedDataCache = {};
					},

					renderUI: function() {
						var instance = this;

						var triggerNode = instance.get(STR_TRIGGER_NODE);

						if (triggerNode) {
							triggerNode.show();
						}

						var enableTooltips = instance.get('enableTooltips');

						if (enableTooltips) {
							instance._activateTooltips();
						}
					},

					bindUI: function() {
						var instance = this;

						var triggerNode = instance.get(STR_TRIGGER_NODE);

						if (triggerNode) {
							triggerNode.on(
								'click',
								A.bind('_handleInputTrigger', instance)
							);
						}

						var inputNode = instance._inputNode;

						var enableAutoFocus = instance.get('enableAutoFocus');
						var enableNavigation = instance.get('enableNavigation');

						if (enableNavigation) {
							A.getDoc().after(
								EVENT_KEY,
								function(event) {
									instance._bindInputEvents();

									var target = event.target.getDOM();

									if (!instance._isTextInput(target)) {
										event.preventDefault();

										var containerNode = instance.get(STR_CONTAINER_NODE);

										if (!containerNode.hasClass(CSS_ACTIVE)) {
											instance._updateContainerNodeState(true);

											Liferay.Util.focusFormField(inputNode);

											instance._handleInputTrigger();
										}
									}
								},
								'down:191'
							);
						}
						else if (enableAutoFocus) {
							if (!instance._inputNodeEventFocus) {
								instance._inputNodeEventFocus = inputNode.on(
									'focus',
									function(event) {
										instance._bindInputEvents();

										var autoComplete = instance._autoComplete;

										if (autoComplete) {
											autoComplete.sendRequest();
										}
										else {
											instance._bindAutoComplete(instance._selectedDataCache);
										}
									}
								);
							}

							Liferay.Util.focusFormField(instance._inputNode);
						}

						var enableTooltips = instance.get('enableTooltips');

						if (enableTooltips) {
							A.on(
								'windowresize',
								A.bind('_activateTooltips', instance)
							);
						}
					},

					_activateTooltips: function() {
						var instance = this;

						var breadcrumbNode = instance.get('breadcrumbNode');

						if (breadcrumbNode) {
							var breadcrumbOffsetWidth = breadcrumbNode.get(STR_OFFSET_WIDTH);
							var breadcrumbScrollWidth = breadcrumbNode.get(STR_SCROLL_WIDTH);

							var currentItemTruncated = false;

							if (breadcrumbScrollWidth > breadcrumbOffsetWidth) {
								currentItemTruncated = true;
							}

							var breadcrumbItemNodes = breadcrumbNode.all('.breadcrumb-name');

							A.each(
								breadcrumbItemNodes,
								function(item) {
									var currentItem = item.hasClass(CSS_CURRENT_BREADCRUMB);

									var offsetWidth = item.get(STR_OFFSET_WIDTH);
									var scrollWidth = item.get(STR_SCROLL_WIDTH);

									var toggleClass = (scrollWidth > offsetWidth || (currentItem && currentItemTruncated));

									item.toggleClass(CSS_TOOLTIP, toggleClass);
								}
							);
						}
					},

					_bindAutoComplete: function(data) {
						var instance = this;

						var breadcrumbData = instance._setCurrentLevelBreadcrumbData();

						if (breadcrumbData) {
							breadcrumbData.params = breadcrumbData.params || {};

							breadcrumbData.params.name = '{query}';

							var param = breadcrumbData.param;

							if (param) {
								breadcrumbData.params[param] = data[param];
							}

							var source = Testray.buildURLWithParams(
								{
									controller: breadcrumbData.controller,
									controllerMethod: breadcrumbData.controllerMethod,
									params: breadcrumbData.params
								}
							);

							var autoComplete = instance._autoComplete;

							if (!autoComplete) {
								autoComplete = instance._initializeAutoComplete(
									{
										breadcrumbData: breadcrumbData,
										source: source
									}
								);
							}
							else {
								autoComplete.clearCache();

								autoComplete.set(
									'resultFormatter',
									instance[breadcrumbData.autoCompleteFormatResults]
								);

								autoComplete.set('resultTextLocator', breadcrumbData.autoCompleteTextLocator);

								autoComplete.set('source', source);
							}

							autoComplete.sendRequest();
						}
					},

					_bindInputEvents: function() {
						var instance = this;

						var inputNode = instance._inputNode;

						if (!instance._inputNodeEventKey) {
							instance._inputNodeEventKey = inputNode.on(
								EVENT_KEY,
								function(event) {
									event.preventDefault();
								},
								'tab'
							);
						}

						if (!instance._inputNodeEventBackspace) {
							instance._inputNodeEventBackspace = inputNode.on(
								EVENT_KEY,
								function(event) {
									if (inputNode.val() === STR_BLANK && instance._currentLevel) {
										instance._handleTraverseOut(event);
									}
								},
								'backspace'
							);
						}
					},

					_formatResultsCaseResult: function(query, results) {
						var hotkeyHintsHTML = Lang.sub(
							TPL_HOTKEY_HINT,
							{
								key: Liferay.Language.get('enter'),
								label: Liferay.Language.get('view-page')
							}
						);

						return AArray.map(
							results,
							function(result) {
								var testrayCaseName = result.raw.testrayCaseName;

								var values = {
									hotkeyHint: hotkeyHintsHTML,
									value: testrayCaseName
								};

								return Lang.sub(TPL_AUTOCOMPLETE_RESULT, values);
							}
						);
					},

					_formatResultsDefault: function(query, results) {
						var hotkeyHintsHTML = Lang.sub(
							TPL_HOTKEY_HINT,
							{
								key: Liferay.Language.get('tab'),
								label: Liferay.Language.get('search-in')
							}
						);

						hotkeyHintsHTML += Lang.sub(
							TPL_HOTKEY_HINT,
							{
								key: Liferay.Language.get('enter'),
								label: Liferay.Language.get('view-page')
							}
						);

						return AArray.map(
							results,
							function(result) {
								var name = result.raw.name;

								var values = {
									hotkeyHint: hotkeyHintsHTML,
									value: name
								};

								return Lang.sub(TPL_AUTOCOMPLETE_RESULT, values);
							}
						);
					},

					_formatResultsRun: function(query, results) {
						var hotkeyHintsHTML = Lang.sub(
							TPL_HOTKEY_HINT,
							{
								key: Liferay.Language.get('tab'),
								label: Liferay.Language.get('search-in')
							}
						);

						hotkeyHintsHTML += Lang.sub(
							TPL_HOTKEY_HINT,
							{
								key: Liferay.Language.get('enter'),
								label: Liferay.Language.get('view-page')
							}
						);

						return AArray.map(
							results,
							function(result) {
								var number = result.raw.number;

								var values = {
									hotkeyHint: hotkeyHintsHTML,
									value: number
								};

								return Lang.sub(TPL_AUTOCOMPLETE_RESULT, values);
							}
						);
					},

					_getBreadcrumbParams: function(text) {
						var instance = this;

						var textArray = text.split('/');

						textArray = instance._removeEmptyItems(textArray);

						var params = {};

						textArray.forEach(
							function(item, index) {
								params[BREADCRUMB_STRUCTURE[index]] = item.trim();
							}
						);

						return params;
					},

					_handleBlur: function(event) {
						var instance = this;

						instance._inputNode.blur();

						instance._updateContainerNodeState(false);

						var selectedContainerNode = instance.get(STR_SELECTED_CONTAINER_NODE);

						selectedContainerNode.empty();

						instance._updateCurrentLevel(0);
					},

					_handleInputTrigger: function() {
						var instance = this;

						instance._initializeCurrentBreadcrumb();

						var inputNode = instance._inputNode;

						inputNode.val(STR_BLANK);

						var contentNode = instance.get(STR_CONTENT_NODE);

						contentNode.once(
							'clickoutside',
							A.bind('_handleBlur', instance)
						);

						A.getDoc().once(
							EVENT_KEY,
							A.bind('_handleBlur', instance),
							'esc'
						);
					},

					_handleNavigation: function(event) {
						var instance = this;

						var data = event.result.raw;

						instance._handleTraverseIn(data, false);

						var selectedContainerNode = instance.get(STR_SELECTED_CONTAINER_NODE);

						selectedContainerNode.addClass(CSS_ACTIVE);

						var selectedText = selectedContainerNode.text();

						if (selectedText !== '') {
							var params = instance._getBreadcrumbParams(selectedText);

							Testray.request(
								{
									controller: 'breadcrumb',
									controllerMethod: 'search.json',
									method: 'GET',
									params: params
								}
							).then(
								instance._handleNavigationSuccess,
								function(response) {
									instance._handleNavigationError(response);
								}
							);
						}
					},

					_handleNavigationError: function(response) {
						var instance = this;

						var message = response.message;

						if (message) {
							var containerId = instance.get(STR_ALERT_CONTAINER_ID);

							Testray.addAlert(
								{
									containerId: containerId,
									message: message,
									type: 'danger'
								}
							);
						}
					},

					_handleNavigationSuccess: function(response) {
						var responseData = response.data;

						var url = responseData.url;

						if (url) {
							window.location.href = url;
						}
					},

					_handleRequestError: function(response) {
						var instance = this;

						var containerId = instance.get(STR_ALERT_CONTAINER_ID);

						Testray.addAlert(
							{
								containerId: containerId,
								message: Liferay.Language.get('your-request-failed-to-complete'),
								type: 'danger'
							}
						);
					},

					_handleTraverseIn: function(data, rebindAutoComplete) {
						var instance = this;

						var autoComplete = instance._autoComplete;
						var inputNode = instance._inputNode;

						if (autoComplete) {
							autoComplete.reset();
						}

						if (data) {
							var selectedText = data[instance._breadcrumbData.autoCompleteTextLocator];

							var selectedItem = Lang.sub(
								TPL_BREADCRUMB_SELECTED_ITEM,
								{
									name: selectedText
								}
							);

							var selectedContainerNode = instance.get(STR_SELECTED_CONTAINER_NODE);

							if (selectedContainerNode) {
								selectedContainerNode.append(selectedItem);

								inputNode.val('');

								instance._updateCurrentLevel(instance._currentLevel + 1);

								instance._selectedDataCache = A.merge(instance._selectedDataCache, data);

								if (rebindAutoComplete) {
									instance._bindAutoComplete(instance._selectedDataCache);
								}
								else if (autoComplete) {
									instance._setCurrentLevelBreadcrumbData();

									autoComplete.set(
										'source',
										{}
									);
								}
							}
						}

						Liferay.Util.focusFormField(inputNode);

						return data;
					},

					_handleTraverseOut: function(event) {
						event.preventDefault();

						var instance = this;

						var selectedContainerNode = instance.get(STR_SELECTED_CONTAINER_NODE);

						var selectedDividerNodes = selectedContainerNode.all('.' + CSS_DIVIDER);
						var selectedNodes = selectedContainerNode.all('.' + CSS_BEADCRUMB_SELECTED_ITEM);

						var lastSelectedDividerNode = selectedDividerNodes.last();
						var lastSelectedNode = selectedNodes.last();

						if (lastSelectedNode && lastSelectedDividerNode) {
							lastSelectedNode.remove();
							lastSelectedDividerNode.remove();
						}

						var lastSelectedNodeText = lastSelectedNode.text();

						var inputNode = instance._inputNode;

						inputNode.val(lastSelectedNodeText);

						inputNode.select();

						instance._updateCurrentLevel(instance._currentLevel - 1);

						var data = instance._selectedDataCache;

						var inputSelectLevel = instance.get('inputSelectLevel');

						if (inputSelectLevel) {
							instance._setInputValue();
						}

						instance._bindAutoComplete(data);
					},

					_initializeAutoComplete: function(data) {
						var instance = this;

						var breadcrumbData = data.breadcrumbData;
						var source = data.source;

						var contentNode = instance.get(STR_CONTENT_NODE);

						var autoComplete = new A.AutoCompleteList(
							{
								activateFirstItem: true,
								align: {
									node: contentNode,
									points: ['tl', 'bl']
								},
								inputNode: instance._inputNode,
								minQueryLength: 0,
								resultFilters: STR_AUTOCOMPLETE_FILTER,
								resultFormatter: instance[breadcrumbData.autoCompleteFormatResults],
								resultListLocator: STR_RESULT_LIST_LOCATOR,
								resultTextLocator: breadcrumbData.autoCompleteTextLocator,
								scrollIntoView: true,
								source: source
							}
						);

						autoComplete.render();

						if (!instance._autoCompleteEventSelect) {
							instance._autoCompleteEventSelect = autoComplete.on(
								'select',
								function(event) {
									event.preventDefault();

									var originEvent = event.originEvent;

									var enableNavigation = instance.get('enableNavigation');
									var inputSelectLevel = instance.get('inputSelectLevel');

									var data = event.result.raw;

									if (enableNavigation && (originEvent.button == 1 || originEvent.keyCode == 13)) {
										instance._handleNavigation(event);
									}
									else if (inputSelectLevel && (inputSelectLevel == instance._currentLevel + 1)) {
										var selectedData = instance._handleTraverseIn(data, false);

										instance._setInputValue(selectedData);
									}
									else if (instance._currentLevel + 1 < BREADCRUMB_DATA_LIST.length) {
										instance._handleTraverseIn(data, true);
									}
								}
							);
						}

						instance._autoComplete = autoComplete;

						return instance._autoComplete;
					},

					_initializeCurrentBreadcrumb: function() {
						var instance = this;

						var breadcrumbCurrentData = {};

						var breadcrumbNode = instance.get('breadcrumbNode');

						if (breadcrumbNode) {
							var breadcrumbNameNodes = breadcrumbNode.all('.' + CSS_BREADCRUMB_NAME);

							var selectedItems = [];

							breadcrumbNameNodes.each(
								function(item) {
									var classPK = item.getData('class-pk');
									var fieldName = item.getData('field-name');

									if (fieldName && classPK) {
										breadcrumbCurrentData[fieldName] = classPK;
									}

									var name = item.text();

									var selectedItem = Lang.sub(
										TPL_BREADCRUMB_SELECTED_ITEM,
										{
											name: name
										}
									);

									selectedItems.push(selectedItem);

									instance._updateCurrentLevel(instance._currentLevel + 1);
								}
							);

							var testrayProjectId = breadcrumbCurrentData.testrayProjectId;

							if (testrayProjectId && testrayProjectId != 0) {
								var selectedItemsHTML = selectedItems.join('');

								var selectedContainerNode = instance.get(STR_SELECTED_CONTAINER_NODE);

								if (selectedContainerNode) {
									selectedContainerNode.append(selectedItemsHTML);
								}
							}
							else {
								instance._updateCurrentLevel(0);
							}
						}

						instance._selectedDataCache = A.merge(instance._selectedDataCache, breadcrumbCurrentData);

						instance._bindAutoComplete(instance._selectedDataCache);
					},

					_isTextInput: function(currentNode) {
						var retVal = false;

						if (currentNode) {
							var nodeName = currentNode.nodeName;

							retVal = currentNode.isContentEditable || nodeName === 'INPUT' || nodeName === 'TEXTAREA';
						}

						return retVal;
					},

					_removeEmptyItems: function(array) {
						return AArray.filter(
							array,
							function(item) {
								var retVal = false;

								if (item !== '') {
									retVal = true;
								}

								return retVal;
							}
						);
					},

					_setCurrentLevelBreadcrumbData: function() {
						var instance = this;

						var currentLevel = instance._currentLevel;

						var breadcrumbData = BREADCRUMB_DATA_LIST[currentLevel];

						instance._breadcrumbData = breadcrumbData;

						return breadcrumbData;
					},

					_setInputValue: function(selectedData) {
						var instance = this;

						var inputValueNode = instance.get('inputValueNode');

						if (inputValueNode) {
							var value = STR_BLANK;

							if (selectedData) {
								value = selectedData[instance._breadcrumbData.param];
							}

							inputValueNode.val(value);

							inputValueNode.simulate('change');
						}
					},

					_setupBreadcrumbDataList: function() {
						var instance = this;

						var excludeTaskBuilds = instance.get('excludeTaskBuilds');

						if (excludeTaskBuilds) {
							var buildsObject = BREADCRUMB_DATA_LIST[2];

							if (buildsObject) {
								buildsObject.params[STR_FILTER_STATUSES] = VALUE_TASK_STATUS_OPEN;
							}
						}
					},

					_updateContainerNodeState: function(state) {
						var instance = this;

						var containerNode = instance.get(STR_CONTAINER_NODE);

						if (containerNode) {
							containerNode.toggleClass(CSS_ACTIVE, state);
						}
					},

					_updateCurrentLevel: function(level) {
						var instance = this;

						instance._currentLevel = level;

						instance.fire(
							'finderLevelChange',
							{
								currentLevel: level
							}
						);
					}
				}
			}
		);

		Testray.BreadcrumbFinder = BreadcrumbFinder;
	},
	'',
	{
		requires: ['autocomplete', 'autocomplete-filters', 'event-focus', 'event-key', 'event-outside', 'event-resize', 'node-event-simulate', 'testray-base']
	}
);