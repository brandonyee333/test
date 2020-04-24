AUI.add(
	'testray-row-checker-toolbar',
	function(A) {
		var Lang = A.Lang;

		var Testray = Liferay.Testray;

		var CHECKBOX_SELECTOR = 'td.first input[type=checkbox]';

		var CSS_VALIDATION_CONTAINER = 'row-checker-toolbar-validation-container';

		var STR_BLANK = '';

		var STR_CHANGE = 'change';

		var STR_CLICK = 'click';

		var STR_CONTAINER = 'container';

		var STR_SEARCH_CONTAINER_WRAPPER = 'searchContainerWrapper';

		var STR_SELECT_ALL_CHECKBOX = 'selectAllCheckbox';

		var TPL_VALIDATION_CONTAINER = '<div class="' + CSS_VALIDATION_CONTAINER + '"></div>';

		var TPL_VALIDATION_ITEM_ERROR = '<li class="validation-item"><i class="icon-exclamation-sign"></i><span class="error-id">{id}</span> {label}</li>';

		var TPL_VALIDATION_LIST = '<ul class="row-checker-toolbar-validation-list">{validationItems}</ul>';

		var RowCheckerToolbar = A.Component.create(
			{
				ATTRS: {
					actionClear: {
						setter: A.one,
						value: '#rowCheckerActionClear'
					},

					actionMerge: {
						value: null
					},

					actionSplit: {
						value: null
					},

					alertContainerId: {
						value: '.testray-alert-container'
					},

					container: {
						setter: A.one,
						value: null
					},

					counterSelector: {
						value: '.item-count'
					},

					searchContainerId: {
						value: null
					},

					searchContainerWrapper: {
						setter: A.one,
						value: null
					},

					selectAllCheckbox: {
						setter: A.one,
						value: '#rowCheckerSelectAllCheckbox'
					}
				},

				NAME: 'row-checker-toolbar',

				prototype: {
					initializer: function() {
						var instance = this;

						var searchContainerWrapper = instance.get(STR_SEARCH_CONTAINER_WRAPPER);

						if (searchContainerWrapper) {
							instance._selectedItems = searchContainerWrapper.all(CHECKBOX_SELECTOR + ':checked');
						}

						var searchContainerId = instance.get('searchContainerId');

						instance._searchContainer = Liferay.SearchContainer.get(searchContainerId);

						instance._errors = [];
					},

					bindUI: function() {
						var instance = this;

						var searchContainerWrapper = instance.get(STR_SEARCH_CONTAINER_WRAPPER);

						searchContainerWrapper.delegate(
							STR_CHANGE,
							A.bind('_handleChecked', instance),
							CHECKBOX_SELECTOR
						);

						var selectAllCheckbox = instance.get(STR_SELECT_ALL_CHECKBOX);

						if (selectAllCheckbox) {
							selectAllCheckbox.on(
								STR_CLICK,
								A.bind('_handleSelectAllCheckbox', instance)
							);
						}

						var actionClear = instance.get('actionClear');

						actionClear.on(
							STR_CLICK,
							A.bind('clear', instance)
						);

						var actionMerge = instance.get('actionMerge');

						if (actionMerge) {
							instance._rowCheckerActionMerge = new Testray.RowCheckerActionMerge(
								{
									button: actionMerge.button,
									redirect: actionMerge.redirect,
									testrayTaskId: actionMerge.testrayTaskId,
									toolbar: instance
								}
							).render();
						}

						var actionSplit = instance.get('actionSplit');

						if (actionSplit) {
							instance._rowCheckerActionSplit = new Testray.RowCheckerActionSplit(
								{
									button: actionSplit.button,
									data: actionSplit.data,
									requestConfig: actionSplit.requestConfig,
									toolbar: instance
								}
							).render();
						}

						var container = instance.get(STR_CONTAINER);

						container.prepend(TPL_VALIDATION_CONTAINER);
					},

					clear: function() {
						var instance = this;

						var selectAllCheckbox = instance.get(STR_SELECT_ALL_CHECKBOX);

						if (selectAllCheckbox) {
							selectAllCheckbox.attr('checked', false);

							selectAllCheckbox.toggleClass('info');
						}

						var selectedItems = instance._selectedItems;

						if (selectedItems) {
							selectedItems.attr('checked', false);

							A.each(
								selectedItems,
								function(item) {
									var selectedRow = item.ancestor('tr');

									selectedRow.toggleClass('info');
								}
							);

							instance._handleChecked();
						}
					},

					getSelectedItems: function() {
						var instance = this;

						return instance._selectedItems;
					},

					getSelectedItemsCount: function() {
						var instance = this;

						var selectedItems = instance._selectedItems;

						var count = 0;

						if (selectedItems) {
							count = selectedItems.size();
						}

						return count;
					},

					_addError: function(id, label) {
						var instance = this;

						var validationItemHTML = Lang.sub(
							TPL_VALIDATION_ITEM_ERROR,
							{
								id: id,
								label: label
							}
						);

						var errors = instance._errors;

						errors.push(validationItemHTML);
					},

					_handleChecked: function() {
						var instance = this;

						var searchContainerWrapper = instance.get(STR_SEARCH_CONTAINER_WRAPPER);

						if (searchContainerWrapper) {
							instance._selectedItems = searchContainerWrapper.all(CHECKBOX_SELECTOR + ':checked');

							instance._selectedItems.attr('autocomplete', 'off');

							var selectedItemsCount = instance.getSelectedItemsCount();

							instance._updateCount(selectedItemsCount);

							instance._updateSelectAllCheckbox(selectedItemsCount);

							searchContainerWrapper.toggleClass('active', selectedItemsCount);

							var container = instance.get(STR_CONTAINER);

							container.toggleClass('inactive', !selectedItemsCount);

							instance._validateItems();
						}
					},

					_handleSelectAllCheckbox: function() {
						var instance = this;

						var selectAllCheckbox = instance.get(STR_SELECT_ALL_CHECKBOX);

						if (selectAllCheckbox) {
							Liferay.Util.checkAll(
								instance._searchContainer._table,
								'rowIds',
								selectAllCheckbox,
								'tbody tr'
							);

							instance._handleChecked();
						}
					},

					_updateCount: function(count) {
						var instance = this;

						var container = instance.get(STR_CONTAINER);
						var counterSelector = instance.get('counterSelector');

						if (container && counterSelector) {
							var counterNode = container.one(counterSelector);

							counterNode.text(count);
						}
					},

					_updateSelectAllCheckbox: function(count) {
						var instance = this;

						var searchContainerSize = instance._searchContainer.getSize();

						var selectAllCheckbox = instance.get(STR_SELECT_ALL_CHECKBOX);

						if (selectAllCheckbox) {
							selectAllCheckbox.attr('indeterminate', false);

							if (count && count < searchContainerSize) {
								selectAllCheckbox.attr('indeterminate', true);
							}
						}
					},

					_validateItems: function() {
						var instance = this;

						instance._errors = [];

						var errors = instance._errors;
						var rowCheckerActionMerge = instance._rowCheckerActionMerge;
						var rowCheckerActionSplit = instance._rowCheckerActionSplit;

						if (rowCheckerActionMerge && rowCheckerActionMerge._validateActionMerge) {
							rowCheckerActionMerge._validateActionMerge();
						}

						if (rowCheckerActionSplit && rowCheckerActionSplit._validateActionSplit) {
							rowCheckerActionSplit._validateActionSplit();
						}

						var validationContainer = A.one('.' + CSS_VALIDATION_CONTAINER);

						var disabled = false;

						if (validationContainer) {
							if (errors.length) {
								disabled = true;

								var validationListHTML = Lang.sub(
									TPL_VALIDATION_LIST,
									{
										validationItems: errors.join(STR_BLANK)
									}
								);

								validationContainer.html(validationListHTML);
							}
							else {
								validationContainer.empty();
							}
						}

						if (rowCheckerActionMerge) {
							var mergeButton = instance._rowCheckerActionMerge.get('button');

							Liferay.Util.toggleDisabled(mergeButton, disabled);
						}

						if (rowCheckerActionSplit) {
							var splitButton = rowCheckerActionSplit.get('button');

							Liferay.Util.toggleDisabled(splitButton, disabled);
						}
					}
				}
			}
		);

		Testray.RowCheckerToolbar = RowCheckerToolbar;
	},
	'',
	{
		requires: ['aui-base', 'liferay-search-container', 'row-checker-action-merge', 'row-checker-action-split', 'testray-base']
	}
);