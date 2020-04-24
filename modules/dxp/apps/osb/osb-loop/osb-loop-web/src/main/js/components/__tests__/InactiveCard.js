import InactiveCard from '../InactiveCard';

describe(
	'InactiveCard',
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
				component = new InactiveCard();

				expect(component).toMatchSnapshot();
			}
		);
	}
);