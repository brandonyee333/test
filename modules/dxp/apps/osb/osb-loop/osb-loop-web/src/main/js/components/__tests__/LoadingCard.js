import LoadingCard from '../LoadingCard';

describe(
	'LoadingCard',
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
			'should render',
			() => {
				component = new LoadingCard();

				expect(component).toMatchSnapshot();
			}
		);
	}
);