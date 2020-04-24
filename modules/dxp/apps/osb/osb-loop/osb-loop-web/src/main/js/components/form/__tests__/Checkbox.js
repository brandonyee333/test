import Checkbox from '../Checkbox';

describe(
	'Checkbox',
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
				component = new Checkbox(
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
				component = new Checkbox(
					{
						name: 'test'
					}
				);

				component.state.value_ = false;

				component.handleChange_();

				expect(component.state.value_).toBe(true);
			}
		);
	}
);