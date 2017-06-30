AUI().add(
	'attachment-upload',
	function(A) {

		/**
		 * OPTIONS
		 *
		 * Required
		 * selectButton {string|object}: A selector or node of the select button.
		 * uploadButton {string|object}: A selector or node of the upload button.
		 * fileSize {number}: The maximum file size that can be uploaded.
		 * fileTypes {string}: A comma separated list of accepted file types.
		 * uploadURL {string}: The URL to where the file will be uploaded.
		 */

		var AttachmentUpload = function(options) {
			var instance = this;

			options = options || {};

			instance._autoUpload = options.autoUpload || false;
			instance._fileSize = options.fileSize;
			instance._fileTypes = options.fileTypes;
			instance._shortFileName = options.shortFileName || false;
			instance._singleFileUpload = options.singleFileUpload || false;

			var randomNamespace = Liferay.Util.randomInt();

			instance._swfId = options.swfId || randomNamespace;

			instance._fileAddErrorCallback = options.fileAddError;
			instance._uploadSuccessCallback = options.uploadSuccess;
			instance._uploadURL = options.uploadURL;

			instance._fileList = A.one(options.fileList);
			instance._selectButton = A.one(options.selectButton);
			instance._uploadButton = A.one(options.uploadButton);

			instance._namespaceId = '_osb_attachment_' + randomNamespace + '_';

			instance._ie6 = Liferay.Browser.isIe() && (Liferay.Browser.getMajorVersion() < 7);

			instance._langCancel = Liferay.Language.get('cancel');
			instance._langDelete = Liferay.Language.get('delete');

			instance._setupCallbacks();
			instance._setupUploader();
			instance._setupEvents();
		};

		AttachmentUpload.prototype = {
			cancelUploads: function() {
			},

			fileAdded: function(file) {
				var instance = this;

				var fileId = instance._namespace(file.id);

				var fileName = file.name;

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
							instance._uploader.cancelUpload(file.id);
						}
					);
				}

				if (instance._singleFileUpload) {
					instance._fileList.setContent(li);
				}
				else {
					instance._fileList.append(li);
				}

				if (instance._autoUpload) {
					var stats = instance._uploader.getStats();

					if (stats.files_queued > 0) {
						instance._uploader.startUpload();
					}
				}
				else {
					instance._uploadButton.show();
				}

				instance.repositionMovie();
			},

			fileAddError: function(file, errorCode, message) {
				var instance = this;

				if (instance._fileAddErrorCallback) {
					return instance._fileAddErrorCallback(file, errorCode, message);
				}
			},

			fileCancelled: function(file, errorCode, message) {
			},

			fileUploadComplete: function(file) {
				var instance = this;

				var li = A.one('#' + instance._namespace(file.id));

				if (li) {
					li.removeClass('uploading');
					li.removeClass('upload-file');
				}

				var stats = instance._uploader.getStats();

				if (stats.files_queued > 0) {
					instance._uploader.startUpload();
				}
			},

			flashLoaded: function() {
				var instance = this;

				if (instance._initiated) {
					return;
				}

				instance.repositionMovie();

				if (!instance._ie6) {
					var calculateOffsetTask = new A.DelayedTask(
						function() {
							instance.repositionMovie();
						}
					);

					var onWindowChange = function(event) {
						calculateOffsetTask.delay(200);
					};

					var win = A.getWin();

					win.on('scroll', onWindowChange);
					win.on('resize', onWindowChange);
				}

				instance._initiated = true;
			},

			getUploaderId: function() {
				var instance = this;

				return instance._uploader.getMovieElement().id;
			},

			repositionMovie: function() {
				var instance = this;

				var selectButton = instance._selectButton;

				var buttonWidth = selectButton.get('offsetWidth');
				var buttonHeight = selectButton.get('offsetHeight');

				var buttonOffset = selectButton.getXY();

				var deltaX = 0;
				var deltaY = 0;

				if (!instance._ie6) {
					deltaX = A.DOM.docScrollX();
					deltaY = A.DOM.docScrollY();
				}

				var regionStyles = {
					left: buttonOffset[0] - deltaX,
					top: buttonOffset[1] - deltaY
				};

				instance._movieContentBox.setStyles(regionStyles);

				try {
					instance._uploader.setButtonDimensions(buttonWidth, buttonHeight);
				}
				catch (e) {
				}
			},

			setUploadURL: function(url) {
				var instance = this;

				instance._uploadURL = url;

				instance._uploader.setUploadURL(url);
			},

			uploadError: function(file, errorCode, message) {
				var instance = this;

				var li = A.one('#' + instance._namespace(file.id));

				if (errorCode === -280) {
					li.remove(true);
				}
				else {
					li.addClass("error");
				}
			},

			uploadProgress: function(file, bytesLoaded) {
			},

			uploadsComplete: function(file) {
			},

			uploadStart: function(file) {
				var instance = this;

				var li = A.one('#' + instance._namespace(file.id));

				if (li) {
					li.addClass('uploading');
				}
			},

			uploadSuccess: function(file, data) {
				var instance = this;

				try {
					data = A.JSON.parse(data);
				}
				catch (e) {
					data = {};
				}

				var li = A.one('#' + instance._namespace(file.id));

				if (instance._uploadSuccessCallback) {
					return instance._uploadSuccessCallback(file, data, li);
				}

				if (li) {
					var actionsContainer = li.one('.actions');

					if (data.assetAttachmentId) {
						li.attr('data-assetAttachmentId', data.assetAttachmentId);

						var actions = A.Node.create('<a class="action delete" href="javascript:;">' + instance._langDelete + '</a>');

						actionsContainer.setContent(actions);
					}
					else {
						li.addClass('error');

						actionsContainer.empty();
					}
				}
			},

			_namespace: function(str) {
				return this._namespaceId + (str || '');
			},

			_setupCallbacks: function() {
				var instance = this;

				instance._cancelUploads = instance._namespace('cancelUploads');
				instance._fileAdded = instance._namespace('fileAdded');
				instance._fileAddError = instance._namespace('fileAddError');
				instance._fileCancelled = instance._namespace('fileCancelled');
				instance._flashLoaded = instance._namespace('flashLoaded');
				instance._uploadStart = instance._namespace('uploadStart');
				instance._uploadProgress = instance._namespace('uploadProgress');
				instance._uploadError = instance._namespace('uploadError');
				instance._uploadSuccess = instance._namespace('uploadSuccess');
				instance._fileUploadComplete = instance._namespace('fileUploadComplete');
				instance._uploadsComplete = instance._namespace('uploadsComplete');
				instance._uploadsCancelled = instance._namespace('uploadsCancelled');

				instance._swfUpload = instance._namespace('cancelUploads');

				window[instance._cancelUploads] = function() {
					instance.cancelUploads.apply(instance, arguments);
				};

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

				window[instance._uploadsComplete] = function() {
					instance.uploadsComplete.apply(instance, arguments);
				};

				window[instance._flashLoaded] = function() {
					instance.flashLoaded.apply(instance, arguments);
				};
			},

			_setupEvents: function() {
				var instance = this;

				if (instance._uploadButton) {
					instance._uploadButton.on(
						'click',
						function(event) {
							instance._uploader.startUpload();
						}
					);
				}
			},

			_setupUploader: function() {
				var instance = this;

				if (instance._fileTypes.indexOf('*') == -1) {
					var fileTypes = instance._fileTypes.split(',');

					fileTypes = A.Array.map(
						fileTypes,
						function(value, key) {
							var fileType = value;

							if (value.indexOf('*') == -1) {
								fileType = '*' + value;
							}
							return fileType;
						}
					);

					instance._fileTypes = fileTypes.join(';');
				}

				instance._uploader = new SWFUpload(
					{
						button_cursor: SWFUpload.CURSOR.HAND,
						button_placeholder_id: '',
						button_window_mode: 'transparent',
						file_post_name: 'file',
						file_queue_error_handler: window[instance._fileAddError],
						file_queued_handler: window[instance._fileAdded],
						file_size_limit : instance._fileSize,
						file_types : instance._fileTypes,
						flash_url: themeDisplay.getPathContext() + '/html/js/misc/swfupload/swfupload_f10.swf',
						swfupload_loaded_handler: window[instance._flashLoaded],
						upload_cancel_callback: window[instance._cancelUploads],
						upload_complete_handler: window[instance._fileUploadComplete],
						upload_error_handler: window[instance._uploadError],
						upload_file_cancel_callback: window[instance._fileCancelled],
						upload_progress_handler: window[instance._uploadProgress],
						upload_queue_complete_callback: window[instance._uploadsComplete],
						upload_start_handler: window[instance._uploadStart],
						upload_success_handler: window[instance._uploadSuccess],
						upload_url: instance._uploadURL
					}
				);

				instance._movieBoundingBox = A.Node.create('<div class="lfr-upload-movie" data-swfId="' + instance._swfId +'"><div class="lfr-upload-movie-content"></div></div>');
				instance._movieContentBox = instance._movieBoundingBox.get('firstChild');

				A.getBody().prepend(instance._movieBoundingBox);

				var movieElement = instance._uploader.getMovieElement();

				if (movieElement) {
					instance._movieContentBox.appendChild(movieElement);

					var defaultStyles = {
						zIndex: 100000
					};

					if (!(Liferay.Browser.isIe() && Liferay.Browser.getMajorVersion() < 7)) {
						defaultStyles.top = 0;
					}

					instance._movieContentBox.setStyles(defaultStyles);
				}
			}
		}

		Liferay.AttachmentUpload = AttachmentUpload;
	},
	'',
	{
		requires: ['aui-base', 'aui-swf', 'json-parse', 'swfupload']
	}
);