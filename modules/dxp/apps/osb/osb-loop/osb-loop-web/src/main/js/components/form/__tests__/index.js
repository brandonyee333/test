import Form from '../index';

describe(
	'Form',
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
				component = new Form();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should handle form submit',
			() => {
				component = new Form(
					{
						validateOnSubmit: true
					}
				);

				const event = {
					preventDefault: jest.fn()
				};

				component.validate = jest.fn(() => true);

				component.handleSubmit_(event);

				expect(event.preventDefault).not.toHaveBeenCalled();

				component.validate = jest.fn(() => false);

				component.handleSubmit_(event);

				expect(event.preventDefault).toHaveBeenCalled();
			}
		);

		it(
			'should return resolved promise',
			() => {
				component = new Form();

				component.getValues = jest.fn(() => true);

				const elementFoo = {
					validateFormElement: () => Promise.resolve('foo')
				};

				component._formElements = [elementFoo];

				return component.validate().then(
					result => {
						expect(result).toBe(true);
					}
				);
			}
		);

		it(
			'should return resolved promise',
			() => {
				component = new Form();

				const elementFoo = {
					validateFormElement: () => Promise.resolve('')
				};

				component._formElements = [elementFoo];

				return component.validate().catch(
					result => {
						expect(result).toBe('');
					}
				);
			}
		);
	}
);