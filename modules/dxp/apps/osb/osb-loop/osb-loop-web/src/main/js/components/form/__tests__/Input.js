import Input from '../Input';

import validateValue from '../../../lib/form-validators';

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
				component = new Input(
					{
						name: 'test'
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render if show_ and valid_ are true',
			() => {
				component = new Input(
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
			'should call `validateFormElement`',
			() => {
				component = new Input(
					{
						name: 'test'
					}
				);

				component.validateFormElement = jest.fn();

				component.handleBlur_();

				expect(component.validateFormElement).toHaveBeenCalled();
			}
		);

		it(
			'should call `validateFormElement` and set value state',
			() => {
				component = new Input(
					{
						name: 'test'
					}
				);

				component.state.show_ = true;

				component.validateFormElement = jest.fn();

				const eventObj = {
					target: {
						value: 'foo'
					}
				};

				component.handleInput_(eventObj);

				expect(component.state.value_).toBe('foo');
				expect(component.validateFormElement).toHaveBeenCalled();
			}
		);

		it(
			'should return promise, call cancel on existing validation, and call setState',
			() => {
				component = new Input(
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
						[
							{
								valid: true
							},
							{
								message: 'bar',
								valid: false
							},
							null
						]
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