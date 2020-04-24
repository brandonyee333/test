import dom from 'metal-dom';

import Checkbox from '../Checkbox';

describe(
	'Checkbox',
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
				component = new Checkbox();

				expect(component).toMatchSnapshot();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'calls onChange',
			() => {
				const spy = jest.fn();

				component = new Checkbox(
					{
						onChange: spy
					}
				);

				dom.triggerEvent(component.element.querySelector('.checkbox-input'), 'change');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);
	}
);