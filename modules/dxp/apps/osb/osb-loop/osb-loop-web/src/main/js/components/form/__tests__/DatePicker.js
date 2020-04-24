import DatePicker, {getDateObject} from '../DatePicker';

describe(
	'DatePicker',
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
				component = new DatePicker({name: 'foo'});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders as disabled',
			() => {
				component = new DatePicker({disabled: true, name: 'foo'});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should set state value',
			() => {
				component = new DatePicker({name: 'foo'});

				component._datePicker = 'test';

				component.handleChange_();

				expect(component.state.value_).toBe('test');
			}
		);

		it(
			'should return date object',
			() => {
				expect(getDateObject(0, 'test')).toEqual({testDay: 31, testMonth: 11, testYear: 1969});
			}
		);
	}
);