import {render, MegaMenu} from 'liferay-help-center-megamenu';

Liferay.Loader.require(
	'metal-debounce/src/debounce',
	'metal-dom/src/dom',
	function(metalDebounceSrcDebounce, metalDomSrcDom) {
		(
			function() {
				const debounce = metalDebounceSrcDebounce.default;
				const dom = metalDomSrcDom.default;

				window.addEventListener(
					'scroll',
					debounce(
						function() {
							const header = document.querySelector('header');

							if (header) {
								if (window.scrollY >= 64) {
									dom.addClasses(header, 'has-scroll-shadow');
								}
								else {
									dom.removeClasses(header, 'has-scroll-shadow');
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