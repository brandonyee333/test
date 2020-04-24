import PrivatePostBar from '../PrivatePostBar';

describe(
	'PrivatePostBar',
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
				component = new PrivatePostBar();

				expect(component).toMatchSnapshot();
			}
		);
	}
);