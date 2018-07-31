AUI.add(
	'liferay-dynamic-uploader',
	function(A) {
		var MSG_TYPE_ERROR = 'error';

		var MSG_TYPE_PROGRESS = 'progress';

		var MSG_TYPE_SUCCESS = 'success';

		var MSG_TYPE_WARN = 'warn';

		var TPL_SWITCH_BUTTON = [
			'<a href="javascript:;" id="{namespace}classicUploaderButton">{classicUploaderText}</a>',
			'<a href="javascript:;" class="hide" id="{namespace}regularUploaderButton">{regularUploaderText}</a>'
		];

		var DynamicUploader = function(options) {
			var instance = this;

			options = options || {};

			instance.namespace = options.namespace || '_liferay_pns_' + Liferay.Util.randomInt() + '_';

			instance.container = A.one(options.container);
			instance.uploaderSwitch = A.one(options.uploaderSwitch);

			instance.messageContainer = instance.container.oneNS(instance.namespace, '#messageContainer');
			instance.uploadContainer = instance.container.oneNS(instance.namespace, '#uploadContainer');
			instance.fallbackContainer = instance.container.oneNS(instance.namespace, '#fallbackContainer');
			instance.uploaderType = instance.container.oneNS(instance.namespace, '#uploaderType');

			instance.maxFiles = options.maxFiles || 1;

			instance.regularUploader = options.regularUploader;
			instance.resumableUploader = options.resumableUploader;

			instance.isSupportBrowser = (
				(typeof(File) !== 'undefined') &&
				(typeof(Blob) !== 'undefined') &&
				(typeof(FileList) !== 'undefined') &&
				(!!Blob.prototype.webkitSlice || !!Blob.prototype.mozSlice || !!Blob.prototype.slice || false));

			// Language keys

			instance.classicUploderMaxFileText = Liferay.Language.get('the-classic-uploader-supports-files-up-to-100-mb');
			instance.invalidFileFolderText = Liferay.Language.get('please-do-not-upload-a-file-folder');
			instance.invalidFilesText = Liferay.Language.get('please-do-not-upload-large-files-and-regular-files-at-the-same-time');
			instance.invalidMaxFilesText = Liferay.Language.get('please-upload-x-files-at-a-time');
			instance.invalidMaxLargeFilesText = Liferay.Language.get('please-upload-x-large-files-at-a-time');
			instance.invalidMaxSizeText = Liferay.Language.get('please-enter-files-no-larger-than-x');
			instance.invalidServersText = Liferay.Language.get('file-servers-are-not-available-please-upload-files-less-than-100-mb');
			instance.newUploderMaxFileText = Liferay.Language.get('the-new-uploader-supports-files-up-to-35-gb');
			instance.useClassicUploaderText = Liferay.Language.get('use-the-classic-uploader');
			instance.useNewUploaderText = Liferay.Language.get('use-the-new-uploader');

			instance.renderUI();
			instance.bindUI();
		};

		DynamicUploader.prototype = {
			renderUI: function() {
				var instance = this;

				if (instance.isSupportBrowser) {
					var templateConfig = {
						classicUploaderText: instance.useClassicUploaderText,
						namespace: instance.namespace,
						regularUploaderText: instance.useNewUploaderText
					};

					A.Template(TPL_SWITCH_BUTTON).render(templateConfig, instance.uploaderSwitch);

					instance.renderNewUploaderUI();
				}
				else {
					instance.renderClassicUploaderUI();
				}
			},

			setupUploader: function(fileList, event) {
				var instance = this;

				var filesCount = fileList.length + instance.regularUploader.files.length + instance.resumableUploader.files.length;
				var maxFiles = instance.maxFiles;
				var maxLargeFiles = 1;

				if (filesCount > maxFiles) {
					instance.updateMessage(instance.invalidMaxFilesText.replace('{0}', maxFiles), MSG_TYPE_ERROR);

					return;
				}

				var largeFilesCount = 0;
				var regularFilesCount = 0;

				for (var i = 0; i < fileList.length; i++) {
					var file = fileList[i];

					if (file.size > instance.maxFileSize) {
						instance.updateMessage(instance.invalidMaxSizeText.replace('{0}', maxFileSize), MSG_TYPE_ERROR);

						return true;
					}
					else if (file.size > (100 * 1024 * 1024)) {
						largeFilesCount = largeFilesCount + 1;
					}
					else if ((file.size > 0) && (file.size < (100 * 1024 * 1024))) {
						regularFilesCount = regularFilesCount + 1;
					}
				}

				if ((instance.resumableUploader.files.length + largeFilesCount) > maxLargeFiles) {
					instance.updateMessage(instance.invalidMaxLargeFilesText.replace('{0}', maxLargeFiles), MSG_TYPE_ERROR);

					return;
				}

				if ((instance.regularUploader.files.length + regularFilesCount) > maxFiles) {
					instance.updateMessage(instance.invalidMaxFilesText.replace('{0}', maxFiles), MSG_TYPE_ERROR);

					return;
				}

				if (((instance.regularUploader.files.length + regularFilesCount) > 0) && ((instance.resumableUploader.files.length + largeFilesCount) > 0)) {
					instance.updateMessage(instance.invalidFilesText, MSG_TYPE_ERROR);

					return;
				}

				if (event._event.dataTransfer && (event._event.dataTransfer.types.length == 1) && (event._event.dataTransfer.types[0] == 'Files') && (event._event.dataTransfer.files.length == 0)) {
					instance.updateMessage(instance.invalidFileFolderText, MSG_TYPE_ERROR);

					return;
				}

				for (var i = 0; i < fileList.length; i++) {
					var file = fileList[i];

					if (!file.type && ((file.size % 4096) === 0) && (file.size <= 1048576)) {
						var isDirectory = false;

						try {
							if (event._event.dataTransfer) {
								var items = event._event.dataTransfer.items;

								var item = items[i];

								if (item.kind != 'file') {
									continue;
								}

								isDirectory = item.webkitGetAsEntry().isDirectory;
							}

							var fileReader = new FileReader();

							fileReader.readAsBinaryString(file);

							if (fileReader.error instanceof DOMError) {
								isDirectory = true;
							}
						}
						catch (NS_ERROR_FILE_ACCESS_DENIED) {
							isDirectory = true;
						}

						if (isDirectory) {
							instance.updateMessage(instance.invalidFileFolderText, MSG_TYPE_ERROR);

							return;
						}
					}

					if (file.size > (100 * 1024 * 1024)) {
						if (instance.resumableUploader.opts.target == '') {
							instance.updateMessage(instance.invalidServersText, MSG_TYPE_ERROR);

							return;
						}

						instance.uploaderType.set('value', 'resumableUploader');

						instance.resumableUploader.addFile(file, event);
					}
					else if ((file.size > 0) && (file.size < (100 * 1024 * 1024))) {
						instance.uploaderType.set('value', 'regularUploader');

						instance.regularUploader.addFile(file, event);
					}
				}
			},

			renderClassicUploaderUI: function() {
				var instance = this;

				instance.fallbackContainer.removeClass('hide');

				instance.uploaderType.set('value', 'classicUploader');

				instance.updateMessage(instance.classicUploderMaxFileText, MSG_TYPE_WARN);
			},

			renderNewUploaderUI: function() {
				var instance = this;

				instance._uploadArea = instance.uploadContainer.oneNS(instance.namespace, '#uploadArea');
				instance._selectButton = instance.uploadContainer.oneNS(instance.namespace, '#selectButton');
				instance._fileInputField = instance.uploadContainer.oneNS(instance.namespace, '#fileInputField');

				instance._classicUploaderButton = instance.uploaderSwitch.oneNS(instance.namespace, '#classicUploaderButton');
				instance._regularUploaderButton = instance.uploaderSwitch.oneNS(instance.namespace, '#regularUploaderButton');

				if (instance.resumableUploader.opts.target != '') {
					instance.updateMessage(instance.newUploderMaxFileText, MSG_TYPE_WARN);
				}
				else {
					instance.updateMessage(instance.invalidServersText, MSG_TYPE_WARN);
				}
			},

			bindUI: function() {
				var instance = this;

				if (!instance.isSupportBrowser) {
					return;
				}

				instance._classicUploaderButton.on(
					'click',
					function(event) {
						instance._handleSwitchUploader('classicUploader');
					}
				);

				instance._regularUploaderButton.on(
					'click',
					function(event) {
						instance._handleSwitchUploader('regularUploader');
					}
				);

				instance._selectButton.on(
					'click',
					function(event) {
						var fileDomNode = instance._fileInputField.getDOMNode();

						fileDomNode.click();
					}
				);

				instance._uploadArea.on('dragenter', instance._handleDragEnter, instance);
				instance._uploadArea.on('dragleave', instance._handleDragLeave, instance);
				instance._uploadArea.on('dragover', instance._handleDragOver, instance);
				instance._uploadArea.on('drop', instance._handleDrop, instance);

				instance._fileInputField.on('change', instance._handleSelectFiles, instance);
			},

			updateMessage: function(msg, type) {
				var instance = this;

				if (type == MSG_TYPE_ERROR) {
					var message = '<span class="message error-message">' + msg + '</span>';
				}
				else if (type == MSG_TYPE_PROGRESS) {
					var message = '<span class="message progress-message">' + msg + '</span>';
				}
				else if (type == MSG_TYPE_SUCCESS) {
					var message = '<span class="message success-message">' + msg + '</span>';
				}
				else if (type == MSG_TYPE_WARN) {
					var message = '<span class="message warn-message">' + msg + '</span>';
				}

				instance.messageContainer.setContent(message);
				instance.messageContainer.show();
			},

			_handleDragEnter: function(event) {
				event.preventDefault();

				event.returnValue = false;
			},

			_handleDragLeave: function(event) {
				var instance = this;

				instance._uploadArea.removeClass('dragging');
			},

			_handleDragOver: function(event) {
				event.preventDefault();

				event.returnValue = false;

				var instance = this;

				instance._uploadArea.addClass('dragging');
			},

			_handleDrop: function(event) {
				event.stopPropagation();

				event.preventDefault();

				var instance = this;

				instance._uploadArea.removeClass('dragging');

				instance.setupUploader(event._event.dataTransfer.files, event);
			},

			_handleSelectFiles: function(event) {
				var instance = this;

				var fileList = event.target.getDOMNode().files;

				instance.setupUploader(fileList, event);
			},

			_handleSwitchUploader: function(uploaderType) {
				var instance = this;

				instance.uploaderType.set('value', uploaderType);

				if (uploaderType == 'classicUploader') {
					instance.fallbackContainer.show();
					instance.uploadContainer.hide();

					instance._classicUploaderButton.hide();
					instance._regularUploaderButton.show();

					instance.updateMessage(instance.classicUploderMaxFileText, MSG_TYPE_WARN);
				}
				else {
					instance.fallbackContainer.hide();
					instance.uploadContainer.show();

					instance._classicUploaderButton.show();
					instance._regularUploaderButton.hide();

					instance.updateMessage(instance.newUploderMaxFileText, MSG_TYPE_WARN);
				}
			}
		};

		Liferay.DynamicUploader = DynamicUploader;
	},
	'',
	{
		requires: ['aui-base', 'aui-io', 'aui-template-deprecated', 'collection', 'liferay-portlet-base']
	}
);