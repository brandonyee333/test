AUI.add(
	'testray-metrics-bar',
	function(A) {
		var Lang = A.Lang;

		var Testray = Liferay.Testray;

		var STATUS_ORDER = ['passed', 'failed', 'blocked', 'test-fix', 'self-completed', 'others-completed', 'incomplete'];

		var TPL_LEGEND_ITEM = '<li class="legend-item" title="{label}">' +
				'<div class="legend-item-key">' +
					'<div class="legend-color-block {status}"></div>' +
				'</div>' +
				'<div class="legend-item-value">' +
					'<div class="legend-item-numbers">' +
						'<span class="primary">{percent}</span>' +
						'<span class="secondary">{count}</span>' +
					'</div>' +
					'<div class="legend-item-label {showLabel}">{label}</div>' +
				'</div>' +
			'</li>';

		var TPL_LEGEND_WRAPPER = '<ul class="legend-container"></ul>';

		var TPL_METRICS_BAR_ITEM = '<div class="metrics-bar-item {status}" title="{value}"></div>';

		var MetricsBar = A.Component.create(
			{
				ATTRS: {
					container: {
						setter: A.one,
						value: null
					},

					data: {
						value: null
					},

					legendContainer: {
						setter: A.one,
						value: null
					},

					legendShowLabels: {
						value: true
					},

					total: {
						setter: Lang.toInt,
						validator: Lang.isNumber,
						value: null
					}
				},

				NAME: 'metrics-bar',

				prototype: {
					renderUI: function() {
						var instance = this;

						instance._buildBar();
					},

					_buildBar: function() {
						var instance = this;

						var data = instance.get('data');
						var legendContainer = instance.get('legendContainer');
						var total = instance.get('total');

						if (!total) {
							total = instance._getTotal(data);
						}

						var metricBarItems = {};
						var metricLagendItems = {};

						A.each(
							data,
							function(item, status, data) {
								var label = Liferay.Language.get(status);

								var value = item;

								var itemLabel = item.label;
								var itemValue = item.value;

								if (itemLabel && itemValue >= 0) {
									label = itemLabel;
									value = itemValue;
								}

								var percent = (value / total) * 100;

								metricBarItems[status] = instance._buildBarItem(label, percent, status);

								if (legendContainer) {
									metricLagendItems[status] = instance._buildLegendItem(label, percent, status, value);
								}
							}
						);

						var itemsList = new A.NodeList();
						var legendList = new A.NodeList();

						A.Array.each(
							STATUS_ORDER,
							function(statusKey) {
								var metricBarItem = metricBarItems[statusKey];

								if (metricBarItem) {
									itemsList.push(metricBarItem);
								}

								if (legendContainer) {
									var metricLegendItem = metricLagendItems[statusKey];

									if (metricLegendItem) {
										legendList.push(metricLegendItem);
									}
								}
							}
						);

						if (legendContainer) {
							var legendWrapper = new A.Node.create(TPL_LEGEND_WRAPPER);

							legendWrapper.append(legendList);

							legendContainer.append(legendWrapper);
						}

						var container = instance.get('container');

						if (container) {
							container.append(itemsList);
						}
					},

					_buildBarItem: function(label, percent, status) {
						var instance = this;

						var percentLabel = Testray.getPercentLabel(percent, label);

						var barItemHTML = Lang.sub(
							TPL_METRICS_BAR_ITEM,
							{
								status: status,
								value: percentLabel
							}
						);

						var barItemNode = A.Node.create(barItemHTML);

						barItemNode.width(percent + '%');

						return barItemNode;
					},

					_buildLegendItem: function(label, percent, status, value) {
						var instance = this;

						var count = Lang.sub(
							Liferay.Language.get('parenthesis-x'),
							[
								value
							]
						);

						var percentLabel = Testray.getPercentLabel(percent);

						var showLabels = instance.get('legendShowLabels');

						var legendItemHTML = Lang.sub(
							TPL_LEGEND_ITEM,
							{
								count: count,
								label: label,
								percent: percentLabel,
								showLabel: showLabels ? '' : 'hide',
								status: status
							}
						);

						return A.Node.create(legendItemHTML);
					},

					_getTotal: function(data) {
						var total = 0;

						A.each(
							data,
							function(item) {
								var increment = item;

								if (item.value >= 0) {
									increment = item.value;
								}

								total += increment;
							}
						);

						return total;
					}
				}
			}
		);

		Testray.MetricsBar = MetricsBar;
	},
	'',
	{
		requires: ['testray-base']
	}
);