AUI.add(
	'testray-avatar',
	function(A) {
		var AArray = A.Array;
		var Lang = A.Lang;

		var Testray = Liferay.Testray;

		var CSS_AVATAR = 'avatar';

		var CSS_AVATAR_CONTAINER_LABEL = 'avatar-container-label';

		var CSS_AVATAR_CONTAINER_PORTRAIT = 'avatar-container-portrait';

		var CSS_AVATAR_SMALL = 'avatar-small';

		var CSS_TESTRAY_TOOLTIP_TRIGGER = 'testray-tooltip-trigger';

		var STR_BLANK = '';

		var STR_COMMA = ',';

		var STR_SPACE = ' ';

		var TPL_AVATAR = '<div class="' + CSS_AVATAR + ' {cssClass}" style="background-image: url({imageURL})" title="{name}"></div>';

		var TPL_AVATAR_ITEM = '<div class="avatar-item">{avatar}{label}</div>';

		var TPL_AVATAR_LABEL = '<span class="avatar-label">{name}</span>';

		var TPL_EMPTY_LINK = '<a class="avatar-empty-link ' + CSS_AVATAR + STR_SPACE + CSS_AVATAR_SMALL + STR_SPACE + CSS_TESTRAY_TOOLTIP_TRIGGER + '" href="{url}" title="{label}">' +
				'<i class="avatar-empty-icon icon-{iconCssClass}"></i>' +
				'<i class="icon-plus"></i>' +
			'</a>';

		var TPL_EXTRA_COUNT = '<div class="' + CSS_TESTRAY_TOOLTIP_TRIGGER + ' avatar-extra-count" title="{names}">{count}</div>';

		var Avatar = A.Component.create(
			{
				ATTRS: {
					container: {
						setter: A.one,
						value: null
					},

					data: {
						value: null
					},

					emptyLinkConfig: {
						value: null
					},

					maxVisible: {
						value: 0
					},

					showLabel: {
						value: false
					}
				},

				NAME: 'avatar',

				prototype: {
					renderUI: function() {
						var instance = this;

						var container = instance.get('container');
						var showLabel = instance.get('showLabel');

						var cssClass = CSS_AVATAR_CONTAINER_PORTRAIT;

						if (showLabel) {
							cssClass = CSS_AVATAR_CONTAINER_LABEL;
						}

						container.addClass(cssClass);

						instance._renderAvatars();
					},

					_buildAvatarItemHTML: function(item) {
						var instance = this;

						var profileURL = item.profileImageURL;
						var showLabel = instance.get('showLabel');
						var userName = item.userName;

						var avatarHTML = Lang.sub(
							TPL_AVATAR,
							{
								cssClass: showLabel ? CSS_AVATAR_SMALL : CSS_TESTRAY_TOOLTIP_TRIGGER,
								imageURL: profileURL || STR_BLANK,
								name: userName || STR_BLANK
							}
						);

						var avatarLabelHTML = STR_BLANK;

						if (showLabel) {
							avatarLabelHTML = Lang.sub(
								TPL_AVATAR_LABEL,
								{
									name: userName
								}
							);
						}

						var avatarItemHTML = Lang.sub(
							TPL_AVATAR_ITEM,
							{
								avatar: avatarHTML,
								label: avatarLabelHTML
							}
						);

						return avatarItemHTML;
					},

					_buildEmptyLinkHTML: function(config) {
						var instance = this;

						var emptyLinkHTML = Lang.sub(
							TPL_EMPTY_LINK,
							{
								iconCssClass: config.iconCssClass,
								label: config.label,
								url: config.url
							}
						);

						return emptyLinkHTML;
					},

					_buildExtraCountHTML: function(config) {
						var data = config.data;

						var userNameList = [];

						AArray.each(
							data,
							function(item) {
								userNameList.push(item.userName);
							}
						);

						var userNameString = userNameList.join(STR_COMMA + STR_SPACE);

						var countLabel = Lang.sub(
							Liferay.Language.get('plus-x'),
							[
								config.extraCount
							]
						);

						var extraCountHTML = Lang.sub(
							TPL_EXTRA_COUNT,
							{
								count: countLabel,
								names: userNameString
							}
						);

						return extraCountHTML;
					},

					_isAvatarsEmpty: function(data) {
						var instance = this;

						var empty = true;

						var dataLength = data.length;

						if (dataLength > 1) {
							empty = false;
						}
						else if (dataLength === 1) {
							var firstDataItem = data[0];

							var profileImageURL = firstDataItem.profileImageURL;
							var userName = firstDataItem.userName;

							if (profileImageURL && userName) {
								empty = false;
							}
						}

						return empty;
					},

					_renderAvatars: function() {
						var instance = this;

						var data = instance.get('data');
						var emptyLinkConfig = instance.get('emptyLinkConfig');

						if (data && !instance._isAvatarsEmpty(data)) {
							var nodeList = [];

							var maxVisible = instance.get('maxVisible');

							var extraCount = data.length - maxVisible;

							if (extraCount > 0) {
								var extraCountHTML = instance._buildExtraCountHTML(
									{
										data: data.slice(maxVisible, data.length),
										extraCount: extraCount
									}
								);

								nodeList.push(extraCountHTML);

								data = data.slice(0, maxVisible);
							}

							data.reverse();

							AArray.each(
								data,
								function(item) {
									var avatarItemHTML = instance._buildAvatarItemHTML(item);

									nodeList.push(avatarItemHTML);
								}
							);

							var nodeListHTML = nodeList.join(STR_BLANK);

							instance._setContainerContent(nodeListHTML);
						}
						else if (emptyLinkConfig) {
							var emptyLinkNode = instance._buildEmptyLinkHTML(emptyLinkConfig);

							instance._setContainerContent(emptyLinkNode);
						}
					},

					_setContainerContent: function(html) {
						var instance = this;

						var container = instance.get('container');

						container.html(html);
					}
				}
			}
		);

		Testray.Avatar = Avatar;
	},
	'',
	{
		requires: ['aui-base']
	}
);