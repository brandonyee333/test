AUI().add('liferay-marketplace-app-list-controller',
	function(A) {
		var MarketplaceAppListController = function(options) {
			var instance = this;

			instance._agreement = options.agreement;
			instance._agreementURL = options.agreementURL;
			instance._appList = options.appList;
			instance._apps = {};
			instance._errorMessageContainer = options.errorMessageContainer;
			instance._id = A.guid();
			instance._getAppURL = options.getAppURL;
			instance._getInstalledAppsURL = options.getInstalledAppsURL;
			instance._namespace = options.namespace;
			instance._restartRequiredMessage = options.restartRequiredMessage;
			instance._uninstallAppURL = options.uninstallAppURL;
			instance._updateAllAppsNode = instance._appList.one('.update-all-block');
			instance._updateAllAppsURL = options.updateAllAppsURL;
			instance._updateAppURL = options.updateAppURL;

			instance._initApps();

			instance._initEvents();

			instance._initControls();
		}

		MarketplaceAppListController.prototype = {

			hasApp: function(appEntryId) {
				var instance = this;

				if (instance._apps[appEntryId]) {
					return true;
				}

				return false;
			},

			uninstallAppEntry: function(appEntryId) {
				var instance = this;

				var app = instance._apps[appEntryId];

				app.awaitingStatus = true;

				instance.updateAppControlButtons(app);

				var data = {
					appId: app.appEntryId
				};

				A.io.request(
					instance._uninstallAppURL,
					{
						data: instance._namespaceKeys(data),
						dataType: 'JSON',
						method: 'POST',
						on: {
							success: function(event) {
								instance._processResponse(this.get('responseData'));
							}
						}
					}
				);
			},

			updateAllApps: function() {
				var instance = this;

				instance._doUpdateAllApps();
			},

			updateAppEntry: function(appEntryId, orderUuid, productEntryName) {
				var instance = this;

				var app = instance._apps[appEntryId];

				app.node.all('.btn:not(.always-show)').hide();
				app.node.one('.btn[data-action=pleaseWait]').show();

				app.awaitingStatus = true;

				var data = {
					appPackageId: app.appPackageId
				};

				if (orderUuid) {
					data.orderUuid = orderUuid;
					data.productEntryName = productEntryName;
				}

				A.io.request(
					instance._updateAppURL,
					{
						data: instance._namespaceKeys(data),
						dataType: 'JSON',
						method: 'POST',
						on: {
							success: function(event) {
								instance._processResponse(this.get('responseData'));
							}
						}
					}
				);
			},

			updateAppControlButtons: function(app) {
				var instance = this;

				if (app == null) {
					return;
				}

				if (!app.compatible) {
					return;
				}

				var node = app.node;

				node.all('.btn').hide();

				if (app.awaitingStatus) {
					node.one('.btn[data-action=pleaseWait]').show();

					return;
				}
				else if (app.installed) {
					if (!app.paclEnabled) {
						var data = {
							appEntryId: app.appEntryId,
							version: app.version
						};

						A.io.request(
							instance._getAppURL,
							{
								data: instance._namespaceKeys(data),
								dataType: 'JSON',
								method: 'POST',
								on: {
									success: function(event, id, obj) {
										if (this.get('responseData').paclEnabled) {
											node.siblings('.security-disabled').show();
										}
									}
								}
							}
						);
					}

					if (app.version != app.userVersion) {
						node.one('.btn[data-action=updateApp]').show();
					}
					else if (!app.remote) {
						node.one('.btn.up-to-date').show();
					}

					if (app.remote) {
						var uninstallButton = node.one('.btn[data-action=uninstallApp]');

						if (uninstallButton) {
							uninstallButton.show();
						}
					}
				}
				else {
					node.one('.btn[data-action=installApp]').show();
				}

				node.one('.btn[data-action=pleaseWait]').hide();
			},

			_doUpdateAllApps : function() {
				var instance = this;

				var appPackageIds = [];

				for (var key in instance._apps) {
					var app = instance._apps[key];

					appPackageIds.push(app.appPackageId);
				}

				instance._updateAllAppsNode.one('.btn.loading').show();
				instance._updateAllAppsNode.one('.btn.update-all').hide();

				var data = {
					appPackageIds: appPackageIds.join()
				};

				A.io.request(
					instance._updateAllAppsURL,
					{
						data: instance._namespaceKeys(data),
						dataType: 'JSON',
						method: 'POST',
						on: {
							success: function(event) {
								var response = this.get('responseData');

								instance._updateAllAppsNode.one('.btn.loading').hide();
								instance._updateAllAppsNode.one('.btn.update-all').show();

								if (response.message == "failed") {
									instance._toggleErrorMessage(true);
								}

								for (var updatedAppId in response.updatedApps) {
									var app = instance._apps[response.updatedApps[updatedAppId].appId];

									if (app) {
										app.node.ancestor('.results-row').remove();

										instance._apps[app.appEntryId] = null;

										if (instance._isAppsListEmpty()) {
											instance._appList.hide();
										}
									}
								}

								A.fire(
									'appListController:updateApp',
									{
										appListControllerId: instance._id,
										apps: response.updatedApps
									}
								);

								instance._updateAllAppControlButtons();
							}
						}
					}
				);
			},

			_initApps : function() {
				var instance = this;

				instance._appList.all('.app-entry-action').each(
					function(item, index, collection) {
						var app = {
							appEntryId: item.getAttribute('data-appEntryId'),
							appPackageId: item.getAttribute('data-appPackageId'),
							awaitingStatus: true,
							compatible: (item.getAttribute('data-compatible') === 'true'),
							downloaded: false,
							installed: false,
							node: item,
							paclEnabled: (item.getAttribute('data-paclEnabled') === 'true'),
							portalRestartRequired: (item.getAttribute('data-portalRestartRequired') === 'true'),
							prepackaged: (item.getAttribute('data-prepackaged') === 'true'),
							purchased: (item.getAttribute('data-purchased') === 'true'),
							remote: false,
							userVersion: '',
							version: item.getAttribute('data-version')
						};

						instance._apps[app.appEntryId] = app;

						instance.updateAppControlButtons(app);
					}
				);
			},

			_initControls: function() {
				var instance = this;

				A.io.request(
					instance._getInstalledAppsURL,
					{
						dataType: 'JSON',
						method: 'POST',
						on: {
							success: function(event) {
								var response = this.get('responseData');

								var installedApps = response.apps;

								for (var i = 0; i < installedApps.length; i++) {
									var installedApp = installedApps[i];

									var app = instance._apps[installedApp.appId];

									if (app) {
										installedApp = instance._updateApp(installedApp);
									}
								}

								instance._updateAllAppsAwaitingStatus(false);

								instance._updateAllAppControlButtons();

								if (instance._updateAllAppsNode != null) {
									instance._updateAllAppsNode.show();

									instance._updateAllAppsNode.one('.btn.update-all').show();

									instance._updateAllAppsNode.one('.btn[data-action=pleaseWait]').hide();
								}

								Liferay.MarketplaceMessenger.postMessage(
									{
										cmd: 'resize',
										height: A.getBody().height() + 50
									}
								);
							}
						}
					}
				);
			},

			_initEvents: function() {
				var instance = this;

				var appList = instance._appList;

				if (!appList) {
					return;
				}

				A.publish('appListController:updateApp', {emitFacade: true});

				A.on('appListController:updateApp', instance._onUpdateApp, instance);

				appList.delegate('click', instance._onClickAppButton, 'a.btn:not(.disabled):not(.update-all)', instance);

				appList.delegate('click', instance._onClickAssetButton, '.asset-information a', instance);

				if (instance._updateAllAppsNode) {
					instance._updateAllAppsNode.one('.update-all').on('click', instance._updateAllApps, instance);
				}
			},

			_isAppsListEmpty: function() {
				var instance = this;

				for (var key in instance._apps) {
					if (instance._apps[key] != null) {
						return false;
					}
				}

				return true;
			},

			_namespaceKeys: function(object) {
				var instance = this;

				var returnObject = {};

				var keys = A.Object.keys(object);

				A.Array.each(
					keys,
					function(key) {
						returnObject[instance._namespace + key] = object[key];
					}
				);

				return returnObject;
			},

			_onUpdateApp: function(event) {
				var instance = this;

				if (!event.appListControllerId || (event.appListControllerId == instance._id)) {
					return;
				}

				for (var key in event.apps) {
					var updatedApp = event.apps[key];

					var app = instance._apps[updatedApp.appId];

					if ((app == null) || ((app.userVersion == updatedApp.version) && (updatedApp.cmd == 'updatedApps'))) {
						continue;
					}

					if (instance._updateAllAppsNode != null) {
						app.node.ancestor('.results-row').remove();

						instance._apps[updatedApp.appId] = null;

						if (instance._isAppsListEmpty()) {
							instance._appList.hide();
						}
					}
					else {
						instance._updateApp(updatedApp);

						instance.updateAppControlButtons(app);
					}
				}
			},

			_onClickAppButton: function(event) {
				var instance = this;

				var button = event.currentTarget;

				var cmd = button.getAttribute('data-action');

				if (!cmd || button.hasAttribute('href')) {
					return;
				}

				event.preventDefault();

				var appEntryId = button.ancestor('.app-entry-action').getAttribute('data-appEntryId');

				if ((cmd === 'installApp') || (cmd === 'updateApp')) {
					var app = instance._apps[appEntryId];

					if (app.portalRestartRequired) {
						if (!confirm(instance._restartRequiredMessage)) {
							return;
						}
					}

					if (!app.purchased) {
						var width = 735;

						window.popup = new A.Dialog(
							{
								destroyOnClose: true,
								draggable: true,
								modal: true,
								resizable: false,
								stack: true,
								title: instance._agreement,
								width: width,
								x: (document.documentElement.clientWidth / 2) - (width / 2),
								y: 300
							}
						).plug(
							A.Plugin.IO,
							{
								uri: instance._agreementURL,
								data: instance._namespaceKeys({appEntryIds: [appEntryId]})
							}
						).render();

						return;
					}

					instance.updateAppEntry(appEntryId);
				}
				else if (cmd === 'uninstallApp') {
					instance.uninstallAppEntry(appEntryId);
				}
			},

			_onClickAssetButton: function(event) {
				event.preventDefault();

				var asset = event.currentTarget.ancestor('.asset-information');

				Liferay.MarketplaceMessenger.postMessage(
					{
						appEntryId: asset.getAttribute('data-appId'),
						cmd: 'goto',
						panel: 'store'
					}
				);
			},

			_processResponse: function(response, supressErrorMessage) {
				var instance = this;

				var failed = false;

				if (response.hasOwnProperty("cmd")) {
					if (response.cmd == "updateApp") {
						var app = instance._apps[response.appId];

						app.awaitingStatus = false;

						if (((response.appId == null) || !response.downloaded || !response.installed)) {
							instance.updateAppControlButtons(app);

							failed = true;
						}
					}
				}
				else {
					failed = true;
				}

				if (!failed) {
					var app = instance._updateApp(response);

					app.awaitingStatus = false;

					var apps = {};

					apps[app.appEntryId] = response;

					A.fire(
						'appListController:updateApp',
						{
							appListControllerId: instance._id,
							apps: apps
						}
					);

					instance.updateAppControlButtons(app);

					if (instance._updateAllAppsNode != null) {
						app.node.ancestor('.results-row').remove();

						instance._apps[app.appEntryId] = null;

						if (instance._isAppsListEmpty()) {
							instance._appList.hide();
						}
					}
				}

				if (!supressErrorMessage) {
					instance._toggleErrorMessage(failed);
				}
			},

			_toggleErrorMessage: function(show) {
				var instance = this;

				if (typeof(show) == "undefined") {
					if (instance._errorMessageContainer.hasClass("aui-helper-hidden")) {
						show = true;
					}
					else {
						show = false;
					}
				}

				if (show) {
					instance._errorMessageContainer.show();
				}
				else {
					instance._errorMessageContainer.hide();
				}
			},

			_updateAllAppControlButtons: function() {
				var instance = this;

				for (var key in instance._apps) {
					instance.updateAppControlButtons(instance._apps[key]);
				}
			},

			_updateAllApps: function() {
				var instance = this;
				var notPurchasedApps = [];
				var confirmationReceived = false;

				for (var key in instance._apps) {
					var app = instance._apps[key];

					if (app.portalRestartRequired && !confirmationReceived) {
						if (!confirm(instance._restartRequiredMessage)) {
							return;
						}
						else {
							confirmationReceived = true;
						}
					}

					if (!app.purchased) {
						notPurchasedApps.push(app.appEntryId);
					}
				}

				if (notPurchasedApps.length > 0) {
					var width = 735;

					window.popup = new A.Dialog(
						{
							destroyOnClose: true,
							draggable: true,
							modal: true,
							resizable: false,
							stack: true,
							title: instance._agreement,
							width: width,
							x: (document.documentElement.clientWidth / 2) - (width / 2),
							y: 300
						}
					).plug(
						A.Plugin.IO,
						{
							uri: instance._agreementURL,
							data: instance._namespaceKeys({appEntryIds: notPurchasedApps.join()})
						}
					).render();

					return;
				}

				instance._doUpdateAllApps();
			},

			_updateAllAppsAwaitingStatus: function(waiting) {
				var instance = this;

				for (var key in instance._apps) {
					if (instance._apps[key] != null) {
						instance._apps[key].awaitingStatus = waiting;
					}
				}
			},

			_updateApp: function(response) {
				var instance = this;

				var app = instance._apps[response.appId];

				app.downloaded = response.downloaded;
				app.installed = response.installed;

				if (response.version) {
					app.remote = true;
				}

				app.userVersion = response.version;

				return app;
			},

			_agreement: null,
			_agreementURL: null,
			_appList: null,
			_apps: {},
			_compatibility: 0,
			_developer: false,
			_errorMessageContainer: null,
			_id: null,
			_getAppURL: null,
			_getInstalledAppsURL: null,
			_getPrepackagedAppsURL: null,
			_installAppURL: null,
			_namespace: null,
			_prepackagedApps: false,
			_purchasedAppsTableNode: null,
			_restartRequiredMessage: null,
			_serveAppURL: null,
			_uninstallAppURL: null,
			_updateAllAppsNode: null,
			_updateAllAppsURL: null,
			_updateAppURL: null
		};

		Liferay.MarketplaceAppListController = MarketplaceAppListController;
	},
	'',
	{
		requires: ['aui-base', 'aui-dialog', 'aui-io', 'json-stringify', 'liferay-marketplace-messenger', 'selector-css3']
	}
);