import {render, MegaMenu} from 'liferay-help-center-megamenu';

Liferay.Loader.require(
	'metal-debounce/src/debounce',
	'metal-dom/src/dom',
	function(metalDebounceSrcDebounce, metalDomSrcDom) {
		(
			function() {
				let debounce = metalDebounceSrcDebounce.default;
				let dom = metalDomSrcDom.default;

				window.addEventListener(
					'scroll',
					debounce(
						function() {
							var header = document.querySelector('header');

							if (header) {
								if (window.scrollY >= 64) {
									dom.addClasses(header, 'has-scroll');
								}
								else {
									dom.removeClasses(header, 'has-scroll');
								}
							}
						},
						250
					)
				);
			}
		)();
	},
	function(error) {
		console.error(error);
	}
);