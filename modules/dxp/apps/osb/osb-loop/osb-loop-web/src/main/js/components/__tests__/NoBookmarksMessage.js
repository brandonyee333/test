import NoBookmarksMessage from '../NoBookmarksMessage';

describe(
	'NoBookmarksMessage',
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
				component = new NoBookmarksMessage();

				expect(component).toMatchSnapshot();
			}
		);
	}
);