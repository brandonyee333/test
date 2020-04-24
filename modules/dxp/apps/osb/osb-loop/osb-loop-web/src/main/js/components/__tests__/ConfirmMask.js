import ConfirmMask from '../ConfirmMask';

describe(
	'ConfirmMask',
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
				component = new ConfirmMask();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'Confirm Mask to only be shown when active is true.',
			() => {
				component = new ConfirmMask();

				let confirmMask = component.element.querySelector('.confirm-mask');

				expect(confirmMask).toBeFalsy();

				component.props.active = true;

				jest.runAllTimers();

				confirmMask = component.element.querySelector('.confirm-mask');

				expect(confirmMask).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);
	}
);