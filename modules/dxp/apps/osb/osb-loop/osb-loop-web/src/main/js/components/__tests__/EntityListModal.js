import EntityListModal from '../EntityListModal';

describe(
	'EntityListModal',
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
				component = new EntityListModal({items: []});

				expect(component).toMatchSnapshot();
			}
		);
	}
);