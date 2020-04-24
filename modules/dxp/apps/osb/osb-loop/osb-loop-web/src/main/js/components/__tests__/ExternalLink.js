import ExternalLink from '../ExternalLink';

describe(
	'ExternalLink',
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
				component = new ExternalLink();

				expect(component).toMatchSnapshot();
			}
		);
	}
);