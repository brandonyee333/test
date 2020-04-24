import Textarea from '../Textarea';

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
				component = new Textarea(
					{
						initialValue: 'foo',
						name: 'test'
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render error message',
			() => {
				component = new Textarea(
					{
						initialValue: 'foo',
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
			'should update value when initialValue changes',
			() => {
				component = new Textarea(
					{
						initialValue: 'foo',
						name: 'test'
					}
				);

				component.props.initialValue = 'bar';

				jest.runAllTimers();

				expect(component.state.value_).toBe('bar');
			}
		);

		it(
			'should call validateFormElement_',
			() => {
				component = new Textarea(
					{
						initialValue: 'foo',
						name: 'test'
					}
				);

				component.validateFormElement_ = jest.fn();

				component.handleBlur_();

				expect(component.validateFormElement_).toHaveBeenCalled();
			}
		);

		it(
			'should call validateFormElement_ and set state value',
			() => {
				component = new Textarea(
					{
						initialValue: 'foo',
						name: 'test'
					}
				);

				component.state.show_ = true;

				component.validateFormElement_ = jest.fn();

				const eventObj = {
					target: {
						value: 'bar'
					}
				};

				component.handleInput_(eventObj);

				expect(component.state.value_).toBe('bar');
				expect(component.validateFormElement_).toHaveBeenCalled();
			}
		);

		it(
			'should return promise, call cancel on existing validation, and call setState',
			() => {
				component = new Textarea(
					{
						initialValue: 'foo',
						name: 'test',
						validator: 'bar'
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

				return component.validateFormElement_().then(
					() => {
						expect(cancelSpy).toHaveBeenCalled();
						expect(component.setState).toHaveBeenCalled();
					}
				);
			}
		);
	}
);