var resumable = new Resumable(
	{
		chunkSize: 25 * 1024 * 1024,
		headers: {
			'Content-Type': 'multipart/form-data;charset=utf-8'
		},
		maxFiles: 1,
		maxFileSize: 35 * 1024 * 1024 * 1024,
		method: 'octet',
		query: function(resumableFile, resumableChunk) {
			var token = A.one("#<portlet:namespace />token");

			queryParam = {
				cmd: "update",
				token: token.get("value")
			};

			return queryParam;
		},
		simultaneousUploads: 10,
		target: '<%= FileRepositoryUtil.getActionURL(PortletPropsValues.FILE_REPOSITORY_SERVERS[0], FileRepositoryUtil.PATH_UPLOAD) %>',
		testChunks: true,
		throttleProgressCallbacks: 1
	}
);

if (!resumable.support) {
	<portlet:namespace />updateMessage("invalid-browser");
}
else {
	A.one('#<portlet:namespace />uploadContainer').show();

	resumable.assignDrop(document.getElementById('<portlet:namespace />uploadArea'));
	resumable.assignBrowse(document.getElementById('<portlet:namespace />selectButton'));

	resumable.on(
		'fileAdded',
		function(file) {
			<portlet:namespace />generateToken(resumable, false);

			A.one('#<portlet:namespace />resumableFile').set("value", null);

			A.one('#<portlet:namespace />messageDisplay').hide();

			A.one('#<portlet:namespace />fileName').setContent(file.fileName);

			A.one('#<portlet:namespace />fileInfo').show();

			A.one('#<portlet:namespace />pauseButton').on(
				'click',
				function() {
					resumable.pause();
				}
			);

			A.one('#<portlet:namespace />resumeButton').on(
				'click',
				function() {
					A.one('#<portlet:namespace />pauseButton').show();
					A.one('#<portlet:namespace />resumeButton').hide();

					resumable.upload();
				}
			);

			A.one('#<portlet:namespace />toolbar').show();
		}
	);

	resumable.on(
		'fileError',
		function(file, message) {
			var response = A.JSON.parse(message);

			A.one('#<portlet:namespace />toolbar').hide();

			<portlet:namespace />updateMessage(response.message);
		}
	);

	resumable.on(
		'fileProgress',
		function(file) {
			var percentLoaded = Math.floor(file.progress() * 100);

			A.one('#<portlet:namespace />progress').setStyle('width', percentLoaded + '%');
		}
	);

	resumable.on(
		'fileSuccess',
		function(file, message) {
			var response = A.JSON.parse(message);

			A.one('#<portlet:namespace />toolbar').hide();

			if ((response.message == "complete") || (response.message == "file-exists")) {
				A.one('#<portlet:namespace />resumableFile').set("value", JSON.stringify(response.file));

				<portlet:namespace />updateMessage(response.message);

				submitForm(document.<portlet:namespace />fm);
			}
			else {
				A.one('#<portlet:namespace />resumableFile').set("value", null);
			}
		}
	);

	resumable.on(
		'pause',
		function() {
			A.one('#<portlet:namespace />pauseButton').hide();
			A.one('#<portlet:namespace />resumeButton').show();
		}
	);
}