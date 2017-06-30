AUI().add(
	'localized-input',
	function(A) {
		var LocalizedInput = function(node) {
			var instance = this;

			var container = A.one(node);

			instance._container = container;

			instance._currentLanguageContainer = container.one('.current-language-container');
			instance._languageContainer = container.one('.language-container');

			instance._localizedInputField = container.one('.localized-input-field input') || container.one('.localized-input-field textarea');

			instance._addTranslationsMenu = container.one('.add-translations-menu ul');
			instance._availableTranslations = container.one('.available-translations');
			instance._enableLocalizedInputCheckbox = container.one('.enable-localized-input input[type=checkbox]');
			instance._removeTranslationLink = container.one('.remove-translation');

			instance._currentLanguageIdInput = container.one('input.current-language-id-input');
			instance._defaultLanguageIdInput = container.one('input.default-language-id-input');
			instance._defaultLanguageIdSelect = container.one('.default-language-id-select select');

			instance._langRemoveLocalizedInput = Liferay.Language.get('unchecking-this-field-will-remove-localized-data-for-languages-not-shown-in-this-view');
			instance._langRemoveTranslation = Liferay.Language.get('are-you-sure-you-want-to-deactivate-this-language');

			instance._setupEvents();
		};

		LocalizedInput.prototype = {
			getCurrentLanguageId: function() {
				var instance = this;

				return instance._currentLanguageIdInput.attr('value');
			},

			getDefaultLanguageId: function() {
				var instance = this;

				if (instance._defaultLanguageIdInput) {
					return instance._defaultLanguageIdInput.attr('value');
				}

				return instance._defaultLanguageIdSelect.attr('value');
			},

			getHiddenLocalizedInput: function(languageId) {
				var instance = this;

				return instance._languageContainer.one('input[data-languageId=' + languageId + ']');
			},

			removeTranslation: function() {
				var instance = this;

				if (confirm(instance._langRemoveTranslation)) {
					instance._localizedInputField.attr('value', '');

					instance.setLocalizedInput(instance.getDefaultLanguageId());
				}
			},

			setHiddenLocalizedInput: function(languageId, content) {
				var instance = this;

				var hiddenLocalizedInput = instance.getHiddenLocalizedInput(languageId);

				hiddenLocalizedInput.attr('value', content);
			},

			setLocalizedInput: function(languageId) {
				var instance = this;

				var currentLanguageId = instance.getCurrentLanguageId();
				var defaultLanguageId = instance.getDefaultLanguageId();

				var content = instance._localizedInputField.attr('value');

				instance.setHiddenLocalizedInput(currentLanguageId, content);

				var addTranslationMenuItem = instance._addTranslationsMenu.one('.language-id-' + currentLanguageId);
				var availableTranslationLink = instance._availableTranslations.one('.language-id-' + currentLanguageId);

				if ((content.length == 0) && (currentLanguageId != defaultLanguageId)) {
					addTranslationMenuItem.show();
					availableTranslationLink.hide();
				}
				else {
					addTranslationMenuItem.hide();
					availableTranslationLink.show();
				}

				var hiddenLocalizedInput = instance.getHiddenLocalizedInput(languageId);

				instance._localizedInputField.set('value', hiddenLocalizedInput.attr('value'));

				addTranslationMenuItem = instance._addTranslationsMenu.one('.language-id-' + languageId);
				availableTranslationLink = instance._availableTranslations.one('.language-id-' + languageId);

				addTranslationMenuItem.hide();
				availableTranslationLink.show();

				var currentLanguageLabel = instance._currentLanguageContainer.one('span');

				currentLanguageLabel.html(hiddenLocalizedInput.attr('data-displayName'));

				if (languageId != defaultLanguageId) {
					instance._removeTranslationLink.show();
				}
				else {
					instance._removeTranslationLink.hide();
				}

				instance._currentLanguageIdInput.set('value', languageId);
			},

			toggleLocalizedInput: function(event) {
				var instance = this;

				if (instance._enableLocalizedInputCheckbox.attr('checked')) {
					instance._currentLanguageContainer.show();
					instance._languageContainer.show();

					if (instance.getCurrentLanguageId() != instance.getDefaultLanguageId()) {
						instance._removeTranslationLink.show();
					}
					else {
						instance._removeTranslationLink.hide();
					}
				}
				else {
					if (confirm(instance._langRemoveLocalizedInput)) {
						instance._currentLanguageContainer.hide();
						instance._languageContainer.hide();
						instance._removeTranslationLink.hide();

						instance.setLocalizedInput(instance.getDefaultLanguageId());
					}
					else {
						instance._enableLocalizedInputCheckbox.set('checked', true);
					}
				}
			},

			_finalize: function() {
				var instance = this;

				var languageId = '';

				var content = instance._localizedInputField.attr('value');

				if (instance._enableLocalizedInputCheckbox.attr('checked')) {
					languageId = instance.getCurrentLanguageId();
				}
				else {
					languageId = instance.getDefaultLanguageId();

					instance.setLocalizedInput(languageId);

					instance._availableTranslations.all('input[type=hidden]').set('value', '');
				}

				instance.setHiddenLocalizedInput(languageId, content);
			},

			_setupEvents: function() {
				var instance = this;

				instance._addTranslationsMenu.delegate(
					'click',
					function(event) {
						instance.setLocalizedInput(event.currentTarget.attr('class').substring(12, 17));
					},
					'li'
				);

				instance._availableTranslations.delegate(
					'click',
					function(event) {
						instance.setLocalizedInput(event.currentTarget.attr('data-languageId'));
					},
					'a'
				);

				if (instance._defaultLanguageIdSelect) {
					instance._defaultLanguageIdSelect.on(
						'change',
						function(event) {
							instance.setLocalizedInput(event.currentTarget.attr('value'));
						},
						instance
					);
				}

				instance._enableLocalizedInputCheckbox.on('change', instance.toggleLocalizedInput, instance);

				instance._submitFormListener = A.Do.before(instance._finalize, window, 'submitForm', instance);

				instance._removeTranslationLink.on('click', instance.removeTranslation, instance);
			}
		};

		Liferay.LocalizedInput = LocalizedInput;
	},
	'',
	{
		requires: ['aui-base']
	}
);