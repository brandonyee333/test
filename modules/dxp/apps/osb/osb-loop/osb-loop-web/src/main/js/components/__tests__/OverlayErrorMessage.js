import OverlayErrorMessage from '../OverlayErrorMessage';

describe(
	'OverlayErrorMessage',
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
				component = new OverlayErrorMessage({message: 'test test'});

				expect(component).toMatchSnapshot();
			}
		);
	}
);