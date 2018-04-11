AUI().use(
	'aui-base',
	'event-outside',
	function(A) {
		var WIN = A.getWin();

		var wrapper = A.one('#wrapper');

		if (wrapper) {
			wrapper.delegate(
				'click',
				function(event) {
					event.stopPropagation();

					var cssClassOpen = 'open';

					var currentTarget = event.currentTarget;

					var targetNode = currentTarget.attr('data-target-node');

					var updateNode = A.one(targetNode) || currentTarget;

					if (updateNode) {
						wrapper.all('.open').each(
							function(node) {
								if (!node.contains(updateNode)) {
									node.removeClass(cssClassOpen);
								}
							}
						);

						var hasCssClassOpen = updateNode.hasClass(cssClassOpen);

						updateNode.toggleClass(cssClassOpen, !hasCssClassOpen);

						if (updateNode.hasClass('nav-search-content')) {
							currentTarget.toggleClass(cssClassOpen, !hasCssClassOpen);

							Liferay.Util.focusFormField('.lfr-search-input');
						}
						else if (!updateNode.hasClass('toggle-menu-content') || !updateNode.ancestor('.toggle-menu-content')) {
							updateNode.on(
								'clickoutside',
								function(event) {
									updateNode.removeClass(cssClassOpen);

									updateNode.detach('clickoutside');
								}
							);
						}
					}
				},
				'.toggle-menu'
			);
		}

		var responsiveContentHeightNodes = A.all('.responsive-content-height');

		var setResponsiveContentHeight = function() {
			var winHeight = WIN.get('innerHeight');

			if (winHeight == undefined) {
				winHeight = document.documentElement.clientHeight;
			}

			var bannerHeight = A.one('#banner').get('clientHeight');

			if (bannerHeight) {
				winHeight -= bannerHeight;
			}

			winHeight -= 75;

			responsiveContentHeightNodes.setStyle('max-height', winHeight);
		};

		if (!responsiveContentHeightNodes.isEmpty()) {
			A.on('load', setResponsiveContentHeight);
			A.on('resize', setResponsiveContentHeight);
		}

		Liferay.Util.focusFormField('#_1_WAR_osbknowledgebaseportlet_keyword_search');
	}
);