AUI.add(
	'testray-breadcrumb-finder-validator',
	function(A) {
		var Lang = A.Lang;

		var Testray = Liferay.Testray;

		var CSS_ICON_CHECKED = 'icon-ok-sign';

		var CSS_ICON_UNCHECKED = 'icon-circle-blank';

		var CSS_VALIDATOR_ITEM = 'breadcrumb-finder-validator-item';

		var SELECTOR_ICON = 'i';

		var STR_DOT = '.';

		var TPL_ICON = '<i class="{iconCss}"></i>';

		var BreadcrumbFinderValidator = A.Component.create(
			{
				ATTRS: {
					container: {
						setter: A.one,
						value: null
					},

					testrayBreadcrumbFinder: {
						value: null
					},

					validateLevel: {
						value: null
					}
				},

				NAME: 'breadcrumb-finder-validator',

				prototype: {
					initializer: function() {
						var instance = this;

						var container = instance.get('container');

						instance._validationDisplayList = container.all(STR_DOT + CSS_VALIDATOR_ITEM);
					},

					bindUI: function() {
						var instance = this;

						var testrayBreadcrumbFinder = instance.get('testrayBreadcrumbFinder');

						testrayBreadcrumbFinder.on(
							'finderLevelChange',
							A.bind('_updateValidation', instance)
						);
					},

					_updateValidation: function(event) {
						var instance = this;

						var currentLevel = event.currentLevel;

						instance._validationDisplayList.each(
							function(item, index) {
								var iconHTML = Lang.sub(
									TPL_ICON,
									{
										iconCss: CSS_ICON_UNCHECKED
									}
								);

								var toggleClass = false;

								if (index < currentLevel) {
									iconHTML = Lang.sub(
										TPL_ICON,
										{
											iconCss: CSS_ICON_CHECKED
										}
									);

									toggleClass = true;
								}

								item.toggleClass('success', toggleClass);

								var icon = item.one(SELECTOR_ICON);

								if (icon) {
									icon.replace(iconHTML);
								}
							}
						);
					}
				}
			}
		);

		Testray.BreadcrumbFinderValidator = BreadcrumbFinderValidator;
	},
	'',
	{
		requires: ['testray-base']
	}
);