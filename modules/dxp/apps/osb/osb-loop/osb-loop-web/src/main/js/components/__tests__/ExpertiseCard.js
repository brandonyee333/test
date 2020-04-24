import {ExpertiseCard} from '../ExpertiseCard';

describe(
	'ExpertiseCard',
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
				component = new ExpertiseCard(
					{
						editURL: null,
						loading: false,
						seeMoreURL: '#',
						topics: []
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);