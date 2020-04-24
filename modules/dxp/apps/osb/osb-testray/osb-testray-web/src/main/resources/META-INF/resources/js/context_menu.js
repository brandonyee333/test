AUI.add(
	'testray-context-menu',
	function(A) {
		var Lang = A.Lang;

		var Testray = Liferay.Testray;

		var CSS_ICON_REMOVE_SIGN = 'icon-remove-sign';

		var CURRENT_USER_ID = themeDisplay.getUserId();

		var MODAL_HEIGHT_DEFAULT = '';

		var MODAL_WIDTH_DEFAULT = 1000;

		var PADDING_WINDOW = 30;

		var PORTLET_NAME = 'testray';

		var STR_ABANDONED = 'abandoned';

		var STR_BLANK = '';

		var STR_COMPLETE = 'complete';

		var STR_IN_ANALYSIS = 'in-analysis';

		var STR_OPEN = 'open';

		var STR_SUBTASKS = 'subtasks';

		var STR_TASKS = 'tasks';

		var STR_UPDATE_USER = 'updateUser';

		var TPL_CONTEXT_MENU = '<ul class="testray-menu testray-menu-context">{items}</ul>';

		var TPL_CONTEXT_MENU_ITEM = '<li class="testray-menu-item"><a href="{url}">{icon}{label}</a></li>';

		var TPL_CONTEXT_MENU_ITEM_ACTION = '<li class="testray-menu-item"><a href="javascript:;" data-action="{menuAction}" data-href="{url}" {attributes}">{icon}{label}</a></li>';

		var TPL_ICON = '<i class="icon {iconCssClass}"></i>';

		var TPL_DELETE_ATTRIBUTES = 'data-delete-message="{message}"';

		var TPL_MODAL_ATTRIBUTES = 'data-modal-height="{height}" data-modal-title="{title}" data-modal-width="{width}"';

		var TPL_URL_DEFAULT = '/{id}/{action}';

		var ContextMenu = A.Component.create(
			{
				ATTRS: {
					containerId: {
						value: STR_BLANK
					},

					menu: {
						value: []
					},

					redirect: {
						value: Liferay.currentURL
					},

					trigger: {
						validator: Lang.isString,
						value: 'tbody tr'
					},

					width: {
						value: 350
					}
				},

				NAME: 'context-menu',

				prototype: {
					initializer: function() {
						var instance = this;

						var containerId = instance.get('containerId');

						instance._searchContainer = Liferay.SearchContainer.get(containerId);
					},

					bindUI: function() {
						var instance = this;

						var containerId = instance.get('containerId');
						var trigger = instance.get('trigger');

						var containerNode = A.one('#' + containerId);
						var triggerNode = A.one(trigger);

						if (containerNode && triggerNode) {
							containerNode.delegate(
								'contextmenu',
								A.bind('_onContextMenu', instance),
								trigger
							);
						}
					},

					_bindActionHandlers: function(contextMenu) {
						var instance = this;

						var boundingBox = contextMenu.get('boundingBox');

						if (boundingBox) {
							boundingBox.delegate(
								'click',
								A.bind('_handleDelete', instance),
								'a[data-action="delete"]'
							);

							boundingBox.delegate(
								'click',
								A.bind('_handleModal', instance),
								'a[data-action="modal"]'
							);
						}
					},

					_buildContextMenuHTML: function(rowSelectedId) {
						var instance = this;

						var menu = instance.get('menu');

						var itemsList = [];

						for (var i = 0; i < menu.length; i++) {
							var item = menu[i];

							if (instance._isItemVisible(item)) {
								var itemHTML = instance._buildItemHTML(item, rowSelectedId);

								itemsList.push(itemHTML);
							}
						}

						var contextMenuHTML = STR_BLANK;

						if (itemsList.length) {
							contextMenuHTML = Lang.sub(
								TPL_CONTEXT_MENU,
								{
									items: itemsList.join(STR_BLANK)
								}
							);
						}

						return contextMenuHTML;
					},

					_buildItemHTML: function(item, rowSelectedId) {
						var instance = this;

						var action = item.action;
						var controller = item.controller;
						var iconCssClass = item.iconCssClass;
						var label = item.label;

						if (action == STR_UPDATE_USER && controller == 'case_results' && instance._isCurrentUser()) {
							iconCssClass = CSS_ICON_REMOVE_SIGN;
							label = Liferay.Language.get('unassign-myself');

							var parameters = item.parameters;

							if (parameters) {
								parameters.assignedUserId = 0;
							}
						}

						var iconHTML = Lang.sub(
							TPL_ICON,
							{
								iconCssClass: iconCssClass
							}
						);

						var url = instance._buildItemURL(item, rowSelectedId);

						var args = {
							icon: iconHTML,
							label: label,
							url: url
						};

						var template = TPL_CONTEXT_MENU_ITEM;

						var menuAction = item.menuAction;

						if (menuAction) {
							args.menuAction = menuAction;

							template = TPL_CONTEXT_MENU_ITEM_ACTION;

							var attributes = STR_BLANK;

							if (menuAction == 'delete' && item.deleteConfig) {
								attributes = Lang.sub(
									TPL_DELETE_ATTRIBUTES,
									{
										message: item.deleteConfig.message || STR_BLANK
									}
								);
							}

							if (menuAction == 'modal') {
								var modalConfig = item.modalConfig;

								var height = modalConfig.height || MODAL_HEIGHT_DEFAULT;
								var width = modalConfig.width || MODAL_WIDTH_DEFAULT;

								attributes = Lang.sub(
									TPL_MODAL_ATTRIBUTES,
									{
										height: height,
										title: modalConfig.title,
										width: width
									}
								);
							}

							args.attributes = attributes;
						}

						return Lang.sub(template, args);
					},

					_buildItemURL: function(item, rowSelectedId) {
						var instance = this;

						var urlTemplate = item.urlTemplate || TPL_URL_DEFAULT;

						var url = Lang.sub(
							'{baseURL}/-/{portletName}/{controller}' + urlTemplate,
							{
								action: item.action,
								baseURL: Liferay.ThemeDisplay.getLayoutURL(),
								controller: item.controller,
								id: rowSelectedId,
								portletName: PORTLET_NAME
							}
						);

						var paramsToAdd = {};

						if (item.method == 'POST') {
							paramsToAdd = A.merge(
								{
									p_auth: Liferay.authToken,
									p_p_lifecycle: 1,
									redirect: instance.get('redirect')
								},
								paramsToAdd
							);
						}

						var userFilterPreferencesCell = instance._rowSelectedNode.one('.user-filter-preferences');

						if (userFilterPreferencesCell) {
							var userFilterPreferencesText = userFilterPreferencesCell.text();

							var userFilterPreferences = JSON.parse(userFilterPreferencesText.trim());

							if (userFilterPreferences) {
								paramsToAdd = A.merge(userFilterPreferences, paramsToAdd);
							}
						}

						if (item.parameters) {
							paramsToAdd = A.merge(item.parameters, paramsToAdd);
						}

						var parametersCss = item.parametersCss;

						if (parametersCss) {
							parametersCss.forEach(
								function(parameter) {
									var parameterValue = Testray.getTableRowCssValue(instance._rowSelectedNode, parameter);

									paramsToAdd[parameter] = parameterValue;
								}
							);
						}

						if (item.action == 'updatePromoted') {
							var promoted = instance._isPromoted(item);

							paramsToAdd = A.merge(
								{
									promoted: (!promoted).toString()
								},
								paramsToAdd
							);
						}

						if (Liferay.ThemeDisplay.isStatePopUp() || item.menuAction == 'modal') {
							paramsToAdd = A.merge(
								{
									p_p_state: 'pop_up'
								},
								paramsToAdd
							);
						}

						var urlWithParams = Liferay.Util.addParams(paramsToAdd, url);

						if (urlWithParams) {
							url = urlWithParams;
						}

						return url;
					},

					_getContextMenu: function() {
						var instance = this;

						var contextMenu = instance._contextMenu;

						if (!contextMenu) {
							contextMenu = new A.Overlay(
								{
									visible: false,
									width: instance.get('width')
								}
							);

							contextMenu.render('body');

							instance._bindActionHandlers(contextMenu);

							instance._contextMenu = contextMenu;
						}

						return contextMenu;
					},

					_getRowSelectedId: function(rowSelectedNode) {
						var instance = this;

						var rowSelectedIndex = rowSelectedNode.get('rowIndex');

						var index = rowSelectedIndex - 1;

						return instance._searchContainer._ids[index];
					},

					_handleDelete: function(event) {
						var currentTarget = event.currentTarget;

						var href = currentTarget.getAttribute('data-href');
						var message = currentTarget.attr('data-delete-message');

						Testray.confirmDelete(href, message);
					},

					_handleModal: function(event) {
						var currentTarget = event.currentTarget;

						var height = currentTarget.attr('data-modal-height');
						var href = currentTarget.attr('data-href');
						var title = currentTarget.attr('data-modal-title');
						var width = currentTarget.attr('data-modal-width');

						Testray.openWindow(href, title, width, height);
					},

					_isCurrentUser: function() {
						var instance = this;

						var assigneeValue = Testray.getTableRowCssValue(instance._rowSelectedNode, 'assignee');

						return assigneeValue === CURRENT_USER_ID;
					},

					_isItemSubtaskActionVisible: function(item) {
						var instance = this;

						var archived = Testray.getTableRowCssValue(instance._rowSelectedNode, 'archived-status');

						var visible = false;

						if (archived !== 'true') {
							var currentUser = instance._isCurrentUser();

							var currentSubtaskStatus = Testray.getTableRowCssValue(instance._rowSelectedNode, 'subtask-status');

							var currentlyComplete = currentSubtaskStatus == STR_COMPLETE;
							var currentlyInAnalysis = currentSubtaskStatus == STR_IN_ANALYSIS;
							var currentlyOpen = currentSubtaskStatus == STR_OPEN;

							if (item.name == 'subtaskAssign' && currentlyInAnalysis) {
								visible = true;
							}
							else if (item.name == 'subtaskAssignAndBeginAnalysis' && currentlyOpen) {
								visible = true;
							}
							else if (item.name == 'subtaskAssignAndReanalyze' && currentlyComplete) {
								visible = true;
							}
							else if (item.name == 'subtaskAssignToMe' && !currentUser && currentlyInAnalysis) {
								visible = true;
							}
							else if (item.name == 'subtaskAssignToMeAndBeginAnalysis' && currentlyOpen) {
								visible = true;
							}
							else if (item.name == 'subtaskAssignToMeAndReanalyze' && currentlyComplete) {
								visible = true;
							}
							else if (item.name == 'subtaskComplete' && currentUser && currentlyInAnalysis) {
								visible = true;
							}
							else if (item.name == 'subtaskReturnToOpen' && currentlyComplete) {
								visible = true;
							}
							else if (item.name == 'view' || (item.name == 'viewAssociatedBuild')) {
								visible = true;
							}
						}

						return visible;
					},

					_isItemTaskActionVisible: function(item) {
						var instance = this;

						var visible = false;

						var currentTaskStatus = Testray.getTableRowCssValue(instance._rowSelectedNode, 'task-status');

						var currentlyAbandoned = currentTaskStatus == STR_ABANDONED;
						var currentlyComplete = currentTaskStatus == STR_COMPLETE;
						var currentlyInAnalysis = currentTaskStatus == STR_IN_ANALYSIS;
						var currentlyOpen = currentTaskStatus == STR_BLANK;

						var incompleteScoreString = Testray.getTableRowCssValue(instance._rowSelectedNode, 'incomplete-score');

						var incompleteScoreNumber = Number(incompleteScoreString);

						var archived = Testray.getTableRowCssValue(instance._rowSelectedNode, 'archived-status');

						if (archived !== 'true') {
							if (item.name == 'taskAbandon' && currentlyInAnalysis && incompleteScoreNumber) {
								visible = true;
							}
							else if (item.name == 'taskComplete' && currentlyInAnalysis && (incompleteScoreNumber === 0)) {
								visible = true;
							}
							else if (item.name == 'taskCreate' && currentlyOpen) {
								visible = true;
							}
							else if (item.name == 'taskReopen' && (currentlyAbandoned || currentlyComplete)) {
								visible = true;
							}
							else if (item.name == 'taskDelete' && (currentlyAbandoned || currentlyComplete)) {
								visible = true;
							}
						}

						return visible;
					},

					_isItemVisible: function(item) {
						var instance = this;

						var controller = item.controller;
						var visible = item.visible;
						var conditionalVisibility = item.conditionalVisibility

						if (visible || conditionalVisibility) {
							if (controller == STR_SUBTASKS || item.name == 'subtaskAssign' || item.name == 'subtaskAssignAndBeginAnalysis' || item.name == 'subtaskAssignAndReanalyze') {
								visible = instance._isItemSubtaskActionVisible(item);
							}
							else if (controller == STR_TASKS) {
								visible = instance._isItemTaskActionVisible(item);
							}
						}
						else if (Lang.isUndefined(visible) && Lang.isUndefined(conditionalVisibility)) {
							visible = true;
						}

						return visible;
					},

					_isMenuVisible: function() {
						var instance = this;

						var menu = instance.get('menu');

						var menuVisible = A.some(
							menu,
							function(item, index) {
								var visible = item.visible;

								return visible || Lang.isUndefined(visible);
							}
						);

						return menuVisible;
					},

					_isPromoted: function(item) {
						var instance = this;

						var rowSelectedNode = instance._rowSelectedNode;

						var rowCellNodes = rowSelectedNode.all('td');

						var promoted = false;

						rowCellNodes.each(
							function(item, index) {
								if (item.hasClass('promoted')) {
									promoted = true;
								}
							}
						);

						return promoted;
					},

					_onClickOutside: function(event, rowSelectedNode) {
						var instance = this;

						instance._contextMenu.hide();

						var hasSelectedClass = rowSelectedNode.hasClass('selected');

						if (hasSelectedClass) {
							rowSelectedNode.removeClass('selected');
						}
					},

					_onContextMenu: function(event) {
						var instance = this;

						if (!event.shiftKey && instance._isMenuVisible()) {
							instance._rowSelectedNode = event.currentTarget;

							var rowSelectedNode = instance._rowSelectedNode;

							var rowSelectedId = instance._getRowSelectedId(rowSelectedNode);

							var contextMenuBodyHTML = instance._buildContextMenuHTML(rowSelectedId);

							if (contextMenuBodyHTML) {
								event.preventDefault();

								var contextMenu = instance._getContextMenu();

								contextMenu.set('bodyContent', contextMenuBodyHTML);

								instance._setContextMenuPosition(contextMenu, event);

								instance._removePreviousRowStyle();
								instance._setSelectedRowStyle(rowSelectedNode);

								contextMenu.show();

								var boundingBox = contextMenu.get('boundingBox');

								if (instance._clickOutsideHandle) {
									instance._clickOutsideHandle.detach();
								}

								instance._clickOutsideHandle = boundingBox.once(
									['click', 'mousedownoutside'],
									A.rbind('_onClickOutside', instance, rowSelectedNode)
								);

								instance._rowSelectedNodePrevious = rowSelectedNode;
							}
						}
					},

					_removePreviousRowStyle: function() {
						var instance = this;

						var rowSelectedNodePrevious = instance._rowSelectedNodePrevious;

						if (rowSelectedNodePrevious) {
							var hasSelectedClassPrevNode = rowSelectedNodePrevious.hasClass('selected');

							if (hasSelectedClassPrevNode) {
								rowSelectedNodePrevious.removeClass('selected');
							}
						}
					},

					_setContextMenuPosition: function(contextMenu, event) {
						var instance = this;

						var positionX = event.pageX;
						var positionY = event.pageY;

						var bodyNode = contextMenu.bodyNode;

						var contextMenuContainer = bodyNode.get('firstChild');

						if (contextMenuContainer) {
							var contextMenuContainerWidth = contextMenuContainer.width();

							var containerRightEdgePosition = event.pageX + contextMenuContainerWidth + PADDING_WINDOW;

							var windowWidth = window.innerWidth;

							if (containerRightEdgePosition > windowWidth) {
								positionX -= contextMenuContainerWidth;
							}

							var contextMenuContainerHeight = contextMenuContainer.height();

							var containerBottomEdgePosition = event.pageY + contextMenuContainerHeight + PADDING_WINDOW;

							var windowHeight = window.innerHeight;

							if (containerBottomEdgePosition > windowHeight) {
								positionY -= contextMenuContainerHeight;
							}
						}

						contextMenu.set(
							'xy',
							[positionX, positionY]
						);
					},

					_setSelectedRowStyle: function(rowSelectedNode) {
						var hasSelectedClassRowNode = rowSelectedNode.hasClass('selected');

						rowSelectedNode.toggleClass('selected', !hasSelectedClassRowNode);
					}
				}
			}
		);

		Testray.ContextMenu = ContextMenu;
	},
	'',
	{
		requires: ['aui-component', 'event-outside', 'liferay-search-container', 'overlay', 'testray-base']
	}
);