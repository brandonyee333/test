import Select from '../Select';

import validateValue from '../../../lib/form-validators';

describe(
	'Select',
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
				component = new Select(
					{
						name: 'test'
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render with an initial value and reset value with new initial value',
			() => {
				component = new Select(
					{
						initialValue: 'foo',
						name: 'test'
					}
				);

				expect(component.state.value_).toBe('foo');

				component.props.initialValue = 'bar';

				jest.runAllTimers();

				expect(component.state.value_).toBe('bar');
			}
		);

		it(
			'should render with an error message',
			() => {
				component = new Select(
					{
						name: 'test'
					}
				);

				component.setState(
					{
						show_: true,
						valid_: false
					}
				);

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should set value and call validateFormElement',
			() => {
				component = new Select(
					{
						name: 'test'
					}
				);

				const event = {
					target: {
						value: 'foo'
					}
				};

				component.validateFormElement = jest.fn();

				component.handleChange_(event);

				expect(component.state.value_).toBe('foo');
				expect(component.validateFormElement).toHaveBeenCalled();
			}
		);

		it(
			'should return promise, call cancel on existing validation, and call setState',
			() => {
				component = new Select(
					{
						name: 'test',
						validator: 'foo'
					}
				);

				component.setState = jest.fn();

				const cancelSpy = jest.fn();

				component._validateRequest = {
					cancel: cancelSpy
				};

				validateValue.mockImplementation(
					() => Promise.resolve(
						{
							message: 'test',
							valid: true
						}
					)
				);

				return component.validateFormElement().then(
					() => {
						expect(cancelSpy).toHaveBeenCalled();
						expect(component.setState).toHaveBeenCalled();
					}
				);
			}
		);
	}
);