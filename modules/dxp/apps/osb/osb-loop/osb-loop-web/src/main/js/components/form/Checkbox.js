import Checkbox from '../Checkbox';
import FormComponent, {Config} from './FormComponent';

class FormCheckbox extends FormComponent {
	created() {
		this.handleChange_ = this.handleChange_.bind(this);

		this.state.value_ = this.props.initialValue;
	}

	handleChange_() {
		this.state.value_ = !this.state.value_;
	}

	render() {
		return (
			<Checkbox
				{...this.otherProps()}
				checked={this.state.value_}
				name={this.props.name}
				onChange={this.handleChange_}
			/>
		);
	}
}

FormCheckbox.PROPS = {
	initialValue: Config.bool().value(false)
};

FormCheckbox.STATE = {
	value_: Config.value(false)
};

export default FormCheckbox;