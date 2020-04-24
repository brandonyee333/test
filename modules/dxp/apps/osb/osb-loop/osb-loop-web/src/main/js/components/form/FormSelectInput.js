import FormComponent, {Config} from './FormComponent';
import {uniqueId} from 'lodash';

import Label from './Label';
import SelectInput from '../SelectInput';

class FormSelectInput extends FormComponent {
	created() {
		this._id = uniqueId('formSelectInput');

		this.handleSelect_ = this.handleSelect_.bind(this);

		this.state.value_ = this.props.initialValue;
	}

	handleSelect_(value) {
		this.state.value_ = value;
	}

	render() {
		const {label} = this.props;

		return (
			<div>
				{label &&
					<Label for={this._id} text={label} />
				}

				<SelectInput
					{...this.otherProps()}
					onSelect={this.handleSelect_}
					selectedItem={this.state.value_}
					selectId={this._id}
				/>
			</div>
		);
	}
}

FormSelectInput.PROPS = {
	initialValue: Config.object().required(),
	label: Config.string()
};

FormSelectInput.STATE = {
	value_: Config.object()
};

export default FormSelectInput;