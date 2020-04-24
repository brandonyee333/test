import FormComponent from '../FormComponent';

describe(
	'FormComponent',
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
				component = new FormComponent({name: 'foo'});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call processValue and return processed value',
			() => {
				const processValue = jest.fn(item => item);

				component = new FormComponent(
					{
						name: 'foo',
						processValue
					}
				);

				component.state.value_ = 'bar';

				const retVal = component.getFormElementValue();

				expect(retVal).toBe('bar');
				expect(processValue).toHaveBeenCalledWith('bar', 'foo');
			}
		);
	}
);