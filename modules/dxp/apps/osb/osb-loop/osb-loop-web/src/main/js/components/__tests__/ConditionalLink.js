import ConditionalLink from '../ConditionalLink';

describe(
	'ConditionalLink',
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
				component = new ConditionalLink();

				expect(component).toMatchSnapshot();
			}
		);
	}
);