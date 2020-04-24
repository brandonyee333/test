import Hotkey from '../HotKey';

describe(
	'Hotkey',
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
				component = new Hotkey(
					{
						shortcut: {
							definition: 'test',
							keys: 't'
						}
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);