import Component, {Config} from 'metal-jsx';
import Pikaday from 'pikaday';
import {uniqueId} from 'lodash';

import Input from './Input';
import Label from './Label';

export function getDateObject(value, name) {
	const date = new Date(value);

	return {
		[`${name}Day`]: date.getDate(),
		[`${name}Month`]: date.getMonth(),
		[`${name}Year`]: date.getFullYear()
	};
}

class FormDatePicker extends Component {
	created() {
		this._id = uniqueId('formFormDatePicker');

		this.handleChange_ = this.handleChange_.bind(this);

		const {initialValue} = this.props;

		if (initialValue) {
			const date = new Date(initialValue);

			this.state.value_ = `${date.getMonth() + 1}/${date.getDate()}/${date.getFullYear()}`;
		}
	}

	attached() {
		const {
			props: {disabled},
			refs: {input},
			state: {value_}
		} = this;

		if (!disabled) {
			this._datePicker = new Pikaday(
				{
					field: input.element,
					format: 'L',
					onSelect: this.handleChange_
				}
			);

			this._datePicker.setDate(value_);
		}
	}

	detached() {
		if (this._datePicker && this._datePicker.destroy) {
			this._datePicker.destroy();
		}
	}

	handleChange_() {
		if (this._datePicker) {
			this.state.value_ = this._datePicker.toString();
		}
	}

	render() {
		const {
			props: {disabled, label, processValue},
			state: {value_}
		} = this;

		return (
			<div>
				{label &&
					<Label for={this._id} text={label} />
				}

				<Input
					{...this.otherProps()}
					disabled={disabled}
					initialValue={value_}
					processValue={processValue}
					ref="input"
				/>
			</div>
		);
	}
}

FormDatePicker.PROPS = {
	disabled: Config.bool(),
	initialValue: Config.string(),
	label: Config.string(),
	processValue: Config.func().value(getDateObject)
};

FormDatePicker.STATE = {
	value_: Config.value('')
};

export default FormDatePicker;