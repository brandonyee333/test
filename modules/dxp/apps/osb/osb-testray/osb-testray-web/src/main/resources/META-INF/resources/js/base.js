AUI.add(
	'testray-base',
	function(A) {
		var Lang = A.Lang;

		var LString = Lang.String;

		var LABEL_GREATER_THAN_99 = '> 99';

		var LABEL_LESS_THAN_1 = '< 1';

		var STR_BLANK = '';

		var TPL_DUPLICATE_ERROR = '<div class="duplicate-error">{message}</div>';

		var TPL_LEGEND_ITEM = '<li class="legend-item">' +
				'<div class="legend-item-key">' +
					'<div class="legend-color-block {statusCss}"></div>' +
				'</div>' +
				'<div class="legend-item-value">' +
					'<div class="legend-item-numbers">' +
						'<span class="primary">{percent}</span>' +
						'<span class="secondary">{count}</span>' +
					'</div>' +
					'<span class="legend-item-label">{label}</span>' +
				'</div>' +
			'</li>';

		var Testray = A.Component.create(
			{
				NAME: 'testray-base',

				prototype: {
					addAlert: function(config) {
						var icon = 'info-circle';

						if (config.type == 'success') {
							icon = 'check';
						}
						else if (config.type == 'danger') {
							icon = 'exclamation-full';
						}

						new Liferay.Alert(
							{
								duration: Liferay.Testray.CONSTANTS.ALERT_TRANSITION_DURATION,
								icon: icon,
								message: config.message || STR_BLANK,
								type: config.type || 'info'
							}
						).render(config.containerId);
					},

					addPercentLabel: function(config) {
						var instance = this;

						var percent = 0;

						if (config.total) {
							percent = (config.value / config.total) * 100;
						}

						var percentLabel = instance.getPercentLabel(percent);

						var container = config.container;

						var containerNode = A.one(container);

						if (containerNode) {
							var label = Lang.sub(
								Liferay.Language.get('parenthesis-x'),
								[
									percentLabel
								]
							);

							containerNode.text(label);
						}
					},

					buildBaseURL: function(config) {
						var baseURL = Liferay.ThemeDisplay.getLayoutURL() + '/-/testray/';
						var controller = config.controller || '';

						var requestURL = baseURL + controller;

						var controllerMethod = config.controllerMethod;

						if (controllerMethod) {
							requestURL += '/' + controllerMethod;
						}

						return requestURL;
					},

					buildURLWithParams: function(config) {
						var url = this.buildBaseURL(config);

						var params = config.params;

						if (params) {
							url += '?';

							var paramKeys = Object.keys(params);

							paramKeys.forEach(
								function(key, index) {
									if (index) {
										url += '&';
									}

									url += key + '=' + params[key];
								}
							);
						}

						return url;
					},

					cleanText: function(text) {
						var value = LString.trim(text);

						value = LString.toLowerCase(text);

						return value;
					},

					clearForm: function(formId) {
						var form = A.one('#' + formId);

						if (form) {
							form.all('input, select, text, textarea').each(
								function(item) {
									var type = item.attr('type');

									if (type == 'checkbox') {
										item.set('checked', false);
									}
									else if (type == 'select-multiple' || type == 'select-one') {
										item.set('selectedIndex', -1);
									}
									else if ((type == 'text') || (type == 'textarea')) {
										item.val('');
									}

									if (item.get('disabled')) {
										item.set('disabled', false);
									}
								}
							);
						}
					},

					clearSearchContainer: function(searchContainerId) {
						var searchContainer = Liferay.SearchContainer.get(searchContainerId);

						searchContainer.updateDataStore([]);

						var searchContainerRows = searchContainer._table.all('tbody tr:not(.lfr-template)');

						if (searchContainerRows) {
							searchContainerRows.remove();
						}
					},

					closeWindow: function() {
						var dialog = Liferay.Util.getWindow();

						if (dialog) {
							dialog.hide();
						}
					},

					combineCheckboxValues: function(checkboxFieldsetId, checkboxName, checkboxCssClass, combinedInputId) {
						var checkboxFieldset = A.one('#' + checkboxFieldsetId);

						if (checkboxFieldset) {
							checkboxFieldset.attr('disabled', true);

							var checkboxes = checkboxFieldset.all('.' + checkboxCssClass);

							checkboxes.set('disabled', true);

							var checkboxValueArray = [];

							checkboxes.each(
								function(item, index) {
									if (item.attr('checked')) {
										var regExp = new RegExp(checkboxName + '(\\S+)', 'g');

										var name = item.attr('id');

										var match = regExp.exec(name);

										checkboxValueArray.push(match[1]);
									}
								}
							);

							var combinedInputNode = A.one('#' + combinedInputId);

							if (combinedInputNode) {
								combinedInputNode.val(checkboxValueArray);
							}
						}
					},

					compileRowValues: function(rows) {
						var rowsCompiled = [];

						rows.each(
							function(row) {
								var selectInputs = row.all('select');

								var values = selectInputs.val().join();

								rowsCompiled.push(values);
							}
						);

						return rowsCompiled;
					},

					confirmDelete: function(url, customMessage) {
						var message = customMessage || Liferay.Language.get('are-you-sure-you-want-to-delete-this');

						if (confirm(message)) {
							submitForm(document.hrefFm, url);
						}
					},

					filterList: function(input, itemSelector, itemTextSelector, noResultsMessageSelector) {
						var instance = this;

						var items = A.all(itemSelector);

						var noResults = true;

						items.each(
							function(item) {
								var itemTextNode = item.one(itemTextSelector);

								if (itemTextNode) {
									var term;

									var inputValue = input.value;

									if (inputValue) {
										term = instance.cleanText(inputValue);
									}

									var itemText = itemTextNode.text();

									var text = instance.cleanText(itemText);

									var matches = false;

									if (!term || text.indexOf(term) > -1) {
										matches = true;

										noResults = false;
									}

									item.toggle(matches);
								}
							}
						);

						var noResultsNode = A.one(noResultsMessageSelector);

						if (noResultsNode) {
							noResultsNode.toggle(noResults);
						}
					},

					getPercentLabel: function(percent, label) {
						var percentValue = Math.round(percent) || 0;

						if (percent > 99 && percent < 100) {
							percentValue = LABEL_GREATER_THAN_99;
						}
						else if (percent > 0 && percent < 1) {
							percentValue = LABEL_LESS_THAN_1;
						}

						var percentLabel = Lang.sub(
							Liferay.Language.get('x-percent'),
							[
								percentValue
							]
						);

						if (label) {
							percentLabel = Lang.sub(
								Liferay.Language.get('x-percent-x'),
								[
									percentValue,
									label
								]
							);
						}

						return percentLabel;
					},

					getTableRowCssValue: function(rowNode, cssClass) {
						var value = STR_BLANK;

						if (cssClass && rowNode) {
							var cell = rowNode.one('.' + cssClass);

							if (cell) {
								var classList = cell.getDOM().classList;

								var regEx = new RegExp(cssClass + '-(.*)', 'g');

								A.Array.some(
									classList,
									function(item, index) {
										var match = regEx.exec(item);

										if (match) {
											value = match.pop();
										}

										return match;
									}
								);
							}
						}

						return value;
					},

					isDuplicateInList: function(item, index, list) {
						var listExcludingCurrent = list.filter(
							function(filterItem, filterIndex) {
								return index !== filterIndex;
							}
						);

						var duplicate = false;

						if (listExcludingCurrent.indexOf(item) > -1) {
							duplicate = true;
						}

						return duplicate;
					},

					openWindow: function(url, title, width, height) {
						Liferay.Util.openWindow(
							{
								dialog: {
									height: height,
									width: width || '1500'
								},
								title: title,
								uri: url
							}
						);
					},

					progressBar: function(data) {
						var cssClass = data.cssClass;
						var statusLabel = data.statusLabel;
						var subsetCasesCount = data.subsetCasesCount;
						var totalCasesCount = data.totalCasesCount;

						var percent = 0;

						if (totalCasesCount) {
							percent = (subsetCasesCount / totalCasesCount) * 100;
						}

						var percentLabel = Math.round(percent);

						if (percent > 99 && percent < 100) {
							percentLabel = '> 99';
						}
						else if (percent > 0 && percent < 1) {
							percentLabel = '< 1';
						}

						var progressBar = A.one('#' + data.id + ' .' + cssClass);

						if (progressBar) {
							progressBar.width(percent + '%');

							var percentTitle = Lang.sub(
								Liferay.Language.get('x-percent-x'),
								[
									percentLabel,
									statusLabel
								]
							);

							progressBar.attr('title', percentTitle);
						}

						if (data.showLegend) {
							var legendContainer = A.one('.legend-container');

							if (legendContainer) {
								var countText = Lang.sub(
									Liferay.Language.get('parenthesis-x'),
									[
										subsetCasesCount
									]
								);

								var percentText = Lang.sub(
									Liferay.Language.get('x-percent'),
									[
										percentLabel
									]
								);

								var legendItemHTML = Lang.sub(
									TPL_LEGEND_ITEM,
									{
										count: countText,
										label: statusLabel,
										percent: percentText,
										statusCss: cssClass
									}
								);

								legendContainer.append(legendItemHTML);
							}
						}
					},

					request: function(config) {
						var requestURL = this.buildBaseURL(config);

						var dataType = config.dataType || 'json';
						var method = config.method || 'POST';

						return A.Promise(
							function(resolve, reject) {
								A.io.request(
									requestURL,
									{
										data: config.params,
										dataType: dataType,
										form: config.form,
										method: method,
										on: {
											failure: function(event, id, xhr) {
												var message = Liferay.Language.get('an-unexpected-error-occurred');

												var response = xhr.response;

												if (response) {
													var responseJSON = JSON.parse(xhr.response);

													var responseMessage = responseJSON.message;

													if (responseMessage) {
														message = responseMessage;
													}
												}

												reject(
													{
														message: message
													}
												);
											},
											success: function() {
												var responseData = this.get('responseData');

												if (responseData.status === 200) {
													resolve(responseData);
												}
												else {
													reject(responseData);
												}
											}
										}
									}
								);
							}
						);
					},

					setFormInput: function(config) {
						var form = Liferay.Form.get(config.formName);

						if (form) {
							var formNode = form.formNode;

							var inputNode = formNode.one('#' + config.inputId);

							if (inputNode) {
								inputNode.val(config.val);
							}
						}
					},

					setWindowTitle: function(title) {
						var dialog = Liferay.Util.getWindow();

						if (dialog) {
							dialog.titleNode.text(title);
						}
					},

					validateUniqueFormRow: function(container, row) {
						var instance = this;

						var containerNode = A.one(container);

						var formRowsUnique = true;

						if (containerNode) {
							var rows = A.all(row);

							var rowsCompiled = instance.compileRowValues(rows);

							rowsCompiled.forEach(
								function(item, index) {
									var duplicate = instance.isDuplicateInList(item, index, rowsCompiled);

									var row = rows.item(index);

									row.toggleClass('has-duplicate', duplicate);

									var duplicateErrorNode = row.one('.duplicate-error');

									if (duplicate) {
										formRowsUnique = false;

										if (!duplicateErrorNode) {
											row.append(
												Lang.sub(
													TPL_DUPLICATE_ERROR,
													{
														message: Liferay.Language.get('runs-must-be-unique')
													}
												)
											);
										}
									}
									else if (duplicateErrorNode) {
										duplicateErrorNode.remove();
									}
								}
							);
						}

						return formRowsUnique;
					}
				}
			}
		);

		Liferay.Testray = new Testray();

		Liferay.Testray.CONSTANTS = {
			ALERT_HIDE_DELAY: 5000,
			ALERT_TRANSITION_DURATION: 500
		};

		Liferay.Testray.TEMPLATES = {
			ALERT_LINK: '{message}<a class="alert-link" href="{linkURL}">{linkText}</a>'
		};
	},
	'',
	{
		requires: ['aui-base', 'aui-io-request', 'liferay-alert', 'liferay-form', 'promise']
	}
);