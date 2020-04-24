AUI.add(
	'testray-loading-bar',
	function(A) {
		var Lang = A.Lang;

		var Testray = Liferay.Testray;

		var CSS_LOADING_BAR_CONTAINER = 'testray-loading-bar-container';

		var NUMBER_DEBOUNCE = 1000;

		var NUMBER_INTERVAL = 2000;

		var NUMBER_TIMEOUT_RELOAD = 2000;

		var TPL_LOADING_BAR = '<div class="testray-loading-bar"></div>';

		var LoadingBar = A.Component.create(
			{
				ATTRS: {
					container: {
						setter: A.one,
						validator: Lang.isString,
						value: null
					},

					requestConfig: {
						validator: Lang.isObject,
						value: {}
					},

					totalName: {
						validator: Lang.isString,
						value: null
					},

					valueName: {
						validator: Lang.isString,
						value: null
					}
				},

				NAME: 'avatar',

				prototype: {
					renderUI: function() {
						var instance = this;

						var container = instance.get('container');

						container.addClass(CSS_LOADING_BAR_CONTAINER);

						var loadingBar = A.Node.create(TPL_LOADING_BAR);

						container.append(loadingBar);

						instance._loadingBar = loadingBar;

						instance._interval = setInterval(
							A.debounce(
								'_getStatus',
								NUMBER_DEBOUNCE,
								instance
							),
							NUMBER_INTERVAL
						);
					},

					_getStatus: function() {
						var instance = this;

						var requestConfig = instance.get('requestConfig');

						Testray.request(requestConfig).then(
							function(response) {
								var data = response.data;

								var totalName = instance.get('totalName');
								var valueName = instance.get('valueName');

								var total = data[totalName];
								var value = data[valueName];

								instance._setWidth(
									{
										total: total,
										value: value
									}
								);

								if (total === value) {
									instance._loadComplete();
								}
							}
						);
					},

					_loadComplete: function() {
						var instance = this;

						clearInterval(instance._interval);

						setTimeout(
							function() {
								window.location.reload();
							},
							NUMBER_TIMEOUT_RELOAD
						);
					},

					_setWidth: function(data) {
						var instance = this;

						var total = data.total;
						var value = data.value;

						var percentage = value / total * 100 || 100;

						instance._loadingBar.setStyle('width', percentage + '%');
					}
				}
			}
		);

		Testray.LoadingBar = LoadingBar;
	},
	'',
	{
		requires: ['testray-base']
	}
);