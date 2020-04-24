AUI.add(
	'testray-metrics-panel',
	function(A) {
		var Testray = Liferay.Testray;

		var METRICS_BAR_CONTAINER = '<div class="metrics-bar"></div>';

		var METRICS_LEGEND_CONTAINER = '<div class="metrics-legend"></div>';

		var MetricsPanel = A.Component.create(
			{
				ATTRS: {
					data: {
						value: null
					},

					panel: {
						setter: A.one,
						value: null
					},

					total: {
						value: null
					}
				},

				NAME: 'metrics-panel',

				prototype: {
					renderUI: function() {
						var instance = this;

						var panel = instance.get('panel');

						if (panel) {
							var panelHeader = panel.one('.panel-toggle');

							if (panelHeader) {
								var metricsBarNode = A.Node.create(METRICS_BAR_CONTAINER);
								var metricsLegendNode = A.Node.create(METRICS_LEGEND_CONTAINER);

								var data = instance.get('data');
								var total = instance.get('total');

								new Testray.MetricsBar(
									{
										container: metricsBarNode,
										data: data,
										legendContainer: metricsLegendNode,
										legendShowLabels: false,
										total: total
									}
								).render();

								panelHeader.append(metricsLegendNode);

								panelHeader.append(metricsBarNode);
							}
						}
					}
				}
			}
		);

		Testray.MetricsPanel = MetricsPanel;
	},
	'',
	{
		requires: ['testray-base', 'testray-metrics-bar']
	}
);