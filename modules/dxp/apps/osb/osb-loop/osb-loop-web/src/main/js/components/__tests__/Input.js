import Input from '../Input';

describe(
	'Input',
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
				component = new Input();

				expect(component).toBeTruthy();
			}
		);

		it(
			'calls focus when focusOnAttached is true',
			() => {
				component = new Input(
					{
						focusOnAttached: true
					}
				);

				const spy = jest.fn();

				component.focus = spy;

				component.attached();

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);

		it(
			'does not call focus when focusOnAttached is false',
			() => {
				component = new Input();

				const spy = jest.fn();

				component.focus = spy;

				component.attached();

				jest.runAllTimers();

				expect(spy).not.toBeCalled();
			}
		);
	}
);