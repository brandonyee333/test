import Test from '../Test';

describe(
	'Test',
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
				component = new Test();

				expect(component).toMatchSnapshot();
			}
		);
	}
);