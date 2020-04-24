AUI.add(
	'testray-graph',
	function(A) {
		var Lang = A.Lang;

		var Testray = Liferay.Testray;

		var AXIS_X_POSITION = 'outer-center';

		var AXIS_Y_POSITION = 'outer-middle';

		var CSS_CLASS_LEGEND_CONTAINER = 'legend-container';

		var CSS_CLASS_LEGEND_ITEM = 'legend-item';

		var DATA_STATUS_ID_LIST = [
			'metrics.passed',
			'metrics.failed',
			'metrics.blocked',
			'metrics.test-fix',
			'metrics.incomplete'
		];

		var DATA_COLORS = {
			'metrics.blocked': '#F8D72E',
			'metrics.failed': '#E73A45',
			'metrics.incomplete': '#E3E9EE',
			'metrics.passed': '#3CD587',
			'metrics.test-fix': '#59BBFC'
		};

		var DATA_STATUS_LABELS = {
			'metrics.blocked': Liferay.Language.get('blocked').toUpperCase(),
			'metrics.failed': Liferay.Language.get('failed').toUpperCase(),
			'metrics.incomplete': Liferay.Language.get('incomplete').toUpperCase(),
			'metrics.passed': Liferay.Language.get('passed').toUpperCase(),
			'metrics.test-fix': Liferay.Language.get('test-fix').toUpperCase()
		};

		var DONUT_BAR_WIDTH = 15;

		var GRAPH_HEIGHT = 300;

		var GRAPH_PADDING_BOTTOM = 5;

		var GRAPH_PADDING_TOP = 20;

		var LEGEND_TILE_SIZE = 12;

		var STR_BLANK = '';

		var TPL_TOOLTIP = '<div class="c3-tooltip">' +
				'<h5 class="c3-tooltip-title">{title}</h5>' +
				'<ul class="c3-tooltip-data">{data}</ul>' +
			'</div>';

		var TPL_TOOLTIP_DATA_ITEM = '<li class="c3-tooltip-item">' +
				'<span class="c3-tooltip-color" style="background-color: {color};"></span>' +
				'<span class="item-value">{value}</span> ' +
				'<span class="item-status">{status}</span>' +
			'</li>';

		var TPL_LEGEND_ITEM = '<div class="legend-item-key">' +
				'<div class="legend-color-block {status}"></div>' +
			'</div>' +
			'<div class="legend-item-value">' +
				'<div class="legend-item-numbers">' +
					'<span class="primary">{value}</span>' +
					'<span class="secondary">({percentLabel})</span>' +
				'</div>' +
				'<div class="legend-item-label">{statusLabel}</div>' +
			'</div>';

		var TPL_TOOLTIP_DONUT = '<div class="c3-tooltip legend-container">' +
				'<div class="legend-item">' +
					TPL_LEGEND_ITEM +
				'</div>' +
			'</div>';

		var Graph = A.Component.create(
			{
				ATTRS: {
					containerId: {
						value: null
					},

					controller: {
						value: null
					},

					controllerMethod: {
						value: null
					},

					dataFormat: {
						value: null
					},

					graphHeight: {
						value: GRAPH_HEIGHT
					},

					legend: {
						value: true
					},

					legendContainerId: {
						value: null
					},

					params: {
						value: null
					},

					reverseData: {
						value: false
					},

					type: {
						value: 'area',
					},

					xAxisKey: {
						value: 'name'
					},

					xAxisLabel: {
						value: STR_BLANK
					},

					yAxisLabel: {
						value: STR_BLANK
					}
				},

				NAME: 'graph',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._config = config;
					},

					renderUI: function() {
						var instance = this;

						instance._buildGraph();
					},

					_buildDonutTooltip: function(data, defaultTitleFormat, defaultValueFormat, color) {
						var instance = this;

						var item = data[0];

						var percentLabel = Liferay.Testray.getPercentLabel(item.ratio * 100);
						var status = item.id.split('.').pop();
						var statusLabel = item.name;
						var value = item.value;

						return Lang.sub(
							TPL_TOOLTIP_DONUT,
							{
								percentLabel: percentLabel,
								status: status,
								statusLabel: Liferay.Language.get(status),
								value: value
							}
						);
					},

					_buildGraph: function() {
						var instance = this;

						var config = instance._config;

						Testray.request(
							{
								controller: config.controller,
								controllerMethod: config.controllerMethod,
								method: 'GET',
								params: config.params
							}
						).then(
							function(response) {
								var data = response.data;

								if (instance.get('reverseData')) {
									data = data.reverse();
								}

								var dataFormat = instance.get('dataFormat');

								if (dataFormat === 'runs') {
									Testray.request(
										{
											controller: 'runs',
											controllerMethod: 'index.json',
											method: 'GET',
											params: config.params
										}
									).then(
										function(response) {
											instance._additionalData = response.data;
										}
									);
								}

								if (dataFormat === 'totalMetrics') {
									var dataList = [];

									data = dataList.concat(data);
								}

								var simplifiedData = instance._simplifyData(data);

								if (instance.get('type') === 'donut') {
									instance._generateDonutGraph(simplifiedData);
								}
								else {
									instance._generateDefaultGraph(simplifiedData);
								}
							}
						);
					},

					_buildTooltip: function(data, defaultTitleFormat, defaultValueFormat, color) {
						var instance = this;

						var title = defaultTitleFormat(data[0].index);

						if (instance.get('dataFormat') === 'runs') {
							title = instance._getRunTitle(title);
						}

						var itemHTML = data.map(
							function(item) {
								return Lang.sub(
									TPL_TOOLTIP_DATA_ITEM,
									{
										color: color(item),
										status: item.name,
										value: item.value || 0
									}
								);
							}
						);

						var html = Lang.sub(
							TPL_TOOLTIP,
							{
								data: itemHTML.join(''),
								title: title
							}
						);

						return html;
					},

					_generateDefaultGraph: function(data) {
						var instance = this;

						var config = instance._config;

						var xAxisKey = instance.get('xAxisKey');

						c3.generate(
							{
								axis: {
									x: {
										label: {
											position: AXIS_X_POSITION,
											text: instance.get('xAxisLabel')
										},
										type: 'category'
									},
									y: {
										label: {
											position: AXIS_Y_POSITION,
											text: instance.get('yAxisLabel')
										}
									}
								},
								bar: {
									width: {
										max: 30
									}
								},
								bindto: '#' + config.containerId,
								data: {
									colors: DATA_COLORS,
									groups: [DATA_STATUS_ID_LIST],
									json: data,
									keys: {
										value: [
											'metrics.passed',
											'metrics.failed',
											'metrics.blocked',
											'metrics.test-fix',
											'metrics.incomplete',
											xAxisKey
										]
									},
									names: DATA_STATUS_LABELS,
									type: instance.get('type'),
									x: xAxisKey
								},
								legend: {
									inset: {
										anchor: 'top-right',
										step: 1,
										x: 10,
										y: -30
									},
									item: {
										tile: {
											height: LEGEND_TILE_SIZE,
											width: LEGEND_TILE_SIZE
										}
									},
									position: 'inset',
									show: instance.get('legend')
								},
								padding: {
									bottom: GRAPH_PADDING_BOTTOM,
									top: GRAPH_PADDING_TOP
								},
								size: {
									height: instance.get('graphHeight')
								},
								tooltip: {
									contents: function(data, defaultTitleFormat, defaultValueFormat, color) {
										return instance._buildTooltip(data, defaultTitleFormat, defaultValueFormat, color)
									}
								}
							}
						);
					},

					_generateDonutGraph: function(data) {
						var instance = this;

						var config = instance._config;

						var dataMetrics = data[0].metrics;

						var totalCount = dataMetrics.blocked +
							dataMetrics.failed +
							dataMetrics.incomplete +
							dataMetrics.passed +
							dataMetrics['test-fix'];

						var donutGraph = c3.generate(
							{
								bindto: '#' + config.containerId,
								data: {
									colors: DATA_COLORS,
									groups: [DATA_STATUS_ID_LIST],
									json: data,
									keys: {
										value: DATA_STATUS_ID_LIST
									},
									names: DATA_STATUS_LABELS,
									type: 'donut'
								},
								donut: {
									expand: false,
									label: {
										show: false
									},
									title: totalCount,
									width: DONUT_BAR_WIDTH
								},
								legend: {
									show: instance.get('legend')
								},
								size: {
									height: instance.get('graphHeight')
								},
								tooltip: {
									contents: function(data, defaultTitleFormat, defaultValueFormat, color) {
										return instance._buildDonutTooltip(data, defaultTitleFormat, defaultValueFormat, color)
									}
								}
							}
						);

						var legendContainerId = instance.get('legendContainerId');

						d3.select('#' + legendContainerId)
							.insert('ul', '#' + legendContainerId)
							.attr('class', CSS_CLASS_LEGEND_CONTAINER)
							.selectAll('li')
							.data(DATA_STATUS_ID_LIST)
							.enter()
							.append('li')
							.attr('class', CSS_CLASS_LEGEND_ITEM)
							.html(
								function(id) {
									var status = id.split('.').pop();

									var total = totalCount;
									var value = donutGraph.data.values(id);

									var percentLabel = Liferay.Testray.getPercentLabel((value / total) * 100);

									return Lang.sub(
										TPL_LEGEND_ITEM,
										{
											percentLabel: percentLabel,
											status: status,
											statusLabel: Liferay.Language.get(status),
											value: value
										}
									);
								}
							)
							.on(
								'mouseover',
								function(id) {
									donutGraph.focus(id);
								}
							)
							.on(
								'mouseout',
								function(id) {
									donutGraph.revert();
								}
							)
					},

					_getRunTitle: function(runNumber) {
						var instance = this;

						var currentRun = instance._additionalData.filter(
							function(item) {
								return item.number === runNumber;
							}
						);

						var runTitleHTML = '<div class="title-item">' + runNumber + '</div>';

						if (currentRun.length > 0) {
							currentRun[0].testrayFactors.forEach(
								function(item) {
									runTitleHTML += '<div class="title-item">' + item.testrayFactorOptionName + '</div>';
								}
							);
						}

						return runTitleHTML || runNumber;
					},

					_simplifyData: function(data) {
						return data.map(
							function(item) {
								var metrics = item.metrics;

								metrics.incomplete = metrics.untested + metrics['in-progress'];

								delete metrics.untested;
								delete metrics['in-progress'];

								return item;
							}
						);
					}
				}
			}
		);

		Testray.Graph = Graph;
	},
	'',
	{
		requires: ['c3', 'testray-base']
	}
);