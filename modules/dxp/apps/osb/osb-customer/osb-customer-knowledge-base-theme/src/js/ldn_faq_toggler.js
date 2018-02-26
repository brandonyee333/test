AUI().ready(
	'aui-base',
	function(A) {
		var kbEntityBody = A.one('.kb-entity-body');

		if (kbEntityBody) {
			kbEntityBody.delegate(
				'click',
				function(event) {
					event.stopPropagation();

					var question = event.currentTarget;
					var questionIcon = question.one('span');

					if (question && questionIcon) {
						var answer = question.next();
						var show = question.getAttribute('data-show');

						if (show == 'true') {
							answer.addClass('hide');

							question.attr('data-show', 'false');
							question.setStyle('fontWeight', 'normal');

							questionIcon.removeClass('icon-caret-down');
							questionIcon.addClass('icon-caret-right');
						}
						else {
							answer.removeClass('hide');

							question.attr('data-show', 'true');
							question.setStyle('fontWeight', 'bold');

							questionIcon.removeClass('icon-caret-right');
							questionIcon.addClass('icon-caret-down');
						}
					}
				},
				'.ldn-faq-toggle-button'
			);
		}
	}
);