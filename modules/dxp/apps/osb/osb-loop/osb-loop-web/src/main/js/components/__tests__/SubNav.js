import SubNav from '../SubNav';

describe(
	'SubNav',
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
				component = new SubNav();

				expect(component).toMatchSnapshot();
			}
		);
	}
);