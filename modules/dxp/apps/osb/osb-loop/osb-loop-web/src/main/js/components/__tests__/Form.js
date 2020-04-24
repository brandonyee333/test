import Component, {Config} from 'metal-jsx';

import Form from '../form';
import FormComponent from '../form/FormComponent';

describe(
	'Form',
	() => {
		let component;

		let valid = false;

		class TestInput extends FormComponent {
			getFormElementValue() {
				return this.props.value;
			}

			validateFormElement() {
				return valid;
			}

			render() {
				return <div />;
			}
		}

		TestInput.PROPS = {
			value: Config.string().required
		};

		class TestForm extends Component {
			render() {
				return (
					<Form ref="form">
						<TestInput name="foo" value="baz" />

						<TestInput name="bar" value="biz" />
					</Form>
				);
			}
		}

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
			'should validate fields',
			() => {
				component = new TestForm();

				const formComponent = component.components.form;

				formComponent.validate().then(
					res => expect(res).toBe(valid)
				);

				valid = !valid;

				formComponent.validate().then(
					res => expect(res).toBe(valid)
				);
			}
		);

		it(
			'should return values for form',
			() => {
				component = new TestForm();

				const values = component.components.form.getValues();

				expect(values.foo).toBe('baz');
				expect(values.bar).toBe('biz');
			}
		);
	}
);