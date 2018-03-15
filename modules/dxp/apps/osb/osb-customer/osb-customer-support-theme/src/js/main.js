var animateFixedPortletMsgsTimeout;

var animateOpenFixedPortletMsgs = function() {
	if (typeof animateFixedPortletMsgsTimeout !== 'undefined') {
		clearTimeout(animateFixedPortletMsgsTimeout);
	}

	var msgs = document.querySelectorAll('div.portlet-msg-error, div.portlet-msg-success');

	var topOff = 55;

	for (var i = 0; i < msgs.length; i++) {
		if (window.getComputedStyle(msgs[i]).position == 'fixed') {
			msgs[i].style.top = topOff + 'px';

			topOff += 35;
		}
	}
};

var animateCloseFixedPortletMsgs = function() {
	var msgs = document.querySelectorAll('div.portlet-msg-error, div.portlet-msg-success');

	var topOff = 52;

	var stackCount = 0;

	for (var i = 0; i < msgs.length; i++) {
		if (window.getComputedStyle(msgs[i]).position == 'fixed') {
			if (stackCount < 3) {
				stackCount++;

				topOff += 3;
			}

			msgs[i].style.top = topOff + 'px';
		}
	}
};

var animateFixedPortletMsgsSetTimeout = function() {
	animateFixedPortletMsgsTimeout = setTimeout(animateCloseFixedPortletMsgs, 5000);
};

AUI().ready(
	'anim',
	'aui-base',
	'event',
	'transition',
	function(A) {
		var WIN = A.getWin();

		var Lang = A.Lang;

		A.on('hover', animateOpenFixedPortletMsgs, animateFixedPortletMsgsSetTimeout, 'div.portlet-msg-error, div.portlet-msg-success');

		var onScreenHelperNodes = A.all('.on-screen-helper');

		var updateOnScreen = function() {
			var currentScrollPos = WIN.get('docScrollY');
			var winHeight = WIN.get('innerHeight');

			if (Lang.isUndefined(winHeight)) {
				winHeight = document.documentElement.clientHeight;
			}

			onScreenHelperNodes.each(
				function(item) {
					var dataOffsetBottom = Lang.toInt(item.attr('data-offset-bottom'));
					var dataOffsetTop = Lang.toInt(item.attr('data-offset-top'));

					var dataRepeatBottom = item.attr('data-repeat-bottom');
					var dataRepeatTop = item.attr('data-repeat-top');

					var topEdge = item.getY();

					var topEdgeOffset = topEdge;

					if (dataOffsetTop) {
						topEdgeOffset -= dataOffsetTop;
					}

					var bottomEdge = topEdge + item.get('clientHeight');

					var screenBottom = topEdge - winHeight;

					if (dataOffsetBottom) {
						screenBottom += dataOffsetBottom;
					}

					if ((currentScrollPos > topEdgeOffset) && (currentScrollPos <= bottomEdge)) {
						item.addClass('on-screen-top');
					}
					else if (dataRepeatTop == 'true') {
						item.removeClass('on-screen-top');
					}

					if ((currentScrollPos > screenBottom) && (currentScrollPos <= bottomEdge)) {
						item.addClass('on-screen-bottom');
					}
					else if (dataRepeatBottom == 'true') {
						item.removeClass('on-screen-bottom');
					}
				}
			);
		};

		if (!onScreenHelperNodes.isEmpty()) {
			A.on('scroll', updateOnScreen);
			A.on('resize', updateOnScreen);

			updateOnScreen();
		}

		var toggleClassOffClick = function(event) {
			var currentTargetNode = event.currentTarget;

			var baseClassName = 'class-toggle-off-click-';

			var targetClass = currentTargetNode.attr('data-target-class');

			var className = 'active';

			if (targetClass) {
				className = targetClass;
			}

			className = baseClassName + className;

			var nodes = currentTargetNode.attr('data-target-node');

			if (nodes) {
				nodes = A.all(nodes);
			}
			else {
				nodes = A.NodeList.create();

				nodes.push(currentTargetNode);
			}

			var contentClass = baseClassName + 'content';

			nodes.each(
				function(node) {
					var active = false;

					var nodeContent = node.one('.' + contentClass);

					if (!nodeContent) {
						active = true;

						nodeContent = node;
					}

					if (event.target.hasClass(contentClass) || event.target.ancestor('.' + contentClass)) {
						active = true;
					}

					node.addClass(className);

					nodeContent.on(
						'clickoutside',
						function(event) {
							if (active) {
								node.removeClass(className);

								nodeContent.detach('clickoutside');
							}
							else {
								active = true;
							}
						}
					);
				}
			);
		};

		A.all('.class-toggle-off-click').on('click', toggleClassOffClick);

		var toggleClassCarousel = function(event) {
			var currentTargetNode = event.currentTarget;

			var baseClassName = 'class-toggle-carousel-';

			var targetClass = currentTargetNode.attr('data-target-class');

			var className = 'active';

			if (targetClass) {
				className = targetClass;
			}

			className = baseClassName + className;

			var nodes = currentTargetNode.attr('data-target-node');

			if (nodes) {
				nodes = A.all(nodes);
			}
			else {
				nodes = A.NodeList.create();

				nodes.push(currentTargetNode);
			}

			nodes.each(
				function(node) {
					var nodeClass = node.getAttribute('class');

					var regex = new RegExp('.' + baseClassName + '[\\w\'-]*', 'g');

					className = nodeClass.replace(regex, '') + ' ' + className;

					node.setAttribute('class', className);
				}
			);
		};

		A.all('.class-toggle-carousel').on('click', toggleClassCarousel);

		var toggleClass = function(event) {
			event.stopPropagation();

			var currentTargetNode = event.currentTarget;

			var baseClassName = 'class-toggle-';

			var className = 'active';

			var targetClass = currentTargetNode.attr('data-target-class');

			if (targetClass) {
				className = targetClass;
			}

			className = baseClassName + className;

			var nodes = currentTargetNode.attr('data-target-node');

			if (nodes) {
				nodes = A.all(nodes);
			}
			else {
				nodes = A.NodeList.create();

				nodes.push(currentTargetNode);
			}

			nodes.each(
				function(node) {
					node.toggleClass(className);

					if (currentTargetNode.hasAttribute('data-transition-property')) {
						var transitionProperty = currentTargetNode.getAttribute('data-transition-property');

						var transitionDuration = Lang.toInt(currentTargetNode.getAttribute('data-transition-duration'));

						if (!transitionDuration) {
							transitionDuration = 0.5;
						}

						var transitionEnd = currentTargetNode.getAttribute('data-transition-end');
						var transitionStart = currentTargetNode.getAttribute('data-transition-start') || 0;

						if (!transitionEnd) {
							var content = node.one('.' + baseClassName + 'content');

							if (content) {
								var clientHeight = content.get('clientHeight');

								transitionEnd = Lang.toInt(clientHeight);
							}
						}

						var config = [];

						config[transitionProperty] = transitionStart;

						if (node.hasClass(className)) {
							config[transitionProperty] = transitionEnd;
						}

						config['duration'] = transitionDuration;

						node.transition(config);

						node.setStyle(transitionProperty, config[transitionProperty]);
					}
				}
			);
		};

		A.all('.class-toggle').on('click', toggleClass);

		var responsiveContentHightNodes = A.all('.responsive-content-height');

		var setResponsiveContentHeight = function() {
			var winHeight = WIN.get('innerHeight');

			if (Lang.isUndefined(winHeight)) {
				winHeight = document.documentElement.clientHeight;
			}

			var banner = A.one('#banner');

			if (banner) {
				var bannerHeight = banner.get('clientHeight');

				if (bannerHeight) {
					winHeight -= bannerHeight;
				}

				winHeight -= 75;

				responsiveContentHightNodes.setStyle('max-height', winHeight);
			}
		};

		if (!responsiveContentHightNodes.isEmpty()) {
			A.on('load', setResponsiveContentHeight);
			A.on('resize', setResponsiveContentHeight);
		}

		function displayScrollArrow() {
			var mobileNavWrapper = A.one('.lesa-mobile-nav-wrapper');

			if (mobileNavWrapper) {
				var mobileNav = mobileNavWrapper.one('.lesa-mobile-nav ul');

				if (mobileNav) {
					var mobileWidth = mobileNav.outerWidth() > WIN.get('winWidth');

					mobileNavWrapper.toggleClass('overflow', mobileWidth);
				}
			}
		}

		A.on(
			['load', 'resize'],
			function(event) {
				A.throttle(displayScrollArrow(), 250);
			}
		);

		setTimeout(
			function() {
				A.all('.portlet-msg-success').addClass('hide');
			},
			20000
		);

		animateOpenFixedPortletMsgs();
		animateFixedPortletMsgsSetTimeout();
	}
);