import FormComponent, {Config} from './FormComponent';
import InputList from '../InputList';

class FormInputList extends FormComponent {
	created() {
		this.handleChange_ = this.handleChange_.bind(this);

		this.state.value_ = this.props.initialValue;
	}

	handleChange_(value) {
		this.state.value_ = value;
	}

	render() {
		return (
			<InputList
				{...this.otherProps()}
				allRemovable={true}
				items={this.state.value_}
				onChange={this.handleChange_}
				placeholder={Liferay.Language.get('search')}
			/>
		);
	}
}

FormInputList.PROPS = {
	initialValue: Config.array().value([])
};

FormInputList.STATE = {
	value_: Config.value([])
};

export default FormInputList;