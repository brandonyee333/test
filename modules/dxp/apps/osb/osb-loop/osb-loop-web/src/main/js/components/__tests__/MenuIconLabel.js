import MenuIconLabel from '../MenuIconLabel';

describe(
	'MenuIconLabel',
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
				component = new MenuIconLabel(
					{
						label: 'test label',
						name: 'tests'
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);