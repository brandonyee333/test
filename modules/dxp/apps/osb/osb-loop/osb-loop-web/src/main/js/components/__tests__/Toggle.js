import dom from 'metal-dom';

import Toggle from '../Toggle';

describe(
	'Toggle',
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
				component = new Toggle();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'calls onChange',
			() => {
				const spy = jest.fn();

				component = new Toggle(
					{
						onChange: spy
					}
				);

				dom.triggerEvent(component.element.querySelector('.toggle-input'), 'change');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);
	}
);