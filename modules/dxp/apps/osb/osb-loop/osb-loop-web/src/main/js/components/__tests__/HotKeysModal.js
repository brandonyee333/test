import HotKeysModal from '../HotKeysModal';

describe(
	'HotKeysModal',
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
				component = new HotKeysModal();

				expect(component).toMatchSnapshot();
			}
		);
	}
);