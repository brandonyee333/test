import dom from 'metal-dom';

import Option from '../Option';

describe(
	'Option',
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
				component = new Option();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call onClick',
			() => {
				const spy = jest.fn();

				component = new Option(
					{
						onChange: spy
					}
				);

				dom.triggerEvent(component.element, 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);
	}
);