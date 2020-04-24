AUI.add(
	'testray-row-checker-action-merge',
	function(A) {
		var Testray = Liferay.Testray;

		var CSS_SUBTASK_NAME = 'subtask-name';

		var STR_BLANK = '';

		var STR_BUTTON = 'button';

		var STR_DOT = '.';

		var STR_TR = 'tr';

		var SELECTOR_SUBTASK_NAME = STR_DOT + CSS_SUBTASK_NAME;

		var RowCheckerActionMerge = A.Component.create(
			{
				ATTRS: {
					button: {
						setter: A.one,
						value: null
					},

					redirect: {
						value: null
					},

					testrayTaskId: {
						value: null
					},

					toolbar: {
						value: null
					}
				},

				NAME: 'row-checker-action-merge',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._toolbar = instance.get('toolbar');
					},

					bindUI: function() {
						var instance = this;

						var button = instance.get(STR_BUTTON);

						if (button) {
							button.on(
								'click',
								A.bind('_handleAction', instance)
							);
						}
					},

					_handleAction: function(event) {
						var instance = this;

						var selectedItems = instance._toolbar.getSelectedItems();

						var selectedNamesList = [];

						A.each(
							selectedItems,
							function(item) {
								var selectedRow = item.ancestor(STR_TR);

								if (selectedRow) {
									var nameNode = selectedRow.one(SELECTOR_SUBTASK_NAME);

									if (nameNode) {
										var name = nameNode.text().trim();

										selectedNamesList.push(name);
									}
								}
							}
						);

						var currentTarget = event.currentTarget;

						if (currentTarget) {
							Liferay.Util.toggleDisabled(currentTarget, true);

							var processingText = Liferay.Language.get('merging');

							currentTarget.text(processingText);

							var mergeURL = Testray.buildBaseURL(
								{
									controller: 'subtasks',
									controllerMethod: 'merge'
								}
							);

							var redirect = instance.get('redirect');
							var testrayTaskId = instance.get('testrayTaskId');

							mergeURL = Liferay.Util.addParams(
								{
									names: selectedNamesList.join(),
									p_auth: Liferay.authToken,
									p_p_lifecycle: 1,
									redirect: redirect,
									testrayTaskId: testrayTaskId
								},
								mergeURL
							);

							submitForm(document.hrefFm, mergeURL);
						}
					},

					_validateActionMerge: function() {
						var instance = this;

						var toolbar = instance.get('toolbar');

						var selectedItemsCount = toolbar.getSelectedItemsCount();

						if (selectedItemsCount) {
							var label = STR_BLANK;

							if (selectedItemsCount < 2) {
								label = Liferay.Language.get('please-select-at-least-two-subtasks-to-merge');
							}

							if (label) {
								toolbar._addError(STR_BLANK, label);
							}
						}

						var selectedItems = toolbar.getSelectedItems();

						if (selectedItems) {
							selectedItems.each(
								function(item) {
									instance._validateActionMergeItem(item);
								}
							);
						}
					},

					_validateActionMergeItem: function(item) {
						var instance = this;

						var itemRow = item.ancestor('tr');

						if (itemRow) {
							var subtaskNameCell = itemRow.one('.subtask-name');

							var subtaskName = STR_BLANK;

							if (subtaskNameCell) {
								subtaskName = subtaskNameCell.text().trim();
							}

							var toolbar = instance.get('toolbar');

							var assignedUserId = Testray.getTableRowCssValue(itemRow, 'assignee');

							var currentUserId = themeDisplay.getUserId();

							if ((assignedUserId > 0) && (assignedUserId != currentUserId)) {
								var assignedLabel = A.Lang.sub(
									Liferay.Language.get('subtask-x-must-be-assigned-to-you-to-be-used-in-a-merge'),
									[
										subtaskName
									]
								);

								toolbar._addError(subtaskName, assignedLabel);
							}
						}
					}
				}
			}
		);

		Testray.RowCheckerActionMerge = RowCheckerActionMerge;
	},
	'',
	{
		requires: ['liferay-alert', 'testray-base']
	}
);