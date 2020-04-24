import InputList from '../InputList';

describe(
	'InputList',
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
				component = new InputList(
					{
						name: 'test'
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should set state value',
			() => {
				component = new InputList(
					{
						name: 'test'
					}
				);

				component.state.value_ = false;

				component.handleChange_(true);

				expect(component.state.value_).toBe(true);
			}
		);
	}
);