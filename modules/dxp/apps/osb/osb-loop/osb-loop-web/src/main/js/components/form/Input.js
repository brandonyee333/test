import getCN from 'classnames';
import {bindAll, uniqueId} from 'lodash';

import FormComponent, {Config} from './FormComponent';
import Input from '../Input';
import Label from './Label';
import validateValue from '../../lib/form-validators';

class FormInput extends FormComponent {
	created() {
		this._id = uniqueId('FormInput');

		const {initialValue} = this.props;

		if (initialValue) {
			this.state.value_ = initialValue;
		}

		bindAll(
			this,
			'handleInput_',
			'handleBlur_'
		);
	}

	handleBlur_(event) {
		this.validateFormElement();
	}

	handleInput_(event) {
		this.state.value_ = event.target.value;

		if (this.state.show_) {
			this.validateFormElement();
		}
	}

	isRequired_() {
		const {validator} = this.props;

		return validator && (validator === 'required' || validator.hasOwnProperty('required'));
	}

	validateFormElement() {
		const {validator} = this.props;

		let retVal = true;

		if (validator) {
			if (this._validateRequest) {
				this._validateRequest.cancel();
			}

			this._validateRequest = validateValue(this.state.value_, validator);

			retVal = this._validateRequest.then(
				result => {
					if (result instanceof Array) {
						result = result.find(item => !item.valid);
					}

					const valid = result ? result.valid : true;

					this.setState(
						{
							message_: result ? result.message : '',
							show_: true,
							valid_: valid
						}
					);

					return valid;
				}
			);
		}

		return retVal;
	}

	syncInitialValue(initialValue) {
		if (initialValue) {
			this.state.value_ = initialValue;
		}
	}

	render() {
		const {
			props: {label, name},
			state: {message_, show_, valid_, value_}
		} = this;

		let status = 'default';

		if (show_) {
			status = valid_ ? 'success' : 'error';
		}

		return (
			<div class={getCN('form-input-container', status)}>
				{label &&
					<Label for={this._id} required={this.isRequired_()} text={label} />
				}

				<Input
					{...this.otherProps()}
					id={this._id}
					name={name}
					onBlur={this.handleBlur_}
					onInput={this.handleInput_}
					role={status}
					value={value_}
				/>

				{show_ && !valid_ &&
					<div class="error-message">{message_}</div>
				}
			</div>
		);
	}
}

FormInput.PROPS = {
	initialValue: Config.string().value(''),
	label: Config.string(),
	validateOnBlur: Config.bool().value(true),
	validator: Config.oneOfType(
		[
			Config.string(),
			Config.object()
		]
	)
};

FormInput.STATE = {
	message_: Config.value(''),
	show_: Config.value(false),
	valid_: Config.value(true),
	value_: Config.value('')
};

export default FormInput;