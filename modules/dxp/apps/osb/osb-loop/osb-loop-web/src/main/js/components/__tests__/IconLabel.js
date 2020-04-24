import IconLabel from '../IconLabel';

describe(
	'IconLabel',
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
				component = new IconLabel();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders with a label',
			() => {
				const LABEL = 'test label';

				component = new IconLabel();

				expect(component).toMatchSnapshot();

				component.props.label = LABEL;

				jest.runAllTimers();

				const label = component.element.querySelector('.loop-icon-label');

				expect(label.innerHTML).toBe(LABEL);
			}
		);
	}
);