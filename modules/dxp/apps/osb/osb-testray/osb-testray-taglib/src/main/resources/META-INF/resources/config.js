;(function() {
	AUI().applyConfig(
		{
			groups: {
				'osb-testray-taglib': {
					base: MODULE_PATH + '/',
					combine: Liferay.AUI.getCombine(),
					modules: {
						'moment': {
							path: 'date/js/moment.min.js'
						}
					},
					root: MODULE_PATH + '/'
				}
			}
		}
	);
})();