import PostContent from '../PostContent';

describe(
	'PostContent',
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
				component = new PostContent();

				expect(component).toMatchSnapshot();
			}
		);
	}
);