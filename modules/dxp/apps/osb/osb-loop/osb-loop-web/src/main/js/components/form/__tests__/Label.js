import Label from '../Label';

describe(
	'Label',
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
				component = new Label({text: 'foo'});

				expect(component).toMatchSnapshot();
			}
		);
	}
);