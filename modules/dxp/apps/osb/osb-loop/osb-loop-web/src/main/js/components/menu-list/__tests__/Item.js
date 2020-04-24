import Item from '../Item';

describe(
	'Item',
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
				component = new Item();

				expect(component).toMatchSnapshot();
			}
		);
	}
);