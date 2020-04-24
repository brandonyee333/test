import EntityListCard from '../EntityListCard';

describe(
	'EntityListCard',
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
				component = new EntityListCard(
					{entities: []}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);