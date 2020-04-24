import Component, {Config} from 'metal-jsx';

class FormComponent extends Component {
	attached() {
		const {ancestorForm} = this.context;

		if (ancestorForm) {
			ancestorForm.addFormElement(this);
		}
	}

	detached() {
		const {ancestorForm} = this.context;

		if (ancestorForm) {
			ancestorForm.removeFormElement(this);
		}
	}

	getFormElementName() {
		return this.props.name;
	}

	getFormElementValue() {
		const {name, processValue} = this.props;

		let {value_} = this.state;

		if (processValue) {
			value_ = processValue(value_, name);
		}

		return value_;
	}

	validateFormElement() {
		return true;
	}
}

FormComponent.PROPS = {
	name: Config.string().required(),
	processValue: Config.func()
};

export {Config} from 'metal-jsx';

export default FormComponent;