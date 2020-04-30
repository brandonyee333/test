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
	if (sessionStorage.getItem('showSiteWideNotification') === null) {
		sessionStorage.setItem('showSiteWideNotification', 'true');
	}

	if (siteWideNotification && sessionStorage.getItem('showSiteWideNotification') === 'true') {
		siteWideNotification.classList.remove('hide');

		// Layout Updates

		var mainContent = document.querySelector('main');
		var mainContentWithControlMenu = document.querySelector('.has-control-menu main');

		if (mainContentWithControlMenu) {
			mainContentWithControlMenu.setAttribute("style", "margin-top: 208px;")
		} else if (mainContent) {
			mainContent.setAttribute("style", "margin-top: 145px;")
		}

		// Closing Notification

		var closeNotification = document.getElementById('closeNotification');

		if (closeNotification) {
			closeNotification.addEventListener('click',
				function (event) {
					siteWideNotification.classList.add('hide');

					sessionStorage.setItem('showSiteWideNotification', 'false');
			})
		}
	}
}
