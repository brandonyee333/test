import EntityPillList from '../EntityPillList';

describe(
	'EntityPillList',
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
				component = new EntityPillList(
					{
						entities: []
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);