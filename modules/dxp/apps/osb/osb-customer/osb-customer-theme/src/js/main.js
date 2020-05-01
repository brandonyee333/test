export {render, MegaMenu} from 'liferay-help-center-megamenu';

Liferay.Loader.require(
	'metal-debounce/src/debounce',
	'metal-dom/src/dom',
	function(metalDebounceSrcDebounce, metalDomSrcDom) {
		(
			function() {
				var debounce = metalDebounceSrcDebounce.default;
				var dom = metalDomSrcDom.default;

				window.addEventListener(
					'scroll',
					debounce(
						function() {
							var header = document.querySelector('header');

							if (header) {
								if (window.scrollY >= 56) {
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

// Site wide notification

var siteWideNotification = document.getElementById('siteWideNotification');

if (siteWideNotification) {
	var header = document.querySelector('header.header');
	var initialHeaderHeight = 56;

	if (header) {
		initialHeaderHeight = header.offsetHeight;
	}

	var controlMenu = document.getElementById('ControlMenu');

	if (controlMenu) {
		initialHeaderHeight += controlMenu.offsetHeight; 
	}

	if (sessionStorage.getItem('showSiteWideNotification') === null) {
		sessionStorage.setItem('showSiteWideNotification', 'true');
	}

	if (sessionStorage.getItem('showSiteWideNotification') === 'true') {
		siteWideNotification.classList.remove('hide');

		// Layout Updates

		var currentHeaderHeight = 168;

		if (header) {
			currentHeaderHeight = header.offsetHeight;
		}

		if (controlMenu) {
			currentHeaderHeight += controlMenu.offsetHeight; 
		}

		var mainContent = document.querySelector('main');
		var mainContentOffset = 'margin-top: ' + currentHeaderHeight + 'px;';

		if (mainContent) {
			mainContent.setAttribute("style", mainContentOffset);
		}

		// Closing Notification

		var closeNotification = document.getElementById('closeNotification');

		if (closeNotification) {
			closeNotification.addEventListener('click',
				function (event) {
					siteWideNotification.classList.add('hide');

					// Layout Updates

					var initialContentOffset = 'margin-top: ' + initialHeaderHeight + 'px;';

					if (mainContent) {
						mainContent.setAttribute("style", initialContentOffset);
					}

					sessionStorage.setItem('showSiteWideNotification', 'false');
			})
		}
	}
}
