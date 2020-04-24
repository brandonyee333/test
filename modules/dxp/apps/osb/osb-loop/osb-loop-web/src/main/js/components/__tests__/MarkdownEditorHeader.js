import {noop} from 'lodash';

import MarkdownEditorHeader from '../MarkdownEditorHeader';

describe(
	'MarkdownEditorHeader',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new MarkdownEditorHeader(
					{
						onEditClick: noop,
						onPreviewClick: noop
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders as disabled',
			() => {
				component = new MarkdownEditorHeader(
					{
						disabled: true,
						onEditClick: noop,
						onPreviewClick: noop
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders as preview',
			() => {
				component = new MarkdownEditorHeader(
					{
						onEditClick: noop,
						onPreviewClick: noop,
						preview: true
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);