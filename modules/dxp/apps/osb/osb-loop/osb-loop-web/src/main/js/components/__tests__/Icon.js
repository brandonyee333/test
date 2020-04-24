import Icon from '../Icon';

describe(
	'Icon',
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
				component = new Icon();

				expect(component).toMatchSnapshot();
			}
		);
	}
);