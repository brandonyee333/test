AUI.add(
	'testray-row-checker-action-split',
	function(A) {
		var Lang = A.Lang;

		var Testray = Liferay.Testray;

		var EVENT_CLICK = 'click';

		var STR_BLANK = '';

		var STR_BUTTON = 'button';

		var STR_COMMA = ',';

		var STR_TOOLBAR = 'toolbar';

		var STR_TR = 'tr';

		var TPL_VIEW_TESTRAY_SUBTASK_URL = Liferay.ThemeDisplay.getLayoutURL() + '/-/testray/subtasks/{id}';

		var RowCheckerActionSplit = A.Component.create(
			{
				ATTRS: {
					button: {
						setter: A.one,
						value: null
					},

					data: {
						value: null
					},

					requestConfig: {
						value: null
					},

					toolbar: {
						value: null
					}
				},

				NAME: 'row-checker-action-split',

				prototype: {
					bindUI: function() {
						var instance = this;

						var button = instance.get(STR_BUTTON);

						if (button) {
							button.on(
								EVENT_CLICK,
								A.bind('_handleAction', instance)
							);
						}
					},

					_handleAction: function(event) {
						var instance = this;

						var requestConfig = instance.get('requestConfig');
						var toolbar = instance.get(STR_TOOLBAR);

						var selectedItems = toolbar.getSelectedItems();

						if (selectedItems) {
							var selectedItemsValues = selectedItems.get('value').join(STR_COMMA);

							requestConfig.params = requestConfig.params || {};

							requestConfig.params.testrayCaseResultIds = selectedItemsValues;

							var currentTarget = event.currentTarget;

							if (currentTarget) {
								var originalText = currentTarget.text();

								Liferay.Util.toggleDisabled(currentTarget, true);

								var processingText = Liferay.Language.get('splitting');

								currentTarget.text(processingText);

								Testray.request(requestConfig).then(
									function(response) {
										instance._resetButtonText(currentTarget, originalText);

										instance._handleSuccess(response, selectedItems);
									}
								).catch(
									function(response) {
										instance._resetButtonText(currentTarget, originalText);

										instance._handleError(response);
									}
								);
							}
						}
					},

					_handleError: function(response) {
						var instance = this;

						var toolbar = instance.get(STR_TOOLBAR);

						var alertContainerId = toolbar.get('alertContainerId');

						new Liferay.Alert(
							{
								duration: Testray.CONSTANTS.ALERT_TRANSITION_DURATION,
								icon: 'exclamation-full',
								message: response.message,
								type: 'danger'
							}
						).render(alertContainerId);
					},

					_handleSuccess: function(response, selectedItems) {
						var instance = this;

						var toolbar = instance.get(STR_TOOLBAR);

						var searchContainerId = toolbar.get('searchContainerId');

						var searchContainer = Liferay.SearchContainer.get(searchContainerId);

						if (searchContainer) {
							A.each(
								selectedItems,
								function(item) {
									var selectedRow = item.ancestor(STR_TR);

									var rowId = item.val();

									searchContainer.deleteRow(selectedRow, rowId);
								}
							);
						}

						var data = response.data;

						var name = data.name;

						var linkText = Lang.sub(
							Liferay.Language.get('view-x'),
							[
								name
							]
						);

						var viewTestraySubtaskURL = Lang.sub(
							TPL_VIEW_TESTRAY_SUBTASK_URL,
							{
								id: data.testraySubtaskId
							}
						);

						var selectedItemsSize = selectedItems.size();

						var successMessage = Lang.sub(
							Liferay.Language.get('x-tests-were-split-into-x-successfully'),
							[
								selectedItemsSize,
								name
							]
						);

						if (selectedItemsSize === 1) {
							successMessage = Lang.sub(
								Liferay.Language.get('x-test-was-split-into-x-successfully'),
								[
									selectedItemsSize,
									name
								]
							);
						}

						instance._showSuccessMessage(
							{
								linkText: linkText,
								linkURL: viewTestraySubtaskURL,
								message: successMessage
							}
						);

						toolbar.clear();
					},

					_resetButtonText: function(button, originalText) {
						Liferay.Util.toggleDisabled(button, false);

						button.text(originalText);
					},

					_showSuccessMessage: function(config) {
						var instance = this;

						var toolbar = instance.get(STR_TOOLBAR);

						var successMessageHTML = Lang.sub(
							Testray.TEMPLATES.ALERT_LINK,
							config
						);

						new Liferay.Alert(
							{
								delay: {
									hide: Testray.CONSTANTS.ALERT_HIDE_DELAY,
									show: 0
								},
								duration: Testray.CONSTANTS.ALERT_TRANSITION_DURATION,
								icon: 'check',
								message: successMessageHTML,
								type: 'success'
							}
						).render(toolbar.get('alertContainerId'));
					},

					_validateActionSplit: function() {
						var instance = this;

						var data = instance.get('data');
						var toolbar = instance.get(STR_TOOLBAR);

						var assignedUserId = data.assignedUserId;

						var currentUserId = themeDisplay.getUserId();

						if (assignedUserId != currentUserId) {
							var errorLabelAssignedToYou = Lang.sub(
								Liferay.Language.get('subtask-x-must-be-assigned-to-you-to-be-used-in-a-split'),
								[
									data.subtaskName
								]
							);

							toolbar._addError(STR_BLANK, errorLabelAssignedToYou);
						}

						var subtaskStatus = data.subtaskStatus;

						if (subtaskStatus !== 'in-analysis') {
							var errorLabelInAnalysis = Lang.sub(
								Liferay.Language.get('subtask-x-must-be-in-analysis-to-be-used-in-a-split'),
								[
									data.subtaskName
								]
							);

							toolbar._addError(STR_BLANK, errorLabelInAnalysis);
						}

						var selectedItemsCount = toolbar.getSelectedItemsCount();

						var testrayCaseResultsTotalCount = data.testrayCaseResultsTotalCount;

						if (selectedItemsCount == testrayCaseResultsTotalCount) {
							var errorLabelSplitAll = Liferay.Language.get('you-cannot-split-all-case-results-from-a-subtask');

							toolbar._addError(STR_BLANK, errorLabelSplitAll);
						}
					}
				}
			}
		);

		Testray.RowCheckerActionSplit = RowCheckerActionSplit;
	},
	'',
	{
		requires: ['liferay-alert', 'testray-base']
	}
);