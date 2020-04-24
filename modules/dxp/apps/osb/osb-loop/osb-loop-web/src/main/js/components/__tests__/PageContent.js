import PageContent from '../PageContent';

describe(
	'PageContent',
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
				component = new PageContent(
					{
						displayURL: 'testURL',
						message: 'test test message',
						title: 'test test title'
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);