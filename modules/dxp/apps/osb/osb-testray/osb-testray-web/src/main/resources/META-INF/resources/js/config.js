;(function() {
	AUI().applyConfig(
		{
			groups: {
				'osb-testray-web': {
					base: MODULE_PATH + '/js/',
					combine: Liferay.AUI.getCombine(),
					modules: {
						'c3': {
							path: 'c3/c3.min.js',
							requires: [
								'd3'
							]
						},
						'choices': {
							path: 'choices.min.js'
						},
						'd3': {
							path: 'd3/d3.min.js'
						},
						'testray-avatar': {
							path: 'avatar.js',
							requires: [
								'testray-base'
							]
						},
						'testray-base': {
							path: 'base.js',
							requires: [
								'aui-base',
								'aui-io-request',
								'liferay-alert',
								'promise'
							]
						},
						'testray-breadcrumb-finder': {
							path: 'breadcrumb_finder.js',
							requires: [
								'autocomplete',
								'autocomplete-filters',
								'event-focus',
								'event-key',
								'event-outside',
								'event-resize',
								'node-event-simulate',
								'testray-base'
							]
						},
						'testray-breadcrumb-finder-validator': {
							path: 'breadcrumb_finder_validator.js',
							requires: [
								'testray-base'
							]
						},
						'testray-context-menu': {
							path: 'context_menu.js',
							requires: [
								'aui-component',
								'event-outside',
								'liferay-search-container',
								'overlay',
								'testray-base'
							]
						},
						'testray-graph': {
							path: 'graph.js',
							requires: [
								'c3',
								'testray-base'
							]
						},
						'testray-loading-bar': {
							path: 'loading_bar.js',
							requires: [
								'testray-base'
							]
						},
						'testray-metrics-bar': {
							path: 'metrics_bar.js',
							requires: [
								'testray-base'
							]
						},
						'testray-metrics-panel': {
							path: 'metrics_panel.js',
							requires: [
								'testray-base',
								'testray-metrics-bar'
							]
						},
						'testray-row-checker-action-merge': {
							path: 'row_checker_action_merge.js',
							requires: [
								'liferay-alert',
								'testray-base'
							]
						},
						'testray-row-checker-action-split': {
							path: 'row_checker_action_split.js',
							requires: [
								'liferay-alert',
								'testray-base'
							]
						},
						'testray-row-checker-toolbar': {
							path: 'row_checker_toolbar.js',
							requires: [
								'testray-base',
								'testray-row-checker-action-merge',
								'testray-row-checker-action-split'
							]
						}
					},
					root: MODULE_PATH + '/js/'
				}
			}
		}
	);
})();