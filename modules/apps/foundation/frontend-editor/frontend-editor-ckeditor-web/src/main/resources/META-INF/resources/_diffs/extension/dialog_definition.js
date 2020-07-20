CKEDITOR.on(
	'dialogDefinition',
	function(event) {
		if (event.editor === ckEditor) {
			var boundingWindow = event.editor.window;

			var dialogDefinition = event.data.definition;

			var onShow = dialogDefinition.onShow;

			var dialog;

			dialogDefinition.onShow = function() {
				dialog = this;

				if (typeof onShow === 'function') {
					onShow.apply(this, arguments);
				}

				centerDialog();
			};

			var centerDialog = function () {
				var instance = dialog;

				var editorElement = instance.getParentEditor().container;

				var documentPosition = editorElement.getLast().getDocumentPosition();

				var dialogSize = instance.getSize();

				var x =
					documentPosition.x +
					((editorElement.getLast().getSize('width', true) - dialogSize.width) /
						2 -
						window.scrollX);
				var y =
					documentPosition.y +
					((editorElement.getLast().getSize('height', true) -
						dialogSize.height) /
						2 -
						window.scrollY);

				instance.move(x, y, false);
			};

			AUI().use('aui-debounce', A => {
				boundingWindow.on(
					'resize',
					A.debounce(() => {
						centerDialog();
					}, 250)
				);
			});
		}
	}
);