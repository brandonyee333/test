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

AUI().use(
	'anim',
	'event',
	'transition',
	function(A) {
		var WIN = A.getWin();

		A.all('.animate-scroll').on(
			'click',
			function(event) {
				event.preventDefault();

				var node = event.currentTarget;

				var section = A.one(node.get('hash'));

				var offset = parseInt(node.attr('data-offset'));

				var scrollTo = parseInt(section.getY());

				if (offset) {
					scrollTo -= offset;
				}

				new A.Anim(
					{
						duration: 0.5,
						easing: 'easeBoth',
						node: 'win',
						to: {
							scroll: [0, scrollTo]
						}
					}
				).run();
			}
		);

		A.on('hover', animateOpenFixedPortletMsgs, animateFixedPortletMsgsSetTimeout, 'div.portlet-msg-error, div.portlet-msg-success');

		var lazyLoadNodes = A.all('.lazy-load');

		var lazyLoad = function() {
			var currentScrollPos = WIN.get('docScrollY');

			var winHeight = WIN.get('innerHeight');

			if (winHeight == undefined) {
				winHeight = document.documentElement.clientHeight;
			}

			lazyLoadNodes.each(
				function(item, index, collection) {
					if (!item.hasClass('lazy-loaded')) {
						var loadPos = item.getY() - winHeight;

						var dataOffset = parseInt(item.attr('data-offset'));

						if (dataOffset) {
							loadPos += dataOffset;
						}

						if (currentScrollPos > loadPos) {
							var datasrc = item.attr('datasrc');
							var src = item.attr('src');

							if (src != datasrc) {
								item.attr('src', datasrc);
							}

							item.addClass('lazy-loaded');
						}
					}
				}
			);
		};

		if (!lazyLoadNodes.isEmpty()) {
			A.on('scroll', lazyLoad);
			A.on('resize', lazyLoad);

			lazyLoad();
		}

		var onScreenHelperNodes = A.all('.on-screen-helper');

		var updateOnScreen = function() {
			var currentScrollPos = WIN.get('docScrollY');

			var winHeight = WIN.get('innerHeight');

			if (winHeight == undefined) {
				winHeight = document.documentElement.clientHeight;
			}

			onScreenHelperNodes.each(
				function(item, index, collection) {
					var dataOffsetBottom = parseInt(item.attr('data-offset-bottom'));
					var dataOffsetTop = parseInt(item.attr('data-offset-top'));
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
							if (!active) {
								active = true;

								return;
							}

							node.removeClass(className);

							nodeContent.detach('clickoutside');
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

					if (!currentTargetNode.hasAttribute('data-transition-property')) {
						return;
					}

					var transitionProperty = currentTargetNode.getAttribute('data-transition-property');

					var transitionDuration = parseInt(currentTargetNode.getAttribute('data-transition-duration'));

					if (!transitionDuration) {
						transitionDuration = 0.5;
					}

					var transitionStart = currentTargetNode.getAttribute('data-transition-start') || 0;

					var transitionEnd = currentTargetNode.getAttribute('data-transition-end');

					if (!transitionEnd) {
						transitionEnd = parseInt(node.one('.' + baseClassName + 'content').get('clientHeight'));
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
			);
		};

		A.all('.class-toggle').on('click', toggleClass);

		var responsiveContentHightNodes = A.all('.responsive-content-height');

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

			responsiveContentHightNodes.setStyle('max-height', winHeight);
		};

		if (!responsiveContentHightNodes.isEmpty()) {
			A.on('load', setResponsiveContentHeight);
			A.on('resize', setResponsiveContentHeight);
		}
	}
);

AUI().ready(
	function(A) {
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