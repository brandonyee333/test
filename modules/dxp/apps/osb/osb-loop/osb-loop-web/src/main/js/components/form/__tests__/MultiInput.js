import MultiInput from '../MultiInput';

describe(
	'MultiInput',
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
				component = new MultiInput(
					{
						initialValue: ['baz'],
						label: 'qux',
						name: 'foo',
						selector: 'bar',
						types: ['test']
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call preventDefault and decrement input count',
			() => {
				component = new MultiInput({name: 'foo', selector: 'bar'});

				component.state.inputCount_ = 3;

				const eventObj = {
					preventDefault: jest.fn()
				};

				component.handleDecrement_(eventObj);

				expect(component.state.inputCount_).toBe(2);
				expect(eventObj.preventDefault).toHaveBeenCalled();
			}
		);

		it(
			'should call preventDefault and increment input count',
			() => {
				component = new MultiInput({name: 'foo', selector: 'bar'});

				component.state.inputCount_ = 3;

				const eventObj = {
					preventDefault: jest.fn()
				};

				component.handleIncrement_(eventObj);

				expect(component.state.inputCount_).toBe(4);
				expect(eventObj.preventDefault).toHaveBeenCalled();
			}
		);
	}
);