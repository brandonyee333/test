AUI().add(
	'attachment-upload-html5',
	function(A) {

		/**
		 * OPTIONS
		 *
		 * Required
		 * dragAndDropNode {string|object}: A selector or node of the area designated to drag and drop files.
		 * fileList {string|object}: A selector or node of the area to populate upload results
		 * resumableUploader {object}: An instance of Resumable from Resumable.js.
		 * selectButton {string|object}: A selector or node of the select button.
		 * uploadURL {string}: The URL to where the file will be uploaded.
		 */

		var AttachmentUploadHTML5 = function(options) {
			var instance = this;

			options = options || {};

			instance._dragAndDropNode = A.one(options.dragAndDropNode);
			instance._fileAddErrorCallback = options.fileAddError;
			instance._fileList = A.one(options.fileList);
			instance._fileType = options.fileType || '*';
			instance._fileTypeErrorMessage = options.fileTypeErrorMessage || '[$FILE_NAME$] is not a valid file type. Valid extensions are [$EXTENSIONS$].';
			instance._fileTypeErrorMessageExtensionPlaceholder = options.fileTypeErrorMessageExtensionPlaceholder || '[$EXTENSIONS$]';
			instance._fileTypeErrorMessageFileNamePlaceholder = options.fileTypeErrorMessageFileNamePlaceholder || '[$FILE_NAME$]';
			instance._ie6 = Liferay.Browser.isIe() && (Liferay.Browser.getMajorVersion() < 7);
			instance._langCancel = Liferay.Language.get('cancel');
			instance._langDelete = Liferay.Language.get('delete');
			instance._namespaceId = '_osb_attachment_' + Liferay.Util.randomInt() + '_';
			instance._resumableUploader = options.resumableUploader;
			instance._selectButton = A.one(options.selectButton);
			instance._shortFileName = options.shortFileName || false;
			instance._singleFileUpload = options.singleFileUpload || false;
			instance._uploadSuccessCallback = options.uploadSuccess;

			instance._setupCallbacks();
			instance._setupEvents();
		};

		AttachmentUploadHTML5.prototype = {
			fileAdded: function(resumableFile) {
				var instance = this;

				var fileName = resumableFile.fileName;

				if (fileName) {
					var extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

					if ((AUI().Array.indexOf(instance._fileType, extension) == -1)) {
						resumableFile.cancel();

						var message = instance._fileTypeErrorMessage;

						message = message.replace(instance._fileTypeErrorMessageExtensionPlaceholder, instance._fileType.join(', '));
						message = message.replace(instance._fileTypeErrorMessageFileNamePlaceholder, fileName);

						alert(message);

						return;
					}
				}

				var fileId = instance._namespace(resumableFile.uniqueIdentifier);

				instance._resumableUploader.upload();

				if (instance._shortFileName && (fileName.length > 35)) {
					fileName = fileName.substring(0, 32) + "..";
				}

				var li = A.Node.create(
					'<li class="file upload-file" id="' + fileId + '">' +
						'<span class="actions loading-animation">' +
							'<a class="action cancel-button" href="javascript:;" id="' + fileId + 'cancelButton">' + instance._langCancel + '</a>' +
						'</span>' +
						'<span class="title">' + fileName + '</span>' +
						'<span class="progress-bar">' +
							'<span class="progress" id="' + fileId + 'progress"></span>' +
						'</span>' +
					'</li>');

				var cancelButton = li.all('.cancel-button');

				if (cancelButton) {
					cancelButton.on(
						'click',
						function() {
							instance.fileCancelled(resumableFile);
						}
					);
				}

				if (instance._singleFileUpload) {
					instance._fileList.setContent(li);
				}
				else {
					instance._fileList.append(li);
				}
			},

			fileAddError: function(resumableFile, errorCode, message) {
				var instance = this;

				if (instance._fileAddErrorCallback) {
					return instance._fileAddErrorCallback(resumableFile, errorCode, message);
				}
			},

			fileCancelled: function(resumableFile) {
				var instance = this;

				instance._resumableUploader.removeFile(resumableFile);

				var fileId = instance._namespace(resumableFile.uniqueIdentifier);

				var elem = A.one('#' + fileId);

				var actionsContainer = elem.one('.actions');

				var messageNode = A.Node.create('<div class="msg">' + Liferay.Language.get('upload-cancelled') + '</div>');

				elem.addClass('error');

				elem.append(messageNode);

				actionsContainer.remove();

				return;
			},

			fileUploadComplete: function(resumableFile) {
				var instance = this;

				var li = A.one('#' + instance._namespace(resumableFile.uniqueIdentifier));

				if (li) {
					li.removeClass('uploading');
					li.removeClass('upload-file');
				}
			},

			uploadError: function(resumableFile, errorCode, message) {
				var instance = this;

				var li = A.one('#' + instance._namespace(resumableFile.uniqueIdentifier));

				if (errorCode === -280) {
					li.remove(true);
				}
				else {
					li.addClass('error');
				}
			},

			uploadProgress: function(resumableFile, bytesLoaded) {
				var instance = this;

				var li = A.one('#' + instance._namespace(resumableFile.uniqueIdentifier));

				if (li && !li.hasClass('uploading')) {
					li.addClass('uploading');
				}
			},

			uploadStart: function(resumableFile) {
				var instance = this;

				var li = A.one('#' + instance._namespace(resumableFile.uniqueIdentifier));

				if (li) {
					li.addClass('uploading');
				}
			},

			uploadSuccess: function(resumableFile, data) {
				var instance = this;

				try {
					data = A.JSON.parse(data);
				}
				catch (e) {
					data = {};
				}

				var li = A.one('#' + instance._namespace(resumableFile.uniqueIdentifier));

				if (instance._uploadSuccessCallback) {
					instance._uploadSuccessCallback(resumableFile, data, li);
				}
				else if (li) {
					var actionsContainer = li.one('.actions');

					if (data.assetAttachmentId) {
						li.attr('data-assetAttachmentId', data.assetAttachmentId);

						var actions = A.Node.create('<a class="action delete" href="javascript:;">' + instance._langDelete + '</a>');

						actionsContainer.setContent(actions);

						instance.fileUploadComplete(resumableFile);
					}
					else {
						li.addClass('error');

						actionsContainer.empty();
					}
				}

				var deleteButton = li.all('.delete');

				if (deleteButton) {
					deleteButton.on(
						'click',
						function() {
							instance._resumableUploader.removeFile(resumableFile);
						}
					);
				}
			},

			_namespace: function(str) {
				return this._namespaceId + (str || '');
			},

			_setupCallbacks: function() {
				var instance = this;

				instance._fileAdded = instance._namespace('fileAdded');
				instance._fileAddError = instance._namespace('fileAddError');
				instance._fileCancelled = instance._namespace('fileCancelled');
				instance._uploadStart = instance._namespace('uploadStart');
				instance._uploadProgress = instance._namespace('uploadProgress');
				instance._uploadError = instance._namespace('uploadError');
				instance._uploadSuccess = instance._namespace('uploadSuccess');
				instance._fileUploadComplete = instance._namespace('fileUploadComplete');

				window[instance._fileAdded] = function() {
					instance.fileAdded.apply(instance, arguments);
				};

				window[instance._fileAddError] = function() {
					instance.fileAddError.apply(instance, arguments);
				};

				window[instance._fileCancelled] = function() {
					instance.fileCancelled.apply(instance, arguments);
				};

				window[instance._uploadStart] = function() {
					instance.uploadStart.apply(instance, arguments);
				};

				window[instance._uploadProgress] = function() {
					instance.uploadProgress.apply(instance, arguments);
				};

				window[instance._uploadError] = function() {
					instance.uploadError.apply(instance, arguments);
				};

				window[instance._fileUploadComplete] = function() {
					instance.fileUploadComplete.apply(instance, arguments);
				};

				window[instance._uploadSuccess] = function() {
					instance.uploadSuccess.apply(instance, arguments);
				};
			},

			_setupEvents: function() {
				var instance = this;

				if (instance._selectButton) {
					instance._resumableUploader.assignBrowse(instance._selectButton.getDOM());
				}

				if (instance._dragAndDropNode) {
					instance._resumableUploader.assignDrop(instance._dragAndDropNode.getDOM());

					instance._dragAndDropNode.on(
						'dragover',
						function() {
							instance._dragAndDropNode.addClass('upload-file-hover');
						}
					);

					instance._dragAndDropNode.on(
						'dragleave',
						function() {
							instance._dragAndDropNode.removeClass('upload-file-hover');
						}
					);

					instance._dragAndDropNode.on(
						'drop',
						function() {
							instance._dragAndDropNode.removeClass('upload-file-hover');
						}
					);
				}

				instance._resumableUploader.on(
					'fileAdded',
					function(resumableFile) {
						instance.fileAdded(resumableFile);
					}
				);

				instance._resumableUploader.on(
					'fileError',
					function(resumableFile) {
						instance.fileAddError(resumableFile, null, 'fileError');

						instance._resumableUploader.removeFile(resumableFile);
					}
				);

				instance._resumableUploader.on(
					'fileProgress',
					function(resumableFile) {
						instance.uploadProgress(resumableFile, resumableFile.progress());
					}
				);

				instance._resumableUploader.on(
					'fileSuccess',
					function(resumableFile, message) {
						var fileId = instance._namespace(resumableFile.uniqueIdentifier);

						var elem = document.querySelector('#' + fileId);

						instance.uploadSuccess(resumableFile, message, elem);
					}
				)
			}
		}

		Liferay.AttachmentUploadHTML5 = AttachmentUploadHTML5;
	},
	'',
	{
		requires: ['aui-base', 'json-parse']
	}
);